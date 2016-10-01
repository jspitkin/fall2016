package assignment05;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 * @author Jana Klopsch (u0854469) & Alex Henabray (u0795787), last updated: 09/27/2016
 * Course: CS 2420
 * Assignment 05
 * 
 */
public class InsertionSort {

	/**
	 * This generic method sorts an array of items using an insertion sort algorithm and
	 * uses an input comparator object.
	 * @param inputArray
	 * @param arrayComparator
	 */
	public static<T> void insertionSort(ArrayList<T> inputArray, Comparator<? super T> arrayComparator ) {

		T currentItem;
		int pointerIndex = 0;

		// The following block of code implements an insertion sort algorithm to sort the
		// items in inputArray.
		for(int unsorttedIndex = 1; unsorttedIndex < inputArray.size(); unsorttedIndex++){

			currentItem = inputArray.get(unsorttedIndex);
			pointerIndex = unsorttedIndex;

			while(pointerIndex > 0 && 0 < arrayComparator.compare(inputArray.get(pointerIndex -1), currentItem)){
				inputArray.set(pointerIndex, inputArray.get(pointerIndex - 1));
				pointerIndex--;
			}

			inputArray.set(pointerIndex, currentItem);

		}
	}
	
}
