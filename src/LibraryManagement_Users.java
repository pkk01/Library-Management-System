import java.util.Scanner;

public class LibraryManagement_Users {
    UserOperations userOperations = new UserOperations();

    public void manageUser(Scanner scanner) {
        boolean runner = true;

        while (runner) {
            // crud operations for user
            System.out.println("1. Add User ");
            System.out.println("2. Remove User");
            System.out.println("3. Show Users");
            System.out.println("4. Update  Users");
            System.out.println("5. Go to main menu");

            int choice = scanner.nextInt();
            // Switch cases

            switch (choice) {
                case 1:
                    // add user

                    System.out.println("Enter user Id: ");
                    int userID = scanner.nextInt();
                    System.out.println("Enter user's name: ");
                    String uName = scanner.nextLine();
                    System.out.println("Enter book id to issued: ");
                    int bookId = scanner.nextInt();
//                    userOperations.addUser(userID, uName, bookId);
                    break;
                case 2:
                    // remove user
                    break;
                case 3:
                    // Display all users
                    break;
                case 4:
                    // update user details
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    runner = false;
                    break;

                default:
                    System.out.println("Wrong value entered");
            }
        }

    }
}
