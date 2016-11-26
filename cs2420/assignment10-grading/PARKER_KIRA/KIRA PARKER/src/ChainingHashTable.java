package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * 
 * @author Kira Parker u1073760
 *
 */
public class ChainingHashTable implements Set<String>{
	private LinkedList<String>[] storage;
	private int size;
	private HashFunctor functor;
	private int capacity;
	private int numberOfCollisions;
	
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor){
		this.capacity = getNextPrimeNumber(capacity);
		storage = (LinkedList<String>[]) new LinkedList[this.capacity];
		size = 0;
		this.functor = functor;
		numberOfCollisions = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(String item) {
		if(item == null){
			throw new NullPointerException();
		}
		if(size/capacity >= 10){
			rehash();
		}
		return addToArray(item, storage);
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
	 * when array is more than half full, resizes array to next prime number and rehashes elements
	 * to add them to the temporary array. then sets storage to temporary array. 
	 */
	private void rehash(){
		size = 0;
		numberOfCollisions = 0;
		capacity = getNextPrimeNumber(capacity+1);
		@SuppressWarnings("unchecked")
		LinkedList<String>[] temp = (LinkedList<String>[]) new LinkedList[this.capacity];
		for(int index = 0; index<storage.length; index++){
			if(storage[index] != null){
				for (String element: storage[index]){
					addToArray(element, temp);
				}
			}
		}
		storage = temp;
	}
	
	/**
	 * adds element to given array using hash function (if element is not already in array)
	 * @param item -- item to be added to the array
	 * @param arr -- array that the item is to be added to
	 * @return true if the element is added, false if the element was already contained in the array
	 */
	private boolean addToArray(String item, LinkedList<String>[] arr) {
		int location = functor.hash(item)%capacity; //index in array where item will go
		if(arr[location] == null){ //need to create linked list at this index in the array
			arr[location] = new LinkedList<String>();
		} 
		else{
			for (String element: arr[location]){
				if(element.equals(item)){ //item already in the hash table
					return false;
				}
			}
		}
		if(arr[location].size() >0){
			numberOfCollisions++; 
		}
		arr[location].add(item);
		size++;
		return true;
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
	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(String item) {
		if(item == null){
			throw new NullPointerException();
		}
		int location = functor.hash(item)%capacity; //index in array where item will go
		if(storage[location] == null){ //need to create linked list at this index in the array
			return false;
		} else{
			for (String element: storage[location]){
				if(element.equals(item)){ //item already in the hash table
					return true;
				}
			}
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
	 * Used for testing purposes
	 * @return the number of collisions incurred in the hash table (element maps to location in array that is occupied by another element)
	 */
	public int getNumberOfCollisions(){
		return numberOfCollisions;
	}

}
