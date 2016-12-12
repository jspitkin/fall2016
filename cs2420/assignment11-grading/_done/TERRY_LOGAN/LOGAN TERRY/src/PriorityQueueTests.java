package assignment11;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * The test class for PriorityQueue
 * 
 * @author Logan Terry u0973436
 */
public class PriorityQueueTests {

	//Just testing findMin(), deleteMin(), and add().
	PriorityQueue<Integer> empty, filled, even;
	
	@Before
	public void setUp() throws Exception {
		empty = new PriorityQueue<Integer>();
		filled = new PriorityQueue<Integer>();
		even = new PriorityQueue<Integer>();
		for(int i = 0; i < 20; i++){
			filled.add(20-i);
			even.add((20-i)*2);
		}
	}

	@Test (expected = NoSuchElementException.class)
	public void FindMinOnEmpty(){
		empty.findMin();
	}
	
	@Test
	public void FindMinOnFilled(){
		assertEquals(new Integer(1), filled.findMin());
	}
	
	@Test
	public void FindMinOnEven(){
		assertEquals(new Integer(2), even.findMin());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void DeleteMinOnEmpty(){
		empty.deleteMin();
	}
	
	@Test
	public void DeleteMinOnFilled(){
		assertEquals(new Integer(1), filled.deleteMin());
		assertEquals(new Integer(2), filled.findMin());
		assertEquals(19, filled.size());
		assertTrue(heapWorks(filled.toArray()));
	}
	
	@Test
	public void DeleteMinOnEven(){
		assertEquals(new Integer(2), even.deleteMin());
		assertEquals(new Integer(4), even.findMin());
		assertEquals(19, even.size());
		assertTrue(heapWorks(even.toArray()));
	}

	@Test (expected = NullPointerException.class)
	public void AddNullItem(){
		filled.add(null);
	}
	
	@Test
	public void AddToEmpty(){
		empty.add(47);
		assertEquals(1, empty.size());
		assertArrayEquals(new Integer[]{47}, empty.toArray());
	}
	
	@Test
	public void AddToFilledEnd(){
		filled.add(47);
		assertEquals(21, filled.size());
		assertTrue(heapWorks(filled.toArray()));
	}
	
	@Test
	public void AddToFilledMiddle(){
		filled.add(10);
		assertEquals(21, filled.size());
		assertTrue(heapWorks(filled.toArray()));
	}
	
	@Test
	public void AddToFilledBeginning(){
		filled.add(1);
		assertEquals(21, filled.size());
		assertTrue(heapWorks(filled.toArray()));
	}
	
	@Test
	public void AddToEvenEnd(){
		even.add(47);
		assertEquals(21, even.size());
		assertTrue(heapWorks(even.toArray()));
	}
	
	@Test
	public void AddToEvenMiddle(){
		even.add(20);
		assertEquals(21, even.size());
		assertTrue(heapWorks(even.toArray()));
	}
	
	@Test
	public void AddToEvenBeginning(){
		even.add(2);
		assertEquals(21, even.size());
		assertTrue(heapWorks(even.toArray()));
	}
	
	private boolean heapWorks(Object[] arr){
		return recursHeap(arr, 0);
	}
	
	@SuppressWarnings("unchecked")
	private boolean recursHeap(Object[] arr, int index){
		Comparator cmp = Comparator.naturalOrder();
		if(2*index+1 > arr.length){
			return true;
		}
		if(cmp.compare(arr[index], arr[2*index+1]) > 0){
			return false;
		}
		if(2*index+2 > arr.length){
			return true;
		}
		if(cmp.compare(arr[index], arr[2*index+2]) > 0){
			return false;
		}
		return recursHeap(arr, 2*index+1) || recursHeap(arr,2*index+2);
	}

}
