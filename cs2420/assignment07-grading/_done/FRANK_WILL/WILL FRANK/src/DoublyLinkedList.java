//William Frank
//u1067292
package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E>{

	private Node firstItem;
	private Node lastItem;
	
	private int size;

	/**
	 * Creates an empty DoublyLinkedList
	 */
	public DoublyLinkedList()
	{
		firstItem = null;
		lastItem = null;
		size = 0;
	}

	/**
	 * Inserts the specified element at the beginning of the list
	 * @param element the element to be added
	 */
	@Override
	public void addFirst(E element) {

		Node newNode = new Node(element);


		if (firstItem == null)
		{
			firstItem = newNode;
			lastItem = newNode;
		}
		else
		{
			firstItem.setPreviousNode(newNode);
			newNode.setNextNode(firstItem);
			firstItem = newNode;
		}
		
		size++;

	}

	/**
	 * Inserts the specified element at the end of the list.
	 * @param element the element to be added
	 */
	@Override
	public void addLast(E o) {

		Node newNode = new Node(o);


		if (lastItem == null)
		{
			firstItem = newNode;
			lastItem = newNode;
		}
		else
		{
			lastItem.setNextNode(newNode);
			newNode.setPreviousNode(lastItem);
			lastItem = newNode;
		}
		
		size++;

	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size()) 
	 * @param index the position of the new element to be inserted
	 * @param element the value to be inserted into the list
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		
		if (index > size || index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if (size < 1 || index < 1)
		{
			addFirst(element);
			return;
		}
		
		if (index == size)
		{
			addLast(element);
			return;
		}

		Node newNode = new Node(element);

		Node current = this.firstItem;

		for (int i = 0; i < index-1; i++)
		{
				current = current.getNextNode();
		}
		
		Node next = current.getNextNode();
		
		current.setNextNode(newNode);
		next.setPreviousNode(newNode);
		
		newNode.setNextNode(next);
		newNode.setPreviousNode(current);

		size++;

	}

	/**
	 * Returns the first element in the list
	 * @return the first element in the list
	 * @throws NoSuchElementException is thrown if there is no value in the beginning of the list (should only occur if the list is empty)
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		
		if (firstItem == null)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return (E) firstItem.getNodeValue();
		}
		
	}

	/**
	 * Returns the last element in the list
	 * @return the last element in the list
	 * @throws NoSuchElementException is thrown if there is no value in the end of the list (should only occur if the list is empty)
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		
		if (lastItem == null)
		{
			throw new NoSuchElementException();
		}
		else
		{
			return (E) lastItem.getNodeValue();
		}
	}

	/**
	 * Returns the element at the specified position in the list.
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * @return the item at the given index
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if (size < 1 || index < 1)
		{
			return getFirst();
		}
		
		if (index == size)
		{
			return getLast();
		}
		
		Node current = firstItem;

		for (int i = 0; i < index; i++)
		{
				current = current.getNextNode();
		}

		return (E) current.getNodeValue();
	}

	/**
	 * Removes and returns the first element from the list.
	 * @throws NoSuchElementException if the list is empty.
	 * @return the item that is removed
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		
		if (firstItem == null)
		{
			throw new NoSuchElementException();
		}
		
		Node removed = firstItem;
		
		if (size == 1)
		{
			this.clear();
		}
		else
		{
			firstItem.setPreviousNode(null);
			firstItem = firstItem.getNextNode();
			size--;
		}

		
		return (E) removed.getNodeValue();
	}

	/**
	 * Removes and returns the last element from the list.
	 * @throws NoSuchElementException if the list is empty.
	 * @return the item that is removed.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {

		if (lastItem == null)
		{
			throw new NoSuchElementException();
		}

		Node removed = lastItem;
		
		if (size == 1)
		{
			this.clear();
		}
		else
		{
			lastItem = lastItem.getPreviousNode();
			lastItem.setNextNode(null);
			size--;
		}
		
		return (E) removed.getNodeValue();
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * @throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * @param index the index of the item to be removed
	 * @return the item that was removed
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		
		if (size <= 1 || index < 1)
		{
			return removeFirst();
		}
		
		if (index == size-1)
		{
			return removeLast();
		}
		
		Node current = firstItem;

		for (int i = 0; i < index; i++)
		{
			current = current.getNextNode();
		}
		
		Node previous = current.getPreviousNode();
		Node next = current.getNextNode();
		
		previous.setNextNode(next);
		next.setPreviousNode(previous);

		size--;
		
		return (E) current.getNodeValue();
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * @param element the value to search for
	 * @return the position of element, or -1 if it is not found
	 */
	@Override
	public int indexOf(E element) {
		
		int index = 0; 
		for (Node current = firstItem; current != null; current = current.getNextNode())
		{
			if (current.getNodeValue().equals(element))
			{
				return index;
			}
			index++;
		}
		
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * @param element the element to search for
	 * @return the index of the last occurrence of the given element, or -1 if not found
	 */
	@Override
	public int lastIndexOf(E element) {
		
		int index = 0;
		
		for (Node current = firstItem; current.getPreviousNode() != null; current = current.getPreviousNode())
		{
			if (current.getNodeValue().equals(element))
			{
				return index;
			}
			index++;
		}
		
		return -1;
	}

	/**
	 * @return the number of elements in this list.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return true if this list contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		firstItem = null;
		lastItem = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * @return an array containing all elements of the list in order
	 */
	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		
		int index = 0;
		
		for (Iterator<E> iterator = this.iterator(); iterator.hasNext();) {
			E value = (E) iterator.next();
			
			array[index] = value;
			index++;
		}
		
		return array;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}
	
	
	private class DoublyLinkedListIterator implements Iterator<E>{
		
		Node current;
		
		public DoublyLinkedListIterator() {
			current = null;
		}

		@Override
		public boolean hasNext() {
			if (size == 0)
			{
				return false;
			}
			if (current == null)
			{
				return true;
			}
			else
			{
				return (current.getNextNode() != null);
			}
		}

		@Override
		public E next() {
			if (current == null)
			{
				current = firstItem;
			}
			else
			{
				current = current.getNextNode();
			}
			
			return (E) current.getNodeValue();
		}
		
		@Override
		public void remove() {
			Node previous = current.getPreviousNode();
			Node next = current.getNextNode();
			
			if (previous != null)
			{
				previous.setNextNode(next);
			}
			
			if (next != null)
			{
				next.setPreviousNode(previous);
			}
			
			size--;
			
		}
		
	}

}
