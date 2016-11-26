package assignment10;

/**
 * Creates a bad hash code that will result in many collisions
 * 
 * @author Spencer Jewett (U0677872)
 * @since 11/9/2016
 *
 */
public class BadHashFunctor implements HashFunctor {

	/**
	 * turns the item inputted into a hash
	 * 
	 * @param item - item who hash code is to be determined
	 * @return - and integer of the hash code created
	 */
	public int hash(String item) {
		if(item.equals("")){
			return 0;
		}
		char lastChar = item.charAt(item.length() - 1);
		int hashed = (int) lastChar;
		return hashed;
	}

}
