package assignment02;

import java.util.GregorianCalendar;

/**
 * A class representation of a library book, derived from Book and contains the library book's
 * holder (a generic) and due date represented by a GregorianCalendar.
 * 
 * If a library book is checked in, its holder and due date should be set to null. 
 * 
 * @author Rebekah Peterson, last updated 9/7/16
 *
 */
public class LibraryBookGeneric<Type> extends Book {
	
	private Type holder;
	private GregorianCalendar dueDate;
	
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = null;
		dueDate = null;
	}
	
	/**
	 * 
	 * @return the holder
	 */
	public Type getHolder() {
		return holder;
	}
	
	/**
	 * 
	 * @return the due date, as a GregorianCalendar object
	 */
	public GregorianCalendar getDueDate() {
		return dueDate;
	}
	
	/**
	 * Sets holder and due date to null, the specifications for a checked in library book.
	 */
	public void checkIn() {
		holder = null;
		dueDate = null;
	}
	
	/**
	 * Sets the holder and due date as specified, holding data regarding check out.
	 * 
	 * @param holder -- a library holder, of the generic type
	 * @param dueDate -- A GregorianCalendar object of the date the book is to be due
	 */
	public void checkOut(Type holder, GregorianCalendar dueDate) {
		this.holder = holder;
		this.dueDate = dueDate;
	}
	
	/**
	 * 
	 * @return true if checked out, false otherwise
	 */
	public boolean isCheckedOut() {
		if (holder == null && dueDate == null) {
			return false;
		}
		else {
			return true;
		}
	}
}
