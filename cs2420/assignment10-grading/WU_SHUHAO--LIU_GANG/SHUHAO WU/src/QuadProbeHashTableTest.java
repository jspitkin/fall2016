package assignment10;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class QuadProbeHashTableTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	MediocreHashFunctor test = new MediocreHashFunctor();
	QuadProbeHashTable tester = new QuadProbeHashTable(3, test);

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_add() {
		tester.add("a");
		tester.add("ab");
		tester.add("abc");
		assertEquals(tester.table[1], "a");
		assertEquals(tester.table[2], "ab");
		assertEquals(tester.table[3], "abc");
		// rehash
		tester.add("abcd");
		assertEquals(tester.table[4], "abcd");
	}

	@Test
	public void test_addAll() {
		tester.add("a");
		tester.add("ab");
		tester.add("abc");
		ArrayList<String> testArray = new ArrayList<String>();
		testArray.add("a");
		assertFalse(tester.addAll(testArray));
		testArray.add("ab");
		testArray.add("abc");
		testArray.add("abcde");
		assertTrue(tester.addAll(testArray));
	}

	@Test
	public void test_clear() {
		tester.add("a");
		tester.clear();
		assertNull(tester.table[1]);
		assertEquals(tester.size(), 0);
	}

	@Test
	public void test_contains() {
		tester.add("a");
		tester.add("ab");
		tester.add("abc");
		assertTrue(tester.contains("ab"));
		assertFalse(tester.contains("apple"));
	}

	@Test
	public void test_containsAll() {
		tester.add("a");
		tester.add("ab");
		tester.add("abc");
		ArrayList<String> testArray = new ArrayList<String>();
		testArray.add("a");
		testArray.add("ab");
		testArray.add("abc");
		assertTrue(tester.containsAll(testArray));
		testArray.add("abcde");
		assertFalse(tester.containsAll(testArray));
	}

	@Test
	public void test_isEmpty() {
		assertTrue(tester.isEmpty());
		tester.add("a");
		assertFalse(tester.isEmpty());
	}

	@Test
	public void test_size() {
		assertEquals(tester.size(), 0);
		tester.add("first");
		assertEquals(tester.size(), 1);
		tester.add("second");
		assertEquals(tester.size(), 2);
	}

}
