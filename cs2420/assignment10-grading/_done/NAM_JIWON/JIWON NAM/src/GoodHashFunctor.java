package assignment10;

/**
 * Good Hash Functor class
 * 
 * @author Jiwon Nam
 *
 */
public class GoodHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int primes[] = new int[] {2, 3, 5, 7, 13, 17, 19, 23};
		int result = 0;
		for(int i = 0; i < item.length(); i++) {
			result += item.charAt(i) * primes[i % item.length()] * primes[7 - i % item.length()];
			result = result *13;
		}
		
		return Math.abs(result);
	}

}
