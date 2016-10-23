package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * @author LinJia
 * uid: u1091732
 * test checkFile method
 */
public class JTestBSC {

	private BalancedSymbolChecker BSC = new BalancedSymbolChecker();

	@Test
	public void testClass1 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class1.java").equals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead."));			
	}

	@Test
	public void testClass2 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class2.java").equals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead."));			
	}
	
	@Test
	public void testClass3 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class3.java").equals("No errors found. All symbols match."));			
	}
	
	@Test
	public void testClass4 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class4.java").equals("ERROR: File ended before closing comment."));			
	}
	
	@Test
	public void testClass5 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class5.java").equals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead."));			
	}
	
	@Test
	public void testClass7 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class7.java").equals( "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead."));			
	}
	
	@Test
	public void testClass8 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class8.java").equals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead."));			
	}
	
	@Test
	public void testClass9 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class9.java").equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead."));			
	}
	
	@Test
	public void testClass10 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class10.java").equals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead."));			
	}
	
	@Test
	public void testClass11() throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class11.java").equals("ERROR: Unmatched symbol at the end of file. Expected }."));			
	}
	
	@Test
	public void testClass12 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class12.java").equals("No errors found. All symbols match."));			
	}
	
	@Test
	public void testClass13 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class13.java").equals("No errors found. All symbols match."));			
	}
	
	@Test
	public void testClass14 () throws FileNotFoundException
	{
		assertTrue( BSC.checkFile("Class14.java").equals("No errors found. All symbols match."));			
	}
	
	
}
