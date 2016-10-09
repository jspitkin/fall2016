package assignment06;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This is a testing class to test the methods contained within the doubly linked list class.
 * @author Brian Park
 *
 */
public class DLLJUnitTests {
	
	DoublyLinkedList<Integer> addingList = new DoublyLinkedList<Integer>();
	DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
	
	@Before
	public void setUp() throws Exception {
		for(int i = 0; i < 10; i ++){
			list.add(i, i);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddTestAppending() { 
		
		for(int i = 0; i < 10; i ++){
			addingList.add(i, i);
		}
		assertArrayEquals(addingList.toArray(), new Integer[]{0,1,2,3,4,5,6,7,8,9});
	}
	@Test
	public void AddTestInsertionForwards() { 
		for(int i = 0; i < 10; i ++){
			addingList.add(i, i);
		}
		addingList.add(5, 1337);

	}
	@Test
	public void AddTestInsertionBackwards() { 
		for(int i = 0; i < 10; i ++){
			addingList.add(i, i);
		}
		addingList.add(8, 1337);
		Assert.assertEquals((Integer) 1337, addingList.get(8));
		
	}
	@Test
	public void AddTestHead() {
		for(int i = 0; i < 10; i ++){
			addingList.add(i, i);
		}
		addingList.add(0, 1337);
		Assert.assertEquals((Integer) 1337, addingList.get(0));
		
	}
	@Test
	public void AddTestTail() {
		for(int i = 0; i < 10; i ++){
			addingList.add(i, i);
		}
		addingList.add(9, 1337);
		Assert.assertEquals((Integer) 1337, addingList.get(9));
	}
	
	@Test
	public void getFirstTest() {
		assertEquals((int)0, (int) list.getFirst());
	}
	@Test
	public void getFirstTestChanged() {
		list.add(0, 12);
		assertEquals((int) 12, (int) list.getFirst());
	}
	@Test
	public void getLastTest() {
		assertEquals((int)9,(int)list.getLast());
	}
	@Test
	public void getLastTestChanged() {
		list.addLast(13);
		assertEquals((int)13,(int)list.getLast());
	}
	
	@Test
	public void getTest() {
		
		assertEquals((int)9,(int)list.get(9));
	}
	@Test
	public void getTestHead() {
		assertEquals((int)0,(int)list.get(0));
	}
	@Test
	public void getTestTail() {
		assertEquals((int)9,(int)list.get(9));
	}
	
	@Test
	public void RemoveFirstTest() {
		list.removeFirst();
		assertEquals((int)1, (int)list.get(0));
	}
	@Test
	public void RemoveFirstTestMultipleCalls() {
		list.removeFirst();
		list.removeFirst();
		list.removeFirst();
		assertEquals((int)3, (int)list.get(0));
		
	}

	@Test
	public void RemoveLastTestOnce() {
		list.removeLast();
		assertEquals((int)8, (int)list.getLast());
		
		
	}
	@Test
	public void RemoveLastTestMultipleCalls() {
		list.removeLast();
		list.removeLast();
		list.removeLast();
		assertEquals((int)6, (int)list.getLast());
	}
	@Test
	public void RemoveTest() {
		list.remove(5);
		assertEquals((int) 6,(int) list.get(5));
		
	}
	@Test
	public void RemoveTestHead() {
		list.remove(0);
		
		assertEquals((int) 1,(int) list.get(0));
	}
	@Test
	public void RemoveTestTail() {
		list.remove(9);
		assertEquals((int) 8,(int) list.get(8));
		
	}
	@Test
	public void RemoveTestMultipleCalls() {
		list.remove(5);
		list.remove(5);
		list.remove(5);
		assertEquals((int) 8,(int) list.get(5));
	}
	@Test
	public void IndexOfTestDefault() {
		
		assertEquals((int) 6, list.indexOf(6));
	}
	@Test
	public void IndexOfTestDYnamicTest() {
		assertEquals((int) 9, list.indexOf(9));
		list.add(5, 1337);
		assertEquals((int) 10, list.indexOf(9));
		assertEquals((int) 5, list.indexOf(1337));
	}
	@Test
	public void IndexOfTest() {
		assertEquals((int) -1, list.indexOf(13337));
	}
	@Test
	public void LastIndexOfTestDefault() {
		assertEquals((int) 6, list.indexOf(6));
	}
	@Test
	public void LastIndexOfTestDynamic() {
	list.add(8, 6);
	list.add(9, 6);
	list.add(10, 6);
	assertEquals((int) 10, list.lastIndexOf(6));
	}
	@Test
	public void LastIndexOfTestNotElement() {
		assertEquals((int) -1, list.lastIndexOf(26));
	}
	@Test (expected = NullPointerException.class)
	public void ClearTest() {
		list.clear();
		list.get(4);
		list.get(0);
	}
	@Test
	public void ToArrayTest() {
		assertArrayEquals(new Integer[]{0,1,2,3,4,5,6,7,8,9}, list.toArray());
	}
//	@Test
//	public void ToArrayTest2() {
//		fail("Not yet implemented");
//	}

	

}
