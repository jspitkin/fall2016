package assignment10;

import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {
	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
    int size=0;
	int capacity;
	double loadFactor;
	HashFunctor hasher;
	String[] table;
	int numOfCol = 0;

	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		this.capacity = capacity;
		hasher = functor;
		if (!isPrime(capacity)) {
			capacity = nextPrime(capacity);
		}
		table = new String[capacity];
		for (int i = 0; i < table.length; i++)
			table[i] = null;
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
		loadFactor = (double) size / capacity;
		if (item == null)
			return false;
		if (this.contains(item))
			return false;
		if (loadFactor >= 0.5) {
			this.resize();
			this.add(item);

		} else {
            int index = hasher.hash(item);
            boolean foundSpot = false;
			for (int i = 0; foundSpot == false; i++) {
				index = (index + (i * i)) % capacity;
				if (table[index] == (null)) {
					table[index] = item;
					size++;
					foundSpot = true;
				}
				else
					numOfCol++;
			}
			return true;
		}
		return false;
	}
	/**
	 * Resize 
	 * table size should be a prime number and that the table should be resized and rehashed when ¦Ë exceeds 0.5
	 */

	private void resize() {

		int newCapacity = nextPrime(this.capacity);
		String[] temp = new String[this.capacity];
		for (int i = 0; i < temp.length; i++)
			temp[i] = table[i];
		table = new String[newCapacity];
		for (int j = 0; j < newCapacity; j++)
			table[j] = null;
		capacity = newCapacity;
		for (int k = 0; k < temp.length; k++) {
			if (temp[k] != null)
				table[k] = temp[k];
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
		boolean changed = false;
		for (String s : items) {
			if (!this.contains(s)) {
				this.add(s);
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */

	@Override
	public void clear() {
		table = new String[capacity];
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
		for (String s : table) {
			if (s == item)
				return true;
		}
		return false;
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
		for (String s : items) {
			if (!this.contains(s))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * 
	 * @param a
	 * @return a boolean to determine whether the input is a prime number or
	 *         not.
	 */
	public static boolean isPrime(int a) {
		boolean flag = true;
		if (a < 2) {
			return false;
		} else {
			for (int i = 2; i <= Math.sqrt(a); i++) {
				if (a % i == 0) {
					flag = false;
					break;
				}
			}
		}
		return flag;
	}

	/**
	 * 
	 * @param a
	 * @return a prime number just after the input number.
	 */
	public static int nextPrime(int a) {
		for (int i = a + 1;; i++) {
			boolean flag = true;
			for (int j = 2; j <= i / 2; j++) {
				if (i % j == 0) {
					flag = false;
					break;
				} else if (i % j != 0) {
					continue;
				} else {
					break;
				}
			}
			if (flag) {
				return i;
			}
		}
	}
}
