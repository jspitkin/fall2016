package assignment06;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * This suite tests the functionality of the 
 * DoublyLinkedList and it's methods.
 * 
 * @author Cooper Pender (u0843147)
 * 
 * Last Edited On: 10/02/2016
 */
public class DoublyLinkedListJUnitTest {

	DoublyLinkedList<Integer> intListTester;
	DoublyLinkedList<String> stringListTester;
	
	@Before
	public void setUp() throws Exception {
		intListTester = new DoublyLinkedList<Integer>();
		stringListTester = new DoublyLinkedList<String>();
	}

	@Test
	public void addFirstAndGetNormalTest() {
		intListTester.addFirst(5);
		intListTester.addFirst(9);
		assertEquals((Integer)9, intListTester.get(0));
	}
	
	@Test
	public void addFirstAndGetEmptyTest() {
		stringListTester.addFirst("cake");
		assertEquals("cake", stringListTester.get(0));
	}
	
	@Test
	public void addFirstAndGetNullTest() {
		stringListTester.addFirst(null);
		assertEquals(null, stringListTester.get(0));
	}
	
	@Test
	public void addLastNormalTest() {
		intListTester.addLast(3);
		intListTester.addFirst(2);
		intListTester.addLast(1);
		assertEquals((Integer)1, intListTester.get(2));
	}
	
	@Test
	public void addLastEmptyTest() {
		intListTester.addLast(3);
		assertEquals((Integer)3, intListTester.get(0));
	}
	
	@Test
	public void addLastNullTest() {
		stringListTester.addLast("pop");
		stringListTester.addLast("corn");
		stringListTester.addLast(null);
		assertEquals(null, stringListTester.get(2));
	}
	
	@Test 
	public void addNormalTest() {
		intListTester.add(0, 6);
		intListTester.add(0, 7);
		intListTester.add(1, 2);
		intListTester.toArray();
		assertEquals((Integer)2, intListTester.get(1));
		
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void addWrongIndexTest() {
		stringListTester.add(1, "chocolate");
	}
	
	@Test
	public void addEndOfListTest() {
		intListTester.addFirst(4);
		intListTester.addLast(2);
		intListTester.add(2, 8);
		assertEquals((Integer)8, intListTester.get(2));
	}
	
	@Test
	public void getFirstNormalTest() {
		stringListTester.add(0, "cake");
		stringListTester.addFirst("popsicle");
		stringListTester.addLast("pizza");
		assertEquals("popsicle", stringListTester.getFirst());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void getFirstEmptyTest() {
		intListTester.getFirst();
	}
	
	@Test
	public void getLastNormalTest() {
		intListTester.addFirst(5);
		intListTester.add(0, 2);
		intListTester.addLast(9);
		assertEquals((Integer)9, intListTester.getLast());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void getLastEmptyTest() {
		intListTester.getLast();
	}
	
	@Test
	public void getFirstAndLastOneItemListTest() {
		stringListTester.addFirst("cake");
		assertEquals("cake", stringListTester.getFirst());
		assertEquals(null, stringListTester.getLast());
	}
	
	@Test
	public void removeFirstNormalTest() {
		intListTester.add(0, 5);
		intListTester.add(0, 6);
		intListTester.add(0, 5);
		assertEquals((Integer)5, intListTester.removeFirst());
		assertEquals(2, intListTester.size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeFirstEmptyTest() {
		stringListTester.removeFirst();
	}
	
	@Test
	public void removeFirstSingleItemListTest() {
		stringListTester.addFirst("cake");
		assertEquals("cake", stringListTester.removeFirst());
		assertEquals(0, stringListTester.size());
	}
	
	@Test
	public void removeLastNormalTest() {
		intListTester.add(0, 1);
		intListTester.add(0, 2);
		intListTester.add(0, 3);
		intListTester.toArray();
		assertEquals((Integer)1, intListTester.removeLast());
		assertEquals(2, intListTester.size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeLastEmptyTest() {
		stringListTester.removeLast();
	}
	
	@Test
	public void removeLastSingleItemListTest() {
		stringListTester.addFirst("cake");
		assertEquals("cake", stringListTester.removeLast());
		assertEquals(0, stringListTester.size());
	}
	
	@Test
	public void removeNormalTest() {
		intListTester.add(0, 6);
		intListTester.addFirst(5);
		intListTester.addFirst(8);
		intListTester.addFirst(9);
		assertEquals((Integer)8, intListTester.remove(1));
		assertEquals(3, intListTester.size());
		intListTester.toArray();
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void removeWrongIndexTest() {
		intListTester.remove(8);
	}
	
	@Test
	public void indexOfDuplicateTest() {
		stringListTester.addFirst("cake");
		stringListTester.addFirst("pop");
		stringListTester.addFirst("soda");
		stringListTester.addFirst("cake");
		stringListTester.addFirst("squid");
		assertEquals(1, stringListTester.indexOf("cake"));
		
	}
	
	@Test
	public void indexOfNormalTest() {
		intListTester.addLast(9);
		intListTester.addLast(4);
		intListTester.addLast(80);
		assertEquals(2, intListTester.indexOf(80));
	}
	
	@Test
	public void indexOfNotPresentTest() {
		intListTester.addLast(1);
		intListTester.addLast(5);
		intListTester.addLast(2);
		assertEquals(-1, intListTester.indexOf(7));
	}
	
	@Test
	public void lastIndexOfNormalTest() {
		stringListTester.addFirst("cake");
		stringListTester.addFirst("pop");
		stringListTester.addFirst("soda");
		stringListTester.addFirst("cake");
		stringListTester.addFirst("squid");
		stringListTester.toArray();
		assertEquals(4, stringListTester.lastIndexOf("cake"));
	}
	
	@Test
	public void lastIndexOfNotPresentTest() {
		stringListTester.addFirst("cake");
		stringListTester.addFirst("spaghetti");
		stringListTester.addFirst("pop");
		assertEquals(-1, stringListTester.lastIndexOf("soda"));
	}
	
	@Test
	public void isEmptyTrueTest() {
		assertEquals(true, intListTester.isEmpty());
	}
	
	@Test
	public void isEmptyFalseTest() {
		intListTester.addFirst(4);
		assertEquals(false, intListTester.isEmpty());
	}
	
	@Test
	public void clearTest() {
		stringListTester.addFirst("cake");
		stringListTester.addFirst("pop");
		stringListTester.addFirst("soda");
		stringListTester.addFirst("chocolate");
		stringListTester.clear();
		assertEquals(0, stringListTester.size());
	}
	
	@Test
	public void toArrayNullTest() {
		intListTester.addFirst(null);
		assertEquals(null, intListTester.toArray()[0]);
	}
	
	@Test
	public void toArrayNormalTest() {
		intListTester.addFirst(4);
		intListTester.addFirst(9);
		intListTester.addFirst(11);
		Object[] intArr = intListTester.toArray();
		assertEquals(3, intArr.length);
		assertEquals(4, intArr[2]);
	}
	
	@Test
	public void iteratorHasNextTrueTest() {
		Iterator<Integer> intIter = intListTester.iterator();
		intListTester.addFirst(6);
		assertEquals(true, intIter.hasNext());
	}
	
	@Test
	public void iteratorHasNextFalseTest() {
		Iterator<String> stringIter = stringListTester.iterator();
		assertEquals(false, stringIter.hasNext());
	}
	
	@Test
	public void iteratorNextWithElementTest() {
		Iterator<Integer> intIter = intListTester.iterator();
		intListTester.addFirst(6);
		intListTester.addFirst(7);
		intListTester.addFirst(1);
		assertEquals((Integer)1, intIter.next());
		assertEquals((Integer)7, intIter.next());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void iteratorNextExceptionTest() {
		Iterator<String> stringIter = stringListTester.iterator();
		stringIter.next();
	}
	
	@Test
	public void iteratorRemoveNormalTester() {
		Iterator<Integer> intIter = intListTester.iterator();
		intListTester.addFirst(7);
		intListTester.addFirst(1);
		intListTester.addFirst(9);
		intListTester.addFirst(10);
		intListTester.addFirst(4);
		intIter.next();
		intIter.next();
		intIter.next();
		intIter.remove();
		assertEquals(-1, intListTester.indexOf(9));
	}
	
	@Test (expected = IllegalStateException.class)
	public void iteratorRemoveEmptyTest() {
		Iterator<Integer> intIter = intListTester.iterator();
		intIter.remove();
	}

}
