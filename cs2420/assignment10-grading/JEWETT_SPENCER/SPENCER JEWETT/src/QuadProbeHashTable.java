package assignment10;

import java.util.Collection;

/**
 * HashTable that uses A Quadratic Probe strategy for collision management.
 * 
 * @author Spencer Jewett (U0677872)
 * @since 11/9/2016
 *
 */
public class QuadProbeHashTable implements Set<String>{
	private int size;
	private String[] table;
	private int originalCapacity;
	private HashFunctor hasher;
	private int collisions = 0;
	
	  /** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     * 
     * @param capacity - the size of the backing array
     * @param functor - Hash functor to be used in this table
     */
   public QuadProbeHashTable(int capacity, HashFunctor functor){
	   size = 0;
	   if(!isPrime(capacity)){
		   capacity = nextBiggestPrime(capacity);
	   }
	   originalCapacity = capacity;
	   table = new String[capacity];
	   hasher = functor;

   }

	/**
	 * Adds a string to the HashTabe
	 * 
	 * @param item - a String to be added to the table
	 * 				 If null, NullPointerException is thrown.
	 * @return - true if the item was added, false if no place
	 * 				was found in the table to add the item.
	 */
	public boolean add(String item) {
		if (item == null){
			throw new NullPointerException();
		}
		tableGrowthManager();
		int capacity = table.length;
		int index = hasher.hash(item) % capacity;
		int modifier = 0;
		while(table[index] != null){
			modifier++;
			collisions++;
			index = (index + ((int)Math.pow(modifier, 2))) % capacity;
			if(modifier == (capacity)){ 
				return false;
			}
		}
		
		table[index] = item;
		size++;
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
	public void clear() {
		table = new String[originalCapacity];
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
		int capacity = table.length;
		int index = hasher.hash(item) % capacity;
		int modifier = 0;
		if(table[index] == null || !table[index].equals(item) ){

			while(table[index] != item){
				modifier++;
				index = (index + ((int)Math.pow(modifier, 2))) % capacity;
				if(modifier == capacity){
					return false;
					}
				}
		}
		return true;
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
	 * Determines if the number inputed is prime
	 * 
	 * @param questionableCapacity - number to be checked
	 * @return - true if the number is prime, false if otherwise
	 */
	private boolean isPrime(int questionableCapacity){
		if(questionableCapacity % 2 == 0){
			return false;
		}
		
		for(int i = 3; i * i <= questionableCapacity; i += 2){
			if(questionableCapacity % i == 0){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Determines the next biggest prime number to the one inputed
	 * 
	 * @param currentCapacity - the number at which we want to find the next prime 
	 * 							number of greater value
	 * @return integer of the next prime number of greater value
	 */
	private int nextBiggestPrime(int currentCapacity){
		int number = currentCapacity++;
		while(!isPrime(number)){ 
			number++;
		}
		return number;
	}
	
	/**
	 * Manages the table capacity to account for the current anti-collision strategy
	 * (QuadProbe)
	 * Will increase the size of the table by to the next largest prime number from the product of doubling 
	 * the length of the old table. Then it will  put all data in the old table into the new larger one
	 * (rehashed and inserted)
	 */
	private void tableGrowthManager(){
		if (size >= table.length/2){
			String[] oldTable = table.clone();
			table = new String[nextBiggestPrime(table.length * 2)];
			size = 0;
			for(String item : oldTable){
				if(item != null){
					add(item);
				}
			}
		}
	}
	
	/**
	 * Counts the collisions within the program
	 * @return - the number of collisions for the set;
	 */
	public int collisionCounter(){
		return collisions;
	}
   
}
