package library;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private Integer bookId;
    private String title;
    private String author;
    private String edition;
    private BookStatus status;

    private List<BookAvailabilityListener> listeners = new ArrayList<>();
    public Book(Integer bookId, String title, String author, String edition) {
        if (bookId == null || title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.status = BookStatus.AVAILABLE;
    }
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException("bookId cannot be null");
        }
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("title cannot be null");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null) {
            throw new IllegalArgumentException("author name cannot be null");
        }
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public void checkout() {
        this.status = BookStatus.BORROWED;
    }

    public void returnBook() {
        this.status = BookStatus.AVAILABLE;
        notifyBookAvailable();
    }
    public void makeUnavailable() {
        this.status = BookStatus.NOT_AVAILABLE;
    }
    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", status=" + status +
                '}';
    }

    public void addAvailabilityListener(BookAvailabilityListener listener) {
        listeners.add(listener);
    }

    public void removeAvailabilityListener(BookAvailabilityListener listener) {
        listeners.remove(listener);
    }

    private void notifyBookAvailable() {
        for (BookAvailabilityListener listener: listeners) {
            listener.onBookAvailable(this);
        }
    }
}
