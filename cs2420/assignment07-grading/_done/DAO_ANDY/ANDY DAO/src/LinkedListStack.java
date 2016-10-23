package assignment07;

import java.util.NoSuchElementException;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author Andy Dao, uID: u0692334
 * 
 * @param <E>
 *            -- the type of elements contained in the stack
 */
public class LinkedListStack<E> {

    private DoublyLinkedList<E> stack;

    public LinkedListStack () {
	stack = new DoublyLinkedList<>();
    }

    /**
     * Removes all of the elements from the stack.
     */
    public void clear() {
	stack.clear();
    }

    /**
     * Returns true if the stack contains no elements.
     */
    public boolean isEmpty() {
	return stack.isEmpty();
    }

    /**
     * Returns the item at the top of the stack without removing it from the
     * stack. Throws NoSuchElementException if the stack is empty.
     */
    public E peek() throws NoSuchElementException {
	checkStackSize();
	return stack.getFirst();
    }

    /**
     * Returns and removes the item at the top of the stack. Throws
     * NoSuchElementException if the stack is empty.
     */
    public E pop() throws NoSuchElementException {
	checkStackSize();
	return stack.removeFirst();
    }

    /**
     * Pushes the input item onto the top of the stack.
     */
    public void push(E item) {
	stack.addFirst(item);
    }

    /**
     * Returns the number of items in the stack.
     */
    public int size() {
	return stack.size();
    }

    /**
     * Helper method to check if the stack size is empty. If it is empty, throw
     * NoSuchElementException.
     * 
     * @throws NoSuchElementException
     *             - When the stack is empty
     */
    private void checkStackSize() throws NoSuchElementException {
	if (stack.isEmpty()) {
	    throw new NoSuchElementException();
	}
    }
}