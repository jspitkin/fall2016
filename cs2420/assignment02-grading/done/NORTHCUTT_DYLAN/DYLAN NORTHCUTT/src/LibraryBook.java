/**
 * author: Dylan Northcutt
 * Uid: u1055102
 */
package assignment02;
import java.util.GregorianCalendar;

public class LibraryBook extends Book{

	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		
	}

	private String  holder;
	
	private GregorianCalendar dueDate;
	
	
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
	 * Checks if the book is currently checked in if not it sets holder and due date
	 * to null and returns true
	 * title.
	 * 
	 * @return true if book was checked out and is now checked in
	 */
	public boolean checkIn(){
	if((holder==null) && (dueDate==null)){
			return false;
	}
	holder = null;
	dueDate = null;
	return true;
	}
	
	
	/**
	 * Checks if the book is currently checked out if not it sets holder and due date
	 * to what was specified
	 * 
	 * @parama new holder, a new due date
	 * @return true if book was checked in and is now checked out
	 */
	public boolean checkOut(String newHolder, GregorianCalendar newDueDate){
		if((holder==null) && (dueDate==null)){
	holder = newHolder; 
	dueDate = newDueDate;
	return true;
	}
		return false;
	}
}



