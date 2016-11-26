package assignment10;

/**
 * 
 * @author Kira Parker u1073760
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	/**
	 * @param item -- String to be hashed
	 * @return product of integer values of all characters in the String
	 */
	@Override
	public int hash(String item) {
		int product = 1;
		for(int index = 0; index<item.length(); index++){
			product *= (int)item.charAt(index);
		}
		product = Math.abs(product);
		if(product<0){ //product = MIN_INTEGER
			return 2^31-1;
		}
		return product;
	}

}
