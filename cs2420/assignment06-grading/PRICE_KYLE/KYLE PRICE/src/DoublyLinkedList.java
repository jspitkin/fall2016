package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DoublyLinkedList - implements list but it keeps track of the final item in
 * the list as well as the first.
 * 
 * @author Kyle Price 09/30/2016
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> { // I'm not putting javadoc comments for each method 
	private Node<E> head;                                          // because they are already provided in the "list" class. 
	private Node<E> tail;
	private int size;

	/**
	 * Node - is a custom node object for DoublyLinkedList.
	 * 
	 * @author Kyle Price 
	 * 09/30/2016
	 * @param <E>
	 */
	private static class Node<E> {
		private E data;
		private Node<E> next = null;
		private Node<E> prev = null;

		/**
		 * Node - is the constructor for a Node object.
		 * 
		 * @param data
		 */
		public Node(E data) {
			this.data = data;
		}
		
		/**
		 * setNext - sets the "next" member variable for a node. 
		 * @param _next
		 */
		public <E> void setNext(Node _next) {
			this.next = _next;
		}
		
		/**
		 * setPrev - sets the "prev" member variable for a node. 
		 * @param _prev
		 */
		public <E> void setPrev(Node _prev) {
			this.prev = _prev;
		}
	}

	/**
	 * DoublyLinkedList - is the constructor for a DoublyLinkedList object.
	 */
	public DoublyLinkedList() {
		this.head = null;
		this.tail = null;
		size = 0;
	}

	@Override
	public int size() { 
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Object[] toArray() {
		Object[] arr = new Object[size];
		if (size == 0) {
			return arr; 
		}
		Node<E> temp = head;
		int count = 0;
		while (count < size) {
			arr[count] = temp.data;
			count++;
			temp = temp.next;
		}
		return arr;
	}

	@Override
	public void clear() {
		size = 0;
		this.head = null;
		this.tail = null;
	}

	@Override
	public E get(int index) {
		if (index < 0 || index > (size - 1)) {
			throw new IndexOutOfBoundsException();
		}
		int count = 0;
		Node<E> temp = head;
		while (count <= index) {
			if (index == count) {
				return temp.data;
			}
			count++;
			temp = temp.next;
		}
		return null; 
	}

	@Override
	public void add(int index, Object element) { 
		if (index == 0) { // if it's currently empty
			this.addFirst((E) element);
			return;
		}
		if (index < 0 || index > size) { // if index is out of bounds
			throw new IndexOutOfBoundsException();
		}
		Node<E> itemToAdd = new Node<E>((E) element);
		if (index == (size - 1)) { // if it belongs where the end item currently is
			itemToAdd.setPrev(tail.prev);
			itemToAdd.setNext(tail);
			tail.prev.setNext(itemToAdd);
			tail.setPrev(itemToAdd);
			size++;
			return;
		}
		if (index == size) {  // if it needs to be added to the very end
			itemToAdd.setPrev(tail);
			tail.setNext(itemToAdd);
			tail = itemToAdd;
			size++;
			return;
		}
		Node<E> temp = head;
		int count = 0;
		while (count <= index) { // if it needs to be added somewhere in the middle
			if (count == index) {
				itemToAdd.setNext(temp);
				itemToAdd.setPrev(temp.prev);
				temp.prev.setNext(itemToAdd);
				temp.setPrev(itemToAdd); 
				size++;
				return;
			}
			count++;
			temp = temp.next;
		}
	}

	@Override
	public E remove(int index) {
		if (index > (size - 1) || index < 0) { // if index is out of bounds
			throw new IndexOutOfBoundsException();
		}
		if (index == (size - 1)) { // if the item to be removed is at the end
			return this.removeLast();
		}
		if (index == 0) { // if the first item needs to be removed
			return this.removeFirst();
		}
		Node<E> temp = head;
		int count = 0;
		while (count <= index) { // if index is somewhere in the middle
			if (count == index) {
				temp.prev.setNext(temp.next);
				temp.next.setPrev(temp.prev);
				size--;
				return temp.data;
			}
			count++;
			temp = temp.next;
		}
		return null;
	}

	@Override
	public int indexOf(Object o) {
		Node<E> temp = head;
		int count = 0;
		while (count < size) {
			if (o.equals(temp.data)) {
				return count;
			}
			count++;
			temp = temp.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object item) {
		Node<E> temp = tail;
		int count = size - 1;
		while (count >= 0) {
			if (item.equals(temp.data)) {
				return count;
			}
			count--;
			temp = temp.prev;
		}
		return -1;
	}

	@Override
	public void addFirst(E element) {
		Node<E> item = new Node<E>(element);
		if (size == 0) {
			head = item;
			tail = item;
			size++;
			return;
		}
		item.setNext(head);
		head.setPrev(item);
		head = item;
		size++;
	}

	@Override
	public void addLast(E element) {
		Node<E> item = new Node<E>(element);
		if (size == 0) {
			head = item;
			tail = item;
			size++;
			return;
		}
		item.setPrev(tail);
		tail.setNext(item);
		tail = item;
		size++;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		} else {
			return head.data;
		}
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		} else {
			return tail.data;
		}
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<E> returnValue = head;
		if (size == 1) {
			head = null;
			tail = null;
			size--;
			return returnValue.data;
		}
		head.next.setPrev(null); 
		head = head.next;
		size--;
		return returnValue.data;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		Node<E> returnValue = tail;
		if (size == 1) {
			head = null;
			tail = null;
			size--;
			return returnValue.data;
		}
		tail.prev.setNext(null);
		tail = tail.prev;
		size--;
		return returnValue.data;
	}

	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private Node<E> currentNode = null;
			private boolean canRemove = false; // flag to indicate if next has been called 

			@Override
			public boolean hasNext() {
				if (currentNode == null && head.data != null) { // if next hasn't been called
					return true;
				}
				if (currentNode.next != null) {
					return true;
				} else {
					return false;
				}

			}

			@Override
			public E next() {
				if (currentNode == null) { // if it's the first time next is being called
					if (head == null) { // if there are no elements in the list
						throw new NoSuchElementException();
					} else {
						canRemove = true;
						currentNode = new Node<E>(head.data);
						currentNode.next = head.next;
						return currentNode.data; 
					}
				} else {
					if (currentNode.next != null) {
						currentNode = currentNode.next;
						canRemove = true;
						return currentNode.data;
					}
					throw new NoSuchElementException();
				}
			}

			@Override
			public void remove() {
				if (canRemove) { // if next has been called
					if (currentNode.prev == null && currentNode.next == null) { // if currentNode is the only item in the list
						currentNode = null;
						size--;
						return;
					}
					if (currentNode.prev == null) { // if it's the first item in the list
						currentNode.next.setPrev(null);
						head = currentNode.next;
						canRemove = false;
						currentNode = currentNode.next;
						size--;
					} else if (currentNode.next == null) { // if it's the final item in the list
						currentNode.prev.setNext(null);
						tail = currentNode.prev;
						canRemove = false;
						currentNode = currentNode.prev;  
						size--;
					} else { // if it's somewhere in the middle
						currentNode.next.setPrev(currentNode.prev);
						currentNode.prev.setNext(currentNode.next);
						canRemove = false;
						currentNode = currentNode.next;
						size--;
					}

				} else {
					throw new IllegalStateException();
				}
			}

		};
	}
}
