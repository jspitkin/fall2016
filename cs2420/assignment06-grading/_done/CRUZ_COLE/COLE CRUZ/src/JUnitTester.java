//Cole Cruz
package assignment06;

import org.junit.Test;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;

/**
 * JUnit tester for the DoublyLinkedList class.
 * 
 * @author Cole Cruz
 *
 */
public class JUnitTester {

	private static DoublyLinkedList<Integer> integerDLL;
	private static DoublyLinkedList<Object> emptyDLL;
	private static int size;

	/**
	 * Whatever setup needs to be done before each test method.
	 */
	@Before
	public void setUp() {
		emptyDLL = new DoublyLinkedList<Object>();
		integerDLL = new DoublyLinkedList<Integer>();
		size = 10;
		for (int count = 1; count < 10; count++) {
			integerDLL.addFirst(count);
		}
		integerDLL.addFirst(1);
	}

	/**
	 * Various tests for each method of the AnagramUtil class.
	 */
	@Test
	public void addFirstEmptyList() {
		emptyDLL.addFirst(4);
		Assert.assertEquals(4, (int) emptyDLL.getLast());
	}

	@Test
	public void addFirst() {
		integerDLL.addFirst(1);
		Assert.assertEquals(1, (int) integerDLL.getFirst());
	}

	@Test
	public void addLastEmptyList() {
		emptyDLL.addLast(6);
		Assert.assertEquals(6, (int) emptyDLL.getFirst());
	}

	@Test
	public void addLast() {
		integerDLL.addLast(8);
		Assert.assertEquals(8, (int) integerDLL.getLast());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void addThrow() {
		integerDLL.add(size + 1, null);
		integerDLL.add(-1, null);
	}

	@Test
	public void addIndexMin() {
		integerDLL.add(0, 9);
		Assert.assertEquals(9, (int) integerDLL.getFirst());
	}

	@Test
	public void addIndexMax() {
		integerDLL.add(size, 3);
		Assert.assertEquals(3, (int) integerDLL.getLast());
	}

	@Test
	public void add() {
		integerDLL.add(2, 1);
		Assert.assertEquals(1, (int) integerDLL.get(2));
	}

	@Test(expected = NoSuchElementException.class)
	public void getFirstThrow() {
		emptyDLL.getFirst();
	}

	@Test
	public void getFirst() {
		Assert.assertEquals(1, (int) integerDLL.getFirst());
	}

	@Test(expected = NoSuchElementException.class)
	public void getLastThrow() {
		emptyDLL.getLast();
	}

	@Test
	public void getLast() {
		Assert.assertEquals(1, (int) integerDLL.getLast());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void getThrow() {
		integerDLL.get(size);
		integerDLL.get(-1);
	}

	@Test
	public void getIndexMin() {
		Assert.assertEquals(1, (int) integerDLL.get(0));
	}

	@Test
	public void getIndexMax() {
		Assert.assertEquals(1, (int) integerDLL.get(size - 1));
	}

	@Test
	public void get() {
		Assert.assertEquals(8, (int) integerDLL.get(2));
		Assert.assertEquals(6, (int) integerDLL.get(4));
		Assert.assertEquals(4, (int) integerDLL.get(6));
		Assert.assertEquals(2, (int) integerDLL.get(8));
	}

	@Test(expected = NoSuchElementException.class)
	public void removeFirstThrow() {
		emptyDLL.removeFirst();
	}

	@Test
	public void removeFirst() {
		Assert.assertEquals(1, (int) integerDLL.removeFirst());
		Assert.assertEquals(9, integerDLL.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void removeLastThrow() {
		emptyDLL.removeLast();
	}

	@Test
	public void removeLast() {
		Assert.assertEquals(1, (int) integerDLL.removeLast());
		Assert.assertEquals(9, integerDLL.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void removeThrow() {
		integerDLL.remove(size);
		integerDLL.remove(-1);
	}

	@Test
	public void removeIndexMin() {
		Assert.assertEquals(1, (int) integerDLL.remove(0));
		Assert.assertEquals(9, integerDLL.size());
	}

	@Test
	public void removeIndexMax() {
		Assert.assertEquals(1, (int) integerDLL.remove(size - 1));
		Assert.assertEquals(9, integerDLL.size());
	}

	@Test
	public void remove() {
		Assert.assertEquals(8, (int) integerDLL.remove(2));
		Assert.assertEquals(9, integerDLL.size());
	}

	@Test
	public void indexOfDoesNotExist() {
		Assert.assertEquals(-1, integerDLL.indexOf(0));
		Assert.assertEquals(-1, emptyDLL.indexOf(0));
	}

	@Test
	public void indexOf() {
		Assert.assertEquals(0, integerDLL.indexOf(1));
	}

	@Test
	public void lastIndexOfDoesNotExist() {
		Assert.assertEquals(-1, integerDLL.indexOf(0));
		Assert.assertEquals(-1, emptyDLL.indexOf(0));
	}

	@Test
	public void lastIndexOf() {
		Assert.assertEquals(9, integerDLL.lastIndexOf(1));
	}

	@Test
	public void sizeEmptyList() {
		Assert.assertEquals(0, emptyDLL.size());
	}

	@Test
	public void size() {
		Assert.assertEquals(10, integerDLL.size());
	}

	@Test
	public void isEmpty() {
		Assert.assertTrue(emptyDLL.isEmpty());
		Assert.assertFalse(integerDLL.isEmpty());
	}

	@Test
	public void clear() {
		emptyDLL.addFirst(1);
		Assert.assertFalse(emptyDLL.isEmpty());
		emptyDLL.clear();
		Assert.assertTrue(emptyDLL.isEmpty());
	}

	@Test
	public void toArray() {
		Object[] answerArray = new Object[] { 1, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		Assert.assertArrayEquals(answerArray, integerDLL.toArray());
		answerArray = new Object[] {};
		Assert.assertArrayEquals(answerArray, emptyDLL.toArray());
	}

	@Test
	public void iteratorHasNext() {
		Assert.assertTrue(integerDLL.iterator().hasNext());
		Assert.assertFalse(emptyDLL.iterator().hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void iteratorNextThrow() {
		emptyDLL.iterator().next();
		for (int count = 0; count <= integerDLL.size(); count++) {
			integerDLL.iterator().next();
		}
	}

	@Test
	public void iteratorNext() {
		Assert.assertEquals(1, (int) integerDLL.iterator().next());
	}

	@Test(expected = IllegalStateException.class)
	public void iteratorRemoveThrow() {
		emptyDLL.iterator().remove();
		integerDLL.iterator().next();
		for (int count = 0; count <= integerDLL.size(); count++) {
			integerDLL.iterator().remove();
		}
	}

	@Test
	public void iteratorRemove() {
		integerDLL.iterator().next();
		integerDLL.iterator().remove();
		Assert.assertEquals(9, integerDLL.size());
	}

}
