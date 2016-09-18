package assignment02;


/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 * 
 * @author Ashley Grevelink u0749357
 *
 */
public class Book {

	private long isbn;

	private String author;

	private String title;

	public Book(long isbn, String author, String title) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
	}

	/**
	 * Accessor for author.
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * Accessor for ISBN.
	 * @return the ISBN
	 */
	public long getIsbn() {
		return this.isbn;
	}

	/**
	 * Accessor for title.
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
		//test to prevent thrown exceptions due to incorrect type casting
		if (!(other instanceof Book)) {
			return false;
		}
	
		//creating a Book object from the argument other for testing purposes
		Book book = (Book) other;
		
		//comparing isbn, author and title of the lhs (this) and rhs (book) Books
		if (this.isbn == book.isbn && this.author == book.author && this.title == book.title) {
			return true;
		}
		
		//else
		return false;
	}

	/**
	 * Returns a string representation of the book.
	 * @return a String
	 */
	public String toString() {
		return isbn + ", " + author + ", \"" + title + "\"";
	}

	/**
	 * Returns a hash code.
	 * @return int
	 */
	@Override
	public int hashCode() {
		return (int) isbn + author.hashCode() + title.hashCode();
	}
}
