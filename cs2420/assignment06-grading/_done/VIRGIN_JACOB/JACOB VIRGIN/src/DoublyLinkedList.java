package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * 
 * @author Jacob Virgin, u0832933
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements Iterable<E> {
	private Node head;
	private Node tail;
	private Node current;
	private int size;
	
	
	
	public DoublyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}

	/** 
	 * Inserts the specified element at the beginning of the list.  
	 * O(1) for a doubly-linked list.  
	 */ 
	void addFirst(E element) {
		if(size == 0){
			head = new Node(element);
			this.tail = head;
		} else {
			Node temp = head;
			head = new Node(element);
			head.next = temp;
			temp.previous = head;
			if(size == 1){
				tail.previous = temp;
			} else {
				temp.next.previous = temp;
			}
			//System.out.println(temp);
		}
		
		size++;
		
	}
	
	
	/**Inserts the specified element at the end of the list.  
	 * O(1) for a doubly-linked list.  
	 */ 
	
	void addLast(E o) {
		
		if(size == 0){
			tail = new Node(o);
			this.head = tail;
		} else {
			Node temp = tail;
			tail = new Node(o);
			tail.previous = temp;
			temp.next = tail;
			if(size == 1){
				head.next = temp;
			} else {
				System.out.println(temp.previous);
				temp.previous.next = temp;
			}
			
		}
		size++;
	}
	
	/**  * Inserts the specified element at the specified position in the list.  
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index > size())
	 * O(N) for a doubly-linked list.  
	 * 
	 */ 
	void add(int index, E element) {
		if(index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		
		Node temp = new Node();
		 if(index <= size /2){
			 temp = head;
			 for(int ind = 0; ind < index; ind++){
				 temp = temp.Next();
			 }
		 } else {
			 temp = tail;
			 for(int ind = size - 1; ind > index; ind--){
				 temp = temp.Previous();
			 }
		 }
		
		Node node = new Node(element);
		node.next = temp;
		node.previous = temp.previous;
		temp.previous = node;
		node.previous.next = node;
		size++;
	}
	
	/**  * Returns the first element in the list.  
	 * Throws NoSuchElementException if the list is empty.  
	 *  O(1) for a doubly-linked list.  
	 */ 
	E getFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("This list is currently empty");
		}
		
		return (E) head.data;
	}
	
	/**  * Returns the last element in the list.  * 
	 * Throws NoSuchElementException if the list is empty.  * 
	 * O(1) for a doubly-linked list.  *
	 */
	E getLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("This list is currently empty");
		}
		return (E) tail.data;
	}
	
	/**  * Returns the element at the specified position in the list.  * 
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * O(N) for a doubly-linked list.  *
	 */
	E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		Node temp = new Node();
		 if(index <= size /2){
			 temp = head;
			 for(int ind = 0; ind < index; ind++){
				 temp = temp.Next();
			 }
		 } else {
			 temp = tail;
			 for(int ind = size - 1; ind > index; ind--){
				 temp = temp.Previous();
			 }
		 }
		 
		return (E) temp.data;
	}
	
	/**  * Removes and returns the first element from the list.  
	 * Throws NoSuchElementException if the list is empty.  
	 * O(1) for a doubly-linked list.  
	 */ 
	E removeFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		
		if(size ==1){
			this.head = null;
			this.tail = null;
		}
		Node temp = head;
		head = head.next;
		head.previous = null;
		
		size--;
		
		return (E) temp.data;
	}
	
	/**
	 * Removes and returns the last element from the list.  * 
	 * Throws NoSuchElementException if the list is empty.  * 
	 * O(1) for a doubly-linked list.  *
	 */ 
	E removeLast() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException();
		}
		if(size == 1){
			this.head = null;
			this.tail = null;
		}
		Node temp = tail;
		tail = tail.previous;
		tail.next = null;
		
		size--;
		
		return (E) temp.data;
	}
	
	/**Removes and returns the element at the specified position in the list.  * 
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||index >= size())
	 * O(N) for a doubly-linked list.  *
	 */ 
	 E remove(int index) throws IndexOutOfBoundsException {
		 if(index < 0 || index >= size){
			 throw new IndexOutOfBoundsException();
		 }
		 Node temp = new Node();
		 if(index <= size /2){
			 temp = head;
			 for(int ind = 0; ind < index; ind++){
				 temp = temp.Next();
			 }
		 } else {
			 temp = tail;
			 for(int ind = size - 1; ind > index; ind--){
				 temp = temp.Previous();
			 }
		 }
		 
		 temp.previous.next = temp.next;
		 temp.next.previous = temp.previous;
		 
		 size--;
		 
		return (E) temp.data;
	}
	 
	/**  * Returns the index of the first occurrence of the specified element in
	the list,
	 * or -1 if this list does not contain the element.  * O(N) for a doubly-linked list.  */ 
	 int indexOf(E element) {
		int count = 0;
		Node temp = head;
		
		while(temp.data != element && count < size){
			temp = temp.Next();
			count++;
		}
		if(temp.data == element){
			return count;
		} else {
			return -1;
		}
	}
	 
	/**  * Returns the index of the last occurrence of the specified element in this list,
	 * or -1 if this list does not contain the element.  * O(N) for a doubly-linked list.  *
	 */ 
	 int lastIndexOf(E element) {
		 int count = size;
			Node temp = tail;
			while(temp.data != element && count >= 0){
				temp = temp.Previous();
				count--;
			}
			if(temp.data == element){
				return count;
			} else {
				return -1;
			}
	}
	 
	/**  * Returns the number of elements in this list.  * 
	 * O(1) for a doubly-linked list.  *
	 */ 
	 int size() {
		return size;
	}
	 
	/**  * Returns true if this collection contains no elements.  * 
	 * O(1) for a doubly-linked list.  *
	 */ 
	 boolean isEmpty() {
		 if(size == 0){
			 return true;
		 } else {
			 return false;
		 }
	}
	 
	/**  * Removes all of the elements from this list.  *
	 *  O(1) for a doubly-linked list.  *
	 */ 
	 void clear() {
		 this.head = null;
		 this.tail = null;
		 this.size = 0;
	}
	 
	/**  * Returns an array containing all of the elements in this list in proper
	sequence
	 * (from first to last element).  * 
	 * O(N) for a doubly-linked list.  *
	 */ 
	 Object[] toArray() {
		 Object[] arr = new Object[size];
		 Node temp = head;
		 for(int index = 0; index < size; index++){
			 arr[index] = temp.data;
			 temp = temp.Next();
		 }
		return arr;
	}


	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>(){
			private Node cursor;
			private Node head;
			private Node tail;

	        public void Iterator(Node head, Node tail) {
	            this.head = head;
	            this.tail = tail;
	            this.cursor = head;
	        }

	        public boolean hasNext() {
	        	if(this.cursor.next == null){
	        		return false;
	        	} else {
	        		return true;
	        	}
	        }

	        public E next() {
	            if(this.hasNext()) {
	                Node current = cursor;
	                cursor = cursor.next;
	                return (E) current.data;
	            }
	            throw new NoSuchElementException();
	        }

	        public void remove() {
	            throw new UnsupportedOperationException();
	        }
		};
	}
	
	
	@Override
	public String toString(){
		String listString = new String();
		for(int ind = 0; ind < size; ind++){
			listString += this.get(ind) + "\n";
		}
		return listString;
	}
}


