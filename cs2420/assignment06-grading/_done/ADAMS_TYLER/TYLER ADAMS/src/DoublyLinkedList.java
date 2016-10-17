package assignment06;

/**
 * A DoublyLinkedList functions as a list using a series linked nodes.
 * 
 * @author Tyler Adams
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private Node head, tail;
	private int size;
	
	public DoublyLinkedList() {
		size = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * Returns an iterator for this list.
	 */
	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addFirst(E element) {
		Node newHead = new Node(element, null, head);
		
		if(size++ != 0) {
			head.previous = newHead;
		}
		else
			tail = newHead;
		
		head = newHead;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLast(E element) {
		Node newTail = new Node(element, tail, null);
		
		if(size++ != 0) {
			tail.next = newTail;
		}
		else
			head = newTail;
		
		tail = newTail;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Index must be between 0 and the list size (inclusive).\nSize: " + size + ", got: " + index);
		}
		
		if(index == 0) //It's easier and cleaner to just reuse my code on the boundary cases.
			addFirst(element);
		else if(index == size)
			addLast(element);
		else {
			Node prevNode = getNode(index);
			
			appendBefore(element, prevNode);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size == 0) {
			throw new NoSuchElementException("This list is empty.");
		}
		
		return head.value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size == 0) {
			throw new NoSuchElementException("This list is empty.");
		}
		
		return tail.value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index must be between 0 and the list size (inclusive).\nSize: " + size + ", got: " + index);
		
		return getNode(index).value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size == 0)
			throw new NoSuchElementException("This list is empty");
		
		E value = head.value;
		if(head.next == null)
			head = null;
		else
			head = head.next;
		
		size--;
		return value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(size == 0)
			throw new NoSuchElementException("This list is empty");
		
		E value = tail.value;
		if(tail.previous == null)
			tail = null;
		else
			tail = tail.previous;
		
		size--;
		return value;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Index must be between 0 and the list size (inclusive).\nSize: " + size + ", got: " + index);
		
		if(index == 0)
			return removeFirst();
		if(index == size - 1)
			return removeLast();
		
		Node node = getNode(index);
		return removeInPlace(node);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(E element) {
		if(size == 0)
			return -1;
		
		Node current = head;
		
		for(int idx = 0; idx < size; idx++) {
			if(current.value.equals(element))
				return idx;
			
			current = current.next;
		}
			
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(E element) {
		if(size == 0)
			return -1;
		
		Node current = tail;
		
		for(int idx = size - 1; idx >= 0; idx--) {
			if(current.value.equals(element))
				return idx;
			
			current = current.previous;
		}
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
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
		Object[] result = new Object[size];
		
		if(size == 0)
			return result;
		
		Node node = head;
		for(int idx = 0; idx < size; idx++) {
			result[idx] = node.value;
			node = node.next;
		}
		return result;
	}
	
	/**
	 * Inserts a node with the given element before the given node. NOT TO BE USED ON THE HEAD (will throw null pointer exception).
	 * 
	 * @param element - to be the value of the new node.
	 * @param node - node to follow the new node.
	 */
	private void appendBefore(E element, Node node) {
		Node prevNode = node.previous;
		Node newNode = new Node(element, prevNode, node);
		
		prevNode.next = newNode;
		node.previous = newNode;
		
		size++;
	}
	
	/**
	 * Returns the node at the given index. If an invalid index is passed, it will either throw an exception or return null, depending
	 * on the index.
	 * 
	 * @param index
	 * @return node
	 */
	private Node getNode(int index) {
		Node node;
		if(index > size - index) {
			node = tail;
			
			for(int reverseIdx = size - 1; reverseIdx > index; reverseIdx--)
				node = node.previous;
		}
		else {
			node = head;
			
			for(int forewardIdx = 0; forewardIdx < index; forewardIdx++)
				node = node.next;
		}
		
		return node;
	}
	
	/**
	 * Removes a given node without head/tail considerations.
	 * 
	 * @param node unwanted node
	 * @return value of the removed node
	 */
	private E removeInPlace(Node node) {
		E value = node.value;
		
		if(node.previous != null)
			node.previous.next = node.next;
		if(node.next != null)
			node.next.previous = node.previous;
		
		size--;
		return value;
	}
	
	/**
	 * Each node contains a value and is linked to a previous node (unless it is a head) and a succeeding node (unless it is a tail).
	 * 
	 * @author Tyler Adams
	 */
	private class Node {
		private Node previous, next;
		private E value;
		
		/**
		 * Creates a new node with the given pointers.
		 * 
		 * @param _value - value this node should hold.
		 * @param _previous - previous node, or null
		 * @param _next - next node, or null
		 */
		public Node(E _value, Node _previous, Node _next) {
			previous = _previous;
			next = _next;
			value = _value;
		}
	}
	
	/**
	 * Provides the required implementation for the iterator of the DoublyLinkedList class.
	 * 
	 * @author Tyler Adams
	 */
	private class DoublyLinkedListIterator implements Iterator<E> {

		private Node currentNode, prevNode;
		boolean nextCalled;
		
		/**
		 * Creates a new iterator for the DoublyLinkedList.
		 */
		public DoublyLinkedListIterator() {
			currentNode = head;
			nextCalled = false;
		}
		
		/**
		 * Determines if the iterator has any more elements.
		 * 
		 * @return True if the iterator has more elements, or false if it has no more elements.
		 */
		@Override
		public boolean hasNext() {
			return currentNode != null;
		}
		
		/**
		 * Returns the next element in the list, if it exists.
		 * 
		 * @throws NoSuchElementException if the iterator has no more elements.
		 */
		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException("This iterator has no more elements.");
			
			E value = currentNode.value;
			prevNode = currentNode;
			currentNode = currentNode.next;
			nextCalled = true;
			return value;
		}

		/**
		 * Removes from the Iterator the last element returned. Can only be called once per call to next().
		 * 
		 * @throws IllegalStateException if the next() method has not yet been called or if the remove() method has already been called once
		 * since the last call to next().
		 */
		@Override
		public void remove() throws IllegalStateException{
			if(!nextCalled)
				throw new IllegalStateException("The next() method has not been called since the last call to remove().");
			
			if(prevNode.previous == null)
				removeFirst();
			else if(currentNode == null)
				removeLast();
			else
				removeInPlace(prevNode);
			nextCalled = false;
		}
	}
}
