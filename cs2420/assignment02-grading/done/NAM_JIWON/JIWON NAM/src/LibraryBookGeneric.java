package assignment02;
/**
 * @author Jiwon Nam
 */
import java.util.GregorianCalendar;

public class LibraryBookGeneric <Type> extends Book
{
	public LibraryBookGeneric(long isbn, String author, String title) 
	{
		super(isbn, author, title);
	}

	private Type holder;
	private GregorianCalendar dueDate;
	private boolean checkIn = true;

	
	/**
	 * @return the holder
	 */
	public Type getHolder()
	{
		return holder;
	}
	
	/**
	 * 
	 * @return the due date
	 */
	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getCheckIn()
	{
		return checkIn;
	}
	
	/**
	 * 
	 * @param holder
	 * @param month
	 * @param day
	 * @param year
	 */
	public void setCheckOut(Type holder, int month, int day, int year)
	{
		checkIn = false;
		this.holder = holder;
		this.dueDate = new GregorianCalendar(year, month, day);	
	}
	
	/**
	 * 
	 */
	public void setCheckIn()
	{
		this.checkIn = true;
		this.dueDate = null;
		this.holder = null;
	}
}
