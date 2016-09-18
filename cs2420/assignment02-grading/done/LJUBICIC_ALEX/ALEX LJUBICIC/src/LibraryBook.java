package assignment02;

//Alessandro Ljubicic
//u0753409

import java.util.GregorianCalendar;

public class LibraryBook extends Book{

	private String bookHolder;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title) {
		
		super(isbn, author, title);
		
		//initialize to null
		this.bookHolder = null;
		this.dueDate = null;
	}

	/**
	 * 
	 * @return The current holder of the book, or null if there is no holder
	 */
	public String getHolder() {
		return this.bookHolder;
	}
	/**
	 * Sets the holder of the book to the new holder parameter given
	 * @param newHolder
	 */
	public void setHolder(String newHolder) {
		this.bookHolder = newHolder;
	}
	/**
	 * 
	 * @return The current due date, or null if there is no due date
	 */
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}
	/**
	 * Sets the due date to the new due date parameter given
	 * @param newDueDate
	 */
	public void setDate(GregorianCalendar newDueDate) {
		this.dueDate = newDueDate;
	}
	/**
	 * Checks a book in by setting the holder and dueDate variables to null
	 */
	public void checkIn() {
		this.bookHolder = null;
		this.dueDate = null;
	}
	
	/**
	 * Checks whether the book is checked in or not
	 * based upon whether the holder and dueDate variables are null
	 * @return True or False
	 */
	public boolean isCheckedIn() {
		
		if((this.getHolder() == null) && (this.getDueDate() == null)) {
			return true;
		}
		return false;
	}
	
	//An advanced method of checking out I never got around to using in my code. Ignore.
	public LibraryBook checkOut(String newBookHolder, GregorianCalendar newDueDate) {
		
		this.bookHolder = newBookHolder;
		this.dueDate = newDueDate;
		
		LibraryBook newLibraryBook = new LibraryBook(super.getIsbn(), super.getAuthor(), super.getTitle());
		
				return newLibraryBook;
	}
}
