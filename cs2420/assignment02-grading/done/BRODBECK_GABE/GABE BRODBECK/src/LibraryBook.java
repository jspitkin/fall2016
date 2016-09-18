package assignment02;
//Gabe Brodbeck u0847035
import java.util.GregorianCalendar;


public class LibraryBook extends Book{
	GregorianCalendar dueDate=null;
	String currentHolder=null;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		
	}
	/**
	 * @return the current holder of the book or null if the book isn't checked out
	 */
	public String getHolder(){
		return currentHolder;
	}
	/**
	 * @return returns the due date of the book as a Gregorian Calendar or null if the book isn't checked out
	 */
	public GregorianCalendar getDueDate(){
		return dueDate;
		
	}
	/**
	 * @param holder -- A string representing the name of the new holder
	 * @param month -- An int representing the month of the due date
	 * @param day -- An int representing the day of the month of the due date
	 * @param year -- An int representing the year of due date
	 */
	public void checkOut(String holder, int month, int day, int year){
		if(currentHolder==null){
		dueDate=new GregorianCalendar(year, month, day);
		currentHolder=holder;
		}
	}
	/**
	 * Checks in in the book by setting due date and holder to null
	 */
	public void checkIn(){
		dueDate=null;
		currentHolder=null;
	}
	
}
