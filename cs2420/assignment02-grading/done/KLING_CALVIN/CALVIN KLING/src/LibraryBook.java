package assignment02;

import java.util.GregorianCalendar;

/**
 * Representation of a library book, used with the Laibrary class.
 * Includes holder and dueDate fields, can be checked in and out.
 * 
 * @author Calvin Kling
 * @version 9/6/2016
 */
public class LibraryBook extends Book
{
	private String holder;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title)
	{
		super(isbn, author, title);
		dueDate = null;
		holder = null;
	}
	
	/**
	 * Getter function for the value stored in the private class variable holder.
	 * 
	 * @return String representation of the book's current holder or "-1" if not checked out.
	 */
	public String getHolder()
	{
		return holder;
	}
	
	/**
	 * Getter function for the object referenced by the private class variable dueDate.
	 * 
	 * @return GregorianCalendar object representing the date which the book is to be returned to the library.
	 */
	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}
	
	/**
	 * Resets a book's due date and holder upon being returned to the library.
	 */
	public void checkin()
	{
		dueDate = null;
		holder = null;
	}
	
	/**
	 * Sets the holder and dueDate when the book is checked out.
	 * 
	 * @param user -- new holder of the library book.
	 * 
	 * @param month -- month of the new due date, January = 0.
	 * 
	 * @param day -- day of the new due date.
	 * 
	 * @param year -- year of the new due date.
	 */
	public void checkout(String user, int month, int day, int year)
	{
		holder = user;
		dueDate = new GregorianCalendar(year, month, day); 
	}
}
