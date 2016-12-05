package assignment10;


import java.util.Collection;
import java.util.LinkedList;
/**
 * 
 * @author Jordan Gardner
 * Chaining Hash Table class, uses a Linked List to store
 * Strings after hashing them using a functor.
 * See good, bad, and mediocre functors.  
 * Methods allow user to add, addAll, clear, getsize, test empty.
 * 
 *
 */
public class ChainingHashTable implements Set<String> {

	
	private HashFunctor hf;
	int size;
	private LinkedList<String>[] storage;
	private int maxSize;
	double load;
	int numOfItems;

	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		hf = functor;
		size = capacity;
		numOfItems = 0;
		
	}

	@Override
	public boolean add(String item) {
		if (item == null) {
			return true;
		}

		int hasher = hf.hash(item);
		//hashes the item based on the hash functor
		int hash = hasher%size;
		
		while (hash >= size) {
			hash = hash - size;
		}


		LinkedList<String> start = storage[hash];
		if (start == null) {
			start = new LinkedList<String>();
			start.add(item);
			numOfItems++;
			
			storage[hash] = start;
			load=numOfItems/size;
			return true;

		} else if (!start.contains(item)) {
			
			start.add(item);
			numOfItems++;
			storage[hash] = start;
			load=numOfItems/size;
			return true;
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		for (String s : items) {
			add(s);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		maxSize = Prime.nextPrime(size);
		numOfItems = 0;
		load=0;
		storage = (LinkedList<String>[]) new LinkedList[maxSize];

	}

	@Override
	public boolean contains(String item) {
		if (item == null) {
			return true;
		}
		int hasher = hf.hash(item);
		int hash = hasher % size;
		while (hash >= size) {
			hash = hash - size;
		}
		LinkedList<String> temp = storage[hash];
		if (temp == null) {
			return false;
		}

		if (temp.contains(item)) {
			return true;

		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String s : items) {
			if (!contains(s)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		if (numOfItems == 0) {
			return true;
		} else {

			return false;
		}
	}

	@Override
	public int size() {

		return numOfItems;
	}

}
