package assignment10;

import java.util.Collection;

/**
 * This class creates a hash table that uses quadratic probing 
 * 
 * @author Kameron Service
 *
 */
public class QuadProbeHashTable implements Set<String>{
	
	private int size, itemCount;
	public int collisions;
	private String[] arr;
	private HashFunctor fun;
	
	 /** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
   public QuadProbeHashTable(int capacity, HashFunctor functor){
	  
	   if(!isPrime(capacity)){
		   size = getNextLargestPrime(capacity);
	   }else{
		   size = capacity;
	   }
	   
	   if(size == 0)
		   size = 1;
	   
	   arr = new String[size];
	   itemCount = 0;
	   fun = functor;
   }
   
   /**
    * Check to see if a number is prime
    * @param n - the number to check
    * @return - True if n is prime, false otherwise
    */
   private boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}
   
   /**
    * Finds the next prime number
    * @param num - the original number
    * @return - the next prime
    */
   private int getNextLargestPrime(int num){
	   num++;
	   while(!isPrime(num)){
		   num++;
	   }
	   return num;
   }

	@Override
	public boolean add(String item) {
		if(item == null){
			throw new NullPointerException();
		}		
		
		int origItemCount = itemCount;
		int hash = fun.hash(item);
		int hashCode = hash % size;
		
		if(arr[hashCode] == null){
			arr[hashCode] = item;
		}else{
			int i = 1;
			int index = hashCode;
			do{
				collisions++;
				if(arr[index] == item){
					return false;
				}
				index = hashCode + (i*i);
				while(index > size -1){
					index = index - size;
				}
				i++;
			}while(arr[index] != null);
			arr[index] = item;
				
		}
		itemCount++;
		if((double)size / (double)itemCount < 2.0){
			
			rehash();
		}
		
		return itemCount > origItemCount;
	}
	
	/**
	 * Rehashes the hash table
	 */
	private void rehash(){
		collisions = 0;
		itemCount = 0;
		String[] tempArr = new String[size];
		
		for(int i = 0; i < size; i++){
			tempArr[i] = arr[i];
		}
		
		size = getNextLargestPrime(size * 2);
		arr = new String[size];
		
		for(int i = 0; i < tempArr.length; i++){
			if(tempArr[i] != null){
				String str = tempArr[i];
				this.add(str);
			}
		}
		
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		if(items.equals(null))
			throw new NullPointerException();
		
		int origCount = itemCount;
		for(String str : items){
			add(str);
		}
		if(itemCount > origCount)
			return true;
		
		return false;
	}

	@Override
	public void clear() {
		arr = new String[size];
		itemCount = 0;
	}

	@Override
	public boolean contains(String item) {
		if(item.equals(null))
			throw new NullPointerException("The item to check is null.");
		
		int hash = fun.hash(item);
		int hashCode = hash % size;

		int index = hashCode;
		int i = 1;
		while(arr[index] != null){
			if(arr[index].equals(item)){
				return true;
			}
			index = hashCode +  (i*i);
			while(index > size - 1){
				index = index - size;				
			}
			i++;
		}		
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if(items == null)
			throw new NullPointerException("The collection is null.");
		for(String str : items){
			if(str == null)
				throw new NullPointerException("An item in the collection is null.");
			
			if(!this.contains(str)){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		return itemCount == 0;
	}

	@Override
	public int size() {
		return itemCount;
	}
	

}
