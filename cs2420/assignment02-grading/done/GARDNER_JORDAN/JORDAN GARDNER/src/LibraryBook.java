package assignment02;

import java.util.GregorianCalendar;

/**
 * 
 * @author Jordan Gardner u0566259
 *
 */
public class LibraryBook extends Book {
	
	private String holder;
	private GregorianCalendar datedue;
	
	public LibraryBook(long _isbn, String _author, String _title) {
		super(_isbn,_author,_title);	
	}
	public String getHolder(){
		return this.holder;	
	}
	public GregorianCalendar getDueDate(){
		return this.datedue;		
	}
	public void setHolder(String holder){
		this.holder=holder;		
	}
	public void setDateDue(GregorianCalendar DateDue){
		this.datedue=DateDue;		
	}
	
}
