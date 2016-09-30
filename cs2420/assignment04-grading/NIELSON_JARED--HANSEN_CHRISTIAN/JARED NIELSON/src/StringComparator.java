package assignment04;

import java.util.Comparator;

/**
 * Implementation of the Comparator interface for Strings. Compares
 * strings based on their natural ordering ie compare(s1, s2) == s1.compareTo(s2)
 * @author Jared Nielson u049206 Christian Hansen u0621884
 *
 */
public class StringComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
}
