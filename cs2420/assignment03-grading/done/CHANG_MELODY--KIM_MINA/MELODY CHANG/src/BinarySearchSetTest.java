package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Ching-Yuan Chang u0914005, Min Kim u1054673
 */
public class BinarySearchSetTest {

	BinarySearchSet<String> list = new BinarySearchSet<String>();
	BinarySearchSet<Integer> listInt = new BinarySearchSet<Integer>();
	BinarySearchSet<String> listString = new BinarySearchSet<String>(); // secondary
																		// string
																		// list
	BinarySearchSet<Integer> listIntg = new BinarySearchSet<Integer>(); // secondary
																		// integer
																		// list

	@Before
	public void setup() {
		for (int increment = 0; increment < 10; increment++) {
			listIntg.add(increment);
			listString.add("string" + increment);
		}
	}

	@Test
	public void addStringWithNoDuplicates() {
		assertEquals(true, list.add("true"));
		assertEquals(true, list.add("true1"));
		assertEquals(2, list.size());
	}

	@Test
	public void addintegerWithNoDuplicates() {
		assertEquals(true, listInt.add(1));
		assertEquals(true, listInt.add(2));
		assertEquals(2, listInt.size());
	}

	@Test
	public void addStringWithDuplicates() {
		assertEquals(true, list.add("true"));
		assertEquals(false, list.add("true"));
		assertEquals(1, list.size());
	}

	@Test
	public void addIntegerWithDuplicates() {
		assertEquals(true, listInt.add(1));
		assertEquals(false, listInt.add(1));
		assertEquals(1, listInt.size());
	}

	@Test
	public void addStringWithMultipleDuplicates() {
		assertEquals(true, list.add("true"));
		assertEquals(false, list.add("true"));
		assertEquals(true, list.add("true1"));
		assertEquals(false, list.add("true1"));
		assertEquals(true, list.add("true2"));
		assertEquals(false, list.add("true2"));
		assertEquals(3, list.size());
	}

	@Test
	public void addIntegerWithMultipleDuplicates() {
		assertEquals(true, listInt.add(1));
		assertEquals(false, listInt.add(1));
		assertEquals(true, listInt.add(2));
		assertEquals(false, listInt.add(2));
		assertEquals(true, listInt.add(3));
		assertEquals(false, listInt.add(3));
		assertEquals(3, listInt.size());
	}

	@Test
	public void addNullElementToEmptyArray() {
		assertEquals(false, list.add(null));
		assertEquals(0, list.size());
	}

	@Test
	public void addWithNullElementToNonEmptyStringArray() {
		assertEquals(false, list.add(null));
		assertEquals(true, list.add("true2"));
		assertEquals(false, list.add(null));
		assertEquals(1, list.size());
	}

	@Test
	public void addWithNullElementToNonEmptyIntegerArray() {
		assertEquals(false, listInt.add(null));
		assertEquals(true, listInt.add(1));
		assertEquals(false, listInt.add(null));
		assertEquals(1, listInt.size());
	}

	@Test
	public void addMoreThenTenStringsToDoubleTheSizeOfArray() {
		assertEquals(true, list.add("true"));
		assertEquals(true, list.add("true1"));
		assertEquals(true, list.add("true2"));
		assertEquals(true, list.add("true3"));
		assertEquals(true, list.add("true4"));
		assertEquals(true, list.add("true5"));
		assertEquals(true, list.add("true6"));
		assertEquals(true, list.add("true7"));
		assertEquals(true, list.add("true8"));
		assertEquals(true, list.add("true9"));
		assertEquals(true, list.add("true10"));
		assertEquals(true, list.add("true11"));
		assertEquals(true, list.add("true12"));
		assertEquals(13, list.size());
	}

	@Test
	public void addMoreThenTenIntergersToDoubleTheSizeOfArray() {
		assertEquals(true, listInt.add(1));
		assertEquals(true, listInt.add(2));
		assertEquals(true, listInt.add(3));
		assertEquals(true, listInt.add(4));
		assertEquals(true, listInt.add(5));
		assertEquals(true, listInt.add(6));
		assertEquals(true, listInt.add(7));
		assertEquals(true, listInt.add(8));
		assertEquals(true, listInt.add(9));
		assertEquals(true, listInt.add(10));
		assertEquals(true, listInt.add(11));
		assertEquals(true, listInt.add(12));
		assertEquals(true, listInt.add(13));
		assertEquals(true, listInt.add(14));
		assertEquals(14, listInt.size());
	}

	@Test
	public void getFirstWithStrings() {
		assertEquals(true, list.add("true"));
		assertEquals(true, list.add("true1"));
		assertEquals("true", list.first());
	}

	@Test
	public void gerFirstWithIntergers() {
		assertEquals(true, listInt.add(1));
		assertEquals(true, listInt.add(2));
		assertEquals(1, listInt.first().intValue());
	}

	@Test(expected = NoSuchElementException.class)
	public void firstException() {
		list.first();
	}

	@Test
	public void addAllWithStringAndNoDuplicates() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("h");
		test.add("k");
		test.add("hg");
		test.add("g");

		assertTrue(list.addAll(test));
		assertEquals(true, list.contains("i"));
		assertEquals(4, list.size());
	}

	@Test
	public void addAllWithIntergersAndNoDuplicates() {
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);

		assertTrue(listInt.addAll(test));
		assertEquals(true, listInt.contains(3));
		assertEquals(4, listInt.size());
	}

	@Test
	public void addAllWithStringAndNoDuplicatesFalse() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("h");
		test.add("k");
		test.add("l");
		test.add("f");

		assertTrue(list.addAll(test));
		assertEquals(true, list.contains("3"));
		assertEquals(4, list.size());
		assertFalse(list.addAll(test));
	}

	@Test
	public void addAllWithIntergersAndNoDuplicatesFalse() {
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);

		assertTrue(listInt.addAll(test));
		assertEquals(true, listInt.contains(3));
		assertEquals(4, listInt.size());
		assertFalse(listInt.addAll(test));
	}

	@Test
	public void addAllWithStringAndDuplicates() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("h");
		test.add("j");
		test.add("h");
		test.add("f");

		assertTrue(list.addAll(test));
		assertEquals(false, list.contains("v"));
		assertEquals(3, list.size());
	}

	@Test
	public void addAllWithIntergersAndDuplicates() {
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(2);
		test.add(2);
		test.add(4);

		assertTrue(listInt.addAll(test));
		assertEquals(false, listInt.contains(3));
		assertEquals(3, listInt.size());
	}

	@Test
	public void addAllWithStringWithNullElements() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add(null);
		test.add("4");

		assertTrue(list.addAll(test));
		assertEquals(false, list.contains("3"));
		assertEquals(3, list.size());
	}

	@Test
	public void addAllWithIntergersWithNullElements() {
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(null);
		test.add(3);
		test.add(4);

		assertTrue(listInt.addAll(test));
		assertEquals(true, listInt.contains(3));
		assertEquals(3, listInt.size());
	}

	@Test
	public void containsWithStrings() {
		list.add("true1");
		list.add("true2");
		list.add("true3");

		assertEquals(true, list.contains("true2"));
	}

	@Test
	public void containsWithIntegers() {
		listInt.add(1);
		listInt.add(2);
		listInt.add(3);
		assertEquals(true, listInt.contains(3));
	}

	@Test
	public void containsFalse() {

		assertEquals(false, list.contains("true4"));

		list.add("true1");
		list.add("true2");
		list.add("true3");

		assertEquals(false, list.contains("true4"));
	}

	@Test
	public void containsWithIntegersFalse() {

		assertEquals(false, listInt.contains(5));

		listInt.add(1);
		listInt.add(2);
		listInt.add(3);
		assertEquals(false, listInt.contains(5));
	}

	@Test
	public void containsFalseWithNullElement() {

		assertEquals(false, list.contains(null));
	}

	@Test
	public void containsWithIntegersFalseNullElement() {

		assertEquals(false, listInt.contains(null));
	}

	@Test
	public void containsAll() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("k");
		test.add("i");
		test.add("h");
		test.add("o");
		list.addAll(test);

		assertTrue(list.containsAll(test));
	}

	@Test
	public void containsAllFalse() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("k");
		test.add("i");
		test.add("h");
		test.add("o");
		list.addAll(test);
		list.remove("o");

		assertEquals(false, list.containsAll(test));
	}

	@Test
	public void isEmptyTrue() {
		list.add("1");
		list.add("2");
		list.add("3");
		list.clear();

		assertEquals(0, list.size());
		assertEquals(true, list.isEmpty());
	}

	@Test
	public void isEmptyFalse() {
		list.add("1");
		list.add("2");
		list.add("3");

		assertEquals(false, list.isEmpty());
	}

	@Test
	public void RemoveTrue() {
		list.add("1");
		list.add("2");
		list.add("3");
		assertTrue(list.remove("2"));
		assertFalse(list.contains("2"));
		assertEquals(2, list.size());
	}

	@Test
	public void RemoveFalse() {
		list.add("1");
		list.add("2");
		list.add("3");
		assertFalse(list.remove("5"));
		assertEquals(3, list.size());
	}

	@Test
	public void RemoveFalseWithEmptyArray() {
		assertFalse(list.remove("5"));
	}

	@Test
	public void RemoveFalseWithNullElements() {
		list.add("1");
		list.add("2");
		list.add("3");
		assertFalse(list.remove(null));
		assertEquals(3, list.size());
	}

	@Test
	public void removeAllTrue() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");
		list.addAll(test);

		assertTrue(list.removeAll(test));
		assertEquals(0, list.size());
		
	}

	@Test
	public void removeAllTrueWithOneDuplicate() {

		ArrayList<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");

		ArrayList<String> test1 = new ArrayList<String>();
		test1.add("1");
		test1.add("2");
		test1.add("2");
		test1.add("4");

		list.addAll(test);

		assertTrue(list.removeAll(test1));
		assertEquals(1, list.size());
	}

	@Test
	public void removeAllFalse() {

		ArrayList<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		test.add("4");

		ArrayList<String> test1 = new ArrayList<String>();
		test1.add("5");
		test1.add("6");
		test1.add("7");
		test1.add("8");

		list.addAll(test);

		assertFalse(list.removeAll(test1));
	}

	@Test
	public void Size() {
		ArrayList<String> test = new ArrayList<String>();
		test.add("1");
		test.add("2");
		test.add("3");
		list.addAll(test);

		assertEquals(3, list.size());
		
		list.remove("2");
		assertEquals(2, list.size());

	}

	@Test
	public void clearValidCase() {
		listString.clear();
		listIntg.clear();
		assertTrue(listString.isEmpty());
		assertTrue(listInt.isEmpty());
	}

	@Test
	public void clearAlreadyEmpty() {
		listString.clear();
		assertTrue(listString.isEmpty());
		listIntg.clear();
		assertTrue(listInt.isEmpty());
	}

	@Test(expected = NoSuchElementException.class)
	public void lastExceptionNoSuchIntElement() {
		listInt.last();
	}

	@Test(expected = NoSuchElementException.class)
	public void lastExceptionNoSuchStrElement() {
		list.last();
	}

	@Test
	public void testBinarySearch() {
		int location = listIntg.BinarySearch(4);
		assertSame(4, location);
		location = listString.BinarySearch("string6");
		assertSame(6, location);
		assertNotSame(20, location);
	}

	@Test(expected = NoSuchElementException.class)
	public void iteratorNextNoSuchElementExceptionStr() {
		BinarySearchSet<String> listStringNew = new BinarySearchSet<String>();

		for (int iter = 0; iter < 10; iter++) {
			listStringNew.iterator().next();
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void iteratorNextNoSuchElementExceptionInt() {
		BinarySearchSet<Integer> listIntNew = new BinarySearchSet<Integer>();

		for (int iter = 0; iter < 10; iter++) {
			listIntNew.iterator().next();
		}
	}

	@Test
	public void iteratorValidCase() {
		for (int iter = 0; iter < 9; iter++) {
			assertTrue(listString.iterator().hasNext());
			assertTrue(listIntg.iterator().hasNext());
			listIntg.iterator().next();

			listIntg.iterator().remove();
		}
		assertFalse(listIntg.iterator().hasNext());

	}
}
