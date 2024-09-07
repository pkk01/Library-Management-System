import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LibraryManagement {
    private int bID;
    private String bTitle;
    private int bYop;

    String url = "jdbc:postgresql://localhost:5432/";
    String db = "librarymanagement";
    String username = "postgres";
    String password = "ADMIN123";

    public void addBook(int bID, String bTitle, int bYOP) throws SQLException {
        this.bID = bID;
        this.bTitle = bTitle;
        this.bYop = bYOP;
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
            e.printStackTrace();
        }

    }
}
