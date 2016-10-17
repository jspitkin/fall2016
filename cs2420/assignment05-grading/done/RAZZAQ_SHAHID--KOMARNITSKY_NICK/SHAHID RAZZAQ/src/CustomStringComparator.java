package assignment05;

import java.util.Comparator;
/**
 * This class creates a custom string comparator which lexicographically sorts the strings 
 * @authors = Nickolas Komarnitsky u0717854 , Shahid Bilal Razzaq u0996062
 *
 */
public class CustomStringComparator implements Comparator<String> {

	@Override
	/**
	 * returns an int based on comparison values.
	 */
	public int compare(String o1, String o2) {
		if (o1.toLowerCase().compareTo(o2.toLowerCase()) == 0) {
			return 0;
		}
		else {
			return o1.toLowerCase().compareTo(o2.toLowerCase());
		}
	}
}
