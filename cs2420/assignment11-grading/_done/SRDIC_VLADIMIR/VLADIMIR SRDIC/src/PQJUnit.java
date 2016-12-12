package assignment11;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class PQJUnit {
	
	private PriorityQueue<Integer> pq;
	private PriorityQueue<Integer> pqC;
	
	@Before
	public void setup() {
		pq = new PriorityQueue<Integer>();
		pqC = new PriorityQueue<Integer>(new ReverseIntegerComparator());
	}

	@Test
	public void testAddThreeItems() {
		pq.add(8);
		assertEquals(pq.toArray()[0], 8);
		pq.add(3);
		assertEquals(pq.toArray()[0], 3);
		assertEquals(pq.toArray()[1], 8);
		pq.add(4);
		assertEquals(pq.toArray()[0], 3);
		assertEquals(pq.toArray()[1], 8);
		assertEquals(pq.toArray()[2], 4);
	}
	
	@Test
	public void testAddDuplicates() {
		Integer[] toCompare = {1,7,3,8,8,5};
		
		pq.add(8);
		pq.add(3);
		pq.add(5);
		pq.add(7);
		pq.add(8);
		pq.add(1);
		
		assertTrue(Arrays.equals(toCompare, pq.toArray()));
	}
	
	@Test
	public void testAddOverThreeItems() {
		Integer[] toCompare = {1,7,3,8,12,5};
		
		pq.add(8);
		pq.add(3);
		pq.add(5);
		pq.add(7);
		pq.add(12);
		pq.add(1);
		
		assertTrue(Arrays.equals(toCompare, pq.toArray()));
	}
	
	@Test(expected = NullPointerException.class)
	public void testAddNull() {
		pq.add(null);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFindMinEmptyQueue() {
		pq.findMin();
	}
	
	@Test
	public void testFindMinOneItem() {
		pq.add(10);
		assertTrue(pq.findMin() == 10);
	}
	
	@Test
	public void testFindMinPlacedInOrder() {
		pq.add(1);
		pq.add(3);
		pq.add(5);
		pq.add(7);
		pq.add(9);
		assertTrue(pq.findMin() == 1);
	}
	
	@Test
	public void testFindMinReverseOrder() {
		pq.add(9);
		pq.add(7);
		pq.add(5);
		pq.add(3);
		pq.add(1);
		assertTrue(pq.findMin() == 1);
	}
	
	@Test
	public void testFindMinNoOrder() {
		pq.add(5);
		pq.add(9);
		pq.add(3);
		pq.add(7);
		pq.add(1);
		assertTrue(pq.findMin() == 1);
	}
	
	@Test
	public void testDeleteMinPlacedInOrder() {
		pq.add(1);
		pq.add(3);
		pq.add(5);
		pq.add(7);
		pq.add(9);
		assertTrue(pq.deleteMin() == 1);
		assertTrue(pq.findMin() == 3);
		assertTrue(pq.size() == 4);
	}
	
	@Test
	public void testDeleteMinReverseOrder() {
		pq.add(9);
		pq.add(7);
		pq.add(5);
		pq.add(3);
		pq.add(1);
		assertTrue(pq.deleteMin() == 1);
		assertTrue(pq.findMin() == 3);
		assertTrue(pq.size() == 4);
	}
	
	@Test
	public void testDeleteMinNoOrder() {
		pq.add(5);
		pq.add(9);
		pq.add(3);
		pq.add(7);
		pq.add(1);
		assertTrue(pq.deleteMin() == 1);
		assertTrue(pq.findMin() == 3);
		assertTrue(pq.size() == 4);
	}
	
	@Test
	public void testFindMinPlacedInOrderComparator() {
		pqC.add(9);
		pqC.add(7);
		pqC.add(5);
		pqC.add(3);
		pqC.add(1);
		assertTrue(pqC.findMin() == 9);
	}
	
	@Test
	public void testFindMinReverseOrderComparator() {
		pqC.add(1);
		pqC.add(3);
		pqC.add(5);
		pqC.add(7);
		pqC.add(9);
		assertTrue(pqC.findMin() == 9);
	}
	
	@Test
	public void testFindMinNoOrderComparator() {
		pqC.add(5);
		pqC.add(9);
		pqC.add(3);
		pqC.add(7);
		pqC.add(1);
		assertTrue(pqC.findMin() == 9);
	}
	
	@Test
	public void testDeleteMinPlacedInOrderComparator() {
		pqC.add(9);
		pqC.add(7);
		pqC.add(5);
		pqC.add(3);
		pqC.add(1);
		assertTrue(pqC.deleteMin() == 9);
		assertTrue(pqC.findMin() == 7);
		assertTrue(pqC.size() == 4);
	}
	
	@Test
	public void testDeleteMinReverseOrderComparator() {
		pqC.add(1);
		pqC.add(3);
		pqC.add(5);
		pqC.add(7);
		pqC.add(9);
		assertTrue(pqC.deleteMin() == 9);
		assertTrue(pqC.findMin() == 7);
		assertTrue(pqC.size() == 4);
	}
	
	@Test
	public void testDeleteMinNoOrderComparator() {
		pqC.add(5);
		pqC.add(9);
		pqC.add(3);
		pqC.add(7);
		pqC.add(1);
		assertTrue(pqC.deleteMin() == 9);
		assertTrue(pqC.findMin() == 7);
		assertTrue(pqC.size() == 4);
	}
	
	@Test
	public void testAddThreeItemsComparator() {
		pqC.add(3);
		assertEquals(pqC.toArray()[0], 3);
		pqC.add(8);
		assertEquals(pqC.toArray()[0], 8);
		assertEquals(pqC.toArray()[1], 3);
		pqC.add(4);
		assertEquals(pqC.toArray()[0], 8);
		assertEquals(pqC.toArray()[1], 3);
		assertEquals(pqC.toArray()[2], 4);
	}
	
	@Test
	public void testAddOverThreeItemsComparator() {
		Integer[] toCompare = {12,8,5,3,7,1};
		
		pqC.add(8);
		pqC.add(3);
		pqC.add(5);
		pqC.add(7);
		pqC.add(12);
		pqC.add(1);
		
		assertTrue(Arrays.equals(toCompare, pqC.toArray()));
	}

	/**
	 * Used to sort Integers in reverse order, basically turns this PriorityQueue
	 * into a max-heap for testing purposes.
	 * @author Vladimir Srdic
	 *
	 */
	private class ReverseIntegerComparator implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return - o1.compareTo(o2);
		}
		
	}
}
