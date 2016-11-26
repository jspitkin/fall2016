package assignment10;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
public class GoodHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int hashcode = 0;
		for (int i = 0; i < item.length(); i++) {
			hashcode = hashcode * 37 + item.charAt(i);
		}
		return hashcode;

	}
}
