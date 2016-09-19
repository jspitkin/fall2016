/**
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
package assignment03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
/**
 * Timing test class, template pulled from lab
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
public class Timing {

	private static final int ITER_COUNT = 100;

	public static void main(String[] args) {

		// "spin up" in order to help remove start time jiter
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		// open up a file writer so we can write to file.

		//Change the file name to match experiment used
		try (FileWriter fw = new FileWriter(new File("add_experiment.txt"))) {
			Random random = new Random();
			/**
			 * Commented out so other timing test can run
			 */
//			for (int exponent = 10; exponent <= 19; exponent++) { // This is used as the exponent to calculate the size of the set.
//
//				int size = (int) Math.pow(2, exponent);
//
//				// Do the experiment multiple times, and average out the results
//				long totalTime = 0;
//
//				for (int iter = 0; iter < ITER_COUNT; iter++) {
//
//					// SET UP!
//					BinarySearchSet<Integer> set = new BinarySearchSet<>();
//					for (int i = 0; i < size; i++) {
//						set.add(i);
//					}
//					
//					
//					int findElement = random.nextInt(size); // This gets me a
//															// random int
//															// between 0 and
//															// size;
//
//					// TIME IT!
//					long start = System.nanoTime();
//					set.contains(findElement);
//					long stop = System.nanoTime();
//
//
//					totalTime += stop - start;
//
//				}
//
//				double averageTime = totalTime / (double) ITER_COUNT;
//				fw.write(size + "\t" + averageTime + "\n"); // write to file.
//
//			}

			 
			for (int exponent = 10; exponent <= 19; exponent++) { // This is used as the exponent to calculate the ssize of tahe set.

				int size = (int) Math.pow(2, exponent);

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {

					// SET UP!
					BinarySearchSet<Integer> set = new BinarySearchSet<>();
					for (int i = 0; i < size; i++) {
						set.add(i);
					}
					
					
					int findElement = random.nextInt(size); // This gets me a
															// random int
															// between 0 and
															// size;

					// TIME IT!
					long start = System.nanoTime();
					set.add(findElement);
					long stop = System.nanoTime();

					set.remove(findElement);

					totalTime += stop - start;

				}

				double averageTime = totalTime / (double) ITER_COUNT;
				fw.write(size + "\t" + averageTime + "\n"); // write to file.

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Done");

	}
}
