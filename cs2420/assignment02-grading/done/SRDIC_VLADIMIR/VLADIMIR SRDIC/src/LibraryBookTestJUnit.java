package assignment02;

import static org.junit.Assert.*;
import java.util.GregorianCalendar;

import org.junit.Test;

/**
 * Tests for the LibraryBook class
 * 
 * @author Vladimir Srdic
 *
 */
public class LibraryBookTestJUnit {

	@Test
	public void checkOut() {
		LibraryBook b = new LibraryBook(0, "", "");
		b.checkOut("Vlad", new GregorianCalendar(1, 1, 1));
		
		assertEquals("Me", b.getHolder());
		assertEquals(new GregorianCalendar(1, 1, 1), b.getDueDate());
	}
	
	@Test
	public void checkIn() {
		LibraryBook b = new LibraryBook(0, "", "");
		b.checkOut("Vlad", new GregorianCalendar(1, 1, 1));
		b.checkIn();

		assertEquals(null, b.getHolder());
		assertEquals(null, b.getDueDate());
	}
}
