package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This is a doubly linked list written by myself. It could provide a similar list as Java's LinkedList class
 * @author Haoran Chen
 * @uid 1060286
 */
public class DoublyLinkedList<T> implements List<T>, Iterable<T>{

	/**
	 * A helper class giving Node, which has a data, a previous node and a next node
	 * 
	 *
	 */
	private class Node{
		private Node prev;
		private Node next;
		private T data;	
	}
	private Node head;
	private Node tail;
	private int size = 0;	
	
	/**
	 * Constructor of the class. The head and tail are linked here.
	 */
	public DoublyLinkedList(){
		head = new Node();
		tail = new Node();
		head.next = tail;
		head.prev = null;
		tail.next = null;
		tail.prev = head;
	}
	

	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addFirst(T element) {
		Node newNode = new Node();
		newNode.data = element;
		if(this.isEmpty()){
			newNode.next = tail;
			tail.prev = newNode;
		}
		else{		
			head.next.prev = newNode;
			newNode.next = head.next;
		}
		head.next = newNode;
		newNode.prev = head;
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(T o) {
		Node newNode = new Node();
		newNode.data = o;
		if(this.isEmpty()){
			head.next = newNode;
			newNode.prev = head;
		}
		else{		
			tail.prev.next = newNode;
			newNode.prev = tail.prev;
		}
		newNode.next = tail;
		tail.prev = newNode;
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, T element) throws IndexOutOfBoundsException {
		if(index<0 || index > this.size()){
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0){
			this.addFirst(element);
		}
		else if(index == this.size()){
			this.addLast(element);
		}
		else{
			Node tmp = head.next;
			int count = 0;
			while(count<index-1){
				tmp = tmp.next;
				count ++;
			}
			Node newNode = new Node();
			newNode.data = element;
			newNode.next = tmp.next;
			newNode.prev = tmp;
			tmp.next.prev = newNode;
			tmp.next = newNode;
			size++;
		}
	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public T getFirst() throws NoSuchElementException {
		if(this.isEmpty()){
			throw new NoSuchElementException();		
		}
		return (T) head.next.data;
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public T getLast() throws NoSuchElementException {
		if(this.isEmpty()){
			throw new NoSuchElementException();		
		}
		return (T) tail.prev.data;
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		if(index<0 || index >= this.size()){
			throw new IndexOutOfBoundsException();		
		}
		Node tmp = head.next;
		int count = 0;
		while(count<index){
			tmp = tmp.next;
			count ++;
		}
		return tmp.data;
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public T removeFirst() throws NoSuchElementException {
		if(this.isEmpty()){
			throw new NoSuchElementException();	
		}
		Node tmp = head.next;
		head.next = head.next.next;
		head.next.prev = head;
		size--;
		return (T) tmp.data;
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public T removeLast() throws NoSuchElementException {
		if(this.isEmpty()){
			throw new NoSuchElementException();
			}
		Node tmp = tail.prev;
		tail.prev = tail.prev.prev;
		tail.prev.next = tail;
		size--;
		return (T) tmp.data;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		Node tmp = new Node();
		if(index<0 || index >= this.size()){
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0){
			return this.removeFirst();
		}
		else if(index == this.size()){
			return this.removeLast();
		}
		else{
			tmp = head.next;
			int count = 0;
			while(count<index){
				tmp = tmp.next;
				count ++;
			}
			tmp.next.prev=tmp.prev;
			tmp.prev.next = tmp.next;
			size--;
		}
		return tmp.data;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(T element) {
		Node tmp = head.next;
		int count = 0;
		while(!tmp.equals(tail)){
			if(tmp.data.equals(element)){
				return count;
			}
			tmp = tmp.next;
			count ++;
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(T element) {
		Node tmp = tail.prev;
		int count = this.size-1;
		while(!tmp.equals(head)){
			if(tmp.data.equals(element)){
				return count;
			}
			tmp = tmp.prev;
			count --;
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
		if(head.next.equals(tail)){
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
		head = new Node();
		tail = new Node();
		head.prev = null;
		head.next = tail;
		tail.prev = head;
		tail.next = null;
		size = 0;
		
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[this.size()];
		Node tmp = head.next;
		for(int i = 0; i<this.size();i++){
			result[i] = tmp.data;
			tmp = tmp.next;
		}
		return result;
	}
	
	/**
	 * Supplies an iterator for the linkedlist
	 */
	@Override
	public Iterator<T> iterator() {
		Iterator<T> dataArrayIterator = new Iterator<T>(){
			boolean checkNext;
			Node current = head;
			@Override
			public boolean hasNext() {	
				return (!current.next.equals(tail)) ;
			}
			@Override
			public T next() {	
				if (!this.hasNext())
					throw new NoSuchElementException();
				checkNext = true;
				current = current.next;
				return current.data;
			}
			@Override
			public void remove(){
				if (!checkNext)
					throw new IllegalStateException();
				current.prev.next = current.next;
				current.next.prev = current.prev;
				checkNext = false;
				size--;
			}
		};	
		return dataArrayIterator;
	}

}
