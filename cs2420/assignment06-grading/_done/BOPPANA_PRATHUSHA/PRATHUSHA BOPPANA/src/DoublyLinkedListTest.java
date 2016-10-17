package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Prathusha Boppana (u0778008)
 *
 */
public class DoublyLinkedListTest {
	DoublyLinkedList<Integer> emptyIntList;
	DoublyLinkedList<Integer> oneElementIntList;
	DoublyLinkedList<Integer> smallIntList;
	Iterator<Integer> iter;
	
	@Before
	public void setUp() throws Exception {
		emptyIntList = new DoublyLinkedList<Integer>();
		oneElementIntList = new DoublyLinkedList<Integer>();
		smallIntList = new DoublyLinkedList<Integer>();
		
		oneElementIntList.addFirst(new Integer(0));
		for(int j = 0; j < 4; j++){
			smallIntList.addLast(new Integer(j));
		}
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void hasNextEmptyIter() {
		iter = emptyIntList.iterator();
		Assert.assertFalse(iter.hasNext());
	}
	@Test
	public void hasNextOneElementIter() {
		iter = oneElementIntList.iterator();
		Assert.assertTrue(iter.hasNext());
		iter.next();
		Assert.assertFalse(iter.hasNext());
	}
	@Test
	public void hasNextSmallIntListIter() {
		iter = smallIntList.iterator();
		Assert.assertTrue(iter.hasNext());
		iter.next();
		Assert.assertTrue(iter.hasNext());
		iter.next();
		Assert.assertTrue(iter.hasNext());
		iter.next();
		Assert.assertTrue(iter.hasNext());
		iter.next();
		Assert.assertFalse(iter.hasNext());
	}
	
	//next iterator tests
	@Test(expected = NoSuchElementException.class)
	public void nextEmptyIter(){
		iter = emptyIntList.iterator();
		iter.next();
	}
	@Test
	public void nextOneElementIter(){
		iter = oneElementIntList.iterator();
		Assert.assertEquals(new Integer(0), iter.next());
	}
	@Test(expected = NoSuchElementException.class)
	public void nextOneElementIterException(){
		nextOneElementIter();
		iter.next();
	}
	@Test
	public void nextSmallIntIter(){
		iter = smallIntList.iterator();
		for(int i = 0; i < 4; i++){
			Assert.assertEquals(new Integer(i), iter.next());
		}
	}
	@Test(expected = NoSuchElementException.class)
	public void nextSmallIntIterException(){
		nextSmallIntIter();
		iter.next();
	}
	
	//remove iterator tests
	@Test(expected = NullPointerException.class)
	public void removeWithoutCallingNext(){
		iter = emptyIntList.iterator();
		iter.remove();
		
		iter = oneElementIntList.iterator();
		iter.remove();

		iter = smallIntList.iterator();
		iter.remove();
	}
	@Test(expected = NoSuchElementException.class)
	public void removeEmptyIter(){
		iter = emptyIntList.iterator();
		iter.next();
		iter.remove();
	}
	@Test(expected = NullPointerException.class)
	public void removeOneElementIter(){
		iter = oneElementIntList.iterator();
		iter.next();
		iter.remove();
	}
	@Test
	public void removeSmallIntIter(){
		iter = smallIntList.iterator();
		Assert.assertEquals(new Integer(0), iter.next());
		Assert.assertEquals(4, smallIntList.size());
		iter.next();
		iter.remove();
		Assert.assertEquals(new Integer(2), iter.next());
		Assert.assertEquals(3, smallIntList.size());
	}
	
	
	//addFirst, getFirst, getLast tests
	@Test
	public void addFirstEmpty(){
		Assert.assertTrue(emptyIntList.isEmpty());
		
		emptyIntList.addFirst(new Integer(5));
		Assert.assertEquals(new Integer(5), emptyIntList.getFirst());
		Assert.assertEquals(new Integer(5), emptyIntList.getLast());
		Assert.assertEquals(1, emptyIntList.size());
	}
	@Test
	public void addFirstOneElement(){
		Assert.assertEquals(1, oneElementIntList.size());
		
		oneElementIntList.addFirst(new Integer(1));
		Assert.assertEquals(new Integer(1), oneElementIntList.getFirst());
		Assert.assertEquals(new Integer(0), oneElementIntList.getLast());
		Assert.assertEquals(2, oneElementIntList.size());
	}
	@Test
	public void addFirstSmallInt(){
		Assert.assertEquals(4, smallIntList.size());
		
		smallIntList.addFirst(new Integer(22));
		Assert.assertEquals(new Integer(22), smallIntList.getFirst());
//		for(int i = 1; i < 4; i++){
//			Assert.assertEquals(new Integer(i), iter.next());
//		}
		Assert.assertEquals(new Integer(3), smallIntList.getLast());
		Assert.assertEquals(5, smallIntList.size());
	}
	
	//addLast tests
	@Test
	public void addLastEmpty(){
		Assert.assertEquals(0, emptyIntList.size());
		
		emptyIntList.addLast(new Integer(5));
		Assert.assertEquals(new Integer(5), emptyIntList.getFirst());
		Assert.assertEquals(new Integer(5), emptyIntList.getLast());
		Assert.assertEquals(1, emptyIntList.size());
	}
	@Test
	public void addLastOneElement(){
		Assert.assertEquals(1, oneElementIntList.size());
		
		oneElementIntList.addLast(new Integer(1));
		Assert.assertEquals(new Integer(0), oneElementIntList.getFirst());
		Assert.assertEquals(new Integer(1), oneElementIntList.getLast());
		Assert.assertEquals(2, oneElementIntList.size());
	}
	@Test
	public void addLastSmallInt(){
		Assert.assertEquals(4, smallIntList.size());
		
		smallIntList.addLast(new Integer(22));
		Assert.assertEquals(new Integer(0), smallIntList.getFirst());
		Assert.assertEquals(new Integer(22), smallIntList.getLast());
		Assert.assertEquals(5, smallIntList.size());
	}
	
	//add tests
	@Test
	public void addEmptyValidIndex(){
		emptyIntList.add(0, new Integer(70));
		Assert.assertEquals(new Integer(70), emptyIntList.get(0));
		Assert.assertEquals(0, emptyIntList.indexOf(70));
		Assert.assertEquals(-1, emptyIntList.lastIndexOf(2));
		Assert.assertEquals(new Integer(70), emptyIntList.getFirst());
		Assert.assertEquals(new Integer(70), emptyIntList.getLast());
		Assert.assertEquals(1, emptyIntList.size());
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void addEmptyBadIndex(){
		emptyIntList.add(1, new Integer(70));
	}
	@Test(expected = NullPointerException.class)
	public void addSmallTailIndex(){
		smallIntList.add(4, new Integer(70));
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void addSmallBadIndex(){
		smallIntList.add(5, new Integer(70));
	}
	@Test
	public void addSmallIntList(){
		smallIntList.add(2, new Integer(2));
//		Assert.assertEquals(new Integer(2), smallIntList.get(2));
		Assert.assertEquals(2, smallIntList.indexOf(2));
//		Assert.assertEquals(3, emptyIntList.lastIndexOf(2));
		Assert.assertEquals(new Integer(0), smallIntList.getFirst());
		Assert.assertEquals(new Integer(3), smallIntList.getLast());
		Assert.assertEquals(5, smallIntList.size());
	}
	
	//special getFirst and getLast tests
	@Test(expected = NoSuchElementException.class)
	public void getFirstEmpty(){
		emptyIntList.getFirst();
	}
	@Test(expected = NoSuchElementException.class)
	public void getLastEmpty(){
		emptyIntList.getLast();
	}
	
	//get tests
	@Test
	public void getEmptyTailIndex(){
		Assert.assertNull(emptyIntList.get(0));
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void getEmptyBadIndex(){
		emptyIntList.get(1);
	}
	@Test
	public void getEmptyValidIndex(){
		emptyIntList.add(0, new Integer(25));
		Assert.assertEquals(new Integer(25), emptyIntList.get(0));
	}
	
	//removeFirst, removeLast, remove tests
	@Test(expected = NullPointerException.class)
	public void removeFirstEmpty(){
		emptyIntList.removeFirst();
	}
	@Test(expected = NullPointerException.class)
	public void removeLastEmpty(){
		emptyIntList.removeLast();
	}
	@Test(expected = NullPointerException.class)
	public void removeEmptyValidIndex(){
		emptyIntList.remove(0);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void removeEmptyBadIndex(){
		emptyIntList.remove(1);
	}
	@Test
	public void removeFirstOneElement(){
		oneElementIntList.removeFirst();
		Assert.assertEquals(0, smallIntList.size());
		Integer[] intArray = new Integer[1];
		intArray[0] = new Integer(0);
		Assert.assertArrayEquals(intArray, smallIntList.toArray());
	}
	@Test
	public void removeLastOneElement(){
		oneElementIntList.removeLast();
		Assert.assertEquals(0, smallIntList.size());
	}
	@Test
	public void removeOneElement(){
		oneElementIntList.remove(0);
		Assert.assertEquals(0, smallIntList.size());
	}
	@Test
	public void removeFirstSmallInt(){
		smallIntList.removeFirst();
		Assert.assertEquals(new Integer(1), smallIntList.getFirst());
		Assert.assertEquals(3, smallIntList.size());
	}
	@Test
	public void removeLastSmallInt(){
		smallIntList.removeLast();
		Assert.assertEquals(new Integer(2), smallIntList.getLast());
		Assert.assertEquals(3, smallIntList.size());
	}
	@Test
	public void removeSmallInt(){
		smallIntList.remove(1);
		Assert.assertEquals(new Integer(0), smallIntList.getFirst());
		Assert.assertEquals(new Integer(2), smallIntList.get(1));
		Assert.assertEquals(3, smallIntList.size());
	}

	//size and isEmpty tests
	@Test
	public void sizeANDisEmptyDiffLists(){
		Assert.assertEquals(0, emptyIntList.size());
		Assert.assertTrue(emptyIntList.isEmpty());
		Assert.assertEquals(1, oneElementIntList.size());
		Assert.assertFalse(oneElementIntList.isEmpty());
		Assert.assertEquals(4, smallIntList.size());
		Assert.assertFalse(smallIntList.isEmpty());
	}
	
}
