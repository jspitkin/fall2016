/**
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A BinarySearchSet that provides a total ordering on its elements. The elements are
 * ordered using their natural ordering, or by a Comparator provided at BinarySearchSet
 * creation time. Thus, all elements inserted into a BinarySearchSet must
 * implement the Comparable interface or be accepted by the specified
 * Comparator. The set's iterator will traverse the set in ascending element
 * order.
 * 
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 * 
 * @param <E> -- the type of elements maintained by this set
 */
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

	private E[] backingArray;
	private int size;
	private Comparator<? super E> globalComparator;
	private boolean flag = false;

	
	
	
	/**
	 * Creates a generic BinarySearchSet without a custom comparator
	 */
	public BinarySearchSet() {

		size = 0;
		backingArray = (E[]) new Object[100];
		globalComparator = new ComparableInDisguise();
		flag = true;

	}
	
	/**
	 * Creates a generic BinarySearchSet with a custom comparator
	 * @param comparator - custom comparator
	 */
	public BinarySearchSet(Comparator<? super E> comparator) {

		size = 0;
		backingArray = (E[]) new Object[100];
		globalComparator = comparator;
	}

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	public Comparator<? super E> comparator() {

		if (flag == true) {
			return null;
		}

		return globalComparator;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	public E first() throws NoSuchElementException {

		if (backingArray[0] == null) {
			throw new NoSuchElementException();
		}

		return backingArray[0];

	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	public E last() throws NoSuchElementException {

		if (backingArray[0] == null) {
			throw new NoSuchElementException();
		}
		return backingArray[size - 1];

	}

	/**
	 * Adds the specified element to this set if it is not already present and
	 * not set to null.
	 * 
	 * @param o
	 *            -- element to be added to this set
	 * @return true if this set did not already contain the specified element
	 */
	public boolean add(E element) {

		if (element == null) {
			return false;
		}

		int pos = this.find(element);

		if (pos >= 0) {
			return false;
		}

		if (pos < 0) {
			pos = -(pos + 1);
		}

		if (size == backingArray.length - 1) {
			this.grow();
		}

		E[] copyOfBackingArray = (E[]) new Object[size * 2 + 1];

		int count = 0;
		for (int i = 0; i <= size; i++) {

			if (i != pos) {
				copyOfBackingArray[i] = backingArray[i - count];

			} else {
				copyOfBackingArray[pos] = element;
				count++;
			}

		}

		this.backingArray = copyOfBackingArray;
		size++;

		return true;
	}

	/**
	 * Adds all of the elements in the specified collection to this set if they
	 * are not already present and not set to null.
	 * 
	 * @param c
	 *            -- collection containing elements to be added to this set
	 * @return true if this set changed as a result of the call
	 */
	public boolean addAll(Collection<? extends E> elements) {

		Iterator<E> listIterator = (Iterator<E>) elements.iterator();

		while (listIterator.hasNext()) {
			E element = listIterator.next();

			if (element == null) {
				return false;
			}

			if (this.add(element) != true) {
				return false;
			}

		}
		return true;
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	public void clear() {

		for (E item : backingArray) {
			item = null;

		}
		size = 0;
	}

	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	public boolean contains(Object element) {

		int trueFalse = find((E) element);
		if (trueFalse < 0) {
			return false;
		}
		return true;

	}

	/**
	 * @param c
	 *            -- collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	public boolean containsAll(Collection<?> elements) {
		Iterator<E> listIterator = (Iterator<E>) elements.iterator();
		boolean flag = false;
		while (listIterator.hasNext()) {
			E element = listIterator.next();

			if (this.contains(element)) {
				flag = true;
			} else {
				return false;
			}

		}
		return flag;
	}

	/**
	 * @return true if this set contains no elements
	 */
	public boolean isEmpty() {

		return size == 0;
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	public Iterator<E> iterator() {

		return new Iterator<E>() {

			private int current = 0;

			private boolean iterFlag;

			/**
			 * Does a null check for the next element to be iterated over, to determine if there are any more elements remaining.
			 *  
			 * 
			 * 
			 * @return 
			 * 		--true if the iterator can determine the existence of a next  element.
			 * 		--false if current is greater than or equal to the size of a BinarySearchSet
			 * 		--false if the next element in the backing array is null, therefore having no more elements in a BinarySearchSet
			 */
			@Override
			public boolean hasNext() {

				if (current >= size) {
					return false;
				}

				if (backingArray[current + 1] == null) {

					return false;

				}

				return true;
			}

			/**
			 * Returns the next element in a BinarySearchSet unless there are no elements,
			 * in which case a NoSuchElementException will be thrown
			 * 
			 * @return Returns the generic element that is next in a BinarySearchSet
			 */
			@Override
			public E next() {

				if (backingArray[current] == null) {
					iterFlag = false;
					throw new NoSuchElementException();
				} else {
					iterFlag = true;
					return backingArray[current++];

				}


			}

			/**
			 * Removes the last element called by next.
			 * If next was not called before remove, a IllegalStateException will be thrown.
			 * 
			 */
			@Override
			public void remove() {

				if (iterFlag == true) {
					iterFlag = false;
					BinarySearchSet.this.remove(current - 1);
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
	public boolean remove(Object element) {

		if (element == null) {
			return false;
		}

		int pos = this.find((E) element);

		if (pos >= 0) {

			this.remove(pos);


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
	public boolean removeAll(Collection<?> elements) {
		Iterator<E> listIterator = (Iterator<E>) elements.iterator();

		while (listIterator.hasNext()) {
			E element = listIterator.next();

			if (element == null) {
				return false;
			}

			if (this.remove(element) != true) {
				return false;
			}

		}
		return true;
	}

	/**
	 * @return the number of elements in this set
	 */
	public int size() {
		return size;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	public Object[] toArray() {

		Object[] theThing = new Object[size];

		for (int i = 0; i < size; i++) {

			theThing[i] = backingArray[i];

		}

		return theThing;

	}

	

	/**
	 * The find method is a binary search algorithm used to find the index of a searched item,
	 * or the index of where an item should be inserted.
	 * 
	 * @param e
	 *            -- element to be located in a BinarySearchSet
	 * @return 
	 * 		--Positive int value of the elements location
	 * 		--Negative int value for the location of where element should be inserted
	 */
	private int find(E element) {
		int begining = 0;
		int mid = 0;
		int end = size;

		while (begining < end) {

			mid = (begining + end) / 2;

			//This if statement determines what half of the array to search
			if (globalComparator.compare(element, backingArray[mid]) > 0) {
				begining = mid + 1;
			} else {
				end = mid;
			}
		}
		//determines whether or not the element exists, and returns the index where something should go
		if (begining == size || globalComparator.compare(element, backingArray[begining]) != 0) {

			return -(begining + 1);

		}
		return begining;

	}

	
	
	/**
	 * 	
	 * Removes the element from the given index.
	 * 
	 * @param i
	 *      -- The index of the backing array who's element is to be removed
	 * @return 
	 * 		--Positive int value of the elements location
	 * 		--Negative int value for the location of where element should be inserted
	 */
	private void remove(int index)

	{

		if (index >= 0) {

			E[] copyOfBackingArray = (E[]) new Object[backingArray.length];

			int count = 0;
			for (int i = 0; i <= size; i++) {
				if (i == index) {
					count++;
				}

				copyOfBackingArray[i] = backingArray[i + count];

			}

			this.backingArray = copyOfBackingArray;

			size--;

		}

	}

	/**
	 * If the backing array is full, grow will double its size.
	 */
	private void grow() {
		E[] copyOfBackingArray = (E[]) new Object[backingArray.length * 2];

		for (int i = 0; i < size; i++) {
			copyOfBackingArray[i] = backingArray[i];

		}

		this.backingArray = copyOfBackingArray;
	}

	private class ComparableInDisguise implements Comparator<E> {

		@Override
		public int compare(E lhs, E rhs) {
			if (lhs instanceof Comparable) {

				return ((Comparable<E>) lhs).compareTo(rhs);

			} else {
				throw new IllegalArgumentException("You called the wrong constructor!");
			}
		}
	}

}
