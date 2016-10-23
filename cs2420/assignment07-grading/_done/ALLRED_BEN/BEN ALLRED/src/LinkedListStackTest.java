package assignment07;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for LinkedListStack class
 * 
 * @author Benjamin Allred u1090524
 *
 */
public class LinkedListStackTest {
	
	LinkedListStack<String> list = new LinkedListStack<String>();
	
	/**
	 * set up list
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		list.push("blah blah blah");
		list.push("other stuff");
		list.push("dingus");
	}

	/**
	 * tests if clear truly clears it
	 */
	@Test
	public void testClear() {
		list.clear();
		assertTrue(list.isEmpty());
	}
	
	/**
	 * tests peek to return correct data
	 */
	@Test
	public void testPeek() {
		assertTrue(list.peek().equals("dingus"));
	}
	
	/**
	 * tests that pop returns correct data and removes that data
	 */
	@Test
	public void testPop() {
		assertTrue(list.pop().equals("dingus"));
		assertTrue(list.pop().equals("other stuff"));
		assertTrue(list.pop().equals("blah blah blah"));
	}
	
	/**
	 * tests that push puts data on top of the stack
	 */
	@Test
	public void testPush() {
		assertTrue(list.pop().equals("dingus"));
		list.push("dingus");
		assertTrue(list.pop().equals("dingus"));
		list.push("Hello darkness");
		assertTrue(list.peek().equals("Hello darkness"));
		assertTrue(list.pop().equals("Hello darkness"));
	}
	
	/**
	 * tests to ensure size keeps track of the correct number
	 */
	@Test
	public void testSize() {
		assertTrue(list.size() == 3);
		assertTrue(list.pop().equals("dingus"));
		assertTrue(list.size() == 2);
		list.push("Hello darkness");
		assertTrue(list.size() == 3);
		list.clear();
		assertTrue(list.size() == 0);
		list.push("Hello darkness");
		assertTrue(list.size() == 1);
		assertTrue(list.peek().equals("Hello darkness"));
		assertTrue(list.size() == 1);
	}

}
