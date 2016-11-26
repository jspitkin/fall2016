package assignment10;

import java.util.Collection;

/**
 * Creates a hash table of Strings of the given capacity size, and using the hash function.
 * @author Jana Klopsch (u0854469)
 * Last Modified 11-9-16
 *
 */
public class QuadProbeHashTable implements Set<String>{

	public String[] hashArray;
	private int size;
	public int capacity;
	public int collisions;
	public HashFunctor functor;

	/** Constructs a hash table of the given capacity that uses the hashing function
	 * specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor){

		hashArray = new String[capacity];
		size = 0;
		this.capacity = capacity;
		this.functor = functor;
		collisions = 0;
	}

	@Override
	public boolean add(String item) {

		//Check to see if load capacity > .5
		if(size > capacity/2){
			capacity = nextPrime(capacity*2);
			QuadProbeHashTable rehash = new QuadProbeHashTable(capacity, functor);
			size = 0;
			//rehash all items from first hash table into the new one, then add the item initially trying to add
			for(int index = 0; index < hashArray.length; index++){
				if(hashArray[index] != null){
					rehash.add(hashArray[index]);
					size++;
				}
			}
			hashArray = rehash.hashArray;
			return rehash.add(item);
		}

		//calculate hash value of item
		int hashVal = functor.hash(item);

		//mod by capacity
		int mapVal = hashVal % capacity;

		//add in that index -- if full quad probing
		if(hashArray[mapVal] == null){
			hashArray[mapVal] = item;
			size++;
			return true;
		}
		int quad = 1;
		while(hashArray[mapVal] != null){
			collisions++;
			mapVal = mapVal + quad*quad;	//use quadratic probing to find next valid index
			mapVal = mapVal % capacity;
			quad++;
		}
		hashArray[mapVal] = item;
		size++;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean flag = true;
		boolean temp;
		for(String element : items){
			temp = add(element);
			if(!temp){
				flag = false;
			}
		}
		return flag;
	}


	@Override
	public void clear() {
		for(int index = 0; index < hashArray.length; index++){
			hashArray[index] = null;
		}
		size = 0;
	}

	@Override
	public boolean contains(String item) {

		//calculate hash value of item
		int hashVal = functor.hash(item);
		//mod by capacity
		int mapVal = hashVal % capacity;
		int quad = 1;
		int count = 0;

		while(count < capacity){
			if(hashArray[mapVal] != null){
				if(hashArray[mapVal].equals(item)){	//found!
					return true;
				}
			}
			mapVal = mapVal + quad*quad;	//use quadratic probing to find next valid index
			mapVal = mapVal % capacity;
			quad++;
			count++;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean flag = true;
		for(String element : items){
			if(!this.contains(element)){
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean isEmpty() {
		if(size() == 0){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * Identifies if a number is prime or not
	 * @param value - the number being identified
	 * @return - true if value is prime, false otherwise
	 */
	public boolean isPrime(int value){
		//Assumes value is non-negative
		if(value <= 2){ //Values 0, 1, 2 are prime
			return true;
		}
		if(value % 2 == 0){
			return false;
		}
		for(int square = 3; (square*square) <= value; square += 2){
			if(value % square == 0){
				return false;
			}
		}
		return true;
	}

	/**
	 * Calculates the next largest prime number following current
	 * @param current - the number to start at
	 * @return - next largest integer after current
	 */
	public int nextPrime(int current){
		current++;
		while(!isPrime(current)){
			current++;
		}
		return current;
	}


}
