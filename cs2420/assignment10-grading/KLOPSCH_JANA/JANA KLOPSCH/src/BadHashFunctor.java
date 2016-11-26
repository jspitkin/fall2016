package assignment10;

/**
 * Hash Function for an inputed String.
 * @author Jana Klopsch (u0854469)
 * Last Modified 11-6-16
 *
 */
public class BadHashFunctor implements HashFunctor{
	
	/**
	 * Returns an integer hash value for an inputed String.  This particular function
	 * returns the length of the String.
	 */
	public int hash(String item){
		return item.length();
	}

}
