package assignment07;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * DoublyLinkedList
 * @author Longsheng Du
 * u1093993
 * CS 2420
 * 10/14/2016
 */

public class StackSymbolCheckerTest {
	LinkedListStack<Integer> testStack;
	BalancedSymbolChecker check;
	
	@Before
	public void NewList() {
		testStack = new LinkedListStack<Integer>();	
		check = new BalancedSymbolChecker();

	}
	
	//Stack Tests
	@Test
	public void EmptyStackBasicTest() {		
		Assert.assertEquals(testStack.size(),0);	
		Assert.assertTrue(testStack.isEmpty());	
	}
	
	@Test(expected=NoSuchElementException.class)
	public void EmptyStackPeekTest() {
		testStack.peek();
	}
	
	@Test(expected=NoSuchElementException.class)
	public void EmptyStackPopTest() {
		testStack.pop();
	}
	
	
	
	@Test
	public void StackPushTest() {
		testStack.push(1);
		Assert.assertEquals(testStack.peek(),(Integer)1);	
		testStack.push(2);
		Assert.assertEquals(testStack.peek(),(Integer)2);	
	}
	
	@Test
	public void StackPopTest() {
		testStack.push(1);
		testStack.push(2);
		Assert.assertEquals(testStack.pop(),(Integer)2);	
		Assert.assertEquals(testStack.pop(),(Integer)1);
		
		Assert.assertTrue(testStack.isEmpty());	
	}
	
	@Test
	public void StackClearTest() {
		testStack.push(1);
		testStack.push(2);		
		testStack.push(3);		
		Assert.assertFalse(testStack.isEmpty());	
		testStack.pop();
		testStack.pop();
		testStack.pop();
		Assert.assertTrue(testStack.isEmpty());	

	}
	
	
	
	//BalancedSymbolChecker Tests
	@Test(expected=FileNotFoundException.class)
	public void BalancedSymbolCheckFileNotFoundException() throws FileNotFoundException {

		check.checkFile("aFileWhichDonotExist");
	}

	
	@Test
	public void BalancedSymbolCheckClass1(){
		try {
			String strCheck = check.checkFile("A7_examples/Class1.java");
			String strComp = "ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void BalancedSymbolCheckClass2(){
		try {
			String strCheck = check.checkFile("A7_examples/Class2.java");
			String strComp = "ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass3(){
		try {
			String strCheck = check.checkFile("A7_examples/Class3.java");
			String strComp = "No errors found. All symbols match.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass4(){
		try {
			String strCheck = check.checkFile("A7_examples/Class4.java");
			String strComp = "ERROR: File ended before closing comment.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass5(){
		try {
			String strCheck = check.checkFile("A7_examples/Class5.java");
			String strComp = "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass6(){
		try {
			String strCheck = check.checkFile("A7_examples/Class6.java");
			String strComp = "No errors found. All symbols match.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass7(){
		try {
			String strCheck = check.checkFile("A7_examples/Class7.java");
			String strComp = "ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass8(){
		try {
			String strCheck = check.checkFile("A7_examples/Class8.java");
			String strComp = "ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass9(){
		try {
			String strCheck = check.checkFile("A7_examples/Class9.java");
			String strComp = "ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass10(){
		try {
			String strCheck = check.checkFile("A7_examples/Class10.java");
			String strComp = "ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass11(){
		try {
			String strCheck = check.checkFile("A7_examples/Class11.java");
			String strComp = "ERROR: Unmatched symbol at the end of file. Expected }.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass12(){
		try {
			String strCheck = check.checkFile("A7_examples/Class12.java");
			String strComp = "No errors found. All symbols match.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass13(){
		try {
			String strCheck = check.checkFile("A7_examples/Class13.java");
			String strComp = "No errors found. All symbols match.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void BalancedSymbolCheckClass14(){
		try {
			String strCheck = check.checkFile("A7_examples/Class14.java");
			String strComp = "No errors found. All symbols match.";
			Assert.assertEquals(strComp,strCheck);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	



}
