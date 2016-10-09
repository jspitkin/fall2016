package assignment06;
//Travis Taylor Cassity
//u0905687
import java.util.Iterator;
import java.util.NoSuchElementException;


public class DoublyLinkedList<E> implements List<E>, Iterable<E>{
	//Include a zero-parameter constructor, public DoublyLinkedList(). 
		//This is known as the 'default constructor'
	//TODO:
		//Ask about if iterator should be E or Node
		//Ask if toArray should be the E data of each node, or just each node. For now, assuming data.
		//Java Docs
	private Node head;
	private Node tail;
	private Node itHead;
	private int indexer;
	private int itCounter;
	private boolean nextCalled = false;

	
	private class Node {
		private E data;
		private Node next;
		private Node previous;
	} 

	
	/**
	 * Constructor
	 */
	public DoublyLinkedList() {
		indexer = -1;
		itCounter = -1;
		
	}
	
	/**
	 * Shoves element at the start of the linked list. Becomes new head.
	 */
	@Override	
	public void addFirst(E element) {
		Node newNode = new Node();
		newNode.data = element;
		indexer++;
		
		if(head == null){
			head = newNode;
			tail = newNode;
			return;
		}
		newNode.next = head;
		head.previous = newNode;
		head = newNode;
	}

	/**
	 * Tacks 'o' onto the end of the linked list. Becomes new tail.
	 */
	@Override
	public void addLast(E o) {
		Node newNode = new Node();
		newNode.data = o;
		indexer++;
		
		//Empty list
		if(head == null){
			head = newNode;
			tail = newNode;
			return;
		}
		newNode.previous = tail;
		tail.next = newNode;
		tail = newNode;
	}

	/**
	 * Adds given element at given index. Throws an error if the index is outside of the linked list size.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException {
		
		Node newNode = new Node();
		Node previous = new Node();
		newNode.data = element;
		
		//LL has no items. Set head/tail and exit. TODO: Remove the || right
		if(index == 0 || indexer<0){
			addFirst(element);
			return;
		}
		//Insert at tail
		else if(index == size()){
			addLast(element);
			return;
		}
		//Index error
		if(index >= size())
			throw new IndexOutOfBoundsException();
		
		//Add newnode
			//Get previous
		previous = getNode(index-1);
			//Set the new node's previous to be previous,
			//our new node's next to be previous' old next,
			//and previous to point to our new node.
		newNode.previous = previous;
		newNode.next=previous.next;
		previous.next=newNode;
		indexer++;
		
	}

	/** Extra method
	 * Appends the contents of the input array to the linked list.
	 * @param elements
	 */
	public void addAll(E[] elements){
		for(int i = 0; i < elements.length; i++)
			add(indexer, elements[i]);
	}
	
	/**Grabs the first element (the head) of the linked list, if it exists
	 * 
	 */
	@Override
	public E getFirst() throws NoSuchElementException {
		if(head == null)
			throw new NoSuchElementException();
		return (E) head.data;
	}

	/**
	 * Grabs the last element (the tail) of the linked list, if it exists
	 */
	@Override
	public E getLast() throws NoSuchElementException {
		if(tail == null)
			throw new NoSuchElementException();
		return (E) tail.data;
	}

	/**
	 * Snatches up the value at the given index, if it is within the linked list size. Returns -1 if it is not 
	 * in the Linked List.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		
		if(index >= size())
			throw new IndexOutOfBoundsException();
		Node temp = head;
		
		for(int i = 0; i < index; i++){
			temp = temp.next;
		}
		return (E) temp.data;
	}

	/**
	 * Returns the node at the given index.
	 * @param index
	 * @return
	 */
	public Node getNode(int index){
		Node temp = head;
		
		for(int i = 0; i < index; i++){
			temp = temp.next;
		}
		return temp;
	}
	
	/**
	 * Chops off the linked lists's head.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException {
		//If empty
		if(indexer <0)
			throw new NoSuchElementException();
		
		//Grab head data since it will be "lost"
		E temp = (E) head.data;
		//Do
		head = head.next;
		indexer--;
		
		return temp;
	}

	/**
	 * Cuts off the linked list's tail. 
	 * "Like the ingenious salamander, the wild Linked List will grow a new tail after some time,
	 * if it has enough mass left. "
	 */
	@Override
	public E removeLast() throws NoSuchElementException {
		//If empty
		if(indexer < 0)
			throw new NoSuchElementException();
		
		//Grab tail data since it will be "lost"
		E temp = (E) tail.data;
		//Do
		tail = tail.previous;
		indexer--;
		
		return temp;
	}

	/**
	 * Chops the index out of the linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException {
		
		if(index >= size())
			throw new IndexOutOfBoundsException();
		
		//Skips the rest of the if statements. Unbelievably tiny optimization.
		if(index != 0 && index != indexer);
		//Empty, head, and tail handlers
		else if(indexer < 0)
			return null;
		else if(index == 0)
			return removeFirst();
		else if(index == indexer)
			return removeLast();
		
		Node current = head;
		
		for(int i = 0; i < index; i++)
			current = current.next;
		
		//Set the nodes
		//TODO: Test these! May have to use Get to find the previous and next, since this might be overwriting them.
		return removeHelper(current);
	}

	private E removeHelper(Node current) {
		current.next.previous = current.previous;
		current.previous.next = current.next;
		current.next = null;
		current.previous = null;

		indexer--;
		
		return (E) current.data;
	}

	/**
	 * Finds the first index where the given element exists in the linked list. If it doesn't exist,
	 * it returns a -1. In the Sasquatch Hunters Society, we call this a "video editing opportunity"
	 */
	@Override
	public int indexOf(E element) {
		// TODO Auto-generated method stub
		if(indexer<0)
			return -1;
		
		Node temp = head;
		
		for(int i = 0; i < size(); i++)
			if(temp.data.equals(element)) //TODO: This is failing when size is 129 or higher? why?
				return i;
			else
				temp = temp.next;
		
		return -1;
	}

	/**
	 * Find the last index in the linked list where the given element exists. 
	 * If it does.
	 * {Twilight Zone music plays}
	 */
	@Override
	public int lastIndexOf(E element) {
		if(size() <1)
			return -1;
		if(tail.data == element)
			return indexer;
		
		Node temp = head;
		int lastSeen = -1;
		
		for(int i = 0; i < size(); i++)
			if(temp.data == element){
				temp = temp.next;
				lastSeen = i;
			}
			else
				temp = temp.next;
			
		return lastSeen;
	}

	/**
	 * Reports the current size of the linked list. Note: Do not fat shame the linked list. It's sensitive.
	 */
	@Override
	public int size() {
		return indexer+1;
	}

	/**
	 * Reports if the linked list is empty. If it is, it's probably hungry. Feed it.
	 */
	@Override
	public boolean isEmpty() {
		if(indexer < 0)
			return true;
		return false;
	}

	/**
	 * Empties the linked list.
	 * Also makes it opaque.
	 */
	@Override
	public void clear() {
		tail = null;
		head = null;
		indexer = -1;
	}

	/**
	 * Converts the linked list to an array by ordering it logically through .next, and fills the data with the node's data.
	 * "One day, Bits the Linked List realized it was an array trapped in a Linked List's body. After a few
	 * trips to a psychologist, Bits was cleared to get a dataType to an array. Bit now prefers to be called Bytes."
	 */
	@Override
	public Object[] toArray() {
		//Nullchecker
		if(indexer < 0)
			return null;
		
		Object[] outArray = new Object[size()];
		Node temp = head;
		
		for(int i = 0; i < size(); i++){
			outArray[i] = temp.data;
			temp = temp.next;
		}
		
		return outArray;
	}
	
	/**
	 * Extra method. Calls toArray then prints the array to the console.
	 */
	public void print(){
		if(size() <1)
			return;
		Object[] out = toArray();
		for(int i = 0; i < out.length; i++)
			System.out.println(out[i]);
	}
	
	//ITERATORS
	@Override
	public Iterator<E> iterator() {
		itHead = head;
		return new Iterator<E>() {
			
			/**
			 * Checks if the next value is null or not (exists)
			 * Summons the question, "Do I have a next bit?" As our linked list is a Buddist,
			 * it believes there is always a Next.
			 */
			@Override
			public boolean hasNext() {
				if (itHead.next== (null)) {
					return false;
				}
				return true;
			}
			
			/**
			 * Returns the value of the next node, and moves the iterator up one step.
			 */
			@Override
			public E next() {
				if(!hasNext())
					throw new NoSuchElementException();
				E data = itHead.data;
				itHead = itHead.next;
				itCounter++;
				nextCalled = true;
				return (E) data;
			}

			@Override
			public void remove() throws IllegalStateException {
				if(itCounter < 0 || !nextCalled)
					throw new IllegalStateException();
				itHead = itHead.next;
				nextCalled = false;
				removeHelper(itHead.previous);
			}
		};
	}
	

}
