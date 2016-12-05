package assignment10;

import java.util.Collection;

/**
 * 
 * @author Christian Hansen, u0621884
 * 
 * This class is a representation of Hash Table that implements quadratic probing. There
 * are different methods to allow the user to add, check size, etc. The capacity will 
 * always be prime which helps with distributing the strings
 *
 */
public class QuadProbeHashTable implements Set<String>{
	
	private String[] table;
	private int size;
	private HashFunctor functor;
	private int originalCapacity;
	
    /** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
    public QuadProbeHashTable(int capacity, HashFunctor functor){
    	
    	int primeCapacity = getNextHighestPrime(capacity);
    	this.originalCapacity = primeCapacity;

    	this.functor = functor;
    	size = 0;
    	table = new String[primeCapacity];
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
			this.copyTable();
		}
		
		boolean result = this.placeItem(table, item);
		
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
	@Override
	public void clear() {
		this.size = 0;
		this.table = new String[originalCapacity];
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

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return size;
	}
	
	// --------------------- Private Helper Methods ------------------
	
	/**
	 * Checks if the tables capacity is over 50% used. True if it is and
	 * false if it is not.
	 * 
	 * @return true or false
	 */
	private boolean checkLoad(){
		if((size / (double) table.length) > .5){
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Adds the string given to the array given. The index the item is added to
	 * is found by hashing the item and then using quadratic probing if necessary.
	 * 
	 * @param array - the array to which the item is added
	 * @param item - the String that is added
	 * @return true if successfully added, false if not
	 */
	private boolean placeItem(String[] array, String item){
		
		int hashNumber = functor.hash(item);
		int quadNum = 0;
		int position = 0;
		
		while(quadNum < (array.length*2)){
			
			position = ( hashNumber + (quadNum*quadNum) ) % array.length;
			
			if(array[position] == null){
				array[position] = item;
				return true;
			}else if(array[position].equals(item)){
				return false;
			}
			
			quadNum++;
		}
		return false;
	}
	
	/**
	 * searchForItem looks for the item given as a parameter to see if 
	 * it is found inside the member variable table. It uses hashing to look
	 * for the item, if it is not at the spot where hashing states it should be
	 * then it begins checking the spots to the right of it using quadratic
	 * probing. If it ever encounters null then the item is not found in the array
	 * and the method returns false.
	 * 
	 * @param item - the string to be looked for
	 * @return true if found, false if not
	 */
	private boolean searchForItem(String item){
		
		int hashNumber = functor.hash(item);
		int quadNum = 0;
		int position = 0;
		
		while(quadNum < (table.length*2)){
			
			position = ( hashNumber + (quadNum*quadNum) ) % table.length;
			
			if(table[position] == null){
				return false;
			}else if(table[position].equals(item)){
				return true;
			}
			
			quadNum++;
		}
		return false;
	}
	
	/**
	 * copyTable will double size of the array (or a little more since
	 * it goes to the next highest prime number) and then re hashes all of 
	 * the data. It does this by creating a temporary array and then at the
	 * end setting table to point to the same data that the tempTable points
	 * to.
	 */
	private void copyTable(){
		int newCap = getNextHighestPrime(table.length*2);
		String[] tempTable = new String[newCap];
		
		for(int index = 0; index < table.length; index++){
			if(table[index] != null){
				placeItem(tempTable, table[index]);
			}
		}
		
		table = tempTable;
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
