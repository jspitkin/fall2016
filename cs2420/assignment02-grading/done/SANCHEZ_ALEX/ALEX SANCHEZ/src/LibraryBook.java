package assignment02;

/**
 * @author Alexander Sanchez u0911341
 * 
 */

import java.util.Calendar;
import java.util.GregorianCalendar;

public class LibraryBook extends Book {

	private String holderName;
	private GregorianCalendar dueDate;
	private boolean checked;

	public LibraryBook(long _isbn, String _author, String _title) {

		super(_isbn, _author, _title);

		checked = false;
		dueDate = new GregorianCalendar();
		holderName = "";

	}

	/**
	 * 
	 * @return Name of holder
	 */
	public String getHolder() {

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
			holderName = "";

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
	public void checkOut(String _holder, int month, int day, int year) {

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
