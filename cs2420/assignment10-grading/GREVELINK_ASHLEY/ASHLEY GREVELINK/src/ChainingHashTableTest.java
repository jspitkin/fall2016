package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

/**
 * @author Ashley Grevelink u0749357
 */
public class ChainingHashTableTest {
	
	@Test
	public void testCollisions() {
		ChainingHashTable test = new ChainingHashTable(14,
				new BadHashFunctor());
		test.add("Hello2");
		test.add("Goodbye2");
		test.add("Hello");
		test.add("Goodbye");
		test.add("Hello3");
		test.add("Goodbye3");
		test.add("Hello6");
		test.add("Hello7");
		//System.out.print("Collisions: " + test.collisions());
		assertTrue(test.collisions() == 7);
	}
	
	@Test
	public void testWorkingBasics() {
		ChainingHashTable test = new ChainingHashTable(14,
				new BadHashFunctor());
		test.contains("a");
		test.add("Hello");
		test.add("Goodbye");
		boolean result = test.contains("Hello");
		assertTrue(result);
	}

	@Test(expected = NullPointerException.class)
	public void testContainsNullEmptyHashTable() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		String nullString = null;
		test.contains(nullString);
	}

	@Test(expected = NullPointerException.class)
	public void testContainsNull() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		test.add("Goodbye");
		String nullString = null;
		test.contains(nullString);
	}

	@Test(expected = NullPointerException.class)
	public void testAddNullEmptyHashTable() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		String nullString = null;
		test.add(nullString);
	}

	@Test(expected = NullPointerException.class)
	public void testAddNull() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		test.add("Hallo");
		String nullString = null;
		test.add(nullString);
	}

	@Test(expected = NullPointerException.class)
	public void testAddAllNull() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		ArrayList<String> emptylist = null;
		test.addAll((Collection<? extends String>) emptylist);
	}

	@Test
	public void testAddAllEmptySet() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		ArrayList<String> emptylist = new ArrayList<String>();
		boolean result = test.addAll((Collection<? extends String>) emptylist);
		assertFalse(result);
	}
	
	@Test
	public void testDoubleSizeAndRehash() {
		ChainingHashTable test = new ChainingHashTable(4,
				new BadHashFunctor());
		assertTrue(test.capacity() == 5);
		test.add("Hello2");
		test.add("Goodbye2");
		test.add("Hello");
		test.add("Goodbye");
		test.contains("Hello2");
		test.contains("Goodbye2");
		test.contains("Hello");
		test.contains("Goodbye");
		test.add("Hello3");
		test.add("Goodbye3");
		test.add("Hello6");
		test.add("Hello7");
		test.contains("Hello3");
		test.contains("Goodbye3");
		test.contains("Hello6");
		test.contains("Hello7");
	}

	@Test
	public void testClear() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		test.add("Hello");
		assertTrue(test.contains("Hello"));
		test.clear();
		assertFalse(test.contains("Hello"));
	}

	@Test
	public void testContainsAll() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		test.add("Hello");
		test.add("Goodbye");
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Goodbye");
		boolean result = test.containsAll((Collection<? extends String>) list);
		assertTrue(result);
	}

	@Test(expected = NullPointerException.class)
	public void testContainsAllNull() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		test.add("Hello");
		test.add("Goodbye");
		ArrayList<String> list = null;
		test.containsAll((Collection<? extends String>) list);
	}

	@Test(expected = NullPointerException.class)
	public void testContainsAllNullEmptyHashTable() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		ArrayList<String> list = null;
		test.containsAll((Collection<? extends String>) list);
	}

	@Test
	public void testAddAllWithExtras() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		test.add("Hello");
		ArrayList<String> list = new ArrayList<String>();
		list.add("Hello");
		list.add("Goodbye");
		boolean result = test.addAll((Collection<? extends String>) list);
		assertTrue(result);
	}

	@Test
	public void testContainsAllFalse() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		test.add("Hello");
		ArrayList<String> list = new ArrayList<String>();
		list.add("Howdy");
		list.add("Goodbye");
		boolean result = test.containsAll((Collection<? extends String>) list);
		assertFalse(result);
	}

	@Test
	public void testContainsAllEmptySet() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		test.add("Hello");
		ArrayList<String> list = new ArrayList<String>();
		boolean result = test.containsAll((Collection<? extends String>) list);
		assertTrue(result);
	}

	@Test
	public void testContainsAllEmptySetOfEmptyHashTable() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		ArrayList<String> list = new ArrayList<String>();
		boolean result = test.containsAll((Collection<? extends String>) list);
		assertTrue(result);
	}

	@Test
	public void testIsEmpty() {
		ChainingHashTable test = new ChainingHashTable(10,
				new BadHashFunctor());
		assertTrue(test.isEmpty());
	}

}
