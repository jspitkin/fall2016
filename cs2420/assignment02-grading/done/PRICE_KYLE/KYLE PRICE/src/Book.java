package assignment02;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * @author Kyle Price
 * 09/07/2016
 *
 */
public class Book {

	private long isbn;

	private String author;

	private String title;
	
	/**
	 * A book object is created from this method. 
	 * 
	 * @param isbn - identifying number the book.	
	 * @param author - the author of the book. 
	 * @param title - the title of the book.
	 */
	public Book(long isbn, String author, String title) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
	}

	/**
	 * getAuthor - serves as a getter for the author of the book. 
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * getIsbn - serves as a getter for the isbn number.
	 * 
	 * @return the ISBN
	 */
	public long getIsbn() {
		return this.isbn;
	}

	/**
	 * getTitle - serves as a getter for the title of the book.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Two books are considered equal if they have the same ISBN, author, and
	 * title.
	 * 
	 * @param other - the object being compared with "this".
	 * @return true if "other" is a Book and is equal to "this", false otherwise.
	 */
	public boolean equals(Object other) {
		if ((other instanceof Book) && (this.getIsbn() == ((Book) other).getIsbn())
				&& (this.getAuthor().equals(((Book) other).getAuthor())
						&& this.getTitle().equals(((Book) other).getTitle()))) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * toString - Returns a string representation of the book.
	 */
	public String toString() {
		return isbn + ", " + author + ", \"" + title + "\"";
	}

	@Override
	public int hashCode() {
		return (int) isbn + author.hashCode() + title.hashCode();
	}
}
