package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The DoublyLinkedList class is a data structure representing a list where items are linked the the
 * item immediately preceeding and after the current item. The references to the start and end of the list are stored
 * as variables. Any other item must be found by traversing the links. 
 * 
 * @author Calvin Kling
 *
 * @param <E> - Generic object parameter.
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private Node head, tail, current, previous;
	private int size;
	
	/**
	 * Constructs an empty doubly linked list
	 */
	public DoublyLinkedList() 
	{
		
		size = 0;
		head = null;
		tail = null;
		current = null;
		previous = null;
	}

	@Override
	public Iterator<E> iterator() 
	{
		return new LinkedListIterator();
	}

	@Override
	public void addFirst(E element) 
	{
		
		if(size == 0)
		{
			head = new Node(element);
			tail = head;
			size++;
			return;
		}
		
		current = head;
		head = new Node(element);
		head.next = current;
		current.last = head;
		if (size == 1)
			tail = current;
		size++;
	}

	@Override
	public void addLast(E o) 
	{
		
		if(size == 0)
		{
			head = new Node(o);
			tail = head;
			size++;
			return;
		}
		
		current = tail;
		tail = new Node(o);
		tail.last = current;
		current.next = tail;
		size++;
		
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException 
	{
		
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		if(index == 0)
		{
			addFirst(element);
			return;
		}
		
		else if(index == size)
		{
			addLast(element);
			return;
		}
		
		
		if(index < size/2)
		{
			current = head;
			int newIndex = index;
			
			while(newIndex != 0)
			{
				current = current.next;
				newIndex--;
			}
			
			previous = current.last;
			current.last = new Node(element);
			previous.next = current.last;
			current.last.next = current;
			previous.next.last = previous;
			size++;
			
			return;
		}
		
		int backTrackIndex = size - index;
		current = tail;
		
		while(backTrackIndex != 0)
		{
			current = current.last;
			backTrackIndex--;
		}
		
		previous = current.last;
		current.last = new Node(element);
		previous.next = current.last;
		current.last.next = current;
		previous.next.last = previous;
		size++;
	}

	@Override
	public E getFirst() throws NoSuchElementException 
	{
		
		if(size == 0)
			throw new NoSuchElementException();
		
		return head.data;
	}

	@Override
	public E getLast() throws NoSuchElementException 
	{

		if(size == 0)
			throw new NoSuchElementException();
		
		return tail.data;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException 
	{

		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		if(index == 0)
			return getFirst();
		
		else if(index == (size-1))
			return getLast();
		
		if(index < size/2)
		{
			current = head;
			int newIndex = index;
			
			while(newIndex != 0)
			{
				current = current.next;
				newIndex--;
			}
			
			return current.data;
		}
		
		int backTrackIndex = (size - 1) - index;
		current = tail;
		
		while(backTrackIndex != 0)
		{
			current = current.last;
			backTrackIndex--;
		}
		
		return current.data;
	}

	@Override
	public E removeFirst() throws NoSuchElementException 
	{
		
		if(size == 0)
			throw new NoSuchElementException();
		
		E element = head.data;
		
		if(size == 1)
		{
			head = null;
			tail = null;
			size--;
			return element;
		}
		
		head = head.next;
		head.last = null;
		size--;
		
		return element;
	}

	@Override
	public E removeLast() throws NoSuchElementException 
	{
		
		if(size == 0)
			throw new NoSuchElementException();
		
		E element = head.data;
		
		if(size == 1)
		{
			head = null;
			tail = null;
			size--;
			return element;
		}
		
		tail = tail.last;
		tail.next = null;
		size--;
		
		return element;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException 
	{
		
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		if(index == 0)
			return removeFirst();
		
		else if(index == (size -1))
			return removeLast();
		
		E element;
		if(index < size/2)
		{
			current = head;
			int newIndex = index;
			
			while(newIndex != 0)
			{
				current = current.next;
				newIndex--;
			}
			
			element = current.data;
			previous = current.last;
			current = current.next;
			previous.next = current;
			current.last = previous;
			size--;
			
			return element;
		}
		
		int backTrackIndex = (size - 1) - index;
		current = tail;
		
		while(backTrackIndex != 0)
		{
			current = current.last;
			backTrackIndex--;
		}
		
		element = current.data;
		previous = current.last;
		current = current.next;
		previous.next = current;
		current.last = previous;
		size--;
		
		return element;
	}

	@Override
	public int indexOf(E element) 
	{

		if(size == 0)
			return -1;
		
		current = head;
		int index = 0;
    	
    	while(current != null && !current.data.equals(element))
    	{
    		current = current.next;
    		index++;
    	}
    	
    	if(current == null)
    		return -1;
		
    	return index;
	}

	@Override
	public int lastIndexOf(E element) 
	{
		
		if(size == 0)
			return -1;
		
		current = head;
		int index = 0, lastIndex = -1;
    	
    	while(current != null)
    	{
    		if(current.data.equals(element))
    			lastIndex = index;
    		current = current.next;
    		index++;
    	}
		
    	return lastIndex;
	}

	@Override
	public int size() 
	{

		return size;
	}

	@Override
	public boolean isEmpty() 
	{
		
		if(size == 0)
			return true;
		
		return false;
	}

	@Override
	public void clear() 
	{
		head = null;
		tail = null;
		current = null;
		size = 0;
	}

	@Override
	public Object[] toArray() 
	{

		current = head;
		Object[] listArray = new Object[size];
		
		for(int counter = 0; counter < size; counter++)
		{
			listArray[counter] = current.data;
			current = current.next;
		}
		
		return listArray;
	}
	
	/**
	 * A basic node class for use in a doubly linked list.
	 * 
	 * @author Calvin Kling
	 *
	 */
	private class Node
    {
        private final E data;   // The data element - cannot be changed after it is assigned
        private Node next;      // Initialized to null when this object is created
        private Node last;		//Initialized to null when this object is created
        
        /**
         * Builds this node to contain the specified data.  By default, this
         * node does not point to any other nodes, although it
         * is expected that 'next' and 'last' may change.  
         * 
         * The data reference cannot be changed.
         * 
         * @param data   the data to store in the node
         */
        Node (E data)
        {
            this.data = data;
        }
    }
	
	/**
	 * Iterator for the DoublyLinkedList class.
	 * 
	 * @author Calvin Kling
	 *
	 */
	private class LinkedListIterator implements Iterator<E> {

		private Node pointer, last;
		
		private LinkedListIterator() 
		{
			pointer = null;
			last = null;
		}

		@Override
		public boolean hasNext() 
		{
			return !isEmpty() && pointer != tail;
		}

		@Override
		public E next() 
		{
			if(head == null)
				throw new NoSuchElementException();
			
			else if(pointer == null)
			{
				pointer = head;
				return pointer.data;
			}
			
			if(pointer.next == null)
				throw new NoSuchElementException();
			
			pointer = pointer.next;
			
			return pointer.data;
		}

		@Override
		public void remove()
		{
			last = pointer.last;
			pointer = pointer.next;
			
			last.next = pointer;
			pointer.last = last;
			pointer = last;
			
			size--;
		}
	}
}
