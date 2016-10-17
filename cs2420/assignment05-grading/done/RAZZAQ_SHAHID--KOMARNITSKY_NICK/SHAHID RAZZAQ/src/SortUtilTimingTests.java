package assignment05;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Timing Test Class for SortUtil mergeSort and QuickSort methods
 * @author feede
 *
 */
public class SortUtilTimingTests {

	public static final int ITERATION_COUNT = 500;
	//private static SortUtil sortAlgorithms;
	private static ArrayList<Integer> permutedList;

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		//Generate a large permtuted list 
	//	sortAlgorithms = new SortUtil();
		permutedList = new ArrayList<Integer>();
		Scanner userInput = new Scanner(System.in);
		System.out.println("Make the Following Selection:\n1.quickSortPiviotTest\n2.MergeVsQuickSort\n3.MergeortThresholdValue");
		int selection = userInput.nextInt();
		if(selection==1){
			System.out.println("Size   MedianOfThree    Random    Middle");
			testQuickSortPiviots(permutedList);
			
		}else if(selection==2){
			System.out.println("Size   MergeSort    QuickSort ");
			testMergeSortVSQuickSort(permutedList);
		}else if(selection==3){
			System.out.println("Size   MergeSort    Threshold ");
			testMergeSortThresholdValues(permutedList);
		}
		else{
			System.exit(0);
		}
		

	}
	
	
	//Methods for timing quickSort
	/**
	 * 
	 */
	public static void testQuickSortPiviots(ArrayList<Integer> inputList){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		SortUtil sortAlgorithms = new SortUtil();
		try (FileWriter containsExperimentFile = new FileWriter(new File("quickSortPiviotTest.txt"))) {
			int sizeOfList = 1000;
			for (int i = 0; i <= 10; i++) {
				//int size = (int) Math.pow(2, exp);
				
				inputList = sortAlgorithms.generateAverageCase(sizeOfList);

				long randomPiviotTime = 0;
				long medianOfThreePiviotTime=0;
				long middlePiviotTime =0;

				//test random piviot
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {
					ArrayList<Integer> testList = new ArrayList<Integer>();
					
					//Generate AverageaCase List:
					testList = (ArrayList<Integer>) inputList.clone();
					//Select Random Piviot point, and put it on end:
					sortAlgorithms.swap(testList,sortAlgorithms.randomPiviotPoint(testList) , testList.size()-1);
					long start = System.nanoTime(); // start timer
					sortAlgorithms.quicksort(testList, new CustomComparator());
					long stop = System.nanoTime(); // end timer
					randomPiviotTime += stop - start;
				}
				double averageRandomPiviotTime = randomPiviotTime / (double) 100;
				
				//test median of three piviot
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {
					ArrayList<Integer> testList = new ArrayList<Integer>();
					//Generate AverageaCase List:
					testList = (ArrayList<Integer>) inputList.clone();
					//Select Random Piviot point, and put it on end:
					sortAlgorithms.swap(testList,sortAlgorithms.medianOfThreePiviot(testList, 0, testList.size()-1, new CustomComparator()) , testList.size()-1);
					long start = System.nanoTime(); // start timer
					sortAlgorithms.quicksort(testList, new CustomComparator());
					long stop = System.nanoTime(); // end timer
					medianOfThreePiviotTime += stop - start;
				}
				double averageMedianOfThreePiviotTime = medianOfThreePiviotTime / (double) 100;
				
				//Test middle piviot value
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {
					ArrayList<Integer> testList = new ArrayList<Integer>();
					//Generate AverageaCase List:
					testList = (ArrayList<Integer>) inputList.clone();
					//Select Random Piviot point, and put it on end:
					sortAlgorithms.swap(testList,sortAlgorithms.middlePiviotPoint(testList) , testList.size()-1);
					long start = System.nanoTime(); // start timer
					sortAlgorithms.quicksort(testList, new CustomComparator());
					long stop = System.nanoTime(); // end timer
					middlePiviotTime += stop - start;
				}
			
				double averageMiddlePiviotTime = middlePiviotTime / (double) 100;
				
				System.out.println(sizeOfList+ "\t"+ averageMedianOfThreePiviotTime + "\t" + averageRandomPiviotTime +"\t" + averageMiddlePiviotTime);
				containsExperimentFile.write(sizeOfList+ "\t"+ averageMedianOfThreePiviotTime + "\t" + averageRandomPiviotTime +"\t" + averageMiddlePiviotTime+ "\n");
				sizeOfList = sizeOfList + sizeOfList;
			}
		} catch (IOException e) {
			System.err.println("Cannot Write To File!");
		}
		//Charter charter = new Charter(); charter.createChart("quickSortPiviotTest.txt", "quickSortPiviotTest.png");
		
	}	
	
	/**
	 * 
	 * @param inputList
	 */
	public static void testMergeSortVSQuickSort(ArrayList<Integer> inputList){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		SortUtil sortAlgorithms = new SortUtil();
		try (FileWriter containsExperimentFile = new FileWriter(new File("MergeSortVSQuickSort.txt"))) {
			int sizeOfList = 1000;
			for (int i = 0; i < 10; i++) {
				//int size = (int) Math.pow(2, exp);
				
				//Change this to best/worst/average as needed
				inputList = sortAlgorithms.generateAverageCase(sizeOfList);
				long randomPiviotTime = 0;
				long mergeSortTime=0;
			
				//test merge sort with best threshold value 
		
				
				//test random piviot with quicksort 
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {
					ArrayList<Integer> testList = new ArrayList<Integer>();
					
					//Generate AverageaCase List:
					testList = (ArrayList<Integer>) inputList.clone();
					//Select Random Piviot point, and put it on end:
					sortAlgorithms.swap(testList,sortAlgorithms.randomPiviotPoint(testList) , testList.size()-1);
					long start = System.nanoTime(); // start timer
					sortAlgorithms.quicksort(testList, new CustomComparator());
					long stop = System.nanoTime(); // end timer
					randomPiviotTime += stop - start;
				}
	
				double averageQuickSortTime = randomPiviotTime / (double) 100;
				
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {
					ArrayList<Integer> testList = new ArrayList<Integer>();
					
					//Generate AverageaCase List:
					testList = (ArrayList<Integer>) inputList.clone();
					//Select Random Piviot point, and put it on end:
					int thresholdValue = 200;
					long start = System.nanoTime(); // start timer
					sortAlgorithms.mergesort(testList, new CustomComparator(), thresholdValue);
					long stop = System.nanoTime(); // end timer
					mergeSortTime += stop - start;
				}
				double averageMergeSortTime = mergeSortTime / (double) 100;
				
			
				System.out.println(sizeOfList + "\t" + averageMergeSortTime + "\t" + averageQuickSortTime);
				
				sizeOfList = sizeOfList + 1000;
			}
		} catch (IOException e) {
			System.err.println("Cannot Write To File!");
		}
		//Charter charter = new Charter(); charter.createChart("quickSortPiviotTest.txt", "quickSortPiviotTest.png");
		
	}
	
	public static void testMergeSortThresholdValues(ArrayList<Integer> inputList){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		SortUtil sortAlgorithms = new SortUtil();
		try (FileWriter containsExperimentFile = new FileWriter(new File("MergeSortThresholdValues.txt"))) {
			int sizeOfList = 1000;
			int thresholdValue = 0;
			for (int i = 0; i <= 5; i++) {
				
				
				inputList = sortAlgorithms.generateAverageCase(sizeOfList);
				long mergeSortTime=0;
				

				//test merge sort with best threshold value 
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {
					ArrayList<Integer> testList = new ArrayList<Integer>();
					
					//Generate AverageaCase List:
					testList = (ArrayList<Integer>) inputList.clone();
					long start = System.nanoTime(); // start timer
					sortAlgorithms.mergesort(testList, new CustomComparator(), thresholdValue);
					long stop = System.nanoTime(); // end timer
					mergeSortTime += stop - start;
				}
				double averageMergeSortTime = mergeSortTime / (double) 100;
				System.out.println(sizeOfList + "\t" + averageMergeSortTime + "\t" + thresholdValue);
				
				sizeOfList = sizeOfList + sizeOfList;
				thresholdValue = thresholdValue +100;
			}
		} catch (IOException e) {
			System.err.println("Cannot Write To File!");
		}
		
		
		
		
	}
	
}