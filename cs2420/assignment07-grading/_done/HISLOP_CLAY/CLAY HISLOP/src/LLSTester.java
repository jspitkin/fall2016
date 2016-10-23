package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/* @author Clayton Hislop
* uID: u0515744
*/
public class LLSTester {
	
	private LinkedListStack<String> listStack, emptyStack;

	@Before
	public void setUp() {
		emptyStack = new LinkedListStack<String>();
		listStack = new LinkedListStack<String>();
		listStack.push("t");
		listStack.push("s");
		listStack.push("z");
		listStack.push("r");
	}
	
	@Test
	public void clearListConfirmUsingSize() {
		listStack.clear();
		assertTrue(listStack.size() == 0);
	}
	
	@Test
	public void clearThenPushNewItems() {
		listStack.clear();
		listStack.push("2");
		listStack.push("4");
		assertEquals("4", listStack.peek());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void clearThenPop() {
		listStack.clear();
		listStack.pop();
	}
	
	@Test
	public void peekAfterPoppingItems() {
		listStack.pop();
		listStack.pop();
		assertTrue(listStack.peek() == "s");
	}
	
	@Test
	public void peekOriginalList() {
		assertTrue(listStack.peek() == "r");
	}
	
	@Test
	public void peekAfterPushingItems() {
		listStack.push("a");
		listStack.push("c");
		listStack.push("w");
		assertTrue(listStack.peek() == "w");		
	}
	
	@Test(expected = NoSuchElementException.class)
	public void peekOnEmptyList() {
		emptyStack.peek();
	}
	
	@Test
	public void isEmptyTest() {
		assertFalse(listStack.isEmpty());
	}
	
	@Test
	public void isEmptyAfterClear()	 {
		listStack.clear();
		assertTrue(listStack.isEmpty());
	}
	
	@Test
	public void isEmptyAfterClearThenPushingItems() {
		listStack.clear();
		listStack.push("99");
		listStack.push("22");
		listStack.pop();
		assertTrue(listStack.peek() == "99" && !listStack.isEmpty());
	}
	
	@Test
	public void popOneItem() {
		assertEquals("r", listStack.pop());
	}
	
	@Test
	public void popToMakeEmptyList() {
		listStack.pop();
		listStack.pop();
		listStack.pop();
		listStack.pop();
		assertTrue(listStack.isEmpty());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void popOnEmptyList() {
		emptyStack.pop();
	}		
	
	@Test
	public void sizeOfOriginalList() {
		assertEquals(4, listStack.size());
	}
	
	@Test
	public void sizeOfEmpty() {
		assertEquals(0, emptyStack.size());
	}
	
	@Test
	public void sizeAfterPushing() {
		listStack.push("q");
		listStack.push("m");
		listStack.push("1");
		listStack.push("0");
		assertEquals(8, listStack.size());
	}
	
	@Test
	public void sizeAfterPopping() {
		listStack.pop();
		listStack.pop();
		assertTrue(listStack.size() == 2);
	}
	
}
