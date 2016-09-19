package assignment03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Ching-Yuan Chang u0914005, Min Kim u1054673
 */
public class ContainsTimingExperiment {

	private static final int ITER_COUNT = 1000;

	public static void main(String[] args) {
		// you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000)
			;

		try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) { // open
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
			for (int exp = 10; exp <= 20; exp++) { // This is used as the
													// exponent to calculate the
													// size of the set.
				int size = (int) Math.pow(2, exp); // or ..
				size = 1 << exp; // the two statements are equivalent, look into
									// bit-shifting if you're interested what is
									// going on here.
				// int size = 1000000; 

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					BinarySearchSet<Integer> set = new BinarySearchSet<Integer>();
					for (int i = 0; i < size; i++) {
						set.add(i);
					}
					// set.remove(56);

					// TIME IT!
					for (int i = 0; i < 30; i++) {
						int rand = new Random().nextInt(size); // This gets me a random number
						long start = System.nanoTime();
						// set.add(56);

						set.contains(rand);
						long stop = System.nanoTime();
						totalTime += stop - start;
						// set.remove(56);
						// double averageTime = totalTime / (double) ITER_COUNT;
						// System.out.println(size + "\t" + averageTime); //
						// print to
						// console
					}

					set.clear();

				}
				double averageTime = totalTime / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
