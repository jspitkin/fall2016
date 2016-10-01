package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
/**
 * This class generates mergeSort, and quickSort Algorithms
 * Nickolas Komarnitsky u0717854 , Shahid Bilal Razzaq u0996062
 *
 */
public class SortUtil {

	private static int threshold;

	/**
	 * Performs an insertion sort on an input array using a custom comparator
	 *
	 * @param inputArray
	 *            -- input array of items
	 * @param comparator
	 *            -- input custom comparator
	 */
	public static <T> void insertionSort(T[] inputArray, Comparator<? super T> comparator, int leftBound,
			int rightBound) {
		for (int i = leftBound; i < rightBound; i++) {
			T index = inputArray[i];
			int j = i;
			if (comparator != null) {
				while (j > 0 && comparator.compare(inputArray[j - 1], index) > 0) {
					inputArray[j] = inputArray[j - 1];
					j--;
				}
			}
			else {
				while (j > 0 && (((Comparable) inputArray[j - 1]).compareTo(index)) > 0) {
					inputArray[j] = inputArray[j - 1];
					j--;
				}
			}
			inputArray[j] = index;
		}
	}

	@SuppressWarnings("unchecked")
	/**
	 * Merge Sort driver Method
	 * @param arr
	 * @param comp
	 * @param threshold
	 */
	public static <T> void mergesort(ArrayList<T> arr, Comparator<? super T> comp, int threshold) {
		T[] input = (T[]) arr.toArray();
		T[] temp = (T[]) new Object[arr.size()];
		setThreshold(threshold);
		actualMergeSort(input, temp, comp, 0, input.length - 1);
		arr.clear();
		for (int i = 0; i < temp.length; i++) {
			arr.add(temp[i]);
		}
	}

	/**
	 * Merge sort recursive method
	 * @param arr1
	 * @param comp
	 * @param leftBound
	 * @param rightBound
	 */
	public static <T> void actualMergeSort(T[] arr, T[] temp, Comparator<? super T> comp, int leftBound,
			int rightBound) {
		if (leftBound == rightBound) {
			return;
		}
		int middle = (leftBound + rightBound) / 2;
		if ((middle - leftBound) + 1 <= getThreshold()) {
			insertionSort(arr, comp, leftBound, middle);
		}
		else if ((rightBound - middle + 1) + 1 <= getThreshold()) {
			insertionSort(arr, comp, middle + 1, rightBound);
		}
		actualMergeSort(arr, temp, comp, leftBound, middle);
		actualMergeSort(arr, temp, comp, middle + 1, rightBound);
		merge(arr, temp, leftBound, middle, rightBound, comp);
	}

	/**
	 * Merge the arrays 
	 * @param arr
	 * @param comp
	 * @param start
	 * @param middle
	 * @param end
	 * @return
	 */
	public static <T> void merge(T[] arr, T[] temp, int leftBound, int middle, int rightBound,
			Comparator<? super T> comp) {
		int cap = rightBound - leftBound + 1;
		int index1 = leftBound;
		int index2 = middle + 1;
		int index3 = 0;
		while (index1 <= middle && index2 <= rightBound) {
			if (comp.compare(arr[index1], arr[index2]) < 0) {
				temp[index3] = arr[index1];
				index1++;
			}
			else {
				temp[index3] = arr[index2];
				index2++;
			}
			index3++;
		}

		while (index1 <= middle) {
			temp[index3] = arr[index1];
			index1++;
			index3++;
		}
		while (index2 <= rightBound) {
			temp[index3] = arr[index2];
			index2++;
			index3++;
		}

		for (index3 = 0; index3 < cap; index3++) {
			arr[leftBound + index3] = (T) temp[index3];
		}
	}

	
	//QuickSort Algorithm from here on out
	
	
	/**
	 * QuickSort Driver Method.
	 * @param arrayList
	 * @param comp
	 */
	public static <T> void quicksort(ArrayList<T> arrayList, Comparator<? super T> comp) {
			
			recursiveQuickSort(arrayList, 0, arrayList.size()-1, comp);
	}
	
	/**
	 * The actual recursive method which will perform a quickSort on the input Array
	 * @param <T>
	 * @param inputArray
	 * @param piviot
	 * @param leftPointer
	 * @param rightPointer
	 * @return
	 */
	public static <T> void recursiveQuickSort(ArrayList<T> inputArray, int leftPosition, int rightPosition, Comparator<? super T> comp){
		if(leftPosition >= rightPosition){
			return;
		}
		int piviotLocation = partition(inputArray, leftPosition, rightPosition, comp);
		recursiveQuickSort(inputArray, leftPosition, piviotLocation-1, comp);
		recursiveQuickSort(inputArray, piviotLocation+1, rightPosition, comp);
	}
	
	/**
	 * This Method partitions the arraylist
	 * @param inputArray
	 * @param lowIndex
	 * @param highIndex
	 * @return
	 */
	public static <T> int partition(ArrayList<T> inputArray, int leftBound, int rightBound, Comparator<? super T> comp){
		Comparator<? super T> arrayComparator = comp;
	
		int leftPointerPosition = leftBound -1;
		int rightPointerPosition = rightBound;
		while(true){
			while(arrayComparator.compare(inputArray.get(++leftPointerPosition), inputArray.get(rightBound)) < 0){
				
			}
			while(((rightPointerPosition > 0)) &&(arrayComparator.compare(inputArray.get(--rightPointerPosition), inputArray.get(rightBound)) > 0)){
				
			}
			if(leftPointerPosition >= rightPointerPosition){
				break;
			}else{
				swap(inputArray, leftPointerPosition, rightPointerPosition);
			}
		}
		swap(inputArray, leftPointerPosition, rightBound);
		return leftPointerPosition;
	
	}
	
	/**
	 * Helper method that Swaps Elements in the array
	 * @param inputArray
	 * @param leftItem
	 * @param rightItem
	 */
	public static <T> void swap(ArrayList<T> inputArray, int leftItem, int rightItem){
		T temp = inputArray.get(leftItem);
		inputArray.set(leftItem, inputArray.get(rightItem));
		inputArray.set(rightItem, temp);
	}
	
	
	/**
	 * This method will return the middle pivot point needed to do the quickSort
	 * @param inputARray
	 * @return -- location of middle piviot point
	 */
	public static <T> int middlePiviotPoint(ArrayList<T> inputArray){
		int piviotPoint = inputArray.size()/2;
		return piviotPoint;
	}
	/**
	 * This method will return the LeftMost pivot point needed to do the quickSort
	 * this can be the worst case scenario for a list 
	 * @param inputARray -- original array
	 * @return - location of left pivot point
	 */
	public static <T> int leftPiviotPoint(ArrayList<T> inputArray){
		return 0;
	}
	/**
	 * Returns the right pivot point 
	 * @param inputArray -- the original array
	 * @return location of right pivot point
	 */
	public static <T> int rightPiviotPoint(ArrayList<T> inputArray){
		int piviotPoint = inputArray.size()-1;
		return piviotPoint;
	}
	
	/**
	 * This will return the median of threee poviot point
	 * @param inputArray --original array
	 * @param comp -- custom comparator
	 * @return -- location of median pivot locaton 
	 */
	public static <T> int medianOfThreePiviot(ArrayList<T> inputArray,int leftPoint, int rightPoint, Comparator comp){
		Comparator objectComparator = comp;
		T left = inputArray.get(leftPoint);
		T center = inputArray.get((leftPoint+rightPoint) /2);
		T right = inputArray.get(rightPoint);
		
		if(objectComparator.compare(left, center)>0 && objectComparator.compare(left, right)<0 && objectComparator.compare(center, right)<0){
			return leftPoint;
		}else if(objectComparator.compare(left, center)<0 && objectComparator.compare(left, right)<0 && objectComparator.compare(center, right)>0){
			return rightPoint;
		}else if(objectComparator.compare(left, center)>0 &&objectComparator.compare(left, right)>0 && objectComparator.compare(center, right)<0){
			return rightPoint;
		}else{
			return ((leftPoint+rightPoint)/2);
		}
	}

	
	/**
	 * this will return a random piviot point 
	 * @param inputArray --original array
	 * @return -- location of a random location
	 */
	public static <T> int randomPiviotPoint(ArrayList<T> inputArray){
		Random randomNumber = new Random();
		return randomNumber.nextInt(inputArray.size()-1);
	}
	
	/**
	 * This method returns an arrayList of Integers from 1 to size in ascending Order
	 * @param size --size of array
	 * @return -- best case array
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> bestCaseList = new ArrayList<Integer>();
		for(int i=0 ; i< size; i++){
			bestCaseList.add(i);
		}
		return bestCaseList;

	}

	/**
	 * This method returns an arrayList of Integers from 1 to size in random order
	 * @param size -- size of array
	 * @return -- average case array
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		Random randomNumberGenerator = new Random();
		ArrayList<Integer> averageCaseList = new ArrayList<Integer>();
		while(averageCaseList.size()<size){
			int randomNumber = randomNumberGenerator.nextInt(size);
			if(!averageCaseList.contains(randomNumber)){
				averageCaseList.add(randomNumber);
			}
		}
		return averageCaseList;

	}

	/**
	 * This method returns an arrayList of integers from 1 to size in descending order.
	 * @param size -- size of array
	 * @return -- worst case array
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {
		ArrayList<Integer> worstCaseList = new ArrayList<Integer>();
		for (int i = size-1; i >=0 ; i--){
			worstCaseList.add(i);
		}
		return worstCaseList;

	}

	/**
	 * The Threshold value is returned
	 * @return -- gets the threshold
	 */
	public static int getThreshold() {
		return threshold;
	}

	/**
	 * setter method to set a threshold for merge sort
	 * @param threshold -- size of threshold value
	 */
	public static void setThreshold(int threshold) {
		SortUtil.threshold = threshold;
	}
}
