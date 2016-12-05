package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Separate chaining hash table implementation
 * @author Andrew Worley: u0651238
 */
public class ChainingHashTable implements Set<String>{
	private LinkedList<String>[] storage;
	private HashFunctor hasher;
	private int size;
	private int collisions;
	
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		if (capacity == 0) {
			capacity = 10;
		}
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		hasher = functor;
		size = 0;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean add(String item) throws NullPointerException {
		if (contains(item)) {
			return false;
		}
		
		int insertIndex = hasher.hash(item) % storage.length;
		
		if (storage[insertIndex] == null) {
			storage[insertIndex] = new LinkedList<String>();
		}
		if (storage[insertIndex].size() > 1) {
			collisions++;
		}
		storage[insertIndex].add(item);
		size++;
		
		return true;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean addAll(Collection<? extends String> items) throws NullPointerException {
		boolean setModified = false;
		
		for (String item : items) {
			if (add(item)) {
				setModified = true;
			}
		}
		return setModified;
	}

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * @inheritDoc
	 */
	public void clear() {
		storage = (LinkedList<String>[]) new LinkedList[storage.length];
		collisions = 0;
		size = 0;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean contains(String item) throws NullPointerException {
		if (item.equals(null)) {
			throw new NullPointerException();
		}
		if (size == 0) {
			return false;
		}
		
		int checkIndex = hasher.hash(item) % storage.length;
		
		if (storage[checkIndex] != null && storage[checkIndex].contains(item)) {
			return true;
		}

		return false;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean containsAll(Collection<? extends String> items) throws NullPointerException{
		boolean isInSet = false;
		
		for(String item : items) {
			if (contains(item)) {
				isInSet = true;
			}
		}
		
		return isInSet;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public int size() {
		return size;
	}
	
	/**
	 * @return -- amount of collisions
	 */
	public int getCollisons() {
		return collisions;
	}

}
