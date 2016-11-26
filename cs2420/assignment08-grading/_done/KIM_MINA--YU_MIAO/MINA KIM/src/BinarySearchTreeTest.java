package assignment08;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * @author u1054673 Mina Kim & Miao Yu u0827659
 */

public class BinarySearchTreeTest {
	
	BinarySearchTree<String> test = new BinarySearchTree<String>();
	ArrayList<String> al = new ArrayList<String>();

	
	@Test
	public void testAddOneValid()
	{
		assertTrue(test.add("jacopo"));
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testAddTwoValid()
	{
		test.add("jacopo");
		assertTrue(test.add("morgana"));
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testAddThreeValid()
	{
		test.add("love");
		test.add("live");
		test.add("sunshine");
		assertTrue(test.size() == 3);
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testAddDuplicate()
	{
		test.add("love");
		assertFalse(test.add("love"));
		assertEquals(1, test.size());
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddNullValue()
	{
		test.add(null);
	}
	
	@Test
	public void testAddRightHeavy()
	{
		test.add("a");
		test.add("b");
		test.add("c");
		test.add("d");
		test.add("e");
		test.add("f");
		test.add("g");
		assertEquals(7, test.size());
		// test.writeDot("testBinarySearch.dot");
	}
	
	
	@Test
	public void testAddAllValid() 
	{
		al.add("b");
		al.add("v");
		al.add("c");
		al.add("a");
		assertTrue(test.addAll(al));
		// test.writeDot("testAddAllValid.dot");
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddAllNullFirst()
	{
		al.add(null);
		al.add("egg");
		al.add("plant");
		test.addAll(al);
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddAllNullLater()
	{
		al.add("a");
		al.add("braca");
		al.add("dabra");
		al.add(null);
		test.addAll(al);
	}
	
	
	@Test
	public void testClear()
	{
		test.add("a");
		test.add("deletable");
		test.add("set");
		test.clear();
		assertEquals(0, test.size());
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testClearIsEmpty()
	{
		test.add("a");
		test.add("forgettable");
		test.add("set");
		test.clear();
		assertTrue(test.isEmpty());
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testClearAlreadyEmpty()
	{
		test.clear();
		assertTrue(test.isEmpty());
	}
	
	
	@Test
	public void testContainsValid()
	{
		test.add("yes");
		assertTrue(test.contains("yes"));
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testContainsInvalid()
	{
		test.add("yes");
		assertFalse(test.contains("no"));
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainsExceptionNullFirstEmpty()
	{
		test.contains(null);
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainsExceptionNullFirstFilled()
	{
		test.add("hmm");
		test.contains(null);
	}
	
	
	@Test (expected = NullPointerException.class)
	public void testContainsAllNullFirst()
	{
		test.add("hmm");
		al.add(null);
		al.add("hmm");
		test.containsAll(al);
	}
	
	@Test (expected = NullPointerException.class)
	public void testContainsAllNullNotFirst()
	{
		test.add("hmm");
		al.add("hmm");
		al.add(null);
		test.containsAll(al);
	}
	
	@Test
	public void testContainsAllDefault()
	{
		al.add("k");
		al.add("f");
		al.add("c");
		test.addAll(al);
		assertTrue(test.containsAll(al));
	}
	
	@Test
	public void testContainsAllSlightMoreComplex()
	{
		al.add("jacopo");
		al.add("morgana");
		al.add("house");
		al.add("giselle");
		al.add("maria");
		al.add("white haired girl");
		al.add("bearzatti");
		al.add("halo top");
		al.add("michel");
		al.add("michelle");
		test.addAll(al);
		assertTrue(test.containsAll(al));
	}
	
	@Test
	public void testContainsAllLastNotIncluded()
	{
		al.add("jacopo");
		al.add("morgana");
		al.add("house");
		al.add("giselle");
		al.add("maria");
		al.add("white haired girl");
		al.add("bearzatti");
		al.add("halo top");
		al.add("michel");
		test.addAll(al);
		al.add("michelle");
		
		assertFalse(test.containsAll(al));
	}
	
	
	@Test (expected = NoSuchElementException.class)
	public void testFirstExceptionCase()
	{
		test.first();
	}	

	@Test (expected = NoSuchElementException.class)
	public void testFirstExceptionCaseAfterClear()
	{
		test.add("no");
		test.add("snsd");
		test.add("bb");
		test.add("shinhwa");
		test.clear(); 
		test.first();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testLastExceptionCase()
	{
		test.last();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void testLastExceptionCaseAfterClear()
	{
		test.add("no");
		test.add("snsd");
		test.add("bb");
		test.add("shinhwa");
		test.clear(); 
		test.last();
	}
	
	@Test
	public void testFirstOneElement()
	{
		test.add("whistle");
		assertEquals("whistle", test.first());
	}
	
	@Test
	public void testFirstComplex()
	{
		test.add("whistle");
		test.add("dodo");
		test.add("destiny");
		test.add("yuri");
		test.add("amnesia");
		test.add("hat");
		assertEquals("amnesia", test.first());
		test.remove("amnesia");
		assertEquals("destiny", test.first());
	}
	
	@Test
	public void testLastOneElement()
	{
		test.add("whistle");
		assertEquals("whistle", test.last());
	}
	
	@Test
	public void testLastComplex()
	{
		test.add("whistle");
		test.add("dodo");
		test.add("destiny");
		test.add("yuri");
		test.add("amnesia");
		test.add("hat");
		assertEquals("yuri", test.last());
	}
	
	
	@Test (expected = NullPointerException.class)
	public void testRemoveException()
	{
		test.remove(null);
	}
		
	@Test
	public void testRemoveRootOneElement()
	{
		test.add("hi");
		assertTrue(test.remove("hi"));
		assertTrue(test.size() == 0);
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveRootCaseTwo()
	{
		test.add("hi");
		test.add("bye");
		assertTrue(test.remove("hi"));
		assertEquals(1, test.size());
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveRootCaseThree()
	{
		test.add("hi");
		test.add("bye");
		test.add("sayonara");
		assertTrue(test.remove("hi"));
		assertEquals(2, test.size());		
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveRootMoreComplex()
	{
		test.add("k");
		test.add("f");
		test.add("c");
		test.add("b");
		test.add("a");
		test.add("y");
		test.add("z");
		test.add("r");
		test.add("p");
		test.add("x");
		
		assertTrue(test.remove("k"));
		assertEquals(9, test.size());
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveRootMostComplex()
	{
		al.add("k");
		al.add("f");
		al.add("c");
		al.add("b");
		al.add("a");
		al.add("y");
		al.add("z");
		al.add("r");
		al.add("p");
		al.add("q");
		al.add("x");
		test.addAll(al);
		
		assertTrue(test.remove("k"));
		assertEquals(10, test.size());
		// test.writeDot("testBinarySearch.dot");
		
	}
	
	@Test
	public void testRemoveMiddle()
	{
		test.add("tea");
		test.add("jasmine");
		test.add("flower");
		test.add("rose");
		
		assertTrue(test.remove("rose"));
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveRootFirst()
	{
		al.add("k");
		al.add("f");
		al.add("c");
		al.add("b");
		al.add("a");
		al.add("y");
		al.add("z");
		al.add("r");
		al.add("p");
		al.add("q");
		al.add("x");
		test.addAll(al);
		
		assertTrue(test.remove("k"));
		assertTrue(test.remove("c"));
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveRootNotFirst()
	{
		al.add("k");
		al.add("f");
		al.add("c");
		al.add("b");
		al.add("a");
		al.add("y");
		al.add("z");
		al.add("r");
		al.add("p");
		al.add("q");
		al.add("x");
		test.addAll(al);
		
		test.remove("x");
		test.remove("c");
		assertTrue(test.remove("k"));
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveAlreadyRemoved()
	{
		test.add("l");
		test.add("c");
		test.add("d");
		assertTrue(test.remove("c"));
		assertFalse(test.remove("c"));
		// test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveFromEmpty()
	{
		assertFalse(test.remove("c"));
	}
	
	
	@Test (expected = NullPointerException.class)
	public void testRemoveAllException()
	{
		al.add("e");
		al.add(null);
		test.addAll(al);
		test.removeAll(al);
	}
	
	@Test
	public void testRemoveAllEmpty()
	{
		assertTrue(test.removeAll(al));
	}
	
	@Test
	public void testRemoveAllManual()
	{
		al.add("morgana");
		al.add("jfc");
		al.add("scream");
		al.add("what");
		al.add("am");
		al.add("i");
		al.add("listening");
		al.add("to");
		al.add("zest");
		test.addAll(al);
		
		test.remove("to");
		test.remove("listening");
		test.remove("i");
		test.remove("am");
		test.remove("what");
		test.remove("jfc");
		test.remove("zest");
		test.remove("scream");
		test.remove("morgana");
		
		assertTrue(test.isEmpty());
		//test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveAllProper()
	{
		al.add("morgana");
		al.add("scream");
		al.add("jfc");
		al.add("what");
		al.add("am");
		al.add("i");
		al.add("listening");
		//al.add("zest");
		// al.add("to");
		test.addAll(al);
		assertTrue(test.removeAll(al));
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
		test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveAllWithAdditions()
	{
		al.add("morgana");
		al.add("scream");
		al.add("jfc");
		al.add("what");
		al.add("am");
		al.add("i");
		al.add("listening");
		al.add("zest");
		al.add("to");
		test.addAll(al);
		assertTrue(test.removeAll(al));
		assertEquals(0, test.size());
		assertTrue(test.isEmpty());
		test.writeDot("testBinarySearch.dot");
	}
	
	@Test
	public void testRemoveAllOne()
	{
		test.add("one");
		assertTrue(test.remove("one"));
		assertTrue(test.isEmpty());
	}
	
	@Test
	public void testRemoveAllRightHeavy()
	{
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("d");
		al.add("e");
		al.add("f");
		al.add("g");
		al.add("h");
		al.add("i");
		al.add("j");
		al.add("k");
		al.add("l");
		test.addAll(al);
		assertTrue(test.removeAll(al));
		assertEquals(0, test.size());
	}	
	
	@Test
	public void testRemoveAllLeftHeavy()
	{
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("d");
		al.add("e");
		al.add("f");
		al.add("g");
		al.add("h");
		al.add("i");
		al.add("j");
		al.add("k");
		al.add("l");		
		
		test.add("a");
		test.add("b");
		test.add("c");
		test.add("d");
		test.add("e");
		test.add("f");
		test.add("g");
		test.add("h");
		test.add("i");
		test.add("j");
		test.add("k");
		test.add("l");
		test.addAll(al);
		assertTrue(test.removeAll(al));
		assertEquals(0, test.size());
	}

	@Test
	public void testToArrayEmpty(){
		al = test.toArrayList();
		assertTrue(al.isEmpty());
	
	}
	
	@Test
	public void testToArray()
	{
		test.add("a");
		test.add("pp");
		test.add("le");
		test.add("s");
		al = test.toArrayList();
		
		ArrayList<String> alBase = new ArrayList<String>();
		alBase.add("a");
		alBase.add("le");
		alBase.add("pp");
		alBase.add("s");
		
		assertEquals(alBase, al);
	}
	
	
	// TODO test complex  (both) & clear
}











