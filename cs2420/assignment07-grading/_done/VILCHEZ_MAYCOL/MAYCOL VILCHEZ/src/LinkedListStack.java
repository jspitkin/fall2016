package assignment07;

import java.util.NoSuchElementException;

import assignment06.DoublyLinkedList;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author ??
 * 
 * @param <E>
 *            -- the type of elements contained in the stack
 */

/**
 * @author maycol vilchez u0832923
 */

public class LinkedListStack<E> {

	private DoublyLinkedList<E> stack;
	// private LinkedList<E> stack;

	public LinkedListStack() {
		stack = new DoublyLinkedList<E>();
		// stack = new LinkedList<>();
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

		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return stack.getLast();
	}

	/**
	 * Returns and removes the item at the top of the stack. Throws
	 * NoSuchElementException if the stack is empty.
	 */
	public E pop() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return stack.removeLast();
	}

	/**
	 * Pushes the input item onto the top of the stack.
	 */
	public void push(E item) {

		stack.addLast(item);

	}

	/**
	 * Returns the number of items in the stack.
	 */
	public int size() {
		return stack.size();
	}
}