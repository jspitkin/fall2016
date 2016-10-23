/**
 * @author Scott Krstyen
 * UNID = U0760822
 */
package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
	
	public Node<E> head;
	public Node<E> tail;
	public static int size = 0;
	public Node<E> current;
	
	public static class Node<E> {
		
		public E data = null;
		public Node<E> next = null;
		public Node<E> prev = null;
		
	}
	/**
	 * Constructor for DoublyLinkedList
	 */
	public DoublyLinkedList(){
		head = new Node<E>();
		tail = new Node<E>();
		current = new Node<E>();
		size = 0;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addFirst(E element) {
		
		Node<E> node = new Node<E>();
		node.data = element;
		
		if(DoublyLinkedList.size == 0){
			this.setHead(node);
			this.tail = node;
			DoublyLinkedList.size++;
			return;
		}
		
		node.next = this.getHead();
		this.getHead().prev = node;
		this.setHead(node);
		DoublyLinkedList.size++;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLast(E o) {
		Node<E> node = new Node<E>();
		node.data = o;
		
		if(DoublyLinkedList.size == 0){
			setHead(node);
			tail = node;
			DoublyLinkedList.size++;
			return;
		}
		
		node.prev = this.tail;
		this.tail.next = node;
		this.tail = node;
		DoublyLinkedList.size++;
		
		
		
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}else{
			if(index == 0){
				addFirst(element);
				return;
			}else if(index == size){
				addLast(element);
				return;
			}
			this.current = this.getHead();
			for(int i = 0; i < index; i++){
				this.current = this.current.next;
			}
			Node<E> node = new Node<E>();
			node.data = element;
			node.next = this.current;
			
			Node<E> previousNode = this.current.prev;
			previousNode.next = node;
			
			this.current.prev = node;
			
			DoublyLinkedList.size++;
		}
		
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(DoublyLinkedList.size != 0){
			return this.getHead().data;
		}else{
			throw new NoSuchElementException();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(DoublyLinkedList.size != 0){
			return this.tail.data;
		}else{
			throw new NoSuchElementException();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}
		this.current = this.getHead();
		for(int i = 0; i < index; i++){
			this.current = this.current.next;
		}
		return this.current.data;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(DoublyLinkedList.size > 1){
			E oldData = (E) this.getHead().data;
			this.setHead(this.getHead().next);
			DoublyLinkedList.size--;
			if(DoublyLinkedList.size == 1){
				this.tail = this.getHead();
			}
			return oldData;
		}else if(DoublyLinkedList.size == 1){
			E oldData = (E) this.getHead().data;
			this.setHead(null);
			this.tail = null;
			DoublyLinkedList.size--;
			return oldData;
		}else{
			throw new NoSuchElementException();
		}
		
		
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(DoublyLinkedList.size > 1){
			E oldData = (E) this.tail.data;
			this.tail = this.tail.prev;
			DoublyLinkedList.size--;
			if(DoublyLinkedList.size == 1){
				this.setHead(this.tail);
			}
			return oldData;
		}else if(DoublyLinkedList.size == 1){
			E oldData = (E) this.tail.data;
			this.setHead(null);
			this.tail = null;
			DoublyLinkedList.size--;
			return oldData;
		}else{
			throw new NoSuchElementException();
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException();
		}else{
			if(index == 0){
				return removeFirst();
			}else if(index == size - 1){
				return removeLast();
			}
			this.current = this.getHead();
			for(int i = 0; i < index; i++){
				this.current = this.current.next;
			}
			
			Node<E> previousNode = this.current.prev;
			previousNode.next = this.current.next;
			
			Node<E> nextNode = this.current.next;
			nextNode.prev = this.current.prev;
			
			DoublyLinkedList.size--;
			
			return this.current.data;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(E element) {
		this.current = this.getHead();
		
		for(int i = 0; i < DoublyLinkedList.size; i++){
			if(this.current.data == element){
				return i;
			}
			this.current = this.current.next;
		}
		
		return -1;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(E element) {
		this.current = this.tail;
		
		for(int i = 0; i < DoublyLinkedList.size; i++){
			if(this.current.data == element){
				return size - i - 1;
			}
			this.current = this.current.prev;
		}
		
		return -1;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return DoublyLinkedList.size;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		if(DoublyLinkedList.size == 0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		this.setHead(null);
		this.tail = null;
		DoublyLinkedList.size = 0;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] toArray() {
		
		Object[] array = new Object[DoublyLinkedList.size];
		
		this.current = this.head;
		
		for(int i = 0; i < size; i++){
			array[i] = this.current.data;
			this.current = this.current.next;
		}
		return array;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyIterator<E>(this.head);
	}
	/**
	 * Getter for the head node
	 * @return the head node
	 */
	public Node<E> getHead() {
		return head;
	}
	/**
	 * Setter for the head node
	 */
	public void setHead(Node<E> head) {
		this.head = head;
	}

	public class MyIterator<E> implements Iterator<E> {

		DoublyLinkedList<E> dll = new DoublyLinkedList<E>();
		private Node<E> current = null;
		private Boolean callable = false;
		
		
		public MyIterator(Node<E> head){
			if(head != null){
				this.current = head;
			}
			
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			if(this.current.next != null){
				return true;
			}else{
				return false;
			}
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		public E next() throws NoSuchElementException {
			if(this.current.next == null){
				throw new NoSuchElementException();
			}
			E data = (E) this.current.data;
			this.current =  this.current.next;
			callable = true;
			return data;
		}
		/**
		 * {@inheritDoc}
		 */
		public void remove() throws UnsupportedOperationException, IllegalStateException{
			if(!callable){
				throw new IllegalStateException();
			}
			
			Node<E> previousNode = this.current.prev;
			Node<E> nodeBeforePreviousNode = previousNode.prev;
 			
			if(nodeBeforePreviousNode != null){
				nodeBeforePreviousNode.next = this.current;
				this.current.prev = nodeBeforePreviousNode;
			}else{
				this.current.prev = null;
				dll.setHead(this.current);
			}
			
			callable = false;
			DoublyLinkedList.size--;
			
		}
		
	}

}
