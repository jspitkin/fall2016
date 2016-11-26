package assignment10;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class ChainingHashTable implements Set<String> {
	private HashFunctor hashfunctor;
	private int size;
	public int collisions = 0;
	private LinkedList<String>[] storage;

	/**
	 * Constructor for the chaining hash table
	 * 
	 * @param capacity
	 * @param functor
	 */
	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		storage = (LinkedList<String>[]) new LinkedList[nextPrime(capacity)];
		for (int j = 0; j < storage.length; j++) {
			storage[j] = new LinkedList<String>();
		}
		size = 0;
		hashfunctor = functor;
	}

	/**
	 * add the item into the chaining hash table
	 */
	@Override
	public boolean add(String item) {
		int hashcode = Math.abs(hashfunctor.hash(item)) % storage.length;
		if (storage[hashcode].contains(item)) {
			return false;
		}

		else if (!storage[hashcode].contains(item) && loadfactor() < 1) {
			if (!storage[hashcode].isEmpty() && !storage[hashcode].contains(item)) {
				collisions++;
			}
			storage[hashcode].add(item);
			size++;
			return true;

		} else {

			rehash();
			add(item);

			return true;
		}

	}

	/**
	 * rehash the chaining hash table once the loadfactor get to the size above
	 * 1
	 */
	private void rehash() {
		LinkedList<String>[] oldstorage = storage;
		storage = new LinkedList[nextPrime(2 * oldstorage.length)];// creates a
																	// new table
																	// of 2*old
																	// hashtable
																	// size
		for (int j = 0; j < storage.length; j++) {
			storage[j] = new LinkedList<String>();// creat a new linkedlist for
													// each index in the array
		}

		size = 0;

		for (int i = 0; i < oldstorage.length; i++) {// rehash old items in the
														// old hash table
			for (String item : oldstorage[i]) {
				add(item);
			}
		}
	}

	/**
	 * add all will add all of the items in the collection of items
	 */
	@Override
	public boolean addAll(Collection<? extends String> items) {

		boolean addall = false;
		for (String item : items) {
			if (add(item)) {
				addall = true;
			}
		}
		return addall;
	}

	/**
	 * clear will clear the table
	 */
	@Override
	public void clear() {
		size = 0;

		for (int i = 0; i < storage.length; i++) {
			storage[i].clear();// clear each list first to save memory on the
								// computer
		}
		storage = (LinkedList<String>[]) new LinkedList[size];

	}

	/**
	 * contain is to contain the item in each linkedlist in the array of
	 * linkedlist
	 */
	@Override
	public boolean contains(String item) {
		if (size == 0) {
			return false;
		}
		int hashcode = Math.abs(hashfunctor.hash(item)) % storage.length;
		if (storage[hashcode].contains(item)) {
			return true;
		}
		return false;
	}

	/**
	 * containall check if the hash table contain all of the items in the
	 * collection if it does return true if not return false
	 */
	@Override
	public boolean containsAll(Collection<? extends String> items) {
		boolean containall = true;
		for (String item : items) {
			if (!contains(item)) {
				containall = false;
			}
		}
		return containall;

	}

	/**
	 * isEmpty return true if hashtable is empty return false if not
	 */
	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	/**
	 * return the size of the hash table
	 */
	@Override
	public int size() {

		return size;
	}

	/**
	 * return the next prime number of the parameter
	 * 
	 * @param a
	 * @return
	 */

	private int nextPrime(int a) {
		boolean isPrime = false;
		if (a < 2) {
			isPrime = true;
			return 2;

		}
		while (!isPrime) {
			a++;
			isPrime = true;
			int m = (int) Math.ceil(Math.sqrt(a));
			for (int i = 2; i <= m; i++) {
				if (a % i == 0) {
					isPrime = false;
					break;
				}

			}
		}
		return a;
	}

	/**
	 * return the load factor of the hash table which is size/hash table length
	 * 
	 * @return
	 */
	private float loadfactor() {
		if (size == 0) {
			return 0;
		}
		return (float) size / storage.length;

	}

	public LinkedList<String>[] gettable() {
		return storage;
	}

	public static String randomString(int length) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			// ASCII values a-z,A-Z are contiguous (52 characters)
			Random r = new Random();
			char randomChar = (char) ((r.nextInt(26) + 'a'));// This will throw
																// a null
																// pointer! Find
																// the bug and
																// squash it!

			stringBuilder.append(randomChar);

		}
		return stringBuilder.toString();
	}
}
