package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Clayton Hislop
 * uID: u0515744
 */
public class PriorityQueueTest {

	PriorityQueue<Integer> queue, dupQueue, empty;
	PriorityQueue<Character> charQueue;
	int[] intArray = new int[]{5, 31, 8, 22, 67, 17, 98, 33, 3};
	int[] duplicates = new int[]{5, 88, 31, 22, 67, 17, 8, 33, 4, 22, 17, 8};
	char[] charArray = new char[]{'t', 'r', 'e', 'z', 'o', 'c', 'i', 'f', 'w'}; 
	Random rng = new Random(123456);
	
	@Before
	public void setUp() throws Exception {
		empty = new PriorityQueue<Integer>();
		
		queue = new PriorityQueue<Integer>();
		for (int i = 0; i < intArray.length; i++) {
			queue.add(intArray[i]);
		}
		
		dupQueue = new PriorityQueue<Integer>();	
		for (int i = 0; i < duplicates.length; i++) {
			dupQueue.add(duplicates[i]);
		}
	}

	@Test
	public void addTest() {
		queue.add(52);
		assertEquals(10, queue.size());
	}
	
	@Test (expected = NullPointerException.class)
	public void addNull() {
		queue.add(null);		
	}
	
	@Test
	public void addNewMin() {
		queue.add(-1);
		assertTrue(queue.findMin() == -1);
	}
	
	@Test
	public void findMinTest() {
		assertEquals(3, (int)queue.findMin());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void findMinOfEmptyQueue() {
		empty.findMin();
	}
	
	@Test
	public void findMinAfterDeleteItems() {
		queue.deleteMin();
		queue.deleteMin();
		assertEquals(8, (int)queue.findMin());
	}
	
	@Test
	public void deleteMinTest() {
		int deletedVal = queue.deleteMin();
		assertTrue(deletedVal == 3);
	}
	
	@Test
	public void duplicatesTest() {
		for (int i = 0; i < duplicates.length; i++) {
			empty.add(duplicates[i]);
		}
		assertTrue(empty.findMin() == 4);
	}

	@Test
	public void dupRemoveMin() {
		assertTrue(dupQueue.deleteMin() == 4);
		
	}
	
	@Test
	public void charTest() {
		charQueue = new PriorityQueue<Character>();
		for (int i = 0; i < charArray.length; i++) {
			charQueue.add(charArray[i]);
		}
		charQueue.add('a');
		assertTrue(charQueue.findMin() == 'a');
	}
	
	@Test (expected = NoSuchElementException.class)
	public void deleteFromEmptyQueue() {
		empty.deleteMin();
	}
	
	@Test
	public void deleteOnlyItem() {
		empty.add(4);
		int del = empty.deleteMin();
		assertTrue(del == 4 && empty.size() == 0);
	}
	
	@Test
	public void addingABunchOfItems() {
		for (int i = 100; i > 0; i--) {
			empty.add(i);
		}
		assertTrue(empty.findMin() == 1 && empty.size() == 100);
	}
	
	@Test
	public void deleteMinAfterAddingNewMin() {
		queue.add(1);
		assertTrue(queue.deleteMin() == 1 && queue.size() == 9);
	}
	
}
