import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// all operations related to manage the books
public class LibraryManagement {

    String url = "jdbc:postgresql://localhost:5432/";
    String db = "librarymanagement";
    String username = "postgres";
    String password = "ADMIN123";

    // method to add books
    public void addBook(int bID, String bTitle, int bYOP) {
        try {
            Connection connection = DriverManager.getConnection(url + db, username, password);
            String query = "insert into books values "
                    + "(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bID);
            preparedStatement.setString(2, bTitle);
            preparedStatement.setInt(3, bYOP);
            preparedStatement.execute();

            System.out.println("\nBook Added Successfully\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    // method to display all books
    public void showBooks() {
        try {
            Connection connection = DriverManager.getConnection(url + db, username, password);
            Statement statement = connection.createStatement();
            String query = "Select * from books";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    // use this to display data in tabular form

                    System.out.printf("\n%-15s %-30s %-10s %n", "Book ID", "Book Name", "YOP");
                    System.out.println("-------------------------------------------------------");
                }
                System.out.printf("%-15d %-30s %-10d %n", resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getInt(3));
            }
            System.out.println();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // boolean method to check if the book is present or not
    public boolean isBookPresent(int bookID) {
        try {
            Connection connection = DriverManager.getConnection(url + db, username, password);
            String query = "select 1 from books where bookid = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, bookID);

            ResultSet resultSets = preparedStatement.executeQuery();
            return resultSets.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // method to delete the book ny bookID
    public void deleteBook(int bookID) {
        if (isBookPresent(bookID)) {
            try {

                Connection connection = DriverManager.getConnection(url + db, username, password);
                String query = "delete from books "
                        + "where bookid = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, bookID);
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0)
                    System.out.println("Book with ID " + bookID + " is deleted successfully");
                else
                    System.out.println("Book with ID " + bookID + " is not found in the DataBase");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else
            System.out.println("Book with ID " + bookID + " is not present in DB");
    }

    // method to update book
    public void updateBook(Scanner scanner) {
        System.out.println("Enter Book ID: ");
        int bid = scanner.nextInt();
        scanner.nextLine();

        // checking if book with bid is present in database or not
        if (isBookPresent(bid)) {

            System.out.println("1. Update Book's Name ");
            System.out.println("2. Update Book's YOP ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.println("Enter new name: ");
                String newBookName = scanner.nextLine();

                update(bid, "bookname", newBookName);

                System.out.println("Book Name changed successfully");

            } else if (choice == 2) {
                System.out.println("Enter new YOP: ");
                int newYOP = scanner.nextInt();

                update(bid, "yop", newYOP);

                System.out.println("Book YOP changed successfully");

            } else {
                System.out.println("wrong choice entered");
            }

        } else
            System.out.println("Book with ID " + bid + " is not found in database");

    }

    // to update book using generic method
    public <T> void update(int BookID, String column, T newValue) {
        try {
            Connection connection = DriverManager.getConnection(url + db, username, password);

            String query = "update books "
                    + "set " + column + " = ? "
                    + " where bookid = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setObject(1, newValue);
            preparedStatement.setInt(2, BookID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
                System.out.println("Book modified successfully");
            else
                System.out.println("Executed successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}