package assignment10;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String>{
	private LinkedList<String>[] storage;
	private int size;
	private HashFunctor hf;
	private int collisionCount;
	
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		size = 0;
		hf = functor;
	}

	@Override
	public boolean add(String item) {
		if (size >= (3 * storage.length) / 4)
			rehash();
		int index = hf.hash(item)%storage.length;
		LinkedList<String> list = storage[index];
		if(list == null) {
			storage[index] = new LinkedList<String>();
			storage[index].addLast(item);
			size++;
			return true;
		}
		if(!list.contains(item)) {
			collisionCount++;
			list.addLast(item);
			size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for(String s : items)
			if(add(s))
				changed = true;
		return changed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		storage = (LinkedList<String>[]) new LinkedList[101];
		collisionCount = 0;
		
	}

	@Override
	public boolean contains(String item) {
		int index = hf.hash(item);
		LinkedList<String> list = storage[index];
		if(list == null) 
			return false;
		return list.contains(item);
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String s : items)
			if(!contains(s))
				return false;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		LinkedList<String>[] oldArray = storage;
		storage = (LinkedList<String>[]) new LinkedList[nextPrime(storage.length * 4)];
		size = 0;
		for (LinkedList<String> s : oldArray)
			if (s != null)
				addAll(s);
	}
	
	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;
		for (; !isPrime(n); n += 2)
			;
		return n;
	}

	/**
	 * Lets you know if that thar number is a prime.
	 * 
	 * @param n
	 * @return true if it is a prime number
	 */
	private static boolean isPrime(int n) {
		if (n % 2 == 0)
			return false;
		for (int i = 3; i < Math.sqrt(n); i++)
			if (n % i == 0)
				return false;
		return true;
	}

	public int getCollisions(){
		return collisionCount;
	}

}
