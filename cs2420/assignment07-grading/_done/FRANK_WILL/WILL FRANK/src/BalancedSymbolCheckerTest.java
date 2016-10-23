//William Frank
//u1067292
package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class BalancedSymbolCheckerTest {
	
	BalancedSymbolChecker checker = new BalancedSymbolChecker();

	String output;
	
	@Test
	public void class1() {
		try {
			output = checker.checkFile("Class1.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead."));
	}
	
	@Test
	public void class2() {
		try {
			output = checker.checkFile("Class2.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead."));
	}
	
	@Test
	public void class3() {
		try {
			output = checker.checkFile("Class3.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("No errors found. All symbols match."));
	}

	@Test
	public void class4() {
		try {
			output = checker.checkFile("Class4.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: File ended before closing comment."));
	}
	
	@Test
	public void class5() {
		try {
			output = checker.checkFile("Class5.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead."));
	}
	
	@Test
	public void class6() {
		try {
			output = checker.checkFile("Class6.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("No errors found. All symbols match."));
	}
	
	@Test
	public void class7() {
		try {
			output = checker.checkFile("Class7.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead."));
	}
	
	@Test
	public void class8() {
		try {
			output = checker.checkFile("Class8.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead."));
	}
	
	@Test
	public void class9() {
		try {
			output = checker.checkFile("Class9.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead."));
	}
	
	@Test
	public void class10() {
		try {
			output = checker.checkFile("Class10.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead."));
	}
	
	@Test
	public void class11() {
		try {
			output = checker.checkFile("Class11.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("ERROR: Unmatched symbol at the end of file. Expected }."));
	}
	
	@Test
	public void class12() {
		try {
			output = checker.checkFile("Class12.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("No errors found. All symbols match."));
	}
	
	@Test
	public void class13() {
		try {
			output = checker.checkFile("Class13.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(output);
		
		assertTrue(output.equals("No errors found. All symbols match."));
	}
	
	@Test
	public void class14() {
		try {
			output = checker.checkFile("Class14.java");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertTrue(output.equals("No errors found. All symbols match."));
	}
}
