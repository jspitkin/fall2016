//Cole Cruz
package assignment10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

/**
 * A chaining hash table for Strings.
 * 
 * @author Cole Cruz
 *
 */
public class ChainingHashTable implements Set<String> {

	LinkedList<String>[] storage;
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
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		storage = (LinkedList<String>[]) new LinkedList[prime(capacity)];
		hashFunction = functor;
		for (int index = 0; index < storage.length; index++) {
			storage[index] = new LinkedList<String>();
		}
		collisions = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(String item) {
		int index = hashFunction.hash(item) % storage.length;
		if (!storage[index].contains(item)) {
			if (storage[index].size() > 0) {
				collisions++;
			}
			storage[index].add(item);
			if (rehashNeeded()) {
				rehash();
			}
			return true;
		}
		return false;
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
			storage[index] = new LinkedList<String>();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(String item) {
		int index = hashFunction.hash(item) % storage.length;
		if (storage[index].contains(item)) {
			return true;
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
		boolean empty = true;
		for (int index = 0; index < storage.length; index++) {
			if (!storage[index].isEmpty()) {
				empty = false;
			}
		}
		return empty;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		int size = 0;
		for (int index = 0; index < storage.length; index++) {
			size += storage[index].size();
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
		int sum = 0;
		for (int index = 0; index < storage.length; index++) {
			sum += storage[index].size();
		}
		if ((sum / storage.length) > 5/* test size */) {
			return true;
		}
		return false;
	}

	/**
	 * Rehashes the hash table.
	 */
	@SuppressWarnings("unchecked")
	public void rehash() {
		ArrayList<String> data = new ArrayList<String>();
		for (int index = 0; index < storage.length; index++) {
			data.addAll(storage[index]);
		}
		storage = (LinkedList<String>[]) new LinkedList[prime(storage.length * 2)];
		collisions = 0;
		addAll(data);
	}

}
