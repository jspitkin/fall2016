/**
 * Author Alan Hansing u0668729
 */
package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
	}
	private Type holder;
	
	private GregorianCalendar dueDate;
	
	public Type getHolder(){
		return this.holder;
	}
	/** Returns the due date of the book in question.
	 * @param - none
	 * @return - the due date of the book.
	 */
	public GregorianCalendar getDueDate(){
		return this.dueDate;
	}
	/**Checks out a book
	 * 
	 * @param bookOwner - the person checking out the book.
	 * @param bookDueDate - the date the book will be due.
	 */
	public void CheckOut(Type bookOwner, GregorianCalendar bookDueDate){
		this.holder = bookOwner;
		this.dueDate = bookDueDate;
	}
	
	/**
	 * Clears the due date and book holder upon Check-In
	 * @param - none.
	 */
	public void CheckIn(){
		this.holder = null;
		this.dueDate = null;
	}

}
