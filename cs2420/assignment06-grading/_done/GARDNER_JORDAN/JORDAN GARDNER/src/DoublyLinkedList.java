package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author Jordan Gardner u0566259
 * DoublyLinkedList<E> Traverses a list in order one by one.
 * This class implements methods to allow input and removal of items in the list.
 * Furthermore, allows the user to access head, tail and other parts of the list.
 * Can iterate through the list using the iterator class at bottom.
 * Keeps track of data at indices and modifies data accordingly in the case of
 * addition and removal.
 * 
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E> {
	
	//variables for size of list,head node, and tail node
	private int size;
	 Node<E> head;
	 Node<E> tail;
	 /**
	  * Constructor for size 0 list
	  */
	public DoublyLinkedList(){
		size=0;
		head=null;
		tail=null;
	}
	/**
	 * Mini class to help with node declaration and data acquisition
	 *
	 * @param <I>
	 */
	private class Node<I>{
		
		private I data;
		private Node<I> next;
		private Node<I> previous;
		//set method for data
		public Node( I _data)
		{
			data = _data;
		}
	}

	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addFirst(Object element) {
		add(0,element);
		
	}
	/**
	 * Inserts the specified element at the end of the list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void addLast(Object o) {
		if(size==1||size==0){
			add(size,o);
		}else{
			add(size-1,o);
		}
		
	}
	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, Object element) throws IndexOutOfBoundsException {
		
		if(index<0||index>size){
			throw new IndexOutOfBoundsException();
		}
		if(size==0){
			Node<E> number1 = new Node<E>((E) element);
			head = number1;
			tail = number1;
			size++;
			return;
		}
		if(size==1){
			Node<E>current = new Node<E>((E) element);
			if(index==0){
				tail.previous = current;
			current.next = head;
			head = current;
			
			size++;
			return;
			}
			else if(index==1){
			
			current.previous = tail;
			tail = current;
			head.next = current;
			size++;
			return;
			}
			
		}
		if (index == 0){
		Node<E> number1 = new Node<E>((E) element);
		
		head = number1;
		number1.next = head;
		
		head.next.previous = number1;
		
		size++;
		return;
		}
		Node<E> temp = head;
		Node<E> include=new Node<E>((E)element);
		for(int i=0;i<index;i++){
			temp=temp.next;
			
		}
		if(index+1==size){
			tail=include;
			include.previous=temp;
			temp.next=include;
			
		}
		else{
			if(size==0){
				head=include;
				tail=include;
				return;
			}
			
			if (temp.next != null)
				temp.next.previous = include;
			
			if (temp.previous != null)
					temp.previous.next = include;
			include.previous = temp.previous;
			include.next = temp.next;
			
//				Node<E> newNode = new Node<E>((E) element);
//				Node<E> current = new Node<E>((E) element);
//				newNode.previous = current;
//				newNode.next = current.next;
//				newNode.previous.next = newNode;
//				newNode.next.previous = newNode;
//				size++;
			}
			size++;

	}
	/**
	 * Returns the first element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size==0){
			throw new NoSuchElementException();
		}
		
		return this.get(0);
	}
	/**
	 * Returns the last element in the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size==0){
			throw new NoSuchElementException();
		}
		return this.get(this.size()-1);
	}
	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0||index>=size){
			throw new IndexOutOfBoundsException();
		}
		Node<E> temp=head;
		if(size==index+1){
			return tail.data;
		}
		for(int i=0;i<index;i++){
			temp=temp.next;
		}
		return temp.data;
	}
	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size==0){
			throw new NoSuchElementException();
		}
		E removethis = this.get(0);
		remove(0);
		return removethis;
	}
	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(size==0){
			throw new NoSuchElementException();
		}
		E removethis = this.get(size-1);
		remove(size-1);
		return removethis;
	}
	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(size<=index||index< 0){
			throw new IndexOutOfBoundsException();
		}
		if(size==0){
			throw new IndexOutOfBoundsException();
		}
		Node<E> temp=head;
		for(int i=0;i<index;i++){
			temp=temp.next;
		}
		if(index==0){
			E data = temp.data;

			head=head.next;
			size--;
			return data;
		}
		if(size==1&&index==0){
			E data= head.data;
			tail=null;
			head=null;
			size--;
			return data;
		}
		if(size-1==index){
			E data = temp.data;
			tail=tail.previous;
			tail.next=null;
			size--;
			return data;
		}
		
		size--;
//		Node<E>current = head;
//		current.previous.next = current.next;
//		current.next.previous = current.previous;
//		current.previous=null;
//		current.next=null;
		temp.previous.next=temp.next;
		temp.next.previous=temp.previous;
		temp.previous=null;
		temp.next=null;
		
		
		return temp.data;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int indexOf(Object element) {
		Node<E> temp=head;
		for(int i=0; i<=size;i++){
			if(temp.data==element){
				return i;
			}else{
				temp=temp.next;
			}
		}
		return -1;
	}
	/**
	 * Returns the index of the last occurrence of the specified element in this list, 
	 * or -1 if this list does not contain the element.
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public int lastIndexOf(Object element) {
		
		Node<E> temp=tail;
		for(int i=size-1; i>=0;i--){
			if(temp.data.equals(element)){
				return i;
			}else{
				temp=temp.previous;
			}
		}
		return -1;
	
	}
	/**
	 * Returns the number of elements in this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public int size() {
		
		return size;
	}
	/**
	 * Returns true if this collection contains no elements.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public boolean isEmpty() {
		if(size==0){
			return true;
		}
		
		return false;
	}
	/**
	 * Removes all of the elements from this list.
	 * O(1) for a doubly-linked list.
	 */
	@Override
	public void clear() {
		size=0;
		head=null;
		tail=null;
		
	}
	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() {
		Object[] templist = new Object[size];
		Node<E> temp=head;
		for(int i=0; i<size;i++){
			templist[i]=temp.data;
			temp=temp.next;
		}
		return templist;
	}
	public Iterator<E> Iterator(){
		return new DoublyLinkedListIterator();
	}
	public class DoublyLinkedListIterator implements Iterator<E> {

		
		private Node<E> currentNode;
		
		/**
		 * Determines if using the next() method would throw and exception
		 * or return an element.
		 */
		@Override
		public boolean hasNext() {
			if(!isEmpty()){
				if(currentNode!=tail){
					return true;
				}
			}
			return false;
		}

		/**
		 * Returns the next element in the Set
		 */
		@Override
		public E next() throws NoSuchElementException{
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			if(currentNode==null){
				currentNode=head;
				return currentNode.data;
			}
			else{
				currentNode.previous=currentNode;
				currentNode=currentNode.next;
			}
			return currentNode.data;
		}
		
		/**
		 * Removes the the last element returned by next()
		 */
		@Override
		public void remove() throws IllegalStateException {
			if(hasNext()){
				if(currentNode.previous !=null){
					currentNode.previous.next=currentNode.next;
				}
				else{
					head=currentNode.next;
				}
			}else{
				throw new IllegalStateException();
			}
		}
	}

}
