/**
 * @author Alan Hansing u0668729
 */
package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class DoublyLinkedList<E> implements List<E>{

	private Node<E> head;
	private Node<E> tail;
	private int size;
	
	private static class Node<E>{
		E data;
		Node<E> before;
		Node<E> after;
		
	}
	public DoublyLinkedList() {
		head = new Node<E>();
		tail = new Node<E>();
		head.after = tail;
		head.before = null;
		tail.before = head;
		tail.after = null;
		
	}

	@Override
	public void addFirst(E element) {
		if(size == 0){
			head.data = element;
			size++;
		}
		else{
			Node<E> temp = head;
			Node<E> head = new Node<E>();
			head.data = element;
			head.after = temp;
			head.before = null;
			size++;
		}
	}

	@Override
	public void addLast(E element) {
		if(size == 0){
			addFirst(element);
		}
		else if(size == 1){
			tail.data = element;
			size++;
		}
		else{
			Node<E> temp = new Node<E>();
			temp.data = element;
			temp.before = tail;
			temp.after = null;
			tail.after = temp;
			tail = temp;
			size++;
		}
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index > size){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			addFirst(element);
			return;
		}
		if(index ==size){
			addLast(element);
			return;
		}
		else if(index > size/2){
			int count = 0;
			Node<E> temp = tail;
			while(size - count > index){
				temp = temp.before;
			}
			Node<E> toAdd = new Node<E>();
			toAdd.data = element;
			toAdd.after = temp.after;
			toAdd.before = temp.before;
			toAdd.after.before = toAdd;
			toAdd.before.after = toAdd;
			size++;
			return;
		}
		else {
			int count = 0;
			Node<E> temp;
			Node<E>	toInsert = new Node<E>();
			toInsert.data = (E)element;
			temp = head;
			// iterating up to the specific index.
			while(count < index){
				temp = temp.after;
				count++;
			}
			// inserting the node into the correct position.
			toInsert.after = temp;
			toInsert.before = temp.before;
			temp.before.after = toInsert;	
			temp.before = toInsert;
			size++;	
		}
	}

	@Override
	public E getFirst() throws NoSuchElementException {
		if(head.data == null){
			throw new NoSuchElementException();
		}
		else{
			return head.data;
		}
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if(tail.data == null){
			throw new NoSuchElementException();
		}
		else{
			return tail.data;
		}
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		int count = 0;
		
		if(index > size/2){
			Node<E>temp = tail;
			while(size-count > index +1){
				temp = temp.before;
				count++;
			}
			return temp.data;
		}
		else{
			Node<E>temp = head;
			while(count < index){
				temp = temp.after;
				count++;
			}
			return temp.data;
		}
		
	}

	@Override
	public E removeFirst() throws NoSuchElementException {
		E temp = head.data;
		head = head.after;
		head.before = null;
		return temp;
	}

	@Override
	public E removeLast() throws NoSuchElementException {
		E temp = tail.data;
		tail = tail.before;
		tail.after = null;
		return temp;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		int count = 0;
		if(index > size/2){
			Node<E> temp = tail;
			while(size-count > index){
				temp = temp.before;
				count++;
			}
			temp.before.after = temp.after;
			temp.after.before = temp.before;
			
		}
		return null;
	}

	@Override
	public int indexOf(E element) {
		for(int i = 0; i <size; i++){
			if(this.get(i).equals(element)){
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E element) {
		int indexOfLast = -1;
		for(int i = 0; i <size; i++){
			if(this.get(i).equals(element)){
				indexOfLast = i;
			}
		}
		return indexOfLast;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		
		return size == 0;
	}

	@Override
	public void clear() {
		head = new Node<E>();
		tail = new Node<E>();
		head.after = tail;
		head.before = null;
		tail.before = head;
		tail.after = null;
		size = 0;
	}

	@Override
	public Object[] toArray() {
		Object[] toReturn = new Object[size];
		Node<E> temp = new Node<E>();
		temp = head;
		for(int i = 0; i < size; i++){
			toReturn[i] = temp.data;
			temp = temp.after;
		}
		return toReturn;
	}
	
	/**
	 * Creates an iterator that helps the user iterate through the list.
	 * @returns an iterator of type E
	 */
	public Iterator<E> iterator(){
		return new Iterator<E>(){

			Node<E> nextNode = head;
			Node<E> previousNode = tail;
			@Override
			public boolean hasNext() {
				if(nextNode.after == null){
					return false;
				}
				return true;
			}

			@Override
			public E next() {
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				E temp = nextNode.data;
				nextNode = nextNode.after;
				return temp;
			}
			
			@SuppressWarnings("unused")
			public E previous(){
				E temp = previousNode.data;
				previousNode = previousNode.before;
				return temp;
			}
			
		};
	}

}
