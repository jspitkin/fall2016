package assignment10;

import java.util.Collection;

/**
 * A hash table of string objects that uses quadratic probing to resolve collisions
 * @author Brayden Carlson
 *
 */
public class QuadProbeHashTable implements Set<String> {
	
	private String[] array;
	private HashFunctor functor;
	private int capacity;
	private int size;
	int collisions; // Used for experiments
	
	/** Constructs a hash table of the given capacity that uses the hashing function
    * specified by the given functor.
    */
    public QuadProbeHashTable(int capacity, HashFunctor functor) {
    	this.capacity = nextPrime(capacity);
    	this.functor = functor;
    	this.array = new String[this.capacity];
    	this.size = 0;
    	this.collisions = 0;
    }

    /**
     * Ensures that this set contains the specified item.
     * 
     * @param item -
     *          the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     *         if the input item was actually inserted); otherwise, returns false
     */
	@Override
	public boolean add(String item) {
		int hash = functor.hash(item) % capacity;
		// If there is no collision
		if(array[hash] == null) {
			array[hash] = item;
			size++;
			checkResize(); // Check if load factor is > 0.5
			return true;
		}
		else if(array[hash] == item) {
			return false;
		}
		// If there is a collision
		else {
			int i = 1;
			while(true) {
				collisions++;
				int quad = (int) ((hash + Math.pow(i, 2)) % capacity);
				if(array[quad] == null) {
					array[quad] = item;
					size++;
					checkResize(); // Check if load factor is > 0.5
					return true;
				}
				else if(array[quad] == item) {
					return false;
				}
				i++;
			}
		}
	}
	
	/**
	 * Helper method. Checks if the HashTable needs to be resized and rehashed. If so, does so.
	 */
	private void checkResize() {
		if(((double)size / (double)capacity) > 0.5) {
			capacity = nextPrime(capacity + 1);
			String[] oldArray = array;
			clear();
			for(String item : oldArray) {
				if(item != null) {
					add(item);
				}
			}
		}
	}
	
	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items -
	 *          the collection of items whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is,
	 *         if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for(String item : items) {
			if(add(item)) {
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
		array = new String[capacity];
		size = 0;
		collisions = 0;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item -
	 *          the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 */
	@Override
	public boolean contains(String item) {
		int hash = functor.hash(item) % capacity;
		//If there is no collision
		if(array[hash] == null) {
			return false;
		}
		else if(array[hash] == item) {
			return true;
		}
		//If there is a collision
		else {
			int i = 1;
			while(true) {
				int quad = (int) ((hash + Math.pow(i, 2)) % capacity);
				if(array[quad] == null) {
					return false;
				}
				else if(array[quad] == item) {
					return true;
				}
				i++;
			}
		}
	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items -
	 *          the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String item : items) {
			if(!contains(item)) {
				return false;
			}
		}
		return true;
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
	 * Returns the capacity of the HashTable. Used for testing.
	 */
	public int capacity() {
		return capacity;
	}

	/**
	 * Returns the next ordinal prime number after 'n'
	 */
	private int nextPrime(int n) {
		while(!isPrime(n)) {
			if(n % 2 == 0) {
				n++;
			}
			else {
				n+= 2;
			}
		}
		return n;
	}
	
	/**
	 * Helper method. Returns true if 'n' is a prime number. Returns false otherwise.
	 */
	private boolean isPrime(int n) {
		for(int i = 2; i <= Math.sqrt(n); i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
