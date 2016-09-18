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
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 */
public class LibraryGeneric<Type> {

	private ArrayList<LibraryBookGeneric<Type>> library;

	public LibraryGeneric() {
		library = new ArrayList<LibraryBookGeneric<Type>>();
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
		library.add(new LibraryBookGeneric<Type>(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list
	 *            -- list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBookGeneric<Type>> list) {
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
		ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

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
					toBeAdded.add(new LibraryBookGeneric<Type>(isbn, author, title));
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
	public Type lookup(long isbn) {

		for(int isbnFind = 0; isbnFind < library.size() ; isbnFind++){
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
	 * @return 
	 * 			-- Returns an ArrayList consisting only of the books checked out by the holder. Empty if no books are checked out.
	 */

	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {

		//ArrayList to hold all books checked out by holder.
		ArrayList<LibraryBookGeneric<Type>> holderArray = new ArrayList<LibraryBookGeneric<Type>>();

		for(int holderFind = 0; holderFind < library.size(); holderFind++){
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
	public boolean checkout(long isbn, Type holder, int month, int day, int year) {

		for(int findIsbn = 0; findIsbn < library.size(); findIsbn++){
			if(isbn == library.get(findIsbn).getIsbn()){
				//Sets new dueDate
				GregorianCalendar newDueDate = new GregorianCalendar(year, month, day);
				//If the current holder of the book doesn't equal null, than the book is already
				//checked out and will return false.
				if(library.get(findIsbn).getHolder() != null){
					return false;
				}
				//Calls helper method in LibraryBook class to set the new values.
				library.get(findIsbn).bookCheckOut(holder, newDueDate);

				return true;
			}
		}
		//If the above isn't true than we can assume there isn't a book with the correct isbn.
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

		for(int findIsbn = 0; findIsbn < library.size() ; findIsbn++){
			if(isbn == library.get(findIsbn).getIsbn()){
				//Checks to see if the book is already turned in.
				if(library.get(findIsbn).getHolder() == null){
					return false;
				}
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
	public boolean checkin(Type holder) {
		//Counter to track how many books were changed.
		int count = 0;
		for(int findHolder = 0; findHolder < library.size() ; findHolder++){
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

	/**
	 * Returns the list of library books, sorted by ISBN (smallest ISBN first).    
	 * @return - sorted list of library books by ISBN.
	 */

	public ArrayList<LibraryBookGeneric<Type>> getInventoryList() {     

		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();     
		libraryCopy.addAll(library);
		OrderByIsbn comparator = new OrderByIsbn();     
		sort(libraryCopy, comparator);     
		return libraryCopy;
	}
	/**
	 * Returns the list of library books, sorted by author
	 * @return - sorted list of library books by author.
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {    
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		OrderByAuthor comparator = new OrderByAuthor();
		libraryCopy.addAll(library);
		sort(libraryCopy, comparator);
		return libraryCopy;   
	}
	/**
	 * Returns the list of library books whose due date is older than the input date. 
	 * The list is sorted by date (oldest first).
	 * If no library books are overdue, returns an empty list.    
	 * @param month
	 * @param day
	 * @param year
	 * @return - sorted list of library books by due date.
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {    
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();

		OrderByDueDate comparator = new OrderByDueDate();

		//Creates a new date based on the specified paraments that were passed in.
		GregorianCalendar specifiedDate = new GregorianCalendar(year, month, day);

		for(int overDue = 0; overDue < library.size() ; overDue++){

			//Checks to see if due date isn't null.
			if(library.get(overDue).getDueDate() != null){
				if(library.get(overDue).getDueDate().after(specifiedDate)){
					libraryCopy.add(library.get(overDue));
				}
			} 
		}

		sort(libraryCopy, comparator);

		return libraryCopy;
	}
	/**
	 *  Performs a SELECTION SORT on the input ArrayList.  
	 *  1. Find the smallest item in the list. 
	 *  2. Swap the smallest item with the first item in the list.  
	 *  3. Now let the list be the remaining unsorted portion   
	 *  (second item to Nth item) and repeat steps 1, 2, and 3.
	 * @param list
	 * @param c
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) { 
		for(int i = 0; i < list.size() - 1; i++) { 
			int j, minIndex;  
			for (j = i + 1, minIndex = i; j < list.size(); j++)         
				if (c.compare(list.get(j), list.get(minIndex)) < 0)        
					minIndex = j;       
			ListType temp = list.get(i);       
			list.set(i, list.get(minIndex));    
			list.set(minIndex, temp);    
		}   
	}
	/** Comparator that defines an ordering among library books using the ISBN. */   
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

		/** Returns a negative value if lhs is smaller than rhs. Returns a positive
		 * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.     
		 */     
		public int compare(LibraryBookGeneric<Type> lhs,LibraryBookGeneric<Type> rhs) {     
			return (int) (lhs.getIsbn() - rhs.getIsbn());    
		} 

	}
	/**Comparator that defines an ordering among library books using the author, and book title as a tie-breaker. 
	 */   
	protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {

		@Override
		public int compare(LibraryBookGeneric<Type> o1, LibraryBookGeneric<Type> o2) {
			//If the author is identical than the comparison will be done with the
			// titles.
			if(o1.getAuthor().equals(o2.getAuthor())){
				return (o1.getTitle().compareTo(o2.getTitle()));
			}
			else

				return (o1.getAuthor().compareTo(o2.getAuthor()));
		}

	}
	/**Comparator that defines an ordering among library books using the due date. 
	 * 
	 */   
	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns 0 if the dates are equal; 
		 * a value < 0 if this Date is before the Date argument;
		 * and a value greater than 0 if this Date is after the Date argument.
		 */
		@Override
		public int compare(LibraryBookGeneric<Type> o1, LibraryBookGeneric<Type> o2) {

			return (o1.getDueDate().compareTo(o2.getDueDate()));
		}
	}
}
