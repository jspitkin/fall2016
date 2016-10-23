package assignment07;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a doubly linked list used to back the LinkedListStack in
 * CS2420's Assignment 6. Aside from this comment and a bug fix in lastIndexOf,
 * this class is unmodified from Assignment 6
 * 
 * @author Ella Moskun u0897252
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	private Node head;
	private Node tail;
	private int size;

	/**
	 * Constructs a new, empty DoublyLinkedList.
	 */
	public DoublyLinkedList() {
		clear();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addFirst(E element) {
		if (size == 0) {
			Node node = new Node(element, null, null);
			head = node;
			tail = node;
		} else {
			Node node = new Node(element, null, head);
			head.previous = node;
			head = node;
		}
		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLast(E element) {
		if (size == 0) {
			Node node = new Node(element, null, null);
			head = node;
			tail = node;
		} else {
			Node node = new Node(element, tail, null);
			tail.next = node;
			tail = node;
		}
		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index == 0 && size == 0) {
			Node node = new Node(element, null, null);
			head = node;
			tail = node;
		} else if (index == 0) {
			Node node = new Node(element, null, head);
			head.previous = node;
			head = node;
		} else if (index == size) {
			Node node = new Node(element, tail, null);
			tail.next = node;
			tail = node;
		} else {
			Node next = getNode(index);
			Node previous = next.previous;
			Node node = new Node(element, previous, next);
			previous.next = node;
			next.previous = node;
		}
		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		} else {
			return head.element;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		} else {
			return tail.element;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		return getNode(index).element;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		} else if (size == 1) {
			Node node = head;
			clear();
			return node.element;
		} else {
			Node node = head;
			head.next.previous = null;
			head = head.next;
			size--;
			return node.element;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		} else if (size == 1) {
			Node node = tail;
			clear();
			return node.element;
		} else {
			Node node = tail;
			tail.previous.next = null;
			tail = tail.previous;
			size--;
			return node.element;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		Node node = getNode(index);
		removeNode(node);
		return node.element;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(E element) {
		int index = 0;
		Node node = head;
		while (index < size) {
			if (node.element == null && element == null) {
				return index;
			} else if (node.element != null && node.element.equals(element)) {
				return index;
			}
			node = node.next;
			index++;
		}
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(E element) {
		int index = size - 1;
		Node node = tail;
		while (index >= 0) {
			if (node.element == null && element == null) {
				return index;
			} else if (node.element != null && node.element.equals(element)) {
				return index;
			}
			node = node.previous;
			index--;
		}
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		Node currentElement = head;
		for (int index = 0; index < size; index++) {
			array[index] = currentElement.element;
			currentElement = currentElement.next;
		}
		return array;
	}

	/**
	 * Returns an new Iterator over the DoublyLinkedList.
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> iterator = new Iterator<E>() {

			int index = -1;
			Node node = null;
			boolean removeLegal = false;

			@Override
			public boolean hasNext() {
				return index + 1 < size;
			}

			@Override
			public E next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				} else if (index == -1) {
					node = head;
				} else {
					node = node.next;
				}
				index++;
				removeLegal = true;
				return node.element;
			}

			@Override
			public void remove() {
				if (removeLegal) {
					removeNode(node);
					node = node.previous;
					index--;
					removeLegal = false;
				} else {
					throw new IllegalStateException();
				}
			}

		};
		return iterator;
	}

	/**
	 * Private helper method to get a Node (NOT its element) at a specified
	 * index
	 * 
	 * @param targetIndex
	 *            the specified index
	 * @return the Node at the specified index
	 * @throws IndexOutOfBoundsException
	 *             when (targetIndex < 0 || targetIndex >= size)
	 */
	private Node getNode(int targetIndex) throws IndexOutOfBoundsException {
		if (targetIndex < 0 || targetIndex >= size) {
			throw new IndexOutOfBoundsException();
		} else {
			int currentIndex = 0;
			Node currentNode = head;
			while (currentIndex < targetIndex) {
				currentNode = currentNode.next;
				currentIndex++;
			}
			return currentNode;
		}
	}

	/**
	 * Private helper method to remove a given Node
	 * 
	 * @param node
	 *            the Node to remove
	 */
	private void removeNode(Node node) {
		Node previous = node.previous;
		Node next = node.next;

		if (previous == null) {
			head = next;
		} else {
			previous.next = next;
		}

		if (next == null) {
			tail = previous;
		} else {
			next.previous = previous;
		}

		size--;
	}

	/**
	 * A private class for our internal representation of a Node.
	 */
	private class Node {
		private Node previous;
		private Node next;
		private E element;

		/**
		 * Constructs a new Node with the given parameters
		 * 
		 * @param element
		 *            The element the Node is to point to
		 * @param previous
		 *            The Node to the left
		 * @param next
		 *            The node to the right
		 */
		public Node(E element, Node previous, Node next) {
			this.element = element;
			this.previous = previous;
			this.next = next;
		}
	}
}
