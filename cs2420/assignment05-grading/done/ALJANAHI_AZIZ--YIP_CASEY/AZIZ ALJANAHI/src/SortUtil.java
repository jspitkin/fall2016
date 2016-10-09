package assignment05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * SortUtil
 * 
 * @author Ho Lam Yip u1025709
 * @author Abdulaziz Aljanahi u0901606
 */
public class SortUtil<T> {
	static Comparator<Integer> comparator;
	private static int quicksortThreshold = 5;
	private static int mergesortThreshold = 7;
	private static int Pivot = 1;

	/**
	 * Sets the Threshold for mergesort
	 * 
	 * @param x
	 *            - new Threshold
	 */
	public static void setMThresh(int x) {
		mergesortThreshold = x;
	}

	/**
	 * Sets the Threshold for quicksort
	 * 
	 * @param x
	 *            - new Threshold
	 */
	public static void setQThresh(int x) {
		mergesortThreshold = x;
	}

	/**
	 * Gets the Threshold for mergesort
	 * 
	 * @return Threshold
	 */
	public static int getMThresh() {
		return mergesortThreshold;
	}

	/**
	 * Gets the Threshold for quicksort
	 * 
	 * @return Threshold
	 */
	public static int setQThresh() {
		return mergesortThreshold;
	}

	/**
	 * Sorts an array list via mergesort, according to a gevin comparator
	 * 
	 * @param arr
	 *            - array sorted
	 * @param comp
	 *            - comparator
	 */
	public static <T> void mergesort(ArrayList<T> arr, Comparator<? super T> comp) {
		if (arr.size() <= 1 || arr == null) {
			return;
		}

		T[] temp = (T[]) new Object[arr.size()];

		Ms(arr, temp, 0, arr.size() - 1, comp);
	}

	/**
	 * Private method that handles the actual sorting of the list through
	 * recursion
	 * 
	 * @param arr
	 *            - sort list
	 * @param temp
	 *            - temporary list to move elements in
	 * @param leftIndex
	 *            - the index to start sorting from
	 * @param rightIndex
	 *            - the index to end sorting from
	 * @param comp
	 *            - comparator
	 */
	private static <T> void Ms(ArrayList<T> arr, T[] temp, int leftIndex, int rightIndex, Comparator<? super T> comp) {
		if (leftIndex + mergesortThreshold > rightIndex) {// if threshold is met
															// use insertionsort
			insertionSort(arr, leftIndex, rightIndex, comp);
		} else {
			int mid = (leftIndex + rightIndex) / 2;
			Ms(arr, temp, leftIndex, mid, comp);
			Ms(arr, temp, mid + 1, rightIndex, comp);
			merge(arr, temp, leftIndex, mid + 1, rightIndex, comp);
		}
	}

	/**
	 * Private method that handles the merging of the lists
	 * 
	 * @param arr
	 *            - sort list
	 * @param temp
	 *            - temporary list to move elements in
	 * @param leftIndex
	 *            - the index to start merging from
	 * @param rightIndex
	 *            - the index to end merging from
	 * @param comp
	 *            - comparator
	 */
	private static <T> void merge(ArrayList<T> arr, T[] temp, int leftIndex, int midIndex, int rightIndex,
			Comparator<? super T> comp) {
		int end = midIndex - 1; // the point before the middle point, where
								// checking ends
		int tIndex = leftIndex; // starting point
		int elements = rightIndex - leftIndex + 1; // number of elements in list

		while (leftIndex <= end && midIndex <= rightIndex) { // if the left did
																// not reach the
																// end point and
																// the mid point
																// did not reach
																// the list end
																// point
			if (comp.compare(arr.get(leftIndex), arr.get(midIndex)) <= 0) {
				temp[tIndex] = arr.get(leftIndex); // adds element to temporary
													// array
				tIndex++;
				leftIndex++;
			} else {
				temp[tIndex] = arr.get(midIndex);
				tIndex++;
				midIndex++;
			}
		}
		while (leftIndex <= end) { //
			temp[tIndex] = arr.get(leftIndex);
			tIndex++;
			leftIndex++;
		}
		while (midIndex <= rightIndex) { // as long as mid is less than the
											// right most position, set the
											// element at the temporary index to
											// the element at mid point
			temp[tIndex] = arr.get(midIndex);
			tIndex++;
			midIndex++;
		}

		for (int i = 0; i < elements; i++, rightIndex--) {
			arr.set(rightIndex, temp[rightIndex]);
		}
	}

	/**
	 * Sorts an array list via quicksort, according to a gevin comparator
	 * 
	 * @param arr
	 *            - array sorted
	 * @param comp
	 *            - comparator
	 */
	public static <T> void quicksort(ArrayList<T> arr, Comparator<? super T> comp) {
		if (arr.size() <= 1) {
			return;
		}
		Qs(arr, 0, arr.size() - 1, comp);
	}
 public static void setPivot(int x){
	 Pivot=x;
	 
 }
	/**
	 * Private method that handles the actual sorting of the list through
	 * recursion
	 * 
	 * @param arr
	 *            - sort list
	 * @param temp
	 *            - temporary list to move elements in
	 * @param leftIndex
	 *            - the index to start sorting from
	 * @param rightIndex
	 *            - the index to end sorting from
	 * @param comp
	 *            - comparator
	 */
	private static <T> void Qs(ArrayList<T> arr, int leftIndex, int rightIndex, Comparator<? super T> comp) {

		try {
			if (arr == null)
				return;
			if (leftIndex + quicksortThreshold > rightIndex) { // if the
																// threshold is
																// met use
																// insertion
																// sort
				insertionSort(arr, leftIndex, rightIndex, comp);
			} else {
				T pivot;
				if (Pivot == 1 || Pivot > 3)
					middlePivot(arr, leftIndex, rightIndex, comp); // generate
																	// pivot
				if (Pivot == 2)
					medianPivot(arr, leftIndex, rightIndex, comp);
				
				if (Pivot == 3)
					firstPivot(arr, leftIndex, rightIndex, comp);
				pivot = arr.get(rightIndex);
				int L = leftIndex - 1;
				int R = rightIndex;

				while (true) { // endless loop

					while (L < rightIndex && comp.compare(arr.get(++L), (pivot)) < 0) {
					} // find value greater than pivot
					while (R > leftIndex && comp.compare((arr.get(--R)), pivot) > 0) {
					} // find value less than pivot
					if (L >= R) {
						break; // breaks loop if the two pointers meet
					}
					swap(arr, L, R); // if does not break, that means L & R are
										// partners, so swap them
				}
				swap(arr, L, rightIndex);
				Qs(arr, leftIndex, L - 1, comp); // recursion calls
				Qs(arr, L + 1, rightIndex, comp);

			}
		} catch (Exception e) {
			return;
		}
	}

	public static <T> void PivotChooser(int x, ArrayList<T> arr, int leftIndex, int rightIndex,
			Comparator<? super T> comp) {
		if (x == 1)
			medianPivot(arr, leftIndex, rightIndex, comp);
		if (x == 2)
			middlePivot(arr, leftIndex, rightIndex, comp);
		if (x == 3)
			firstPivot(arr, leftIndex, rightIndex, comp);
	}

	/**
	 * Finds the median between the first,middle and last elements in a list,
	 * then arranges them accordingly, and places the median in the last index
	 * of the list
	 * 
	 * @param arr
	 *            - list
	 * @param leftIndex
	 *            - beginning point of list/sublist
	 * @param rightIndex
	 *            - end point of list/sublist
	 * @param comp
	 *            - comparator
	 */
	private static <T> void medianPivot(ArrayList<T> arr, int leftIndex, int rightIndex, Comparator<? super T> comp) {
		int middle = leftIndex + (rightIndex - leftIndex) / 2;
		if (comp.compare(arr.get(middle), (arr.get(leftIndex))) < 0) { // compares
																		// the
																		// first,
																		// middle
																		// and
																		// last
																		// index
																		// and
																		// swaps
																		// them
																		// accordingly
			swap(arr, leftIndex, middle);
		}
		if (comp.compare(arr.get(rightIndex), (arr.get(leftIndex))) < 0) {
			swap(arr, leftIndex, rightIndex);
		}
		if (comp.compare(arr.get(rightIndex), (arr.get(middle))) < 0) {
			swap(arr, middle, rightIndex);
		}
		swap(arr, middle, rightIndex);

	}

	/**
	 * Finds the middle element in the list, and places the median in the last
	 * index of the list
	 * 
	 * @param arr
	 *            - list
	 * @param leftIndex
	 *            - beginning point of list/sublist
	 * @param rightIndex
	 *            - end point of list/sublist
	 * @param comp
	 *            - comparator
	 */
	private static <T> void middlePivot(ArrayList<T> arr, int leftIndex, int rightIndex, Comparator<? super T> comp) {
		int middle = leftIndex + (rightIndex - leftIndex) / 2; // finds the
																// middle
																// element
		swap(arr, middle, rightIndex);

	}

	/**
	 * Finds the first element in the list, and places the median in the last
	 * index of the list (worst case)
	 * 
	 * @param arr
	 *            - list
	 * @param leftIndex
	 *            - beginning point of list/sublist
	 * @param rightIndex
	 *            - end point of list/sublist
	 * @param comp
	 *            - comparator
	 */
	private static <T> void firstPivot(ArrayList<T> arr, int leftIndex, int rightIndex, Comparator<? super T> comp) {
		swap(arr, leftIndex, rightIndex);
	}

	/**
	 * 
	 * @param size
	 *            - size of list
	 * @return generates an ArrayList of integers 1 to n in ascending order
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> bestCase = new ArrayList<Integer>(size);
		for (int i = 1; i <= size; i++) {
			bestCase.add(i);
		}
		return bestCase;
	}

	/**
	 * 
	 * @param size
	 *            - list size
	 * @return an ArrayList of integers randomly generated from 1 to size
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		ArrayList<Integer> averageCase = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			averageCase.add((int) ((Math.random() * size) + 1));
		}
		return averageCase;
	}

	/**
	 * 
	 * @param size
	 *            - list size
	 * @return an ArrayList of integers 1 to size in descending order
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {
		ArrayList<Integer> worstCase = new ArrayList<Integer>(size);
		for (int i = size; i >= 1; i--) {
			worstCase.add(i);
		}
		return worstCase;
	}

	/**
	 * sorts a list via insertionSort, according to a gevin comparator
	 * 
	 * @param arr
	 *            - array sorted
	 * @param leftIndex
	 *            - beginning point of list/sublist
	 * @param rightIndex
	 *            - end point of list/sublist
	 * @param comp
	 *            - comparator
	 * 
	 */
	private static <T> void insertionSort(ArrayList<T> arr, int leftIndex, int rightIndex, Comparator<? super T> comp) {

		for (int sortIndex = leftIndex + 1; sortIndex <= rightIndex; sortIndex++) { // if
																					// the
																					// sorting
																					// index
																					// is
																					// between
																					// the
																					// specified
																					// range
																					// of
																					// checking
			T temp = arr.get(sortIndex); // sets the element at the element
											// needed to be sorted
			int unsortedIndex = sortIndex;
			while (unsortedIndex > leftIndex && comp.compare(temp, arr.get(unsortedIndex - 1)) < 0) { // as
																										// long
																										// as
																										// the
																										// unsorted
																										// index
																										// is
																										// in
																										// the
																										// specified
																										// range
																										// and
																										// it
																										// is
																										// less
																										// than
																										// element
																										// it
																										// is
																										// compared
																										// with,
																										// swap
																										// them
				arr.set(unsortedIndex, arr.get(unsortedIndex - 1));
				unsortedIndex--;
			}
			arr.set(unsortedIndex, temp);
		}
	}

	/**
	 * Swaps two elements in an list according to their indexes
	 * 
	 * @param arr
	 *            - list
	 * @param x
	 *            - 1st Index
	 * @param y
	 *            - 2nd Index
	 */

	private static <T> void swap(ArrayList<T> arr, int x, int y) {
		T temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
}
