package assignment02;
//Gabe Brodbeck u0847035
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for Library.
 * 
 * 
 */
public class LibraryTest {

	public static void main(String[] args) {
		// test an empty library
		Library lib = new Library();

		if (lib.lookup(978037429279L) != null)
			System.err.println("TEST FAILED -- empty library: lookup(isbn)");
		ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
		if (booksCheckedOut == null || booksCheckedOut.size() != 0)
			System.err.println("TEST FAILED -- empty library: lookup(holder)");
		if (lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008))
			System.err.println("TEST FAILED -- empty library: checkout");
		if (lib.checkin(978037429279L))
			System.err.println("TEST FAILED -- empty library: checkin(isbn)");
		if (lib.checkin("Jane Doe"))
			System.err.println("TEST FAILED -- empty library: checkin(holder)");

		// test a small library--------------------------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------------------------
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");

		if (lib.lookup(9780330351690L) != null)
			System.err.println("TEST FAILED -- small library: lookup(isbn)");
		if (!lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008))
			System.err.println("TEST FAILED -- small library: checkout");
		booksCheckedOut = lib.lookup("Jane Doe");
		if (booksCheckedOut == null || booksCheckedOut.size() != 1
				|| !booksCheckedOut.get(0).equals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))
				|| !booksCheckedOut.get(0).getHolder().equals("Jane Doe")
				|| !booksCheckedOut.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
			System.err.println("TEST FAILED -- small library: lookup(holder)");
		if (!lib.checkin(9780330351690L))
			System.err.println("TEST FAILED -- small library: checkin(isbn)");
		if (lib.checkin("Jane Doe"))
			System.err.println("TEST FAILED -- small library: checkin(holder)");

		// test a medium library-------------------------------------------------------------------------------------------------------------------------
		//-----------------------------------------------------------------------------------------------------------------------------------------------
		lib.addAll("Mushroom_Publishing.txt");
		//Tests to be sure that it is possible to find the newly added books.
		if(lib.lookUpFindBook(9781843190677L)==null){
			System.err.println("TEST FAILED -- medium library: lookup(isbn)");
		}
		//These two tests check 2 books out from the library under the name Stevie Lund
		if (!lib.checkout(9781843190677L, "Stevie Lund", 1, 1, 2008)){
		System.err.println("TEST FAILED -- small library: checkout");
		}
		if (!lib.checkout(9781843193319L, "Stevie Lund", 1, 1, 2008)){
			System.err.println("TEST FAILED -- small library: checkout");
			}
		if (!lib.checkout(9781843190936L, "Alicia Laurent", 11, 11, 2012)){
			System.err.println("TEST FAILED -- small library: checkout");
			}
		//Checks to see if it is possible to check out a book a 2nd time
		if (lib.checkout(9781843193319L, "Stevie Lund", 1, 1, 2011)){
			System.err.println("TEST FAILED -- small library: checkout. Checked out a book twice");
			}
		//Checks to make sure that the lookup(handle) methods work properly.
		booksCheckedOut = lib.lookup("Stevie Lund");
		if(booksCheckedOut.size()<1){
			System.err.println("TEST FAILED -- medium library: lookup(handle). Did not find any books checked out by Stevie Lund "
					+ "but it should find 2 books");
		}
		if(booksCheckedOut.size()!=2){
			System.err.println("TEST FAILED -- medium library: lookup(handle). Did not find the correct number of books checked"
					+ " out by Stevie Lund");
		}
		lib.checkin(9781843190677L);
		booksCheckedOut = lib.lookup("Stevie Lund");
		if(booksCheckedOut.size()<1){
			System.err.println("TEST FAILED -- medium library: lookup(handle). Did not find any books checked out by Stevie Lund "
					+ "but it should find 1 book");
		}
		if(booksCheckedOut.size()==2){
			System.err.println("TEST FAILED -- medium library: lookup(handle). Stevie Lund checked a book back in but the "
					+ "lookup(handle) method found that book to still be checked out.");
		}
		lib.checkin("Stevie Lund");
		booksCheckedOut = lib.lookup("Stevie Lund");
		if(booksCheckedOut.size()!=0){
			System.err.println("TEST FAILED -- medium library: checkin(handle). Failed to check in books held by handle");
		}

		System.out.println("Testing done.");
	}

	/**
	 * Returns a library of "dummy" books (random ISBN and placeholders for
	 * author and title).
	 * 
	 * Useful for collecting running times for operations on libraries of
	 * varying size.
	 * 
	 * @param size
	 *            -- size of the library to be generated
	 */
	public static ArrayList<LibraryBook> generateLibrary(int size) {
		ArrayList<LibraryBook> result = new ArrayList<LibraryBook>();

		for (int i = 0; i < size; i++) {
			// generate random ISBN
			Random randomNumGen = new Random();
			String isbn = "";
			for (int j = 0; j < 13; j++)
				isbn += randomNumGen.nextInt(10);

			result.add(new LibraryBook(Long.parseLong(isbn), "An author", "A title"));
		}

		return result;
	}

	/**
	 * Returns a randomly-generated ISBN (a long with 13 digits).
	 * 
	 * Useful for collecting running times for operations on libraries of
	 * varying size.
	 */
	public static long generateIsbn() {
		Random randomNumGen = new Random();

		String isbn = "";
		for (int j = 0; j < 13; j++)
			isbn += randomNumGen.nextInt(10);

		return Long.parseLong(isbn);
	}
}
