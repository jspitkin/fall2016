package assignment10;

/**
 * A good hash functor that will not result in many collisions
 * @author Brayden Carlson
 *
 */
public class GoodHashFunctor implements HashFunctor{

	/**
	 * Returns the hash of the given string 'item.' Uses the algorithm Java uses for String.hashCode()
	 */
	@Override
	public int hash(String item) {
		char[] charArray = item.toCharArray();
		int n = item.length();
		int sum = 0;
		for(int i = 0; i < n; i++) {
			int pow = 31;
			for(int j = 0; j < n - 2 - i; j++) { // Exponent
				pow *= 31;
			}
			sum += charArray[i] * pow;
		}
		return Math.abs(sum);
	}

}
