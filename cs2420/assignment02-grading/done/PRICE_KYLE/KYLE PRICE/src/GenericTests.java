package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * GenericTests - JUnit tests from both "LibraryBookGeneric" and "LibraryGeneric classes". 
 * @author Kyle Price
 * u1018878
 * 09/07/2016
 *
 */

public class GenericTests {
	LibraryBookGeneric<String> book1, book2, book3, book4, book5, book6;
	LibraryBookGeneric<PhoneNumber> book3Phone;
	GregorianCalendar today, tomorrow;
	PhoneNumber pN1;
	LibraryGeneric<String> lib1;
	LibraryGeneric<LibraryBookGeneric<PhoneNumber>> libType;
	LibraryGeneric<LibraryBookGeneric<String>> books, books2;
	ArrayList<LibraryBookGeneric<String>> lib2;
	

	@Before
	public void setUp() throws Exception {
	// LibraryGeneric<PhoneNumber>
	libType = new LibraryGeneric<LibraryBookGeneric<PhoneNumber>>();
	libType.add(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
	libType.add(9781843190042L, "Martyn Folkes", "Bath City Centre Street Map and Guide");
	libType.add(9781843192701L, "Moyra Caldecott", "The Lily and the Bull");
		
	// Books with string holders
	book1 = new LibraryBookGeneric<String>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
	book2 = new LibraryBookGeneric<String>(9781843190042L, "Martyn Folkes", "Bath City Centre Street Map and Guide");
	book3 = new LibraryBookGeneric<String>(9781843192701L, "Moyra Caldecott", "The Lily and the Bull");
	book4 = new LibraryBookGeneric<String>(9781843192954L, "Dennis Radha-Rose",	"The Anxiety Relief Program");
	book5 = new LibraryBookGeneric<String>(9781843193319L, "Alan Burt Akers", "Transit to Scorpio");
	book6 = new LibraryBookGeneric<String>(9781843190028L, "Moyra Caldecott", "Crystal Legends");
	
	// Books with PhoneNumber Holders
	book3Phone = new LibraryBookGeneric<PhoneNumber>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
	
	// PhoneNumber objects
	pN1 = new PhoneNumber("4352371508");
	
	// GregorianCalendar objects
	
	today = new GregorianCalendar(2016, 9, 7);
	tomorrow = new GregorianCalendar(2016, 9, 8);
	
	// LibraryGenerics<String> 
	lib1 = new LibraryGeneric<String>();
    lib1.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib1.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib1.add(9780446580342L, "David Baldacci", "Simple Genius");
    lib1.add(9781843192954L, "Dennis Radha-Rose", "The Anxiety Relief Program");
    lib1.add(9781843193319L, "Alan Burt Akers",	"Transit to Scorpio");
    
    // LibraryGeneric<String> 
    books = new LibraryGeneric<LibraryBookGeneric<String>>();
    books2 = new LibraryGeneric<LibraryBookGeneric<String>>();
    
    books2.add(9781843192701L, "Moyra Caldecott", "The Lily and the Bull");
    books2.add(9781843192954L, "Dennis Radha-Rose", "The Anxiety Relief Program");
    books2.add(9781843193319L, "Alan Burt Akers", "Transit to Scorpio");
    books.addAll("Mushroom_Publishing.txt");
    
    // ArrayList of LBG<String>
    lib2 = new ArrayList<LibraryBookGeneric<String>>();
	
	}
	@After
	public void tearDown() throws Exception {
	}
	
	
	// THIS SECTION WILL TEST THE CLASS "LibraryBookGeneric".
	
	
	// getHolder tests
	@Test
	public void testGetHolderBasic() {
		book1.checkOut("john", today);
		assertEquals(true, book1.getHolder().equals("john"));
	}
	@Test
	public void testGetHolderStringIsCloseButDifferent() {
		book2.checkOut("bill joel", today);
		assertEquals(true, book2.getHolder().equals("bill joel"));
	}
	@Test
	public void testGetHolderNotCheckedOut() {
		assertEquals(true, book2.getHolder() == null);
	}
	@Test
	public void testGetHolderWrongHolder() {
		book1.checkOut("john", today);
		assertEquals(false, book1.getHolder().equals("John"));
	}
	@Test
	public void testGetHolderWithPhoneNumberHolder() {
		book3Phone.checkOut(pN1, today);
		assertEquals(true, book3Phone.getHolder().equals(pN1));
	}
	
	// checkOut tests - I figure most of the other tests wouldn't work without checkOut
	// so I'm not going to test it as rigorously. 
	
	@Test
	public void testCheckOutWithString() {
		book1.checkOut("john", today);
		assertTrue(book1.getHolder() != null);
	}

	@Test
	public void testChecOutnWithPhoneNumber() {
		book3Phone.checkOut(pN1, today);
		assertTrue(book3Phone.getHolder() != null);
	}
	
	// checkIn tests - I figure most of the other tests wouldn't work without checkIn
	// so I'm not going to test it as rigorously. 
	
	@Test
	public void testCheckInWithPhoneNumber() {
		book3Phone.checkOut(pN1, today);
		assertTrue(book3Phone.getHolder() != null); // Verifies that v3p is checked out.
		book3Phone.checkIn();
		assertTrue(book3Phone.getHolder() == null); // Verifies that v3p is checked in.
	}
	public void testCheckInWithString() {
		book1.checkOut("william shakespeare", today);
		book1.checkIn();
		assertTrue(book1.getHolder() == null); // Verifies that v3p is checked in.
	}
	
	// getDueDate tests 
	
	@Test
	public void testGetDueDate() {
		book1.checkOut("heinrich himmler", today);
		assertTrue(book1.getDueDate().equals(today));
	}
	@Test
	public void testGetDueDateShouldBeFalse() {
		book1.checkOut("heinrich himmler", today);
		assertFalse(book1.getDueDate().equals(tomorrow));
	}
	@Test
	public void testGetDueDateNull() {
		assertTrue(book1.getDueDate() == null);
	}
	@Test
	public void testGetDueDateWithPhoneNumber() {
		book3Phone.checkOut(pN1, today);
		assertTrue(book3Phone.getDueDate().equals(today));
	}
	
	
	
	// THIS SECTION WILL TEST THE CLASS "LibraryGeneric".
	
	
	
	// getOrderedByAuthor tests - which also tests its Comparator. 
	
	@Test
	public void testgetOrderedByAuthor() {
		ArrayList<LibraryBookGeneric<String>> orderedByAuthor = new ArrayList<LibraryBookGeneric<String>>();
		orderedByAuthor.add(book5);
		orderedByAuthor.add(book4);
		orderedByAuthor.add(book3);
		assertEquals(orderedByAuthor, books2.getOrderedByAuthor());	
	}
	@Test
	public void testgetOrderedByAuthorOrderingMushroomFile() {
		assertTrue(books.getOrderedByAuthor().get(0).equals(book5));
		
	}
	@Test
	public void testgetOrderedByAuthorOrderingMushroomFileWithSameAuthor() {
		assertTrue(books.getOrderedByAuthor().get(18).equals(book1));
		
	}
	
	// getOverdueList tests - which also tests its Comparator.
	
	@Test
	public void testGetOverdueList() {
		lib1.checkout(9780374292799L, "bill", 9, 1, 2016);
		lib1.checkout(9780330351690L, "jane", 9, 6, 2016);
		lib1.checkout(9781843192954L, "you", 9, 4, 2016);
		assertTrue(lib1.getOverdueList(9, 4, 2016).get(1).equals(book4));
		
	}
	@Test
	public void testGetOverdueListEarlierCheckout() {
		lib1.checkout(9780374292799L, "harry", 9, 5, 2016);
		lib1.checkout(9780330351690L, "taylor", 9, 5, 2016);
		lib1.checkout(9781843192954L, "me", 9, 5, 2016);
		assertTrue(lib1.getOverdueList(9, 5, 2016).size() == 3);
		
	}
	
	// lookup tests 
	
	@Test
	public void testLookUpBasic() {
		lib1.checkout(9780374292799L, "harry", 9, 5, 2016);
		assertTrue(lib1.lookup(9780374292799L).equals("harry"));
		
	}

	
}
