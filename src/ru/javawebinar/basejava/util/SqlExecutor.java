package ru.javawebinar.basejava.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface SqlExecutor<T> {
        public T execute(PreparedStatement preparedStatement) throws SQLException;
}
