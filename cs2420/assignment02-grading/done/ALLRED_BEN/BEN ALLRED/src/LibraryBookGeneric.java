package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{
	private Type holder;
	private GregorianCalendar dueDate;
	
	/**
	 * calls Book's constructor
	 * @param isbn
	 * @param author
	 * @param title
	 */
	
	public LibraryBookGeneric(long isbn, String author, String title)
	{
		super(isbn, author, title);
	}
	
	/**
	 * 
	 * @return holder
	 */
	
	public Type getHolder()
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
	 * sets holder and dueDate to whatever was passed in
	 * @param holder
	 * @param month
	 * @param day
	 * @param year
	 */
	public void checkOut(Type holder, int month, int day, int year)
	{
		this.holder = holder;
		dueDate = new GregorianCalendar(year, month, day);
	}
	
	/**
	 * sets holder and dueDate to null
	 */
	public void checkIn()
	{
		holder = null;
		dueDate = null;
	}

}
