package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 *
 * @author Diego Baez Lopez UID: u075947
 * 
 */
public class Library {

	private ArrayList<LibraryBook> library;

	public Library() {
		library = new ArrayList<LibraryBook>();
	}

	/**
	 * Add the specified book to the library, assume no duplicates.
	 * 
	 * @param isbn
	 *            -- ISBN of the book to be added
	 * @param author
	 *            -- author of the book to be added
	 * @param title
	 *            -- title of the book to be added
	 */
	public void add(long isbn, String author, String title) {

		library.add(new LibraryBook(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list
	 *            -- list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBook> list) {

		library.addAll(list);
	}

	/**
	 * Add books specified by the input file. One book per line with ISBN,
	 * author, and title separated by tabs.
	 * 
	 * If file does not exist or format is violated, do nothing.
	 * 
	 * @param filename
	 */
	public void addAll(String filename) {

		ArrayList<LibraryBook> toBeAdded = new ArrayList<LibraryBook>();

		try (Scanner fileIn = new Scanner(new File(filename))) {

			int lineNum = 1;

			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();

				try (Scanner lineIn = new Scanner(line)) {
					lineIn.useDelimiter("\\t");

					if (!lineIn.hasNextLong()) {
						throw new ParseException("ISBN", lineNum);
					}
					long isbn = lineIn.nextLong();

					if (!lineIn.hasNext()) {
						throw new ParseException("Author", lineNum);
					}
					String author = lineIn.next();

					if (!lineIn.hasNext()) {
						throw new ParseException("Title", lineNum);
					}
					String title = lineIn.next();
					toBeAdded.add(new LibraryBook(isbn, author, title));
				}
				lineNum++;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns null.
	 * 
	 * @param isbn
	 *            -- ISBN of the book to be looked up
	 */
	public String lookup(long isbn) {

		for (int i = 0; i < library.size(); i++) {
			if (library.get(i).getIsbn() == isbn) {
				return library.get(i).getHolder();
			}
		}
		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * 
	 * @param holder
	 *            -- holder whose checked out books are returned
	 */
	public ArrayList<LibraryBook> lookup(String holder) {

		// FILL IN -- do not return null
		ArrayList<LibraryBook> foundBooks = new ArrayList<LibraryBook>();
		if (holder == null) {
			for (int i = 0; i < library.size(); i++) {
				if (library.get(i).getHolder() == holder) {
					foundBooks.add(library.get(i));
				}
			}
		} else {

			for (int i = 0; i < library.size(); i++) {
				if (library.get(i).getHolder() != null) {

					if (library.get(i).getHolder().compareToIgnoreCase(holder) == 0) {
						foundBooks.add(library.get(i));
					}
				}
			}
		}
		return foundBooks;
	}

	/**
	 * A helper method added to return the specific book looked up by ISBN.
	 * 
	 * If book is not found null is returned
	 * 
	 * @param isbn
	 *            -- isbn of the book being looked up.
	 */
	public LibraryBook getBook(long isbn) {

		for (int i = 0; i < library.size(); i++) {
			if (library.get(i).getIsbn() == isbn) {
				return library.get(i);
			}
		}
		return null;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked out, returns
	 * false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn
	 *            -- ISBN of the library book to be checked out
	 * @param holder
	 *            -- new holder of the library book
	 * @param month
	 *            -- month of the new due date of the library book
	 * @param day
	 *            -- day of the new due date of the library book
	 * @param year
	 *            -- year of the new due date of the library book
	 * 
	 */
	public boolean checkout(long isbn, String holder, int month, int day, int year) {

		// FILL IN -- do not return false unless appropriate
		GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
		if (lookup(isbn) == null) {
			LibraryBook currentBook = getBook(isbn);
			if (currentBook != null) {
				currentBook.setDueDate(dueDate);
				currentBook.setHolder(holder);
				return true;
			}
			return false; // no book found

		}
		return false; // book already checked out
	}

	/**
	 * Unsets the holder and due date of the library book.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked in, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn
	 *            -- ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn) {

		// FILL IN -- do not return false unless appropriate
		LibraryBook check = getBook(isbn);
		if (check != null) {
			if (lookup(isbn) != null) {
				check.setDueDate(null);
				check.setHolder(null);
				return true;
			}
			return false; // Book checked in already
		}
		return false; // no book found
	}

	/**
	 * Unsets the holder and due date for all library books checked out be the
	 * specified holder.
	 * 
	 * If no books with the specified holder are in the library, returns false;
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param holder
	 *            -- holder of the library books to be checked in
	 */
	public boolean checkin(String holder) {

		ArrayList<LibraryBook> current = new ArrayList<LibraryBook>();
		current = lookup(holder);

		if (current.isEmpty()) {
			return false;
		}
		for (LibraryBook book : current) {
			book.setDueDate(null);
			book.setHolder(null);
		}

		return true;
	}
}
