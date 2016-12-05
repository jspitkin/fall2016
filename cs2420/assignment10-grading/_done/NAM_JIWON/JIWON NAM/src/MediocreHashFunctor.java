package assignment10;

/**
 * Mediocre Hash Functor class
 * 
 * @author Jiwon Nam
 *
 */
public class MediocreHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int result = 0;
		int[] switching = new int[]{2, 3};
		for(int i = 0; i < item.length(); i++) {
			result += (int)(item.charAt(i)) * switching[i % 2];
			result = result * 5;
		}
		
		
		return result;
	}

}
