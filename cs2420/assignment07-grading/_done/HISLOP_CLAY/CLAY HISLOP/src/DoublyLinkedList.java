package assignment06;


import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author Clayton Hislop uID: u0515744
 *
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
		
	private Node head;
	private Node tail;
	private int size = 0;
	
	
	public DoublyLinkedList() {
	}
	
	private class Node {
		private E data;
		private Node next = null;
		private Node previous = null;
	}

	@Override
	public void addFirst(E element) {
		if (isEmpty()) {
			head = new Node();
			tail = head;
			head.data = element;
			head.next = new Node();
		}
		else {
			Node newData =new Node();
			newData.data = element;
			newData.next = head;
			head.previous = newData;
			head = newData;
		}		
		size++;				
	}

	@Override
	public void addLast(E o) {
		if(isEmpty()){
			addFirst(o);
			return;
		}
		
		Node newData = new Node();
		newData.data = o;
		tail.next = newData;
		newData.previous = tail;
		tail = newData;		
		size++;
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		
		if ((index < 0 || index > size())) {
			throw new IndexOutOfBoundsException();
		}
		
		if (size == 0) {
			head = new Node();
			head.data = element;
			tail = head;
			size++;
			return;
		}
		
		Node newData = new Node();
		if (index == size) {
			newData.data = element;
			newData.previous = tail;
			tail.next = newData;
			tail = newData;
			size++;
			return;
		}
		else if (index == 0) {
			addFirst(element);
			return;
		}
		
		Node currentNode = head;		
		for (int i = 1; i < index; i++) {
			currentNode = currentNode.next;
		}				
		newData.data = element;
		newData.next = currentNode.next;
		newData.previous = currentNode;
		currentNode.next = newData;
		newData.next.previous = newData;		
		size++;
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}		
		return head.data;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}	
		return tail.data;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if ((index < 0 || index > size()) || size ==0) {
			throw new IndexOutOfBoundsException();
		}
		
		Node currentNode = head;		
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}		
		return currentNode.data;
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		E returnData = head.data;
		if (size == 1) {
			head = null;
			tail = head;
		}
		else {
			head = head.next;
			head.previous = null;
		}
		size--;
		return returnData;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		
		E returnData = tail.data;
		if (size == 1) {
			head = null;
			tail = head;
		}
		else {
			tail = tail.previous;
			tail.next = null;
		}
		size--;
		return returnData;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if ((index < 0 || index > size()) || size == 0) {
			throw new IndexOutOfBoundsException();
		}
		E returnData;
		if (size == 1) {
			returnData = head.data;
			head = null;
			tail = head;
			size--;
			return returnData;
		}		
		
				
		Node currentNode = head;		
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		
		if (index == 0) {
			returnData = head.data;
			head = head.next;
			head.previous = null;
		}
		else {
		returnData = currentNode.data;
		currentNode.next.previous = currentNode.previous;
		currentNode.previous.next = currentNode.next;
		}
		size--;
		return returnData;
	}

	@Override
	public int indexOf(E element) {
		Node currentNode = head;
		for (int i = 0; i < size; i++) {
			if (currentNode.data.equals(element)) {
				return i;
			}
			currentNode = currentNode.next;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		int index = -1;
		
		Node currentNode = head;
		for (int i = 0; i < size; i++) {
			if (currentNode.data.equals(element)) {
				index = i;
			}
			currentNode = currentNode.next;
		}
		return index;		
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (head == null) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		head = null;
		tail = head;
		size = 0;
	}

	@Override
	public Object[] toArray() {
		E[] array = (E[])new Object[size];
		
		Node currentNode = head;
		for (int index = 0; index < size; index++) {
			array[index] = currentNode.data;
			currentNode = currentNode.next;
		}
		return array;
	}

	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();		
	}
	
	protected class DoublyLinkedListIterator implements Iterator<E>{
		private Node currentNode = null;
		boolean hasRemoved;
		int index = 0;
		@Override
		public boolean hasNext() {					
			return !isEmpty() && currentNode != tail;
		}

		@Override
		public E next() {	
			if (currentNode == null) {
				hasRemoved = false;
				currentNode = head;
				return currentNode.data;
			}			
			if (currentNode == tail){
				throw new NoSuchElementException();
			}
			
			hasRemoved = false;		
			currentNode = currentNode.next;
			return currentNode.data;
		}
		
		public void remove(){
			if(currentNode.data == null  || hasRemoved) {
				throw new IllegalStateException();
			}
			if (size == 1) {
				head = null;
				tail = head;
				size = 0;
				return;
			}
			else {
				if (currentNode == head) {
					head = head.next;
					head.previous = null;
				}			
				else if (currentNode == tail) {
				tail = tail.previous;
				tail.next = null;
				}
				else {
					currentNode.next.previous = currentNode.previous;
					currentNode.previous.next = currentNode.next;
				}
			}
			hasRemoved = true;
			size--;			
		}
	}
}
