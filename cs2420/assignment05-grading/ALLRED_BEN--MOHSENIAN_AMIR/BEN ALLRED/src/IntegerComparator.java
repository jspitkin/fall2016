package assignment05;

import java.util.Comparator;

/**
 * Simple Comparator class that uses Integers natural comparing
 * @author Benjamin Allred u1090524
 * @author Amir Mohsenian u0737564
 */
public class IntegerComparator implements Comparator<Integer>
{

	public int compare(Integer left, Integer right) {
		if(left.compareTo(right) > 0)
		{
			return 1;
		}
		else if(left.compareTo(right) == 0)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
	
}