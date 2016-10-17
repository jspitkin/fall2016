package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * This class represents Java's DoublyLinkedList with type E.
 * It has a head and a tail and the elements can only be accessed through these Nodes.
 * @author Prathusha Boppana (u0778008)
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>{
	private Node<E> head;
	private Node<E> current;
	private Node<E> tail;
	private int size;
	
	public DoublyLinkedList() {
		head = new Node<E>(null, null, null);
		current = head;
		tail = new Node<E>(null, null, null);
		size = 0;
	}
	
	/**
	 * This Node class represents each chunk of data in a DoublyLinkedList.
	 * It has the element, a Node for the previous element, and a Node for the next element.
	 * @author Prathusha
	 *
	 * @param <E>
	 */
	private class Node<E>{
		private E data;
		private Node<E> prev;
		private Node<E> next;
		
		private Node(E _data, Node<E> _prev, Node<E> _next){
			data = _data;
			prev = _prev;
			next = _next;
		}
	}

	/**
	 * @return an iterator for the elements in this list, where the elements are
	 *         linked by nodes
	 */
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator<E>();
	}
	
	/**
	 * Creates an iterator specific to DoublyLinkedListIterator<E>.
	 * 
	 * @param <E>
	 */
	protected class DoublyLinkedListIterator<E> implements Iterator<E> {
		int iterCount;
		DoublyLinkedListIterator() {
			current = head;
			iterCount = 0;
		}
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			if(iterCount +1 > size()){
				return false;
			}
//			return current.next!=null && indexOf(current.next.data)<size();
			return true;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public E next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			if(hasNext()){
				iterCount++;
				E data = (E) get(iterCount);
				return data;
			}
			return null;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void remove(){
			if(size() == 0){
				return;
			}
			get(iterCount);
			if(size() == 1){
				current = null;
			}
			else if(current == head){
				current = current.next;
				current.prev = null;
			}
			else if(current == tail){
				current = current.prev;
				current.next = null;				
			}
//			DoublyLinkedList.this.remove(iterCount);
			current.prev.next = current.next; //TODO: I'm not sure if I did this right, check this
			current.next.prev = current.prev;
			current.next = null;
			current.prev = null;
			current = head;
			size--;
		}

	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addFirst(E element) {
		if(size() == 0){
			head = new Node<E>(element, null, tail);
			tail = new Node<E>(element, head, null);
			current = head;
		}
		else if(size() == 1){
			head.data = element;
			head.next = tail;
			tail.prev = head;
			current = head;
		}
		else{
			Node<E> tempHead = head;
			tempHead.next = head.next.next;
			head = new Node<E>(element, null, tempHead);
			tempHead.prev = head;
		}
		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addLast(E o) {
		if(size() == 0){
			head = new Node<E>(o, null, tail);
			tail = new Node<E>(o, head, null);
			current = head;
		}
		else if(size() == 1){
			tail.data = o;
			head.next = tail;
			tail.prev = head;
			current = head;
		}
		else{
			Node<E> tempTail = tail;
			tempTail.prev = tail.prev.prev;
			tail = new Node<E>(o, tempTail, null);
			tempTail.next = tail;
		}
		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0){
			addFirst(element);
			return;
		}
		else if(index == size()-1){
			addLast(element);
			return;
		}
		
		if(index <= size()/2){
			current = head;
			for(int position = 1; position < index; position++){
				current = current.next;
			}
		}
		else{
			current = tail;
			for(int position = size()-1; position >= index; position--){
				current = current.prev;
			}
		}
		Node<E> newNode = new Node<E>(element, current, current.next);
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
		current = head; //need to set position to head again
		size++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		return head.data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			return head.data;
		}
		else if(index == size()-1){
			return tail.data;
		}
		if(index <= size()/2){
			current = head;
			for(int position = 1; position < index; position++){
				current = current.next;
			}
		}
		else{
			current = tail;
			for(int position = size()-1; position >= index; position--){
				current = current.prev;
			}
		}

		return current.data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		if(size() == 1){
			head = null;
			tail = null;
		}
		if(size == 2){
			head = new Node<E>(tail.data, null, tail);
		}
		else{
			head = head.next;
			head.prev = null;
		}
		size--;
		return head.data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		if(size() == 0){
			throw new NoSuchElementException();
		}
		if(size() == 1){
			head = null;
			tail = null;
		}
		if(size() == 2){
			tail = new Node<E>(head.data, head, null);
		}
		else{
			tail = tail.prev;
			tail.next = null;
		}
		size--;
		return tail.data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size()){
			throw new IndexOutOfBoundsException();
		}
		if(index == 0){
			removeFirst();
		}
		else if(index == size() -1){
			removeLast();
		}
		if(size() == 1){
			head = null;
			tail = null;
		}
		else{
			get(index);
			if(current == head){
				current = current.next;
				current.prev = null;
			}
			else if(current == tail){
				current = current.prev;
				current.next = null;				
			}
			current.prev.next = current.next; //TODO: I'm not sure if I did this right, check this
			current.next.prev = current.prev;
			current.next = null;
			current.prev = null;
			current = head;
		}
		size--;
		return current.data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int indexOf(E element) {
		if(size() == 0){
			return -1;
		}
		current = head;
		for(int pos = 0; pos < size(); pos++){
			if(current.data.equals(element)){
				return pos;
			}
			current = current.next;
		}
		return -1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int lastIndexOf(E element) {
		if(size() == 0){
			return -1;
		}
		int index = -1;
		current = head;
		for(int pos = 0; pos < size(); pos++){
			if(current.data.equals(element)){
				index = pos;
			}
			current = current.next;
		}
		return index;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		if(size() == 0){
			return true;
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {
		head = null;
//		head.next = null;
		tail = null;
//		tail.prev = null;
		size = 0;
	}

	@Override
	public E[] toArray() {
		E[] elementArr = (E[]) new Object[size];
		current = head;
		for(int pos = 0; pos < size(); pos++){
			elementArr[pos] = current.data;
			current = current.next;
		}
		return elementArr;
	}

}
