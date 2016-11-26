package assignment10;

import java.util.Collection;
/**
 * 
 * @author Jordan Gardner 
 * QuadProbeHashTable class that uses the Quad Prob
 * method to fill a hash table using a functor.  Rehashes at 75% 
 * full, and the user has access to add, addAll,clear,test Empty,
 * test contains etc.
 * Quad prob uses prime numbers to initialize and increase the 
 * size of the intial array.
 *
 */
public class QuadProbeHashTable implements Set<String> {

	int size;
	int numOfItems;
	public String[] ht;
	double load;
	private HashFunctor hf;
	private int maxSize;

	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		maxSize = Prime.nextPrime(capacity);
		ht = new String[maxSize];
		size = capacity;
		load = 0;
		hf = functor;
		numOfItems = 0;
		
		
	}

	@Override
	public boolean add(String item) {

		int x = 1, y = 1;

		if (item == null) {
			return true;
		}
		int hasher = hf.hash(item);
		//hashes item
		int hash = hasher % size;
		
		while (hash >= size) {
			hash = hash - size;
		}
		if (hash != 0) {
			hash--;
		}
		if ((ht[hash] != null) && (ht[hash].equals(item))){
			if (ht[hash] == null) {
				ht[hash] = item;
				numOfItems++;
			}
			load = (double)numOfItems/(double)size;
				
				if (load >= 0.75) {
					rehash();
				}
				return true;
			}
		while (ht[hash] != null) {
			x = x + 1;
			y = x * x;
			hash = hash + y;
			while (hash >= size) {
				hash = hash - size;
			}
			if ((ht[hash] != null) && (ht[hash].equals(item))){
				if (ht[hash] == null) {
					ht[hash] = item;
					numOfItems++;
				}
				load = (double)numOfItems/(double)size;

					if (load >= 0.75) {
						rehash();
					}
					return true;
				}
		}
		if (ht[hash] == null) {
			ht[hash] = item;
			numOfItems++;
		}
		load = (double)numOfItems/(double)size;

			if (load >= 0.75) {
				rehash();
			}
			return true;

	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		for (String s : items) {
			add(s);
		}
		return true;
	}

	@Override
	public void clear() {
		numOfItems = 0;
		ht = new String[size];
		load = 0;

	}

	@Override
	public boolean contains(String item) {

		int x = 1;
		int y = 1;
		if (item == null) {
			return true;
		}

		int hasher = hf.hash(item);
		int hash = hasher % size;
		while (hash >= size) {
			hash = hash - size;
		}
		if (hash != 0) {
			hash--;
		}
		if ((ht[hash] != null) && (ht[hash].equals(item))) {
			if (ht[hash] == null && item != null){
				return false;
			}else if (ht[hash].compareTo(item) == 0){
				return true;
			}
		}
		while (ht[hash] != null) {
			x = x + 1;
			y = x * x;
			hash = hash + y;
			while (hash >= size) {
				hash = hash - size;
			}
			if ((ht[hash] != null) && (ht[hash].equals(item))) {
				if (ht[hash] == null && item != null){
					return false;
				}else if (ht[hash].compareTo(item) == 0){
					return true;
				}
			}

		}
		if (ht[hash] == null && item != null) {
			return false;
		}else if (ht[hash].compareTo(item) == 0){
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for (String s : items) {
			if (!contains(s)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		if (numOfItems == 0) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {

		return numOfItems;
	}

	private void rehash() {
		
		String[] arr = new String[Prime.nextPrime(size * 2)];
		size = arr.length;
		boolean go=true;
		for (String s : ht) {
			
			if (s == null)
				continue;
			int hasher = hf.hash(s);
			int hash = hasher % size;
			int x = 1;
			int y = 1;
			while (hash >= size) {
				hash = hash - size;
			}
			if (hash != 0) {
				hash--;
			}
			if ((arr[hash] != null) && (arr[hash].equals(s))) {
				arr[hash] = s;
				go=false;
			}
			if(go){
			while (arr[hash] != null) {
				x = x + 1;
				y = x * x;
				hash = hash + y;
				while (hash >= size) {
					hash = hash - size;
				}
				if ((arr[hash] != null) && (arr[hash].equals(s))) {
					arr[hash] = s;
					go=false;
				}

			}
			if(go){
			arr[hash] = s;
			}
			go=true;
		}
		}
		load = numOfItems/size;
				ht = arr;
	}
}
