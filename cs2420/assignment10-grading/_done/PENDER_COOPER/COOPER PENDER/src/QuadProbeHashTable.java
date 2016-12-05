package assignment10;

import java.util.Collection;

/**
 * This class represents a hash table, which uses 
 * quadratic probing to resolve any collisions.
 * 
 * @author Cooper Pender (u0843147)
 * Last Edited On: 11/5/2016
 *
 */
public class QuadProbeHashTable implements Set<String>{

	private int size;
	private int collisions;
	private String[] backArr;
	private HashFunctor hashFunc;
	
	
	/**
	 * Constructs a hash table of the given 
	 * capacity that uses the hashing function specified by the functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		
		if(isPrime(capacity)) { //Checking capacity entered, if its prime or not
			backArr = new String[capacity];
		}
		else {
			backArr = new String[nextPrime(capacity)]; //if not go to next prime for capacity.
		}
		size = 0;
		collisions = 0;
		hashFunc = functor;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean add(String item) {
		
		if(item == null) {
			throw new NullPointerException();
		}
		
		if((double)size / backArr.length >= 0.5) { //if load capacity is met, resize and rehash
			
			String[] oldArr = backArr;
			int tempSize = size;
			backArr = new String[nextPrime(backArr.length*2)];
			for(int i = 0; i < oldArr.length; i++) {
				if(oldArr[i] != null) {
					add(oldArr[i]); //adding here takes care of rehashing
				}	
			}
			size = tempSize;
		}
		
		if(contains(item)) { //make sure no duplicates
			return false;
		}
		
		int index = findIndex(item, true);
		
		backArr[index] = item;
		size++;
		return true;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean addAll(Collection<? extends String> items) {
		boolean result = false;
		for(String element : items) {
			if(!contains(element)) {
				add(element);
				result = true;
			}
		}
		return result;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public void clear() {
		backArr = new String[backArr.length];
		size = 0;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean contains(String item) {	
		
		if(item == null) {
			throw new NullPointerException();
		}	
		
		int index = findIndex(item, false);
		
		if(backArr[index] == item) {
			return true;
		}
		
		return false;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean containsAll(Collection<? extends String> items) {
		for(String element : items) {
			if(!contains(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Private method that will conduct a quadratic probe
	 * if the specified index is occupied.
	 * 
	 * @param item - item to insert into hashtable
	 * @return - the index at which to insert the item
	 */
	private int findIndex(String item, boolean collisionsOn) {
		
		int index = (hashFunc.hash(item) % backArr.length); //generate index through hashing and moding	
		int quadIndex = index;
		int quad = 1;
		
			while(backArr[index] != null && backArr[index] != item) {
				
				if(collisionsOn) {
					collisions++;
				}
				index = (quadIndex + quad * quad++) % backArr.length;
				
				if(index > backArr.length - 1) {
					index = index - backArr.length;
				}
			}
			return index;
		}
	/**
	 * Checks if a number is prime or not.
	 * 
	 * @param input - The number to check.
	 * @return - True if prime, false otherwise.
	 */
	private boolean isPrime(int input) {
		
		if(input > 2 && input % 2 == 0) {
			return false;
		}
		for(int i = 2; i < input; i++) {
			if(input % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Gives the next prime number after a 
	 * given input.
	 * 
	 * @param input - The number to jump from.
	 * @return - The new prime number.
	 */
	private int nextPrime(int input) {
		
		int newPrime = input + 1;
		
		while(!(isPrime(newPrime))) {
			newPrime += 1;
		}
		return newPrime;
	}
	
	/**
	 * Returns the number of collisions incured by adding to this table.
	 * @return - number of collisions
	 */
	public int getCollisions() {
		return collisions;
	}
}
