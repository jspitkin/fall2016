package assignment10;

/**
 * 
 * @author Ashley Grevelink u0749357
 *
 */
public class GoodHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		int hash = 5;
		for (int index = 0; index < item.length(); index++) {
		    hash = hash*31 + item.charAt(index);
		}
		return hash;
	}

}
