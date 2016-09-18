package assignment02;

import java.util.GregorianCalendar;

/**
 * @author Eduardo Ortiz -u0922628
 *  CS 2400 MW 3-4pm 
 *
 * @param <Type> Generic type of library book holder data.
 */
public class LibraryBookGeneric<Type> extends Book{

	private Type holder;
	private GregorianCalendar date;

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		 date = null;
		 holder = null;
	}
	 
	public Type getHolder(){
		return holder;
	}
	public void setHolder(Type holder_){
		holder = holder_;
	}
	public GregorianCalendar getDueDate(){
		return date;
	}	
	public void setDueDate(GregorianCalendar date_){
		date= date_;
	}
	public void checkIn(){
		 date = null;
		 holder = null;
	}
	public void checkOut(GregorianCalendar date_, Type holder_){
		date = date_;
		holder = holder_;
	
		
	}
	}
