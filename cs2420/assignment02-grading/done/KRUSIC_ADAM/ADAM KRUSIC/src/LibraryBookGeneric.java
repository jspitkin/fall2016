package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{

	private Type holder;
	private GregorianCalendar dueDate;
	
	public LibraryBookGeneric(long isbn, String author, String title){
		super(isbn, author, title);
	}
	
	public Type getHolder(){
		return this.holder;
	}
	
	public GregorianCalendar getDueDate(){
		return this.dueDate;
	}
	
	public void checkIn(){
		this.dueDate = null;
		this.holder = null;
	}
	
	public void checkOut(Type holder, GregorianCalendar date){
		this.dueDate = date;
		this.holder = holder;
	}
}