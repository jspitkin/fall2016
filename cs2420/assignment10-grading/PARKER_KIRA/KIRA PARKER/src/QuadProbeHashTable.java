package assignment10;

import java.util.Collection;

/**
 * 
 * @author Kira Parker u1073760
 *
 */
public class QuadProbeHashTable implements Set<String>{
	private String[] storage;
	private int size;
	private int capacity;
	private HashFunctor functor;
	private int numberOfCollisions;
	
	/** 
	 * Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
   public QuadProbeHashTable(int capacity, HashFunctor functor){
	   this.capacity = getNextPrimeNumber(capacity);
	   storage = new String[this.capacity];
	   size = 0;
	   this.functor = functor;
	   numberOfCollisions = 0; 
   }
   
   /**
    * finds first prime number larger than or equal to num
    * @param num -- number that prime number cannot be smaller than
    * @return next prime number above num
    */
   private int getNextPrimeNumber(int num){
	   while(!isPrime(num)){
		   num++;
	   }
	   return num;
   }
   
   /**
    * @param num -- number to check if prime
    * @return true if num is prime, false otherwise
    */
   private boolean isPrime(int num){
	   for(int factor = 2; factor <= Math.sqrt(num); factor ++){
		   if(num%factor == 0){
			   return false;
		   }
	   }
	   return true;
   }

   /**
    * {@inheritDoc}
    */
	@Override
	public boolean add(String item){
		if(item == null){
			throw new NullPointerException();
		}
		if((double)size/capacity >= .5){
			rehash();
		}
		return addToArray(item, storage);
	}
	
	/**
	 * adds element to given array using hash function (if element is not already in array)
	 * @param item -- item to be added to the array
	 * @param arr -- array that the item is to be added to
	 * @return true if the element is added, false if the element was already contained in the array
	 */
	private boolean addToArray(String item, String[] arr) {
		int location = functor.hash(item)%capacity;
		int newLocation = location;
		int count = 1;
		while(arr[newLocation] != null){
			if(arr[newLocation].equals(item)){
				return false;
			}
			newLocation = (location + count^2)%capacity;
			count++;
		}
		if(newLocation != location){
			numberOfCollisions++;
		}
		arr[newLocation] = item;
		size += 1;
		return true;
	}

	/**
	 * when array is more than half full, resizes array to next prime number and rehashes elements
	 * to add them to the temporary array. then sets storage to temporary array. 
	 */
	private void rehash(){
		size = 0;
		numberOfCollisions = 0;
		capacity = getNextPrimeNumber(capacity+1);
		String[] temp = new String[capacity];
		for(int index = 0; index<storage.length; index++){
			if(storage[index] != null){ //if there is an item in the old array index, add it to the new array
				addToArray(storage[index], temp);
			}
		}
		storage = temp;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for (String item: items){
			changed = add(item) || changed;
		}
		return changed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		size = 0;
		storage = new String[capacity];
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(String item) {
		if(item == null){
			throw new NullPointerException();
		}
		int location = functor.hash(item)%capacity;
		int newLocation = location;
		int count = 1;
		while(storage[newLocation] != null){
			if(storage[newLocation].equals(item)){
				return true;
			}
			newLocation = (location + count^2)%capacity;
			count++;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String item:items){
			if(!contains(item)){
				return false;
			}
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * For testing purposes to count the number of collisions that have occured so far in the hash table
	 * @return the number of collisions incurred
	 */
	public int getNumberOfCollisions(){
		return numberOfCollisions;
	}

}
