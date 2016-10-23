package assignment07;

import static org.junit.Assert.*;


import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit Tester for the LinkedListStack class.
 * 
 * @author Makenzie Peacock (u0873188)
 */
public class LinkedListStackTester {

	LinkedListStack<Character> charStack; 
	LinkedListStack<String> stringStack;
	LinkedListStack<Integer> intStack;

	@Before
	public void setUp() throws Exception {
		charStack = new LinkedListStack<Character>();
		stringStack = new LinkedListStack<String>();
		intStack = new LinkedListStack<Integer>();
	}

	//Tests for the push method, along with size and isEmpty.
	@Test
	public void pushCharBasic() {
		charStack.push('a');

		assertTrue(charStack.size() == 1);
		assertFalse(charStack.isEmpty());
	}

	@Test
	public void pushStringBasic() {
		stringStack.push("test");

		assertTrue(stringStack.size() == 1);
		assertFalse(stringStack.isEmpty());
	}

	@Test
	public void pushIntBasic() {
		intStack.push(0);

		assertTrue(intStack.size() == 1);
		assertFalse(intStack.isEmpty());
	}

	@Test
	public void pushCharMultipleValues() {
		charStack.push('a');
		charStack.push('b');
		charStack.push('c');
		charStack.push('A');
		charStack.push('B');
		charStack.push('C');

		assertTrue(charStack.size() == 6);
		assertFalse(charStack.isEmpty());
	}

	@Test
	public void pushStringMultipleValues() {
		stringStack.push("I'm");
		stringStack.push("just");
		stringStack.push("a");
		stringStack.push("poor");
		stringStack.push("boy");
		stringStack.push("nobody");
		stringStack.push("loves");
		stringStack.push("me");

		assertTrue(stringStack.size() == 8);
	}

	@Test
	public void pushIntMultipleValues() {
		intStack.push(0);
		intStack.push(2);
		intStack.push(4);
		intStack.push(8);
		intStack.push(16);

		assertTrue(intStack.size() == 5);
	}

	//tests for the peek method
	@Test
	public void peekCharOneInStack() {
		charStack.push('a');

		assertTrue(charStack.peek() == 'a');
	}

	@Test
	public void peekStringOneInStack() {
		stringStack.push("test");

		assertTrue(stringStack.peek() == "test");
	}

	@Test
	public void peekIntOneInStack() {
		intStack.push(0);

		assertTrue(intStack.peek() == 0);
	}

	@Test
	public void peekCharMultipleValuesInStack() {
		charStack.push('a');
		charStack.push('b');
		charStack.push('c');
		charStack.push('A');
		charStack.push('B');
		charStack.push('C');

		assertTrue(charStack.peek() == 'C');
		assertTrue(charStack.size() == 6);
	}

	@Test
	public void peekStringMultipleValuesInStack() {
		stringStack.push("Is");
		stringStack.push("this");
		stringStack.push("the");
		stringStack.push("real");
		stringStack.push("life?");
		stringStack.push("Is");
		stringStack.push("this");
		stringStack.push("just");
		stringStack.push("fantasy?");

		assertTrue(stringStack.peek() == "fantasy?");
		assertTrue(stringStack.size() == 9);
	}
	
	@Test
	public void peekIntMulitpleValuesInStack() {
		intStack.push(0);
		intStack.push(2);
		intStack.push(4);
		intStack.push(8);
		intStack.push(16);

		assertTrue(intStack.peek() == 16);
		assertTrue(intStack.size() == 5);
	}

	//tests including the clear method
	@Test (expected = NoSuchElementException.class)
	public void peekCharOnEmptyList(){
		charStack.push('a');
		charStack.push('b');
		charStack.push('c');
		charStack.push('A');
		charStack.push('B');
		charStack.push('C');
		
		charStack.clear();
		
		charStack.peek();
	}

	@Test (expected = NoSuchElementException.class)
	public void peekStringOnEmptyList(){
		stringStack.push("Caught");
		stringStack.push("in");
		stringStack.push("a");
		stringStack.push("landslide.");
		
		stringStack.clear(); 
		
		stringStack.peek();
	}

	@Test (expected = NoSuchElementException.class)
	public void peekIntOnEmptyList(){
		intStack.push(0);
		intStack.push(2);
		intStack.push(4);
		intStack.push(8);
		intStack.push(16);
		
		intStack.clear();
		
		intStack.peek();
	}

	//tests for the pop method
	@Test
	public void popCharBasic() {
		charStack.push('a');

		assertTrue(charStack.pop() == 'a');
		assertTrue(charStack.isEmpty());
	}

	@Test
	public void popStringBasic() {
		stringStack.push("test");

		assertTrue(stringStack.pop() == "test");
		assertTrue(stringStack.isEmpty());
	}

	@Test
	public void popIntBasic() {
		intStack.push(0);

		assertTrue(intStack.pop() == 0);
		assertTrue(intStack.isEmpty());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void popCharOnEmptyList(){
		charStack.peek();
	}

	@Test (expected = NoSuchElementException.class)
	public void popStringOnEmptyList(){
		stringStack.peek();
	}

	@Test (expected = NoSuchElementException.class)
	public void popIntOnEmptyList(){
		intStack.peek();
	}
	
}
