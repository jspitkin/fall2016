package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ryan Cantera (u0855101) and Dax Wilson (u0264580)
 *	This is the testing suite for BinarySearchSet
 */
public class BinarySearchSetJUnitTest {

	BinarySearchSet<Integer> intList;
	BinarySearchSet<String> stringList;
	BinarySearchSet<Integer> intListWithComparator;
	BinarySearchSet<String> stringListWithComparator;
	List<Integer> intArrayList;
	List<String> stringArrayList;
	reverseIntegerOrder comparatorInt = new reverseIntegerOrder();
	reverseAlphabetOrder comparatorString = new reverseAlphabetOrder();

	@Before
	public void setUp() throws Exception {
		intList = new BinarySearchSet<Integer>();
		intListWithComparator = new BinarySearchSet<Integer>(comparatorInt);
		stringList = new BinarySearchSet<String>();
		stringListWithComparator = new BinarySearchSet<String>(comparatorString);
		intArrayList = new ArrayList<Integer>();
		stringArrayList = new ArrayList<String>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void IntegerTests() {
		assertTrue(intList.isEmpty());
		intList.add(9);
		intList.add(12);
		intList.add(10);
		assertTrue(intList.contains(12));
		assertTrue(intList.contains(9));
		assertTrue(intList.contains(10));
		assertFalse(intList.contains(5));
		assertTrue(intList.last().equals(12));
		assertFalse(intList.last().equals(9));
		assertTrue(intList.first().equals(9));
		assertFalse(intList.first().equals(12));
		intList.remove(12);
		assertTrue(intList.size() == 2);
		assertTrue(intList.last() == 10);
		intArrayList.add(9);
		intArrayList.add(10);
		intList.removeAll(intArrayList);
		assertTrue(intList.isEmpty());
		intArrayList.clear();
		for (int i = 0; i < 10; i++) {
			intArrayList.add(i);
		}

		intList.addAll(intArrayList);
		assertEquals(10, intList.size());

		intList.removeAll(intArrayList);
		assertTrue(intList.isEmpty());

		intList.clear();
		assertTrue(intList.isEmpty());

		assertEquals(false, intList.iterator().hasNext());
		intList.add(3);
		intList.add(4);
		assertTrue(intList.iterator().next() == 4);
		intList.iterator().remove();
		assertTrue(intList.size() == 1);
	}

	@Test
	public void StringTests() {
		stringList.add("Apple");
		stringList.add("Banana");
		stringList.add("Cherry");
		stringList.add("Doughnut");
		stringList.add("Egg");
		assertTrue(stringList.size() == 5);
		assertTrue(stringList.first().equals("Apple"));
		assertTrue(stringList.last().equals("Egg"));
		stringList.remove("Apple");
		stringList.remove("Egg");
		assertFalse(stringList.contains("Apple"));
		assertFalse(stringList.contains("Egg"));
		assertTrue(stringList.size() == 3);
		assertTrue(stringList.last().equals("Doughnut"));
		assertTrue(stringList.first().equals("Banana"));
		stringArrayList.add("Banana");
		stringArrayList.add("Cherry");
		stringArrayList.add("Doughnut");
		stringList.removeAll(stringArrayList);
		assertTrue(stringList.isEmpty());
	}

	@Test
	public void testWithComparator() {
		intListWithComparator.add(1);
		intListWithComparator.add(2);
		intListWithComparator.add(3);
		assertTrue(intListWithComparator.first() == 3);
		intListWithComparator.clear();
		assertTrue(intListWithComparator.size() == 0);
		stringListWithComparator.add("Apple");
		stringListWithComparator.add("Banana");
		stringListWithComparator.add("Cherry");
		assertTrue(stringListWithComparator.first().equals("Cherry"));
		stringListWithComparator.clear();
		assertTrue(stringListWithComparator.size() == 0);
		
	}

	protected class reverseAlphabetOrder implements Comparator<String> {

		/**
		 * Comparator who's ordering is Z-A
		 */

		@Override
		public int compare(String o1, String o2) {
			return o2.compareTo(o1);
		}
	}

	protected class reverseIntegerOrder implements Comparator<Integer> {

		/**
		 * Comparator who's ordering is high-low
		 */

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}

}
