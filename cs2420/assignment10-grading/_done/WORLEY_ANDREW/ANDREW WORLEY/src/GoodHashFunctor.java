package assignment10;

/**
 * A good hash implementation
 * @author Andrew Worley: u0651238
 */
public class GoodHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int hashVal = 10;	
		int result = item.length()*(item.charAt(0)*10);
		
		for(int i = 0; i < item.length(); i++) {
			result += hashVal*item.charAt(i)*item.charAt(item.length()-1-i);
		}
		
		if (result < 0) {
			result *= -1;
		}
		return result;
	}

}