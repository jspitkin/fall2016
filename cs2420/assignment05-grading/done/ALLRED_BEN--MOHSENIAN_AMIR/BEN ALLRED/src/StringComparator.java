package assignment05;

import java.util.Comparator;

/**
 * Simple Comparator class that uses Strings natural comparing
 * @author Benjamin Allred u1090524
 * @author Amir Mohsenian u0737564
 */
class StringComparator implements Comparator<String>
{
	public int compare(String first, String second)
	{
		if(first.compareTo(second) > 0)
		{
			return 1;
		}
		else if(first.compareTo(second) == 0)
		{
			return 0;
		}
		else
		{
			return -1;
		}
	}
}