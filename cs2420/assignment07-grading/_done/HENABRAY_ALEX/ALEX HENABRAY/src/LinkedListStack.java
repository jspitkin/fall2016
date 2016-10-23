package assignment07;

import assignment06.DoublyLinkedList;
import java.util.NoSuchElementException;

/**
 * Represents a generic stack (first in, last out).
 * 
 * @author Alex Henabray (uID: u0795787), last updated 10/19/16
 * 
 * 
 * @param <E>
 *            -- the type of elements contained in the stack
 */
public class LinkedListStack<E> {

	private DoublyLinkedList<E> stack;

	// TA Question: How do I implent a doublylinked list indirectly to 
	// ensure the Big-O behavior of each stack method is O(1)?
	
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
		if(stack.isEmpty() == true) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Returns the item at the top of the stack without removing it from the
	 * stack. Throws NoSuchElementException if the stack is empty.
	 */
	public E peek() throws NoSuchElementException {
		if(stack.isEmpty() == true) {
			throw new NoSuchElementException("The stack is empty.");
		}
		
		return stack.getFirst();
	}

	/**
	 * Returns and removes the item at the top of the stack. Throws
	 * NoSuchElementException if the stack is empty.
	 */
	public E pop() throws NoSuchElementException {
		if(stack.isEmpty() == true) {
			throw new NoSuchElementException("The stack is empty.");
		}
		
		E element = stack.getFirst();
		stack.removeFirst();
		return element;
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
}