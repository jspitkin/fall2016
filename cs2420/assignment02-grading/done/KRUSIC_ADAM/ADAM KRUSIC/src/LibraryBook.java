package assignment02;

import java.util.GregorianCalendar;

public class LibraryBook extends Book{

	private String holder;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title){
		super(isbn, author, title);
	}
	
	public String getHolder(){
		return this.holder;
	}
	
	public GregorianCalendar getDueDate(){
		return this.dueDate;
	}
	
	public void checkIn(){
		this.dueDate = null;
		this.holder = null;
	}
	
	public void checkOut(String holder, GregorianCalendar date){
		this.dueDate = date;
		this.holder = holder;
	}
}