package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 * 
 * @author Ashley Grevelink u0749357
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
		Collection<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

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
		for (int index = 0; index < library.size(); index++) {
			LibraryBookGeneric<Type> tempBook = library.get(index);
			long tempIsbn = tempBook.getIsbn();
			if (tempIsbn == isbn) {
				// TODO I'm not sure type-casting works this way. I have to
				// return the holder,
				// but what type? I'm going to return Type
				return (Type) tempBook.getHolder();
			}
		}
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
	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder) {
		ArrayList<LibraryBookGeneric<Type>> booksCheckedOut = new ArrayList<LibraryBookGeneric<Type>>();

		int index = 0;
		while (index < library.size()) {
			LibraryBookGeneric<Type> tempBook = library.get(index);
			Type tempHolder = tempBook.getHolder();
			if (tempHolder != null) {
				if (tempHolder.equals(holder)) {
					booksCheckedOut.add(tempBook);
				}
			}
			index++;
		}
		return booksCheckedOut;
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
		for (int index = 0; index < library.size(); index++) {
			LibraryBookGeneric<Type> tempBook = library.get(index);
			long tempIsbn = tempBook.getIsbn();
			if (tempIsbn == isbn) {
				if (tempBook.getHolder() != null) {
					// If the book with the specified ISBN is already checked
					// out, returns false.
					return false;
				}
				// Sets the holder and due date of the library book with the
				// specified ISBN.
				GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
				tempBook.checkOut(holder, dueDate);
				return true;
			}
		}
		// If no book with the specified ISBN is in the library, returns false.
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
		for (int index = 0; index < library.size(); index++) {
			LibraryBookGeneric<Type> tempBook = library.get(index);
			long tempIsbn = tempBook.getIsbn();
			if (tempIsbn == isbn) {
				if (tempBook.getHolder() == null) {
					// If the book with the specified ISBN is already checked
					// in, returns false.
					return false;
				}
				// check in the book
				tempBook.checkIn();
				return true;
			}
		}
		// If no book with the specified ISBN is in the library, returns false.
		return false;
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
	public boolean checkin(Type holder) {
		int holdersBooks = 0;
		for (int index = 0; index < library.size(); index++) {
			LibraryBookGeneric<Type> tempBook = library.get(index);
			Type tempHolder = tempBook.getHolder();
			if (tempHolder == holder) {
				tempBook.checkIn();
				holdersBooks++;
			}
		}
		if (holdersBooks > 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the list of library books, sorted by ISBN (smallest ISBN first).
	 * @return ArrayList<LibraryBookGeneric<Type>> list of library books, sorted by ISBN (smallest first)
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
	 * @return ArrayList<LibraryBookGeneric<Type>> the list of library books, sorted by author
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor() {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		OrderByAuthor comparator = new OrderByAuthor();

		sort(libraryCopy, comparator);

		return libraryCopy;
	}

	/**
	 * Returns the list of library books whose due date is older than the input
	 * date. The list is sorted by date (oldest first).
	 *
	 * If no library books are overdue, returns an empty list.
	 * 
	 * @return ArrayList<LibraryBookGeneric<Type>> or empty list
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		GregorianCalendar dateCutoff = new GregorianCalendar(year, month, day);
		
		for (int index = 0; index < library.size(); index++){
			GregorianCalendar tempCutoff = library.get(index).getDueDate();
			//if the book is overdue
			if (tempCutoff != null && tempCutoff.compareTo(dateCutoff) > 0 && library.get(index) != null)
			{
				//add to libraryCopy
				libraryCopy.add(library.get(index));
			}
		}
		if (libraryCopy.size() > 0){
			OrderByDueDate comparator = new OrderByDueDate();
			sort(libraryCopy, comparator);
		}
		return libraryCopy;


	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 1. Find the smallest
	 * item in the list. 2. Swap the smallest item with the first item in the
	 * list. 3. Now let the list be the remaining unsorted portion (second item
	 * to Nth item) and repeat steps 1, 2, and 3.
	 * 
	 * @param ArrayList<ListType>, Comparator<ListType>
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for (int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the ISBN.
	 */
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a
		 * positive value if lhs is larger than rhs. Returns 0 if lhs and rhs
		 * are equal.
		 * @returns -1, 0, 1
		 */
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			return (int) (lhs.getIsbn() - rhs.getIsbn());
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the author,
	 * and book title as a tie-breaker.
	 */
	protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a
		 * positive value if lhs is larger than rhs. Returns 0 if lhs and rhs
		 * are equal. Uses book title as a secondary test.
		 * 
		 * @return -1, 0, 1
		 * 
		 */
		@Override
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			String lhsAuthor = lhs.getAuthor();
			String rhsAuthor = rhs.getAuthor();
			if (lhsAuthor.compareTo(rhsAuthor) == 0) {
				String lhsTitle = lhs.getTitle();
				String rhsTitle = rhs.getTitle();
				return lhsTitle.compareTo(rhsTitle);
			} else {
				return -1;
			}
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the due
	 * date.
	 */
	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {

		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a
		 * positive value if lhs is larger than rhs. Returns 0 if lhs and rhs
		 * are equal.
		 * 
		 * @return -1, 0, 1
		 */
		@Override
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			GregorianCalendar lhsDate = lhs.getDueDate();
			GregorianCalendar rhsDate = rhs.getDueDate();
			return lhsDate.compareTo(rhsDate);
		}
	}
}
