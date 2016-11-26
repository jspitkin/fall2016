package assignment10;

/**
 * Mediocre quality of a Hash Function designed 
 * to still cause some collisions
 * 
 * @author Chris Grayston u0906710
 *
 */
public class MediocreHashFunctor implements HashFunctor{
	
	@Override
	public int hash(String item) {
		int mediocreHash = 0;
		for(int i = 0; i < item.length(); i += 2) {
			mediocreHash += item.charAt(i) * 2;
		}
		return mediocreHash;
	}
}
