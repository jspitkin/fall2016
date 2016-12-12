package assignment11;

import java.util.Comparator;

/**
 * a simple comparator to reverse the ordering of strings for easy testing of comparator implementation
 * in priority queue
 * @author Braeden Barwick u0959391
 */
public class ReverseStringComparator implements Comparator<String> {

	@Override
	public int compare(String s1, String s2) {
		if(s1.compareTo(s2) < 0)
			return 1;
		else if( s1.compareTo(s2) > 0)
			return -1;
		else
			return 0;
	}

}
