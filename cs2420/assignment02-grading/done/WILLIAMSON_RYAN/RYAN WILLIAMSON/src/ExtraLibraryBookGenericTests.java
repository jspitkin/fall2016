package assignment02;
/**
 * Ryan Williamson
 * Last Updated: Sept 7, 2016
 * u0838551
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.*;

public class ExtraLibraryBookGenericTests {

	// Initializing a new library of generic type String for testing.
	LibraryGeneric<String> lib = new LibraryGeneric<String>();
	
	@Before
	public void setUp() throws Exception {

		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void isbnLookUpTestNull(){
		assertEquals(null, lib.lookup(9780374292799L));
	}
	
	@Test
	public void isbnLookUpTestHolderCheckViaIsbn(){
		lib.checkout(9780330351690L, "Ryan", 1, 3, 1995);
		assertEquals("Ryan", lib.lookup(9780330351690L));
		//Calling method again to see if issues arise.
		assertEquals("Ryan", lib.lookup(9780330351690L));
		
	}
	
	@Test
	public void holderLookUpWithOneBookCheckedOut(){
		lib.checkout(9780446580342L, "Ryan", 1, 3, 1995);
		ArrayList<LibraryBookGeneric<String>> bookHolderArray = lib.lookup("Ryan");
		assertTrue(bookHolderArray.size() == 1);
	}
	@Test
	public void holderLookUpWithTwoBooksCheckedOut(){
		lib.checkout(9780446580342L, "Ryan", 1, 3, 1995);
		lib.checkout(9780330351690L, "Ryan", 1, 3, 1995);
		ArrayList<LibraryBookGeneric<String>> bookHolderArray = lib.lookup("Ryan");
		assertTrue(bookHolderArray.size() == 2);
	}
	@Test
	public void checkInAndCheckOutTest(){
		lib.checkout(9780374292799L, "Ryan", 1, 3, 1995);
		ArrayList<LibraryBookGeneric<String>> bookHolderArray = lib.lookup("Ryan");
		assertTrue(bookHolderArray.size() == 1);
		//bookHolderArray should be empty, because checkin method will return an empty list
		//if no books are checked out to "Ryan".
		lib.checkin("Ryan");
		bookHolderArray.remove(0);
		assertEquals(bookHolderArray, lib.lookup("Ryan"));
	}
	
	@Test
	public void getOverDueList(){
		lib.checkout(9780374292799L, "Ryan", 1, 3, 1995);
		lib.checkout(9780330351690L, "Ryan", 1, 2, 1995);
		lib.checkout(9780446580342L, "Ryan", 1, 1, 1995);
	
		ArrayList<LibraryBookGeneric<String>> overDueArrayList = lib.getOverdueList(1, 4, 1995);
		assertTrue(overDueArrayList.size() == 0);
	}
	
	@Test
	public void getOverDueList1(){
		lib.checkout(9780374292799L, "Ryan", 1, 3, 1995);
		lib.checkout(9780330351690L, "Ryan", 1, 2, 1995);
		lib.checkout(9780446580342L, "Ryan", 1, 1, 1995);
		ArrayList<LibraryBookGeneric<String>> overDueArrayList = lib.getOverdueList(1, 1, 1995);
		assertTrue(overDueArrayList.size() == 2);
	}
	
	@Test
	public void getInventoryList(){
		lib.checkout(9780374292799L, "Ryan", 1, 3, 1995);
		lib.checkout(9780330351690L, "Ryan", 1, 2, 1995);
		lib.checkout(9780446580342L, "Ryan", 1, 1, 1995);
		ArrayList<LibraryBookGeneric<String>> isnbInventoryList = lib.getInventoryList();
		assertTrue(isnbInventoryList.size() == 3);
	}
	
	@Test
	public void getOrderByAuthor(){
		
		ArrayList<LibraryBookGeneric<String>> orderedByAuthorList = lib.getOrderedByAuthor();
		//Double checked accuracy via debugger.
		assertTrue(orderedByAuthorList.size() == 3);
	}
	@Test
	public void getOrderByTitle(){
		
		lib.add(8780446580342L, "David Baldacci", "Zebra");
		ArrayList<LibraryBookGeneric<String>> orderedByAuthorList = lib.getOrderedByAuthor();
		//Double checked accuracy via debugger.
		assertTrue(orderedByAuthorList.size() == 4);
	}

}
