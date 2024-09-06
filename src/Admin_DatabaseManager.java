import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Admin_DatabaseManager {

//    this class will all the admin operations which are not related
//    and should not be modified by the client

//    function to create database
    private void createDatabase() {
        try {
            String url = "jdbc:postgresql://localhost:5432/";
            String username = "postgres";
            String password = "ADMIN123";
            Connection conn = DriverManager.getConnection(url,username,password);

            Statement stm = conn.createStatement();
            String query = "Create database LibraryManagement";
            stm.execute(query);
            System.out.println("Database Created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] pk) {
        Admin_DatabaseManager ad = new Admin_DatabaseManager();
        ad.createDatabase();
    }
}
