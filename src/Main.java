import java.util.*;
public class Main{
    public static void main(String[] args){
        Library library=new Library();
        Scanner sc=new Scanner(System.in);
        while(true){
            System.out.println("\n===== Library Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Search Book");
            System.out.println("5. Rate Book");
            System.out.println("6. View History");
            System.out.println("7. View All Books");
            System.out.println("8. View Reservations for a Book");
            System.out.println("9. Show book ratings");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id=sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title=sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author=sc.nextLine();
                    System.out.print("Enter Book quantity: ");
                    int quantity=sc.nextInt();
                    library.addBook(new Book(id, title, author, quantity));
                    break;
                case 2:
                    System.out.print("Enter Book ID to borrow: ");
                    int bid=sc.nextInt(); sc.nextLine();
                    System.out.print("Enter your name: ");
                    String user=sc.nextLine();
                    library.borrowBook(bid, user);
                    break;
                case 3:
                    System.out.print("Enter Book ID to return: ");
                    library.returnBook(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter keyword to search: ");
                    String keyword=sc.nextLine();
                    library.searchBook(keyword);
                    break;
                case 5:
                    System.out.print("Enter Book ID to rate: ");
                    int rateId=sc.nextInt();
                    System.out.print("Enter your rating (1 to 5): ");
                    int rating=sc.nextInt();
                    library.rateBook(rateId, rating);
                    break;
                case 6:
                    library.showHistory();
                    break;
                case 7:
                    library.listAllBooks();
                    break;
                case 8:
                    System.out.print("Enter Book ID to check reservations: ");
                    int resId=sc.nextInt();
                    library.showReservationsForBook(resId);
                    break;
                case 9:
                    System.out.print("Enter Book ID to view rating: ");
                    int rid=c.nextInt();
                    library.showBookRating(rid);
                    break;

                case 10:
                    System.out.println("Exiting Library System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
