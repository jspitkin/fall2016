package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * HashTable for Strings using a Chaining strategy for collision management
 * 
 * @author Spencer Jewett (U0677872)
 * @since 11/9/2016
 *
 */
public class ChainingHashTable implements Set<String> {
	private int size;
	private HashFunctor hasher;
	private LinkedList<String>[] storage;
	private int tableSize;
	private int collisions = 0;
	
	/**
	 * Constructor for the HashTable
	 * 
	 * @param capacity - the length of the table
	 * @param functor - the hash functor to be used
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor){
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		tableSize = capacity;
		hasher = functor;
		size = 0;
	}

	/**
	 * Adds a string to the HashTabe
	 * 
	 * @param item - a String to be added to the table
	 * 				 If null, NullPointerException is thrown.
	 * @return - true if the item was added, false if otherwise.
	 */
	public boolean add(String item) {
		if(item == null){
			throw new NullPointerException();
		}
		int index = (hasher.hash(item) % tableSize);

		if(storage[index] == null){ // if it hasn't been started yet
			LinkedList<String> start = new LinkedList<String>(); // create a linked list
			if(start.add(item)){// add item to the linked list
				size++;
				storage[index] = start; // put the linked list into the LinkedList of linked lists at the index
			}
			
		}
		else{
			collisions++;
			LinkedList<String> temp = storage[index];  // create a temporary LinkedList
			if(temp.add(item)){ //append the item into the list
				size++;
				storage[index] = temp; // dump the updated list back into the linkedList
			}
			
		}
		
		if(!contains(item)){
			return false;
		}
		return true;
	}

	/**
	 * Function to add all the Strings in a collection to the HashTable
	 * 
	 * @param items -  a collection of strings to be added to the HashTable
	 * 					If null, NullPointerException is thrown.
	 * @return - true if all Strings were added, false if otherwise
	 */
	public boolean addAll(Collection<? extends String> items) {
		boolean result;
		for(String item : items){
			if(item == null){
				throw new NullPointerException();
			}
			result = add(item);
			
			if(!result){
				return false;
			}
		}
		return true;
	}

	/**
	 * Clears the array of data 
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		storage = (LinkedList<String>[]) new LinkedList[tableSize];
		size = 0;
	}

	/**
	 * Checks if the HashTable contains a specific string
	 * 
	 * @param item - a string (to be sought for) If null, NullPointerException is thrown.
	 * @return - true if the HashTable contains the specific item, false if otherwise.
	 */
	public boolean contains(String item) {
		if(item == null){
			throw new NullPointerException();
		}
		
		if(isEmpty()){
			return false;
		}
		int itemHash = hasher.hash(item) % tableSize;
		LinkedList<String> temp = storage[itemHash];
		if(temp.contains(item)){
			return true;
		}
		return false;
	}

	/**
	 * Checks if the HashTable contains all of the items in a collection of Strings
	 * 
	 * @param items - a collection of strings
	 * @return - true if all items in the collection are in the HashTable, false if otherwise
	 */
	public boolean containsAll(Collection<? extends String> items) {
		for (String item : items){
			if (item == null){
				throw new NullPointerException();
			}
			
			if(!contains(item)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if the HashTable is Empty
	 * 
	 * @return - true if the table is empty, false if otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * fetches the size of the HashTable
	 * 
	 * @return - an integer of the size of the table
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Access the number of collisions within the table
	 * 
	 * @return the number of collisions in the current iteration of
	 * 			the table
	 */
	public int collisionCount(){
		return collisions;
	}

}
