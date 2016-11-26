package assignment10;
/**
 * 
 * @author Taylor Cassity
 *
 */
import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {
	/**
	 * public ChainingHashTable(int capacity, HashFunctor functor)
	 * 
	 * Since ChainingHashTable will require an array of LinkedList<String>,
	 * which is a generic type, Java will give a warning when initializing it.
	 * We must specifically cast a non-generic array to a generic one (just like
	 * in assignment 3). 
	 */
	private LinkedList<String>[] storage;
	int size;
	int capacity;
	int collisions;
	HashFunctor hasher;

	@SuppressWarnings("unchecked")
	public ChainingHashTable(int capacity, HashFunctor functor) {
		this.capacity = capacity;
		storage = (LinkedList<String>[]) new LinkedList[capacity];
		hasher = functor;
		size = 0;
		collisions = 0;
	}

	@Override
	public boolean add(String item) {
		if (capacity < 1) {
			return false;
		}
		
		int x = (hasher.hash(item)+1) % (capacity-1);
		if (storage[x] == null) {
			storage[x] = new LinkedList<String>();
			collisions--;
		}
		storage[x].add(item);
		size++;
		collisions++;
		return true;
			
	}
	
	/**
	 * Returns the current number of collisions that have occured.
	 * @return
	 */
	public int getCollisions(){
		return collisions;
	}

	@Override
	public boolean addAll(Collection<? extends String> items) {
		if(capacity < 1)
			return false;
		for (String x : items)
			add(x);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		collisions = 0;
		storage = (LinkedList<String>[]) new LinkedList[capacity];
	}

	@Override
	public boolean contains(String item) {
		if (capacity < 1) {
			return false;
		}
		
		int x = hasher.hash(item) % (capacity-1);

		if (storage[x] == null) {
			return false;
		}
		return storage[x].contains(item);
		
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
}
