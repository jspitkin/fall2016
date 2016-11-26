/**
 * Assignment 08 - BinarySearchTree
 * Names: Nicholas Kerr & Dallin Van Mondfrans
 * Nicholas's uID: u0125990
 * Dallin's uID: u0717113
 * Date: October 26, 2016
 */

package assignment08;

/**
 * A Binary Search Tree is a recursive data structure, so you really only need to implement it's base
 * structure, the Binary Search Node! That's what we'll be doing in todays lab.
 * 
 * @param <T> - IMPORTANT: The BinarSearchNode is generic, but when you write Assignment08 DO NOT have your 
 * inner BinarySearchNode be generic. The BST class should be the only class with type arguments.
 * This class needs to be generic for the purposes of the lab, but if you use this class in Assignment 8, make 
 * sure to remove the type parameters.   
 */
public class BinarySearchNode<T extends Comparable<? super T>> {

	private T data;
	private BinarySearchNode<T> left;
	private BinarySearchNode<T> right;

	/**
	 * Construct a new node with known children
	 */
	public BinarySearchNode(T data, BinarySearchNode<T> left,
			BinarySearchNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * Construct a new node with no children
	 */
	public BinarySearchNode(T data) {
		this(data, null, null);
	}

	/**
	 * Getter method.
	 * @return the node data.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Setter method.
	 * @param data
	 *            - the node data to be set.
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Getter method.
	 * 
	 * @return the left child node.
	 */
	public BinarySearchNode<T> getLeft() {
		return left;
	}

	/**
	 * Setter method.
	 * @param _left
	 *            - the left child node to be set.
	 */
	public void setLeft(BinarySearchNode<T> left) {
		this.left = left;
	}

	/**
	 * Getter method.
	 * @return the right child node.
	 */
	public BinarySearchNode<T> getRight() {
		return right;
	}

	/**
	 * Setter method.
	 * @param right
	 *            - the right child node to be set.
	 */
	public void setRight(BinarySearchNode<T> right) {
		this.right = right;
	}


	/**
	 * Number of children Use this to help figure out which BST deletion case to
	 * perform
	 * 
	 * @return The number of children of this node
	 */
	public int numChildren() {
		int numChildren = 0;
		if (getLeft() != null) {
			numChildren++;
		}
		if (getRight() != null) {
			numChildren++;
		}
		
		return numChildren;
	}

	/**
	 * @return The leftmost node in the binary tree rooted at this node.
	 */
	public BinarySearchNode<T> getLeftmostNode() {
		if( this.getLeft() == null ) {
			return this;
		}
		return this.getLeft().getLeftmostNode();
	}
	
	/**
	 * @return The rightmost node in the binary tree rooted at this node.
	 */
	public BinarySearchNode<T> getRightmostNode() {
		if( this.getRight() == null ) {
			return this;
		}
		return this.getRight().getRightmostNode();
	}
	
	/**
	 * @return The height of the binary tree rooted at this node. The height of
	 *         a tree is the length of the longest path to a leaf node. Consider
	 *         a tree with a single node to have a height of zero.
	 * 
	 *         The height of a tree with more than one node is the greater of
	 *         its two subtrees' heights, plus 1
	 */
	public int height() {
		if( this.getLeft() == null && this.getRight() == null ) {
			return -1;
		}
		return Math.max(this.getLeft().height(), this.getRight().height()) + 1;
	}

	/**
	 * 
	 * This method applies to binary search trees only (not general binary
	 * trees).
	 * 
	 * @return The successor of this node.
	 * 
	 *         The successor is a node which can replace this node in a case-3
	 *         BST deletion. It is either the smallest node in the right
	 *         subtree, or the largest node in the left subtree.
	 */
	public BinarySearchNode<T> getSuccessor() {
		return this.getRight().getLeftmostNode();
	}
	
	/**
	 * @return whether or not this node is a "leaf" node (has no children)
	 */
	public boolean isLeaf() {
		return (this.getLeft() == null && this.getRight() == null);
	}
	
	/**
	 * Performs a depth-first search to find the specified item
	 * @param item - the item you are searching for
	 * @return a BinarySearchNode whose data matches the item, or null
	 * if cannot be found (not in this tree)
	 */
	public BinarySearchNode<T> DFS(T item) {
		
		if( item.compareTo(this.getData()) == 0 ) {
			return this;
		} else if( item.compareTo(this.getData()) < 0 ) {
			if( this.getLeft() == null ) {
				return null;
			}
			return this.getLeft().DFS(item);
		} else {
			if( this.getRight() == null ) {
				return null;
			}
			return this.getRight().DFS(item);
		}
	}
	
	/**
	 * Finds the parent of the child whose data matches the item specified
	 * @param item
	 * @return the parent BinarySearchNode of the child whose data matches 
	 * the item specified
	 */
	public BinarySearchNode<T> getParentOf(T item) {
		boolean skipLeft = false;
		boolean skipRight = false;
		if( this.getLeft() == null ) {
			skipLeft = true;
		}
		if( this.getRight() == null ) {
			skipRight = true;
		}
		
		// Base cases
		if( !skipLeft ) {
			if( item.compareTo(this.getLeft().getData()) == 0 ) {
				return this;
			}
		}
		if( !skipRight ) {
			if( item.compareTo(this.getRight().getData()) == 0 ) {
				return this;
			}
		}
		
		if( item.compareTo(this.getData()) < 0 ) {
			if( this.getLeft() == null ) { // Item is not in this tree
				return null;
			}
			return this.getLeft().getParentOf(item); // Recursive call
		} else {
			if( this.getRight() == null ) { // Item is not in this tree
				return null;
			}
			return this.getRight().getParentOf(item); // Recursive call
		}
	
	}
}
