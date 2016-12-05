package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * @author Christian Hansen, u0621884
 * 
 * This class is a representation of Hash Table that implements linked lists. There
 * are different methods to allow the user to add, check size, etc. The capacity will 
 * always be prime which helps with distributing the strings. If a collision occurs the
 * item is just added on to the linked list at that location.
 *
 */
public class ChainingHashTable implements Set<String>{
	
	private LinkedList<String>[] storage;
	private int size;
	private HashFunctor functor;
	private int originalCapacity;
	
	/**
	 * Constructor that allows the user to set the original size of the array and
	 * the functor object that will be used for hashing. if the capacity given is 
	 * not prime then the next highest prime number is used.
	 * 
	 * @param capacity
	 * @param functor
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor){
		
    	int primeCapacity = getNextHighestPrime(capacity);
    	this.originalCapacity = primeCapacity;
    	
		storage = (LinkedList<String>[]) new LinkedList[primeCapacity];
		size = 0;
		this.functor = functor;
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
	public boolean add(String item) throws NullPointerException{
		
		if(item == null){
			throw new NullPointerException();
		}
		
		if(this.checkLoad()){
			this.copyStorage();
		}
		
		boolean result = this.placeItem(storage, item);
		
		if(result){
			size++;
		}
		
		return result;
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
		boolean wasChanged = false;
		
		for(String item : items){
			boolean tempResponse = add(item);
			
			if(tempResponse && !wasChanged){
				wasChanged = true;
			}
		}	
		return wasChanged;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.size = 0;
		this.storage = (LinkedList<String>[]) new LinkedList[originalCapacity];
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
	public boolean contains(String item) throws NullPointerException{
		
		if(item == null){
			throw new NullPointerException();
		}

		return this.searchForItem(item);
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
		boolean wasChanged = true;
		
		for(String item : items){
			boolean tempResponse = contains(item);
			
			if(!tempResponse && wasChanged){
				wasChanged = false;
			}
		}	
		return wasChanged;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}
	
	// ---------- Private Helper Methods ---------------
	
	/**
	 * Checks the load of storage. returns true if the load
	 * is above what it should be and storage needs to be expanded.
	 * 
	 * @return true or false
	 */
	private boolean checkLoad(){
		if((size / (double) storage.length) > 6){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * copyStorage creates a new temporary LinkedList array, using hashing to
	 * copy all the old data into it. Then it sets the storage to equal tempStorage
	 * thus copying over all the data to a new larger array of linked lists. The
	 * new capacity is the next highest prime number above that number.
	 *
	 */
	@SuppressWarnings("unchecked")
	private void copyStorage(){
		int newCap = getNextHighestPrime(storage.length*2);
		LinkedList<String>[] tempStorage = (LinkedList<String>[]) new LinkedList[newCap];
		
		for(int index = 0; index < storage.length; index++){
			if(storage[index] != null){
				while(storage[index].peek() != null){
					placeItem(tempStorage, storage[index].pop());
				}
			}
		}
		storage = tempStorage;
	}
	
	/**
	 * Adds the string given to the array given. The index the item is added to
	 * is found by hashing the item and placing in the linked list at the position
	 * of the array..
	 * 
	 * @param array - the array to which the item is added
	 * @param item - the String that is added
	 * @return true if successfully added, false if not
	 */
	private boolean placeItem(LinkedList<String>[] arrayOfLinks, String item){
		int position = functor.hash(item) % arrayOfLinks.length;
		
		if(arrayOfLinks[position] == null){
			arrayOfLinks[position] = new LinkedList<String>();
		}
		
		if(!arrayOfLinks[position].contains(item)){
			arrayOfLinks[position].add(item);
			return true;
		}
		return false;
	}
	
	/**
	 * Searches for the item by hashing the item and looking at that LinkedList. If
	 * it is found in that linked list it returns true or if not false. If the linkedList has
	 * not even been initialized it is also false.
	 * 
	 * @param item - the String to be searched for
	 * @return true if it is found, false if not
	 */
	private boolean searchForItem(String item){
		int position = functor.hash(item) % storage.length;
		
		if(storage[position] == null){
			return false;
		}
		
		return storage[position].contains(item);
	}
	
	/**
	 * getNextHighestPrime will get the next highest prime number above
	 * the number that is given. If the number given is a prime number it
	 * will just return that number
	 * 
	 * @param capacity - an integer
	 * @return current - The next highest prime number above capacity
	 */
	private int getNextHighestPrime(int capacity) {
		
		int current = capacity;
		
		while(!checkIfPrime(current)){
			current++;
		}
		return current;
	}
	
	/**
	 * Checks if the number given is a prime number. It does so by checking if
	 * any numbers between 1 and the square root of the number cleanly divide the
	 * number. If none do then it is a prime number.
	 * 
	 * @param number - integer to be checked for being prime
	 * @return true if prime, false if not.
	 */
	private boolean checkIfPrime(int number){
		for(int index = (int) Math.sqrt(number); index > 1; index--){
			if(number % index == 0){
				return false;
			}
		}
		return true;
	}

}
