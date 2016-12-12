package assignment11;

import java.util.Random;

/**
 * Tests the run time of various priority queue methods
 * @author Andrew Keaton Bruce
 * u1006889
 * 11/15/2016
 *
 */
public class TimeTestPriority {
	public static final int BILLION = 1_000_000_000;
	public static final int MILLION = 1_000_000;
	public static final int THOUSAND = 1_000; //Microseconds
	public static final int ITER_COUNT = 10000;
	
	public static void main(String args[]) {
		PriorityQueue<Integer> pri = new PriorityQueue<Integer>();
		Random rng = new Random();
		
		for (int exp = 10; exp <= 23; exp++) {
			pri = new PriorityQueue<Integer>();
			
			int size = (int)Math.pow(2, exp);
			
			for (int current = 0; current < size; current++) {
				int rand = rng.nextInt(size);
				
				pri.add(rand);
			}
			
			long totalTimeAdd = 0;
			long totalTimeRemove = 0;
			for (int iter = 0; iter < ITER_COUNT; iter++) {
				int rand = rng.nextInt(size);
				
				long startAdd = System.nanoTime();
				pri.add(rand);
				long stopAdd = System.nanoTime();

				long startRemove = System.nanoTime();
				pri.deleteMin();
				long stopRemove = System.nanoTime();
				
				totalTimeAdd += (stopAdd - startAdd);	
				totalTimeRemove += (stopRemove - startRemove);
			}
			
			double avgTimeAdd = totalTimeAdd / (double) ITER_COUNT;
			double avgTimeRemove = totalTimeRemove / (double) ITER_COUNT;

			avgTimeAdd = Math.floor((avgTimeAdd) * 1000) / 1000;
			avgTimeRemove = Math.floor((avgTimeRemove) * 1000) / 1000;

			System.out.println(size + "\t" + avgTimeAdd + "\t" + avgTimeRemove);
		}
	}
}
