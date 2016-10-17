package assignment06;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DoublyLinkedList.java -- An implementation of a Doubly Linked List according to List.java interface. 
 * This list representation that can accommodate any type of item. Also implement Java's Iterable interface (including the remove method).
 * 
 * @author Rebekah Peterson u0871657
 * 
 * <E> -- the type of elements contained in the list
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {
    
    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
	this.head = null;
	this.tail = null;
	this.size = 0;
    }

    /**
     * Inserts the specified element at the end of the list.
     * O(1) for a doubly-linked list.
     * 
     * @param element -- the element to be added
     */
    @Override
    public void addFirst(E element) {
	// set element as a node
	Node newNode = new Node(element, null, head);

	// update head's previous
	if (!(head == null)) {
	    this.head.prev = newNode;
	}

	// update head and tail pointers
	head = newNode;
	if (tail == null) {
	    tail = newNode;
	}

	size++;
    }

    /**
     * Inserts the specified element at the end of the list.
     * O(1) for a doubly-linked list.
     * 
     * @param element -- the element to be added
     */
    @Override
    public void addLast(E element) {
	// set element as a node
	Node newNode = new Node(element, tail, null);

	// update tail's next
	if (!(tail == null)) {
	    this.tail.next = newNode;
	}

	// update head and tail pointers
	if (head == null) {
	    head = newNode;
	}
	tail = newNode;

	size++;
    }

    /**
     * Inserts the specified element at the specified position in the list.
     * O(N) for a doubly-linked list.
     * 
     * @param index -- the specified position as an int
     * @param element -- the element to be added
     * @throws IndexOutOfBoundsException -- if index is out of range (index < 0 || index > size())
     */
    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
	// index is out of bounds, first, or last
	if (index < 0 || index > size) {
	    throw new IndexOutOfBoundsException();
	} else if (index == 0) {
	    addFirst(element);
	    return;
	} else if (index == size) {
	    addLast(element);
	    return;
	}


	Node current = findCurrent(index);

	// create node, update references
	Node newNode = new Node(element, current.prev, current);
	current.prev.next = newNode;
	current.prev = newNode;
	current = newNode;

	size++;
    }

    /**
     * Returns the first element in the list.
     * O(1) for a doubly-linked list.
     * 
     * @return the first element
     * @throws NoSuchElementException -- if the list is empty
     */
    @Override
    public E getFirst() throws NoSuchElementException {
	if (head == null) {
	    throw new NoSuchElementException();
	}

	return head.value;
    }

    /**
     * Returns the last element in the list.
     * O(1) for a doubly-linked list.
     * 
     * @return the last element
     * @throws NoSuchElementException -- if the list is empty
     */
    @Override
    public E getLast() throws NoSuchElementException {
	if (tail == null) {
	    throw new NoSuchElementException();
	}

	return tail.value;
    }

    /**
     * Returns the element at the specified position in the list.
     * O(N) for a doubly-linked list.
     * 
     * @param index -- the specified position
     * @return the element at the index
     * @throws IndexOutOfBoundsException -- if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
	// index is out of bounds, first, or last
	if (index < 0 || index >= size || size == 0) {
	    throw new IndexOutOfBoundsException();
	} else if (index == 0) {
	    return getFirst();
	} else if (index == size - 1) {
	    return getLast();
	}

	Node current = findCurrent(index);

	return current.value;
    }

    /**
     * Removes and returns the first element from the list.
     * O(1) for a doubly-linked list.
     * 
     * @return the first element (removed)
     * @throws NoSuchElementException -- if the list is empty
     */
    @Override
    public E removeFirst() throws NoSuchElementException {
	if (head == null) {
	    throw new NoSuchElementException();
	}

	// save old head
	Node oldHead = head;

	// update head (and tail if size is 1)
	if (!(head.next == null)) {
	    Node newHead = head.next;
	    head = newHead;
	    head.prev = null;
	}
	else { 
	    head = null;
	    tail = null;
	}

	size--;
	return oldHead.value;
    }

    /**
     * Removes and returns the last element from the list.
     * O(1) for a doubly-linked list.
     * 
     * @return the last element (removed)
     * @throws NoSuchElementException -- if the list is empty
     */
    @Override
    public E removeLast() throws NoSuchElementException {
	if (head == null) {
	    throw new NoSuchElementException();
	}

	// save old tail
	Node oldTail = tail;

	// update tail (and head if size is 1)
	if (!(tail.prev == null)) {
	    Node newTail = tail.prev;
	    tail = newTail;
	    tail.next = null;
	}
	else { 
	    head = null;
	    tail = null;
	}

	size--;
	return oldTail.value;
    }

    /**
     * Removes and returns the element at the specified position in the list.
     * O(N) for a doubly-linked list.
     * 
     * @param index -- the specified position
     * @return the removed element
     * @throws IndexOutOfBoundsException -- if index is out of range (index < 0 || index >= size())
     */
    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
	// index is out of bounds, first, or last
	if (index < 0 || index >= size || size == 0) {
	    throw new IndexOutOfBoundsException();
	} else if (index == 0) {
	    return removeFirst();
	} else if (index == size - 1) {
	    return removeLast();
	}

	Node current = findCurrent(index);

	// save old current
	Node oldCurrent = current; 

	// remove old current by updating current
	current.prev.next = oldCurrent.next;
	current.next.prev = oldCurrent.prev;
	current = oldCurrent.next;

	size--;
	return oldCurrent.value;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the list, 
     * or -1 if this list does not contain the element.
     * O(N) for a doubly-linked list.
     * 
     * @param element -- the specified element
     * @return the position it is first found at
     */
    @Override
    public int indexOf(E element) {
	if (!(head == null)) {
	    // is the element at the head?
	    Node current = head;
	    if (current.value.equals(element)) {
		return 0;
	    }
	    // is it in the rest of the list?
	    for (int i = 1; i < size; i++) {
		Node temp = current.next;
		current = temp;
		if (current.value.equals(element)) {
		    return i;
		}
	    }
	}		
	return -1; 
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, 
     * or -1 if this list does not contain the element.
     * O(N) for a doubly-linked list.
     * 
     * @param element -- the specified element
     * @return the position it is last found at
     */
    @Override
    public int lastIndexOf(E element) {
	if (!(head == null)) {
	    // is the element at the tail?
	    Node current = tail;
	    if (current.value.equals(element)) {
		return size - 1;
	    }
	    // is it in the rest of the list?
	    for (int i = size - 2; i >= 0; i--) {
		Node temp = current.prev;
		current = temp;
		if (current.value.equals(element)) {
		    return i;
		}
	    }
	}		
	return -1; 
    }

    /**
     * Returns the number of elements in this list.
     * O(1) for a doubly-linked list.
     * 
     * @return the number of elements
     */
    @Override
    public int size() {
	return size;
    }

    /**
     * Returns true if this collection contains no elements.
     * O(1) for a doubly-linked list.
     * 
     * @return returns true if collection is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
	if (size == 0) {
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
	head = null;
	tail = null;
	size = 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence 
     * (from first to last element).
     * O(N) for a doubly-linked list.
     * 
     * @return an array containing all elements
     */
    @Override
    public Object[] toArray() {
	Object[] arr = new Object[size];

	// fill array
	if (size > 0) {
	    arr[0] = head.value;
	    Node currentNode = head;
	    for (int i = 1; i < size; i++) {
		Node temp = currentNode.next;
		currentNode = temp;
		arr[i] = currentNode.value;
	    }
	}

	return arr;
    }

    /**
     * Creates an iterator for DLLs.
     * 
     * @return a doubly linked list iterator
     */
    @Override
    public Iterator<E> iterator() {
	return new DoublyLinkedListIterator();
    }
    
    /**
     * A helper method for finding the node at a particular index. Accomplished by traversing through the list.
     * 
     * @param index -- the specified position
     * @return the current Node
     */
    private Node findCurrent(int index) {
	// traverse through, find index
	Node current = null;
	if (index < size / 2) { // closer to beginning
	    current = head;
	    for (int i = 0; i < index; i++) {
		Node temp = current.next;
		current = temp;
	    }
	} else { // closer to end
	    current = tail;
	    for (int i = size - 1; i > index; i--) {
		Node temp = current.prev;
		current = temp;
	    }
	}

	return current;
    }

    /**
     * A nested class for the iterator of a Doubly Linked List. 
     * 
     */
    public class DoublyLinkedListIterator implements Iterator<E> {

	private int currentPosition;
	private boolean canRemove;

	public DoublyLinkedListIterator() {
	    this.currentPosition = 0;
	    canRemove = false;
	}

	/**
	 * Returns true if the iteration has more elements. 
	 * (In other words, returns true if next() would return an element rather than throwing an exception.)
	 * 
	 * @return true if the iteration has more elements, false otherwise
	 */
	@Override
	public boolean hasNext() {
	    return currentPosition < size;
	}

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @return the next element in the iteration
	 * @throws NoSuchElementException - if the iteration has no more elements
	 */
	@Override
	public E next() throws NoSuchElementException {
	    if (!this.hasNext()) {
		throw new NoSuchElementException();
	    }
	    canRemove = true;

	    Node current = findCurrent(currentPosition++);

	    return current.value;
	}

	/**
	 * Removes from the underlying collection the last element returned by this iterator (optional operation). 
	 * This method can be called only once per call to next(). The behavior of an iterator is unspecified if 
	 * the underlying collection is modified while the iteration is in progress in any way other than by calling this method.
	 * 
	 * @throws IllegalStateException - if the next method has not yet been called, or the remove method has already been called after 
	 * the last call to the next method
	 */
	@Override
	public void remove() throws IllegalStateException {
	    if (currentPosition == 0 || canRemove == false) {
		throw new IllegalStateException();
	    }
	    canRemove = false;

	    currentPosition--;
	    if (currentPosition == 0) {
		removeFirst();
		return;
	    } else if (currentPosition == size - 1) {
		removeLast();
		return;
	    }
	    
	    Node current = findCurrent(currentPosition);

	    // save old current
	    Node oldCurrent = current; 

	    // remove old current by updating current
	    current.prev.next = oldCurrent.next;
	    current.next.prev = oldCurrent.prev;
	    current = oldCurrent.next;

	    size--;
	}
    }

    /**
     * A helper class which creates a Node which holds the value and links of a linked list.
     * 
     */
    public class Node {
	private E value;
	private Node prev;
	private Node next;

	public Node(E value, Node prev, Node next) {
	    this.value = value;
	    this.prev = prev;
	    this.next = next;
	}
    }
}
