package assignment10;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Tester Class for all assignment10 classes
 * 
 * @author Spencer Jewett (u0677872)
 * @since 11/9/2016
 *
 */
public class Assignment10JunitTesting {
	private ChainingHashTable chainTableTest;
	private QuadProbeHashTable quadTableTest;
	private ArrayList<String> content;
	private ArrayList<String> contentWithNull;
	private GoodHashFunctor testFunctor;

	@Before
	public void setUp(){
		testFunctor = new GoodHashFunctor();
		content = new ArrayList<String>();
		contentWithNull = new ArrayList<String>();
		content.add("apples");
		content.add("butter");
		content.add("charlie");
		content.add("dove");
		content.add("edward");
		content.add("freddie");
		contentWithNull.addAll(content);
		contentWithNull.add(2 , null);


		//Chaining Table Set Up
		chainTableTest = new ChainingHashTable(7, testFunctor);
		
		//QuadProbe Table Set Up
		quadTableTest = new QuadProbeHashTable(31, testFunctor);
	}
	
	@Test
	public void chainingAddTest1() {
		assertEquals(true, chainTableTest.add("george"));
		assertTrue(chainTableTest.contains("george"));
		chainTableTest.clear();
		assertFalse(chainTableTest.contains("george"));
		assertEquals(0, chainTableTest.size());
		assertTrue(chainTableTest.isEmpty());

	}
	
	@Test
	public void chainingAddAllTest1(){
		assertTrue(chainTableTest.addAll(content));
		assertTrue(chainTableTest.contains("apples"));
		assertTrue(chainTableTest.containsAll(content));
		assertEquals(6, chainTableTest.size());
		chainTableTest.clear();
		assertFalse(chainTableTest.contains("edward"));
		assertFalse(chainTableTest.containsAll(content));
	}
	
	@Test
	public void chainingAddDuplicate(){
		assertEquals(true, chainTableTest.add("hamburger"));
		assertTrue(chainTableTest.contains("hamburger"));
		assertTrue(chainTableTest.add("hamburger"));
		assertTrue(chainTableTest.contains("hamburger"));
	}
	
	@Test
	public void chainingAddDuplicateList(){
		assertTrue(chainTableTest.addAll(content));
		assertTrue(chainTableTest.contains("butter"));
		assertTrue(chainTableTest.containsAll(content));
		assertEquals(6, chainTableTest.size());
		assertTrue(chainTableTest.addAll(content));
		assertTrue(chainTableTest.contains("apples"));
		assertTrue(chainTableTest.containsAll(content));
		assertEquals(12, chainTableTest.size());
		chainTableTest.clear();
		assertTrue(chainTableTest.isEmpty());
	}
	
	@Test (expected = NullPointerException.class)
	public void chainingAddNullTest(){
		assertFalse(chainTableTest.add(null));
	}
	
	@Test (expected = NullPointerException.class)
	public void chainingAddAllWithNullTest(){
		assertFalse(chainTableTest.addAll(contentWithNull));
	}	
	
	@Test (expected = NullPointerException.class)
	public void chainingContainsNullTest(){
		assertTrue(chainTableTest.add("apples"));
		assertFalse(chainTableTest.contains(null));
	}
	
	@Test (expected = NullPointerException.class)
	public void chainingContainsAllWithNullTest(){
		assertTrue(chainTableTest.addAll(content));
		assertFalse(chainTableTest.containsAll(contentWithNull));
	}	
	
	@Test
	public void quadProbeAddTest1() {
		assertTrue(quadTableTest.add("george"));
		assertTrue(quadTableTest.contains("george"));
		assertEquals(1, quadTableTest.size());
		assertTrue(quadTableTest.add(" "));
		assertTrue(quadTableTest.contains(" "));
		assertEquals(2, quadTableTest.size());
		assertTrue(quadTableTest.add(""));
		assertTrue(quadTableTest.contains(""));
		assertEquals(3, quadTableTest.size());
		quadTableTest.clear();
		assertFalse(quadTableTest.contains("george"));
		assertEquals(0, quadTableTest.size());
		assertTrue(quadTableTest.isEmpty());

	}
	
	@Test
	public void quadProbeAddAllTest1(){
		assertTrue(quadTableTest.addAll(content));
		assertTrue(quadTableTest.contains("apples"));
		assertTrue(quadTableTest.containsAll(content));
		assertEquals(6, quadTableTest.size());
		quadTableTest.clear();
		assertFalse(quadTableTest.contains("edward"));
		assertFalse(quadTableTest.containsAll(content));
	}
	
	@Test
	public void quadProbeAutoResizeTester1(){
		quadTableTest = new QuadProbeHashTable(3, testFunctor);
		assertTrue(quadTableTest.addAll(content));
		assertTrue(quadTableTest.contains("apples"));
		assertTrue(quadTableTest.containsAll(content));
		assertEquals(6, quadTableTest.size());

	}
	
	@Test
	public void quadProbeAutoResizeTester2(){
		quadTableTest = new QuadProbeHashTable(12, testFunctor);
		assertTrue(quadTableTest.addAll(content));
		assertTrue(quadTableTest.contains("apples"));
		assertTrue(quadTableTest.containsAll(content));
		assertEquals(6, quadTableTest.size());
		assertTrue(quadTableTest.addAll(content));
		assertEquals(12, quadTableTest.size());
	}
	
	@Test
	public void quadProbeAddDuplicateTest1(){
		assertTrue(quadTableTest.add("hamburger"));
		assertEquals(true, quadTableTest.contains("hamburger"));
		assertTrue(quadTableTest.add("hamburger"));
		assertTrue(quadTableTest.contains("hamburger"));
	}
	
	@Test
	public void quadProbeAddAllDuplicateTest1(){
		assertTrue(quadTableTest.addAll(content));
		assertTrue(quadTableTest.contains("butter"));
		assertTrue(quadTableTest.containsAll(content));
		assertEquals(6, quadTableTest.size());
		assertTrue(quadTableTest.addAll(content));
		assertTrue(quadTableTest.contains("apples"));
		assertTrue(quadTableTest.containsAll(content));
		assertEquals(12, quadTableTest.size());
		quadTableTest.clear();
		assertTrue(quadTableTest.isEmpty());
	}
	
	@Test (expected = NullPointerException.class)
	public void quadProbeAddNullTest(){
		assertFalse(quadTableTest.add(null));
	}
	
	@Test (expected = NullPointerException.class)
	public void quadProbeAddListWithNullTest(){
		assertFalse(quadTableTest.addAll(contentWithNull));
	}	
	
	@Test (expected = NullPointerException.class)
	public void quadProbeContainsNullTest(){
		assertTrue(quadTableTest.add("apples"));
		assertFalse(quadTableTest.contains(null));
	}
	
	@Test (expected = NullPointerException.class)
	public void quadProbeContainsNullInListTest(){
		assertTrue(quadTableTest.addAll(content));
		assertFalse(quadTableTest.containsAll(contentWithNull));
	}	

}
