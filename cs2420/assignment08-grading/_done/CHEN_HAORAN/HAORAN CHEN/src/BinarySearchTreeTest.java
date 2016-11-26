package assignment08;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Junit test for our BST
 * @author Eduardo Ortiz/Haoran Chen
 * @uid u09226248/u1060286
 * CS 2420 MW 3-4
 *
 */
public class BinarySearchTreeTest {

	BinarySearchTree hi = new BinarySearchTree();
	ArrayList answer = new ArrayList();

	
	
	@Test
	public void getFirstTest1() {
		hi.add("1");
		hi.add("2");
		hi.add("3");
		assertEquals("1",hi.first());
	
	}
	@Test (expected = NoSuchElementException.class)
	public void getFirstTest2() {
		assertEquals("1",hi.first());
	
	}
	@Test
	public void getlastTest1() {
		hi.add("1");
		hi.add("2");
		hi.add("3");
		assertEquals("3",hi.last());
	
	}
	@Test (expected = NoSuchElementException.class)
	public void getLastTest2() {
		assertEquals("1",hi.last());
	}@Test
	public void isEmptyTest1() {
		assertTrue(hi.isEmpty());
	}
	@Test
	public void isEmptyTest2() {
		hi.add("yo");
		assertFalse(hi.isEmpty());
	}
	@Test
	public void sizetest1() {
		int x = hi.size();
		assertEquals(0, x);
	}	
	@Test
	public void sizetest2() {
		hi.add("hi");
		int x = hi.size();
		assertEquals(1, x);
	}	
	@Test
	public void containsTest1() {
		hi.add("hi");
		boolean answer = hi.contains("hi");
		assertEquals(true, answer);
	}	
	@Test
	public void containsTest2() {
		hi.add("hi");
		boolean answer = hi.contains("bye");
		assertEquals(false, answer);
	}	
	@Test
	public void containsTest3() {
		hi.add("bro");
		hi.add("3");
		hi.add("wassup");
		assertTrue(hi.contains("3"));
	}
	
	@Test
	public void containsTest4() {
		boolean answer = hi.contains("bye");
		assertEquals(false, answer);
	}	
	@Test
	public void addTest1() {
		hi.add("hi");
		int answer = hi.size();
		assertEquals(1, answer);
	}	
	
	@Test
	public void addTest2() {
		hi.add("hello");
		hi.add("hi");
		int answer = hi.size();
		assertEquals(2, answer);
	}	
	@Test
	public void removetest1() {
	
		hi.add("hi");
		hi.add("bye");
		hi.remove("bye");
		assertEquals(false, hi.contains("bye"));
			
	}
	
	@Test //check this 
	public void  removetest2() {
		boolean result = hi.remove("bye");
		assertFalse(result);

}
	@Test
	public void removetest3() {
	
		hi.add("hi");
		boolean result = hi.remove("bye");
		assertFalse(result);
		}
	@Test
	public void removeTest4() {
	
		hi.add("hi");
		boolean result = hi.remove("hi");
		assertFalse(hi.contains("hi"));
		}
	@Test
	public void zeroChildRemoveTest5() { 
		hi.add("hi");
		hi.add("how ");
		hi.add("are");
		hi.add("you");
		hi.add("doing");
		hi.remove("doing");
		hi.remove("you");
	}
	@Test
	public void twoChildRemoveTest6() { 
		hi.add(10);
		hi.add(5);
		hi.add(15);
		hi.add(12);
		hi.add(17);
		hi.remove(15);
		answer.add(5);
		answer.add(10);
		answer.add(12);
		answer.add(17);
		assertEquals(answer.toString(),hi.toArrayList().toString());
	}
	@Test
	public void singleChildRemoveTest7() { 
		hi.add(10);
		hi.add(5);
		hi.add(3);
		hi.remove(5);
		answer.add(3);
		answer.add(10);
		assertEquals(answer.toString(),hi.toArrayList().toString());
		
		
	}
	@Test
	public void arrayListTest1() {
		hi.add("hi");
		hi.add("bye");
		hi.add("bro");
		hi.add("yoyoy");
	}
	
@Test 
public void removeAllTest1(){
	hi.add(10);
	hi.add(5);
	hi.add(15);
	hi.add(12);
	hi.add(17);
	hi.add(13);
	hi.add(19);
	answer.add(15);
	answer.add(10);
	
	hi.removeAll(answer);
	ArrayList expected = new ArrayList();
	expected.add(5);
	expected.add(12);
	expected.add(13);
	expected.add(17);
	expected.add(19);
	assertEquals(expected.toString(), hi.toArrayList().toString());
}
@Test 
public void addAllTest1(){

	hi.add(19);
	answer.add(15);
	answer.add(10);
	
	hi.addAll(answer);
	ArrayList expected = new ArrayList();
	expected.add(10);
	expected.add(15);
	expected.add(19);
	assertEquals(expected.toString(), hi.toArrayList().toString());
}
}
