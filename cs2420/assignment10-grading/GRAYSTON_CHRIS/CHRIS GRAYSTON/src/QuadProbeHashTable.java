package assignment10;

import java.util.Collection;

/**
 * This class uses Quadratic Hashing in order to store Strings
 * into an Array which grows when a specified load factor is met
 * and allows the user to store and find elements very quickly within 
 * a growing array, also allows users to pass in a way to resolve collisions.
 * 
 * @author Chris Grayston u0906710
 *
 */
public class QuadProbeHashTable implements Set<String> {

	private int size;
	private HashFunctor functor;
	private boolean beenCollision;
	private Entry[] elements;
	private int collisionCount;

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		elements = new Entry[capacity];
		size = 0;
		this.functor = functor;
		beenCollision = false;
		collisionCount = 0;
	}

	/**
	 * Inner class keeps track of our current entry that we will be putting into
	 * the hash set, and weather the entry has been deleted yet.
	 */
	class Entry {
		public String item;
		public boolean isAlive;

		public Entry(String item) {
			this.item = item;
			isAlive = true;
		}

		public Entry(String item, boolean isAlive) {
			this.item = item;
			this.isAlive = isAlive;
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

		// if load factor (= number of entries / internal array size) is bigger
		// than 0.5
		// then expand the internal array and rehash
		if (lambda() > 0.5)
			refactor();

		// find the position where the given item should be inserted or the
		// given item located
		int pos = getPosition(item);
		if (elements[pos] == null) { // item is never added to the set -- add it
			elements[pos] = new Entry(item);
			size++;
			return true;
		} else if (elements[pos].isAlive) // item is presently in the set
			return false;
		else { // item is not in the set presently -- add it
			elements[pos].isAlive = true;
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
		int position = getPosition(item);

		if (elements[position] == null) // the given item is never added to the set
			return false;
		else {
			// item will be present if it is active
			return elements[position].isAlive;
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
	 * Gets position where current item should be placed.
	 * 
	 * @param item
	 * @return
	 */
	private int getPosition(String item) {
		// // get the hash code for item
		// int position = hashCode(item);
		//
		// // store number of collisions
		// int count = 0;
		//
		// // search for the given item until an empty (null) entry is
		// encountered
		// // or it is found
		// // (in the sense that it is presently in the set or was in the set
		// // before)
		// while (items[position] != null && !items[position].item.equals(item))
		// {
		// count++;
		// position += 2 * count - 1; // quadratic probing
		// position %= items.length;
		// }
		// if (countCollision) // we are counting collisions -- update
		// // collisionCounts
		// collisionCount = count;
		// return position;

		// compute the hash code of the given item -- this is the position the
		// item should be
		// located if there is no collision and the item was added to the set
		// before
		int pos = hashCode(item);
		// count stores the number of collisions for finding the given item
		int count = 0;
		// search for the given item until an empty (null) entry is encountered
		// or it is found
		// (in the sense that it is presently in the set or was in the set
		// before)
		while (elements[pos] != null && !elements[pos].item.equals(item)) {
			count++;
			pos += 2 * count - 1; // quadratic probing
			pos %= elements.length;
		}
		if (beenCollision) // we are counting collisions -- update
							// collisionCounts
			collisionCount = count;
		return pos;
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
	 * Returns lambda or the load factor.
	 * 
	 * @return - lambda or the load factor
	 */
	private double lambda() {
		return (double) size / elements.length;
	}

	/**
	 * Gets next prime number
	 * 
	 * @param n
	 * @return
	 */
	private int nextPrime(int n) {

		for (int i = n; i < 2 * n; ++i) {
			if (isPrime(i)) {
				return i;
			}
		}

		return 0;
	}

	/**
	 * Finds if number is prime or not
	 * 
	 * @param n
	 * @return
	 */
	private static boolean isPrime(int n) {
		// check if n is a multiple of 2
		if (n % 2 == 0)
			return false;
		// if not, then just check the odds
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	/**
	 * Expands this hash table and rehashes the entries.
	 */
	private void refactor() {
		Entry[] temp = elements;
		elements = new Entry[nextPrime(2 * elements.length)];
		size = 0;

		for (int i = 0; i < temp.length; i++)
			if (temp[i] != null && temp[i].isAlive)
				add(temp[i].item);
	}

	public int getCollisions() {

		return collisionCount;
	}

	public void setCountCollision(boolean b) {
		beenCollision = b;

	}

}
