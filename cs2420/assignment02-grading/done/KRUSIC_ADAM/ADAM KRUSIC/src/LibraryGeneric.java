package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.event.RowSorterEvent.Type;

import java.util.Comparator;

	public class LibraryGeneric<Type> {
	/**
	 * Class representation of a library (a collection of library books).
	 * 
	 */
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
		public String lookup(long isbn) {
			for(int j = 0; j<this.library.size();){
				if((this.library.get(j).getIsbn()) == isbn){
					return ((this.library.get(j).getHolder()).toString());
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
			ArrayList<LibraryBookGeneric<Type>> books = new ArrayList<LibraryBookGeneric<Type>>();	
				for (int i=0; i<this.library.size(); i++){
					if((this.library.get(i).getHolder()) == holder){
						books.add(this.library.get(i));
					}
				}
			return books;
		}

		/**
		 * Sets the holder and due date of the library book with the specified ISBN.
		 * 
		 * If no book with the specified ISBN isadd in the library, returns false.
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
			for(int y=0; y<this.library.size(); y++){
				if(this.library.get(y).getIsbn() == isbn){			}
					if(this.library.get(y).getHolder() == null){
						this.library.get(y).checkOut(holder, (new GregorianCalendar(year, month, day)));
				}
			}
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
		public boolean checkIn(long isbn){
			Iterator<LibraryBookGeneric<Type>> iterator = library.iterator();
			
			while(iterator.hasNext()) {
				LibraryBookGeneric<Type> current = iterator.next();
				
				if(current.getIsbn() == isbn && current.getHolder() != null) {
					current.checkIn();
					return true;
				}
			}
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
		public boolean checkIn(Type holder) {
			Iterator<LibraryBookGeneric<Type>> iterator = library.iterator();
			
			while(iterator.hasNext()) {
				LibraryBookGeneric<Type> current = iterator.next();
						
				if(current.getHolder() == holder) {
					current.checkIn();
					return true;
				}
			}
		return false;
	}
		/**
		   * Returns the list of library books, sorted by ISBN (smallest ISBN first).
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
		   */
		  public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year) {
			  ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
			  
			  OrderByDueDate comparator = new OrderByDueDate();
			  
			  sort(libraryCopy, comparator);
			  
			  return libraryCopy;			  
		  }



		  /**
		   * Performs a SELECTION SORT on the input ArrayList. 
		   *    1. Find the smallest item in the list. 
		   *    2. Swap the smallest item with the first item in the list. 
		   *    3. Now let the list be the remaining unsorted portion 
		   *       (second item to Nth item) and repeat steps 1, 2, and 3.
		   */
		  private static <ListType> void sort(ArrayList<ListType> list,
		      Comparator<ListType> c) {
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
	     * Returns a negative value if lhs is smaller than rhs. Returns a positive
	     * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
	     */
		  public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
		      return (int) (lhs.getIsbn() - rhs.getIsbn());
	      }
	  }

	 /**
	   * Comparator that defines an ordering among library books using the author,  and book title as a tie-breaker.
	   */
	  protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>> {

		  public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs) {
			  if(lhs.getAuthor() == rhs.getAuthor()) {
				   lhs.getTitle().compareTo(rhs.getTitle());
				   return (int) ()
				   }
			  } 
		  }
	  }

	  /**
	   * Comparator that defines an ordering among library books using the due date.
	   */
	  protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>> {

		  public int compare(LibraryBookGeneric<java.awt.Window.Type> lhs, LibraryBookGeneric<Type> rhs) {
		      return (int) (lhs.getDueDate() - rhs.getDueDate());      
	  }

}








