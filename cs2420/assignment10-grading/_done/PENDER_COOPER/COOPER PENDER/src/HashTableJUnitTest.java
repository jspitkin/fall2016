package assignment10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * This suite tests the functionality of both the
 * quadratic probing hashtable, and the seperate chaining
 * hashtable. 
 * 
 * @author Cooper Pender (u0843147)
 * Last Edited On: 11/5/2016
 *
 */
public class HashTableJUnitTest {

	private QuadProbeHashTable quadTable;
	private QuadProbeHashTable quadMidTable;
	private ChainingHashTable chainTable;
	private BadHashFunctor badFunc;
	private MediocreHashFunctor midFunc;
	private GoodHashFunctor goodFunc;
	
	@Before
	public void setUp() throws Exception {
		
		badFunc = new BadHashFunctor();
		midFunc = new MediocreHashFunctor();
		goodFunc = new GoodHashFunctor();
		
		quadTable = new QuadProbeHashTable(3, badFunc);
		quadMidTable = new QuadProbeHashTable(10, midFunc);
		chainTable = new ChainingHashTable(130, goodFunc);
	}

	@Test
	public void addQuadTest() {
		quadTable.add("cake");
		quadTable.add("pop");
		quadTable.add("soda");
		quadTable.add("candy");
		quadTable.add("apple");
		quadTable.add("pizza");
		quadTable.add("butter");
		assertEquals(7, quadTable.size());
		assertEquals(true, quadTable.contains("apple"));
	}
	
	@Test
	public void addQuadFalseTest() {
		quadTable.add("cake");
		quadTable.add("pop");
		quadTable.add("soda");
		quadTable.add("candy");
		quadTable.add("apple");
		quadTable.add("pizza");
		quadTable.add("butter");
		assertEquals(false, quadTable.add("soda"));
		
	}
	
	@Test
	public void addChainTest() {
		chainTable.add("cake");
		chainTable.add("pop");
		chainTable.add("kale");
		chainTable.add("apple");
		chainTable.add("pizza");
		assertEquals(5, chainTable.size());
		assertFalse(chainTable.contains("kales"));
	}
	
	@Test (expected = NullPointerException.class)
	public void addNullTest() {
		chainTable.add(null);
	}
	
	@Test
	public void addAllChainTest() {
		ArrayList<String> testArr = new ArrayList<String>();
		testArr.add("soda");
		testArr.add("pop");
		chainTable.add("cake");
		chainTable.add("kale");
		chainTable.addAll(testArr);
		assertEquals(4, chainTable.size());
		assertEquals(true, chainTable.contains("pop"));
	}
	
	@Test
	public void addAllQuadTest() {
		ArrayList<String> testArr = new ArrayList<String>();
		testArr.add("ice");
		testArr.add("kale");
		quadMidTable.add("ice");
		quadMidTable.add("pop");
		quadMidTable.addAll(testArr);
		assertEquals(3, quadMidTable.size());
	}
	
	@Test (expected = NullPointerException.class)
	public void addAllNullTest() {
		ArrayList<String> testArr = new ArrayList<String>();
		testArr.add("cake");
		testArr.add(null);
		quadTable.addAll(testArr);
	}
	
	@Test
	public void clearChainTest() {
		chainTable.add("cake");
		chainTable.add("kale");
		chainTable.add("pop");
		chainTable.add("soda");
		chainTable.clear();
		assertEquals(0, chainTable.size());
		assertEquals(false, chainTable.contains("soda"));
	}
	
	@Test
	public void clearQuadTest() {
		quadTable.add("cake");
		quadTable.add("pop");
		quadTable.add("soda");
		quadTable.add("kale");
		quadTable.add("mousse");
		quadTable.clear();
		assertEquals(0, quadTable.size());
		assertEquals(false, quadTable.contains("pop"));
	}
	
	@Test
	public void clearEmptyTest() {
		quadMidTable.clear();
	}
	
	@Test (expected = NullPointerException.class)
	public void containsNullTest() {
		chainTable.add("cake");
		chainTable.add("pop");
		chainTable.add("candy");
		chainTable.contains(null);
	}
	
	@Test
	public void containsAllQuadTest() {
		ArrayList<String> testArr = new ArrayList<String>();
		testArr.add("cake");
		testArr.add("pop");
		testArr.add("kale");
		quadMidTable.add("kale");
		quadMidTable.add("chips");
		quadMidTable.add("pizza");
		quadMidTable.add("cake");
		assertEquals(false, quadMidTable.containsAll(testArr));
	}
	
	@Test
	public void containsAllChainTest() {
		ArrayList<String> testArr = new ArrayList<String>();
		testArr.add("cake");
		testArr.add("pop");
		testArr.add("kale");
		chainTable.add("cake");
		chainTable.add("pop");
		chainTable.add("candy");
		chainTable.add("kale");
		assertEquals(true, chainTable.containsAll(testArr));
	}
	
	@Test (expected = NullPointerException.class)
	public void containsAllNullTest() {
		ArrayList<String> testArr = new ArrayList<String>();
		testArr.add("cake");
		testArr.add("pop");
		testArr.add(null);
		chainTable.add("cake");
		chainTable.add("pop");
		chainTable.containsAll(testArr);
	}
	
	@Test
	public void isEmptyTrueTest() {
		quadMidTable.add("cake");
		quadMidTable.add("kale");
		assertEquals(false, quadMidTable.isEmpty());
	}
	
	@Test
	public void isEmptyFalseTest() {
		assertEquals(true, chainTable.isEmpty());
	}
}
