package assignment02;
/**
 * JUnit test codes
 * 
 * @author Jiwon Nam
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LibraryJUnitTest 
{
	Library lib = new Library();
	LibraryGeneric<Double> libGen = new LibraryGeneric<Double>();
	ArrayList<LibraryGeneric<Double>> orderedByAuthorResult = new ArrayList<LibraryGeneric<Double>>();
	ArrayList<LibraryGeneric<Double>> orderedByDueDateResult = new ArrayList<LibraryGeneric<Double>>();
	Book bookTest;
	LibraryBook libraryBookTest;
	LibraryBookGeneric<Integer> libraryBookGenericTest;
	@Before
	public void setUp() throws Exception 
	{
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		lib.add(9780374292780L, "Sam Smith", "Lay me Down");
		lib.add(9780374592791L, "Sam Smith", "Stay With Me");
		lib.add(9780374292796L, "Sam Smith", "I'm Not the Only One");
		lib.add(9780374292775L, "Ketty Perry", "Firework");
		lib.add(9780374292780L, "Avril Lavigne", "Let Me Go");
		lib.add(9780374302780L, "Bruno Mars", "Just the Way You Are");
		
		libGen.add(9780374292799L, "Thomas L. Friedman", "The World is Flat"); //9 (author)
		libGen.add(9780330351690L, "Jon Krakauer", "Into the Wild"); //4 (author)
		libGen.add(9780446580342L, "David Baldacci", "Simple Genius"); //3 (author)
		libGen.add(9780374292788L, "Sam Smith", "Lay me Down"); //6 (author)
		libGen.add(9780374592791L, "Sam Smith", "Stay With Me"); // 8 (author)
		libGen.add(9780374292796L, "Sam Smith", "I'm Not the Only One"); //7 (author)
		libGen.add(9780374292775L, "Ketty Perry", "Firework"); //5 (author)
		libGen.add(9780374292780L, "Avril Lavigne", "Let Me Go"); //1 (author)
		libGen.add(9780374302780L, "Bruno Mars", "Just the Way You Are"); //2 (author)
		
		
		bookTest = new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		libraryBookTest = new LibraryBook(9780374292780L, "Sam Smith", "Lay me Down");
		libraryBookGenericTest = new LibraryBookGeneric<Integer>(9780374292775L, "Ketty Perry", "Firework");
	}

	@After
	public void setDown() throws Exception
	{
		lib = new Library();
	}
	/**
	 * book class tests
	 */
	@Test
	public void bookClassTest() 
	{
		// get isbn test
		assertEquals(9780374292799L, bookTest.getIsbn());
		// get author test
		assertEquals("Thomas L. Friedman", bookTest.getAuthor());
		// get title test
		assertEquals("The World is Flat", bookTest.getTitle());
		// equal test
		Book equalTestBook = new Book(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		Book nonEqualTestBook = new Book(9780330351690L, "Jon Krakauer", "Into the Wild");
		assertTrue(bookTest.equals(equalTestBook));
		// non equal test
		assertTrue(!(bookTest.equals(nonEqualTestBook)));
	}
	/**
	 * libraryBook class tests
	 */
	@Test
	public void libraryBookTest()
	{
		// get isbn test
		assertEquals(9780374292780L, libraryBookTest.getIsbn());
		// get author test
		assertEquals("Sam Smith", libraryBookTest.getAuthor());
		// get title test
		assertEquals("Lay me Down", libraryBookTest.getTitle());		
		// check out test
		libraryBookTest.setCheckOut("Jiwon Nam", 03, 12, 1990);
			// get holder test
			assertEquals("Jiwon Nam", libraryBookTest.getHolder());
			// get due date test
			assertEquals(new GregorianCalendar(1990, 03, 12), libraryBookTest.getDueDate());
			// get check out status test
			assertTrue(!libraryBookTest.getCheckIn());
		// checkin test
		libraryBookTest.setCheckIn();
			// holder null test
			assertEquals(null, libraryBookTest.getHolder());
			// due date null test
			assertEquals(null, libraryBookTest.getDueDate());
			// get check in status test
			assertTrue(libraryBookTest.getCheckIn());
	}
	/**
	 * library class tests
	 */
	@Test
	public void libraryTest()
	{	
		// lookup with isbn test
		// nobody check out
		assertEquals(null, lib.lookup(9780374292775L));
		// check out with Jiwon Nam
		assertTrue(lib.checkout(9780374292775L, "Jiwon Nam", 02, 02, 1990));
		// lookup with isbn test
		assertEquals("Jiwon Nam", lib.lookup(9780374292775L));
		// Jiwon Check out 2 more books
		assertTrue(lib.checkout(9780374292799L, "Jiwon Nam", 03, 15, 1993));
		assertTrue(lib.checkout(9780374302780L, "Jiwon Nam", 07, 21, 2000));
		// lookup with holder test
		ArrayList<LibraryBook> result = lib.lookup("Jiwon Nam");
		assertEquals(new LibraryBook(9780374292799L, "Thomas L. Friedman", "The World is Flat"), result.get(0));
		assertEquals(new LibraryBook(9780374292775L, "Ketty Perry", "Firework"), result.get(1));
		assertEquals(new LibraryBook(9780374302780L, "Bruno Mars", "Just the Way You Are"), result.get(2));		
		// lookup with wrong holder test
		result = lib.lookup("Bird Show");
		assertTrue(result.isEmpty());
		// checkin with isbn test
		assertTrue(lib.checkin(9780374292775L));
		// check the book returned by watching holder and due date
		assertEquals(null, lib.lookup(9780374292775L));
		
		// checkin with holder test
		assertTrue(lib.checkin("Jiwon Nam"));
		// check books checked out by Jiwon Nam, expect null
		result = lib.lookup("Jiwon Nam");
		assertTrue(result.isEmpty());
	}
	/**
	 * libraryBookGeneric class tests
	 */
	@Test
	public void libraryBookGenericTest()
	{
		// get isbn test
		assertEquals(9780374292775L,libraryBookGenericTest.getIsbn());
		// get author test
		assertEquals("Ketty Perry", libraryBookGenericTest.getAuthor());
		// get title test
		assertEquals("Firework", libraryBookGenericTest.getTitle());	
		// check out test by Jiwon Nam
		libraryBookGenericTest.setCheckOut(801800442, 02, 02, 1990);
		// get check out status test
		assertTrue(!libraryBookGenericTest.getCheckIn());
		// get holder test
		assertTrue(libraryBookGenericTest.getHolder().equals(801800442));
		// get due date test
		assertEquals(new GregorianCalendar(1990, 02, 02), libraryBookGenericTest.getDueDate());
		// check in test
		libraryBookGenericTest.setCheckIn();
		// get check in status test
		assertTrue(libraryBookGenericTest.getCheckIn());
		// check holder and due date
		assertEquals(null, libraryBookGenericTest.getHolder());
		assertEquals(null, libraryBookGenericTest.getDueDate());
	}
	/**
	 * libraryGeneric class tests
	 */
	@Test
	public void libraryGenericTest()
	{
		// lookup with isbn test
		// nobody check out
		assertEquals(null, libGen.lookup(9780374292775L));
		// check out with Jiwon Nam
		assertTrue(libGen.checkout(9780374292775L, 8013321342d, 02, 02, 1990));
		// lookup with isbn test
		assertTrue(libGen.lookup(9780374292775L).equals(8013321342d));
		// Jiwon Check out 2 more books
		assertTrue(libGen.checkout(9780374292799L, 8013321342d, 03, 15, 1993));
		assertTrue(libGen.checkout(9780374302780L, 8013321342d, 07, 21, 2000));
		// lookup with holder test
		ArrayList<LibraryBookGeneric<Double>> result = libGen.lookup(8013321342d);
		assertEquals(new LibraryBookGeneric<Double>(9780374292799L, "Thomas L. Friedman", "The World is Flat"), result.get(0));
		assertEquals(new LibraryBookGeneric<Double>(9780374292775L, "Ketty Perry", "Firework"), result.get(1));
		assertEquals(new LibraryBookGeneric<Double>(9780374302780L, "Bruno Mars", "Just the Way You Are"), result.get(2));		
		// lookup with wrong holder test
		result = libGen.lookup(8013321343d);
		assertTrue(result.isEmpty());
		// checkin with isbn test
		assertTrue(libGen.checkin(9780374292775L));
		// check the book returned by watching holder and due date
		assertEquals(null, libGen.lookup(9780374292775L));				
		// checkin with holder test
		assertTrue(libGen.checkin(8013321342d));
		// check books checked out by Jiwon Nam, expect null
		result = libGen.lookup(8013321342d);
		assertTrue(result.isEmpty());		
		// orderByAuthor test
		ArrayList<LibraryBookGeneric<Double>> orderedByAuthorTest = libGen.getOrderedByAuthor();
		
		assertEquals(new LibraryBookGeneric<Double>(9780374292780L, "Avril Lavigne", "Let Me Go"), orderedByAuthorTest.get(0));
		assertEquals(new LibraryBookGeneric<Double>(9780374302780L, "Bruno Mars", "Just the Way You Are"), orderedByAuthorTest.get(1));
		assertEquals(new LibraryBookGeneric<Double>(9780446580342L, "David Baldacci", "Simple Genius"), orderedByAuthorTest.get(2));
		assertEquals(new LibraryBookGeneric<Double>(9780330351690L, "Jon Krakauer", "Into the Wild"), orderedByAuthorTest.get(3));
		assertEquals(new LibraryBookGeneric<Double>(9780374292775L, "Ketty Perry", "Firework"), orderedByAuthorTest.get(4));
		assertEquals(new LibraryBookGeneric<Double>(9780374292788L, "Sam Smith", "Lay me Down"), orderedByAuthorTest.get(5));
		assertEquals(new LibraryBookGeneric<Double>(9780374292796L, "Sam Smith", "I'm Not the Only One"), orderedByAuthorTest.get(6));
		assertEquals(new LibraryBookGeneric<Double>(9780374592791L, "Sam Smith", "Stay With Me"), orderedByAuthorTest.get(7));
		assertEquals(new LibraryBookGeneric<Double>(9780374292799L, "Thomas L. Friedman", "The World is Flat"), orderedByAuthorTest.get(8));
		
		// print type
		for(int i = 0; i < orderedByAuthorTest.size(); i++)
		{
			System.out.println(orderedByAuthorTest.get(i).toString());
		}
		
		System.out.println("-------------------------------------------");
		// getOrderByOverDue test
		// set every libraryBookGeneric has been checked out
		libGen.checkout(9780374292780L, 2234242334d, 8, 11, 2013); //2
		libGen.checkout(9780374302780L, 1234567789d, 9, 27, 2014); //3
		libGen.checkout(9780446580342L, 2423456333d, 7, 5, 2011); //1
		libGen.checkout(9780330351690L, 2802489342d, 12, 11, 2016); //9
		libGen.checkout(9780374292775L, 3490534534d, 5, 4, 2016); //7
		libGen.checkout(9780374292788L, 4444444444d, 8, 1, 2016); //8
		libGen.checkout(9780374292796L, 2324244423d, 3, 2, 2015); //5
		libGen.checkout(9780374592791L, 8889009090d, 4, 27, 2016); //6
		libGen.checkout(9780374292799L, 8934893333d, 1, 1, 2015); //4
		
		
		ArrayList<LibraryBookGeneric<Double>> overDueListTest = libGen.getOverdueList(4, 26, 2016);
		assertEquals(new LibraryBookGeneric<Double>(9780446580342L, "David Baldacci", "Simple Genius"), overDueListTest.get(0));
		assertEquals(new LibraryBookGeneric<Double>(9780374292780L, "Avril Lavigne", "Let Me Go"), overDueListTest.get(1));
		assertEquals(new LibraryBookGeneric<Double>(9780374302780L, "Bruno Mars", "Just the Way You Are"), overDueListTest.get(2));
		assertEquals(new LibraryBookGeneric<Double>(9780374292799L, "Thomas L. Friedman", "The World is Flat"), overDueListTest.get(3));
		assertEquals(new LibraryBookGeneric<Double>(9780374292796L, "Sam Smith", "I'm Not the Only One"), overDueListTest.get(4));
		
		// print type
		for(int i = 0; i < overDueListTest.size(); i++)
		{
			System.out.println(overDueListTest.get(i).toString());
		}
		
	}
	

}
