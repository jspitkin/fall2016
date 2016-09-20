/**
 * @author Richard Child u0581030
 * @author Clayton Hislop u0515744
 */


package assignment03;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import assignment03.BinarySearchSet.BinarySearchSetIterator;

public class SearchSetTester {
	
	ArrayList<String> array1;
	ArrayList<Integer> array2;
	BinarySearchSet<String> emptySet;
	BinarySearchSet<String> set1, set2;
	ReverseOrder reverse;
	Iterator<String> iterator;
	@Before
	public void setUp(){
		
		//Empty set
		emptySet = new BinarySearchSet<String>();
		
		//BinarySearchSet with some items
		set1 = new BinarySearchSet<String>();
		set1.add("apple");
		set1.add("banana");
		set1.add("orange");
		set1.add("grape");
		set1.add("aaa");
		
		set2 = new BinarySearchSet<String>();
		array1 = new ArrayList<String>();
		array1.add("apple");
		array1.add("banana");
		array1.add("orange");
		array1.add("grape");
		array1.add("aaa");
		
		array2 = new ArrayList<Integer>();
		array2.add(1);
		array2.add(2);
		array2.add(3);
		array2.add(4);
		array2.add(5);
		
		reverse = new ReverseOrder();
		
		iterator = set1.iterator();
		
		
		
	}
	
	private class ReverseOrder implements Comparator<Integer>{

		@Override
		public int compare(Integer o1, Integer o2) {
			if(o1 < 02)
				return 1;
			else if(o1 > o2)
				return -1;
			return 0;
		}
		
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	
	/**
	 * Empty search set should throw 'No such element' exception
	 * when trying to retrieve first element in empty set.
	 */
	@Test
	public void emptySetFirst() {
		thrown.expect(NoSuchElementException.class);
		emptySet.first();
	}
	
	@Test
	public void firstItem() {
		assertEquals("aaa", set1.first());
	}
	
	/**
	 * Empty search set should throw 'No such element' exception
	 * when trying to retrieve last element in empty set.
	 */
	@Test
	public void emptySetLast(){
		thrown.expect(NoSuchElementException.class);;
		emptySet.last();
	}
	@Test
	public void addElementToEmptySet(){
		assertEquals(true, emptySet.add("apple"));
	}
	
	@Test
	public void expandTest() {
		set1.add("coconut");
		set1.add("tomato");
		set1.add("potato");
		set1.add("grapefruit");
		set1.add("chicken");
		set1.add("pasta");
		assertEquals("tomato", set1.last());
		assertEquals(11, set1.toArray().length);
	}
	
	@Test
	public void toArraySize(){
		assertEquals(5 ,set1.toArray().length);
	}
	
	@Test
	public void searchSetAddOneElement(){
		String[] array = {"aaa"};
		assertEquals(true,emptySet.add("aaa"));		
	}
	
	@Test
	public void searchSetLastItem() {
		set1.add("tomato");
		assertEquals("tomato", set1.last());
	}	
	
	@Test
	public void searchSetCorrectOrder(){
		String[] array = {"aaa","apple", "banana", "grape", "orange"};
		Object[] searchSet = set1.toArray();
		assertEquals(array[0], searchSet[0]);
		assertEquals(array[1], searchSet[1]);
		assertEquals(array[2], searchSet[2]);
		assertEquals(array[3], searchSet[3]);
		assertEquals(array[4], searchSet[4]);
		
	}
	
	@Test
	public void searchSetDuplicate(){
		assertEquals(false, set1.add("grape"));
	}
	
	@Test
	public void addAllNewArrayList() {
		assertTrue(set2.addAll(array1));
	}
	
	@Test
	public void addAllDuplicates() {
		assertFalse(set1.addAll(array1));
	}
	
	@Test
	public void addAllMixedItems() {
		array1.add("tomato");
		array1.add("potato");
		array1.add("grapefruit");
		array1.add("chicken");		
		assertTrue(set1.addAll(array1));
	}

	@Test
	public void clearSearchSet() {
		set1.clear();
		assertEquals(emptySet.toArray().length, set1.toArray().length);
		thrown.expect(NoSuchElementException.class);
		set1.first();
	}
	
	@Test
	public void searchSetContains() {
		assertTrue(set1.contains("orange"));
		
	}
	
	@Test
	public void searchSetDoesNotContain() {
		assertFalse(set1.contains("bananas"));
	}
	
	@Test
	public void searchSetContainsAll() {
		assertTrue(set1.containsAll(array1));
	}
	
	@Test
	public void searchSetDoesNotContainAll() {
		array1.add("chocolate");
		assertFalse(set1.containsAll(array1));
	}
	
	@Test
	public void addComparator(){
		BinarySearchSet<Integer> set = new BinarySearchSet<Integer>(reverse);
		set.addAll(array2);
		int[] nums = {5,4,3,2,1};
		assertEquals(nums[0], set.toArray()[0]);
	}
	
	@Test
	public void removeItemFromSearchSet() {		
		assertTrue(set1.remove("orange"));
		assertFalse(set1.contains("orange"));
	}
	
	@Test
	public void removeItemNotInSearchSet() {
		assertFalse(set1.remove("corn"));
	}
	
	@Test
	public void removeAllFromSearchSet() {
		array1.remove(0);
		array1.remove(1);
		array1.remove(1);
		assertTrue(set1.removeAll(array1));
		assertEquals(3, set1.toArray().length);
		assertFalse(set1.contains("aaa"));
	}
	
	@Test 
	public void removeAllFromSetFalse() {
		array1.clear();
		array1.add("bread");
		array1.add("soup");
		array1.add("peanuts");
		assertFalse(set1.removeAll(array1));
	}
	
	@Test
	public void iteratorTest(){
		
		while(iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
		assertEquals(0, set1.toArray().length);
	}
	@Test 
	public void interatorNoSuchElement() {
		while(iterator.hasNext()){
			iterator.next();
			iterator.remove();
		}
		thrown.expect(NoSuchElementException.class);
		iterator.next();
	}
	
	@Test
	public void iteratorIllegalState() {
		thrown.expect(IllegalStateException.class);
		iterator.next();
		iterator.remove();
		iterator.remove();
	}
	
}
