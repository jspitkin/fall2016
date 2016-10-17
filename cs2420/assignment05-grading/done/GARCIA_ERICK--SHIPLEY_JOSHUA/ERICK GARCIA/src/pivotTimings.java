package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


/**
 * 
 * 	@author Erick Daniel Garcia
 *	@author Joshua Shipley
 */

public class pivotTimings {
	
	private static final int MIDDLE_OF_THREE = 1;
	private static final int RANDOM = 2;
	private static final int MIDDLE_OF_LIST = 3;
	
	private static final int REP_COUNT = 1000;
	private static final int SET_SIZE = 10000;
	
	static Random rand = new Random(123465789);
	
	public static void main(String[] args){
		
		//Build set of arrays of length size
		ArrayList<ArrayList<Integer>> listSet = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> mainList = SortUtil.generateAverageCase(SET_SIZE);
				
		for(int i = 0; i < REP_COUNT; i++){
			listSet.add((ArrayList<Integer>)mainList.clone());
		}
				
		long startTime, endTime;
		ArrayList<Long> timings = new ArrayList<Long>();
		
		for (int testType = 1; testType <= 3; testType++) {
			for (ArrayList<Integer> list : listSet) {
				startTime = System.nanoTime();
				quicksort(list, null, testType);
				endTime = System.nanoTime();
				
				timings.add(endTime - startTime);
			}
			
			long sum = 0;
			for(Long time : timings){
				sum += time;
			}
			
			System.out.println(testType + "\t" + (sum/REP_COUNT));
			
			listSet.clear();
			for(int i = 0; i < REP_COUNT; i++){
				listSet.add((ArrayList<Integer>)mainList.clone());
			}
		}
	}
	/**
	 * Sorts the given list with a quick sort algorithm using the given comparator
	 * @param list - The list to be sorted
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable
	 */
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator, int pivot){
		if(list == null){
			return;
		}
		
		quickSort(list, comparator, 0, list.size() - 1, pivot);
	}
	/**
	 * A quick sort algorithm that sorts the list using the given comparator by selecting a pivot, sorts all objects less than the pivot to the
	 * left of it, and all those greater than to the right. It then goes into the left and right portions and sorts them.
	 * @param list - The list to be sorted
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable.
	 * @param leftBound - The leftmost index of the sub array portion of list
	 * @param rightBound - The rightmost index of the sub array portion of the list
	 */
	private static <T> void quickSort(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound, int pivotType){
		if(leftBound >= rightBound){
			return;
		}
		
	;
		
		int pivotIndex  = partition(list, leftBound, rightBound, comparator, pivotType);
		
		//System.out.println(leftBound + "\t"+pivotIndex + "\t"+ rightBound);
		
		
		quickSort(list, comparator, leftBound, pivotIndex -1 , pivotType);
		quickSort(list, comparator, pivotIndex + 1, rightBound, pivotType);
	}
	/**
	 * Partitions the given sub portion of the list such that all objects less than the selected pivot are to the left and all those greater
	 * are to the right.
	 * 
	 * The pivot is selected by taking the first, last, and middle objects of the array, then finding which one serves as the medium of the three
	 * @param list - The list to be sorted
	 * @param leftBound - The leftmost index of the sub array portion of the list
	 * @param rightBound - The rightmost index of the sub array portion of the list
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable.
	 * @return - The index of the newly positioned pivot. No matter the arrangement of the other objects, the pivot is in it's permanent place
	 * once the partitioning is complete.
	 */
	private static <T> int partition(ArrayList<T> list, int leftBound, int rightBound, Comparator<? super T> comparator, int pivotType) {
		
		int pivotIndex;
		
		switch(pivotType){
		case MIDDLE_OF_THREE:
			pivotIndex = findMedium(list, leftBound, rightBound, middleIndex(leftBound, rightBound));
			break;
		case RANDOM:
			pivotIndex = rand.nextInt(rightBound-leftBound) + leftBound;
			break;
		case MIDDLE_OF_LIST:
			pivotIndex = middleIndex(leftBound, rightBound);
			break;
		default:
			pivotIndex = leftBound;
			break;
		}

		swap(list, pivotIndex, rightBound);
		
		 int leftIndex = leftBound, rightIndex = rightBound - 1;
		 
		 while(leftIndex < rightIndex){
			 while((newCompare(list.get(leftIndex), list.get(rightBound), comparator) < 0) &&  leftIndex <  list.size()){
				 leftIndex++;
			 }
			 	 
			 while(newCompare(list.get(rightIndex), list.get(rightBound), comparator) > 0 &&  rightIndex> 0 ){
				 rightIndex--;
			 }
			 
			
			 if(leftIndex <= rightIndex){
				 
				 swap(list, leftIndex, rightIndex);
			 
			 }
		 }
		 
		 swap(list, leftIndex, rightBound);
		 
		return pivotIndex;
	}
	/**
	 * Calculates which object is the medium of the three contained within list
	 * @param list - The list containing the objects to be compared
	 * @param left - The index of first object to be compared
	 * @param right - The index of second object to be compared
	 * @param middle - The index of the third object to be compared
	 * @return - The index of the medium value of the three objects within list
	 */
	private static <T> int findMedium(ArrayList<T> list, int left, int right, int middle) {
		if(list.size() < 2){
			return 1;
		}
		
		T first = list.get(left);
		T second = list.get(middle);
		T third = list.get(right);
		
		T[] set = (T[]) new Object[]{list.get(left), list.get(middle), list.get(right)};
		
		insertionSort(set, null);
		
		if(set[1] == first){
			return left;
		}
		else if(set[1] == second){
			return middle;
		}
		else return right;
	}
	
	/**
	 * An insertion sort algorithm that will rearrange the given array using the provided comparator.
	 * If the comparator is null, assumes that the object is naturally Comparable.
	 * @param sortArray - The array that is to be sorted
	 * @param comparator - The comparator that dictates how the array is to be sorted. Passing null will dictate natural ordering.
	 */
	public static <T> void insertionSort(T[] sortArray, Comparator<? super T> comparator){
		
		for(int unsortedStart = 1; unsortedStart < sortArray.length; unsortedStart++){
			T temp = sortArray[unsortedStart];
			
			int workBack;
			for(workBack = unsortedStart - 1; workBack >= 0 && newCompare(temp, sortArray[workBack], comparator) < 0; workBack--){
				sortArray[workBack + 1] = sortArray[workBack];
			}
			
			sortArray[workBack + 1] = temp;
		}
		
	}
	/**
	 * Swaps two elements in an ArrayList
	 * @param list - The list containing the two elements that are to be swapped
	 * @param index1 - The index of the first element to be swapped
	 * @param index2 - The index of the second element to be swapped
	 */
	private static <T> void swap(ArrayList<T> list, int index1, int index2){
		T temp = list.get(index1);
		list.set(index1, list.get(index2));
		list.set(index2, temp);
	}
	
	/**
	 * Returns the middle value given a left and right bound
	 * @param leftBound - The leftmost bound
	 * @param rightBound - The rightmost bound
	 * @return - The value exactly in the middle of the two values.
	 */
	
	private static int middleIndex(int leftBound, int rightBound){
		return ((rightBound - leftBound) / 2) + leftBound;
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
}