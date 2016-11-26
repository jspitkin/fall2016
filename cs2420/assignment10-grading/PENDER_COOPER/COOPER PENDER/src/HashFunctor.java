package assignment10;

/**
 * Serves as a guide for how to define a functor that contains a hashing
 * function for String items (i.e., the hash method).
 * 
 * @author Cooper Pender (u0843147)
 * Last Edited On: 11/5/2016
 * 
 */
public interface HashFunctor {

  /**
   * Hashes a string, returning a unique integer.
   * 
   * @param item - string to be hashed.
   * @return - unique integer.
   */
  public int hash(String item);

}