package assignment11;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
	PriorityQueue<Integer> list = new PriorityQueue<Integer>();

	@Before
	public void setUp() throws Exception {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.add(8);
		list.add(9);
		list.add(10);
	}

	@Test
	public void testSize() {
		assertEquals(10, list.size());
	}

	@Test
	public void testClear() {
		list.clear();
		assertEquals(0, list.size());
	}

	@Test
	public void testFindMin() {
		// assertEquals(0, list.findMin());
	}

	@Test
	public void testDeleteMin() {
		list.deleteMin();
		// assertEquals(1, list.);
	}

	@Test
	public void testAdd() {
		list.add(11);
		assertEquals(11, list.size());
	}

	@Test
	public void testToArray() {
		list.deleteMin();
		list.deleteMin();
		list.clear();

		list.generateDotFile("PriorityQueueBinaryHeap.dot");
	}

}
