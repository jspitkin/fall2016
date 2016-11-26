package assignment10;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String>{
	private LinkedList<String>[] storage;
	private int size;
	private int collision;
	private HashFunctor functor;
	private final static double LIMIT = 0.8;
	
	  @SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor){			
		  storage = (LinkedList<String>[]) new LinkedList[getPrime(capacity)];
		  this.size = 0;
		  this.collision = 0;
		  this.functor = functor;
		  
		  
	  }
	  
	   public int getPrime(int a){
		   while(!isPrime(a)){
			   a++;
		   }
		   return a;
	   }
	   
	   public Boolean isPrime(int a){
		   if(a == 1){
			   return true;
		   }else{
			   for(int i = 2; i < a;i++){
				   if(a%i==0){
					   return false;
				   }
			   }
			   return true;
		   }	   
	   }
	   
	   
	public void reHash(){
		   @SuppressWarnings("unchecked")
		LinkedList<String>[] newStorage = (LinkedList<String>[]) new LinkedList[getPrime(storage.length*2)];	
		   for(int m = 0; m < storage.length;m++){
			   if(this.storage[m]!=null){
				   int hashNumber = functor.hash(storage[m].get(0));
				   int index = Math.abs(hashNumber % newStorage.length);
				   newStorage[index] = storage[m];
			   }
		   }
		   
	   storage = newStorage;
	}
	
	@Override
	  /**
	   * Ensures that this set contains the specified item.
	   * 
	   * @param item -
	   *          the item whose presence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if the input item was actually inserted); otherwise, returns false
	   */
	public boolean add(String item) {
		if(item == null){
			throw new NullPointerException();
		}
		if(this.contains(item)){
			return false;
		}
		if(((double)(size+1)/storage.length)>=LIMIT){
			reHash();
		}
		int hashNumber = this.functor.hash(item);
		int index = Math.abs(hashNumber % storage.length);
		if(this.storage[index] == null){
			storage[index] = new LinkedList<String>();
			storage[index].add(item);
			size++;
			return true;
		}else{	
		collision++;
		storage[index].add(item);
		size++;
		return true;
		}
	}

	@Override
	  /**
	   * Ensures that this set contains all items in the specified collection.
	   * 
	   * @param items -
	   *          the collection of items whose presence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if any item in the input collection was actually inserted);
	   *         otherwise, returns false
	   */
	public boolean addAll(Collection<? extends String> items) {
		
		if(this.containsAll(items)){
			return false;
		}
		for (Iterator<? extends String> iterator = items.iterator(); iterator.hasNext();) {
			String iterable_element = iterator.next();
			if(!(contains(iterable_element))){
				add(iterable_element);
			}
		}
		return true;	
	
	}

	@Override
	public void clear() {
		@SuppressWarnings("unchecked")
		LinkedList<String>[] storage2 = new LinkedList[storage.length];
		storage = storage2;
		size = 0;
		collision = 0;
		
	}

	@Override
	public boolean contains(String item) {
		if(item == null){
			throw new NullPointerException();
		}else{
			int hashNumber = this.functor.hash(item);
			int index = Math.abs(hashNumber % storage.length);
			if(storage[index] == null){
				return false;
			}else{
				if(storage[index].contains(item)){
					return true;
				}
				return false;
			}
		}
	}

	@Override
	  /**
	   * Ensures that this set contains all items in the specified collection.
	   * 
	   * @param items -
	   *          the collection of items whose presence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if any item in the input collection was actually inserted);
	   *         otherwise, returns false
	   */
	
	public boolean containsAll(Collection<? extends String> items) {

		if(items==null){
			throw new NullPointerException();
		}
		for (Iterator<? extends String> iterator = items.iterator(); iterator.hasNext();){
			String iterable_element = iterator.next();
			if(!(contains(iterable_element))){
				return false;
			}			
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0){
			return true;
		}else{
		return false;
		}
	}

	@Override
	public int size() {
		
		return size;
	}
	public LinkedList<String>[] getStorage() {
		return storage;
	}
	public void setStorage(LinkedList<String>[] storage) {
		this.storage = storage;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getCollision() {
		return collision;
	}
	public void setCollision(int collision) {
		this.collision = collision;
	}

	public HashFunctor getFunctor() {
		return functor;
	}
	public void setFunctor(HashFunctor functor) {
		this.functor = functor;
	}
	public static double getLimit() {
		return LIMIT;
	}


}
