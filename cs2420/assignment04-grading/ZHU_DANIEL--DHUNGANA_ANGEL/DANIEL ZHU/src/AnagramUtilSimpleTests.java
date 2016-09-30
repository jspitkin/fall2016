package assignment04;

import java.util.Arrays;
import java.util.Comparator;

public class AnagramUtilSimpleTests
{
	
	
	public static void main(String [] args)
	{
		AnagramUtil util = new AnagramUtil();
		
		String [] array = new String[]{"data", "tata", "atat", "cat"};
		String [] array2 = new String[]{"edbca", "EBcaB", "bbace", "act", "ebbac", "cat", "tac", "tca"};
		String [] array3 = new String[0];
		
		System.out.println(Arrays.toString(util.getLargestAnagramGroup(array)));
		
		System.out.println(Arrays.toString(util.getLargestAnagramGroup(array2)));
		
		System.out.println(Arrays.toString(util.getLargestAnagramGroup(array3)));
		
		
	 
	 
		
	}

}
