package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Ching-Yuan Chang u0914005, Min Kim u1054673
 */

public class SortUtil {

	private static int threshold = 80;
	// private static int getPivot = 0;
	public static Random rand = new Random();

	/**
	 * Performs a merge sort on the generic ArrayList given as input
	 * 
	 * @param array
	 * @param cmp
	 */
	public static <T> void mergesort(ArrayList<T> array, Comparator<? super T> cmp) {
		if (array != null && cmp != null) {
			mergeSort(array, 0, array.size() - 1, cmp);
		}
	}

	/**
	 * Sorts an array using a recursive mergesort, then merging
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @param cmp
	 */
	private static <T> void mergeSort(ArrayList<T> array, int left, int right, Comparator<? super T> cmp) {
		if (right - left <= threshold) {
			insertionsSort(array, left, right, cmp);
			return;
		}
		int mid = (left + right) / 2;
		mergeSort(array, left, mid, cmp);
		mergeSort(array, mid + 1, right, cmp);
		merge(array, left, mid, right, cmp);

	}

	/**
	 * Sorts an array using an insertion sort
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @param cmp
	 */
	private static <T> void insertionsSort(ArrayList<T> array, int left, int right, Comparator<? super T> cmp) {
		for (int i = left; i <= right; i++) {
			T index = array.get(i);
			int j = i;
			while (j > left && j > 0) {
				T c1 = array.get(j - 1);
				if (cmp.compare(c1, index) > 0) {
					T temp = array.get(j);
					array.set(j, array.get(j - 1));
					array.set(j - 1, temp);
				}
				j--;
			}
		}

	}

	/**
	 * Merges the two halves of a merge sort together
	 * 
	 * @param array
	 * @param left
	 * @param mid
	 * @param right
	 * @param cmp
	 */
	private static <T> void merge(ArrayList<T> array, int left, int mid, int right, Comparator<? super T> cmp) {
		ArrayList<T> tmp = new ArrayList<T>();
		int low = left;
		int high = mid + 1;
		while (low <= mid && high <= right) {
			if (cmp.compare(array.get(low), array.get(high)) == 1) {
				tmp.add(array.get(high));
				high++;
			} else {
				tmp.add(array.get(low));
				low++;
			}
		}

		while (low <= mid) {
			tmp.add(array.get(low));
			low++;
		}
		while (high <= right) {
			tmp.add(array.get(high));
			high++;
		}
		int index = left;
		for (int i = 0; i < tmp.size(); i++) {
			array.set(index, tmp.get(i));
			index++;
		}

	}

	/**
	 * Find pivot of a quicksort using the middle of the array
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @return
	 */
	public static <T> int pivotMiddle(ArrayList<T> array, int left, int right) {
		int pivot = right + left;
		pivot /= 2;

		return pivot;
	}

	/**
	 * Find pivot of a quicksort using a random section of the array
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @return
	 */
	public static <T> int pivotRandom(ArrayList<T> array, int left, int right) {
		int pivot = rand.nextInt(right - left) + left;
		return pivot;
	}

	/**
	 * Find pivot in a quicksort by finding the median of three values
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @return
	 */
	public static <T> int pivotMiddleOfThree(ArrayList<T> array, int left, int right, Comparator<? super T> compare) {
		int middle = (left + right) / 2;

		if (compare.compare(array.get(right), array.get(middle)) < 0) {
			swap(array, right, middle);
		}
		if (compare.compare(array.get(middle), array.get(left)) < 0) {
			swap(array, middle, left);
		}
		if (compare.compare(array.get(right), array.get(left)) < 0) {
			swap(array, right, left);
		}

		return middle;
	}

	/**
	 * Swap two elements of an array list
	 * 
	 * @param array
	 * @param left
	 * @param right
	 */
	public static <T> void swap(ArrayList<T> array, int left, int right) {
		if (left != right) {
			T temp = array.get(left);
			array.set(left, array.get(right));
			array.set(right, temp);
		}
	}

	/**
	 * This method performs a quick sort on the generic ArrayList given as
	 * input. Base case for the quick sort
	 * 
	 * @param array
	 * @param cmp
	 */
	public static <T> void quicksort(ArrayList<T> array, Comparator<? super T> compare) {
		// Ensure array size is greater than 1; otherwise, technically already
		// sorted & sorting is unnecessary
		if (array.size() > 1 && compare != null) {
			quicksortRecursive(array, 0, array.size() - 1, compare);
		}

	}

	/**
	 * Partitions the array for the quicksort & finds a new pivot
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @return
	 */
	public static <T> int partition(ArrayList<T> array, int left, int right, Comparator<? super T> compare) {
		// int pivot = 0;
		//
		// if (getPivot == 0) {
		// pivot = pivotRandom(array, left, right); // TODO change as necessary
		// } else if (getPivot == 1) {
		//
		// pivot = pivotMiddleOfThree(array, left, right, compare); // TODO
		// change as
		// // necessary
		//
		// } else {
		// pivot = pivotMiddle(array, left, right); // TODO change as necessary
		// }

		int pivot = pivotMiddle(array, left, right); // TODO change as necessary
		T pivotData = array.get(pivot);
		swap(array, pivot, right);
		pivot = right;

		int subLeft = left;
		int subRight = right - 1;

		while (subLeft < subRight) {
			while (subLeft <= subRight) {
				if (compare.compare(array.get(subLeft), pivotData) <= 0) {
					if (subLeft <= right) {
						subLeft++;
					}

				} else {
					break;
				}
			}
			while (subLeft < subRight) {
				if (compare.compare(array.get(subRight), pivotData) >= 0) {
					if (subRight >= left) {
						subRight--;
					}
				} else {
					break;
				}
			}

			if (subLeft < subRight) // check that ordering hasn't changed during
									// the if statements
			{
				swap(array, subLeft, subRight); // partners found; swap them
			}
		}

		// If subLeft is the same as subRight, swap the pivot with either
		if (subLeft == subRight) {
			swap(array, subLeft, pivot);
			pivot = subLeft;
			subRight++;
		}

		return pivot;
	}

	/**
	 * Recursive helper function for the quicksort
	 * 
	 * @param array
	 * @param left
	 * @param right
	 */
	public static <T> void quicksortRecursive(ArrayList<T> array, int left, int right, Comparator<? super T> compare) {
		if (left >= right) {
			return;
		} else if (right - left <= threshold) {
			insertionsSort(array, left, right, compare);
		} else {
			int pivot = partition(array, left, right, compare);
			quicksortRecursive(array, left, pivot - 1, compare);
			quicksortRecursive(array, pivot + 1, right, compare);
		}

	}

	/**
	 * Generates a best case array for testing (sorted order)
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int num = 1; num <= size; num++) {
			list.add(num);
		}
		return list;
	}

	/**
	 * Generates an average case array for testing (permuted order)
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateAverageCase(int size) {
		ArrayList<Integer> list = new ArrayList<>();
		// for (int num = 1; num <= size; num++) {
		// list.add(num);
		// }

		while (list.size() < size) {
			int randNum = rand.nextInt(size) + 1;

			if (!list.contains(randNum)) {
				list.add(randNum);
			}
		}

		// Collections.shuffle(list);
		// Collections.shuffle(list);
		return list;

	}

	/**
	 * Generates a worst case array for testing (reversed order)
	 * 
	 * @param size
	 * @return
	 */
	public static ArrayList<Integer> generateWorstCase(int size) {
		ArrayList<Integer> arrayWorst = new ArrayList<Integer>();
		int storeSize = size;
		for (int index = 0; index < size; index++) {
			arrayWorst.add(storeSize);
			storeSize--;
		}
		return arrayWorst;
	}

	public static int getThreshold(int thresh) {
		threshold = thresh;
		return thresh;
	}

	// public static void setPivot(int piv) {
	// getPivot = piv;
	// }

	/**
	 * Used to print out sub array, from start to end index inclusive.
	 * 
	 * @param list
	 * @param start
	 * @param end
	 * @return
	 */
	private static <T> String printSubArray(ArrayList<T> list, int start, int end) {
		StringBuilder sb = new StringBuilder("[");
		for (; start <= end; start++) {
			sb.append(list.get(start)).append(", ");
		}
		// removes the trailing ', ' from the string.
		if (sb.length() > 1) {
			sb.setLength(sb.length() - 2);
		}
		sb.append("]"); // close it off.
		return sb.toString();
	}

}
