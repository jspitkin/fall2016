package assignment10;

/**
 * Used for testing quality of GoodHashFunctor.
 * @author Kira Parker, u1073760
 *
 */
public class JavaHashFunctor implements HashFunctor{

	/**
	 * Used for testing quality of GoodHashFunctor
	 * @param item -- String to be hashed
	 * @return String hashcode 
	 */
	@Override
	public int hash(String item) {
		return Math.abs(item.hashCode());
	}

}
