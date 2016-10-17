package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ching-Yuan Chang u0914005
 */

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private int size;
	private Node head;
	private Node tail;

	/**
	 * initialize the private variables
	 */
	public DoublyLinkedList() {
		head = new Node();
		tail = new Node();
		head.prev = head;
		head.next = head;
		tail = head;
		size = 0;
	}

	/**
	 * a class that constructs a node
	 */
	private class Node {
		private E element;
		private Node next;
		private Node prev;

	}

	/**
	 * Inserts the specified element at the beginning of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addFirst(E element) {
		Node toAdd = new Node();
		toAdd.element = element;
		if (isEmpty()) {
			head = toAdd;
			tail = toAdd;
		} else {
			head.prev = toAdd;
			toAdd.next = head;
			head = toAdd;
		}
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addLast(E o) {
		Node toAdd = new Node();
		toAdd.element = o;
		toAdd.prev = tail;
		tail.next = toAdd;
		tail = toAdd;
		size++;

	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			addFirst(element);
		} else {
			Node cur = head;
			for (int idx = 0; idx < index; idx++) {
				cur = cur.next;
			}
			Node toAdd = new Node();
			toAdd.element = element;
			toAdd.prev = cur.prev;
			cur.prev.next = toAdd;
			toAdd.next = cur;
			cur.prev = toAdd;
			size++;
		}
	}

	/**
	 * Returns the first element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return head.element;
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return tail.element;
	}

	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException();
		}

		Node cur = head;
		// check if the index is closer to head or tail to determine which end
		// it will start iteration
		if (index >= size() / 2) {
			for (int idx = size() - 1; idx > index; idx--) {
				cur = tail;
				cur = cur.prev;
			}
		} else {
			for (int idx = 0; idx < index; idx++) {
				cur = cur.next;
			}

		}
		return cur.element;
	}

	/**
	 * Removes and returns the first element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E remove = null;
		if (size == 1) {
			remove = head.element;
			head = null;
			tail = head;
		} else {
			remove = head.element;
			head = head.next;
			head.prev = null;
		}
		size--;

		return remove;
	}

	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E remove = null;
		if (size == 1) {
			remove = head.element;
			head = null;
			tail = null;
		} else {
			remove = tail.element;
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return remove;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E remove = null;
		if (index == 0) {
			remove = removeFirst();
		} else if (index == size() - 1) {
			remove = removeLast();

		} else {
			Node cur = head;
			// check if the index is closer to head or tail to determine which
			// end it will start iteration
			if (index >= size() / 2) {
				for (int idx = size() - 1; idx > index; idx--) {
					cur = tail;
					cur = cur.prev;
				}
			} else {
				for (int idx = 0; idx < index; idx++) {
					cur = cur.next;
				}

			}
			remove = cur.element;
			cur.prev.next = cur.next;
			cur.next.prev = cur.prev;
			size--;
		}
		return remove;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int indexOf(E element) {
		for (int idx = 0; idx < size(); idx++) {
			Node cur = head;
			if (cur.element == iterator().next()) {
				return idx;
			}
			cur = cur.next;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element) {
		int index = -1;
		Node cur = head;
		for (int idx = 0; idx < size(); idx++) {
			if (cur.element == element) {
				index = idx;
			}
			cur = cur.next;
		}
		return index;
	}

	/**
	 * Returns the number of elements in this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Removes all of the elements from this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public void clear() {
		head.element = null;
		head.prev = head;
		head.next = head;
		tail = head;
		size = 0;

	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		Object[] list = new Object[size()];
		Node cur = head;
		for (int idx = 0; idx < size(); idx++) {
			list[idx] = cur.element;
			cur = cur.next;

		}

		return list;
	}

	/**
	 * returns a new iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new iter();
	}

	/**
	 * the Iterator class that iterates through the list and removes items if
	 * wanted
	 */
	private class iter implements Iterator<E> {
		private Node cur = head;
		private int index = 0;
		private boolean removed = false;

		/**
		 * check if there is a next item
		 */
		@Override
		public boolean hasNext() {
			if (cur != null) {
				return true;
			}
			return false;
		}

		/**
		 * iterate to the next element, if no more element throw new
		 * NoSuchElementException
		 */
		@Override
		public E next() {
			if (hasNext()) {
				E next = cur.element;
				cur = cur.next;
				index++;
				removed = false;
				return next;
			} else {
				throw new NoSuchElementException();
			}
		}

		/**
		 * removes the current item, if remove is being called twice in a roll,
		 * throw IllegalStateException
		 */
		@Override
		public void remove() {
			if (removed == false) {
				removed = true;
				if (cur.next != null && cur.prev != null) {
					cur.prev.next = cur.next;
					cur.next.prev = cur.prev;
					cur = cur.next;
				} else if (cur.next == null) {
					cur.prev.next = null;
					cur = null;
				} else if (cur.prev == null) {
					cur.next.prev = null;
					cur = cur.next;
				}
				index--;
			} else {
				throw new IllegalStateException();
			}

		}

	}

}
