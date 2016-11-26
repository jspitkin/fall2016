package assignment10;

/**
 * 
 * @author Chase Stephens
 * 
 * Good Hash Function
 *
 */

public class GoodHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		Integer hash = 3;
		//uses prime numbers to help generate unique hashes and depends on both length and string contents
		for(int i = 0; i < item.length(); i++){
			hash = hash*23 + Character.getNumericValue(item.charAt(i));
		}
		return Math.abs(hash);
	}
} 
