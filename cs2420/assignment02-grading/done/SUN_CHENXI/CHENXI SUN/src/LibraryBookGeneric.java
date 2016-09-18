/**
 * Name Chenxi Sun
 * uid u0455173
 */


package assignment02;

import java.util.GregorianCalendar;

public class LibraryBookGeneric<Type> extends Book  {
	
	
	
	
	
	public LibraryBookGeneric(long isbn, String author, String title) {
		super(isbn, author, title);
		// TODO Auto-generated constructor stub
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.checkin=true;//this is a checkin boolean true for checked in and false for checked out
		this.dueDate=null;//this is the dueDate of type gregorian calendar this will have a dueDate if checked out and null for checked in
		this.holderName=null;//this is of type generic so as able to be a string or a telephone number. 
							//it holds the holder of the book when checked out and null when checked in
	}

	

	private long isbn;

	private String author;

	private String title;

	private Type holderName;
	private GregorianCalendar dueDate;
	private boolean checkin;
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * @return the ISBN
	 */
	public long getIsbn() {
		return this.isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Two books are considered equal if they have the same ISBN, author, and
	 * title.
	 * 
	 * @param other
	 *            -- the object begin compared with "this"
	 * @return true if "other" is a Book and is equal to "this", false otherwise
	 */
	public boolean equals(Object other) {
		// FILL IN -- do not return false unless appropriate
		if(other instanceof Book){//same as book 
			if((this.getAuthor().equals(((Book) other).getAuthor()))&&(this.getIsbn()==(((Book) other).getIsbn()))&&(this.getTitle().equals(((Book) other).getTitle()))){
				return true;
		}
		}
		
		else{
			return false;
		}
		return false;
	}

	/**
	 * Returns a string representation of the book.
	 */
	public String toString() {
		return isbn + ", " + author + ", \"" + title + "\"";
	}

	@Override
	public int hashCode() {
		return (int) isbn + author.hashCode() + title.hashCode();
	}
	public Type getHolder(){
		return holderName;
	}
	
	public GregorianCalendar getDueDate(){
		return dueDate;
	}
	
	public void checkin(){//when book are check in duedate and holdername set to null and check in set to true
	
		this.checkin=true;
		this.dueDate=null;
		this.holderName=null;
		
	}
	public void checkout(Type holderName, GregorianCalendar dueDate){//checkout will set due date and holder name when checked out
	
		this.holderName=holderName;
		this.dueDate=dueDate;
		this.checkin=false;
		
	}
	

	


}
