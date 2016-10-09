package assignment06;

import java.util.NoSuchElementException;

/**
 * Creates a generic linked list, and implements methods of List Class.
 * The linked list takes in objects of type E.  
 * @author Jana Klopsch (u0854469)   Last Modified 10/2/16
 * course: CS2420
 * Assignment 6
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<Object>{

	private Node head;
	private Node tail;
	private int size;

	/**
	 * Creates a Node to hold the element of type E, and links to the previous 
	 * and next nodes.
	 * @author Jana Klopsch
	 *
	 */
	private class Node {
		private E data;
		private Node previous;
		private Node next;
	}

	/**
	 * Constructs an (initially) empty doubly linked list
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
		size = 0;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void addFirst(Object element) {
		Node item = new Node();
		item.data = (E) element;
		item.previous = null;

		if(isEmpty()){
			item.next = null;
			head = item;
			tail = item;
		}
		else {
			item.next = head;
			item.next.previous = item;
			head = item;
		}
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addLast(Object o) {
		Node item = new Node();
		item.data = (E) o;
		item.next = null;

		//If it's the only item in list
		if(isEmpty()){
			item.previous = null;
			head = item;
			tail = item;
		}
		else {
			item.previous = tail;
			item.previous.next = item;
			tail = item;
		}
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void add(int index, Object element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0){
			addFirst(element);
		}
		else if(index == size){
			addLast(element);
		}
		else{
			Node item = new Node();
			item.data = (E) element;
			Node current;
			if(index < size/2){	//if index is closer to beginning, start at head
				current = head;
				for(int iter = 0; iter < index - 1; iter++){ //Find where item is being added
					current = current.next;
				}
			}
			else{ //if index is closer to end, start at tail
				current = tail;
				for(int iter = size -1; iter > index - 1; iter--){
					current = current.previous;
				}
			}
			item.previous = current;
			item.next = current.next;
			item.previous.next = item;
			item.next.previous = item;
			size++;
		}

	}

	@Override
	public Object getFirst() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return (Object) head.data;
	}

	@Override
	public Object getLast() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return (Object) tail.data;
	}

	@Override
	public Object get(int index) throws IndexOutOfBoundsException {

		if(isEmpty()){
			throw new IndexOutOfBoundsException();
		}
		else if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0){
			return getFirst();
		}
		else if(index == size - 1){
			return getLast();
		}
		Node current;
		if(index < size/2){	//if index is closer to beginning, start at head
			current = head;
			for(int iter = 0; iter < index; iter++){ //Find where item is being added
				current = current.next;
			}
		}
		else{ //if index is closer to end, start at tail
			current = tail;
			for(int iter = size -1; iter > index; iter--){
				current = current.previous;
			}
		}

		return (Object) current.data;
	}

	@Override
	public Object removeFirst() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		if(size == 1){
			head = null;
			tail = null;
			size = 0;
			return null;
		}
		else{
			Node temp = head.next;
			temp.previous = null;
			head = temp;
		}
		size--;
		return (Object) head.data;
	}

	@Override
	public Object removeLast() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		if(size == 1){
			head = null;
			tail = null;
			size = 0;
			return null;
		}
		else {
			Node temp = tail.previous;
			temp.next = null;
			tail = temp;
		}
		size--;
		return (Object) tail.data;
	}

	@Override
	public Object remove(int index) throws IndexOutOfBoundsException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}	
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0){
			return removeFirst();
		}
		else if(index == size -1){
			return removeLast();
		}
		else {
			Node current;
			if(index < size/2){	//if index is closer to beginning, start at head
				current = head;
				for(int iter = 0; iter < index; iter++){ //Find where item is being added
					current = current.next;
				}
			}
			else{ //if index is closer to end, start at tail
				current = tail;
				for(int iter = size -1; iter > index; iter--){
					current = current.previous;
				}
			}
			current.previous.next = current.next;
			current.next.previous = current.previous;
			current = null;
			size--;
		}
		return get(index);
	}

	@Override
	public int indexOf(Object element) {
		if(!isEmpty()){
			Node temp = head;
			for(int index = 0; index < size; index++){
				Object data = temp.data;
				if(element == null){
					if(data == null){
						return index;
					}
				}
				else {
					if(data.equals(element)){
						return index;
					}
				}
				temp = temp.next;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object element) {
		int elementIndex = -1;
		if(!isEmpty()){
			Node temp = head;
			for(int index = 0; index < size; index++){
				Object data = temp.data;
				if(element == null){
					if(data == null){
						elementIndex = index;
					}
				}
				else {
					if(data.equals(element)){
						elementIndex = index;
					}
				}
				temp = temp.next;
			}
		}
		return elementIndex;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0){
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		while(size > 0){
			removeFirst();
		}
	}

	@Override
	public Object[] toArray() {
		Object[] listArray = new Object[size];
		for(int index = 0; index < size; index++){
			listArray[index] = get(index);
		}
		return listArray;
	}

}
