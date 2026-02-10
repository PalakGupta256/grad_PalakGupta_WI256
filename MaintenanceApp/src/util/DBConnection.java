package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/layoutdb";
    private static final String USER = "postgres"; // your DB username
    private static final String PASSWORD = "Palak@123"; // your DB password

    public static Connection getConnection() throws SQLException {
        try {
            // Force load PostgreSQL driver (recommended for VS Code)
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
