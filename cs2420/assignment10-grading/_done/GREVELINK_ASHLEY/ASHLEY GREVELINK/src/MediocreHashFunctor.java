package assignment10;

/**
 * 
 * @author Ashley Grevelink u0749357
 *
 */
public class MediocreHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		return item.length();
	}

}
