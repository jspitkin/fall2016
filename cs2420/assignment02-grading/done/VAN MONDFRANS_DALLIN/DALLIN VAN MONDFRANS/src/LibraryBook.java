/**
 * Assignment 2
 * Name: Dallin Van Mondfrans
 * uID: u0717113 
 * Date: September 7, 2016
 */

package assignment02;

import java.util.GregorianCalendar;

/**
 * Represents a book with a holder and dueDate when checked out. 
 * If the book has not been checked out or has been checked back in, 
 * then the holder and due date are set to null.
 * 
 * The dueDate is of type GregorianCalendar.
 */
public class LibraryBook extends Book {

	private String holder;
	private GregorianCalendar dueDate;
	
	/**
	 * Extends Book by adding private fields holder and dueDate
	 *  
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		this.holder = null;
		this.dueDate = null;
	}
	
	/**
	 * @return the holder
	 */
	public String getHolder() {
		return this.holder;
	}
	
	/**
	 * @return the dueDate
	 */
	public GregorianCalendar getDueDate() {
		return this.dueDate; 
	}
	
	/**
	 * Assigns this LibraryBook a holder and a dueDate
	 * 
	 * @param holderName
	 * @param dueDate
	 */
	public void checkBookOut(String holderName, GregorianCalendar dueDate) {
		this.holder = holderName;
		this.dueDate = dueDate; 
	}
	
	/**
	 *  Sets the holder and dueDate to null
	 */
	public void checkBookIn() {
		this.holder = null;
		this.dueDate = null;
	}
	
}
