package assignment02;

import java.util.GregorianCalendar;


/**
 * Class representation of a LibraryBook, it holds 3 values that cannot be changed
 * once the book is created, the isbn, author, and title. It also holds the holder
 * of the book as well as the date it is due, if the book is checked out. These
 * values will change with the book being checked in and out.
 * 
 * Generic version
 * 
 * @author Vladimir Srdic
 *
 */

public class LibraryBookGeneric<Type> extends Book{
	
	private Type holder;
	private GregorianCalendar dueDate;
	private boolean checkedOut;

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = null;
		dueDate = null;
		checkedOut = false;
	}
	
	public void checkOut(Type holder, GregorianCalendar dueDate) {
		this.holder = holder;
		this.dueDate = dueDate;
		checkedOut = true;
	}
	
	public void checkIn() {
		holder = null;
		dueDate = null;
		checkedOut = false;
	}
	
	public Type getHolder() {
		return holder;
	}
	
	public GregorianCalendar getDueDate() {
		return dueDate;
	}
	
	public boolean getCheckedOut() {
		return checkedOut;
	}

}
