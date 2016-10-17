package assignment05;

import java.util.Comparator;

/**
 * This Class creates a custom comparator which reverses the orders in which
 * strings are evaluated
 * 
 * @authors = Nickolas Komarnitsky u0717854 , Shahid Bilal Razzaq u0996062
 * 
 *
 *
 */
public class CustomComparator<T> implements Comparator<T> {

	@Override
	public int compare(T o1, T o2) {
		// TODO Auto-generated method stub
		return ((Comparable) o1).compareTo((Comparable) o2);
	}

}
