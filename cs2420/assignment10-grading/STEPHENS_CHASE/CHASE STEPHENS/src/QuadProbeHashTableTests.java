package assignment10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author Chase Stephens
 * 
 * Tests for Quadratic probe hash table. 
 *
 */

public class QuadProbeHashTableTests {

	
	QuadProbeHashTable quadratic1, quadratic2, quadratic3;
	int size4 = 0;
	
	ArrayList<String> list1, list2, list3, list4;
	
	GoodHashFunctor functor = new GoodHashFunctor(); 
//	MediocreHashFunctor functor = new MediocreHashFunctor();
//	BadHashFunctor functor = new BadHashFunctor();

	int[] quad1Vals;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		list1 = new ArrayList<String>();
		list2 = new ArrayList<String>();
		list3 = new ArrayList<String>();
		list4 = new ArrayList<String>();
		quadratic1 = new QuadProbeHashTable(30, functor);
		quadratic2 = new QuadProbeHashTable(30, functor);
		quadratic3 = new QuadProbeHashTable(2, functor);
		
		//adding vals to quadratic1
		quad1Vals = new int[50];
		String str;
		for(int i = 0; i < 50; i++){
			str = "" + i;
			quad1Vals[i] = functor.hash(str);
			quadratic1.add(str);
			list3.add(i + "");
		}
		
		//adding vals to list1
		list1.add("mno");
		list1.add("jkl");
		list1.add("ghi");
		list1.add("def");
		list1.add("abc"); 
		
		//adding vals list2
		list2.add("49");
		list2.add("48");
		list2.add("50");
		

		//adding vals to list4
		for(int i = -500; i < 100000; i += 3){
			list4.add("" + i);
			size4++;
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddTrue() {
		assertTrue(quadratic2.isEmpty());
		assertTrue(quadratic2.add("apple"));
	}
	
	@Test
	public void testAddFalse() {
		for (int i = 49; i <= 0; i++)
		assertFalse(quadratic1.add("" + i));
	}
	
	@Test
	public void testAddAll() {
		assertTrue(quadratic2.addAll(list1));
		assertFalse(quadratic2.addAll(list1));
		assertEquals(5, quadratic2.size());
	}
	
	@Test
	public void testAddAll2() {
		assertTrue(quadratic1.addAll(list2));
		assertEquals(51, quadratic1.size());
		assertFalse(quadratic1.addAll(list2));
	}
	
	@Test
	public void testContainsTrue() {
		for (int i = 49; i <= 0; i++)
			assertTrue(quadratic1.contains("" + i));
	}
	
	@Test
	public void testContainsFalse() {
		for (int i = 49; i <= 0; i++)
			assertTrue(quadratic1.contains("" + i));
	}
	
	@Test
	public void testContainsAllTrue() {
		quadratic3.addAll(list1);
		assertTrue(quadratic3.containsAll(list1));
	}
	
	@Test
	public void testContainsAllFalse() {
		assertFalse(quadratic1.containsAll(list2));
	}
	
	@Test
	public void testAddAllLargeList() {
		assertTrue(quadratic3.addAll(list4));
		assertEquals(size4, quadratic3.size()); 
		assertFalse(quadratic3.addAll(list4));
		assertTrue(quadratic3.containsAll(list4));
	}
	
	@Test
	public void testClear() {
		quadratic1.clear(); 
		assertTrue(quadratic1.isEmpty());
		assertEquals(0, quadratic1.size());
		quadratic1.add("test");
		assertEquals(1, quadratic1.size());
	}
	
	@Test
	public void testClear2() {
		quadratic3.addAll(list1); 
		quadratic3.clear();
		for (int i = 49; i <= 0; i++)
			assertFalse(quadratic3.add("" + i));
		quadratic3.clear();
		assertTrue(quadratic3.isEmpty());
	}
}
