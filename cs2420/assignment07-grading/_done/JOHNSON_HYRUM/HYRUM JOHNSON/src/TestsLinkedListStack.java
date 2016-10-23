package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * TestLinkedListStack.java
 * 
 * @author Hyrum Johnson
 */

public class TestsLinkedListStack {
	LinkedListStack<String> newStringStack = new LinkedListStack<>();
	LinkedListStack<Integer> newIntStack = new LinkedListStack<>();

	@Before
	public void setUp() {
		newStringStack.push("ItemOne");
		newStringStack.push("ItemTwo");
		newStringStack.push("ItemThree");
		newStringStack.push("ItemFour");
		newStringStack.push("ItemFive");
		newStringStack.push("ItemSix");
		newStringStack.push("ItemSeven");
		newStringStack.push("ItemEight");
		newStringStack.push("ItemNine");
		newStringStack.push("ItemTen");
	}

	@Test
	public void clearTest() {
		// Clear Method.
		newStringStack.clear();
		assertEquals(true, newStringStack.isEmpty());
		assertTrue(0 == newStringStack.size());
	}

	@Test
	public void emptyLinkedListStackTest() {
		newIntStack.clear();
		assertEquals(true, newIntStack.isEmpty());
	}

	@Test
	public void peekTest() {
		assertTrue("ItemTen" == newStringStack.peek());
	}

	@Test(expected = NoSuchElementException.class)
	public void peekEmptyTest() {
		assertEquals("ItemSeven", newIntStack.peek());
	}

	@Test
	public void popTest() {
		// Make sure newIntStack is still empty.
		assertEquals(true, newIntStack.isEmpty());

		// Add ten items.
		assertEquals("ItemTen", newStringStack.pop());
		assertEquals("ItemNine", newStringStack.pop());
		assertEquals("ItemEight", newStringStack.pop());
		assertEquals("ItemSeven", newStringStack.pop());
		assertEquals("ItemSix", newStringStack.pop());
		assertEquals("ItemFive", newStringStack.pop());
		assertEquals("ItemFour", newStringStack.pop());
		assertEquals("ItemThree", newStringStack.pop());
		assertEquals("ItemTwo", newStringStack.pop());
		assertEquals("ItemOne", newStringStack.pop());
	}

	@Test(expected = NoSuchElementException.class)
	public void popEmptyTest() {
		// Clear then pop to get NoSuchElementExeption
		newIntStack.clear();
		newIntStack.pop();
	}

	@Test
	public void pushTest() {
		newStringStack.push("ItemEleven");
		assertEquals("ItemEleven", newStringStack.pop());
		assertEquals(10, newStringStack.size());
	}

	@Test
	public void pushEmptyTest() {
		newIntStack.push(100);
		assertEquals(100, (int) newIntStack.pop());
	}

	@Test
	public void sizeTest() {
		assertEquals(10, newStringStack.size());
		assertEquals(0, newIntStack.size());

		// Push 7 items.
		newStringStack.push("Item");
		newStringStack.push("anotherItem");
		newStringStack.push("anotherItem");
		newStringStack.push("anotherItem");
		newStringStack.push("anotherItem");
		newStringStack.push("anotherItem");
		newStringStack.push("anotherItem");

		// Check size.
		assertEquals(17, newStringStack.size());

		// Pop 7 items off.
		for (int i = 0; i < 7; i++) {
			newStringStack.pop();
		}

		// Check size.
		assertEquals(10, newStringStack.size());

		// Pop all items off.
		for (int i = 0; i < 10; i++) {
			newStringStack.pop();
		}

		// Check size again.
		assertEquals(0, newStringStack.size());

	}
}
