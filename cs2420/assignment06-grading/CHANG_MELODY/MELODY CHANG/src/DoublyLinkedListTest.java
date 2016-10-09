package assignment06;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * @author Ching-Yuan Chang u0914005
 */

public class DoublyLinkedListTest {
	DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

	@Test
	public void testAddFirstWithGetFirst() {
		list.addFirst(10);
		list.addFirst(34);
		assertSame(34, list.getFirst());
	}

	@Test
	public void testAddLastWithGetLast() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		assertSame(33, list.getLast());
	}

	@Test
	public void testAddToNonEmptyListIndex3UsingGetIndex() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 17);
		assertSame(17, list.get(3));
	}

	@Test
	public void testAddToEmptyList() {
		list.add(0, 12);
		assertSame(list.getLast(), list.getFirst());
		assertSame(12, list.getLast());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddExceptionWithNegativeIndex() {
		list.add(-3, 5);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddExceptionWithIndexGreaterThanSize() {
		list.addFirst(3);
		list.add(3, 5);
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetFirstException() {
		list.getFirst();
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetLastException() {
		list.getLast();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetExceptionWithNegativeIndex() {
		list.get(-2);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetExceptionWithIndexGreaterThanSize() {
		list.addFirst(3);
		list.get(7);
	}

	@Test
	public void testRemoveFirst() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		assertSame(34, list.getFirst());
		assertSame(34, list.removeFirst());
		assertSame(10, list.getFirst());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirstException() {
		list.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		assertSame(33, list.getLast());
		assertSame(33, list.removeLast());
		assertEquals(234, (int) list.getLast());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveLastException() {
		list.removeLast();
	}

	@Test
	public void testRemoveIndex3() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);
		assertSame(12, list.remove(3));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveExceptionWithNegativeIndex() {
		list.remove(-3);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveExceptionWithIndexGreaterThanSize() {
		list.addFirst(3);
		list.remove(4);
	}

	@Test
	public void testIndexOfWithActualElement() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);
		assertSame(3, list.lastIndexOf(12));
	}

	@Test
	public void testIndexOfWithNoActualElement() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);
		assertSame(-1, list.lastIndexOf(357));
	}

	@Test
	public void testLastIndexOfWithActualElement() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);
		list.addLast(12);
		assertSame(5, list.lastIndexOf(12));
	}

	@Test
	public void testLastIndexOfWithNoActualElement() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);
		assertSame(-1, list.lastIndexOf(357));
	}

	@Test
	public void testSize() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);
		assertSame(5, list.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);
		assertFalse(list.isEmpty());
	}

	@Test(expected = NoSuchElementException.class)
	public void testClearWithGetFirstException() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);
		list.clear();
		list.getFirst();
	}

	@Test
	public void testToArray() {
		list.addFirst(10);
		list.addFirst(34);
		list.addLast(234);
		list.addLast(33);
		list.add(3, 12);

		Object[] goal = new Object[list.size()];
		goal[0] = 34;
		goal[1] = 10;
		goal[2] = 234;
		goal[3] = 12;
		goal[4] = 33;

		assertArrayEquals(goal, list.toArray());
	}

	@Test
	public void testIteratorHasNextAndNext() {
		list.addFirst(10);
		list.addFirst(34);
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.hasNext());
		iter.next();
		assertTrue(iter.hasNext());
		iter.next();
		assertFalse(iter.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void testIteratorNextExeption() {
		list.addFirst(10);
		list.addFirst(34);
		Iterator<Integer> iter = list.iterator();
		assertTrue(iter.hasNext());
		iter.next();
		assertTrue(iter.hasNext());
		iter.next();
		assertFalse(iter.hasNext());
		iter.next();
	}

	@Test
	public void testIteratorRemove() {
		list.addFirst(10);
		list.addFirst(34);
		Iterator<Integer> iter = list.iterator();
		iter.remove();
		assertTrue(iter.hasNext());
		iter.next();
		assertFalse(iter.hasNext());
	}

	@Test(expected = IllegalStateException.class)
	public void testIteratorRemoveTwiceInARoll() {
		list.addFirst(10);
		list.addFirst(34);
		Iterator<Integer> iter = list.iterator();
		iter.remove();
		iter.remove();
	}

}
