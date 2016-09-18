package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {

	private Type holder;
	private GregorianCalendar dueDate;
	
	
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = null;
		dueDate = new GregorianCalendar();
	}
	
	/**
	 * @return the Holder of the book
	 */
	public Type getHolder() {
		
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
	public void checkin() {
		this.holder = null;
		this.dueDate = null;
	}
	

	/**
	 * 
	 * 	Sets the holder and due date of the library book
	 */
	public void checkout(Type holder, GregorianCalendar dueDate) {
		this.holder = holder;
		this.dueDate = dueDate;

	}
}
