package assignment10;

import java.util.Collection;

/**
 * 
 * @author Taylor Cassity
 *
 */

public class QuadProbeHashTable implements Set<String> {
	/**
	 * To allow your QuadProbeHashTable class to be used with any hash function,
	 * notice that the constructor accepts a function object containing the int
	 * hash(String item) method, and that method should be used whenever an
	 * item's hash code is needed. The HashFunctor interface is given below.
	 * 
	 * Finally, recall that the table size should be a prime number and that the
	 * table should be resized and rehashed when LAMBDA exceeds 0.5. (Hint: If
	 * the capacity given in the constructor is not prime, use the next largest
	 * prime number as the table size.)
	 */

	/**
	 * Constructs a hash table of the given capacity that uses the hashing
	 * function specified by the given functor.
	 */

	String[] table;
	int capacity;
	int size;
	int collisions;
	HashFunctor hasher;

	public QuadProbeHashTable(int capacity, HashFunctor functor) {

		this.capacity = capacity;
		if(!isPrime(capacity))
			this.capacity = findPrime(capacity) -1;
		table = new String[this.capacity -1];
		hasher = functor;
		size = 0;
		collisions = 0;
	}

	@Override
	public boolean add(String item) {

		if (capacity < 1) {
			return false;
		}
		
		int x =  (hasher.hash(item)+1) % (capacity-2); //note: reduce this to -1?
		resize();

		if (table[x] == null) {
			table[x] = item;
			size++;
			return true;
		}

		// Quadratic Placing
		else {
			int i = 0;
			int temp = x;
			while (table[temp] != null) {
				// increase by i^2 per iteration
				temp += x + (int) Math.abs(Math.pow(++i, 2));
				// x is larger than the array!
				if(temp >= capacity -1) {
					temp = temp % (capacity -1);
				}
				if (table[temp] == null) {
					table[temp] = item;
					size++;
					collisions++;
					return true;
				}
				// x is larger than the array!
			}
			table[temp] = item;
			collisions++;
			size++;
			return true;
		}
	}

	/**
	 * Resizes the array to the next highest prime number
	 * Rehashes all items into the new array
	 */
	private void resize() {

		if(size < (int)(capacity * 0.75))
			return;
		
		String[] temp = table;
		int x = findPrime(capacity);
		capacity = x;
		table = new String[x];
		size = 0;
		int c = collisions;
		for(String y : temp)
			if(y != null)
				add(y);
		collisions = c;
	}

	/**
	 * Finds the next highest prime number for the current capacity
	 * @return
	 */
	public static int findPrime(int c) {
		int x = c *2;
		while(true){
			if(isPrime(x +2))
				return x +2;
			if(isPrime(x +4))
				return x +4;
			x++;
		}
		
	}
	
	/**
	 * Returns the current number of collisions that have occured.
	 * @return
	 */
	public int getCollisions(){
		return collisions;
	}
	
	/**
	 * Checks if x is a prime number.
	 * @param x
	 * @return
	 */
	public static boolean isPrime(int x){
		if(x < 3)
			return true;
		for(int i = 2; i < 11; i++)
			if(x % i == 0)
				return false;
		return true;
	}
	
	public void print(){
		System.out.print("[");
		for(String x : table)
			System.out.print(x + ", ");
		System.out.print("]");
	}
	
	public String[] toArray(){
		String[] out = new String[size];
		for(int i = 0; i < size; i++)
			if(table[i] != null)
				out[i] = table[i];
		return out;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		if(capacity < 1)
			return false;
		for (String x : items)
			add(x);
		return true;
	}

	@Override
	public void clear() {
		table = new String[capacity];
		size = 0;
		collisions = 0;
	}

	@Override
	public boolean contains(String item) {
		
		if (capacity < 1) {
			return false;
		}
		
		int x = hasher.hash(item) % (capacity-1);
		resize();

		if (table[x] == null && table[x] == item) {
			return true;
		}

		// Quadratic Placing
		else {
			int i = 0;
			int temp = x;
			while (table[temp] != null) {
				// increase by i^2 per iteration
				temp = x;
				temp += (int) Math.abs(Math.pow(++i, 2));
				// x is larger than the array!
				if(temp > capacity -1) {
					temp = temp % (capacity -1);
				}
				if (temp < capacity-1) {
					if (table[temp] != null && table[temp] == item) {
						return true;
					}
				}

			}
			return true;
		}
	}

	@Override
	public boolean containsAll(Collection<? extends String> items) {
		if(size < 1)
			return false;
		for (String x : items)
			contains(x);
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
}
