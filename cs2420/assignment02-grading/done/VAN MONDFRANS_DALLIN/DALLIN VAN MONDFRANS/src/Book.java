/**
 * Assignment 2
 * Name: Dallin Van Mondfrans
 * uID: u0717113 
 * Date: September 7, 2016
 */

package assignment02;

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
	 * Creates the book object and initializes the private fields
	 * @param isbn
	 * @param author
	 * @param title
	 */
	public Book(long isbn, String author, String title) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
	}

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
	 * @param other (any object)
	 *            -- the object being compared with "this"
	 * @return true if "other" is a Book and is equal to "this", false otherwise
	 */
	public boolean equals(Object other) {
		
		if (!(other instanceof Book)) {
			return false;
		}
		
		Book bookObject = (Book)other; 
		
		if ((this.isbn != bookObject.isbn) || !(this.author.equals(bookObject.author)) || !(this.title.equals(bookObject.title)) ) {
			return false;
		}
		
		return true;
	}

	/**
	 * Returns a string representation of the book.
	 * @return string representation of the book
	 */
	public String toString() {
		return isbn + ", " + author + ", \"" + title + "\"";
	}

	/**
	 * @return unique hashCode for this Book object
	 */
	@Override
	public int hashCode() {
		return (int) isbn + author.hashCode() + title.hashCode();
	}
}
