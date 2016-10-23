/**
 * Assignment 6 - DoublyLinkedList
 * @author Dallin Van Mondfrans
 * uID: u0717113
 * Date: October 5, 2016
 */

package assignment06;

public class Node<E> {

	protected E data = null;
	protected Node next;
	protected Node previous;
	
	/**
	 * Will initialize this Node with the passed in data of 
	 * type E, setting next and previous to null.
	 * @param data
	 */
	protected Node(E data) {
		this.data = data;
		next = null;
		previous = null;
	}

}
