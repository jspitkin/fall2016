package assignment02;

import java.util.GregorianCalendar;

/**
 * 
 * @author Chris Grayston u0906710
 * 
 * LibraryBook is an extension of book adding extra featrues
 *
 */
public class LibraryBook extends Book{

	private String holder;
	private GregorianCalendar dueDate;
	
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = "No Holder";
		dueDate = new GregorianCalendar();
	}
	
	/**
	 * @return the Holder of the book
	 */
	public String getHolder() {
		
		return this.holder;
	}
	
	public GregorianCalendar getDueDate() {
		return dueDate;		
	}
	
	/**
	 * Unsets the holder and due date
	 * @return 
	 * 
	 */
	public void checkIn() {
		this.holder = null;
		this.dueDate = null;
	}
	

	/**
	 * 
	 * 	Sets the holder and due date of the library book
	 */
	public void checkOut(String holder, GregorianCalendar dueDate) {
		this.holder = holder;
		this.dueDate = dueDate;

	}
}
