package assignment03;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator over a collection.
 * @author Cole Cruz and Colton Haacke
 * u1065058 and u0935270
 * @param <E> - The object being collected.
 */
public class SearchSetIterator<E> implements Iterator<E>{

    private int count;
    private BinarySearchSet<E> binarySearchSet;
    private E latestElement;
    
    public SearchSetIterator (BinarySearchSet<E> givenBinarySearchSet) {
	count = 0;
	binarySearchSet = givenBinarySearchSet;
    }
    
    /**
     * 
     * @return true if iterator has more elements
     */
    public boolean hasNext() {
	return count < binarySearchSet.size();
    }
    
    /**
     * 
     * @return - the next element in the iterator
     * @throws NoSuchElementException - if there is no next element
     */
    public E next() throws NoSuchElementException {
	latestElement = binarySearchSet.get(count);
	count++;
	return latestElement;
    }

    /**
     * Removes the last element returned by the iterator
     */
    public void remove() {
	binarySearchSet.remove(latestElement);
	count--;
    }
}
