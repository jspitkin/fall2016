package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * 
 * @author Jana Klopsch (u0854469) & Alex Henabray (u0795787), last updated: 09/27/2016
 * Course: CS 2420
 * Assignment 05
 * 
 */
public class SortUtil {

	// this variable is used for the analysis portion of this assignment
	private static int threshhold;
	private static int whichPivot;

	public static void setThreshold(int thresholdValue){
		threshhold = thresholdValue;
	}
	public static void setWhichPivot(int value){
		whichPivot = value;
	}

	/**
	 * This method implements the mergesort algorithm to sort
	 * an ArrayList<T>, using a comparator provided by the user. 
	 * 
	 * @param inputArray - an ArrayList<T> to be sorted
	 * @param comparator - a Comparator object used to compare two elements of type T
	 */
	public static <T> void mergesort(ArrayList<T> inputArrayList, Comparator<? super T> comparator) {


		//Check to see if array is empty or contains one element
		if(inputArrayList.size() <= 1){
			return;
		}
		//Create temporary ArrayList to use in mergeSort
		ArrayList<T> tmpArrayList = new ArrayList<T>();

		//Implement mergeSort on the inputArray
		mergeSort( inputArrayList, tmpArrayList, 0, inputArrayList.size() - 1, comparator);
	}


	/**
	 * This method implements the mergesort algorithm and uses recursion to sort
	 * the inpu ArrayList<T>.
	 * @param inputArrayList 
	 * @param tmpArrayList 
	 * @param left - index for left bound on portion of the ArrayList to be sorted
	 * @param right - index right bound on portion of the ArrayList to be sorted
	 * @param comparator - a Comparator object used to compare two elements of type T
	 */
	public static <T> void mergeSort (ArrayList<T> inputArrayList, ArrayList<T> tmpArrayList, int left, int right, Comparator<? super T> comparator) {

		//If sub-arrays are small enough, use insertion sort
		if(right - left <= threshhold){
			InsertionSort.insertionSort(inputArrayList, comparator);
		}
		//Check if left pointer has crossed right pointer in inputArray
		else if(left < right) {
			//Set center pointer
			int center = (left + right) / 2;
			//Implement mergeSort on left half of array
			mergeSort(inputArrayList, tmpArrayList, left, center, comparator);
			//Implement mergeSort on right half of array
			mergeSort(inputArrayList, tmpArrayList, center + 1, right, comparator);
			//Merge left and right half of inputArray once both halves have been sorted
			merge(inputArrayList, tmpArrayList, left, center + 1, right, comparator);


		}
	}


	/**
	 * This method combines the elements from two ArrayList<T> to a single sorted ArrayList<T>
	 * @param inputArrayList
	 * @param tmpArrayList 
	 * @param leftPos 
	 * @param rightPos
	 * @param rightEnd 
	 * @param comparator - a Comparator object used to compare two elements of type T
	 */
	public static <T> void merge(ArrayList<T> inputArrayList, ArrayList<T> tmpArrayList, int leftPos, int rightPos, int rightEnd, Comparator<? super T> comparator) {

		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numOfElements = rightEnd - leftPos + 1;

		//Index through each half of the array, until one half has all elements placed in tmpArrayList
		while(leftPos <= leftEnd && rightPos <= rightEnd) {
			// Compare first item in left half with first item in right half of array
			//Place the smaller element in the tmpArrayList

			if(comparator.compare(inputArrayList.get(leftPos), inputArrayList.get(rightPos)) >= 0) {
				tmpArrayList.add(tmpPos++, inputArrayList.get(rightPos++));
			}
			else {
				tmpArrayList.add(tmpPos++, inputArrayList.get(leftPos++));
			}
		}
		//If elements are left in left half, place them in tmpArrayList
		while(leftPos <= leftEnd) {
			tmpArrayList.add(tmpPos++, inputArrayList.get(leftPos++));
		}
		//If elements are left in right half, place them in tmpArrayList
		while(rightPos <= rightEnd) {
			tmpArrayList.add(tmpPos++, inputArrayList.get(rightPos++));
		}
		//Copy elements from tmpArrayList back into inputArrayList
		for(int i = 0; i < numOfElements; i++, rightEnd--) {
			inputArrayList.set(rightEnd, tmpArrayList.get(rightEnd));
		}

	}

	/**
	 * This method implements the quick sort algorithm to sort an ArrayList<T> using a comparator 
	 * provided by the user.
	 * @param inputArray - an ArrayList<T>
	 * @param comparator - a Comparator object used to compare two elements of type T
	 */
	public static <T> void quicksort(ArrayList<T> inputArray, Comparator<? super T> comparator) {

		//Base cases
		if(inputArray.size() <= 1){
			return;
		}
		else if(inputArray.size() == 2) {
			if(comparator.compare(inputArray.get(0), inputArray.get(1)) > 0) {
				swap(inputArray, 0, 1);
				return;
			}
			return;
		}

		int pivotIndex;
		if(whichPivot == 1){
			pivotIndex = middlePivot(inputArray);
		}
		else if(whichPivot == 2){
			pivotIndex = medianPivot(inputArray, comparator);
		}
		else {
			 pivotIndex = randomPivot(inputArray);
		}
			

		Object pivotItem = inputArray.get(pivotIndex);

		//Create an array of elements smaller than pivot item
		ArrayList<T> leftArray = new ArrayList<T>();
		//Create an array of elements greater than pivot item
		ArrayList<T> rightArray = new ArrayList<T>();

		// Add elements to leftArray that are less than the pivot
		// Add elements to rightArray that are greater than the pivot
		for(int index =0; index < inputArray.size(); index ++){
			if(comparator.compare(inputArray.get(index), inputArray.get(pivotIndex)) < 0){
				leftArray.add(inputArray.get(index));
			}
			else {
				rightArray.add(inputArray.get(index));
			}
		}


		if(leftArray.size() == 0) {
			leftArray.add(inputArray.get(pivotIndex));
			rightArray.remove(pivotItem);
		}

		// Recursive calls to quick sort
		quicksort(leftArray, comparator);
		quicksort(rightArray, comparator);

		// Merge leftArray, the pivot, and rightArray into a single ArrayList<T>
		ArrayList<T> finalArray = new ArrayList<T>();
		int index = 0;
		for(; index < leftArray.size(); index++) {
			finalArray.add(leftArray.get(index));
		}

		for(int rightIndex = 0; rightIndex < rightArray.size(); rightIndex++) {
			finalArray.add(rightArray.get(rightIndex));
		}

		for(int finalIndex =0; finalIndex < finalArray.size(); finalIndex++){
			inputArray.set(finalIndex, finalArray.get(finalIndex));
		}

	}

	/**
	 * This method swaps two elements in an ArrayList<T>
	 * @param inputArrayList
	 * @param left - index of item in inputArrayList
	 * @param right - index of item in inputArrayList
	 */
	@SuppressWarnings("unchecked")
	public static <T> void swap(ArrayList<T> inputArrayList, int left, int right) {

		Object holdElement = inputArrayList.get(left);
		inputArrayList.set(left, inputArrayList.get(right));
		inputArrayList.set(right, (T) holdElement);

	}



	/**
	 * Selects the middle index for the pivot used in quick sort
	 * @param inputArray
	 * @return
	 */
	public static <T> int middlePivot(ArrayList<T> inputArray){
		return inputArray.size()/2;
	}

	/**
	 * Selects the median element index for the pivot used in quick sort
	 * @param inputArray
	 * @param comparator
	 * @return
	 */
	public static <T> int medianPivot(ArrayList<T> inputArray, Comparator<? super T> comparator){

		ArrayList<T> array = new ArrayList<T>();
		array.add(inputArray.get(0));
		array.add(inputArray.get(inputArray.size()/2));
		array.add(inputArray.get(inputArray.size()-1));

		//Sort the three elements
		InsertionSort.insertionSort(array, comparator);	

		return inputArray.indexOf(array.get(1));
	}


	/**
	 * Selects a random element index for the pivot used in quick sort
	 * @param inputArray
	 * @return
	 */
	public static <T> int randomPivot(ArrayList<T> inputArray) {
		Random rand = new Random();
		return rand.nextInt(inputArray.size());
	}


	/**
	 * Creates an ArrayList of type Integer, containing elements 1 to 
	 * size in ascending order
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateBestCase(int size) {

		if(size < 0) {
			throw new IllegalArgumentException();
		}

		ArrayList<Integer> bestCaseArray = new ArrayList<Integer>();

		for(int index = 1; index <= size; index++) {
			bestCaseArray.add(index);
		}
		return bestCaseArray;
	}

	/**
	 * Creates an ArrayList of type Integer, containing elements in no
	 * particular order
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {

		if(size < 0) {
			throw new IllegalArgumentException();
		}

		ArrayList<Integer> averageCaseArray = new ArrayList<Integer>();

		Random rand = new Random();
		for(int index = 0; index < size; index++) {


			int currentValue = rand.nextInt(size) + 1;

			while(averageCaseArray.contains(currentValue)) {
				currentValue = rand.nextInt(size) + 1;
			}

			averageCaseArray.add(currentValue);			
		}
		return averageCaseArray;
	}

	/**
	 * Creates an ArrayList of type Integer, containing elements from size to 1
	 * in descending order
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {

		if(size < 0) {
			throw new IllegalArgumentException();
		}

		ArrayList<Integer> worstCaseArray = new ArrayList<Integer>();

		for(int index = size; index >= 1; index--) {
			worstCaseArray.add(index);
		}
		return worstCaseArray;
	}

}
