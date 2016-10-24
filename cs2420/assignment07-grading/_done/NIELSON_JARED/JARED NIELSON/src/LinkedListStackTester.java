package assignment07;

import org.junit.Assert;

import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListStackTester {

	private static final int TEST_SIZE = 10;
	private LinkedListStack<String> empty;
	private LinkedListStack<String> one;
	private LinkedListStack<String> two;
	private LinkedListStack<String> three;
	
	@Before
	public void setUp() throws Exception {
		empty = new LinkedListStack<String>();
		one = new LinkedListStack<String>();
		two = new LinkedListStack<String>();
		three = new LinkedListStack<String>();
		
		one.push("0");
		
		two.push("0");
		two.push("1");
		
		three.push("0");
		three.push("1");
		three.push("2");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	
	/*******************************************************************
	 * clear tests
	 *******************************************************************/
	@Test
	public void clear() {
		three.clear();
		
		Assert.assertEquals(0, three.size());
	}
	
	@Test (expected = NoSuchElementException.class)
	public void popAfterClear(){
		three.clear();
		
		three.pop();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void peekAfterClear(){
		three.clear();
		
		three.peek();
	}
	
	/*******************************************************************
	 * isEmpty tests
	 *******************************************************************/
	
	@Test
	public void emptyIsEmpty(){
		Assert.assertTrue(empty.isEmpty());
	}
	
	@Test
	public void nonEmptyIsEmpty(){
		Assert.assertFalse(one.isEmpty());
	}
	
	/*******************************************************************
	 * peek tests
	 *******************************************************************/
	
	@Test (expected = NoSuchElementException.class)
	public void emptyPeek(){
		empty.peek();
	}
	
	@Test
	public void onePeek(){
		Assert.assertEquals("0", one.peek());
	}
	
	@Test
	public void twoPeek(){
		Assert.assertEquals("1", two.peek());
	}
	
	@Test
	public void threePeek(){
		Assert.assertEquals("2", three.peek());
	}
	
	/*******************************************************************
	 * pop tests
	 *******************************************************************/
	@Test (expected = NoSuchElementException.class)
	public void emptyPop(){
		empty.pop();
	}
	
	@Test
	public void onePop(){
		Assert.assertEquals("0", one.pop());
		Assert.assertEquals(0, one.size());
	}
	
	@Test
	public void twoPop(){
		Assert.assertEquals("1", two.pop());
		Assert.assertEquals(1, two.size());
	}
	
	@Test
	public void threePop(){
		Assert.assertEquals("2", three.pop());
		Assert.assertEquals(2, three.size());
	}
	
	/*******************************************************************
	 * push tests
	 *******************************************************************/
	@Test
	public void emptyPush(){
		empty.push("0");
		Assert.assertEquals(1, empty.size());
		Assert.assertEquals("0", empty.peek());
	}
	
	@Test
	public void onePush(){
		one.push("1");
		Assert.assertEquals(2, one.size());
		Assert.assertEquals("1", one.peek());
	}
	
	@Test
	public void twoPush(){
		two.push("2");
		Assert.assertEquals(3, two.size());
		Assert.assertEquals("2", two.peek());
	}
	
	@Test
	public void threePush(){
		three.push("3");
		Assert.assertEquals(4, three.size());
		Assert.assertEquals("3", three.peek());
	}
	
	/*******************************************************************
	 * size tests
	 *******************************************************************/
	
	@Test
	public void emptySize(){
		Assert.assertEquals(0, empty.size());
	}
	
	@Test
	public void oneSize(){
		Assert.assertEquals(1, one.size());
	}
	
	@Test
	public void twoSize(){
		Assert.assertEquals(2, two.size());
	}
	
	@Test
	public void threeSize(){
		Assert.assertEquals(3, three.size());
	}
	
	/*******************************************************************
	 * combo tests
	 *******************************************************************/
	@Test
	public void popAfterPeek(){
		Assert.assertEquals(three.peek(), three.pop());
	}
	
	@Test
	public void peekAfterPop(){
		Assert.assertNotEquals(three.pop(), three.peek());
	}
	
	@Test
	public void popToEmpty(){
		three.pop();
		three.pop();
		three.pop();
		
		Assert.assertTrue(three.isEmpty());
		Assert.assertEquals(0, three.size());
	}
	
	@Test
	public void pushPeekAndSize(){
		for(int index = 0; index < TEST_SIZE; index++){
			empty.push("" + index);
			
			Assert.assertEquals("Incorrect Size at " + index, index + 1, empty.size());
			Assert.assertEquals("" + index, empty.peek());
		}
	}
	
	@Test
	public void pushPopAndSize(){
		for(int index = 0; index < TEST_SIZE; index++){
			empty.push("" + index);
		}
		
		for(int index = TEST_SIZE - 1; index >=0; index--){
			Assert.assertEquals("" + index, empty.pop());
			Assert.assertEquals(index, empty.size());
		}
	}
	
	@Test
	public void loopTest(){
		for(int index = 0; index < TEST_SIZE; index++){
			empty.push("" + index);
		}
		
		while(!empty.isEmpty()){
			empty.pop();
		}
		
		//We've sucessfully popped without exception
		Assert.assertTrue(true);
	}
}