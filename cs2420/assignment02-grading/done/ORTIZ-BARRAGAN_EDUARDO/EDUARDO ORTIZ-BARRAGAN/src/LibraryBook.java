package assignment02;

import java.util.GregorianCalendar;

/**
 * @author Eduardo Ortiz -u0922628
 * CS 2400 MW 3-4pm 
 */
public class LibraryBook extends Book{

	private String holder;
	private GregorianCalendar date;
	private boolean checker;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		 date = null;
		 holder = null;
	}
	 
	/** Method to get the holder value.
	 * @return Who has the books
	 */
	public String getHolder(){
		return holder;
	}
	/** Sets the person who has the books
	 * @param holder_ Person who has the books
	 */
	public void setHolder(String holder_){
		holder = holder_;
	}
	/**Gets the due date of books
	 * @return the date due for the books
	 */
	public GregorianCalendar getDueDate(){
		return date;
	}	
	/**Sets the due date for the books
	 * @param date_
	 */
	public void setDueDate(GregorianCalendar date_){
		date= date_;
	}
	/** Sets the user and due date to null, book is returned. 
	 * 
	 */
	public void checkIn(){
		 date = null;
		 holder = null;
	}
	/** Sets teh date and user, book is checked out.
	 * @param date_ Date the book is due.
	 * @param holder_ Who has the book.
	 */
	public void checkOut(GregorianCalendar date_, String holder_){
		date = date_;
		holder = holder_;
	
		
	}
	}
