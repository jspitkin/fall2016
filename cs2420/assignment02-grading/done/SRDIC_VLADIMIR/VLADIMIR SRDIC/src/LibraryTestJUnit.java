package assignment02;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
/**
 * Tests for the Library Class
 * 
 * @author Vladimir Srdic
 *
 */
public class LibraryTestJUnit {
	
	@Test 
	public void emptyLibraryLookup() {
		Library lib = new Library();
		assertEquals(null, lib.lookup(978037429279L));
	}
	
	@Test 
	public void emptyLibraryLookupSize() {
		Library lib = new Library();
		assertEquals(0, lib.lookup("Jane Doe").size());
	}

	@Test
	public void checkInNonexistantHolder() {
		Library lib = new Library();
		assertEquals(false, lib.checkin("Jane Doe"));
	}
	
	@Test
	public void checkInNonexistantisbn() {
		Library lib = new Library();
		assertEquals(false, lib.checkin(0000L));
	}
	
	@Test
	public void smallLibraryLookup() {
		Library lib = new Library();
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		
		assertEquals(null, lib.lookup(9780374292799L));
		
		lib.checkout(9780374292799L, "Sam", 10, 10, 10);
		
		assertEquals("Sam", lib.lookup(9780374292799L));
		ArrayList<LibraryBook> books = new ArrayList<LibraryBook>();
		books.add(new LibraryBook(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		assertEquals(books, lib.lookup("Sam"));
		
	}

	@Test
	public void smallLibraryCheckout() {
		Library lib = new Library();
		lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
		lib.add(9780446580342L, "David Baldacci", "Simple Genius");
		
		assertEquals(true, lib.checkout(9780330351690L, "Jane Doe", 1, 1, 2008));
		ArrayList<LibraryBook> books = new ArrayList<LibraryBook>();
		books.add(new LibraryBook(9780330351690L, "Jon Krakauer", "Into the Wild"));
		assertEquals(books, lib.lookup("Jane Doe"));
		
		assertEquals(true, lib.checkin(9780330351690L));
		assertEquals(false, lib.checkin("Jane Doe"));
	}	
	
	@Test
	public void textFileLibraryTest() {
		Library lib = new Library();
		lib.addAll("Mushroom_Publishing.txt");
		
		lib.checkout(9781843190004L, "Sam", 10, 10, 2010);
		lib.checkout(9781843193319L, "Sam", 10, 12, 2010);
		lib.checkout(9781843192954L, "Not Sam", 10, 15, 2010);
		
		assertEquals(false, lib.checkin(9781843190011L));
		assertEquals(true, lib.checkin(9781843190004L));
		assertEquals(true, lib.checkin("Sam"));
		assertEquals(false, lib.checkin(9781843193319L));
		
		ArrayList<LibraryBook> books = new ArrayList<LibraryBook>();
		books.add(new LibraryBook(9781843192954L, "Dennis Radha-Rose",	"The Anxiety Relief Program"));
		assertEquals(books, lib.lookup("Not Sam"));
		
	}
	
}
