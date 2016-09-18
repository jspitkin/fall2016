package assignment02;

//Alessandro Ljubicic
//u0753409

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book{

	private Type bookHolder;
	private GregorianCalendar dueDate;
	
	public LibraryBookGeneric(long isbn, String author, String title) {
		
		super(isbn, author, title);
		
		this.bookHolder = null;
		this.dueDate = null;
	}

	public Type getHolder() {
		return this.bookHolder;
	}
	
	public void setHolder(Type newHolder) {
		this.bookHolder = newHolder;
	}
	
	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}
	
	public void setDate(GregorianCalendar newDueDate) {
		this.dueDate = newDueDate;
	}
	
	public void checkIn() {
		this.bookHolder = null;
		this.dueDate = null;
	}
	
	public boolean isCheckedIn() {
		
		if((this.getHolder() == null) && (this.getDueDate() == null)) {
			return true;
		}
		return false;
	}
	
	public LibraryBookGeneric<Type> checkOut(Type newBookHolder, GregorianCalendar newDueDate) {
		
		this.bookHolder = newBookHolder;
		this.dueDate = newDueDate;
		
		LibraryBookGeneric<Type> newLibraryBook = new LibraryBookGeneric<Type>(super.getIsbn(), super.getAuthor(), super.getTitle());
		
				return newLibraryBook;
	}
}
