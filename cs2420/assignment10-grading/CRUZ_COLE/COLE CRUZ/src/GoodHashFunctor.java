//Cole Cruz
package assignment10;

/**
 * A good hash functor for Strings.
 * 
 * @author Cole Cruz
 *
 */
public class GoodHashFunctor implements HashFunctor {

	/**
	 * Creates a hash from the given String.
	 * 
	 * @param --
	 *            the String to hash.
	 */
	@Override
	public int hash(String item) {
		int charTotal = 0;
		for (int index = 0; index < item.length(); index++) {
			charTotal += (int) item.charAt(index);
		}
		return charTotal;
	}

}
