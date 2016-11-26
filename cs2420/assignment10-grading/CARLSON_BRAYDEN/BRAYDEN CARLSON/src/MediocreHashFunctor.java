package assignment10;

/**
 * A mediocre hash functor
 * @author Brayden Carlson
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	/**
	 * Returns the hash of the given string 'item'
	 */
	@Override
	public int hash(String item) {
		char[] charArray = item.toCharArray();
		int sum = 0;
		for(char c : charArray) {
			sum += (int) c;
		}
		return sum;
	}

}
