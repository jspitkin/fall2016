package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * JUnit tester for our LibraryGeneric class
 *
 * @author Diego Baez Lopez UID: u075947
 */

public class LibraryGenericJUnitTester {

	@Test
	public void testNameID() {
		// test a library that uses names (String) to id patrons
		LibraryGeneric<String> lib1 = new LibraryGeneric<String>();
		lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib1.add(9780446580342L, "David Baldacci", "Simple Genius");
		assertEquals(3, lib1.lookup(null).size());
		String patron1 = "Jane Doe";

		// first checkout
		assertTrue(lib1.checkout(9780330351690L, patron1, 1, 1, 2008));

		// second checkout
		assertTrue(lib1.checkout(9780374292799L, patron1, 1, 1, 2008));

		// lookup holder
		ArrayList<LibraryBookGeneric<String>> booksCheckedOut1 = lib1.lookup(patron1);
		assertNotNull(booksCheckedOut1);
		assertEquals(2, booksCheckedOut1.size());
		assertTrue(booksCheckedOut1.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut1.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron1, booksCheckedOut1.get(0).getHolder());
		assertEquals(new GregorianCalendar(2008, 1, 1), booksCheckedOut1.get(0).getDueDate());
		assertEquals(patron1, booksCheckedOut1.get(1).getHolder());
		assertEquals(new GregorianCalendar(2008, 1, 1), booksCheckedOut1.get(1).getDueDate());

		// checkin holder
		assertTrue(lib1.checkin(patron1));
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		lib.addAll("Mushroom_Publishing.txt");

		lib.checkout(9781843190004L, "Diego Baez", 2, 3, 2015); // (3/2/2015)
		lib.checkout(9781843190011L, "Diego Baez", 2, 3, 2015);
		lib.checkout(9781843190028L, "Diego Baez", 2, 3, 2015);
		lib.checkout(9781843190042L, "Diego Baez", 2, 3, 2015);

		ArrayList<LibraryBookGeneric<String>> testLib = new ArrayList<LibraryBookGeneric<String>>();
		testLib = lib.getOverdueList(3, 4, 2014); // (4/4/2015)
		assertTrue(testLib.isEmpty());

		// System.out.println("Test1 done");

		lib.checkout(9781843190110L, "Joseph Horne", 1, 2, 2014); // (2/2/2014)

		testLib = lib.getOverdueList(1, 2, 2015); // (3/4/2015)
		assertFalse(testLib.isEmpty());
		assertEquals(1, testLib.size());

		// addtional test, make sure dueDate = today is not Overdue
		testLib = lib.getOverdueList(2, 3, 2015);
		assertEquals(1, testLib.size());

		// check that list comes back sorted with oldest first
		lib.checkout(9781843193319L, "OldestOverdue", 2, 3, 2012);
		lib.checkout(9781843190875L, "NewestOverdue", 2, 2, 2015);
		// Joseph Horne in middle
		testLib = lib.getOverdueList(2, 3, 2015);
		assertEquals(3, testLib.size());

		assertEquals("OldestOverdue", testLib.get(0).getHolder());
		assertEquals("Joseph Horne", testLib.get(1).getHolder());
		assertEquals("NewestOverdue", testLib.get(2).getHolder());

		// TESTS on getInventoryList
		testLib = lib.getInventoryList();
		assertEquals(23, testLib.size());
		assertEquals(9781843190004L, testLib.get(0).getIsbn());
		assertEquals(9781843190011L, testLib.get(1).getIsbn());
		assertEquals(9781843190028L, testLib.get(2).getIsbn());
		assertEquals(9781843190042L, testLib.get(3).getIsbn());
		assertEquals(9781843190073L, testLib.get(4).getIsbn());
		assertEquals(9781843190110L, testLib.get(5).getIsbn());
		assertEquals(9781843190349L, testLib.get(6).getIsbn());
		assertEquals(9781843190363L, testLib.get(7).getIsbn());
		assertEquals(9781843190394L, testLib.get(8).getIsbn());
		assertEquals(9781843190400L, testLib.get(9).getIsbn());
		assertEquals(9781843190479L, testLib.get(10).getIsbn());
		assertEquals(9781843190516L, testLib.get(11).getIsbn());
		assertEquals(9781843190677L, testLib.get(12).getIsbn());
		assertEquals(9781843190769L, testLib.get(13).getIsbn());
		assertEquals(9781843190875L, testLib.get(14).getIsbn());
		assertEquals(9781843190936L, testLib.get(15).getIsbn());
		assertEquals(9781843190998L, testLib.get(16).getIsbn());
		assertEquals(9781843191230L, testLib.get(17).getIsbn());
		assertEquals(9781843192022L, testLib.get(18).getIsbn());
		assertEquals(9781843192039L, testLib.get(19).getIsbn());
		assertEquals(9781843192701L, testLib.get(20).getIsbn());
		assertEquals(9781843192954L, testLib.get(21).getIsbn());
		assertEquals(9781843193319L, testLib.get(22).getIsbn());

	}

	@Test
	public void testPhoneNumberID() {
		// test a library that uses phone numbers (PhoneNumber) to id patrons
		LibraryGeneric<PhoneNumber> lib2 = new LibraryGeneric<PhoneNumber>();
		lib2.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib2.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib2.add(9780446580342L, "David Baldacci", "Simple Genius");

		PhoneNumber patron2 = new PhoneNumber("801.555.1234");

		// first checkout
		assertTrue(lib2.checkout(9780330351690L, patron2, 1, 1, 2008));

		// second checkout
		assertTrue(lib2.checkout(9780374292799L, patron2, 1, 1, 2008));

		ArrayList<LibraryBookGeneric<PhoneNumber>> booksCheckedOut2 = lib2.lookup(patron2);
		// lookup holder
		assertNotNull(booksCheckedOut2 == null);
		assertEquals(2, booksCheckedOut2.size());
		assertTrue(booksCheckedOut2.contains(new Book(9780330351690L, "Jon Krakauer", "Into the Wild")));
		assertTrue(booksCheckedOut2.contains(new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat")));
		assertEquals(patron2, booksCheckedOut2.get(0).getHolder());
		assertEquals(new GregorianCalendar(2008, 1, 1), booksCheckedOut2.get(0).getDueDate());
		assertEquals(patron2, booksCheckedOut2.get(1).getHolder());
		assertEquals(new GregorianCalendar(2008, 1, 1), booksCheckedOut2.get(1).getDueDate());

		// checkin holder
		assertTrue(lib2.checkin(patron2));

	}

	@Test
	public void testGetInventoryListWUnsorted() {
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		lib.addAll("Mushroom_Publishing.txt");

		lib.checkout(9781843190004L, "Diego Baez", 2, 3, 2015); // (3/2/2015)
		lib.checkout(9781843190011L, "Diego Baez", 2, 3, 2015);
		lib.checkout(9781843190028L, "Diego Baez", 2, 3, 2015);
		lib.checkout(9781843190042L, "Diego Baez", 2, 3, 2015);

		ArrayList<LibraryBookGeneric<String>> testLib = new ArrayList<LibraryBookGeneric<String>>();
		testLib = lib.getOverdueList(3, 4, 2014); // (4/4/2015)
		assertTrue(testLib.isEmpty());

		lib.checkout(9781843190110L, "Joseph Horne", 1, 2, 2014); // (2/2/2014)

		testLib = lib.getOverdueList(1, 2, 2015); // (3/4/2015)
		assertFalse(testLib.isEmpty());
		assertEquals(1, testLib.size());

		// addtional test, make sure dueDate = today is not Overdue
		testLib = lib.getOverdueList(2, 3, 2015);
		assertEquals(1, testLib.size());

		// check that list comes back sorted with oldest first
		lib.checkout(9781843193319L, "OldestOverdue", 2, 3, 2012);
		lib.checkout(9781843190875L, "NewestOverdue", 2, 2, 2015);
		// Joseph Horne in middle
		testLib = lib.getOverdueList(2, 3, 2015);
		assertEquals(3, testLib.size());

		assertEquals("OldestOverdue", testLib.get(0).getHolder());
		assertEquals("Joseph Horne", testLib.get(1).getHolder());
		assertEquals("NewestOverdue", testLib.get(2).getHolder());

		// TESTS on getInventoryList
		testLib = lib.getInventoryList();
		assertEquals(23, testLib.size());
		assertEquals(9781843190004L, testLib.get(0).getIsbn());
		assertEquals(9781843190011L, testLib.get(1).getIsbn());
		assertEquals(9781843190028L, testLib.get(2).getIsbn());
		assertEquals(9781843190042L, testLib.get(3).getIsbn());
		assertEquals(9781843190073L, testLib.get(4).getIsbn());
		assertEquals(9781843190110L, testLib.get(5).getIsbn());
		assertEquals(9781843190349L, testLib.get(6).getIsbn());
		assertEquals(9781843190363L, testLib.get(7).getIsbn());
		assertEquals(9781843190394L, testLib.get(8).getIsbn());
		assertEquals(9781843190400L, testLib.get(9).getIsbn());
		assertEquals(9781843190479L, testLib.get(10).getIsbn());
		assertEquals(9781843190516L, testLib.get(11).getIsbn());
		assertEquals(9781843190677L, testLib.get(12).getIsbn());
		assertEquals(9781843190769L, testLib.get(13).getIsbn());
		assertEquals(9781843190875L, testLib.get(14).getIsbn());
		assertEquals(9781843190936L, testLib.get(15).getIsbn());
		assertEquals(9781843190998L, testLib.get(16).getIsbn());
		assertEquals(9781843191230L, testLib.get(17).getIsbn());
		assertEquals(9781843192022L, testLib.get(18).getIsbn());
		assertEquals(9781843192039L, testLib.get(19).getIsbn());
		assertEquals(9781843192701L, testLib.get(20).getIsbn());
		assertEquals(9781843192954L, testLib.get(21).getIsbn());
		assertEquals(9781843193319L, testLib.get(22).getIsbn());
	}

	@Test
	public void testGetOrderedByAuthor() {
		LibraryGeneric<String> lib = new LibraryGeneric<String>();
		lib.addAll("Mushroom_Publishing.txt");

		ArrayList<LibraryBookGeneric<String>> testLib = new ArrayList<LibraryBookGeneric<String>>();
		testLib = lib.getOrderedByAuthor();
		assertEquals(23, testLib.size());
		// TODO: verify that sorting by first name is acceptable
		assertEquals("Alan Burt Akers", testLib.get(0).getAuthor());
		assertEquals("Anthony J D Burns", testLib.get(1).getAuthor());
		assertEquals("Carol E. Meacham", testLib.get(2).getAuthor());
		assertEquals("Cheryl Jones", testLib.get(3).getAuthor());
		assertEquals("Daniel Wyatt", testLib.get(4).getAuthor());
		assertEquals("David Meade Betts", testLib.get(5).getAuthor());
		assertEquals("Dennis Radha-Rose", testLib.get(6).getAuthor());
		assertEquals("Emma Lorant", testLib.get(7).getAuthor());
		assertEquals("Esme Ellis", testLib.get(8).getAuthor());
		assertEquals("Helen K Barker", testLib.get(9).getAuthor());
		assertEquals("Jean Fanelli", testLib.get(10).getAuthor());
		assertEquals("Jen Alexander", testLib.get(11).getAuthor());
		assertEquals("Kate Clarke", testLib.get(12).getAuthor());
		assertEquals("Martyn Folkes", testLib.get(13).getAuthor());
		assertEquals("Mary Lancaster", testLib.get(14).getAuthor());
		assertEquals("Moyra Caldecott", testLib.get(15).getAuthor());
		assertEquals("Moyra Caldecott", testLib.get(16).getAuthor());
		assertEquals("Moyra Caldecott", testLib.get(17).getAuthor());
		assertEquals("Moyra Caldecott", testLib.get(18).getAuthor());
		assertEquals("Renee Angers", testLib.get(19).getAuthor());
		assertEquals("Roger Taylor", testLib.get(20).getAuthor());
		assertEquals("Roger Taylor", testLib.get(21).getAuthor());
		assertEquals("William Fitzmaurice", testLib.get(22).getAuthor());

		// add some lower case to see if that works
		lib.add(9780374292799L, "thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "david Baldacci", "Simple Genius");

		testLib = lib.getOrderedByAuthor();
		assertEquals(26, testLib.size());
		// TODO: verify that sorting by first name is acceptable
		assertEquals("Alan Burt Akers", testLib.get(0).getAuthor());
		assertEquals("Anthony J D Burns", testLib.get(1).getAuthor());
		assertEquals("Carol E. Meacham", testLib.get(2).getAuthor());
		assertEquals("Cheryl Jones", testLib.get(3).getAuthor());
		assertEquals("Daniel Wyatt", testLib.get(4).getAuthor());
		assertEquals("david Baldacci", testLib.get(5).getAuthor());
		assertEquals("David Meade Betts", testLib.get(6).getAuthor());
		assertEquals("Dennis Radha-Rose", testLib.get(7).getAuthor());
		assertEquals("Emma Lorant", testLib.get(8).getAuthor());
		assertEquals("Esme Ellis", testLib.get(9).getAuthor());
		assertEquals("Helen K Barker", testLib.get(10).getAuthor());
		assertEquals("Jean Fanelli", testLib.get(11).getAuthor());
		assertEquals("Jen Alexander", testLib.get(12).getAuthor());
		assertEquals("jon Krakauer", testLib.get(13).getAuthor());
		assertEquals("Kate Clarke", testLib.get(14).getAuthor());
		assertEquals("Martyn Folkes", testLib.get(15).getAuthor());
		assertEquals("Mary Lancaster", testLib.get(16).getAuthor());
		assertEquals("Moyra Caldecott", testLib.get(17).getAuthor());
		assertEquals("Moyra Caldecott", testLib.get(18).getAuthor());
		assertEquals("Moyra Caldecott", testLib.get(19).getAuthor());
		assertEquals("Moyra Caldecott", testLib.get(20).getAuthor());
		assertEquals("Renee Angers", testLib.get(21).getAuthor());
		assertEquals("Roger Taylor", testLib.get(22).getAuthor());
		assertEquals("Roger Taylor", testLib.get(23).getAuthor());
		assertEquals("thomas L. Friedman", testLib.get(24).getAuthor());
		assertEquals("William Fitzmaurice", testLib.get(25).getAuthor());
	}
}