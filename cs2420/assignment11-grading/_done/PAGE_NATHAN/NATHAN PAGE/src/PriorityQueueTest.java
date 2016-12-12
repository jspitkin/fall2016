package assignment11;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PriorityQueue data structure in assignment 11.
 * 
 * @author Nathan Page
 * @uid u0741592
 */
public class PriorityQueueTest {

	private PriorityQueue<Integer> queue1;
	private PriorityQueue<String> queue2;

	@Before
	public void setUp() throws Exception {
		queue1 = new PriorityQueue<Integer>();
		queue2 = new PriorityQueue<String>();
	}

	@After
	public void tearDown() throws Exception {
		queue1.clear();
		queue2.clear();
	}

	@Test
	public void testSize() {
		assertEquals(queue1.size(), 0);
		queue1.add(1);
		assertEquals(queue1.size(), 1);
	}

	@Test
	public void testClear() {
		queue1.add(0);
		queue1.add(1);
		queue1.add(2);
		queue1.add(3);
		queue1.add(4);
		assertEquals(queue1.size(), 5);
		queue1.clear();
		assertEquals(queue1.size(), 0);
	}

	@Test
	public void testFindMin() {
		queue1.add(1);
		queue1.add(3);
		queue1.add(2);
		queue1.add(5);
		queue1.add(9);
		queue1.add(6);
		queue1.add(0);
		assertTrue(queue1.findMin() == 0);
	}

	@Test
	public void testDeleteMin() {
		queue1.add(1);
		queue1.add(3);
		queue1.add(2);
		queue1.add(5);
		queue1.add(9);
		queue1.add(6);
		queue1.add(0);
		assertEquals(queue1.size(), 7);
		queue1.deleteMin();
		assertEquals(queue1.size(), 6);
		assertTrue(queue1.findMin() == 1);
	}

	@Test
	public void testAdd() {
		queue2.add("a");
		queue2.add("b");
		queue2.add("c");
		queue2.add("d");
		queue2.add("e");
		queue2.add("f");
		assertEquals(queue2.size(), 6);
		assertTrue(queue2.findMin().equals("a"));
	}
}
