package assignment11;

import java.util.Comparator;

/**
 * 
 * @author Yixiong Qin
 *
 */
public class comparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		
		if (o1.compareTo(o2) > 0) {
			return -1;
			
		} else {
			return 1;
		}
	}
}
