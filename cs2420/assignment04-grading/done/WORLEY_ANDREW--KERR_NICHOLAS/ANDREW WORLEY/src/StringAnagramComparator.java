package assignment04;

import java.util.Comparator;

/**
 * Class represents a comparator used to sort
 * strings by their a -z sorted form
 * @author Andrew Worley: u0651238
 * @author Nicholas Kerr: u0125990
 * last update, 9/20/16 22:10
 */
public class StringAnagramComparator implements Comparator<String> {

	@Override
	/**
	 * Method uses an insertion sort for each argument
	 * and uses the String compareTo method for 
	 * a lexicographical comparison result
	 * @param lhs -- String to be compared by
	 * @param rhs -- String to be compared
	 * @return -- -1 if lhs precedes rhs, 0 if equal, and 1 if rhs precedes lhs
	 */
	public int compare(String lhs, String rhs) {
		lhs = AnagramUtil.sort(lhs);
		rhs = AnagramUtil.sort(rhs);
		
		return lhs.compareTo(rhs);
	}

}
