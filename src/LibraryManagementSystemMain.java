import java.sql.SQLException;
import java.util.Scanner;

// main class to call out all the operation from different classes

public class LibraryManagementSystemMain {
    public static void main(String[] pk) throws SQLException {
        LibraryManagement_Books libraryManagementBooks = new LibraryManagement_Books();
        LibraryManagement_Users libraryManagementUsers = new LibraryManagement_Users();
        Scanner sc = new Scanner(System.in);
        boolean runner = true;
        while (runner) {

            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. Check Availability Book");
            System.out.println("5. Show available books");
            System.out.println("6. Manage User");
            System.out.println("7. Exit to main menu ");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                // add books
                case 1:

                    System.out.print("Enter Book ID: ");
                    int bID = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Book Title: ");
                    String bTitle = sc.nextLine();
                    System.out.print("Enter Author Name: ");
                    String bAuthor = sc.nextLine();
                    System.out.print("Enter Year of publication: ");
                    int bYOP = sc.nextInt();
                    libraryManagementBooks.addBook(bID, bTitle, bAuthor, bYOP);
                    break;

                // update books
                case 2:

                    // passing the same scanner object to clear the buffer and loop should not be
                    // terminated after execution
                    // use when another switch case is required called method
                    libraryManagementBooks.updateBook(sc);
                    break;

                // to delete book
                case 3:
                    System.out.print("Enter Book ID to delete: ");
                    int bookID = sc.nextInt();
                    libraryManagementBooks.deleteBook(bookID);
                    break;

                // check book availability --> boolean
                case 4:

                    System.out.print("Enter Book ID to search: ");
                    int bookid = sc.nextInt();
                    boolean isPresent = libraryManagementBooks.isBookPresent(bookid);
                    if (isPresent)
                        System.out.println("This Book is available");
                    else
                        System.out.println("Sorry This Book is currently not available");
                    break;

                // display all books
                case 5:
                    libraryManagementBooks.showBooks();
                    break;

                case 6:
//                   // method to manage the users
                    libraryManagementUsers.manageUser(sc);

                    // exiting the while loop
                case 7:
                    System.out.println("Bye and keep learning!!");
                    System.out.println("\n    ~DEVELOPED BY PK");
                    runner = false;
                    break;

                default:
                    System.out.println("Please choose correct option");
            }
        }
        sc.close();
    }
}
