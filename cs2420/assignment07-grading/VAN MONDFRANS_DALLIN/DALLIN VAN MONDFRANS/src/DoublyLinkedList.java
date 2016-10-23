/**
 * Assignment 6 - DoublyLinkedList
 * @author Dallin Van Mondfrans
 * uID: u0717113
 * Date: October 5, 2016
 */

package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	/**
	 * Default constructor for DoublyLinkeList<E>. Will initialize the list.
	 */
	public DoublyLinkedList() {
		this.head = null;
		this.tail = head;
		this.size = 0;
	}
	
	/**
	 * {@inheritDoc};
	 */
	@Override
	public void addFirst(E element) {
		
		Node<E> el = new Node<E>(element);
		
		if( size == 0 ) {
			head = el;
			tail = el;
		} else {
			head.previous = el;
			el.next = head;
			head = el;
		}
		size++;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public void addLast(E o) {
		
		Node<E> el = new Node<E>(o);
		
		if( size == 0 ) {
			head = el;
			tail = el;
		} else {
			tail.next = el;
			el.previous = tail;
			tail = el;
		}
		size++;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		
		if( size == 0 || index == 0) {
			addFirst(element);
			return;
		} else if( index == size ) {
			addLast(element);
			return;
		}
		
		Node<E> el = new Node<E>(element);
		Node<E> currentElement = getNode(index);
		
		currentElement.previous.next = el;
		el.previous = currentElement.previous;
		currentElement.previous = el;
		el.next = currentElement;
		size++;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if( size == 0 ) {
			throw new NoSuchElementException();
		}
		return head.data;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size == 0) {
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if( index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> element = getNode(index);
		return element.data;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if( size == 0 ) {
			throw new NoSuchElementException();
		} else if( size == 1 ) {
			Node<E> temp = head;
			head = null;
			tail = null;
			size--;
			return temp.data;
		}
		Node<E> temp = head;
		head = head.next;
		head.previous = null;
		size--;
		return temp.data;
		
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if( size == 0 ) {
			throw new NoSuchElementException();
		} else if( size == 1 ) {
			Node<E> temp = head;
			head = null;
			tail = null;
			size--;
			return temp.data;
		}
		Node<E> temp = tail;
		tail = tail.previous;
		tail.next = null;
		size--;
		return temp.data;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		E tempData = null;
		if( size == 0 || index > size-1 ) {
			throw new NoSuchElementException();
		} else if(index == 0) {
			tempData = (E) removeFirst();
			return tempData;
		} else if(index == size-1) {
			tempData = (E) removeLast();
			return tempData;
		}
		
		Node<E> temp = getNode(index);
		temp.previous.next = temp.next;
		temp.next.previous = temp.previous;
		
		size--;
		return temp.data;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public int indexOf(E element) {
		Node<E> currentElement = head;
		int result = -1;
		for(int i = 0; i < size; i++) {
			if(currentElement.data.equals(element)) {
				result =  i;
				break;
			} else if(currentElement.next == null) {
				break;
			}
			currentElement = currentElement.next;
		}
		return result;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public int lastIndexOf(E element) {
		Node<E> currentElement = head;
		int result = -1;
		for(int i = 0; i < size; i++) {
			if(currentElement.data.equals(element)) {
				result =  i;
			} else if(currentElement.next == null) {
				break;
			}
			currentElement = currentElement.next;
		}
		return result;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public boolean isEmpty() {
		if( size == 0 && head == null ) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		
		for(int index = 0; index < size; index++) {
			array[index] = get(index);
		}
		return array;
	}
	
	/**
	 * This is a private method that is used only inside of the DoublyLinkedList class. It
	 * is used to get the Node at a specific index, and not just the data value that it contains.
	 * @param index - the index of the Node you want returned
	 * @return Node at the desired index location
	 */
	private Node<E> getNode(int index) {
		Node<E> currentElement = head;
		if( index < size/2 ) { // Start from head and work forwards
			for(int i = 0; i < index; i++) {
				currentElement = currentElement.next;
			}
		} else { // Start from tail and work backwards
			currentElement = tail;
			for(int i = size-1; i > index; i--) {
				currentElement = currentElement.previous;
			}
		}
		return currentElement;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			
			int curser = 0;
			boolean canRemove = false;
			
			/**
			 * {@inheritDoc};
			 */
			@Override
			public boolean hasNext() {
				return curser < size;
			}

			/**
			 * {@inheritDoc};
			 */
			@Override
			public E next() throws NoSuchElementException {
				try {
					E temp = (E) get(curser);
					curser++;
					canRemove = true;
					return temp;
				} catch(Exception e) {
					throw new NoSuchElementException();
				}
			}
			
			/**
			 * {@inheritDoc};
			 */
			@Override
			public void remove() throws IllegalStateException {
				if( !canRemove ) {
					throw new IllegalStateException();
				} else {
					try {
						DoublyLinkedList.this.remove(curser-1);
						canRemove = false;
						return;
					} catch(Exception e) {
						return;
					}
					
				}
			}
			
		};
	}
	
}
