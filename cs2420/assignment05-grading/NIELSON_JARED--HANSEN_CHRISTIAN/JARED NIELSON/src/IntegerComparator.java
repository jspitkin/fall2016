package assignment05;

import java.util.Comparator;

/**
 * Comparator class for Integers simply compares them through subtraction
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class IntegerComparator implements Comparator<Integer> {
	
	/**
	 * Compares two Integers through subtraction
	 */
	@Override
	public int compare(Integer o1, Integer o2) {
		return o1 - o2;
	}

}
