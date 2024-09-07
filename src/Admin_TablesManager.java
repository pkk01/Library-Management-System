import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_TablesManager {
    String url = "jdbc:postgresql://localhost:5432/";
    String db = "librarymanagement";
    String username = "postgres";
    String password = "ADMIN123";

    public static void main(String[] pk) {
        Admin_TablesManager tablesManger = new Admin_TablesManager();

        // tablesManger.createTableBooks();
        tablesManger.createTableUsers();
    }

    private void createTableUsers() {
        try {
            Connection conn = DriverManager.getConnection(url + db, username, password);
            String query = "create table Users "
                    + "(UserID int primary key, "
                    + "UserName varchar (30) not null, "
                    + "BookID int not null, "
                    + "FOREIGN KEY (BookID) REFERENCES Books(bookid))";
            Statement stm = conn.createStatement();
            stm.execute(query);
            System.out.println("Table added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableBooks() {
        try {
            Connection conn = DriverManager.getConnection(url + db, username, password);
            String query = "Create table Books (BookID int primary key, BookName varchar (30) not null, YOP date not null)";
            Statement stm = conn.createStatement();
            boolean check = stm.execute(query);
            if (check)
                System.out.println("Done");
            else
                System.out.println("Gotcha");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
