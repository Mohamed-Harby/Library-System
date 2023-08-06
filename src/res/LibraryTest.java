package res;

import library.Book;
import library.Library;
import library.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

    @BeforeEach
    void setUp() {
        Library.getInstance().resetInstance();
    }

    @Test
    void getInstanceAlwaysReturnSameObject() {
        Library library1 = Library.getInstance();
        Library library2 = Library.getInstance();
        assertSame(library1, library2);
    }

    // Name changes tests
    @Test
    void changeLibraryNameCorrectly() {
        assertEquals("Aprintage", Library.getInstance().withName("Aprintage").getLibraryName());
    }

    @Test
    void changeLibraryNameToNull() {
        assertThrows(IllegalArgumentException.class, () -> Library.getInstance().withName(null));
    }

    @Test
    void changeLibraryNameToEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> Library.getInstance().withName(""));
    }

    @Test
    void changeLibraryNameToSpacesString() {
        assertThrows(IllegalArgumentException.class, () -> Library.getInstance().withName("     "));
    }

    // Code changes tests
    @Test
    void changeLibraryCodeCorrectly() {
        assertEquals("LIB-00", Library.getInstance().withCode("LIB-00").getLibraryCode());
    }

    @Test
    void changeLibraryCodeToNull() {
        assertThrows(IllegalArgumentException.class, () -> Library.getInstance().withCode(null));
    }

    @Test
    void changeLibraryCodeToEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> Library.getInstance().withCode(""));
    }

    @Test
    void changeLibraryCodeToSpacesString() {
        assertThrows(IllegalArgumentException.class, () -> Library.getInstance().withCode("     "));
    }

    @Test
    void noBooksReturnsNonNullObject() {
        assertNotNull(Library.getInstance().getBooks());
    }

    @Test
    void getBooksNeverReturnsNull() {
        assert(Library.getInstance().getBooks() != null); // must return empty list if no books
    }
    @Test
    void getUsersNeverReturnsNull() {
        assert(Library.getInstance().getUsers() != null); // must return empty list if no users
    }

    @Test
    void addNullBookThrowsNullArgException() {
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().addBook(null)
        );
    }

    @Test
    void addTheSameBookTwice() {
        Library.getInstance().addBook(new Book(0, "title", "author", "edition"));
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().addBook(new Book(0, "title", "author", "edition"))
        );
    }

    @Test
    void addBookWithSameId() {
        Library.getInstance().addBook(new Book(3, "Jungles", "Ibrahim", "First"));
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().addBook(new Book(3, "The Monsters", "Lee Yang", "Third"))
        );
    }
    @Test
    void removeNullBookThrowsNullArgException() {
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().removeBook(null)
        );
    }

    @Test
    void removeBookWithNotValidIdThrowsNullArgException() {
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().removeBook(-1)
        );
    }

    @Test
    void removeBookWithNotExistingBookThrowsNullArgException() {
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().removeBook(99)
        );
    }

    @Test
    void removeBookWorksProperlyWithRealObjects() {
        Library.getInstance().addBook(new Book(0, "title", "author", "edition"));
        Library.getInstance().removeBook(0);
        List<Book> draftList = Library.getInstance().getBooks().stream().filter(book -> book.getBookId() == 0).collect(Collectors.toList());
        assertEquals(0, draftList.size());
    }


    @Test
    void addingNullUserReturnsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().addUser(null)
        );
    }

    @Test
    void addTheSameUserTwice() {
        User user = new User(0, "username");
        Library.getInstance().addUser(user);
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().addUser(user)
        );
    }

    @Test
    void removeNullUserThrowsNullArgException() {
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().removeUser(null)
        );
    }

    @Test
    void removeUserWithNotValidIdThrowsNullArgException() {
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().removeUser(-1)
        );
    }

    @Test
    void removeUserWithNotExistingBookThrowsNullArgException() {
        assertThrows(IllegalArgumentException.class,
                () -> Library.getInstance().removeUser(99)
        );
    }

    @Test
    void removeUserWorksProperlyWithRealObjects() {
        Library.getInstance().addUser(new User(0, "userName"));
        Library.getInstance().removeUser(0);
        List<User> draftList = Library.getInstance().getUsers().stream().filter(user -> user.getUserId() == 0).collect(Collectors.toList());
        assertEquals(0, draftList.size());
    }
}