package assignment02;
/**
 * Ryan Williamson
 * Last Updated: Sept 7, 2016
 * u0838551
 */

import java.util.GregorianCalendar;

/**
 * This class represents a library book, it's holder (whom rented the book) and it's due date.
 * 
 */

public class LibraryBookGeneric<Type> extends Book {
	private Type holder;
	private GregorianCalendar dueDate;

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
	}
	/**
	 * This is a getter method for the private variable holder.
	 * @return - The name of the individual whom checked out the book.
	 */
	public Type getHolder(){
		
		return this.holder;
	}

	/**
	 * This is a getter method for the private variable dueDate.
	 * @return - due date
	 */
	public GregorianCalendar getDueDate(){

		return this.dueDate;
	}
	/**
	 * This method checks the book out to an individual.
	 * @param holder - Individual whom checked the book out.
	 * @param dueDate - Due date of the book.
	 */
	public void bookCheckOut(Type holder, GregorianCalendar dueDate){
		this.holder = holder;
		this.dueDate = dueDate;
		
	}
	/**
	 * This method will set the value of the private variables holder and dueDate to null.
	 * Thus, stating that the book isn't checked out to any one and is know in the library.
	 */
	public void bookCheckIn(){
		this.holder = null;
		this.dueDate = null;
	}
	
	/**
	 * Sets the due date for the private variable dueDate.
	 * @param year - int value specified year.
	 * @param month - int value 1 - 12
	 * @param day - int value  1 - 31
	 */
	public void setDueDate(int year, int month, int day){
	
		dueDate = new GregorianCalendar(year, month, day);

	}
}
