package assignment02;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for Library.
 * 
 * @author Ashley Grevelink u0749357
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
//		Library lib2 = new Library();
//		lib2.addAll("Mushroom_Publishing.txt");
//		//9781843190004L
//
//		if (lib2.lookup(9781843190004L) != null)
//			System.err.println("TEST FAILED -- medium library: lookup(isbn)");
//		if (!lib2.checkout(9781843190004L, "Joe Bloggs", 1, 1, 2008))
//			System.err.println("TEST FAILED -- medium library: checkout");
//		ArrayList<LibraryBook> booksCheckedOut2 = lib2.lookup("Joe Bloggs");
//		if (booksCheckedOut2 == null || booksCheckedOut2.size() != 1
//				|| !booksCheckedOut2.get(0).equals(new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"))
//				|| !booksCheckedOut2.get(0).getHolder().equals("Joe Bloggs")
//				|| !booksCheckedOut2.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
//			System.err.println("TEST FAILED -- medium library: all lookup(holder)");
//		
//		if (booksCheckedOut2 == null || booksCheckedOut2.size() != 1) 
//			System.err.println("TEST FAILED -- medium library: null lookup(holder)");
//		if (!booksCheckedOut2.get(0).equals(new Book(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound")))
//				System.err.println("TEST FAILED -- medium library: Book equals lookup(holder)");
//				
//		if ( !booksCheckedOut2.get(0).getHolder().equals("Joe Bloggs"))
//			System.err.println("TEST FAILED -- medium library: holder equals lookup(holder)");
//		if ( !booksCheckedOut2.get(0).getDueDate().equals(new GregorianCalendar(2008, 1, 1)))
//			System.err.println("TEST FAILED -- medium library: dueDate equals lookup(holder)");
//		
//		
//		if (!lib2.checkin(9781843190004L))
//			System.err.println("TEST FAILED -- medium library: checkin(isbn)");
//		if (lib2.checkin("Joe Bloggs"))
//			System.err.println("TEST FAILED -- medium library: checkin(holder)");
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
