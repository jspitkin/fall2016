/**
 * Chenxi Sun
 * Uid u0455173
 */

package assignment02;

import java.util.GregorianCalendar;


public class LibraryBook extends Book {
	public LibraryBook(long isbn, String author, String title) {
		super(isbn, author, title);
		this.isbn=isbn;
		this.author=author;
		this.title=title;
		this.checkin=true;
		this.dueDate=null;
		this.holderName=null;
		
		// TODO Auto-generated constructor stub
	}
	private long isbn;

	private String holderName;
	private GregorianCalendar dueDate;
	private String author;
	private boolean checkin;
	private String title;

	

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
		// FILL IN -- do not return false unless appropriate
	
		
		if(other instanceof Book){
					if((this.getAuthor().equals(((Book) other).getAuthor()))&&(this.getIsbn()==((((Book) other).getIsbn()))&&(this.getTitle().equals(((Book) other).getTitle())))){
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
	
	public String getHolder(){
		return holderName;
	}
	
	public GregorianCalendar getDueDate(){
		return dueDate;
	}
	public void checkin(){
		this.checkin=true;
		this.dueDate=null;
		this.holderName=null;
	}
	public void checkout(String holderName, GregorianCalendar dueDate){
		
		
	
		this.holderName=holderName;
		this.dueDate=dueDate;
		this.checkin=false;
		
	}
	public boolean getStatus(){
		return this.checkin;
	}
	
}
