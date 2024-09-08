import java.sql.SQLException;
import java.util.Scanner;

public class LibraryManagementSystemMain {
    public static void main(String[] pk) throws SQLException {
        LibraryManagement lm = new LibraryManagement();
        Scanner sc = new Scanner(System.in);
        boolean runner = true;
        while (runner) {
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Check Availability Book");
            System.out.println("4. Show available books");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: // add books

                    System.out.print("Enter Book ID: ");
                    int bID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String bTitle = sc.nextLine();
                    System.out.print("Enter Year of publication: ");
                    int bYOP = sc.nextInt();
                    lm.addBook(bID, bTitle, bYOP);
                    break;

                case 2: // update books
                    // add method
                    break;
                case 3: // check book availability --> can be boolean
                    // add method
                    break;
                case 4: // display all books
                    lm.showBooks();
                    break;
                case 5:
                    System.out.println("Bye and keep learning");
                    runner = false;
                    break;

                default:
                    System.out.println("Please choose correct option");
            }

        }
        sc.close();
    }
}
