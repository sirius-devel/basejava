package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.util.SqlExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public <T> T request(String request, SqlExecutor<T> se) {
        try (Connection conn = this.connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(request)) {
            return se.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
