//William Frank
//u1067292
package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class LinkedListStackTest {
	
	LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
	
	@Before
	public void before() {
		stack.clear();
		
		stack.push(1);
		stack.push(2);
		stack.push(3);

	}

	@Test
	public void clear() {
		stack.clear();
		
		assertTrue(stack.size() == 0);
		assertTrue(stack.isEmpty());
	}

	@Test
	public void isEmptyOnNotEmptyStack() {
		assertFalse(stack.isEmpty());
	}
	
	@Test
	public void peekWholeStack() {
		assertTrue(stack.peek() == 3);
		
		stack.pop();
		
		assertTrue(stack.peek() == 2);
		
		stack.pop();
		
		assertTrue(stack.peek() == 1);
	}
	
	@Test
	public void peekEmptyStack() {
		boolean exception = false;
		stack.clear();
		
		try {
			stack.peek();
		}
		catch (NoSuchElementException e) {
			exception = true;
		}
		
		assertTrue(exception);
	}
	
	@Test
	public void popWholeStack() {
		assertTrue(stack.pop() == 3);
		
		assertTrue(stack.pop() == 2);
		
		assertTrue(stack.pop() == 1);
	}
	
	@Test
	public void popWholeStackSize() {
		assertTrue(stack.size() == 3);
		
		stack.pop();
		
		assertTrue(stack.size() == 2);
		
		stack.pop();
		
		assertTrue(stack.size() == 1);
		
		stack.pop();
		
		assertTrue(stack.size() == 0);
		assertTrue(stack.isEmpty());
	}
	
	@Test
	public void popEmptyStack() {
		boolean exception = false;
		stack.clear();
		
		try {
			stack.pop();
		}
		catch (NoSuchElementException e) {
			exception = true;
		}
		
		assertTrue(exception);
	}
	
	@Test
	public void pushSizeTest() {
		stack.clear();
		
		assertTrue(stack.size() == 0);
		
		for (int counter = 0; counter < 20; counter++)
		{
			stack.push(counter);
		}
		
		assertTrue(stack.size() == 20);
		
	}
}
