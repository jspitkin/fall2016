package assignment02;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * BookTester - JUnit tests for the class "Book".
 * @author Kyle Price
 * u1018878
 * 09/07/2016
 */
public class BookTester {
	Book testBook, testBookSame, testBookDifferent, testBookNumDifferent;

	@Before
	public void setUp() throws Exception {
		testBook = new Book(123456, "jk rowling", "harry potter");
		testBookSame = new Book(123456, "jk rowling", "harry potter");
		testBookDifferent = new Book(123456, "jk rowling", "Harry potter");
		testBookNumDifferent = new Book(023456, "jk rowling", "harry potter");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsBasic() {
		assertTrue(testBook.equals(testBookSame));
	}

	@Test
	public void testEqualsDifferentButWrongCaps() {
		assertFalse(testBook.equals(testBookDifferent));
	}

	@Test
	public void testEqualsDifferentButWrongNumber() {
		assertFalse(testBook.equals(testBookNumDifferent));
	}

}
