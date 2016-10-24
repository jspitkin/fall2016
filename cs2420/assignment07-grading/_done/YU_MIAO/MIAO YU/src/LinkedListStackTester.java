package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class LinkedListStackTester {
	
	@Test
	public void testPush() {
		LinkedListStack<Integer> test = new LinkedListStack();
		for(int i=0;i<15000;i++){
			test.push(i);
			assertTrue(test.peek().equals(i));
		}
	}
	@Test
	public void testPop(){
		LinkedListStack<Integer> test = new LinkedListStack();
		for(int i = 0 ; i < 5000; i++){
			test.push(i);
			test.push(i);
			test.pop();
			assertTrue(test.peek().equals(i));
		}
	}
	@Test
	public void testIsEmpty(){
		LinkedListStack<Integer> test = new LinkedListStack();
		for(int i = 0 ; i < 5000; i++){
			test.push(i);
			test.push(i);
			test.pop();
			test.pop();
		}
		assertTrue(test.isEmpty());
	}
	@Test
	public void testClear(){
		LinkedListStack<Integer> test = new LinkedListStack();
		for(int i = 0 ; i < 5000; i++){
			test.push(i);
			test.push(i);
		}
		test.clear();
		assertTrue(test.isEmpty());
	}
	@Test
	public void testPeekWithException(){
		LinkedListStack<Integer> test = new LinkedListStack();
		try{
			test.peek();
		}catch(NoSuchElementException e){
			boolean thrown = true;
		}
		for(int i = 0 ; i < 5000; i++){
			test.push(i);
			test.push(i);
			assertTrue(test.peek().equals(i));
		}
	}
}
