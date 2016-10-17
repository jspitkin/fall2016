package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;


/**
 * 
 * 	@author Erick Daniel Garcia
 *	@author Joshua Shipley
 */
public class SortUtil {

	private static Random rand = new Random(123465789);
	private static int INSERTION_THRESHOLD = 8;
	
	/**
	 * Sorts the given ArrayList with the provided comparator with a merge sort algorithm
	 * @param list - The list to be sorted
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable
	 */
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator){
		if(list == null){
			return;
		}
		
		mergeSort(list, comparator, 0, list.size() - 1, new ArrayList<T>());
	}
	
	/**
	 * A merge sort algorithm that sorts the given array using the provided comparator by "splitting" list into sub-arrays using the left and
	 * right bounds, then combines them into temp before copying them back into the array in order.
	 * @param list - The list to be sorted
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable
	 * @param leftBound - The index indicating the lower bound of the sub - portion of the array
	 * @param rightBound - The index indicating the upper bound of the sub - portion of the array
	 * @param temp - The temporary array that holds the objects while they are being sorted out of list
	 */
	private static <T> void mergeSort(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound, ArrayList<T> temp){
		if(leftBound >= rightBound){
			return;
		}
		
		if(leftBound - rightBound <= INSERTION_THRESHOLD){
			insertionSort(list, null, leftBound, rightBound);
			return;
		}
		
		int mid = (leftBound + rightBound) / 2;
		mergeSort(list, comparator, leftBound, mid, temp);
		mergeSort(list, comparator, mid + 1, rightBound, temp);
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
	 * Sorts the given list with a quick sort algorithm using the given comparator
	 * @param list - The list to be sorted
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable
	 */
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator){
		if(list == null){
			return;
		}
		
		quickSort(list, comparator, 0, list.size() - 1);
	}
	
	/**
	 * A quick sort algorithm that sorts the list using the given comparator by selecting a pivot, sorts all objects less than the pivot to the
	 * left of it, and all those greater than to the right. It then goes into the left and right portions and sorts them.
	 * @param list - The list to be sorted
	 * @param comparator - The comparator to dictate how the objects are to be sorted. If null, assumes the objects are comparable.
	 * @param leftBound - The leftmost index of the sub array portion of list
	 * @param rightBound - The rightmost index of the sub array portion of the list
	 */
	private static <T> void quickSort(ArrayList<T> list, Comparator<? super T> comparator, int leftBound, int rightBound){
		if(leftBound >= rightBound){
			return;
		}
		
		int pivotIndex  = partition(list, leftBound, rightBound, comparator);
		quickSort(list, comparator, leftBound, pivotIndex - 1);
		quickSort(list, comparator, pivotIndex + 1, rightBound);
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
	private static <T> int partition(ArrayList<T> list, int leftBound, int rightBound, Comparator<? super T> comparator) {
		
		int pivotIndex = findMedium(list, leftBound, rightBound, middleIndex(leftBound, rightBound));
		
		 swap(list, pivotIndex, rightBound);
		
		 int leftIndex = leftBound, rightIndex = rightBound - 1;
		 
		 while(leftIndex < rightIndex){
			 while(newCompare(list.get(leftIndex), list.get(rightBound), comparator) < 0 && leftIndex < list.size()){
				 leftIndex++;
			 }
			 
			 while(newCompare(list.get(rightIndex), list.get(rightBound), comparator) > 0 && rightIndex > 0){
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
	 * Generates an in-order ArrayList of Integer objects of the given size
	 * @param size - The needed size of the Array List
	 * @return - The newly generated, in-order Array List
	 */
	public static ArrayList<Integer> generateBestCase(int size){
		
		return buildArray(new ArrayList<Integer>(), size, "Best");
	}
	 /**
	  * Generates an randomly ordered ArrayList of Integer objects of the given size
	  * @param size - The needed size of the Array List
	  * @return - The newly and randomly generated Array List
	  */
	public static ArrayList<Integer> generateAverageCase(int size){
		
		return buildArray(new ArrayList<Integer>(), size, "Average");
	}
	
	/**
	 * Generates a reverse-order ArrayList of Integer objects of the given size
	 * @param size - The needed size of the Array List
	 * @return - The newly and randomly generated Array List
	 */
	public static ArrayList<Integer> generateWorstCase(int size){
		
		return buildArray(new ArrayList<Integer>(), size, "Worst");
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
	 * Fills an ArrayList based on the specified case-type and size
	 * @param list - The that is to be filled
	 * @param size - The needed size of the Array List
	 * @param caseType - Specifies how the Array List is to be filled.
	 * 		- Best -> In order
	 * 		- Average -> Random
	 * 		- Worst -> Reverse order
	 * @return - The newly filled Array List
	 */
	private static ArrayList<Integer> buildArray(ArrayList<Integer> list, int size, String caseType){
		for(int i = 0; i < size; i++){
			list.add(i);
		}
		switch(caseType){
		
		case "Best":
			break;
		case "Average":
			Collections.shuffle(list, rand);
			break;
		case "Worst":
			Collections.reverse(list);
			break;
		}
		
		return list;
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
		
		ArrayList<T> set = (ArrayList<T>) new ArrayList<Object>();
		set.add(list.get(left));
		set.add(list.get(middle));
		set.add(list.get(right));
		
		insertionSort(set, null, 0, set.size() - 1);
		
		if(set.get(1) == first){
			return left;
		}
		else if(set.get(1) == second){
			return middle;
		}
		else return right;
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
}
