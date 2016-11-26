package assignment10;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class QuadProbeHashTable implements Set<String> {
	private HashFunctor hashes;
	private String[] hashtable;
	private int size;
	public int collisions = 0;

	/**
	 * constructor for QuadProbeHashTable with capacity and the hashfunctor as
	 * parameters
	 * 
	 * @param capacity
	 * @param functor
	 */
	public QuadProbeHashTable(int capacity, HashFunctor functor) {
		hashtable = new String[nextPrime(capacity)];
		size = 0;
		hashes = functor;
	}

	/**
	 * add function for the Quad HashTable
	 */

	@Override
	public boolean add(String item) {
		int hashcode = Math.abs(hashes.hash(item)) % hashtable.length;

		if (contains(item)) {
			return false;
		} else if (hashtable[hashcode] == null) {
			hashtable[hashcode] = item;
			size++;
			return true;
		}

		else if (hashtable[hashcode] != null && loadfactor() < .5) {
			collisions++;
			int index = 0;
			for (int i = 1; i < hashtable.length; i++) {

				index = Math.abs((hashcode + i * i)) % hashtable.length;
				if (hashtable[index] == null) {

					size++;
					hashtable[index] = item;
					return true;
				} else {
					collisions++;
				}

			}
		} else if (loadfactor() > .5) {
			rehash();
			add(item);

		}

		return false;
	}

	/**
	 * rehash the quad probe hash table after load factor is above .5
	 * 
	 */

	private void rehash() {
		String[] oldhashtable = hashtable;
		hashtable = new String[nextPrime(2 * oldhashtable.length)];// create a
																	// new table
																	// of
																	// 2*oldhash
																	// table
																	// next
																	// prime
																	// number
																	// capacity
		size = 0;// set size to 0 due to rehashing
		for (int i = 0; i < oldhashtable.length; i++) {
			if (oldhashtable[i] != null) {
				add(oldhashtable[i]);// rehash all of the value in the old hash
										// table
			}
		}
	}

	/**
	 * addall which is a function to add a list into the hashtable
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
	 * clear the hash table
	 */
	@Override
	public void clear() {
		size = 0;
		collisions = 0;

		for (int i = 0; i < hashtable.length; i++) {
			hashtable[i] = null;
		}
		hashtable = new String[size];

	}

	/**
	 * contain check if the item exist in the hashtable
	 */

	@Override
	public boolean contains(String item) {
		int hashcode = Math.abs(hashes.hash(item)) % hashtable.length;
		if (size == 0) {
			return false;
		}
		if (hashtable[hashcode] == null) {
			return false;
		}

		else if (hashtable[hashcode] != null) {

			int index = 0;
			for (int i = 0; i < hashtable.length; i++) {

				index = Math.abs((hashcode + i * i) % hashtable.length);
				if (hashtable[index] == null) {
					return false;
				} else if (hashtable[index].equals(item)) {
					return true;
				}

			}
		}

		return false;
	}

	/**
	 * contain all check if the collection of items all contain in the hash
	 * table return false if not all are contained otherwise return true
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
	 * isEmpty return true if hash table is empty other wise return false
	 */
	@Override
	public boolean isEmpty() {

		return size == 0;
	}

	/**
	 * return the size of the hash table
	 * 
	 * @param none
	 * @return int
	 */
	@Override
	public int size() {

		return size;
	}

	/**
	 * return the next prime number of the hash table
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
	 * return the loadfactor which is size/hashtable.length
	 * 
	 * @return
	 */
	public double loadfactor() {
		if (size == 0) {
			return 0;
		}
		return (double) size / (double) hashtable.length;

	}

	public String[] gettable() {
		return hashtable;
	}

	/**
	 * random string generate a string of length of input parameter
	 * 
	 * @param length
	 * @return String
	 */
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
