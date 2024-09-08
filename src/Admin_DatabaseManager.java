import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Admin_DatabaseManager {

    // this class will all the admin operations which are not related
    // and should not be modified by the client

    // function to create database
    private void createDatabase() {
        try {
            String url = "jdbc:postgresql://localhost:5432/";
            String username = "postgres";
            String password = "ADMIN123";
            Connection connection = DriverManager.getConnection(url, username, password);

            Statement statement = connection.createStatement();
            String query = "Create database LibraryManagement";
            statement.execute(query);
            System.out.println("Database Created successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] pk) {
        Admin_DatabaseManager adminDatabaseManager = new Admin_DatabaseManager();
        adminDatabaseManager.createDatabase();
    }
}
