package assignment10;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int hashcode=0;
		for(int i=0;i<item.length();i++){
			hashcode+=item.charAt(i);
		}
		return hashcode;
	}

}
