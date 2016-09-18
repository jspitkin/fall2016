package assignment02;
/**
 * class represent a LibraryBook which contains isbn, author, title, hoder, and duedate
 * 
 * @author Jiwon Nam
 */
import java.util.*;

public class LibraryBook extends Book
{
	private String holder;
	private GregorianCalendar dueDate;
	private boolean checkIn = true;

	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the holder
	 */
	public String getHolder()
	{
		return holder;
	}
	
	/**
	 * get duedate
	 * 
	 * @return the due date
	 */
	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}
	
	/**
	 * get check in status
	 * 
	 * @return checkIn, boolean
	 */
	public boolean getCheckIn()
	{
		return checkIn;
	}
	
	/**
	 * 
	 * set check out with four parameters
	 * set holder and duedate with parameters
	 * 
	 * @param holder
	 * @param month
	 * @param day
	 * @param year
	 */
	public void setCheckOut(String holder, int month, int day, int year)
	{
		checkIn = false;
		this.holder = holder;
		this.dueDate = new GregorianCalendar(year, month, day);	
	}
	
	/**
	 * set check in status
	 * set holder and due date equal null
	 */
	public void setCheckIn()
	{
		this.checkIn = true;
		this.dueDate = null;
		this.holder = null;
	}
}
