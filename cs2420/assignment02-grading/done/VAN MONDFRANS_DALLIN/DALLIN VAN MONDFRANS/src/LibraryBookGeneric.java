/**
 * Assignment 2
 * Name: Dallin Van Mondfrans
 * uID: u0717113 
 * Date: September 7, 2016
 */

package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {

	private Type holder;
	private GregorianCalendar dueDate;
	
	/**
	 * Extends Book by adding private fields holder and dueDate
	 *  
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		this.holder = null;
		this.dueDate = null;
	}
	
	/**
	 * @return the holder
	 */
	public Type getHolder() {
		return this.holder;
	}
	
	/**
	 * @return the dueDate
	 */
	public GregorianCalendar getDueDate() {
		return this.dueDate; 
	}
	
	/**
	 * Assigns the LibraryBookGeneric a holder and dueDate
	 * 
	 * @param holder
	 * @param dueDate
	 */
	public void checkBookOut(Type holder, GregorianCalendar dueDate) {
		this.holder = holder;
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
