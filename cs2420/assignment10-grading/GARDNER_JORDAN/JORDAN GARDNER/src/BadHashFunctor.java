package assignment10;
/**
 * 
 * @author Jordan Gardner
 *
 */
public class BadHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		
		return item.charAt(0);
	}

}
