package assignment10;

/**
 * A really bad hash functor
 * 
 * @author Ben Figlin (u1115949)
 *
 */
public class BadHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		return item.length();
	}

}
