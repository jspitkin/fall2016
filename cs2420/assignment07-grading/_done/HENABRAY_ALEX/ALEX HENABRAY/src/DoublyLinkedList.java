package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author Alex Henabray (uID: u0795787), last updated 10-05-16
 * Course: CS 2420
 * Assignment 06
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private int size = 0;
	private ListNode head, tail, currentNode;
	private Iterator<E> iter;

	/**
	 * Constructs a Doubly Linked List
	 */
	public DoublyLinkedList() {
		head = new ListNode();
		tail = new ListNode();

		head.setNext(tail);
		head.setPrev(null);

		tail.setNext(null);
		tail.setPrev(head);
	}

	/**
	 * This class defines nodes, which are the 
	 * components of a doubly linked list.
	 * @author Alex Henabray
	 *
	 */
	private class ListNode {

		private E element;
		private ListNode prev, next;

		// Constructor
		public ListNode() {
		}

		// The following methods are basic getters & setters
		// for the private fileds defined for each ListNode object.

		/*
		 * Return the previous node of the current node
		 */
		public ListNode getPrev() {
			return prev;
		}

		/**
		 * Set the previous node of the current node
		 * @param node
		 */
		public void setPrev(ListNode node) {
			prev = node;
		}

		public ListNode getNext() {
			return next;
		}

		public void setNext(ListNode node) {
			next = node;
		}

		public E getElement() {
			return element;
		}

		/**
		 * This method specifies what element a node contains and 
		 * the next / prev nodes it is connected to
		 * @param prevNode
		 * @param nextNode
		 * @param elem
		 */
		public void setNode(ListNode prevNode, ListNode nextNode, E elem) {
			next = nextNode;
			nextNode.prev = this;

			prev = prevNode;
			prevNode.next = this;

			element = elem;	
		}
	}


	@Override
	public void addFirst(E element) {
		ListNode newNode = new ListNode();
		newNode.setNode(head, head.getNext(), element);
		size++;
	}

	@Override
	public void addLast(E o) {
		ListNode newNode = new ListNode();
		newNode.setNode(tail.getPrev(), tail, o);
		size++;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {

		if( index < 0 || index > this.size ) {
			throw new IllegalArgumentException("Index must be greater than zero and less than the size of the doubly linked list.");
		}

		// If the size of the doubly linked list is 2, then
		// add a node between the head & tail node.

		// If the user wants to add the element to the beginning or end
		// of the doubly linked list, call addFirst or addLast.

		ListNode addNode = new ListNode();

		if(this.size() == 0) {
			addNode.setNode(head, tail, element);
			size++;

		}
		else if(index == 0) {
			this.addFirst(element);
			return;
		}
		else if(index == this.size()) {
			this.addLast(element);
			return;
		}
		else {

			iter = this.iterator();
			iter.next();
			int iterIndex = 0;

			while(iter.hasNext()) {
				if(iterIndex == index) {
					addNode.setNode(currentNode.prev, currentNode, element);
					size++;
				}
				iterIndex++;
				iter.next();
			}
		}

	}

	@Override
	public E getFirst() throws NoSuchElementException {

		if(this.size() == 0) {
			throw new NoSuchElementException();
		}

		iter = this.iterator();
		return this.iter.next();
	}

	@Override
	public E getLast() throws NoSuchElementException {

		if(this.size() == 0) {
			throw new NoSuchElementException();
		}

		int iterIndex = 0;
		int listSize = this.size();
		iter = this.iterator();
		E currentElement = iter.next();

		while(iter.hasNext() && iterIndex < listSize) {

			if(iterIndex == listSize - 1) {
				return currentElement;
			}

			currentElement = iter.next();
			iterIndex++;
		}

		return null;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {

		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}

		iter = this.iterator();
		E currentElement = iter.next();
		int iterIndex = 0;

		while(iter.hasNext()) {

			if(iterIndex == index) {
				return currentElement;
			}

			currentElement = iter.next();
			iterIndex++;
		}

		return null;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {

		if(this.size() == 0) {
			throw new NoSuchElementException();
		}

		iter = this.iterator();

		E removedElement = iter.next();
		iter.remove();

		return removedElement;

	}

	@Override
	public E removeLast() throws NoSuchElementException {

		if(this.size() == 0) {
			throw new NoSuchElementException();
		}

		iter = this.iterator();
		E currentElement = iter.next();
		int index = 0;

		while(iter.hasNext()) {

			if(index == this.size() - 1) {
				iter.remove();
				return currentElement;
			}

			currentElement = iter.next();
			index++;

		}


		return null;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {


		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}

		iter = this.iterator();
		E currentElement = iter.next();
		int iterIndex = 0;

		while(iter.hasNext()) {

			if(iterIndex == index) {
				iter.remove();
				return currentElement;
			}

			iterIndex++;
			currentElement = iter.next();
		}

		return null;
	}

	@Override
	public int indexOf(E element) {

		iter = this.iterator();
		E currentElement = iter.next();
		int iterIndex = 0;

		while(iter.hasNext()) {

			if(currentElement.equals(element)) {
				return iterIndex;
			}

			currentElement = iter.next();
			iterIndex++;
		}

		return -1;
	}

	@Override
	public int lastIndexOf(E element) {

		// First count number of times element is in
		// the doubly linked list
		Iterator<E> countIter = this.iterator();
		int countIndex = 0;
		E currentElement = countIter.next();

		while(countIter.hasNext()) {

			if(currentElement.equals(element)) {
				countIndex++;
			}

			currentElement = countIter.next();
		}

		if(countIndex == 0) {
			return -1;
		}

		iter = this.iterator();
		int index = 0;
		int iterIndex = 0;
		currentElement = iter.next();

		while(iter.hasNext()) {

			if(currentElement.equals(element)) {
				index++;
			}


			if(index == countIndex) {
				return iterIndex;
			}

			iterIndex++;
			currentElement = iter.next();

		}

		return -1;

	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {

		if(this.size() == 0) {
			return true;
		}
		else {
			return false;
		}

	}

	
	@Override
	public void clear() {
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	@Override
	public Object[] toArray() {

		Object[] resultArray = new Object[this.size];

		int iterIndex = 0;
		int index = 0;
		iter = this.iterator();

		while(iterIndex <= this.size()) {

			if(iterIndex != 0) {
				resultArray[index] = iter.next();
				index++;
			}

			iterIndex++;
		}


		return resultArray;
	}

	@Override
	public Iterator<E> iterator() {

		currentNode = head;

		return new Iterator<E>() {

			@Override
			public boolean hasNext() {

				if(currentNode.getNext() != null) {
					return true;
				}

				return false;
			}

			@Override
			public E next() {

				currentNode = currentNode.getNext();
				return currentNode.getElement();
			}

			@Override
			public void remove() {

				//If the currentNode is the head / tail node, return.
				if(currentNode.getNext() == null || currentNode.getPrev() == null) {
					return;
				}
				else {
					currentNode.prev.next = currentNode.next;
					currentNode.next.prev = currentNode.prev;
					size--;
				}
			}
		};
	}

}
