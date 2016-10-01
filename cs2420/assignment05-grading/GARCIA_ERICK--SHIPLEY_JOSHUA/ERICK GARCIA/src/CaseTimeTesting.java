package assignment05;

import java.util.ArrayList;

/**
 * Testing best worst and average cases
 * 	@author Erick Daniel Garcia
 *	@author Joshua Shipley
 */


public class CaseTimeTesting {

	static ArrayList<ArrayList<Integer>> quicksortListSet;
	static ArrayList<ArrayList<Integer>> mergesortListSet;
	private static final int REP_COUNT = 10;
	private static int MAX_SIZE = 50000;
	
	public static void main(String[] args){
		quicksortListSet = new ArrayList<ArrayList<Integer>>();
		mergesortListSet = new ArrayList<ArrayList<Integer>>();
		
		testBestCase();
		testAverageCase();
		testWorstCase();
	}
	/**
	 * Testing best case of both QuickSort and mergeSort
	 */
	private static void testBestCase(){
		System.out.println("Best Case:");
		System.out.println("Quicksort:");
		
		long startTime, endTime;
		ArrayList<Long> timings;
		long sum;
		for (int size = 1024; size < MAX_SIZE; size*= 2) {
			ArrayList<Integer> ref = SortUtil.generateBestCase(size);
			for (int i = 0; i < REP_COUNT; i++) {
				quicksortListSet.add((ArrayList<Integer>) ref.clone());
			}
			timings = new ArrayList<Long>();
			for (ArrayList<Integer> list : quicksortListSet) {
				startTime = System.nanoTime();
				SortUtil.quicksort(list, null);
				endTime = System.nanoTime();

				timings.add(endTime - startTime);
			}
			sum = 0;
			for (Long time : timings) {
				sum += time;
			}
			System.out.println(size + "\t" + sum / REP_COUNT);
			quicksortListSet.clear();
			timings.clear();
		}
		
		System.out.println("Mergesort: ");
		
		for (int size = 1024; size < MAX_SIZE; size*= 2) {
			ArrayList<Integer> ref = SortUtil.generateBestCase(size);
			for (int i = 0; i < REP_COUNT; i++) {
				mergesortListSet.add((ArrayList<Integer>) ref.clone());
			}
			timings = new ArrayList<Long>();
			for (ArrayList<Integer> list : mergesortListSet) {
				startTime = System.nanoTime();
				SortUtil.mergesort(list, null);
				endTime = System.nanoTime();

				timings.add(endTime - startTime);
			}
			sum = 0;
			for (Long time : timings) {
				sum += time;
			}
			System.out.println(size + "\t" + sum / REP_COUNT);
			mergesortListSet.clear();
			timings.clear();
		}
}
	
	/**
	 * Testing Worst case of both QuickSort and mergeSort
	 */
	
	private static void testWorstCase(){
		System.out.println("Worst Case:");
		System.out.println("Quicksort:");
		
		long startTime, endTime;
		ArrayList<Long> timings;
		long sum;
		for (int size = 1024; size < MAX_SIZE; size*= 2) {
			ArrayList<Integer> ref = SortUtil.generateWorstCase(size);
			for (int i = 0; i < REP_COUNT; i++) {
				quicksortListSet.add((ArrayList<Integer>) ref.clone());
			}
			timings = new ArrayList<Long>();
			for (ArrayList<Integer> list : quicksortListSet) {
				startTime = System.nanoTime();
				SortUtil.quicksort(list, null);
				endTime = System.nanoTime();

				timings.add(endTime - startTime);
			}
			sum = 0;
			for (Long time : timings) {
				sum += time;
			}
			System.out.println(size + "\t" + sum / REP_COUNT);
			quicksortListSet.clear();
			timings.clear();
		}
		
		System.out.println("Mergesort: ");
		
		for (int size = 1024; size < MAX_SIZE; size*= 2) {
			ArrayList<Integer> ref = SortUtil.generateWorstCase(size);
			for (int i = 0; i < REP_COUNT; i++) {
				mergesortListSet.add((ArrayList<Integer>) ref.clone());
			}
			timings = new ArrayList<Long>();
			for (ArrayList<Integer> list : mergesortListSet) {
				startTime = System.nanoTime();
				SortUtil.mergesort(list, null);
				endTime = System.nanoTime();

				timings.add(endTime - startTime);
			}
			sum = 0;
			for (Long time : timings) {
				sum += time;
			}
			System.out.println(size + "\t" + sum / REP_COUNT);
			mergesortListSet.clear();
			timings.clear();
		}
}
	/**
	 * Testing average case of both QuickSort and mergeSort
	 */
	private static void testAverageCase(){
		System.out.println("Average Case:");
		System.out.println("Quicksort:");
		
		long startTime, endTime;
		ArrayList<Long> timings;
		long sum;
		for (int size = 1024; size < MAX_SIZE; size*= 2) {
			ArrayList<Integer> ref = SortUtil.generateAverageCase(size);
			for (int i = 0; i < REP_COUNT; i++) {
				quicksortListSet.add((ArrayList<Integer>) ref.clone());
			}
			timings = new ArrayList<Long>();
			for (ArrayList<Integer> list : quicksortListSet) {
				startTime = System.nanoTime();
				SortUtil.quicksort(list, null);
				endTime = System.nanoTime();

				timings.add(endTime - startTime);
			}
			sum = 0;
			for (Long time : timings) {
				sum += time;
			}
			System.out.println(size + "\t" + sum / REP_COUNT);
			quicksortListSet.clear();
			timings.clear();
		}
		
		System.out.println("Mergesort: ");
		
		for (int size = 1024; size < MAX_SIZE; size*= 2) {
			ArrayList<Integer> ref = SortUtil.generateAverageCase(size);
			for (int i = 0; i < REP_COUNT; i++) {
				mergesortListSet.add((ArrayList<Integer>) ref.clone());
			}
			timings = new ArrayList<Long>();
			for (ArrayList<Integer> list : mergesortListSet) {
				startTime = System.nanoTime();
				SortUtil.mergesort(list, null);
				endTime = System.nanoTime();

				timings.add(endTime - startTime);
			}
			sum = 0;
			for (Long time : timings) {
				sum += time;
			}
			System.out.println(size + "\t" + sum / REP_COUNT);
			mergesortListSet.clear();
			timings.clear();
		}
}
}
