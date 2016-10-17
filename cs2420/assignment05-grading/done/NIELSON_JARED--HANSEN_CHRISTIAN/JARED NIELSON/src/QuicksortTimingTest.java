package assignment05;

import java.util.ArrayList;
import java.util.Random;

/**
 * Timing class designed to compare the run time of quicksort using
 * various pivot selection methods.
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class QuicksortTimingTest {
	
	private static final int CAP_SIZE = 1048577;
	private static final int COUNT_TIMES = 10;
	private static final int MILLI = 1000000;
	
	public static void main(String args[]){
		
		for(int nanoCallCount = 0; nanoCallCount < 1000; nanoCallCount++){
			System.nanoTime();
		}
		
		StringComparator cmp = new StringComparator();
		
		ArrayList<String> medianOfThreePivotList = new ArrayList<String>();
		ArrayList<String> randomPivotList = new ArrayList<String>();
		ArrayList<String> middlePivotList = new ArrayList<String>();
		
		for(int iter = 1024; iter < CAP_SIZE; iter*=2){
			
			long medianPivotTime = 0;
			long randomPivotTime = 0;
			long middlePivotTime = 0;
			
			for(int count = 0; count < COUNT_TIMES; count++){
				
				long seed = 35985738394857L;
				seed += 10;
				
				Random rObject = new Random(seed);
				
				// Filling them the same
				for(int index = 0; index < iter; index++){
					medianOfThreePivotList.add("" + index);
					randomPivotList.add("" + index);
					middlePivotList.add("" + index);
				}
				
				for(int index = 0; index < iter; index++){
					
					int randomIndex = rObject.nextInt(iter);
					
					String tmp = medianOfThreePivotList.get(index);
					
					medianOfThreePivotList.set(index, medianOfThreePivotList.get(randomIndex));
					randomPivotList.set(index, randomPivotList.get(randomIndex));
					middlePivotList.set(index, middlePivotList.get(randomIndex));
					
					medianOfThreePivotList.set(randomIndex, tmp);
					randomPivotList.set(randomIndex, tmp);
					middlePivotList.set(randomIndex, tmp);
				}
				
				long start;
				long end;
				
				SortUtil.setPivotChoiceMethod(SortUtil.MEDIAN_OF_THREE);
				start = System.nanoTime();
				SortUtil.quicksort(medianOfThreePivotList, cmp);
				end = System.nanoTime();
				medianPivotTime += (end - start);
		
				SortUtil.setPivotChoiceMethod(SortUtil.RANDOM_PIVOT);
				start = System.nanoTime();
				SortUtil.quicksort(randomPivotList, cmp);
				end = System.nanoTime();
				randomPivotTime += (end - start);
				
				
				SortUtil.setPivotChoiceMethod(SortUtil.MIDDLE_ELEMENT);
				start = System.nanoTime();
				SortUtil.quicksort(middlePivotList, cmp);
				end = System.nanoTime();
				middlePivotTime += (end - start);
				
				medianOfThreePivotList.clear();
				randomPivotList.clear();
				middlePivotList.clear();
			}
			
			double medianAverage = ( (double) medianPivotTime / (COUNT_TIMES*MILLI) );
			double randomAverage = ( (double) randomPivotTime / (COUNT_TIMES*MILLI) );
			double middleAverage = ( (double) middlePivotTime / (COUNT_TIMES*MILLI) );
			
			System.out.println(""+ iter + "\t" + medianAverage + "\t" + randomAverage + "\t" + middleAverage);
		}
		
	}

}
