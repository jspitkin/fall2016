package assignment02;

import java.util.GregorianCalendar;

/**
 * LibraryBook - inherits from Book. It adds two member variables, dueDate and holder. 
 * 
 * @author Kyle Price
 * 09/07/2016
 */
public class LibraryBook extends Book {
	
	String holder; // A string "name" of the person who has the book checked out. 
	GregorianCalendar dueDate; // A GregorianCalendar object of when the book is due. 
	
	// I initialize these to null so that checkIn and checkOut are immediately usable and valid.
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		dueDate = null;
		holder = null;
	}
	
	/**
	 * getHolder - a getter for the current holder of the book.
	 * 
	 * @return - a String version of the holder of the book. 
	 */
	public String getHolder() {
		return holder;
	}
	
	/**
	 * getDueDate - is a getter for the dueDate of the book. 
	 * 
	 * @return
	 */
	public GregorianCalendar getDueDate() { 
		return dueDate;
	}
	
	/**
	 * checkIn - sets the holder and dueDate values to null, therefore "checking them in". 
	 */
	public void checkIn() {
		this.holder = null;
		this.dueDate = null;
	}
	
	/**
	 * checkOut - sets the holder to the parameter "name" and dueDate to the parameter "dueDate". 
	 * therefore "checking it out".
	 * 
	 * @param name - A String denoting the name of the holder.
	 * @param dueDate - a GregorianCalendar object denoting the dueDate of the book.
	 */
	public void checkOut(String name, GregorianCalendar dueDate) { 
		this.holder = name;
		this.dueDate = dueDate;
	}

}
