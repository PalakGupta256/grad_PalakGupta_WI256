import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        try {
            // Wait for database container to start
            Thread.sleep(5000);

            String url = "jdbc:postgresql://db:5432/studentdb";
            String user = "postgres";
            String password = "Palak@123";

            Connection conn = DriverManager.getConnection(url, user, password);

            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO students(name, age) VALUES('Alice', 21)";
            stmt.executeUpdate(sql);

            System.out.println("Record inserted successfully!");

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.out.println("Container interrupted.");
        }
    }
}