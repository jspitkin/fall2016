//Cole Cruz
package assignment07;

/**
 * Node for linked list.
 * 
 * @author Cole Cruz
 *
 * @param <E>
 *            -- node type.
 */
public class ListNode<E> {

	private E data;
	private ListNode<E> previous;
	private ListNode<E> next;

	/**
	 * Creates a list node with the specified data.
	 * 
	 * @param element
	 *            -- the data for the node.
	 */
	public ListNode(E element) {
		data = element;
	}

	/**
	 * Returns the data of the node.
	 * 
	 * @return the data of the node.
	 */
	public E getData() {
		return data;
	}

	/**
	 * Sets the data of the node.
	 * 
	 * @param data
	 *            -- the data for the node.
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * Returns the previous node.
	 * 
	 * @return the previous node.
	 */
	public ListNode<E> getPrevious() {
		return previous;
	}

	/**
	 * Sets the previous node.
	 * 
	 * @param previous
	 *            -- the node to set as the previous.
	 */
	public void setPrevious(ListNode<E> previous) {
		this.previous = previous;
	}

	/**
	 * Returns the next node.
	 * 
	 * @return the next node.
	 */
	public ListNode<E> getNext() {
		return next;
	}

	/**
	 * Sets the next node.
	 * 
	 * @param next
	 *            -- the node to set as the next.
	 */
	public void setNext(ListNode<E> next) {
		this.next = next;
	}

}
