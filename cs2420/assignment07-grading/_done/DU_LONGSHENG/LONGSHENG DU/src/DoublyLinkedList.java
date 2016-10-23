package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DoublyLinkedList
 * 
 * @author Longsheng Du u1093993 CS 2420 10/1/2016
 */

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	// Private Fields and Methods

	private Node head;
	private Node tail;
	private int size;

	/**
	 * Node class for DoublyLinkedList
	 */
	private class Node {
		private E data;
		private Node next;
		private Node prev;

		public Node() {
			this.next = null;
			this.prev = null;
			this.data = null;
		}
	}

	/**
	 * Get Node in the List.
	 * 
	 * @param index
	 * @return the Node of certain index.
	 */
	private Node getNode(int index) {
		if (index < 0 || index > this.size - 1) {
			return null;
		} else if (this.size - 1 - index >= index - 0) {
			Node current = this.head;
			for (int i = 0; i < index; i++) {
				current = current.next;
			}
			return current;
		} else {
			Node current = this.tail;
			for (int i = this.size - 1; i > index; i--) {
				current = current.prev;
			}
			return current;
		}
	}

	// Public Methods

	/**
	 * Default zero-parameter constructor.
	 */
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * Add the element to the head of the List.
	 * 
	 * @param element
	 */
	@Override
	public void addFirst(E element) {
		Node temp = new Node();
		temp.data = element;

		if (this.head == null) {
			this.tail = this.head = temp;
		} else {
			this.head.prev = temp;
			temp.next = this.head;
			this.head = temp;
		}

		this.size++;
	}

	/**
	 * Add the element to the tail of the List.
	 * 
	 * @param o
	 */
	@Override
	public void addLast(E o) {
		Node temp = new Node();
		temp.data = o;

		if (this.tail == null) {
			this.tail = this.head = temp;
		} else {
			this.tail.next = temp;
			temp.prev = this.tail;
			this.tail = temp;
		}

		this.size++;
	}

	/**
	 * Add the element to certain index of the List.
	 * 
	 * @param index
	 * @param E
	 *            element
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			addFirst(element);
		} else if (index == this.size) {
			addLast(element);
		} else {
			Node temp = new Node();
			temp.data = element;

			Node current = getNode(index);
			temp.prev = current.prev;
			temp.next = current;

			temp.prev.next = temp;
			temp.next.prev = temp;

			this.size++;
		}

	}

	/**
	 * Get the data in head of the List.
	 * 
	 * @return E element
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (this.head == null) {
			throw new NoSuchElementException();
		} else {
			return this.head.data;
		}
	}

	/**
	 * Get the data in tail of the List.
	 * 
	 * @return E element
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if (this.tail == null) {
			throw new NoSuchElementException();
		} else {
			return this.tail.data;
		}
	}

	/**
	 * Get the data in certain index of the List.
	 * 
	 * @return E element
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException();
		} else {
			Node current = getNode(index);
			return current.data;
		}
	}

	/**
	 * Remove first element in List.
	 * 
	 * @return E element - The removed element
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (this.head == null) {
			throw new NoSuchElementException();
		} else if (this.size == 1) {
			E temp = this.head.data;
			clear();
			return temp;
		} else {
			E temp = this.head.data;
			this.head = this.head.next;
			this.head.prev = null;
			this.size--;

			return temp;
		}
	}

	/**
	 * Remove last element in List.
	 * 
	 * @return E element - The removed element
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if (this.tail == null) {
			throw new NoSuchElementException();
		} else if (this.size == 1) {
			E temp = this.tail.data;
			clear();
			return temp;
		} else {
			E temp = this.tail.data;
			this.tail = this.tail.prev;
			this.tail.next = null;
			this.size--;

			return temp;
		}
	}

	/**
	 * Remove first element in certain index of the List.
	 * 
	 * @param index
	 * @return E element - The removed element
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return removeFirst();
		} else if (index == this.size - 1) {
			return removeLast();
		} else {
			Node current = getNode(index);

			current.prev.next = current.next;
			current.next.prev = current.prev;
			this.size--;

			return current.data;
		}

	}

	/**
	 * Search a element from head.
	 * 
	 * @param element
	 * @return int index - The index of that element, -1 if not found
	 */
	@Override
	public int indexOf(E element) {
		Node current = this.head;
		for (int i = 0; i < this.size; i++) {
			if (element.equals(current.data)) {
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	/**
	 * Search a element from tail.
	 * 
	 * @param element
	 * @return int index - The index of that element, -1 if not found
	 */
	@Override
	public int lastIndexOf(E element) {
		Node current = this.tail;
		for (int i = this.size - 1; i >= 0; i--) {
			if (element.equals(current.data)) {
				return i;
			}
			current = current.prev;
		}
		return -1;
	}

	/**
	 * Get the size of the List.
	 * 
	 * @return int - The size of the List
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * Get if the List is empty.
	 * 
	 * @return boolean - True if the List is empty, otherwise False
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Reset all List.
	 */
	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * Convert List to array.
	 * 
	 * @return Object[] - a array contains all elements in the List
	 */
	@Override
	public Object[] toArray() {
		Object[] dataArray = new Object[this.size];

		Node current = this.head;
		for (int i = 0; i < this.size; i++) {
			dataArray[i] = current.data;
			current = current.next;
		}

		return dataArray;
	}

	/**
	 * Iterator for the list.
	 * 
	 * @return Iterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			private int cursor = 0;
			private int lastRet = -1;
			Node current = head;
			Node lastNode = null;

			public boolean hasNext() {
				if (cursor < size) {
					return true;
				}
				return false;
			}

			public E next() {
				if (cursor >= size) {
					throw new NoSuchElementException();
				}

				lastRet = cursor;
				lastNode = current;

				cursor++;
				current = current.next;

				return lastNode.data;
			}

			public void remove() {
				if (lastRet < 0) {
					throw new IllegalStateException();
				}

				if (lastRet == 0) {
					removeFirst();
				} else if (lastRet == size - 1) {
					removeLast();
				} else {
					lastNode.prev.next = lastNode.next;
					lastNode.next.prev = lastNode.prev;

					size--;
				}

				// DoublyLinkedList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				lastNode = null;
			}

		};

	}

}
