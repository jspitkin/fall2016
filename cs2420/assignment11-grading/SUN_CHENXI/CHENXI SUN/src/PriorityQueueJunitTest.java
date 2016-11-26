package assignment11;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
import static org.junit.Assert.*;

import org.junit.Test;

public class PriorityQueueJunitTest {
	PriorityQueue<Integer> abc = new PriorityQueue<Integer>();

	@Test
	public void addTest() throws Exception {
		abc.add(10);
		abc.add(20);
		assertEquals((Integer) 10, abc.findMin());
		abc.deleteMin();
		assertEquals((Integer) 20, abc.findMin());
		abc.add(5);
		assertEquals((Integer) 5, abc.findMin());
	}

	@Test
	public void findMinTest() throws Exception {
		abc.add(10);
		abc.add(20);
		assertEquals((Integer) 10, abc.findMin());
		abc.deleteMin();
		assertEquals((Integer) 20, abc.findMin());
		abc.add(5);
		assertEquals((Integer) 5, abc.findMin());
	}

	@Test
	public void deleteMinTest() throws Exception {
		abc.add(10);
		abc.add(20);
		abc.add(100);
		abc.add(200);
		assertEquals((Integer) 10, abc.deleteMin());
		assertEquals((Integer) 20, abc.deleteMin());
		assertEquals((Integer) 100, abc.deleteMin());
		assertEquals((Integer) 200, abc.deleteMin());
	}

}
