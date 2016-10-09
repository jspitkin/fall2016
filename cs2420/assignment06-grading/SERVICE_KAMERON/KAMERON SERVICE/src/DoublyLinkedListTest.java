package assignment06;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoublyLinkedListTest {
	
	private DoublyLinkedList<Integer> list1, list2, list3, smallList, timing;

	private ArrayList<Integer> arrList;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		list1 = new DoublyLinkedList<Integer>();
		list2 = new DoublyLinkedList<Integer>();
		list3 = new DoublyLinkedList<Integer>();
		smallList = new DoublyLinkedList<Integer>();
		
		for(int i = 0; i < 100; i++){
			list1.addLast(i);
		}
		for(int i = 99; i >= 0; i--){
			list2.addFirst(i);
		}
		for(int i = 0; i < 100; i++){
			list3.add(i, i);
		}
		for(int i = 0; i < 5; i++){
			smallList.add(i, i);
		}
		
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void addFirstAndAddLastTest() {
		assertArrayEquals(list1.toArray(), list2.toArray());
	}
	@Test
	public void addFirstTest(){
		Object[] arr = new Object[100];
		for(int i = 0; i < 100; i++){
			arr[i] = i;
		}
		assertArrayEquals(arr, list2.toArray());
	}
	@Test
	public void addLastTest(){
		Object[] arr = new Object[100];
		for(int i = 0; i < 100; i++){
			arr[i] = i;
		}
		assertArrayEquals(arr, list1.toArray());
	}
	@Test
	public void addTest(){
		assertArrayEquals(list1.toArray(), list3.toArray());
	}
	@Test
	public void addTest2(){
		smallList.add(2, 2);
		Object[] arr = new Object[]{0, 1, 2, 2, 3, 4};
		assertArrayEquals(smallList.toArray(), arr);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void addExceptionTest(){
		smallList.add(10, 10);
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void addExceptionTest2(){
		smallList.add(-1, 10);
	}
	
	@Test
	public void clearTest1(){
		list1.clear();
		assertEquals(list1.size(), 0);
	}
	@Test
	public void clearTest2(){
		smallList.clear();
		smallList.addLast(5);
		Object[] arr = new Object[]{5};
		assertArrayEquals(smallList.toArray(), arr);
	}
	
	@Test
	public void getTest1(){
		assertEquals((int)list1.get(50), 50);
	}
	@Test
	public void getTest2(){
		assertEquals((int)list1.get(99), 99);
	}
	@Test
	public void getTestFalse(){
		assertFalse(list1.get(5).equals(6));
	}
	@Test(expected = IndexOutOfBoundsException.class)
	public void getIndexOutOfBoundExceptionTest(){
		smallList.get(50);
	}
	@Test(expected = NoSuchElementException.class)
	public void getNoSuchElementExceptionTest(){
		smallList.clear();
		smallList.get(50);
	}
	
	@Test
	public void indexOfTest(){
		assertEquals((int)list1.indexOf(99), 99);
	}
	@Test
	public void indexOfTestFalse(){
		assertFalse(list1.indexOf(5) == 6);
	}
	@Test
	public void indexOf_0_Test(){
		assertEquals(list1.indexOf(0), 0);
	}
	@Test
	public void indexOf_99_Test(){
		assertEquals(list1.indexOf(99), 99);
	}
	@Test
	public void indexOf_0_TestFalse(){
		assertFalse(list1.indexOf(0) == 1);
	}
	@Test(expected = NoSuchElementException.class)
	public void indexOfNoSuchElementExceptionTest(){
		smallList.clear();
		smallList.indexOf(50);
	}
	
	@Test
	public void isEmptyTrue(){
		list1.clear();
		assertTrue(list1.isEmpty());
	}
	@Test
	public void isEmptyFalse(){
		assertFalse(list1.isEmpty());
	}
	
	@Test
	public void lastIndexOfAnItemNotFoundTest(){
		assertEquals(list1.lastIndexOf(500), -1);
	}
	@Test
	public void lastIndexOfItemFoundTest(){
		assertEquals(list1.lastIndexOf(50), 50);
	}
	@Test
	public void lastIndexOfItemFoundAtEndTest(){
		assertEquals(list1.lastIndexOf(99), 99);
	}
	
	@Test
	public void removeFirstTest(){
		smallList.removeFirst();
		Object[] arr = new Object[]{1, 2, 3, 4};
		assertArrayEquals(smallList.toArray(), arr);
	}
	@Test
	public void removeLastTest(){
		smallList.removeLast();
		Object[] arr = new Object[]{0, 1, 2, 3};
		assertArrayEquals(smallList.toArray(), arr);
	}
	
	
}
