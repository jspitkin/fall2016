package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class creates a Hash Table that uses chaining to store String into an
 * Array of ArrayList objects and allows the user to pass in a way to resolve
 * collisions.
 * 
 * @author Chris Grayston u0906710
 *
 */
public class ChainingHashTable implements Set<String> {

	private int collisionCount;
	private boolean wasCollision;
	private HashFunctor functor;
	private Entry[] elements;
	private int size;

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public ChainingHashTable(int capacity, HashFunctor functor) {
		elements = new Entry[capacity];

		for (int i = 0; i < capacity; i++) {
			elements[i] = new Entry();
		}
		size = 0;
		this.functor = functor;
		wasCollision = false;
		collisionCount = 0;
	}

	/**
	 * Inner class keeps track of our current entry that we will be putting into
	 * the hash set, and weather the entry has been deleted yet.
	 */
	class Entry {

		public LinkedList<String> storage;

		public Entry() {
			storage = new LinkedList<String>();
		}
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 */
	@Override
	public boolean add(String item) {

		if (contains(item))
			return false;

		else {
			elements[hashCode(item)].storage.addFirst(item);
			size++;
			return true;
		}
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean wasChange = false;
		for (String item : items) {
			wasChange = add(item) || wasChange;
		}
		return wasChange;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		int sizeOfSet = elements.length;
		elements = new Entry[sizeOfSet];
		for (int i = 0; i < sizeOfSet; i++) {
			elements[i] = new Entry();
		}

		size = 0;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 */
	@Override
	public boolean contains(String item) {
		// find position in the set our item would be
		int position = hashCode(item);

		int numOfCollisions = elements[position].storage.indexOf(item);

		// check with number of collisions
		if (numOfCollisions == -1) {
			if (wasCollision) {
				collisionCount = elements[position].storage.size();
			}

			return false;
		}

		else {
			if (wasCollision) {
				collisionCount = numOfCollisions;
			}

			return true;
		}

	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean wasChange = true;
		for (String item : items) {
			wasChange = contains(item) && wasChange;
		}
		return wasChange;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {

		return size;
	}

	/**
	 * Will compute the hash code of the passed item.
	 * 
	 * @param item
	 *            - item to hash
	 * @return - hash code of passed item
	 */
	private int hashCode(String item) {
		int hashReturn = functor.hash(item) % elements.length;

		// find hash position
		if (hashReturn >= 0)
			return hashReturn;
		else
			return hashReturn + elements.length;
	}

	/**
	 * Sets the countCollision according to the given status.
	 */
	public void setCountCollision(boolean status) {
		wasCollision = status;
	}

	/**
	 * Returns the total number of collisions for all operations involving
	 * searching
	 */
	public int getCollisions() {
		return collisionCount;
	}

}
