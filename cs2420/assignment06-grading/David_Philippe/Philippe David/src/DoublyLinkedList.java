package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a DoubleLinkedList. These functions provide functionality
 * of a double linked List.
 * 
 * @author Philippe David
 *
 * @param <E>
 *            -- the type of elements contained in the list
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	/**
	 * Double linked Node definition. Private helper class.
	 * 
	 * @author Philippe David
	 */
	private class DoublyLinkedNode {

		/* Declarations for element, pHead, and pPrevious. */
		private E pElement;
		private DoublyLinkedNode pNext;
		private DoublyLinkedNode pPrevious;

		/**
		 * Constructor for making an empty node, i.e. for making the head or
		 * tail of a linked list
		 */
		protected DoublyLinkedNode() {
			pElement = null;
			pNext = null;
			pPrevious = null;
		}

		/**
		 * Constructor for making a new node for the double linked list
		 * 
		 * @param element
		 * @param previousNode
		 * @param nextNode
		 */
		protected DoublyLinkedNode(E element, DoublyLinkedNode previousNode, DoublyLinkedNode nextNode) {
			this.pElement = element;
			this.pPrevious = previousNode;
			this.pNext = nextNode;
		}
	}

	/* Private member variable for storing important information */
	private int size = 0; // Number of elements on list; equals 0 initially.
	private DoublyLinkedNode pNode; // Sentinel for end
	private DoublyLinkedNode pHead; // Sentinel for head node

	/**
	 * Constructor for making a new node for a new DoublyLinkedList object.
	 */
	public DoublyLinkedList() {
		this.pNode = new DoublyLinkedNode();
		this.pHead = new DoublyLinkedNode();

		// The ends have to be connected.
		this.pHead.pNext = this.pNode;
		this.pNode.pPrevious = this.pHead;
	}

	/**
	 * Inserts the specified element at the beginning of the list. O(1) for a
	 * doubly-linked list.
	 * 
	 * @param element
	 *            -- the data to be stored in the node.
	 */
	@Override
	public void addFirst(E element) {
		// Create a new node and add it to the list.
		DoublyLinkedNode newItem = new DoublyLinkedNode(element, this.pHead, this.pHead.pNext);
		this.pHead.pNext.pPrevious = newItem;
		this.pHead.pNext = newItem;

		this.size += 1;
	}

	/**
	 * Inserts the specified element at the end of the list. O(1) for a
	 * doubly-linked list.
	 * 
	 * @param o
	 *            -- the data to be stored in the node.
	 */
	@Override
	public void addLast(E o) {
		DoublyLinkedNode newItem = new DoublyLinkedNode(o, this.pNode.pPrevious, this.pNode);
		this.pNode.pPrevious.pNext = newItem;
		this.pNode.pPrevious = newItem;

		this.size += 1;

	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) O(N) for a doubly-linked list.
	 * 
	 * @param index
	 *            -- the index of the node in the list
	 * @param element
	 *            -- the data to be stored in the node.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}

		DoublyLinkedNode linkAtIndex = this.pHead;
		// Traverse through the linked list starting at the beginning
		for (int i = 0; i <= index; i++) {
			linkAtIndex = linkAtIndex.pNext;
		}

		DoublyLinkedNode newNode = new DoublyLinkedNode(element, linkAtIndex.pPrevious, linkAtIndex);
		linkAtIndex.pPrevious.pNext = newNode;
		linkAtIndex.pPrevious = newNode;

		size += 1;

	}

	/**
	 * Returns the first element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 * 
	 * @throws NoSuchElementException
	 *             when the list is empty.
	 * @return the first element in the list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}

		return pHead.pNext.pElement;
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 * 
	 * @throws NoSuchElementException
	 *             when the list is empty.
	 * @return the last element in the list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}

		return pNode.pPrevious.pElement;
	}

	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 * 
	 * @param index
	 *            -- the specified position in the list.
	 * @return the element at the specified position in the list.
	 * @throws IndexOutOfBoundsException
	 *             when the index is out of range.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		DoublyLinkedNode linkAtIndex = this.pHead;
		// Traverse through the linked list starting at the beginning
		for (int i = 0; i <= index; i++) {
			linkAtIndex = linkAtIndex.pNext;
		}

		return linkAtIndex.pElement;
	}

	/**
	 * Removes and returns the first element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 * 
	 * @return the first element from the list.
	 * @throws NoSuchElementException
	 *             if the list is empty.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}

		DoublyLinkedNode doublyLinkedNode = this.pHead.pNext;
		doublyLinkedNode.pPrevious.pNext = this.pHead;
		this.pHead.pNext = doublyLinkedNode.pNext;

		size--;

		return doublyLinkedNode.pElement;
	}

	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 * 
	 * @return the last element from the list.
	 * @throws NoSuchElementException
	 *             if the list is empty.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if (this.size == 0) {
			throw new NoSuchElementException();
		}

		DoublyLinkedNode doublyLinkedNode = this.pNode.pPrevious;
		doublyLinkedNode.pPrevious.pNext = this.pHead;
		this.pNode.pPrevious = doublyLinkedNode.pPrevious;

		size -= 1;

		return doublyLinkedNode.pElement;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size()) O(N) for a doubly-linked list.
	 * 
	 * @param index
	 *            -- the specified index in the list.
	 * @return the element at the specified position in the list.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		DoublyLinkedNode linkAtIndex = this.pHead;
		// Traverse through the linked list starting at the beginning
		for (int i = 0; i <= index; i++) {
			linkAtIndex = linkAtIndex.pNext;
		}

		linkAtIndex.pPrevious.pNext = linkAtIndex.pNext;
		linkAtIndex.pNext.pPrevious = linkAtIndex.pPrevious;

		this.size--;

		return linkAtIndex.pElement;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 * 
	 * @param element
	 *            -- the item to search for.
	 * @return the index of the first occurrence of the specified element in the
	 *         list, -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(E element) {
		DoublyLinkedNode linkAtIndex = this.pHead.pNext;
		// Traverse through the linked list starting at the begining
		for (int i = 0; i < this.size; i++) {
			if (linkAtIndex.pElement.equals(element))
				return i;
			linkAtIndex = linkAtIndex.pNext;
		}

		return -1;

	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 * 
	 * @param element
	 *            -- the item to search for.
	 * @return the index of the last occurrence of the specified element in the
	 *         list, -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(E element) {
		DoublyLinkedNode linkAtIndex = this.pNode.pPrevious;

		// Traverse through the linked list, starting at the most recent node to
		// the oldest
		for (int i = size - 1; i >= 0; i--) {
			if (linkAtIndex.pElement.equals(element)) {
				return i;
			}

			linkAtIndex = linkAtIndex.pPrevious;
		}

		// Return -1 if the element is not found in the LinkedList
		return -1;
	}

	/**
	 * Returns the number of elements in this list. O(1) for a doubly-linked
	 * list.
	 * 
	 * @return an integer corresponding to the number of elements in the list.
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Returns true if this collection contains no elements. O(1) for a
	 * doubly-linked list.
	 * 
	 * @return a boolean indicating whether the list is empty.
	 */
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Removes all of the elements from this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public void clear() {
		pHead.pPrevious = pNode;
		pNode.pNext = pHead;
		this.size = 0;

	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). O(N) for a doubly-linked list.
	 * 
	 * @return an Object[] containing all of the elements in this list.
	 */
	@Override
	public Object[] toArray() {
		Object[] objectArray = new Object[this.size];
		DoublyLinkedNode linkAtIndex = pHead.pNext;
		for (int i = 0; i < this.size; i++) {
			objectArray[i] = linkAtIndex.pElement;
			linkAtIndex = linkAtIndex.pNext;
		}

		return objectArray;
	}

	/**
	 * Returns an iterator over the elements in this set.
	 * 
	 * @return An iterator over the elements in this set, where the elements are
	 *         returned in the order as the list.
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> implementedIterator = new Iterator<E>() {
			DoublyLinkedNode tempLink = DoublyLinkedList.this.pHead;
			boolean removed = false;
			boolean removeBeforeNext = true;

			@Override
			public boolean hasNext() {
				return tempLink.pNext != DoublyLinkedList.this.pNode && tempLink.pNext != null && tempLink != null;
			}

			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				} else {
					tempLink = tempLink.pNext;
					removed = false;
					removeBeforeNext = false;
					return tempLink.pElement;
				}

			}

			@Override
			public void remove() {
				// if(tempLink.pNext == pNode.pPrevious )
				// throw new IllegalStateException();
				// if( tempLink.pNext == null )
				// throw new IllegalStateException();
				if (removed)
					throw new IllegalStateException();
				if (removeBeforeNext)
					throw new IllegalStateException();
				removed = true;

				tempLink.pPrevious.pNext = tempLink.pNext;
				tempLink.pNext.pPrevious = tempLink.pPrevious;

				DoublyLinkedList.this.size--;
			}
		};

		return implementedIterator;
		
	}

}
