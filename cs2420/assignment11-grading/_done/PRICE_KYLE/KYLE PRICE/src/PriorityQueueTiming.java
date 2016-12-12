package assignment11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author Kyle Price 
 * 11/15/2016
 * 
 * PriorityQueue - is the timing test for the class PriorityQueue.
 *
 */
public class PriorityQueueTiming {

	private static final int ITER_COUNT = 100;

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("binary_heap.tsv"))) { 
			Random random = new Random();
			for (int exp = 10; exp <= 17; exp++) { 
				int size = (int) Math.pow(2, exp); 

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				PriorityQueue<Integer> q;
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					q = new PriorityQueue<Integer>();
					for (int i = 0; i < size; i++) {
						q.add(random.nextInt(size));
					}
					// TIME IT!
					for (int i = 0; i < size; i++) {
						long start = System.nanoTime();
						q.deleteMin();
						long stop = System.nanoTime();
						totalTime += stop - start;
					}
				}
				double averageTime = totalTime / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime);
				fw.write(size + "\t" + averageTime + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter = new Charter();
		charter.createChart("binary_heap.tsv", "chart.png");
	}
}
