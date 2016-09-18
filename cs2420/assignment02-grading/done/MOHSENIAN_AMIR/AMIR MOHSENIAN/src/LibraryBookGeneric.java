//Amir Mohsenian
package assignment02;
import java.util.GregorianCalendar;


public class LibraryBookGeneric<Type> extends Book



{

	//Construct a class LibraryBook derived from Book and 
	//containing the library book's holder (a String) 

	Type bookHolder;


	//and due date represented by a GregorianCalendar

	GregorianCalendar dueDate = new GregorianCalendar();


	//If a library book is checked in, its holder and due date should be set to null. 
	boolean checkedIn;


	public LibraryBookGeneric(long isbn, String author, String title)
	{
		super(isbn, author, title);
		// TODO Auto-generated constructor stub

		checkedIn = false;
		bookHolder = null;
		dueDate = null;
	}

	public Type getHolder()
	{
		return bookHolder;	

	}

	public boolean checkedInNow()
	{
		return checkedIn;
	}


	public GregorianCalendar getDueDate()
	{
		return dueDate;

	}



	
	public void bookIsCheckedOut(Type holder1, GregorianCalendar dueDate1)
	{
		checkedIn = true;
		bookHolder = holder1;
		dueDate = dueDate1;		

	}


	//If a library book is checked in, its holder and due date should be set to null.
	public LibraryBookGeneric<Type> bookIsCheckedIn()
	{
		checkedIn=false;
		dueDate=null;
		bookHolder=null;
		return this;
	}


}
