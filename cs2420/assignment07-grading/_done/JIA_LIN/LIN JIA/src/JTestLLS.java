package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
/**
 * @author LinJia
 * uid: u1091732
 * test LinkedListStack methods
 */
public class JTestLLS {
	LinkedListStack<Integer> myStack = new LinkedListStack<Integer>();
	LinkedListStack<Integer> myStackEmpty = new LinkedListStack<Integer>();

	@Before
	public void setUp() 
	{

	myStack.push(0);
	myStack.push(1);
	myStack.push(2);
	myStack.push(3);
	myStack.push(4);
	myStack.push(5);
	}
	@Test
	public void testClear() {
		
		myStack.clear();
		assertEquals(0, myStack.size());
	}
	@Test
	public void testIsEmpty() {
		myStack.clear();
		assertEquals(true, myStack.isEmpty());

	}
	@Test 
	public void testPeek(){
		assertTrue(myStack.peek().equals(5));
	}
	@Test 
	public void testPop(){
		myStack.pop();
		assertTrue(myStack.peek().equals(4));
	}
	@Test 
	public void testPush(){
		myStack.push(9);
		assertTrue(myStack.peek().equals(9));
	}
	@Test
    public void testSize() {
		
		myStack.clear();
		assertEquals(0, myStack.size());
	}
	@Test
	public void testExceptions() throws Exception{
		try{
			myStackEmpty.peek();
			}
		catch (NoSuchElementException e){
			
		}
		try{
			myStackEmpty.pop();
			}
		catch (NoSuchElementException e){
			
		}
		
		
	}
		


}