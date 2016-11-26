package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Savannah Simmons, u1086770
 *
 */
public class PriorityQueueTester {
	
	PriorityQueue<Integer> emptyIntQueue, oneIntQueue, manyIntQueue;
	PriorityQueue<String> emptyStringQueue, oneStringQueue, manyStringQueue;
	
	@Before
	public void setUp() throws Exception {
		emptyIntQueue = new PriorityQueue<Integer>();
		
		oneIntQueue = new PriorityQueue<Integer>();
		oneIntQueue.add(1);
		
		manyIntQueue = new PriorityQueue<Integer>();
		manyIntQueue.add(8);
		manyIntQueue.add(-3);
		manyIntQueue.add(-2);
		manyIntQueue.add(10);
		manyIntQueue.add(0);
		manyIntQueue.add(8);
		manyIntQueue.add(-100);
		manyIntQueue.add(100);
		manyIntQueue.add(4);
		manyIntQueue.add(4);
		manyIntQueue.add(4);
		
		emptyStringQueue = new PriorityQueue<String>(new StringLengthComparator());
		
		oneStringQueue = new PriorityQueue<String>(new StringLengthComparator());
		oneStringQueue.add("hi");
		
		manyStringQueue = new PriorityQueue<String>(new StringLengthComparator());
		manyStringQueue.add("hello");
		manyStringQueue.add("a");
		manyStringQueue.add("abcdefghijklmnop");
		manyStringQueue.add("hola");
		manyStringQueue.add("bc");
		manyStringQueue.add("abc");
		manyStringQueue.add("hello");
		manyStringQueue.add("oy");
		manyStringQueue.add("qwerty");
		manyStringQueue.add("yo");
		manyStringQueue.add("hello");
		
	}
	
	//Add is tested through the combination of the setup and the following tests

	@Test
	public void sizeEmpty(){
		assertEquals(0, emptyIntQueue.size());
	}
	
	@Test
	public void sizeBasic(){
		assertEquals(11, manyStringQueue.size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void findMinEmptyComparable() {
		emptyIntQueue.findMin();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void findMinEmptyComparator(){
		emptyStringQueue.findMin();
	}
	
	@Test
	public void findMinOneElementComparable(){
		assertEquals((Integer) 1, oneIntQueue.findMin());
	}
	
	@Test
	public void findMinOneElementComparator(){
		assertEquals("hi", oneStringQueue.findMin());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void deleteMinEmptyComparable() {
		emptyIntQueue.deleteMin();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void deleteMinEmptyComparator(){
		emptyStringQueue.deleteMin();
	}
	
	@Test
	public void deleteMinOneElementComparable(){
		assertEquals((Integer) 1, oneIntQueue.deleteMin());
		assertEquals(0, oneIntQueue.size());
	}
	
	@Test
	public void deleteMinOneElementComparator(){
		assertEquals("hi", oneStringQueue.deleteMin());
		assertEquals(0, oneStringQueue.size());
	}
	
	@Test
	public void deleteMinBasicComparable(){
		assertEquals((Integer)(-100), manyIntQueue.deleteMin());
		assertEquals((Integer) (-3), manyIntQueue.findMin());
	}
	
	@Test
	public void deleteMinBasicComparator(){
		assertEquals("a", manyStringQueue.deleteMin());
		assertEquals(10, manyStringQueue.size());
	}

}
