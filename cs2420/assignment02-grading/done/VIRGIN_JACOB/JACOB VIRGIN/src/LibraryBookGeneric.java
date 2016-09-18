package assignment02;

import java.util.GregorianCalendar;

/**
 * Represents a book that could be held in a library, with any type of information 
 * regarding the patron who currently has the book checked out
 * @author Jacob Virgin, u0832933
 *
 * @param <Type> --The type of information that describes the patron that has the book checked out
 */

public class LibraryBookGeneric<Type> extends Book{
	public Type holder;
	public GregorianCalendar dueDate;
	public boolean checkedOut;
	
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = null;
		dueDate = null;
		checkedOut = false;
	}
	
	public Type getHolder(){
		return this.holder;
	}
	
	public GregorianCalendar getDueDate(){
		return this.dueDate;
	}
	
	/**
	 * 
	 * @param patron
	 * @param dateDue
	 * @return true if the book has been successfully checked out, false if the book is already checked out
	 */
	public boolean checkBookOut(Type patron, int month, int date, int year){
		if(!checkedOut){
			this.holder = patron;
			this.dueDate = new GregorianCalendar(year, month, date);
			this.checkedOut = true;
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * 
	 * @return true if the book has been successfully checked in, false if the book is already checked in
	 */
	public boolean checkBookIn() {
		if(checkedOut){
			this.holder = null;
			this.dueDate = null;
			this.checkedOut = false;
			return true;
		}
		
		return false;
		
		
	}
	
	


}
