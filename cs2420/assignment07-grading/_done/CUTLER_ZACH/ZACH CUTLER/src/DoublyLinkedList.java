package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This class represents a DoublyLinkedList which implements the List and Iterable interfaces
 * @author Zachary Cutler, u1025642
 */
public class DoublyLinkedList<E> implements List, Iterable<E>{
	private int size;
	private Node head;
	private Node tail;
	/**
	 * Class representing a Node used in the DoublyLinkedList
	 *
	 */
	private class Node{
		private E data;
		private Node next;
		private Node prev;
		
		public Node(E _data){
			data = _data;
		}	
	}

	
	public DoublyLinkedList(){	
		size = 0; 
		head = null;
		tail = null;
	}
	/**
	 * Iterator implementation for the DoublyLinkedList
	 */
	@Override
	public Iterator<E> iterator() {
		
		return new Iterator<E>() {

			Node currentNode = null;
			/**
			 * returns true if the list has another item
			 * standard implementation of hasNext
			 */
			@Override
			public boolean hasNext() {
				if(!currentNode.next.equals(null)){
					return true;
				}
				else{
					return false;
				}
				
			}
			/**
			 * Returns the next item in the iterator and move the pointer up one
			 * standard implementation of next.
			 */
			@Override
			public E next() {
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				if(currentNode.equals(null)){
					currentNode = head;
					return currentNode.data;
				}
				else{
					currentNode = currentNode.next;
					return currentNode.data;
				}
			}
			/**
			 * removes the item where the iterator is pointing
			 * Throws a IllegalStateException if next has not been called or remove is 
			 * called more than once between next calls
			 */
			@Override
			public void remove() {
				if(currentNode.next.prev.equals(currentNode)){
					throw new IllegalStateException();
				}
				else{
					currentNode.prev.next = currentNode.next;
					currentNode.next.prev = currentNode.prev;
				}
			}
		};
	}
	/**
	 * Adds a given item to the start of the list
	 * @param element - given element to be added 
	 */
	@Override
	public void addFirst(Object element) {
		Node _element = new Node((E)element);
		
		if(this.isEmpty()){
			_element.next = null;
			_element.prev = null;
			head = _element;
			tail = _element;
		}
		else{
			head.prev = _element;
			_element.next = head;
			_element.prev = null;
			head = _element;		
		}
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(Object o) {
		Node _element = new Node((E) o);
		
		if(this.isEmpty()){
			_element.next = null;
			_element.prev = null;
			head = _element;
			tail = _element;
		}
		else{
			tail.next = _element;
			_element.next = null;
			_element.prev = tail;
			tail = _element;		
		}
		size++;	
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, Object element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		Node _element = new Node((E)element);
		if(index == 0){
			this.addFirst(element);
		}
		else if (index == size){
			this.addLast(element);
		}
		else{
			Node fakeStart = head;
			for(int i = 1; i <= size; i++){
				if(i == index){
					Node temp = fakeStart.next;
					fakeStart.next = _element;
					_element.prev = fakeStart;
					_element.next = temp;
					temp.prev = _element;
					break;
				}
				fakeStart = fakeStart.next;
			}
			size++;
		}
	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public Object getFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		return head.data;
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public Object getLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return head.data;
		}
		else if(index == size - 1){
			return tail.data;
		}
		else{
			Node fakeStart = head.next;
			for(int i = 1; i <= size; i++){
				if(i == index){
					return fakeStart.data;
				}
				fakeStart = fakeStart.next;
			}
			return null;
		}
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public Object removeFirst() throws NoSuchElementException {
		if(this.size == 0){
			throw new NoSuchElementException();
		}
		if(size == 1){
			E first = head.data;
			this.clear();
			return first;
		}
		E first = head.data;
		head = head.next;
		head.prev = null;
		size--;
		return first;
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public Object removeLast() throws NoSuchElementException {
		if(this.size == 0){
			throw new NoSuchElementException();
		}
		if(size == 1){
			E first = head.data;
			this.clear();
			return first;
		}
		E last = tail.data;
		tail = tail.prev;
		tail.next = null;
		size--;
		return last;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		E finalData = null;
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return this.removeFirst();
		}
		else if(index == size - 1){
			return this.removeLast();
		}
		else {
			Node fakeStart = head;
			for(int i = 0; i <= size; i++){
				if(i == index){
					fakeStart.prev.next = fakeStart.next;
					fakeStart.next.prev = fakeStart.prev;
					finalData = fakeStart.data;
					break;
				}
				fakeStart = fakeStart.next;
			}
			size--;
		}
		return finalData;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(Object element) {
		Node fakeStart = head;
		for(int i = 0; i < size; i++){
			if(fakeStart.data.equals(element)){
				return i;
			}
			fakeStart = fakeStart.next;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(Object element) {
		Node fakeStart = head;
		boolean hasOne = false;
		int current = 0;
		for(int i = 0; i < size; i++){
			if(fakeStart.data.equals(element)){
				hasOne = true;
				current = i;
			}
		}
		if(hasOne == true){
			return current;
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}
		return false;
	}

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {
		size = 0;
		head = null;
		tail = null;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		E[] arr = (E[]) new Object[size];
		Node current = head;
		for(int i = 0; i < size; i++){
			arr[i] = current.data;
			current = current.next;
		}
		return arr;
	}

}

