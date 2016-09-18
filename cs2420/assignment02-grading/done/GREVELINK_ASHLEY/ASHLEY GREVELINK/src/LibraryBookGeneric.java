package assignment02;

import java.util.GregorianCalendar;

/**
 * A class LibraryBook derived from Book which contains the library book's holder (a String) 
 * and due date represented by a GregorianCalendar.
 * 
 * If a library book is checked in, its holder and due date is set to null. 
 * 
 * @author Ashley Grevelink u0749357
*/
public class LibraryBookGeneric<Type> extends Book {

	private String holderNameOrIsbn;
	private PhoneNumber holderPhone;
	private GregorianCalendar dueDate;
	
	public LibraryBookGeneric(long isbn, String author, String title) {
		//create a Book
		super(isbn, author, title);
	}

	/**
	 * If holder = null, return null. Returns the holder.
	 * @return a casted Type of holder as either a name, Isbn or phone number
	 */
	@SuppressWarnings("unchecked")
	public Type getHolder() {
		if (holderNameOrIsbn == null && holderPhone == null)
		{
			return null;
		}
		if (holderNameOrIsbn != null)
		{
			return (Type) holderNameOrIsbn;
		}
		if (holderPhone != null)
		{
			return (Type) holderPhone;
		}
		return null;
	}

	/**
	 * Accessor for due date.
	 * @return the due date of a book, or null if it is null
	 */
	public GregorianCalendar getDueDate() {
		if (dueDate == null){
			return null;
		}
		return dueDate;
	}
	
	/**
	 * Sets holder and due date to null.
	 */
	public void checkIn() {
			holderNameOrIsbn = null;
			holderPhone = null;
			dueDate = null;
	}
	
	/**
	 * Sets due date for a newly checkout book. Sets the Type of holder.
	 * @param newHolder
	 * @param newDueDate
	 */
	public void checkOut(Type newHolder, GregorianCalendar newDueDate) {
			if (newHolder instanceof String) {
				this.holderNameOrIsbn = (String) newHolder;
			}
			if (newHolder instanceof PhoneNumber) {
				holderPhone = (PhoneNumber) newHolder;
			}

			dueDate = newDueDate;
	}

}
