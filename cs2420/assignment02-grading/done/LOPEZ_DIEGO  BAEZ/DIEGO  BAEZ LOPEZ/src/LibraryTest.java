package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for Library.
 * 
 * @author Diego Baez Lopez UID: u075947
 */
public class LibraryTest {

	public static void main(String[] args) {

		printTestMessages();
		// test an empty library
		Library lib = new Library();
		ArrayList<LibraryBook> l = lib.lookup(null);
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

		// test a small library
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

		// test a medium library
		lib.addAll("Mushroom_Publishing.txt");

		// FILL IN

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

	/**
	 * Runs each test, and prints a list of messages to the console telling
	 * whether or not each test passed.
	 */
	private static void printTestMessages() {

		if (sizeTestForAddToLibrary()) {
			System.out.println("\"sizeTestForAddToLibrary()\" returned true: test pass SUCCESS");
		} else {
			System.err.println("\"sizeTestForAddToLibrary()\" returned false: test pass FAILURE");
		}

		if (sizeTestForAddAllToLibrarySuccessfulForArraylist()) {
			System.out
					.println("\"sizeTestForAddAllToLibrarySuccessfulForArraylist()\" returned true: test pass SUCCESS");
		} else {
			System.err.println(
					"\"sizeTestForAddAllToLibrarySuccessfulForArraylist()\" returned false: test pass FAILURE");
		}

		if (testLookupByHolderForHolderWithNoBooksCheckedOut()) {
			System.out
					.println("\"testLookupByHolderForHolderWithNoBooksCheckedOut()\" returned true: test pass SUCCESS");
		} else {
			System.err.println(
					"\"testLookupByHolderForHolderWithNoBooksCheckedOut()\" returned false:  test pass FAILURE");
		}

		if (testLookupByExistingISBN()) {
			System.out.println("\"testLookupByExistingISBN()\" returned true: test pass SUCCESS");
		} else {
			System.err.println("\"testLookupByExistingISBN()\" returned false:  test pass FAILURE");
		}

		if (testCheckoutSuccessfulForBookNotCheckedOut()) {
			System.out.println("\"testCheckoutSuccessfulForBookNotCheckedOut()\" returned true: test pass SUCCESS");
		} else {
			System.err.println("\"testCheckoutSuccessfulForBookNotCheckedOut()\" returned false:  test pass FAILURE");
		}

		if (testCheckoutFailureForBookCheckedOut()) {
			System.out.println("\"testCheckoutFailureForBookCheckedOut()\" returned true: test pass SUCCESS");
		} else {
			System.err.println("\"testCheckoutFailureForBookCheckedOut()\" returned false:  test pass FAILURE");
		}

		if (testCheckoutFailureForBookNotInLibrary()) {
			System.out.println("\"testCheckoutFailureForBookNotInLibrary()\" returned true: test pass SUCCESS");
		} else {
			System.err.println("\"testCheckoutFailureForBookNotInLibrary()\" returned false:  test pass FAILURE");
		}

		// TODO: Write for all tests
	}

	/**
	 * @returns true if the test passes, false otherwise. The test passes if the
	 *          library contains 100 entries, fails otherwise.
	 */
	public static boolean sizeTestForAddToLibrary() {

		Library library = new Library();
		long isbn = 123456789;
		String author = "1";
		String title = "2";
		// Add 100 things to the library
		for (int multipurposeInteger = 1; multipurposeInteger <= 100; multipurposeInteger++) {
			library.add(isbn, author, title);
			isbn += multipurposeInteger;
			author += multipurposeInteger;
			title += 2 * multipurposeInteger;
		}
		if (library.lookup(null).size() == 100) {
			return true;
		}
		return false;
	}

	/**
	 * @returns true if the test passes, false otherwise. The test passes if the
	 *          library contains 10 entries, fails otherwise.
	 */
	public static boolean sizeTestForAddAllToLibrarySuccessfulForArraylist() {

		Library library = new Library();
		ArrayList<LibraryBook> libraryList = new ArrayList<LibraryBook>();
		libraryList.add(new LibraryBook(123456781, "Mr. Writer", "How To Write: Volume 1"));
		libraryList.add(new LibraryBook(123456782, "Mr. Writer", "How To Write: Volume 2"));
		libraryList.add(new LibraryBook(123456783, "Mr. Writer", "How To Write: Volume 3"));
		libraryList.add(new LibraryBook(123456784, "Mr. Writer", "How To Write: Volume 4"));
		libraryList.add(new LibraryBook(123456785, "Mr. Writer", "How To Write: Volume 5"));
		libraryList.add(new LibraryBook(123456786, "Mr. Writer", "How To Write: Volume 6"));
		libraryList.add(new LibraryBook(123456787, "Mr. Writer", "How To Write: Volume 7"));
		libraryList.add(new LibraryBook(123456788, "Mr. Writer", "How To Write: Volume 8"));
		libraryList.add(new LibraryBook(123456789, "Mr. Writer", "How To Write: Volume 9"));
		libraryList.add(new LibraryBook(123456780, "Mr. Writer", "How To Write: Volume 10"));
		// Add the library list to the library
		library.addAll(libraryList);
		if (library.lookup(null).size() == 10) {
			return true;
		}
		return false;
	}

	/**
	 * @returns true if the test passes, false otherwise. The test passes if the
	 *          lookup returns an empty list for the holder named David, fails
	 *          otherwise.
	 */
	public static boolean testLookupByHolderForHolderWithNoBooksCheckedOut() {

		Library library = new Library();
		long isbn = 123456789;
		String author = "1";
		String title = "2";
		// Add 100 things to the library
		for (int multipurposeInteger = 1; multipurposeInteger <= 100; multipurposeInteger++) {
			library.add(isbn, author, title);
			isbn += multipurposeInteger;
			author += multipurposeInteger;
			title += 2 * multipurposeInteger;
		}
		if (library.lookup("David").size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @returns true if the test passes, false otherwise. The test passes if the
	 *          library contains 10 entries, fails otherwise.
	 */
	public static boolean testLookupByExistingISBN() {

		Library library = new Library();
		ArrayList<LibraryBook> libraryList = new ArrayList<LibraryBook>();
		libraryList.add(new LibraryBook(123456781, "Mr. Writer", "How To Write: Volume 1"));
		libraryList.add(new LibraryBook(123456782, "Mr. Writer", "How To Write: Volume 2"));
		libraryList.add(new LibraryBook(123456783, "Mr. Writer", "How To Write: Volume 3"));
		libraryList.add(new LibraryBook(123456784, "Mr. Writer", "How To Write: Volume 4"));
		libraryList.add(new LibraryBook(123456785, "Mr. Writer", "How To Write: Volume 5"));
		libraryList.add(new LibraryBook(123456786, "Mr. Writer", "How To Write: Volume 6"));
		libraryList.add(new LibraryBook(123456787, "Mr. Writer", "How To Write: Volume 7"));
		libraryList.add(new LibraryBook(123456788, "Mr. Writer", "How To Write: Volume 8"));
		libraryList.add(new LibraryBook(123456789, "Mr. Writer", "How To Write: Volume 9"));
		libraryList.add(new LibraryBook(123456780, "Mr. Writer", "How To Write: Volume 10"));
		// Add the library list to the library
		library.addAll(libraryList);
		if (library.lookup(null).contains(new LibraryBook(123456788, "Mr. Writer", "How To Write: Volume 8"))
				&& !(library.lookup(null).contains(987654321))) {
			return true;
		}
		return false;
	}

	/**
	 * @returns true if the test passes, false otherwise. The test passes if
	 *          Daniel successfully checked out "How to Write" Volumes 1 and 2,
	 *          and fails otherwise.
	 */
	public static boolean testCheckoutSuccessfulForBookNotCheckedOut() {

		Library library = new Library();
		ArrayList<LibraryBook> libraryList = new ArrayList<LibraryBook>();
		libraryList.add(new LibraryBook(123456781, "Mr. Writer", "How To Write: Volume 1"));
		libraryList.add(new LibraryBook(123456782, "Mr. Writer", "How To Write: Volume 2"));
		libraryList.add(new LibraryBook(123456783, "Mr. Writer", "How To Write: Volume 3"));
		library.addAll(libraryList);
		return library.checkout(123456781, "Daniel", 6, 25, 2017) && library.checkout(123456782, "Daniel", 6, 25, 2017);
	}

	/**
	 * @returns true if the test passes, false otherwise. The test passes if
	 *          Daniel successfully checked out the first three volumes of
	 *          "How to Write", and David failed to checkout the same books
	 *          while Daniel still had them checked out.
	 */
	public static boolean testCheckoutFailureForBookCheckedOut() {

		Library library = new Library();
		ArrayList<LibraryBook> libraryList = new ArrayList<LibraryBook>();
		libraryList.add(new LibraryBook(123456781, "Mr. Writer", "How To Write: Volume 1"));
		libraryList.add(new LibraryBook(123456782, "Mr. Writer", "How To Write: Volume 2"));
		libraryList.add(new LibraryBook(123456783, "Mr. Writer", "How To Write: Volume 3"));
		library.addAll(libraryList);
		library.checkout(123456781, "Daniel", 6, 25, 2017);
		library.checkout(123456782, "Daniel", 6, 25, 2017);
		library.checkout(123456783, "Daniel", 6, 25, 2017);
		return !(library.checkout(123456781, "David", 6, 25, 2017));
	}

	/**
	 * @returns true if the test passes, false otherwise. The test passes if
	 *          David fails to check out the fourth volume of "How to Write," as
	 *          it is not in the library.
	 */
	public static boolean testCheckoutFailureForBookNotInLibrary() {

		Library library = new Library();
		ArrayList<LibraryBook> libraryList = new ArrayList<LibraryBook>();
		libraryList.add(new LibraryBook(123456781, "Mr. Writer", "How To Write: Volume 1"));
		libraryList.add(new LibraryBook(123456782, "Mr. Writer", "How To Write: Volume 2"));
		libraryList.add(new LibraryBook(123456783, "Mr. Writer", "How To Write: Volume 3"));
		library.addAll(libraryList);
		return !(library.checkout(123456784, "David", 6, 25, 2017));
	}

	/**
	 * @returns true if the test passes, false otherwise. The test passes if
	 *          David fails to check out the fourth volume of "How to Write," as
	 *          it is not in the library.
	 */
	public static boolean testSuccessiveCheckoutAndCheckinSuccessful() {

		Library library = new Library();
		ArrayList<LibraryBook> libraryList = new ArrayList<LibraryBook>();
		libraryList.add(new LibraryBook(123456781, "Mr. Writer", "How To Write: Volume 1"));
		libraryList.add(new LibraryBook(123456782, "Mr. Writer", "How To Write: Volume 2"));
		libraryList.add(new LibraryBook(123456783, "Mr. Writer", "How To Write: Volume 3"));
		library.addAll(libraryList);
		return !(library.checkout(123456784, "David", 6, 25, 2017));
	}
}
