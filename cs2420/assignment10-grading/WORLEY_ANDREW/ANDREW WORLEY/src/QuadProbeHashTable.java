package assignment10;

import java.util.Collection;

/**
 * QuadProbe hash table implementation
 * @author Andrew Worley: u0651238
 */
public class QuadProbeHashTable implements Set<String>{
	private int size;
	private int initialCapacity;
	private String[] backArray;
	private HashFunctor hasher;
	private int collisions;
	
	/** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
   public QuadProbeHashTable(int capacity, HashFunctor functor) {
	   size = 0;
	   hasher = functor;
	   if (isPrime(capacity)) {
		   initialCapacity = capacity;
	   }
	   else {
		   initialCapacity = getNextPrime(capacity);
	   }
	   backArray = new String[initialCapacity];
	   collisions = 0;
   }

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean add(String item) throws NullPointerException {
		if (contains(item)) {
			return false;
		}
		
		int hashIndex = hasher.hash(item) % backArray.length;
		
		//Don't stop probing for an insert index until the item is inserted
		for (int base = 0; ;base++) {			
			int quadProb = base * base;
			int insertIndex = hashIndex + quadProb;
			
			insertIndex = insertIndex % backArray.length;
			
			//if this insert index is open, then this loop will return true
			if (backArray[insertIndex] == null) {
				backArray[insertIndex] = item;
				size++;
				if (size >= (backArray.length/2)) {
					resizeTable();
				}
				return true;
			}
			collisions++;
		}
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean addAll(Collection<? extends String> items) throws NullPointerException {
		boolean setModified = false;
		
		for (String item : items) {
			if (add(item)) {
				setModified = true;
			}
		}
		return setModified;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public void clear() {
		size = 0;
		collisions = 0;
		backArray = new String[initialCapacity];
	}

	@Override
	public boolean contains(String item) throws NullPointerException{
		if (item.equals(null)) {
			throw new NullPointerException();
		}
		if (size == 0) {
			return false;
		}
		
		int hashIndex = hasher.hash(item) % backArray.length;
		
		//Don't stop searching until all possible indices are checked
		for (int base = 0; ;base++) {
			int quadProbe = base * base;
			int checkIndex = hashIndex + quadProbe;
		
			checkIndex = checkIndex % backArray.length;
			
			//one way out of this loop, the item would be here but isn't
			if (backArray[checkIndex] == null) {
				return false;
			}
			//the item is at the index, time to return true
			if (backArray[checkIndex].equals(item)) {
				return true;
			}
		}
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean containsAll(Collection<? extends String> items) throws NullPointerException{
		boolean isInSet = false;
		
		for(String item : items) {
			if (contains(item)) {
				isInSet = true;
			}
		}
		
		return isInSet;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		}
		return false;
	}

	@Override
	/**
	 * @inheritDoc
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Finds the next prime number after the integer argument
	 * passed into the method.
	 * @param num -- integer value
	 * @return -- the next prime number from the integer passed
	 */
	public int getNextPrime(int num) {
		while(!isPrime(num)) {
			num++;
		}
		return num;
	}
	
	/**
	 * Checks if a number is prime or not. Note, 1 and 0 are neither
	 * prime nor composite and will return false.
	 * @param num -- integer to be determined prime or not
	 * @return -- true if the integer is prime, false otherwise
	 */
	public boolean isPrime(int num) {
		//handle special cases
		if (num == 0 || num == 1) {
			return false;
		}
		if (num == 2) {
			return true;
		}
		
		//check against even numbers
		if (num % 2 == 0) {
			return false;
		}
		
		//check against odd numbers
	    for(int i = 3; i*i <= num; i += 2) {
	        if(num % i == 0) {
	            return false;
	        }
	    }
	    return true;
	}
	
	/**
	 * Resizes the backing array by doubling the old size, then finding the next
	 * prime number to that size. Than the new backing array is re-populated
	 * by rehashing all previous items.
	 * 	 
	 */
	private void resizeTable() {
		String[] temp = backArray;
		int newSize = getNextPrime(backArray.length*2);
		
		size = 0;
		backArray = new String[newSize];
		
		for (int index = 0; index < temp.length; index++) {
			if (temp[index] != null) {
				add(temp[index]);
			}
		}
	}
	
	/**
	 * @return -- amount of collisions
	 */
	public int getCollisons() {
		return collisions;
	}

}
