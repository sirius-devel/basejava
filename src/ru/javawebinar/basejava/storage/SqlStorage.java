package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.AbstractSection;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.SectionType;
import ru.javawebinar.basejava.sql.SqlHelper;
import ru.javawebinar.basejava.util.JsonParser;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
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
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumeMap.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("" +
                    "select c.resume_uuid, c.type, c.value " +
                    "from contact c ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addResumeContact(resumeMap.get(rs.getString("resume_uuid")), rs);
                }
            }
            try (PreparedStatement ps = connection.prepareStatement("select s.resume_uuid, s.type, s.content " +
                    "from section s ")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addResumeSection(resumeMap.get(rs.getString("resume_uuid")), rs);
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
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, JsonParser.write(e.getValue(), AbstractSection.class));
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void removeDbContacts(Resume r, Connection conn) throws SQLException {
        deleteAttributes(r, conn,"delete  from contact where resume_uuid=?");
    }

    private void removeDbSections(Resume r, Connection conn) throws SQLException {
        deleteAttributes(r, conn, "delete  from section where resume_uuid=?");
    }

    private void deleteAttributes(Resume r, Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getUuid());
            ps.execute();
        }
    }

    private void addResumeContact(Resume r, ResultSet rs) throws SQLException {
        String value = rs.getString("value");
        if (value != null) {
            r.addContact(ContactType.valueOf(rs.getString("type")), value);
        }
    }

    private void addResumeSection(Resume r, ResultSet rs) throws SQLException {
        String content = rs.getString("content");
        if (content != null) {
            SectionType type = SectionType.valueOf(rs.getString("type"));
            r.addSection(type, JsonParser.read(content, AbstractSection.class));
        }
    }

}