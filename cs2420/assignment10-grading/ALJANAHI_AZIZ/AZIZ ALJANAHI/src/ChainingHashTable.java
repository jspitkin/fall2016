package assignment10;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * Creates a Hash Table utilizing separate chaining
 */
public class ChainingHashTable implements Set<String> {
  private LinkedList<String>[] table;
  private HashFunctor func;
  private int size, cap, collisions;

  /**
   * Creates the Chaining Hash Table
   * 
   * @param capacity
   * @param func
   */
  @SuppressWarnings("unchecked")
  public ChainingHashTable(int _cap, HashFunctor _func) {
    cap = makePrime(_cap);
    table = (LinkedList<String>[]) new LinkedList[cap];
    size = 0;
    func = _func;
  }

  /**
   * Adds a specified item to the table.
   * 
   * @param item - to be added
   * @return true if this set changed as a result of this method call (that is, if the input item
   *         was actually inserted); otherwise, returns false
   */
  @Override
  public boolean add(String item) {
    int code = getHashCode(item); 
    if (find(item) != null) //if item is in the current hashcode return false
      return false;
    if (size + 1 > table.length)
      resize();
    if (table[code] == null)
      table[code] = new LinkedList<String>();
    if (table[code] != null)
      collisions+=table[code].size();
    table[code].add(item);
    size++;
    return true;
  }

  /**
   * Adds Items in a collection to the table.
   * 
   * @param items - list to be added
   * @return true if this set changed as a result of this method call (that is, if any item in the
   *         input collection was actually inserted); otherwise, returns false
   */
  @Override
  public boolean addAll(Collection<? extends String> items) {
    boolean add = false;
    for (String item : items) {
      if (this.add(item))
        add = true;
    }
    return add;
  }

  /**
   * Removes all items from this set. The set will be empty after this method call.
   */
  @SuppressWarnings("unchecked")
  @Override
  public void clear() {
    table = (LinkedList<String>[]) new LinkedList[cap];
    size = 0;
    collisions = 0;
  }

  /**
   * Determines if there is an item in this set that is equal to the specified item.
   * 
   * @param item - the item sought in this set
   * @return true if there is an item in this set that is equal to the input item; otherwise,
   *         returns false
   */
  @Override
  public boolean contains(String item) {
    return find(item) == null ? false : true; //returns true if item is found, false otherwise
  }

  /**
   * Determines if for each item in the specified collection, there is an item in this set that is
   * equal to it.
   * 
   * @param items - the collection of items sought in this set
   * @return true if for each item in the specified collection, there is an item in this set that is
   *         equal to it; otherwise, returns false
   */
  @Override
  public boolean containsAll(Collection<? extends String> items) {
    for (String x : items) {
      if (!this.contains(x)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns true if this set contains no items.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of items in this set.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Finds the next prime number after the given number
   * 
   * @param number
   * @return
   */
  private int nextPrime(int n) {
    while (true) {
      n += 1;
      int sqt = (int) Math.sqrt(n);
      for (int i = 2; i <= sqt; i++) {
        if (n % i == 0) {
          break;
        }
      }
      if (checkPrime(n))
        return n;
    }

  }

  /**
   * Checks to see if the number is prime
   * 
   * @param num
   * @return
   */
  private boolean checkPrime(int n) {
    if (n < 2)
      return false;

    if (n == 2)
      return true;

    if (n % 2 == 0)
      return false;

    for (int i = 3; i * i <= n; i += 2)
      if (n % i == 0)
        return false;

    return true;
  }

  /**
   * Gets hashcode of a given item
   * 
   * @param item - item to get hashcode
   * @return hashcode
   */
  private int getHashCode(String item) {
    return ((func.hash(item) & 0x7fffffff) % cap);
  }

  /**
   * checks if a number is prime, if not returns the next prime
   * 
   * @param n number to be checked
   * @return returns same number if it is prime, returns the next prime number otherwise
   */
  private int makePrime(int n) {
    if (checkPrime(n))
      return n;
    else
      return (nextPrime(n));

  }
/**
 * Finds a certain item in a list
 * @param item - item to be found
 * @return item if found, null otherwise.
 */
  private String find(String item) {
    int code = getHashCode(item);
    if (table[code] == null)
      return null;
    for (String x : table[code])
      if (x.equals(item))
        return x;
    return null;
  }
/**
 * resizes the Linked list to double the size
 */
  private void resize() {
    @SuppressWarnings("unchecked")
    LinkedList<String>[] temp = (LinkedList<String>[]) new LinkedList[Math.max(cap * 2, 1)];
    for (int i = 0; i < cap; i++) {
      temp[i] = table[i];
    }
    table = temp;
  }

  /**
   * Gets the number of collisions
   * 
   * @return collisions
   */
  public int getCollisions() {
    return collisions;
  }

}
