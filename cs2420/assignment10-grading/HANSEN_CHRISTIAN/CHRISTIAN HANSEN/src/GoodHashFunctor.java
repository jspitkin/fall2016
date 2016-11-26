package assignment10;

/**
 * 
 * @author Christian Hansen, U0621884
 * 
 * This is a functor class which contains a method to hash an object. It is an 
 * example of a good Hash function so it performs fairly well. Multiplies the total
 * by 61 which is a prime number and then adds on the char. It does this until the
 * end of the hash and then returns the absolute value of the hashTotal. I believe it 
 * performs well because it creates very large numbers and each number is unique to the
 * String given including the order of the characters. By multiplying by a large prime
 * number we create a larger number without adding on extra numbers that can be factored out.
 *
 */
public class GoodHashFunctor implements HashFunctor{

	/**
	 * This function returns a hash based on the string. It returns the total which is 
	 * created by multiplying the total by 61 and adding the char.
	 * 
	 * @return hashTotal
	 */
	@Override
	public int hash(String item) {
		
		int hashTotal = 0;
		
		for(int index = 0; index < item.length(); index++){
			hashTotal = (hashTotal * 17) + item.charAt(index);
		}
		
		return Math.abs(hashTotal);
	}

}
