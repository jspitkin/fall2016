package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class containing JUnit testing for Priority Queue
 * @author Daxton Wilson, u0264580
 */
public class PriorityQueueTesting {

	private PriorityQueue<Integer> pQ;

	@Before
	public void setUp() throws Exception {
		pQ = new PriorityQueue<Integer>();
		pQ.add(3);
		pQ.add(6);
		pQ.add(5);
		pQ.add(1);
		pQ.add(4);
		pQ.add(7);
		pQ.add(9);
		pQ.add(1);
		pQ.add(10);
		pQ.add(2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		pQ.add(13);
		pQ.deleteMin();
		assertTrue(pQ.findMin() == 1);
	}
	
	@Test(expected = NoSuchElementException.class)
	public void clearTest(){
		pQ.clear();
		assertTrue(pQ.findMin().equals(null));
	}
	
	@Test
	public void lotsOfDeletionTest() {
		pQ.generateDotFile("DeletedMinBefore.DOT");
		pQ.deleteMin();
		pQ.deleteMin();
		pQ.deleteMin();
		pQ.deleteMin();
		pQ.deleteMin();
		pQ.deleteMin();
		pQ.deleteMin();
		pQ.deleteMin();
		pQ.generateDotFile("DeletedMinAfter.DOT");
		assertTrue(pQ.findMin() == 9);
	}

}
