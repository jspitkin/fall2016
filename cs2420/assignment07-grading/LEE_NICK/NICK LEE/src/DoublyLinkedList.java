package assignment07;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Creates a doubly linked list. 
 * @author Nickolas Lee
 * 10-4-16
 * @param <E> the type of object to be stored in the list
 */
public class DoublyLinkedList<E> implements Iterable<E>, List<E>{
	private Node head;
	private Node tail;
	private int size;

	public DoublyLinkedList(){ // default constructor
		this.head = null;
		this.tail = null;
		size = 0;
	}

	private class Node{
		E data;
		Node previous;
		Node after;

		public Node() {
			super();
			this.data = null;
			this.previous = null;
			this.after = null;
		}

		public Node(E data, Node previous, Node after) {
			super();
			this.data = data;
			this.previous = previous;
			this.after = after;
		}

		/**
		 * @return the data
		 */
		public E getData() {
			return data;
		}

		/**
		 * @return the previous
		 */
		public Node getPrevious() {
			return previous;
		}

		/**
		 * @param previous the previous to set
		 */
		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		/**
		 * @return the after
		 */
		public Node getAfter() {
			return after;
		}

		/**
		 * @param after the after to set
		 */
		public void setAfter(Node after) {
			this.after = after;
		}
	}


	/**
	 * Starts at the head and moves along the linked list and returns 
	 * the Node at the specified index.
	 * @param index
	 * @return the Node
	 */
	private Node traverseF(int index){
		Node current = new Node(null, null, head);
		for(int el = 0; el <= index; el++){
			current = current.getAfter();
		}
		return current;
	}


	@Override
	public void addFirst(E element) {

		if(size <= 0){
			Node newNode = new Node(element, null, null);
			this.head = newNode;
			this.tail = newNode;
		}
		else if(size == 1){ 
			Node newNode = new Node(element, null, this.tail);
			newNode.getAfter().setPrevious(newNode);
			this.head = newNode;
		}
		else { // size > 1
			Node newNode = new Node(element, null, this.head);
			newNode.getAfter().setPrevious(newNode);
			this.head = newNode;
		}
		size++; 
	}


	@Override
	public void addLast(E o) {
		if(size <= 0){
			Node newNode = new Node(o, null, null);
			this.head = newNode;
			this.tail = newNode;
		}
		else if(size == 1){
			Node newNode = new Node(o, this.head, null);
			newNode.getPrevious().setAfter(newNode);
			this.tail = newNode;
		}
		else { // size > 1
			Node newNode = new Node(o, this.tail, null);
			newNode.getPrevious().setAfter(newNode);
			this.tail = newNode;
		}
		size++; 
	}


	@Override
	public E getFirst() throws NoSuchElementException {
		if(this.head == null){
			throw new NoSuchElementException();
		}
		return this.head.getData();
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if(this.tail == null){
			throw new NoSuchElementException();
		}
		return this.tail.getData();
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}

		if(index == 0){// adding to the beginning of the list
			addFirst(element);
			return;
		}
		else if(index == size){// adding to the end of the list
			addLast(element);
			return;
		}

		Node current = traverseF(index);
		Node newNode = new Node();
		newNode.setPrevious(current);
		newNode.setAfter(current.getAfter());
		newNode.getPrevious().setAfter(newNode);
		newNode.getAfter().setPrevious(newNode);
		size++;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size){ 
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		Node forward = traverseF(index);
		return forward.getData();
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if(head == null){
			throw new NoSuchElementException();
		}

		E data = head.getData();
		if(head == tail){
			head = null;
			tail = null;
		}
		else{
			head.getAfter().setPrevious(null);
			head = head.getAfter();
		}
		size--;
		return data;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if(tail == null){
			throw new NoSuchElementException();
		}

		E data = tail.getData();
		if(head == tail){
			head = null;
			tail = null;
		}
		else{
			tail.getPrevious().setAfter(null);
			tail = tail.getPrevious();
		}
		size--;
		return data;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}

		if(index == 0){
			return removeFirst();
		}
		else if(index == size - 1){
			return removeLast();
		}
		else{
			Node current = traverseF(index);
			E data = current.getData();
			current.getAfter().setPrevious(current.getPrevious());
			current.getPrevious().setAfter(current.getAfter());
			current = current.getAfter();
			current.setAfter(null); // makes sure to remove the Node from memory
			current.setPrevious(null);
			size--;
			return data;
		}
	}

	/**
	 * @param <E> Type of the data entered into each Node
	 * @return an iterator over the elements in this list
	 */
	@Override
	public Iterator<E> iterator() {

		return new Iterator<E>() {
			Node current = head;
			boolean calledNextOnHead = false;
			boolean calledNextBeforeRemove = false;

			@Override
			public boolean hasNext() {
				if(head != null && current.getAfter() != null){
					return true;
				}
				return false;
			}

			@Override
			public E next() {
				if(head == null || !hasNext() && head != tail){ // yes if size=0, no if size=1, ...
					throw new NoSuchElementException();
				}
				E data;
				if(current == head && !calledNextOnHead){
					data = current.getData();
					calledNextOnHead = true;
					return data;
				}
				else {
					calledNextOnHead = false;
				}
				current = current.getAfter();
				calledNextBeforeRemove = true;
				return current.getData(); 
			}

			@Override
			public void remove() { // for iterator
				if(!(calledNextOnHead || calledNextBeforeRemove)){
					throw new IllegalStateException("Must call next before each call to remove");
				} 

				if(calledNextOnHead){ // remove head
					if(head == tail){
						head = null; 
						tail = null;
					}
					else if(current == head){
						current.getAfter().setPrevious(null);
						current = current.getAfter();
						head = head.getAfter();
					}

					calledNextOnHead = false;
				}
				else if(calledNextBeforeRemove){ 
					current.getAfter().setPrevious(current.getPrevious());
					current.getPrevious().setAfter(current.getAfter());
					current = current.getAfter();
					current.setAfter(null); // makes sure to remove the Node from memory
					current.setPrevious(null);
				}
				size--;
				calledNextBeforeRemove = false;
			}
		};
	}


	@Override
	public int indexOf(E element) {
		Node current = new Node(null, null, head);
		for(int el = 0; el < size; el++){
			current = current.getAfter();
			if(current.getData().equals(element)){
				return el;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		Node current = new Node(null, tail, null);
		for(int el = size - 1; el >= 0; el--){
			current = current.getPrevious();
			if(current.getData().equals(element)){
				return el;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		for(int cur = 0; cur < result.length; cur++){
			result[cur] = get(cur);
		}
		return result;
	}

}
