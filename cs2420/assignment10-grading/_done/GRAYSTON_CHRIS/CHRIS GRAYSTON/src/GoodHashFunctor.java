package assignment10;

/**
 * Good quality of a Hash Function designed 
 * to cause minimal possible collisions.
 * 
 * @author Chris Grayston u0906710
 *
 */
public class GoodHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int hashValue = 0;
		for(int i = 0; i < item.length(); i++)
			hashValue = hashValue*29 + item.charAt(i);
			
		return hashValue;
	}

}
