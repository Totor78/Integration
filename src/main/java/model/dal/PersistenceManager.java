package model.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PersistenceManager {

    private static final String DB_URL = "jdbc:mariadb://127.0.0.1:3307/mspr?user=root&password=";

    private static Connection connection;

    private PersistenceManager() {}

    static Connection getConnection() throws SQLException {
        if ( null == connection || connection.isClosed() ) {
            connection = DriverManager.getConnection( DB_URL );
        }

        return connection;
    }

    public static void closeConnection() throws SQLException {
        if ( null != connection && !connection.isClosed() ) {
            connection.close();
        }
    }
}