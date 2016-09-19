package assignment03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Ryan Cantera (u0855101) and Dax Wilson (u0264580) This class contains
 *         methods that are used to add to and modify a set
 * @param <E>
 */
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

	private E[] newSet;// backing array
	private Comparator<? super E> userComparator;
	int closestIndex;
	private int count;

	/**
	 * Constructor for a set without a comparator object as a parameter
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchSet() {
		newSet = (E[]) new Object[10];
		userComparator = null;
		count = 0;
	}

	/**
	 * Constructor for a set with a comparator object as a parameter
	 * 
	 * @param comparator
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchSet(Comparator<? super E> comparator) {
		newSet = (E[]) new Object[10];
		userComparator = comparator;
		count = 0;
	}

	/**
	 * This method is used to search the array and check if the object that is
	 * being searched for is in the array
	 * 
	 * @param newSet
	 *            - the array being searched
	 * @param element
	 *            - the object being searched for
	 * @return - an int indicating the index of the object if it is contained in
	 *         the array
	 */
	@SuppressWarnings("unchecked")
	public int binarySearch(E[] newSet, Object element) {
		int low = 0;
		int high = count;
		int compareToResult;
		if (count == 0)
			throw new IndexOutOfBoundsException();

		while (low < high) {
			int middle = (low + high) / 2;
			if(userComparator != null){
				compareToResult = userComparator.compare(newSet[middle], (E) element);
			}
			else{
				compareToResult = ((Comparable<E>) newSet[middle]).compareTo((E) element);
			}
			if (compareToResult == 0) {
				return middle;
			} else if (compareToResult < 0) {
				low = middle + 1;
			} else if (compareToResult > 0) {
				high = middle;
			}
		}

		return -1;// returns an impossible index if not found
	}

	/**
	 * A binary search that is specific for adding to the array. Checks to see
	 * where the object being added should be added.
	 * 
	 * @param newSet
	 *            - the array to which the user is adding
	 * @param element
	 *            - the object that is to be added
	 * @return - an int indicating where the object should be added
	 */
	@SuppressWarnings("unchecked")
	public int binarySearchForAdd(E[] newSet, E element) {
		int low = 0;
		int high = count;
		int compareToResult;

		int middle = 0;
		while (low < high) {
			middle = (low + high) / 2;
			if(userComparator != null){
				compareToResult = userComparator.compare(newSet[middle], (E) element);
			}
			else{
				compareToResult = ((Comparable<E>) newSet[middle]).compareTo((E) element);
			}
			
			if (compareToResult < 0) {
				low = middle + 1;
			} else if (compareToResult > 0) {
				high = middle;
			} else
				return -1;
		}

		return (low + high) / 2;
	}

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> comparator() {
		return userComparator;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {
		return newSet[0];
	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E last() throws NoSuchElementException {
		return newSet[count - 1];
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
		int elementIndex;// find proper index
		if (count > 0) {
			elementIndex = binarySearchForAdd(newSet, element);
			if (count == newSet.length) {
				E[] copyOfArray = Arrays.copyOf(newSet, newSet.length);
				newSet = (E[]) new Object[newSet.length * 2];
				for (int j = 0; j < copyOfArray.length; j++) {
					newSet[j] = copyOfArray[j];
				}
			}

		} else {
			elementIndex = 0;
		}

		if(elementIndex == -1)
			return false;
		for (int indexCount = elementIndex; indexCount < newSet.length - 1; indexCount++) {
			newSet[indexCount + 1] = newSet[indexCount];
		}
		if (elementIndex != -1) {
			newSet[elementIndex] = element;
			count++;
			return true;
		}
		return false;
	}

	/**
	 * Adds all of the elements in the specified collection to this set if they
	 * are not already present and not set to null.
	 * 
	 * @param c
	 *            -- collection containing elements to be added to this set
	 * @return true if this set changed as a result of the call
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean addAll(Collection<? extends E> elements) {
		int newArrayIndex = 0;

		for (E element : elements) {
			add(element);
			newArrayIndex++;
		}

		if (newArrayIndex == elements.size() - 1)
			return true;

		return false;

	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	@Override
	public void clear() {
		for (int index = 0; index < newSet.length; index++) {
			newSet[index] = null;
		}
		count = 0;
	}

	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@Override
	public boolean contains(Object element) {
		if (binarySearch(newSet, element) != -1) {
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
		if (binarySearch(newSet, elements) != -1) {
			return true;
		}
		return false;
	}

	/**
	 * @return true if this set contains no elements
	 */
	@Override
	public boolean isEmpty() {
		return (count == 0);
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			int index = 0;

			@Override
			public boolean hasNext() {
				return ((count - (index)) > 0);
			}

			@Override
			public E next() {
				index++;
				return newSet[index];
			}

			@Override
			public void remove() {
				BinarySearchSet.this.remove(newSet[index]);
			}
			// NEVER use the iterator inside the data structure
		};
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param o
	 *            -- object to be removed from this set, if present
	 * @return true if this set contained the specified element
	 */
	@Override
	public boolean remove(Object element) {
		int elementIndex = binarySearch(newSet, element);
		if (elementIndex != -1) {
			if (newSet.length < elementIndex || elementIndex < 0)
				throw new IndexOutOfBoundsException();

			for (int k = elementIndex; k < count - 1; k++) {
				newSet[k] = newSet[k + 1];
			}
			count--;
			return true;
		} else {
			return false;
		}
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
		int indexCount = elements.size();
		
		for (Object element : elements) {
			remove(element);
			indexCount--;
		}
		if(indexCount == 0){
			return true;
		}
		
		return false;
	}

	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size() {
		return count;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
		return newSet;
	}

}
