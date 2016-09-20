package assignment03;

import java.util.Comparator;

/**
 * A comparator to compare two different objects of type E.
 * @author Cole Cruz and Colton Haacke
 * u1065058 and u0935270
 */
public class SearchSetComparator<E> implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
	    	if (o1 instanceof Integer && o2 instanceof Integer) {
	    	    if ((Integer) o1 > (Integer) o2) {
	    		return 1;
	    	    }
	    	    else if ((Integer) o1 == (Integer) o2) {
	    		return 0;
	    	    }
	    	    else {
	    		return -1;
	    	    }		
	    	}
		return o1.toString().compareTo(o2.toString());
	}

}
