package assignment11;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

/**
 * This test used to test methods in PriorityQueue
 * @author Yixiong Qin
 *
 */
public class PriorityQueueTest {
	
	private ArrayList<Integer> smallSize;
	private PriorityQueue<Integer> test;
	private ArrayList<Integer> bigSize;
	private PriorityQueue<Integer> test1;
	private TreeSet<Integer> helperTree;
	private PriorityQueue<Integer> empty;

	@Before
	public void setUp() throws Exception {
		
		smallSize = generateArray(10);
		smallSize.add(-1);
		test = new PriorityQueue<Integer>();
	
		for (int i = 0; i < smallSize.size(); i++) {
			test.add(smallSize.get(i));
		}
		bigSize = this.generateArray(1000);
		helperTree = new TreeSet<Integer>();
		helperTree.addAll(bigSize);
		test1 = new PriorityQueue<Integer>(new comparator());
		for (int temp : bigSize) {
			test1.add(temp);
		}
		empty = new PriorityQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * This tests the "findMin" method
	 */
	@Test
	public void testFindMin() {
		assertEquals(-1, (int) test.findMin());
		assertEquals((int) helperTree.last(), (int) test1.findMin());
	}

	/**
	 * This tests the "NoSuchElementException"
	 */
	@Test(expected = NoSuchElementException.class)
	public void testException() {
		empty.deleteMin();
	}

	/**
	 * This tests the "deleteMin" method
	 */
	@Test
	public void testDeleteMin() {
		assertEquals(-1, (int) test.deleteMin());
		assertEquals(10, test.size());
		for (int i = 0; i < 10; i++) {
			test.deleteMin();
		}
		assertEquals(0, test.size());
		assertEquals((int) helperTree.last(), (int) test1.deleteMin());
		assertEquals(999, test1.size());
	}

	/**
	 * This tests the "add" method
	 */
	@Test
	public void testAdd() {
		test.add(12);
		test.add(10001);
		test.add(10000);
		assertEquals(14, test.size());
		test1.add(0);
		test1.add(6);
		assertEquals(1002, test1.size());
	}

	/**
	 * Helper method in order to generate an array and add random numbers into
	 * the array
	 * 
	 * @param size
	 * @return
	 */
	public ArrayList<Integer> generateArray(int size) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		Random rand = new Random();
		while (temp.size() < size) {
			int num = rand.nextInt(size);
			temp.add(num);
		}
		return temp;
	}
}
