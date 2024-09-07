import java.util.Scanner;

public class LibraryManagementSystemMain {
    public static void main(String[] pk) {
        LibraryMangement lm = new LibraryMangement();
        Scanner sc = new Scanner(System.in);
        boolean runner = true;
        while (runner) {
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Check Availability Book");
            System.out.println("4. Show available books");
            System.out.println("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
//                    add method
                    break;
                case 2:
//                     add method
                    break;
                case 3:
//                    add method
                    break;
                case 4:
//                    add method
                case 5:
                    System.out.println("Bye and keep learning");
                    runner = false;
                    break;

                default:
                    System.out.println("Please choose correct option");
            }

        }
    }
}
