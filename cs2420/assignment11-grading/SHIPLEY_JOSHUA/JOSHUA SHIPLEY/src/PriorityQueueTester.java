package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTester {

	PriorityQueue<Integer> queue;
	
	@Before
	public void resetQueue(){
		queue = new PriorityQueue<Integer>();
	}
	
	@Test(expected = NoSuchElementException.class)
	public void FindMinTest_Exception(){
		queue.findMin();
	}
	
	@Test
	public void FindMinTest() {
		queue.add(5);
		assertEquals((Integer)5, queue.findMin());
		
		queue.add(8);
		assertEquals((Integer)5, queue.findMin());
		
		queue.add(3);
		assertEquals((Integer)3, queue.findMin());
		
		queue.add(7);
		assertEquals((Integer)3, queue.findMin());
		
		queue.add(9);
		assertEquals((Integer)3, queue.findMin());
		
		queue.add(4);
		assertEquals((Integer)3, queue.findMin());
		
		queue.add(2);
		assertEquals((Integer)2, queue.findMin());
		
		queue.add(1);
		assertEquals((Integer)1, queue.findMin());
		
		queue.add(6);
		assertEquals((Integer)1, queue.findMin());
		
		queue.add(0);
		assertEquals((Integer)0, queue.findMin());
		
		queue.add(1);
		assertEquals((Integer)0, queue.findMin());
		
		queue.add(3);
		assertEquals((Integer)0, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)1, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)1, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)2, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)3, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)3, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)4, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)5, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)6, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)7, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)8, queue.findMin());
		
		queue.deleteMin();
		assertEquals((Integer)9, queue.findMin());
	}

	@Test(expected = NoSuchElementException.class)
	public void deleteMinTest_Exception(){
		queue.deleteMin();
	}
	
	@Test
	public void deleteMinTest(){
		queue.add(5);
		queue.add(8);
		queue.add(3);
		queue.add(7);
		queue.add(9);
		queue.add(4);
		queue.add(2);
		queue.add(1);
		queue.add(6);
		queue.add(0);
		queue.add(1);
		queue.add(3);
		
		assertEquals((Integer)0, queue.deleteMin());
		assertEquals((Integer)1, queue.findMin());
		assertEquals(11, queue.size());
		
		assertEquals((Integer)1, queue.deleteMin());
		assertEquals((Integer)1, queue.findMin());
		assertEquals(10, queue.size());

		assertEquals((Integer)1, queue.deleteMin());
		assertEquals((Integer)2, queue.findMin());
		assertEquals(9, queue.size());

		assertEquals((Integer)2, queue.deleteMin());
		assertEquals((Integer)3, queue.findMin());
		assertEquals(8, queue.size());

		assertEquals((Integer)3, queue.deleteMin());
		assertEquals((Integer)3, queue.findMin());
		assertEquals(7, queue.size());

		assertEquals((Integer)3, queue.deleteMin());
		assertEquals((Integer)4, queue.findMin());
		assertEquals(6, queue.size());

		assertEquals((Integer)4, queue.deleteMin());
		assertEquals((Integer)5, queue.findMin());
		assertEquals(5, queue.size());

		assertEquals((Integer)5, queue.deleteMin());
		assertEquals((Integer)6, queue.findMin());
		assertEquals(4, queue.size());

		assertEquals((Integer)6, queue.deleteMin());
		assertEquals((Integer)7, queue.findMin());
		assertEquals(3, queue.size());

		assertEquals((Integer)7, queue.deleteMin());
		assertEquals((Integer)8, queue.findMin());
		assertEquals(2, queue.size());

		assertEquals((Integer)8, queue.deleteMin());
		assertEquals((Integer)9, queue.findMin());
		assertEquals(1, queue.size());
		
		assertEquals((Integer)9, queue.deleteMin());
		assertEquals(0, queue.size());
	}
	
	@Test
	public void addTest(){
		queue.add(5);
		assertEquals((Integer)5, queue.findMin());
		assertEquals(1, queue.size());
		
		queue.add(8);
		assertEquals((Integer)5, queue.findMin());
		assertEquals(2, queue.size());
		
		queue.add(3);
		assertEquals((Integer)3, queue.findMin());
		assertEquals(3, queue.size());
		
		queue.add(7);
		assertEquals((Integer)3, queue.findMin());
		assertEquals(4, queue.size());
		
		queue.add(9);
		assertEquals((Integer)3, queue.findMin());
		assertEquals(5, queue.size());
		
		queue.add(4);
		assertEquals((Integer)3, queue.findMin());
		assertEquals(6, queue.size());
		
		queue.add(2);
		assertEquals((Integer)2, queue.findMin());
		assertEquals(7, queue.size());
		
		queue.add(1);
		assertEquals((Integer)1, queue.findMin());
		assertEquals(8, queue.size());
		
		queue.add(6);
		assertEquals((Integer)1, queue.findMin());
		assertEquals(9, queue.size());
		
		queue.add(0);
		assertEquals((Integer)0, queue.findMin());
		assertEquals(10, queue.size());
		
		queue.add(1);
		assertEquals((Integer)0, queue.findMin());
		assertEquals(11, queue.size());
		
		queue.add(3);
		assertEquals((Integer)0, queue.findMin());
		assertEquals(12, queue.size());
	}
}
