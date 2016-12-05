package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class QuadProbeHashTableTest {

	private ArrayList<String> a;

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
		QuadProbeHashTable test = new QuadProbeHashTable(7, functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		test.add("gorilla");
		assertTrue(test.getTable()[3]=="dog");
		assertTrue(test.size() == 5);
	}


	@Test
	public void testAddAll() {
		HashFunctor functor = new BadHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(7, functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		test.add("gorilla");
		a = new ArrayList<String>();
		a.add("orange");
		a.add("apple");
		a.add("po");
		assertFalse(test.containsAll(a));
		assertTrue(test.addAll(a));
		assertTrue(test.getSize() == 7);		
		
	}

	@Test
	public void testClear() {
		HashFunctor functor = new BadHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(7, functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		test.add("gorilla");
		test.clear();
		assertTrue(test.size()==0);
		assertTrue(test.getCollision()==0);
	}

	@Test
	public void testContains() {
		HashFunctor functor = new BadHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(7, functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		test.add("gorilla");
		assertTrue(test.contains("bird"));
		assertTrue(test.contains("cat"));
		assertTrue(test.contains("gorilla"));
		assertFalse(test.contains("fdad"));
	}

	@Test
	public void testContainsAll() {
		HashFunctor functor = new BadHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(7, functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		test.add("gorilla");
		a = new ArrayList<String>();
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
		QuadProbeHashTable test = new QuadProbeHashTable(7, functor);	
		assertTrue(test.isEmpty());
		test.add("ada");
		assertFalse(test.isEmpty());
	}

	@Test
	public void testSize() {
		HashFunctor functor = new BadHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(7, functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		test.add("gorilla");
		assertTrue(test.getSize() == 5);
		test.add("gorilla");
		assertTrue(test.getSize() == 5);
	}
	
	@Test
	public void testCollision(){
		HashFunctor functor = new BadHashFunctor();
		QuadProbeHashTable test = new QuadProbeHashTable(7, functor);		
		test.add("apple");
		test.add("dog");
		test.add("bird");
		test.add("cat");
		assertTrue(test.getCollision() == 2);
		test.add("bdor");
		assertTrue(test.getCollision() == 4);
		test.add("cdor");
		assertTrue(test.getCollision() == 7);
	}

}
