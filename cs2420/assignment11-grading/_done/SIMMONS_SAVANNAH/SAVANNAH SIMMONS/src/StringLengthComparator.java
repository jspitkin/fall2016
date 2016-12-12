package assignment11;

import java.util.Comparator;
/**
 * Example comparator for strings that returns a positive integer if the first string
 * is longer than the second, a negative if the first is shorter,
 * and zero if they are the same length
 * 
 * @author Savannah Simmons, u1086770
 *
 */
public class StringLengthComparator implements Comparator<String>{

		@Override
		public int compare(String string1, String string2){
			return string1.length() - string2.length();
		}
		

}
