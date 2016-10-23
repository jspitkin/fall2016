package assignment06;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Timing class for the DoublyLinkedList class and all of its methods
 * @author Zachary Cutler, u1025642
 *
 */
public class DoublyLinkedListTest {
	 
	DoublyLinkedList list;
	
	@Before
	public void setUp() throws Exception{
		list = new DoublyLinkedList<Integer>();	
	}
	
	@Test
	public void getFirstTest(){
		list.addFirst(2);
		list.addFirst(8);
		assertEquals(list.getFirst(), 8);
	}
	
	@Test (expected = Exception.class)
	public void getFirstTestEmptyArray(){
		list.getFirst();
	}
	
	@Test
	public void getLastTest(){
		list.addFirst(2);
		list.addFirst(8);
		assertEquals(list.getLast(), 2);
	}
	
	@Test (expected = Exception.class)
	public void getLastTestEmptyArray(){
		list.getLast();
	}
	
	@Test
	public void addFirstTest(){
		list.addFirst(7);
		list.addFirst(2);
		assertEquals(list.toArray(), new Integer[]{2, 7});
		list.addFirst(1);
		assertEquals(list.toArray(), new Integer[]{1, 2, 7});
	}
	
	@Test
	public void addFirstEmptyArray(){
		list.addFirst(1);
		assertEquals(list.toArray(), new Integer[]{1});
	}
	
	@Test
	public void addLastTest(){
		list.addFirst(7);
		list.addFirst(2);
		list.addLast(9);
		list.addLast(1);
		assertEquals(list.toArray(), new Integer[]{2, 7, 9, 1});
	}
	
	@Test
	public void addLastEmptyArray(){
		list.addLast(1);
		assertEquals(list.toArray(), new Integer[]{1});
	}
	
	
	
	@Test
	public void addTest(){
		list.addFirst(7);
		list.addFirst(2);
		list.add(1, 27);
		assertEquals(list.toArray(), new Integer[]{2, 27, 7});
	}
	
	@Test
	public void addTestLarge(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(90);
		list.addFirst(18);
		list.addFirst(258);
		list.addFirst(3);
		list.addFirst(1);
		list.add(1, 27);
		list.add(7, 19);
		list.add(3, 0);
		assertEquals(list.toArray(), new Integer[]{1, 27, 3, 0, 258, 18, 90, 2, 19, 7});
	}
	
	@Test
	public void addToEndOfArrayTest(){
		list.addFirst(7);
		list.addFirst(2);
		list.add(2, 27);
		assertEquals(list.toArray(), new Integer[]{2, 7, 27});
	}
	
	@Test
	public void addToStartOfArrayTest(){
		list.addFirst(7);
		list.addFirst(2);
		list.add(0, 27);
		assertEquals(list.toArray(), new Integer[]{27, 2, 7});
	}
	
	@Test (expected = Exception.class)
	public void addTestOutOfBoundsTooHigh(){
		list.addFirst(7);
		list.addFirst(2);
		list.add(3, 19);
	}
	 
	@Test (expected = Exception.class)
	public void addTestOutOfBoundsTooLow(){
		list.addFirst(7);
		list.addFirst(2);
		list.add(-1, 19);
	}
	
	
	@Test
	public void removeMiddle(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.remove(2);
		assertEquals(list.toArray(), new Integer[]{1, 7, 2});
	}
	
	@Test
	public void removeSingleElement(){
		list.addFirst(2);
		list.remove(0);
		assertEquals(list.toArray(), new Integer[]{});
	}
	
	@Test
	public void removeTestLarge(){
		list.addFirst(7);
		list.addFirst(2);
		list.addFirst(90);
		list.addFirst(18);
		list.addFirst(258);
		list.addFirst(3);
		list.addFirst(1);
		list.remove(3);
		assertEquals(list.remove(1), 3);
		list.remove(3);
		assertEquals(list.toArray(), new Integer[]{1, 258, 90, 7});
	}
	
	@Test
	public void removeFirstTest(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.removeFirst();
		assertEquals(list.toArray(), new Integer[]{7, 3, 2});
	}
	
	@Test
	public void clearTest(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.clear();
		list.addFirst(9);
		assertEquals(list.toArray(), new Integer[]{9});
	}
	
	@Test
	public void isEmptyTestFalse(){
		list.addFirst(2);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void isEmptyTest(){
		assertTrue(list.isEmpty());
	}
	
	@Test (expected = Exception.class)
	public void removeFirstTestEmpty(){
		list.removeFirst();
	}
	
	@Test (expected = Exception.class)
	public void removeLastTestEmpty(){
		list.removeLast();
	}
	
	@Test
	public void removeLastTest(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.removeLast();
		assertEquals(list.toArray(), new Integer[]{1, 7, 3});
	}
	
	@Test
	public void getTestMiddle(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		assertEquals(list.get(2), 3);
	}
	
	@Test
	public void getTestLargeAndMultiple(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.addFirst(19);
		list.addFirst(22);
		list.addFirst(20);
		list.addFirst(70);
		assertEquals(list.get(4), 1);
		assertEquals(list.get(0), 70);
		assertEquals(list.get(2), 22);
	}
	
	@Test
	public void getTestEnd(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		assertEquals(list.get(3), 2);
	}
	
	@Test (expected = Exception.class)
	public void getTestEndError(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		assertEquals(list.get(4), 2);
	}
	
	@Test (expected = Exception.class)
	public void getTestStartError(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		assertEquals(list.get(-1), 2);
	}
	
	@Test
	public void getTestStart(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		assertEquals(list.get(0), 1);
	}
	
	@Test
	public void removeLast(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.remove(3);
		assertEquals(list.toArray(), new Integer[]{1, 7, 3});
	}
	
	@Test
	public void removeFirst(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.remove(0);
		assertEquals(list.toArray(), new Integer[]{7, 3, 2});
	}
	
	@Test
	public void removeFirstTesterReturn(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		assertTrue(list.removeFirst().equals(1));
	}
	
	@Test
	public void removeFirstTester(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.removeFirst();
		assertEquals(list.toArray(), new Integer[]{7, 3, 2});
	}
	
	@Test
	public void removeLastTesterReturn(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		assertTrue(list.removeLast().equals(2));
	}
	
	@Test
	public void removeLastTester(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.removeLast();
		assertEquals(list.toArray(), new Integer[]{1, 7, 3});
	}
	
	@Test (expected = Exception.class)
	public void removeTooLowIndex(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.remove(-1);
		assertEquals(list.toArray(), new Integer[]{7, 3, 2});
	}
	
	@Test (expected = Exception.class)
	public void removeTooHighIndex(){
		list.addFirst(2);
		list.addFirst(3);
		list.addFirst(7);
		list.addFirst(1);
		list.remove(4);
		assertEquals(list.toArray(), new Integer[]{7, 3, 2});
	}
}
