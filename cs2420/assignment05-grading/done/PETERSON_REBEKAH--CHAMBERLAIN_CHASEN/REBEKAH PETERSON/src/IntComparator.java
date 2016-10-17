package assignment05;

import java.util.Comparator;

/**
 * A comparator for Character objects. Used for testing AnagramUtil.java.
 * 
 * @author Chasen Chamberlain u0583257 & Rebekah Peterson u0871657
 */
public class IntComparator implements Comparator<Integer> {

    	/**
	 * Compares two Integer objects according to natural ordering.
	 * 
	 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second
	 */
    	@Override
	public int compare(Integer lhs, Integer rhs) {
		return lhs.compareTo(rhs);
	}

}
