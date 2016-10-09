package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A Doubly Linked List.
 * @author Noah Goetz
 * @uid u1046618
 * @param <E>
 *            : the type of elements contained in the list
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>{

	private int size;
	private Node head;
	private Node tail;
	private Iterator<E> iterator;

	/**
	 * Default Constructor
	 */
	public DoublyLinkedList(){
		size = 0;
		head = null;
		tail = head;
		iterator = new DoublyLinkedListIterator();
	}
	
	/**
	 * Default Constructor
	 */
	public DoublyLinkedList(Iterator<E> newIterator){
		size = 0;
		head = null;
		tail = head;
		iterator = newIterator;
	}
	
	/**
	 * @param The data to put in the newly added Node.
	 * Inserts the specified element at the beginning of the list. 
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addFirst(E element) {
		if(head==null){
			head = new Node();
			head.data = element;
			head.next = tail;
			head.prev = null;
		}
		else{
		Node newNode = new Node();
		newNode.data = element;
		newNode.next = head;
		newNode.prev = null;
		head.prev = newNode;
		head = newNode;
		}
		size++;
	}

	/**
	 * @param The data to put in the newly added Node.
	 * Inserts the specified element at the end of the list. 
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(E element) {
		if(tail==null){
			tail = new Node();
			tail.data = element;
			tail.prev = head;
			tail.next = null;
		}
		else{
			Node newNode = new Node();
			newNode.data = element;
			newNode.next = null;
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		}
		size++;
	}

	/**
	 * @param The data to put in the newly added Node as well as the index to place the new data at.
	 * Inserts the specified element at the specified position in the list. 
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size()) 
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException();
		Node newNode = new Node();
		newNode.data = element;
		Node elementAtIndex;
		if(index > size/2){
			elementAtIndex = tail;
			for(int currentIndex = size-1; currentIndex > index; currentIndex--){
				elementAtIndex=elementAtIndex.prev;
			}
		}
		else{
			elementAtIndex = head;
			for(int currentIndex = 0; currentIndex < index; currentIndex++){
				elementAtIndex=elementAtIndex.next;
			}
		}
		newNode.next = elementAtIndex;
		newNode.prev = elementAtIndex.prev;
		elementAtIndex.prev.next = newNode;
		elementAtIndex.prev = newNode;
		size++;
	}

	/**
	 * @return the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size < 1)
			throw new NoSuchElementException();
		return head.data;
	}

	/**
	 * @return the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size < 1)
			throw new NoSuchElementException();
		return tail.data;
	}

	/**
	 * @return the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index > size-1 || index < 0)
			throw new IndexOutOfBoundsException();
		Node elementAtIndex;
		if(index > size/2){
			elementAtIndex = tail;
			for(int currentIndex = size-1; currentIndex > index; currentIndex--){
				elementAtIndex = elementAtIndex.prev;
			}
		}
		else{
			elementAtIndex = head;
			for(int currentIndex = 0; currentIndex < index; currentIndex++){
				elementAtIndex = elementAtIndex.next;
			}
		}
		return elementAtIndex.data;
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size < 1)
			throw new NoSuchElementException();
		E data = head.data;
		if(size > 1){
			head = head.next;
			head.prev = null;
		}
		else{
			head = null;
			tail = head;
		}
		size--;
		return data;
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(size < 1)
			throw new NoSuchElementException();
		E data = tail.data;
		if(size > 1){
			tail = tail.prev;
			tail.next = null;
		}
		else{
			tail = null;
			head = tail;
		}
		size--;
		return data;
	}

	/**
	 * @param The index of the Node to be removed.
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index > size-1 || index < 0)
			throw new IndexOutOfBoundsException();
		Node elementAtIndex;
		if(index > size/2){
			elementAtIndex = tail;
			for(int currentIndex = size-1; currentIndex>index; currentIndex--){
				elementAtIndex = elementAtIndex.prev;
			}
		}
		else{
			elementAtIndex = head;
			for(int currentIndex = 0; currentIndex < index; currentIndex++){
				elementAtIndex = elementAtIndex.next;
			}
		}
		elementAtIndex.prev.next = elementAtIndex.next;
		elementAtIndex.next.prev = elementAtIndex.prev;
		size--;
		return elementAtIndex.data;
	}

	/**
	 * @param The data which is in the Node which you want to retrieve the Index of.
	 * @return the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(E element) {
		for(int index = 0; index < size; index++)
			if((get(index)).equals(element))
				return index;
		return -1;
	}

	/**
	 * @param The data which is in the Node which you want to retrieve the Index of.
	 * @return the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element) {
		for(int index = size-1; index >= 0; index--)
			if((get(index)).equals(element))
				return index;
		return -1;
	}

	/**
	 * @return the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		return (size==0);
	}

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {
		size = 0;
		head = null;
		tail = head;
	}

	/**
	 * @return an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E[] toArray() {
		E[] array = (E[]) new Object[size];
		for(int index = 0; index<size; index++)
			array[index] = get(index);
		return array;
	}

	/**
	 * @return the iterator being used by the Doubly Linked List
	 */
	@Override
	public Iterator<E> iterator() {
		return iterator;
	}

	private class Node {
		private E data;
		private Node next;
		private Node prev;
	}

	private class DoublyLinkedListIterator implements Iterator<E> {

		private Node currentNode = head.next;
		private Node lastNode = null;
		private int currentIndex = 0;

		@Override
		/**
		 * @return true-------if there are next more items in the array to iterate through
		 * 		 	false------if there is no next to iterate through
		 */
		public boolean hasNext() {
			return (currentIndex < size-1);
		}
		
		public boolean hasPrevious() {
			return (currentIndex > 0);
		}

		@Override
		/**
		 * @return the elements in the array, 
		 * 			if there is no next element in the array, throw an "NoSuchElementException" exception
		 */
		public E next() throws NoSuchElementException{
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				lastNode = currentNode;
				currentNode = currentNode.next;
				currentIndex++;
				return currentNode.data;
			}
		}


		/**
		 * @return the elements in the array, 
		 * 			if there is no previous element in the array, throw an "NoSuchElementException" exception
		 */
		public E previous() throws NoSuchElementException{
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			else {
				currentNode = lastNode;
				lastNode = lastNode.prev;
				currentIndex--;
				return currentNode.data;
			}
		}
		

		public void remove() {
			DoublyLinkedList.this.remove(currentIndex--);
			currentNode = lastNode;
			lastNode = currentNode.prev;
		}
	}
}