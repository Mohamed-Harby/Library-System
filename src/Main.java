import library.Book;
import library.Library;
import library.User;


public class Main {
    public static void main(String[] args) {

        // Create a library object from the Singleton Library Class
        Library library = Library.getInstance().withName("Maktaba").withCode("LIB-001");

        // Create books
        Book book1 = new Book(1, "Amazon king", "Matt Johnson", "First");
        Book book2 = new Book(2, "Louisa", "Giff Dam", "First");
        Book book3 = new Book(3, "Willy drafts", "Andrew Jem", "Second");
        Book book4 = new Book(4, "Yellow Shark", "Maya Watson", "First");
        Book book5 = new Book(5, "Trip to land X", "Sami Ahmed", "Third");
        Book book6 = new Book(6, "Era of selfishness", "Ragi Samsa", "Fourth");

        // Add books to the library
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);

        // remove the last book
        library.removeBook(book6.getBookId());

        // Create the first user
        User firstUser = new User(1, "Ali");
        library.addUser(firstUser);

        // first user borrows some books
        firstUser.borrowBook(book1);
        /* output:
            Book "Amazon king" has been successfully borrowed!
         */
        firstUser.borrowBook(book2);
        /* output:
            Book "Louisa" has been successfully borrowed!
         */
        firstUser.borrowBook(book2); // illegal operation
        /* output:
            Book "Louisa" is not available for borrowing!
         */
        System.out.println("User borrowed books:");
        firstUser.getBorrowedBooks().forEach(System.out::println);
        /* output:
            User borrowed books:
            Book{bookId=1, title='Amazon king', author='Matt Johnson', edition='First', status=BORROWED}
            Book{bookId=2, title='Louisa', author='Giff Dam', edition='First', status=BORROWED}
         */

        library.displayAvailableBooks();
        /* output:
            Available Books:
                Book{bookId=3, title='Willy drafts', author='Andrew Jem', edition='Second', status=AVAILABLE}
                Book{bookId=4, title='Yellow Shark', author='Maya Watson', edition='First', status=AVAILABLE}
                Book{bookId=5, title='Trip to land X', author='Sami Ahmed', edition='Third', status=AVAILABLE}
         */
        library.displayBorrowedBooks();
        /* output:
            Available Books:
                Borrowed Books:
                    Book{bookId=1, title='Amazon king', author='Matt Johnson', edition='First', status=BORROWED}
                    Book{bookId=2, title='Louisa', author='Giff Dam', edition='First', status=BORROWED}
        */

        // Create the second user
        User secondUser = new User(2, "Fatma");

        // first user subscribes to the first book
        secondUser.subscribeToBook(book1); // second user must be notified when book1 is available
        /* output:
            Subscribed to  "Amazon king" successfully!
         */
        firstUser.returnBook(book1);
        /* output:
            For user: User{userId=2, userName='Fatma'}
            Book "Amazon king" is now available for borrowing.
            Book "Amazon king" has been successfully returned.
         */
        firstUser.subscribeToBook(book2);
        /* output:
            Can't subscribe to this book, you already borrowed it!
         */
        secondUser.unsubscribeFromBook(book1);
        /* output
            Unsubscribed to  "Amazon king" successfully!
         */
    }
}