package assignment02;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 * 
 * @author Rebekah Peterson, last updated 9/7/16
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
		if (!(other instanceof Book)) {
			return false;
		}
		
		Book otherBook = (Book) other;

		return (this.isbn == otherBook.isbn && this.author == otherBook.author &&
				this.title == otherBook.title);
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
