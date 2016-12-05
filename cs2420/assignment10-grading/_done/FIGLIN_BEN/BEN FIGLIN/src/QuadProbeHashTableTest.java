package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * This class tests the quadrature probing hash table.
 * 
 * @author Ben Figlin (u1115949)
 *
 */
public class QuadProbeHashTableTest {
	QuadProbeHashTable table;
	ArrayList<String> wordList;
	
	String[] wordArray = {
			"This", "is", "a", "test", "sentence", 
			"with", "many", "different", "words", 
			"and", "random", "letters", "such", "as", 
			"b", "c", "d", "e", "f", "g", "h", "i", "j", "k"
			};
	
	@Before
	public void setUp() throws Exception {
		table = new QuadProbeHashTable(4, new GoodHashFunctor());
		wordList = new ArrayList<String>();
		for (String word : wordArray) {
			wordList.add(word);
		}
	}
	
	@Test
	public void testAddManyItems() {
		for (int i=0; i<50; i++) {
			assertTrue(table.add("test"+i));

		}
		
		for (int i=0; i<50; i++) {
			assertTrue(table.contains("test"+i));
		}

	}
	
	@Test
	public void testAddNull() {
		assertFalse(table.add(null));
	}

	@Test
	public void testAddExisting() {
		assertTrue(table.add("test"));
		assertFalse(table.add("test"));
	}

	@Test
	public void testAddAll() {
		assertTrue(table.addAll(wordList));
		assertTrue(table.containsAll(wordList));
		assertFalse(table.addAll(wordList));
	}

	@Test
	public void testAddAllEmpty()  {
		assertFalse(table.addAll(new ArrayList<String>()));
	}
	
	@Test
	public void testAddAllNull()  {
		assertFalse(table.addAll(null));
	}

	@Test
	public void testContains()  {
		table.addAll(wordList);

		for (String word : wordList) {
			assertTrue(table.contains(word));
		}
	}

	@Test
	public void testContainsPartially()  {
		table.add("word1");
		table.add("word2");
		table.add("word3");
		table.add("word4");

		assertTrue(table.contains("word1"));
		assertTrue(table.contains("word4"));
		assertFalse(table.contains("word5"));
	}

	@Test
	public void testContainsEmptyTable()  {
		assertFalse(table.contains("word"));
		assertFalse(table.contains(""));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsNull()  {
		table.contains(null);
	}

	@Test
	public void testContainsAllExactly()  {
		table.addAll(wordList);

		assertTrue(table.containsAll(wordList));
	}

	@Test
	public void testContainsAllPartially()  {
		table.addAll(wordList);
		wordList.add("some string!");
		assertFalse(table.containsAll(wordList));
	}
	
	@Test
	public void testContainsAllContained()  {
		ArrayList<String> testList = new ArrayList<String>(wordList);
		wordList.add("some string!");
		table.addAll(wordList);
		
		assertTrue(table.containsAll(testList));
	}

	@Test(expected = NullPointerException.class)
	public void testContainsAllNull()  {
		table.containsAll(null);
	}

	@Test
	public void testSize()  {
		assertEquals(0, table.size());
		
		table.addAll(wordList);
		
		assertEquals(wordList.size(), table.size());
	}

	@Test
	public void testClear()  {
		table.addAll(wordList);
		
		assertFalse(table.isEmpty());
		
		table.clear();
		
		for (String word : wordList) {
			assertFalse(table.contains(word));
		}
		
		assertTrue(table.isEmpty());

	}
	
}
