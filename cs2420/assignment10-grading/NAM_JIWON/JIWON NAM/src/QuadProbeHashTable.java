package assignment10;

import java.util.Collection;

/**
 * This is the class refer Quadratic Probe Hash Table
 * with String inputs
 * @author Jiwon Nam
 */
public class QuadProbeHashTable implements Set<String>{

	private int originCapacity;
	private int capacity;
	private int size;
	private int collision;
	private double loadFactor;
	private HashFunctor functor;
	private String[] table;
	
	/** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
   public QuadProbeHashTable(int capacity, HashFunctor functor) {
	   
	   originCapacity = capacity;
	   
	   if(!isPrime(capacity))		   
		   this.capacity = findNextPrime(capacity);
	   else
		   this.capacity = capacity;
	   
	   this.functor= functor;
	   size = 0;
	   loadFactor = 0.5;
	   collision = 0;
	   table = new String[this.capacity];
   }
	
	@Override
	public boolean add(String item) {
		
		if(item == null)
			return false;
		
		int index = functor.hash(item) % capacity;	// set hash num
		
		index = quadProbing(index, item); // collision
		
		if(index == -1)
			return false;
		
		table[index] = item;
		size++;
		double lamda =  (double) size / (double) capacity;
		
		if(loadFactor < lamda) // check lamda
			rehash();
		
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends String> items) {
		
		if(items.isEmpty())
			return false;
		
		boolean isAdded = false;
		
		for(String item : items) {
			if(add(item))
				isAdded = true;
		}
		return isAdded;
	}

	@Override
	public void clear() {
		capacity = originCapacity;
		size = 0;
		table = new String[capacity];
		loadFactor = 0.5;
		collision = 0;
	}

	@Override
	public boolean contains(String item) {
		if(item == null)
			return false;
		
		int isContained = quadProbing(functor.hash(item) % capacity, item);
		
		if(isContained == -1)
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
	 * helper method for setting next prime number as capacity
	 */
	private int findNextPrime(int num) {
		
		int prime = num + 1;
		while(!isPrime(prime)) {
			prime++;
		}
		
		return prime;
	}
	
	/**
	 * helper method to find prime number, if input is prime number, return true
	 * false otherwise
	 * @param num, input number
	 * @return boolean, true or false
	 */
	private boolean isPrime(int num) {
		if(num == 1 || num == 2)
			return true;
		
		int i = num - 1;
		while(i > 2) {
			if(num % i == 0)
				return false;
			i--;
		}
		return true;
	}
	
	/**
	 * helper method to rehash the table
	 */
	private void rehash() {
		int oldCapacity = capacity;
		
		String[] temp = new String[oldCapacity];
		for(int i = 0; i < oldCapacity; i++) {
			temp[i] = table[i];
		}
		capacity = findNextPrime(oldCapacity * 2);
		
		table = new String[capacity];
		
		for(int i = 0; i < oldCapacity; i++) {
			String item = temp[i];
			if(item != null) {
				
				int index = functor.hash(item) % capacity;
				index = quadProbing(index, item);
				table[index] = item;
			}
		}		
	}
	
	/**
	 * it is a helper method to find next index using quadratic Probing 
	 * if it find a word already input, it will return -1, else, return next index
	 * @param index, input hash number for this item
	 * @param item, item to compare it is already in the set
	 * @return if contains item, return -1, otherwsie position
	 */
	private int quadProbing(int index, String item) {
		int position = index;
		int inc = 1;	// number increment
	
		while(table[position] != null) {
			if(table[position].equals(item)) 	// if table already inserted item
				return -1;
			
			position = (index + inc * inc) % capacity;
			inc++;	
			collision++;
			position = Math.abs(position);
		}
		return position;
	}
	
	/**
	 * helper method to return string array that stored inputs
	 * 
	 * @return String array, table
	 */
	public String[] getTable() {
		return table;
	}
	
	/**
	 * helper method to return capacity
	 * 
	 * @return int, capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	public void setLoadFactor(double loadFactor) {
		this.loadFactor = loadFactor;
	}
	
	public int numOfCollision() {
		return collision;
	}
}
