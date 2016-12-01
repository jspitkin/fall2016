package assignment10;

import java.util.ArrayList;
import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {
	
	private String[] table;
	private HashFunctor hasher;
	private int size = 0;
	private int collisionCount = 0;
	
	/** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
	   table = new String[getNextPrime(capacity)];
	   hasher = functor;
	}

	@Override
	public boolean add(String item) {
		int index = hasher.hash(item)%table.length;
		boolean changed = false;
		
		if(table[index] == null){
			table[index] = item;
			size++;
			changed = true;
		}
		else if(table[index].equals(item)){
			changed = false;
		}
		else{
			index = quadProbe(index, item);
			
			if(table[index] == item)
				changed = false;
			else{
				table[index] = item;
				size++;
				changed = true;
			}
		}
		
		checkLoadFactor();
		
		return changed;
	}
	
	/**
	 * Checks the number of entries against the size of the backing array, and rehashes if necessary
	 */
	private void checkLoadFactor() {
		if(size > table.length/2){
			reHash();
		}
	}

	/**
	 * Iterates through the table quadratically to find either a null space in the array, 
	 * or the specified item
	 * @param index the index to start the probing from
	 * @param item the item to check the table for
	 * @return the index where the item can be placed, or where it already exists
	 */
	private int quadProbe(int index, String item) {
		int newIndex = index;
		int tempIndex = newIndex;
		int i = 0;
		while(table[tempIndex] != null){
			tempIndex = (int) (newIndex + (Math.pow(i, 2)));
			collisionCount++;
			//keep the tempIndex inside the limits of the table
			if(tempIndex >= table.length)
				tempIndex = tempIndex%table.length;
			
			if(table[tempIndex] == null || table[tempIndex].equals(item)){
				newIndex = tempIndex;
				break;
			}

			i++;
		}
		return newIndex;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for(String string : items)
			if(add(string))
				changed = true;
		
		return changed;
	}

	@Override
	public void clear() {
		table = new String[table.length];
		size = 0;
		collisionCount = 0;
	}

	@Override
	public boolean contains(String item) {
		int hash = hasher.hash(item)%table.length;
		
		int index = quadProbe(hash, item);
		
		if(table[index] == item)
			return true;
		else
			return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String string: items)
			if(!contains(string))
				return false;
		
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
	
	/**
	 * @return the number of collisions the table has resolved
	 */
	public int getCollisions(){
		return collisionCount;
	}
	
	/**
	 * doubles the size of the backing array and re-inserts each item
	 */
	private void reHash(){
		String[] tempArray = table.clone();
		
		clear();
		table = new String[getNextPrime(table.length*2)];
		
		for(int i = 0; i < tempArray.length; i++){
			if(tempArray[i] != null)
				add(tempArray[i]);
		}
	}
	
	/**
	 * returns the next prime number after the given integer
	 * @param n the number being checked
	 * @return n if n is prime, otherwise return the next largest prime number
	 */
	private int getNextPrime(int n){
		int i = n;
		while(true){
			if(isPrime(i))
				return i;
			else
				i++;
		}
	}

	//checks whether an int is prime or not.
	boolean isPrime(int n) {
	    //check if n is a multiple of 2
	    if (n%2==0) return false;
	    //if not, then just check the odds
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
	
	/**
	 * Allows for easy printing of the backing table array
	 * @return an ArrayList representing the table
	 */
	public ArrayList<String> toArray(){
		ArrayList<String> retArray = new ArrayList<String>();
		for(int i = 0; i < table.length; i++)
			retArray.add(table[i]);
		
		return retArray;
	}
}
