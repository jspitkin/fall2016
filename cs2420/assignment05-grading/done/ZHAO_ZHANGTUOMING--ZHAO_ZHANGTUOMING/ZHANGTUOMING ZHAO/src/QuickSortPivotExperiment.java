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


public class QuickSortPivotExperiment extends SortUtil
{		
	
	
	public static void main(String[] args)
	{
		
		System.out.println(" size of the list" + "\t" + "pivot selection 1" + "\t" +"pivot selection 2" + 
				"\t" + "pivot selection 3");
		int timesToLoop = 1000;
		int size = 1000;
		
		/**
		 * this class is going to make a interger comparator for the Tester
		 */
		class IntegerComparator implements Comparator<Integer> {
			/**
			 * this is a comparator for comparing the integer
			 */
			@Override
			public int compare(Integer objectInteger1, Integer objectInteger2) {

				return objectInteger1.compareTo(objectInteger2);
			}

		}

		IntegerComparator integerCmp = new IntegerComparator();
		
		
		for (int n = 100; n <= size; n += 100)
		{
			ArrayList<Integer> testArr_1 = new ArrayList<Integer>();
	    	testArr_1 = generateAverageCase(size);
	    	ArrayList<Integer> testArr_2 = generateAverageCase(size);
	    	ArrayList<Integer> testArr_3 = generateAverageCase(size);
	    	long startTime_1, midpointTime_1, stopTime_1;

		      // First, spin computing stuff until one second has gone by.
		      // This allows this thread to stabilize.
		      startTime_1 = System.nanoTime();
		      while (System.nanoTime() - startTime_1 < 1000000000) { // empty block 
		      }

		      // Now, run the test.  
		      startTime_1 = System.nanoTime();

		      for (int i = 0; i < timesToLoop; i++) {
		    	  quick_sort_1(testArr_1, 0, testArr_1.size() - 1, pivotItemSelection_1(testArr_1),integerCmp);
		      }

		      midpointTime_1 = System.nanoTime();

		      // Run an empty loop to capture the cost of running the "timesToLoop" loop.
		      /*for (long i = 0; i < timesToLoop; i++) { // empty block 
		      }*/

		      stopTime_1 = System.nanoTime();

		      // Compute the time, subtract the cost of running the loop
		      // from the cost of running the loop and doing the lookups.
		      // Average it over the number of runs.
		      double averageTime_1 = ((double)((midpointTime_1 - startTime_1) - (stopTime_1 - midpointTime_1)))
		          / (double)timesToLoop;
		      
		      long startTime__2,midpointTime_2, stopTime_2;

		      // First, spin computing stuff until one second has gone by.
		      // This allows this thread to stabilize.
		      startTime__2 = System.nanoTime();
		      while (System.nanoTime() - startTime__2 < 1000000000) { // empty block 
		      }

		      // Now, run the test.  
		      startTime__2 = System.nanoTime();

		      for (int i = 0; i < timesToLoop; i++) {
		    	  quick_sort_1(testArr_2, 0, testArr_2.size() - 1, pivotItemSelection_2(testArr_2),integerCmp);
		      }

		      midpointTime_2 = System.nanoTime();

		      // Run an empty loop to capture the cost of running the "timesToLoop" loop.
		      /*for (long i = 0; i < timesToLoop; i++) { // empty block 
		      }*/

		      stopTime_2 = System.nanoTime();

		      // Compute the time, subtract the cost of running the loop
		      // from the cost of running the loop and doing the lookups.
		      // Average it over the number of runs.
		      double averageTime_2 = ((double)((midpointTime_2 - startTime__2) - (stopTime_2 - midpointTime_2)))
		          / (double)timesToLoop;
		      
		      long startTime_3, midpointTime_3, stopTime_3;

		      // First, spin computing stuff until one second has gone by.
		      // This allows this thread to stabilize.
		      startTime_3 = System.nanoTime();
		      while (System.nanoTime() - startTime_3 < 1000000000) { // empty block 
		      }

		      // Now, run the test.  
		      startTime_3 = System.nanoTime();

		      for (int i = 0; i < timesToLoop; i++) {
		    	  quick_sort_1(testArr_3, 0, testArr_3.size() - 1, pivotItemSelection_3(testArr_3,integerCmp),integerCmp);
		      }

		      midpointTime_3 = System.nanoTime();

		      // Run an empty loop to capture the cost of running the "timesToLoop" loop.
		      /*for (long i = 0; i < timesToLoop; i++) { // empty block 
		      }*/

		      stopTime_3 = System.nanoTime();

		      // Compute the time, subtract the cost of running the loop
		      // from the cost of running the loop and doing the lookups.
		      // Average it over the number of runs.
		      double averageTime_3 = ((double)((midpointTime_3 - startTime_3) - (stopTime_3 - midpointTime_3)))
		          / (double)timesToLoop;
		      
		      System.out.println(" " + n  + "\t" + "                " + averageTime_1 + "\t" + "    " + averageTime_2 + 
		    		  "\t" + averageTime_3);
		}
	}

}
