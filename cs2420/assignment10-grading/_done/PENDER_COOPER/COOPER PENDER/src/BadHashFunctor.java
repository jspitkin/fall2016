package assignment10;

/**
 * This class represents a hash function that is bad.
 * This hash function adds all the characters of the 
 * string and then mods the result by the capacity.
 * 
 * @author Cooper Pender (u8043147)
 * Last Edited On: 11/5/2016
 *
 */
public class BadHashFunctor implements HashFunctor{

	@Override
	/**
	 * @inheritDoc
	 */
	public int hash(String item) {
		
		int hashValue = 0;
		
		for(int i = 0; i < item.length(); i++) {
			hashValue += item.charAt(i);
		}
		
		return Math.abs(hashValue);
	}
}
