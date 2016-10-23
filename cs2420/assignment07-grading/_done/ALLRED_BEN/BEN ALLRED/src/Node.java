package assignment06;

/**
 * 
 * @author Benjamin Allred u1090524
 * simple class that is used in DoublyLinkedList
 * @param <E> generic type
 */
public class Node<E> {
	
	protected E data;
	protected Node<E> next, prev;
	
	/**
	 * default constructor
	 */
	Node()
	{
		next = null;
		prev = null;
		data = null;
	}
	
	/**
	 * sets data fields
	 * @param data
	 * @param next
	 * @param prev
	 */
	Node(E data, Node<E> next, Node<E> prev)
	{
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
}
