package assignment02;

import java.util.GregorianCalendar;

/**
 * Class representation of a library book. The ISBN, author, and title can never change
 * once the book is created. The holder and due date change when the LibraryBook is checked 
 * out or returned.
 *  
 * @author Nathaniel Coleman
 *
 */

public class LibraryBook extends Book {
	
	private String holder;
	private GregorianCalendar dueDate;

	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		
		holder = null;
		dueDate = null;
	}
	/**
	 * @return the holder
	 */
	public String getHolder()
	{
		return this.holder;
	}
	/**
	 * Modifier method for holder
	 */
	public void setHolder(String holder)
	{
		this.holder = holder;
	}
	/**
	 * @return the due date
	 */
	public GregorianCalendar getDueDate()
	{
		return this.dueDate;
	}
	/**
	 * Modifier method for due date
	 */
	public void setDueDate(GregorianCalendar dueDate)
	{
		this.dueDate = dueDate;
	}

	/**
	 * Takes a book out of the library and notes the name of the holder of the
	 * book and marks the due date.
	 * @param holder - name of person that checks book out
	 * @param dueDate - when the book is due to be returned
	 */
	public void checkOut(String holder, GregorianCalendar dueDate)
	{
		this.holder = holder;
		this.dueDate = dueDate;
	}
	
	/**
	 * Checks a book back into the library. Holder name and due date are forgotten
	 */
	public void checkIn()
	{
		this.holder = null;
		this.dueDate = null;
	}
	/**
	 * Returns true if book is checked in. False otherwise.
	 */
	public boolean checkStatus()
	{
		if (this.holder == null)
			return true;
		
		return false;
	}
}
