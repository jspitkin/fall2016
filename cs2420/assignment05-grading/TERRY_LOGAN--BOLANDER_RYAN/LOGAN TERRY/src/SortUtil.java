package assignment05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * A class that allows mergesort and quicksort on ArrayLists.
 * @author Ryan Bolander u0016911
 * @author Logan Terry u0973436
 *
 */
public class SortUtil {

	//0 means insertion sort doesn't happen
	private static int threshold = 16;
	
	/**
	 * MergeSort
	 * @param list - The ArrayList to sort
	 * @param comparator - The comparator to determine how to sort
	 */
	public static <T> void mergesort(ArrayList<T> list, Comparator<? super T> comparator){
		@SuppressWarnings("unchecked")
		T[] temp = (T[]) new Object[list.size()];
		msort(list,temp,0,list.size()-1,comparator);
	}
	
	/**
	 * The recursive function used by mergesort.
	 * @param list - The list to sort
	 * @param temp - A temporary array to help sorting
	 * @param start - The start index of the items to sort
	 * @param end - The end index of the items to sort
	 * @param comparator - The comparator to determine how to sort
	 */
	private static <T> void msort(ArrayList<T> list, T[] temp, int start, int end, Comparator<? super T> comparator){
		//Base case
		if(end-start < threshold+1){
			insertionSort(list,start,end,comparator);
			return;
		}
		//Left half
		msort(list,temp,start,(start+end)/2,comparator);
		msort(list,temp,(start+end)/2+1,end,comparator);
		merge(list,temp,start,end,comparator);
	}
	
	/**
	 * The function that actually does the merging.
	 * @param list - The list to sort
	 * @param temp - A temporary array to help sorting
	 * @param start - The start index of the items to sort
	 * @param end - The end index of the items to sort
	 * @param comparator - The comparator to determine how to sort
	 */
	private static <T> void merge(ArrayList<T> list, T[] temp, int start, int end, Comparator<? super T> comparator){
		int insertionPoint = start;
		int leftTarget = start;
		int rightTarget = (start+end)/2+1;
		while(leftTarget != (start+end)/2+1 && rightTarget != end+1){
			if(comparator.compare(list.get(leftTarget), list.get(rightTarget)) < 0){
				temp[insertionPoint] = list.get(leftTarget);
				leftTarget++;
			}else{
				temp[insertionPoint] = list.get(rightTarget);
				rightTarget++;
			}
			insertionPoint++;
		}
		if(leftTarget == (start+end)/2 + 1){
			//Automatically add the right half
			while(rightTarget != end+1){
				temp[insertionPoint] = list.get(rightTarget);
				rightTarget++;
				insertionPoint++;
			}
		}else{
			//Automatically add the left half
			while(leftTarget != (start+end)/2+1){
				temp[insertionPoint] = list.get(leftTarget);
				leftTarget++;
				insertionPoint++;
			}
		}
		//Re-insert into the ArrayList
		for(int i = start; i <= end; i++){
			list.set(i, temp[i]);
		}
	}
	
	/**
	 * Selection sort for use in mergesort after a certain threshold. Pulled from assignment04.
	 * @param list - The list to sort
	 * @param start - The start index of the items to sort
	 * @param end - The end index of the items to sort
	 * @param comparator - The comparator to determine how to sort
	 */
	private static <T> void insertionSort(ArrayList<T> list, int start, int end, Comparator<? super T> comparator) {
		for (int i = start+1; i <= end; i++) {
			T currentItem = list.get(i);
			for (int j = i - 1; j >= start; j--) {
				if (comparator.compare(list.get(j), currentItem) > 0) {
					list.set(j+1,list.get(j));
					if(j == start){
						list.set(j, currentItem);
					}
				} else {
					list.set(j+1, currentItem);
					break;
				}
			}
		}
	}
	
	/**
	 * QuickSort
	 * @param list - ArrayList to sort
	 * @param comparator - Comparator to determine how to sort
	 */
	public static <T> void quicksort(ArrayList<T> list, Comparator<? super T> comparator){
		qsort(list,0,list.size() - 1,comparator);
	}
	
	/**
	 * The recursive function used by quicksort.
	 * @param list - The list to sort
	 * @param start - The start index of the items to sort
	 * @param end - The end index of the items to sort
	 * @param comparator - The comparator to determine how to sort
	 */
	public static <T> void qsort(ArrayList<T> list, int start, int end, Comparator<? super T> comparator){
		//Base case
		if(end-start < 1){
			return;
		}
		
		//Change the next line for different pivot choices.
		T pivot = list.get((start+end)/2);
		
		list.set((start+end)/2, list.get(end));
		list.set(end, pivot);
		int greaterTarget = start;
		int lesserTarget = end-1;
		while(greaterTarget < lesserTarget){
			//Find greaterTarget
			if(comparator.compare(list.get(greaterTarget), pivot) > 0){
				//Find lesserTarget
				if(comparator.compare(list.get(lesserTarget), pivot) < 0){
					//Swap the items
					T temp = list.get(lesserTarget);
					list.set(lesserTarget, list.get(greaterTarget));
					list.set(greaterTarget, temp);
					greaterTarget++;
					lesserTarget--;
				}else{
					lesserTarget--;
				}
			}else{
				greaterTarget++;
			}
		}
		//Special case if lesser met greater
		if(comparator.compare(list.get(greaterTarget), pivot) < 0){
			greaterTarget++;
		}
		//Pivot swap
		list.set(end, list.get(greaterTarget));
		list.set(greaterTarget, pivot);
		//Sort the left
		qsort(list,start,greaterTarget,comparator);
		//Sort the right
		qsort(list,greaterTarget + 1,end,comparator);
		
	}
	
	/**
	 * This method generates and returns an ArrayList of integers 1 to size in ascending order.
	 * @param size - The size of the ArrayList
	 * @return - The ascending order ArrayList
	 */
	public static ArrayList<Integer> generateBestCase(int size){
		ArrayList<Integer> out = new ArrayList<Integer>();
		for(int i = 1; i <= size; i++){
			out.add(i);
		}
		return out;
	}
	
	/**
	 * This method generates and returns an ArrayList of integers 1 to size in permuted order (i,e., randomly ordered).
	 * @param size - The size of the ArrayList
	 * @return - The randomly ordered ArrayList
	 */
	public static ArrayList<Integer> generateAverageCase(int size){
		ArrayList<Integer> out = generateBestCase(size);
		Collections.shuffle(out, new Random(0));
		return out;
	}
	
	/**
	 * This method generates and returns an ArrayList of integers 1 to size in descending order.
	 * @param size - The size of the ArrayList.
	 * @return - The descending order ArrayList.
	 */
	public static ArrayList<Integer> generateWorstCase(int size){
		ArrayList<Integer> out = new ArrayList<Integer>();
		for(int i = size; i > 0; i--){
			out.add(i);
		}
		return out;
	}
	
	
	
}
