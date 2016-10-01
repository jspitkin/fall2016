package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * This class contains a set of tools for sorting with quicksort, mergesort, and creating arrays of different cases.
 * @author Anthony Iovino (u0734680) & Jacob Brown (u0583647)
 *
 */
public class SortUtil{
 
	/**
	 * The threshold (size of subArray) at which mergesort switches to insertion sort. (Default of 2)
	 */
	static int mergeThreshold = 2;
	
	/**
	 * The strategy pivot selection strategy for quickSort (1 - 3; 1 = Middle Index; 2 = Lowest index in array; 3 = Random Index)
	 */
	static int quickSortStrategy = 1;
	
//MergeSort Methods: 
	
	/**
	 * This driver method performs a mergesort on a generic ArrayList given as input.
	 * @param list the ArrayList that needs sorting.
	 * @param comparator a comparator object which sorts 
	 */
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator){
	
		// Creates a buffer array for the sorting.
		@SuppressWarnings("unchecked")
		T[] buffer = (T[]) new Object[list.size()];
		
		//Initating the recursive sorting.
		mergeSortRecursive(list, buffer, 0, list.size() - 1, comparator);
	}
	
	/**
	 * Private method which performs a recursive a mergesort on an entire generic ArrayList.
	 *   
	 * @param list incoming Arraylist.
	 * @param buffer a holder array (Needs to be size of first array)
	 * @param left - left side the array.
	 * @param right - right side of the array.
	 * @param comparator - the comparator
	 */
	private static <T> void mergeSortRecursive(ArrayList<T> list, T[] buffer, int left,int right, Comparator<? super T> comparator){

		
		if (left <= right) 
		{
			// if the size of the array has become small enough, switch to insertion Sort
			if(right - left <= mergeThreshold){
				insertionSort(list,left,right,comparator);
				return;
			}
	
			int middle = ((left + right) / 2);
			
			//Perform a recursive mergeSort on two halves of parent array.
			mergeSortRecursive(list, buffer, left, middle, comparator);
			mergeSortRecursive(list, buffer, middle + 1, right, comparator);
			
			//Perform actual sorting of subArray.
			mergeArrays(list,buffer,left, middle + 1, right, comparator);
		}

	}

	/**
	 * This method merges the subarrays of the generic ArrayList given as input.  
	 * @param list - incoming array.
	 * @param buffer - buffer array.
	 * @param leftPos - beginning of left bounds.
	 * @param rightPos - beginning of right bounds.
	 * @param rightBounds end of right bounds.
	 * @param comparator comparing object.
	 */
	public static <T> void mergeArrays(ArrayList<T> list, T[] buffer, int leftPos, int rightPos, int rightBounds, Comparator<? super T> comparator ){
		
		int leftBounds = rightPos - 1;
		int currentPos = leftPos;
		
		//Compares two different sides of an array and adds them to a buffer if one is smaller than the other.
		while ((leftPos <= leftBounds) && (rightPos <= rightBounds)) {
			if (comparator.compare(list.get(leftPos),list.get(rightPos)) < 0) {
				buffer[currentPos] = list.get(leftPos);
				currentPos++; leftPos++;
			}
			else {
				buffer[currentPos] = list.get(rightPos);
				currentPos++; rightPos++;
			}
		}
		
		// If there are any elements from the left side, place in buffer array.
		while (leftPos <= leftBounds) {
			buffer[currentPos] = list.get(leftPos);
			currentPos++; leftPos++;
		}
		
		// If there are any elements from the right side, place in buffer array.
		while (rightPos <= rightBounds) {
			buffer[currentPos] = list.get(rightPos);
			currentPos++; rightPos++;
		}
		
		int Elements = rightBounds - leftPos + 1;
		
		// Add elements from buffer back to orginal array.
		for (int i = 0; i < Elements; i++ ) {
			list.set(rightBounds,buffer[rightBounds]);
			rightBounds--;
		}
		
	}

	/**
	 * This method performs insertion sort on the Generic ArrayList given as input.  
	 * @param list
	 * @param leftBounds
	 * @param rightBounds
	 * @param comparator
	 */
	public static <T> void insertionSort(ArrayList<T> list,int leftBounds, int rightBounds, Comparator<? super T> comparator){
		
		for (int sortedPos = leftBounds; sortedPos < rightBounds + 1; sortedPos++){
			for (int currPos = sortedPos - 1; currPos >= 0; currPos--) {
				if (comparator.compare(list.get(currPos), list.get(currPos + 1)) > 0 ) {
					T swapObject = list.get(currPos);
					
					list.set(currPos, list.get(currPos + 1));
					list.set(currPos + 1, swapObject);
					
				}
				else {
					break;
				}
			}
		}
	}
	
//QuickSort Methods: 
	
	/**
	 * This driver method performs a quicksort on the generic ArrayList given as input.
	 * @param - incoming ArrayList to be sorted.
	 * @param - incoming comparator which is used as sorting logic for the objects of the array.
	 */
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator){
		//Initiate the recursive sorting.
		quicksortRecursive(list, comparator, 0, list.size()-1);
	}
	
	/**
	 * This recursive method performs a quicksort on the generic ArrayList given as input.  
	 * @param list - incoming array.
	 * @param comparator
	 * @param lower
	 * @param higher
	 */
	public static <T> void quicksortRecursive(ArrayList<T> list, Comparator<? super T> comparator, int lower, int higher){
		
		int pivotIndex;
		
		// Cases of which index is choosen depending on a global variable.
		switch (quickSortStrategy) {
		case 1: pivotIndex = (higher-lower)/2 + lower;
				break;
		case 2: pivotIndex = lower;
				break;
		case 3: pivotIndex = (int) Math.min((int) higher,lower + new Random().nextInt(higher - lower));
				break;
		default: pivotIndex = (higher-lower)/2 + lower;
				break;
		}
		
		T pivotValue =  list.get(pivotIndex);
		
		int i = lower;  
		int j = higher;
		
		// while list boundaries haven't crossed
		while (i <= j) {
			// Skip any numbers in left list that are less than pivot
			while (comparator.compare(list.get(i), pivotValue) < 0) {
				i++;
			}
			// Skip any numbers in right list that are greater than pivot
			while (comparator.compare(list.get(j), pivotValue) > 0) {
				j--;
			}
			
			// 2 numbers that are out of position have been found, swap them
			if (i <= j) {
				swap(list, i, j);
				i++;
				j--;
			}
		}
		
		// Sort left list recursively
		if (lower < j)
			quicksortRecursive(list, comparator, lower, j);
		// Sort right list recursively
		if (i < higher)
			quicksortRecursive(list, comparator, i, higher);
	}
	
	/**
	 * This helper method swaps the elements in the ArrayList given as input, after they are compared to the pivot.  
	 * @param list
	 * @param i
	 * @param j
	 */
	private static <T> void swap(ArrayList<T> list, int i, int j) {//Swap helper method 
		T temp = list.get(i);
		list.set(i,  list.get(j));
		list.set(j, temp);
	}

		
//Array Generators and Print Methods: 
	/**
	 * This method generates and returns an ArrayList of integers 1 to size in ascending order.
	 * @param size
	 * @return ArrayList
	 */
	public static ArrayList<Integer> generateBestCase(int size){

		ArrayList<Integer> bestCase = new ArrayList<Integer>(size);
	
		for(int i = 1; i <= size; i++){
			bestCase.add(i);
		}
		
		return bestCase;
		
	}
	
	/**
	 * This method generates and returns an ArrayList of integers 1 to size in permuted order (i,e., randomly ordered).
	 * @param size
	 * @return ArrayList 
	 */
	public static ArrayList<Integer> generateAverageCase(int size){
		
        ArrayList<Integer> averageCase = new ArrayList<Integer>();

        for (int i = 1; i <= size; i++) {
        	averageCase.add(i);
        }
        
        Random randomObject  = new Random();
        
        for(int iteration = 1; iteration < size; iteration++ ){
        	
        	int ranNumber = Math.min(size-1,randomObject.nextInt(size) + 1);
        	
        	int cacheInt = averageCase.get(iteration);
        	
            averageCase.set(iteration, averageCase.get(ranNumber));
            averageCase.set(ranNumber, cacheInt);
        }
        
		return averageCase;
		
	}
	
	/**
	 * This method generates and returns an ArrayList of integers 1 to size in descending order.
	 * @param size
	 * @return 
	 */
	public static ArrayList<Integer> generateWorstCase(int size){
	
		ArrayList<Integer> worstCase = new ArrayList<Integer>(size);
		
		for(int i = size; i >= 1; i--){
			worstCase.add(i);
		}
		
		return worstCase;
	}

	public static <T> void printArray(T[] arrayPrinted) {
	
		String stringStrong = "[";
	
		
		
		for (int i = 0; i < arrayPrinted.length; i++ ) {
			if (arrayPrinted[i] != null) {
				stringStrong+= arrayPrinted[i].toString() + ",";
			}
		}
		
		System.out.println(stringStrong.substring(0, stringStrong.length() - 1) + "]");
		
	}
	
	public static <T> void printArray(ArrayList<T> arrayPrinted) {
		
		String stringStrong = "[";
	
		
		
		for (int i = 0; i < arrayPrinted.size(); i++ ) {
			stringStrong+= arrayPrinted.get(i).toString() + ",";
		}
		
		System.out.println(stringStrong.substring(0, stringStrong.length() - 1) + "]");
		
	}
	
}
	

