package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * It is a tester class for LinkedListStack class. It tests all the methods in the class
 * 
 * @author Haoran Chen
 * @uid u1060286
 */
public class LinkedListStackTester {

	LinkedListStack<Integer> tester = new LinkedListStack<Integer>();
	@Before
	public void setUp() throws Exception {
		tester.push(1);
		tester.push(3);
		tester.push(5);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void emptyTest1() {
		tester = new LinkedListStack<Integer>();
		assertTrue(tester.isEmpty());
	}
	
	@Test
	public void emptyTest2() {
		assertFalse(tester.isEmpty());
	}
	
	@Test
	public void clearTest() {
		assertFalse(tester.size() == 0);
		tester.clear();
		assertTrue(tester.size() == 0);
	}
	
	@Test
	public void emptySize() {
		tester.clear();
		assertEquals(tester.size(), 0);
	}
	
	@Test
	public void fullSize() {
		assertEquals(tester.size(), 3);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void peekEmpty() {
		tester.clear();
		tester.peek();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void popEmpty() {
		tester.clear();
		tester.pop();
	}
	
	@Test 
	public void peek() {		
		assertTrue(tester.peek().equals(5));
		
	}
	
	@Test 
	public void pop() {		
		assertTrue(tester.pop().equals(5));
		assertEquals(tester.size(),2);
		assertTrue(tester.peek().equals(3));
		
	}
	

}
