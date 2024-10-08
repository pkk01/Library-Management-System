import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin_TablesManager {

    // using same credential as of Books table to avoid repetition
    // and to maintain security for password leak
    LibraryManagement_Books libraryManagementBooks = new LibraryManagement_Books();
    String url = libraryManagementBooks.url;
    String db = libraryManagementBooks.db;
    String username = libraryManagementBooks.username;
    String password = libraryManagementBooks.password;

    public static void main(String[] pk) {
        Admin_TablesManager tablesManger = new Admin_TablesManager();
        tablesManger.createTableBooks();
//        tablesManger.createTableUsers();
    }

    private void createTableUsers() {
        try {
            Connection connection = DriverManager.getConnection(url + db, username, password);
            String query = "create table Users "
                    + "(UserID int primary key, "
                    + "UserName varchar (30) not null, "
                    + "BookID int not null, "
                    + "FOREIGN KEY (BookID) REFERENCES Books(bookid))";
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Table added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableBooks() {
        try {
            Connection connection = DriverManager.getConnection(url + db, username, password);
            String query = "Create table Books "
                    + "(BookID int primary key, "
                    + "BookName varchar (30) not null, "
                    + "BookAuthor varchar (30) not null, "
                    + "YOP int not null)";
            Statement statement = connection.createStatement();
            statement.execute(query);
            System.out.println("Gotcha");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
