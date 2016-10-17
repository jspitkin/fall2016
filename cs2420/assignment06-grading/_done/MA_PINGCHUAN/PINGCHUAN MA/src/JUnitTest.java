package assignment06;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnitTest {
	DoublyLinkedList<Integer> mylist;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mylist = new DoublyLinkedList<>();
		for (int i = 0; i < 20; i++) {
			mylist.addLast(i);
		}
	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testAddFirst() {
		mylist.addFirst(20);
		int actual = mylist.get(0);
		int expected = 20;
		assertEquals(expected, actual);
	}

	@Test
	public void testAddLast() {
		mylist.addLast(100);
		int actual = mylist.get(20);
		int expected = 100;
		assertEquals(expected, actual);
	}

	@Test
	public void testAdd() {
		mylist.add(1, 30);
		int actual = mylist.get(1);
		int expected = 30;
		assertEquals(expected, actual);
	}

	@Test
	public void testGetFirst() {
		int actual = mylist.getFirst();
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void testGetLast() {
		int actual = mylist.getLast();
		int expected = 19;
		assertEquals(expected, actual);
	}

	@Test
	public void testGet() {
		int actual = mylist.get(2);
		int expected = 2;
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoveFirst() {
		int actual = mylist.removeFirst();
		int expected = 0;
		assertEquals(expected, actual);
	}

	@Test
	public void testRemoveLast() {
		int actual = mylist.removeLast();
		int expected = 19;
		assertEquals(expected, actual);
	}

	@Test
	public void testRemove() {
		int actual = mylist.remove(1);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void testIndexOf() {
		int actual = mylist.indexOf(2);
		int expected = 2;
		assertEquals(expected, actual);
	}

	@Test
	public void testLastIndexOf() {
		mylist.add(19, 2);
		int actual = mylist.lastIndexOf(2);
		int expected = 19;
		assertEquals(expected, actual);
	}

	@Test
	public void testSize() {
		int actual = mylist.size();
		int expected = 20;
		assertEquals(expected, actual);
	}

	@Test
	public void testIsEmpty() {
		boolean actual = mylist.isEmpty();
		assertFalse(actual);
	}

	@Test
	public void testClear() {
		mylist.clear();
		assertTrue(mylist.isEmpty());

	}

	@Test
	public void testToArray() {
		Object[] actual = mylist.toArray();
		Object[] expected = new Object[20];
		for (int i = 0; i < 20; i++) {
			expected[i] = i;
		}
		assertArrayEquals(expected, actual);

	}

	@Test
	public void testIteratornext() {
		ArrayList<Integer> actual = new ArrayList<>();
		ArrayList<Integer> expected = new ArrayList<>();
		Iterator<Integer> iterator = mylist.iterator();
		while (iterator.hasNext()) {
			actual.add(iterator.next());
		}
		for (int i = 0; i < 20; i++) {
			expected.add(i);
		}
		assertEquals(expected, actual);
	}

	@Test
	public void testIteratormove() {
		Iterator<Integer> iterator = mylist.iterator();
		while (iterator.hasNext()) {
			iterator.remove();
		}

		assertTrue(mylist.isEmpty());

	}

}
