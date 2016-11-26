package assignment10;

/**
 * 
 * @author Christian Hansen, U0621884
 * 
 * This is a functor class which contains a method to hash an object. It is an 
 * example of a poor Hash function so it does not perform well. It returns the length
 * of the string. This is poor because strings of the same length will have the same 
 * hash (which will be a lot) and it does not produce a very large hash which is problematic
 * for a hash table that is very large
 *
 */
public class BadHashFunctor implements HashFunctor{
	
	/**
	 * This method returns the length of the item to be
	 * used as a hash
	 * 
	 * @return the length of item
	 */
	@Override
	public int hash(String item) {
		return item.length();
	}

}
