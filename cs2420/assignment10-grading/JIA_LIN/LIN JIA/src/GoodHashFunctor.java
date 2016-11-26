
package assignment10;
/**
 * GoodHashFunctor
 * @author Lin Jia
 * uid: u1091732
 *
 */

public class GoodHashFunctor implements HashFunctor{


	@Override
	public int hash(String item) {
		int hash = 7;
		for (int i = 0; i < item.length(); i++) {
		    hash = Math.abs(hash*31 + item.charAt(i));
		}
		return hash;
	}
}