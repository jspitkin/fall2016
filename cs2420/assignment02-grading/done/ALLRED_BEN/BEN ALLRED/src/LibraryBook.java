//Benjamin Allred
package assignment02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book {
	private String holder;
	private GregorianCalendar dueDate;
	
	/**
	 * call to super class constructor
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public LibraryBook(long isbn, String author, String title)
	{
		super(isbn, author, title);
	}
	
	/**
	 * 
	 * @return holder
	 */
	public String getHolder()
	{
		return holder;
	}
	
	/**
	 * 
	 * @return dueDate
	 */
	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}
	
	/**
	 * sets holder and dueDatet to what was passed in
	 * @param holder
	 * @param month
	 * @param day
	 * @param year
	 */
	public void checkOut(String holder, int month, int day, int year)
	{
		this.holder = holder;
		dueDate = new GregorianCalendar(year, month, day);
	}
	
	/**
	 * set holder and dueDate back to null
	 */
	public void checkIn()
	{
		holder = null;
		dueDate = null;
	}

}
