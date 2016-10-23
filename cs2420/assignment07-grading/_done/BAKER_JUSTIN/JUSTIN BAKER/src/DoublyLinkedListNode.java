package assignment07;
/**
 * @author Justin Baker, u0726589
 */
public class DoublyLinkedListNode<E> {
	protected E element;
	protected DoublyLinkedListNode<E> next;
	protected DoublyLinkedListNode<E> previous;
	protected DoublyLinkedListNode(E element, DoublyLinkedListNode<E> next, DoublyLinkedListNode<E> previous){
		this.element = element;
		this.next=next;
		this.previous=previous;
	}
	/**
	 * creates empty LinkedListNode
	 */
	protected DoublyLinkedListNode(){
		
	}
	/**
	 * creates LinkedListNode with element
	 * @param element
	 */
	protected DoublyLinkedListNode(E element){
		this.element=element;
	}

}
