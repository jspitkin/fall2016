package assignment10;

import java.util.Collection;

/**
 * This class implements a hash table using the quadrature probing strategy.
 * 
 * @author Ben Figlin (u1115949)
 *
 */
public class QuadProbeHashTable implements Set<String> {

	private String[] hashTable;
	private HashFunctor functor;
	private int size;
	private int collisions;
	private int originalCapacity;
	
	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		hashTable = new String[nextPrime(capacity)];		
		this.originalCapacity = capacity;
		this.functor = functor;
		size = 0;
		collisions = 0;
	}

	@Override
	public boolean add(String item) {
		if (item == null) {
			return false;
		}
		
		// if the load factor is > 0.5, resize (and rehash) the array.
		if (((double)size / (double)hashTable.length) > 0.5) {
			resize();
		}
		
		int index = functor.hash(item) % hashTable.length;
		
		int freeIndex = index;
		int i = 1;
		
		// find an empty slot with the quadratic technique.
		while(hashTable[freeIndex] != null) {
			if (hashTable[freeIndex].equals(item)) {
				return false;
			}
			
			freeIndex = (index + (i*i)) % hashTable.length;
			i++;
			this.collisions++;
		}
		
		hashTable[freeIndex] = item;
		size++;
		
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		if (items == null) {
			return false;
		}
		
		boolean result = false;
		
		for (String item : items) {
			result |= add(item);
		}
		
		return result;
	}

	@Override
	public void clear() {
		hashTable = new String[nextPrime(originalCapacity)];
		size = 0;
		collisions = 0;
	}

	@Override
	public boolean contains(String item) {
		if (item == null) {
			throw new NullPointerException();
		}
		
		int index = functor.hash(item) % hashTable.length;
		
		int freeIndex = index;
		int i = 1;
		
		// find an empty slot with the quadratic technique.
		while(hashTable[freeIndex] != null) {
			if (hashTable[freeIndex].equals(item)) {
				return true;
			}
			
			freeIndex = (index + (i*i)) % hashTable.length;
			i++;
		}
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if (items == null) {
			throw new NullPointerException();
		}
		
		for (String item : items) {
			if (contains(item) == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Gets the value of the collisions counter
	 * 
	 * @return The number of occurred collisions
	 */
	public int nCollisions() {
		return collisions;
	}
	
	/**
	 * Resizes the array to x2 of the size (but chooses a prime number 
	 * for the array size). 
	 * Adds all previous array items to the new array and rehashes them.
	 * 
	 */
	private void resize() {
		String[] temp = hashTable;
		
		hashTable = new String[nextPrime(hashTable.length*2)];		
		size = 0;
		
		for (String item : temp) {
			add(item);
		}
	}

	/**
	 * Used to find and return the next prime number after the number specified.
	 * 
	 * @param num
	 *            the input number to search the next prime number for.
	 * @return The next prime number after the specified input number.
	 */
	private static int nextPrime(int num) {
		int i = 0;

		if (num < 2) {
			return 2;
		}

		while (true) {
			int counter = 0;
			for (i = num - 1; i > 1; i--) {
				if (num % i == 0) {
					counter++;
				}
			}
			if (counter == 0) {
				return num;
			}

			num++;
		}
	}

}
