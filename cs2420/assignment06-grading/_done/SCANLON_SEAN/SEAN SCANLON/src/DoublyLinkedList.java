package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements Iterable<E>{
	private int n;        
    private Node Head;    
    private Node Tail;
	
	public DoublyLinkedList() {
		
    }
	private class Node {
        E elementIndex;
        Node afterIndex;
        Node beforeIndex;
 
        public Node(E element, Node afterI, Node beforeI) {
            this.elementIndex = element;
            this.afterIndex = afterI;
            this.beforeIndex = beforeI;
        }
    }
	
	
	@Override
	public Iterator<E> iterator() {
		Iterator itr = iterator();
		return itr;
	}

	
	
	
	
	
	
	
	void addFirst(E element) {
		Node last = afterIndex.beforeIndex;
        Node x = new Node();
        x.elementIndex = element;
        x.next = afterIndex;
        
       
        
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	void addLast(E o) {
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	void add(int index, E element) throws IndexOutOfBoundsException {
	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	E getFirst() throws NoSuchElementException {
		return null;
	}
	
	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	E getLast() throws NoSuchElementException {
		return null;
	}
	
	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	E get(int index) throws IndexOutOfBoundsException {
		return null;
	}
	
	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	E removeFirst() throws NoSuchElementException {
		return null;
	}
	
	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	E removeLast() throws NoSuchElementException {
		return null;
	}
	
	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	E remove(int index) throws IndexOutOfBoundsException {
		return null;
	}
	
	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	int indexOf(E element) {
		return 0;
	}
	
	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	int lastIndexOf(E element){
		return n;
		
	}
	
	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	int size(){
		return n;
		
	}
	
	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	boolean isEmpty(){
		return false;
		
	}
	
	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	void clear(){
		
	}
	
	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	Object[] toArray(){
		
	}
}
