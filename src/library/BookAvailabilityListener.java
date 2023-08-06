package library;

public interface BookAvailabilityListener {
    /*
        Interface used to notify the users who are listeners to their subscribed books.
        It is a utility used to make the Observer Pattern, to add functionality to users
        that can be executed using the books.
     */
    void onBookAvailable(Book book);
}
