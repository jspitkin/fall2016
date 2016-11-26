package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * The class must represent a hash table of String objects and must use separate
 * chaining to resolve collisions. You do not need to implement rehashing for
 * this hash table, since it will never be full, however, if you don't implement
 * rehashing, performance may suffer, depending on the initial size of the
 * table.
 * 
 * @author mosesmanning
 *
 */
public class ChainingHashTable implements Set<String> {
	@SuppressWarnings("unused")
	private LinkedList<String>[] storage;
	int size;
	int itemsInArray;
	double loadFactor;
	public HashFunctor hashFunc;
	int collisions = 0;

	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		// Initialize storage size items and our hashFunctor
		storage = new LinkedList[capacity];
		size = capacity;
		itemsInArray = 0;
		hashFunc = functor;
	}

	@Override
	public boolean add(String item) {
		//
		if (item == null) {
			return true;
		}
		int index = grabIndex(item, storage);
		LinkedList<String> temp = storage[index];
		if (temp == null) {
			temp = new LinkedList<String>();
			temp.add(item);
			itemsInArray++;
			loadFactor = ((double) (itemsInArray) / (double) (size));
			storage[index] = temp;
		} else if (!temp.contains(item)) {
			if (!temp.isEmpty()) {
				collisions++;
			}
			temp.add(item);
			itemsInArray++;
			loadFactor = ((double) (itemsInArray) / (double) (size));
			storage[index] = temp;
		}
		if (loadFactor >= 0.5) {
			rehash();
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
		storage = new LinkedList[primeNumberGen.nextPrimeNumber(size)];
		itemsInArray = 0;
		loadFactor = 0;
		collisions = 0;
	}

	@Override
	public boolean contains(String item) {
		if (item == null) {
			return true;
		}
		LinkedList<String> temp = storage[this.grabIndex(item, storage)];
		if (temp == null) {
			return false;
		}
		return (temp.contains(item));
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean itemsPresent = true;
		for (String string : items) {
			if (!contains(string)) {
				itemsPresent = false;
			}
		}
		return itemsPresent;
	}

	private int grabIndex(String item, LinkedList<String>[] storage2) {
		if (item == null) {
			return 0;
		}
		int hashVal = hashFunc.hash(item);
		int index = hashVal % size;
		while (index >= size) {
			index = index - size;
		}
		return index;
	}

	@Override
	public boolean isEmpty() {
		return itemsInArray == 0;
	}

	private void rehash() {
		ChainingHashTable tempArray = new ChainingHashTable(primeNumberGen.nextPrimeNumber(itemsInArray * 2), hashFunc);
		size = tempArray.size;
		for (LinkedList<String> linkedList : storage) {
			if (linkedList == null) {
				continue;
			}
			tempArray.addAll(linkedList);
		}
		storage = tempArray.storage;
		loadFactor = tempArray.loadFactor;
	}

	@Override
	public int size() {
		return size;
	}

}
