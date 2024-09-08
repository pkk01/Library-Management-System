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
            System.out.println("3. Delete Book");
            System.out.println("4. Check Availability Book");
            System.out.println("5. Show available books");
            System.out.println("6. Exit to main menu: ");
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
                case 3: // to delete book
                    System.out.print("Enter Book ID to delete");
                    int bookID = sc.nextInt();
                    lm.deleteBook(bookID);
                    break;
                case 4: // check book availability -->  boolean

                    System.out.print("Enter Book ID to search: ");
                    int bookid = sc.nextInt();
                    boolean isPresent = lm.isBookPresent(bookid);
                    if (isPresent)
                        System.out.println("This Book is available");
                    else
                        System.out.println("Sorry This Book is currently not available");
                    break;

                case 5: // display all books
                    lm.showBooks();
                    break;
                case 6:
                    System.out.println("Bye and keep learning!!");
                    runner = false;
                    break;

                default:
                    System.out.println("Please choose correct option");
            }
        }
        sc.close();
    }
}
