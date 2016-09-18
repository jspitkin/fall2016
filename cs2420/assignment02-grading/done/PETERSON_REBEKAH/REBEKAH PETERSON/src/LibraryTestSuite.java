package assignment02;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test suite for testing..
 * 
 * @author Rebekah Peterson, last updated 9/7/16
 *
 */
public class LibraryTestSuite {
	
	Book sampleBook;

	@Before
	public void setUp() throws Exception {
		sampleBook = new Book(987654321, "Doe", "Once There Was a Daffodil");
	}

	@Test
	public void equalsWithDifferentObjectTest() {
		Integer i = new Integer(10);
		assertFalse(sampleBook.equals(i));
	}
	
	@Test
	public void notEqualsTest() {
		Book testBook = new Book(987654321, "Once There Was a Daffodil", "Doe");
		assertFalse(testBook.equals(sampleBook));
	}
	
	@Test
	public void equalsTest() {
		Book testBook = new Book(987654321, "Doe", "Once There Was a Daffodil");
		assertTrue(testBook.equals(sampleBook));
		assertTrue(sampleBook.equals(testBook));
	}

}
