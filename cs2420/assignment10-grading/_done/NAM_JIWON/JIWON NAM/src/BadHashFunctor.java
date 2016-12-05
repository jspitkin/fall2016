package assignment10;

/**
 * Bad Hash Functor class
 * 
 * @author Jiwon Nam
 *
 */
public class BadHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int total = 0;
		for(int i = 0; i < item.length(); i++) {
			total += item.charAt(i);
		}
		
		return Math.abs(total);
	}

}
