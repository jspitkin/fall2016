package assignment10;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 
 * @author Chase Stephens
 * 
 *         Represents a Hash Table that uses separate chaining.
 *
 */

public class ChainingHashTable implements Set<String> {

	private int collisionCount = 0;
	private LinkedList<String>[] storage;
	HashFunctor upTownFunc;
	private int numOfLists, size;
	private double lambda; // average length of linked list

	/**
	 * Constructor for a chain hash table.
	 * 
	 * @param capacity
	 *            - max number of items, size grows when 50% capacity is
	 *            reached.
	 * @param functor
	 *            - Hash Fucntion
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		numOfLists = capacity;
		storage = (LinkedList<String>[]) new LinkedList[numOfLists];
		lambda = 0;
		size = 0;
		upTownFunc = functor;
	}

	/**
	 * Adds an item to this hash table.
	 * 
	 * @return - true if the item was added, false if the item was already in
	 *         this table.
	 */
	@Override
	public boolean add(String item) {
		int index = upTownFunc.hash(item) % numOfLists;
		if (storage[index] != null) {
			ListIterator<String> listIterator = storage[index].listIterator();
			while (listIterator.hasNext()) {
				collisionCount++;
				if (listIterator.next().equals(item)) {
					return false;
				}
			}
		} else {
			storage[index] = new LinkedList<String>();
		}
		storage[index].add(item);
		size++;
		lambda = (double) size / numOfLists;
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
	 * 
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
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		lambda = 0;
		storage = (LinkedList<String>[]) new LinkedList[numOfLists];
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
		int index = upTownFunc.hash(item) % numOfLists;
		if (storage[index] != null) {
			ListIterator<String> listIterator = storage[index].listIterator();
			while (listIterator.hasNext()) {
				if (listIterator.next().equals(item)) {
					return true;
				}
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
	 * @return The number of collisions that have occurred on this set.
	 */
	public int getCollisionCount() {
		return collisionCount;
	}

	/**
	 * Rehashes the items into a larger table when the load factor exceeds 0.5.
	 */
	private void reHash() {
		// copying over old data
		int oldSize = numOfLists;
		LinkedList<String>[] oldTable = storage;
		numOfLists = numOfLists * 2;
		this.clear();
		for (int i = 0; i < oldSize; i++) {
			if (oldTable[i] != null) {
				ListIterator<String> listIterator = oldTable[i].listIterator();
				while (listIterator.hasNext()) {
					this.add(listIterator.next());
				}
			}
		}
	}
}
