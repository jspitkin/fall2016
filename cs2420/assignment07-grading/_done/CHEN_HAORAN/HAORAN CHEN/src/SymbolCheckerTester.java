package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The tester tests 14 different java files provided by the professor.
 * 
 * @author Haoran Chen
 * @uid u1060286
 */
public class SymbolCheckerTester {

	BalancedSymbolChecker b = new BalancedSymbolChecker();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",b.checkFile("Class1.java"));
	}
	
	@Test
	public void test2() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.",b.checkFile("Class2.java"));
	}
	
	@Test
	public void test3() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.",b.checkFile("Class3.java"));
	}
	
	@Test
	public void test4() throws FileNotFoundException{
		assertEquals("ERROR: File ended before closing comment.",b.checkFile("Class4.java"));
	}
	
	@Test
	public void test5() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.",b.checkFile("Class5.java"));
	}
	
	@Test
	public void test6() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.",b.checkFile("Class6.java"));
	}
	
	@Test
	public void test7() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.",b.checkFile("Class7.java"));
	}
	
	@Test
	public void test8() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.",b.checkFile("Class8.java"));
	}
	
	@Test
	public void test9() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.",b.checkFile("Class9.java"));
	}
	
	@Test
	public void test10() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.",b.checkFile("Class10.java"));
	}
	
	@Test
	public void test11() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.",b.checkFile("Class11.java"));
	}
	
	@Test
	public void test12() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.",b.checkFile("Class12.java"));
	}
	
	@Test
	public void test13() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.",b.checkFile("Class13.java"));
	}
	
	@Test
	public void test14() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.",b.checkFile("Class14.java"));
	}

}
