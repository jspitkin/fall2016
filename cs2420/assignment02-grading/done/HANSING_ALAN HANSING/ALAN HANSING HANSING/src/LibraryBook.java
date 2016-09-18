/**
 * Author Alan Hansing u0668729
 */
package assignment02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book {

	private String holder;
	
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		holder = null;
	}
	/**Returns the holder of the book in question.
	 * 
	 * @return - the holder of the book.
	 */
	public String getHolder(){
		return this.holder;
	}
	
	/**This returns the due date of the  book in question.
	 * 
	 * @return - the due date of the book.
	 */
	public GregorianCalendar getDueDate(){
		return this.dueDate;
	}
	
	/**Modifies the data fields associated with the owner of the current book.
	 * 
	 * @param bookOwner - the current holder of the book.
	 * @param bookDueDate - the date the book must be returned by.
	 */
	public void CheckOut(String bookOwner, GregorianCalendar bookDueDate){
		this.holder = bookOwner;
		this.dueDate = bookDueDate;
	}
	
	/**
	 * Clears all information related to the holder of the book.
	 */
	public void CheckIn(){
		this.holder = null;
		this.dueDate = null;
	}
}
