package assignment06;

import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

/**
 * JUnit Test class tests individual methods in DoublyLinkedList Class 
 * @author Jana Klopsch (u0854469)   Last Modified 10/2/16
 * course: CS2420
 * Assignment 6
 *
 */
public class DoublyLinkedListTests {

	Node first;
	Node last;

	Integer[] integerList;
	Integer[] emptyIntList;
	Integer[] oneIntList;
	String[] stringList;
	String[] emptyStringList;
	String[] oneStringList;

	String cat;
	String dog;
	String mouse;
	String tree;
	String duck;
	String cow;
	String chicken;


	@Before
	public void setUp() throws Exception {

		cat = "cat";
		dog = "dog";
		mouse = "mouse";
		tree = "tree";
		duck = "duck";
		cow = "cow";
		chicken = "chicken";

		integerList = new Integer[3];
		integerList[0] = 0;
		integerList[1] = 5;
		integerList[2] = 10;

		oneIntList = new Integer[1];
		oneIntList[0] = 23;
		
		emptyIntList = new Integer[0];

		stringList = new String[3];
		stringList[0] = dog;
		stringList[1] = cat;
		stringList[2] = mouse;

		oneStringList = new String[1];
		oneStringList[0] = tree;
		
		emptyStringList = new String[0];

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testToArrayBasic() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(10);
		testList.addFirst(5);
		testList.addFirst(0);
		Object[] finalArray = new Object[3];
		finalArray = (Object[]) testList.toArray();
		Object[] expectedArray = new Object[]{0, 5, 10};
		Assert.assertArrayEquals(finalArray, expectedArray);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		Object[] finalStringArray = new Object[3];
		finalStringArray = (Object[]) testStringList.toArray();
		Object[] expectedStringArray = new Object[]{dog, cat, mouse};
		Assert.assertArrayEquals(finalStringArray, expectedStringArray);
	}
	
	@Test
	public void testToArrayOneItem() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(10);
		Object[] finalArray = new Object[1];
		finalArray = (Object[]) testList.toArray();
		Object[] expectedArray = new Object[]{10};
		Assert.assertArrayEquals(finalArray, expectedArray);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(dog);
		Object[] finalStringArray = new Object[1];
		finalStringArray = (Object[]) testStringList.toArray();
		Object[] expectedStringArray = new Object[]{dog};
		Assert.assertArrayEquals(finalStringArray, expectedStringArray);
	}
	
	@Test
	public void testToArrayEmpty() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		Object[] finalArray = new Object[0];
		finalArray = (Object[]) testList.toArray();
		Object[] expectedArray = new Object[]{};
		Assert.assertArrayEquals(finalArray, expectedArray);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		Object[] finalStringArray = new Object[0];
		finalStringArray = (Object[]) testStringList.toArray();
		Object[] expectedStringArray = new Object[]{};
		Assert.assertArrayEquals(finalStringArray, expectedStringArray);
	}

	@Test
	public void testAddFirstToEmpty() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(23);
		Assert.assertArrayEquals(testList.toArray(), oneIntList);
		Assert.assertTrue(testList.size() == 1);
		Assert.assertFalse(testList.isEmpty());
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(tree);
		Assert.assertArrayEquals(testStringList.toArray(), oneStringList);
		Assert.assertTrue(testStringList.size() == 1);
		Assert.assertFalse(testStringList.isEmpty());
	}

	@Test
	public void testAddFirstBasic() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(10);
		testList.addFirst(5);
		testList.addFirst(0);
		Object[] finalArray = new Object[3];
		finalArray = (Object[]) testList.toArray();
		Assert.assertArrayEquals(finalArray, integerList);
		Assert.assertTrue(testList.size() == 3);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		Object[] finalStringArray = new Object[3];
		finalStringArray = testStringList.toArray();
		Assert.assertArrayEquals(finalStringArray, stringList);
	}

	@Test
	public void testAddLastToEmpty() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addLast(23);
		Assert.assertArrayEquals(testList.toArray(), oneIntList);
		Assert.assertTrue(testList.size() == 1);
		Assert.assertFalse(testList.isEmpty());
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addLast(tree);
		Assert.assertArrayEquals(testStringList.toArray(), oneStringList);
		Assert.assertTrue(testStringList.size() == 1);
		Assert.assertFalse(testStringList.isEmpty());
	}

	@Test
	public void testAddLastBasic() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addLast(0);
		testList.addLast(5);
		testList.addLast(10);	
		Object[] finalArray = new Object[3];
		finalArray = (Object[]) testList.toArray();
		Assert.assertArrayEquals(finalArray, integerList);
		Assert.assertTrue(testList.size() == 3);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addLast(dog);
		testStringList.addLast(cat);
		testStringList.addLast(mouse);
		Object[] finalStringArray = new Object[3];
		finalStringArray = testStringList.toArray();
		Assert.assertArrayEquals(finalStringArray, stringList);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddToEmpty() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.add(3, 9);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.add(4, cat);
	}

	@Test
	public void testAddAverage() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(10);	
		testList.add(1, 5);
		Object[] finalArray = new Object[3];
		finalArray = (Object[]) testList.toArray();
		Assert.assertArrayEquals(finalArray, integerList);
		Assert.assertTrue(testList.size() == 3);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addLast(dog);
		testStringList.addLast(mouse);
		testStringList.add(1, cat);
		Object[] finalStringArray = new Object[3];
		finalStringArray = testStringList.toArray();
		Assert.assertArrayEquals(finalStringArray, stringList);
	}

	@Test
	public void testAddToBeginningOfList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(5);
		testList.addLast(10);
		testList.add(0, 0);
		Object[] finalArray = new Object[3];
		finalArray = (Object[]) testList.toArray();
		Assert.assertArrayEquals(finalArray, integerList);
		Assert.assertTrue(testList.size() == 3);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addLast(cat);
		testStringList.addLast(mouse);
		testStringList.add(0, dog);
		Object[] finalStringArray = new Object[3];
		finalStringArray = testStringList.toArray();
		Assert.assertArrayEquals(finalStringArray, stringList);
	}

	@Test
	public void testAddToEndOfList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.add(2, 10);
		Object[] finalArray = new Object[3];
		finalArray = (Object[]) testList.toArray();
		Assert.assertArrayEquals(finalArray, integerList);
		Assert.assertTrue(testList.size() == 3);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addLast(dog);
		testStringList.addLast(cat);
		testStringList.add(2, mouse);
		Object[] finalStringArray = new Object[3];
		finalStringArray = testStringList.toArray();
		Assert.assertArrayEquals(finalStringArray, stringList);
	}

	@Test
	public void testAddToEmptyList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.add(0, 23);
		Object[] finalArray = new Object[1];
		finalArray = (Object[]) testList.toArray();
		Assert.assertArrayEquals(finalArray, oneIntList);
		Assert.assertTrue(testList.size() == 1);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.add(0, tree);
		Object[] finalStringArray = new Object[3];
		finalStringArray = testStringList.toArray();
		Assert.assertArrayEquals(finalStringArray, oneStringList);
		Assert.assertTrue(testStringList.size() == 1);
	}

	@Test
	public void testGetFirstAndLastBasic() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.addLast(10);
		Object first = testList.getFirst();
		Object last = testList.getLast();
		Object data = testList.get(1);
		Assert.assertTrue(data.equals((Object) 5));
		Assert.assertTrue(first.equals((Object) 0));
		Assert.assertTrue(last.equals((Object) 10));
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addLast(dog);
		testStringList.addLast(cat);
		testStringList.addLast(mouse);
		Object firstString = testStringList.getFirst();
		Object lastString = testStringList.getLast();
		Object middle = testStringList.get(1);
		Assert.assertTrue(firstString.equals((Object) dog));
		Assert.assertTrue(lastString.equals((Object) mouse));
		Assert.assertTrue(middle.equals((Object) cat));
	}

	@Test
	public void testGetFirstAndLastSingleList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(23);
		Object first = testList.getFirst();
		Object last = testList.getLast();
		Assert.assertTrue(first.equals((Object) 23));
		Assert.assertTrue(last.equals(first));
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(tree);
		Object firstString = testStringList.getFirst();
		Object lastString = testStringList.getLast();
		Assert.assertTrue(firstString.equals((Object) tree));
		Assert.assertTrue(firstString.equals(lastString));
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetEmptyIntList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.get(0);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testGetFirstEmptyIntList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.getFirst();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testGetLastEmptyIntList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.getLast();
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetEmptyStringList() {
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.get(0);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testGetFirstEmptyStringList() {
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.getFirst();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testGetLastEmptyStringList() {
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.getLast();
	}
	
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetInvalidIndexIntList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.addLast(10);
		testList.get(9);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetInvalidIndexStringList(){
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		testStringList.addFirst(mouse);
		testStringList.get(5);
	}
	
	@Test
	public void testGetFirstAndLastNullItem() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(null);
		testList.addLast(null);
		Object data = testList.get(1);
		Object first = testList.getFirst();
		Object last = testList.getLast();
		Assert.assertNull(data);
		Assert.assertNull(first);
		Assert.assertNull(last);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(null);
		testStringList.addLast(null);
		Object dataString = testStringList.get(1);
		Object firstString = testStringList.getFirst();
		Object lastString = testStringList.getLast();
		Assert.assertNull(dataString);
		Assert.assertNull(firstString);
		Assert.assertNull(lastString);
	}
	
	@Test
	public void testRemoveBasic() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.addLast(10);
		testList.add(3, 17);
		testList.addFirst(19);
		testList.addLast(8);
		testList.removeFirst();
		testList.removeLast();
		testList.remove(3);
		Object[] finalArray = new Object[3];
		finalArray = (Object[]) testList.toArray();
		Assert.assertArrayEquals(finalArray, integerList);
		Assert.assertTrue(testList.size() == 3);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		testStringList.add(1, chicken);
		testStringList.addFirst(cow);
		testStringList.addLast(duck);
		testStringList.removeFirst();
		testStringList.removeLast();
		testStringList.remove(1);
		Object[] finalStringArray = new Object[3];
		finalStringArray = testStringList.toArray();
		Assert.assertArrayEquals(finalStringArray, stringList);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeFromSingleIntList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(8);
		testList.removeFirst();
		Assert.assertTrue(testList.size() == 0);
		testList.getFirst();		
	}
	
	@Test (expected = NoSuchElementException.class)
	public void removeFromSingleStringList() {
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.removeFirst();
		Assert.assertTrue(testStringList.size() == 0);
		testStringList.getFirst();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveFirstEmptyIntList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.removeFirst();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveLastEmptyIntList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.removeLast();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveEmptyIntList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.remove(0);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveFirstEmptyStringList() {
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.removeFirst();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveLastEmptyStringList() {
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.removeLast();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveEmptyStringList() {
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.remove(0);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveInvalidIndex() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.addLast(10);
		testList.remove(19);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveInvalidIndexStrings() {
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		testStringList.remove(8);
	}
	
	@Test
	public void testIndexOfBasic() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.addLast(10);
		testList.addLast(5);
		Assert.assertTrue(testList.indexOf((Object) 0) == 0);
		Assert.assertTrue(testList.indexOf((Object) 5) == 1);
		Assert.assertTrue(testList.indexOf((Object) 10) == 2);
		Assert.assertTrue(testList.indexOf((Object) 25) == -1);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		testStringList.addLast(dog);
		Assert.assertTrue(testStringList.indexOf(mouse) == 2);
		Assert.assertTrue(testStringList.indexOf(dog) == 0);
		Assert.assertTrue(testStringList.indexOf(cat) == 1);
		Assert.assertTrue(testStringList.indexOf(cow) == -1);
	}
	
	@Test
	public void testLastIndexOfBasic() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.addLast(10);
		testList.addLast(5);
		Assert.assertTrue(testList.lastIndexOf((Object) 0) == 0);
		Assert.assertTrue(testList.lastIndexOf((Object) 5) == 3);
		Assert.assertTrue(testList.lastIndexOf((Object) 10) == 2);
		Assert.assertTrue(testList.lastIndexOf((Object) 25) == -1);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		testStringList.addLast(dog);
		Assert.assertTrue(testStringList.lastIndexOf(mouse) == 2);
		Assert.assertTrue(testStringList.lastIndexOf(dog) == 3);
		Assert.assertTrue(testStringList.lastIndexOf(cat) == 1);
		Assert.assertTrue(testStringList.lastIndexOf(cow) == -1);
	}
	
	@Test
	public void testIndexOfSingleList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(23);
		Assert.assertTrue(testList.indexOf((Object) 23) == 0);
		Assert.assertTrue(testList.lastIndexOf((Object) 23) == 0);
		Assert.assertTrue(testList.indexOf((Object) 90) == -1);
		Assert.assertTrue(testList.lastIndexOf((Object) 90) == -1);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(tree);
		Assert.assertTrue(testStringList.indexOf(tree) == 0);
		Assert.assertTrue(testStringList.lastIndexOf(tree) == 0);
		Assert.assertTrue(testStringList.indexOf(cow) == -1);
		Assert.assertTrue(testStringList.lastIndexOf(cow) == -1);
	}
	
	@Test
	public void testIndexOfEmptyList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		Assert.assertTrue(testList.indexOf((Object) 0) == -1);
		Assert.assertTrue(testList.lastIndexOf((Object)54) == -1);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		Assert.assertTrue(testStringList.indexOf(cow) == -1);
		Assert.assertTrue(testStringList.lastIndexOf(duck) == -1);
	}
	
	@Test
	public void testIndexOfNullItem() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.addLast(10);
		testList.addLast(null);
		testList.addLast(null);
		Assert.assertTrue(testList.indexOf((Object) null) == 3);
		Assert.assertTrue(testList.lastIndexOf((Object) null) == 4);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		testStringList.addLast(null);
		testStringList.addFirst(null);
		Assert.assertTrue(testStringList.indexOf(null) == 0);
		Assert.assertTrue(testStringList.lastIndexOf(null) == 4);
	}
	
	@Test
	public void testClearBasic() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.addLast(5);
		testList.addLast(10);
		testList.clear();
		Assert.assertArrayEquals(testList.toArray(), emptyIntList);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.addFirst(cat);
		testStringList.addFirst(dog);
		testStringList.clear();
		Assert.assertArrayEquals(testStringList.toArray(), emptyStringList);
	}
	
	@Test
	public void testClearSingleList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.addFirst(0);
		testList.clear();
		Assert.assertArrayEquals(testList.toArray(), emptyIntList);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.addFirst(mouse);
		testStringList.clear();
		Assert.assertArrayEquals(testStringList.toArray(), emptyStringList);
	}

	@Test
	public void testClearEmptyList() {
		DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
		testList.clear();
		Assert.assertArrayEquals(testList.toArray(), emptyIntList);
		
		DoublyLinkedList<String> testStringList = new DoublyLinkedList<String>();
		testStringList.clear();
		Assert.assertArrayEquals(testStringList.toArray(), emptyStringList);
	}
	
	
}
