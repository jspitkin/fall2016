package assignment02;

import java.util.GregorianCalendar;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 *
 */
public class LibraryBook extends Book {

	public String holder;

	public GregorianCalendar dueDate;
	
	public boolean isCheckedOut;

	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		// TODO Auto-generated constructor stub
	}

	public String getHolder() {
		return this.holder;
	}

	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}

	public void checkIn() {
		this.holder = null;
		this.dueDate = null;
		this.isCheckedOut = false;
	}

	public void checkOut(String holder, GregorianCalendar dueDate) {
		this.holder = holder;
		this.dueDate = dueDate;
		this.isCheckedOut = true;
	}
}
