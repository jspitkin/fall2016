package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a double linked list and implements the List and
 * Iterable interfaces.
 * 
 * @author Cooper Pender (u0843147)
 *
 * Last Edited On: 10/03/16
 * 
 * @param <E> - Type of elements to be maintained by this set.
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private DoublyLinkedListNode head;
	private DoublyLinkedListNode tail;
	private int size; //used to keep track of true size of list.

	// Default constructor for a doubly linked list.
	public DoublyLinkedList() {
		size = 0;
		head = new DoublyLinkedListNode(); //create the hyead and tail nodes, set them to null.
		tail = new DoublyLinkedListNode();
		head.next = tail;
		tail.prev = head;
	}

	@Override
	public void addFirst(E element) {

		DoublyLinkedListNode insertNode = new DoublyLinkedListNode();
		insertNode.nodeData = element;

		if (size == 0) {
			head.nodeData = element; //check if the head and tail are populated.
		} else if (size == 1) {
			tail.nodeData = head.nodeData;
			tail.prev = insertNode;
			insertNode.next = tail;
			head = insertNode;
		} else {
			head.prev = insertNode;
			head.prev.next = head;
			head = insertNode;
		}
		size++;
	}

	@Override
	public void addLast(E element) {

		DoublyLinkedListNode insertNode = new DoublyLinkedListNode();
		insertNode.nodeData = element;

		if (size == 0) {
			head.nodeData = element;
		} else if (size == 1) {
			tail.nodeData = element;
		} else {
			tail.next = insertNode;
			tail.next.prev = tail;
			tail = insertNode;
		}
		size++;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		DoublyLinkedListNode insertNode = new DoublyLinkedListNode();
		insertNode.nodeData = element;

		if (index == 0) {
			addFirst(element);
		} else if (index == (size)) {
			addLast(element);
		} else {
			int curIndex = 0;
			DoublyLinkedListNode currentNode = head;

			while (curIndex < index) { //find the current node to insert behind.
				curIndex++;
				currentNode = currentNode.next;
			}

			currentNode.prev.next = insertNode;
			insertNode.prev = currentNode.prev;
			currentNode.prev = insertNode;
			insertNode.next = currentNode;
			size++;
		}
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return head.nodeData;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		return tail.nodeData;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {

		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		DoublyLinkedListNode startNode = head;
		DoublyLinkedListNode endNode = tail;

		int startIndex = 0;
		int endIndex = size - 1;

		while (startIndex < index && endIndex > index) { //close in on index from both sides (head & tail).
			startIndex++;
			endIndex--;
			startNode = startNode.next;
			endNode = endNode.prev;
		}
		if (startIndex == index) {
			return startNode.nodeData;
		}
		return endNode.nodeData;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {

		if (size == 0) {
			throw new NoSuchElementException();
		}
		E returnElement = head.nodeData;
		head = head.next;
		size--;
		return returnElement;

	}

	@Override
	public E removeLast() throws NoSuchElementException {
		E returnElement;

		if (size == 0) {
			throw new NoSuchElementException();
		} else if (size == 1) {
			returnElement = head.nodeData;
			clear();
		} else {
			returnElement = tail.nodeData;
			tail = tail.prev;
			size--;
		}
		return returnElement;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		DoublyLinkedListNode startNode = head;
		DoublyLinkedListNode endNode = tail;

		int startIndex = 0;
		int endIndex = size - 1;
		E returnValue;

		if (index == 0) {
			returnValue = removeFirst(); //if removing head or tail, just call previous methods.
			return returnValue;
		} else if (index == size - 1) {
			returnValue = removeLast();
			return returnValue;
		}

		else {

			while (startIndex < index && endIndex > index) { 
				startIndex++;
				endIndex--;
				startNode = startNode.next;
				endNode = endNode.prev;
			}

			if (startIndex == index) {
				returnValue = startNode.nodeData;
				startNode.prev.next = startNode.next;
				startNode.next.prev = startNode.prev;
			}

			else {
				returnValue = endNode.nodeData;
				endNode.prev.next = endNode.next;
				endNode.next.prev = endNode.prev;
			}

			size--;
			return returnValue;
		}
	}

	@Override
	public int indexOf(E element) {

		int curIndex = 0;
		DoublyLinkedListNode tempNode = head;

		while (tempNode != null) {

			if (tempNode.nodeData == element) {
				return curIndex;
			}
			curIndex++;
			tempNode = tempNode.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		int curIndex = size - 1;
		DoublyLinkedListNode tempNode = tail;

		while (tempNode != null) {

			if (tempNode.nodeData == element) {
				return curIndex;
			}
			curIndex++;
			tempNode = tempNode.prev;
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		head.nodeData = null; //change head & tail to null and repoint them to eachother.
		tail.nodeData = null;
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	@Override
	public Object[] toArray() {

		Object[] returnArray = new Object[size];
		DoublyLinkedListNode insertNode = head;

		for (int listIter = 0; listIter < size; listIter++) {
			returnArray[listIter] = insertNode.nodeData;
			insertNode = insertNode.next;
		}
		return returnArray;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}

	/**
	 * This private class creates an iterator that can traverse a doubly linked
	 * list.
	 * 
	 * @author Cooper Pender (u0843147)
	 * 
	 * Last Edited On: 10/03/2016
	 */
	private class DoublyLinkedListIterator implements Iterator<E> {

		private int counter = 0; //used to keep track of iterator position.

		@Override
		public boolean hasNext() {
			if (counter <= size - 1) {
				return true;
			}
			return false;
		}

		@Override
		public E next() {

			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			if (counter == 0) {
				counter++;
				return head.nodeData;
			} else {
				return get(counter++);
			}
		}

		@Override
		public void remove() throws IllegalStateException {

			if (counter == 0) {
				throw new IllegalStateException();
			}
			if (counter == 1) {
				head = head.next;
				counter--;
			} else if (counter == size) {
				tail = tail.prev;
				counter--;
			} else {
				DoublyLinkedList.this.remove(--counter);
			}
		}
	}

	/**
	 * This private class represents the individual nodes that make up a linked
	 * list.
	 * 
	 * @author Cooper Pender (u0843147)
	 * 
	 * Last Edited On: 10/03/2016
	 */
	private class DoublyLinkedListNode {

		private E nodeData; //data held by node.
		private DoublyLinkedListNode next; 
		private DoublyLinkedListNode prev;

	}
}
