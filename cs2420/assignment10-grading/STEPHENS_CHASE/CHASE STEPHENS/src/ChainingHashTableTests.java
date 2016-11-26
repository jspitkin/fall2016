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
 * Tests for chaining hash tables. 
 *
 */

public class ChainingHashTableTests {

	ChainingHashTable chain1, chain2, chain3; 
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
		chain1 = new ChainingHashTable(30, functor);
		chain2 = new ChainingHashTable(30, functor);
		chain3 = new ChainingHashTable(50, functor);
		
		//adding vals to chain1
		quad1Vals = new int[50];
		String str;
		for(int i = 0; i < 50; i++){
			str = "" + i;
			quad1Vals[i] = functor.hash(str);
			chain1.add(str);
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
		assertTrue(chain2.isEmpty());
		assertTrue(chain2.add("apple"));
	}
	
	@Test
	public void testAddFalse() {
		for (int i = 49; i <= 0; i++)
		assertFalse(chain1.add("" + i));
	}
	
	@Test
	public void testAddAll() {
		assertTrue(chain2.addAll(list1));
		assertFalse(chain2.addAll(list1));
		assertEquals(5, chain2.size());
	}
	
	@Test
	public void testAddAll2() {
		assertTrue(chain1.addAll(list2));
		assertEquals(51, chain1.size());
		assertFalse(chain1.addAll(list2));
	}
	
	@Test
	public void testContainsTrue() {
		for (int i = 49; i <= 0; i++)
			assertTrue(chain1.contains("" + i));
	}
	
	@Test
	public void testContainsFalse() {
		for (int i = 49; i <= 0; i++)
			assertTrue(chain1.contains("" + i));
	}
	
	@Test
	public void testContainsAllTrue() {
		chain3.addAll(list1);
		assertTrue(chain3.containsAll(list1));
	}
	
	@Test
	public void testContainsAllFalse() {
		assertFalse(chain1.containsAll(list2));
	}
	
	@Test
	public void testAddAllLargeList() {
		assertTrue(chain3.addAll(list4));
		assertEquals(size4, chain3.size()); 
		assertFalse(chain3.addAll(list4));
		assertTrue(chain3.containsAll(list4));
	}
	
	@Test
	public void testClear() {
		chain1.clear();
		assertTrue(chain1.isEmpty());
		assertEquals(0, chain1.size());
		chain1.add("test");
		assertEquals(1, chain1.size());
	}
	
	@Test
	public void testClear2() {
		chain3.addAll(list1);
		chain3.clear();
		for (int i = 49; i <= 0; i++)
			assertFalse(chain3.add("" + i));
		chain3.clear();
		assertTrue(chain3.isEmpty());
	}
}
