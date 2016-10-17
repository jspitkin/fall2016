package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Utility class that contains generic sorting methods for ArrayLists
 * Both quicksort and mergesort are both implemented in this class.
 * 
 * The mergesort takes advantage of insertion sort to sort small arrays. The threshold for
 * this insertion sort can be set by calling setInsertionThreshold.
 * 
 * By default the quicksort uses a median of three pivot selection. This selection can be changed
 * by calling setPivotChoiceMethod and passing in one of the constants for the class.
 * 
 * @author Jared Nielson u0495206, Christian Hansen u0621884
 *
 */
public class SortUtil {
	
	private static int INSERTION_SORT_THRESHOLD = 10;
	private static int PIVOT_CHOICE = 1;
	private static Random rng = new Random();
	
	public static final int MEDIAN_OF_THREE = 0;
	public static final int RANDOM_PIVOT = 2;
	public static final int MIDDLE_ELEMENT = 1;
	
	/**
	 * Performs a mergesort on the ArrayList using the given comparator. Once the subarray sizes
	 * are lower than the insertion sort threshold insertion sort is used to sort the subarrays. 
	 * By default this threshold is 10 but can bechanged by calling the setInsertionThreshold method.
	 * @param toSort - The ArrayList to be sorted
	 * @param cmp - The Comparator to be used for the sort.
	 */
	public static <T> void mergesort(ArrayList<T> toSort, Comparator<? super T> cmp){
		if(toSort.size() <= 1){
			return;
		}
		
		Object[] tempArray = new Object[toSort.size()];
		
		mergesortRecursive(toSort, cmp, 0, toSort.size() - 1, tempArray);
		
		
	}
	
	/**
	 * Performs a quicksort on the ArrayList using the given comparator. By default the pivot
	 * for the sort is selected using a median of three method. The pivot selection method can be changed
	 * by calling setPivotSelectionMethod and then passing in one of the choice constants.
	 * @param toSort - The ArrayList to be sorted.
	 * @param cmp - The Comparator to be used for the sort.
	 */
	public static <T> void quicksort(ArrayList<T> toSort, Comparator<? super T> cmp){
		if(toSort.size() <= 1){
			return;
		}
		
		quicksortRecursive(toSort, cmp, 0, toSort.size() - 1);
		
	}
	
	/**
	 * Generates a best case scenario array list (ie sorted) of Integers from 1 to size.
	 * @param size - The upper bound of the desired ArrayList
	 * @return a sorted ArrayList of Integers from one to size.
	 */
	public static ArrayList<Integer> generateBestCase(int size){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int index = 1; index <= size; index++){
			result.add(index);
		}
		
		return result;
	}
	
	/**
	 * Generates an average case scenario array list (ie permuted) of Integers from 1 to size.
	 * @param size - The upper bound of the desired ArrayList
	 * @return a random ArrayList of Integers withe elements one to size.
	 */
	public static ArrayList<Integer> generateAverageCase(int size){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int index = 1; index <= size; index++){
			result.add(index);
		}
		
		for(int curIdx = 0; curIdx < size; curIdx++){
			int rdmIdx = rng.nextInt(size);
			
			Integer tmp = result.get(curIdx);
			result.set(curIdx, result.get(rdmIdx));
			result.set(rdmIdx, tmp);
		}
		
		return result;
	}
	
	/**
	 * Generates a worst case scenario ArrayList (ie reverse sorted) of Integers from size to 1.
	 * @param size - The upper bound of the desired ArrayList
	 * @return a reverse sorted ArrayList of Integers from size to one.
	 */
	public static ArrayList<Integer> generateWorstCase(int size){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for(int index = size; index > 0; index--){
			result.add(index);
		}
		
		return result;
	}
	
	/**
	 * Sets the pivot choice method for the quicksort method. Either Median of Three, Random Pivot, or Middle Element
	 * @param choice - The choice for the method. Class constants are provided for the choice.
	 * If an int other than 0, 1 or 2 is passed in the default method is set.
	 */
	public static void setPivotChoiceMethod(int choice){
		if( choice < 0 || choice > 3){
			PIVOT_CHOICE = 0;
		}
		
		PIVOT_CHOICE = choice;
	}
	
	/**
	 * Sets the insertion sort threshold for mergesort method in this class.
	 * @param threshold - The maximum size of a subarray before insertion sort is used.
	 * If a value less than 0 is passed the threshold is set to 0.
	 */
	public static void setInsertionThreshold(int threshold){
		if(threshold < 0){
			INSERTION_SORT_THRESHOLD = 0;
		}
		
		INSERTION_SORT_THRESHOLD = threshold;
	}
	
	/*********************************************************************************
	 * Private mergesort Helper Methods
	 *********************************************************************************/
	
	/**
	 * Recursive portion of the mergesort method, called from the public driver method.
	 * @param toSort - The ArrayList to be sorted
	 * @param cmp - The comparator for the sort
	 * @param left - The index of the left element (inclusive) of subbarray to be sorted
	 * @param right - The index of the right element (inclusive) of the subarray to be sorted
	 * @param tmp - A temporary Array for the merge sort (should be created in the driver method).
	 */
	private static <T> void mergesortRecursive(ArrayList<T> toSort, Comparator<? super T> cmp, 
												int left, int right, Object[] tmp){
		if(right - left <= INSERTION_SORT_THRESHOLD){
			insertionsort(toSort, cmp, left, right);
			return;
		}
		
		int mid = (left + right)/2;
		mergesortRecursive(toSort, cmp, left, mid, tmp);
		mergesortRecursive(toSort, cmp, mid+ 1, right, tmp);
		merge(toSort, cmp, left, mid + 1, right, tmp);
	}
	
	/**
	 * Merging method for the mergesort
	 * @param toSort - The ArrayList to be sorted
	 * @param cmp - The comparator for the sort
	 * @param left - The index of the left element (inclusive) of subbarray to be sorted
	 * @param right - The index of the right element (inclusive) of the subarray to be sorted
	 * @param tmp - A temporary Array for the merge sort (should be created in the driver method).
	 */
	@SuppressWarnings("unchecked")
	private static <T> void merge(ArrayList<T> toSort, Comparator<? super T> cmp,
									int left, int mid, int right, Object[] tmp){
		
		int indexLeft = left;
		int indexRight = mid;
		int indexTmpArray = 0;
		
		while(indexLeft < mid && indexRight <= right){
			if(cmp.compare(toSort.get(indexLeft), toSort.get(indexRight)) <= 0){
				tmp[indexTmpArray] = toSort.get(indexLeft);
				indexLeft++;
				indexTmpArray++;
			}else{
				tmp[indexTmpArray] = toSort.get(indexRight);
				indexRight++;
				indexTmpArray++;
			}
		}
		
		while(indexLeft < mid){
			tmp[indexTmpArray] = toSort.get(indexLeft);
			indexLeft++;
			indexTmpArray++;
		}
		
		while(indexRight <= right){
			tmp[indexTmpArray] = toSort.get(indexRight);
			indexRight++;
			indexTmpArray++;
		}
		
		for(int index = 0; index < indexTmpArray; index++){
			toSort.set(left, (T) tmp[index]);
			left++;
		}
		
		
	}
	
	/**
	 * Performs an insertion sort on the subarray passed in.
	 * @param toSort - The ArrayList to be sorted
	 * @param cmp - The comparator for the sort
	 * @param left - The index of the left element (inclusive) of subbarray to be sorted
	 * @param right - The index of the right element (inclusive) of the subarray to be sorted
	 */
	private static <T> void insertionsort(ArrayList<T> toSort, Comparator<? super T> cmp,
											int left, int right){
		for(int outIdx = left + 1; outIdx <= right; outIdx++){
			T tmp = toSort.get(outIdx);
			for(int innerIdx = outIdx; innerIdx > left; innerIdx--){
				if(cmp.compare(tmp, toSort.get(innerIdx - 1)) < 0){
					toSort.set(innerIdx, toSort.get(innerIdx - 1));
					toSort.set(innerIdx - 1, tmp);
 				} else {
 					break;
 				}
			}
		}
	}
	
	/********************************************************************************************
	 * quicksort private helper methods
	 ********************************************************************************************/
	/**
	 * Recursive portion of the quicksort method called from the public driver method.
	 * @param toSort - The ArrayList to be sorted
	 * @param cmp - The comparator for the sort
	 * @param left - The index of the left element (inclusive) of subbarray to be sorted
	 * @param right - The index of the right element (inclusive) of the subarray to be sorted
	 */
	private static <T> void quicksortRecursive(ArrayList<T> toSort, Comparator<? super T> cmp, int left, int right){
		if((right - left) < 1){
			return;
		}
		if((right - left) == 1){
			if(cmp.compare(toSort.get(left), toSort.get(right)) > 0){
				T tmp = toSort.get(left);
				toSort.set(left, toSort.get(right));
				toSort.set(right, tmp);
			}
			return;
		}
		
		int pivotIdx = partition(toSort, cmp, left, right);
		quicksortRecursive(toSort, cmp, left, pivotIdx - 1);
		quicksortRecursive(toSort, cmp, pivotIdx + 1, right);
		
		
	}
	
	/**
	 * Method to partition a subarray
	 * @param toSort - The ArrayList to be sorted
	 * @param cmp - The comparator for the sort
	 * @param left - The index of the left element (inclusive) of subbarray to be sorted
	 * @param right - The index of the right element (inclusive) of the subarray to be sorted
	 * @return - The final index of the pivot after the partitioning
	 */
	private static <T> int partition(ArrayList<T> toSort, Comparator<? super T> cmp, int left, int right){
		int pivotIdx = getPivot(toSort, cmp, left, right);
		int leftPointer = left;
		int rightPointer = right - 1;
		
		T pivot = toSort.get(pivotIdx);
		toSort.set(pivotIdx, toSort.get(right));
		toSort.set(right, pivot);
		T tmp;
		
		while(true){
			while(cmp.compare(toSort.get(leftPointer), pivot) <= 0 && leftPointer <= rightPointer){
				leftPointer++;
			}
			
			while(rightPointer >= leftPointer && cmp.compare(toSort.get(rightPointer), pivot) > 0){
				rightPointer--;
			}
			
			if(leftPointer >= rightPointer){
				break;
			}
			
			tmp = toSort.get(leftPointer);
			toSort.set(leftPointer, toSort.get(rightPointer));
			toSort.set(rightPointer, tmp);
			
		}
		
		toSort.set(right, toSort.get(leftPointer));
		toSort.set(leftPointer, pivot);
		
		return leftPointer;
		
		
	}
	
	/**
	 * Method for selecting a pivot based on the PIVOT_CHOICE constant above.
	 * @param toSort - The ArrayList to be sorted
	 * @param cmp - The comparator for the sort
	 * @param left - The index of the left element (inclusive) of subbarray to be sorted
	 * @param right - The index of the right element (inclusive) of the subarray to be sorted
	 * @return - The index of the pivot choice.
	 */
	private static <T> int getPivot(ArrayList<T> toSort, Comparator<? super T> cmp, int left, int right){
		switch(PIVOT_CHOICE){
			case MIDDLE_ELEMENT:
				return (left + right)/2;
			case RANDOM_PIVOT:
				return randomPivot(left, right);
			default:
			case MEDIAN_OF_THREE:
				return medianOfThree(toSort, cmp, left, right);
		}
	}
	
	/**
	 * Method for determining the index of the pivot for quicksort based on the median of three method.
	 * @param toSort - The ArrayList to be sorted
	 * @param cmp - The comparator for the sort
	 * @param left - The index of the left element (inclusive) of subbarray to be sorted
	 * @param right - The index of the right element (inclusive) of the subarray to be sorted
	 * @return - The index of the pivot choice.
	 */
	private static <T> int medianOfThree(ArrayList<T> toSort, Comparator<? super T> cmp, int left, int right){
		T leftValue = toSort.get(left);
		T rightValue = toSort.get(right);
		T midValue = toSort.get((left + right)/2);
		
		if((cmp.compare(leftValue, midValue) < 0 && cmp.compare(midValue, rightValue) < 0)||
				(cmp.compare(rightValue, midValue) < 0 && cmp.compare(midValue, leftValue) < 0)){
			return (left + right)/2;
		}
		
		if((cmp.compare(midValue, leftValue) < 0 && cmp.compare(leftValue, rightValue) < 0)||
				(cmp.compare(rightValue, leftValue) < 0 && cmp.compare(leftValue, midValue) < 0)){
			return left;
		}
		
		return right;
	}
	
	/**
	 * Method for determining the index of a pivot randomly
	 * @param left - The left bound (inclusive) of the subarray
	 * @param right - The right bound (inclusie) of the subarray.
	 * @return The index of the pivot.
	 */
	private static int randomPivot(int left, int right){
		return rng.nextInt(right - left + 1) + left;
	}
	
	
}









