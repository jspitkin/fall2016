package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class creates a doubly linked list.
 * 
 * @author Makenzie Peacock (u0873188)
 * 
 * @param <E>
 *            -- the type of elements contained in the list.
 */
public class DoublyLinkedList<E> implements List<E> {
	private ListNode _head;
	private ListNode _tail;
	private int _size;

	/**
	 * Creates the doubly linked list. The default constructor.
	 */
	public DoublyLinkedList(){
		_head = new ListNode(null, null, null);
		_tail = new ListNode(null, null, null);
		_size = 0;

		_head.setNext(_tail);
		_tail.setPrevious(_head);
	}

	/**
	 * Creates a node for the doubly linked list.
	 */
	private class ListNode{
		private E _value;
		private ListNode _next;
		private ListNode _previous;

		public ListNode(E value, ListNode next, ListNode previous){
			_value = value;
			_next = next;
			_previous = previous;
		}

		public E getValue(){
			return  _value;
		}

		@SuppressWarnings("unused")
		public void setValue(E value){
			_value = value;
		}

		public ListNode getNext(){
			return _next;
		}

		public void setNext(ListNode next){
			_next = next;
		}

		public ListNode getPrevious(){
			return _previous;
		}

		public void setPrevious(ListNode previous){
			_previous = previous;
		}

	}

	/**
	 * creates an iterator.
	 */
	public Iterator<E> iterator(){
		Iterator<E> iterator = new Iterator<E>(){
			private ListNode currentNode = null;
			int indexCounter = 0;


			@Override
			public boolean hasNext() {
				if(indexCounter < _size){
					return currentNode.getNext() != null;
				}
				else
					return _size > 0;

			}

			@SuppressWarnings("unchecked")
			@Override
			public E next() {
				if(indexCounter > 0){
					currentNode = currentNode._next;
				}
				else{
					currentNode = _head;
				}
				indexCounter++;
				return (E) currentNode;		
			}

			@Override
			public void remove(){
				ListNode previousNode = currentNode.getPrevious();
				ListNode nextNode = currentNode.getNext();

				if (previousNode != null){
					previousNode.setNext(nextNode);
				}
				if(nextNode != null){
					nextNode.setPrevious(previousNode);
				}
			}
		};
		return iterator;

	}


	/**
	 * Inserts the specified element at the beginning of the list.
	 * 
	 * @param element - a generic value.
	 */
	@Override
	public void addFirst(E element) {
		ListNode node = new ListNode(element, _head, null);

		if(_size > 0)
			_head.setPrevious(node);
		_head = node;

		if(_size == 0)
			_tail = _head;
		_size++;
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * 
	 * @param element - a generic value.
	 */
	@Override
	public void addLast(E element) {
		ListNode node = new ListNode(element, null, _tail);

		if(_size > 0)
			_tail.setNext(node);
		_tail = node;

		if(_size == 0)
			_head = _tail;
		_size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws an IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * 
	 * @param index - an integer.
	 * @param element - a generic value.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		ListNode tempNode = _head;
		ListNode node = new ListNode(element, null, null);
		int counter = 0;

		if (index < 0 || index > _size)
			throw new IndexOutOfBoundsException();
		else {
			if(index == 0)
				this.addFirst(element);
			else if(index == _size - 1)
				this.addLast(element);
			else{
				while(counter < index){
					tempNode = tempNode.getNext();
					counter++;
				}
				node.setNext(tempNode);
				tempNode.getPrevious().setNext(node);
				tempNode.setPrevious(node);
				_size++;
			}			
		}
	}

	/**
	 * Gets the first element.
	 * Throws a NoSuchElementException if the list is empty.
	 * 
	 * @return The first element in the list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(this.isEmpty())
			throw new NoSuchElementException();
		else
			return (E) _head.getValue();
	}

	/**
	 * Gets the last element
	 * Throws a NoSuchElementException if the list is empty.
	 * 
	 * @return The last element in the list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(this.isEmpty())
			throw new NoSuchElementException();
		else
			return (E) _tail.getValue();
	}

	/**
	 * Gets an element from the list.
	 * Throws an IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * 
	 * @param index - an integer.
	 * 
	 * @return The element at the specified position in the list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		ListNode tempNode = _head;
		int counter = 0;

		if(index < 0 || index >= _size)
			throw new IndexOutOfBoundsException();
		else{
			while(counter < index){
				tempNode = tempNode.getNext();
				counter++;
			}
		}
		return tempNode.getValue();
	}

	/**
	 * Removes the first element from the list.
	 * Throws a NoSuchElementException if the list is empty.
	 * 
	 * @return the first element from the list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		ListNode tempNode = null;

		if(this.isEmpty())
			throw new NoSuchElementException();
		else{
			tempNode = _head;
			_head = _head._next;
			_size--;
		}
		return (E) tempNode.getValue();
	}

	/**
	 * Removes the last element from the list.
	 * Throws a NoSuchElementException if the list is empty.
	 * 
	 * @return The last element from the list
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		ListNode tempNode = null;

		if(this.isEmpty())
			throw new NoSuchElementException();
		else{
			tempNode = _tail;
			_tail = _tail._previous;
			_tail.setNext(null);
			_size--;
		}
		return (E) tempNode.getValue();
	}

	/**
	 * Removes an element from the list. 
	 * Throws an IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * 
	 * @param element - an integer.
	 * 
	 * @returns The element at the specified position in the list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		ListNode tempNode = _head;

		int counter = 0;

		if (index < 0 || index >= _size)
			throw new IndexOutOfBoundsException();
		else{
			while(counter < index){
				tempNode = tempNode.getNext();
				counter++;
			}
			ListNode previousNode = tempNode.getPrevious();
			ListNode nextNode = tempNode.getNext();

			if (previousNode != null)
				previousNode.setNext(nextNode);
			if(nextNode != null)
				nextNode.setPrevious(previousNode);

			_size--;
		}
		return (E) tempNode.getValue();
	}

	/**
	 * Gets the first index of an element in the list 
	 * or -1 if this list does not contain the element.
	 * 
	 * @param element - a generic value.
	 *
	 * @return The index of the first occurrence of the specified element in the list.
	 */
	@Override
	public int indexOf(E element) {
		int result = -1;
		ListNode tempNode = _head;
		int index = 0;

		while(tempNode != null){
			if(tempNode.getValue() == element){
				result = index;
				break;
			}
			else{
				tempNode = tempNode.getNext();
				index++;
			}
		}
		return result;
	}

	/**
	 * Gets the last index of an element in the list.
	 * or -1 if this list does not contain the element.
	 * 
	 * @param element - a generic value.
	 *
	 * @return The index of the last occurrence of the specified element in this list
	 */
	@Override
	public int lastIndexOf(E element) {
		int result = -1;
		ListNode tempNode = _head;
		int index = 0;

		while(tempNode != null){
			if(tempNode.getValue() == element)
				result = index;
			tempNode = tempNode.getNext();
			index++;
		}
		return result;
	}

	/**
	 * Gets the size of the list
	 *
	 * @return The number of elements in this list.
	 */
	@Override
	public int size() {
		return _size;
	}

	/**
	 * Determines if the list is empty.
	 * 
	 * @return true if this collection contains no elements.
	 */
	@Override
	public boolean isEmpty() {
		return _size == 0;
	}

	/**
	 * Removes all elements from the list.
	 */
	@Override
	public void clear() {
		_head = new ListNode(null, null, null);
		_tail = new ListNode(null, null, null);
		_size = 0;
	}

	/**
	 * Converts the DoublyLinkedList to an array.
	 * 
	 * @return an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 */
	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray() {
		E[] result = (E[]) new Object[_size];

		ListNode tempNode = _head;

		for(int index = 0; index < _size; index++){
			result[index] = (E) tempNode.getValue();
			tempNode = tempNode.getNext();
		}
		return result;
	}
}