package assignment11;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
	PriorityQueue<String> stringPrioQueue, emptyPrioQueue;
	
	@Before
	public void setUp() throws Exception {
		emptyPrioQueue = new PriorityQueue<>();
		
		stringPrioQueue = new  PriorityQueue<>();
		stringPrioQueue.add("pie");
		stringPrioQueue.add("plum");
		stringPrioQueue.add("turkey");
		stringPrioQueue.add("timberlands");
		stringPrioQueue.add("vin diesel");
		stringPrioQueue.add("karl marx");
		stringPrioQueue.add("anana");
	}

	@Test
	public void testFindMin() {
		assertEquals("anana", stringPrioQueue.findMin());
	}

	@Test
	public void testDeleteMin() {
		assertEquals("anana", stringPrioQueue.deleteMin());
		assertEquals("karl marx", stringPrioQueue.deleteMin());
		assertEquals("pie", stringPrioQueue.deleteMin());
		assertEquals("plum", stringPrioQueue.deleteMin());
		assertEquals("timberlands", stringPrioQueue.deleteMin());
		assertEquals("turkey", stringPrioQueue.deleteMin());
		assertEquals("vin diesel", stringPrioQueue.deleteMin());
	}

	@Test
	public void testAdd() {
		stringPrioQueue.add("aardvark");
		assertEquals("aardvark", stringPrioQueue.findMin());
	}

	@Test
	public void testPriorityQueueWithComparator() {
		PriorityQueue<String> stringPrioQueueWithComparator = new PriorityQueue<>(Comparator.naturalOrder());
		
		stringPrioQueueWithComparator.add("pie");
		stringPrioQueueWithComparator.add("plum");
		stringPrioQueueWithComparator.add("turkey");
		stringPrioQueueWithComparator.add("timberlands");
		stringPrioQueueWithComparator.add("vin diesel");
		stringPrioQueueWithComparator.add("karl marx");
		stringPrioQueueWithComparator.add("anana");
		
		assertEquals("anana", stringPrioQueueWithComparator.deleteMin());
		assertEquals("karl marx", stringPrioQueueWithComparator.deleteMin());
		assertEquals("pie", stringPrioQueueWithComparator.findMin());
		
		stringPrioQueueWithComparator.add("aardvark");
		assertEquals("aardvark", stringPrioQueueWithComparator.findMin());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testFindMinOnEmptyPriorityQueue() {
		emptyPrioQueue.findMin();
	}

	@Test(expected = NoSuchElementException.class)
	public void testDeleteMinOnEmptyPriorityQueue() {
		emptyPrioQueue.deleteMin();
	}
	
	@Test
	public void testAddExpansion() {
		stringPrioQueue.add("a");
		stringPrioQueue.add("b");
		stringPrioQueue.add("c");
		stringPrioQueue.add("c");
		stringPrioQueue.add("c");
		stringPrioQueue.add("c");
		stringPrioQueue.add("d");
		stringPrioQueue.add("e");
		assertEquals("a", stringPrioQueue.findMin());
	}
}
