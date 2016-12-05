package assignment10;

import java.util.ArrayList;
import java.util.Collection;
import assignment10.Set;

/**
 * 
 * @author Ashley Grevelink u0749357
 *
 */
public class QuadProbeHashTable implements Set<String> {

	private String[] storage;
	private int size;
	private int capacity;
	private int originalCapacity;
	private HashFunctor functor;
	private int collisions;

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		this.functor = functor;
		this.capacity = findNextPrime(capacity);
		originalCapacity = this.capacity;
		size = 0;
		storage = new String[this.capacity];
		collisions = 0;

		// empty spots in the array are set to null
		for (int index = 0; index < storage.length; index++) {
			storage[index] = null;
		}

		// -recall that a functor is an object which encapsulates a method
		// (just like Comparator)
		// the HashFunctor defines a hash method, which implements a
		// hash function
	}

	// add(null) → NullPointerException

	@Override
	public boolean add(String item) {
		if (item == null) {
			throw new NullPointerException();
		}

		double loadFactor = ((double) size / (double) capacity);
		if (loadFactor >= 0.5) {
			increaseSizeAndRehash();
		}

		if (!this.contains(item)) {
			int hashCode = getHashCode(item);
			int hash = hashCode % capacity;

			int quadProbeDegree = 1;
			int originalHash = hash;

			if (storage[hash] != null) {
				collisions++;
			}

			while (storage[hash] != null && !(storage[hash].equals(item))) {
				hash = ((originalHash + quadProbeDegree * quadProbeDegree) % capacity);
				quadProbeDegree++;

				// wrap around
				while (hash >= capacity()) {
					int difference = hash - capacity();
					hash = difference;
				}
			}
			if (storage[hash] == null) {
				size++;
				storage[hash] = item;
				return true;
			}
		}
		return false;
	}

	/**
	 * Useful for testing to make sure the capacity changes when load factor is
	 * greater or equal to 0.5.
	 * 
	 * @return capacity
	 */
	public int capacity() {
		return capacity;
	}

	private void increaseSizeAndRehash() {
		ArrayList<String> tempStorageAL = new ArrayList<String>(size);

		int newCapacity = capacity * 2;
		newCapacity = findNextPrime(newCapacity);

		// empty spots in the array are set to null
		for (int index = 0; index < capacity; index++) {
			if (storage[index] != null) {
				tempStorageAL.add(storage[index]);
			}
		}

		size = 0;
		capacity = newCapacity;
		storage = new String[newCapacity];
		addAll(tempStorageAL);

	}

	/**
	 * 
	 * @param capacity
	 * @return
	 */
	private int findNextPrime(int capacity) {
		// prevents divide-by-zero errors
		if (capacity < 3) {
			capacity = 3;
		}
		while (!isPrime(capacity)) {
			capacity++;
		}

		return capacity;
	}

	/**
	 * 
	 * @param number
	 * @return
	 */
	private boolean isPrime(int number) {
		int sqrt = (int) Math.sqrt(number);
		for (int i = 2; i <= sqrt; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	// addAll(null) → NullPointerException
	// addAll(emptySet) → False
	@Override
	public boolean addAll(Collection<? extends String> items) {
		if (items == null) {
			throw new NullPointerException();
		}

		boolean somethingAdded = false;
		int numberAdded = 0;
		for (String elt : items) {
			somethingAdded = this.add(elt);
			if (somethingAdded == true) {
				numberAdded++;
			}
		}
		if (numberAdded > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		size = 0;
		storage = new String[originalCapacity];
		capacity = originalCapacity;

		// empty spots in the array are set to null
		for (int index = 0; index < storage.length; index++) {
			storage[index] = null;
		}
	}

	// contains(null) for a non-empty hash table → NullPointerException
	// contains(null) for an empty hash table → NullPointerException
	@Override
	public boolean contains(String item) {
		if (item == null) {
			throw new NullPointerException();
		}

		// get hashCode for item
		int hashCode = getHashCode(item);
		int hash = hashCode % capacity;

		int quadProbeDegree = 1;
		int originalHash = hash;

		while (storage[hash] != null) {
			if (storage[hash].equals(item)) {
				return true;
			}
			hash = ((originalHash + quadProbeDegree * quadProbeDegree) % capacity);
			quadProbeDegree++;

			while (hash >= capacity()) {
				int difference = hash - capacity();
				hash = difference;
			}

		}

		return false;
	}

	private int getHashCode(String item) {
		return functor.hash(item);
	}

	// containsAll(null) for a non-empty hash table → NullPointerException
	// containsAll(null) for a empty hash table → NullPointerException
	// containsAll(emptySet) for a non-empty hash table → True
	// containsAll(emptySet) for an empty hash table → True
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if (items == null) {
			throw new NullPointerException();
		}

		boolean somethingAdded = false;
		int numberAdded = 0;
		int numberOfElements = 0;
		for (String elt : items) {
			somethingAdded = this.contains(elt);
			numberOfElements++;
			if (somethingAdded == true) {
				numberAdded++;
			}
		}
		if (numberAdded == numberOfElements) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns the number of collisions. In this case, a collision occurs if the
	 * hash in the array contains an item.
	 * 
	 * @return collisions
	 */
	public int collisions() {
		return collisions;
	}

}
