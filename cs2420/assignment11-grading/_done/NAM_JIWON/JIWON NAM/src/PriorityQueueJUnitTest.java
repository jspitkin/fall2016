package assignment11;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
/**
 * This is JUnit test class, it will check add, findMin, and deleteMin method
 * also, it check throwing exception
 * @author Jiwon Nam
 *
 */
public class PriorityQueueJUnitTest {

	Comparator<Integer> cmp;
	PriorityQueue<Integer> pq;
	int[] descendExpect;
	int[] customExpect;
	ArrayList<Integer> ranList;
	ArrayList<Integer> ascendList;
	ArrayList<Integer> descendList;
	@Before
	public void setup() {

		cmp = new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1 - o2;
			}
		};
		pq = new PriorityQueue<Integer>(cmp);
		descendExpect = new int[]{1, 2, 5, 4, 3, 9, 6, 10, 7, 8};
		customExpect = new int[]{1, 2, 3, 5, 4, 8, 9, 10, 6, 7};
		ranList = PriorityQueueTimer.generateRanNum(1000);
		ascendList = PriorityQueueTimer.generateAscending(1000);
		descendList = PriorityQueueTimer.generateDescending(1000);
	}
	/**
	 * add method jUnit test, it will check random order input, 
	 * ascending order input, and descending order input
	 * comparing with my expectation
	 */
	@Test
	public void addTest() {
		// add empty set
		pq.add(0);
		assertEquals(0, pq.toArray()[0]);
		pq.clear();
		assertEquals(0, pq.size());
		pq.add(3);
		assertEquals(3, pq.toArray()[0]);
		pq.clear();
		assertEquals(0, pq.size());
		pq.add(100);
		assertEquals(100, pq.toArray()[0]);
		pq.clear();
		assertEquals(0, pq.size());
		// it can store 1 to 10 
		for(int i = 1; i <= 10; i++) {
			pq.add(i);
			assertEquals(i, pq.size());
		}
		
		Object[] result = pq.toArray();
		// ascending input check
		for(int i = 0; i < 10; i++) 
			assertEquals(i + 1, result[i]);
		
		// make it clear
		pq.clear();
		assertEquals(0, pq.size());
		
		// if it is ascending order to input
		for(int i = 10; i >= 1; i--) {
			pq.add(i);
			assertEquals(11 - i, pq.size());
		}
		
		result = pq.toArray();
		// descending input check
		for(int i = 0; i < 10; i++) 
			assertEquals(descendExpect[i], result[i]);
		
		pq.clear();
		assertEquals(0, pq.size());
		// add 1-10 customized order
		pq.add(1);
		pq.add(10);
		pq.add(3);
		pq.add(7);
		pq.add(2);
		pq.add(8);
		pq.add(9);
		pq.add(5);
		pq.add(6);
		pq.add(4);
		
		result = pq.toArray();
		
		for(int i = 0; i < 10; i++)
			assertEquals(customExpect[i], result[i]);
	}
	
	/**
	 * findMin method test, add lots of item to array, 
	 * check returning smallest element
	 */
	@Test
	public void findMinTest() {
		// add ascend order numbers
		for(int i = 0; i < 1000; i++)
			pq.add(ascendList.get(i));
		// always 1
		assertEquals(1, (int)pq.findMin());

		pq.clear();
		assertEquals(0, pq.size());
		// add random order number
		for(int i = 0; i < 1000; i++)
			pq.add(ranList.get(i));
		// always 1
		assertEquals(1, (int)pq.findMin());
		
		pq.clear();
		assertEquals(0, pq.size());
		// add descending order number
		for(int i = 0; i < 1000; i++)
			pq.add(descendList.get(i));
		// always 1
		assertEquals(1, (int)pq.findMin());
	}
	
	/**
	 * deleteMin method test, it will check every return value to use
	 * deleteMin method, and after deleting,checking it has next smallest value
	 */
	@Test
	public void deleteMinTest() {
		
		for(int i = 0; i < 10; i++)
			pq.add(i + 1);
		
		for(int i = 0; i < 10; i++)
			pq.deleteMin();
		
		pq.clear();
		assertEquals(0, pq.size());
		
		// add ascend order numbers
		for (int i = 0; i < 1000; i++)
			pq.add(ascendList.get(i));
		// delete min until size = 0, check min after deletion
		for (int i = 1; i <= 1000; i++) {
			assertEquals(i, (int) pq.findMin());
			assertEquals(i, (int) pq.deleteMin());	
		}
		pq.clear();
		assertEquals(0, pq.size());
		// add random order number
		for (int i = 0; i < 1000; i++)
			pq.add(ranList.get(i));
		// delete min until size = 0, check min after deletion
		for (int i = 1; i <= 1000; i++) {
			assertEquals(i, (int) pq.findMin());
			assertEquals(i, (int) pq.deleteMin());	
		}
		pq.clear();
		assertEquals(0, pq.size());
		// add descending order number
		for (int i = 0; i < 1000; i++)
			pq.add(descendList.get(i));
		// delete min until size = 0, check min after deletion
		for (int i = 1; i <= 1000; i++) {
			assertEquals(i, (int) pq.findMin());
			assertEquals(i, (int) pq.deleteMin());	
		}
	}
		
	/**
	 * generate dot file to see in my eyes, testing by myself
	 */
	@Test
	public void dotTest() {
		ranList = PriorityQueueTimer.generateRanNum(10);
		ascendList = PriorityQueueTimer.generateAscending(10);
		descendList = PriorityQueueTimer.generateDescending(10);
		for (int i = 0; i < 10; i++)
			pq.add(ranList.get(i));
		
		pq.generateDotFile("random_input.dot");
		
		pq.clear();
		assertEquals(0, pq.size());
		
		for (int i = 0; i < 10; i++)
			pq.add(ascendList.get(i));
		
		pq.generateDotFile("ascending_input.dot");
		pq.clear();
		assertEquals(0, pq.size());
		
		for (int i = 0; i < 10; i++)
			pq.add(descendList.get(i));
		
		pq.generateDotFile("descending_input.dot");
	}

	/**
	 * findMin method, throw exception test with empty size array
	 */
	@Test (expected = NoSuchElementException.class)
	public void findMinException() {
		pq.clear();
		assertEquals(0, pq.size());
		// throw exception with empty array
		pq.findMin();
	}
	
	/**
	 * deleteMin method, throw exception test with empty size array
	 */
	@Test (expected = NoSuchElementException.class)
	public void deleteMinException() {
		pq.clear();
		assertEquals(0, pq.size());
		// throw exception with empty array
		pq.deleteMin();
	}
	
}
