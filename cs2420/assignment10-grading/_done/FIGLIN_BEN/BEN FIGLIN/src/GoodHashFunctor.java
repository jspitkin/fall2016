package assignment10;

/**
 * A really good hash functor
 * 
 * @author Ben Figlin (u1115949)
 *
 */
public class GoodHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		// random prime number seed
		int hash = 37; 
		
		for (int i = 0; i < item.length(); i++) {
			// multiply by a prime and add the next char value (also multiplied by a prime)
		    hash = hash*53 + item.charAt(i); 
		}
		
		return hash > 0 ? hash : -hash;
	}

}
