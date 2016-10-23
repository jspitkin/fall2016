//Cole Cruz
package assignment07;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for any list type.
 * 
 * @author Cole Cruz
 *
 * @param <E>
 *            -- iterator type.
 */
public class ListIterator<E> implements Iterator<E> {

	private DoublyLinkedList<E> list;
	private int count = 0;
	private boolean correctState = false;

	/**
	 * Creates an iterator for the provided list.
	 * 
	 * @param listToIterate
	 *            -- list to iterate.
	 */
	public ListIterator(DoublyLinkedList<E> listToIterate) {
		list = listToIterate;
	}

	/**
	 * Returns true if the iteration has more elements.
	 * 
	 * @return true if the iteration has more elements.
	 */
	@Override
	public boolean hasNext() {
		if(count < list.size()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the next element in the iteration.
	 * Throws NoSuchElementException if the iteration has no more elements.
	 * 
	 * @return the next element in the iteration.
	 */
	@Override
	public E next() throws NoSuchElementException {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		correctState = true;
		count++;
		return list.get(count - 1);
	}

	/**
	 * Removes from the underlying collection the last element returned by this
	 * iterator (optional operation). This method can be called only once per
	 * call to next(). The behavior of an iterator is unspecified if the
	 * underlying collection is modified while the iteration is in progress in
	 * any way other than by calling this method.
	 * Throws IllegalStateException if the next method has not yet been called, 
	 * or the remove method has already been called after the last call to the 
	 * next method.
	 */
	@Override
	public void remove() throws IllegalStateException {
		if(!correctState) {
			throw new IllegalStateException();
		}
		correctState = false;
		list.remove(count - 1);
	}

}
