package ru.kalantyr.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    public void execute(String sql) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(connectionString);
        }
        finally {
            if (connection != null)
                connection.close();
        }
    }
}
