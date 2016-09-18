package assignment02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book{

	private String holder;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		holder=null;
		dueDate=null;
	}
	
	

	public String getHolder(){
		return this.holder;
		
	}
	public GregorianCalendar getDueDate(){
		return this.dueDate;
		
	}

	public void checkOut(String _holder, GregorianCalendar _dueDate){
		this.holder=_holder;
		this.dueDate=_dueDate;
	}
	
	public void checkIn(){
		this.holder=null;
		this.dueDate=null;
	}
}
