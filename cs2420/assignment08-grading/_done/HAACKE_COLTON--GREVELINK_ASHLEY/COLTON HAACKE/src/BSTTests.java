package assignment08;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test cases for the SpellChecker and BinarySearchTree classes
 * @author Colton Haacke (u0935270) and Ashley Grevelink (u0749357)
 *
 */
public class BSTTests {
	
	BinarySearchTree<Integer> testTree;
	BinarySearchTree<Integer> emptyTree;
	SpellChecker exampleDictionary;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testTree = new BinarySearchTree<Integer>();
		for (int count = 0; count < 100; count++) {
			testTree.add(count);
		}
		emptyTree = new BinarySearchTree<Integer>();
		
		exampleDictionary = new SpellChecker(new File("dictionary.txt"));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addNotExists() {
		assertTrue(testTree.add(101));
		assertTrue(testTree.contains(101));
	}
	
	@Test
	public void addExists() {
		assertFalse(testTree.add(0));
		assertTrue(testTree.contains(0));
	}
	
	@Test (expected = NullPointerException.class)
	public void addNull() {
		testTree.add(null);
	}

	@Test
	public void addAllNotExistsAll() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 101; count < 200; count++){
			array.add(count);
		}
		
		assertTrue(testTree.addAll(array));
		assertTrue(testTree.containsAll(array));
	}
	
	@Test
	public void addAllNotExistsSome() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 50; count < 150; count++){
			array.add(count);
		}
		
		assertTrue(testTree.addAll(array));
		assertTrue(testTree.containsAll(array));
	}
	
	@Test
	public void addAllNotExistsLast() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 1; count < 101; count++){
			array.add(count);
		}
		
		assertTrue(testTree.addAll(array));
		assertTrue(testTree.containsAll(array));
	}
	
	@Test
	public void addAllExistsAll() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 0; count < 100; count++){
			array.add(count);
		}
		
		assertFalse(testTree.addAll(array));
		assertTrue(testTree.containsAll(array));
	}
	
	@Test (expected = NullPointerException.class)
	public void addAllNullFirst() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(null);
		for (int count = 101; count < 200; count++){
			array.add(count);
		}
		testTree.addAll(array);
	}
	
	@Test (expected = NullPointerException.class)
	public void addAllNullLast() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 101; count < 200; count++){
			array.add(count);
		}
		array.add(null);
		testTree.addAll(array);
	}

	@Test
	public void clearNotEmpty() {
		testTree.clear();
		assertTrue(testTree.isEmpty());
	}
	
	@Test
	public void clearEmpty() {
		emptyTree.clear();
		assertTrue(emptyTree.isEmpty());
	}
	
	@Test
	public void containsNotExists() {
		assertFalse(testTree.contains(101));
	}
	
	@Test
	public void containsExists() {
		assertTrue(testTree.contains(0));
	}
	
	@Test (expected = NullPointerException.class)
	public void containsNull() {
		testTree.contains(null);
	}

	@Test
	public void containsAllNotExistsAll() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 100; count < 200; count++){
			array.add(count);
		}
		
		assertFalse(testTree.containsAll(array));
	}
	
	@Test
	public void containsAllNotExistsSome() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 50; count < 150; count++){
			array.add(count);
		}
		
		assertFalse(testTree.containsAll(array));
	}
	
	@Test
	public void containsAllNotExistsLast() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 1; count < 101; count++){
			array.add(count);
		}
		
		assertFalse(testTree.containsAll(array));
	}
	
	@Test
	public void containsAllExistsAll() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 0; count < 100; count++){
			array.add(count);
		}
		
		assertTrue(testTree.containsAll(array));
	}
	
	@Test (expected = NullPointerException.class)
	public void containsAllNullFirst() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(null);
		for (int count = 101; count < 200; count++){
			array.add(count);
		}
		testTree.containsAll(array);
	}
	
	@Test (expected = NullPointerException.class)
	public void containsAllNullLast() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 101; count < 200; count++){
			array.add(count);
		}
		array.add(null);
		testTree.containsAll(array);
	}

	@Test
	public void firstExists() {
		assertEquals(0, (int) testTree.first());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void firstNotExists() {
		emptyTree.first();
	}

	@Test
	public void isEmptyTrue() {
		assertTrue(emptyTree.isEmpty());
	}
	
	@Test
	public void isEmptyFalse() {
		assertFalse(testTree.isEmpty());
	}

	@Test
	public void lastExists() {
		assertEquals(99, (int) testTree.last());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void lastNotExists() {
		emptyTree.last();
	}

	@Test
	public void removeNotExists() {
		assertFalse(testTree.remove(100));
		assertFalse(testTree.contains(100));
	}
	
	@Test
	public void removeExists() {
		assertTrue(testTree.remove(0));
		assertFalse(testTree.contains(0));
	}
	
	@Test (expected = NullPointerException.class)
	public void removeNull() {
		testTree.remove(null);
	}
	
	//TODO Test for different children cases
	@Test
	public void removeTwoChildren() {
		BinarySearchTree<Integer> newTree = new BinarySearchTree<Integer>();
		newTree.add(10);
		newTree.add(5);
		newTree.add(2);
		newTree.add(7);
		newTree.add(15);
		newTree.add(12);
		newTree.add(17);
		for (int count = 0; count < 20; count++) {
			newTree.add(count);
		}
		
		assertTrue(newTree.remove(5));
		assertFalse(newTree.contains(5));
	}

	@Test
	public void removeAllNotExistsAll() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 100; count < 200; count++){
			array.add(count);
		}
		
		assertFalse(testTree.removeAll(array));
	}
	
	@Test
	public void removeAllNotExistsSome() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 50; count < 150; count++){
			array.add(count);
		}
		
		assertTrue(testTree.removeAll(array));
	}
	
	@Test
	public void removeAllNotExistsLast() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 1; count < 101; count++){
			array.add(count);
		}
		
		assertTrue(testTree.removeAll(array));
	}
	
	@Test
	public void removeAllExistsAll() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 0; count < 100; count++){
			array.add(count);
		}
		
		assertTrue(testTree.removeAll(array));
	}
	
	@Test (expected = NullPointerException.class)
	public void removeAllNullFirst() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		array.add(null);
		for (int count = 101; count < 200; count++){
			array.add(count);
		}
		testTree.removeAll(array);
	}
	
	@Test (expected = NullPointerException.class)
	public void removeAllNullLast() {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int count = 101; count < 200; count++){
			array.add(count);
		}
		array.add(null);
		testTree.removeAll(array);
	}

	@Test
	public void sizeEmpty() {
		assertEquals(0, emptyTree.size());
	}
	
	@Test
	public void sizeNotEmpty() {
		assertEquals(100, testTree.size());
	}

	@Test
	public void  toArrayListEmpty() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		assertEquals(testArray, emptyTree.toArrayList());
	}
	
	@Test
	public void toArrayListNotEmpty() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		for (int count = 0; count < 100; count++) {
			testArray.add(count);
		}
		assertEquals(testArray, testTree.toArrayList());
	}
	
	@Test
	public void addToDictionaryExists() {
		exampleDictionary.addToDictionary("pineapple");
		assertTrue(exampleDictionary.contains("pineapple"));
	}
	
	@Test
	public void addToDictionaryNotExists() {
		exampleDictionary.addToDictionary("burrito");
		assertTrue(exampleDictionary.contains("burrito"));
	}
	
	@Test (expected = NullPointerException.class)
	public void addToDictionaryNull() {
		exampleDictionary.addToDictionary(null);
	}
	
	@Test
	public void removeFromDictionaryExists() {
		exampleDictionary.removeFromDictionary("pineapple");
		exampleDictionary.removeFromDictionary("pineapple");
	}
	
	@Test
	public void removeFromDictionaryNotExists() {
		exampleDictionary.removeFromDictionary("burrito");
		assertFalse(exampleDictionary.contains("burrito"));
	}
	
	@Test (expected = NullPointerException.class)
	public void removeFromDictionaryNull() {
		exampleDictionary.removeFromDictionary(null);
	}
	
	@Test
	public void spellCheckNoneMisspelled() {
		File file = new File("hello_world.txt");
		assertEquals(new ArrayList<String>(), exampleDictionary.spellCheck(file));
	}
	
	@Test
	public void spellCheckMisspelled() {
		File file = new File("good_luck.txt");
		ArrayList<String> misspell = new ArrayList<String>();
		misspell.add("bst");
		assertEquals(misspell, exampleDictionary.spellCheck(file));
	}
}
