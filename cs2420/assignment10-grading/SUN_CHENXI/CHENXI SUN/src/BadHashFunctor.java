package assignment10;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
public class BadHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {

		return item.length();
	}

}
