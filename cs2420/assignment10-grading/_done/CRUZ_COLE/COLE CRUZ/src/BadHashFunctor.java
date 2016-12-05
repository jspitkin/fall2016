//Cole Cruz
package assignment10;

/**
 * A bad hash functor for Strings.
 * 
 * @author Cole Cruz
 *
 */
public class BadHashFunctor implements HashFunctor {

	/**
	 * Creates a hash from the given String.
	 * 
	 * @param --
	 *            the String to hash.
	 */
	@Override
	public int hash(String item) {
		return item.length();
	}

}
