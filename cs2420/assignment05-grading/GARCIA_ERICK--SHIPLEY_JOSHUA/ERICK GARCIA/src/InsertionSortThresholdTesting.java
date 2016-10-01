package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


/**
 * 
 * 	@author Erick Daniel Garcia
 *	@author Joshua Shipley
 */
public class InsertionSortThresholdTesting {
	
	private static final int REP_COUNT = 1000;
	private static final int TOP_THRESHOLD = 5000;
	private static final int SET_SIZE = 10000;
	Random rand = new Random(123456789);
		
	public static void main(String[] args) {
		testInsertionThreshold();
	}
	
	private static void testInsertionThreshold(){
		//Build set of arrays of length size
		ArrayList<ArrayList<Integer>> listSet = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> mainList = SortUtil.generateAverageCase(SET_SIZE);
		
		for(int i = 0; i < REP_COUNT; i++){
			listSet.add((ArrayList<Integer>)mainList.clone());
		}
		
		System.out.println("Threshold" + "\t" + "Time");
		
		//Change threshold after each test set
		for(int threshold = 0; threshold < TOP_THRESHOLD; threshold *= 2){
			
			//In each list, time how long it takes to sort at the specific threshold
			long startTime, endTime;
			ArrayList<Long> timings = new ArrayList<Long>();
			
			for(ArrayList<Integer> list : listSet){
				startTime = System.nanoTime();
				mergesort(list, null, threshold);
				endTime = System.nanoTime();
				timings.add(endTime - startTime);
			}
			
			//Print out the resulting average time
			long sum = 0;
			for(Long time : timings){
				sum += time;
			}
			
			System.out.println(threshold + "\t" + (sum/REP_COUNT));
			
			listSet.clear();
			for(int i = 0; i < REP_COUNT; i++){
				listSet.add((ArrayList<Integer>)mainList.clone());
			}
			
			if(threshold == 0){
				threshold++;
			}
		}
	}
	/**
	 * Sorts the given ArrayList with the provided comparator with a merge sort algorithm
	 * @param list - The list to be sorted
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable
	 * @param threshold - threshold to be passed to the insertionSort
	 */
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator, int threshold){
		if(list == null){
			return;
		}
		
		mergeSort(list, comparator, 0, list.size() - 1, new ArrayList<T>(), threshold);
	}
	/**
	 * A merge sort algorithm that sorts the given array using the provided comparator by "splitting" list into sub-arrays using the left and
	 * right bounds, then combines them into temp before copying them back into the array in order.
	 * @param list - The list to be sorted
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable
	 * @param leftBound - The index indicating the lower bound of the sub - portion of the array
	 * @param rightBound - The index indicating the upper bound of the sub - portion of the array
	 * @param temp - The temporary array that holds the objects while they are being sorted out of list
	 * @param insertionThreshold - threshold to be passed to the insertionSort
	 */
	private static <T> void mergeSort(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound, ArrayList<T> temp, int insertionThreshold){
		if(leftBound >= rightBound){
			return;
		}
		
		if(rightBound - leftBound <= insertionThreshold){
			
			insertionSort(list, null, leftBound, rightBound);
			return;
		}
		
		int mid = (leftBound + rightBound) / 2;
		mergeSort(list, comparator, leftBound, mid, temp, insertionThreshold);
		mergeSort(list, comparator, mid + 1, rightBound, temp, insertionThreshold);
		combine(list, leftBound, mid + 1, rightBound, comparator, temp);
	}
	
	/**
	 * Goes through the list objects, which is split up into two sub arrays as denoted by the start, mid, and end indexes, and combines them
	 * into temp in order as denoted by the given comparator. The contents of temp is then copied back into list in order.
	 * @param list - The list that has been split into two sub arrays and need to be combined in order
	 * @param start - The starting index of the first sub array
	 * @param mid - The ending index of the first sub array and one less than the start of the second sub array
	 * @param end - The ending index of the second sub array
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable
	 * @param temp  -The temporary array that holds the objects while they are being sorted out of list
	 */
	private static <T> void combine(ArrayList<T> list, int start, int mid, int end, Comparator<? super T> comparator, ArrayList<T> temp) {
		int leftIndex = start, rightIndex = mid;
		
		while(leftIndex < mid && rightIndex <= end){
			if(newCompare(list.get(leftIndex), list.get(rightIndex), comparator) < 0){
				temp.add(list.get(leftIndex));
				leftIndex++;
			}
			else{
				temp.add(list.get(rightIndex));
				rightIndex++;
			}
		}
		
		while(leftIndex < mid || rightIndex <= end){
			if(leftIndex < mid){
				temp.add(list.get(leftIndex));
				leftIndex++;
			}
			if(rightIndex <= end){
				temp.add(list.get(rightIndex));
				rightIndex++;
			}
		}
		
		for(int i = 0; i < end - start + 1; i++){
			list.set(i + start, temp.get(i));
		}
		
		temp.clear();
	}
	
	/**
	 * Compares the two given elements with the given Comparator.
	 * If the Comparator is null, assumes the two elements to extend Comparable.
	 * @param element1 - The first element in the comparison.
	 * @param element2 - The second element that is decided to be greater than, less than, or equal to element1.
	 * @param comparator - The Comparator that dictates how the two elements are to be sorted. Passing null will dictate natural ordering.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <T> int newCompare(T element1 ,T element2, Comparator<? super T> comparator){
		
		if(comparator == null){
			
			return ((Comparable) element1).compareTo((Comparable) element2);
		}	
		else{
			
			return comparator.compare(element1, element2);
		}
		
	}
	
	/**
	 * An insertion sort algorithm that will rearrange the given array using the provided comparator.
	 * If the comparator is null, assumes that the object is naturally Comparable.
	 * @param sortArray - The array that is to be sorted
	 * @param comparator - The comparator that dictates how the array is to be sorted. Passing null will dictate natural ordering.
	 */
	public static <T> void insertionSort(ArrayList<? super T> sortArray, Comparator<? super T> comparator, int leftBound, int rightBound){
		
		for(int unsortedStart = leftBound + 1; unsortedStart < rightBound; unsortedStart++){
			T temp = (T) sortArray.get(unsortedStart);
			
			int workBack;
			for(workBack = unsortedStart - 1; workBack >= 0 && newCompare(temp, (T)sortArray.get(workBack), comparator) < 0; workBack--){
				sortArray.set(workBack + 1, (T) sortArray.get(workBack));
			}
			
			sortArray.set(workBack + 1, temp);
		}
		
	}
}
