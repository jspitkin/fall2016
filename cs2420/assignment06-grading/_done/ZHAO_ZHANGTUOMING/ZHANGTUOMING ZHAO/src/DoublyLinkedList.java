package assignment06;

import java.util.NoSuchElementException;

/**
 * @author Zhangtuoming Zhao
 * @UID u0847610
 */
public class DoublyLinkedList<E> implements List<E> {

	private int size;
	private Node head;
	private Node tail;

	/**
	 * Constructor. Creates a initial linked list.
	 */
	public DoublyLinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	/**
	 * Inserts the specified element at the beginning of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addFirst(E element) {
		// TODO Auto-generated method stub
		Node temp = new Node(element, null, null);
		if (this.size == 0) {
			this.head = temp;
			this.tail = temp;
			size++;
		} else {
			temp.setNext(head);
			this.head.setPrev(temp);
			this.head = head.getPrev();
			size++;
		}

	}

	/**
	 * Inserts the specified element at the end of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addLast(E o) {
		// TODO Auto-generated method stub
		Node temp = new Node(o, null, null);
		if (this.size == 0) {
			this.head = temp;
			this.tail = temp;
			size++;
		} else {
			temp.setPrev(tail);
			this.tail.setNext(temp);
			this.tail = tail.getNext();
			size++;
		}
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if (this.size == 0 && index == 0) {
			Node temp = new Node(element, null, null);
			this.head = temp;
			this.tail = temp;
			size++;
		} else if (this.size == 0 && index != 0)
			throw new IndexOutOfBoundsException(
					"The input index is out of the size of the linked list.Please try other one.");
		else {
			if (index == 0) {
				Node n = new Node(element, head, null);
				head.setPrev(n);
				head = head.getPrev();
				size++;
			} else if (index == size) {
				Node n = new Node(element, null, tail);
				tail.setNext(n);
				tail = tail.getNext();
				size++;
			} else if (index > 0 && index <= size - 1) {
				Node temp = head;
				for (int i = 0; i < index; i++) {
					temp = temp.getNext();
				}
				Node n = new Node(element, temp, temp.getPrev());
				temp.getPrev().setNext(n);
				temp.setPrev(n);
				size++;
			} else
				throw new IndexOutOfBoundsException(
						"The input index is out of the size of the linked list.Please try other one.");
		}
	}

	/**
	 * Returns the first element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (this.size == 0)
			throw new NoSuchElementException("There is not a first item in the linked List");
		else {
			return (E) head.getItem();
		}
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (this.size == 0)
			throw new NoSuchElementException("The linked list is emplty.");
		else {
			return (E) tail.getItem();
		}
	}

	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		Node temp = this.head;
		IndexOutOfBoundsException ex = new IndexOutOfBoundsException(
				"Input index is larger than list size. Please try a smaller interger");
		if (index > this.size - 1 || index < 0) {
			throw ex;
		} else if (index == 0) {
			return (E) head.getItem();
		} else if (index == size - 1) {
			return (E) tail.getItem();
		} else {
			for (int i = 0; i < index; i++) {
				temp = temp.getNext();
			}
			return (E) temp.getItem();
		}
	}

	/**
	 * Removes and returns the first element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (this.size == 0)
			throw new NoSuchElementException("The list is empty.");
		else if (this.size == 1) {
			Object ele = head.getItem();
			this.head = null;
			this.tail = null;
			this.size = 0;
			return (E) ele;
		} else {
			Object ele = head.getItem();
			head.getNext().setPrev(null);
			head = head.getNext();
			size--;
			return (E) ele;
		}
	}

	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (this.size == 0)
			throw new NoSuchElementException("The list is empty.");
		else if (this.size == 1) {
			Object ele = head.getItem();
			this.head = null;
			this.tail = null;
			this.size = 0;
			return (E) ele;
		} else {
			Object ele = tail.getItem();
			tail.getPrev().setNext(null);
			tail = tail.getPrev();
			size--;
			return (E) ele;
		}
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		Node temp = head;
		if (index > size - 1 || index < 0 || this.size == 0)
			throw new IndexOutOfBoundsException("The index is out of the size of the list.");
		else if (this.size == 1) {
			Object ele = head.getItem();
			this.head = null;
			this.tail = null;
			this.size = 0;
			return (E) ele;
		} else {
			for (int i = 0; i < index; i++) {
				temp = temp.getNext();
			}
			if (temp == head) {
				head.getNext().setPrev(null);
				this.head = head.getNext();
				size--;
			} else if (temp == tail) {
				tail.getPrev().setNext(null);
				this.tail = tail.getPrev();
				size--;
			} else {
				temp.getPrev().setNext(temp.getNext());
				temp.getNext().setPrev(temp.getPrev());
				size--;
			}
			return (E) temp.getItem();
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		Node temp = this.head;
		if (this.size == 0)
			return -1;
		else {
			for (int i = 0; i < this.size; i++) {
				if ((E) temp.getItem() == element)
					return i;
				else {
					temp = temp.getNext();
				}
			}
			return -1;
		}
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element) {
		// TODO Auto-generated method stub
		Node temp = this.tail;
		if (this.size == 0)
			return -1;
		else {
			for (int i = this.size - 1; i >= 0; i--) {
				if ((E) temp.getItem() == element)
					return i;
				else {
					temp = temp.getPrev();
				}
			}
			return -1;
		}
	}

	/**
	 * Returns the number of elements in this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

	/**
	 * Returns true if this collection contains no elements. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (this.size == 0) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Removes all of the elements from this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		Object[] objectArray = new Object[this.size];
		Node temp = head;
		for (int i = 0; i < this.size; i++) {
			objectArray[i] = temp.getItem();
			temp = temp.getNext();
		}
		return objectArray;
	}

	/**
	 * making a class for nodes of the doubly-Linked List making an iterator for
	 * nodes of the doubly-Linked List
	 * 
	 * @param item
	 *            item in the linked list
	 * @param nextItem
	 *            if there is a next node
	 * @param prevItem
	 *            if there is a previous node
	 */
	private class Node<E> {

		private E item;
		private Node<E> prevNode;
		private Node<E> nextNode;

		/**
		 * making an iterator for nodes of the doubly-Linked List
		 * 
		 * After I google the question and ask TA, T Object and E Object are the
		 * same. and T is for type and E is for Element.
		 * 
		 * @param item
		 *            item in the linked list
		 * @param nextItem
		 *            if there is a next node
		 * @param prevItem
		 *            if there is a previous node
		 */
		public Node(E item, Node<E> nextItem, Node<E> prevItem) {
			this.item = item;
			this.nextNode = nextItem;
			this.prevNode = prevItem;
		}

		public Node(E item) {
			this(item, null, null);
		}

		public E getItem() {
			return this.item;
		}

		public void setItem(E item) {
			this.item = item;
		}

		public Node<E> getNext() {
			return this.nextNode;
		}

		public Node<E> getPrev() {
			return this.prevNode;
		}

		public void setNext(Node<E> next) {
			this.nextNode = next;
		}

		public void setPrev(Node<E> prev) {
			this.prevNode = prev;
		}

	}

}
