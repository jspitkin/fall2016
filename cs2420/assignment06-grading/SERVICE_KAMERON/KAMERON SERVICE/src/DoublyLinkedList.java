package assignment06;

import java.util.Iterator;

import java.util.NoSuchElementException;

@SuppressWarnings("unchecked")
public class DoublyLinkedList<E> implements List<E>, Iterable<E>{
	
	private Node<E> head; 
	private Node<E> tail;
	private int size;
	
	public DoublyLinkedList(){
		size = 0;
	}
	
	@Override
	public void addFirst(E element) {
		if(size == 0){
			head = new Node<E>(element, null, null);
			tail = head;
			size = 1;
		}else{
			Node<E> temp = head;
			head = new Node<E>(element, null, temp);
			head.next = temp;
			temp.prev = head;
			size++;
		}
		
	}

	@Override
	public void addLast(E element) {
			if(size == 0){
				head = new Node<E>(element, null, null);
				tail = head;
				size = 1;
			}else if(size == 1){
				head.next = new Node<E>(element, head, null);
				tail = head.next;
				//tail.prev = head;
				size = 2;
			}else{
				Node<E> temp = tail;
				tail = new Node<E>(element, temp.prev, null);
				tail.prev = temp;
				temp.next = tail;
				size++;
			}
			tail.next = null;			
	}

	@Override
	public void add(int index, E element) {
			if(index < 0 || index > size){
				throw new IndexOutOfBoundsException("Index " + index + " is out of bounds.");
			}
			if(index == size){
				this.addLast(element);
				return;
			}
			
			Node<E> temp = head;
			if(index > size/2){
				temp = tail;
				for(int iter = size - 1; iter > index; iter--){
					temp = temp.prev;
				}
			}else{
				for(int iter = 0; iter < index; iter ++){
					temp = temp.next;
				}
			}
			Node<E> node = new Node<E>(element, temp.prev, temp);
			temp.prev.next = node;
			temp.prev = node;
			size++;
	}

	@Override
	public void clear() {
		head.next = null;
		tail.prev = null;
		head = null;
		tail = head;
		size = 0;
	}

	@Override
	public E get(int index) {
		if(size == 0)
			throw new NoSuchElementException("Size is zero.");
		if(index < 0 || index > size - 1)
			throw new IndexOutOfBoundsException("The index " + index + " is out of bounds");
		Node<E> temp = head;
		if(index > size/2){
			temp = tail;
			for(int iter = size - 1; iter > index; iter--){
				temp = temp.prev;
			}
		}else{
			for(int iter = 0; iter < index; iter ++){
				temp = temp.next;
			}
		}
		
		return temp.item;
	}

	@Override
	public int indexOf(Object o) {
		if(size == 0)
			throw new NoSuchElementException("Size is zero.");
		Node<E> temp = head;
		for(int iter = 0; iter < size; iter++){
			if(temp.item == o){
				return iter;
			}
			temp = temp.next;
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		if(size == 0)
			return true;
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyIterator<E>();
	}
	
	private class MyIterator<E> implements Iterator<E> {
	
		private Node<E> cursor;
	
		public MyIterator(){
			cursor = (Node<E>) head;
		}

		@Override
		public boolean hasNext() {
			if(cursor.next != null)
				return true;
			return false;
		}
	
		@Override
		public E next() {
			if(cursor.next != null){
				Node<E> current = cursor;
				cursor = cursor.next;
				return current.item;
			}
			throw new NoSuchElementException();
		}
		
		@Override
		public void remove(){
			if(cursor == head){
				cursor = cursor.next;
				head = head.next;
				head.prev = null;
			}else if(cursor == tail){
				cursor = cursor.prev;
				tail = tail.prev;
				tail.next = null;
			}else{
				cursor.prev.next = cursor.next;
				cursor.next.prev = cursor.prev;
				cursor = cursor.next;
			}
		}
	
	}

	@Override
	public int lastIndexOf(Object o) {
		if(size == 0)
			throw new NoSuchElementException("Size is zero.");
		Node<E> temp = tail;
		for(int iter = size - 1; iter >= 0; iter--){
			if(temp.item == o){
				return iter;
			}
			temp = temp.prev;
		}
		return -1;
	}
	
	@Override
	public E removeFirst() throws NoSuchElementException {
		E element = head.item;
		head = head.next;
		head.prev = null;	
		size--;
		return element;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		E element = tail.item;
		tail = tail.prev;
		tail.next = null;
		size--;
		return element;
	}
	
	@Override
	public E remove(int index) {
		if(size == 0)
			throw new NoSuchElementException("Size is zero.");
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException("Index of " + index + " is out of bounds");
		}
		E element = null;
		Node<E> temp = head;
		if(index == size - 1){
			element = removeLast();
		}else if(index == 0){
			element = removeFirst();
		}else if(index > size/2){
			temp = tail;
			for(int iter = size - 1; iter > index; iter--){
				temp = temp.prev;
			}
			element = temp.item;
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
		}else{
			for(int iter = 0; iter < index; iter ++){
				temp = temp.next;
			}
			element = temp.item;
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
		}
		
		size--;
		
		return element;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		E[] arr = (E[])new Object[size];
		Node<E> temp = head;
		for(int iter = 0; iter < size; iter++){
			arr[iter] = temp.item;
			temp = temp.next;
		}
		return arr;
	}



	@Override
	public E getFirst() throws NoSuchElementException {
		return head.item;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		return tail.item;
	}

}
