package assignment08;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class BinarySearchTreeTest {
	BinarySearchTree<Integer> intBST = new BinarySearchTree<Integer>();
	BinarySearchTree<String> stringBST = new BinarySearchTree<String>();
	BinarySearchTree<Integer> newIntBST = new BinarySearchTree<Integer>();

	@Test
	public void testAddmethod() {
		intBST.add(1);
		intBST.add(2);
		assertTrue(intBST.size == 2);
	}

	@Test(expected = NullPointerException.class)
	public void testAddNullException() {
		intBST.add(null);
	}

	@Test
	public void testAddAll() {
		ArrayList<Integer> biglist = new ArrayList<Integer>();
		biglist.add(1);
		biglist.add(2);
		biglist.add(2);
		biglist.add(2);
		biglist.add(1);
		biglist.add(2);
		intBST.addAll(biglist);
		assertTrue(intBST.size == 6);
	}

	@Test
	public void testClearMethod() {
		BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
		testBST.add(1);
		assertEquals(1, testBST.size());
		testBST.clear();
		assertEquals(0, testBST.size());

	}

	@Test
	public void testAddAllStrings() {
		ArrayList<String> bigListOfStrings = new ArrayList<String>();
		stringBST.add("the");
		stringBST.add("quick");
		stringBST.add("brown");
		stringBST.add("fox");
		stringBST.add("jumped");
		stringBST.add("over");
		bigListOfStrings.add("the");
		bigListOfStrings.add("lazy");
		bigListOfStrings.add("dog");
		assertEquals(true, stringBST.contains("fox"));
		assertEquals(false, stringBST.contains("lazy"));
		assertEquals(false, stringBST.contains("dog"));

		stringBST.addAll(bigListOfStrings);
		assertEquals(true, stringBST.contains("lazy"));
		assertEquals(true, stringBST.contains("dog"));

	}

	@Test(expected = NullPointerException.class)
	public void testAddAllNullException() {
		BinarySearchTree<Integer> tempBST = new BinarySearchTree<Integer>();
		ArrayList<Integer> nullList = new ArrayList<Integer>();
		nullList.add(null);
		tempBST.addAll(nullList);
	}

	@Test
	public void testContainsMethod() {
		BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
		testBST.add(1);
		assertTrue(testBST.contains(1));
		assertFalse(testBST.contains(2));
		testBST.clear();
		assertFalse(testBST.contains(2));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsException() {
		BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
		testBST.contains(null);
	}

	@Test
	public void testFirstMethod() {
		BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
		testBST.add(1);
		testBST.add(2);
		testBST.add(3);
		assertEquals(1, (int) testBST.first());
	}

	@Test
	public void testContainsAllMethod() {
		BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(1);
		test.add(2);
		test.add(3);

		assertEquals(true, testBST.addAll(test));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsAllException() {
		BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
		ArrayList<Integer> test = new ArrayList<Integer>();
		test.add(null);
		testBST.addAll(test);
	}

	@Test
	public void testIsEmptyMethod() {
		BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
		assertTrue(testBST.isEmpty());
	}

	@Test
	public void testSizeMethod() {
		BinarySearchTree<Integer> testBST = new BinarySearchTree<Integer>();
		testBST.add(1);
		assertEquals(1, testBST.size());
	}

	@Test
	public void testLastMethod() {
		BinarySearchTree<Integer> newTestingBST = new BinarySearchTree<Integer>();
		newTestingBST.add(1);
		newTestingBST.add(2);
		newTestingBST.add(3);
		assertEquals(3, (int) newTestingBST.last());
	}

	@Test
	public void testRemoveMethod() {

		newIntBST.add(1);
		newIntBST.add(2);
		newIntBST.add(3);
		newIntBST.add(4);
		newIntBST.remove(3);
		newIntBST.remove(2);
		assertEquals(false, newIntBST.contains(3));
		assertEquals(false, newIntBST.contains(2));
	}

}