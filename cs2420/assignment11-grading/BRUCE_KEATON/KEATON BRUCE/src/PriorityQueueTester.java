package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * PriorityQueueTester provides JUnit testing of PriorityQueue
 * @author Andrew Keaton Bruce
 * u1006889
 * 11/15/2016
 *
 */
public class PriorityQueueTester {
	PriorityQueue<Integer> testQueue;

	@Before
	public void setUp() throws Exception {
		testQueue = new PriorityQueue<Integer>();
	}
	
	@Test
	public void addAndRemoveOneTest() {
		testQueue.add(1);
		testQueue.deleteMin();
	}

	@Test
	public void addTest() {
		testQueue.add(3);
		testQueue.add(22);
		testQueue.add(31);
		testQueue.add(100);
		testQueue.add(56);
		testQueue.add(43);
		testQueue.add(45);
		testQueue.add(6);
		testQueue.add(17);
		testQueue.add(92);	
		testQueue.add(4);
		testQueue.add(23);
		testQueue.add(32);
		testQueue.add(101);
		testQueue.add(57);
		testQueue.add(44);
		testQueue.add(46);
		testQueue.add(7);
		testQueue.add(18);
		testQueue.add(93);
		testQueue.generateDotFile("testQueue.dot");
	}
	
	@Test
	public void removeTest() {
		testQueue.add(3);
		testQueue.add(22);
		testQueue.add(31);
		testQueue.add(100);
		testQueue.add(56);
		testQueue.add(43);
		testQueue.add(45);
		testQueue.add(6);
		
		testQueue.generateDotFile("beforeRemove.dot");
		testQueue.deleteMin();
		testQueue.generateDotFile("afterRemove.dot");
	}
	
	@Test
	public void removeLongerTest() {
		testQueue.add(3);
		testQueue.add(22);
		testQueue.add(31);
		testQueue.add(100);
		testQueue.add(56);
		testQueue.add(43);
		testQueue.add(45);
		testQueue.add(6);
		testQueue.add(17);
		testQueue.add(92);	
		testQueue.add(4);
		testQueue.add(23);
		testQueue.add(32);
		testQueue.add(101);
		testQueue.add(57);
		testQueue.add(44);
		testQueue.add(46);
		testQueue.add(7);
		testQueue.add(18);
		testQueue.add(93);
		
		testQueue.generateDotFile("beforeRemove.dot");
		testQueue.deleteMin();
		testQueue.generateDotFile("afterRemove.dot");
	}
	
	@Test
	public void removeMoreTest() {
		testQueue.add(3);
		testQueue.add(22);
		testQueue.add(31);
		testQueue.add(100);
		testQueue.add(56);
		testQueue.add(43);
		testQueue.add(45);
		testQueue.add(6);
		testQueue.add(17);
		testQueue.add(92);	
		testQueue.add(4);
		testQueue.add(23);
		testQueue.add(32);
		testQueue.add(101);
		testQueue.add(57);
		testQueue.add(44);
		testQueue.add(46);
		testQueue.add(7);
		testQueue.add(18);
		testQueue.add(93);
		
		testQueue.generateDotFile("beforeRemove.dot");
		testQueue.deleteMin();
		//testQueue.generateDotFile("afterRemove1.dot");
		testQueue.deleteMin();
		//testQueue.generateDotFile("afterRemove2.dot");
		testQueue.deleteMin();
		//testQueue.generateDotFile("afterRemove3.dot");
		testQueue.deleteMin();
		//testQueue.generateDotFile("afterRemove4.dot");
		testQueue.deleteMin();
		testQueue.generateDotFile("afterRemove.dot");
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeNothingTest() {
		testQueue.deleteMin();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void findMinNothingTest() {
		testQueue.findMin();
	}
	
	@Test
	public void findMinTest() {
		testQueue.add(1);
		testQueue.add(6);
		testQueue.add(7);
		testQueue.add(3);
		testQueue.add(0);
		testQueue.add(2);
		
		assertEquals(0, testQueue.findMin().intValue());
	}
}
