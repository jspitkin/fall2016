package assignment10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {
	
	private LinkedList<String>[] table;
	private HashFunctor hasher;
	private int size;
	private int numLists;
	private int numCollisions = 0;
	
	/** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		table = new LinkedList[getNextPrime(capacity)];
		hasher = functor;
		size = 0;
		numLists = 0;
	}

	@Override
	public boolean add(String item) {
		int index = hasher.hash(item) % table.length;
		boolean changed = false;
		
		//if a list already exists at the index in the table, add the item to the list
		if(table[index] != null && !table[index].contains(item)){
			table[index].add(item);
			numCollisions++;// adding an item to a table counts as a single collisions
			size++;
			changed = true;
		}
		//if no list exists at that index, create a new list
		else if(table[index] == null){
			table[index] = new LinkedList<String>();
			numLists++;
			table[index].add(item);
			size++;
			changed = true;
		}
		//if a list already exists, and contains the item
		else
			changed = false;
		
		checkAverageListLength();
		
		return changed;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for(String item : items){
			if(add(item))
				changed = true;
		}
		return changed;
	}

	@Override
	public void clear() {
		@SuppressWarnings("unchecked")
		LinkedList<String>[] newTable = new LinkedList[table.length];
		size = 0;
		numLists = 0;
		numCollisions = 0;
		table = newTable;
	}

	@Override
	public boolean contains(String item) {
		int index = hasher.hash(item)%table.length;
		
		if(table[index] != null && table[index].contains(item))
			return true;
		else
			return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String item : items){
			if(!contains(item))
				return false;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return(size == 0);
	}

	@Override
	public int size() {
		return size;
	}
	
	/**
	 * @return the number of collisions the table has resolved
	 */
	public int getCollisions(){
		return numCollisions;
	}
	
	/**
	 * calculates the average number of strings in each chained list and rehashes
	 * the table if the load factor exceeds 10 (10 items per index in the table)
	 * @return the average number of strings in each list
	 */
	private void checkAverageListLength(){
		int totalItems = size;
		int totalLists = numLists;

		int averageListSize = totalItems/totalLists;
		
		if(averageListSize/table.length >= 10)
			reHash();
	}

	/**
	 * recreates the table with double the size of the original.
	 */
	private void reHash() {
		@SuppressWarnings("unchecked")
		LinkedList<String>[] newTable = new LinkedList[getNextPrime(table.length*2)];
		ArrayList<LinkedList<String>> tempLists = new ArrayList<LinkedList<String>>();
		
		for(LinkedList<String> list : table){
			tempLists.add(list);
		}
		
		clear();
		table = newTable;
		
		for(LinkedList<String> list : tempLists){
			addAll(list);
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

}
