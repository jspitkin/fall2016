//Cole Cruz
package assignment07;

import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.BeforeClass;

/**
 * JUnit tester for the BalancedSymbolChecker class.
 * 
 * @author Cole Cruz
 *
 */
public class JUnitTester {

	private static BalancedSymbolChecker checker;
	private static LinkedListStack<Character> stack;

	/**
	 * Whatever setup needs to be done before test run.
	 */
	@BeforeClass
	public static void setUpBeforeClass() {
		checker = new BalancedSymbolChecker();
		stack = new LinkedListStack<Character>();
	}

	/**
	 * Various tests for each method of the AnagramUtil class.
	 */
	@Test
	public void class1() throws FileNotFoundException {
		Assert.assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",
				checker.checkFile("Class1.java"));
	}

	@Test
	public void class2() throws FileNotFoundException {
		Assert.assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",
				checker.checkFile("Class2.java"));
	}

	@Test
	public void class3() throws FileNotFoundException {
		Assert.assertEquals("No errors found. All symbols match.", checker.checkFile("Class3.java"));
	}

	@Test
	public void class4() throws FileNotFoundException {
		Assert.assertEquals("ERROR: File ended before closing comment.", checker.checkFile("Class4.java"));
	}

	@Test
	public void class5() throws FileNotFoundException {
		Assert.assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",
				checker.checkFile("Class5.java"));
	}

	@Test
	public void class6() throws FileNotFoundException {
		Assert.assertEquals("No errors found. All symbols match.", checker.checkFile("Class6.java"));
	}

	@Test
	public void class7() throws FileNotFoundException {
		Assert.assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",
				checker.checkFile("Class7.java"));
	}

	@Test
	public void class8() throws FileNotFoundException {
		Assert.assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",
				checker.checkFile("Class8.java"));
	}

	@Test
	public void class9() throws FileNotFoundException {
		Assert.assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",
				checker.checkFile("Class9.java"));
	}

	@Test
	public void class10() throws FileNotFoundException {
		Assert.assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",
				checker.checkFile("Class10.java"));
	}

	@Test
	public void class11() throws FileNotFoundException {
		Assert.assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",
				checker.checkFile("Class11.java"));
	}

	@Test
	public void class12() throws FileNotFoundException {
		Assert.assertEquals("No errors found. All symbols match.", checker.checkFile("Class12.java"));
	}

	@Test
	public void class13() throws FileNotFoundException {
		Assert.assertEquals("No errors found. All symbols match.", checker.checkFile("Class13.java"));
	}

	@Test
	public void class14() throws FileNotFoundException {
		Assert.assertEquals("No errors found. All symbols match.", checker.checkFile("Class14.java"));
	}

	@Test(expected = FileNotFoundException.class)
	public void fileDoesntExist() throws FileNotFoundException {
		checker.checkFile("thisClassDoesntExist.java");
	}

	@Test
	public void isEmpty() {
		Assert.assertTrue(stack.isEmpty());
	}

	@Test
	public void push() {
		stack.push('d');
		stack.push('c');
		stack.push('b');
	}

	@Test
	public void peek() {
		stack.push('a');
		Assert.assertEquals('a', (char) stack.peek());
	}

	@Test
	public void pop() {
		stack.push('a');
		Assert.assertEquals('a', (char) stack.pop());
	}

	@Test
	public void size() {
		Assert.assertEquals(4, stack.size());
	}

	@Test
	public void clear() {
		stack.clear();
	}

	@Test(expected = NoSuchElementException.class)
	public void peekError() {
		stack.peek();
	}

	@Test(expected = NoSuchElementException.class)
	public void popError() {
		stack.pop();
	}

}
