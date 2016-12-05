package assignment10;

/**
 * Bad quality of a Hash Function 
 * designed to cause many collisions
 * 
 * @author Chris Grayston u0906710
 *
 */
public class BadHashFunctor implements HashFunctor{
	
	@Override
	public int hash(String item) {
		int mediocreHash = 0;
		for(int i = 0; i < item.length(); i += 4) {
			mediocreHash += item.charAt(i) + 1;
		}
		return mediocreHash;
	}
}
