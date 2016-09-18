package assignment02;

import java.util.GregorianCalendar;

/**
 * Class representation of a LibraryBookGeneric inherited from book. This is the
 * supports Java Generics.
 * 
 * Note that ISBNs are unique.
 * 
 * @author Diego Baez Lopez UID: u075947
 */

public class LibraryBookGeneric<Type> extends Book {

	private Type holder;

	private GregorianCalendar dueDate;

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		this.holder = null;
		this.dueDate = null;
	}

	/**
	 * @return the author
	 */
	public Type getHolder() {

		return this.holder;
	}

	/**
	 * @return the due date of a book
	 */
	public GregorianCalendar getDueDate() {

		return this.dueDate;
	}

	/**
	 * sets the holder of the book
	 */
	public void setHolder(Type holder) {

		this.holder = holder;
	}

	/**
	 * sets the due date of the book
	 */
	public void setDueDate(GregorianCalendar dueDate) {

		this.dueDate = dueDate;
	}

}
