package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Creates a hash table of Strings, using separate chaining to handle collisions.
 * @author Jana Klopsch (u0854469)
 * Last Modified 11-6-16
 */
public class ChainingHashTable implements Set<String>{

	private LinkedList<String>[] storage; //Array of LinkedLists
	private int size;
	public int capacity;
	public HashFunctor functor;

	@SuppressWarnings("unchecked")
	/**
	 * ChainingHashTable constructor: creates hash table of given capacity, using given
	 * hash function.  Handles collisions using separate chaining, via linkedLists.
	 * @param capacity - size of hash table
	 * @param functor - hash function to determine hash values of Strings
	 */
	public ChainingHashTable(int capacity, HashFunctor functor){

		storage = (LinkedList<String>[]) new LinkedList[capacity];
		this.capacity = capacity;
		this.functor = functor;
		this.size = 0;
		
		for(int index = 0; index < capacity; index ++){
			storage[index] = new LinkedList<String>();
		}
	}

	@Override
	public boolean add(String item) {

		//calculate hash value of item
		int hashVal = functor.hash(item);

		//mod by capacity
		int mapVal = hashVal % capacity;

		//add in that index -- if full quad probing
		if(storage[mapVal] == null){
			storage[mapVal].addFirst(item);
		}
		else{
			storage[mapVal].addLast(item);
		}
		size++;
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean flag = true;
		boolean temp;
		for(String element : items){
			temp = add(element);
			if(!temp){
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public void clear() {
		for(int index = 0; index < capacity; index++){
			storage[index].clear();
		}
		size = 0;
	}

	@Override
	public boolean contains(String item) {
		//calculate hash value of item
		int hashVal = functor.hash(item);
		//mod by capacity
		int mapVal = hashVal % capacity;

		if(storage[mapVal] != null){
			return storage[mapVal].contains(item);
		}

		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean flag = true;
		for(String element : items){
			if(!this.contains(element)){
				flag = false;
			}
		}
		return flag;
	}

	@Override
	public boolean isEmpty() {
		if(size() == 0){
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

}
