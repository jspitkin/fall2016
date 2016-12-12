package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

/**
 * PriorityQueueTest - runs a series of tests for PriorityQueue.  
 * @author Kyle Price
 * 11/15/2016
 */
public class PriorityQueueTest {
	PriorityQueue<String> q;
	PriorityQueue<Integer> numbers;
	PriorityQueue<Node> nodes;
	NodeComparator comp;
	
	Node node1 = new Node(1);
	Node node0 = new Node(0);
	Node node7 = new Node(7);
	Node node2 = new Node(2);

	@Before
	public void setUp() throws Exception {
		q = new PriorityQueue<String>();
		numbers = new PriorityQueue<Integer>();
		comp = new NodeComparator();
		nodes = new PriorityQueue<Node>(comp);
	}
	@Test (expected = NoSuchElementException.class)
	public void testClear() {
		q.add("you");
		q.add("suck");
		q.clear();
		assertTrue(q.size() == 0 && q.findMin() == "suck");
	}

	@Test
	public void testAddBasic() {
		q.add("you");
		q.add("suck");
		assertTrue(q.size() == 2 && q.findMin() == "suck");
	}

	@Test
	public void testAddDuplicates() {
		q.add("you");
		q.add("you");
		q.add("you");
		q.add("you");
		assertTrue(q.size() == 4 && q.findMin() == "you");
	}

	@Test
	public void testAddIntegers() {
		numbers.add(1);
		numbers.add(4);
		numbers.add(6);
		numbers.add(0);
		assertTrue(numbers.size() == 4 && numbers.findMin() == 0);
	}

	@Test
	public void testAddGenerics() {
		nodes.add(node1);
		nodes.add(node7);
		nodes.add(node0);
		nodes.add(node2);
		assertTrue(nodes.size() == 4 && nodes.findMin().getValue() == 0);
	}

	@Test
	public void testRemoveBasic() {
		q.add("sup");
		q.add("how");
		q.add("are");
		q.add("you");
		q.deleteMin();
		q.deleteMin();
		assertTrue(q.size() == 2 && q.findMin() == "sup");
	}

	@Test
	public void testRemoveDuplicates() {
		q.add("sup");
		q.add("sup");
		q.add("hey");
		q.add("sup");
		q.add("sup");
		q.deleteMin();
		q.deleteMin();
		assertTrue(q.size() == 3 && q.findMin() == "sup");
	}

	@Test
	public void testRemoveInts() {
		numbers.add(1);
		numbers.add(0);
		numbers.add(65);
		numbers.add(5);
		numbers.deleteMin();
		numbers.deleteMin();
		assertTrue(numbers.size() == 2 && numbers.findMin() == 5);
	}

	@Test
	public void testRemoveGeneric() {
		nodes.add(node7);
		nodes.add(node0);
		nodes.add(node1);
		nodes.add(node2);
		nodes.add(node0);
		nodes.deleteMin();
		nodes.deleteMin();
	}

	@Test
	public void testRemoveSeveral() {
		Random rand = new Random(4);
		for (int i = 0; i < 250; i++) {
			numbers.add(rand.nextInt(150));
		}
		for (int i = 0; i < 249; i++) {
			numbers.deleteMin();
		}
		assertTrue(numbers.size() == 1 && numbers.findMin() == 149);
	}
	@Test
	public void testToArray() {
		numbers.add(4);
		numbers.add(9);
		numbers.add(6);
		numbers.add(4);
		numbers.add(8);
		numbers.add(7);
		numbers.add(0);
		Integer [] arr = {0, 4, 4, 9, 8, 7, 6};
		assertArrayEquals(arr, numbers.toArray());
	}
	@Test
	public void testToArrayRemove() {
		numbers.add(0);
		numbers.add(0);
		numbers.add(4);
		numbers.add(4);
		numbers.add(234);
		numbers.add(5);
		numbers.add(0);
		numbers.deleteMin();
		numbers.deleteMin();
		numbers.deleteMin();
		numbers.deleteMin();
		numbers.deleteMin();
		numbers.deleteMin();
		numbers.generateDotFile("numbers.dot");
		assertTrue(numbers.deleteMin() == 234);
	}
}
