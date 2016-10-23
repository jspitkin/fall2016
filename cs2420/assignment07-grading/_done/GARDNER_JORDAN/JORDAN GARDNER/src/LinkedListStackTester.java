package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * 
 * @author Jordan Gardner u0566259
 *
 */
public class LinkedListStackTester {
	LinkedListStack<Character> test1= new LinkedListStack<Character>();
	LinkedListStack<Character> test2= new LinkedListStack<Character>();
	LinkedListStack<Character> test3= new LinkedListStack<Character>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		test1.push('a');
		test1.push('b');
		test1.push('c');
		test1.push('d');
		test1.push('e');
		test1.push('f');
		test1.push('g');
		test1.push('h');
		test1.push('i');
		test1.push('j');
		
		test2.push('z');
		
		test3.push(')');
		test3.push('{');
		test3.push('[');

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testClear() {
		test1.clear();
		assertEquals(0,test1.size());
		test2.clear();
		assertEquals(0,test2.size());
		test3.clear();
		assertEquals(0,test3.size());
	}
	@Test
	public void testisEmpty() {
		test1.clear();
		assertTrue(test1.isEmpty());
		test2.clear();
		assertTrue(test2.isEmpty());
		test3.clear();
		assertTrue(test3.isEmpty());
	}
	@Test
	public void testPeek() {
		assertEquals((Character)'j',(Character)test1.peek());
		assertEquals((Character)'z',(Character)test2.peek());
		assertEquals((Character)'[',(Character)test3.peek());
	}
	@Test
	public void testPop() {
		assertEquals((Character)'j',(Character)test1.pop());
		assertEquals((Character)'z',(Character)test2.pop());
		assertEquals((Character)'[',(Character)test3.pop());
		assertEquals((Character)'i',(Character)test1.peek());
		assertEquals((Character)'{',(Character)test3.peek());
	}
	@Test
	public void testPush() {
		test1.push('k');
		test2.push('y');
		test3.push(',');
		assertEquals((Character)'k',(Character)test1.peek());
		assertEquals((Character)'y',(Character)test2.peek());
		assertEquals((Character)',',(Character)test3.peek());
	}
	@Test
	public void testSize() {
		assertEquals(10,test1.size());
		assertEquals(1,test2.size());
		assertEquals(3,test3.size());
	}
	//comment out below to test BalancedSymbolChecker
//	public static void main(String[] args){
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class1.java"));
//	} catch (FileNotFoundException e13) {
//		// TODO Auto-generated catch block
//		e13.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class2.java"));
//	} catch (FileNotFoundException e12) {
//		// TODO Auto-generated catch block
//		e12.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class3.java"));
//	} catch (FileNotFoundException e11) {
//		// TODO Auto-generated catch block
//		e11.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class4.java"));
//	} catch (FileNotFoundException e10) {
//		// TODO Auto-generated catch block
//		e10.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class5.java"));
//	} catch (FileNotFoundException e9) {
//		// TODO Auto-generated catch block
//		e9.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class6.java"));
//	} catch (FileNotFoundException e8) {
//		// TODO Auto-generated catch block
//		e8.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class7.java"));
//	} catch (FileNotFoundException e7) {
//		// TODO Auto-generated catch block
//		e7.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class8.java"));
//	} catch (FileNotFoundException e6) {
//		// TODO Auto-generated catch block
//		e6.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class9.java"));
//	} catch (FileNotFoundException e5) {
//		// TODO Auto-generated catch block
//		e5.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class10.java"));
//	} catch (FileNotFoundException e4) {
//		// TODO Auto-generated catch block
//		e4.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class11.java"));
//	} catch (FileNotFoundException e3) {
//		// TODO Auto-generated catch block
//		e3.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class12.java"));
//	} catch (FileNotFoundException e2) {
//		// TODO Auto-generated catch block
//		e2.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class13.java"));
//	} catch (FileNotFoundException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
//	
//	try {
//		System.out.println(BalancedSymbolChecker.checkFile("C:/Users/Jordan/workspace/CS2420/Class14.java"));
//	} catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	}
}
