package assignment11;

import static org.junit.Assert.*;

/**
 * Test suite for min heap
 * @author - Lindsey Loveland
 */

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTest {
	
	PriorityQueue<Integer> normalHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> smallHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> largeHeap = new PriorityQueue<Integer>();
	PriorityQueue<Integer> emptyHeap = new PriorityQueue<Integer>();
	PriorityQueue<String> stringHeap = new PriorityQueue<String>();
	
	@Before
	public void setup(){
		
		//normal heap
		normalHeap.add(15);
		normalHeap.add(14);
		normalHeap.add(10);
		normalHeap.add(2);
		normalHeap.add(6);
		normalHeap.add(90);
		normalHeap.add(39);
		normalHeap.add(-83);
		normalHeap.add(45);
		normalHeap.add(65);
		normalHeap.add(0);
		normalHeap.add(-45);
		normalHeap.add(-23);
		normalHeap.add(90);
		normalHeap.add(4357);
		
		//small heap
		smallHeap.add(1);
		smallHeap.add(2);
		smallHeap.add(3);
		smallHeap.add(9);
		smallHeap.add(8);

		
		//large heap
		largeHeap.add(54);
		largeHeap.add(3);
		largeHeap.add(-34);
		largeHeap.add(12);
		largeHeap.add(67);
		largeHeap.add(45);
		largeHeap.add(875);
		largeHeap.add(0);
		largeHeap.add(-223);
		largeHeap.add(74);
		largeHeap.add(23);
		largeHeap.add(643);
		largeHeap.add(13);
		largeHeap.add(24);
		largeHeap.add(2345);
		largeHeap.add(421);
		largeHeap.add(34);
		largeHeap.add(-456);
		largeHeap.add(36);
		largeHeap.add(-3456);
		
		//string heap
		stringHeap.add("a");
		stringHeap.add("z");
		stringHeap.add("d");
		stringHeap.add("e");
		stringHeap.add("h");
		stringHeap.add("d");
		stringHeap.add("p");
		stringHeap.add("i");
		stringHeap.add("n");
		stringHeap.add("q");
		
	}
	
	/**
	 * Method to test the findMin method on a small, normal, large, and string heap
	 */
	@Test
	public void findMinTest() {
		
		//stringHeap findMin
		String result = stringHeap.findMin();
		assertEquals("a", result);
		stringHeap.deleteMin();
		result = stringHeap.findMin();
		assertEquals("d", result);
		
		//smallHeap findMin
		int result2 = smallHeap.findMin();
		assertEquals(1, result2);
		smallHeap.deleteMin();
		result2 = smallHeap.findMin();
		assertEquals(2, result2);		
		
		//normalHeap findMin
		int result3 = normalHeap.findMin();
		assertEquals(-83, result3);
		normalHeap.deleteMin();
		result3 = normalHeap.findMin();
		assertEquals(-45, result3);
		
		//largeHeap findMin
		int result4 = largeHeap.findMin();
		assertEquals(-3456, result4);
		largeHeap.deleteMin();
		result4 = largeHeap.findMin();
		assertEquals(-456, result4);
		
	}
	/**
	 * Method to test the add function on a small, normal, large, and string heap
	 */
	@Test
	public void addTest(){
		
		//string heap add
		assertEquals(stringHeap.size(), 10);
		stringHeap.add("a");
		assertEquals(stringHeap.size(), 11);
		stringHeap.add("v");
		stringHeap.add("q");
		assertEquals(stringHeap.size(), 13);
		stringHeap.add("e");
		stringHeap.add("p");
		stringHeap.add("c");
		assertEquals(stringHeap.size(), 16);
		
		//smallHeap add
		assertEquals(smallHeap.size(), 5);
		smallHeap.add(10);
		assertEquals(smallHeap.size(), 6);
		for(int i = 0; i < 40; i++){
			smallHeap.add(i);
		}
		assertEquals(smallHeap.size(), 46);
		
		//normalHeap add
		assertEquals(normalHeap.size(), 15);
		normalHeap.add(18298);
		assertEquals(normalHeap.size(), 16);
		for(int i = 0; i < 40; i++){
			normalHeap.add(i);
		}
		assertEquals(normalHeap.size(), 56);
		
		//largeHeap add
		assertEquals(largeHeap.size(), 20);
		largeHeap.add(-87);
		assertEquals(largeHeap.size(), 21);
		for(int i = 0; i < 40; i++){
			largeHeap.add(i);
		}
		assertEquals(largeHeap.size(), 61);
	}
	
	/**
	 * Method to test deleteMin on a small, normal, large, and string heap
	 */
	@Test
	public void deleteMin(){
		
		//stringHeap deleteMin
		String min = stringHeap.findMin();
		assertEquals("a", min);
		stringHeap.deleteMin();
		stringHeap.deleteMin();
		stringHeap.deleteMin();
		min = stringHeap.findMin();
		assertEquals("e", min);
		
		//smallHeap deleteMin
		int min2 = smallHeap.findMin();
		assertEquals(1, min2);
		smallHeap.deleteMin();
		smallHeap.deleteMin();
		smallHeap.deleteMin();
		min2 = smallHeap.findMin();
		assertEquals(8, min2);
		
		//normalHeap deleteMin
		int min3 = normalHeap.findMin();
		assertEquals(-83, min3);
		normalHeap.deleteMin();
		normalHeap.deleteMin();
		min3 = normalHeap.findMin();
		assertEquals(-23, min3);
		
		//largeHeap deleteMIn
		int min4 = largeHeap.findMin();
		assertEquals(-3456, min4);
		largeHeap.deleteMin();
		largeHeap.deleteMin();
		largeHeap.deleteMin();
		min4 = largeHeap.findMin();
		assertEquals(-34, min4);
		
		
	}
	
	/**
	 * Method to test the clear function on a small, normal, large, and string heap.
	 */
	@Test
	public void clear(){
		
		//stringHeap clear
		assertFalse(stringHeap.size() == 0);
		stringHeap.clear();
		assertTrue(stringHeap.size() == 0);
		
		//smallHeap clear
		assertFalse(smallHeap.size() == 0);
		smallHeap.clear();
		assertTrue(smallHeap.size() == 0);
		
		//normalHeap clear
		assertFalse(normalHeap.size() == 0);
		normalHeap.clear();
		assertTrue(normalHeap.size() == 0);
		
		//largeHeap clear
		assertFalse(largeHeap.size() == 0);
		largeHeap.clear();
		assertTrue(largeHeap.size() == 0);
		
		
	}

}
