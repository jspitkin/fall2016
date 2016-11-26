package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * A hash table of string objects that uses separate chaining to resolve collisions
 * @author Brayden Carlson
 *
 */
public class ChainingHashTable implements Set<String> {
	
	private LinkedList<String>[] array;
	private HashFunctor functor;
	private int capacity;
	private int size;
	int collisions; // Used for experiments

	/** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
   	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
	   this.capacity = capacity;
	   this.functor = functor;
	   size = 0;
	   collisions = 0;
	   array = (LinkedList<String>[]) new LinkedList[capacity];
	   for(int i = 0; i < array.length; i++) {
		   array[i] = new LinkedList<String>();
	   }
   }
	
	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item -
	 *          the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that is,
	 *         if the input item was actually inserted); otherwise, returns false
	 */
	@Override
	public boolean add(String item) {
		int hash = functor.hash(item) % capacity;
		if(!array[hash].contains(item)) {
			if(array[hash].size() > 0) collisions++;
			array[hash].add(item);
			size++;
			return true;
		}
		return false;
	}

	/**
     * Ensures that this set contains all items in the specified collection.
     * 
     * @param items -
     *          the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is,
     *         if any item in the input collection was actually inserted);
     *         otherwise, returns false
     */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for(String item : items) {
			if(add(item)) {
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		collisions = 0;
		array = (LinkedList<String>[]) new LinkedList[capacity];
		for(int i = 0; i < array.length; i++) {
			array[i] = new LinkedList<String>();
		}
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item -
	 *          the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 */
	@Override
	public boolean contains(String item) {
		int hash = functor.hash(item) % capacity;
		if(array[hash].contains(item)) {
			return true;
		}
		return false;
	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items -
	 *          the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an item
	 *         in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String item : items) {
			if(!contains(item)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

}
