package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Junit Testing class for the LinkedList Stack 
 * @author ShahidBilal Razzaq
 * u0996062
 *
 */
public class LinkedListStackTest {
	LinkedListStack<String> sampleStack = new LinkedListStack<String>();
	String s1= "double";
	String s2= "cod";
	String s3= "formula";
	String s4= "jog	";
	String s5= "melody";
	String s6= "canada";

	@Before
	public void setUp() throws Exception {
		
		sampleStack.push(s1);
		sampleStack.push(s2);
		sampleStack.push(s3);
		sampleStack.push(s4);
		sampleStack.push(s5);
		sampleStack.push(s6);
	}

	@Test
	public void testClearingStack(){
		sampleStack.clear();
		assertTrue(sampleStack.isEmpty());
		assertEquals(0, sampleStack.size());
	}
	@Test
	public void testIsEmptyStack(){
		assertFalse(sampleStack.isEmpty());
		assertEquals(6, sampleStack.size());
	}
	
	@Test
	public void testPeekMethod(){
		assertEquals(s6, sampleStack.peek());
	}
	@Test
	public void testPeekMethodWithLastElementSame(){
		assertEquals("canada", sampleStack.peek());
	}
	@Test
	(expected = NoSuchElementException.class)
	public void testPeekMethodWithoutAnyElement(){
		sampleStack.clear();
		assertEquals(new NoSuchElementException(), sampleStack.peek());
		assertEquals(0, sampleStack.size());
	}
	@Test
	public void testPopMethodWithElements(){
		sampleStack.pop();
		assertEquals(s5, sampleStack.peek());
		assertEquals(5, sampleStack.size());
	}
	@Test
	(expected = NoSuchElementException.class)
	public void testPopMethodWitoutElements(){
		sampleStack.clear();
		assertEquals(new NoSuchElementException(), sampleStack.pop());
		assertEquals(0, sampleStack.size());
	}
	@Test
	public void testPushMethodOnStackWithElements(){
		sampleStack.push("facebook");
		assertEquals("facebook", sampleStack.peek());
		assertEquals(7, sampleStack.size());
	}
	@Test
	public void testPushMethodOnEmptyStack(){
		LinkedListStack<String> emptyStack = new LinkedListStack<String>();
		assertEquals(0, emptyStack.size());
		emptyStack.push(s2);
		
	}
	@Test
	public void testPushMethodOnEmptyStackAndCheckPeek(){
		LinkedListStack<String> emptyStack = new LinkedListStack<String>();
		assertEquals(0, emptyStack.size());
		emptyStack.push(s2);
		assertEquals(s2, emptyStack.peek());
		
	}
	@Test
	(expected = NoSuchElementException.class)
	public void testPushMethodOnEmptyStackAndCheckPop(){
		LinkedListStack<String> emptyStack = new LinkedListStack<String>();
		assertEquals(0, emptyStack.size());
		emptyStack.push(s2);
		assertEquals(s2, emptyStack.peek());
		emptyStack.pop();
		assertEquals(new NoSuchElementException(),emptyStack.peek());
		
	}
	@Test
	public void testPushingAndPoppint(){
		LinkedListStack<String> emptyStack = new LinkedListStack<String>();
		emptyStack.push("(");
		emptyStack.push("{");
		emptyStack.push("[");
		emptyStack.pop();
		emptyStack.push("6");
		assertEquals("6", emptyStack.peek());
		
		
	}
	@Test
	public void testPushingAndPoppinWithPoppedToAnEmptyStackAndRepush(){
		LinkedListStack<String> emptyStack = new LinkedListStack<String>();
		emptyStack.push("(");
		emptyStack.push("{");
		emptyStack.push("[");
		emptyStack.pop();
		emptyStack.push("6");
		emptyStack.pop();
		emptyStack.pop();
		emptyStack.pop();
		assertEquals(0, emptyStack.size());
		emptyStack.push(s1);
		assertEquals(1, emptyStack.size());
		assertEquals(s1, emptyStack.peek());
		
		
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
