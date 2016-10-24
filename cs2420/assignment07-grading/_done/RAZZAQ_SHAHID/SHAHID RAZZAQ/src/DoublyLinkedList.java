package assignment07;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This Class generates a doubly linked list
 * 
 * @author ShahidBilal Razzaq u0996062
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	// Private Instance Variables For this class
	private Node headNode;
	private Node tailNode;
	private int sizeOfList;

	/**
	 * Zero Parameter Constructor.
	 */
	public DoublyLinkedList() {
		headNode = null;
		tailNode = null;
		sizeOfList = 0;
	}

	@Override
	/**
	 * Inserts the specified element at the beginning of the list. O(1) for a
	 * doubly-linked list.
	 */
	public void addFirst(E element) {
		Node newNode = new Node(element, headNode, null);
		if (tailNode == null) {
			tailNode = newNode;
		} else if (headNode == null) {
			headNode = newNode;
		} else {
			newNode.nextNode = headNode;
			headNode.previousNode = newNode;

		}
		headNode = newNode;

		sizeOfList++;

	}

	@Override
	/**
	 * Inserts the specified element at the end of the list. O(1) for a
	 * doubly-linked list.
	 */
	public void addLast(E o) {
		Node newNode = new Node(o, null, tailNode);
		if (headNode == null) {
			headNode = newNode;
		} else if (tailNode == null) {
			tailNode = newNode;
		} else {
			newNode.previousNode = tailNode;
			tailNode.nextNode = newNode;
		}
		tailNode = newNode;

		sizeOfList++;

	}

	@Override
	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) O(N) for a doubly-linked list.
	 */
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		Node elementToInsert = new Node(element, null, null);
		if (this.size() == 0) {
			headNode = elementToInsert;
			tailNode = elementToInsert;
		} else if (index == 0) {
			elementToInsert.nextNode = headNode;
			headNode.previousNode = elementToInsert;
			headNode = elementToInsert;
		} else if (index == this.size()) {
			elementToInsert.previousNode = tailNode;
			tailNode.nextNode = elementToInsert;
			tailNode = elementToInsert;
		} else {
			Node previousNode = getNode(index - 1);

			elementToInsert.nextNode = previousNode.nextNode;
			elementToInsert.nextNode.previousNode = elementToInsert;

			elementToInsert.previousNode = previousNode;
			previousNode.nextNode = elementToInsert;
		}

		sizeOfList++;

	}

	@Override
	/**
	 * Returns the first element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	public E getFirst() throws NoSuchElementException {
		if (this.size() <= 0) {
			throw new NoSuchElementException();
		}
		if (headNode == null) {
			return null;
		} else {
			return headNode.element;
		}

	}

	@Override
	/**
	 * Returns the last element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	public E getLast() throws NoSuchElementException {
		if (this.size() <= 0) {
			throw new NoSuchElementException();
		}
		if (tailNode == null) {
			return null;
		} else {
			return tailNode.element;
		}
	}

	@Override
	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}

		// do a search to find index:
		int middleIndex = (0 + this.size()) / 2;
		Node returnNode = null;

		if (index <= middleIndex) {
			returnNode = headNode;
			for (int i = 0; i < index; i++) {
				returnNode = returnNode.nextNode;
			}

		} else {
			returnNode = tailNode;
			for (int i = this.size() - 1; i > index; i--) {
				returnNode = returnNode.previousNode;
			}
		}

		return returnNode.element;
	}

	@Override
	/**
	 * Removes and returns the first element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	public E removeFirst() throws NoSuchElementException {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		Node removeNode;
		Node nextNode;
		removeNode = headNode;
		nextNode = headNode.nextNode;
		headNode = nextNode;

		sizeOfList--;
		return removeNode.element;
	}

	@Override
	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	public E removeLast() throws NoSuchElementException {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		Node removeNode;
		Node previousNode;
		removeNode = tailNode;
		previousNode = tailNode.previousNode;
		tailNode = previousNode;
		sizeOfList--;

		return removeNode.element;
	}

	@Override
	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size()) O(N) for a doubly-linked list.
	 */
	public E remove(int index) throws IndexOutOfBoundsException {

		if (index <= 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}
		E returnElementValue;
		if (index == 0) {
			return removeFirst();
		} else if (index == this.size() - 1) {
			return removeLast();
		} else {
			Node nodeBeforeRemovalNode = getNode(index - 1);
			Node nodeToRemove = nodeBeforeRemovalNode.nextNode;
			Node nodeAfterRemovalNode = nodeToRemove.nextNode;

			nodeBeforeRemovalNode.nextNode = nodeAfterRemovalNode;

			if (nodeAfterRemovalNode != null) {
				nodeAfterRemovalNode.previousNode = nodeBeforeRemovalNode;

			} else {
				tailNode = nodeAfterRemovalNode;
			}

			returnElementValue = nodeToRemove.element;
		}

		sizeOfList--;
		return returnElementValue;

	}

	/**
	 * Helper Method, returns the index of a Node
	 * 
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public Node getNode(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException();
		}

		// do a search to find index:
		int middleIndex = (0 + this.size()) / 2;
		Node returnNode = null;

		if (index <= middleIndex) {
			returnNode = headNode;
			for (int i = 0; i < index; i++) {
				returnNode = returnNode.nextNode;
			}

		} else {
			returnNode = tailNode;
			for (int i = this.size() - 1; i > index; i--) {
				returnNode = returnNode.previousNode;
			}
		}

		return returnNode;
	}

	@Override
	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	public int indexOf(E element) {
		Node currentNode = headNode;
		int count = 0;

		while (currentNode != null) {
			if (currentNode.element.equals(element)) {
				return count;
			} else {
				currentNode = currentNode.nextNode;
				count++;
			}
		}
		return -1;
	}

	@Override
	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	public int lastIndexOf(E element) {
		Node currentNode = headNode;
		int index = 0;
		for (int i = 0; i < this.size(); i++) {
			if (currentNode.element.equals(element)) {
				index = i;
			}
			currentNode = currentNode.nextNode;
		}
		if (index == 0) {
			return -1;
		} else {
			return index;
		}
	}

	@Override
	/**
	 * Returns the number of elements in this list. O(1) for a doubly-linked
	 * list.
	 */
	public int size() {

		return sizeOfList;
	}

	@Override
	/**
	 * Returns true if this collection contains no elements. O(1) for a
	 * doubly-linked list.
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	/**
	 * Removes all of the elements from this list. O(1) for a doubly-linked
	 * list.
	 */
	public void clear() {
		headNode = null;
		tailNode = null;
		sizeOfList = 0;
	}

	@Override
	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). O(N) for a doubly-linked list.
	 */
	public Object[] toArray() {
		Object[] doublyLinkedListArray = new Object[this.size()];
		for (int i = 0; i < doublyLinkedListArray.length; i++) {
			doublyLinkedListArray[i] = this.get(i);
		}

		return doublyLinkedListArray;
	}

	@Override
	/**
	 * Iterator for this data structure
	 */
	public Iterator<E> iterator() {

		Iterator<E> doublyListIterator = new Iterator<E>() {

			Node iteratorHeadNode = headNode;

			@Override
			public boolean hasNext() {
				return iteratorHeadNode != tailNode.nextNode;
			}

			@Override
			public E next() {
				E result = iteratorHeadNode.element;
				iteratorHeadNode = iteratorHeadNode.nextNode;
				return result;

			}

			@Override
			public void remove() {
				if (iteratorHeadNode.previousNode.previousNode == null) {
					Node removeNode;
					Node nextNode;
					removeNode = headNode;
					nextNode = headNode.nextNode;
					headNode = nextNode;
					sizeOfList--;
				} else if (iteratorHeadNode.nextNode == null) {
					Node removeNode;
					Node previousNode;
					removeNode = tailNode;
					previousNode = tailNode.previousNode;
					tailNode = previousNode;
					sizeOfList--;
				} else {

					Node currentNode = iteratorHeadNode.previousNode;
					Node afterNode = iteratorHeadNode;
					Node previousNode = currentNode.previousNode;

					previousNode.nextNode = iteratorHeadNode;
					afterNode.previousNode = previousNode;

					sizeOfList--;
				}
			}
		};

		return doublyListIterator;
	}

	/**
	 * Private Class, generates a Node Object
	 * 
	 * @author ShahidBilal Razzaq
	 *
	 */
	private class Node {
		E element;
		Node nextNode;
		Node previousNode;

		/**
		 * Constructor
		 * 
		 * @param _element
		 */
		public Node(E _element) {
			this.element = _element;
		}

		/**
		 * Constructor for the Node Class
		 * 
		 * @param _element
		 * @param _nextNode
		 * @param _previousNode
		 */
		public Node(E _element, Node _nextNode, Node _previousNode) {
			this.element = _element;
			this.nextNode = null;
			this.previousNode = null;
		}

	}

}
