package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * JUnit test for BalancedSymbolChecker class
 * 
 * @author Benjamin Allred u1090524
 *
 */
public class BalancedSymbolCheckerTest {

	BalancedSymbolChecker bsc = new BalancedSymbolChecker();

	@Test
	public void class1() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class1.java"), "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class1.java not found");
		}
	}
	
	@Test
	public void class2() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class2.java"), "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class2.java not found");
		}
	}
	
	@Test
	public void class3() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class3.java"), "No errors found. All symbols match.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class3.java not found");
		}
	}
	
	@Test
	public void class4() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class4.java"), "ERROR: File ended before closing comment.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class4.java not found");
		}
	}
	
	@Test
	public void class5() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class5.java"), "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class5.java not found");
		}
	}
	
	@Test
	public void class6() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class6.java"), "No errors found. All symbols match.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class6.java not found");
		}
	}
	
	@Test
	public void class7() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class7.java"), "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class7.java not found");
		}
	}
	
	@Test
	public void class8() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class8.java"),  "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class8.java not found");
		}
	}
	
	@Test
	public void class9() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class9.java"),  "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class9.java not found");
		}
	}
	
	@Test
	public void class10() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class10.java"), "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class10.java not found");
		}
	}
	
	@Test
	public void class11() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class11.java"), "ERROR: Unmatched symbol at the end of file. Expected }.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class11.java not found");
		}
	}
	
	@Test
	public void class12() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class12.java"), "No errors found. All symbols match.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class12.java not found");
		}
	}
	
	@Test
	public void class13() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class13.java"), "No errors found. All symbols match.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class13.java not found");
		}
	}
	
	@Test
	public void class14() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\Class14.java"), "No errors found. All symbols match.");
		} catch(FileNotFoundException e)
		{
			System.err.println("Class14.java not found");
		}
	}
	
	@Test
	public void testDoublyLinkedListFile() {
		try{
		assertEquals(bsc.checkFile("src\\assignment06\\DoublyLinkedList.java"), "No errors found. All symbols match.");
		} catch(FileNotFoundException e)
		{
			System.err.println("DoublyLinkedList.java not found");
		}
	}
	
	@Test
	public void testLinkedListStackFile() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\LinkedListStack.java"), "No errors found. All symbols match.");
		} catch(FileNotFoundException e)
		{
			System.err.println("LinkedListStack.java not found");
		}
	}
	
	@Test
	public void finalTest() {
		try{
		assertEquals(bsc.checkFile("src\\assignment07\\BalancedSymbolChecker.java"), "No errors found. All symbols match.");
		} catch(FileNotFoundException e)
		{
			System.err.println("BalancedSymbolChecker.java not found");
		}
	}

}
