//Cole Cruz
package assignment07;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Doubly linked list for any type.
 * 
 * @author Cole Cruz
 *
 * @param <E>
 *            -- list type.
 */
public class DoublyLinkedList<E> implements Iterable<E>, List<E> {

	private Iterator<E> iterator = new ListIterator<E>(this);
	private ListNode<E> list;

	/**
	 * Creates a doubly linked list for any type.
	 */
	public DoublyLinkedList() {
		list = null;
	}

	/**
	 * Inserts the specified element at the beginning of the list. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @param element
	 *            -- element to add.
	 */
	@Override
	public void addFirst(E element) {
		if (isEmpty()) {
			list = new ListNode<E>(element);
		} else {
			ListNode<E> nodeToAdd = new ListNode<E>(element);
			list.setPrevious(nodeToAdd);
			nodeToAdd.setNext(list);
			list = nodeToAdd;
		}
	}

	/**
	 * Inserts the specified element at the end of the list. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @param o
	 *            -- element to add.
	 */
	@Override
	public void addLast(E o) {
		if (isEmpty()) {
			list = new ListNode<E>(o);
		} else {
			ListNode<E> current = list;
			while (current.getNext() != null) {
				current = current.getNext();
			}
			ListNode<E> nodeToAdd = new ListNode<E>(o);
			current.setNext(nodeToAdd);
			nodeToAdd.setPrevious(current);
		}
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) 
	 * O(N) for a doubly-linked list.
	 * 
	 * @param index
	 *            -- index to add the element.
	 * @param element
	 *            -- element to add.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if ((index < 0) || (index > size())) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			addFirst(element);
		} else if (index == size()) {
			addLast(element);
		} else {
			ListNode<E> current = list;
			for (int count = 1; count <= index; count++) {
				current = current.getNext();
			}
			ListNode<E> nodeToAdd = new ListNode<E>(element);
			nodeToAdd.setPrevious(current.getPrevious());
			current.getPrevious().setNext(nodeToAdd);
			nodeToAdd.setNext(current);
			current.setPrevious(nodeToAdd);
		}
	}

	/**
	 * Returns the first element in the list. 
	 * Throws NoSuchElementException if the list is empty. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @return the first element in the list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return list.getData();
	}

	/**
	 * Returns the last element in the list. 
	 * Throws NoSuchElementException if the list is empty. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @return the last element in the list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		ListNode<E> current = list;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		return current.getData();
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || 
	 * index >= size()) 
	 * O(N) for a doubly-linked list.
	 * 
	 * @param index
	 *            -- index of the element to return.
	 * 
	 * @return the element at the specified position in the list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return getFirst();
		} else if (index == size() - 1) {
			return getLast();
		} else {
			ListNode<E> current = list;
			current = current.getNext();
			for (int currentIndex = 1; currentIndex < size() - 1; currentIndex++) {
				if (currentIndex == index) {
					break;
				}
				current = current.getNext();
			}
			return current.getData();
		}
	}

	/**
	 * Removes and returns the first element from the list. 
	 * Throws NoSuchElementException if the list is empty. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @return the first element from the list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		ListNode<E> removedNode = list;
		list = list.getNext();
		if(list != null) {
			list.getPrevious().setNext(null);
			list.setPrevious(null);
		}
		return removedNode.getData();
	}

	/**
	 * Removes and returns the last element from the list. 
	 * Throws NoSuchElementException if the list is empty. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @return the last element from the list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		if(size() == 1) {
			removeFirst();
		}
		ListNode<E> current = list;
		while (current.getNext() != null) {
			current = current.getNext();
		}
		ListNode<E> removedNode = current;
		current.getPrevious().setNext(null);
		current.setPrevious(null);
		return removedNode.getData();
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size()) 
	 * O(N) for a doubly-linked list.
	 * 
	 * @param index
	 *            -- index of element to remove.
	 * 
	 * @return the element at the specified position in the list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if ((index < 0) || (index >= size())) {
			throw new IndexOutOfBoundsException();
		} else if (index == 0) {
			return removeFirst();
		} else if (index == size() - 1) {
			return removeLast();
		} else {
			ListNode<E> current = list;
			for (int count = 1; count < index; count++) {
				current = current.getNext();
			}
			ListNode<E> removedNode = current.getNext();
			current.setNext(removedNode.getNext());
			removedNode.getNext().setPrevious(current);
			removedNode.setNext(null);
			removedNode.setPrevious(null);
			return removedNode.getData();
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. 
	 * O(N) for a doubly-linked list.
	 * 
	 * @param element
	 *            -- element to find index of.
	 * 
	 * @return the index of the first occurrence of the specified element in the
	 *         list, or -1 if this list does not contain the element.
	 */
	@Override
	public int indexOf(E element) {
		ListNode<E> current = list;
		for (int index = 0; index < size(); index++) {
			if (current.getData().equals(element)) {
				return index;
			}
			current = current.getNext();
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. 
	 * O(N) for a doubly-linked list.
	 * 
	 * @param element
	 *            -- element to find index of.
	 * 
	 * @return the index of the last occurrence of the specified element in this
	 *         list, or -1 if this list does not contain the element.
	 */
	@Override
	public int lastIndexOf(E element) {
		int lastIndex = -1;
		ListNode<E> current = list;
		for (int index = 0; index < size(); index++) {
			if (current.getData().equals(element)) {
				lastIndex = index;
			}
			current = current.getNext();
		}
		return lastIndex;
	}

	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 * 
	 * @return the number of elements in this list.
	 */
	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		}
		int size = 1;
		ListNode<E> current;
		current = list;
		while (current.getNext() != null) {
			current = current.getNext();
			size++;
		}
		return size;
	}

	/**
	 * Returns true if this collection contains no elements. 
	 * O(1) for a doubly-linked list.
	 * 
	 * @return true if this collection contains no elements, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		if (list == null) {
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
		list = null;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). 
	 * O(N) for a doubly-linked list.
	 * 
	 * @return an array containing all of the elements in this list in proper
	 *         sequence (from first to last element).
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size()];
		ListNode<E> current = list;
		for (int index = 0; index < array.length; index++) {
			array[index] = current.getData();
			current = current.getNext();
		}
		return array;
	}

	/**
	 * Returns an iterator over elements of type E.
	 * 
	 * @return an iterator.
	 */
	@Override
	public Iterator<E> iterator() {
		return iterator;
	}

}
