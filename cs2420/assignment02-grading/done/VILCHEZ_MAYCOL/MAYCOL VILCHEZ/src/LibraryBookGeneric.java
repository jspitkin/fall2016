
package assignment02;

import java.util.GregorianCalendar;

/**
 * Maycol Vilchez 
 * u0832923
 */

/**
 * Class representation of a library generic book. The ISBN, author, and title can never change once the book is created.
 * 
 * Note that ISBNs are unique.
 * Note that holder is generic
 */

public class LibraryBookGeneric<Type> extends Book {
	private Type holder;
	GregorianCalendar date = new GregorianCalendar();

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = null;
		date = null;
	}

	/**
	 * @return the name of the current holder of the book
	 */
	
	public Type getHolder() {
		return holder;
	}

	/**
	 * @return the date due
	 */
	
	public GregorianCalendar getDueDate() {
		return date;
	}

	/**
	 * Checks a book in by returning the values of holder and date to their default null
	 */
	
	public void checkIn() {
		holder = null;
		date = null;
	}

	/**
	 * checks a book out by assigning the values to holder and date
	 */
	public void checkOut(Type holder, GregorianCalendar dueDate) {
		this.holder = holder;
		this.date = dueDate;
	}
}
