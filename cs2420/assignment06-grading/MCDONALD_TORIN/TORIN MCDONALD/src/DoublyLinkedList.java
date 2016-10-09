package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * 
 * @author Torin McDonald
 * @uid u0940253
 *
 */
public class DoublyLinkedList<E> implements List<E>  {
	private Node<E> head;
	private int size;
	private Node<E> tail;



	private class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;
		/**
		 * NodeConstructor
		 */
		public void Node(){
			next=null;
			prev= null;
			data=null;

		}

	}

	/**
	 * Constructor
	 */
	public void DoubleLinkedList(){
		head= new Node<E>();
		head.data=null;
		head.prev=null;
		tail=new Node<E>();
		tail.next=null;
		size=0;



	}

	@Override
	public void addFirst(E element) {

		Node<E> temp= new Node<E>();
		temp.data=element;
		Node<E> current= new Node<E>();
		current=head;
		if(size==0){
			head=temp;
			head.next=tail;
			tail=temp;
		}
		else if(size==1){
			head=temp;
			tail.prev=temp;
			head.next=tail;

		}
		else{
			head=temp;
			current.prev=head;
			head.next=current;



		}

		size++;
	}

	@Override
	public void addLast(E o) {

		Node<E> temp=new Node<E>();
		temp.data=o;
		Node<E> current= new Node<E>();
		current=tail;
		if(size==0){
			addFirst(o);
		}

		else{

			tail=temp;
			tail.prev=current;
			current.next=tail;
			size++;
		}
	}

	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> newNode=new Node<E>();
		newNode.data=element;
		Node<E> current=head;

		if(index==0){
			addFirst(element);		
		}
		else{
			for(int i=1;i<size;i++){

				if(i==index){
					Node<E> temp= new Node<E>();
					temp=current.next;
					current.next=newNode;
					newNode.prev=current;
					newNode.next=temp;
					temp.prev=newNode;
				}
				current=current.next;
			}
			size++;
		}


	}
	@Override
	public E getFirst() throws NoSuchElementException {
		if(size==0){
			throw new NoSuchElementException();
		}
		return head.data;
	}

	@Override
	public E getLast() throws NoSuchElementException {
		if(size==0){
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> temp= head;
		for (int i=0;i<index;i++){
			temp=temp.next;
		}
		return temp.data;



	}


	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size==0){
			throw new NoSuchElementException();
		}
		Node<E> temp= new Node<E>();
		temp=head;
		head=head.next;
		size--;
		return temp.data;

	}

	@Override
	public E removeLast() throws NoSuchElementException {
		if(size==0){
			throw new NoSuchElementException();
		}
		Node<E> temp=new Node<E>();
		temp=tail;
		tail=tail.prev;
		size--;
		return temp.data;
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}

		Node<E> current= head;
		for (int i=0;i<index;i++){
			current=current.next;
		}
		Node<E> temp= new Node<E>();
		temp=current;
		if(index==0){
			removeFirst();
		}
		else if(index==size-1){
			removeLast();
		}
		else{

			current.prev.next=current.next;
			current.next.prev=current.prev;
			size--;
		}
		return temp.data;
	}

	@Override
	public int indexOf(E element) {
		Node<E> current= new Node<E>();
		current=head;
		for(int i=0;i<size;i++){
			if(current.data==element){
				return i;
			}		
			current=current.next;
			
		}
		return -1;
		
	}

	@Override
	public int lastIndexOf(E element) {
		Node<E> current= new Node<E>();
		current=tail;
		for(int i=size-1;i>=0;i--){
			if(current.data==element){
				return i;
			}		
			current=current.prev ;
			
		}
		return -1;
	
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size==0)
			return true;
		else{
			return false;

		}
	}

	@Override
	public void clear() {
	
		head=null;
		size=0;
	}

	@Override
	public Object[] toArray() {
		Object[] temp= new Object[size];
		Node<E> current= new Node<E>();
		current=head;

		for(int i=0; i<size;i++){

			temp[i]=current.data;
			current=current.next;
		}

		return temp;
	}
	public Iterator<E> iterator()
	{
		return new DoublyLinkedListIterator<E>();
	}

	/**
	 * 
	 * @author Torin McDonald
	 * Iterator implementation
	 *
	 * @param <E>
	 */
	private class DoublyLinkedListIterator<E> implements Iterator<E> {

		private  DoublyLinkedList<E>.Node<E> nextNode;
		public DoublyLinkedListIterator(){
			nextNode= (DoublyLinkedList<E>.Node<E>) head;
		}
		@Override
		public boolean hasNext() {
			return nextNode!=null;
		}

		@Override
		public E next() {
			if(!hasNext()){
				throw new NoSuchElementException();
			}
			E data=(E) nextNode.data;  //why does this need a cast?
			nextNode=nextNode.next;
			return 	data;
		}
		@Override
		public void remove(){ 

		}

	}

}

