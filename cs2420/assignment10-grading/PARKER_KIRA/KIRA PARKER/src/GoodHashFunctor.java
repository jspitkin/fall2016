package assignment10;

/**
 * 
 * @author Kira Parker u1073760
 *
 */
public class GoodHashFunctor implements HashFunctor {

	/**
	 * @param item -- String to be hashed
	 * @return sum of integer values of all characters in the String
	 */
	@Override
	public int hash(String item) {
		int sum = 0;
		for(int index = 0; index<item.length(); index++){
			sum += (int)item.charAt(index);
		}
		sum = Math.abs(sum);
		if(sum<0){
			return 2^31-1;
		}
		return sum;
	}

}
