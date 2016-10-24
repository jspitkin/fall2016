package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.xml.soap.Node;
/**
 * 
 * @author Amir Mohsenian
 * u0737564
 *
 * @param <E>
 */
public class DoublyLinkedList <E> implements Iterable<E>, List <E>
{
	int value= 0;
	Node left, right;

	/**
	 * Nodes are used to put objects into DoubleyLinkedLists
	 * @author Amir
	 *
	 * @param <E>
	 */
	public static class Node<E>
	{
		E object1;
		Node<E> after, before;

		Node(Node<E> before, E object, Node<E>after)
		{
			this.before = before;
			this.object1 = object;
			this.after = after;

		}


	}
	/**
	 * Adds to the list first
	 */
	@Override
	public void addFirst(E element) 
	{

		Node<E>begin;
		begin=left;

		Node<E>begin2;
		begin2 = new Node<E>(null, element, begin);

		left = begin2;

		if(begin ==null)
		{
			right = begin2;
		}

		else
			if(begin != null)
			{
				begin.before = begin2;
			}

		value = value+1;

	}
	/**
	 * 
	 * Adds the last element
	 */
	@Override
	public void addLast(E o) 
	{
		Node<E>finish;
		finish =right;

		Node<E>finish2;
		finish2 = new Node<E>(finish, o, null);

		right =finish2;

		if(finish == null)
		{
			left = finish2;
		}
		else
		{
			finish.after = finish2;
		}

		value = value +1;


	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index<0)
		{
			throw new IndexOutOfBoundsException();
		}
		if (index>value)
		{
			throw new IndexOutOfBoundsException();
		}

		if (value == index)
		{
			this.addLast(element);
		}

		else
		{

			Node<E>main;
			main = RetrieveNode(index);

			Node<E>before;
			before = main.before;

			Node<E> node1;
			node1 = new Node<E>(before, element, main);

			main.before = node1;

			if(before==null)
			{
				left = node1;
			}
			else
				if(before!=null)
				{
					before.after = node1;
				}

			value = value+1;

		}

	}

	/**
	 * Gets the node from the list
	 * @param index
	 * @return
	 */
	private Node<E> RetrieveNode(int index)
	{
		Node<E> curr;

		if(index> value/2)
		{

			curr = right;


			int index1 = value-1;
			while(index1 >index)
			{

				curr = curr.before;
				index1--;
			}


		}

		else
		{
			curr = left;


			int index2 = 0;
			while(index2<index)
			{
				curr = curr.after;
				index2++;
			}

		}


		return curr;
	}

	/**
	 * gets first object
	 */
	@Override
	public E getFirst() throws NoSuchElementException 
	{
		if(value<0 || value==0)
		{

			throw new NoSuchElementException();
		}
		return (E) left.object1;
	}

	/**
	 * gets last object
	 */
	@Override
	public E getLast() throws NoSuchElementException 
	{
		if(value <0 || value == 0)
		{
			throw new NoSuchElementException();
		}
		// TODO Auto-generated method stub
		return (E) right.object1;
	}

	/**
	 * retrieves object
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException 
	{
		if (index<0)
		{
			throw new IndexOutOfBoundsException();
		}

		if (index > value || index==value)

		{
			throw new IndexOutOfBoundsException();
		}

		Node<E> curr;

		if(index > value / 2)
		{
			curr = right;

			int indexNum = value-1;
			while(indexNum > index)
			{
				curr = curr.before;
				indexNum = indexNum-1;
			}
		}
		else
		{
			curr = left;

			int indexNum2 = 0;
			while (indexNum2 < index) 
			{
				curr = curr.after;

				indexNum2++;
			}
		}

		return curr.object1;
	}

	/**
	 * removes first object
	 */
	@Override
	public E removeFirst() throws NoSuchElementException 
	{

		if (left == null)
		{
			throw new NoSuchElementException();
		}

		return (E) unlinkedNode(left);
	}


	/**
	 * Breaks the link
	 * @param left2
	 * @return
	 */
	private E unlinkedNode(Node<E> curr) 
	{
		E object1 = curr.object1;

		Node<E>after = curr.after;

		Node<E>before = curr.before;

		if(before==null)
		{
			left = after;
		}
		else 
			if(before != null)
			{
				before.after = after;
				curr.before = null;
			}

		if(after == null)
		{
			right = before;
			curr.after = null;
		}

		else
			if(after!=null)
			{
				after.before = before;
				curr.after = null;
			}

		curr.object1 = null;
		value = value -1;
		return object1;
	}

	/**
	 * removes last object
	 */
	@Override
	public E removeLast() throws NoSuchElementException 
	{
		if(this.isEmpty() == true)
		{
			throw new NoSuchElementException();
		}

		else
			if(this.isEmpty() == false)
			{
				return (E) unlinkedNode(right);
			}
		return (E) unlinkedNode(right);
	}

	/**
	 * returns and removes a piece in list
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException 
	{
		if (index<0)
		{
			throw new IndexOutOfBoundsException();

		}
		if(index >value || index==value)
		{
			throw new IndexOutOfBoundsException();

		}


		return unlinkedNode(RetrieveNode(index));
	}

	/**
	 * finds the index of an element in a list
	 */
	@Override
	public int indexOf(E element) 
	{

		Node<E>curr;
		curr = left;

		int index = 0;
		while(index <value)
		{
			if(element==null &&curr.object1==null)
			{
				return index;
			}

			else
				if(curr.object1==null)
				{

				}

				else
					if(curr.object1.equals(element))
					{
						return index;
					}

			curr = curr.after;

			index++;
		}

		return -1;
	}

	/**
	 * Finds the index of a pertaining item
	 */
	@Override
	public int lastIndexOf(E element) 
	{
		Node<E> curr;
		curr = left;

		int lastIndex;
		lastIndex = -1;

		int index1 = 0;

		while(index1<value)
		{
			if(element ==null && curr.object1==null)
			{
				lastIndex = index1;
			}

			else
				if(curr.object1==null)
				{

				}

				else
					if (curr.object1.equals(element))
					{
						lastIndex=index1;
					}

			curr = curr.after;	
			index1++;
		}

		if(lastIndex ==-1)
		{
			return lastIndex;
		}


		return lastIndex;
	}


	/**
	 * gives the size
	 */
	@Override
	public int size() 
	{
		return this.value;
	}

	/**
	 * declares if the list is empty
	 */
	@Override
	public boolean isEmpty() 
	{
		return value ==0;
	}

	/**
	 * erases the list by removing pieces
	 */
	@Override
	public void clear() 
	{
		right = null;
		left = null;
		this.value=0;

	}

	/**
	 * Gives an array of all elements in list
	 */
	@Override
	public Object[] toArray() 
	{		
		Object [] arrNod;

		if(value == 0)
		{
			return arrNod = new Object [0];
		}
		else
		{
			arrNod = new Object [value];
		}

		Node<E> curr;
		curr= left;

		int index1 = 0;
		while(index1 < arrNod.length)
		{
			arrNod[index1] = curr.object1;

			curr = curr.after;

			index1 = index1+1;
		}

		return arrNod;
	}

	/**
	 * Iterator
	 */
	@Override
	public Iterator<E> iterator() 
	{
		return new Iterator<E> ()
		{

			Node<E> lastNod;
			Node<E> proceedingNod = left;
			private int main;

			@Override
			public boolean hasNext() 
			{
				if(main < value)
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			@Override
			public E next() 
			{
				if(hasNext() == false)
				{
					throw new NoSuchElementException();
				}
				lastNod = proceedingNod;
				proceedingNod = proceedingNod.after;
				main = main+1;
				return lastNod.object1;
			}

			@Override
			public void remove() 
			{
				if(lastNod == null)
				{
					throw new IllegalStateException();
				}

				Node<E> lastNext;
				lastNext= lastNod.after;
				unlinkedNode(lastNod);
				if(proceedingNod == lastNod)
				{
					proceedingNod = lastNext;
				}
				else
				{
					main = main-1;
				}
				lastNod = null;
			}
		};
	}

	/**
	 * Make a list that has nothing in it.
	 */
	public DoublyLinkedList()
	{

	}

}
