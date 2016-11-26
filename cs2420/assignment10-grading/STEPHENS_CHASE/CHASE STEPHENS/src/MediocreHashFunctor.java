package assignment10;

/**
 * 
 * @author Chase Stephens
 * 
 * Mediocre hash fucntion. 
 *
 */

public class MediocreHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		Integer hash = 2;
		//uses even number to generate less unique hashes and depends on both length and string contents
		for(int i = 0; i < item.length(); i++){
			hash = hash*5 + Character.getNumericValue(item.charAt(i));
		}
		return Math.abs(hash);
	}
}
