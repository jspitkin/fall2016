package assignment10;

public class BadHashFunctor implements HashFunctor{


	@Override
	/*
	 * Create one hashing function that you think is bad 
	 * (although not as bad as the one above). You should expect that it will result in many collisions. 
	 * Put the hashing function in the class BadHashFunctor.(non-Javadoc)
	 * 
	 * input: String item
	 * return: length
	 */
	public int hash(String item) {
		
		return item.length();
	}

}
