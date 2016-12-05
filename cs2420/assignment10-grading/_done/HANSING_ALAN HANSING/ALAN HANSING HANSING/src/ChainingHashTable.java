package assignment10;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {

	private LinkedList<String>[] storage;
	private HashFunctor func;
	private int size;
	
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor){
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		for(int i = 0; i < storage.length; i++){
			storage[i] = new LinkedList<String>();
		}
		func = functor;
		size = 0;

	}
	

	@Override
	public boolean add(String item) {
		if(storage != null && !contains(item)){
			size++;
			return storage[func.hash(item)%storage.length].add(item);
		}
		else{
			return false;
		}
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean toReturn = false;
		for(String toAdd: items){
			toReturn |= add(toAdd);
		}
		return toReturn;
	}

	@Override
	public void clear() {
		for(int i = 0; i < storage.length; i++){
			storage[i] = null;
		}
		size = 0;
	}

	@Override
	public boolean contains(String item) {
		int location = func.hash(item)%storage.length;
		if(storage[location] == null){
			return false;
		}
		
		return storage[location].contains(item);		
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean toReturn = true;
		for(String toCheck: items){
			toReturn &= contains(toCheck);
		}
		return toReturn;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

}
