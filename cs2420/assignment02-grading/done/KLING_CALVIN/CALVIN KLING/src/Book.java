package assignment02;

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * 
 * Note that ISBNs are unique.
 *
 * @author Calvin Kling
 * @version 9/6/2016
 */
public class Book {

	private long isbn;

	private String author;

	private String title;

	public Book(long isbn, String author, String title)
	{
		this.isbn = isbn;
		this.author = author;
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor()
	{
		return this.author;
	}

	/**
	 * @return the ISBN
	 */
	public long getIsbn()
	{
		return this.isbn;
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return this.title;
	}

	/**
	 * Two books are considered equal if they have the same ISBN, author, and
	 * title.
	 * 
	 * @param other -- the object being compared with "this"
	 * @return true if "other" is a Book and is equal to "this", false otherwise
	 */
	public boolean equals(Object other)
	{
		//If they are the same reference, they are the same item.
		if(this == other)
			return true;
		
		//If other is not a book, they cannot be equal.
		if(!(other instanceof Book))
			return false;
		
		Book otherBook = (Book) other;
		
		if(otherBook.isbn == this.isbn && otherBook.author.equals(this.author) && otherBook.title.equals(this.title))
			return true;

		return false;
	}

	/**
	 * Returns a string representation of the book.
	 */
	public String toString()
	{
		return isbn + ", " + author + ", \"" + title + "\"";
	}

	@Override
	public int hashCode()
	{
		return (int) isbn + author.hashCode() + title.hashCode();
	}
}
