package assignment10;

/**
 * Mediocre hash implementation
 * @author Andrew Worley: u0651238
 *
 */
public class MediocreHashFunctor implements HashFunctor{

	@Override
	public int hash(String item) {
		int result = 0;
		item = scrambleString(item);
		
		for (int index = 0; index < item.length(); index++) {
			result += item.charAt(index);
		}
		
		if (result < 0) {
			result *= -1;
		}
		
		return result;
	}
	
	/**
	 * Driver method, scrambles the passed String
	 * by recursively dividing the string into smaller pieces
	 * and swapping each left and right hand piece.
	 * @param item -- String data
	 * @return -- Scrambled String output from the input String
	 */
	private String scrambleString(String item) {
		String result = null;
		
		result = scrambleStringRecursive(item);
		
		return result;
	}
	
	/**
	 * Divides the input into halves then swaps the left and
	 * right hand side.
	 * @param item -- String item
	 * @return -- A scrambled string
	 */
	private String scrambleStringRecursive(String item) {
		if (item.length() < 2) {
			return item;
		}
		
		int mid = item.length()/2;
		String lhs = item.substring(0, mid);
		String rhs = item.substring(mid+1/item.length());
		
		lhs = scrambleStringRecursive(lhs);
		rhs = scrambleStringRecursive(rhs);
		
		return rhs+lhs;
	}

}
