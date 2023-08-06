package library;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Library {
    private String libraryName;
    private String libraryCode;
    private List<Book> books;
    private List<User> users;
    private static final Library instance = new Library("Default-Library", "LIB-01");

    private Library(String name, String code) {
        this.libraryName = name;
        this.libraryCode = code;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public static Library getInstance() {
        return instance;
    }

    public Library withName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null!");
        }
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty!");
        }
        this.libraryName = name;
        return this;
    }

    public Library withCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("code cannot be null!");
        }
        if (code.trim().isEmpty()) {
            throw new IllegalArgumentException("code cannot be empty!");
        }
        this.libraryCode = code;
        return this;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public String getLibraryCode() {
        return libraryCode;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Null argument passed!");
        }
        for (Book existeingBook: books) {
            if (Objects.equals(existeingBook.getBookId(), book.getBookId())) {
                throw new IllegalArgumentException("Object with the same bookId already exists!");
            }
        }
        this.books.add(book);
    }


    public void removeBook(Integer bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException("bookId cannot be null!");
        }
        if (bookId < 0) {
            throw new IllegalArgumentException("bookId cannot be less than zero!");
        }

        List<Book> filteredBooks = books.stream().filter(b -> Objects.equals(b.getBookId(), bookId)).collect(Collectors.toList());
        if (filteredBooks.isEmpty()) {
            throw new IllegalArgumentException("The Library has no book with bookId=" + bookId);
        } else {
            books.remove(filteredBooks.get(0));
        }
    }

    public void addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null!");
        }
        for (User existingUser: users) {
            if (user == existingUser) { // pointing to the same object
                throw new IllegalArgumentException("User already exists!");
            }
        }
        this.users.add(user);
    }


    public void removeUser(Integer userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null!");
        }
        if (userId < 0) {
            throw new IllegalArgumentException("userId cannot be less than zero!");
        }

        List<User> filteredUsers = users.stream().filter(b -> Objects.equals(b.getUserId(), userId)).collect(Collectors.toList());
        if (filteredUsers.isEmpty()) {
            throw new IllegalArgumentException("The Library has no user with userId=" + userId);
        } else {
            users.remove(filteredUsers.get(0));
        }
    }

    public void displayAvailableBooks() {
        System.out.println("Available Books:");
        for (Book book : books) {
            if (book.getStatus() == BookStatus.AVAILABLE) {
                System.out.println(book);
            }
        }
    }

    public void displayBorrowedBooks() {
        System.out.println("Borrowed Books:");
        for (Book book : books) {
            if (book.getStatus() == BookStatus.BORROWED) {
                System.out.println(book);
            }
        }
    }

    public void resetInstance() {
        this.books.clear();
        this.users.clear();
        this.libraryCode = "LIB-01";
        this.libraryName = "Default-Library";
    }
}
