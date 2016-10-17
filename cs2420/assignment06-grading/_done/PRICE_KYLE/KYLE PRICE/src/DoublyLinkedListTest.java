package assignment06;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * DoublyLinkedListTest - runs 61 rigorous JUnit tests for the class DoublyLinkedList. 
 * @author Kyle Price
 * 10/05/2016
 *
 */
public class DoublyLinkedListTest {
	
	DoublyLinkedList<Integer> list, hasItems;
	DoublyLinkedList<String> stringList;
	Iterator<Integer> iter;
	Iterator<String> iterString;
	
	@Before
	public void setUp() throws Exception {
		list = new DoublyLinkedList<Integer>();
		hasItems = new DoublyLinkedList<Integer>();
		hasItems.addFirst(0);
		hasItems.addFirst(2);
		hasItems.addFirst(4);
		hasItems.addFirst(6);
		stringList = new DoublyLinkedList<String>();
		stringList.add(0, "lions");
		stringList.add(0, "tigers");
		stringList.add(1, "bears");
		stringList.add(2, "mewtwo");
		stringList.add(3, "trump");
		iter = hasItems.iterator();
		iterString = stringList.iterator();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//addFirst tests
	
	@Test
	public void testAddFirstEmpty() {
		list.addFirst(8);
		assertTrue(list.getFirst().equals(8));
	}
	@Test
	public void testAddFirstBasic() {
		list.addFirst(8);
		list.addFirst(10);
		assertTrue(list.getFirst().equals(10));
	}
	@Test
	public void testAddFirstNull() {
		list.addFirst(10);
		list.addFirst(null);
		assertTrue(list.getFirst() == null);
	}
	@Test
	public void testAddFirstDuplicates() {
		list.addFirst(10);
		list.addFirst(8);
		list.addFirst(null); // tail still points to 8 as prev
		list.addFirst(10);
		assertTrue(list.getFirst().equals(10));
	}
	
	// addLast tests
	
	@Test
	public void testAddLastBasic() {
		list.addLast(null);
		list.addLast(10);
		assertTrue(list.getLast().equals(10));
	}
	@Test
	public void testAddLastNull() {
		list.addLast(5);
		list.addLast(null);
		assertTrue(list.getLast() == null);
	}
	@Test
	public void testAddLastSeveral() {
		list.addLast(null);
		list.addLast(10);
		list.addLast(8);
		list.addLast(4);
		list.addLast(0);
		list.addLast(1);
		assertTrue(list.getLast().equals(1));
		assertTrue(list.getFirst() == null);
	}
	@Test
	public void testAddLastDuplicates() {
		list.addLast(5);
		list.addLast(null);
		list.addLast(5);
		list.addLast(null);
		assertTrue(list.getLast() == null);
	}
	
	// get tests 
	
	@Test
	public void testGetBasic() {
		assertTrue(hasItems.get(2).equals(2));
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetTooLarge() {
		assertTrue(hasItems.get(100).equals(2));
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetTooSmall() {
		assertTrue(hasItems.get(-1).equals(2));
	}
	@Test
	public void testGet0() {
		assertTrue(hasItems.get(0).equals(6));
	}
	@Test
	public void testGetNull() {
		hasItems.addFirst(null);
		hasItems.addFirst(null);
		hasItems.addFirst(null);
		assertTrue(hasItems.get(2) == null);
	}
	@Test
	public void testGetLoop() {
		for (int i = 100; i > 0; i--) {
			list.addFirst(i);
		}
		assertTrue(list.get(49).equals(50));
	}
	
	
	// add tests
	
	@Test
	public void testAddBasic() {
		hasItems.add(1, 100);
		assertTrue(hasItems.get(1).equals(100));
		assertTrue(hasItems.get(2).equals(4));
		assertTrue(hasItems.get(0).equals(6));
	}
	@Test
	public void testAddNull() {
		hasItems.add(1, null);
		assertTrue(hasItems.get(1) == null);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddTooLarge() {
		hasItems.add(5, null);
		assertTrue(hasItems.get(1) == null);
	}
	@Test
	public void testAddAtTheEdgeEnd() {
		hasItems.add(3, 3);
		assertTrue(hasItems.get(3).equals(3));
		assertTrue(hasItems.get(4).equals(0));
	}
	@Test
	public void testAddAtEnd() {
		hasItems.clear();
		hasItems.addFirst(3);
		hasItems.addFirst(3);
		hasItems.add(2, 3);
		assertTrue(hasItems.size() == 3);
	}
	@Test
	public void testAddAtTheEdgeBeginning() {
		hasItems.add(0, 3);
		assertEquals(new Integer(3), hasItems.get(0));
		assertEquals(new Integer(6), hasItems.get(1));
	}
	
	// removeFirst tests 
	
	@Test
	public void testRemoveFirstBasic() {
		hasItems.removeFirst();
		assertEquals(new Integer(4), hasItems.get(0));
	}
	@Test
	public void testRemoveFirstNull() {
		hasItems.addFirst(null);
		hasItems.removeFirst();
		assertEquals(new Integer(6), hasItems.get(0));
	}
	@Test (expected = NoSuchElementException.class)
	public void testRemoveEmpty() {
		list.removeFirst();
		assertEquals(new Integer(6), hasItems.get(0));
	}
	@Test
	public void testRemoveFirstSingle() {
		list.addFirst(10);
		list.removeFirst();
		assertEquals(0, list.size());
	}
	
	// removeLast tests 
	
	@Test
	public void testRemoveLastBasic() {
		hasItems.removeLast();
		assertEquals(new Integer(2), hasItems.getLast());
		assertEquals(3, hasItems.size());
	}
	@Test (expected = NoSuchElementException.class)
	public void testRemoveLastEmpty() {
		list.removeLast();
	}
	@Test
	public void testRemoveLastSingleItem() {
		list.addFirst(0);
		list.removeLast();
		assertEquals(0, list.size());
	}
	
	// remove tests
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveTooLarge() {
		hasItems.remove(5);
	}
	@Test
	public void testRemoveBasic() {
		hasItems.remove(2);
		assertEquals(new Integer(0), hasItems.getLast());
		assertEquals(3, hasItems.size());
	}
	@Test
	public void testRemoveEdgeFirst() {
		hasItems.remove(0);
		assertEquals(new Integer(4), hasItems.getFirst());
		assertEquals(3, hasItems.size());
	}
	@Test
	public void testRemoveEdgeLast() {
		hasItems.remove(3);
		assertEquals(new Integer(2), hasItems.getLast());
		assertEquals(3, hasItems.size());
	}
	@Test
	public void testIndexOfBasic() {
		assertEquals(2, hasItems.indexOf(2));
	}
	@Test
	public void testIndexOfNotThere() {
		assertEquals(-1, hasItems.indexOf(3));
	}

	@Test
	public void testIndexOfStrings() {
		assertEquals(2, stringList.indexOf("mewtwo"));
	}
	@Test
	public void testIndexOfStringsDuplicates() {
		stringList.add(3, "mewtwo");
		assertEquals(2, stringList.indexOf("mewtwo"));
	}
	
	// lastIndexOf tests
	
	@Test
	public void testLastIndexOfBasic() {
		assertEquals(2, stringList.lastIndexOf("mewtwo"));
	}
	@Test
	public void testLastIndexOfStringDuplicates() {
		stringList.add(0, "mewtwo");
		stringList.addLast("mewtwo");
		assertEquals(6, stringList.lastIndexOf("mewtwo"));
	}
	@Test
	public void testLastIndexOfNotThere() {
		assertEquals(-1, stringList.lastIndexOf("yoshi"));
	}
	
	// clear tests
	
	@Test
	public void testClearIntegers() {
		hasItems.clear();
		assertEquals(0, hasItems.size());
	}
	@Test
	public void testClearStrings() {
		stringList.clear();
		assertEquals(0, stringList.size());
	}
	
	// size lists 
	
	@Test
	public void testSizeBasic() {
		hasItems.clear();
		assertEquals(0, hasItems.size());
	}
	@Test
	public void testSizeStrings() {
		assertEquals(5, stringList.size());
	}
	@Test
	public void testSizeLargeLoop() {
		for (int i = 0; i < 100; i++) {
			list.addLast(i);
		}
		assertEquals(100, list.size());
	}
	
	// isEmpty tests
	
	@Test
	public void testisEmptyTrue() {
		hasItems.clear();
		assertTrue(hasItems.isEmpty());
	}
	@Test
	public void testisEmptyFalse() {
		assertFalse(hasItems.isEmpty());
	}
	
	// toArray tests 
	
	@Test
	public void testToArrayIntegers() {
		Object[] arr = hasItems.toArray();
		Object [] correctArr = {6, 4, 2, 0};
		assertArrayEquals(correctArr, arr);
	}
	@Test
	public void testToArrayEmptyInteger() {
		Object[] arr = list.toArray();
		Object [] correctArr = new Object [0];
		assertArrayEquals(correctArr, arr);
	}
	@Test
	public void testToArrayHasNull() {
		hasItems.add(3, null);
		Object[] arr = hasItems.toArray();
		Object [] correctArr = {6, 4, 2, null, 0};
		assertArrayEquals(correctArr, arr);
	}
	@Test
	public void testToArrayStrings() {
		Object[] arr = stringList.toArray();
		Object [] correctArr = {"tigers", "bears", "mewtwo", "trump", "lions"};
		assertArrayEquals(correctArr, arr);
	}
	
	// iterator test (hasNext, next and remove)
	
	
	// hasNext tests 
	
	@Test
	public void testHasNextBasicPointingAt0() {
		assertTrue(iter.hasNext());
	}
	@Test
	public void testHasNextBasicMiddle() {
		iter.next();
		iter.next();
		assertTrue(iter.hasNext());
	}
	@Test
	public void testHasNextEndFalse() {
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		assertFalse(iter.hasNext());
	}
	@Test
	public void testHasNextStrings() {
		assertTrue(iterString.hasNext());
	}
	@Test
	public void testHasNextStringEnd() {
		iterString.next();
		iterString.next();
		iterString.next();
		assertTrue(iter.hasNext());
	}
	@Test
	public void testHasNextStringFalse() {
		iterString.next();
		iterString.next();
		iterString.next();
		iterString.next();
		iterString.next();
		assertFalse(iterString.hasNext());
	}
	
	// next tests
	
	@Test
	public void testNextBasic() {
		assertTrue(iter.next().equals(6));
	}
	@Test
	public void testNextLastItem() {
		iter.next();
		iter.next();
		iter.next();
		assertTrue(iter.next().equals(0));
	}
	@Test (expected = NoSuchElementException.class)
	public void testNextException() {
		iter.next();
		iter.next();
		iter.next();
		iter.next();
		assertTrue(iter.next().equals(0));
	}
	
	// remove tests (iterator version)
	
	@Test
	public void testRemoveBasicInteger() {
		iter.next();
		iter.remove();
		assertTrue(hasItems.getFirst().equals(4));
	}
	@Test
	public void testRemoveBasicString() {
		iterString.next();
		iterString.remove();
		iterString.next();
		iterString.remove();
		assertTrue(stringList.size() == 3);
	}
	
	// for-each loop
	
	@Test
	public void testForEachLoop() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Integer i : hasItems) {
			int x = 2;
			x *= i;
			list.add(x);
		}
		assertTrue(list.get(0).equals(12) && list.get(3).equals(0));
	}
}
