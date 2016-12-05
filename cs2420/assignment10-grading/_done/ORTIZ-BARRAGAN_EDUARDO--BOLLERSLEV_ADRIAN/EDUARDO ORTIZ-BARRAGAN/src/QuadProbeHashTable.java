package assignment10;

import java.util.Collection;

/**
 * @author Eduardo Ortiz
 * CS2420 3-4
 * u0922628
 *
 */
public class QuadProbeHashTable implements Set<String>{
	int arraySize;
	int currentSize;
	public String[] items;
	private HashFunctor convert;
	boolean booladd =true;
	int numColli;
	
	/**
	 * @param capacity the initial size of the string array/HashTable
	 * @param functor the hash method we wish to use to determine spot in table.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor)	{
	
		items = new String[nextPrime(capacity)];
		convert = functor;
		currentSize = 0;
		arraySize = items.length;
		numColli = 0;
		
		
	}
	public boolean add(String item) {
	
		
		if(item == null)
				throw new NullPointerException();
		if(contains(item)==true)
			return false;
			
		if (items[getIndex(item)] == null){
				items[getIndex(item)] = (item);
				currentSize ++;
							
		}  

	if(rehashRatio() >= 0.5)
		{
			rehash();
		}
		
		return true;
	}
	
	/**Helper method to add to determine where in the list the item should be added.
	 * @param item element to be added to hashTable
	 * @return the index of where the value belongs
	 */
	private int getIndex(String item) {
		int index = 0;
		int count = 0;
		if (!(index >= 0))
			index = index + items.length;
		else
			index = convert.hash(item)%items.length;
		
		while(items[index]!=null && !(items[index].equals(item))) {
			count++;
			index = index + 2*count-1;		
			index = index % items.length;
			
			
		}
		numColli = count;
		return index;
	}
	
	public boolean addAll(Collection<? extends String> items) {
		boolean addAll = true;
		if(items.size()==0)
			return false;
		for (String s: items)
			if (!add(s))
				addAll = false;
			else if(s == null)
				throw new NullPointerException();
			
		return addAll;
	}
	
	public void clear() {
		items = new String[arraySize];
		currentSize = 0;
		int numColi = 0;
	}
	
	public boolean contains(String item) {
		if((size()== 0)&&(item==null))
			throw new NullPointerException();
		if(item == null)
			throw new NullPointerException();
		int expectedIndex = getIndex(item);
		if (items[expectedIndex] == null && item != null)
			return false;
		return items[expectedIndex].compareTo(item) == 0;
	}
		
	public boolean containsAll(Collection<? extends String> items) {
		if((size() == 0)&&(items.size()!=0))
			throw new NullPointerException();
		else if(items.size()==0)
			return true;
		
		boolean containsAll = true;
		for (String s: items){
			if((s == null)||(s == null)&&(size()==0))
				throw new NullPointerException();
			if (!contains(s))
				containsAll = false;}
		return containsAll;
	}

	
	public boolean isEmpty() {
		boolean answer = (currentSize ==0);
		return answer;
	}
	/** Helper method to check load factor
	 * @return load factor value
	 */
	public double rehashRatio() {
		double answer = (double)currentSize/items.length;
		return answer;
	}
	
	public int size() {
		
		return currentSize;
	}
	/** Method to rehash or resize the hash table if the minimum ratio has been attained. 	 
	 */
	private void rehash(){
		String[] temp = items; 
		items = new String[nextPrime(2*items.length)];
		currentSize = 0;
		
		for (int i=0; i<temp.length; i++) 
			if (temp[i] != null )
				add(temp[i]);
	}
	
	  /**
	 * @param n The value to be tested
	 * @return whether the tested value is a prime number or not
	 */
	private static boolean isPrime(int n) {
	        for(int i=2; i<n; i++) {
	            if(n%i==0)
	            	return false;
	        }
	        return true;
	    }
	 /**Helper method to find the next prime number following n.
	 * @param n the value with which we'll use to find next prime. 
	 * @return next prime or n if n is prime. 
	 */
	public static int nextPrime(int n) {
	        for(int i=n+1; true; i++) {
	            if(isPrime(i)==true)
	            	return i;
	        }
	    }
	    }