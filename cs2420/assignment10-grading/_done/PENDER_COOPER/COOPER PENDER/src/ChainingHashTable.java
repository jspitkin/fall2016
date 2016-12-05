package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class represents a hash table which 
 * will resolve collisions through seperate chaining.
 * 
 * @author Cooper Pender (u0843147)
 * Last Edited On: 11/5/2016
 *
 */
public class ChainingHashTable implements Set<String> {

	private LinkedList<String>[] storage;
	private int size;
	private int collisions;
	private HashFunctor hashFunc;
	
	//default constructor
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		
		if(isPrime(capacity)) { 
			storage = (LinkedList<String>[]) new LinkedList[capacity];
		}
		else {
			storage = (LinkedList<String>[]) new LinkedList[nextPrime(capacity)]; 
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
		if(contains(item)) {
			return false;
		}
		int index = (hashFunc.hash(item) % storage.length);
		
		if(storage[index] != null) {
			collisions++;
		}
		
		if(storage[index] == null) { //make new linkedlist at index if not already made.
			storage[index] = new LinkedList<String>();
		}
		storage[index].add(item);
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

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * @inheritDoc
	 */
	public void clear() {
		storage = (LinkedList<String>[]) new LinkedList[storage.length];
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
		int index = (hashFunc.hash(item) % storage.length);
		LinkedList<String> targList = storage[index];
		if(targList == null) {
			return false;
		}
		for(String element : targList) {
			if(element == item) {
				return true;
			}
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
	 * This returns the number of collisions incured by adding to this set.
	 * 
	 * @return - number of collisions
	 */
	public int getCollisions() {
		return collisions;
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
}
