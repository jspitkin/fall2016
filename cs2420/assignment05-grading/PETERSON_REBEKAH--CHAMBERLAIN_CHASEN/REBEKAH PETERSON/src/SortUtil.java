package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Class for performing sorting Array Lists experiments.
 * 
 * @author Chasen Chamberlain u0583257 & Rebekah Peterson u0871657
 */
public class SortUtil {

    private static int threshold = 50;
    private static int pivotSelection = 2; // 0 is end index, 1 is middle index,
					   // 2 is median of three
    private static long seed = 4444444444444L;

    /**
     * 
     * This driver method performs a mergesort on the generic ArrayList given as
     * input.
     * 
     * @param list
     *            -- the list to be sorted
     * @param comparator
     *            -- the order of comparing
     */
    public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator) {
	if (list == null || list.isEmpty()) {
	    return;
	}
	@SuppressWarnings("unchecked")
	T[] temp = (T[]) new Object[list.size()];
	mergesortRecursive(list, temp, 0, list.size() - 1, comparator);
    }

    /**
     * The recursive part of mergesort.
     * 
     * @param ourList
     *            -- the list to be sorted
     * @param left
     *            -- the left index of the section to be mergesorted
     * @param right
     *            -- the right index of the section to be mergesorted
     */
    private static <T> void mergesortRecursive(ArrayList<T> ourList, T[] temp, int left, int right,
	    Comparator<? super T> cmp) {
	if (right - left <= threshold) {
	    insertionSort(ourList, left, right, cmp);
	    return;
	}
	int mid = (left + right) / 2;
	mergesortRecursive(ourList, temp, left, mid, cmp);
	mergesortRecursive(ourList, temp, mid + 1, right, cmp);
	merge(ourList, temp, left, mid + 1, right, cmp);
    }

    /**
     * The merge of mergesort.
     * 
     * @param ourList
     *            -- the list to be sorted
     * @param temp
     *            -- the temp array used in merging
     * @param start
     *            -- the left index of the left subarray
     * @param mid
     *            -- the left index of the right subarray
     * @param end
     *            -- the last index of the section to be mergesorted
     */
    private static <T> void merge(ArrayList<T> ourList, T[] temp, int start, int mid, int end,
	    Comparator<? super T> cmp) {
	int leftPointer = start;
	int rightPointer = mid;
	int tempIndexCounter = 0;

	// compare smallest of each subarray and copy smallest to temparray
	while (leftPointer < mid && rightPointer <= end) {
	    if (cmp.compare(ourList.get(leftPointer), ourList.get(rightPointer)) < 0) {
		temp[tempIndexCounter++] = ourList.get(leftPointer++);
	    } else {
		temp[tempIndexCounter++] = ourList.get(rightPointer++);
	    }
	}

	// copy leftover elements to temp
	while (leftPointer < mid) {
	    temp[tempIndexCounter++] = ourList.get(leftPointer++);
	}
	while (rightPointer <= end) {
	    temp[tempIndexCounter++] = ourList.get(rightPointer++);
	}

	// copy sorted temp back to original list
	tempIndexCounter = 0;
	for (int i = start; i < end + 1; i++, tempIndexCounter++) {
	    ourList.set(i, temp[tempIndexCounter]);
	}
    }

    /**
     * Insertion sorts the section, called at a certain threshold.
     * 
     * @param ourList
     *            -- the list to be sorted
     * @param left
     *            -- the left index of the section to be insertion sorted
     * @param right
     *            -- the right index of the section to be insertion sorted
     * @param cmp
     *            -- the order of comparing
     */
    private static <T> void insertionSort(ArrayList<T> ourList, int left, int right, Comparator<? super T> cmp) {
	for (int iteration = left + 1; iteration < right + 1; iteration++) {
	    T item = ourList.get(iteration); // item to be inserted.

	    int indexOfItemToBeInserted = iteration;
	    while (indexOfItemToBeInserted > left && cmp.compare(ourList.get(indexOfItemToBeInserted - 1), item) > 0) {
		ourList.set(indexOfItemToBeInserted, ourList.get(indexOfItemToBeInserted - 1));
		indexOfItemToBeInserted--;
	    }
	    ourList.set(indexOfItemToBeInserted, item);
	}
    }

    /**
     * This driver method performs a quicksort on the generic ArrayList given as
     * input.
     * 
     * @param list
     *            -- the list to be sorted
     * @param comparator
     *            -- the order of comparing
     */
    public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator) {
	if (list == null || list.isEmpty()) {
	    return;
	}
	quicksortRecursive(list, 0, list.size() - 1, comparator);
    }

    /**
     * The recursive part of quicksort.
     * 
     * @param ourList
     *            -- the list to be sorted
     * @param left
     *            -- the left index of the section to be quicksorted
     * @param right
     *            -- the right index of the section to be quicksorted
     * @param cmp
     *            -- the order of comparing
     */
    private static <T> void quicksortRecursive(ArrayList<T> ourList, int left, int right, Comparator<? super T> cmp) {
	if (left >= right) {
	    return;
	}
	int pivot_Index = partition(ourList, left, right, cmp);
	quicksortRecursive(ourList, left, pivot_Index - 1, cmp);
	quicksortRecursive(ourList, pivot_Index + 1, right, cmp);
    }

    /**
     * This method picks a pivot, and then partitions by putting all items lower
     * than the pivot on the left and all items higher than the pivot on the
     * right.
     * 
     * @param ourList
     *            -- the list to be sorted
     * @param left
     *            -- the left index of the subarray
     * @param right
     *            -- the right index of the subarray
     * @param cmp
     *            -- the order of comparing
     * @return the index of where the pivot ended up in the list
     */
    private static <T> int partition(ArrayList<T> ourList, int left, int right, Comparator<? super T> cmp) {

	int pivot = 0;
	switch (pivotSelection) {
	// pivot selected at the end of the list
	case 0:
	    pivot = right;
	    break;
	// pivot selected in the middle of the list
	case 1:
	    pivot = (left + right) / 2;
	    break;
	// pivot selected by median of three.
	case 2:
	    if (ourList.size() >= 3) {
		pivot = medianOfThree(ourList, left, right, cmp);
	    }
	    // else pivot can stay at 0
	    break;
	}

	// swap the pivot to the end;
	swap(ourList, pivot, right);

	int L = left, R = right - 1;
	while (L <= R) {
	    if (cmp.compare(ourList.get(L), ourList.get(right)) < 0) {
		L++;
		continue;
	    }
	    if (cmp.compare(ourList.get(R), ourList.get(right)) > 0) {
		R--;
		continue;
	    }

	    swap(ourList, L, R);
	    L++;
	    R--;
	}

	// put pivot back in place once all sorting has finished.
	swap(ourList, L, right);
	pivot = L;

	return pivot;
    }

    /**
     * Swaps two elements in our list.
     * 
     * @param ourList
     *            -- the list to be sorted
     * @param left
     *            -- index of one element
     * @param right
     *            -- index of the other element
     */
    private static <T> void swap(ArrayList<T> ourList, int left, int right) {
	T temp = ourList.get(left);
	ourList.set(left, ourList.get(right));
	ourList.set(right, temp);
    }

    /**
     * Picks a new pivot by selecting the median of three.
     * 
     * @param ourList
     *            -- the list to be sorted
     * @param left
     *            -- the left index of the subarray
     * @param right
     *            -- the right index of the subarray
     * @param cmp
     *            -- the order of comparing
     * @return index of the median of three
     */
    private static <T> int medianOfThree(ArrayList<T> ourList, int left, int right, Comparator<? super T> cmp) {
	int pivot = 0;
	T first = ourList.get(left);
	T middle = ourList.get((left + right) / 2);
	T last = ourList.get(right);

	// add three items: first, middle, and last
	ArrayList<T> median = new ArrayList<T>();
	median.add(first);
	median.add(middle);
	median.add(last);

	// sort the three items
	insertionSort(median, 0, median.size() - 1, cmp);

	// find out which item is at the center, set the pivot
	if (cmp.compare(median.get(1), first) == 0) {
	    pivot = left;
	} else if (cmp.compare(median.get(1), last) == 0) {
	    pivot = right;
	} else {
	    pivot = (left + right) / 2;
	}
	return pivot;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in
     * ascending order.
     * 
     * @param size
     *            -- the size of the list
     * @return an ArrayList of integers 1 to size in ascending order
     */
    public static ArrayList<Integer> generateBestCase(int size) {
	ArrayList<Integer> bestCase = new ArrayList<Integer>();
	for (int index = 1; index <= size; index++) {
	    bestCase.add(index);
	}
	return bestCase;
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in
     * permuted order (i,e., randomly ordered).
     * 
     * @param size
     *            -- the size of the list
     * @return an ArrayList of integers 1 to size in permuted order (i,e.,
     *         randomly ordered)
     */
    public static ArrayList<Integer> generateAverageCase(int size) {
	ArrayList<Integer> averageCase = new ArrayList<Integer>();
	for (int index = 1; index <= size; index++) {
	    averageCase.add(index);
	}
	shuffle(averageCase);
	return averageCase;
    }

    /**
     * A helper method that shuffles a list.
     * 
     * @param listToBeShuffled
     *            - list to be shuffled
     */
    private static void shuffle(ArrayList<Integer> listToBeShuffled) {
	Random random = new Random(seed);
	for (int i = 0; i < listToBeShuffled.size(); i++) {
	    swap(listToBeShuffled, i, random.nextInt(listToBeShuffled.size() - 1));
	}
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in
     * descending order.
     * 
     * @param size
     *            -- the size of the list
     * @return an ArrayList of integers 1 to size in descending order
     */
    public static ArrayList<Integer> generateWorstCase(int size) {
	ArrayList<Integer> worstCase = new ArrayList<Integer>();
	for (int index = size; index > 0; index--) {
	    worstCase.add(index);
	}
	return worstCase;

    }

    /**
     * Used to print out subarray, from start to end index inclusive.
     * 
     * @param list
     *            -- list to be sorted
     * @param start
     *            -- left index of subarray
     * @param end
     *            -- right index of subarray
     * @return user-friendly String version of this subarray
     */
    @SuppressWarnings("unused")
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
