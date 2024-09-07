import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Admin_TablesManager {
    String url = "jdbc:postgresql://localhost:5432/";
    String db = "librarymanagement";
    String username = "postgres";
    String password = "ADMIN123";

    public static void main(String[] pk) {
        Admin_TablesManager tablesManger = new Admin_TablesManager();

//        tablesManger.createTableBooks();
        tablesManger.createTableUsers();
    }

    private void createTableUsers() {

    }

    private void createTableBooks() {
        try {
            Connection conn = DriverManager.getConnection(url+db,username,password);
            String query = "Create table Books (BookID int primary key, BookName varchar (30) not null, YOP date not null)";
            Statement stm = conn.createStatement();
            boolean check = stm.execute(query);
            if (check)
                System.out.println("Done");
            else
                System.out.println("Gotcha");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
