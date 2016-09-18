package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{



	private Type holder;
	private GregorianCalendar dueDate;
	
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		holder=null;
		dueDate=null;
	}
	
	

	public Type getHolder(){
		return this.holder;
		
	}
	public GregorianCalendar getDueDate(){
		return this.dueDate;
		
	}

	public void checkOut(Type _holder, GregorianCalendar _dueDate){
		this.holder=_holder;
		this.dueDate=_dueDate;
	}
	
	public void checkIn(){
		this.holder=null;
		this.dueDate=null;
	}
}



