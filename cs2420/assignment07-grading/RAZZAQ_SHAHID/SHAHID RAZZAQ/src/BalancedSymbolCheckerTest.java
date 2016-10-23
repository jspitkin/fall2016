package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * Junit Tests for BalancedSymbolChecker Class
 * 
 * @author ShahidBilal Razzaq
 * u0996062
 * 
 *
 */
public class BalancedSymbolCheckerTest {
	
	BalancedSymbolChecker symbolChecker = new BalancedSymbolChecker();

	@Before
	public void setUp() throws Exception {
		
		
	}
	
	//Tests the sample class provided, as well as my own classes, and modified classes for a variety of testing scenarios 
	@Test
	(expected = FileNotFoundException.class)
	public void testNonExistingFile() throws FileNotFoundException{
		assertEquals( new FileNotFoundException() , symbolChecker.checkFile("notAFile.txt"));
	}
	@Test
	public void testGivenClass1() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.",symbolChecker.checkFile("Class1.java"));
	}
	@Test
	public void testGivenClass2() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.", symbolChecker.checkFile("Class2.java"));
	}
	@Test
	public void testGivenClass3() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.", symbolChecker.checkFile("Class3.java"));
	}
	@Test
	public void testGivenClass4() throws FileNotFoundException{
		assertEquals("ERROR: File ended before closing comment.", symbolChecker.checkFile("Class4.java"));
	}
	@Test
	public void testGivenClass5() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.", symbolChecker.checkFile("Class5.java"));
	}
	@Test
	public void testGivenClass6() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.", symbolChecker.checkFile("Class6.java"));
	}
	
	@Test
	public void testGivenClass7() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.", symbolChecker.checkFile("Class7.java"));
	}
	
	@Test
	public void testGivenClass8() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.", symbolChecker.checkFile("Class8.java"));
	}
	
	@Test
	public void testGivenClass9() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.", symbolChecker.checkFile("Class9.java"));
	}
	
	@Test
	public void testGivenClass10() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.", symbolChecker.checkFile("Class10.java"));
	}
	
	@Test
	public void testGivenClass11() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.", symbolChecker.checkFile("Class11.java"));
	}
	
	@Test
	public void testGivenClass12() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.", symbolChecker.checkFile("Class12.java"));
	}
	
	@Test
	public void testGivenClass13() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.", symbolChecker.checkFile("Class13.java"));
	}
	
	@Test
	public void testGivenClass14() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.", symbolChecker.checkFile("Class14.java"));
	}
	@Test
	public void testMyClass15() throws FileNotFoundException{
		assertEquals("ERROR: File ended before closing comment.",symbolChecker.checkFile("Class15.java"));
	}
	@Test
	public void testMyClass16() throws FileNotFoundException{
		assertEquals("No errors found. All symbols match.", symbolChecker.checkFile("Class16.java"));
	}
	@Test
	public void testMyClass17() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 7 and column 6. Expected }, but read ] instead.", symbolChecker.checkFile("Class17.java"));
	}
	@Test
	public void testMyClass18() throws FileNotFoundException{
		assertEquals("ERROR: Unmatched symbol at line 10 and column 1. Expected  , but read ] instead.", symbolChecker.checkFile("Class18.java"));
	}
	
	
	
	
	
	
	

	
	
	
	
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
}
