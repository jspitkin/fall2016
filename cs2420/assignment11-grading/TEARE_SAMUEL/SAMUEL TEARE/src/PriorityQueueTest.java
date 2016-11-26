package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A class for testing the PriorityQueue class.
 * 
 * @author Samuel Teare | UID: u0663592
 *
 */
public class PriorityQueueTest {

	private PriorityQueue<Integer> emptyPQ, smallPQ;
	
	@Before
	public void setUp() throws Exception {
		emptyPQ = new PriorityQueue<Integer>();
		
		smallPQ = new PriorityQueue<Integer>();
		smallPQ.add(10);
		smallPQ.add(5);
		smallPQ.add(3);
		smallPQ.add(4);
		smallPQ.add(6);
		smallPQ.add(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void emptyQueueTest() {
		assertTrue(emptyPQ.size() == 0);
	}
	
	@Test
	public void addToQueueTest() {
		emptyPQ.add(1);
		assertTrue(emptyPQ.findMin() == 1);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void findMinEmptyPQThrowExceptionTest() {
		emptyPQ.findMin();
	}
	
	@Test
	public void findMinSmallPQTest() {
		assertTrue(smallPQ.findMin() == 2);
	}
	
	@Test
	public void deleteMinSmallPQTest() {
		int removeVal = smallPQ.deleteMin();
		assertEquals(2, removeVal);
		assertTrue(smallPQ.findMin() ==3);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void deleteMinEmptyPQThrowExceptionTest() {
		emptyPQ.deleteMin();
	}
	
	@Test
	public void clearSmallPQTest() {
		smallPQ.clear();
		assertTrue(smallPQ.size() == 0);
	}

}
