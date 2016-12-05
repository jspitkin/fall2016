package assignment10;

/**
 * This class represents a hash function which is good.
 * This function multiplies the value by 128, then adds
 * the corresponding value to the character value
 * of the string.
 * 
 * @author Cooper Pender (u0843147)
 * Last Edited On: 11/5/2016
 *
 */
public class GoodHashFunctor implements HashFunctor{

	@Override
	/**
	 * @inheritDoc
	 */
	public int hash(String item) {
		int hashValue = 31;
		for(int i = 0; i < item.length() / 2; i++) {
			hashValue = hashValue * 11 + item.charAt(i);
		}
		return Math.abs(hashValue);
	}

}
