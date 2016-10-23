package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
/**
 * Testing class for the BalancedSymbolChecker class
 * @author Zachary Cutler, u1025642
 *
 */
public class BalancedSymbolCheckerTester {
	
	BalancedSymbolChecker checker;

	@Before
	public void setUp() throws Exception {
		checker = new BalancedSymbolChecker();
	}
	
	@Test
	public void classTest1(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class1.java"), "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest2(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class2.java"), "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest3(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class3.java"), "No errors found. All symbols match.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest4(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class4.java"), "ERROR: File ended before closing comment.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest5(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class5.java"), "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}

	@Test
	public void classTest6(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class6.java"), "No errors found. All symbols match.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest7(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class7.java"), "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest8(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class8.java"), "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest9(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class9.java"), "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest10(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class10.java"), "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest11(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class11.java"), "ERROR: Unmatched symbol at the end of file. Expected }.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest12(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class12.java"), "No errors found. All symbols match.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	@Test
	public void classTest13() throws FileNotFoundException {
			assertEquals(checker.checkFile("A7_examples/Class13.java"), "No errors found. All symbols match.");


	}
	@Test
	public void classTest14(){
			try {
				assertEquals(checker.checkFile("A7_examples/Class14.java"), "No errors found. All symbols match.");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

	}
	
}
