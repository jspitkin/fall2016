package assignment06;

/**
 * @arthur Chenxi Sun
 * @Uid u0455173
 * 
 * This class is a DoublyLinkedList that can traverse forward and backward for get method
 * It is very similar to LinkedList as it has addFirst addLast and getFirst getLast.
 * The difference is that if the get index in the first half it will traverse forward to get the node data
 * if the index is in the second half it will traverse backward from the end to get the data
 */


import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	private int size = 0;
	private MyNode<E> begin;
	private MyNode<E> end;

	public DoublyLinkedList() {

	}

	public static void main(String[] args) {
	

	}

	/**
	 * addFirst to the linkedlist
	 * 
	 * @param item
	 */

	public void addFirst(E item) {
		if (isEmpty()) {
			begin = new MyNode<E>(item, null, null);
			end = begin;

		}

		else {
			MyNode<E> NewNode = new MyNode<E>(item, begin.prev, begin);
			begin.prev = NewNode;
			NewNode.next = begin;
			begin = NewNode;

		}
		size++;
	}

	/**
	 * remove the first item in the list will throw exception if list is empty
	 * 
	 * 
	 * @return E
	 */
	public E removeFirst() {
		if (isEmpty()) {
			throw new IllegalStateException();
		}

		else if (size == 1) {
			E olddata;
			olddata = begin.data;
			begin = null;
			size--;
			return olddata;
		} else {
			MyNode<E> temp = begin;

			begin = temp.next;
			size--;
		}

		return begin.data;

	}

	/**
	 * remove the last item in the list will throw exception if list is empty
	 * 
	 * 
	 * @return E
	 */
	public E removeLast() {
		if (isEmpty()) {
			throw new IllegalStateException();
		} else if (size == 1) {
			E olddata;
			olddata = end.data;
			end = null;
			size--;
			return olddata;
		} else {
			MyNode<E> temp = end;

			end = temp.prev;

			size--;
		}
		return end.data;

	}

	/**
	 * get the first item in the list will throw exception if list is empty
	 * 
	 * @return E
	 */
	public E getFirst() {
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		return begin.data;
	}

	/**
	 * get the last item in the list
	 * 
	 * 
	 * @return E
	 */
	public E getLast() {
		if (isEmpty()) {
			throw new IllegalStateException();
		}
		return end.data;
	}

	/**
	 * add the item to the end of the list
	 * 
	 * @param item
	 */
	public void addLast(E item) {
		if (isEmpty()) {
			end = new MyNode<E>(item, null, null);
			begin = end;

		}

		else {
			MyNode<E> NewNode = new MyNode<E>(item, end, null);
			end.next = NewNode;
			NewNode.prev = end;
			end = NewNode;

		}
		size++;
	}

	/**
	 * This function add the item to the end of the list and return true if
	 * successful
	 * 
	 * param Object return boolean
	 */
	@Override
	public boolean add(Object arg0) {

		if (isEmpty()) {
			end = new MyNode<E>((E) arg0, null, null);
			begin = end;

		}

		else {
			MyNode<E> NewNode = new MyNode<E>((E) arg0, end, null);
			end.next = NewNode;
			NewNode.prev = end;
			end = NewNode;

		}
		size++;
		return true;
	}

	/**
	 * add the object arg1 after index arg0
	 * 
	 * param int, Object
	 */
	@Override
	public void add(int arg0, Object arg1) {
		if (arg0 == 0) {
			addFirst((E) arg1);
		}

		MyNode<E> temp = begin;
		for (int i = 0; i < arg0; i++) {
			temp = temp.next;

		}
		MyNode<E> NewNode = new MyNode<E>((E) arg1, end, null);
		end.next = NewNode;
		NewNode.prev = end;
		end = NewNode;
	}

	/**
	 * add all of the item in the collection and append it to the end of the
	 * list
	 * 
	 * param Collection return boolean
	 */
	@Override
	public boolean addAll(Collection arg0) {
		for (E item : (Collection<E>) arg0) {
			add(item);
		}

		return true;
	}

	@Override
	public boolean addAll(int arg0, Collection arg1) {
		MyNode<E> temp = begin;
		for (int i = 0; i < arg0; i++) {
			temp = temp.next;

		}
		for (E item : (Collection<E>) arg1) {
			MyNode<E> NewNode = new MyNode<E>((E) item, temp, temp.next);
			NewNode.prev=temp;
			temp.next=NewNode;
			NewNode.next=temp.next;
		}

		return true;
	}

	/**
	 * clear the linkedlist
	 */
	@Override
	public void clear() {
		size = 0;

	}

	/**
	 * Go through the linkedlist and return a boolean of true if arg0 is in the
	 * list and false if not
	 * 
	 * param Object return boolean
	 */
	@Override
	public boolean contains(Object arg0) {

		for (int i = 0; i < size; i++) {
			if (get(i).equals((E) arg0)) {
				return true;
			}

		}

		return false;
	}

	/**
	 * Go through the list and check for through the collection and see if the
	 * list contains the collection of items and return true or false
	 * 
	 * param Collection return boolean
	 * 
	 */
	@Override
	public boolean containsAll(Collection arg0) {
		for (E item : (Collection<E>) arg0) {
			if (!contains(item)) {
				return false;
			}
		}

		return true;

	}

	/**
	 * The get method utilize the doublylinkedlist ability to traverse forward
	 * and traverse back the get method will return the item at the index of the
	 * list
	 * 
	 * return E param int
	 * 
	 */
	@Override
	public E get(int arg0) {
		if (arg0 < 0 || arg0 > size - 1) { // if the index is less than 0 or
											// greater than size
			throw new IndexOutOfBoundsException(); // throw
													// indexoutofboundexception
		}

		if (arg0 < (size / 2)) { // if index is the lower half of the list the
									// list will utilize
			if (arg0 == 0) { // .next to return the item
				return getFirst();
			}

			MyNode<E> temp = begin;
			for (int i = 0; i < arg0; i++) {
				temp = temp.next;

			}
			return temp.data;
		} else {
			if (arg0 == (size - 1)) { // check for cases if the index is equal
										// to size and return getLast()
				return getLast();
			} else {

				MyNode<E> temp = end;

				for (int i = size-1; i >= arg0; i--) {
					temp = temp.prev;

				}
				return temp.data;
			}
		}

	}

	/**
	 * This method return the index of the object from the list
	 * 
	 * 
	 * param Object return int
	 */
	@Override
	public int indexOf(Object arg0) {
		MyNode<E> temp = begin;
		for (int i = 0; i < size; i++) {
			if (temp.data.equals((E) arg0)) {
				return i;
			}
			temp = temp.next;

		}
		return -1;
	}

	/**
	 * This method check if the list is empty
	 * 
	 * return boolean;
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub

		return (size == 0);
	}

	/**
	 * This method return the last index of the object that occurs in the list
	 * 
	 * param Object return int
	 * 
	 */

	@Override
	public int lastIndexOf(Object arg0) {

		int lastindex = -1;
		MyNode<E> temp = begin;
		for (int i = 0; i < size; i++) {
			if (temp.data.equals((E) arg0)) {
				lastindex = i;
			}
			temp = temp.next;

		}
		return lastindex;
	}

	/**
	 * This method remove from the list object and return true if remove is
	 * successful if the list does not contains the object it returns false
	 * 
	 * return boolean param Object
	 */
	@Override
	public boolean remove(Object arg0) {

		MyNode<E> temp = begin;
		for (int i = 0; i < size; i++) {
			if (temp.next != null) {
				temp = temp.next;
				if (temp.data.equals(arg0)) {
					size--;
					if (temp.next != null) {
						temp.next.prev = temp.prev;
					} else {
						end = temp.prev;
					}
					if (temp.prev != null) {
						temp.prev.next = temp.next;
					} else {
						begin = temp.next;
					}

					return true;
				}
			}
		}

		return false;
	}

	/**
	 * this method remove E type from the list and returns the E from the list.
	 * if the index is out of bound it throws indexoutofboundexception
	 * 
	 * param E return int
	 * 
	 */
	@Override
	public E remove(int arg0) {
		E olddata;
		if (arg0 < 0 || arg0 > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		MyNode<E> temp = begin;
		for (int i = 0; i < arg0; i++) {

			temp = temp.next;

		}
		olddata = (E) temp.data;
		if (temp.next != null) {
			temp.next.prev = temp.prev;
		} else {
			end = temp.prev;
		}
		if (temp.prev != null) {
			temp.prev.next = temp.next;
		} else {
			olddata = (E) begin.data;
			begin = temp.next;
			return (E) olddata;
		}

		size--;
		return olddata;

	}

	/**
	 * this method removeAll of the item in the collection. If the list is
	 * changed it will return a true. If the list does not contain any of the
	 * items in the collection will return false
	 * 
	 * param Collection return boolean
	 * 
	 */
	@Override
	public boolean removeAll(Collection arg0) {
		boolean checker = false;

		for (E item : (Collection<E>) arg0) {
			if (contains(item)) {
				remove(item);
				checker = true;
			}
		}

		return checker;
	}

	/**
	 * This method will retain all of the items in the collection and remove
	 * anything not in the collection. If the list is changed will return true
	 * otherwise false.
	 * 
	 * param Collection return boolean
	 */
	@Override
	public boolean retainAll(Collection arg0) {
		boolean checker = false;
		MyNode<E> temp = begin;
		for (int i = 0; i < size; i++) {
			if (temp.next != null) {
				temp = temp.next;
				for (E item : (Collection<E>) arg0) {
					if (!temp.data.equals(item)) {
						remove(temp);
						checker = true;
					}
				}
			}
		}

		return checker;
	}

	/**
	 * This method will set the item of type E to index arg0
	 * 
	 * @param int,Object
	 * @return E
	 */
	@Override
	public E set(int arg0, Object arg1) {
		E olddata;
		if (arg0 < 0 || arg0 > size - 1) {
			throw new IndexOutOfBoundsException();
		}

		if (arg0 < (size / 2)) {	//
			if (arg0 == 0) {
				olddata = (E) begin.data;
				begin.data = (E) arg1;
				return olddata;
			}

			MyNode<E> temp = begin;
			for (int i = 0; i < arg0; i++) {
				temp = temp.next;

			}
			olddata = (E) temp.data;
			temp.data = (E) arg1;
			return olddata;
		} else {
			if (arg0 == (size - 1)) {
				olddata = (E) end.data;
				end.data = (E) arg1;
				return end.data;

			} else {

				MyNode<E> temp = end;

				for (int i = size-1; i >= arg0; i--) {
					temp = temp.prev;

				}
				olddata = (E) temp.data;
				temp.data = (E) arg1;
				return olddata;
			}
		}

	}

	/**
	 * This method return the size of the list
	 * 
	 * @param
	 * @return int
	 */
	@Override
	public int size() {

		return size;
	}

	/**
	 * This method will return a sublist from index arg0 to index arg1
	 * 
	 * 
	 * @param int,
	 *            int
	 * @return List
	 */
	@Override
	public List subList(int arg0, int arg1) {
		if (arg0 < 0 || arg0 > size - 1 || arg1 < 0 || arg1 > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		DoublyLinkedList<E> list1 = new DoublyLinkedList<E>();
		for (int i = arg0; i < arg1; i++) {
			list1.add(get(i));

		}

		return list1;
	}

	/**
	 * This will return the list to an array
	 * 
	 * @param none
	 * @return Object[]
	 */
	@Override
	public Object[] toArray() {
		E[] array = (E[]) new Object[size];
		MyNode<E> temp = begin;
		for (int i = 0; i < size; i++) {
			temp = temp.next;
			array[i] = (E) temp;
		}

		return array;
	}

	@Override
	public Object[] toArray(Object[] arg0) {
		E[] array = (E[]) new Object[arg0.length];
		MyNode<E> temp = begin;
		for (int i = 0; i < arg0.length; i++) {

			array[i] = (E) arg0[i];
		}

		return array;
	}

	/**
	 * This will creates a new iterator that can iterate through the list
	 */
	@Override
	public Iterator<E> iterator() {

		return new MyIterator<E>();
	}

	/**
	 * This is the iterator class that will have the function of next, hasnext,
	 * and remove
	 * 
	 * @author chenxis
	 *
	 * @param <E>
	 */
	protected class MyIterator<E> implements Iterator<E> {

		private int itCounter = 0;
		boolean removed = false;

		public MyIterator() {
			itCounter = 0;
		}

		/**
		 * This method will return a boolean to check if list has next item
		 * 
		 * @param none
		 * @return boolean
		 */
		@Override
		public boolean hasNext() {

			return itCounter < size;
		}

		/**
		 * This method will return the next item in the iterator if has no next
		 * item will throw nosuchelementException
		 * 
		 * @param none
		 * @return E
		 */
		@Override
		public E next() {

			if (hasNext()) {
				removed = false;
				return (E) get(itCounter++);

			} else {
				throw new NoSuchElementException();
			}

		}

		/**
		 * This method will remove the next item in the iterator when next is
		 * called if removed twice will throw illegalstateException
		 * 
		 */
		public void remove() {

			if (isEmpty() || removed) {
				throw new IllegalStateException();

			} else if (size == 1) {
				begin = null;
				end = null;
				itCounter--;
				size--;

			} else {
				MyNode<E> temp = (MyNode<E>) begin;
				for (int i = 0; i < itCounter; i++) {
					if (temp.next != null) {
						temp = temp.next;
					}
				}
				if (temp.next != null) {
					temp.next.prev = temp.prev;
				} else {
					end = end.prev;
				}
				if (temp.prev != null) {
					temp.prev.next = temp.next;
				} else {

					begin = begin.next;

				}

				removed = true;
				itCounter--;
				size--;
			}

		}

	}

	@Override
	public ListIterator<E> listIterator() {

		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
