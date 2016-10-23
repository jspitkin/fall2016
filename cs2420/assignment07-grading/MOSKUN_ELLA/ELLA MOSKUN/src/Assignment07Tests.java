package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests cases for CS 2420's Assignment 07. Includes tests for LinkedListStack
 * and BalancedSymbolChecker.
 * 
 * @author Ella Moskun u0897252
 */
public class Assignment07Tests {

	LinkedListStack<Integer> emptyStack;
	LinkedListStack<Integer> normalStack;
	BalancedSymbolChecker checker;

	@Before
	public void setUp() {
		emptyStack = new LinkedListStack<Integer>();
		normalStack = new LinkedListStack<Integer>();
		normalStack.push(1);
		normalStack.push(3);
		normalStack.push(2);
		checker = new BalancedSymbolChecker();
	}

	/*
	 * ---LinkedListStack---
	 */

	@Test
	public void testPushWhenEmpty() {
		emptyStack.push(4);
		assertEquals(1, emptyStack.size());
		assertEquals(4, (int) emptyStack.pop());
	}

	@Test
	public void testPushWhenNotEmpty() {
		normalStack.push(4);
		normalStack.push(0);
		assertEquals(5, normalStack.size());
		assertEquals(0, (int) normalStack.pop());
		assertEquals(4, (int) normalStack.pop());

	}

	@Test
	public void testPushNull() {
		normalStack.push(null);
		assertEquals(4, normalStack.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void testPopWhenEmpty() {
		emptyStack.pop();
	}

	@Test
	public void testPopWhenNotEmpty() {
		assertEquals(2, (int) normalStack.pop());
		assertEquals(3, (int) normalStack.pop());
		assertEquals(1, normalStack.size());
		assertEquals(1, (int) normalStack.pop());
		assertEquals(0, normalStack.size());
	}

	@Test
	public void testPopNull() {
		normalStack.push(null);
		normalStack.push(null);
		assertEquals(null, normalStack.pop());
		assertEquals(4, normalStack.size());
		assertEquals(null, normalStack.pop());
		assertEquals(3, normalStack.size());
	}

	@Test(expected = NoSuchElementException.class)
	public void testPeekWhenEmpty() {
		emptyStack.peek();
	}

	@Test
	public void testPeekWhenNotEmpty() {
		assertEquals(2, (int) normalStack.peek());
		assertEquals(2, (int) normalStack.peek());
		assertEquals(3, normalStack.size());
		assertEquals(2, (int) normalStack.pop());
	}

	@Test
	public void testPeekNull() {
		normalStack.push(null);
		assertEquals(null, normalStack.peek());
		assertEquals(null, normalStack.peek());
		assertEquals(4, normalStack.size());
		assertEquals(null, normalStack.pop());
	}

	@Test
	public void testIsEmptyWhenEmpty() {
		assertTrue(emptyStack.isEmpty());
	}

	@Test
	public void testIsEmptyWhenNotEmpty() {
		assertFalse(normalStack.isEmpty());
	}

	@Test
	public void testClearWhenEmpty() {
		emptyStack.clear();
		assertTrue(emptyStack.isEmpty());
	}

	@Test
	public void testClearWhenNotEmpty() {
		normalStack.clear();
		assertTrue(normalStack.isEmpty());
	}

	@Test
	public void testSizeWhenEmpty() {
		assertEquals(0, emptyStack.size());
	}

	@Test
	public void testSizeWhenNotEmpty() {
		assertEquals(3, normalStack.size());
	}

	/*
	 * ---BalancedSymbolChecker---
	 */

	@Test(expected = FileNotFoundException.class)
	public void testCheckFileWithNonexistantFile() throws FileNotFoundException {
		checker.checkFile("please_for_the_love_of_cats_do_not_let_this_file_exist.java");
	}

	@Test
	public void testCheckFileClass1() throws FileNotFoundException {
		String expected = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
		String actual = checker.checkFile("Class1.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass2() throws FileNotFoundException {
		String expected = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
		String actual = checker.checkFile("Class2.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass3() throws FileNotFoundException {
		String expected = "No errors found. All symbols match.";
		String actual = checker.checkFile("Class3.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass4() throws FileNotFoundException {
		String expected = "ERROR: File ended before closing comment.";
		String actual = checker.checkFile("Class4.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass5() throws FileNotFoundException {
		String expected = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
		String actual = checker.checkFile("Class5.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass6() throws FileNotFoundException {
		String expected = "No errors found. All symbols match.";
		String actual = checker.checkFile("Class6.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass7() throws FileNotFoundException {
		String expected = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
		String actual = checker.checkFile("Class7.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass8() throws FileNotFoundException {
		String expected = "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
		String actual = checker.checkFile("Class8.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass9() throws FileNotFoundException {
		String expected = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
		String actual = checker.checkFile("Class9.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass10() throws FileNotFoundException {
		String expected = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
		String actual = checker.checkFile("Class10.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClas11() throws FileNotFoundException {
		String expected = "ERROR: Unmatched symbol at the end of file. Expected }.";
		String actual = checker.checkFile("Class11.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass12() throws FileNotFoundException {
		String expected = "No errors found. All symbols match.";
		String actual = checker.checkFile("Class12.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass13() throws FileNotFoundException {
		String expected = "No errors found. All symbols match.";
		String actual = checker.checkFile("Class13.java");
		assertEquals(expected, actual);
	}

	@Test
	public void testCheckFileClass14() throws FileNotFoundException {
		String expected = "No errors found. All symbols match.";
		String actual = checker.checkFile("Class14.java");
		assertEquals(expected, actual);
	}
}
