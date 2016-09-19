package assignment02;
import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {
	private long isbn_;
	private String author_;
	private String title_;
	private Type holder_;
	private GregorianCalendar dueDate;

	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		isbn = isbn_;
		author = author_;
		title = title_;
		dueDate = new GregorianCalendar();
	}

	public Type getHolder() {
		return holder_;
	}

	public GregorianCalendar getDueDate() {
		return dueDate;
	}

	public void checkOut(Type holder, int year, int month, int dayOfMonth) {
		holder_ = holder;
		dueDate = new GregorianCalendar(year, month, dayOfMonth);
		// dueDate.add(Calendar.MONTH, 1);
		// Line above was meant to make due date a month from check in date.
		// Realized that the test code was just setting the due date straight up
		// after like an hour of debugging.
	}

	public void checkIn() {
		holder_ = null;
		dueDate = null;
	}

}