package assignment10;

import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 
 * @author Ashley Grevelink u0749357
 *
 */
public class ChainingHashTable implements Set<String> {

	// Declare the array in your class:
	private LinkedList<String>[] storage;
	private int size;
	private int capacity;
	private int originalCapacity;
	private HashFunctor functor;
	private int collisions;

	/**
	 * 
	 * @param capacity
	 * @param functor
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		this.functor = functor;
		this.capacity = findNextPrime(capacity);
		originalCapacity = capacity;
		size = 0;
		storage = (LinkedList<String>[]) new LinkedList[this.capacity];
		collisions = 0;
		

		// empty spots in the array are set to null
		for (int index = 0; index < storage.length; index++) {
			storage[index] = null;
		}
	}

	@Override
	public boolean add(String item) {
		if (item == null) {
			throw new NullPointerException();
		}

		if (!this.contains(item)) {
			int hashCode = getHashCode(item);
			int hash = hashCode % capacity;

			if (storage[hash] == null) {
				storage[hash] = new LinkedList<String>();
				storage[hash].add(item);
				size++;

			}
			else {
				storage[hash].add(item);
				size++;
				collisions++;
			}
			return true;

		}
		return false;
	}

	private int getHashCode(String item) {
		return functor.hash(item);
	}

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

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		collisions = 0;
		storage = (LinkedList<String>[]) new LinkedList[originalCapacity];
		capacity = originalCapacity;

		// empty spots in the array are set to null
		for (int index = 0; index < storage.length; index++) {
			storage[index] = null;
		}
	}

	@Override
	public boolean contains(String item) {
		if (item == null) {
			throw new NullPointerException();
		}

		int hashCode = getHashCode(item);
		int hash = hashCode % capacity;

		if (storage[hash] != null) {
			// traverse LL
			ListIterator<String> listIterator = storage[hash].listIterator();
			while (listIterator.hasNext()) {
				if (listIterator.next().equals(item)) {
					return true;
				}
			}
		}
		return false;
	}

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

	@Override
	public boolean isEmpty() {
		if (size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
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
	
	/**
	 * Returns the number of collisions. In this case, a collision
	 * occurs if the hash in the array already contains a LinkedList
	 * and therefore has an item already.
	 * 
	 * @return collisions
	 */
	public int collisions() {
		return collisions;
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

}
