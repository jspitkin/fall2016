package assignment05;

import java.util.Comparator;

/**
 * Comparator class for strings. Compare strings using their natural (lexocographical) ordering.
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class StringComparator implements Comparator<String>{
	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2);
	}
}
