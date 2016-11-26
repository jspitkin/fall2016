package assignment11;

import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

public class JUnitTesting extends TestCase {

	Random rand = new Random();
	PriorityQueue<Integer> smallHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> unbalancedHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> mediumHeap = new PriorityQueue<Integer>();
	PriorityQueue<?> emptyHeap = new PriorityQueue<Object>();
	PriorityQueue<Integer> heapInOrder = new PriorityQueue<Integer>();
	PriorityQueue<Integer> WithDoubles = new PriorityQueue<Integer>();

	@Before
	protected void setUp() throws Exception {

		// Add values for testing
		smallHeap.add(0);
		smallHeap.add(1);
		smallHeap.add(2);
		unbalancedHeap.add(0);
		unbalancedHeap.add(1);
		unbalancedHeap.add(2);
		unbalancedHeap.add(3);
		unbalancedHeap.add(4);

		// Add 40 random values to 3 heaps.
		for (int i = 0; i < 30; i++) {
			int temp = rand.nextInt(100);
			mediumHeap.add(temp);
		}

		// Add 40 random values
		for (int i = 0; i < 40; i++) {
			int temp = 42;
			WithDoubles.add(temp);
		}
		mediumHeap.add(-1);

		// add sequential values to ordered
		for (int i = 1; i <= 100; i++)
			heapInOrder.add(i);

	}

	@Test
	public void testFindMin() {

		// AssertTrue tests.
		assertTrue(-1 <= mediumHeap.findMin());
		assertTrue(0 <= smallHeap.findMin());
		assertTrue(0 <= unbalancedHeap.findMin());
		assertTrue(42 <= WithDoubles.findMin());
	}

	@Test
	public void testDeleteMin() {

		// AssertTrue tests
		assertEquals(31, mediumHeap.size());
		assertEquals(3, smallHeap.size());
		assertEquals(5, unbalancedHeap.size());
		mediumHeap.generateDotFile("MediumQueueBeforeDelete.dot");
		unbalancedHeap.generateDotFile("unbalancedQueueBefore.dot");

		// Operation
		assertTrue(-1 <= mediumHeap.deleteMin());
		assertTrue(0 <= smallHeap.deleteMin());
		assertTrue(0 <= unbalancedHeap.deleteMin());
		unbalancedHeap.generateDotFile("unbalancedQueueAfter.dot");

		// Post-Conditions
		assertEquals(30, mediumHeap.size());
		assertEquals(2, smallHeap.size());
		assertEquals(4, unbalancedHeap.size());
		mediumHeap.generateDotFile("MediumQueueAfter.dot");

		// Loop to empty on medium
		for (int i = 30; i > 0; i--) {
			mediumHeap.deleteMin();
			assertEquals(i - 1, mediumHeap.size());
		}

		// Null test.
		Throwable error = null;
		try {
			emptyHeap.deleteMin();
			mediumHeap.deleteMin();
			heapInOrder.deleteMin();
		} catch (Throwable e) {
			error = e;
		}
		assertNotNull(error);
		assertSame(NoSuchElementException.class, error.getClass());
	}

	@Test
	public void testAdd() {
		assertTrue(-1 <= mediumHeap.findMin());
		mediumHeap.add(-100);
		assertTrue(-100 <= mediumHeap.findMin());
		mediumHeap.clear();
		assertEquals(0, mediumHeap.size());
		rand.setSeed(7);

		// Test Add with grow method.
		for (int i = 1; i < 10000; i++) {
			mediumHeap.add(i * -1);
			assertTrue(i * -1 <= mediumHeap.findMin());
			assertEquals(i, mediumHeap.size());
		}

	}

}
