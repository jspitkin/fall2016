package assignment02;
/**
 * Ryan Williamson
 * Last Updated: Sept 7, 2016
 * u0838551
 */

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 *
 */
public class Book {

	private long isbn;

	private String author;

	private String title;
	
	/**
	 * Constructor for Book class.
	 * @param isbn - A unique number specified to the book.
	 * @param author - Author of the book
	 * @param title - Title of the book.
	 */

	public Book(long isbn, String author, String title) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
	}

	/**
	 * This is a getter method for the private variable author.
	 * @return the author of the book.
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * This is a getter for the private variable isbn.
	 * @return the ISBN of the book.
	 */
	public long getIsbn() {
		return this.isbn;
	}

	/**
	 * This is a getter method for the private variable title.
	 * @return the title of the book.
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
		//Checks to insure objects are of the same type.
		if(!(other instanceof Book)){
			return false;
		}
		//If the above is true, than other must be of the type Book.
		Book compare =(Book)other;
		
		//Two books are considered equal if they have the same ISBN, author, and title.
		if(this.isbn == compare.isbn && this.author == compare.author && this.title == compare.title){
			
			return true;
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
}
