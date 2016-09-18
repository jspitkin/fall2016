package assignment02;

import java.util.GregorianCalendar;

/**
 * Class representation of a LibraryBook inherited from book. GregorianCalendar
 * functionality added.
 * 
 * @author Diego Baez Lopez UID: u075947
 */

public class LibraryBook extends Book {

	private String holder;
	private GregorianCalendar dueDate;

	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		this.holder = null;
		this.dueDate = null;
	}

	/**
	 * @return the author
	 */
	public String getHolder() {
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
	public void setHolder(String holder) {
		this.holder = holder;
	}

	/**
	 * sets the due date of the book
	 */
	public void setDueDate(GregorianCalendar dueDate) {
		this.dueDate = dueDate;
	}

}
