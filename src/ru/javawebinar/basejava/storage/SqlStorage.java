package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("delete from resume");
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume r;
            try (PreparedStatement ps = conn
                    .prepareStatement( "select * from resume r where r.uuid =? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                r = new Resume(uuid, rs.getString("full_name"));
            }
            try (PreparedStatement ps = conn
                    .prepareStatement( "select c.type, c.value from contact c " +
                            " where c.resume_uuid =? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addResumeContact(r, rs);
                }
            }
            try (PreparedStatement ps = conn
                    .prepareStatement( "select s.type, s.content from section s " +
                            " where s.resume_uuid =? ")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addResumeSection(r, rs);
                }
            }
            return r;
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn
                    .prepareStatement("update resume set full_name = ? where uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, r.getUuid());
                if (ps.executeUpdate() != 1) {
                    throw new NotExistStorageException(r.getUuid());
                }
            }
            removeDbContacts(r, conn);
            addDbContacts(r, conn);
            removeDbSections(r, conn);
            addDbSections(r, conn);
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("insert into resume (uuid, full_name) values (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.executeUpdate();
            }
            addDbContacts(r, conn);
            addDbSections(r, conn);
            return  null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("delete from resume where uuid=?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(connection -> {
            Map<String, Resume> resumeMap = new LinkedHashMap<>();
            try (PreparedStatement ps = connection.prepareStatement("select * from resume r " +
                    " order by r.full_name, r.uuid")) {
                ResultSet rsResume = ps.executeQuery();
                while (rsResume.next()) {
                    String uuid = rsResume.getString("uuid");
                    resumeMap.put(uuid, new Resume(uuid, rsResume.getString("full_name")));
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("" +
                    "select c.resume_uuid, c.type, c.value " +
                    "from contact c ")) {
                ResultSet rsContact = ps.executeQuery();
                while (rsContact.next()) {
                    addResumeContact(resumeMap.get(rsContact.getString("resume_uuid")), rsContact);
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("select s.resume_uuid, s.type, s.content " +
                    "from section s ")) {
                ResultSet rsSection = ps.executeQuery();
                while (rsSection.next()) {
                    addResumeSection(resumeMap.get(rsSection.getString("resume_uuid")), rsSection);
                }
            }
            return new ArrayList<>(resumeMap.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("select count(*) from resume", ps -> {
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    private void addDbContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("insert into contact(resume_uuid, type, value) values (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
    private void addDbSections(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn
                .prepareStatement("insert into section (resume_uuid, type, content) values (?,?,?)")) {
            for (Map.Entry<SectionType, AbstractSection> e : r.getSections().entrySet()) {
                if (e.getValue() instanceof TextSection) {
                    ps.setString(3, ((TextSection) e.getValue()).getText());
                } else if (e.getValue() instanceof ListSection) {
                    String sectionText = String.join("\n", ((ListSection) e.getValue()).getItems());
                    ps.setString(3, sectionText);
                }
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void removeDbContacts(Resume r, Connection conn) {
        sqlHelper.execute("delete  from contact where resume_uuid=?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    private void removeDbSections(Resume r, Connection conn) {
        sqlHelper.execute("delete  from section where resume_uuid=?", ps -> {
            ps.setString(1, r.getUuid());
            ps.execute();
            return null;
        });
    }

    private void addResumeContact(Resume r, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            r.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addResumeSection(Resume r, ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        String value = rs.getString("value");
        if (type != null) {
            if (!value.contains("\n")) {
                r.addSection(SectionType.valueOf(type), new TextSection(value));
            } else {
                r.addSection(SectionType.valueOf(type),
                        new ListSection(Arrays.asList(value.split("\n"))));
            }
        }
    }

}