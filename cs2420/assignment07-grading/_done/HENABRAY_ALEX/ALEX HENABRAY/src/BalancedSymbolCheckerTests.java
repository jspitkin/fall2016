package assignment07;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Alex Henabray (uID: u0795787), last updated 10/19/16
 *
 */
public class BalancedSymbolCheckerTests {

	BalancedSymbolChecker checker;
	@Before
	public void setUp() throws Exception {
		checker = new BalancedSymbolChecker();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClass01() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class1.java").equals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead."));
	}
	
	@Test
	public void testClass02() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class2.java").equals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead."));
	}
	
	@Test
	public void testClass03() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class3.java").equals("No errors found. All symbols match."));
	}

	@Test
	public void testClass04() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class4.java").equals("ERROR: File ended before closing comment."));
	}
	
	@Test
	public void testClass05() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class5.java").equals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead."));
	}
	
	@Test
	public void testClass06() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class6.java").equals("No errors found. All symbols match."));
	}
	
	@Test
	public void testClass07() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class7.java").equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead."));
	}
	
	@Test
	public void testClass08() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class8.java").equals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead."));
	}
	
	@Test
	public void testClass09() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class9.java").equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead."));
	}
	
	@Test
	public void testClass10() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class10.java").equals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead."));
	}
	
	@Test
	public void testClass11() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class11.java").equals("ERROR: Unmatched symbol at the end of file. Expected }."));
	}
	
	@Test
	public void testClass12() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class12.java").equals( "No errors found. All symbols match."));
	}
	
	@Test
	public void testClass13() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class13.java").equals("No errors found. All symbols match."));
	}
	
	@Test
	public void testClass14() throws FileNotFoundException {
		Assert.assertTrue(checker.checkFile("Class14.java").equals("No errors found. All symbols match."));
	}

}
