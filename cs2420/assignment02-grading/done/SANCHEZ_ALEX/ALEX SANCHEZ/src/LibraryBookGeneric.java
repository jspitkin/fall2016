package assignment02;


/**
 * @author Alexander Sanchez u0911341
 * 
 */


import java.util.Calendar;
import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book {

	private Type holderName;
	private GregorianCalendar dueDate;
	private boolean checked;

	public LibraryBookGeneric(long _isbn, String _author, String _title) {

		super(_isbn, _author, _title);

		checked = false;
		dueDate = new GregorianCalendar();
		holderName = null;
		

	}

	/**
	 * 
	 * @return Name of holder
	 */
	public Type getHolder() {

		return holderName;
	}

	/**
	 * 
	 * @return due date of bookC
	 */
	public GregorianCalendar getDueDate() {
		return dueDate;
	}

	/**
	 * Checks in book and wipes due date and holder name. Does nothing if
	 * already checked in.
	 */
	public void checkIn() {

		if (checked) {

			checked = false;
			dueDate = new GregorianCalendar();
			holderName = null;

		}
	}

	/**
	 * Checks out the book and stores appropriate information
	 * 
	 * @param _holder
	 *            - Name of holder
	 * @param month
	 *            - month checked out
	 * @param day
	 *            - day checked out
	 * @param year
	 *            - year checked out
	 */
	public void checkOut(Type _holder, int month, int day, int year) {

		if (!checked) {

			checked = true;
			holderName = _holder;
			dueDate = new GregorianCalendar(year, month, day);

		}
	}

	/**
	 * 
	 * @return true if book is checked out. False if checked in.
	 */
	public boolean isCheckedOut() {
		return checked;
	}

}
