package assignment10;

import java.util.Collection;

/**
 * 
 * @author Chase Stephens
 * 
 *         This class represents a Quadratic probe hash table.
 *
 */

public class QuadProbeHashTable implements Set<String> {

	private int collisionCount = 0;
	String[] table;
	HashFunctor upTownFunc;
	private int MAX_SIZE, size;
	private double lambda; // portion of arraylist full

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		MAX_SIZE = findPrime(capacity);
		size = 0;
		lambda = 0;
		upTownFunc = functor;
		table = new String[MAX_SIZE];
	}

	/**
	 * Adds an item to this hash table.
	 * 
	 * @return - true if the item was added, false if the item was already in
	 *         this table.
	 */
	@Override
	public boolean add(String item) {
		int index = upTownFunc.hash(item) % MAX_SIZE;
		int x = 1;
		while (table[index] != null) {
			collisionCount++;
			if (table[index].equals(item)) {
				return false;
			}
			index += x * x++;
			if (index > MAX_SIZE - 1) {
				index -= MAX_SIZE - 1;
			}
		}
		table[index] = item;
		size++;
		lambda = (double) size / MAX_SIZE;
		if (lambda > 0.5) {
			reHash();
		}
		return true;
	}

	/**
	 * Adds a collection of Strings to this hash table
	 * 
	 * @param items
	 *            - collection of item to be added.
	 * @return - true if any of the items were added to this table, false
	 *         otherwise.
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		Boolean toReturn = false;
		for (String str : items) {
			if (this.add(str)) {
				toReturn = true;
			}
		}
		return toReturn;
	}

	/**
	 * Clears this table.
	 */
	@Override
	public void clear() {
		size = 0;
		table = new String[MAX_SIZE];
		lambda = 0;
	}

	/**
	 * Checks if a specific string is in this table.
	 * 
	 * @param item
	 *            - String to check for.
	 * @return - true if this table contains this item, false otherwise.
	 */
	@Override
	public boolean contains(String item) {
		int x = 1;
		int index = upTownFunc.hash(item) % MAX_SIZE;
		while (table[index] != null) {
			if (table[index].equals(item)) {
				return true;
			}
			index += x * x++;
			if (index > MAX_SIZE - 1) {
				index -= MAX_SIZE - 1;
			}
		}
		return false;
	}

	/**
	 * Determines of the hash table contains an entire collection of Strings.
	 * 
	 * @param items
	 *            - collection of Strings
	 * @return - true if all strings are in the table, false otherwise.
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String str : items) {
			if (!this.contains(str)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return - true is this table is empty, false otherwise.
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * @return size of this table.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * @return - true is this table is empty, false otherwise.
	 */
	private void reHash() {
		// copying over old data
		int oldSize = MAX_SIZE;
		String[] oldTable = table;

		MAX_SIZE = findPrime(MAX_SIZE * 2);
		this.clear();

		for (int i = 0; i < oldSize; i++) {
			if (oldTable[i] != null) {
				this.add(oldTable[i]);
			}
		}
	}

	/**
	 * finds the smallest prime number that is larger than some prime number.
	 * 
	 * @param prime
	 *            - number to find the next largest prime of.
	 * @return - prime number.
	 */
	private static int findPrime(int prime) {
		Boolean notPrime = true;
		prime--;
		while (notPrime) {
			notPrime = false;
			int sqrt = (int) Math.sqrt(++prime);
			for (int i = 2; i <= sqrt; i++) {
				if (prime % i == 0) {
					notPrime = true;
					break;
				}
			}
		}
		return prime;
	}

	/**
	 * @return The number of collisions that have occured on this set.
	 */
	public int getCollisionCount() {
		return collisionCount;
	}
}
