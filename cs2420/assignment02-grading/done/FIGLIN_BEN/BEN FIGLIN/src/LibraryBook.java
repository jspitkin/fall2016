package assignment02;

import java.util.GregorianCalendar;

/**
 * Class representation of a library book. this class extends the Book class 
 * but adds the holder name and the due date to it.
 * 
 * The library book can be checked in and out.
 * 
 * @author Ben Figlin (u1115949)
 * 
 */
public class LibraryBook extends Book {

	private String holder;
	
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
	}
	
	/**
	 * @return the holder
	 */
	public String getHolder() {
		return this.holder;
	}
	
	/**
	 * @return the due date
	 */
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}
	
	/**
	 * Check in a book and remove the holder and due date from it
	 * 
	 * @return true if success, false if book was already checked in
	 */
	public boolean checkIn() {
		if (this.holder == null && this.dueDate == null) {
			return false;
		}
		
		this.holder = null;
		this.dueDate = null;
		
		return true;
	}
	
	/**
	 * Check out a book and add the holder and due date details to it
	 * 
	 * @param holder
	 * 			-- the name of the holder checking the book out
	 * @param dueDate
	 * 			-- the due date for returning the book
	 * 
	 * @return true if success, false if book was already checked out
	 */
	public boolean checkOut(String holder, GregorianCalendar dueDate) {
		if (this.holder != null || this.dueDate != null) {
			return false;
		}
		
		this.holder = holder;
		this.dueDate = dueDate;
		
		return true;
	}

}
