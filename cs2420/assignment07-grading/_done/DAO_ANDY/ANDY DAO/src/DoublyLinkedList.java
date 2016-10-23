package assignment07;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A DoublyLinked List class that implements Java's List and Iterable interface
 * with zero based indexing (the first item is considered at index 0).
 * 
 * @author Andy Dao, uID: u0692334
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> {

    private int size; // Size of DoublyLinkedList
    private Node head; // The head of the DoublyLinkedList Node
    private Node tail; // The tail of the DoublyLinkedList Node

    /**
     * Generic implementation of Node class
     */
    private class Node {
	private E data;
	private Node next;
	private Node prev;
    }

    /**
     * Zero parameter constructor for DoublyLinkedList, known as the default
     * constructor.
     */
    public DoublyLinkedList () {
	head = null;
	tail = null;
	size = 0;
    }

    @Override
    public Iterator<E> iterator() {
	return new Iterator<E>() {

	    private Node currentNode = head; // Start at head
	    boolean callRemove = false; // Initialize to false until we call next()
	    private int currentIndex = 0; // Current index we are at is 0 to
	    // start

	    @Override
	    public boolean hasNext() {
		// See if there is a next node
		if (currentIndex < size) {
		    return true; // Returns true if there is
		}
		return false; // False otherwise
	    }

	    @Override
	    public E next() {
		if (hasNext() == false) { // If there is no more elements
		    throw new NoSuchElementException();
		}

		if (currentIndex == 0) {
		    callRemove = true;
		    currentIndex++;
		    return currentNode.data;
		}
		currentIndex++;
		currentNode = currentNode.next;
		callRemove = true; // Set to true so we know we can call remove
		// since we used next();
		return currentNode.data;
	    }

	    @Override
	    public void remove() {
		// If we have not yet called next(), throw exception
		if (callRemove == false) {
		    throw new IllegalStateException();
		}

		currentIndex--;
		DoublyLinkedList.this.remove(currentIndex);
		callRemove = false; // Now set to false since we can only call
		// remove once per next() call
	    }
	};
    }

    @Override
    public void addFirst(E element) {
	// Create new node for the element we're inserting
	Node insertNode = new Node();
	insertNode.data = element;

	// See if there's anything in the doublylinkedlist first
	if (size == 0) {
	    // Assign the node we're inserting next and previous to null since
	    // there are no other elements
	    insertNode.next = null;
	    insertNode.prev = null;

	    // Assign head and tail of doublylinkedlist to the element we're
	    // inserting
	    head = insertNode;
	    tail = insertNode;
	}
	else { // if the element we are inserting isn't the only element
		   // currently in the doublylinkedlist
	       // This is where all the switcharoo happens, in order to
	       // insert the new node/element into the head
	    head.prev = insertNode; // Make the node we are inserting, heads
	    // prev
	    insertNode.next = head; // Make the insertNode.next what the head is
	    head = insertNode; // Assign the insertNode to be the head now
	    head.prev = null; // head.prev is now set to null
	    tail.next = null; // as well as tail.next
	}
	size++; // Increment the size since we inserted an element
    }

    @Override
    public void addLast(E o) {
	// Create new node for the element we're inserting
	Node insertNode = new Node();
	insertNode.data = o;
	insertNode.next = null;

	// See if there's anything in the doublylinkedlist first
	// (Same as addFirst because there's no element in the doublylinkedlist
	// anyways)
	if (size == 0) {
	    head = insertNode;
	    tail = insertNode;
	    head.prev = null;
	    tail.next = null;
	}
	else if (size == 1) { // When of size 1
	    insertNode.prev = head;
	    head.next = insertNode;
	    tail = insertNode;
	}
	else { // This is where all the switcharoo happens, in order to insert
	    insertNode.prev = tail;
	    tail.next = insertNode;
	    tail = insertNode;
	}
	size++; // Increment the size since we inserted an element
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
	// If the index we are adding to is less than 0 or greater than it's
	// size
	// throw IndexOutOfBoundsException
	if (index < 0 || index > size) {
	    throw new IndexOutOfBoundsException();
	}

	// Create a new node for the element to be inserted
	Node insertNode = new Node();
	insertNode.data = element;

	// First check if the doublylinkedlist is empty
	if (isEmpty() == true || size == 0) { // if empty, just add it as the
	    // first element in the
	    // doublylinkedlist
	    head = insertNode;
	    tail = insertNode;
	    size++; // Increment the size since we inserted an element
	}
	else if (index == 0) {
	    addFirst(element);
	}
	else if (index == size) {
	    addLast(element);
	}
	else {
	    // This node will be the previous node after it moves to make room
	    // for the new element
	    Node prevNode = head;
	    int count = 1;

	    while (count < index) {
		prevNode = prevNode.next;
		count++;
	    }
	    // This node will be the next node after it moves to make room for
	    // the new element
	    Node nextNode = prevNode.next;
	    prevNode.next = insertNode;
	    // Assign the inserted node to have correct nodes for prev and next
	    insertNode.prev = prevNode;
	    insertNode.next = nextNode;
	    nextNode.prev = prevNode.next;

	    head.prev = null;
	    tail.next = null;
	    size++; // Increment the size since we inserted an element
	}

    }

    @Override
    public E getFirst() throws NoSuchElementException {
	if (size == 0) {
	    throw new NoSuchElementException();
	}
	return head.data;
    }

    @Override
    public E getLast() throws NoSuchElementException {
	if (size == 0) {
	    throw new NoSuchElementException();
	}
	return tail.data;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException();
	}
	Node tempNode = head;

	// increment up to the index
	for (int i = 0; i < index; i++) {
	    // When we get to the index
	    if (i == index) {
		return tempNode.data;
	    }
	    tempNode = tempNode.next;
	}
	return tempNode.data;
    }

    @Override
    public E removeFirst() throws NoSuchElementException {
	if (head == null) {
	    throw new NoSuchElementException();
	}
	Node removedHead = head; // Hold the value of the head in a temp node to be returned
	head = head.next; // The head is now reassigned
	size--;
	return removedHead.data;
    }

    @Override
    public E removeLast() throws NoSuchElementException {
	if (isEmpty()) {
	    throw new NoSuchElementException();
	}
	Node tempNode = tail; // This is going to be the new tail
	tail = tail.prev; // The tail is now reassigned
	size--;
	return tempNode.data;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
	// If the index we are removing from to is less than 0 or greater than
	// it's size
	// throw IndexOutOfBoundsException
	if (index < 0 || index >= size) {
	    throw new IndexOutOfBoundsException();
	}
	Node nodeRemoved = head; // The node to be removed

	if (index == 0) { // Removing the first index, just use removeFirst
	    removeFirst();
	}
	else if (index == size - 1) {
	    removeLast();
	}
	else {
	    // The while loop is for incrementing up to the index we want to
	    // remove
	    int count = 0;
	    while (count < index) {
		nodeRemoved = nodeRemoved.next;
		count++;
	    }
	    Node prevNode = nodeRemoved.prev; // Remember the node we are
	    // removing, its' prev node
	    Node nextNode = nodeRemoved.next; // Remember the node we are
	    // removing, its' next node
	    prevNode.next = nextNode;
	    nextNode.prev = prevNode;
	    size--;
	}
	// Garbage collection will take care of the node we just removed since
	// it's not used
	return nodeRemoved.data;
    }

    @Override
    public int indexOf(E element) {
	Node currentNode = head; // Start at the head
	int currentIndex = 0; // Start index at 0

	// Search for the element
	while (currentNode != null) {
	    // If the current element (the current node) equals the element we
	    // are searching for, return that index
	    if (currentNode.data.equals(element)) {
		return currentIndex;
	    }
	    // If we didn't trigger the if statement above, go to next node
	    currentNode = currentNode.next;
	    currentIndex++;
	}
	return -1; // Return -1 if we did not find the element
    }

    @Override
    public int lastIndexOf(E element) {
	Node currentNode = tail; // Start at the tail
	int currentIndex = size - 1; // Start the current index at the very end
	// (size-1), which we'll increment down

	// Make sure the current node is not null
	while (currentNode != null) {
	    // See if the current node we are at is the element
	    if (currentNode.data.equals(element)) {
		return currentIndex;
	    }
	    currentNode = currentNode.prev;
	    currentIndex--;
	}
	return -1;
    }

    @Override
    public int size() {
	return size;
    }

    @Override
    public boolean isEmpty() {
	if (head == null || size == 0) {
	    return true;
	}
	else {
	    return false;
	}
    }

    @Override
    public void clear() {
	head = null;
	size = 0;
    }

    @Override
    public Object[] toArray() {
	Object[] arr = new Object[size]; // Create array of the current size of
	// the DoublyLinkedList

	Node temp = head; // Assign the head node to a temp Node

	for (int index = 0; index < arr.length; index++) {
	    arr[index] = temp.data; // Add the node element to the array
	    temp = temp.next; // Go to the next node, assign the temp node to
	    // that node
	}
	return arr;
    }

}
