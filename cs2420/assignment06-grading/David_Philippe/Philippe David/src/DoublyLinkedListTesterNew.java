package assignment06;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Spliterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for DoublyLinkedList.
 * 
 * @author Philippe David (u0989696)
 */
public class DoublyLinkedListTesterNew {

	private DoublyLinkedList<Integer> testList;

	@Before
	public void setUp() throws Exception {

		testList = new DoublyLinkedList<Integer>();

		for (int i = 0; i < 1000; i++)
			testList.addLast(i);
	}

	@Test
	public void testDoublyLinkedList() {
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		assertTrue(list != null);
	}

	@Test
	public void testAddFirst() {
		testList.addFirst(-1);
		assertTrue(testList.getFirst().equals(new Integer(-1)));
		assertTrue(testList.get(1).equals(new Integer(0)));

	}

	@Test
	public void testAddFirstForEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addFirst(0);
		assertTrue(testList.getFirst().equals(new Integer(0)));
	}

	@Test
	public void testAddFirstForNull() {
		testList.addFirst(null);
		assertTrue(testList.getFirst() == (null));
	}

	@Test
	public void testAddFirstForNullLargeArray() {
		testList.addFirst(null);
		assertTrue(testList.getFirst() == (null));
	}

	@Test
	public void testAddFirstForTwoNulls() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addFirst(null);
		emptyList.addFirst(null);
		assertTrue(emptyList.getFirst() == null);
	}

	@Test
	public void testAddLast() {
		testList.addLast(-1);
		assertTrue(testList.getLast().equals(new Integer(-1)));
		assertTrue(testList.get(0).equals(new Integer(0)));
		assertTrue(testList.get(999).equals(new Integer(999)));

	}

	@Test
	public void testAddLastForEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addLast(0);
		assertTrue(emptyList.getFirst().equals(new Integer(0)));
		assertTrue(emptyList.getLast().equals(new Integer(0)));
		assertTrue(emptyList.get(0).equals(new Integer(0)));

	}

	@Test
	public void testAddLastForNull() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addLast(null);
		assertTrue(emptyList.getLast() == null);
	}

	@Test
	public void testAddLastForNullLargeArray() {
		testList.addLast(null);
		assertTrue(testList.getLast() == (null));
		assertTrue((int) testList.getFirst() == (0));

	}

	@Test
	public void testAddFirstForThreeNulls() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addFirst(null);
		emptyList.addFirst(null);
		emptyList.addFirst(1);
		assertTrue(emptyList.getFirst().equals(new Integer(1)));
		assertTrue(emptyList.get(1) == (null));
		assertTrue(emptyList.getLast() == (null));

	}

	@Test
	public void testAddEnd() {
		testList.add(1000, new Integer(1000));
		assertTrue(testList.get(0).equals(new Integer(0)));
		assertTrue(testList.getLast().equals(new Integer(1000)));
	}

	@Test
	public void testAddBegining() {
		testList.add(0, new Integer(1000));
		assertTrue(testList.get(0).equals(new Integer(1000)));
		assertTrue(testList.get(1).equals(new Integer(0)));
		assertTrue(testList.getLast().equals(new Integer(999)));
	}

	@Test
	public void testAddMiddle() {
		testList.add(500, new Integer(1000));
		assertTrue(testList.get(500).equals(new Integer(1000)));
		assertTrue(testList.get(0).equals(new Integer(0)));
		assertTrue(testList.getLast().equals(new Integer(999)));
	}

	@Test
	public void testAddEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.add(0, new Integer(1000));

		assertTrue(emptyList.getFirst().equals(new Integer(1000)));
		assertTrue(emptyList.getLast().equals(new Integer(1000)));
	}

	@Test
	public void testAddNull() {
		testList.add(1000, null);

		assertTrue(testList.getFirst().equals(new Integer(0)));
		assertTrue(testList.get(999).equals(new Integer(999)));

		assertTrue(testList.getLast() == null);
		testList.add(1001, 1);
		assertTrue(testList.get(1001).equals(new Integer(1)));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddSizeOutOfBounds() {
		testList.add(999919, null);

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddNegitiveIndex() {
		testList.add(-10000, null);
	}

	@Test
	public void testGetFirst() {
		assertTrue(testList.getFirst().equals(new Integer(0)));
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetFirstEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.getFirst();
	}

	@Test
	public void testGetLast() {
		assertTrue(testList.getLast().equals(new Integer(999)));
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetLastEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.getLast();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.get(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetSizeOutOfBounds() {
		testList.get(999919);

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetNegitiveIndex() {
		testList.get(-10000);
	}

	@Test
	public void testGet() {
		assertFalse(testList.get(1).equals(new Integer(500)));
		assertTrue(testList.get(500).equals(new Integer(500)));
	}

	@Test
	public void testRemoveFirst() {
		testList.removeFirst();
		assertTrue(testList.getFirst().equals(new Integer(1)));
	}

	@Test
	public void testRemoveFirstTwice() {
		testList.removeFirst();
		testList.removeFirst();
		assertTrue(testList.getFirst().equals(new Integer(2)));
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirstEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		testList.removeLast();
		assertTrue(testList.getLast().equals(new Integer(998)));
	}

	@Test
	public void testRemoveLastTwice() {
		testList.removeLast();
		testList.removeLast();
		assertTrue(testList.getLast().equals(new Integer(997)));
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveLastEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.removeLast();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.get(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemovetSizeOutOfBounds() {
		testList.get(999919);

	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveNegitiveIndex() {
		testList.get(-10000);
	}

	@Test
	public void testRemoveFirstItem() {
		testList.remove(0);
		assertTrue(testList.getFirst().equals(new Integer(1)));
	}

	@Test
	public void testRemoveLastItem() {
		testList.remove(999);
		assertTrue(testList.getLast().equals(new Integer(998)));
	}

	@Test
	public void testRemoveMiddleItem() {
		testList.remove(500);
		assertTrue(testList.get(500).equals(new Integer(501)));
	}

	@Test
	public void testIndexOf() {
		assertTrue(testList.indexOf(999) == 999);
	}

	@Test
	public void testIndexOfNotIncluded() {
		assertTrue(testList.indexOf(91283921) == -1);
	}

	@Test
	public void testLastIndexOfNotIncluded() {
		assertTrue(testList.lastIndexOf(91283921) == -1);
	}

	@Test
	public void testLastIndexOf() {
		testList.add(700, 500);
		assertTrue(testList.lastIndexOf(500) == 700);
	}

	@Test
	public void testLastIndexOfTwo() {
		testList.add(30, 500);
		assertTrue(testList.lastIndexOf(500) == 501);
	}

	@Test
	public void testSize() {
		assertTrue(testList.size() == 1000);
	}

	@Test
	public void testSizeEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		assertTrue(emptyList.size() == 0);
	}

	@Test
	public void testIsEmptyYes() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		assertTrue(emptyList.isEmpty());
	}

	@Test
	public void testIsEmptyNo() {
		assertFalse(testList.isEmpty());
	}

	@Test
	public void testClearEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.clear();
		assertTrue(emptyList.isEmpty());
	}

	@Test
	public void testClear() {
		testList.clear();
		assertTrue(testList.isEmpty());
	}

	@Test
	public void testToArrayEmpty() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		Object[] oList = emptyList.toArray();
		assertTrue(oList.length == 0);
	}

	@Test
	public void testToArray() {
		Object[] oList = testList.toArray();
		assertTrue(oList.length == 1000);
		assertTrue(oList[0].equals(new Integer(0)));
		assertTrue(oList[999].equals(new Integer(999)));
	}

	@Test
	public void testIteratorWhileLoopAndOrdering() {
		// This also checks the ordering
		final int SIZE = 1000;
		ArrayList<Integer> testSet = new ArrayList<Integer>();
		for (int i = 0; i < SIZE; i++) {
			testSet.add(i);
		}

		int count = 0;
		Iterator<Integer> myIterator = testList.iterator();
		while (myIterator.hasNext()) {
			Assert.assertEquals(myIterator.next(), testList.get(count));
			count++;
		}
	}

	@Test
	public void testIteratorEnhancedForLoop() {
		// This also checks the ordering
		final int SIZE = (int) Math.pow(10, 3);
		ArrayList<Integer> testSet = new ArrayList<Integer>();
		for (int i = 0; i < SIZE; i++) {
			testSet.add(i);
		}

		int count = 0;
		for (Integer element : testList) {
			Assert.assertEquals(element, testSet.get(count));
			count++;
		}

	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorNextWithEmptySet() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();

		Iterator<Integer> myIterator = emptyList.iterator();
		myIterator.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorNextOne() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();

		emptyList.addFirst(1);
		Iterator<Integer> myIterator = emptyList.iterator();
		Assert.assertEquals(myIterator.next(), new Integer(1));
		myIterator.next();
	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorNextTwo() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();

		emptyList.addFirst(2);
		emptyList.addFirst(1);
		Iterator<Integer> myIterator = emptyList.iterator();
		Assert.assertEquals(myIterator.next(), new Integer(1));
		Assert.assertEquals(myIterator.next(), new Integer(2));
		myIterator.next();
	}

	@Test(expected = IllegalStateException.class)
	public void testIteratorRemoveWithEmptySet() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		Iterator<Integer> myIterator = emptyList.iterator();
		myIterator.remove();
	}

	@Test
	public void testIteratorRemoveOneSizeOne() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addFirst(1);
		Iterator<Integer> myIterator = emptyList.iterator();
		Assert.assertEquals(myIterator.next(), new Integer(1));
		myIterator.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void testIteratorRemoveTwoSizeOne() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addFirst(1);
		Iterator<Integer> myIterator = emptyList.iterator();
		Assert.assertEquals(myIterator.next(), new Integer(1));
		myIterator.remove();
		myIterator.remove();
	}

	@Test
	public void testIteratorRemoveTwoSizeTwo() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addFirst(2);
		emptyList.addFirst(1);
		Iterator<Integer> myIterator = emptyList.iterator();
		Assert.assertEquals(myIterator.next(), new Integer(1));
		myIterator.remove();
		Assert.assertEquals(myIterator.next(), new Integer(2));
		myIterator.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void testIteratorRemoveThreeSizeTwo() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addFirst(2);
		emptyList.addFirst(1);
		Iterator<Integer> myIterator = emptyList.iterator();
		Assert.assertEquals(myIterator.next(), new Integer(1));
		myIterator.remove();
		Assert.assertEquals(myIterator.next(), new Integer(2));
		myIterator.remove();
		myIterator.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void testIteratorRemoveThreeSizeThreeDuplicateRemove() {
		DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<Integer>();
		emptyList.addFirst(3);

		emptyList.addFirst(2);
		emptyList.addFirst(1);
		Iterator<Integer> myIterator = emptyList.iterator();
		Assert.assertEquals(myIterator.next(), new Integer(1));
		myIterator.remove();
		Assert.assertEquals(myIterator.next(), new Integer(2));
		myIterator.remove();
		myIterator.remove();
	}

	@Test
	public void testIteratorRemove() {
		// This also checks the ordering
		final int SIZE = (int) Math.pow(10, 3);
		ArrayList<Integer> testSet = new ArrayList<Integer>();
		for (int i = 0; i < SIZE; i++) {
			testSet.add(i);
		}

		int count = 0;
		Iterator<Integer> myIterator = testList.iterator();
		while (myIterator.hasNext()) {

			Assert.assertEquals(myIterator.next(), testSet.get(count));
			myIterator.remove();
			count++;
		}
		Assert.assertEquals(testList.isEmpty(), true);
	}

	@Test
	public void testIterableForEach() {
		final int SIZE = (int) Math.pow(10, 3);
		ArrayList<Integer> testSet = new ArrayList<Integer>();
		for (int i = 0; i < SIZE; i++) {
			testSet.add(i);
		}

		ArrayList<Integer> newSet = new ArrayList<Integer>();
		testList.forEach(element -> newSet.add(element));

		Assert.assertEquals(testSet, newSet);
	}

	@Test
	public void testForEachRemaining() {
		final int SIZE = (int) Math.pow(10, 3);
		ArrayList<Integer> testSet = new ArrayList<Integer>();
		for (int i = 0; i < SIZE; i++) {
			testSet.add(i);
		}

		ArrayList<Integer> newSet = new ArrayList<Integer>();
		Iterator<Integer> myIterator = testList.iterator();
		myIterator.forEachRemaining(element -> newSet.add(element));

		Assert.assertEquals(testSet, newSet);
	}

	@Test
	public void testSpliterator() {
		// It exists, but it's largely unimplemented
		final int SIZE = (int) Math.pow(10, 3);
		ArrayList<Integer> testSet = new ArrayList<Integer>();
		for (int i = 0; i < SIZE; i++) {
			testSet.add(i);
		}

		Spliterator<Integer> mySpliterator = testList.spliterator();

		Assert.assertEquals(mySpliterator.characteristics(), 0);
	}

}
