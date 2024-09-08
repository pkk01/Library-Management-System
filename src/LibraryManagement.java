import java.sql.*;

public class LibraryManagement {


    String url = "jdbc:postgresql://localhost:5432/";
    String db = "librarymanagement";
    String username = "postgres";
    String password = "ADMIN123";

    public void addBook(int bID, String bTitle, int bYOP) {
        try {
            Connection conn = DriverManager.getConnection(url + db, username, password);
            String query = "insert into books values "
                    + "(?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, bID);
            pstm.setString(2, bTitle);
            pstm.setInt(3, bYOP);
            pstm.execute();

            System.out.println("\nBook Added Successfully\n");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void showBooks() {
        try {
            Connection conn = DriverManager.getConnection(url + db, username, password);
            Statement stm = conn.createStatement();
            String query = "Select * from books";
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                if (rs.isFirst()) {
                    System.out.printf("\n%-15s %-30s %-10s %n", "Book ID", "Book Name", "YOP");
                    System.out.println("-------------------------------------------------------");
                }
                System.out.printf("%-15d %-30s %-10d %n", rs.getInt(1),
                        rs.getString(2), rs.getInt(3));
            }
            System.out.println();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isBookPresent(int bookID) {
        try {
            Connection conn = DriverManager.getConnection(url + db, username, password);
            String query = "select 1 from books where bookid = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            pstm.setInt(1, bookID);
            ResultSet rs = pstm.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteBook(int bookID) {
        try {
            Connection conn = DriverManager.getConnection(url + db, username, password);
            String query = "delete from books "
                    + "where bookid = ?";
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setInt(1, bookID);
            int affectedRows = stm.executeUpdate();
            if (affectedRows > 0)
                System.out.println("Book with ID " + bookID + " is deleted successfully");
            else
                System.out.println("Book with ID " + bookID + " is not found in the DataBase");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}