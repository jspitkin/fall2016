//Cole Cruz
package assignment10;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A quadratic probing hash table for Strings.
 * 
 * @author Cole Cruz
 *
 */
public class QuadProbeHashTable implements Set<String> {

	String[] storage;
	HashFunctor hashFunction;
	int collisions;

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 * 
	 * @param capacity
	 *            -- the given capacity.
	 * @param functor
	 *            -- the given hashing function.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		storage = new String[prime(capacity)];
		hashFunction = functor;
		collisions = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(String item) {
		if (contains(item)) {
			return false;
		}
		int index = hashFunction.hash(item) % storage.length;
		if (storage[index] == null) {
			storage[index] = item;
			if (rehashNeeded()) {
				rehash();
			}
			return true;
		}
		int probe = 1, newIndex = index;
		while (storage[newIndex] != null) {
			newIndex = (index + (int) Math.pow(probe, 2)) % storage.length;
			probe++;
			collisions++;
		}
		storage[newIndex] = item;
		if (rehashNeeded()) {
			rehash();
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for (String item : items) {
			if (add(item)) {
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		for (int index = 0; index < storage.length; index++) {
			storage[index] = null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(String item) {
		int index = hashFunction.hash(item) % storage.length;
		if (item.equals(storage[index])) {
			return true;
		}
		int probe = 1, newIndex = index;
		while ((storage[newIndex] != null)) {
			newIndex = (index + (int) Math.pow(probe, 2)) % storage.length;
			if (item.equals(storage[newIndex])) {
				return true;
			}
			probe++;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean changed = true;
		for (String item : items) {
			if (!contains(item)) {
				changed = false;
			}
		}
		return changed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		for (int index = 0; index < storage.length; index++) {
			if (storage[index] != null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		int size = 0;
		for (int index = 0; index < storage.length; index++) {
			if (storage[index] != null) {
				size++;
			}
		}
		return size;
	}

	/**
	 * Returns the next prime number after or equal to number.
	 * 
	 * @param number
	 *            -- the number to start at.
	 * @return the next prime number after or equal to number.
	 */
	public int prime(int number) {
		int prime = 0, numberToTest = number;
		while (prime < number) {
			if (isPrime(numberToTest)) {
				prime = numberToTest;
			} else {
				numberToTest++;
			}
		}
		return prime;
	}

	/**
	 * Returns if the number is prime.
	 * 
	 * @param number
	 *            -- the number to check.
	 * @return if the number is prime.
	 */
	public boolean isPrime(int number) {
		if ((number == 2) || (number == 3)) {
			return true;
		}
		if (number % 2 == 0) {
			return false;
		}
		for (int index = 3; index < number / 2; index++) {
			if ((number % index) == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Decides if a rehash is needed.
	 * 
	 * @return true if a rehash is needed false otherwise.
	 */
	public boolean rehashNeeded() {
		if (size() >= storage.length / 2) {
			return true;
		}
		return false;
	}

	/**
	 * Rehashes the hash table.
	 */
	public void rehash() {
		ArrayList<String> data = new ArrayList<String>();
		for (int index = 0; index < storage.length; index++) {
			if (storage[index] != null) {
				data.add(storage[index]);
			}
		}
		storage = new String[prime(storage.length * 2)];
		collisions = 0;
		addAll(data);
	}

}
