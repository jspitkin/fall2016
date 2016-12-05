package assignment10;

import java.util.Collection;

public class QuadProbeHashTable implements Set<String> {
	private String[] storage;
	private int size;
	private HashFunctor hashFunct;
	private int initialCap;
	private int collisionCount=0;
	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		storage = new String[capacity];
		size = 0;
		hashFunct = functor;
		initialCap = capacity;
	}

	@Override
	public boolean add(String item) {
		if (size >= storage.length / 2) {
			rehash();
		}
		int index = hashFunct.hash(item)%storage.length;
		int offset = 1;

		// Look for the first slot
		while (storage[index% storage.length] != null) {
			
			
			// Don't change anything if you find yourself.
			if (storage[index% storage.length].equals(item))
				return false;
			if (index < storage.length) {
				index += (offset^2);
				offset ++;
				index = index%storage.length;
			
			}
			
			// Loop around; you've encountered the end of all Strings.
			/*if (index > items.length)
				index = index % items.length;/*/
			collisionCount++;
		}
		storage[index% storage.length] = item;
		size++;
		return true;
	}

	private void rehash() {
		String[] oldArray = storage;
		storage = new String[nextPrime(storage.length * 4)];
		size = 0;
		for (String s : oldArray)
			if (s != null)
				add(s);
		//System.out.println("rehashed");
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		boolean changed = false;
		for (String s : items)
			if (add(s) && !changed)
				changed = true;
		return changed;
	}

	@Override
	public void clear() {
		storage = new String[initialCap];
		size = 0;
		collisionCount = 0;
	}

	@Override
	public boolean contains(String item) {
		int index = hashFunct.hash(item) % storage.length;
		int offset = 1;

		// Look for the first available bucket. Vacancies are guaranteed!
		while (storage[index] != null) {
			// Don't change anything if you find yourself.
			if (storage[index].equals(item))
				return true;
			if (index < storage.length) {
				index+= offset;
				offset += 2;
			}
			if(index>storage.length)
				index -= storage.length;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		for(String s: items)
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

	/**
	 * returns the next prime after n in the natural numbers. Inspired by the
	 * textbook.
	 * 
	 * @param n
	 * @return a prime number
	 */
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
