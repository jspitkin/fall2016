package assignment05;

import java.util.ArrayList;
import java.util.Random;

/**
 * Timing class designed to time different insertion sort thresholds for merge sort.
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class MergesortThresholdTimingTest {
	
	private static final int CAP_SIZE = 1048577;
	private static final int COUNT_TIMES = 10;
	private static final int MILLI = 1000000;
	
	public static void main(String args[]){
		
		StringComparator cmp = new StringComparator();
		
		ArrayList<String> data0 = new ArrayList<String>();
		ArrayList<String> data10 = new ArrayList<String>();
		ArrayList<String> data50 = new ArrayList<String>();
		ArrayList<String> data100 = new ArrayList<String>();
		ArrayList<String> data150 = new ArrayList<String>();
		
		for(int iter = 1024; iter < CAP_SIZE; iter*=2){
		
			long Threshold0Time = 0;
			long Threshold10Time = 0;
			long Threshold50Time = 0;
			long Threshold100Time = 0;
			long Threshold150Time = 0;
			
			
			for(int count = 0; count < COUNT_TIMES; count++){
			
				long seed = 35985738394857L;
				seed += 10;
				
				Random rObject = new Random(seed);
				
				// Filling them the same
				for(int index = 0; index < iter; index++){
					data0.add("" + index);
					data10.add("" + index);
					data50.add("" + index);
					data100.add("" + index);
					data150.add("" + index);
				}
				
				for(int index = 0; index < iter; index++){
					
					int randomIndex = rObject.nextInt(iter);
					
					String tmp = data0.get(index);
					
					data0.set(index, data0.get(randomIndex));
					data10.set(index, data10.get(randomIndex));
					data50.set(index, data50.get(randomIndex));
					data100.set(index, data100.get(randomIndex));
					data150.set(index, data150.get(randomIndex));
					
					data0.set(randomIndex, tmp);
					data10.set(randomIndex, tmp);
					data50.set(randomIndex, tmp);
					data100.set(randomIndex, tmp);
					data150.set(randomIndex, tmp);
				}
				
				long start;
				long end;
				
				for(int nanoCallCount = 0; nanoCallCount < 1000; nanoCallCount++){
					System.nanoTime();
				}
				SortUtil.setInsertionThreshold(0);
				start = System.nanoTime();
				SortUtil.quicksort(data0, cmp);
				end = System.nanoTime();
				Threshold0Time += (end - start);
		
				SortUtil.setInsertionThreshold(10);
				start = System.nanoTime();
				SortUtil.quicksort(data10, cmp);
				end = System.nanoTime();
				Threshold10Time += (end - start);
				
				
				SortUtil.setInsertionThreshold(50);
				start = System.nanoTime();
				SortUtil.quicksort(data50, cmp);
				end = System.nanoTime();
				Threshold50Time += (end - start);
				
				SortUtil.setInsertionThreshold(100);
				start = System.nanoTime();
				SortUtil.quicksort(data100, cmp);
				end = System.nanoTime();
				Threshold100Time += (end - start);
				
				SortUtil.setInsertionThreshold(150);
				start = System.nanoTime();
				SortUtil.quicksort(data150, cmp);
				end = System.nanoTime();
				Threshold150Time += (end - start);
				
				data0.clear();
				data10.clear();
				data50.clear();
				data100.clear();
				data150.clear();
			}
			
			double data0Average = ( (double) Threshold0Time / (COUNT_TIMES*MILLI) );
			double data10Average = ( (double) Threshold10Time / (COUNT_TIMES*MILLI) );
			double data50Average = ( (double) Threshold50Time / (COUNT_TIMES*MILLI) );
			double data100Average = ( (double) Threshold100Time / (COUNT_TIMES*MILLI) );
			double data150Average = ( (double) Threshold150Time / (COUNT_TIMES*MILLI) );
			
			System.out.println(""+ iter + "\t" + data0Average + "\t" + data10Average + "\t" + data50Average 
					+ "\t" + data100Average + "\t" + data150Average);
		}
	}
}
