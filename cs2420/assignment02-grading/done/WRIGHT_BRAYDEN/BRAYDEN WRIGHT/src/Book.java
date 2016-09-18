package assignment02;

/**
 * File modified by Brayden Wright.
 */

/**
 * Class representation of a book. The ISBN, author, and title can never change
 * once the book is created.
 * <p>
 * Note that ISBNs are unique.
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
     * @param other -- the object begin compared with "this"
     * @return true if "other" is a Book and is equal to "this", false otherwise
     */
    public boolean equals(Object other) {

        // Check if 'other' is a Book, return false otherwise
        if (!(other instanceof Book)) {
            return false;
        }
        Book otherBook = (Book) other;

        // Returns true if ISBN, author, and title are equal; false if any are not equal.
        return (this.getIsbn() == otherBook.getIsbn())
                & (this.getAuthor().equals(otherBook.getAuthor()))
                & (this.getTitle().equals(otherBook.getTitle()));
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
