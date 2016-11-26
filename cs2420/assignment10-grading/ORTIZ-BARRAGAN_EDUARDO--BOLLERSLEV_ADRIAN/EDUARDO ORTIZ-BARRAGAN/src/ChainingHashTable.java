package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Eduardo Ortiz, Adrian Bollerslev
 */
public class ChainingHashTable implements Set<String> {
	
	private LinkedList<String>[] entries;	//an array containing entries
	private int size;
	private int numcolls;
	private HashFunctor hf;	//the specified hash function

	/**
	 * This constructor creates a Hash Table which uses
	 * separate chaining to resolve collisions
	 * @param capacity the capacity of the hash table
	 * @param functor is a Hashing function provided by the user
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		hf = functor;
		numcolls = 0;
		entries = (LinkedList<String>[])new LinkedList[capacity];
		for (int i=0; i<capacity; i++){
			entries[i] = new LinkedList<String>();
		}
		size = 0;
	}
	/**
	 * Hash the specified item using the hash function.
     * @param item the item to be hashed
     * @return the hash code for the specified item
	 */
	public int hashCode(String item) {
		int temph = hf.hash(item)%entries.length;
		//this avoids negative values
		if (temph < 0){
			return temph + entries.length;
		}
		else{
			return temph;
		}
	}
	
	@Override
	public boolean add(String item) {
		if(item == null){
			throw new NullPointerException();
		}
		//add the item and increment the size
		if(!contains(item)){	
			entries[hashCode(item)].addFirst(item);
			size++;
			return true;
		}
		//if the item is in the set already, return false
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		//a variable to check if the set is changed
		boolean checker = false;
		for (String s : items) {
			if(add(s)){
				//if the set is changed at all, return true
				checker = true;
			}
			
		}
		//we cannot add nothing
		if(items.isEmpty()){
			return false;
		}
		return checker;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		int capacity = entries.length;
		entries = (LinkedList<String>[])new LinkedList[capacity];
		for (int i=0; i<capacity; i++)
			entries[i] = new LinkedList<String>();
		size = 0;
		
	}

	@Override
	public boolean contains(String item) {
		if(item == null){
			throw new NullPointerException();
		}
		int spot = hashCode(item);
		int collc = entries[spot].indexOf(item);
		//the item is in the hash set at its specified position
		if (collc != -1) {	
			numcolls = collc;
			return true;
		}
		numcolls = entries[spot].size();
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if(items.isEmpty()){
			return true;
		}
		for(String s : items){
			if(!contains(s)){
				//if the set does not contain ALL the values return false
				return false;
			}		
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	//
	/**
	 * @return the load factor for timing
	 */
	public double loadFactor() {
		return (double)size/entries.length;
	}
	/**
	 * A helper method which returns the number of collisions
	 * @return the number of collisions
	 */
	public int getColl() {
		return numcolls;
	}
}