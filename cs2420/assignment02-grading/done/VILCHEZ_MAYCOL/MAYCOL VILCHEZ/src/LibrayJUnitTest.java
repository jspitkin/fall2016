package assignment02;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Maycol Vilchez 
 * u0832923
 */

public class LibrayJUnitTest {
	private Library library;

	@Before
	public void setUpLibrary() throws Exception {
		library = new Library();
		library.addAll("Mushroom_Publishing.txt");
	}

	@Test
	public void checkOutReturnName() {
		library.checkout(9781843190004L, "Maycol Vilchez", 01, 26, 2016);
		String name = library.lookup(9781843190004L);
		assertEquals("Maycol Vilchez", name);
	}

	@Test
	public void checkOutReturnBookListAndThenCheckInOneOfTheBooks() {
		library.checkout(9781843190004L, "Erick Vilchez", 01, 26, 2016);
		library.checkout(9781843190011L, "Erick Vilchez", 01, 26, 2016);
		library.checkout(9781843190028L, "Maycol Vilchez", 01, 26, 2016);
		library.checkout(9781843190042L, "Erick Vilchez", 01, 26, 2016);
		ArrayList<LibraryBook> actual = new ArrayList<>();
		actual = library.lookup("Erick Vilchez");
		ArrayList<LibraryBook> expected = new ArrayList<>();
		expected.add(new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		expected.add(new LibraryBook(9781843190011L, "Moyra Caldecott", "The Eye of Callanish"));
		expected.add(new LibraryBook(9781843190042L, "Martyn Folkes", "Bath City Centre Street Map and Guide"));
		assertEquals(expected, actual);
		library.checkin(9781843190011L);
		assertEquals(null, library.lookup(9781843190011L));
	}

	@Test
	public void testLookupByIsbn() {
		library.checkout(9781843190028L, "Maycol Vilchez", 01, 26, 2016);
		String actual = library.lookup(9781843190028L);
		String expected = "Maycol Vilchez";
		assertEquals(expected, actual);
	}

	@Test
	public void addBookToLibrary() {
		library.add(8781843190004L, "Erick Vilchez", "Computer Science is Fun");
		library.checkout(8781843190004L, "Maycol Vilchez", 02, 02, 2016);
		assertEquals("Maycol Vilchez", library.lookup(8781843190004L));
	}

	@Test
	public void lookupBookThatIsntInTheLibrary() {
		assertEquals(null, library.lookup(8781843190004L));
	}

	@Test
	public void lookupHolderWithNoBooksCheckedOut() {
		assertEquals(new ArrayList<LibraryBook>(), library.lookup("Erick Vilchez"));
	}

	@Test
	public void tryToCheckOutBookThatIsAlreadyCheckedOut() {
		library.checkout(9781843190028L, "Maycol Vilchez", 01, 26, 2016);
		assertEquals(false, library.checkout(9781843190028L, "Erick Vilchez", 01, 26, 2016));
	}

	@Test
	public void successfulCheckout() {
		assertEquals(true, library.checkout(9781843190028L, "Erick Vilchez", 01, 26, 2016));
	}

	@Test
	public void succesfullCheckinOfAllBooksHeldByOnePerson() {
		library.checkout(9781843190004L, "Maycol Vilchez", 01, 26, 2016);
		library.checkout(9781843190011L, "Maycol Vilchez", 01, 26, 2016);
		library.checkout(9781843190042L, "Maycol Vilchez", 01, 26, 2016);
		library.checkin("Maycol Vilchez");
		assertEquals(new ArrayList<LibraryBook>(), library.lookup("Maycol Vilchez"));
	}

	@Test
	public void successfulCheckInOfOneBook() {
		library.checkout(9781843190004L, "Erick Vilchez", 01, 26, 2016);
		library.checkout(9781843190011L, "Erick Vilchez", 01, 26, 2016);
		library.checkin(9781843190011L);
		ArrayList<LibraryBook> expected = new ArrayList<>();
		expected.add(new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		assertEquals(expected, library.lookup("Erick Vilchez"));
		assertEquals(null, library.lookup(9781843190011L));
	}
}
