package assignment_06;

/**
 * Anthony Iovino 
 */


import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private Node before;
	private Node after;
	private int numberOfNodes;

	public DoublyLinkedList() {
		before = new Node(null, null, null);
		after = new Node(before, null, null);
		before.next = after;
	}

	@Override
	public void addFirst(E element) {
		Node newNode = new Node(before, element, before.next);

		
		before.next.prev = newNode;// update after reference

		
		before.next = newNode;// update before reference

		numberOfNodes++;
	}

	@Override
	public void addLast(E element) {
		Node newNode = new Node(after.prev, element, after);

		
		after.prev.next = newNode;// update before reference

		
		after.prev = newNode;// update after reference

		numberOfNodes++;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > numberOfNodes)
			throw new IndexOutOfBoundsException();

		
		Node currentNode = before.next;// move to correct node
		int currIndex = 0;
		while (currIndex != index) {
			currentNode = currentNode.next;
			currIndex++;
		}

		Node newNode = new Node(currentNode.prev, element, currentNode);

		
		currentNode.prev.next = newNode;// Update before reference

		
		currentNode.prev = newNode;// Update after reference

		numberOfNodes++;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		return before.next.data;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		return after.prev.data;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= numberOfNodes)
			throw new IndexOutOfBoundsException();

		Node currentNode = null;

		
		if (index < numberOfNodes / 2) {// Start from left of list
			currentNode = before.next;
			int currIndex = 0;
			while (currIndex != index) {
				currentNode = currentNode.next;
				currIndex++;
			}
		}
	
		else {	// Start from right of list
			currentNode = after.prev;
			int currIndex = numberOfNodes - 1;
			while (currIndex != index) {
				currentNode = currentNode.prev;
				currIndex--;
			}
		}

		return currentNode.data;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		E data = before.next.data;
		
		
		before.next.next.prev = before;// Update after reference

		
		before.next = before.next.next;// Update before reference

		numberOfNodes--;
		
		return data;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if (isEmpty())
			throw new NoSuchElementException();

		E data = after.prev.data;
		
		
		after.prev.prev.next = after;// Update before reference

		
		after.prev = after.prev.prev;// Update after reference

		numberOfNodes--;
		
		return data;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= numberOfNodes)
			throw new IndexOutOfBoundsException();

		
		Node currentNode = before.next;// Travel to correct node
		int currIndex = 0;
		while (currIndex != index) {
			currentNode = currentNode.next;
			currIndex++;
		}
				
		E data = currentNode.data;
		
	
		currentNode.prev.next = currentNode.next;	// Update before reference

		
		currentNode.next.prev = currentNode.prev;   // Update after reference

		numberOfNodes--;
		
		return data;
	}

	@Override
	public int indexOf(E element) {
		Node currentNode = before.next;
		int index = 0;
		while (currentNode != after) {
			if (currentNode.data.equals(element))
				return index;

			currentNode = currentNode.next;
			index++;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		Node currentNode = after.prev;
		int index = numberOfNodes - 1;
		while (currentNode != before) {
			if (currentNode.data.equals(element))
				return index;

			currentNode = currentNode.prev;
			index--;
		}

		return -1;
	}

	@Override
	public int size() {
		return numberOfNodes;
	}

	@Override
	public boolean isEmpty() {
		return numberOfNodes == 0;
	}

	@Override
	public void clear() {
		before.next = after;
		after.prev = before;
		numberOfNodes = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[numberOfNodes];

		Node currentNode = before.next;
		int index = 0;
		while (currentNode != after) {
			array[index] = currentNode.data;
			currentNode = currentNode.next;
			index++;
		}

		return array;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator(before.next);
	}

	@Override
	public String toString() {
		String result = "";

		result += size() + " ";

		Node currentNode = before.next;
		while (currentNode != after) {
			result += currentNode.toString();
			currentNode = currentNode.next;
		}

		return result;
	}


	
	private class Node {
		E data;
		Node prev, next;

		public Node(Node prev, E data, Node next) {
			this.prev = prev;
			this.data = data;
			this.next = next;
		}

		@Override
		public String toString() {
			return data + " ";
		}
	}
	
	private class DoublyLinkedListIterator implements Iterator<E> {

		private Node currentNode;
		
		public DoublyLinkedListIterator(Node start) {
			currentNode = start;
		}
		
		@Override
		public boolean hasNext() {
			return currentNode != after;
		}

		@Override
		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			
			E data = currentNode.data;
			
			currentNode = currentNode.next;
			return data;
		}
		
		@Override
		public void remove() {
			if (currentNode.prev == before)
				throw new IllegalStateException();
			
			Node removeNode = currentNode.prev;
			
			
			removeNode.prev.next = removeNode.next;// Update before reference

			
			removeNode.next.prev = removeNode.prev;// Update after reference

			numberOfNodes--;
        }
	}
}