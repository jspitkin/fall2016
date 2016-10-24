package assignment07;

import java.util.NoSuchElementException;

import assignment06.DoublyLinkedList;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author Nathan Milot u1063587
 * @since Oct 7, 2016
 * 
 * @param <E>
 *            -- the type of elements contained in the stack
 */
public class LinkedListStack<E> {

	private DoublyLinkedList<E> stack;

	public LinkedListStack() {
		stack = new DoublyLinkedList<E>();
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
		return stack.getFirst();
	}

	/**
	 * Returns and removes the item at the top of the stack. Throws
	 * NoSuchElementException if the stack is empty.
	 */
	public E pop() throws NoSuchElementException {
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
	 * Prints out a string represntation of the stack
	 */
	@Override
	public String toString(){
		return stack.toString();
	}
}