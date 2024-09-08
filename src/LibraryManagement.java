import java.sql.*;

public class LibraryManagement {


    String url = "jdbc:postgresql://localhost:5432/";
    String db = "librarymanagement";
    String username = "postgres";
    String password = "ADMIN123";

    public void addBook(int bID, String bTitle, int bYOP) throws SQLException {
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
                    System.out.printf("%-15s %-20s %-10s %n", "Book ID", "Book Name", "YOP");
                }
                System.out.printf("%-15d %-20s %-10d %n", rs.getInt(1), rs.getString(2), rs.getInt(3));
            }

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
}
