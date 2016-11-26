package assignment10;

import static org.junit.Assert.*;
/**
 * JUnit test case
 * @author Jiwon Nam
 */
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class HashTableJUnitTest {

	int originCapacity = 7;
	QuadProbeHashTable quadProbing;
	ChainingHashTable chaining;
	
	HashFunctor badHash = new BadHashFunctor();
	HashFunctor medHash = new MediocreHashFunctor();
	HashFunctor goodHash = new GoodHashFunctor();
	ArrayList<String> list = new ArrayList<String>();
	@Before
	public void setup() {
		quadProbing = new QuadProbeHashTable(originCapacity, badHash);
		chaining = new ChainingHashTable(7, badHash);
		for(int i = 0; i < 26; i++) {
			list.add("" + (char)(i + 65));
		}
	}

	@Test
	public void quadProbeAddTest() {
		// use bad hash functor
		// bad functor -> sum of each character ascii numbers and divided by 26
		// add method
		// hash functor = 3
		
		assertTrue(quadProbing.add("a"));
		assertTrue(!quadProbing.add("a"));	// duplication false
		// hash functor = 107/26 % 7 = 4
		assertTrue(quadProbing.add("k"));
		assertTrue(!quadProbing.add("k"));
		// functor = 4 , but it already in, quad probing -> 5
		assertTrue(quadProbing.add("l"));
		assertTrue(!quadProbing.add("l"));
		// functor = 4, already in, quad probing -> 1
		assertTrue(quadProbing.add("m"));
		assertTrue(!quadProbing.add("m"));
		// before rehashing, size and capacity
		assertEquals(17, quadProbing.getCapacity());
		assertEquals(4, quadProbing.size());
		// rehash thispoint, capacity = 17
		// functor = 7,
		assertTrue(quadProbing.add("ab"));
		// check rehash
		assertEquals(17, quadProbing.getCapacity());
		assertEquals(5, quadProbing.size());
		
		assertTrue(quadProbing.add("ay"));
		assertTrue(quadProbing.add("mm"));
		assertEquals(1, quadProbing.numOfCollision());
		quadProbing.clear();
		// check it is empty
		assertTrue(quadProbing.isEmpty());
		// check lots of alphabet input works
		for(int i = 0; i < 26; i++) {
			assertTrue(quadProbing.add("" + (char)(i + 65)));
		}
		assertEquals(0, quadProbing.numOfCollision());
		// 7(after 4th) -> 17 (after 8th)-> 37 (after 18th) -> 79 (after 34th) ->
		assertEquals(79, quadProbing.getCapacity());
		assertEquals(26, quadProbing.size());
		
		// add all method
		// if all list variable already in the table return false check
		assertTrue(!quadProbing.addAll(list));	
		// clear
		quadProbing.clear();
		// check it is empty
		assertTrue(quadProbing.isEmpty());
		
		assertTrue(quadProbing.addAll(list));
		assertEquals(79, quadProbing.getCapacity());
		assertEquals(26, quadProbing.size());
	}
	
	@Test
	public void quadProbeContainTest() {
		// bad hash functor
		// add items to table A to Z
		for(int i = 0; i < 26; i++) {
			assertTrue(quadProbing.add("" + (char)(i + 65)));
		}
		// check using contain A to Z
		for(int i = 0; i < 26; i++) {
			assertTrue(quadProbing.contains("" + (char)(i + 65)));
		}
		quadProbing.clear();
		assertTrue(quadProbing.isEmpty());
		// return false, if item not in the table
		assertTrue(!quadProbing.contains("hi"));
		assertTrue(!quadProbing.containsAll(list));
		// check contains all
		assertTrue(quadProbing.addAll(list));
		assertTrue(quadProbing.containsAll(list));
	}

	@Test
	public void chainingHashAddTest() {
		// default lamda = 3 to rehash the table
		assertTrue(chaining.add("a"));
		assertTrue(!chaining.add("a"));
		assertTrue(chaining.add("b"));
		assertTrue(!chaining.add("b"));
		assertTrue(chaining.add("abc"));
		assertTrue(!chaining.add("abc"));
		assertEquals(3, chaining.size());
		assertEquals(7, chaining.getCapacity());
		
		chaining.clear();
		assertTrue(chaining.isEmpty());
		
		// add 26 item A-Z
		for(int i = 0; i < 26; i++) {
			assertTrue(chaining.add("" + (char)(i + 65)));
		}
		// check duplication
		for(int i = 0; i < 26; i++) {
			assertTrue(!chaining.add("" + (char)(i + 65)));
		}
		// check it has been rehashed
		assertEquals(26, chaining.size());
		assertEquals(14, chaining.getCapacity());
		// clear table
		chaining.clear();
		assertTrue(chaining.isEmpty());
		// addAll method test
		assertTrue(chaining.addAll(list));
		// check duplication
		assertTrue(!chaining.addAll(list));	
	}
	
	@Test
	public void chainingHashContainsTest() {
		// with empty hash set, false contains method
		assertTrue(!chaining.contains("dfas"));
		
		// add 26 item A-Z
		for(int i = 0; i < 26; i++) {
			assertTrue(chaining.add("" + (char)(i + 65)));
		}
		
		for(int i = 0; i < 26; i++) {
			assertTrue(chaining.contains("" + (char)(i + 65)));
		}
		
		assertTrue(!chaining.contains("dffsaa"));
		
		// clear
		chaining.clear();
		assertTrue(chaining.isEmpty());
		assertTrue(!chaining.containsAll(list));
		
		assertTrue(chaining.addAll(list));
		assertTrue(chaining.containsAll(list));
		
	}
}
