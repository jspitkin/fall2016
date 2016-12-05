package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * This class creates a hash table that uses separate chaining
 * 
 * @author Kameron Service u0963620
 *
 */
public class ChainingHashTable implements Set<String> {
	private int size, itemCount, origSize;
	
	private LinkedList<String>[] arr;
	private HashFunctor fun;
	private int lamda;
	
	/** Constructs a hash table of the given capacity that uses the hashing function
     * specified by the given functor.
     */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor){
		size = capacity;
		if(capacity == 0)
			size = 1;
		
		itemCount = 0;
		arr = (LinkedList<String>[]) new LinkedList[size];
		for(int i = 0; i < size; i++){
			arr[i] = new LinkedList<String>();
		}
		fun = functor;
		lamda = 1;
		origSize = size;
	}

	@Override
	public boolean add(String item) {
		if(item == null)
			throw new NullPointerException("The item is null.");
		
		int origItemCount = itemCount;
		
		int hash = fun.hash(item);
		int hashCode = hash % size;
		
		if(arr[hashCode].contains(item))
			return false;
		
		arr[hashCode].push(item);
		itemCount++;
		if(itemCount / size > lamda){
			rehash();
		}
		return itemCount > origItemCount;
	}
	
	/**
	 * Rehashes the table
	 */
	@SuppressWarnings("unchecked")
	private void rehash(){
		int origSize = size;
		size = size * 2;
		LinkedList<String>[] tempArr = (LinkedList<String>[]) new LinkedList[size];
		for(int i = 0; i < size; i++)
			tempArr[i] = new LinkedList<String>();
		itemCount = 0;
		for(int i = 0; i < origSize; i++){
			while(arr[i].peek() != null){
				String str = arr[i].pop();
				int hash = fun.hash(str);
				int hashCode = hash % size;
				tempArr[hashCode].add(str);
				itemCount++;
			}
		}
		arr = tempArr;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		if(items == null)
			throw new NullPointerException("The collection is null.");
		
		int origCount = itemCount;
		for(String str : items){
			this.add(str);
		}
		return itemCount > origCount;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		arr = (LinkedList<String>[]) new LinkedList[origSize];
		for(int i = 0; i < origSize; i++){
			arr[i] = new LinkedList<String>();
		}
		size = origSize;
		itemCount = 0;
	}

	@Override
	public boolean contains(String item) {
		if(item == null)
			throw new NullPointerException("The item is null.");
		
		int hash = fun.hash(item);
		int hashCode = hash % size;
		return arr[hashCode].contains(item);
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if(items == null)
			throw new NullPointerException("The collection is null.");
		
		for(String str : items){
			if(!this.contains(str))
				return false;
			
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
