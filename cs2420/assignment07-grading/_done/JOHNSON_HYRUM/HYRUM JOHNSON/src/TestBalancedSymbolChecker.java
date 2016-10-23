package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
/**
 * TestBalanced SymbolChecker.java
 * 
 * @author Hyrum Johnson
 */
public class TestBalancedSymbolChecker {

	BalancedSymbolChecker BalancedSymbolChecker;
	

	@Before
	public void setUp() throws FileNotFoundException {
		BalancedSymbolChecker = new BalancedSymbolChecker();
	}

	@Test
	public void TestForClass1() {
		try {
			String expectedString = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
			assertEquals(expectedString ,
					BalancedSymbolChecker.checkFile("Class1.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass2() {
		try {
			String expectedString = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
			assertEquals(expectedString ,
					BalancedSymbolChecker.checkFile("Class2.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass3() {
		
		try {
			String expectedString = "No errors found. All symbols match.";
			assertEquals(expectedString, BalancedSymbolChecker.checkFile("Class3.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass4() {
		try {
			String expectedString = "ERROR: File ended before closing comment.";
			assertEquals(expectedString, BalancedSymbolChecker.checkFile("Class4.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass5() {
		try {
			String expectedString = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
			assertEquals(expectedString,
					BalancedSymbolChecker.checkFile("Class5.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass6() {
		try {
			String expectedString = "No errors found. All symbols match.";
			assertEquals(expectedString, BalancedSymbolChecker.checkFile("Class6.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass7() {
		try {
			String expectedString = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
			assertEquals(expectedString,
					BalancedSymbolChecker.checkFile("Class7.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass8() {
		try {
			String expectedString = "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
			assertEquals(expectedString,
					BalancedSymbolChecker.checkFile("Class8.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass9() {
		try {
			String expectedString = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
			assertEquals(expectedString,
					BalancedSymbolChecker.checkFile("Class9.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass10() {
		try {
			String expectedString = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
			assertEquals(expectedString,
					BalancedSymbolChecker.checkFile("Class10.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass11() {
		try {
			String expetedString = "ERROR: Unmatched symbol at the end of file. Expected }.";
			assertEquals(expetedString,
					BalancedSymbolChecker.checkFile("Class11.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass12() {
		try {
			String expetedString = "No errors found. All symbols match.";
			assertEquals(expetedString , BalancedSymbolChecker.checkFile("Class12.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass13() {
		try {
			String expetedString = "No errors found. All symbols match.";
			assertEquals(expetedString, BalancedSymbolChecker.checkFile("Class13.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

	@Test
	public void TestForClass14() {
		try {
			String expetedString = "No errors found. All symbols match.";
			assertEquals(expetedString , BalancedSymbolChecker.checkFile("Class14.java"));
		} catch (FileNotFoundException e) {
			fail("File could not be found");
		}
	}

}
