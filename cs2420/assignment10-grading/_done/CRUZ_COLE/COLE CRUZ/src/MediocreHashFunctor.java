//Cole Cruz
package assignment10;

/**
 * A mediocre hash functor for Strings.
 * 
 * @author Cole Cruz
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	/**
	 * Creates a hash from the given String.
	 * 
	 * @param --
	 *            the String to hash.
	 */
	@Override
	public int hash(String item) {
		if (item.length() > 0) {
			return ((int) item.charAt(0)) + ((int) item.charAt(item.length() - 1));
		}
		return 0;
	}

}
