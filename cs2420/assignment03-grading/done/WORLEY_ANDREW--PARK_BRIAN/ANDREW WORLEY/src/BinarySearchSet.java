package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class is a list of generic data.
 * 
 * @author Andrew Worley / u0651238
 * @author Brian Park / u0735732
 * last update 9/14/16 00:38
 */
@SuppressWarnings("unchecked")
public class BinarySearchSet<E> implements Iterable<E>, SortedSet<E>{
	
	private E[] baseArray;
	private int size;
	private Comparator<E> itemComparator;
	private boolean isComparable;
	
	public BinarySearchSet() {
		baseArray = (E[]) new Object[8];
		itemComparator = null;
		isComparable = true;
	}
	
	/**
	 * Constructor for BinarySearchSet class that defines whether to use the comparable interface or the comparator interface is used which is decided by the 
	 * type E and the comparator passed
	 * 
	 * @param comparator: the desired comparator for the type E
	 */
	public BinarySearchSet(Comparator<? super E> comparator)  {
		baseArray = (E[]) new Object[8];
		itemComparator = (Comparator<E>) comparator;
	}
	
	/**
	 * Default constructor for the BinarySearchSet: assumes the type E has a natural order and will be compared with the comparable interface
	 * 
	 */
	public Comparator<? super E> comparator() {
		return itemComparator;
	}
	
	@Override
	/**
	 * Accessor for the first element in the collection
	 * @return --
	 * 		   The first element in the collection
	 */
	public E first() throws NoSuchElementException {
		if (size == 0){
			throw new NoSuchElementException();
		}
		return baseArray[0];
	}
	@Override
	/**
	 * Accessor for the last element in the collection
	 * @return --
	 * 		   The last element in the collection
	 */
	public E last() throws NoSuchElementException {
		if (size == 0){
			throw new NoSuchElementException();
		}
		return baseArray[size-1];
	}
	@Override
	/**
	 * Method adds unique elements to the collection
	 * @param -- the item to be added to the set
	 * @return -- False if the element to be added is a duplicate:
	 * 			  True when the element is successfully added to the collection
	 */
	public boolean add(E element) {
		int insertLoc = searchOrGetInsertIndex(element, true);
		
		if (size>1 && insertLoc < size) {
			if ((isComparable) && ((Comparable<E>) baseArray[insertLoc]).compareTo(element) == 0) {
				return false;
			}
			else if ((!isComparable) && (itemComparator.compare(element, baseArray[insertLoc]) == 0)) {
				return false;
			}
		}
		
		if ((insertLoc == baseArray.length) && insertLoc < size) {
			baseArray[insertLoc] = element;
			size++;
			return true;
		}
		if (size < baseArray.length) {
			for (int index = size; index > insertLoc; index--) {
				baseArray[index] = baseArray[index-1];
			}
			baseArray[insertLoc] = element;
			size++;
			return true;
		}
		else {
			E[] arrayCopy = (E[]) new Object[baseArray.length*2];
			arrayCopy[insertLoc] = element;
			size++;
			
			for(int index = 0; index < insertLoc; index++) {
				arrayCopy[index] = baseArray[index];
			}
			for(int index = insertLoc+1; index < size; index++) {
				arrayCopy[index] = baseArray[index-1];
			}
			baseArray = arrayCopy;
			return true;
		}
	}
	@Override
	/**
	 * Adds all of the elements in the given collection to the given BinarySearchSet if they are not already there
	 * 
	 * @param elements -- a collection of elements to be searched for in a set
	 * @return returns true if the set has changed as a result of this method call
	 */
	public boolean addAll(Collection<? extends E> elements) {
		boolean changed = false;
		for(E current: elements){
			if(add(current)){;
				changed = true;
			}
		}
		return changed;
	}

	@Override
	/**
	 * Removes all of the elements in the given BinarySearchSet and resets it's size
	 */
	public void clear() {
		baseArray = (E[]) new Object[8]; //is this needed? 
		size = 0;
	}
	
	@Override
	/**
	 * {@inheritDoc}
	 * @param -- element -- the element to be searched for
	 * @return -- true if the method is contained in the BinarySearchSet
	 */
	public boolean contains( Object element) { 
		try {
			if (searchOrGetInsertIndex((E) element, false) == -1) {
				return false;
			}
			return true;
		}catch(ClassCastException e) {
			return false;
		}
	
	}
	@Override
	/**
	 * Method checks if all the elements in the specified 
	 * collection exist in this collection.
	 * @param elements - A collection<?> of objects
	 * @return true if all of the elements provided are within the collection 
	 */
	public boolean containsAll(Collection<?> elements) {
			for(Object element: elements){
				if(!contains(element)){
					return false;
				}
			}
		return true;
	}
	@Override
	/**
	 * Method returns a boolean to evaluate if
	 * the collection is empty or not
	 * @return ---
	 * 			True if the collection is empty.
	 * 			False if otherwise
	 */
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	* This is as sub class that defines how a BinarySearchSet is iterated through
	* @author: Brian Park / u0735732, Andrew Worley / u0651238
	*/
	@Override
	public Iterator<E> iterator() {

		Iterator<E> iterate = new Iterator<E>() {

			private int nextIndex = -1;

			/**
			 * Returns true if there exists a next element in the collection
			 */
			public boolean hasNext() {
				return nextIndex < size - 1;
			}

			/**
			 * Returns the next element in the collection starting at the index
			 * 0
			 * @throws IndexOutOfBoundsException -- if hasNext() evaluates to false;
			 */
			public E next() {
				if (!(hasNext())) {
					throw new IndexOutOfBoundsException();
				} else {
					++nextIndex;
					return baseArray[nextIndex];
				}
			}

			/**
			 * Removes the last element returned by this iterator. This method
			 * is only applicable once per call to next() of this iterator.
			 * @throws NoSuchElementException -- if nothing has been returned by this iterator
			 */
			public void remove() {
				if (nextIndex != -1) {
					int removeIndex = nextIndex--;
					BinarySearchSet.this.remove((baseArray[removeIndex]));
				}
				else{
					throw new IllegalStateException();
				}
			}
		};
		return iterate;
	}
	
	/**
	 * Method removes an element from the collection
	 * @param element - An object
	 * @return --
	 * 			True if the element specified was removed.
	 * 			False if the element does not exist in the collection
	 */
	public boolean remove(Object element) {
		int index = searchOrGetInsertIndex((E) element, false);
		if(index == -1){
			return false;
		}
		removeHelper(index);
		return true;
	}

	@Override
	/**
	 * Removes all items in the given collection that are contained within the instance of BinarySearchSet this method is called upon.
	 * @return True if the set was changed as a result of this method false otherwise
	 */
	public boolean removeAll(Collection<?> elements) {
		boolean wasRemoved = false;
	
		for(Object e : elements){
			if(remove(e)){
				wasRemoved = true;
			}
		}
		return wasRemoved;
	}
	@Override
	/**
	 * @return returns the number of elements of the instance of BinarySearchSet this method is called upon
	 */
	public int size() {
		return size;
	}
	
	/**
	 * returns an array representing the provided BinarySearchSet
	 * @return a basic array representing the given BinarySearchSet
	 * 
	 */
	public Object[] toArray() {
		E[] copyArray = (E[]) new Object[size];
		for (int i = 0; i < size; i++) {
			copyArray[i] = baseArray[i];
		}
		return copyArray;
	}
	
	
	/**
	 * Returns the element at the specified location
	 * @param index - integer
	 * @return - Data at the index
	 * @throws out of bound exception if the index is outside of the base array
	 */
	public E get(int index) {
		if (index >= size){
			throw new IndexOutOfBoundsException();
		}
		
		return baseArray[index];
	}
	
	
	/**
	 * This is a helper method that removes an element and 
	 * moves the consecutive elements up by one index
	 * 
	 * @param targetIndex the index to be overwritten
	 * @return a new array with the given index overwritten and other elements 
	 * decremented  by one index
	 * 
	 */
	public E[] removeHelper(int targetIndex){
		for (int index = targetIndex ; index < size; index++){
			baseArray[index] = baseArray[index+1];
		}
		size--;
		return baseArray;
	}
	
	/**
	 * Helper method that is used to find where the given object should be inserted into the given BinarySearchSet
	 * 
	 * @param element Element to search for
	 * @return The index that the given element should be inserted at or -1 if the element exists in the BinarySearchSet
	 */
	public int searchOrGetInsertIndex(E element, boolean isInsertOn) {
		int low = 0;
		int high = size-1;
		
		if (isComparable) {
			if (isInsertOn && (size == 0 || ((Comparable <E>)element).compareTo(baseArray[0]) < 0)) {
				return 0;
			}
			
			if (isInsertOn && ((((Comparable<E>) element)).compareTo(baseArray[high]) > 0)) {
				return high+1;
			}
			
			while (low <= high) {
				int mid = (high+low)/2;
				if (isInsertOn && (mid > 0) && 
					(((Comparable<E>) element)).compareTo(baseArray[mid-1]) > 0 && 
					(((Comparable<E>) element).compareTo(baseArray[mid]) < 0)) {
					return mid;
				}
				if ((((Comparable<E>) element)).compareTo(baseArray[mid]) < 0) {
					high = mid - 1;
				}
				else if ((((Comparable<E>) element)).compareTo(baseArray[mid]) > 0) {
					low = mid + 1;	
				}
				else {
					return mid;
				}
			}
			return -1;
		}
		else {
			if (isInsertOn && (size == 0 || (itemComparator.compare(element,baseArray[0]) < 0))) {
				return 0;
			}
			
			if (isInsertOn && (itemComparator.compare(element,baseArray[high]) > 0)) {
				return high+1;
			}
			
			while (low <= high) {
				int mid = (high+low)/2;
				if (isInsertOn && (mid > 0) && 
					(itemComparator.compare(element,baseArray[mid-1]) > 0) && 
					(itemComparator.compare(element,baseArray[mid]) < 0)) {
					return mid;
				}
				if (itemComparator.compare(element,baseArray[mid]) < 0) {
					high = mid - 1;
				}
				else if (itemComparator.compare(element,baseArray[mid]) > 0) {
					low = mid + 1;	
				}
				else {
					return mid;
				}
			}
			return -1;
		}
	}
}
