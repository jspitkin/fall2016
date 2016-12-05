package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/** The class must represent a hash table of String objects and must use separate chaining to resolve collisions. You do not 
 * need to implement rehashing for this hash table, since it will never be full, however, if you don't implement rehashing, 
 * performance may suffer, depending on the initial size of the table.
 * 
 * @author Ryan Bolander u0016911
 *
 */
public class ChainingHashTable implements Set<String>
{
	private LinkedList<String>[] storage;
	private int size = 0;
	private HashFunctor thisFunctor;
	private int thisCapacity = 100;
	private float lambda;

	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor)
	{
		storage = (LinkedList<String>[]) new LinkedList[thisCapacity];
		lambda = 0;
		size = 0;
		thisFunctor = functor;
	}

	@Override
	public boolean add(String item) 
	{
		// Get my hash index to add item
		int index = getIndex(item);
		
		
		if(!contains(item))
		{
			if(storage[index] == null)
			{
				storage[index] = new LinkedList<String>();
				storage[index].add(item);
			}
			else
			{
				storage[index].addLast(item);
			}
			
			size++;
			lambda = (float)size / (float)thisCapacity;
			return true;
		}
		
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) 
	{
		boolean changed = false;

		for (String element : items) 
		{
			if (!changed) 
			{
				changed = add(element);
			}
			else 
			{
				add(element);
			}
		}

		return changed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() 
	{
		storage = (LinkedList<String>[]) new LinkedList[thisCapacity];
		size = 0;
		lambda = 0;
	}

	@Override
	public boolean contains(String item) 
	{
		// Get the hash index of the item I am looking for
		int index = getIndex(item);
		
		if(storage[index] == null)
		{
			return false;
		}

		for(int i = 0; i < storage[index].size(); i++)
		{
			if(storage[index].get(i).equals(item))
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) 
	{
		for (String element: items) 
		{
			if (!contains(element)) 
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() 
	{
		if(size == 0)
		{
			return true;
		}

		return false;
	}

	@Override
	public int size() 
	{
		return size;
	}

	/**
	 * Method to get the hash index for item being inserted
	 * @param item
	 * @return The index where to insert an item based on the hash
	 */
	private int getIndex(String item)
	{
		int tempIndex = thisFunctor.hash(item);
		tempIndex = tempIndex % (thisCapacity - 1);
		return (tempIndex);
	}

}
