package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Refined timing test class, using less manual updates to SortUtil class,
 * and timing methods within TimingTests class.  Results are also written
 * to txt file.
 * @author Alex Henabray (u0795787)
 * Course: CS 2420
 * Assignment05
 *
 */
public class TimingTests {

	public static final int MILLION = 1_000_000;
	public static final int BILLION = 1_000_000_000;


	/**
	 * This method contains code necessary to run the
	 * threshold experiment. The ArrayLists that are sorted 
	 * generated using the generate average case method.
	 * 
	 * @param size
	 * @param threshold
	 */
	public static void mergeSortThresholdExp() {

		long startTime = 0;
		long endTime = 0;
		long averageTime = 0;
		int iterations;
		double returnTime;

		// Run experiment with 5 different threshold values
		int[] thresholdArray = new int[] {0, 50, 100,  150, 200, 250};
		

		// This for loop runs the timing experiment for different sized 
		// ArrayLists
		for(int size = 100; size <= 51_200; size *= 2 ) {

			System.out.println("Size: " + size + "\n");

			ArrayList<Integer> originalArrayList = SortUtil.generateAverageCase(size);
			ArrayList<Integer> tmpArrayList = new ArrayList<Integer>();

			// If ArrayList is small, calculate the average time using
			// 12_000 iterations of mergeSort. Else, use 1000 iterations
			if( size <= 1000) {
				iterations = 12_000;
			}
			else {
				iterations = 1000;
			}

			// Run the experiment for five different threshold values
			for(int threshold : thresholdArray) {
				SortUtil.setThreshold(threshold);

				// Copy elements from originalArrayList to tmpArrayList
				for(int index = 0; index < size; index++) {
					tmpArrayList.add(originalArrayList.get(index));
				}

				for(int iter = 1; iter <= iterations; iter++) {
					startTime = System.nanoTime();
					SortUtil.mergesort(tmpArrayList, new IntegerComparatorL2G());
					endTime = System.nanoTime() - startTime;

					averageTime += endTime;
				}

				// Calculate average time for ArrayList of given size & threshold
				averageTime /= iterations;
				returnTime = averageTime / (double) BILLION;

				System.out.println(threshold + "\t" + returnTime);
				if(threshold == thresholdArray[5])
					System.out.println("\n");
			}
		}
	}

	/**
	 * This method contains the code necessary to run the
	 * quick sort pivot experiment.
	 */
	public static void quickSortPivotExperiment() {

		long startTime = 0;
		long endTime = 0;
		long averageTime = 0;
		int iterations;
		double returnTime;

		try(FileWriter fw = new FileWriter(new File("pivotExperiment.txt"))) {

			for(int size = 100; size <= 51_200; size *= 2 ) {
				System.out.println("Size: " + size + "\n");

				fw.write(size);
				fw.write(System.lineSeparator());

				ArrayList<Integer> originalArrayList = SortUtil.generateAverageCase(size);
				ArrayList<Integer> tmpArrayList = new ArrayList<Integer>();

				// If ArrayList is small, calculate the average time using
				// 12_000 iterations of mergeSort. Else, use 1000 iterations
				if( size <= 1000) {
					iterations = 12_000;
				}
				else {
					iterations = 1000;
				}

				for(int value = 1; value <= 3; value++) {
					SortUtil.setWhichPivot(value);

					if(value == 1) {
						System.out.println("middle pivot" + "\n");
					}
					else if(value == 2) {
						System.out.println("median pivot" + "\n");

					}
					else {
						System.out.println("random pivot" + "\n");
					}

					// Copy elements from originalArrayList to tmpArrayList
					for(int index = 0; index < size; index++) {
						tmpArrayList.add(originalArrayList.get(index));
					}

					for(int iter = 1; iter <= iterations; iter++) {
						startTime = System.nanoTime();
						SortUtil.quicksort(tmpArrayList, new IntegerComparatorL2G());
						endTime = System.nanoTime() - startTime;

						averageTime += endTime;
					}

					// Calculate average time for ArrayList of given size & threshold
					averageTime /= iterations;
					returnTime = averageTime / (double) BILLION;

					System.out.println(size + "\t" + returnTime);
					fw.write(size + "\t" + returnTime);
					fw.write(System.lineSeparator());
				}

			}

			fw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public static void mergeVsQuick() {

		long startTime = 0;
		long endTime = 0;
		long averageTime = 0;
		int iterations;
		double returnTime;

		for(int size = 100; size <= 51_200; size *= 2 ) {
			System.out.println("Size: " + size + "\n");

			ArrayList<Integer> originalArrayList = SortUtil.generateAverageCase(size);
			ArrayList<Integer> tmpArrayList = new ArrayList<Integer>();

			// If ArrayList is small, calculate the average time using
			// 12_000 iterations of mergeSort. Else, use 1000 iterations
			if( size <= 1000) {
				iterations = 12_000;
			}
			else {
				iterations = 1000;
			}

			for(int value = 1; value <= 3; value++) {
				SortUtil.setWhichPivot(value);

				if(value == 1) {
					System.out.println("middle pivot" + "\n");
				}
				else if(value == 2) {
					System.out.println("median pivot" + "\n");

				}
				else {
					System.out.println("random pivot" + "\n");
				}

				// Copy elements from originalArrayList to tmpArrayList
				for(int index = 0; index < size; index++) {
					tmpArrayList.add(originalArrayList.get(index));
				}

				for(int iter = 1; iter <= iterations; iter++) {
					startTime = System.nanoTime();
					SortUtil.quicksort(tmpArrayList, new IntegerComparatorL2G());
					endTime = System.nanoTime() - startTime;

					averageTime += endTime;
				}

				// Calculate average time for ArrayList of given size & threshold
				averageTime /= iterations;
				returnTime = averageTime / (double) BILLION;

				System.out.println(size + "\t" + returnTime);

			}

		}
	}


	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// tests for threshold experiment
		mergeSortThresholdExp();

		// tests for pivot experiment


		// tests for merge vs. quick

	}

}
