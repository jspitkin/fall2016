package assignment07;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * @author maycol vilchez u0832923
 */

public class BalancedSymbolCheckerTest {

	private String file;
	private String expected;
	private BalancedSymbolChecker check = new BalancedSymbolChecker();

	@Test(expected = FileNotFoundException.class)
	public void testClassNoFileFound() throws FileNotFoundException {

		file = check.checkFile("Class.java");

	}

	@Test
	public void Class1() {
		try {
			file = check.checkFile("Class1.java");
			expected = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class2() {
		try {
			file = check.checkFile("Class2.java");
			expected = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class3() {
		try {
			file = check.checkFile("Class3.java");
			expected = "No errors found. All symbols match.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class4() {
		try {
			file = check.checkFile("Class4.java");
			expected = "ERROR: File ended before closing comment.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class5() {
		try {
			file = check.checkFile("Class5.java");
			expected = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class6() {
		try {
			file = check.checkFile("Class6.java");
			expected = "No errors found. All symbols match.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class7() {
		try {
			file = check.checkFile("Class7.java");
			expected = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class8() {
		try {
			file = check.checkFile("Class8.java");
			expected = "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class9() {
		try {
			file = check.checkFile("Class9.java");
			expected = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class10() {
		try {
			file = check.checkFile("Class10.java");
			expected = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class11() {
		try {
			file = check.checkFile("Class11.java");
			expected = "ERROR: Unmatched symbol at the end of file. Expected }.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class12() {
		try {
			file = check.checkFile("Class12.java");
			expected = "No errors found. All symbols match.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class13() {
		try {
			file = check.checkFile("Class13.java");
			expected = "No errors found. All symbols match.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}

	@Test
	public void Class14() {
		try {
			file = check.checkFile("Class14.java");
			expected = "No errors found. All symbols match.";
			assertEquals(expected, file);
		} catch (FileNotFoundException e) {
			System.out.println("fine not found");
		}
	}
}
