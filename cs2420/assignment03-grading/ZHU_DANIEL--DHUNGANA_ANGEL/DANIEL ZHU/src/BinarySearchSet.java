package assignment03;



import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * 
 * @author Daniel Zhu, u0922894, Angel Dhungana, u1021745
 *
 *
 * @param <E>
 */
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {
	private E[] data = (E[]) new Object[10];
	private int numOfData = 0;
	private boolean newComparator;
	private Comparator<? super E> arrayComparator = null;

	public BinarySearchSet() {
		newComparator = false;
	}

	public BinarySearchSet(Comparator<? super E> comparator) {
		newComparator = true;
		arrayComparator = comparator;
	}

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> comparator() {
		// TODO Auto-generated method stub
		if (newComparator == false) {
			return null;
		}
		return arrayComparator;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return data[0];
	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return data[data.length - 1];
	}
	/**
	 * Performs a Binary search to see if the element is in the array or not.
	 * 
	 * @param o
	 *            -- element to be added to be searched for
	 * @return the index where the element is or should be.
	 */
	public int BinarySearch(E element){
		int low=0;
		int high=numOfData -1;
		int mid=(low+high)/2;
		while(high>=low){
			int result = myCompare(data[mid],element);
			if(result >0){ // if midpoint is > than the element we're looking for
				high=mid-1;
			}
			else if(result < 0) {
				low=mid+1;
			} else {
				return mid;
			}
			mid=(low+high)/2;
		}
		return mid;
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
		
		if (numOfData == data.length ) {
		E[] newDataWithDoubleSize = (E[]) new Object[data.length * 2];
			for (int i = 0; i < data.length; i++) {
				newDataWithDoubleSize[i] = data[i];
			}

			data = newDataWithDoubleSize;
		}
		if(numOfData==0){
				data[0]=element;
				numOfData++;
				return true;
		}
		int elementPoint=BinarySearch(element);
		int compValueAtInsert = myCompare(element,data[elementPoint]);
		if(compValueAtInsert==0){
			return false;
		}
		
		else{
			for (int j = numOfData - 1; j > elementPoint; j--) {
				data[j + 1] = data[j];
			}
			if(compValueAtInsert > 0) {
				data[elementPoint + 1] = element;
			} else {
				data[elementPoint +1] = data[elementPoint];
				data[elementPoint]=element;
			}
			numOfData++;
			return true;
			}
		}
		// TODO Auto-generated method stub


	/**
	 * Comparing method, based on whether a comparator method had been passed in or not
	 * 
	 * @param o
	 *            -- element to be added to this set
	 * @return int depending on comparator, 1 if lhs>rhs, 0 if lhs=rhs, -1 if lhs<rhs
	 */

	private int myCompare(E lhs, E rhs) {
		if (arrayComparator == null) {
			return ((Comparable<E>) lhs).compareTo(rhs);
		} else {
			// something else!
			return arrayComparator.compare(lhs, rhs);
		}
	}


	/* } */
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
		// TODO Auto-generated method stub
		boolean change=false;
		for (E item : elements) {
			this.add(item);
		}

		return false;
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		for (int numOfItems = 0; numOfItems < numOfData; numOfItems++)
			data[numOfItems] = null;

		numOfData = 0;

	}

	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@Override
	public boolean contains(Object element) {
		// TODO Auto-generated method stub
		int elementPoint=BinarySearch((E)element);
		if(myCompare(data[elementPoint],(E)element)==0){
			return true;
		}
		else{
		return false;
		}
	}

	/**
	 * @param c
	 *            -- collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	@Override
	public boolean containsAll(Collection<?> elements) {
		// TODO Auto-generated method stub
		
		E[] newData = (E[]) elements.toArray();
		int sizeOfElements = elements.size();
		
			for (int elementsOfCollection = 0; elementsOfCollection < sizeOfElements; elementsOfCollection++) {
				if (this.contains(newData[elementsOfCollection])==false) {
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
		// TODO Auto-generated method stub
		if (this.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub

		return new Iterator<E>() {

			/// iterator stuff
			 private int index;
	            private boolean hasRemoved;
	            @Override
	            public boolean hasNext() {
	                // TODO Auto-generated method stub
	                if(index < data.length )
	                {
	                    return true;
	                }
	                return false;

	            }

	            @Override
	            public E next() {
	                if( !hasNext())
	                {
	                	throw new NoSuchElementException();
	                }
	                	hasRemoved = false;
	                return data[index++];
	                
	            }

	            @Override
	            public void remove()
	            {
	            	if( hasRemoved = false)
	            	{
	                data[index--] = data[numOfData];
	                
	            	}
	            	else
	            	{
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
		// TODO Auto-generated method stub
		int elementPoint=BinarySearch((E)element);
		if(myCompare(data[elementPoint],(E)element)!=0){
			return false;
		}
		else{
			for (int j =elementPoint; j < data.length-1; j++) {
				data[j] = data[j+1];
			}
			numOfData--;
			return true;
		}
		/*for (int i = 0; i < this.data.length; i++) {
			if (element != null && myCompare(data[i], (E) element) == 0) {

				for (int j = i + 1; j < data.length; i++, j++) {
					data[i] = data[j];
				}

				numOfData--;
				return true;
			}
		}
		return false;*/

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
		// TODO Auto-generated method stub
		E[] removalData = (E[]) elements.toArray();
		
			for (int elementsOfCollection = 0; elementsOfCollection < elements.size(); elementsOfCollection++) {
				if(this.remove(removalData[elementsOfCollection])==false){
					return false;
				}
			}


		return true;
	}

	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numOfData;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return data;
	}

}
