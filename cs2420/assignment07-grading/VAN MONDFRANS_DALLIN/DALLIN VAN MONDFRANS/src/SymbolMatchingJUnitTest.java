/**
 * Assignment 7 - Symbol Checker
 * Name: Dallin Van Mondfrans
 * uID: u0717113
 * Date: October 19, 2016
 */

package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SymbolMatchingJUnitTest {

BalancedSymbolChecker bsc;
	
	@Before
	public void setUp() throws Exception {
		bsc = new BalancedSymbolChecker();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSimpleSymbolMatchingFile() {
		try {
			String result = bsc.checkFile("simpleSymbolMatching");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void unmatchedSymbolTest() {
		try {
			String result = bsc.checkFile("unmatchedSymbol");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 1 and column 4. Expected }, but read ) instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void unmatchedSymbol2Test() {
		try {
			String result = bsc.checkFile("unmatchedSymbol2");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 2 and column 3. Expected ), but read ] instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void unmatchedSymbolAtEOFTest() {
		try {
			String result = bsc.checkFile("unmatchedSymbolAtEOF");
			assertTrue(result.equals("ERROR: Unmatched symbol at the end of file. Expected }."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void emptyFileTest() {
		try {
			String result = bsc.checkFile("emptyFile");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void balancedSymbolsWithBlockCommentTest() {
		try {
			String result = bsc.checkFile("balancedSymbolsAndComment");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void balancedSymbolsWithLineCommentTest() {
		try {
			String result = bsc.checkFile("balancedSymbolsAndLineComment");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void balancedSymbolsWithCommentInBetweenSymbolsTest() {
		try {
			String result = bsc.checkFile("commentInBetweenBrackets");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void closingBracketsCommentedOutWithLineComment() {
		try {
			String result = bsc.checkFile("commentOutClosingBrackets");
			assertTrue(result.equals("ERROR: Unmatched symbol at the end of file. Expected )."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void unfinishedBlockComment() {
		try {
			String result = bsc.checkFile("unfinishedBlockComment");
			assertTrue(result.equals("ERROR: File ended before closing comment."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCodeSymbolCheck() {
		try {
			String result = bsc.checkFile("testCodeSymbolCheck");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCodeSymbolCheckWithMissingBracket() {
		try {
			String result = bsc.checkFile("testCodeMissingBracket");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 6 and column 3. Expected ), but read } instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void closingBracketBeforeOpening() {
		try {
			String result = bsc.checkFile("closingBracketBeforeOpening");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 1 and column 1. Expected  , but read ) instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDoublyLinkedListCode() {
		try {
			String result = bsc.checkFile("DoublyLinkedListFile");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBalancedSymbolCheckerCode() {
		try {
			String result = bsc.checkFile("BalancedSymbolCheckerFile");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass1() {
		try {
			String result = bsc.checkFile("Class1.java");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass2() {
		try {
			String result = bsc.checkFile("Class2.java");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass3() {
		try {
			String result = bsc.checkFile("Class3.java");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass4() {
		try {
			String result = bsc.checkFile("Class4.java");
			assertTrue(result.equals("ERROR: File ended before closing comment."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass5() {
		try {
			String result = bsc.checkFile("Class5.java");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass6() {
		try {
			String result = bsc.checkFile("Class6.java");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass7() {
		try {
			String result = bsc.checkFile("Class7.java");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass8() {
		try {
			String result = bsc.checkFile("Class8.java");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass9() {
		try {
			String result = bsc.checkFile("Class9.java");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testClass10() {
		try {
			String result = bsc.checkFile("Class10.java");
			assertTrue(result.equals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass11() {
		try {
			String result = bsc.checkFile("Class11.java");
			assertTrue(result.equals("ERROR: Unmatched symbol at the end of file. Expected }."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass12() {
		try {
			String result = bsc.checkFile("Class12.java");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass13() {
		try {
			String result = bsc.checkFile("Class13.java");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testClass14() {
		try {
			String result = bsc.checkFile("Class14.java");
			assertTrue(result.equals("No errors found. All symbols match."));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
