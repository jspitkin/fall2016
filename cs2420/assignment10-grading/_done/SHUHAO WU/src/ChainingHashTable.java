package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * @author shuhao The class must represent a hash table of String objects and
 *         must use separate chaining to resolve collisions.
 */
public class ChainingHashTable implements Set<String> {
	int size = 0;
	int collisions =0;
	int capacity;
	double loadFactor;
	HashFunctor hasher;
	LinkedList<String>[] table;

	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		this.capacity = capacity;
		hasher = functor;
		table = (LinkedList<String>[]) new LinkedList[capacity];
		for (int i = 0; i < table.length; i++)
			table[i]= new LinkedList<String>();
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 */
	@Override
	public boolean add(String item) {
		if (item == null)
			return false;
		if (this.contains(item))
			return false;
		else {
			int index = hasher.hash(item)% capacity;
			collisions+=table[index].size();
			table[index].add(item);	
			size++;
			return true;
		}
	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for (String s : items) {
			if (!this.contains(s)) {
				this.add(s);
				changed = true;
			}
		}
		return changed;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		for (int i = 0; i < table.length; i++)
			table[i]= new LinkedList<String>();
		size = 0;
	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 */
	@Override
	public boolean contains(String item) {
		int index = hasher.hash(item)% capacity;
		if(size==0)
			return false;
		for (int i = 0; i<table[index].size();i++){
			if (table[index].get(i) == item)
				return true;
		}
		return false;
	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String s : items) {
			if (!this.contains(s))
				return false;
		}
		return true;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}

}
