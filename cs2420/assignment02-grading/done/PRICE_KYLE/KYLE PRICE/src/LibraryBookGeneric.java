package assignment02;

import java.util.GregorianCalendar;

/**
 * 
 * @author Kyle Price
 * 09/07/2016
 * u1018878
 *
 * @param <Type>
 */
public class LibraryBookGeneric<Type> extends Book {
	
	Type holder; // A generic version of holder.
	GregorianCalendar dueDate;
	
	/**
	 * LibraryBookGeneric - is a constructor for a LibraryBookGeneric object.
	 * The holder object is of type <Type>. 
	 * 
	 */
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title); 
		dueDate = null;
		holder = null;
	}
	
	/**
	 * getHolder - is a getter for the generic member variable holder. 
	 *  
	 * @return - returns the generic holder object.
	 */
	public Type getHolder() { 
		return holder;
	}
	
	/**
	 * getDueDAte - returns the dueDate of the LibraryBookGeneric.
	 * 
	 * @return - a GregorianCalendar object. 
	 */
	public GregorianCalendar getDueDate() {
		return dueDate;
	}
	
	/**
	 * checkIn - sets the holder and dueDate values to null, therefore "checking them in". 
	 */
	public void checkIn() {
		this.holder = null;
		this.dueDate = null;
	}
	
	/**
	 * checkOut - sets the holder to the parameter "name" and dueDate to the parameter "dueDate". 
	 * therefore "checking it out".
	 * 
	 * @param name - A generic denoting the name of the holder.
	 * @param dueDate - a GregorianCalendar object denoting the dueDate of the book.
	 */
	public void checkOut(Type name, GregorianCalendar dueDate) { 
		this.holder = name; 
		this.dueDate = dueDate;
	}
}

