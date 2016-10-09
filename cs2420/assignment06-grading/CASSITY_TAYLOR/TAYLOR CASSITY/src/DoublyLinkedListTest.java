package assignment06;
//Travis Taylor Cassity
//u0905687
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest {
	//TODO: Iterator testing
	
	DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
	DoublyLinkedList<Integer> ell = new DoublyLinkedList<>(); //empty
	int listSize = 100; //Do not bring below 21 due to hard-coded tests
	Integer singleTestValue = 99; //singleTestValue
	Integer[] array = new Integer[listSize*2];
	Random rand = new Random(listSize);

	@Before
	public void setUp(){
		for(int i = 0; i < listSize; i++)
			array[i] = i;
		for(int j = 0; j < listSize; j++)
			dll.add(j, array[j]);
	}
	
	@After
	public void tearDown(){
		array = new Integer[listSize];
	}
	
	/**
	 * Generates and returns a list of random integer
	 * @return
	 */
	public Object[] randomList(){
		Object[] randList = new Object[listSize*2];
		for(int i = 0; i < listSize; i++)
			randList[i] = rand.nextInt(listSize - 1);
		 Arrays.sort(randList);
		 return randList;
	}
	
	@Test
	public void addfirst(){
		dll.addFirst(singleTestValue);
		assertEquals(dll.getFirst(), singleTestValue);
		ell.addFirst(singleTestValue);
		assertEquals(ell.getFirst(), singleTestValue);
	}

	@Test
	public void addLast(){
		dll.addLast(singleTestValue);
		assertEquals(dll.getLast(), singleTestValue);
		assertEquals(dll.getLast(), singleTestValue);
		dll.addLast(99999999);
		ell.addLast(99999999);
	}
	
	@Test
	public void add(){
		dll.add(5, singleTestValue);
		assertEquals(dll.get(5), singleTestValue);
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void addbreak(){
		dll.add(99999999, 99999999);
		ell.add(99999999, 99999999);
	}
	
	@Test
	public void getfirst(){
		assertEquals(dll.getFirst(), new Integer(0));
	}
	@Test(expected=NoSuchElementException.class)
	public void getfirstbreak(){
		ell.getFirst();
	}
	
	@Test
	public void getlast(){
		assertEquals((int)dll.getLast(), listSize-1);
	}
	@Test(expected=NoSuchElementException.class)
	public void getlastbreak(){
		ell.getLast();
	}
	
	@Test
	public void get(){
		assertEquals((int)dll.get(10), 10);
		assertEquals((int)dll.get(listSize-1), listSize-1);
		assertEquals((int)dll.get(0), 0);
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void getbreak(){
		dll.get(listSize);
		ell.get(0);
	}
	
	@Test
	public void removefirst(){
		assertEquals((int)dll.removeFirst(), 0);
		assertEquals((int)dll.get(0), 1);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void removefirstBreak(){
		//Break
		ell.removeFirst();
	}
	
	@Test
	public void removelast(){
		assertEquals((int)dll.removeLast(), listSize-1);
		assertEquals((int)dll.getLast(), listSize-2);
		
	}
	@Test(expected=NoSuchElementException.class)
	public void removelastbreak(){
		ell.removeLast();
	}
	
	@Test
	public void remove(){
		assertEquals((int)dll.remove(0), 0);
		assertEquals((int)dll.get(0), 1);
		
		assertEquals((int)dll.remove(5), 6);
		assertEquals((int)dll.get(5), 7);
		
		assertEquals((int)dll.remove(1), 2);
		assertEquals((int)dll.get(1), 3);
	}
	@Test(expected=IndexOutOfBoundsException.class)
	public void removebreak(){
		dll.remove(listSize);
		dll.remove(listSize*2);
		ell.remove(999);
	}
	
	@Test
	public void indexof(){
		assertEquals(dll.indexOf(5), 5);
		assertEquals(dll.indexOf(0), 0);
		assertEquals(dll.indexOf(listSize-1), listSize-1);
		assertEquals(dll.indexOf(1), 1);
		
		//Break
		assertEquals(dll.indexOf(-1), -1);
		assertEquals(dll.indexOf(listSize+2), -1);
		ell.indexOf(-1);
	}
	
	@Test
	public void lastindexOf(){
		assertEquals(dll.lastIndexOf(5), 5);
		dll.add(10, 20);
		assertEquals((int)dll.get(10), 20);
		//Break
		assertEquals(ell.lastIndexOf(9999), -1);
	}
	
	@Test
	public void size(){
		assertEquals(dll.size(), listSize);
		assertEquals(ell.size(), 0);
	}
	@Test
	public void isempty(){
		assertFalse(dll.isEmpty());
		assertTrue(ell.isEmpty());
	}
	
	@Test
	public void clear(){
		dll.clear();
		assertEquals(dll.size(), 0);
		assertTrue(dll.isEmpty());
		
		ell.clear();
		assertEquals(ell.size(), 0);
		assertTrue(ell.isEmpty());
	}
	
	@Test
	public void toarray(){
		Object[] objarray = new Object[listSize];
		
		for(int i = 0; i < listSize; i++)
			objarray[i] = array[i];
		
		Object[] outArray = dll.toArray();
		
		for(int i = 0; i < listSize; i++)
			assertTrue(outArray[i].equals(objarray[i]));
		
		ell.toArray();
	}
	
	//integers
	@Test
	public void nextiter(){
		Iterator<Integer> it = dll.iterator();
		Assert.assertEquals(it.next(), dll.get(0));
		it.next(); //at 1
		it.next(); //at 2
		Assert.assertEquals(it.next(), dll.get(3));
		it.next(); //at 3
		it.next(); //at 4
		Assert.assertTrue(it.hasNext());
	}
	@Test
	public void removeiter(){
		Iterator<Integer> it = dll.iterator();
		it.next();
		it.remove();
		Assert.assertEquals(it.next(), dll.get(1)); //index 1 obj is now index 0 obj
		it.next();
		it.remove();
		Assert.assertEquals(it.next(), dll.get(3)); //Failing, lack the time to properly fix this at the moment.
	}
}
