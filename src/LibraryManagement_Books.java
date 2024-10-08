import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// all operations related to manage the books
public class LibraryManagement_Books {

    public String url = "jdbc:postgresql://localhost:5432/";
    public String db = "librarymanagement";

    // add username and password directly
    public String username = System.getenv("DB_USERNAME");
    public String password = System.getenv("DB_PASSWORD");

    // method to add books
    public void addBook(int bID, String bTitle, String bAuthor, int bYOP) {
        String query = "insert into books values "
                + "(?,?,?,?)";

        // added try-with-resources to avoid leaking of resource and automatically
        // closed them

        try (Connection connection = DriverManager.getConnection(url + db, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, bID);
            preparedStatement.setString(2, bTitle);
            preparedStatement.setString(3, bAuthor);
            preparedStatement.setInt(4, bYOP);
            preparedStatement.execute();

            System.out.println("\nBook Added Successfully\n");
            connection.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    // method to display all books
    public void showBooks() {
        String query = "Select * from books order by bookid";
        try (Connection connection = DriverManager.getConnection(url + db, username, password);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                if (resultSet.isFirst()) {
                    // use this to display data in tabular form
                    System.out.printf("\n%-15s %-30s %-20s %-10s %n", "Book ID", "Book Name", "Author Name", "YOP");
                    System.out.println("----------------------------------------------------------------------------");
                }
                System.out.printf("%-15d %-30s %-20s %-10d %n", resultSet.getInt(1),
                        resultSet.getString(2), resultSet.getString(3),
                        resultSet.getInt(4)); // Assuming YOP is in the 4th column
            }
            // to add extra space after the output
            System.out.println();

        } catch (SQLException e) {
            System.err.println("Error displaying Books: " + e.getMessage());
        }
    }

    // boolean method to check if the book is present or not
    public boolean isBookPresent(int bookID) {
        String query = "select 1 from books where bookid = ?";
        try (Connection connection = DriverManager.getConnection(url + db, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSets = preparedStatement.executeQuery()) {

            preparedStatement.setInt(1, bookID);
            return resultSets.next();

        } catch (SQLException e) {
            System.err.println("Error checking book: " + e.getMessage());
            return false;
        }
    }

    // method to delete the book ny bookID
    public void deleteBook(int bookID) {
        String query = "delete from books "
                + "where bookid = ?";
        if (isBookPresent(bookID)) {
            try (Connection connection = DriverManager.getConnection(url + db, username, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                preparedStatement.setInt(1, bookID);
                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0)
                    System.out.println("Book with ID " + bookID + " is deleted successfully");
                else
                    System.out.println("No book found with ID " + bookID);

            } catch (Exception e) {
                System.err.println("Error deleting book: " + e.getMessage());
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
            // options available to update

            System.out.println("1. Update Book's Name ");
            System.out.println("2. Update Book's YOP ");
            System.out.println("3. Update Book's Author Name ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {

                System.out.print("Enter new Book name: ");
                String newBookName = scanner.nextLine();

                // calling update method with the parameters
                update(bid, "bookname", newBookName);

                System.out.println("Book Name changed successfully");

            } else if (choice == 2) {
                System.out.print("Enter new YOP: ");
                int newYOP = scanner.nextInt();

                update(bid, "yop", newYOP);

                System.out.println("Book YOP changed successfully");

            } else if (choice == 3) {
                System.out.println("Enter new Author Name for Book: ");
                String newAuthor = scanner.nextLine();
                update(bid, "bookauthor", newAuthor);
            } else {
                System.out.println("wrong choice entered");
            }

        } else
            System.out.println("Book with ID " + bid + " is not found in database");
    }

    // to update book using generic method
    public <T> void update(int BookID, String column, T newValue) {
        String query = "update books "
                + "set " + column + " = ? "
                + " where bookid = ? ";

        try (Connection connection = DriverManager.getConnection(url + db, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setObject(1, newValue);
            preparedStatement.setInt(2, BookID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
                System.out.println("Book modified successfully");
            else
                System.out.println("Executed successfully");

        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }
}