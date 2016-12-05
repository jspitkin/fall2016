package assignment10;

import java.util.Collection;
import java.util.LinkedList;


/**
 * ChainingHashTable
 * @author Lin Jia
 * uid: u1091732
 *
 */
public class ChainingHashTable implements Set<String>{
	int size;
	double loadFactor;
	int capacity;
	int collisions;
	private LinkedList<String>[] storage;
	private HashFunctor functor;

	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		size = 0;
		loadFactor = 0;
//		storage = (LinkedList<String>[]) new LinkedList[capacity];
		if(!isPrime(capacity)){
			capacity = getPrime(capacity);
			storage = (LinkedList<String>[]) new LinkedList[capacity];
			}
		this.capacity = capacity;
		this.functor = functor;
	}

	@Override
	public boolean add(String item) {

		int index = this.functor.hash(item) % capacity;
		LinkedList<String> temp = storage[index];
		if(item == null){
			return true;
		}
		if(contains(item)){
			return false;
		}

		if (loadFactor > 50) {
			rehash();
			
			
		
			if(temp == null){
				temp = new LinkedList<String>();
				temp.add(item);
				size++;
				loadFactor = ((double)size) / ((double)capacity);
				storage[index] = temp;
				return true;
			} else {
				if (temp.contains(item)) {
					return false;
				} else {
					temp.add(item);
					size++;
					loadFactor = ((double)size) / ((double)capacity);
					storage[index] = temp;
					collisions++;
					return true;
				}
			}

		}
		else{
			

			
			if(temp == null){
				temp = new LinkedList<String>();
				temp.add(item);
				size++;
				loadFactor = ((double)size) / ((double)capacity);
				storage[index] = temp;
				return true;
			} else {
				if (temp.contains(item)) {
					return false;
				} else {
					temp.add(item);
					size++;
					loadFactor = ((double)size) / ((double)capacity);
					storage[index] = temp;
					collisions++;
					return true;
				}
			}
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
	

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		storage = (LinkedList<String>[]) new LinkedList[capacity];
	}

	@Override
	public boolean contains(String item) {
		
		if(item == null){
			return true;
		}
		int index = this.functor.hash(item) % capacity;
		LinkedList<String> temp = storage[index];
		
		
		if(temp == null){
			return false;
		}

		return (temp.contains(item));
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
	
	@SuppressWarnings("unchecked")
	private void rehash(){
		
		if(!isPrime(capacity*2)){
			capacity = getPrime(capacity*2);
		}
		LinkedList<String>[] temp = (LinkedList<String>[]) new LinkedList[capacity];
		temp = storage;
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		size = 0;
		for(int i = 0; i < temp.length; i++){
			if(temp[i] != null){
				addAll(temp[i]);
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
