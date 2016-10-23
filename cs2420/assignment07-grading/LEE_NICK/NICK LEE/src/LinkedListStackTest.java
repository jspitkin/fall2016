package assignment07;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * CS 2420
 * @author Nickolas Lee
 * Created: Oct 7, 2016
 */
public class LinkedListStackTest {

    LinkedListStack<Integer> empty, numbers;
    LinkedListStack<String> animals;
    
    @Before
    public void setUp() throws Exception {
	empty = new LinkedListStack<Integer>();
	numbers = new LinkedListStack<Integer>();
	animals = new LinkedListStack<String>();
	animals.push("dog");
	animals.push("cat");
	animals.push("bat"); // last in first out
    }

    /**
     * Test method for {@link assignment07.LinkedListStack#clear()}.
     */
    @Test (expected=NoSuchElementException.class)
    public void testClear(){
	assertEquals("bat", animals.peek());

	animals.clear();
	assertEquals(0, animals.size());
	assertEquals("not bat", animals.peek()); // throws the exception
    }

    /**
     * Test method for {@link assignment07.LinkedListStack#isEmpty()}.
     */
    @Test
    public void testIsEmpty(){
	assertTrue(empty.isEmpty());
	assertTrue(numbers.isEmpty());
	assertFalse(animals.isEmpty());
    }

    /**
     * Test method for {@link assignment07.LinkedListStack#peek()}.
     */
    @Test
    public void testPeek() {
	assertEquals(3, animals.size());
	assertEquals("bat", animals.peek());
	assertEquals("bat", animals.peek()); // tests whether it is iterating to the next items or not
	assertEquals(3, animals.size());
    }

    /**
     * Test method for {@link assignment07.LinkedListStack#pop()}.
     */
    @Test
    public void testPop() {
	assertEquals("bat", animals.pop());
	assertEquals("cat", animals.pop());
	assertEquals("dog", animals.pop()); // first in last out
    }
    
    /**
     * Test method for {@link assignment07.LinkedListStack#pop()}.
     */
    @Test (expected=NoSuchElementException.class)
    public void testPopError() {
	assertEquals("bat", animals.pop());
	assertEquals("cat", animals.pop());
	assertEquals("dog", animals.pop()); // first in last out
	assertEquals("not dog", animals.pop()); // throws the error
    }
    /**
     * Test method for {@link assignment07.LinkedListStack#push(java.lang.Object)}.
     */
    @Test
    public void testPush() {
	numbers.push(6);
	assertEquals((Integer) 6, numbers.peek());
	assertEquals("bat", animals.peek());
	animals.push("mouse");
	assertEquals("mouse", animals.peek());
    }

    /**
     * Test method for {@link assignment07.LinkedListStack#size()}.
     */
    @Test
    public void testSize() {
	assertEquals(3, animals.size());
	assertEquals(0, numbers.size());
	numbers.push(8);
	assertEquals(1, numbers.size());
    }

}

