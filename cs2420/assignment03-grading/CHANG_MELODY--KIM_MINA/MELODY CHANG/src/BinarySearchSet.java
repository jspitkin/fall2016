package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ching-Yuan Chang u0914005, Min Kim u1054673
 */

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

	private Comparator<? super E> cmp;
	private int initialSize;
	private E[] array;
	private int arraySize;
	private boolean contain;
	private int removeIdx;

	public BinarySearchSet() {

		this.cmp = null;
		initialSize = 10;
		array = (E[]) new Object[initialSize];
		arraySize = 0;
		contain = false;
		removeIdx = 0;

	}

	public BinarySearchSet(Comparator<? super E> comparator) {

		this.cmp = comparator;
	}

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> comparator() {
		return this.cmp;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {
		if (isEmpty() == true) {
			throw new NoSuchElementException();
		}
		return array[0];
	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E last() throws NoSuchElementException {
		if (isEmpty() == true) {
			throw new NoSuchElementException();
		}

		return array[size() - 1];
	}

	/**
	 * Adds the specified element to this set if it is not already present and
	 * not set to null.
	 * 
	 * @param o
	 *            -- element to be added to this set
	 * @return true if this set did not already contain the specified element
	 */
	@Override
	public boolean add(E element) {
		if (contains(element) == true || element == null) {
			return false;
		}

		// If the array is full, double the array
		if (arraySize == initialSize) {
			E[] temp = (E[]) new Object[initialSize * 2];
			for (int size = 0; size < arraySize; size++) {
				temp[size] = array[size];
			}
			array = temp;
			initialSize = initialSize * 2;
		}

		// if it is the first element, just add it to index 0
		if (arraySize == 0) {
			array[0] = element;
			arraySize++;
			return true;
		}

		int index = BinarySearch(element);
		int size = arraySize;
		for (int idx = size - 1; size >= index; size--) {
			array[idx + 1] = array[idx];
		}
		array[index] = element;
		arraySize++;
		return true;
	}

	/**
	 * Search for the index of element using Binary Search
	 * 
	 * @param obj1
	 *            - the element that we are searching the index of
	 * @return - the index of obj1
	 */
	public int BinarySearch(E obj1) {
		int high = arraySize - 1;
		int low = 0;
		int mid = -1;
		if (array[0] == obj1) {
			contain = true;
		}

		while (high >= low) {
			mid = (high + low) / 2;
			int comp = compare(array[mid], obj1);

			if (comp == 0) {
				contain = true;
				return mid;
			} else if (comp < 0) {
				low = mid + 1;
			} else if (comp > 0) {
				high = mid - 1;
			}

		}

		if (low > high) {
			return low;
		}

		return mid;

	}

	/**
	 * Compare the two objects that are passed in.
	 * 
	 * @return -1, 0, 1 to show whether it is greater than, less than, or equal
	 *         to.
	 */
	public int compare(E obj1, E obj2) {
		if (comparator() != null) {
			return (cmp.compare(obj1, obj2));
		} else {

			return (((Comparable) obj1).compareTo(obj2));
		}
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

		boolean added = false;
		for (E elem : elements) {
			if (add(elem) == true) {
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
		for (int size = 0; size < arraySize; size++) {
			array[size] = null;
		}
		arraySize = 0;
	}

	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@Override
	public boolean contains(Object element) {
		if (element == null || isEmpty() == true) {
			return false;
		}
		removeIdx = BinarySearch((E) element);
		boolean cont = contain;
		contain = false;
		return cont;
	}

	/**
	 * @param c
	 *            -- collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	@Override
	public boolean containsAll(Collection<?> elements) {
		for (Object elem : elements) {
			if (contains(elem) == false) {
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
		if (arraySize == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {

			private int currIndex = 0;
			private boolean removed = false;

			/**
			 * returns true if the next index is not null.
			 */
			@Override
			public boolean hasNext() {
				int curr = currIndex;
				if (array[curr + 1] == null) {
					return false;
				} else {
					return true;
				}
			}

			/**
			 * returns the next element, if there's no next element throws
			 * NoSuchElementException
			 */
			@Override
			public E next() {
				if (hasNext() == false) {
					throw new NoSuchElementException();
				} else {
					int curr = currIndex;
					currIndex++;
					removed = false;
					return array[curr];
				}
			}

			/**
			 * remove the current element. can only be called once per next is
			 * called. otherwise throws IllegalStateException.
			 */
			public void remove() {
				if (removed == false) {
					int curr = currIndex;
					for (int idx = curr; idx < arraySize - 1; idx++) {
						array[idx] = array[idx + 1];
						removed = true;
					}
					array[arraySize - 1] = null;
					arraySize--;
				} else {
					throw new IllegalStateException();
				}
			}

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
		if (element == null || arraySize == 0) {
			return false;
		}
		if (contains(element) == true) {
			for (int idx = removeIdx; idx < arraySize; idx++) {
				array[idx] = array[idx + 1];
			}
			arraySize--;
			return true;
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

		boolean removed = false;
		for (Object elem : elements) {
			if (remove(elem) == true) {
				removed = true;
			}
		}

		return removed;
	}

	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size() {
		return arraySize;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
		return array;
	}

}
