package assignment10;

/**
 * 
 * @author Chase Stephens
 * 
 * Bad hash function. 
 *
 */

public class BadHashFunctor implements HashFunctor {

	//This hash function's outcome is only based on string length, making it a poor choice. 
	@Override
	public int hash(String item) {
		return (item.length()*item.length()*item.length());
	}

}
