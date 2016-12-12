package assignment11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import assignment10.Charter;

public class PriorityQueueTiming {
	private static final int ITER_COUNT = 10;
	static int maxLimit = 524288;
	public static PriorityQueue<Integer> list = new PriorityQueue<>();

	public static void main(String[] args) {
		timePriorityQueueOpperations();
	}

	private static void timePriorityQueueOpperations() {
		try (FileWriter fw = new FileWriter(new File("priorityQueueOpperations.tsv"))) { // open
			// up
			// a
			// file
			// writer
			// so
			// we
			// can
			// write
			// to
			// file.
			// Set sizes of N
			Random random = new Random();

			for (int n = 2 * 2; n <= 524288; n *= 2) {
				long startTime, midTime, endTime;

				// Create random strings of the length of N

				
				for (int i = 0; i <= maxLimit; i++) {
					list.add(random.nextInt(n));
				}
				// Stabilize thread
				startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1_000_000_000)
					;// Stabilizes for 1 second

				// Time the areAnagram method
				startTime = System.nanoTime();
				for (int i = 0; i < ITER_COUNT; i++) {
					// AnagramUtil.areAnagrams(firstString, secondString);
					list.findMin();
				}
				// Time empty loops for midTime and endTime that will computed
				// in the averageTime

				midTime = System.nanoTime();
				for (int i = 0; i < ITER_COUNT; i++) {
				}

				endTime = System.nanoTime();
				// Compute averageTime first with midTime - startTime, then the
				// endTime - midTime, divide by the iteration count
				double averageTime = ((midTime - startTime) - (endTime - midTime)) / ITER_COUNT;
				System.out.println(n + "\t" + averageTime);
				fw.write(n + "\t" + averageTime);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter = new Charter();
		charter.createChart("priorityQueueOpperations.tsv", "chart.png");
	}
}
