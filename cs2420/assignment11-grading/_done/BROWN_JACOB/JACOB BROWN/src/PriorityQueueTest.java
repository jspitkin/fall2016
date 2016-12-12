package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * 
 * A test class for the Priority Queue class.
 * 
 * @author Jacob Brown (u0583647)
 *
 */
public class PriorityQueueTest {
	
	@Test
	public void findMinMethod_Test() {
		
		PriorityQueue<Integer> integerQueue = new PriorityQueue<Integer>();
		
		integerQueue.add(31);
		integerQueue.add(-300);
		integerQueue.add(43);
		integerQueue.add(45);
		integerQueue.add(56);
		integerQueue.add(92);
		integerQueue.add(3);
		integerQueue.add(6);
		
		integerQueue.deleteMin();
		integerQueue.deleteMin();
		
		assertTrue(integerQueue.findMin().equals(6));
		
	}

	@Test
	public void addMethod_Test() {

		PriorityQueue<Integer> integerQueue = new PriorityQueue<Integer>();
		
		integerQueue.add(31);
		integerQueue.add(-300);
		
		assertTrue(integerQueue.findMin().equals(-300));
		
		integerQueue.add(43);
		integerQueue.add(45);
		integerQueue.add(56);
		integerQueue.add(92);
		integerQueue.add(-5000);
		
		assertTrue(integerQueue.findMin().equals(-5000));
		
		integerQueue.deleteMin();
		integerQueue.deleteMin();
		integerQueue.deleteMin();
		
		integerQueue.add(6);
		
		assertTrue(integerQueue.findMin().equals(6));
		
	}
	
	/**
	 * Method to test deleteMin on a small, normal, large, and string heap
	 */
	@Test
	public void deleteMinMethod_Test() {
		
		PriorityQueue<Integer> integerQueue = new PriorityQueue<Integer>();
		
		integerQueue.add(31);
		integerQueue.add(-300);
		
		assertTrue(integerQueue.findMin().equals(-300));
		
		integerQueue.add(43);
		integerQueue.add(45);
		
		integerQueue.deleteMin();
		integerQueue.deleteMin();
		integerQueue.deleteMin();
		
		integerQueue.add(56);
		integerQueue.add(92);
		integerQueue.add(-5000);
		
		assertTrue(integerQueue.findMin().equals(-5000));
		
		integerQueue.add(6);
		
		integerQueue.deleteMin();
		integerQueue.deleteMin();
		integerQueue.deleteMin();
		
		
		assertTrue(integerQueue.findMin().equals(56));
		
	}
	
	/**
	 * Method to test the clear function on a small, normal, large, and string heap.
	 */
	@Test (expected = NoSuchElementException.class)  
	public void clearMethod_Test() {
		
		PriorityQueue<Integer> integerQueue = new PriorityQueue<Integer>();
		
		integerQueue.add(31);
		integerQueue.add(-300);
		integerQueue.add(43);
		integerQueue.add(45);
		integerQueue.add(56);
		integerQueue.add(92);
		integerQueue.add(3);
		integerQueue.add(6);
		
		integerQueue.clear();
		
		integerQueue.deleteMin();
		
	}

}
