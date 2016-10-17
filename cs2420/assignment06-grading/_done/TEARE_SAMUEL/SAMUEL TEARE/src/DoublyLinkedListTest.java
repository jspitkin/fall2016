package assignment06;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test class for DoublyLinkedList.
 * 
 * @author Samuel Teare | UID: u0663592
 */
public class DoublyLinkedListTest {
	
	private DoublyLinkedList<Integer> emptyDLL, singleObjDLL, threeObjDLL, tenObjDLL, tenOnesDLL, doubleObjDLL;
	private Object[] objArrayOf5;
	
	@Before
	public void setUp() throws Exception {
		emptyDLL = new DoublyLinkedList<Integer>();
		
		singleObjDLL = new DoublyLinkedList<Integer>();
		singleObjDLL.addFirst(1);
		
		threeObjDLL = new DoublyLinkedList<Integer>();
		threeObjDLL.addFirst(5);
		threeObjDLL.addFirst(3);
		threeObjDLL.addFirst(1);
		
		tenObjDLL = new DoublyLinkedList<Integer>();
		for(int index = 1; index <= 10; index++) {
			tenObjDLL.addLast(index);
		}
		
		tenOnesDLL = new DoublyLinkedList<Integer>();
		for(int index = 1; index <= 10; index++) {
			tenOnesDLL.addLast(1);
		}
		
		doubleObjDLL = new DoublyLinkedList<Integer>();
		for(int index = 1; index <= 10; index++) {
			doubleObjDLL.addLast(index);
			doubleObjDLL.addLast(index);
		}
		
		objArrayOf5 = new Object[5];
		for(int index = 0; index < 5; index++)  {
			objArrayOf5[index] = index + 1;
		}
		
	}

	@After
	public void tearDown() throws Exception {
	}

	//Empty testing
	@Test
	public void isEmptyTestTrue() {
		assertTrue(emptyDLL.isEmpty());
	}
	
	@Test
	public void isEmptyTestFalse() {
		assertFalse(singleObjDLL.isEmpty());
	}
	
	//Size testing
	@Test
	public void sizeTestEmptyDLL() {
		assertEquals(0, emptyDLL.size());
	}
	
	@Test
	public void sizeTestSingleObjDLL() {
		assertEquals(1, singleObjDLL.size());
	}
	
	@Test
	public void sizeTestSingleObjDLLAddOne() {
		singleObjDLL.addLast(2);
		assertEquals(2, singleObjDLL.size());
	}
	
	//Clear testing
	@Test
	public void clearTestOnTenObjDLL() {
		tenObjDLL.clear();
		assertTrue(tenObjDLL.size() == 0);
		assertTrue(tenObjDLL.isEmpty());
	}
	
	//Testing for addFirst, addLast, and add methods
	@Test
	public void addObjectAtIndex1() {
		threeObjDLL.add(1, 2);
		assertEquals(4, threeObjDLL.size());
		assertEquals(2, (Object) threeObjDLL.get(1));
	}
	
	@Test
	public void addObjectAtIndex2() {
		threeObjDLL.add(2, 4);
		assertEquals(4, threeObjDLL.size());
		assertEquals(4, (Object) threeObjDLL.get(2));
	}
	
	@Test
	public void addTwoObjectsToThreeObjDLL() {
		threeObjDLL.add(1, 2);
		threeObjDLL.add(3, 4);
		assertTrue(threeObjDLL.size() == 5);
		assertArrayEquals(objArrayOf5, threeObjDLL.toArray());
	}
	
	@Test
	public void addLastTestWithThreeObjDLL() {
		threeObjDLL.addLast(6);
		assertArrayEquals(new Object[] {1, 3, 5, 6}, threeObjDLL.toArray());
	}
	
	@Test
	public void addLastTestWithEmptyDLL() {
		emptyDLL.addLast(99);
		assertEquals(emptyDLL.getFirst(), emptyDLL.getLast());
		assertEquals(99, (Object) emptyDLL.get(0));
		assertFalse(emptyDLL.isEmpty());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void addWithIndexBiggerThanDLLSizeException() {
		singleObjDLL.add(9, 9);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void addWithIndexBelowZeroException() {
		singleObjDLL.add(-1, 0);
	}
	
	//Testing for getFirst, getLast, and get methods
	@Test
	public void getFirstWithThreeObjDLL() {
		assertTrue(threeObjDLL.getFirst() == 1);
	}

	@Test(expected=NoSuchElementException.class)
	public void getFirstWithEmptyDLLException() {
		emptyDLL.getFirst();
	}
	
	@Test
	public void getLastWithThreeObjDLL() {
		assertTrue(threeObjDLL.getLast() == 5);
	}
	
	@Test(expected=NoSuchElementException.class)
	public void getLastWithEmptyDLLException() {
		emptyDLL.getLast();
	}

	@Test
	public void getIndex5WithTenObjDLL() {
		assertTrue(tenObjDLL.get(5) == 6);
	}

	@Test(expected=IndexOutOfBoundsException.class)
	public void getWithIndexAtDLLSizeException() {
		singleObjDLL.get(1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void getWithIndexBiggerThanDLLSizeException() {
		singleObjDLL.get(9);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void getWithIndexBelowZeroException() {
		singleObjDLL.get(-1);
	}
	
	//Testing for removeFirst, removeLast, and remove methods
	@Test
	public void removeFirstFromTenObjDLL() {
		tenObjDLL.removeFirst();
		assertTrue(tenObjDLL.size() == 9);
		assertArrayEquals(new Object[] {2, 3, 4, 5, 6, 7, 8, 9, 10}, tenObjDLL.toArray());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void removeFirstWithEmptyDLLException() {
		emptyDLL.removeFirst();
	}
	
	@Test
	public void removeLastFromTenObjDLL() {
		tenObjDLL.removeLast();
		assertTrue(tenObjDLL.size() == 9);
		assertArrayEquals(new Object[] {1, 2, 3, 4, 5, 6, 7, 8, 9}, tenObjDLL.toArray());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void removeLastWithEmptyDLLException() {
		emptyDLL.removeLast();
	}
	
	@Test
	public void removeIndex5FromTenObjDLL() {
		tenObjDLL.remove(5);
		assertTrue(tenObjDLL.size() == 9);
		assertArrayEquals(new Object[] {1, 2, 3, 4, 5, 7, 8, 9, 10}, tenObjDLL.toArray());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeWithIndexBiggerThanDLLSizeException() {
		tenObjDLL.remove(15);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeWithIndexAtDLLSizeException() {
		tenObjDLL.remove(10);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void removeWithIndexBelowZeroSizeException() {
		tenObjDLL.remove(-1);
	}
	
	//Testing for IndexOf and LastIndexOf methods
	@Test
	public void indexOfFirstElement() {
		assertTrue(singleObjDLL.indexOf(1) == 0);
	}
	
	@Test
	public void indexOfElementInEmptyDLL() {
		assertTrue(emptyDLL.indexOf(1) == -1);
	}
	
	@Test
	public void indexOfElementNotContainedInDLL() {
		assertTrue(singleObjDLL.indexOf(99) == -1);
	}
	
	@Test
	public void indexOfElementWithDuplicateInDLL() {
		assertTrue(doubleObjDLL.indexOf(4) == 6);
	}
	
	@Test
	public void indexOfElementWithMultipleDuplicates() {
		assertTrue(tenOnesDLL.indexOf(1) == 0);
	}
	
	@Test
	public void lastIndexOfSingleObjDLL() {
		assertTrue(singleObjDLL.lastIndexOf(1) == 0);
	}
	
	@Test
	public void lastIndexOfElementInEmptyDLL() {
		assertTrue(emptyDLL.lastIndexOf(100) == -1);
	}
	
	@Test
	public void lastIndexOfElementNotContainedInDLL() {
		assertTrue(tenObjDLL.lastIndexOf(11) == -1);
	}
	
	@Test
	public void lastIndexOfElementWithDuplicateInDLL() {
		assertTrue(doubleObjDLL.lastIndexOf(4) == 7);
	}
	
	@Test
	public void lastIndexOfElementWithMultipleDuplicates() {
		assertTrue(tenOnesDLL.lastIndexOf(1) == 9);
	}

}
