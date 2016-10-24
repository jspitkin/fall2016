package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A LinkedList that has nodes that reference the previous and next nodes in the sequence.
 * @author Logan Terry u0973436
 *
 * @param <E> - The type of data this structure should contain. Eg Integers, Doubles, Objects, etc...
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	private int size;
	private Node<E> head;
	private Node<E> tail;
	
	public DoublyLinkedList(){
		size = 0;
		head = null;
		tail = null;
	}
	
	/**
	 * Inserts the specified element at the beginning of the list.
	 * @param element - The element to add to the start of the list
	 */
	@Override
	public void addFirst(E element) {
		add(0,element);
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * @param o - The element to add to the end of the list
	 */
	@Override
	public void addLast(E element) {
		add(size, element);
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range.
	 * @param index - The index to insert the element
	 * @param element - The element to insert
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException(index + " is out of range for this list!");
		}
		//Special if intial item
		if(size == 0){
			head = new Node<E>();
			tail = head;
			head.setData((E) element);
			size++;
			return;
		}
		//Put at front of list
		if(index == 0){
			//Regular case
			Node<E> temp = head;
			head = new Node<E>();
			head.setData((E) element);
			head.setNext(temp);
			temp.setPrevious(head);
			size++;
			return;
		}
		//Put at end of list
		if(index == size){
			Node<E> temp = new Node<E>();
			temp.setData((E) element);
			temp.setPrevious(tail);
			tail.setNext(temp);
			tail = temp;
			size++;
			return;
		}
		//Normal case
		Node<E> before = head;
		for(int i = 0; i < index - 1; i++){
			before = before.next();
		}
		Node<E> after = before.next();
		Node<E> toInsert = new Node<E>();
		toInsert.setData((E) element);
		//Reference changing
		before.setNext(toInsert);
		toInsert.setPrevious(before);
		toInsert.setNext(after);
		after.setPrevious(toInsert);
		size++;
		
	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * @return The element that is first in the list
	 * @throws NoSuchElementException
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("There's no first element in an empty list!");
		}
		return head.data();
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * @return - The element at the end of the list
	 * @throws NoSuchElementException
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("There's no last element in an empty list!");
		}
		return tail.data();
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range.
	 * @param index - The index to retrieve the element at
	 * @return - The element at the specified index
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size - 1){
			throw new IndexOutOfBoundsException(index + " is out of bounds for this list!");
		}
		Node<E> current = head;
		for(int i = 0; i < index; i++){
			current = current.next();
		}
		return current.data();
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * @return The first element in the list
	 * @throws NoSuchElementException
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("There's no first element in an empty list!");
		}
		return remove(0);
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * @return - The element at the end of the list
	 * @throws NoSuchElementException
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("There's no last element in an empty list!");
		}
		return remove(size-1);
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range. 
	 * @param index - The index to remove an element
	 * @return - The element at the specified index
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException(index + " is out of bounds for this list!");
		}
		//Special case if last item
		if(size == 1){
			Node<E> temp = head;
			head = null;
			tail = null;
			size = 0;
			return temp.data();
		}
		//If at the begining
		if(index == 0){
			Node<E> temp = head;
			head = head.next();
			head.setPrevious(null);
			size--;
			return temp.data();
		}
		//If at the end
		if(index == size-1){
			Node<E> temp = tail;
			tail = tail.previous();
			tail.setNext(null);
			size--;
			return temp.data();
		}
		Node<E> current = head;
		for(int i = 0; i < index; i++){
			current = current.next();
		}
		Node<E> before = current.previous();
		Node<E> after = current.next();
		before.setNext(after);
		after.setPrevious(before);
		size--;
		return current.data();
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list,
	 *  or -1 if this list does not contain the element.
	 *  @param element - The element to search for
	 *  @return - The index of the element (if it was found)
	 */
	@Override
	public int indexOf(E element) {
		Node<E> current = head;
		for(int i = 0; i < size; i++){
			//Special null item
			if(current.data() == null){
				if(current.data() == element){
					return i;
				}
			}else if(current.data().equals(element)){
				return i;
			}
			current = current.next();
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list,
	 *  or -1 if this list does not contain the element.
	 *  @param element - The element to search for
	 *  @return - The index of the element (if it was found)
	 */
	@Override
	public int lastIndexOf(E element) {
		Node<E> current = tail;
		for(int i = size -1; i > -1; i--){
			//Special null item
			if(current.data() == null){
				if(current.data() == element){
					return i;
				}
			}else if(current.data().equals(element)){
				return i;
			}
			current = current.previous();
		}
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * @return - The size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * @return - True if this collection contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list
	 *  in proper sequence (from first to last element).
	 *  @return - An Array representing this list
	 */
	@Override
	public E[] toArray() {
		@SuppressWarnings("unchecked")
		E[] arr = (E[]) new Object[size];
		Node<E> current = head;
		for(int i = 0; i < size; i++){
			arr[i] = current.data();
			current = current.next();
		}
		return arr;
	}

	/**
	 * Returns an iterator over this list.
	 * @return An iterator
	 */
	@Override
	public Iterator<E> iterator() {
		Node<E> temp = new Node<E>();
		temp.next = head;
		Iterator<E> out = new Iterator<E>(){
			private Node<E> current = temp;
			private boolean wasNextCalled = false;
			
			/**
			 * Returns true if the iteration has more elements.
			 * @return - True if the iteration has more elements.
			 */
			@Override
			public boolean hasNext(){
				return current.next != null;
			}

			/**
			 * Returns the next element in the iteration.
			 * @return - The next element in the iteration
			 */
			@Override
			public E next() {
				if(!hasNext()){
					throw new NoSuchElementException("No element found!");
				}
				wasNextCalled = true;
				current = current.next();
				return current.data();
			}
			
			/**
			 * Removes from the underlying collection the last element returned by this iterator.
			 * This method can be called only once per call to next().
			 */
			@Override
			public void remove()throws IllegalStateException{
				if(!wasNextCalled){
					throw new IllegalStateException("Next hasn't been called before remove!");
				}
				wasNextCalled = false;
				//Only item case
				if(size == 1){
					tail = null;
					head = null;
					size = 0;
					return;
				}
				//start case
				if(current.previous() == null){
					head = current.next();
					size--;
					return;
				}
				//end case
				if(current.next() == null){
					tail = current.previous();
					size--;
					return;
				}
				//Normal case
				Node<E> before = current.previous();
				Node<E> after = current.next();
				before.setNext(after);
				after.setPrevious(before);
				size--;
			}
			
		};
		return out;
	}

	/**
	 * The Node class for use in constructing a LinkedList structure.
	 * @author Logan Terry
	 *
	 * @param <E> - The type of data the node should contain
	 */
	@SuppressWarnings("hiding")
	private class Node<E> {
		
		private E data;
		private Node<E> next;
		private Node<E> previous;
		
		public Node(){
			data = null;
			next = null;
			previous = null;
		}
		
		/**
		 * Sets the data for this node.
		 * @param data - The data this.data() should reference
		 */
		public void setData(E data){
			this.data = data;
		}
		
		/**
		 * Sets the next node for this node.
		 * @param next - The node this.next() should reference
		 */
		public void setNext(Node<E> next){
			this.next = next;
		}
		
		/**
		 * Sets the previous node for this node.
		 * @param previous - The node this.previous() should reference
		 */
		public void setPrevious(Node<E> previous){
			this.previous = previous;
		}
		
		/**
		 * Returns the data this node is holding.
		 * @return - The data this node is holding
		 */
		public E data(){
			return data;
		}
		
		/**
		 * Returns the next node in the sequence.
		 * @return - The next node in the sequence
		 */
		public Node<E> next(){
			return next;
		}
		
		/**
		 * Returns the previous node in the sequence.
		 * @return - The previous node in the sequence
		 */
		public Node<E> previous(){
			return previous;
		}
		
		
	}
}
