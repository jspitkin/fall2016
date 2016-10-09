package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a DoublyLinkedList data structure and contains various methods to add to and manipulate them.
 * @author Brian Park u0735732
 *
 * @param <E> the type of DoublyLinkedList desired
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	
	private Node tail;
	private Node head;
	private int size = 0;
	
	/**
	 * Inserts the specified element at the beginning of the list. 
	 * @param element: the item to be added to the list
	
	 */
	@Override
	public void addFirst(E element) {
		Node<E> toAdd = new Node<E>();
		toAdd.setElement(element);
		
		if(size == 0){
			tail = toAdd;
			head = toAdd;
		}
		else{
			head.setPrevious(toAdd);
			toAdd.setNext(head);
			head = toAdd;
		}		
		size ++;	
	}
	/**
	 * Inserts the specified element at the end of the list.
	 * @param o: the item to be added to the list

	 */
	@Override
	public void addLast(E o) {
		Node<E> toAdd = new Node<E>();
		toAdd.setElement(o);
		
		if(size == 0){
			tail = toAdd;
			head = toAdd;
		}
		else{
			tail.setNext(toAdd);
			toAdd.setPrevious(tail);
			tail = toAdd;
		}
		size++;	
		
	}
	/**
	 * Inserts the specified element at the specified position in the list. 
	 * 
	 * @Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 *  @param index: insertion index
	 *  @param element: the element to be added
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index > size || index < 0){
			throw new IndexOutOfBoundsException();
		}
		if(index == size){
			addLast(element);
			return;
		}
		if(index == 0){
			addFirst(element);
			return;
		}	
		Node toAdd = new Node();
		toAdd.setElement(element);
		if( size - index < index){ //closer to end of array: iterate backwards
			Node current = tail;
			for(int i = size-1; i >= 0; i--){
				if(i == index){
					current.getPrevious().setNext(toAdd);
					toAdd.setPrevious(current.getPrevious());
					toAdd.setNext(current);
					current.setPrevious(toAdd);
					size++;
					return;	
				}
				current = current.getPrevious();
			}
		}
		else{ //iterate forwards
			Node current = head;
			for(int i = 0; i < size; i ++){
				if(i == index){
					current.getPrevious().setNext(toAdd);
					toAdd.setPrevious(current.getPrevious());
					toAdd.setNext(current);
					current.setPrevious(toAdd);
					size++;
					return;	
				}
				current = current.getNext();
			}
		}
	}

	/**
	 * Returns the first element in the list. 
	 * @Throws NoSuchElementException if the list is empty.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		return (E) head.getElement();
	}
	/**
	 * Returns the last element in the list. 
	 * @Throws NoSuchElementException if the list is empty.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		return (E) tail.getElement();
	}
	
	/**
	 * Returns the element at the specified position in the list. 
	 * @Throws IndexOutOfBoundsException if index is out of range 
	 *  @param index : the index of the desired item to get
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException { //check
		if(index >= size || index < 0){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return getFirst();
		}
		if(index == size-1){
			return getLast();
		}
		if( size - index < index){ //iterate backwards
			Node current = tail;
			for (int i = size-1; i >= 0; i-- ){
				 if(i == index){
					 return (E) current.getElement();
				 }
				 current = current.getPrevious();
			}
		}
		else{ //iterate forwards 
			Node current = head;
			for(int i = 0; i < size; i ++){
				if( i == index){
					return (E) current.getElement();
				}
				current = current.getNext();
			}
		}
		return null;
	}
	/**
	 * Removes and returns the first element from the list. 
	 * @Throws NoSuchElementException if the list is empty.
	 *  
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (size == 0){
			throw new NoSuchElementException();
		}
		Node toRemove = head;
		toRemove.getNext().setPrevious(null);
		head = toRemove.getNext();
		toRemove.setNext(null);
		size--;
		return (E) toRemove;
	}

	/**
	 * Removes and returns the last element from the list. 
	 * @Throws NoSuchElementException if the list is empty.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if (size == 0){
			throw new NoSuchElementException();
		}
		Node toRemove = tail;
		toRemove.getPrevious().setNext(null);
		tail = toRemove.getPrevious();
		toRemove.setPrevious(null);
		size--;
		return (E) toRemove.getElement();
	}
	/**
	 * Removes and returns the element at the specified position in the list. 
	 * @Throws IndexOutOfBoundsException if index is out of range.
	 * @param index: the index of the item to be removed
	 * @return the removed item.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return removeFirst();
		}
		if(index == size-1){
			return removeLast();
		}
		if(size - index < index){ //backward
			Node current = tail;
			for(int i = size-1; i >= 0; i--){
				if (i == index){
					current.getPrevious().setNext(current.getNext());
					current.getNext().setPrevious(current.getPrevious());
					current.setNext(null);
					current.setPrevious(null);
					size--;
					return (E) current;
				}
				current = current.getPrevious();
			}
			
		}
		else{ //forward
			Node current = head;
			for(int i = 0; i < size; i ++){
				if (i == index){
					current.getPrevious().setNext(current.getNext());
					current.getNext().setPrevious(current.getPrevious());
					current.setNext(null);
					current.setPrevious(null);
					size--;
					return (E) current;
				}
				current = current.getNext();
			}
			
		}
		return null;
	}
	 /**
	  * Returns the index of the first occurrence of the specified element in the list,
	  *  or -1 if this list does not contain the element. 
	  *  @param element: the element being searched for.
	  *  @return the index of the first occurence of the item or -1 if it is not present
	  */
	@Override
	public int indexOf(E element) { 
		int currentIndex = 1;
		Iterator<E> iter = iterator();
		if(element.equals(head.getElement())){
			return 0;
		}
		while(iter.hasNext()){
			if (iter.next().equals(element)){
				return currentIndex;
			}
			currentIndex++;
		}
		return -1;
	}
	/**
	 * Returns the index of the last occurrence of the specified element in this list,
	 *  or -1 if this list does not contain the element. 
	 *  @param element: the element being searched for
	 *  @return the index of the item or -1 if the item is not present
	 */
	@Override
	public int lastIndexOf(E element) {
		Node current = tail;
		
		for(int i = size-1; i >= 0; i--){
			if(current.getElement().equals(element)){ 
				return i;
			}
			current = current.getPrevious();
		}
		return -1;
	}
	/**
	 * gets the size of the list
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * @return true if the list is empty
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * clears the list of all elements
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		
	}
	
	/**
	 * Creates an array of all of the elements contained in this list.
	 * @return an array representing the list invoked upon
	 */
	@Override
	public Object[] toArray() {
		E[] array = (E[]) new Object[size];
		Node current = head;
		for(int i = 0; i < size; i ++){
			array[i] = (E) current.getElement();
			current = current.getNext();
		}
		return array;
	}
	/**
	 * This is a node class designed to be used in a DoublyLinkedList. It serves
	 * as a wrapper class that holds the links to the next and previous node as well as 
	 * the data element of the it's list position.
	 * @author Brian Park
	 *
	 * @param <E> the type of node being made
	 */
	private class Node<E> {
			
			private E item;
			private Node next = null;
			private Node previous = null;

			
			public Node(){
				
			}
			public E getElement(){
				return item;
			}
			public void setElement(E ele){
				item = ele;
			}
			public Node getNext(){
				return next;
			}
			public Node getPrevious(){
				return previous;
			}
			public void setPrevious(Node prev){
				previous = prev;
			}
			public void setNext(Node nxt){
				next = nxt;
			}
		}
	/**
	 * This is an iterator implemented for the DoublyLinkedList class
	 */
	@Override
	public Iterator<E> iterator() {
		
		Iterator<E> iter = new Iterator<E>(){
			
			Node current = head; 
			
			/**
			 * @return true if there is another index after the current item
			 */
			@Override
			public boolean hasNext() {
				return current.getNext() != null;
			}
			/**
			 * gets the next element in the list
			 * @return the next item in sequence of the list
			 */
			@Override
			public E next() {
				if(current == head){
					current = current.getNext();
					return (E) head.getElement();
				}
				else{
				E next = (E) current.getNext().getElement();
				current = current.getNext();
				return next;
				}
			}
			/**
			 * removes the element most recently gotten through the next method.
			 */
			public void remove(){
				current.getNext().setPrevious(current.getPrevious());
				current.getPrevious().setNext(current.getNext());
				current.setNext(null);
				current.setPrevious(null);
			}

		};
		return iter;
	}


}
