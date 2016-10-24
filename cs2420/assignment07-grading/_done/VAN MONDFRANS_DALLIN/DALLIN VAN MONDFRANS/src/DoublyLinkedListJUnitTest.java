/**
 * Assignment 6 - DoublyLinkedList
 * @author Dallin Van Mondfrans
 * uID: u0717113
 * Date: October 5, 2016
 */

package assignment06;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoublyLinkedListJUnitTest {

	DoublyLinkedList<Integer> list;
	DoublyLinkedList<Integer> intList;
	static ArrayList<Integer> intArray;
	DoublyLinkedList<String> stringList;
	
	@BeforeClass
	public static void setUpBeforeClass() { 
		intArray = new ArrayList<Integer>();
		Random rand = new Random();
		for(int index = 0; index < 300; index++) {
			intArray.add(rand.nextInt(50));
		}
	}
	
	@Before
	public void setUp() throws Exception {
		list = new DoublyLinkedList<Integer>();
		stringList = new DoublyLinkedList<String>();
		intList = new DoublyLinkedList<Integer>();
		for(int index = 0; index < intArray.size(); index++) {
			intList.add(index, intArray.get(index));
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	// Tests on small lists (size 3 or smaller) -------------------------------
	@Test
	public void testSizeOfEmptyList() {
		assertTrue(list.size() == 0);
	}
	
	@Test 
	public void testHeadAndTailOfEmptyListEqualsNull() {
		try {
			list.get(0);
		} catch(Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void addFristElementUsingAddFirstMethod() {
		list.addFirst(1);
	}
	
	@Test
	public void addFirstElementUsingAddMethod() {
		list.add(0, 1);
		assertTrue(list.getFirst().equals(list.getLast()) && list.size() == 1);
	}
	
	@Test
	public void addTwoElements() {
		list.add(0, 1);
		list.add(1, 2);
		assertTrue(list.getLast().equals(2) && list.size() == 2);
	}
	
	@Test
	public void addThirdElementInMiddle() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		assertTrue(list.get(1).equals(3));
	}
	
	@Test
	public void removeFirst() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		assertTrue(list.removeFirst().equals(1));
	}
	
	@Test
	public void removeFirstNewHeadAndSize() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		list.removeFirst();
		assertTrue(list.size() == 2 && list.getFirst().equals(3) && list.getLast().equals(2));
	}
	
	@Test
	public void removeFirstWithOnlyOneElement() {
		list.add(0, 1);
		list.removeFirst();
		try {
			list.get(0);
		} catch(Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void removeLast() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		list.removeLast();
		assertTrue(list.size() == 2 && list.getFirst().equals(1) && list.getLast().equals(3));
	}
	
	@Test
	public void removeLastWithNoElements() {
		try {
			list.removeLast();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}
	
	@Test
	public void removeMiddleOfThree() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		list.remove(1);
		assertTrue(list.size() == 2 && list.getFirst().equals(1) && list.getLast().equals(2));
	}
	
	@Test
	public void removeFirstUsingRemove() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		list.remove(0);
		assertTrue(list.size() == 2 && list.getFirst().equals(3) && list.getLast().equals(2));
	}
	
	@Test 
	public void removeLastUsingRemove() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		list.remove(2);
		assertTrue(list.size() == 2 && list.getFirst().equals(1) && list.getLast().equals(3));
	}
	
	@Test
	public void getIndexOf() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		assertTrue(list.indexOf(1) == 0 && list.indexOf(3) == 1 && list.indexOf(2) == 2);
	}
	
	@Test
	public void getIndexOfElementsNotInList() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		assertTrue(list.indexOf(4) < 0 && list.indexOf(0) < 0);
	}
	
	@Test
	public void getLastIndexOf() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(2, 1);
		assertTrue(list.lastIndexOf(1) == 2);
	}
	
	@Test
	public void isEmptyOnNonEmptyList() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		assertFalse(list.isEmpty());
	}
	
	@Test
	public void isEmptyOnEmptyArray() {
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void clear() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		list.clear();
		assertTrue(list.isEmpty());
	}
	
	@Test
	public void listToArray() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		assertTrue( Arrays.equals(list.toArray(), new Object[]{1, 3, 2}) );
	}
	
	// Tests on a large list (size 300) -------------------------------------
	@Test
	public void testSizeOfEmptyListLargeList() {
		assertTrue(intList.size() == 300);
	}
	
	@Test 
	public void testHeadAndTailOfEmptyListEqualsNullLargeList() {
		intList.clear();
		try {
			intList.get(0);
		} catch(Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void addFristElementUsingAddFirstMethodLargeList() {
		intList.addFirst(100);
		assertTrue(intList.getFirst().equals(100) && intList.size() == 301);
	}
	
	@Test
	public void addFirstElementUsingAddMethodLargeList() {
		intList.add(0, 100);
		assertTrue(intList.getFirst().equals(100) && intList.size() == 301);
	}
	
	@Test
	public void addOneElementInMiddleLargeList() {
		intList.add(100, 200);
		assertTrue(intList.get(100).equals(200) && intList.size() == 301);
	}
	
	@Test
	public void addLastElementLargeList() {
		intList.addLast(200);
		assertTrue(intList.get(intList.size()-1).equals(200));
	}
	
	@Test
	public void removeFirstLargeList() {
		assertTrue(intList.removeFirst().equals(intArray.get(0)) && intList.size() == 299);
	}
	
	@Test
	public void removeFirstWithOnlyOneElementLargeList() {
		intList.clear();
		intList.add(0, 1);
		intList.removeFirst();
		try {
			intList.get(0);
		} catch(Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void removeLastLargeList() {
		intList.removeLast();
		assertTrue(intList.size() == 299 && intList.getLast().equals(intArray.get(intArray.size()-2)));
	}
	
	@Test
	public void removeMiddleElement() {
		intList.remove(150);
		assertTrue(intList.size() == 299 && !intList.get(150).equals(intArray.get(150)));
	}
	
	@Test
	public void removeFirstUsingRemoveForLargeList() {
		intList.remove(0);
		assertTrue(intList.size() == 299 && intList.getFirst().equals(intArray.get(1)) );
	}
	
	@Test 
	public void removeLastUsingRemoveForLargeList() {
		intList.remove(299);
		assertTrue(intList.size() == 299 && intList.getLast().equals(intArray.get(intArray.size()-2)));
	}
	
	@Test
	public void getIndexOfLargeList() {
		intList.addFirst(51);
		intList.addLast(52);
		intList.add(132, 53);
		assertTrue(intList.indexOf(51) == 0
				&& intList.indexOf(52) == 302 
				&& intList.indexOf(53) == 132);
	}
	
	@Test
	public void getIndexOfElementsNotInListLargeList() {
		assertTrue(intList.indexOf(51) < 0 && intList.indexOf(52) < 0);
	}
	
	@Test
	public void getLastIndexOfLargeList() {
		intList.add(0, 51);
		intList.add(159, 51);
		intList.add(160, 51);
		assertTrue(intList.lastIndexOf(51) == 160);
	}
	
	@Test
	public void isEmptyLargeList() {
		intList.clear();
		assertTrue(intList.isEmpty());
	}
	
	@Test
	public void isEmptyOnNonEmptyListLargeList() {
		assertFalse(intList.isEmpty());
	}
	
	@Test
	public void listToArrayLargeList() {
		assertTrue( Arrays.equals(intList.toArray(), intArray.toArray()) );
	}
	
	// Tests on Strings -----------------------------------------------------
	@Test
	public void testSizeOfStringList() {
		stringList.addLast("Apple");
		stringList.addLast("Boy");
		stringList.addLast("Cat");
		stringList.addLast("Dog");
		stringList.addLast("Elephant");
		assertTrue(stringList.size() == 5);
	}
	
	@Test
	public void addFristElementUsingAddFirstMethodStringList() {
		stringList.addFirst("Apple");
		assertTrue(stringList.getFirst().equals("Apple") && stringList.size() == 1);
	}
	
	@Test
	public void addFirstElementUsingAddMethodStringList() {
		stringList.add(0, "Apple");
		assertTrue(stringList.getFirst().equals("Apple") && stringList.size() == 1);
	}
	
	@Test
	public void addOneElementInMiddleStringList() {
		stringList.add(0, "Apple");
		stringList.add(1, "Cat");
		stringList.add(1, "Boy");
		assertTrue(stringList.get(1).equals("Boy") && stringList.size() == 3);
	}
	
	@Test
	public void addLastElementStringList() {
		stringList.add(0, "Apple");
		stringList.add(1, "Cat");
		stringList.add(1, "Boy");
		stringList.addLast("Dog");
		assertTrue(stringList.get(stringList.size()-1).equals("Dog"));
	}
	
	@Test
	public void removeFirstStringList() {
		stringList.add(0, "Apple");
		stringList.add(1, "Cat");
		stringList.add(1, "Boy");
		stringList.addLast("Dog");
		assertTrue(stringList.removeFirst().equals("Apple") && stringList.size() == 3);
	}
	
	@Test
	public void removeFirstWithOnlyOneElementStringList() {
		stringList.add(0, "Apple");
		stringList.removeFirst();
		try {
			stringList.get(0);
		} catch(Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void removeLastStringList() {
		stringList.add(0, "Apple");
		stringList.add(1, "Cat");
		stringList.add(1, "Boy");
		stringList.addLast("Dog");
		stringList.removeLast();
		assertTrue(stringList.size() == 3 && stringList.getLast().equals("Cat"));
	}
	
	@Test
	public void removeMiddleElementStringList() {
		stringList.addLast("Apple");
		stringList.addLast("Boy");
		stringList.addLast("Cat");
		stringList.addLast("Dog");
		stringList.addLast("Elephant");
		stringList.remove(2);
		assertTrue(stringList.size() == 4 && !stringList.get(2).equals("Cat"));
	}
	
	@Test
	public void removeFirstUsingRemoveForStringList() {
		stringList.addLast("Apple");
		stringList.addLast("Boy");
		stringList.addLast("Cat");
		stringList.addLast("Dog");
		stringList.addLast("Elephant");
		stringList.remove(0);
		assertTrue(stringList.size() == 4 && stringList.getFirst().equals("Boy") );
	}
	
	@Test 
	public void removeLastUsingRemoveForStringList() {
		stringList.addLast("Apple");
		stringList.addLast("Boy");
		stringList.addLast("Cat");
		stringList.addLast("Dog");
		stringList.addLast("Elephant");
		stringList.remove(4);
		assertTrue(stringList.size() == 4 && stringList.getLast().equals("Dog"));
	}
	
	@Test
	public void getIndexOfStringList() {
		stringList.addLast("Apple");
		stringList.addLast("Boy");
		stringList.addLast("Cat");
		stringList.addLast("Dog");
		stringList.addLast("Elephant");
		assertTrue(stringList.indexOf("Elephant") == 4
				&& stringList.indexOf("Cat") == 2 
				&& stringList.indexOf("Boy") == 1);
	}
	
	@Test
	public void getIndexOfElementsNotInListStringList() {
		stringList.addLast("Apple");
		stringList.addLast("Boy");
		stringList.addLast("Cat");
		stringList.addLast("Dog");
		stringList.addLast("Elephant");
		assertTrue(stringList.indexOf("cat") < 0 && stringList.indexOf("zebra") < 0);
	}
	
	@Test
	public void getLastIndexOfStringList() {
		stringList.addLast("Apple");
		stringList.addLast("Apple");
		stringList.addLast("Cat");
		stringList.addLast("Dog");
		stringList.addLast("Cat");
		assertTrue(stringList.lastIndexOf("Apple") == 1 && stringList.lastIndexOf("Cat") == 4);
	}
	
	@Test
	public void isEmptyStringList() {
		assertTrue(stringList.isEmpty());
	}
	
	@Test
	public void isEmptyOnNonEmptyListStringList() {
		stringList.addLast("Apple");
		assertFalse(stringList.isEmpty());
	}
	
	@Test
	public void listToArrayStringList() {
		stringList.addLast("Apple");
		stringList.addLast("Boy");
		stringList.addLast("Cat");
		stringList.addLast("Dog");
		stringList.addLast("Elephant");
		assertTrue( Arrays.equals(stringList.toArray(), new String[]{"Apple", "Boy", "Cat", "Dog", "Elephant"}) );
	}
	
	// Iterator tests -------------------------------------------------
	@Test
	public void enhancedForLoop(){
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		int sum = 0;
		for( Integer el : list) {
			sum += el;
		}
		assertTrue(sum == 6);
	}
	
	@Test
	public void removeByIteratorWithoutCallingNext() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		Iterator<Integer> iter = list.iterator();
		try{
			iter.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	@Test
	public void removeByIterator() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		Iterator<Integer> iter = list.iterator();
		iter.next();
		iter.remove();
		assertTrue(list.size() == 2);
	}
	
	@Test
	public void removeByIteratorTwiceInARow() {
		list.add(0, 1);
		list.add(1, 2);
		list.add(1, 3);
		Iterator<Integer> iter = list.iterator();
		try {
			iter.next();
			iter.remove();
			iter.remove();
		} catch(Exception e) {
			assertTrue(e instanceof IllegalStateException);
		}
	}
	
	@Test
	public void nextWithNoElements() {
		Iterator<Integer> iter = list.iterator();
		try {
			iter.next();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}
	
}
