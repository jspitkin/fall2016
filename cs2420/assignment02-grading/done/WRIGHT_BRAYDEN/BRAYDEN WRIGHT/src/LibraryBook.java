package assignment02;

/**
 * File created by Brayden Wright.
 */
import java.util.GregorianCalendar;

public class LibraryBook extends Book {
    private String holder;
    private GregorianCalendar dueDate;

    /**
     * The book's information, relatively self-explanatory
     * @param isbn -- book's ISBN
     * @param author -- book's author
     * @param title -- book's title
     */
    public LibraryBook(long isbn, String author, String title) {
        super(isbn, author, title);
    }

    /**
     * Gets the current holder of a book
     * @return the holder, null if not currently checked out
     */
    public String getHolder() {
        return this.holder;
    }

    /**
     * Gets the book's due date
     * @return the due date, null if not currently checked out
     */
    public GregorianCalendar getDueDate() {
        return this.dueDate;
    }

    /**
     * Checks the book back in to the library
     */
    public void checkIn() {
        this.holder = null;
        this.dueDate = null;
    }

    /**
     * Check the book out from the library with a due date of one week
     * @param holder -- recorded so the check-out can be tied to a person
     */
    public void checkOut(String holder, int month, int day, int year) {
        this.holder = holder;
        this.dueDate = new GregorianCalendar(year, month, day);
    }
}
