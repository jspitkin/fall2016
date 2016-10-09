package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class for creating and manipulating a doubly linked list
 * @author Samuel Teare | UID: u0663592
 *
 * @param <E> - the type of Object being stored in this doubly linked list
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private int size;
	private Node head, tail;
	
	public DoublyLinkedList () {
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * A class for creating a Node in a doubly linked list
	 * @author Samuel Teare | UID: u0663592
	 *
	 */
	private class Node {
		private E data;
		private Node next, prev;
		
		public Node(E data, Node next, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
		
		public Node getNext() {
			return next;
		}
		
		public Node getPrev() {
			return prev;
		}
		
		public void setNext(Node nextNode) {
			this.next = nextNode;
		}
		
		public void setPrev(Node prevNode) {
			this.prev = prevNode;
		}
		
		public E data() {
			return data;
		}
	}

	/**
	 * returns the iterator for a DoublyLinkedList
	 */
	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}
	
	/**
	 * An iterator for a DoublyLinkedList
	 * 
	 * @author Samuel Teare | UID: u0663592
	 *
	 */
	private class DoublyLinkedListIterator implements Iterator<E> {

		private Node itrFocus;
		private int count;
		
		public DoublyLinkedListIterator() {
			this.itrFocus = DoublyLinkedList.this.head;
			count = 0;
		}
		@Override
		public boolean hasNext() {
			if(this.itrFocus == null) {
				return false;
			}
			return this.itrFocus != DoublyLinkedList.this.tail;
		}

		@Override
		public E next() {
			if(this.hasNext()) {
				Node nextNode = itrFocus;
				itrFocus = itrFocus.getNext();
				count++;
				return nextNode.data();
			}
			throw new NoSuchElementException();
		}
		
		@Override
		public void remove() {
			if(itrFocus == null) {
				throw new NoSuchElementException();
			}
			if(count > 0) {
				Node nextTemp = itrFocus.getNext();
				Node prevTemp = itrFocus.getPrev();
				nextTemp.setPrev(prevTemp);
				prevTemp.setNext(nextTemp);
			}
			else {
				throw new IndexOutOfBoundsException();
			}
		}
	}

	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addFirst(E element) {
		add(0, element);
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(E element) {
		add(size, element);
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		
		if(index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		
		Node newNode = new Node(element, null, null);
		if(index == 0) {
			if(head == null) {
				tail = newNode;
			}
			else {
				head.setPrev(newNode);
				newNode.setNext(head);
			}
			head = newNode;
		}
		else if(index == size) {
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
			
		}
		else {
			Node currentNode = null;
			int midIndex = size/2;
			if(index <= midIndex) {
				currentNode = head;
				for(int tempIndex = 1; tempIndex <= midIndex; tempIndex++) {
					currentNode = currentNode.getNext();
					if(tempIndex == index) {
						Node temp = currentNode.getPrev();
						newNode.setNext(currentNode);
						newNode.setPrev(temp);
						temp.setNext(newNode);
						currentNode.setPrev(newNode);
						break;
					}		
				}
			}
			else {
				currentNode = tail;
				for(int tempIndex = (size - 1); tempIndex > midIndex; tempIndex--) {
					if(tempIndex == index) {
						Node temp = currentNode.getPrev();
						newNode.setNext(currentNode);
						newNode.setPrev(temp);
						temp.setNext(newNode);
						currentNode.setPrev(newNode);
						break;
					}	
					currentNode = currentNode.getPrev();
				}
			}
		}
		size++;
		
	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size() == 0) {
			throw new NoSuchElementException();
		}
		return head.data;
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size() == 0) {
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0) {
			return getFirst();
		}
		else if(index == (size() - 1)) {
			return getLast();
		}
		else {
			Node results;
			int midIndex = size()/2;
			if(index <= midIndex) {
				results = head;
				for(int tempIndex = 1; tempIndex <= midIndex; tempIndex++) {
					results = results.getNext();
					if(tempIndex == index) {
						break;
					}		
				}
			}
			else {
				results = tail;
				for(int tempIndex = (size - 2); tempIndex > midIndex; tempIndex--) {
					results = results.getPrev();
					if(tempIndex == index) {
						break;
					}		
				}
			}
			return results.data;
		}
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		
		if(size == 0) {
			throw new NoSuchElementException();
		}
		
		Node removed = head;
		if(size == 1) {
			head = null;
			tail = null;
		}
		else {
			head = head.getNext();
			head.setPrev(null);
		}
		size--;
		return removed.data;
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		
		if(size == 0) {
			throw new NoSuchElementException();
		}
		
		Node removed = tail;
		if(size == 1) {
			head = null;
			tail = null;
		}
		else {
			tail = tail.getPrev();
			tail.setNext(null);
		}
		size--;
		return removed.data;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		
		if(index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		
		Node removed = null;
		if(index == 0) {
			return removeFirst();
		}
		else if(index == (size - 1)) {
			return removeLast();
		}
		else {
			int midIndex = size/2;
			if(index <= midIndex) {
				removed = head;
				for(int tempIndex = 1; tempIndex <= midIndex; tempIndex++) {
					removed = removed.getNext();
					if(tempIndex == index) {
						Node nextTemp = removed.getNext();
						Node prevTemp = removed.getPrev();
						nextTemp.setPrev(prevTemp);
						prevTemp.setNext(nextTemp);
						break;
					}		
				}
			}
			else {
				removed = tail;
				for(int tempIndex = (size - 2); tempIndex > midIndex; tempIndex--) {
					removed = removed.getPrev();
					if(tempIndex == index) {
						Node nextTemp = removed.getNext();
						Node prevTemp = removed.getPrev();
						nextTemp.setPrev(prevTemp);
						prevTemp.setNext(nextTemp);
						break;
					}		
				}
			}
		}
		
		size--;
		return removed.data;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(Object element) {
		Node temp = head;
		for(int index = 0; index < size; index++) {
			if(element.equals((Object) temp.data)) {
				return index;
			}
			temp = temp.getNext();
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(Object element) {
		Node temp = tail;
		for(int index = (size - 1); index >= 0; index--) {
			if(element.equals((Object) temp.data)) {
				return index;
			}
			temp = temp.getPrev();
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
		
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		Object[] objArray = new Object[size];
		Node currentNode = head;
		for(int index = 0; index < objArray.length; index++) {
			objArray[index] = (Object) currentNode.data();
			currentNode = currentNode.getNext();
		}
		return objArray;
	}

}
