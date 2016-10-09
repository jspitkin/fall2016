package assignment06;

/**
 * This is a JUnit test case for the DoublyLinkedList class.
 * 
 * @author Tyler Adams
 */
import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTester {

	DoublyLinkedList<Integer> list1, list2, list3, list4, emptyList;
	DoublyLinkedList<String> strList1, strList2;
	
	@Before
	public void setUp() throws Exception {
		list1 = new DoublyLinkedList<Integer>();
		list2 = new DoublyLinkedList<Integer>();
		list3 = new DoublyLinkedList<Integer>();
		list4 = new DoublyLinkedList<Integer>();
		emptyList = new DoublyLinkedList<Integer>();
		
		list1.addFirst(0);
		list2.addFirst(0);
		list2.addLast(1);
		list4.addLast(0);
		list4.addLast(1);
		list4.addLast(0);
		list4.addLast(2);
		list4.addLast(0);
		list4.addLast(3);
		list4.addLast(0);
		list4.addLast(2);
		list4.addLast(0);
		list4.addLast(1);
		
		for(int idx = 0; idx < 50; idx++)
			list3.addLast(idx);
		
		strList1 = new DoublyLinkedList<String>();
		strList2 = new DoublyLinkedList<String>();
		
		strList1.addLast("");
		strList2.addLast("I");
		strList2.addLast("have");
		strList2.addLast("been");
		strList2.addLast("one");
		strList2.addLast("acquainted");
		strList2.addLast("with");
		strList2.addLast("the");
		strList2.addLast("night");
	}

	@Test
	public void testConstructorSize() {
		assertEquals(0, emptyList.size());
	}

	@Test
	public void testAddIndexSize() {
		emptyList.add(0, 0);
		assertEquals(1, emptyList.size());
	}

	@Test
	public void testAddFirstSize() {
		emptyList.addFirst(0);
		emptyList.addFirst(0);
		assertEquals(2, emptyList.size());
	}

	@Test
	public void testAddLastSize() {
		emptyList.addLast(0);
		assertEquals(1, emptyList.size());
	}

	@Test
	public void testAddLastSize2() {
		emptyList.addLast(0);
		emptyList.addLast(0);
		emptyList.addLast(0);
		assertEquals(3, emptyList.size());
	}

	@Test
	public void testSize() {
		assertEquals(50, list3.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIndex() {
		emptyList.add(-1, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIndex2() {
		list3.add(-1, 0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddIndex3() {
		list2.add(3, 0);
	}

	@Test
	public void testAddIndex4() {
		list1.add(1, 0);
		assertEquals((int) 0, (int) list1.get(1));
	}

	@Test
	public void testAddIndexValue() {
		list3.add(32, -1);
		assertEquals(-1, (int) list3.get(32));
	}

	@Test
	public void testGetFirstValue() {
		assertEquals(0, (int) list3.getFirst());
	}

	@Test
	public void testGetFirstValue2() {
		list3.addFirst(-1);
		assertEquals(-1, (int) list3.getFirst());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetFirstError() {
		emptyList.getFirst();
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetLastError() {
		emptyList.getLast();
	}

	@Test
	public void testGetLastValue() {
		assertEquals(49, (int) list3.getLast());
	}

	@Test
	public void testGetLastValue2() {
		list2.addLast(-1);
		assertEquals(-1, (int) list2.getLast());
	}

	@Test
	public void testGetIndex() {
		assertEquals(45, (int) list3.get(45));
	}

	@Test
	public void testGetIndex2() {
		assertEquals(15, (int) list3.get(15));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetIndex3() {
		emptyList.get(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetIndex4() {
		list2.get(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetIndex5() {
		list2.get(2);
	}

	@Test
	public void testGetIndex6() {
		assertEquals("", strList1.get(0));
	}

	@Test
	public void testGetIndex7() {
		assertEquals("acquainted", strList2.get(4));
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst() {
		emptyList.removeFirst();
	}

	@Test
	public void testRemoveFirst2() {
		list3.removeFirst();
		assertEquals(1, (int) list3.get(0));
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst3() {
		list1.removeFirst();
		list1.removeLast();
	}

	@Test
	public void testRemoveFirstSize() {
		list3.removeFirst();
		assertEquals(49, (int) list3.size());
	}
	
	@Test
	public void testRemoveFirstValue() {
		assertEquals(0, (int) list3.removeFirst());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveLast() {
		emptyList.removeLast();
	}

	@Test
	public void testRemoveLast2() {
		list3.removeLast();
		assertEquals(48, (int) list3.get(48));
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveLast3() {
		list1.removeLast();
		list1.getFirst();
	}
	
	@Test
	public void testRemoveLastSize() {
		list3.removeLast();
		assertEquals(49, list3.size());
	}

	@Test
	public void testRemoveLastValue() {
		assertEquals(49, (int) list3.removeLast());
	}
	
	@Test
	public void testRemoveValue() {
		assertEquals(45, (int) list3.remove(45));
	}

	@Test
	public void testRemoveValue2() {
		assertEquals(16, (int) list3.remove(16));
	}

	@Test
	public void testRemoveSize() {
		list3.remove(40);
		assertEquals(49, (int) list3.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveError() {
		emptyList.remove(0);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveError2() {
		list1.remove(1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveError3() {
		list2.remove(-1);
	}

	@Test
	public void testIndexOf() {
		assertEquals(-1, emptyList.indexOf(10));
	}

	@Test
	public void testIndexOf1() {
		assertEquals(-1, (int) list3.indexOf(50));
	}

	@Test
	public void testIndexOf2() {
		assertEquals(36, (int) list3.indexOf(36));
	}

	@Test
	public void testIndexOf3() {
		assertEquals(3, list4.indexOf(2));
	}
	
	@Test
	public void testIndexOf4() {
		assertEquals(49, list3.indexOf(49));
	}

	@Test
	public void testLastIndexOf() {
		assertEquals(-1, emptyList.lastIndexOf(10));
	}

	@Test
	public void testLastIndexOf2() {
		assertEquals(9, list4.lastIndexOf(1));
	}

	@Test
	public void testLastIndexOf3() {
		assertEquals(-1, list4.lastIndexOf(4));
	}

	@Test
	public void testIsEmpty() {
		assertTrue(emptyList.isEmpty());
	}

	@Test
	public void testIsEmpty2() {
		list1.removeFirst();
		assertTrue(list1.isEmpty());
	}

	@Test
	public void testIsEmpty3() {
		assertFalse(list1.isEmpty());
	}

	@Test
	public void testClear() {
		list3.clear();
		assertTrue(list3.isEmpty());
	}

	@Test(expected = NoSuchElementException.class)
	public void testClear2() {
		list3.clear();
		list3.getFirst();
	}

	@Test(expected = NoSuchElementException.class)
	public void testClear3() {
		list3.clear();
		list3.getLast();
	}

	@Test
	public void testToArray() {
		assertArrayEquals(new Object[0], emptyList.toArray());
	}

	@Test
	public void testToArray2() {
		assertArrayEquals(new Object[] {0, 1}, list2.toArray());
	}

	@Test
	public void testIterNext() {
		Iterator<Integer> iterator = list3.iterator();
		
		assertEquals(0, (int) iterator.next());
	}

	@Test
	public void testIterNext2() {
		Iterator<Integer> iterator = list3.iterator();
		iterator.next();
		
		assertEquals(1, (int) iterator.next());
	}

	@Test
	public void testIterHasNext() {
		Iterator<Integer> iterator = emptyList.iterator();
		
		assertFalse(iterator.hasNext());
	}

	@Test
	public void testIterHasNext2() {
		Iterator<Integer> iterator = list1.iterator();
		
		assertTrue(iterator.hasNext());
	}

	@Test
	public void testIterHasNext3() {
		Iterator<Integer> iterator = list1.iterator();
		iterator.next();
		
		assertFalse(iterator.hasNext());
	}

	@Test(expected = NoSuchElementException.class)
	public void testIterNextError() {
		Iterator<Integer> iterator = list1.iterator();
		iterator.next();
		iterator.next();
	}

	@Test(expected = IllegalStateException.class)
	public void testIterRemoveError() {
		Iterator<Integer> iterator = list3.iterator();
		iterator.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void testIterRemoveError2() {
		Iterator<Integer> iterator = emptyList.iterator();
		iterator.remove();
	}

	@Test(expected = IllegalStateException.class)
	public void testIterRemoveError3() {
		Iterator<Integer> iterator = list2.iterator();
		iterator.next();
		iterator.remove();
		iterator.remove();
	}

	@Test
	public void testIterRemove() {
		Iterator<Integer> iterator = list3.iterator();
		while(iterator.hasNext()) {
			if(iterator.next() == 15)
				break;
		}
		
		iterator.remove();
		
		assertEquals(-1, (int) list3.indexOf(15));
	}

	@Test
	public void testIterRemove2() {
		Iterator<Integer> iterator = list3.iterator();
		iterator.next();
		iterator.remove();
		
		assertEquals(49, (int) list3.size());
	}
	@Test
	public void testIterRemove3() {
		Iterator<Integer> iterator = list3.iterator();
		while(iterator.hasNext()) {
			iterator.next();
		}
		iterator.remove();
		
		assertEquals(48, (int) list3.getLast());
	}

}
