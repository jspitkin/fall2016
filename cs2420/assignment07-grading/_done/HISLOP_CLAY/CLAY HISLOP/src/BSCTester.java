package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/* @author Clayton Hislop
* uID: u0515744
*/
public class BSCTester {

	BalancedSymbolChecker checker = new BalancedSymbolChecker();
	
	@Test
	public void Class1Test() {
		try {
			assertEquals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.", checker.checkFile("Class1.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class2Test() {
		try {
			assertEquals( "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.", checker.checkFile("Class2.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class3Test() {
		try {
			assertEquals("No errors found. All symbols match.", checker.checkFile("Class3.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class4Test() {
		try {
			assertEquals("ERROR: File ended before closing comment.", checker.checkFile("Class4.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class5Test() {
		try {
			assertEquals( "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.", checker.checkFile("Class5.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class6Test() {
		try {
			assertEquals( "No errors found. All symbols match.", checker.checkFile("Class6.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class7Test() {
		try {
			assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.", checker.checkFile("Class7.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class8Test() {
		try {
			assertEquals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.", checker.checkFile("Class8.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class9Test() {
		try {
			assertEquals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.", checker.checkFile("Class9.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class10Test() {
		try {
			assertEquals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.", checker.checkFile("Class10.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class11Test() {
		try {
			assertEquals("ERROR: Unmatched symbol at the end of file. Expected }.", checker.checkFile("Class11.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class12Test() {
		try {
			assertEquals( "No errors found. All symbols match.", checker.checkFile("Class12.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class13Test() {
		try {
			assertEquals( "No errors found. All symbols match.", checker.checkFile("Class13.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class14Test() {
		try {
			assertEquals( "No errors found. All symbols match.", checker.checkFile("Class14.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class15Test() {
		try {
			assertEquals( "No errors found. All symbols match.", checker.checkFile("Class15.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class16Test() {
		try {
			assertEquals( "No errors found. All symbols match.", checker.checkFile("Class16.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
	
	@Test
	public void Class17Test() {
		try {
			assertEquals( "No errors found. All symbols match.", checker.checkFile("Class17.java"));
		} catch (FileNotFoundException e1) {
			System.err.println("File not found");
		}				
	}
}
