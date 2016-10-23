package assignment06;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the tester class for DoublyLinkedList
 * @author Haoran Chen
 * @uid 1060286
 */
public class DoublyLinkedListTester {

	DoublyLinkedList<Integer> tester = new DoublyLinkedList<Integer>();
	
	@Before
	public void setUp() throws Exception {
		tester.addFirst(1);
		tester.addFirst(3);
		tester.addFirst(5);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void emptyTest1() {
		tester = new DoublyLinkedList<Integer>();
		assertTrue(tester.isEmpty());
	}
	
	@Test
	public void emptyTest2() {
		assertFalse(tester.isEmpty());
	}
	
	@Test
	public void clearTest() {
		assertFalse(tester.size() == 0);
		tester.clear();
		assertTrue(tester.size() == 0);
	}
	
	@Test
	public void emptySize() {
		tester.clear();
		assertEquals(tester.size(), 0);
	}
	
	@Test
	public void fullSize() {
		assertEquals(tester.size(), 3);
	}
	
	@Test
	public void removeSize() {
		tester.removeFirst();
		assertEquals(tester.size(), 2);
	}
	
	@Test
	public void addFirstEmpty() {
		tester.clear();
		tester.addFirst(1);
		assertTrue(tester.getFirst().equals(1));
	}
	
	@Test
	public void addFirstNotEmpty() {
		tester.clear();
		tester.addFirst(1);
		tester.addFirst(3);
		tester.addFirst(5);
		assertTrue(tester.getFirst().equals(5));
	}
	
	@Test
	public void getLastWithFirst() {
		tester.clear();
		tester.addFirst(1);
		assertTrue(tester.getLast().equals(1));
	}
	
	@Test
	public void addLastEmpty() {
		tester.clear();
		tester.addLast(1);
		assertTrue(tester.getLast().equals(1));
	}
	
	@Test
	public void addLastNotEmpty() {
		tester.clear();
		tester.addFirst(1);
		tester.addFirst(3);
		tester.addLast(5);
		assertTrue(tester.getFirst().equals(3));
		assertTrue(tester.getLast().equals(5));
	}
	
	@Test
	public void addNormal() {
		tester.add(1, 2);
		assertEquals(tester.getFirst(), (Integer)5);
		assertTrue(tester.get(1).equals(2));
		assertTrue(tester.get(2).equals(3));
		assertTrue(tester.get(3).equals(1));
		assertEquals(tester.size(),4);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void addIndexEmpty() {
		tester.clear();
		tester.add(4, 2);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void addIndexNeg() {
		tester.add(-4, 2);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void addIndexLarge() {
		tester.add(5, 2);
	}
	
	@Test
	public void addEmpty() {
		tester.clear();
		tester.add(0, 2);
		assertTrue(tester.getFirst().equals(2));
		assertEquals(tester.size(), 1);
	}
	
	@Test
	public void getFirstWithLast() {
		tester.clear();
		tester.addLast(1);
		assertTrue(tester.getFirst().equals(1));
	}
	
	@Test (expected = NoSuchElementException.class)
	public void getFirstEmpty() {
		tester.clear();
		tester.getFirst();
	}
	
	@Test
	public void getLast() {
		tester.clear();
		tester.addLast(1);
		assertTrue(tester.getLast().equals(1));
	}

	@Test (expected = NoSuchElementException.class)
	public void getLastEmpty() {
		tester.clear();
		tester.getLast();
	}
	
	@Test
	public void getTester() {
		assertEquals(tester.get(0), (Integer)5);
		assertEquals(tester.get(1), (Integer)3);
		assertEquals(tester.get(2), (Integer)1);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void getEmpty() {
		tester.clear();
		tester.get(4);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void getMinus() {
		tester.get(-4);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void getTooLarge() {
		tester.get(3);
	}
	
	@Test
	public void removeFirstNotEmpty() {
		tester.removeFirst();
		assertTrue(tester.getFirst().equals(3));
	}
	
	@Test
	public void removeFirstReturn() {
		assertTrue(tester.removeFirst().equals(5));
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeFirstEmpty() {
		tester.clear();
		tester.removeFirst();
	}
	
	@Test
	public void removeLastNotEmpty() {
		tester.removeLast();
		assertTrue(tester.getLast().equals(3));
	}
	
	@Test
	public void removeLastReturn() {
		assertTrue(tester.removeLast().equals(1));
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeLastEmpty() {
		tester.clear();
		tester.removeLast();
	}
	
	@Test
	public void removeNotEmpty() {
		tester.remove(1);
		assertTrue(tester.get(0).equals(5));
		assertTrue(tester.get(1).equals(1));
	}
	
	@Test
	public void removeDoubleNotEmpty() {
		tester.remove(1);
		tester.remove(1);
		assertTrue(tester.size()==1);
	}
	
	@Test
	public void removeReturn() {
		assertEquals(tester.remove(2), (Integer)1);
		assertEquals(tester.remove(1), (Integer)3);
		assertEquals(tester.remove(0), (Integer)5);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void removeEmpty() {
		tester.clear();
		tester.remove(3);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void removeOFB() {
		tester.remove(3);
	}
	
	@Test 
	public void indexOfNotEmpty() {
		assertEquals(tester.indexOf(1), 2);
		assertEquals(tester.indexOf(3), 1);
		assertEquals(tester.indexOf(5), 0);
	}
	
	@Test 
	public void indexOfNotExist() {
		assertEquals(tester.indexOf(-1), -1);
		assertEquals(tester.indexOf(6), -1);
		assertEquals(tester.indexOf(2), -1);
	}
	
	@Test 
	public void indexOfEmpty() {
		tester.clear();
		assertEquals(tester.indexOf(3), -1);
	}
	
	@Test 
	public void lastIndexOfNotdublicate() {
		assertEquals(tester.lastIndexOf(1), 2);
		assertEquals(tester.lastIndexOf(3), 1);
		assertEquals(tester.lastIndexOf(5), 0);
	}
	
	@Test 
	public void lastIndexOfDublicate() {
		tester.addLast(5);
		tester.addLast(3);
		assertEquals(tester.lastIndexOf(1), 2);
		assertEquals(tester.lastIndexOf(3), 4);
		assertEquals(tester.lastIndexOf(5), 3);
	}
	
	@Test 
	public void lastIndexOfNotExist() {
		tester.addLast(5);
		tester.addLast(3);
		assertEquals(tester.lastIndexOf(-1), -1);
		assertEquals(tester.lastIndexOf(30), -1);
		assertEquals(tester.lastIndexOf(6), -1);
	}
	
	@Test 
	public void iteratorNext() {
		Iterator<Integer> It = tester.iterator();
		assertTrue(It.hasNext());
		int first = It.next();
		assertTrue(It.hasNext());
		int second = It.next();
		assertTrue(It.hasNext());
		int third = It.next();
		assertFalse(It.hasNext());
		assertEquals(first, 5);
		assertEquals(second, 3);
		assertEquals(third, 1);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void iteratorNoNext() {
		Iterator<Integer> It = tester.iterator();
		int first = It.next();
		int second = It.next();
		int third = It.next();
		It.next();
	}
	
	@Test 
	public void iteratorEmptyHasNext() {
		tester.clear();
		Iterator<Integer> It = tester.iterator();
		assertFalse(It.hasNext());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void iteratorEmptyNext() {
		tester.clear();
		Iterator<Integer> It = tester.iterator();
		It.next();
	}
	
	@Test (expected = IllegalStateException.class)
	public void iteratorEmptyRemove() {
		Iterator<Integer> It = tester.iterator();
		It.remove();
	}
	
	@Test (expected = IllegalStateException.class)
	public void iteratorDoubleRemove() {
		Iterator<Integer> It = tester.iterator();
		It.next();
		It.remove();
		It.remove();
	}
	
	@Test 
	public void iteratorRemove() {
		Iterator<Integer> It = tester.iterator();
		int first = It.next();
		It.remove();
		int test = It.next();
		assertEquals(first, 5);
		assertEquals(test, 3);
		assertEquals(tester.size(), 2);
	}
	
	@Test 
	public void toArray() {
		Object[] test = tester.toArray();
		assertEquals(test[0], 5);
		assertEquals(test[1], 3);
		assertEquals(test[2], 1);
	}
}
