package assignment10;

import java.util.Collection;


/**
 * @author Abdulaziz Aljanahi
 * @uid u0901606
 * 
 * Creates a Hash Table that utilizes quadratic probing
 */
public class QuadProbeHashTable implements Set<String> {
  private String[] table;
  private HashFunctor func;
  private int size, cap, collisions;
  private static final double load = 0.5;

  /**
   * Constructs a hash table of the given capacity that uses the hashing function specified by the
   * given functor.
   */
  public QuadProbeHashTable(int _cap, HashFunctor _func) {
    table = new String[makePrime(_cap)];
    cap = makePrime(_cap);
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
    if (size >= (cap * load)) // if the size is smaller than
      rehash();
    int code = getHashCode(item); // gets absolute value of hashcodee
    if (table[code] != null) {
      if (table[code].equals(item))
        return false;
      else {
        int ind = probe(item); //if hashcode not empty performs a probe
        if (ind <= 0) {
          return false;
        } else {
          table[ind] = item;
          size++;
          return true;
        }
      }
    }
    size++;
    table[code] = item;
    return true;
  }

  /**
   * Performs quadratic probe
   * 
   * @param item
   * @return
   */
  private int probe(String item) {
    int rehash = 1, ind = getHashCode(item);
    while (table[ind] != null) {
      collisions++;
      ind = Math.abs((ind + (rehash * rehash)) % cap);
      if (table[ind] == null) {
        return ind;
      } else if (table[ind].equals(item)) {
        return -1;
      }
      rehash++;
    }
    return 0;
  }

  /**
   * resizes, and rehashes the current table.
   */
  private void rehash() {
    cap = nextPrime(cap * 2); //set capacity to double the next prime number size
    String[] tempTable = new String[cap]; 
    int code;
    for (String item : table) {
      if (item != null) { //if item is not null rehash it, and insert it into the temporary new table
        code = getHashCode(item);
        if (tempTable[code] == null) { //if the current hash code is empty inset
          tempTable[code] = item;
        } else {
          if (tempTable[code] != null) { //else perform a probe
            int probe = 1;
            int ind = getHashCode(item);
            while (tempTable[ind] != null) { //if not null perform another probe until it finds an empty spot
              ind = Math.abs((ind + (probe * probe)) % cap);

              if (tempTable[ind] == null) {
                tempTable[ind] = item;
                break;
              }
              probe++;
            }
          }
        }
      }
    }
    table = tempTable;
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
  @Override
  public void clear() {
    table = new String[cap];
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

    int code = getHashCode(item);
    if (table[code] == null)
      return false;
    else if (table[code].equals(item))
      return true;
    else {
      if (probe(item) == -1)
        return true;
      else
        return false;
    }
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
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of items in this table.
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
  private boolean checkPrime( int n) {
    if (n < 2) //if less than two then not prime.
      return false;
    if (n == 2)
      return true;
    if (n % 2 == 0)
      return false;
    for (int i = 3; i * i <= n; i += 2)
      if (n % i == 0) //checks if the mod = 0 for all prior numbers
        return false; //if yes not prime
    return true;
  }

  /**
   * Gets hashcode of a given item
   * 
   * @param item - item to get hashcode
   * @return hashcode
   */
  private int getHashCode(String item) {
    return Math.abs(func.hash(item) % cap); //absolute value of hash function
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
   * Gets the number of collisions
   * 
   * @return
   */
  public int getCollisions() {
    return collisions;
  }

}
