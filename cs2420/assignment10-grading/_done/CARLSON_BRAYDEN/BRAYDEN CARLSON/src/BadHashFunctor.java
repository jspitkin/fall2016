package assignment10;

/**
 * A bad hash functor that will result in many collisions
 * @author Brayden Carlson
 *
 */
public class BadHashFunctor implements HashFunctor {

	/**
	 * Returns the hash of the given string 'item'
	 */
	@Override
	public int hash(String item) {
		return item.length();
	}

}
