package assignment06;

/**
 * 
 * @author Jacob Virgin, u0832933
 *
 * @param <E>
 */
public class Node<E> {
	E data;
	Node next;
	Node previous;
	
	public Node(){
		this.data = null;
		this.next = null;
	}
	
	public Node(E data){
		this.data = data;
		this.next = null;
	}
	
	public Node Next(){
		return next;
		
	}
	
	public Node Previous(){
		return previous;
	}

	public String toString(){
		return "" + data;
		
	}

}
