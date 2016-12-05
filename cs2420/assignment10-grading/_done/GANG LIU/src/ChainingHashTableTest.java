package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChainingHashTableTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdd() {
		HashFunctor functor = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(7,functor);	
		test.add("apple");
		assertTrue(test.getSize()==1);
		assertFalse(test.getStorage()[5]==null);
	}

	@Test
	public void testAddAll() {
		HashFunctor functor = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(7,functor);	
		test.add("apple");
		test.add("dog");
		test.add("bird");
		ArrayList<String> a = new ArrayList<String>();
		a.add("orange");
		a.add("apple");
		a.add("po");
		assertTrue(test.addAll(a));
		a.add("apple");
		a.add("po");
		assertFalse(test.addAll(a));
		assertTrue(test.getSize() == 5);
	}

	@Test
	public void testClear() {
		HashFunctor functor = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(7,functor);	
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.clear();
		assertTrue(test.getSize()==0);
		assertTrue(test.getStorage()[5]==null);
	}

	@Test
	public void testContains() {
		HashFunctor functor = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(7,functor);	
		test.add("apple");
		test.add("dog");
		test.add("bird");
		assertTrue(test.contains("apple"));
		assertFalse(test.contains("appled"));
	}

	@Test
	public void testContainsAll() {
		HashFunctor functor = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(7,functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		test.add("gorilla");
		ArrayList<String> a = new ArrayList<String>();
		a.add("cat");
		a.add("apple");
		a.add("dog");
		assertTrue(test.containsAll(a));
		a.add("dogee");
		assertFalse(test.containsAll(a));
	}

	@Test
	public void testIsEmpty() {
		HashFunctor functor = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(7,functor);	
		assertTrue(test.isEmpty());
		test.add("apple");
		test.add("dog");
		test.add("bird");
		assertFalse(test.isEmpty());
	}

	@Test
	public void testSize() {
		HashFunctor functor = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(7,functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		test.add("gorilla");
		assertTrue(test.getSize() == 5);
		assertTrue(test.contains("gorilla"));	
		test.add("gorilla");
		assertTrue(test.getSize() == 5);
	}
	
	@Test
	public void testCollision(){
		HashFunctor functor = new BadHashFunctor();
		ChainingHashTable test = new ChainingHashTable(7,functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		assertTrue(test.getCollision() == 1);
		test.add("bdor");
		assertTrue(test.getCollision() == 2);
		test.add("cdor");
		assertTrue(test.getCollision() == 3);
	}

}
