package dentist_reserve;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String DB_URL = "jdbc:mariadb://localhost/reservation_system";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}