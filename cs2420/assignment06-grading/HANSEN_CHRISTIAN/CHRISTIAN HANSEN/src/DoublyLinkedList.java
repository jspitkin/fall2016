package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author Christian Hansen
 * @UID U0621884
 * @version 10/5/2016
 *
 * @param <E> - The type of Object the Doubly Linked List will hold.
 * 
 * This class can be instantiated to produce a generic linked list in which the nodes are linked to both the previous
 * node and the next node. There are different methods that can be used to access and modify the data. The class
 * implements the interface List and Iterable, as such it is guaranteed to have methods from these interfaces.
 * 
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E>{
	
	private Node head;
	private Node tail;
	private int size;
	
	/**
	 * Simple constructor. Initializes the DoublyLinkedList without any data, as such the
	 * size is set to 0.
	 */
	public DoublyLinkedList(){
		head = null;
		tail = null;
		size = 0;
	}
	
	/**
	 * 
	 * @author Christian Hansen
	 * @version 10/5/2016
	 * @UID U0621884
	 * 
	 * Inner private class. Defines the nodes that the class uses. Each Node has data of type E,
	 * it is linked to a previous node and a next node (both of these can be set to null if there isnt
	 * a node to be linked to).)
	 */
	private class Node{
		private E data;
		private Node next;
		private Node previous;
		
		/**
		 * Constructor for the Node to initialize it with the data to be given
		 * to the member variables.
		 * 
		 * @param data - of type E to be given to the node.
		 * @param next - the node that comes after this node that it points to.
		 * @param previous - the previous node that it points to.
		 */
		private Node(E data, Node next, Node previous){
			this.data = data;
			this.next = next;
			this.previous = previous;
		}
	}
	
	/**
	 * Adds the given element to the front of the list. If there was data already
	 * at the first position then it will be in the second position.
	 * 
	 * @param element - the element that will be added to the front of the list.
	 */
	@Override
	public void addFirst(E element) {
		if(size == 0){
			head = new Node(element, null, null);
			tail = head;
			size++;
		}else{
			Node tempNode = head;
			head = new Node(element, tempNode, null);
			tempNode.previous = head;
			size++;
			if(size==2){
				tail = tempNode;
			}
		}
	}
	
	/**
	 * Adds the given element to the back of the list. If there was data already
	 * at the last position then it will be in the second to last position.
	 * 
	 * @param element - the element that will be added to the last of the list.
	 */
	@Override
	public void addLast(E element){
		if(size == 0){
			head = new Node(element, null, null);
			tail = head;
			size++;
		}else if(size == 1){
			tail = new Node(element, null, head);
			head.next = tail;
			size++;
		}else{
			Node temp = tail;
			tail = new Node(element, null, temp);
			temp.next = tail;
			size++;
		}
	}
	
	/**
	 * Adds the given element to the position given. If there was data already
	 * at the first position then it will be in the position that comes after.
	 * 
	 * @param index - the position where the item will be inserted into.
	 * @param element - the element that will be added to the front of the list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		
		if(index < 0 || index > (size)){
			throw new IndexOutOfBoundsException();
		}
		
		if(index == 0){
			this.addFirst(element);
			
		}else if(index == size){
			this.addLast(element);
			
		}else{
			Node next = getNode(index);
			Node previous = next.previous;
			
			Node newNode = new Node(element, next, previous );
			
			next.previous = newNode;
			previous.next = newNode;
			
			size++;
		}
	}

	/**
	 * Returns the first element in the DoublyLinkedList. Throws NoSuchElementException 
	 * if the list is empty.
	 * 
	 * @return head.data - the data held by the first node, the head
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		
		if(size == 0){
			throw new NoSuchElementException();
		}
		
		return head.data;
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException 
	 * if the list is empty. If there is only one node then this node is both
	 * the head and tail, so the data attached to it will be returned
	 * 
	 * @return tail.data - the data attached to tail
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		
		if(size == 0){
			throw new NoSuchElementException();
		}
		
		return tail.data;
	}
	
	/**
	 * Returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * 
	 * @param index - the position desired
	 * @return the data held by the node at the index
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		
		Node tempNode = this.getNode(index);
		
		return tempNode.data;
	}

	
	/**
	 * Removes and returns the first element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * 
	 * @return the data held by the node that was removed
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		
		if(size == 0){
			throw new NoSuchElementException();
		}
		
		E returnData = head.data;
		head = head.next;
		head.previous = null;
		size--;
		return returnData;
	}
	
	/**
	 * Removes and returns the last element from the list.
	 * Throws NoSuchElementException if the list is empty.
	 * 
	 * @return data held by the node that was removed
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		
		if(size == 0){
			throw new NoSuchElementException();
		}
		
		E returnData;
		
		if(tail != null){
			returnData = tail.data;
			tail = tail.previous;
			tail.next = null;
		}else{
			returnData = head.data;
			head = null;
		}
		size--;
		return returnData;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 || index >= size())
	 * 
	 * @param index - the index of the node to be removed
	 * @return the data held by the node that was removed
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		
		if(index < 0 || index >= size){
			throw new IndexOutOfBoundsException();
		}
		
		Node tempNode = this.getNode(index);
		
		if(index == 0){
			head = tempNode.next;
			head.previous = null;
			
		}else if(index == (size - 1)){
			tail = tempNode.previous;
			tail.next = null;
			
		}else{
			Node previous = tempNode.previous;
			Node next = tempNode.next;
			
			previous.next = next;
			next.previous = previous;
		}
		
		size--;
		return tempNode.data;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * 
	 * @param element - the data of the node to be searched for
	 * @return the index of the element or -1 if not found
	 */
	@Override
	public int indexOf(E element) {
		
		Node tempNode = head;
		
		for(int index = 0; index < size; index++){
			if(tempNode.data == element){
				return index;
			}
			tempNode = tempNode.next;
		}
		
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * 
	 * @param element - the data of the node to be searched for
	 * @return the index of the element or -1 if not found
	 */
	@Override
	public int lastIndexOf(E element) {
		
		Node tempNode = head;
		int elementPosition = -1;
		
		for(int index = 0; index < size; index++){
			if(tempNode.data == element){
				elementPosition = index;
			}
			tempNode = tempNode.next;
		}
		return elementPosition;
	}
	
	/**
	 * Size returns the number of elements in the list.
	 * 
	 * @return size - the number of items in the list
	 */
	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * Returns true if empty or it will return false if the
	 * list is not empty.
	 * 
	 * @return true if empty, false if not
	 */
	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * Removes all of the elements from this list
	 */
	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * 
	 * @return objectArray - an array containing all of the elements
	 */
	@Override
	public Object[] toArray() {
		Object[] objectArray = new Object[size];
		Node tempNode = head;
		
		for(int index = 0; index < size; index++){
			objectArray[index] = tempNode.data;
			tempNode = tempNode.next;
		}
		
		return objectArray;
	}

	/**
	 * Returns the iterator that is defined for the class. It will iterate through all of the
	 * items. There are three different methods. next will return the element connected to the node. hasNext will
	 * return true/false, showing wether there is another element, and remove will remove the item from the list (next 
	 * must be called before removed)
	 * 
	 * @return the iterator that will go through the list.
	 */
	@Override
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator<E>(head);
	}
	
	/**
	 * Returns the node at the position given. If the position is in the upper half it will 
	 * traverse from tail and otherwise it will from the head if it is in the lower half.
	 * 
	 * @param index - the position given
	 * @return tempNode - the node that was at the position
	 */
	private Node getNode(int index){
		Node tempNode;
		
		if(index < (size / 2)){
			tempNode = head;
		
			for(int iter = 1; iter <= index; iter++){
				tempNode = tempNode.next;
			}
		}else{
			tempNode = tail;
			
			for(int iter = (size - 2); iter >= index; iter--){
				tempNode = tempNode.previous;
			}
		}

		return tempNode;
	}
	
	/**
	 * @author Christian Hansen
	 * @version 10/5/2016
	 * @UID U0621884
	 *
	 * @param <T> - the type of the data given by the user.
	 * 
	 * The iterator defined for this object. It implements the iterator interface and the
	 * methods in the interface.
	 */
	private class DoublyLinkedListIterator<T> implements Iterator<T>{
		
		Node current;
		boolean hasNextBeenCalled = false;
		
		public DoublyLinkedListIterator(Node head){
			current = head;
		}
		
		/**
		 * Returns true if there is another node and will return false if there is not
		 * another element
		 * 
		 * @return true if there is another element or false if not
		 */
		@Override
		public boolean hasNext() {
			if(current != null){
				return true;
			}else{
				return false;
			}
		}
		
		/**
		 * returns the next item, it will throw a NoSuchElementException if the next item is
		 * null. 
		 * 
		 * @return the next item in the list.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public T next() throws NoSuchElementException {
			
			if(current == null){
				throw new NoSuchElementException();
			}
			
			T tmp = (T) current.data;
			current = current.next;
			hasNextBeenCalled = true;
			
			return tmp;
		}
		
		/**
		 * Removes the last item returned from next. It calls IllegalStateException if next() has not been
		 * called right before the next method.
		 */
		@Override
		public void remove(){
			
			if(!hasNextBeenCalled){
				throw new IllegalStateException("Must call prior to each remove");
			}
			
			hasNextBeenCalled = false;
			size--;
			
			Node nodeToBeRemoved = current.previous;
			
			if(nodeToBeRemoved.previous == null){
				head = nodeToBeRemoved.next;
				head.previous = null;
				
			}else if(nodeToBeRemoved.next == null){
				tail = nodeToBeRemoved.previous;
				tail.next = null;
			
			}else{
				Node previousNode = nodeToBeRemoved.previous;
				Node nextNode = nodeToBeRemoved.next;
				previousNode.next = nextNode;
				nextNode.previous = previousNode;
			}
		}
	}
}
