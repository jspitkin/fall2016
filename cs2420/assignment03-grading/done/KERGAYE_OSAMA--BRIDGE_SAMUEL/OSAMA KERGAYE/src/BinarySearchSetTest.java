/**
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * The Junit testing suite for the BinarySearchSet
 * 
 * 
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
public class BinarySearchSetTest {

	BinarySearchSet<Integer> smallBinarySearchSet, emptyBinarySearchSet, bigBinarySearchSet;
	ArrayList<Integer> smallArrayList, oneToOneHundred;
	Iterator<Integer> smallBinarySearchSetIntegerIterator;

	@Before
	public void setUp() throws Exception {

		// Small ArrayList
		smallArrayList = new ArrayList<Integer>();
		smallArrayList.add(1);
		smallArrayList.add(14);
		smallArrayList.add(12);

		// Big IntArray
		oneToOneHundred = new ArrayList<Integer>();
		for (int count = 0; count <= 100; count++) {
			oneToOneHundred.add(count);
		}

		// empty Set
		emptyBinarySearchSet = new BinarySearchSet<>();

		// Small SearchSet Integers
		smallBinarySearchSet = new BinarySearchSet<>();
		smallBinarySearchSet.add(5);
		smallBinarySearchSet.add(10);
		smallBinarySearchSet.add(15);
		smallBinarySearchSet.add(20);
		smallBinarySearchSet.add(25);

		// SmallBinarySearchSetIterator
		smallBinarySearchSetIntegerIterator = smallBinarySearchSet.iterator();

		// Big SearchSet Integers
		bigBinarySearchSet = new BinarySearchSet<>();
		for (int count = 1; count <= 100; count++) {
			bigBinarySearchSet.add(count);
		}

	}

	@Test
	public void testComparatorReturnNull() {

		BinarySearchSet<String> noComparator = new BinarySearchSet<>();
		assertNull(noComparator.comparator());

	}

	@Test
	public void testComparatorReturnComparator() {
		Comparator<String> testComparator = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return 0;
			}

		};
		BinarySearchSet<String> hasComparator = new BinarySearchSet<>(testComparator);

		assertTrue(hasComparator.comparator() == testComparator);

	}

	@Test(expected = NoSuchElementException.class)
	public void testFirstForThrowExection() {

		emptyBinarySearchSet.first();

	}

	@Test
	public void testFirstForFirstElement() {

		assertEquals((Integer) 5, smallBinarySearchSet.first());
	}

	@Test(expected = NoSuchElementException.class)
	public void testLastForThrowException() {

		emptyBinarySearchSet.last();

	}

	@Test
	public void testLastForLastElement() {

		assertEquals((Integer) 25, smallBinarySearchSet.last());

	}

	@Test
	public void testAddForTrue() {

		smallBinarySearchSet.add(1);

		assertEquals(true, smallBinarySearchSet.contains(1));

		smallBinarySearchSet.add(2);
		assertEquals(true, smallBinarySearchSet.contains(2));

		smallBinarySearchSet.add(4);
		smallBinarySearchSet.add(5);
		assertFalse(smallBinarySearchSet.add(5));
		smallBinarySearchSet.add(6);
		smallBinarySearchSet.add(7);
		smallBinarySearchSet.add(8);

		assertEquals(true, smallBinarySearchSet.contains(8));

	}

	@Test
	public void testAddForFalseFromAddingNull() {

		assertFalse(smallBinarySearchSet.add(null));

	}

	@Test
	public void testAddForFalseFromAddingDupe() {

		assertEquals(false, smallBinarySearchSet.add(5));

	}

	@Test
	public void testAddAllForTrue() {

		smallBinarySearchSet.addAll(smallArrayList);

		assertEquals(true, smallBinarySearchSet.contains(12));
		assertEquals(true, smallBinarySearchSet.contains(14));

	}

	@Test
	public void testAddAllForFalse() {

		ArrayList<Integer> allreadyInSet = new ArrayList<>();
		allreadyInSet.add(10);
		allreadyInSet.add(15);
		allreadyInSet.add(20);

		assertFalse(smallBinarySearchSet.addAll(allreadyInSet));

	}

	@Test
	public void testAddAllForFalseWhenElementNull() {

		ArrayList<Integer> allreadyInSet = new ArrayList<>();
		allreadyInSet.add(null);

		assertFalse(smallBinarySearchSet.addAll(allreadyInSet));

	}

	@Test
	public void testClear() {

		smallBinarySearchSet.clear();
		assertFalse(smallBinarySearchSet.contains(5));

	}

	@Test
	public void testContainsForTrue() {

		assertTrue(smallBinarySearchSet.contains(10));

	}

	@Test
	public void testContainsForFalse() {

		assertFalse(smallBinarySearchSet.contains(99));

	}

	@Test
	public void testContainsAllForTrue() {

		ArrayList<Integer> testForContainsAll = new ArrayList<>();
		testForContainsAll.add(10);
		testForContainsAll.add(15);
		testForContainsAll.add(20);

		assertTrue(smallBinarySearchSet.containsAll(testForContainsAll));

	}

	@Test
	public void testContainsAllForFalse() {
		ArrayList<Integer> testForContainsAll = new ArrayList<>();
		testForContainsAll.add(10);
		testForContainsAll.add(16);
		testForContainsAll.add(20);

		assertFalse(smallBinarySearchSet.containsAll(testForContainsAll));

	}

	@Test
	public void testIsEmptyForTrue() {

		assertTrue(emptyBinarySearchSet.isEmpty());

	}

	@Test
	public void testIsEmptyForFalse() {

		assertFalse(smallBinarySearchSet.isEmpty());

	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorNext() {

		assertEquals((Integer) 5, smallBinarySearchSetIntegerIterator.next());
		assertEquals((Integer) 10, smallBinarySearchSetIntegerIterator.next());
		assertEquals((Integer) 15, smallBinarySearchSetIntegerIterator.next());
		assertEquals((Integer) 20, smallBinarySearchSetIntegerIterator.next());
		assertEquals((Integer) 25, smallBinarySearchSetIntegerIterator.next());

		// Tests for NoSuchElementException and meme authenticity
		assertEquals((Integer) 42, smallBinarySearchSetIntegerIterator.next());

	}

	@Test
	public void testIteratorHasNext() {

		for (int count = 1; count <= 4; count++) {
			assertTrue(smallBinarySearchSetIntegerIterator.hasNext());
			smallBinarySearchSetIntegerIterator.next();
		}
		assertFalse(smallBinarySearchSetIntegerIterator.hasNext());

	}

	@Test
	public void testIteratorRemove() {

		assertEquals((Integer) 5, smallBinarySearchSetIntegerIterator.next());
		smallBinarySearchSetIntegerIterator.remove();
		assertEquals((Integer) 10, smallBinarySearchSet.first());
		assertEquals((Integer) 15, smallBinarySearchSetIntegerIterator.next());

	}

	@Test
	public void testRemoveForTrue() {

		smallBinarySearchSet.remove(20);
		assertFalse(smallBinarySearchSet.contains(20));

	}

	@Test
	public void testRemoveForFalse() {

		assertFalse(smallBinarySearchSet.remove(99));

	}

	@Test
	public void testRemoveAllForTrue() {

		ArrayList<Integer> testForContainsAll = new ArrayList<>();
		testForContainsAll.add(10);
		testForContainsAll.add(15);
		testForContainsAll.add(20);

		assertTrue(smallBinarySearchSet.removeAll(testForContainsAll));

	}

	@Test
	public void testRemoveAllForFalse() {

		ArrayList<Integer> testForContainsAll = new ArrayList<>();

		testForContainsAll.add(101);
		testForContainsAll.add(15);
		testForContainsAll.add(20);

		assertFalse(smallBinarySearchSet.removeAll(testForContainsAll));

	}

	@Test
	public void testSize() {

		assertEquals(5, smallBinarySearchSet.size());

	}

	@Test
	public void testObjectToArray() {

		int[] smallBinaryInArray = new int[] { 5, 10, 15, 20, 25 };

		for (int count = 0; count <= 4; count++) {
			assertTrue(smallBinarySearchSet.toArray()[count].equals(smallBinaryInArray[count]));
		}

	}

	@Test
	public void testGrow() {
		assertTrue(bigBinarySearchSet.first() == 1);

		assertTrue(bigBinarySearchSet.last() == 100);
		assertTrue(bigBinarySearchSet.size() == 100);

		bigBinarySearchSet.add(101);

		assertTrue(bigBinarySearchSet.last() == 101);
		assertTrue(bigBinarySearchSet.size() == 101);

	}

}
