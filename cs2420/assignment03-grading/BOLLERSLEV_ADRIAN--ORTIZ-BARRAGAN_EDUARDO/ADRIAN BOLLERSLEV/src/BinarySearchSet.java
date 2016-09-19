/**
@Author Adrian
*/

package assignment03;

import java.lang.*;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class BinarySearchSet<E> implements SortedSet<E>,Iterable<E>{
	private E[] list;
	private Comparator<? super E> comp;
	
	/**
	 * The basic constructor for this class. The size of the list is arbitrary and 
	 * this constructor does not include a comparator.
	 */
	public BinarySearchSet(){
		list = (E[]) new Object[10];
		comp = null;
	}
	/**
	 * The basic constructor for this class. The size of the list is arbitrary
	 */
	public BinarySearchSet(Comparator<? super E> comparator){
		list = (E[]) new Object[10];
		comp = comparator;
	}
	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> comparator() {
		return this.comp;
	}
	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E first() throws NoSuchElementException {
		//if the first element is null there is nothing in the list
		if(list[0] == null){
			throw new NoSuchElementException();
		}
		return list[0];
	}
	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E last() throws NoSuchElementException {
		//if the first element is null there is nothing in the list
		if(list[0] == null){
			throw new NoSuchElementException();
		}
		//the call of size returns the size of the list without null values, so the last index is one less than the size
		return list[size()-1];
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
		//create a list of arbitrary size
		E[] list2 = (E[]) new Object[size()+2];
		//if there is nothing in the list then simply add one element
		if (list[0] == null){
			list[0]= element;
			return true;
		}
		//if the value is already in the list don't add it again
		if(contains(element)){
			return false;
		}
		//populate the new list with all the values
		int count = 0;
		while(list[count]!=null){
			list2[count] = list[count];
			count++;
		}
		//add the element to the new list
		list2[count] = element;
		;
		
		list = selectsort(list2);
		return true;
	}
	/**
	 * 
	 * @param list2 takes the list in and does a generic selection sort
	 * @return sorted list
	 */
	public E[] selectsort(E[] list2){
		for(int i=0; i<list2.length -1; i++){
			//place holder
	        int place = i;
	        for(int j=i+1; j<list2.length; j++){
	        	//different cases depending on whether we have comparator
	        	if(comp == null){
	        		//cast comparable so we can compare generics
	        		Comparable<E> temp = (Comparable<E>) list2[place];
	        		if(list2[j]!=null){
	        			if(temp.compareTo((list2[j])) > 0  ){
			                place = j;
			            }
	        		}
	        		
	        	}
	        	else{
	        		//if we have a comparator use that
	        		if(comp.compare(list2[place], list2[j])>0)
		            {
		                place = j;
		            }
	        	}
	            
	        }
	        E swap = list2[place];
	        list2[place] = list2[i];
	        list2[i] = swap;

	    }
		return list2;
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
		//for each element, if you can't add it return false, otherwise return true
		for(E element : elements){
			if(!add(element)){
				return false;
			}
		}
		return true;
	}
	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	@Override
	public void clear() {
		//the size is arbitrary
		list = (E[]) new Object[10];
		
	}
	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@Override
	public boolean contains(Object element) {
		//a binary search to find the element
		int l = 0;
		int h = size()-1;
		if(element == null){
			return true;
		}
		while(h >= l){
			//find the mid point
			int mid = (l+h)/2;
			if(list[mid]==element){
				return true;
			}
			if(comp == null){
				Comparable<Object> temp = (Comparable<Object>) list[mid];
        		if(temp.compareTo((element)) < 0  ){
		            l = mid + 1;
		        }
        		if(temp.compareTo( (element)) > 0  ){
		            h = mid - 1;
		        }
        		
			}
			else{
				if(comp.compare(list[mid], (E) element)<0){
					l = mid + 1;
				}
				if(comp.compare(list[mid], (E) element)<0){
					h = mid - 1;
				}
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
		//for each element if it is not contained return false
		for(Object el : elements){
			if(!contains(el)){
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean isEmpty() {
		return list[0] == null;
	}
	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order
	 */
	@Override
	public Iterator<E> iterator() {
		//return an iterator and override the methods
		return new Iterator<E>(){
			//set an index and checker if you can remove the current index
			int indx = 0;
			boolean canremove = false;
			
			//check if you can move further to the next index
			@Override
			public boolean hasNext() {
				return (indx < (size()) && (list[indx] != null));
			}
			//advance the iterator forward one, if it can move
			@Override
			public E next() {
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				canremove = true;
				return list[indx++];
			}
			//remove the current index
			@Override
			public void remove() {
				if(!canremove){
					throw new IllegalStateException();
				}
				itRemove(indx);
				
				canremove = false;
			}
		};
	}
	//a simple method to remove the current index, gets around the naming issues
	public void itRemove(int indx) {
		if((indx >=0)&&(indx < list.length - 1)){
			remove(list[indx]);
		}
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
		//create a new list and a checker value
		if(!contains(element)){
			return false;
		}
		boolean temp = false;
		E[] list2  = (E[]) new Object[list.length];
		//scan through every element
		for(int i = 0; i < list.length; i++){
			//if the element is not the specified one continue filling the area
			if(!(list[i]==element)){
				list2[i] = list[i];
			}
			else{
				temp = true;
			}
		}
		//after the for loop has ran set the list
		if(temp){
			list = list2;
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
		//for each element in the elements if it cannot be removed return false
		for(Object element : elements){
			if(!remove(element)){
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
		//initialize counter variable and check through all of the elements
		int count = 0;
		for(int i = 0; i < list.length; i++){
			//once we get to a null element return the size
			if(list[i]==null){
				if(i == 0){
					return count;
				}
				else{
					count = i;
					return count;
				}
			}
		}
		return count;
	}
	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray() {
		//return the list and make sure it is cloned so it cannot be mutated
		return list.clone();
	}
	

}


