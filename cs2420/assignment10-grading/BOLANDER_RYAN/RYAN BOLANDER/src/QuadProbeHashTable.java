package assignment10;

import java.util.Collection;

/**
 * To allow your QuadProbeHashTable class to be used with any hash function, notice that the constructor accepts a function object 
 * containing the int hash(String item) method, and that method should be used whenever an item's hash code is needed. The 
 * HashFunctor interface is given below.  Finally, recall that the table size should be a prime number and that the table should 
 * be resized and rehashed when lambda exceeds 0.5.
 * 
 * @author Ryan Bolander u0016911
 *
 */
public class QuadProbeHashTable implements Set<String>
{
	private int size = 0;
	private String[] storage;
	private HashFunctor thisFunctor;
	private int thisCapacity = 0;
	private float lambda;

	/** Constructs a hash table of the given capacity that uses the hashing function
	 * specified by the given functor.  (Hint: If the capacity given in the constructor is not prime, use the next largest 
	 * prime number as the table size.)
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor)
	{
		// Check to make sure array getting created has a capacity of a prime number
		if(!checkIfPrime(capacity))
		{
			capacity = getNextPrime(capacity);
		}

		thisFunctor = functor;
		storage = new String[capacity];
		size = 0;
		thisCapacity = capacity;
		lambda = 0;
	}

	@Override
	public boolean add(String item) 
	{		
		// If lambda is greater than 50% then increase the capacity of our array
		if(lambda > 0.5)
		{
			increaseSize();
		}

		// If the item is not in the list then we can add it
		if(!contains(item))
		{
			// Get my hash index to add item
			int index = getIndex(item);

			int i = 1;

			// While index I am trying to add to is not null keep looking for empty space to add using quadratic probing
			while(storage[index] != null)
			{
				index = index + (i*i);
				i++;

				// If my index is greater than the capacity then wrap around to beginning again
				if(index > thisCapacity - 1)
				{
					index = index - (thisCapacity - 1);
				}
			}

			// Found empty index so add item, increase size, set new lambda, and return true
			storage [index] = item;
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

	@Override
	public void clear() 
	{
		storage = new String[thisCapacity];
		size = 0;
		lambda = 0;
	}

	@Override
	public boolean contains(String item) 
	{
		// Get the hash index of the item I am looking for
		int index = getIndex(item);

		// If the index is null then that item is not in the list else start looking
		if(storage[index] == null)
		{
			return false;
		}
		else
		{
			// Loop that only loops as large as the size of our items in the list checking to see if it's already there
			for(int j = 1; j <= size; j++)
			{
				// If I arrive at a location that is null then item is not in list
				if(storage[index] == null)
				{
					return false;
				}
				// If I find the item then return true else go to the next quadratic hash index for this item
				if(storage[index].equals(item))
				{
					return true;
				}
				else
				{
					index = index + (j * j);

					// If the index is greater than the capacity then wrap it around
					if(index > thisCapacity - 1)
					{
						index = index - (thisCapacity - 1);
					}
				}
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
	 * Method to check if number needing to be the capacity of the array is a prime number
	 * @param numberToCheck
	 * @return true if the number is prime and false if it is not prime
	 */
	private boolean checkIfPrime(int numberToCheck)
	{
		for(int i = 2; i < numberToCheck; i++)
		{
			if(numberToCheck % i == 0)
				return false;
		}

		return true;
	}

	/**
	 * Method to get the next prime number after the requested or current capacity
	 * @param primeSearch
	 * @return the next prime number
	 */
	private int getNextPrime(int primeSearch)
	{
		boolean notFound = true;

		// If I have an even number add one to make it odd because all even numbers are not prime
		if(primeSearch % 2 == 0)
		{
			primeSearch++;
		}

		// While I haven't found the next prime number keep searching by adding 2
		while(notFound)
		{
			if(checkIfPrime(primeSearch))
			{
				notFound = false;
			}

			if(notFound)
			{
				primeSearch+=2;
			}
		}

		return primeSearch;
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

	/**
	 * Method for increasing the size of the array that is storing the variables.  This is to ensure we stay above
	 * the 50% empty requirement
	 */
	private void increaseSize()
	{
		// First double the capacity
		thisCapacity = thisCapacity * 2;

		// Check to see if doubling the capacity managed to be a prime number and if not then get the next prime number
		if(!checkIfPrime(thisCapacity))
		{
			thisCapacity = getNextPrime(thisCapacity);
		}

		// Create a temp area and then have it equal our current array and then clear our current one
		String[] temp = new String[thisCapacity];
		temp = storage;
		clear();

		// For loop to re-add the items that were in the array back into our array in order to re-hash all of them
		for(int i = 0; i < temp.length; i++)
		{
			if(temp[i] != null)
			{
				add(temp[i]);
			}
		}
	}

}
