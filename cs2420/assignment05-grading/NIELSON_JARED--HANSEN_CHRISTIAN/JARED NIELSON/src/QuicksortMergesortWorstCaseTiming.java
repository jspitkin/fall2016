package assignment05;

import java.util.ArrayList;

/**
 * Timing class designed compare runtimes of quicksort and mergesort for
 * worst case scenarios
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class QuicksortMergesortWorstCaseTiming {
	private static int COUNT_TIMES = 10;
	private static int CAP_SIZE = 1048577;
	private static int MILLION = 1000000;
	
	public static void main(String[] args) {
		//Prime the JVM for calls to System.nanoTime()
		for(int nanoCount = 0; nanoCount < 1000; nanoCount++){
			System.nanoTime();
		}
		
		//Set up the sort util with deisred options
		SortUtil.setInsertionThreshold(10);
		SortUtil.setPivotChoiceMethod(SortUtil.MIDDLE_ELEMENT);
		
		//Declare working utilities
		IntegerComparator intCmp = new IntegerComparator();
		ArrayList<Integer> quicksortList;
		ArrayList<Integer> mergesortList;
		
		System.out.println("Size\tquicksortTime\tmergesortTime");
		for(int testSize = 1024; testSize < CAP_SIZE; testSize *=2){
			long quicksortTime = 0;
			long mergesortTime = 0;
			
			for(int iter = 0; iter < COUNT_TIMES; iter++){
				//set up identical lists
				quicksortList = SortUtil.generateWorstCase(testSize);
				mergesortList = SortUtil.generateWorstCase(testSize);
				
				long start;
				long end;
				
				start = System.nanoTime();
				SortUtil.quicksort(quicksortList, intCmp);
				end = System.nanoTime();
				quicksortTime += end - start;
				
				start = System.nanoTime();
				SortUtil.mergesort(mergesortList, intCmp);
				end = System.nanoTime();
				mergesortTime += end - start;
			}
			
			double quicksortAvg = quicksortTime / ((double) COUNT_TIMES * MILLION);
			double mergesortAvg = mergesortTime / ((double) COUNT_TIMES * MILLION);
			
			System.out.println("" + testSize + "\t" + quicksortAvg + "\t" + mergesortAvg);

			
			
		}

	}

}
