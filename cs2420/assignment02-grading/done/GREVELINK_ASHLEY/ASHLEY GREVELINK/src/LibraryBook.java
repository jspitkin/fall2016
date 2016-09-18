package assignment02;

import java.util.GregorianCalendar;

/**
 * A class LibraryBook derived from Book which contains the library book's holder (a String) 
 * and due date represented by a GregorianCalendar.
 * 
 * If a library book is checked in, its holder and due date is set to null. 
 * 
 * @author Ashley Grevelink u0749357
*/
public class LibraryBook extends Book {

	private String holder;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
	}

	/**
	 * If holder = null, return null.
	 * @return
	 */
	public String getHolder() {
		if (holder == null)
		{
			return null;
		}
		return holder;
	}

	public GregorianCalendar getDueDate() {
		if (dueDate == null){
			return null;
		}
		return dueDate;
	}
	
	public void checkIn(){
			holder = null;
			dueDate = null;
	}
	
	public void checkOut(String newHolder, GregorianCalendar newDueDate){
			holder = newHolder;
			dueDate = newDueDate;
	}

}
