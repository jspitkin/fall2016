package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;

/**
 * 
 * Testing class for Library class
 * 
 * @author Diego Baez Lopez UID: u0759247
 *
 */
public class LibraryJUnitTester {

	// test an empty library
	Library lib = new Library();
	ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Jane Doe");
	ArrayList<LibraryBook> allBooksCheckedOut = lib.lookup("Jane Doe");

	@Test
	public void testEmptyLibrary() {
		// lookup isbn
		assertNull(lib.lookup(978037429279L));

		// lookup holder
		assertNotNull(booksCheckedOut);
		assertEquals(0, booksCheckedOut.size());

		// empty library checkout
		assertFalse(lib.checkout(978037429279L, "Jane Doe", 1, 1, 2008));

		// Further test checkout
		assertFalse(lib.checkout(978037429289L, "Jon Doe", 2, 1, 2008));
		assertFalse(lib.checkout(978037429289L, "Joseph Horne", 1, 31, 2008));
		assertFalse(lib.checkout(978037429289L, "Diego Baez", 4, 27, 2008));

		// empty library checkin(isbn)
		assertFalse(lib.checkin(978037429279L));

		// Just a few extra tests for checkin
		assertFalse(lib.checkin(978037423379L));
		assertFalse(lib.checkin(190037419279L));
		assertFalse(lib.checkin(887037429279L));

		// empty library checkin(holder)
		assertFalse(lib.checkin("Jane Doe"));

		// Further test checkin(holder)
		assertFalse(lib.checkin("Jane Doe"));
		assertFalse(lib.checkin("Joseph Horne"));
		assertFalse(lib.checkin("Diego Baez"));

	}

	@Test
	public void testSmallLibrary() {

		// test a small library
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");

		// small library lookup isbn
		assertNull(lib.lookup(9780330351690L));
		// more test for lookup isbn (Rest of the books from above)
		assertNull(lib.lookup(9780374292799L));
		assertNull(lib.lookup(9780446580342L));

		// Make sure lookup(null) value returns the length of our library
		assertEquals(3, lib.lookup(null).size());

		// small library checkout
		assertTrue(lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));

		// check out the rest of the books
		// TODO: should we make sure to double check that valid month and day
		// values are used?
		assertTrue(lib.checkout(9780374292799L, "Joseph Horne", 2, 3, 2015));
		assertTrue(lib.checkout(9780446580342L, "Diego Baez", 12, 31, 2015));

		// make sure the book that was checked out cannot be checked out again
		assertFalse(lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
		assertFalse(lib.checkout(9780374292799L, "Diego Baez", 2, 3, 2015));
		assertFalse(lib.checkout(9780446580342L, "Joseph Horne", 12, 31, 2015));

		booksCheckedOut = lib.lookup("Jane Doe");
		// small library lookup holder
		assertNotNull(booksCheckedOut);
		assertTrue(booksCheckedOut.size() == 1);
		assertEquals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"), booksCheckedOut.get(0));
		assertEquals("Jane Doe", booksCheckedOut.get(0).getHolder());

		// small library lookup holder with all names
		allBooksCheckedOut = lib.lookup("Jane Doe");
		allBooksCheckedOut.addAll(lib.lookup("Joseph Horne"));
		allBooksCheckedOut.addAll(lib.lookup("Diego Baez"));
		assertTrue(allBooksCheckedOut.size() == 3);

		// test checked out feature for all books
		assertEquals("Jane Doe", allBooksCheckedOut.get(0).getHolder());
		assertEquals("Joseph Horne", allBooksCheckedOut.get(1).getHolder());
		assertEquals("Diego Baez", allBooksCheckedOut.get(2).getHolder());

		// small library checkin(isbn)
		assertTrue(lib.checkin(9780330351690L));

		// make sure checkin behaves appropriately with the same ISBN
		assertFalse(lib.checkin(9780330351690L));

		// small library checkin(holder)
		assertFalse(lib.checkin("Jane Doe"));

	}

	@Test
	public void testMediumLibrary() {
		// test a medium library

		lib.addAll("Mushroom_Publishing.txt");
		// make sure the whole library was added this also checks to make sure
		// that all books aren't checked out
		assertEquals(23, lib.lookup(null).size());

		// check each ISBN returns a book the loop gets each isbn then uses the
		// lookup(null).get() to use that as
		// the test parameters
		for (int i = 0; i < lib.lookup(null).size(); i++) {
			// grab isbn for use on lookup(ISBN)
			long isbn = lib.lookup(null).get(i).getIsbn();
			// grab the author of the books to use lookup(string)
			String author = lib.lookup(null).get(i).getAuthor();
			// use the ISBN and check that the proper book is returned also make
			// sure the library has the book
			assertEquals(lib.lookup(null).get(i), lib.getBook(isbn));
			// use the author name to make sure that lookup does not mistake the
			// author as holder
			assertEquals(0, lib.lookup(author).size());
			// use author to see if checkin is also working properly
			assertFalse(lib.checkin(author));
			// also check that all the books are not checked out
			assertFalse(lib.checkin(isbn));
		}

		assertEquals(0, lib.lookup("John Doe").size());

		assertNull(lib.lookup(978033032340L));
		assertNull(lib.getBook(9780330351690L));
		assertNull(lib.getBook(9780446580342L));

		assertEquals(new LibraryBook(9781843193319L, "Alan Burt Akers", "Transit to Scorpio"),
				lib.getBook(9781843193319L));

		// add testing parameters for checkout and checkin on this specific
		// library
		// some of the testing used in small library could be used here but
		// might be redundant
		lib.checkout(9781843190004L, "Diego Baez", 2, 3, 2015);
		lib.checkout(9781843190011L, "Diego Baez", 2, 3, 2015);
		lib.checkout(9781843190028L, "Diego Baez", 2, 3, 2015);
		lib.checkout(9781843190042L, "Diego Baez", 2, 3, 2015);

		assertEquals(4, lib.lookup("Diego Baez").size());
		// should we be worried about case sensitivity?
		// assertEquals(4, lib.lookup("diego baez").size());

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
