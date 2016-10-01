package assignment05;

import java.util.ArrayList;


/**
 * Basic timing test to perform experiments on mergesort and quicksort methods in SortUtil
 * class.  For Mergesort timing, threshold must be set manually before the test is run.  
 * For Quicksort timing, the pivot method must be chosen before test is run.
 * @author Jana Klopsch (u0854469) & Alex Henabray (u0795787), last updated: 09/27/2016
 * Course: CS 2420
 * Assignment 05
 * 
 */
public class SortUtilTimingTests {

	public static final int MILLION = 1_000_000;
	public static final int BILLION = 1_000_000_000;


	/**
	 * This method measures the average time required to sort an ArrayList
	 * using the merge sort method
	 * @param listSize
	 */
	public static void timeMergeSortAverageCase(int listSize) {

		//Use method to generate unsortedIntArrayList that makes sense for testing 
		//(i.e. use generateAverageCase to test average running time for average case arrays)
		ArrayList<Integer> unsortedIntArrayList = SortUtil.generateAverageCase(listSize);
		ArrayList<Integer> copyArrayList = new ArrayList<Integer>();

		//Set the threshold value
		SortUtil.setThreshold(unsortedIntArrayList.size()/2);
		
		long startTime = 0;
		long endTime = 0;
		int iterations = 500;
		long averageTime = 0;

		for(int data = 0; data < iterations; data++) {

			copyArrayList.clear();
			for(int index = 0; index < unsortedIntArrayList.size(); index++) {

				copyArrayList.add(unsortedIntArrayList.get(index));

			}
			
			startTime = System.nanoTime();
			SortUtil.mergesort(copyArrayList, new IntegerComparatorL2G());
			endTime = System.nanoTime() - startTime;
			averageTime += endTime;
		}

		averageTime /= iterations;

		double time = averageTime / (double) BILLION;

		System.out.println("Mergesort time for list size " + listSize + " and Threshold: size/2 is " + time + " seconds");
	}

	//Set pivot choosing method in SortUtil class
	/**
	 * This method measures the average time required to sort an ArrayList
	 * using the quick sort algorithm.
	 * @param listSize
	 */
	public static void timeQuickSortAverage(int listSize) {

		//Use method to generate unsortedIntArrayList that makes sense for testing 
		//(i.e. use generateAverageCase to test average running time for average case arrays)
		ArrayList<Integer> unsortedIntArrayList = SortUtil.generateAverageCase(listSize);
		ArrayList<Integer> copyArrayList = new ArrayList<Integer>();

		//For integers in setWhichPivot,
		// 1: middlePivot
		// 2: medianPivot
		// (any other integer): randomPivot
		SortUtil.setWhichPivot(2);
		
		long startTime = 0;
		long endTime = 0;
		int iterations = 500;
		long averageTime = 0;

		for(int data = 0; data < iterations; data++) {

			copyArrayList.clear();
			for(int index = 0; index < unsortedIntArrayList.size(); index++) {

				copyArrayList.add(unsortedIntArrayList.get(index));

			}

			startTime = System.nanoTime();
			SortUtil.quicksort(copyArrayList, new IntegerComparatorL2G());
			endTime = System.nanoTime() - startTime;
			averageTime += endTime;
		}

		averageTime /= iterations;

		double time = averageTime / (double) BILLION;

		System.out.println("Quicksort time for list size " + listSize + " and for medianPivot is " + time + " seconds");

	}


	public static void main(String[] args) {

		for(int sizeOfList = 500; sizeOfList <= 8_000; sizeOfList *= 2){
			timeMergeSortAverageCase(sizeOfList);
			//timeQuickSortAverage(sizeOfList);
		}
	}


}
