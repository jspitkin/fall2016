package assignment08;
/**
 * Template for a list.
 * 
 * @author Hyrum Johnson
 * 
 * @param <E>
 */
public class Node<E> {
	
	E data;
	public Node<E> left;
	public Node<E> right;

	/**
	 * This keeps track of the data, left, and right values.  
	 * @param data - value connected to the Node.
	 * @param left - Left Node connected to the current node.
	 * @param right - Right Node connected to the current node.
	 */
	public Node(E data, Node<E> left, Node<E> right) 
	{
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * This creates a Node with data, left, and right values. 
	 * @param data
	 */
	public Node(E data) 
	{
		this(data, null, null);
	}

	/**
	 * This get's the Left Node from the current position.
	 * @return left node
	 */
	public Node<E> getLeft(){
		return this.left;
	}

	/**
	 * This get's the Right Node from the current position.
	 * @return Right node
	 */
	public Node<E> getRight(){
		return this.right;
	}

	/**
	 * This method recurses down the the left Nodes until there are no more.  
	 * @return The left most Node from the current node.  
	 */
	public Node<E> getLeftMost(){
		if(this.getLeft() == null){
			return this;
		}else{
			return this.left.getLeftMost();
		}
	}

	/**
	 * This method recurses own the right Nodes until there are no more.
	 * @return The right most Node from the current node.
	 * 
	 */
	public Node<E> getRightMost(){
		if(this.getRight() == null){
			return this;
		}
		return this.right.getRightMost();
	}
}