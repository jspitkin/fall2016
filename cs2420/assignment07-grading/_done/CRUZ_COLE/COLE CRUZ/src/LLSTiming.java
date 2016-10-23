//Cole Cruz
package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * A class to time methods of the LinkedListStack (LLS) class.
 * 
 * @author Cole Cruz
 *
 */
public class LLSTiming {

	private static int ITER_COUNT = 100;
	private static final String dataFileName = "data.tsv";
	private static final String dataFileName2 = "data2.tsv";
	private static final String dataFileName3 = "data3.tsv";
	private static final String graphFileName = "graph.png";

	/**
	 * To time LLS methods.
	 */
	public static void main(String[] args) {
		// you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File(dataFileName))) { // open
																		// up a
																		// file
																		// writer
																		// so we
																		// can
																		// write
																		// to
																		// file.
			for (int exp = 1; exp <= 20; exp++) { // This is used as the
													// exponent to calculate the
													// size of the set.
				int size = (int) Math.pow(2, exp); // or ..

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					LinkedListStack<Integer> testLLS = new LinkedListStack<Integer>();
					for (int count = 1; count <= size; count++) {
						testLLS.push(count);
					}

					// TIME IT!
					long start = System.nanoTime();
					testLLS.push(1);
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = (totalTime / (double) ITER_COUNT) / 1_000;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished first times");
		try (FileWriter fw = new FileWriter(new File(dataFileName2))) { // open
																		// up a
																		// file
																		// writer
																		// so we
																		// can
																		// write
																		// to
																		// file.
			for (int exp = 1; exp <= 20; exp++) { // This is used as the
				// exponent to calculate the
				// size of the set.
				int size = (int) Math.pow(2, exp); // or ..

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					LinkedListStack<Integer> testLLS = new LinkedListStack<Integer>();
					for (int count = 1; count <= size; count++) {
						testLLS.push(count);
					}

					// TIME IT!
					long start = System.nanoTime();
					testLLS.pop();
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = (totalTime / (double) ITER_COUNT) / 1_000;
				System.out.println(size + "\t" + averageTime); // print to
				// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished second times");
		try (FileWriter fw = new FileWriter(new File(dataFileName3))) { // open
			// up a
			// file
			// writer
			// so we
			// can
			// write
			// to
			// file.
			for (int exp = 1; exp <= 20; exp++) { // This is used as the
				// exponent to calculate the
				// size of the set.
				int size = (int) Math.pow(2, exp); // or ..

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					LinkedListStack<Integer> testLLS = new LinkedListStack<Integer>();
					for (int count = 1; count <= size; count++) {
						testLLS.push(count);
					}

					// TIME IT!
					long start = System.nanoTime();
					testLLS.peek();
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = (totalTime / (double) ITER_COUNT) / 1_000;
				System.out.println(size + "\t" + averageTime); // print to
				// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Finished third times");
		Charter charter = new Charter();
		charter.createChart("LinkedListStack Methods Timing Experiment", "LinkedListStack Method Runtimes", "Size (N)",
				"Time (μs)", "Push", dataFileName, "Pop", dataFileName2, "Peek", dataFileName3, graphFileName);
	}

}
