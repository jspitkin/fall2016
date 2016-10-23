package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * BalancedSymbolChecker Tests
 * 
 * @author Amir Mohsenian
 * 
 * u0737564
 *
 */
public class BalancedSymbolCheckerTest {
	
	

	private static BalancedSymbolChecker tests = new BalancedSymbolChecker();
	
	public static void main(String[] args) throws FileNotFoundException
	{
		
		System.out.println(""+tests.checkFile("Class6.java"));
		
	}

	@Test
	public void testing1 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class1.java").equals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead."));			
	}

	@Test
	public void testing2 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class2.java").equals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead."));			
	}
	
	@Test
	public void testing3 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class3.java").equals("No errors found. All symbols match."));			
	}
	
	@Test
	public void testing4 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class4.java").equals("ERROR: File ended before closing comment."));			
	}
	
	@Test
	public void testing5 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class5.java").equals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead."));			
	}
	
	@Test
	public void testing6 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class6.java").equals("No errors found. All symbols match."));			
	}
	
	@Test
	public void testing7 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class7.java").equals( "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead."));			
	}
	
	@Test
	public void testing8 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class8.java").equals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead."));			
	}
	
	@Test
	public void testing9 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class9.java").equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead."));			
	}
	
	@Test
	public void testing10 () throws FileNotFoundException
	{
		assertEquals(true, tests.checkFile("Class10.java").equals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead."));			
	}
	

	
}