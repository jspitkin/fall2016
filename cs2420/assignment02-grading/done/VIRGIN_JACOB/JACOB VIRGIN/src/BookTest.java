package assignment02;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookTest {
	static Book bookOne;
	static Book bookTwo;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		bookOne = new Book(9999999999999L, "John Doe", "The Great Book");
		bookTwo = new Book(9999999999999L, "John Doe", "The Great Book");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(bookOne.equals(bookTwo));
	}

}
