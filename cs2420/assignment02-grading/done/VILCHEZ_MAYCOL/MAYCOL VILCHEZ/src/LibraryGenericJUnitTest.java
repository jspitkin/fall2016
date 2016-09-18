package assignment02;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import assignment02.PhoneNumber;

/**
 * Maycol Vilchez 
 * u0832923
 */
public class LibraryGenericJUnitTest {
	private LibraryGeneric<Object> libraryGeneric;
	private LibraryGeneric<Object> libraryGenericList;

	@Before
	public void setUpLibrary() throws Exception {
		libraryGeneric = new LibraryGeneric<>();
		libraryGeneric.addAll("Mushroom_Publishing.txt");

		libraryGenericList = new LibraryGeneric<>();
		libraryGenericList.add(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound");
		libraryGenericList.add(9781843190677L, "Cheryl Jones", "Herbs for Healthy Skin");
		libraryGenericList.add(9781843190011L, "Moyra Caldecott", "The Eye of Callanish");
		libraryGenericList.add(9781843190110L, "David Meade Betts", "Breaking the Gaze");
		libraryGenericList.add(9781843190875L, "Renee Angers", "Ice and a Curious Man");
		libraryGenericList.add(9781843190073L, "Jen Alexander", "The Coming of the Third");

	}

	@Test
	public void checkOutReturnName() {
		libraryGeneric.checkout(9781843190004L, "Maycol Vilchez", 01, 26, 2016);
		String name = (String) libraryGeneric.lookup(9781843190004L);
		assertEquals("Maycol Vilchez", name);
	}

	@Test
	public void checkOutReturnBookListAndThenCheckInOneOfTheBooks() {
		libraryGeneric.checkout(9781843190004L, "Anderson Porta", 01, 26, 2016);
		libraryGeneric.checkout(9781843190011L, "Anderson Porta", 01, 26, 2016);
		libraryGeneric.checkout(9781843190028L, "Maycol Vilchez", 01, 26, 2016);
		libraryGeneric.checkout(9781843190042L, "Anderson Porta", 01, 26, 2016);
		ArrayList<LibraryBookGeneric<Object>> actual = new ArrayList<>();
		actual = libraryGeneric.lookup("Anderson Porta");
		ArrayList<LibraryBook> expected = new ArrayList<>();
		expected.add(new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		expected.add(new LibraryBook(9781843190011L, "Moyra Caldecott", "The Eye of Callanish"));
		expected.add(new LibraryBook(9781843190042L, "Martyn Folkes", "Bath City Centre Street Map and Guide"));
		assertEquals(expected, actual);
		libraryGeneric.checkin(9781843190011L);
		assertEquals(null, libraryGeneric.lookup(9781843190011L));
	}

	@Test
	public void testLookupByIsbn() {
		libraryGeneric.checkout(9781843190028L, "Maycol Vilchez", 01, 26, 2016);
		String actual = (String) libraryGeneric.lookup(9781843190028L);
		String expected = "Maycol Vilchez";
		assertEquals(expected, actual);
	}

	@Test
	public void addBookToLibrary() {
		libraryGeneric.add(8781843190004L, "Anderson Porta", "Computer Science is Fun");
		libraryGeneric.checkout(8781843190004L, "Maycol Vilchez", 02, 02, 2016);
		assertEquals("Maycol Vilchez", libraryGeneric.lookup(8781843190004L));
	}

	@Test
	public void lookupBookThatIsntInTheLibrary() {
		assertEquals(null, libraryGeneric.lookup(8781843190004L));
	}

	@Test
	public void lookupHolderWithNoBooksCheckedOut() {
		assertEquals(new ArrayList<LibraryBook>(), libraryGeneric.lookup("Anderson Porta"));
	}

	@Test
	public void tryToCheckOutBookThatIsAlreadyCheckedOut() {
		libraryGeneric.checkout(9781843190028L, "Maycol Vilchez", 01, 26, 2016);
		assertEquals(false, libraryGeneric.checkout(9781843190028L, "Anderson Porta", 01, 26, 2016));
	}

	@Test
	public void successfulCheckout() {
		assertEquals(true, libraryGeneric.checkout(9781843190028L, "Anderson Porta", 01, 26, 2016));
	}

	@Test
	public void succesfullCheckinOfAllBooksHeldByOnePerson() {
		libraryGeneric.checkout(9781843190004L, "Maycol Vilchez", 01, 26, 2016);
		libraryGeneric.checkout(9781843190011L, "Maycol Vilchez", 01, 26, 2016);
		libraryGeneric.checkout(9781843190042L, "Maycol Vilchez", 01, 26, 2016);
		libraryGeneric.checkin("Maycol Vilchez");
		assertEquals(new ArrayList<LibraryBook>(), libraryGeneric.lookup("Maycol Vilchez"));
	}

	@Test
	public void successfulCheckInOfOneBook() {
		libraryGeneric.checkout(9781843190004L, "Maycol Vilchez", 01, 26, 2016);
		libraryGeneric.checkout(9781843190011L, "Maycol Vilchez", 01, 26, 2016);
		libraryGeneric.checkin(9781843190011L);
		ArrayList<LibraryBook> expected = new ArrayList<>();
		expected.add(new LibraryBook(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		assertEquals(expected, libraryGeneric.lookup("Maycol Vilchez"));
		assertEquals(null, libraryGeneric.lookup(9781843190011L));
	}

	@Test
	public void sortingByAuthorName() {
		ArrayList<LibraryBookGeneric<Object>> expected = new ArrayList<>();
		expected.add(new LibraryBookGeneric<Object>(9781843190677L, "Cheryl Jones", "Herbs for Healthy Skin"));
		expected.add(new LibraryBookGeneric<Object>(9781843190110L, "David Meade Betts", "Breaking the Gaze"));
		expected.add(new LibraryBookGeneric<Object>(9781843190073L, "Jen Alexander", "The Coming of the Third"));
		expected.add(new LibraryBookGeneric<Object>(9781843190011L, "Moyra Caldecott", "The Eye of Callanish"));
		expected.add(new LibraryBookGeneric<Object>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		expected.add(new LibraryBookGeneric<Object>(9781843190875L, "Renee Angers", "Ice and a Curious Man"));
		assertEquals(expected, libraryGenericList.getOrderedByAuthor());
	}

	@Test
	public void sortingByIsbn() {
		ArrayList<LibraryBookGeneric<Object>> expected = new ArrayList<>();
		expected.add(new LibraryBookGeneric<Object>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		expected.add(new LibraryBookGeneric<Object>(9781843190011L, "Moyra Caldecott", "The Eye of Callanish"));
		expected.add(new LibraryBookGeneric<Object>(9781843190073L, "Jen Alexander", "The Coming of the Third"));
		expected.add(new LibraryBookGeneric<Object>(9781843190110L, "David Meade Betts", "Breaking the Gaze"));
		expected.add(new LibraryBookGeneric<Object>(9781843190677L, "Cheryl Jones", "Herbs for Healthy Skin"));
		expected.add(new LibraryBookGeneric<Object>(9781843190875L, "Renee Angers", "Ice and a Curious Man"));
		assertEquals(expected, libraryGenericList.getInventoryList());
	}

	@Test
	public void sortingByDate() {
		libraryGenericList.checkout(9781843190004L, "Maycol", 01, 27, 2016);
		libraryGenericList.checkout(9781843190011L, "Miguel", 03, 27, 2016);
		libraryGenericList.checkout(9781843190073L, "Nate", 05, 27, 2016);
		libraryGenericList.checkout(9781843190110L, "Luis", 01, 15, 2016);
		libraryGenericList.checkout(9781843190677L, "Carlos", 03, 27, 2015);
		libraryGenericList.checkout(9781843190875L, "Fred", 12, 27, 2015);

		ArrayList<LibraryBookGeneric<Object>> expected = new ArrayList<>();
		expected.add(new LibraryBookGeneric<Object>(9781843190677L, "Cheryl Jones", "Herbs for Healthy Skin"));
		expected.add(new LibraryBookGeneric<Object>(9781843190875L, "Renee Angers", "Ice and a Curious Man"));
		expected.add(new LibraryBookGeneric<Object>(9781843190110L, "David Meade Betts", "Breaking the Gaze"));
		expected.add(new LibraryBookGeneric<Object>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		expected.add(new LibraryBookGeneric<Object>(9781843190011L, "Moyra Caldecott", "The Eye of Callanish"));
		expected.add(new LibraryBookGeneric<Object>(9781843190073L, "Jen Alexander", "The Coming of the Third"));
		assertEquals(expected, libraryGenericList.getOverdueList(5, 27, 2016));
	}

	@Test
	public void checkinoutWithPhoneNumber() {
		PhoneNumber number = new PhoneNumber("8017596700");
		libraryGeneric.checkout(9781843190004L, number, 01, 28, 2016);
		Object name = libraryGeneric.lookup(9781843190004L);
		assertEquals(number, name);
	}

	@Test
	public void lookupByHolderPhoneNumber() {
		PhoneNumber number = new PhoneNumber("8017596700");
		libraryGeneric.checkout(9781843190004L, number, 01, 27, 2016);
		libraryGeneric.checkout(9781843190011L, number, 01, 27, 2016);

		ArrayList<LibraryBookGeneric<Object>> expected = new ArrayList<>();
		expected.add(new LibraryBookGeneric<Object>(9781843190004L, "Moyra Caldecott", "Weapons of the Wolfhound"));
		expected.add(new LibraryBookGeneric<Object>(9781843190011L, "Moyra Caldecott", "The Eye of Callanish"));
		assertEquals(expected, libraryGeneric.lookup(number));
	}

	@Test
	public void lookupByIsbnToGetHoldersPhoneNumber() {
		PhoneNumber number = new PhoneNumber("8017596700");
		libraryGeneric.checkout(9781843190004L, number, 01, 27, 2016);
		libraryGeneric.checkout(9781843190011L, number, 01, 27, 2016);
		assertEquals(number, libraryGeneric.lookup(9781843190004L));
		assertEquals(number, libraryGeneric.lookup(9781843190011L));
	}

	@Test
	public void checkinABookThatWasCheckedOutByPhoneNumber() {
		PhoneNumber number = new PhoneNumber("8017596700");
		libraryGeneric.checkout(9781843190004L, number, 01, 27, 2016);
		libraryGeneric.checkout(9781843190011L, number, 01, 27, 2016);
		libraryGeneric.checkin(number);
		assertEquals(null, libraryGeneric.lookup(9781843190004L));
	}

}
