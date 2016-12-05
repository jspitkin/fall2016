package assignment10;

/**
 * This class represents a hash function which is mediocre.
 * This function take an initial value of 7 and multiplies it
 * by 11 and then adding the character values to it.
 * 
 * @author Cooper Pender (u0843147)
 * Last Edited On: 11/5/2016
 *
 */
public class MediocreHashFunctor implements HashFunctor{

	@Override
	/**
	 * @inheritDoc
	 */
	public int hash(String item) {
		int hashValue = 7;
		for(int i = 0; i < item.length(); i++) {
			hashValue = hashValue*31 + item.charAt(i);
		}
		return Math.abs(hashValue);
	}
}
