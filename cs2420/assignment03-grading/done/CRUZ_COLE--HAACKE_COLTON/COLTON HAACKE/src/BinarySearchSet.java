//Cole Cruz and Colton Haacke
package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Constructs a set with an initial capacity of ten that can hold any type.
 * 
 * @author Cole Cruz and Colton Haacke
 * u1065058 and u0935270
 * @param <E>
 *            -- set type
 */
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

	@SuppressWarnings("unchecked")
	private E[] data = (E[]) new Object[10];
	private Comparator<? super E> comparator;
	private String compareType = "";
	private int length = 0;
	
	/**
	 * Gets compare type
	 */
	public String getCompareType() {
		return compareType;
	}

	/**
	 * Creates the sorted set assuming that the elements are ordered using their
	 * natural ordering (i.e., E implements Comparable<? super E>)
	 */
	public BinarySearchSet() {
		compareType = "comparable";
	}

	/**
	 * Creates the sorted set assuming that the elements are ordered using the
	 * provided comparator
	 * 
	 * @param c
	 *            -- provided comparator
	 */
	public BinarySearchSet(Comparator<? super E> comparator) {
		this.comparator = comparator;
		compareType = "comparator";
	}

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> comparator() {
		if (compareType == "comparable") {
			return null;
		}
		return comparator;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {
	    	if (data[0] == null) {
	    	    throw new NoSuchElementException();
	    	}
		return data[0];
	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E last() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return data[data.length - 1];
	}

	/**
	 * Adds the specified element to this set if it is not already present and
	 * not set to null.
	 * 
	 * @param o
	 *            -- element to be added to this set
	 * @return true if this set did not already contain the specified element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean add(E element) {
		if (element == null) {
			return false;
		}

		if (length == data.length) {
			grow();
		}
		
		if (length == 0) {
			data[length] = element;
			length++;
			return true;
		}
		
		if (length == 1) {
			if (data[0].equals(element)) {
				return false;
			}

			// If there is a comparator, use that to compare the elements, if
			// not then convert to string and compare the strings.
			else if (comparator != null && comparator.compare(data[0], element) > 0
					|| ((Comparable<E>)data[0]).compareTo(element) > 0) {
				if (comparator.compare(data[0], element) > 0) {
					E temp = data[0];
					data[0] = element;
					data[1] = temp;
					length++;
					
				}
			} else {
				data[1] = element;
				length++;
			}
			return true;
		}

		int smallestBound = 0;
		int largestBound = length;
		int current = length / 2;

		while (current != smallestBound) {
			
			if (data[current].equals(element)) {
				return false;
			}

			// If there is a comparator, use that to compare the elements, if
			// not then convert to string and compare the strings.
			else if (comparator != null && comparator.compare(data[current], element) > 0
					|| ((Comparable<E>)data[current]).compareTo(element) > 0) {
					largestBound = current;
					current = smallestBound + ((largestBound - smallestBound) / 2);
				
			} else {
				smallestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);
			}
		}
		
		if (data[current].equals(element)) {
		    	return false;
		}

		length++;

		// Create an empty array and fill it with the elements from the object
		// until it reaches the point that the added element should go in.
		// Then insert the added element and insert the rest of the elements
		// from the object after that.
		E[] tempArr = (E[]) new Object[length];
		for (current = 0; current < largestBound; current++) {
			tempArr[current] = data[current];
		}
		tempArr[current] = element;
		for (current = largestBound + 1; current < length; current++) {
			tempArr[current] = data[current - 1];
		}
		
		data = tempArr;

		return true;
	}

	/**
	 * Doubles the size of the array housing the elements of the set
	 */
	private void grow() {
		@SuppressWarnings("unchecked")
		// Creates a new array with twice the length of the current, then moves
		// all the elements from the current array into the new one and returns
		// the new one.
		E[] tempArr = (E[]) new Object[length * 2];
		int count = 0;
		Iterator<E> iter = iterator();
		while (iter.hasNext()) {
			tempArr[count] = iter.next();
			count++;
		}
		data = tempArr;
	}

	/**
	 * Adds all of the elements in the specified collection to this set if they
	 * are not already present and not set to null.
	 * 
	 * @param c
	 *            -- collection containing elements to be added to this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean addAll(Collection<? extends E> elements) {
		@SuppressWarnings("unchecked")
		E[] collectionArray = (E[]) elements.toArray();
		boolean added = false;
		for (int i = 0; i < collectionArray.length; i++) {
			if (add(collectionArray[i])) {
				added = true;
			}
		}
		return added;
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	@Override
	public void clear() {
		for (int count = 0; count < length; count++) {
			data[count] = null;
		}
	}

	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object element) {
	    	if (element == null) {
	    	    return false;
	    	}
	    	
		int smallestBound = 0;
		int largestBound = length;
		int current = length / 2;

		while (current != smallestBound) {
				
			if (data[current].equals(element)) {
				return true;
			}

			// If there is a comparator, use that to compare the elements, if
			// not then convert to string and compare the strings.
			if (compareType == "comparable") {
			    if (((Comparable<E>) data[current]).compareTo((E) element) > 0) {
				largestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);
			    }
			    else {
				smallestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);
			    }
			}
			else if (compareType == "comparator") {
			    if (comparator.compare(data[current], (E) element) > 0) {
				largestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);					    
			    }
			    else {
				smallestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);
			    }
			}
		}
		
		if (data[current].equals(element)) {
		    	return true;
		}
		return false;
	}

	/**
	 * @param c
	 *            -- collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	@Override
	public boolean containsAll(Collection<?> elements) {
		for (Object item: elements) {
		    if (contains(item) == false) {
			return false;
		    }
		}
		return true;
	}

	/**
	 * @return true if this set contains no elements
	 */
	@Override
	public boolean isEmpty() {
		if (size() != 0) {
			return false;
		}
		return true;
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator() {
		Iterator<E> iter = new SearchSetIterator<E>(this);
		return iter;
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param o
	 *            -- object to be removed from this set, if present
	 * @return true if this set contained the specified element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object element) {
	    	if (element == null) {
	    	    return false;
	    	}
		
		int smallestBound = 0;
		int largestBound = length;
		int current = length / 2;

		while (current != smallestBound) {
				
			if (data[current].equals(element)) {
			    for (int succeedingSpot = current + 1; succeedingSpot < data.length; succeedingSpot++) {
				data[succeedingSpot - 1] = data[succeedingSpot];
				data[succeedingSpot] = null;
			    }
			    length--;
			    return true;
			}

			// If there is a comparator, use that to compare the elements, if
			// not then convert to string and compare the strings.
			if (compareType == "comparable") {
			    if (((Comparable<E>) data[current]).compareTo((E) element) > 0) {
				largestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);
			    }
			    else {
				smallestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);
			    }
			}
			else if (compareType == "comparator") {
			    if (comparator.compare(data[current], (E) element) > 0) {
				largestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);					    
			    }
			    else {
				smallestBound = current;
				current = smallestBound + ((largestBound - smallestBound) / 2);
			    }
			}
		}
		return false;
	}

	/**
	 * Removes from this set all of its elements that are contained in the
	 * specified collection.
	 * 
	 * @param c
	 *            -- collection containing elements to be removed from this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean removeAll(Collection<?> elements) {
		boolean anyRemoved = false;
		for (Object item : elements) {
		    	if (this.remove(item)) {
		    	    anyRemoved = true;
		    	}
		}
		return anyRemoved;
	}

	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size() {
		int size = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null) {
				size++;
			}
		}
		return size;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
		@SuppressWarnings("unchecked")
		E[] elementArray = (E[]) new Object[length];
		int count = 0;
		Iterator<E> iter = iterator();
		while (iter.hasNext()) {
			elementArray[count] = iter.next();
			count++;
		}
		return elementArray;
	}

	/**
	 * 
	 * @param i
	 *            - the index of the element that is being gotten
	 * @return - the element at index i
	 */
	public E get(int i) {
		return data[i];
	}

}
