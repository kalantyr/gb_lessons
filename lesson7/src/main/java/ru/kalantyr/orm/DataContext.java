package ru.kalantyr.orm;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

/**
 * Вспомогательный класс для работы с БД
 */
public class DataContext {
    private final String driverName;
    private final String connectionString;

    public DataContext(String driverName, String connectionString) {
        if (driverName == null || driverName.isEmpty())
            throw new IllegalArgumentException();
        this.driverName = driverName;

        if (connectionString == null || connectionString.isEmpty())
            throw new IllegalArgumentException();
        this.connectionString = connectionString;
    }

    /**
     * Выполняет указанный скрипт
     * @throws SQLException
     */
    public boolean execute(String sqlCommand) throws SQLException {
        try (var connection = DriverManager.getConnection(connectionString)) {
            try (var st = connection.prepareStatement(sqlCommand)) {
                return st.execute();
            }
        }
    }

    /**
     * Выполняет скрипты в транзакции
     * @throws SQLException
     */
    public void executeInTransation(Iterable<String> sqlCommands) throws SQLException {
        try (var connection = DriverManager.getConnection(connectionString)) {
            connection.setAutoCommit(false);
            try {
                for (var sql : sqlCommands) {
                    try (var statement = connection.prepareStatement(sql)) {
                        statement.execute();
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    /**
     * Делает выборку из БД
     * @throws SQLException
     */
    public void executeQuery(String sql, Consumer<ResultSet> consumer) throws SQLException {
        try (var connection = DriverManager.getConnection(connectionString)) {
            try (var st = connection.prepareStatement(sql)) {
                try (var resultSet = st.executeQuery()) {
                    consumer.accept(resultSet);
                }
            }
        }
    }
}
