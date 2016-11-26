package assignment10;
/**
 * BadHashFunctor
 * @author Lin Jia
 * uid: u1091732
 *
 */
public class BadHashFunctor implements HashFunctor{

	
	public int hash(String item) {
		
		return item.length();
	
	}
	
}
