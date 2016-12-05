package assignment10;

import java.util.Collection;
import java.util.LinkedList;
/**
 * Chaining Hash Table class
 * 
 * @author Jiwon Nam
 *
 */
public class ChainingHashTable implements Set<String>{
	
	private int originCapacity;
	private int capacity;
	private int size;
	private int lamda;
	private HashFunctor functor;
	private LinkedList<String>[] storage;
	
	/** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		originCapacity = capacity;
		this.capacity = capacity;
		this.functor= functor;
		storage = (LinkedList<String>[]) new LinkedList[this.capacity];
		// initialize all linked list
		for(int i = 0; i < this.capacity; i++)
			storage[i] = new LinkedList<String>();

		size = 0;
		lamda = 3;
	}
	
	@Override
	public boolean add(String item) {
		
		if(item == null)
			return false;
		
		int index = functor.hash(item) % capacity;
		// check duplication
		if(storage[index].contains(item))
			return false;
		
		storage[index].add(item);
		size++;
		
		int loadFactor = size / capacity;
		if(lamda <= loadFactor)
			rehash();
		
		return true;
	}

	/**
	 * helepr method to rehash the table with new capacity
	 */
	@SuppressWarnings("unchecked")
	private void rehash() {
		int oldCapacity = capacity;
		capacity = capacity * 2;
		
		// copy origin to temp
		LinkedList<String>[] tempList = (LinkedList<String>[]) new LinkedList[oldCapacity];
		
		for(int i = 0; i < tempList.length; i++) {
			tempList[i] = new LinkedList<String>();
			tempList[i].addAll(storage[i]);
		}
		// make new storage with new capacity
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		
		for(int i = 0; i < capacity; i++)
			storage[i] = new LinkedList<String>();
		
		for(int i = 0; i < oldCapacity; i++) {
			if(!tempList[i].isEmpty()) {
				for(String item : tempList[i]) {
					int index = functor.hash(item) % capacity;
					storage[index].add(item);
				}		
			}
		}
	}
	
	/**
	 * helper method to find good value to rehash the hash map run-time
	 * @param lamda
	 */
	public void setLamda(int lamda) {
		this.lamda = lamda;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean isAdded = false;
		if(items.isEmpty())
			return false;
		for(String item : items) {
			if(add(item))
				isAdded = true;
		}
		return isAdded;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		capacity = originCapacity;
		storage = (LinkedList<String>[]) new LinkedList[this.capacity];
		for(int i = 0; i < this.capacity; i++)
			storage[i] = new LinkedList<String>();
		size = 0;
		lamda = 3;
	}

	@Override
	public boolean contains(String item) {
		if(item == null)
			return false;
		
		int index = functor.hash(item) % capacity;
		
		if(storage[index].contains(item))
			return true;
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean isContained = false;
		if(items.isEmpty())
			return false;
		for(String item : items) {
			if(contains(item))
				isContained = true;
		}
		return isContained;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * helper method to return capacity
	 * 
	 * @return int, capacity
	 */
	public int getCapacity() {
		return capacity;
	}
}
