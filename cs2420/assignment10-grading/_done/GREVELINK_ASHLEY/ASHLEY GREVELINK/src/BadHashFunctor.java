package assignment10;

/**
 * 
 * @author Ashley Grevelink u0749357
 *
 */
public class BadHashFunctor implements HashFunctor {

	@Override
	public int hash(String item) {
		return 5;
	}

}
