package assignment10;

import java.util.Collection;

/**
 * To allow your QuadProbeHashTable class to be used with any hash function,
 * notice that the constructor accepts a function object containing the int
 * hash(String item) method, and that method should be used whenever an item's
 * hash code is needed. The HashFunctor interface is given below.
 * 
 * Finally, recall that the table size should be a prime number and that the
 * table should be resized and rehashed when λ exceeds 0.5. (Hint: If the
 * capacity given in the constructor is not prime, use the next largest prime
 * number as the table size.)
 * 
 * @author mosesmanning
 *
 */
public class QuadProbeHashTable implements Set<String> {

	public static String[] storage;
	int arraySize;
	int items;
	public HashFunctor hashFunc;
	int collisions = 0;
	double loadFactor;

	public static void main(String[] args) {

	}

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		storage = new String[primeNumberGen.nextPrimeNumber(capacity)];
		hashFunc = functor;
		items = 0;
		arraySize = storage.length;
		loadFactor = 0;
	}

	@Override
	public boolean add(String item) {
		if (item == null) {
			return true;
		}
		if (storage[getIndex(item, storage)] == null) {
			storage[getIndex(item, storage)] = item;
			items++;
		}
		loadFactor = ((double) (items) / (double) (arraySize));
		if (loadFactor >= 0.5) {
			rehasher();
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		for (String string : items) {
			add(string);
		}
		return true;
	}

	@Override
	public void clear() {
		storage = new String[arraySize];
		items = 0;
		collisions = 0;
		loadFactor = 0;
	}

	@Override
	public boolean contains(String item) {
		if (item == null) {
			return true;
		}
		int index = getIndex(item, storage);
		if (storage[index] == null && item != null) {
			return false;
		}
		return storage[index].compareTo(item) == 0;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean hasAll = true;
		for (String string : items) {
			if (!contains(string)) {
				hasAll = false;
			}
		}
		return hasAll;
	}

	private int getIndex(String item, String[] hashTable) {
		if (item == null) {
			return 0;
		}
		int hasher = hashFunc.hash(item);
		int index = (hasher % arraySize);
		while (index >= arraySize) {
			index = index - arraySize;
		}
		if (index != 0) {
			index--;
		}
		if ((hashTable[index] != null) && (hashTable[index].equals(item))) {
			return index;
		}
		int cell1 = 1;
		int cell2 = 1;
		while (hashTable[index] != null) {
			collisions++;

			cell1 = cell1 + 1;
			cell2 = cell1 * cell1;
			index = index + cell2;
			while (index >= arraySize) {
				index = index - arraySize;
			}

			if (hashTable[index] != null && hashTable[index].equals(item)) {
				return index;
			}
		}
		return index;
	}

	@Override
	public boolean isEmpty() {
		return items == 0;
	}

	private void rehasher() {
		String[] temp = new String[primeNumberGen.nextPrimeNumber(arraySize * 2)];
		arraySize = temp.length;
		for (String string : storage) {
			if (string == null) {
				continue;
			}
			int index = getIndex(string, temp);
			temp[index] = string;
		}
		loadFactor = ((double) (items) / (double) (arraySize));
		storage = temp;
	}

	@Override
	public int size() {
		return items;
	}
}
