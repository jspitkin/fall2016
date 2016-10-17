package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
/**
 * @author Noah Goetz
 * @authorUID u1046618
 * 
 * @partner Zhangtuoming Zhao
 * @partnerUID u0847610
 */
public class MergesortThresholdExperiment extends SortUtil
{
	
	
	
	public static void main(String[] args)
	{
		
		/**
		 * this class is going to make a interger comparator for the Tester
		 * 
		 *
		 */
		 class IntegerComparator implements Comparator<Integer> {
			/**
			 * this is a comparator for comparing the integer
			 */
			public int compare(Integer objectInteger1, Integer objectInteger2) {

				return objectInteger1.compareTo(objectInteger2);
			}

		}

		 class StringComparator implements Comparator<String> {
			/**
			 * the method is OrderByAuthor from assignment02. Which is a comparator
			 * for comparing the String
			 * 
			 * @param ojectString1
			 * @param objectString2
			 * @return
			 */
			public int compare(String ojectString1, String objectString2) {

				return (int) (ojectString1.compareTo(objectString2));

			}

		}
		 
		 
		 
		 IntegerComparator cmp = new IntegerComparator();
		 
		int timesToLoop = 1000;
		for(int size = 1000; size <= 3000; size += 1000)
		{
			System.out.println("when the size of the list is" + " " + size);
			int thresholdMax = size;
		    
		    // For each problem size n . . .
		    for (int n = 100; n <= thresholdMax; n += 100) {

		      // Generate a new "random" integer list of size n.
		    ArrayList<Integer> testArr = new ArrayList<Integer>();
		    testArr = generateAverageCase(size);
		    thresHoldChange(n);
		      
		      long startTime, midpointTime, stopTime;

		      // First, spin computing stuff until one second has gone by.
		      // This allows this thread to stabilize.
		      startTime = System.nanoTime();
		      while (System.nanoTime() - startTime < 1000000000) { // empty block 
		      }

		      // Now, run the test.  
		      startTime = System.nanoTime();

		      for (int i = 0; i < timesToLoop; i++) {
		  		  mergesort(testArr,cmp);
		      }

		      midpointTime = System.nanoTime();

		      // Run an empty loop to capture the cost of running the "timesToLoop" loop.
		    

		      stopTime = System.nanoTime();

		      // Compute the time, subtract the cost of running the loop
		      // from the cost of running the loop and doing the lookups.
		      // Average it over the number of runs.
		      double averageTime = ((double)((midpointTime - startTime) - (stopTime - midpointTime)))
		          / (double)timesToLoop;

		      System.out.println(n + "\t" + averageTime);
		  }
		}
		
	  }
}
