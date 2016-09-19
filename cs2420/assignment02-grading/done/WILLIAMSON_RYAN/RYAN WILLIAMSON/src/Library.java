package assignment02;
/**
 * Ryan Williamson
 * Last Updated: Sept 7, 2016
 * u0838551
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
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

		for(int isbnFind = 0; isbnFind < library.size() - 1; isbnFind++){
			if(isbn == library.get(isbnFind).getIsbn()){
				return library.get(isbnFind).getHolder();
			}
		}
		//If no book with the specified ISBN is in the library, returns null.
		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder
	 *            -- holder whose checked out books are returned
	 */
	
	public ArrayList<LibraryBook> lookup(String holder) {

		//ArrayList to hold all books checked out by holder.
		ArrayList<LibraryBook> holderArray = new ArrayList<LibraryBook>();

		for(int holderFind = 0; holderFind < library.size() - 1; holderFind++){
			if(holder == library.get(holderFind).getHolder()){
				holderArray.add(library.get(holderFind));
			}
		}
		return holderArray;
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

		for(int findIsbn = 0; findIsbn < library.size() - 1; findIsbn++){
			if(isbn == library.get(findIsbn).getIsbn()){
				//Sets new dueDate
				GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
				
				//Calls helper method in LibraryBook class to set the new values.
				library.get(findIsbn).bookCheckOut(holder, dueDate);
				return true;
			}
		}
		//If the above isn't true than we can assume the book is already checked in 
		//or there is not a book with the correct isbn.
		return false;
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

		for(int findIsbn = 0; findIsbn < library.size() - 1; findIsbn++){
			if(isbn == library.get(findIsbn).getIsbn()){
				library.get(findIsbn).bookCheckIn();
				return true;
			}
		}
		//If the above isn't true than we can assume the book is already checked in 
		//or there is not a book with the correct isbn.
		return false;
	}

	/**
	 * Unsets the holder and due date for all library books checked out to the
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
		//Counter to track how many books were changed.
		int count = 0;
		for(int findHolder = 0; findHolder < library.size() - 1; findHolder++){
			if(holder == library.get(findHolder).getHolder()){
				library.get(findHolder).bookCheckIn();
				count++;
			}
		}
		//If count > 0 than more than 1 book was changed and must return true.
		if(count > 0){
			return true;
		}
		else
			//Will return false if 0 books were changed.
			return false;
	}
}