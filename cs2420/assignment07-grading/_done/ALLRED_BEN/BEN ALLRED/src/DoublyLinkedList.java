package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Benjamin Allred u1090524
 * 
 *
 * @param <E> generic type
 */
public class DoublyLinkedList<E> implements List<E>, Iterable{

	protected Node<E> start, end;
	private int size;

	/**
	 * zero parameter constructor that does nothing
	 */
	public DoublyLinkedList()
	{

	}

	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	public void addFirst(E element)
	{
		if(start == null)
		{
			start = new Node<E>(element, null, null);
			end = start;
		}
		else
		{
			Node<E> tempNode = start;
			start = new Node<E>(element, tempNode, null);
			tempNode.prev = start;
		}
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(E o)
	{
		if(end == null)
		{
			start = new Node<E>(o, null, null);
			end = start;
		}
		else
		{
			Node<E> tempNode = end;
			end = new Node<E>(o, null, tempNode);
			tempNode.next = end;
			end.prev = tempNode;
		}
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException
	{
		//throw exception if invalid index
		if(index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		//if list is null make new element start and end
		else if(start == null)
		{
			start = new Node<E>(element, null, null);
			end = start;
			size++;
		}
		//if index is 0 add replace start
		else if(index == 0)
		{
			addFirst(element);
		}
		//if index equals size replace end
		else if(index == size)
		{
			addLast(element);
		}
		//find correct index and insert node

		//go from start to size /2
		else if(index <= (size / 2))
		{
			Node<E> tempNode = start;
			for(int curInd = 0; curInd < index; curInd++)
			{
				tempNode = tempNode.next;
			}
			Node<E> prevNode = tempNode.prev;
			Node<E> newNode = new Node<E>(element, tempNode, tempNode.prev);
			prevNode.next = newNode;
			tempNode.prev = newNode;
			size++;
		}

		//go from end to size/2
		else
		{
			Node<E> tempNode = end;
			for(int curInd = size - 1; curInd > index; curInd--)
			{
				tempNode = tempNode.prev;
			}
			Node<E> prevNode = tempNode.prev;
			Node<E> newNode = new Node<E>(element, tempNode, tempNode.prev);
			prevNode.next = newNode;
			tempNode.prev = newNode;
			size++;
		}
	}

	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException
	{
		if(start != null)
		{
			return start.data;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException
	{
		if(end != null)
		{
			return end.data;
		}
		else
		{
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0)
		{
			return start.data;
		}
		else if(index == size - 1)
		{
			return end.data;
		}
		//from start to size/2
		else if(index <= (size / 2))
		{
			Node<E> tempNode = start;
			for(int curInd = 0; curInd < index; curInd++)
			{
				tempNode = tempNode.next;
			}
			return tempNode.data;
		}
		//from end to size/2
		else
		{
			Node<E> tempNode = end;
			for(int curInd = size - 1; curInd > index; curInd--)
			{
				tempNode = tempNode.prev;
			}
			return tempNode.data;
		}

	}

	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException
	{
		if(start == null)
		{
			throw new NoSuchElementException();
		}
		else if(size == 1)
		{
			Node<E> tempNode = start;
			clear();
			return tempNode.data;
		}
		else
		{
			Node<E> tempNode = start;
			start = start.next;
			start.prev = null;
			size--;
			return tempNode.data;
		}
	}

	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException
	{
		if(start == null)
		{
			throw new NoSuchElementException();
		}
		else if(size == 1)
		{
			Node<E> tempNode = end;
			clear();
			return tempNode.data;
		}
		else
		{
			Node<E> tempNode = end;
			end = end.prev;
			end.next = null;
			size--;
			return tempNode.data;
		}
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0)
		{
			return removeFirst();
		}
		else if(index == size - 1)
		{
			return removeLast();
		}
		//from start to size/2
		else if(index <= (size/2))
		{
			Node<E> tempNode = start;
			for(int curInd = 0; curInd < index; curInd++)
			{
				tempNode = tempNode.next;
			}
			Node<E> prevNode = tempNode.prev;
			Node<E> nextNode = tempNode.next;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			size--;
			return tempNode.data;
		}
		//from end to size/2
		else
		{
			Node<E> tempNode = end;
			for(int curInd = size - 1; curInd > index; curInd--)
			{
				tempNode = tempNode.prev;
			}
			Node<E> prevNode = tempNode.prev;
			Node<E> nextNode = tempNode.next;
			prevNode.next = nextNode;
			nextNode.prev = prevNode;
			size--;
			return tempNode.data;
		}
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(E element)
	{
		Node<E> tempNode = start;
		for(int curInd = 0; curInd < size; curInd++)
		{
			if(tempNode.data.equals(element))
			{
				return curInd;
			}
			tempNode = tempNode.next;		
		}
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element)
	{
		int lastIndexOf = -1;
		Node<E> tempNode = start;
		for(int curInd = 0; curInd < size; curInd++)
		{
			if(tempNode.data.equals(element))
			{
				lastIndexOf = curInd;
			}
			tempNode = tempNode.next;
		}
		return lastIndexOf;
	}

	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public boolean isEmpty()
	{
		if(start == null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear()
	{
		start = null;
		end = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray()
	{
		Object[] array = new Object[size];
		Node<E> tempNode = start;
		for(int curInd = 0; curInd < size; curInd++)
		{
			array[curInd] = tempNode.data;
			if(tempNode.next != null)
			{
				tempNode = tempNode.next;
			}
		}
		return array;
	}

	/**
	 * returns an iterator for DoublyLinkedList
	 */
	@Override
	public Iterator iterator() {

		/**
		 * 
		 * @author Benjamin Allred u1090524
		 *
		 */
		class ListIterator implements Iterator<E>{

			private Node<E> node;

			/**
			 * determines if there is a next element
			 */
			@Override
			public boolean hasNext() {
				if(node == null && start != null)
				{
					return true;
				}
				else if(node.next != null)
				{
					return true;
				}
				return false;
			}

			/**
			 * returns the next element and continues iterating
			 */
			@Override
			public E next() throws NoSuchElementException{
				if(node == null && start != null)
				{
					node = start;
					return node.data;
				}
				else if(hasNext())
				{
					node = node.next;
					return node.data;
				}
				else
				{
					throw new NoSuchElementException();
				}
			}

			/**
			 * removes the last returned element from the list
			 */
			@Override
			public void remove() throws NoSuchElementException{
				if(node == null)
				{
					throw new NoSuchElementException();
				}
				else
				{
					Node<E> temp = node.prev;
					temp.next = node.next;
					Node<E> tempTwo = node.next;
					tempTwo.prev = temp;
					node = temp;
				}
			}
		}
		return new ListIterator();
	}

}
