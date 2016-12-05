package assignment10;
/**
 * MediocreHashFunctor
 * @author Lin Jia
 * uid: u1091732
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	
	public int hash(String item) {
		int hash = 0;
		
		for (int i = 0; i < item.length(); i++) {
		    hash = hash*2 + item.charAt(i);
		}
		return hash;
	}

}
