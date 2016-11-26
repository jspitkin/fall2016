/**
 * Assignment 08 - BinarySearchTree
 * Names: Nicholas Kerr & Dallin Van Mondfrans
 * Nicholas's uID: u0125990
 * Dallin's uID: u0717113
 * Date: October 26, 2016
 */

package assignment08;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BST_JUnitTest {

	BinarySearchTree<Character> bst;
	
	@Before
	public void setUp() throws Exception {
		bst = new BinarySearchTree<Character>();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	// Tests ------------------------------------------------
	@Test
	public void testIsEmpty() {
		assertTrue(bst.isEmpty());
	}
	
	@Test
	public void addOneElement() {
		bst.add('m');
		assertTrue(bst.size() == 1 && bst.getRoot().getData().equals('m'));
	}
	
	@Test
	public void addThreeElements() {
		bst.add('m');
		bst.add('f');
		bst.add('t');
		assertTrue(bst.size() == 3 && bst.getRoot().getLeft().getData().equals('f') 
				&& bst.getRoot().getRight().getData().equals('t'));
	}
	
	@Test
	public void addNullItem() {
		try {
			bst.add(null);
		} catch(Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
		
	}
	
	@Test
	public void firstWithNoElements() {
		try {
			bst.first();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}
	
	@Test
	public void lastWithNoElements() {
		try {
			bst.last();
		} catch(Exception e) {
			assertTrue(e instanceof NoSuchElementException);
		}
	}
	
	@Test
	public void firstAndLastWithOneElement() {
		bst.add('r');
		assertTrue(bst.first().equals('r') && bst.last().equals('r'));
	}
	
	@Test
	public void firstAndLastWithTwoElements() {
		bst.add('r');
		bst.add('s');
		assertTrue(bst.first().equals('r') && bst.last().equals('s'));
	}
	
	@Test
	public void addAllMethodWithAlphabetAndTestFirstAndLast() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		assertTrue(bst.first().equals('a') && bst.last().equals('z') && bst.size() == 26);
	}
	
	@Test
	public void isEmptyOnNonEmptyTree() {
		bst.add('a');
		assertFalse(bst.isEmpty());
	}
	
	@Test
	public void containsWithOneElement() {
		bst.add('a');
		assertTrue(bst.contains('a'));
	}
	
	@Test
	public void containsWithAlphabet() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		assertTrue(bst.contains('a') && bst.contains('n') && bst.contains('z') && bst.contains('p'));
	}
	
	@Test
	public void containsAllWithAlphabet() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		assertTrue(bst.containsAll(getCharList("abcdefghijklmnopqrstuvwxyz")));
	}
	
	@Test
	public void removeLeafElement() {
		bst.add('m');
		bst.add('f');
		bst.add('t');
		assertTrue(bst.remove('t'));
	}
	
	@Test
	public void removeLeafNodeFromAlphabet() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		bst.remove('v');
		//bst.writeDot("alphabet2.dot");
		assertTrue( !bst.contains('v') && bst.size() == 25 );
	}
	
	@Test
	public void removeNodeWithOnlyOneChild() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		bst.remove('d');
		//bst.writeDot("alphabet2.dot");
		assertTrue( !bst.contains('d') && bst.size() == 25 );
	}
	
	@Test
	public void removeRootNode() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		bst.remove('m');
		//bst.writeDot("alphabet2.dot");
		assertTrue( !bst.contains('m') && bst.size() == 25 );
	}
	
	@Test
	public void removeNonExistentElement() {
		bst.add('m');
		assertFalse( bst.remove('d') );
	}
	
	@Test
	public void removeElementFromEmptyTree() {
		assertFalse(bst.remove('a'));
	}
	
	//Remove all elements in different orders
	@Test
	public void removeAllNodesInOrderInserted() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		bst.removeAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		//bst.writeDot("alphabet2.dot");
		assertTrue( bst.isEmpty() && bst.size() == 0 );
	}
	
	@Test
	public void removeAllNodesInReverseOrderInserted() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		bst.removeAll(getCharList("zxvspnligeayurokhdbwqjcftm"));
		//bst.writeDot("alphabet2.dot");
		assertTrue( bst.isEmpty() && bst.size() == 0 );
	}
	
	@Test
	public void removeAllNodesAlphabetically() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		bst.removeAll(getCharList("abcdefghijklmnopqrstuvwxyz"));
		//bst.writeDot("alphabet2.dot");
		assertTrue( bst.isEmpty() && bst.size() == 0 );
	}
	
	@Test
	public void removeAllNodesReverseAlphabetically() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		bst.removeAll(getCharList("zyxwvutsrqponmlkjihgfedcba"));
		//bst.writeDot("alphabet2.dot");
		assertTrue( bst.isEmpty() && bst.size() == 0 );
	}
	
	@Test
	public void testToArrayList() {
		bst.addAll(getCharList("mtfcjqwbdhkoruyaegilnpsvxz"));
		ArrayList<Character> al = bst.toArrayList();
		assertTrue( al.containsAll(getCharList("zyxwvutsrqponmlkjihgfedcba")) && al.size() == 26 );
	}
	

	
	/**
	 * This is Ryan's method that he wrote for us to use in our last lab.
	 * I have included it for convenience.
	 * @param string - string you want to turn into a Character list. 
	 * @return
	 */
	private static List<Character> getCharList(String string) {
		List<Character> charList = new ArrayList<>();
		for(char c : string.toCharArray()) {
			charList.add(c);
		}
		return charList;
	}

}
