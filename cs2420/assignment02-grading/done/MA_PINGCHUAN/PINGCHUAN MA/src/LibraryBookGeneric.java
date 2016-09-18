/**
 * @author Pingchuan Ma
 * @uid u0805309
 */
package assignment02;

import java.util.GregorianCalendar;
/**
 * Class representation of a library book.
 * 
 */
public class LibraryBookGeneric<Type> extends Book {

	private Type holder;
	private GregorianCalendar duedate;

	public LibraryBookGeneric(long isbn, String author, String title) {
			super(isbn, author, title);
			duedate=null;
			holder=null;
			// TODO Auto-generated constructor stub
			
		}
	/**
	 * @return the author
	 */
	public Type getHolder(){
		return holder;
	}
	/**
	 * @return the duedate
	 */
	public GregorianCalendar getDueDate(){
		
		return duedate;
	}
	/**
	 * Unsets the holder and due date of the library book.
	 */
	public void CheckedIn(){
		holder=null;
		duedate=null;
	}
	/**
	 * Sets the holder and due date of the library book to be checked out .
	 * When holder or duedate is null do checkedout.
	 * 
	 * 
	 * @param holder
	 *            -- new holder of the library book
	 * @param month
	 *            -- month of the new due date of the library book
	 * @param day
	 *            -- day of the new due date of the library book
	 * @param year
	 *            -- year of the new due date of the library book
	 * 
	 */
	public void CheckedOut(Type holder,int month,int day,int year){
		//不是同时null？
		if(holder==null||duedate==null){
		this.holder=holder;
		this.duedate=new GregorianCalendar(year,month,day);
		
		}
		
	}
	}



