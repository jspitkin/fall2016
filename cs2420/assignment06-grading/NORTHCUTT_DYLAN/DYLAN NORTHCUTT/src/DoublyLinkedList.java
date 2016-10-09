/*
 * author: Dylan Northcutt
 */
package assignment06;

import java.util.NoSuchElementException;
import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

	private node head;
	private node tail;

	public DoublyLinkedList() {

	}

	@Override
	public void addFirst(Object element) {

		node newNode = new node();
		newNode.data = element;

		if (head != null) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		} else {
			head = newNode;
			tail = newNode;

		}
	}

	@Override
	public void addLast(Object o) {
		node newNode = new node();
		newNode.data = o;

		if (tail != head && tail != null) {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;
		} 
		else if(head==null){
			this.addFirst(o);
		}
		else if (tail == head) {
			tail = newNode;
			tail.prev = head;
			head.next = tail;

		}
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index == 0) {
			this.addFirst(element);
		} else if (index == this.size()) {
			this.addLast(element);
		} else {
			node current = new node();
			current = head;
			for (int place = 1; place < index; place++) {
				current = current.next;
			}
			node newNode = new node();
			newNode.data = element;

			current.prev.next = newNode;
			newNode.prev = current.prev;

			current.prev = newNode;
			newNode.next = current;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public E getFirst() throws NoSuchElementException {

		return (E) this.head.data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E getLast() throws NoSuchElementException {

		return (E) this.tail.data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if (index == 0) {
			return (E) this.head.data;
		}
		node current = new node();
		current = head;
		for (int i = 1; i <= index; i++) {
			current = current.next;
		}
		return  (E) current.data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E removeFirst() throws NoSuchElementException {
		node temp = new node();
		temp = head;
		if(head.next == null){
			head = null;
			return (E)temp;
		}
		head = head.next;
		head.prev.next = null;
		head.prev = null;

		return (E) temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E removeLast() throws NoSuchElementException {
		node temp = new node();
		temp = tail;
		tail = tail.prev;
		tail.next.prev = null;
		tail.next = null;

		return (E) temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index == 0)
			this.removeFirst();
		node current = new node();
		current = head;
		for (int i = 0; i < index; i++) {
		current = current.next;
		}
		
		current.next.prev = current.prev;
		current.prev.next = current.next;


		return (E) current;
	}

	@Override
	public int indexOf(E element) {
		node current = new node();
		current = head;
		if (current == element) {
			return 0;
		}
		for (int place = 0; place < this.size(); place++) {
			current = current.next;
			if (current == element) {
				return place;
			}
		}
		return -1;

	}

	@Override
	public int lastIndexOf(E element) {
		node current = new node();
		current = head;
		if (current == element) {
			return this.size() - 1;
		}
		for (int place = this.size() - 1; place < 0; place--) {
			current = current.prev;
			if (current == element) {
				return place;
			}
		}
		return -1;

	}

	@Override
	public int size() {
		node current = new node();
		current = head;
		int count = 1;
		while (current != tail) {
			current = current.next;
			count++;
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		if (head == null)
			return true;
		return false;
	}

	@Override
	public void clear() {
		for (int i = this.size()-1; i >= 0; i--) {
			this.removeFirst();
		}
	}

	@Override
	public Object[] toArray() {
		int size = this.size();
		Object[] list = new Object[size];
		node current = new node();
		current = head;
		list[0] = current.data;
		for (int i = 1; i < size; i++) {
			current = current.next;
			list[i] = current.data;
		}

		return list;
	}

	
	@Override
	public Iterator<E> iterator() {
		    final DoublyLinkedList<E> list = this;
		    return new Iterator<E>() {
		    	final node firstNode = list.head;
		        node currentNode = null;
		      
		        @Override
		        public boolean hasNext() {
		            if (list.isEmpty()) {
		                return false;
		            } else if (currentNode == null){
		                return true;
		            } else if (currentNode == list.tail){
		                return false;
		            }
		            return true;
		        }
		        
		        @SuppressWarnings("unchecked")
				@Override
		        public E next() {
		            if (list.isEmpty()){
		                throw new NoSuchElementException();
		            } else if (currentNode == null){
		                this.currentNode = firstNode;
		                return (E) currentNode.data;
		            } else if (currentNode.next == null) {
		                throw new NoSuchElementException();
		            }
		            this.currentNode = currentNode.next;
		            return (E) currentNode.data;
		        }
		        
		        public void remove(){
		        	currentNode = null;
		        }
		    };
	
}
}