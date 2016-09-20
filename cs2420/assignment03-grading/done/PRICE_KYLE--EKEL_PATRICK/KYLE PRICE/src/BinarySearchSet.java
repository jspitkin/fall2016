package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Kyle Price and Patrick Ekel
 * 09/14/2016
 *
 * BinarySearchSet - is a custom set of generic objects that is always sorted and 
 * uses the binary search algorithm to locate its elements. 
 */
@SuppressWarnings("unchecked")
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

	private Comparator<? super E> comp; // A comparator object. 

	private int counter; // An integer that keeps track of the number of elements the array "arr".
	
	private boolean flag; // A flag that indicates if binSearch located the object in an "arr" or not.

	private E[] arr;  // An array of generic objects. 
	
	/**
	 * BinarySearchSet - is the constructor to be used when a custom comparator isn't needed to 
	 * compare two objects in the array (the items will have a natural ordering). 
	 */
	public BinarySearchSet() {
		comp = null; // If this constructor is used, comp is set to null so the program can determine
		// whether to use compare to compareTo. 
		counter = 0;
		arr = (E[]) new Object[50];
		
	}
	
	/**
	 * BinarySearchSet(Comparator<? super E> comparator) - is the constructor to be used when 
	 * a customer comparator is needed to compare two objects in the array (no natural ordering).
	 * @param comparator - the comparator to be used. 
	 */
	public BinarySearchSet(Comparator<? super E> comparator) {
		comp = comparator; // Comp is not set to null. 
		counter = 0;
		arr = (E[]) new Object[50];
	}
	
	/**
	 * Comparator<? super E> comparator() - tells the program to use comp as the comparator. 
	 */
	public Comparator<? super E> comparator() {
		return comp;
	}

	/**
	 * first - returns the first element in the array "arr". If there are no elements in "arr",
	 * it will throw a NoSuchElementException exception. 
	 * @return - an object of type E.
	 */
	public E first() throws NoSuchElementException {
		if (counter == 0) {
			throw new NoSuchElementException();
		} else {
			return arr[0];
		}
	}
	
	/**
	 * last - returns the last item in the array "arr". If there are no elements in "arr", it will
	 * throw a NoSuchElementException exception.
	 */
	@Override
	public E last() throws NoSuchElementException {
		if (counter == 0) {
			throw new NoSuchElementException();
		}
		return arr[counter - 1];
	}
	
	/**
	 * add - uses binSearch to find where "element" belongs and inserts it. If the item is
	 * already in the set, it simply returns "false". 
	 * 
	 * @return - "true" if the item was added.
	 */
	@Override
	public boolean add(E element) {
		int theIndex; // The index of element E (where it is OR where it belongs). 
		E[] arrTemp; // A temporary array. 
		theIndex = binSearch(element);
		if (flag){ // If the item was found, return false and exit method. 
			return false;
		}
		
		else{

			if (arr.length <= counter) { // Resize the array (if necessary). 
				arrTemp = (E[]) new Object[arr.length * 2];
				for (int i = 0; i < arr.length; i++) {
					arrTemp[i] = arr[i];
				}
				
				arr = arrTemp;
				
			} else {
				arrTemp = (E[]) new Object[50]; 
			
			}
			if (counter == 0){ // If the array is empty, put element at arr[0]. 
				arr[0] = element;
				flag = true;
			}
			if (theIndex > (counter-1)){ // If it's going to be added to the end, insert it.
				arr[theIndex] = element;
				flag = true;
			}

			if (theIndex <= counter - 1) {
				for (int j = 0; j < theIndex; j++) {
					arrTemp[j] = arr[j]; // Copies elements into new array.
				}
				arrTemp[theIndex] = element; 
				for (int j = theIndex + 1; j < arrTemp.length; j++) {
					arrTemp[j] = arr[j - 1];
				}
				arr = arrTemp; // Assigns arr to the temp array values. 
				flag = true;
			}
			counter++;
		}
		return flag;
	}

	/**
	 * addAll - adds the elements from a collection of E elements to the array "arr". 
	 * @param - elements
	 * @return - true if any of the elements were added. 
	 */
	public boolean addAll(Collection<? extends E> elements) {
		if (elements.size() == 0) {
			return false;
		}

		for (E eachElement : elements) {
			this.add(eachElement);

		}
		return true;
	}
	
	/**
	 * clear - removes all the elements from from "arr". 
	 */
	public void clear() {
		E[] temp = (E[]) new Object[50]; 
		arr = temp;
		counter = 0;

	}
	
	/**
	 * contains - determines whether "element" is in "arr". 
	 * @param - element
	 * @return - true if "element" is in the array "arr". 
	 */
	@Override // why do some have override?
	public boolean contains(Object element) {
		int temp = binSearch((E) element);
		if (flag){
			return true;
		}
		return false;
	}
	
	/**
	 * containsAll - determines whether all of the items from "elements" are found in "arr". 
	 * @return - true if ALL of the items from elements are found in "arr". 
	 */
	@Override
	public boolean containsAll(Collection<?> elements) { 
		for(Object eachElement : elements) {
			if(!(this.contains((E)eachElement))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * isEmpty - determines whether or not "arr" is empty. 
	 * @return - true if "arr" is empty. 
	 */
	public boolean isEmpty() {
		if (counter == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * iterator - creates an iterator object that is accessible outside of the class. 
	 */
	public Iterator<E> iterator() {
		@SuppressWarnings("unchecked")
		Iterator<E> iterator = (Iterator<E>) new Iterator<Object>() {
			private int index = 0; 
			private boolean canRemove = false; // Indicates whether next has been called since the last call of remove.
			
			// Next, hasNext and remove follow the Java API for the interface "Iterable".
			
			@Override
			public boolean hasNext() {
				return (counter > index && arr[index] != null);
			}

			@Override
			public Object next() { 
				canRemove = true;
				return arr[index++]; 

			}

			@Override
			public void remove() {
				if (canRemove == false) {
					throw new IllegalStateException();
				}
				BinarySearchSet.this.remove((E) arr[index]); 
				canRemove = false;

			}

		};
		return iterator;
	}
	
	/**
	 * remove - removes the object "element" from the the array "arr". 
	 * @return - true if the element was removed. 
	 */
	@Override
	public boolean remove(Object element) {
		int theIndex = binSearch((E) element);

		if (!(this.contains((E) element))) { // Returns false if element isn't in array.
			return false;
		}
		if ((counter - 1) == theIndex) {
			arr[theIndex] = null;
			counter--;
			return true;
		} else {
			for (int i = 0; i < (counter - (theIndex - 1)); i++) {
				arr[theIndex + i] = arr[theIndex + i + 1];
			}
			counter--;
			return true;
		}
	}
	
	/**
	 * removeAll - removes all instances of each item of "elements" from the array "arr". 
	 * @param - elements 
	 * @return - true if any of the elements from the collection were removed from "arr". 
	 */
	@Override
	public boolean removeAll(Collection<?> elements) {
		boolean flag = false;
		for (Object eachElement : elements) {
			if (this.contains(eachElement)) {
				this.remove(eachElement);
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * size - returns the number of items in the array. 
	 * @Return - an integer value.
	 */
	public int size() {
		return counter;
	}

	/**
	 * toArray - returns "arr" as an array of objects. 
	 * @return - "arr" 
	 */
	@Override
	public Object[] toArray() {
		return (Object[]) arr;
	}

	/**
	 * compareOrCompareTo - determines whether a method needs to use compare or compareTo. 
	 * @param lhs - left hand side item.
	 * @param rhs - right hand side item.
	 * @return - an integer following the same return patterns as compare and compareTo. 
	 */
	public int compareOrCompareTo(E lhs, E rhs) {
		if (comp == (null)) {
			return (((Comparable<E>) lhs).compareTo(rhs));
		}

		else {
			return ((comp.compare(lhs, rhs)));
		}
	}

	/**
	 * binSearch - performs binary search on the items in "arr". It only works if arr is ordered.
	 * @param element - the item to be search for.
	 * @return - the index "element" should be located (wether it is there or not).
	 */
	public int binSearch(E element) {

		int low = 0;
		int high = counter; 
		int mid = (low + high) / 2;
		if (counter == 0) {  // If counter = 0, the item is automatically "not found".
			flag = false;
		}

		while (high > low) {
			int found = compareOrCompareTo(element, arr[mid]); // This value simplifies the if statements. 

			if (found < 0) {
				high = mid; 
				flag = false;
				mid = (low + high) / 2;

			} else if (found > 0) {
				low = mid + 1;
				flag = false;
				mid = (low + high) / 2;

			} else { 
				flag = true;
				mid = (low + high) / 2;

				return mid;
			}

		}
		return mid;
	}

}