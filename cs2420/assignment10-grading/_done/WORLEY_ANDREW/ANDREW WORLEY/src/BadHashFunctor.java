package assignment10;

/**
 * A bad hash implementation for strings
 * @author Andrew Worley: u0651238
 */
public class BadHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		return item.length();
	}
}
