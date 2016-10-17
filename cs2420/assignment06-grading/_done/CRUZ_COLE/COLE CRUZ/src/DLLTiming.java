//Cole Cruz
package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * A class to time methods of the DoublyLinkedList (DLL) class.
 * 
 * @author Cole Cruz
 *
 */
public class DLLTiming {

	private static int ITER_COUNT = 100;
	private static final String dataFileName = "data.tsv";
	private static final String dataFileName2 = "data2.tsv";
	private static final String graphFileName = "graph.png";

	/**
	 * To time DLL functions
	 */
	public static void main(String[] args) {
		// you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);

		try (FileWriter fw = new FileWriter(new File(dataFileName))) { // open
																		// up a
																		// file
																		// writer
																		// so we
																		// can
																		// write
																		// to
																		// file.
			for (int exp = 1; exp <= 15; exp++) { // This is used as the
													// exponent to calculate the
													// size of the set.
				Random rand = new Random();
				int size = (int) Math.pow(2, exp); // or ..

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
					for(int count = 1; count <= size; count++) {
						testDLL.addLast(count);
					}
					
					// TIME IT!
//					long start = System.nanoTime();
//					testDLL.addFirst(1);
//					long stop = System.nanoTime();
//					totalTime += stop - start;
//					long start = System.nanoTime();
//					testDLL.get(rand.nextInt(size));
//					long stop = System.nanoTime();
//					totalTime += stop - start;
					long start = System.nanoTime();
					testDLL.remove(rand.nextInt(size));
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
			for (int exp = 1; exp <= 15; exp++) { // This is used as the
				// exponent to calculate the
				// size of the set.
				Random rand = new Random();
				int size = (int) Math.pow(2, exp); // or ..

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					ArrayList<Integer> testAL = new ArrayList<Integer>();
					for(int count = 1; count <= size; count++) {
						testAL.add(count);
					}
					
					// TIME IT!
//					long start = System.nanoTime();
//					testAL.add(0, 1);
//					long stop = System.nanoTime();
//					totalTime += stop - start;
//					long start = System.nanoTime();
//					testAL.get(rand.nextInt(size));
//					long stop = System.nanoTime();
//					totalTime += stop - start;
					long start = System.nanoTime();
					testAL.remove(rand.nextInt(size));
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
		Charter charter = new Charter();
//		charter.createChart("Add First Timing Experiment", "DLL addFirst(item) vs ArrayList add(0, item)",
//				"Size (N)", "Time (μs)", "DLL Add First", dataFileName, "ArrayList Add", dataFileName2, graphFileName);
//		charter.createChart("Get Timing Experiment", "DLL get(i) vs ArrayList get(i)",
//				"Size (N)", "Time (ms)", "DLL Get", dataFileName, "ArrayList Get", dataFileName2, graphFileName);
		charter.createChart("Remove Timing Experiment", "DLL remove(i) vs ArrayList remove(i)",
				"Size (N)", "Time (μs)", "DLL Remove", dataFileName, "ArrayList Remove", dataFileName2, graphFileName);
	}

}
