package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for the Generic Library and LibraryBook
 * 
 * @author Vladimir Srdic
 *
 */
public class LibraryGenericTestJUnit {

	LibraryGeneric<String> strLib;
	LibraryGeneric<PhoneNumber> pnLib;

	@Before
	public void setUp() throws Exception {
		strLib = new LibraryGeneric<String>();
		strLib.addAll("Mushroom_Publishing.txt");

		pnLib = new LibraryGeneric<PhoneNumber>();
		pnLib.addAll("Mushroom_Publishing.txt");
	}
	
	@Test
	public void testGetFunctionsAndCheckInOutFunctionsForLibraryBookGeneric() {
		LibraryBookGeneric<String> testBook = new LibraryBookGeneric<String>(0L, "Test Author", "Test Title");
		
		assertEquals(0L, testBook.getIsbn());
		assertEquals("Test Author", testBook.getAuthor());
		assertEquals("Test Title", testBook.getTitle());
		assertEquals(null, testBook.getDueDate());
		assertEquals(null, testBook.getHolder());
		
		testBook.checkOut("Mary", new GregorianCalendar(2010, 1, 1));
		
		assertEquals("Mary", testBook.getHolder());
		assertEquals(new GregorianCalendar(2010, 1, 1), testBook.getDueDate());
		
		testBook.checkIn();
		assertEquals(null, testBook.getDueDate());
		assertEquals(null, testBook.getHolder());
	}

	@Test
	public void testCheckoutWithBooksInLibraryAndAlreadyCheckedOutBooksAndNonExistingBooks() {
		PhoneNumber john = new PhoneNumber("801.968.6948");

		assertEquals(true, pnLib.checkout(9781843190028L, john, 1, 1, 2010));
		assertEquals(true, pnLib.checkout(9781843191230L, john, 1, 1, 2010));
		assertEquals(false, pnLib.checkout(9781843191230L, john, 1, 1, 2010));
		assertEquals(false, pnLib.checkout(6981918L, john, 1, 1, 2010));
		
		assertEquals(true, strLib.checkout(9781843190004L, "Mary", 1, 1, 2010));
		assertEquals(true, strLib.checkout(9781843190073L, "Mary", 1, 1, 2010));
		assertEquals(false, strLib.checkout(9781843190073L, "Mary", 1, 1, 2010));
		assertEquals(false, strLib.checkout(94949156L, "Mary", 1, 1, 2010));
	}

	@Test
	public void testCheckInForExistingBookAndAlreadyCheckOutBookAndNonExistingBook() {
		PhoneNumber john = new PhoneNumber("801.968.6948");

		//Checkout to phonenumber
		pnLib.checkout(9781843190004L, john, 1, 1, 2010);
		pnLib.checkout(9781843190073L, john, 1, 1, 2010);

		assertEquals(true, pnLib.checkin(9781843190004L));
		assertEquals(true, pnLib.checkin(9781843190073L));
		assertEquals(false, pnLib.checkin(9781843190004L));
		assertEquals(false, pnLib.checkin(98619181815L));

		strLib.checkout(9781843190004L, "Mary", 1, 1, 2010);
		strLib.checkout(9781843190073L, "Mary", 1, 1, 2010);

		assertEquals(true, strLib.checkin(9781843190004L));
		assertEquals(true, strLib.checkin(9781843190073L));
		assertEquals(false, strLib.checkin(9781843190004L));
		assertEquals(false, strLib.checkin(98619181815L));
	}

	@Test
	public void testLookupByHolderAndISBN() {

		ArrayList<LibraryBookGeneric<PhoneNumber>> johnOut = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		johnOut.add(new LibraryBookGeneric<PhoneNumber>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		johnOut.add(new LibraryBookGeneric<PhoneNumber>(9781843190073L, "Jen Alexander", "The Coming of the Third"));

		ArrayList<LibraryBookGeneric<PhoneNumber>> maryOut = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		maryOut.add(new LibraryBookGeneric<PhoneNumber>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		maryOut.add(new LibraryBookGeneric<PhoneNumber>(9781843190073L, "Jen Alexander", "The Coming of the Third"));

		PhoneNumber john = new PhoneNumber("801.968.6948");

		pnLib.checkout(9781843190004L, john, 1, 1, 2010);
		pnLib.checkout(9781843190073L, john, 1, 1, 2010);

		strLib.checkout(9781843190004L, "Mary", 1, 1, 2010);
		strLib.checkout(9781843190073L, "Mary", 1, 1, 2010);

		assertEquals(johnOut, pnLib.lookup(john));
		assertEquals(john, pnLib.lookup(9781843190004L));
		assertEquals(maryOut, strLib.lookup("Mary"));
		assertEquals("Mary", strLib.lookup(9781843190004L));
	}
	
	@Test
	public void testGetSortedByAuthorList() {
		LibraryGeneric<String> sortThisLib = new LibraryGeneric<String>();
		sortThisLib.add(3L, "CTest Author", "Test Title1");
		sortThisLib.add(2L, "DTest Author", "Test Title2");
		sortThisLib.add(0L, "ATest Author", "Test Title3");
		
		ArrayList<LibraryBookGeneric<String>> compared = new ArrayList<LibraryBookGeneric<String>>();
		compared.add(new LibraryBookGeneric<String>(0L, "ATest Author", "Test Title3"));
		compared.add(new LibraryBookGeneric<String>(3L, "CTest Author", "Test Title1"));
		compared.add(new LibraryBookGeneric<String>(2L, "DTest Author", "Test Title2"));
		
		ArrayList<LibraryBookGeneric<String>> sorted = sortThisLib.getOrderedByAuthor();
		
		
		assertEquals(true, sorted.equals(compared));
	}
	
	@Test
	public void testGetSortedByISBN() {
		LibraryGeneric<String> sortThisLib = new LibraryGeneric<String>();
		sortThisLib.add(3L, "CTest Author", "Test Title1");
		sortThisLib.add(2L, "DTest Author", "Test Title2");
		sortThisLib.add(0L, "ATest Author", "Test Title3");
		
		ArrayList<LibraryBookGeneric<String>> compared = new ArrayList<LibraryBookGeneric<String>>();
		compared.add(new LibraryBookGeneric<String>(0L, "ATest Author", "Test Title3"));
		compared.add(new LibraryBookGeneric<String>(2L, "DTest Author", "Test Title2"));
		compared.add(new LibraryBookGeneric<String>(3L, "CTest Author", "Test Title1"));
		
		ArrayList<LibraryBookGeneric<String>> sorted = sortThisLib.getInventoryList();
		
		
		assertEquals(true, sorted.equals(compared));
	}
	
	@Test
	public void testGetOverDueBooksWithThreeOverDueBooks() {
		strLib = new LibraryGeneric<String>();
		strLib.add(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
		strLib.add(9781843190073L, "Jen Alexander", "The Coming of the Third");
		strLib.add(9781843190363L, "Emma Lorant", "Cloner");
		
		strLib.checkout(9781843190073L, "Mary", 1, 1, 2011);
		strLib.checkout(9781843190004L, "Mary", 1, 1, 2010);
		strLib.checkout(9781843190363L, "Mary", 1, 1, 2012);
		
		ArrayList<LibraryBookGeneric<String>> sorted = new ArrayList<LibraryBookGeneric<String>>();
		sorted.add(new LibraryBookGeneric<String>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		sorted.add(new LibraryBookGeneric<String>(9781843190073L, "Jen Alexander", "The Coming of the Third"));
		sorted.add(new LibraryBookGeneric<String>(9781843190363L, "Emma Lorant", "Cloner"));
		
		ArrayList<LibraryBookGeneric<String>> compared = strLib.getOverdueList(1, 1, 2015);
		
		assertEquals(true, compared.equals(sorted));
		
	}

}
