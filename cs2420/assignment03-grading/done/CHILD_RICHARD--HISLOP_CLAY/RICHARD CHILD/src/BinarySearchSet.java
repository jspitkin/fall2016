/**
 * @author Richard Child u0581030
 * @author Clayton Hislop u0515744
 */


package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E>{
	
	private E[] searchSet;
	private Comparator<? super E> comparator;
	private int last;	//index of last element in the searchSet
	private int max;	//size of underlying array
	private int size; 	//number of items in the searchSet
	
	/**
	 * Constructor that uses natural ordering for its elements
	 */
	public BinarySearchSet(){
		searchSet = (E[]) new Object[10];
		this.max = 10;
	}
	/**
	 * Constructor that uses comparator to order elements
	 * @param comparator
	 */
	public BinarySearchSet(Comparator<? super E> comparator){
		this.comparator = comparator;
		searchSet = (E[]) new Object[10];
		max = 10;
	}
	
	/**
	 * @return the comparator used in this set, or
	 * null if the set uses natural ordering
	 */
	@Override
	public Comparator<? super E> comparator() {
		return comparator;
	}
	
	/**
	 * @return the first (lowest, smallest) element in the sortedSet
	 * @throws NoSuchElementException if set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {
		if(this.searchSet[0] == null){
			throw new NoSuchElementException();
		}
		return this.searchSet[0];
	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E last() throws NoSuchElementException {
		if(this.searchSet[0] == null){
			throw new NoSuchElementException();
		}
		return this.searchSet[last];
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
		if(this.last == max-1){
			expand();
		}
		if(searchSet[0] == null){
			searchSet[0] = element;
			size++;
			return true;
		}
		int insertIndex = 0;
		E[] copy = (E[]) new Object[max];
		
		if(comparator == null)
			insertIndex = this.findIndex(element);
		else
			insertIndex = this.findIndexComparator(element);
		
		if(insertIndex == -1)
			return false;
		
		last++;
		for(int i = 0; i < insertIndex; i++){
			copy[i] = searchSet[i];
		}
		copy[insertIndex] = element;
		
		for(int j = insertIndex + 1; j <= last; j++){
			copy[j] = searchSet[j-1];
		}
		searchSet = copy;
		size++;
		return true;
	}

	/**
	 * Doubles the size of the underlying array for class
	 */
	private void expand() {
		E[] copy = (E[]) new Object[max*2];
		for(int i = 0; i < max; i++){
			copy[i] = searchSet[i];
		}
		searchSet = copy;
		max = max*2;
		
	}
	
	/**
	 * Finds the index in arrary where element should be inserted
	 * to maintain sorted order. For elements that implement
	 * Comparable.
	 * @param element -- the object we are trying to add to set
	 * @return index element should reside, -1 if element already exists in set
	 */
	private int findIndex(E element){
		int insertIndex;
		int min = 0;
		int max = last;
		int middle;
		while(true){
			
			middle = (max - min)/2 + min;
			if(((Comparable<E>)element).compareTo(searchSet[middle]) > 0){
				min = middle + 1;
				
				if (min >= size){
					insertIndex = size;
					break;
				}
			}
			else if(((Comparable<E>)element).compareTo(searchSet[middle]) < 0){
				if(middle == 0){
					insertIndex = 0;
					break;
				}
				if(((Comparable<E>)element).compareTo(searchSet[middle-1]) > 0){
					insertIndex = middle;
					break;
				}
				max = middle - 1;
			}
			else if(((Comparable<E>)element).compareTo(searchSet[middle]) == 0){
				insertIndex = -1;;
				break;
			}
		}
		return insertIndex;
	}
	
	/**
	 * Finds the index in arrary where element should be inserted
	 * to maintain sorted order. For elements that implement
	 * Comparator.
	 * @param element -- the object we are trying to add to set
	 * @return index element should reside, -1 if element already exists in set
	 */
	private int findIndexComparator(E element){
		int insertIndex;
		int min = 0;
		int max = last;
		int middle;
		
		while(true){
			
			middle = (max - min)/2 + min;
			if(this.comparator().compare(element,  searchSet[middle]) > 0){
				min = middle + 1;
				
				if (min >= size){
					insertIndex = size;
					break;
				}
			}
			else if(this.comparator().compare(element,  searchSet[middle]) < 0){
				if(middle == 0){
					insertIndex = 0;
					break;
				}
				if(this.comparator().compare(element,  searchSet[middle-1]) > 0){
					insertIndex = middle;
					break;
				}
				max = middle - 1;
			}
			else if(this.comparator().compare(element,  searchSet[middle]) == 0){
				insertIndex = -1;
				break;
			}
		}
		return insertIndex;
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
		boolean isAdded = false;
		for(E el : elements){
			if (el != null) {
				if(this.add(el))
					isAdded = true;
			}
		}
		return isAdded;
	}

	@Override
	public void clear() {
		searchSet = (E[]) new Object[10];
		max = 10;
		size = 0;
		last = 0;
	}

	@Override
	public boolean contains(Object element) {
		boolean contained;
		
		if(comparator == null)
			contained = checkExists(element);
		else
			contained = checkExistsComparator(element);
		
		return contained;
	}
	
	/**
	 * Checks to see if element already exists in set.
	 * Is used for elements which implement Comparable.
	 * @param element
	 * @return true if elements already exists in set, false otherwise.
	 */
	private boolean checkExists(Object element){
		int min = 0;
		int max = last;
		int middle;
		
		while ( min <= max) {
			middle = (max - min)/2 + min;
			if(((Comparable<E>)element).compareTo(searchSet[middle]) > 0){
				min = middle + 1;
			}
			else if (((Comparable<E>)element).compareTo(searchSet[middle]) < 0) {
				max = middle - 1;
			}
			else {
				return true;
			}		
		}
		return false;
	}
	
	/**
	 * Checks to see if element already exists in set.
	 * Is used for elements which implement Comparator.
	 * @param element
	 * @return true if elements already exists in set, false otherwise.
	 */
	private boolean checkExistsComparator(Object element){
		int min = 0;
		int max = last;
		int middle;
		
		while ( min <= max) {
			middle = (max - min)/2 + min;
			if(this.comparator().compare((E) element, searchSet[middle]) > 0){
				min = middle + 1;
			}
			else if (this.comparator().compare((E) element, searchSet[middle]) < 0) {
				max = middle - 1;
			}
			else {
				return true;
			}		
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
		
		for (Object el : elements) {
			if (!this.contains(el)) {
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
		if(searchSet[0] == null)
			return true;
		return false;
	}
	
	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator() {
		
		return new BinarySearchSetIterator();
	}
	
	protected class BinarySearchSetIterator implements Iterator<E>{
		
		private int current;
		private boolean hasRemoved;

		public boolean hasNext(){
			
			return current < size ;
		}

		public E next() {
			if (!(current < size)){
				throw new NoSuchElementException();
			}
			hasRemoved = false;
			return searchSet[current++];
		}
		
		public void remove(){
			if(current == 0 || hasRemoved)
				throw new IllegalStateException();

			iteratorRemove(current);
			last--;
			size--;
			current--;
			
			hasRemoved = true;
		}
	}
	
	private void iteratorRemove(int current) {
		
		E[] copy = (E[]) new Object[max];
		for(int i = 0; i < current; i++){
			copy[i] = searchSet[i];
		}
		for(int j = current; j < last; j++){
			copy[j] = (E) searchSet[j+1];
		}
		searchSet = copy;
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
		
		Object[] copy = new Object[max];
		int removeIndex;
		
		if(comparator == null)
			removeIndex = this.find(element);
		else
			removeIndex = this.findComparator(element);
		
		if(removeIndex == -1)
			return false;
		
		for(int i = 0; i < removeIndex; i++){
			copy[i] = searchSet[i];
		}
		for(int j = removeIndex; j < last; j++){
			copy[j] = searchSet[j+1];
		}
		
		searchSet = (E[]) copy;
		last--;
		size--;
		return true;
	}
	
	/**
	 * Finds the index of element in set.
	 * Used for elements which implement Comparable.
	 * @param element -- object to be found in BinarySearchSet
	 * @return the index of the object requested if exists in set, -1 otherwise
	 */
	private int find(Object element){
		int min = 0;
		int max = last;
		int middle;
		
		while ( min <= max) {
			middle = (max - min)/2 + min;
			if(((Comparable<E>)element).compareTo(searchSet[middle]) > 0){
				min = middle + 1;
			}
			else if (((Comparable<E>)element).compareTo(searchSet[middle]) < 0) {
				max = middle - 1;
			}
			else {
				return middle;
			}		
		}
		return -1;
	}
	
	/**
	 * Finds the index of element in set.
	 * Used for elements which implement Comparable.
	 * @param element -- object to be found in BinarySearchSet
	 * @return the index of the object requested if exists in set, -1 otherwise
	 */
	private int findComparator(Object element){
		int min = 0;
		int max = last;
		int middle;
		
		while ( min <= max) {
			middle = (max - min)/2 + min;
			if(this.comparator().compare((E) element,  searchSet[middle]) > 0){
				min = middle + 1;
			}
			else if (this.comparator().compare((E) element, searchSet[middle]) < 0) {
				max = middle - 1;
			}
			else {
				return middle;
			}		
		}
		return -1;
		
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
		boolean wasRemoved = false;
		for(Object o : elements){
			if(o != null)
				if(this.remove(o))
					wasRemoved = true;
		}
		return wasRemoved;
	}
	
	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
		E[] outputArray = (E[]) new Object[last + 1];
		for(int i = 0; i < last + 1; i++){
			outputArray[i] = searchSet[i];
		}
		return outputArray;
	}
}
