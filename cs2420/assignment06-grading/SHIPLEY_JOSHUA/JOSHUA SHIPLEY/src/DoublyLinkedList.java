package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E> implements List<E>, Iterable<E>{
	
	private Node<E> first;
	private Node<E> last;
	private int size;
	
	/**
	 * Creates a DoubleyLinkedList object, or a LinkedList that can be searched starting from the beginning or end of the list
	 */
	public DoublyLinkedList(){
		first = null;
		last = null;
		size = 0;
	}
	
	/**
	 * Inserts the specified element at the beginning of the list.
	 * O(1) for a doubly-linked list.
	 * 
	 * @param element - The element to be added to the list
	 */
	@Override
	public void addFirst(E element) {
		if(first == null){
			first = new Node<E>(element);
			last = first;
		}
		else{
			first.setPrev(new Node<E>(element, first, null));
			first = first.getPrev();
		}
		
		size++;
	}

	/**
	 * Inserts the specified element at the end of the list.
	 * 
	 * @param element - The element to be added to the list
	 */
	@Override
	public void addLast(E element) {
		if(last == null){
			last = new Node<E>(element);
			first = last;
		}
		else{
			last.setNext(new Node<E>(element, null, last));
			last = last.getNext();
		}
		
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Adding at 0 will add the element to the beginning of the list
	 * Adding at current size will add the element to the end of the list
	 * 
	 * @param index - The desired index of the given element
	 * @param element - The element to be added to the list
	 * 
	 * @throws IndexOutOfBoundsException If index is less than 0 or greater than the list's size
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		if(index == 0){
			addFirst(element);
			return;
		}
		else if(index == size){
			addLast(element);
			return;
		}
		
		if(index < 0 || index > size - 1){
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> currentNode;
		if(index <= size/2){
			currentNode = first;
			for(int currentIndex = 0; currentIndex < index; currentIndex++){
				currentNode = currentNode.getNext();
			}
		}
		else{
			currentNode = last;
			for(int currentIndex = size - 1; currentIndex > index; currentIndex--){
				currentNode = currentNode.getPrev();
			}
		}
		
		Node<E> insertNode = new Node<E>(element, currentNode, currentNode.getPrev());
		currentNode.getPrev().setNext(insertNode);
		insertNode.getNext().setPrev(insertNode);
		
		size++;
	}

	/**
	 * Returns the first element in the list.
	 * 
	 * @return - The value of the first element in the list
	 * 
	 * @throws NoSuchElementException If the list is empty.
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(first == null){
			throw new NoSuchElementException("List is empty");
		}
		
		return first.getValue();
	}

	/**
	 * Returns the last element in the list.
	 * 
	 * @return - The last element of the list
	 * 
	 * @throws NoSuchElementException If the list is empty.
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(last == null){
			throw new NoSuchElementException("List is empty");
		}
		
		return last.getValue();
	}

	/**
	 * Returns the element at the specified position in the list.
	 *	
	 *@param index - The index at which to look up the element
	 *
	 *@return - The element at the given index
	 *
	 *@throws IndexOutOfBoundsException If index is out of range (index < 0 || index >= size())
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		if(index < 0 || index > size - 1){
			throw new IndexOutOfBoundsException();
		}
		
		Node<E> currentNode;
		if(index <= size/2){
			currentNode = first;
			for(int currentIndex = 0; currentIndex < index; currentIndex++){
				currentNode = currentNode.getNext();
			}
		}
		else{
			currentNode = last;
			for(int currentIndex = size - 1; currentIndex > index; currentIndex--){
				currentNode = currentNode.getPrev();
			}
		}
		
		return currentNode.getValue();
	}

	/**
	 * Removes and returns the first element from the list.
	 * 
	 * @return - The value of the removed element
	 * 
	 * @throws NoSuchElementException If the list is empty.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		if(size == 0){
			throw new NoSuchElementException("List is already empty");
		}
		
		E returnValue = first.getValue();
		
		if(size == 1){
			first = null;
			last = null;
			size--;
			return returnValue;
		}
		
		first = first.getNext();
		first.setPrev(null);
		size--;
		
		return returnValue;
	}

	/**
	 * Removes and returns the last element from the list.
	 * 
	 * @return - The value of the removed element
	 * 
	 * @throws NoSuchElementException If the list is empty.
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		
		if(size == 0){
			throw new NoSuchElementException("List is already empty");
		}
		
		E returnValue = last.getValue();
		
		if(size == 1){
			first = null;
			last = null;
			size--;
			return returnValue;
		}
		
		last = last.getPrev();
		last.setNext(null);
		size--;
		
		return returnValue;
	}
	
	/**
	 * Removes and returns the element at the specified position in the list.
	 * 
	 * @param index - The index of the desired element to be removed
	 * 
	 * @return - The value of the removed element
	 * 
	 * @throws IndexOutOfBoundsException If index is out of range (index < 0 || index >= size())
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {

		if(index < 0 || index > size - 1){
			throw new IndexOutOfBoundsException();
		}
		else if(index == 0){
			return removeFirst();
		}
		else if(index == size - 1){
			return removeLast();
		}
		
		Node<E> currentNode;
		if(index <= size/2){
			currentNode = first;
			for(int currentIndex = 0; currentIndex < index; currentIndex++){
				currentNode = currentNode.getNext();
			}
		}
		else{
			currentNode = last;
			for(int currentIndex = size - 1; currentIndex > index; currentIndex--){
				currentNode = currentNode.getPrev();
			}
		}
		
		currentNode.getNext().setPrev(currentNode.getPrev());
		currentNode.getPrev().setNext(currentNode.getNext());
		size--;
		
		return currentNode.getValue();
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * 
	 * @return - The index of the first value matching the given element, or -1 if no matching value is found
	 */
	@Override
	public int indexOf(E element) {
		
		Node<E> currentNode = first;
		for(int index = 0; index < size; index++){
			if(currentNode.getValue().equals(element)){
				return index;
			}
			else{
				currentNode = currentNode.getNext();
			}
		}
		
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in the list, 
	 * or -1 if this list does not contain the element.
	 * 
	 * @return - The index of the last value matching the given element, or -1 if no matching value is found
	 */
	@Override
	public int lastIndexOf(Object element) {
		
		Node<E> currentNode = last;
		for(int index = size - 1; index >= 0; index--){
			if(currentNode.getValue().equals(element)){
				return index;
			}
			else{
				currentNode = currentNode.getPrev();
			}
		}
		
		return -1;
	}

	/**
	 * Returns the number of elements in this list.
	 * 
	 * @return - The size of the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns true if this collection contains no elements.
	 * 
	 * @return - True if this list contains 0 elements, otherwise returns false
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper sequence 
	 * (from first to last element).
	 * 
	 * @return - An Object array containing all elements of the list in order
	 */
	@Override
	public Object[] toArray() {
		
		Object[] list = new Object[size];
		
		Node<E> currentNode = first;
		for(int i = 0; i < size; i++){
			list[i] = currentNode.getValue();
			currentNode = currentNode.getNext();
		}
		
		return list;
	}

	/**
	 * Creates and returns an iterator that goes through the list
	 * 
	 * @return - The created iterator for this list
	 */
	@Override
	public Iterator<E> iterator() {

		//Creating new ITerator
				Iterator<E> iterator = new Iterator<E>(){
					
					private Node<E> mostPrev = null;
					
					private boolean hasCalledNext = false;
					
					//Checks if there is another object
					@Override
					public boolean hasNext() {
						if(mostPrev == null){
							return first == null;
						}
						else{
							return mostPrev.getNext() != null;
						}
					}
					
					//Returns the next object
					@Override
					public E next() {
						if(hasNext()){
							if(mostPrev == null){
								mostPrev = first;
							}
							else{
								mostPrev = mostPrev.getNext();
							}
							
							hasCalledNext = true;
						
							return mostPrev.getPrev().getValue();
						}
						else{
							return null;
						}
					}
					@Override 
					public void remove(){
						if(hasCalledNext){
							hasCalledNext = false;
							
							mostPrev.getNext().setPrev(mostPrev.getPrev());
							mostPrev.getPrev().setNext(mostPrev.getNext());
							mostPrev = mostPrev.getPrev();
							
							size--;
						}
					}
				};
		
		return iterator;
	}

}

class Node<E>{
	private Node<E> next;
	private Node <E> prev;
	private E value;
	
	/**
	 * Creates a custom Node object to be used for a DoubleyLinkedList
	 * @param element - The value to be stored within the given Node
	 */
	Node(E element){
		next = null;
		prev = null;
		value = element;
	}
	
	/**
	 * Creates a custom Node object to be used for a DoubleyLinkedList
	 * @param element - The value to be stored within the given Node
	 * @param next - The next Node that follows this Node in the DoubleyLinkedList
	 * @param prev - The previous Node that this Node follows in the DoubleyLinkedList
	 */
	Node(E element, Node<E> next, Node<E> prev){
		this.next = next;
		this.prev = prev;
		value = element;
	}
	
	/**
	 * Returns the next Node in the DoubleyLinkedList
	 * @return - The Node following this one in the list, or null if no such Node exists(i.e. The end of the list)
	 */
	Node<E> getNext(){
		return next;
	}
	
	/**
	 * Reassigns the following Node in the DoubleyLinkedList to the given one
	 * @param element - The Node that is to follow this current Node
	 */
	void setNext(Node<E> element){
		next = element;
	}
	
	/**
	 * Returns the previous Node in the DoubleyLinkedList
	 * @return - The Node that this one follows in the list, or null if no such Node exists (i.e) The start of the list)
	 */
	Node<E> getPrev(){
		return prev;
	}
	
	/**
	 * Reassigns the previous Node in the DoubleyLinkedList to the given one
	 * @param element - The Node that this current Node is to follow
	 */
	void setPrev(Node<E> element){
		prev = element;
	}
	
	/**
	 * The value stored within this Node
	 * @return - The value of this Node
	 */
	E getValue(){
		return value;
	}
}