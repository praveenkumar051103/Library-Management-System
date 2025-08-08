import java.util.*;
public class Library {
    List<Book> books = new LinkedList<>();
    Map<Integer, Book> bookMap = new HashMap<>();
    Map<Integer, Queue<String>> reservationMap = new HashMap<>();
    Stack<String> history = new Stack<>();
    public Library() {
        loadSampleBooks();
        for (Book book : books) {
            bookMap.put(book.id, book);
        }
    }
    private void loadSampleBooks(){
        books.add(new Book(101, "The Great Gatsby", "F. Scott Fitzgerald", 5));
        books.add(new Book(102, "To Kill a Mockingbird", "Harper Lee", 4));
        books.add(new Book(103, "1984", "George Orwell", 6));
        books.add(new Book(104, "Pride and Prejudice", "Jane Austen", 3));
        books.add(new Book(105, "The Hobbit", "J.R.R. Tolkien", 7));
        books.add(new Book(106, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 8));
        books.add(new Book(107, "The Catcher in the Rye", "J.D. Salinger", 4));
        books.add(new Book(108, "Moby-Dick", "Herman Melville", 2));
        books.add(new Book(109, "The Odyssey", "Homer", 3));
        books.add(new Book(110, "The Lord of the Rings", "J.R.R. Tolkien", 5));
        books.add(new Book(111, "The Alchemist", "Paulo Coelho", 6));
        books.add(new Book(112, "The Da Vinci Code", "Dan Brown", 4));
        books.add(new Book(113, "Brave New World", "Aldous Huxley", 5));
        books.add(new Book(114, "Sapiens", "Yuval Noah Harari", 7));
        books.add(new Book(115, "The Shining", "Stephen King", 3));
        books.add(new Book(116, "The Girl on the Train", "Paula Hawkins", 4));
        books.add(new Book(117, "The Book Thief", "Markus Zusak", 5));
        books.add(new Book(118, "Animal Farm", "George Orwell", 6));
        books.add(new Book(119, "The Fault in Our Stars", "John Green", 5));
        books.add(new Book(120, "A Game of Thrones", "George R. R. Martin", 4));
// Indian Authors
        books.add(new Book(201, "Midnight's Children", "Salman Rushdie", 3));
        books.add(new Book(202, "The White Tiger", "Aravind Adiga", 4));
        books.add(new Book(203, "The God of Small Things", "Arundhati Roy", 5));
        books.add(new Book(204, "Train to Pakistan", "Khushwant Singh", 2));
        books.add(new Book(205, "Interpreter of Maladies", "Jhumpa Lahiri", 3));
        books.add(new Book(206, "A Suitable Boy", "Vikram Seth", 2));
        books.add(new Book(207, "Five Point Someone", "Chetan Bhagat", 6));
        books.add(new Book(208, "Wings of Fire", "A.P.J. Abdul Kalam", 5));
        books.add(new Book(209, "The Inheritance of Loss", "Kiran Desai", 3));
        books.add(new Book(210, "The Palace of Illusions", "Chitra Banerjee Divakaruni", 4));
    }

    //    AddBook
    public void addBook(Book book) {
        books.add(book);
        bookMap.put(book.id, book);
        System.out.println("Book added: " + book.title);
    }

    //    Borrow
    public void borrowBook(int id, String user){
        Book book=bookMap.get(id);
        if(book!=null){
            if(book.isAvailable()){
                book.borrow();
                history.push("Borrowed Book: " + book.title + " by " + user);
                System.out.println(user + " borrowed: " + book.title);
            }else{
                System.out.println("No copies available. Adding you to reservation list.");
                reservedBook(id, user);
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    //     Return Book
    public void returnBook(int id) {
        Book book = bookMap.get(id);
        if (book != null) {
            book.returned();
            history.push("Returned Book: " + book.title);
            System.out.println("Returned: " + book.title);

            // Notify next person in queue
            Queue<String> queue = reservationMap.get(id);
            if (queue != null && !queue.isEmpty()) {
                String nextUser = queue.poll();
                System.out.println("Notified " + nextUser + ": Book is now available.");
            }

        } else {
            System.out.println("Book not found.");
        }
    }


    //     Search Book
    public void searchBook(String keyword) {
        boolean found = false;
        for (Book book : books) {
            if (book.title.toLowerCase().contains(keyword.toLowerCase()) || book.author.toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found matching: " + keyword);
        }
    }

    //   History
    public void showHistory(){
        System.out.println("Transaction History:");
        if(history.isEmpty()){
            System.out.println("No transactions yet.");
        }else{
            for(String r:history){
                System.out.println("- " + r);
            }
        }
    }

//    reserved Books
    public void reservedBook(int id, String user){
        Book book=bookMap.get(id);
        if(book==null){
            System.out.println("Book not found.");
            return;
        }
        reservationMap.putIfAbsent(id, new LinkedList<>());
        reservationMap.get(id).add(user);
        history.push("Reserved Book: " + book.title + " by " + user);
        System.out.println(user + " has been added to the reservation list for book: " + book.title);
    }

//    reserve list
public void showReservationsForBook(int bookId){
    if (!bookMap.containsKey(bookId)){
        System.out.println("Book not found.");
        return;
    }
    Queue<String> queue = reservationMap.get(bookId);
    if(queue==null || queue.isEmpty()){
        System.out.println("No reservations for Book ID: " + bookId);
        return;
    }
    System.out.println("Reservations for Book ID " + bookId + ": " + bookMap.get(bookId).getTitle());
    int pos=1;
    for(String user:queue){
        System.out.println(pos + ". " + user);
        pos++;
    }
}
//    List all Books
   public void listAllBooks(){
    if(books.isEmpty()){
        System.out.println("No books available.");
        return;
    }
    System.out.println("----- Book List -----");
    for (Book book:books) {
        System.out.println(book);  // uses the toString() method in Book class
    }
    System.out.println("----------------------");
   }
    public void rateBook(int bookId, int rating){
        Book book=bookMap.get(bookId);
        if(book==null){
            System.out.println("Book not found.");
            return;
        }
        if(rating<1 || rating>5){
            System.out.println("Invalid rating. Please enter a rating between 1 and 5.");
            return;
        }
        book.addRating(rating);
        System.out.println("Thank you! You rated \"" + book.getTitle() + "\" with " + rating + " stars.");
    }
    public void showBookRating(int bookId){
        Book book=bookMap.get(bookId);
        if(book==null){
            System.out.println("Book not found.");
            return;
        }
        System.out.println("Rating for \"" + book.getTitle() + "\": " +
                String.format("%.1f", book.getAverageRating()) + " stars.");
    }
}