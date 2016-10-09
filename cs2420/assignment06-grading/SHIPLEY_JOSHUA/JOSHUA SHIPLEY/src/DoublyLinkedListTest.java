package assignment06;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Test;

public class DoublyLinkedListTest {

	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
	
	@After
	public void clearList(){
		list.clear();
	}
	
	@Test
	public void testAddFirst() {		
		list.addFirst(5);
		assertEquals(0, list.get(0).compareTo(5));
		
		assertEquals(1, list.size());
		
		list.addFirst(3);
		assertEquals(0, list.get(0).compareTo(3));
		assertEquals(0, list.get(1).compareTo(5));
		
		assertEquals(2, list.size());
		
		list.addFirst(9);
		assertEquals(0, list.get(0).compareTo(9));
		assertEquals(0, list.get(1).compareTo(3));
		assertEquals(0, list.get(2).compareTo(5));
		
		assertEquals(3, list.size());
	}

	@Test
	public void testAddLast() {
		list.addLast(5);
		assertEquals(0, list.get(0).compareTo(5));
		
		assertEquals(1, list.size());
		
		list.addLast(3);
		assertEquals(0, list.get(1).compareTo(3));
		assertEquals(0, list.get(0).compareTo(5));
		
		assertEquals(2, list.size());
		
		list.addLast(9);
		assertEquals(0, list.get(2).compareTo(9));
		assertEquals(0, list.get(1).compareTo(3));
		assertEquals(0, list.get(0).compareTo(5));
		
		assertEquals(3, list.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdd_Exception_EmptyList(){
		list.add(7, 7);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdd_Exception_NegativeIndex(){
		list.addLast(9);
		list.addLast(3);
		list.addLast(5);
		
		list.add(-1, 7);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAdd_Exception_LargeIndex(){
		list.addLast(9);
		list.addLast(3);
		list.addLast(5);
		
		list.add(7, 7);
	}
	
	@Test
	public void testAdd() {
		list.add(0, 5);
		assertEquals(0, list.get(0).compareTo(5));
		assertEquals(1, list.size());
		
		list.add(1, 3);
		assertEquals(0, list.get(1).compareTo(3));
		assertEquals(0, list.get(0).compareTo(5));
		assertEquals(2, list.size());
		
		list.add(2, 9);
		assertEquals(0, list.get(2).compareTo(9));
		assertEquals(0, list.get(1).compareTo(3));
		assertEquals(0, list.get(0).compareTo(5));
		assertEquals(3, list.size());
		
		list.add(1, 2);
		list.add(2, 7);
		list.add(0, 5);
		
		assertEquals(0, list.get(0).compareTo(5));
		assertEquals(0, list.get(1).compareTo(5));
		assertEquals(0, list.get(2).compareTo(2));
		assertEquals(0, list.get(3).compareTo(7));
		assertEquals(0, list.get(4).compareTo(3));
		assertEquals(0, list.get(5).compareTo(9));
		assertEquals(6, list.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetFirst_Exception_EmptyList(){
		list.getFirst();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetFirst_Exception_ClearedList(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(8);
		
		list.clear();
		
		list.getFirst();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetFirst_Exception_RemovedList(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(8);
		
		list.remove(2);
		list.remove(1);
		list.remove(0);
		
		list.getFirst();
	}
	
	
	
	@Test
	public void testGetFirst() {
		list.addLast(9);
		list.addLast(3);
		list.addLast(5);
		
		assertEquals(0, list.getFirst().compareTo(9));
		
		list.addFirst(7);
		assertEquals(0, list.getFirst().compareTo(7));
		
		list.add(0, 6);
		assertEquals(0, list.getFirst().compareTo(6));
		
		list.add(4, 1);
		assertEquals(0, list.getFirst().compareTo(6));
	}

	@Test(expected = NoSuchElementException.class)
	public void testGetLast_Exception_EmptyList(){
		list.getLast();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetLast_Exception_ClearedList(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(8);
		
		list.clear();
		
		list.getLast();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testGetLast_Exception_RemovedList(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(8);
		
		list.remove(2);
		list.remove(1);
		list.remove(0);
		
		list.getLast();
	}
	
	
	
	@Test
	public void testGetLast() {
		list.addFirst(9);
		list.addFirst(3);
		list.addFirst(5);
		
		assertEquals(0, list.getLast().compareTo(9));
		
		list.addLast(7);
		assertEquals(0, list.getLast().compareTo(7));
		
		list.add(4, 6);
		assertEquals(0, list.getLast().compareTo(6));
		
		list.add(4, 1);
		assertEquals(0, list.getLast().compareTo(6));
	}

	@Test
	public void testGet() {
		list.addFirst(9);
		list.addFirst(3);
		list.addFirst(5);
		
		assertEquals(0, list.get(0).compareTo(5));
		assertEquals(0, list.get(1).compareTo(3));
		assertEquals(0, list.get(2).compareTo(9));
		
		list.addLast(7);
		assertEquals(0, list.get(3).compareTo(7));
		
		list.add(4, 6);
		assertEquals(0, list.get(4).compareTo(6));
		
		list.add(4, 1);
		assertEquals(0, list.get(0).compareTo(5));
		assertEquals(0, list.get(1).compareTo(3));
		assertEquals(0, list.get(2).compareTo(9));
		assertEquals(0, list.get(3).compareTo(7));
		assertEquals(0, list.get(4).compareTo(1));
		assertEquals(0, list.get(5).compareTo(6));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet_Excepton_EmptyList(){
		list.get(7);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet_Excepton_ClearedList(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(8);
		
		list.clear();
		
		list.get(7);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGet_Excepton_RemovedList(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(8);
		
		list.remove(2);
		list.remove(1);
		list.remove(0);
		
		list.get(7);
	}

	@Test
	public void testRemoveFirst() {
		list.addFirst(9);
		list.addFirst(3);
		list.addFirst(5);
		
		list.removeFirst();
		assertEquals(0, list.getFirst().compareTo(3));
		assertEquals(2, list.size());
		
		list.removeFirst();
		assertEquals(0, list.getFirst().compareTo(9));
		assertEquals(1, list.size());
		
		list.removeFirst();
		assertTrue(list.isEmpty());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst_Excepton_EmptyList(){
		list.removeFirst();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst_Exception_ClearedList(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(8);
		
		list.clear();
		
		list.removeFirst();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveFirst_Exception_RemovedList(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(8);
		
		list.remove(2);
		list.remove(1);
		list.remove(0);
		
		list.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		list.addLast(9);
		list.addLast(3);
		list.addLast(5);
		
		list.removeLast();
		assertEquals(0, list.getLast().compareTo(3));
		assertEquals(2, list.size());
		
		list.removeLast();
		assertEquals(0, list.getLast().compareTo(9));
		assertEquals(1, list.size());
		
		list.removeLast();
		assertTrue(list.isEmpty());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveLast_Excepton_EmptyList(){
		list.removeLast();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveLast_Exception_ClearedList(){
		list.addLast(7);
		list.addLast(2);
		list.addLast(8);
		
		list.clear();
		
		list.removeLast();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveLast_Exception_RemovedList(){
		list.addLast(7);
		list.addLast(2);
		list.addLast(8);
		
		list.remove(2);
		list.remove(1);
		list.remove(0);
		
		list.removeLast();
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove_Exception_EmptyList(){
		list.remove(7);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove_Exception_NegativeIndex(){
		list.addLast(9);
		list.addLast(3);
		list.addLast(5);
		
		list.remove(-1);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemove_Exception_LargeIndex(){
		list.addLast(9);
		list.addLast(3);
		list.addLast(5);
		
		list.remove(7);
	}
	
	@Test
	public void testRemove() {
		list.add(0, 5);
		list.add(1, 3);
		list.add(2, 9);
		list.add(1, 2);
		list.add(2, 7);
		list.add(0, 5);
		assertEquals(6, list.size());
		
		list.remove(1);
		assertEquals(0, list.get(0).compareTo(5));
		assertEquals(0, list.get(1).compareTo(2));
		assertEquals(0, list.get(2).compareTo(7));
		assertEquals(5, list.size());
		
		list.remove(0);
		assertEquals(0, list.get(0).compareTo(2));
		assertEquals(0, list.get(1).compareTo(7));
		assertEquals(0, list.get(2).compareTo(3));
		assertEquals(4, list.size());
		
		list.remove(3);
		assertEquals(0, list.get(0).compareTo(2));
		assertEquals(0, list.get(1).compareTo(7));
		assertEquals(0, list.get(2).compareTo(3));
		assertEquals(3, list.size());
		
		list.remove(1);
		assertEquals(0, list.get(0).compareTo(2));
		assertEquals(0, list.get(1).compareTo(3));
		assertEquals(2, list.size());
		
		list.remove(0);
		assertEquals(0, list.get(0).compareTo(3));
		assertEquals(1, list.size());
		
		list.remove(0);
		assertTrue(list.isEmpty());
	}
	@Test
	public void testIndexOf() {
		list.addLast(5);
		list.addLast(3);
		list.addLast(9);
		list.addLast(7);
		list.addLast(5);
		list.addLast(3);
		list.addLast(9);
		list.addLast(7);
		
		assertEquals(0, list.indexOf(5));
		assertEquals(1, list.indexOf(3));
		assertEquals(2, list.indexOf(9));
		assertEquals(3, list.indexOf(7));
		
		list.addFirst(7);
		assertEquals(0, list.indexOf(7));
		
		assertEquals(-1, list.indexOf(4));
	}

	@Test
	public void testLastIndexOf() {
		list.addLast(5);
		list.addLast(3);
		list.addLast(9);
		list.addLast(7);
		list.addLast(5);
		list.addLast(3);
		list.addLast(9);
		
		assertEquals(4, list.lastIndexOf(5));
		assertEquals(5, list.lastIndexOf(3));
		assertEquals(6, list.lastIndexOf(9));
		assertEquals(3, list.lastIndexOf(7));
		
		list.addLast(7);
		assertEquals(7, list.lastIndexOf(7));
		
		assertEquals(-1, list.lastIndexOf(4));
	}

	@Test
	public void testSize() {
		assertEquals(0, list.size());
		
		//Testing if adding to an empty list and all elements adjusts the size
		list.addFirst(7);
		assertEquals(1, list.size());
		list.removeFirst();
		assertEquals(0, list.size());
		list.addLast(4);
		assertEquals(1, list.size());
		list.removeLast();
		assertEquals(0, list.size());
		list.add(0, 5);
		assertEquals(1, list.size());
		list.remove(0);
		assertEquals(0, list.size());
		
		//Builds the list up a little bit
		list.addFirst(9);
		list.addLast(5);
		list.add(1, 3);
		assertEquals(3, list.size());
		
		//Testing if adding and removing to and from the ends of a list adjusts the size
		list.addFirst(7);
		assertEquals(4, list.size());
		list.removeFirst();
		assertEquals(3, list.size());
		list.addLast(4);
		assertEquals(4, list.size());
		list.removeLast();
		assertEquals(3, list.size());
		list.add(0, 5);
		assertEquals(4, list.size());
		list.remove(0);
		assertEquals(3, list.size());
		list.add(3, 6);
		assertEquals(4, list.size());
		list.remove(3);
		assertEquals(3, list.size());
		
		//Testing if adding and removing to and from the middle of the list adjusts the size
		list.remove(1);
		assertEquals(2, list.size());
		list.add(1, 8);
		assertEquals(3, list.size());
		
		//Testing if clear resets the size
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	public void testIsEmpty() {
		assertTrue(list.isEmpty());
		
		//Testing if adding and removing to and from an empty list adjusts the isEmpty return
		list.addFirst(7);
		assertFalse(list.isEmpty());
		list.removeFirst();
		assertTrue(list.isEmpty());
		list.addLast(4);
		assertFalse(list.isEmpty());
		list.removeLast();
		assertTrue(list.isEmpty());
		list.add(0, 5);
		assertFalse(list.isEmpty());
		list.remove(0);
		assertTrue(list.isEmpty());
		
		//Builds up the list a little bit
		list.addFirst(9);
		list.addLast(5);
		list.add(1, 3);
		assertFalse(list.isEmpty());
		
		//Makes sure that removing from a filled list doesn't change isEmpty to true
		list.addFirst(7);
		list.removeFirst();
		assertFalse(list.isEmpty());
		list.addLast(4);
		list.removeLast();
		assertFalse(list.isEmpty());
		list.add(0, 5);
		list.remove(0);
		assertFalse(list.isEmpty());
		list.add(3, 6);
		list.remove(3);
		assertFalse(list.isEmpty());
		
		list.remove(1);
		assertFalse(list.isEmpty());
		list.add(1, 8);
		
		//Testing that clear completely empties the list
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	public void testClear() {
		list.addFirst(5);
		list.addLast(3);
		list.add(1, 9);
		assertFalse(list.isEmpty());
		
		list.clear();
		assertTrue(list.isEmpty());
	}

	@Test
	public void testToArray() {
		list.addLast(5);
		list.addLast(3);
		list.addLast(9);
		list.addLast(7);
		list.addLast(5);
		list.addLast(3);
		list.addLast(9);
		
		Integer[] checker = new Integer[]{5, 3, 9, 7, 5, 3, 9};
		
		assertArrayEquals(checker, list.toArray());
	}
}