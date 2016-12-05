package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class implements a hash table using the separate chaining strategy.
 * 
 * @author Ben Figlin (u1115949)
 *
 */
public class ChainingHashTable implements Set<String> {
	
	private LinkedList<String>[] storage;
	private HashFunctor functor;
	private int size;
	private int collisions;
	
	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
    @SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
    	storage = (LinkedList<String>[]) new LinkedList[capacity];
    	
    	// create a linked list for each array slot
    	for (int i = 0; i < capacity; i++) {
    		storage[i] = new LinkedList<String>();
    	}
    	
    	this.functor = functor;
    	size = 0;
    	collisions = 0;
    }
    
	@Override
	public boolean add(String item) {
		if (item == null) {
			return false;
		}
		
		int index = functor.hash(item) % storage.length;
		
		if (storage[index].contains(item)) {
			return false;
		}
		
		size++;
		boolean result = storage[index].add(item);
		
		collisions += storage[index].indexOf(item);
		
		return result;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		if (items == null) {
			return false;
		}
		
		boolean result = false;
		
		for (String item : items) {
			result |= add(item);
		}
		
		return result;
	}

	@Override
	public void clear() {
		for (LinkedList<String> element : storage) {
			element.clear();
		}
		
		size = 0;
		collisions = 0;
	}

	@Override
	public boolean contains(String item) {
		if (item == null) {
			throw new NullPointerException();
		}
		
		int index = functor.hash(item) % storage.length;
		
		return storage[index].contains(item);
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if (items == null) {
			throw new NullPointerException();
		}
		
		for (String item : items) {
			if (contains(item) == false) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Gets the value of the collisions counter
	 * 
	 * @return The number of occurred collisions
	 */
	public int nCollisions() {
		return collisions;
	}

}
