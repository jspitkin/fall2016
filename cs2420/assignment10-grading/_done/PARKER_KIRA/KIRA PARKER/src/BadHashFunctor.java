package assignment10;

/**
 * 
 * @author Kira Parker u1073760
 *
 */
public class BadHashFunctor implements HashFunctor {

	/**
	 * @param item -- String to be hashed
	 * @return length of the string
	 */
	@Override
	public int hash(String item) {
		return item.length();
	}

}
