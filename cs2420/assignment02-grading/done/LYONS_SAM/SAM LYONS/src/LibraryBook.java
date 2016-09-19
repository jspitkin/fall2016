package assignment02;

import java.util.GregorianCalendar;

/**
 * Class representation of a library book. A LibraryBook object will contain the ISBN, author, title, name of the
 * holder, and the due date of the book. Additionally, it will indicate whether or not the book is checked in.
 * 
 * @author Samuel Lyons u0861439
 */
public class LibraryBook extends Book
{
    private boolean checkedIn;
    private String holder;
    private GregorianCalendar dueDate;

    public LibraryBook (long isbn, String author, String title)
    {
        super(isbn, author, title);

        checkedIn = true;
        holder = null;
        dueDate = null;
    }

    /**
     * 
     * @return the name of this library book's holder
     */
    public String getHolder ()
    {
        return holder;
    }

    /**
     * 
     * @return the date the book is due back
     */
    public GregorianCalendar getDueDate ()
    {
        return dueDate;
    }

    /**
     * 
     * @return true if the book is checked in
     * @return false if the book is checked out
     */
    public boolean isCheckedIn ()
    {
        if (checkedIn)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Checks a book in
     */
    public void checkBookIn ()
    {
        checkedIn = true;

        holder = null;
        dueDate = null;
    }

    /**
     * Checks a book out
     * 
     * @param date The date the book is due back
     * @param holder The name of the person checking the book out
     */
    public void checkBookOut (GregorianCalendar date, String holder)
    {
        checkedIn = false;
        this.holder = holder;
        this.dueDate = date;
    }
}