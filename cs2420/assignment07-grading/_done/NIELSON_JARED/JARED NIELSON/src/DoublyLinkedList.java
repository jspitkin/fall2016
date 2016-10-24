package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of a DoublyLinkedList data structure. Implements
 * both the List and Iterable interfaces.
 * 
 * @author Jared Nielson u0495206
 * 
 * @Param E - The type held by the list
 *
 */
public class DoublyLinkedList<E> implements Iterable<E>, List<E>{
	
	private int size;
	
	private Node<E> head;
	private Node<E> tail;
	
	public DoublyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	
	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addFirst(E element) {
		if(size == 0){
			head = new Node<E>(element);
			tail = head;
		} else {
			Node<E> tmp = head;
			head = new Node<E>(element);
			head.next = tmp;
			head.next.previous = head;
		}
		size++;
		
		
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(E o) {
		if(size == 0){
			tail = new Node<E>(o);
			head = tail;
		} else {
			Node<E> tmp = tail;
			tail = new Node<E>(o);
			tail.previous = tmp;
			tail.previous.next = tail;
		}
		
		size++;
		
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		//Check for a valid index
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException("Attempted to add element at index " + index + 
												", but size is " + size);
		}
		
		//If index is 0 we can just add first
		if(index == 0){
			addFirst(element);
			return;
		}
		//If index is size we can just add last
		if(index == size){
			addLast(element);
			return;
		}
		
		Node<E> current = iterate(index);
		Node<E> tmp = new Node<E>(element);
		
		current.previous.next = tmp;
		tmp.previous = current.previous;
		current.previous = tmp;
		tmp.next = current;
		
		size++;
		
	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("Attempted to getFirst when there was no first");
		}
		
		return head.data;
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("Attempted to getLast when there was no last");
		}
		
		return tail.data;
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Attempted to access index " + index + 
												", greatest valid index is " + (size - 1));
		}
		
		return iterate(index).data;
	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("Attempted to remove first when there is no first");
		}
		
		E result = head.data;
		
		if(size == 1){
			head = null;
			tail = null;
		} else{
			head = head.next;
			head.previous = null;
		}
		
		size--;
		
		return result;
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("Attempted to remove last when there is no last");
		}
		
		E result = tail.data;
		//If the size is one head and tail are the same and are removed by setting them to null
		if(size == 1){
			head = null;
			tail = null;
		} else {
			tail = tail.previous;
			tail.next = null;
		}
		size--;
		
		return result;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Attempted to remove element at index " + index);
		}
		
		if(index == 0){
			return removeFirst();
		}
		
		if(index == size - 1){
			return removeLast();
		}
		
		Node<E> current = iterate(index);
		E result = current.data;
		
		current.previous.next = current.next;
		current.next.previous = current.previous;
		current = null;
		
		size--;
		
		return result;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(E element) {
		//If the list is empty return -1
		if(size == 0){
			return -1;
		}
		
		Node<E> current = head;
		for(int index = 0; index < size; index++){
			//If the data in the node at the current index we're done
			if(current.data.equals(element)){
				return index;
			}
			
			//get the next node
			current = current.next;
		}
		
		//If we get to this point the item has not been found
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element) {
		if(size == 0){
			return -1;
		}
		
		Node<E> current = tail;
		for(int index = size - 1; index >= 0; index--){
			if(current.data.equals(element)){
				return index;
			}
			
			current = current.previous;
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
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
		
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		
		Node<E> current = head;
		for(int index = 0; index < size; index++){
			result[index] = current.data;
			current = current.next;
		}
		
		return result;
	}

	/**
	 * Returns an iterator for this linked list. The iterator is nonbinding and any 
	 * changes to this LinkedList will void he contract of the iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>(){

			private int nextIndex;
			private boolean canRemove;
			private Node<E> currentNode;
			private Node<E> previousNode;
			
			{
				nextIndex = 0;
				canRemove = false;
				currentNode = head;
				previousNode = null;
			}
			
			
			@Override
			public boolean hasNext() {
				return nextIndex < size;
			}

			@Override
			public E next() {
				if(nextIndex >= size){
					throw new NoSuchElementException("Has no next");
				}
				
				E result = currentNode.data;
				previousNode = currentNode;
				currentNode = currentNode.next;
				nextIndex++;
				canRemove = true;
				
				return result;
			}
			
			@Override
			public void remove(){
				if(!canRemove){
					throw new IllegalStateException("Cannot remove until a call to next is made");
				}
				
				if(size == 1){
					head = null;
					tail = null;
					size--;
					nextIndex--;
					canRemove = false;
					return;
				}
				
				if(nextIndex == 1){
					head = currentNode;
					head.previous = null;
					size --;
					nextIndex--;
					canRemove = false;
					return;
				}
				
				if(nextIndex == size){
					tail = previousNode.previous;
					tail.next = null;
					size--;
					nextIndex--;
					canRemove = false;
					return;
				}
				
				previousNode.previous.next = currentNode;
				currentNode.previous = previousNode.previous;
				size--;
				nextIndex--;
				canRemove = false;	
			}
			
		};
	}
	
	/**
	 * Private method that iterates through the Linked List from the ideal direction
	 * @param index - The index of the desired node.
	 * @return The node at index.
	 */
	private Node<E> iterate(int index){
		if(index < (size/2) - 1){
			
			Node<E> current = head;
			
			for(int stepCount = 0; stepCount < index; stepCount++){
				current = current.next;
			}
			
			return current;
		}
		
		Node<E> current = tail;
		
		for(int stepCount = size - 1; stepCount > index; stepCount--){
			current = current.previous;
		}
		
		return current;

	}
	
	
	/**
	 * Private wrapper class that holds a reference to the data stored in the node as
	 * well as pointers to the next and previous nodes in the DoublyLinkedList
	 * @author Jared Nielson
	 *
	 * @param <T> - The type of data stored in the node
	 */
	private class Node<T>{
		private Node<T> previous;
		private Node<T> next;
		private T data;
		
		public Node(T data){
			previous = null;
			next = null;
			this.data = data;
		}
		
	}
	
}











