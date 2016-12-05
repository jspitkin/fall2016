package assignment10;

import java.util.Collection;

/**
 * QuadProbeHashTable
 * @author Lin Jia
 * uid: u1091732
 *
 */


public class QuadProbeHashTable implements Set<String> {
	int size;
	int capacity;
    int	collisions;
	double loadFunctor;
	private String[] hashTable;
	private HashFunctor functor;
	

	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		size = 0;
//		this.capacity = capacity;// array.length
		this.functor = functor;
//		hashTable = new String[capacity];
		if(!isPrime(capacity)){
		this.capacity = getPrime(capacity);
		hashTable = new String[this.capacity];
	}

	}

	@Override
	public boolean add(String item) {

//		
		if(item == null){
			return true;
		}
		if(contains(item)){
			return false;
		}
		if(loadFunctor > 0.5){
			if(contains(item)){
				return false;
			}
			rehash();
			int index = this.getIndexofAdd(item, hashTable);
			hashTable[index] = item;
			size++;
			loadFunctor = ((double)(size))/((double)(capacity));
			return true;
		}else{
			if(contains(item)){
				return false;
			}
			int index = this.getIndexofAdd(item, hashTable);
			hashTable[index] = item;
			size++;
			loadFunctor = ((double)(size))/((double)(capacity));
			return true;
		}

		
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {

		int temp;
		temp = size();
		for (String s: items)
		{
			add(s);
			
		}
		if(temp == size){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void clear() {
		size = 0;
		hashTable = new String[capacity];
		
	}

	@Override
	public boolean contains(String item) {
		if(item == null){
			return true;
		}else{

			int index = functor.hash(item) % capacity;
			if(hashTable[index] != null && hashTable[index] == item){
				return true;
			}
			if(hashTable[index] == null){
				return false;
			}
			if(hashTable[index] != null && hashTable[index] != item){
				int a = index;
				int i = 1;
				int b = index;
				while(hashTable[b] != item && i < size){
					a = index + i*i;
					b = a % capacity;
					if(hashTable[b] == null){
						return false;
					}else if (hashTable[b] == item){
						return true;
					}i++;
					
				}
			}
		}return false;
		
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if(items == null){
			return true;
		}
		for(String s : items){

			if(!(contains(s))){
				return false;
			}
			
		}
		return true;		
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}
	
//	private int getIndexofAdd(String item, String[] hashTable){
//		int index = functor.hash(item)%capacity;
//		if(item == null){
//			return -1;
//		}else{
//			while(index >= capacity){
//				index = index % capacity; 
//			}
//		}
//		if(hashTable[index] == null){
//			return index;
//		}else {
//			if(hashTable[index] != item){
//				return -1;
//			}
//			int i = 0;
//			while(hashTable[index] != null){
//				i++;
//				index = (index + i*i)%size;
//				return index;
//			}
//		}
//		
//		return index;
//		
//	}
	private int getIndexofAdd(String item, String[] hashTable){
		int index = functor.hash(item) % capacity;
		int a = index;
		int b = index;
		int i = 0;

		if (hashTable[index] == null) {
			return index;
		}
		if(hashTable[index] != null){
			while (hashTable[b] != null) {
				
				i++;
				a = index + i*i;
				b = a % capacity;
				collisions++;
				

			}
			return b;
		}
		return 0;

		}
			
		

	
	private void rehash(){
		
		if(!isPrime(capacity*2)){
			capacity = getPrime(capacity*2);
		}
		String[] temp =  new String[capacity];
		temp = hashTable;
		hashTable = new String[capacity];
		size = 0;
		for(int i = 0; i < temp.length; i++){
			if(temp[i] != null){
				add(temp[i]);
			}
		}
	}
	/**
	 * check the number is prime or not
	 * @param number
	 * @return 
	 */
	private boolean isPrime(int number) {
	    if (number%2==0){
	    	return false;
	    	}
	    for(int i=3;i*i<=number;i+=2) {
	        if(number%i==0)
	            return false;
	    }
	    return true;
	}
	/**
	 * if the number is not prime get a prime number
	 * @param number
	 * @return
	 */
	private int getPrime(int number){
	
		while(!isPrime(number)){
			number++;
		}
		return number;
		
		
	}

}
