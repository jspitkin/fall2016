package assignment10;

/**
 * A 'not so good' hash functor
 * 
 * @author Ben Figlin (u1115949)
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int hash = 0;
		for (int i = 0; i < item.length(); i++) {
		    hash += item.charAt(i);
		}
		return hash;
	}

}
