package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {

	public Type holder;

	public GregorianCalendar dueDate;

	public boolean isCheckedOut;

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		// TODO Auto-generated constructor stub
	}

	public Type getHolder() {
		return this.holder;
	}

	public GregorianCalendar getDueDate() {
		return this.dueDate;
	}

	public void checkIn() {
		this.holder = null;
		this.dueDate = null;
		this.isCheckedOut = false;
	}

	public void checkOut(Type holder, GregorianCalendar dueDate) {
		this.holder = holder;
		this.dueDate = dueDate;
		this.isCheckedOut = true;
	}

}
