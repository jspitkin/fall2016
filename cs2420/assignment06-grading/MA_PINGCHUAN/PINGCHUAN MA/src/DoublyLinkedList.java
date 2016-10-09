package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Template for a doublylinkedlist
 * @author Pingchuan Ma uid:u0805309
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	private DNode<E> head;
	private DNode<E> tail;
	private int size;

	public DoublyLinkedList() {
		size = 0;
	}

	/**
	 * this class keeps track of each element information
	 *
	 */
	private class DNode<E> {
		E element;
		DNode<E> next;
		DNode<E> prev;

		public DNode(E element, DNode<E> next, DNode<E> prev) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
	}

	@Override
	public void addFirst(E element) {
		DNode<E> tmp = new DNode(element, head, null);
		if (head == null || size == 0) {
			head = tmp;
			tail = tmp;
		} else {
			head.prev = tmp;
			tmp.next = head;
			tmp.prev = null;
			head = tmp;
		}
		size++;
	}

	@Override
	public void addLast(E element) {
		DNode<E> tmp = new DNode(element, null, tail);
		if (tail == null || size == 0) {
			head = tmp;
			tail = tmp;
		} else {
			tail.next = tmp;
			tmp.prev = tail;
			tmp.next = null;
			tail = tmp;
		}

		size++;
	}

	@Override
	// no throws IndexOutOfBoundsException???difference
	public void add(int index, E element) throws IndexOutOfBoundsException {
		DNode<E> p = getNode(index - 1);
		DNode<E> tmp = new DNode(element, null, null);
		if (index == size) {
			addLast(element);
			size++;
		} else if (index == 0) {
			addFirst(element);
			size++;
		} else if (index > size - 1||index<0) {
			throw new IndexOutOfBoundsException();
		} else {
			p.next.prev = tmp;
			tmp.next = p.next;
			tmp.prev = p;
			p.next = tmp;
			size++;
		}

	}

	@Override
	public E getFirst() throws NoSuchElementException {
		DNode<E> fd = head;
		if (fd == null)
			throw new NoSuchElementException();
		return fd.element;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		final DNode<E> lt = tail;
		if (lt == null)
			throw new NoSuchElementException();
		return lt.element;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index > size - 1||index<0) {
			throw new IndexOutOfBoundsException();
		}
		DNode<E> p = getNode(index);
		return p.element;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		DNode<E> tmp = head;
		if (tmp == null)
			throw new NoSuchElementException();
		head = head.next;
		head.prev = null;
		size--;
		return tmp.element;

	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		DNode<E> tmp = tail;
		tail = tail.prev;
		tail.next = null;
		size--;
		return tmp.element;

	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		DNode<E> p = removeNode(index);
		if (index > size - 1||index<0) {
			throw new IndexOutOfBoundsException();
		}
		if (p != null) {
			size--;
			return p.element;

		} else
			size--;
		return null;

	}

	@Override
	public int indexOf(E element) {
		int index = 0;
		if (element == null) {
			for (DNode<E> x = head; x != null; x = x.next) {
				if (x.element == null)
					return index;
				index++;
			}
		} else {
			for (DNode<E> x = head; x != null; x = x.next) {
				if (element.equals(x.element))
					return index;
				index++;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		int index = size;
		if (element == null) {
			for (DNode<E> x = tail; x != null; x = x.prev) {
				index--;
				if (x.element == null)
					return index;
			}
		} else {
			for (DNode<E> x = tail; x != null; x = x.prev) {
				index--;
				if (element.equals(x.element))
					return index;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;

	}

	@Override
	public void clear() {
		for (DNode<E> x = head; x != null;) {
			DNode<E> next = x.next;
			x.element = null;
			x.next = null;
			x.prev = null;
			x = next;
		}
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size];
		int i = 0;
		for (DNode<E> x = head; x != null; x = x.next)
			result[i++] = x.element;
		return result;
	}

	@Override
	// how to define this method????????? and how to test it???
	// can i use removeNode????inside???
	public Iterator<E> iterator() {
		DoublyLinkedList<E> list = new DoublyLinkedList<E>();
		return new Iterator<E>() {
			DNode<E> tmp = head;
			int pos=0;
			

			public boolean hasNext() {
				 return  pos<size ;

			}

			public E next() {
				if (this.hasNext()) {
					if (tmp != tail) {
						tmp = tmp.next;
						pos++;
						return tmp.prev.element;
						
					}
					if (tmp == tail) {
						tmp = null;
						pos++;
						return tail.element;
					}
					
				}
				else
					throw new NoSuchElementException();
				return null;
			}
			public void remove() {
				if(tmp!=head&&tmp!=tail) {
					tmp.next.prev = tmp.prev;
					tmp.prev.next = tmp.next;
					
				}
				if(tmp==tail){
					head=null;
					tail=null;
				}
				if (tmp == head){
					head = head.next;
					head.prev=null;
					tmp=tmp.next;
					}		
				size--;
			}
		};

	}

	protected DNode<E> getNode(int i) {
		if (i < 0 || i > size - 1)
			return null;
		if (i == 0)
			return head;
		DNode p = head;
		int j = 0;
		while (p != null && j < i) {
			p = p.next;
			j++;
		}
		return p;
	}

	protected DNode removeNode(int i) {
		DNode p, q;
		if (head == null || size == 0)
			return null;
		if (i == 0) {
			p = head;
			head = head.next;
			size = size - 1;
			return p;
		}
		if (i >= 1 && i <= size - 1) {
			p = getNode(i - 1);
			q = p.next;
			p.next = q.next;
			return q;

		}
		return null;
	}

}
