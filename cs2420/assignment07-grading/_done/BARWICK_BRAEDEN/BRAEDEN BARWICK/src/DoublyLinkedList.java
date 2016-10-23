package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Braeden Barwick u0959391
 *
 * @param <E> the type of object contained in the list.
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>{

	/**
	 * a node used only to point at the head and tail of the list, points to itself in an empty list
	 */
	private Node dummyNode;
	private int size = 0;
	
	/**
	 * default constructor
	 */
	public DoublyLinkedList() {
		dummyNode = new Node(null);
		dummyNode.next = dummyNode;
		dummyNode.previous = dummyNode;
	}
	
	/**
	 * adds the given element to the top of the list, making it the new head of the list
	 */
	@Override
	public void addFirst(E element) {
		Node newNode = new Node(element);
		newNode.next = dummyNode.next;
		dummyNode.next.previous = newNode;
		dummyNode.next = newNode;
		newNode.previous = dummyNode;
		size++;
	}

	/**
	 * adds the given element to the bottom of the list, making it the new tail of the list
	 */
	@Override
	public void addLast(E element) {
		Node newNode = new Node(element);
		newNode.previous = dummyNode.previous;
		dummyNode.previous.next = newNode;
		dummyNode.previous = newNode;
		newNode.next = dummyNode;
		size++;
	}

	/**
	 * adds the given elements to the list at the specified index
	 * @throws IndexOutOfBoundsException when given an invalid index
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException();
		
		int currentIndex = 0;
		Node currentNode = dummyNode.next;
		Node newNode = new Node(element);
		
		while(currentIndex != index){
			currentNode = currentNode.next;
			currentIndex++;
		}
		
		newNode.previous = currentNode.previous;
		currentNode.previous.next = newNode;
		currentNode.previous = newNode;
		newNode.next = currentNode;
		size++;
	}

	/**
	 * returns the item at the head of the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size > 0)
			return dummyNode.next.getValue();
		else
			throw new NoSuchElementException();
	}

	/**
	 * returns the item at the tail of the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size > 0)
			return dummyNode.previous.getValue();
		else
			throw new NoSuchElementException();
	}

	/**
	 * returns the item in the list at the specified index
	 * @throws IndexOutOfBounds if given an invalid index
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		
		int currentIndex = 0;
		Node currentNode = dummyNode.next;
		
		while(currentIndex != index){
			currentNode = currentNode.next;
			currentIndex++;
		}
		
		return currentNode.getValue();
	}

	/**
	 * removes the head of the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size == 0)
			throw new NoSuchElementException();
		
		E ret = dummyNode.next.getValue();
		
		dummyNode.next = dummyNode.next.next;
		
		if(size == 1)
			dummyNode.previous = dummyNode;
		
		size--;
		return ret;
	}

	/**
	 * removes the tail of the list
	 * @throws NoSuchElementException if the list is empty
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(size == 0)
			throw new NoSuchElementException();
		
		E ret = dummyNode.previous.getValue();
		
		dummyNode.previous = dummyNode.previous.previous;
		
		if(size == 1)
			dummyNode.next = dummyNode;
		
		size--;
		return ret;
	}

	/**
	 * removes the item in the list at the specified index
	 * @throws IndexOutOfBoundsException if given an invalid index
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index >= size || index < 0)
			throw new IndexOutOfBoundsException();
		
		int currentIndex = 0;
		Node currentNode = dummyNode.next;
		
		while(currentIndex != index){
			currentNode = currentNode.next;
			currentIndex++;
		}
		
		currentNode.previous.next = currentNode.next;
		currentNode.next.previous = currentNode.previous;
		size--;
		
		return currentNode.getValue();
	}

	/**
	 * @returns the first index that contains the given element.
	 * otherwise returns -1 if the element is not contained within the list
	 */
	@Override
	public int indexOf(E element) {
		if(size == 0)
			return -1;
		int index = -1;
		int currentIndex = 0;
		Node currentNode = dummyNode.next;
		
		while(currentNode.getValue() != null && !currentNode.getValue().equals(element)){
			currentIndex++;
			currentNode = currentNode.next;
		}
		if(currentNode.getValue() == null)
			index = -1;
		else
			index = currentIndex;
		
		return index;
	}

	/**
	 * @return the last index that contains the given element if there is more than one instance of that element, else returns the first index.
	 * otherwise returns -1 if the element is not contained within the list
	 */
	@Override
	public int lastIndexOf(E element) {
		if(size == 0)
			return -1;
		int lastIndex = -1;
		int currentIndex = 0;
		Node currentNode = dummyNode.next;
		
		while(currentNode.getValue() != null){
			if(currentNode.getValue().equals(element))
				lastIndex = currentIndex;
			currentIndex++;
			currentNode = currentNode.next;
		}
		
		return lastIndex;
	}

	/**
	 * @return the number of elemnts in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return true if the list contains no elements, otherwise return false
	 */
	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}

	/**
	 * removes all elements from the list
	 */
	@Override
	public void clear() {
		dummyNode.next = dummyNode;
		dummyNode.previous = dummyNode;
		size = 0;
	}

	/**
	 * @return all the elements in the list as a basic array
	 */
	@Override
	public E[] toArray() {
		@SuppressWarnings("unchecked")
		E[] retArray = (E[]) new Object[size];
		
		int currentIndex = 0;
		Node currentNode = dummyNode.next;
		
		while(currentIndex < size){
			retArray[currentIndex] = currentNode.getValue();
			currentIndex++;
			currentNode = currentNode.next;
		}
	
		return retArray;
	}
	
	/**
	 * contains the value of each element in the list
	 * @author Braeden Barwick u0959391
	 *
	 */
	public class Node {
		public Node next;
		public Node previous;
		private E value;
		
		public Node(E element){
			value = element;
		}
		
		/**
		 * 
		 * @return the value of the node
		 */
		public E getValue(){
			return value;
		}
		
		/*
		 * for testing/debugging
		 * @see java.lang.Object#toString()
		 */
		public String toString(){
			return (String) value;
		}
	}
	
	/**
	 * @return an iterator over the list
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyIterator<E>();
	}
	
	/**
	 * 
	 * @author Braeden Barwick u0959391
	 *
	 * @param <E>
	 */
	@SuppressWarnings("hiding")
	public class MyIterator<E> implements Iterator<E> {

		Node currentNode;
		
		public MyIterator(){
			currentNode = dummyNode;
		}
		
		/**
		 * @return true if there is another element in the list
		 */
		@Override
		public boolean hasNext() {
			if(currentNode.next.getValue() == null)
				return false;
			return true;
		}

		/**
		 * @return the value of the next element in the list
		 */
		@Override
		public E next() {
			if(hasNext()){
				@SuppressWarnings("unchecked")
				E ret = (E) currentNode.next.getValue();
				currentNode = currentNode.next;

				return ret;
			}
			else
				throw new NoSuchElementException();
		}
		
		/**
		 * removes the current element in the iterator
		 */
		public void remove() {
			if(size != 0 && currentNode.getValue() != null){
				currentNode.previous.next = currentNode.next;
				currentNode.next.previous = currentNode.previous;
				size--;
			}
		}
	}

}
