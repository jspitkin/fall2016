package assignment08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Test class for measuring complexity of methods BST class.
 * 
 * @author Colton Haacke (u0935270) and Ashley Grevelink (u0749357)
 */
public class TimingTest {
	private static final int ITER_COUNT = 500;

	public static void main(String[] args) {
		addSortedTiming();
		addRandomOrderTiming();
		addToBSTTiming();
		addToTreeSetTiming();
		containsBSTTiming();
		containsTreeSetTiming();
	}

	private static void addSortedTiming() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("addSorted_experiment.tsv"))) { // open
																						// file.
			for (int size = 1000; size <= 7000; size += 1000) {
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				// Subtract the time of the for-loop
				long totalForLoopTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					//adding to the BST in sorted order
					BinarySearchTree<Integer> myBST = new BinarySearchTree<Integer>();
					for (int i = 1; i < size; i++) {
						myBST.add((Integer) i);
					}
					
					//timing the for-loop
					long forLoopStart = System.nanoTime();
					for (int i = 1; i < size; i++) {
					}
					long forLoopStop = System.nanoTime();
					totalForLoopTime += forLoopStop - forLoopStart;

					//timing contains
					long start = System.nanoTime();
					for (int i = 1; i < size; i++) {
						myBST.contains(i);
					}
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				//subtracting the for-loop time from total time first
				double averageTime = (totalTime - totalForLoopTime) / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addRandomOrderTiming() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("addRandomOrder_experiment.tsv"))) {
			for (int size = 1000; size <= 7000; size += 1000) {
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				long totalForLoopTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					BinarySearchTree<Integer> myBST = new BinarySearchTree<Integer>();

					//creating an ArrayList and using Collections.shuffle to randomize
					ArrayList<Integer> myAL = new ArrayList<Integer>();
					for (int i = 1; i < size; i++) {
						myAL.add((Integer) i);
					}
					Collections.shuffle(myAL);
					
					//adding the shuffled list of size N to the BST
					myBST.addAll(myAL);

					// timing the for-loop
					long forLoopStart = System.nanoTime();
					for (int i = 1; i < size; i++) {
					}
					long forLoopStop = System.nanoTime();
					totalForLoopTime += forLoopStop - forLoopStart;

					//timing contains
					long start = System.nanoTime();
					for (int i = 1; i < size; i++) {
						myBST.contains(i);
					}
					long stop = System.nanoTime();
					totalTime += stop - start;

				}
				//subtracting the for-loop time from total time first
				double averageTime = (totalTime - totalForLoopTime) / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addToBSTTiming() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("addToBST_experiment.tsv"))) {
			for (int size = 1000; size <= 7000; size += 1000) {
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					BinarySearchTree<Integer> myBST = new BinarySearchTree<Integer>();

					//creating an ArrayList and using Collections.shuffle to randomize
					ArrayList<Integer> myAL = new ArrayList<Integer>();
					for (int i = 1; i < size; i++) {
						myAL.add((Integer) i);
					}
					Collections.shuffle(myAL);

					//adding the shuffled list of size N to the BST and timing
					long start = System.nanoTime();
					myBST.addAll(myAL);
					long stop = System.nanoTime();
					totalTime += stop - start;

				}
				double averageTime = (totalTime) / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void containsBSTTiming() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("containsBST_experiment.tsv"))) {
			for (int size = 1000; size <= 7000; size += 1000) {
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				long totalForLoopTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					BinarySearchTree<Integer> myBST = new BinarySearchTree<Integer>();

					//creating an ArrayList and using Collections.shuffle to randomize
					ArrayList<Integer> myAL = new ArrayList<Integer>();
					for (int i = 1; i < size; i++) {
						myAL.add((Integer) i);
					}
					Collections.shuffle(myAL);
					
					//adding the shuffled list of size N to the BST
					myBST.addAll(myAL);

					//timing the for-loop
					long forLoopStart = System.nanoTime();
					for (int i = 1; i < size; i++) {
					}
					long forLoopStop = System.nanoTime();
					totalForLoopTime += forLoopStop - forLoopStart;

					//timing contains
					long start = System.nanoTime();
					for (int i = 1; i < size; i++) {
						myBST.contains(i);
					}
					long stop = System.nanoTime();
					totalTime += stop - start;

				}
				//subtracting the for-loop time from total time first
				double averageTime = (totalTime - totalForLoopTime) / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void addToTreeSetTiming() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("addToTreeSet_experiment.tsv"))) {
			for (int size = 1000; size <= 7000; size += 1000) {
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					TreeSet<Integer> myTS = new TreeSet<Integer>();
					
					//creating an ArrayList and using Collections.shuffle to randomize
					ArrayList<Integer> myAL = new ArrayList<Integer>();
					for (int i = 1; i < size; i++) {
						myAL.add((Integer) i);
					}
					Collections.shuffle(myAL);

					//adding the shuffled list of size N to the TS and timing
					long start = System.nanoTime();
					myTS.addAll(myAL);
					long stop = System.nanoTime();
					totalTime += stop - start;

				}
				double averageTime = (totalTime) / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void containsTreeSetTiming() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		try (FileWriter fw = new FileWriter(new File("containsTreeSet_experiment.tsv"))) {
			for (int size = 1000; size <= 7000; size += 1000) {
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				long totalForLoopTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					TreeSet<Integer> myTS = new TreeSet<Integer>();

					//creating an ArrayList and using Collections.shuffle to randomize
					ArrayList<Integer> myAL = new ArrayList<Integer>();
					for (int i = 1; i < size; i++) {
						myAL.add((Integer) i);
					}
					Collections.shuffle(myAL);
					
					//adding the shuffled list of size N to the TS
					myTS.addAll(myAL);

					//timing the for-loop
					long forLoopStart = System.nanoTime();
					for (int i = 1; i < size; i++) {
					}
					long forLoopStop = System.nanoTime();
					totalForLoopTime += forLoopStop - forLoopStart;

					//timing contains
					long start = System.nanoTime();
					for (int i = 1; i < size; i++) {
						myTS.contains(i);
					}
					long stop = System.nanoTime();
					totalTime += stop - start;

				}
				//subtracting the for-loop time from total time first
				double averageTime = (totalTime - totalForLoopTime) / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
