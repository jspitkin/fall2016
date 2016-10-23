package assignment07;

import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Alex Henabray (uID: u0795787), last modified 10/19/16
 * 
 * This class tests the methods featured in the LinkedListStack Class.
 *
 */

public class LinkedListStackTests {

	LinkedListStack<Integer> intStack; 
	LinkedListStack<Integer> diffIntStack;
	LinkedListStack<String> stringStack;
	LinkedListStack<String> diffStringStack;
	LinkedListStack<String> emptyStack;

	@Before
	public void setUp() throws Exception {

		// Note: The setUp method implicitly tests the push method. An
		// error message will be displayed if something goes wrong
		// while calling the push method.

		intStack = new LinkedListStack<Integer>();
		for(int item = 1; item <= 5; item++) {
			intStack.push(item);
		}

		diffIntStack = new LinkedListStack<Integer>();
		for(int item = 1; item <= 10; item += 2) {
			diffIntStack.push(item);
		}

		String[] wordList1 = new String[] {"big cat", "red kite", "yellow bus", 
				"complex analysis", "computer science"};

		String[] wordList2 = new String[] {"big cat", "green apple", null, 
				"[4]", "computer science"};

		stringStack = new LinkedListStack<String>();
		diffStringStack = new LinkedListStack<String>();

		for(int index = 0; index < wordList1.length; index++) {
			stringStack.push(wordList1[index]);
			diffStringStack.push(wordList2[index]);
		}

		emptyStack = new LinkedListStack<String>();
	}


	@Test
	public void testSize() {
		Assert.assertTrue(intStack.size() == 5);
		Assert.assertTrue(diffIntStack.size() == 5);
		Assert.assertTrue(stringStack.size() == 5);
		// TA Question: If we add / remove null to a doubly linked list / dllStack,
		// do I increment size?
		Assert.assertTrue(diffStringStack.size() == 5);
		Assert.assertTrue(emptyStack.size() == 0);
		
		intStack.pop();
		intStack.pop();
		Assert.assertTrue(intStack.size() == 3);
		
		intStack.push(3);
		Assert.assertTrue(intStack.size() == 4);
	}

	@Test
	public void testPopWithIntStack() {

		Integer result = intStack.pop();
		Assert.assertTrue(result.equals(5));

		result = intStack.pop();
		Assert.assertTrue(result.equals(4));

		result = intStack.pop();
		Assert.assertTrue(result.equals(3));

		result = intStack.pop();
		Assert.assertTrue(result.equals(2));

		result = intStack.pop();
		Assert.assertTrue(result.equals(1));
	}

	@Test
	public void testPopWithStringStack() {

		String result = stringStack.pop();
		Assert.assertTrue(result.equals("computer science"));

		result = stringStack.pop();
		Assert.assertTrue(result.endsWith("complex analysis"));

		result = stringStack.pop();
		Assert.assertTrue(result.equals("yellow bus"));

		result = stringStack.pop();
		Assert.assertTrue(result.equals("red kite"));

		result = stringStack.pop();
		Assert.assertTrue(result.endsWith("big cat"));

		Assert.assertTrue(stringStack.size() == 0);
	}

	@Test
	public void testPopWithDiffStringStack() {

		String result = diffStringStack.pop();
		Assert.assertTrue(result.equals("computer science"));

		result = diffStringStack.pop();
		Assert.assertTrue(result.equals("[4]"));

		result = diffStringStack.pop();
		Assert.assertTrue(result == null);

		result = diffStringStack.pop();
		Assert.assertTrue(result.equals("green apple"));

		result = diffStringStack.pop();
		Assert.assertTrue(result.endsWith("big cat"));

		Assert.assertTrue(diffStringStack.size() == 0);
	}

	@Test
	public void testPopWithDiffIntStack() {
		Assert.assertTrue(diffIntStack.pop().equals(9));
		Assert.assertTrue(diffIntStack.pop().equals(7));
		Assert.assertTrue(diffIntStack.pop().equals(5));
		Assert.assertTrue(diffIntStack.pop().equals(3));
		Assert.assertTrue(diffIntStack.pop().equals(1));
	}

	@Test(expected = NoSuchElementException.class)
	public void testPopException() {
		emptyStack.pop();
	}

	@Test(expected = NoSuchElementException.class)
	public void testPopException2() {
		for(int index = 1; index <= 6; index++) {
			diffStringStack.pop();
		}
	}

	@Test
	public void testPeek() {
		Assert.assertTrue(intStack.peek().equals(5));
		Assert.assertTrue(stringStack.peek().equals("computer science"));

		intStack.pop();
		stringStack.pop();
		Assert.assertTrue(intStack.peek().equals(4));
		Assert.assertTrue(stringStack.peek().equals("complex analysis"));

		intStack.pop();
		stringStack.pop();
		Assert.assertTrue(intStack.peek().equals(3));
		Assert.assertTrue(stringStack.peek().equals("yellow bus"));
	}

	@Test(expected = NoSuchElementException.class)
	public void testPeekException() {
		emptyStack.peek();
	}

	@Test(expected = NoSuchElementException.class)
	public void testPeekException2() {
		for(int index = 1; index <= 5; index++) {
			diffStringStack.pop();
		}
		diffStringStack.peek();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertFalse(intStack.isEmpty());
		Assert.assertFalse(diffIntStack.isEmpty());
		Assert.assertFalse(stringStack.isEmpty());
		Assert.assertFalse(diffStringStack.isEmpty());
		Assert.assertTrue(emptyStack.isEmpty());

		for(int index = 1; index <= 5; index++) {
			diffStringStack.pop();
		}

		Assert.assertTrue(diffStringStack.isEmpty());
	}

	@Test
	public void testClear() {
		intStack.clear();
		Assert.assertTrue(intStack.size() == 0);

		for(int item = 1; item <= 100; item++) {
			intStack.push(item);
		}

		Assert.assertTrue(intStack.size() == 100);

		intStack.clear();

		Assert.assertTrue(intStack.size() == 0);

		stringStack.clear();
		Assert.assertTrue(stringStack.size() == 0);

		diffStringStack.clear();
		Assert.assertTrue(stringStack.size() == 0);
	}

}
