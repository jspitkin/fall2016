package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * This class represents a sorted set of generic elements which uses binary search to optimally
 * add elements in the correct order so that it may remain sorted. It also contains many 
 * other methods that may be helpful. The generic elements may be sorted by natural ordering 
 * or by a given comparator.
 * @author Zachary Cutler (u1025642) and Alessandro Ljubicic (U0753409)
 *
 * @param <E> - 
 */
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {

	private E[] data; //backing array
	private int count; //tracking amount of objects in array
	private Comparator comparator; 

	public BinarySearchSet() {
		data = (E[]) new Object[8];
		count = 0;

	}

	public BinarySearchSet(Comparator<? super E> comparator) {
		data = (E[]) new Object[8];
		count = 0;
		this.comparator = comparator;
	}
	/**
	 * The comparator used to order the elements in this set, or null if this set uses 
	 * the natural ordering of its elements (i.e., uses Comparable). 
	 */
	@Override
	public Comparator<? super E> comparator() {
		return comparator;
	}
	
	/**
	 * Finds the first element in the BinarySearchSet
	 * @return the first item in the set, or NoSuchElementException if set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {

		if (count > 0) {
			return data[0];
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * adds the given element to the set if it is not a duplicate or null
	 * @return true if the element was added, false otherwise
	 */
	@Override
	public boolean add(E element) {	
		
		if(element == null){
			return false;
		}
		
		if(this.contains(element)) {
			return false;
		}
		
		if (count == data.length - 1) {
			E[] newData = (E[]) new Object[data.length * 2];
			for (int transfer = 0; transfer < count; transfer++) {
				newData[transfer] = data[transfer];
			}
			data = newData;

		}
		int insertLocation = binarySearch(element);
		for (int moveUp = count; moveUp > insertLocation; moveUp--) {
			data[moveUp] = data[moveUp - 1];
		}
		data[insertLocation] = element;
		count++;
		return true;
	}
	/**
	 * adds all of the elements in a given collection to the set if they are not null or duplicates
	 * @return true if the set has changed, false otherwise
	 */
	@Override
	public boolean addAll(Collection<? extends E> elements) {
		for (E ele : elements) {
			if (ele != null && !this.contains(ele)) {
				this.add(ele);
			} else {
				return false;
			}
		}
		return true;
	}
	/**
	 * clears the set which it is called upon, completely emptying the set
	 */
	@Override
	public void clear() {
		for (E element : data) {
			this.remove(element);
		}
		count = 0;
	}
	/**
	 * determines if the set contains the given element
	 * @return true if the set contains the element, false otherwise
	 */
	@Override
	public boolean contains(Object element) {
		
		if(element == null){
			return false;
		}
		
		if(count == 0) {
			return false;
		}
		
		if(count == 1){
			if(this.comparator() == null){
				if(((Comparable<E>) element).compareTo(data[0]) == 0){
					return true;
				}
				else{
					return false;
				}
					
			}
			else{
				if (comparator.compare(element, data[0]) == 0){
					
					return true;
				}
				else{
					return false;
				}
			}
		}

		if (this.comparator() == null) {
			int j = binarySearch(element);
			if(data[j] == null){
				return false;
			}
			if (((Comparable<E>) element).compareTo(data[j]) == 0) {
				return true;
			} 
			
			else {
				return false;
			}
		} 
		
		else {
			int j = binarySearch(element);
			if (comparator.compare(element, data[j]) == 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	/**
	 * setter used strictly for testing
	 * @param x
	 */
	public void setData(E[] x) {
		data = x;
	}
	/**
	 * setter used strictly for testing
	 * @param x
	 */
	public void setCount(int x) {
		count = x;
	}
	/**
	 * determines if the set contains all of the elements in a given collection
	 * @return true if the set contains all of the given elements, false otherwise
	 */
	@Override
	public boolean containsAll(Collection<?> elements) {

		int numChanged = elements.size();

		for (Object temp : elements) {
			if (this.contains(temp)) {
				numChanged--;
			}
		}
		if (numChanged == 0) {
			return true;
		}
		return false;
	}
	/**
	 * determines if the set is empty
	 * @return true if the set is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (count == 0) {
			return true;
		}
		return false;
	}
	
	
	@Override
	public Iterator<E> iterator() {
		
		return new Iterator<E>() {
			
			int counter = 0;

			@Override
			public boolean hasNext() {
				if((data[counter]) == null) {
					return false;
					}
				else {
					return true;
					
				}
			}

			@Override
			public E next() {
				
				counter++;
				return data[counter - 1];
				
			}
			
			@Override
			public void remove() {
				
				
				
				data[counter] = null;
				for(int i = counter; i <= count; i++) {
					if(i == count){
						data[i] = null;
						counter--;
					}
					data[i] = data[i+1];
				}
				 
			}
		};
	}

	/**
	 * removes a given element from the set
	 * @return true if the given element is in the set and was removed, false otherwise
	 */
	@Override
	public boolean remove(Object element) {
		for (int currentPosition = 0; currentPosition < data.length; currentPosition++) {
			if (data[currentPosition] == element) {
				for (int moveDown = currentPosition; moveDown < count; moveDown++) {
					data[moveDown] = data[moveDown + 1];
				}
				count--;
				return true;
			}
		}
		return false;
	}
	/**
	 * Removes all of the elements in the given set from the collection if they exist 
	 * in the collection.
	 * @return true if the set has changed, false otherwise
	 */
	@Override
	public boolean removeAll(Collection<?> elements) {

		int numChanged = 0;

		for (Object temp : elements) {
			if (this.contains(temp)) {
				this.remove(temp);
				numChanged++;
			}
		}

		if (numChanged > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * determines the size of the set
	 * @return amount of objects in the set
	 */
	@Override
	public int size() {
		return count;
	}
	/**
	 * Gives the elements in the set as a standard array
	 * @return - array of the sorted elements in the set
	 */
	@Override
	public Object[] toArray() {
		E[] dataArray = (E[]) new Object[count];

		for (int ele = 0; ele < count; ele++) {
			dataArray[ele] = data[ele];
		}

		return dataArray;
	}
	/**
	 * Finds the last element in the BinarySearchSet
	 * @return the last item in the set, or NoSuchElementException if set is empty
	 */
	@Override
	public E last() throws NoSuchElementException {
		if (count > 0) {
			return data[count - 1];
		} else {
			throw new NoSuchElementException();
		}
	}
	/**
	 * This is a helper method which finds the position which the given element
	 * should be located using binary search
	 * @param element - given element to be located
	 * @return index location of given element.
	 */
	public int binarySearch(Object element) {

		if (count == 0) {
			return 0;
		}

		if (comparator == null) {
			int low = 0;
			int high = count - 1;
			int mid = 0;

			while (true) {
				mid = (high + low) / 2;
				if (((Comparable<E>) element).compareTo(data[mid]) == 0) {
					return mid;
				}

				else if (((Comparable<E>) element).compareTo(data[mid]) > 0) {
					low = mid + 1;
					if (low > high) {
						return mid + 1;
					}
				}

				else {
					high = mid - 1;
					if (low > high) {
						return mid;
					}
				}
			}
		} 
		else {

			int low = 0;
			int high = count - 1;
			int mid = 0;

			while (true) {
				mid = (high + low) / 2;
				if (data[mid] == element) {
					return mid;
				}

				else if (comparator.compare(element, data[mid]) > 0) {
					low = mid + 1;
					if (low > high) {
						return mid + 1;
					}
				}

				else {
					high = mid - 1;
					if (low > high) {
						return mid;
					}
				}
			}
		}
	}

}