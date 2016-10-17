package assignment06;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Torin McDonald
 * @uid u0940253
 *
 */
public class DoublyLinkedListTester {
	private DoublyLinkedList<Integer> list, list2;
	
	
	@Before
	public void setUp() throws Exception {
	list= new DoublyLinkedList<Integer>();
	list.addFirst(1);
	list.addFirst(2);
	list.addFirst(3);
	list2= new DoublyLinkedList<Integer>();
	}
	/////////////////////////////////////////////////////////////

	//addFirst
	@Test
	public void addFirstTest() {
		assertArrayEquals(list.toArray(),new Object[]{3,2,1});
	}
	@Test
	public void addFirstEmptyTest(){
		list2.addFirst(1);
		assertArrayEquals(list2.toArray(), new Object[]{1});
	}
	/////////////////////////////////////////////////////////////

	//addLast
	@Test
	public void addLastTest(){
		list.addLast(4);
		assertArrayEquals(list.toArray(), new Object[]{3,2,1,4});
	}
	@Test
	public void addLastTestEmpty(){
		list2.addLast(1);
		assertArrayEquals(list2.toArray(), new Object[]{1});
	}
	@Test
	public void addLastTestSizeOne(){
		list2.addLast(2);
		list2.addLast(1);
		assertArrayEquals(list2.toArray(), new Object[]{2,1});
	}
	/////////////////////////////////////////////////////////////

	//add
	@Test
	public void addTestFull(){
		list.add(1, 5);
		assertArrayEquals(list.toArray(), new Object[]{3,5,2,1});
	}
	@Test
	public void addTestEmpty(){
		list2.add(0, 5);
		assertArrayEquals(list2.toArray(),new Object[]{5});
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void addTestNoSuchElement(){
		list.add(4, 9);
	}
	/////////////////////////////////////////////////////////////

	//getFirst
	@Test
	public void getFirstFullTest(){
		assertTrue(list.getFirst()==3);
	}
	@Test(expected=NoSuchElementException.class)
	public void getFirstEmptyTest(){
		list2.getFirst();
	}
	/////////////////////////////////////////////////////////////

	//getLast
	@Test
	public void getLastFullTest(){
		assertTrue(list.getLast()==1);
	}
	@Test(expected=NoSuchElementException.class)
	public void getLastEmptyTest(){
		list2.getLast();
	}
	@Test
	public void getLastSizeOneTest(){
		list2.addFirst(3);
		assertTrue(list2.getLast()==3);
	}
	/////////////////////////////////////////////////////////////

	//get
	@Test
	public void getFullTest(){
		assertTrue(list.get(2)==1);
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void getEmptyTest(){
		list2.get(0);
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void getOutofBounds(){
		list.get(5);
	}
	/////////////////////////////////////////////////////////////

	//removeFirst
	@Test
	public void removeFirstFull(){
		list.removeFirst();
		assertArrayEquals(list.toArray(), new Object[]{2,1});
	}
	@Test(expected=NoSuchElementException.class)
	public void removeFirstEmpty(){
		list2.removeFirst();
	}
	@Test
	public void removeFirstReturnTest(){
		assertTrue(list.removeFirst()==3);
	}
	/////////////////////////////////////////////////////////////

	//removeLast
	@Test
	public void removeLastFull(){
		list.removeLast();
		assertArrayEquals(list.toArray(),new Object[]{3,2});
	}
	@Test(expected=NoSuchElementException.class)
	public void removeLastEmpty(){
		list2.removeLast();
	}
	@Test
	public void removeLastReturnTest(){
		assertTrue(list.removeLast()==1);
	}
	/////////////////////////////////////////////////////////////
	//remove
	@Test
	public void removeFullTest(){
		list.remove(1);
		assertArrayEquals(list.toArray(),new Object[]{3,1});
	}
	@Test
	public void removeIndexLastTest(){
		list.remove(2);
		assertArrayEquals(list.toArray(),new Object[]{3,2});
	}
	@Test
	public void removeIndexFirstTest(){
		list.remove(0);
		assertArrayEquals(list.toArray(), new Object[]{2,1});
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeEmptyTest(){
		list.remove(6);
	}
	@Test
	public void removeReturnTest(){
		assertTrue(list.remove(1)==2);
	}
	/////////////////////////////////////////////////////////////
	
	//indexOf
	@Test
	public void indexOfFullTest(){
		list.addLast(2);
		assertTrue(list.indexOf(1)==2);
	}
	@Test
	public void indexOfEmptyTest(){
		assertTrue(list2.indexOf(4)==-1);
	}
	@Test
	public void indexOfFullNonExistentTest(){
		assertTrue(list.indexOf(10)==-1);
	}
	/////////////////////////////////////////////////////////////

	//lastIndexOf
	@Test
	public void lastIndexOfFullTest(){
		list.add(2,3);
		assertTrue(list.lastIndexOf(3)==2);
	}
	@Test
	public void lastIndexOfFUllNonExistentTest(){
		assertTrue(list.lastIndexOf(7)==-1);
	}
	
	/////////////////////////////////////////////////////////////

	//size
	@Test
	public void sizeFullTest(){
		assertTrue(list.size()==3);
	}
	
	@Test
	public void sizeEmptyTest(){
		assertTrue(list2.size()==0);
	}

	/////////////////////////////////////////////////////////////

	//isEmpty
	@Test 
	public void isEmptyFullTest(){
		assertFalse(list.isEmpty());
	}
	@Test
	public void isEmptyEmptyTest(){
		assertTrue(list2.isEmpty());

	}
	/////////////////////////////////////////////////////////////

	//clear
	@Test
	public void clearFullTest(){
		list.clear();
		assertArrayEquals(list.toArray(), new Object[]{});
		assertTrue(list.isEmpty());
	}
	@Test
	public void clearEmptyTest(){
		list2.clear();
		assertArrayEquals(list2.toArray(), new Object[]{});

	}
	/////////////////////////////////////////////////////////////

	
	
	
	
	
	
	
	
	
	
	
}
