package assignment10;

import java.util.Collection;
import java.util.Iterator;

public class QuadProbeHashTable implements Set<String>{

	private String[] table;
	private int size;
	private HashFunctor functor;
	private int collision;
	private final static double LIMIT = 0.5;
	
	
    /** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
   public QuadProbeHashTable(int capacity, HashFunctor functor){
	   
	   this.size = 0;
	   this.functor = functor;
	   this.collision = 0;
	   this.table = new String[getPrime(capacity)];
	   
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
	   int i = 1;
	   String[] temp = new String[getPrime(table.length* 2)];	   
	   for(String item : table){
		   if(item != null){
			   int hashNumber = functor.hash(item);
			   int index = Math.abs(hashNumber % table.length);
			   while(temp[index]!=null){
				   index = Math.abs((index + i*i) % table.length);
				   i++;
			   }
			   temp[index] = item;
		   }
	   }
	   table = temp;
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
		int i = 1;
		int hashNumber = functor.hash(item);
		int index = Math.abs(hashNumber % table.length);
		if(contains(item)){
			return false;
		}else{
		if(((double)(size+1)/table.length)>=LIMIT){
			reHash();
		}	
		int index2 = index;
		while(table[index2]!=null){
			collision++;
			index2 = Math.abs((index + i*i) % table.length);		
			i++;
		}
		table[index2] = item;
		size++;
		return true;}
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
		if(containsAll(items)){
			return false;
		}else{
		for (Iterator<? extends String> iterator = items.iterator(); iterator.hasNext();) {
			String iterable_element = iterator.next();
			if(!(contains(iterable_element))){
				add(iterable_element);
			}
		}
		return true;
		}	
	}

	@Override
	  /**
	   * Removes all items from this set. The set will be empty after this method
	   * call.
	   */
	public void clear() {
		String[] table2 = new String[table.length];
		table = table2;
		size = 0;
		collision = 0;
	}

	@Override
	  /**
	   * Determines if there is an item in this set that is equal to the specified
	   * item.
	   * 
	   * @param item -
	   *          the item sought in this set
	   * @return true if there is an item in this set that is equal to the input
	   *         item; otherwise, returns false
	   */
	public boolean contains(String item) {
		if(item == null){
			throw new NullPointerException();
		}else{
			int i = 1;
			int hashNumber = functor.hash(item);
			int index = Math.abs(hashNumber % table.length);
			int index2 = index;
			while(i<=size){
				if(table[index2] == item){
					return true;
				}			
				index2 = Math.abs((index + i*i) % table.length);
				i++;
			}
			return false;
		}
		
	}

	@Override
	  /**
	   * Determines if for each item in the specified collection, there is an item
	   * in this set that is equal to it.
	   * 
	   * @param items -
	   *          the collection of items sought in this set
	   * @return true if for each item in the specified collection, there is an item
	   *         in this set that is equal to it; otherwise, returns false
	   */
	public boolean containsAll(Collection<? extends String> items) {
		if(items==null){
			throw new NullPointerException();
		}
		for (Iterator<? extends String> iterator = items.iterator(); iterator.hasNext();){
			String iterable_element = iterator.next();
			if(!(this.contains(iterable_element))){
				return false;
			}			
		}
		return true;
	
	}

	@Override
	  /**
	   * Returns true if this set contains no items.
	   */
	public boolean isEmpty() {
		if(this.size == 0){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	public String[] getTable() {
		return table;
	}
	public void setTable(String[] table) {
		this.table = table;
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
