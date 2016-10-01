package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Noah Goetz
 * @authorUID u1046618
 * @partner Zhangtuoming Zhao
 * @partnerUID u0847610
 */

public class SortUtil {
	/**
	 * For the mergesort algorithm, see the class notes and/or the textbook.
	 * There is pseudo code in the slides. Your mergesort implementation must
	 * switch over to insertion sort when the size of the sublist to be sorted
	 * meets a certain threshold (i.e., becomes small enough). Make this
	 * threshold value a private static variable that you can easily change. You
	 * will perform experiments to determine which threshold value works best
	 * (see the Analysis Document).
	 * 
	 * @param <List>
	 * 
	 */

	// default threshold is 10.
	private static int threshold = 10;
	
	/**
	 * 
	 * @param array input ArrayList
	 * 
	 * @param cmp comparator that is used
	 */
	public static <T> void mergesort(ArrayList<T> array, Comparator<? super T> cmp){

		if (array.size() <= 1 || array == null)
			return;

		if (array.size() <= threshold)
			insertionSort(array,cmp);

		else
			mergesort(array, 0, array.size() - 1,cmp);
	}

	// the insertion sort
	/**
	 * 
	 * @param arrayList input ArrayList
	 * 
	 * @param cmp comparator that is used
	 */
	private static <T> void insertionSort(
			ArrayList<T> arrayList, Comparator<? super T> cmp) {
		int size = arrayList.size();
		for (int i = 1; i < size; i++) {
			T currentData = arrayList.get(i);
			int temp = i;
			while (temp > 0 && cmp.compare(arrayList.get(temp - 1), currentData) > 0) {
				arrayList.set(temp, arrayList.get(temp - 1));
				temp--;
			}
			arrayList.set(temp, currentData);
		}
	}

	// use the binary search to sort the high and low
	/**
	 * 
	 * @param array input ArrayList
	 * @param low 
	 * @param high 
	 * @param cmp
	 */
	private static <T> void mergesort(
			ArrayList<T> array, int low, int high, Comparator<? super T> cmp) {

		if (low < high) {
			int middle = (low + high) / 2;
			mergesort(array, low, middle, cmp);
			mergesort(array, middle + 1, high, cmp);
			merge(array, low, middle + 1, high,cmp);
		}
	}

	// this is the merge sort code
	/**
	 * 
	 * @param array input ArrayList
	 * 
	 * @param leftPos left position index
	 * @param rightPos right position index
	 * @param rightEnd right end position index
	 * @param cmp
	 */
	private static <T> void merge(
			ArrayList<T> array, int leftPos, int rightPos, int rightEnd,Comparator<? super T> cmp) {
		int leftEnd = rightPos - 1;
		
		
		int tmpPos = leftPos;
		
		int numElements = rightEnd - leftPos + 1;
		
		//temp arrayList to hold the array during the sorting
		ArrayList<T> tmpArray = new ArrayList<T>(array);
		
		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (cmp.compare(array.get(leftPos),array.get(rightPos)) <= 0)
				tmpArray.set(tmpPos++, array.get(leftPos++));
			else
				tmpArray.set(tmpPos++, array.get(rightPos++));
		while (leftPos <= leftEnd)
			tmpArray.set(tmpPos++, array.get(leftPos++));
		while (rightPos <= rightEnd)
			tmpArray.set(tmpPos++, array.get(rightPos++));

		for (int i = 0; i < numElements; i++, rightEnd--)
			array.set(rightEnd, tmpArray.get(rightEnd));
	}

	/**
	 * For the quicksort algorithm, see the class notes and/or the textbook.
	 * There is pseudo code in the slides. You must implement three different
	 * strategies for determining the pivot. Your quicksort implementation
	 * should be able to easily switch among these strategies. (Consider using a
	 * few private helper methods for your different pivot selection
	 * strategies.) You will perform experiments to determine which pivot
	 * strategy works best (see the Analysis Document). Your quicksort may also
	 * switch to insertion sort on some small threshold if you wish.
	 */
	
	public static <T> void quicksort(
			ArrayList<T> elementsList,Comparator<? super T> cmp) {
		quick_sort_1(elementsList, 0, elementsList.size() - 1,
				elementsList.get(5),cmp);
	}

	//this is the quickSort method
	/**
	 * 
	 * @param arrayList
	 * @param leftIndex
	 * @param rightIndex
	 * @param oldPivot
	 * @param cmp
	 */
	protected static <T> void quick_sort_1(
			ArrayList<T> arrayList, int leftIndex, int rightIndex, T oldPivot,Comparator<? super T> cmp) {

		// doing the quicksort
		if (leftIndex < rightIndex) {

			// sorting left, middle, right
			int mid = (leftIndex + rightIndex) / 2;
			if (cmp.compare(arrayList.get(mid),arrayList.get(leftIndex)) < 0)
				swapReferences(arrayList, mid, leftIndex, cmp);
			if (cmp.compare(arrayList.get(rightIndex),arrayList.get(leftIndex)) < 0)
				swapReferences(arrayList, rightIndex, leftIndex, cmp);
			if (cmp.compare(arrayList.get(rightIndex),arrayList.get(mid)) < 0)
				swapReferences(arrayList, rightIndex, mid, cmp);

			// placing pivot at position rightIndex-1
			swapReferences(arrayList, rightIndex - 1, mid,cmp);
			T pivotItem = arrayList.get(rightIndex - 1);

			// doing partitioning
			int i, j;
			for (i = leftIndex, j = rightIndex - 1; i < j;) {
				while (cmp.compare(arrayList.get(++i),pivotItem) < 0)
					; // (intended to be empty)
				while (cmp.compare(pivotItem,arrayList.get(--j)) < 0)
					; // (intended to be empty)
				if (i >= j)
					break;
				swapReferences(arrayList, i, j, cmp);
			}

			// restoring pivot
			swapReferences(arrayList, i, rightIndex - 1, cmp);

			// sorting small elements
			quick_sort_1(arrayList, leftIndex, i - 1, pivotItem, cmp);
			
			// sorting large elements
			quick_sort_1(arrayList, i + 1, rightIndex, arrayList.get(0), cmp);
		}
	}
	/**
	 * 
	 * @param arr
	 * @param index1
	 * @param index2
	 * @param cmp
	 */
	private static <T> void swapReferences(
			ArrayList<T> arr, int index1, int index2,Comparator<? super T> cmp ) {
		T temp = arr.get(index1);
		arr.set(index1, arr.get(index2));
		arr.set(index2, temp);
	}
	/**
	 * 
	 * @param arrayList
	 * @return
	 */
	protected static <T> T pivotItemSelection_1(
			ArrayList<T> arrayList) {
		return arrayList.get(0);
	}
	/**
	 * 
	 * @param arrayList
	 * @return
	 */
	protected static <T> T pivotItemSelection_2(
			ArrayList<T> arrayList) {
		return arrayList.get(arrayList.size() / 2);
	}
	/**
	 * 
	 * @param arrayList
	 * @param cmp
	 * @return
	 */
	protected static <T> T pivotItemSelection_3(
			ArrayList<T> arrayList, Comparator<? super T> cmp) {
		T middle = arrayList.get(arrayList.size() / 2);
		T first = arrayList.get(0);
		T last = arrayList.get(arrayList.size() - 1);
		if (cmp.compare(middle,first) >= 0 && cmp.compare(last,middle) >= 0)
			return middle;
		if (cmp.compare(middle,last) >= 0 && cmp.compare(first,middle) >= 0)
			return middle;
		if (cmp.compare(first,last) >= 0 && cmp.compare(middle,first) >= 0)
			return first;
		if (cmp.compare(first,middle) >= 0 && cmp.compare(last,first) >= 0)
			return first;
		if (cmp.compare(last,first) >= 0 && cmp.compare(middle,last) >= 0)
			return last;
		if (cmp.compare(last,middle) >= 0 && cmp.compare(first,last) >= 0)
			return last;
		else
			return middle;
	}
	
	//Base case 
	
	/**
	 * 
	 * @param size the size of arrayList
	 * 
	 * @return making a increasing sequence of integers hold by an arrayList
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		
		//add one by one
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		for (int i = 1; i < size + 1; i++) {
			arrList.add(i);
		}
		return arrList;

	}
	
	//the average case
	/**
	 * 
	 * @param size size of the arrayList
	 * 
	 * @return //By using the shuffle to make the number disordered. And it will make a random arrayList
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		
		
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		for (int i = 1; i < size + 1; i++) {
			arrList.add(i);
		}
		
		Collections.shuffle(arrList);
		
		return arrList;
	}
	
	//the worst case
	/**
	 * 
	 * @param size
	 *            the size of the returned ArrayList
	 *            
	 * @return An ArrayList of integers in descending order
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {
		ArrayList<Integer> arrList = new ArrayList<Integer>();
		for (int i = size; i > 0; i--) {
			arrList.add(i);
		}
		return arrList;
	}

	public static int thresHoldChange(int num) {
		return threshold = num;
	}

}
