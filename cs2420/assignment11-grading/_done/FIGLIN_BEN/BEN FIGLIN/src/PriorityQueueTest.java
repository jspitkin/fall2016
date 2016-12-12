package assignment11;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Used for testing the various methods of the PriorityQueue class.
 * 
 * @author Ben Figlin (u1115949)
 *
 */
public class PriorityQueueTest {
	int[] intArray = {6,3,8,26,12,32,0,11,9,78,43,24,-5,-8,17,65,23,35,42,-1,51,77,22,14,75,98};
	String[] stringArray = {"hello","text","word","_","*","1","5","a very very super extra long string","","a string","A","b","c","one","two","aa","D","e"};
	PriorityQueue<Integer> intQueue;
	PriorityQueue<String> stringQueue;
	
	// compare strings by length
	Comparator<String> comparator = new Comparator<String>() {
	    public int compare(String obj1, String obj2) {
	        return obj1.length() - obj2.length();
	    }
	};
	
	@Before
	public void setUp() throws Exception {
		intQueue = new PriorityQueue<Integer>();
		stringQueue = new PriorityQueue<String>(comparator);
	}

	/**
	 * add many integer type items to the queue, check for correct ordering
	 */
	@Test
	public void addIntegers() {
		for (int item : intArray) {
			intQueue.add(item);
		}
		
		Integer[] array = Arrays.copyOf(intQueue.toArray(), intQueue.toArray().length, Integer[].class);

		assertEquals(intArray.length, array.length);
		
		for (int i=1; i < array.length; i++) {
			assertTrue(array[(i-1)/2].intValue() <= array[i].intValue());
		}
		
		intQueue.generateDotFile("integers.dot");
	}
	
	/**
	 * add many string type items to the queue, check for correct ordering
	 */
	@Test
	public void addStrings() {
		for (String item : stringArray) {
			stringQueue.add(item);
		}
		
		String[] array = Arrays.copyOf(stringQueue.toArray(), stringQueue.toArray().length, String[].class);

		assertEquals(stringArray.length, array.length);
		
		for (int i=1; i < array.length; i++) {
			assertTrue(array[(i-1)/2].length() <= array[i].length());
		}
		
		stringQueue.generateDotFile("strings.dot");
	}
	
	/**
	 * find minimum item in the queue
	 */
	@Test
	public void findMinIntegers() {
		for (int item : intArray) {
			intQueue.add(item);
		}
		
		assertEquals(-8,intQueue.findMin().intValue());
		
	}
	
	/**
	 * find minimum item in the queue
	 */
	@Test
	public void findMinStrings() {
		for (String item : stringArray) {
			stringQueue.add(item);
		}
		
		assertEquals(0,stringQueue.findMin().length());
	}
	
	/**
	 * find minimum item in an empty queue
	 */
	@Test(expected = NoSuchElementException.class)
	public void findMinIntegersEmpty() {
		intQueue.findMin();
	}
	
	/**
	 * find minimum item in an empty queue
	 */
	@Test(expected = NoSuchElementException.class)
	public void findMinStringsEmpty() {
		stringQueue.findMin();
	}
	
	/**
	 * delete the minimum item from the integer queue
	 */
	@Test
	public void removeIntegers() {
		for (int item : intArray) {
			intQueue.add(item);
		}
		
		assertEquals(intArray.length, intQueue.size());
		
		Integer[] array = Arrays.copyOf(intQueue.toArray(), intQueue.toArray().length, Integer[].class);
		Arrays.sort(array);
		
		for (int i=0; i<intArray.length; i++) {
			assertEquals(array[i], intQueue.deleteMin());
		}
		
		assertEquals(0, intQueue.size());
	}
	
	/**
	 * delete the minimum item from the string queue
	 */
	@Test
	public void removeStrings() {
		for (String item : stringArray) {
			stringQueue.add(item);
		}
		
		assertEquals(stringArray.length, stringQueue.size());
		
		for (int i=0; i<stringArray.length - 1; i++) {
			String temp = stringQueue.deleteMin();
			
			assertTrue(comparator.compare(stringQueue.findMin(), temp) >= 0);
		}
		
		stringQueue.deleteMin();
		
		assertEquals(0, stringQueue.size());
	}
	
	/**
	 * delete the minimum item from an empty queue
	 */
	@Test(expected = NoSuchElementException.class)
	public void deleteMinIntegersEmpty() {
		intQueue.deleteMin();
	}
	
	/**
	 * delete the minimum item from an empty queue
	 */
	@Test(expected = NoSuchElementException.class)
	public void deleteMinStringsEmpty() {
		stringQueue.deleteMin();
	}
	
	/**
	 * clear the queue
	 */
	@Test
	public void clearIntegers() {
		for (int item : intArray) {
			intQueue.add(item);
		}
		intQueue.clear();
		
		assertEquals(0, intQueue.size());
	}

	/**
	 * clear the queue
	 */
	@Test
	public void clearStrings() {
		for (String item : stringArray) {
			stringQueue.add(item);
		}
		stringQueue.clear();
		
		assertEquals(0, stringQueue.size());
	}
}
