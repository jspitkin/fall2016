package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;


/**
 * A class containing the implementation of a BinarySearchTree structure. Contains
 * all the methods used in manipulated the data held in the tree. Makes reference to 
 * a BinarySearchNode class that is the basic foundation for the structure.
 * 
 * 
 * @author Stephen Hogan (u0813633) and Chris Grayston (u0906710)
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	private BinarySearchNode node;
	private int size;
	
	public BinarySearchTree() {
		node = new BinarySearchNode(null);
		size = 0;
	}
	
	/**
	   * Ensures that this set contains the specified item.
	   * 
	   * @param item
	   *          - the item whose presence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if the input item was actually inserted); otherwise, returns false
	   * @throws NullPointerException
	   *           if the item is null
	   */
	@Override
	public boolean add(T item) {
		validateItem(item);
		if(isEmpty()){
			node = new BinarySearchNode(item);
			size++;
			return true;
		}
		else{
			return node.add(item);
		}
	}

	/**
	   * Ensures that this set contains all items in the specified collection.
	   * 
	   * @param items
	   *          - the collection of items whose presence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if any item in the input collection was actually inserted);
	   *         otherwise, returns false
	   * @throws NullPointerException
	   *           if any of the items is null
	   */
	@Override
	public boolean addAll(Collection<? extends T> items) {
		boolean hasChanged = false;
		for(T item: items){
			if(add(item)){
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	/**
	   * Removes all items from this set. The set will be empty after this method
	   * call.
	   */
	@Override
	public void clear() {
		node = new BinarySearchNode(null);
		size = 0;		
	}

	/**
	   * Determines if there is an item in this set that is equal to the specified
	   * item.
	   * 
	   * @param item
	   *          - the item sought in this set
	   * @return true if there is an item in this set that is equal to the input
	   *         item; otherwise, returns false
	   * @throws NullPointerException
	   *           if the item is null
	   */
	@Override
	public boolean contains(T item) {
		validateItem(item);
		if(isEmpty()){
			return false;
		}
		if(comparison(item, binarySearch(node, item).getData()) == 0){
			return true;
		}
		return false;
	}

	/**
	   * Determines if for each item in the specified collection, there is an item
	   * in this set that is equal to it.
	   * 
	   * @param items
	   *          - the collection of items sought in this set
	   * @return true if for each item in the specified collection, there is an item
	   *         in this set that is equal to it; otherwise, returns false
	   * @throws NullPointerException
	   *           if any of the items is null
	   */
	@Override
	public boolean containsAll(Collection<? extends T> items) {		
		for(T item: items){
			if(!contains(item)){
				return false;
			}
		}
		return true;
	}

	/**
	   * Returns the first (i.e., smallest) item in this set.
	   * 
	   * @throws NoSuchElementException
	   *           if the set is empty
	   */
	@Override
	public T first() throws NoSuchElementException {
		validateBST();
		return node.getLeftmostNode().getData();
	}

	

	/**
	   * Returns true if this set contains no items.
	   */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	   * Returns the last (i.e., largest) item in this set.
	   * 
	   * @throws NoSuchElementException
	   *           if the set is empty
	   */
	@Override
	public T last() throws NoSuchElementException {
		validateBST();
		return node.getRightmostNode().getData();
		
	}
	
	/**
	   * Ensures that this set does not contain the specified item.
	   * 
	   * @param item
	   *          - the item whose absence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if the input item was actually removed); otherwise, returns false
	   * @throws NullPointerException
	   *           if the item is null
	   */
	@Override
	public boolean remove(T item){
		if(removeHelper(item)){
			size--;
			return true;
		}
		return false;
	}
	
	/**
	 * Helper method for the remove method. This is the method that actually does
	 * all the work. It makes a call to the remove method in the node class.
	 * 
	 * @param item - item to be removed
	 * @return true if this set changed as a result of this method call (that is,
	 *         if the input item was actually removed); otherwise, returns false
	 * @throws NullPointerException
	 *           if the item is null        
	 */
	private boolean removeHelper(T item) {
		validateItem(item);
        if (node == null)
              return false;
        else {
              if (node.getData() == item) {
                    BinarySearchNode auxRoot = new BinarySearchNode(null);
                    auxRoot.setLeft(node);
                    boolean result = node.remove(item, auxRoot);
                    node = auxRoot.getLeft();
                    return result;
              } else {
                    return node.remove(item, null);
              }
        }
        
  }
	
	/**
	   * Ensures that this set does not contain any of the items in the specified
	   * collection.
	   * 
	   * @param items
	   *          - the collection of items whose absence is ensured in this set
	   * @return true if this set changed as a result of this method call (that is,
	   *         if any item in the input collection was actually removed);
	   *         otherwise, returns false
	   * @throws NullPointerException
	   *           if any of the items is null
	   */
	@Override
	public boolean removeAll(Collection<? extends T> items) {
		boolean hasChanged = false;
		for(T item: items){
			if(remove(item)){
				hasChanged = true;
			}
		}
		return hasChanged;
	}

	/**
	   * Returns the number of items in this set.
	   */
	@Override
	public int size() {
		return size;
	}

	/**
	   * Returns an ArrayList containing all of the items in this set, in sorted
	   * order.
	   */
	@Override
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>();
		nodeToList(node, list);
		return list;
	}
	
	/**
	 * Helper method for the toArrayList method. This recursively traverses the list in 
	 * ascending order and adds each value of each node into the list.
	 * 
	 * @param node - the root of the sub tree
	 * @param list - the list to hold the elements
	 * @return the node of the next subtree because of its recursive nature.
	 */
	private BinarySearchNode nodeToList(BinarySearchNode node, ArrayList<T> list){
		if(node.numChildren() == 0){
			list.add(node.getData());
			return node;
		}
		if(node.getLeft() != null)
			nodeToList(node.getLeft(), list);
		list.add(node.getData());
		if(node.getRight() != null)
			nodeToList(node.getRight(), list);
		return null;
	}
	
	/**
	 * Binary Search method that traverses the tree recursively in a binary fashion as to 
	 * decrease significantly the time it takes to search the list.
	 * 
	 * @param node - root of the subtree
	 * @param item - the item being searched for
	 * @return the item if it is found in the tree, otherwise the leaf node that is closest
	 * 			to the item
	 */
	private BinarySearchNode binarySearch(BinarySearchNode node, T item){
		if(node.numChildren() == 0 || node.getData().compareTo(item) == 0){
			return node;
		}
		if(item.compareTo(node.getData()) < 0 && node.hasLeft()){
			return binarySearch(node.getLeft(), item);
		}
		if(item.compareTo(node.getData()) > 0 && node.hasRight()){
			return binarySearch(node.getRight(), item);
		}
		return node;
	}
	
	/**
	 * Used to validate whether or not the BST is not empty.
	 * @throws NoSuchElementException
	 */
	private void validateBST() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Validates whether or not the item being added or removed is not null
	 * @param item
	 */
	private void validateItem(T item) {
		if(item == null){
			throw new NullPointerException();
		}
	}
	
	/**
	 * Helper method used to compare two items.
	 * @param lhs - first item
	 * @param rhs - second item
	 * @return a negative number if lhs comes before rhs, positive number if lhs comes
	 * 			after rhs, and 0 if they are equal.
	 */
	private int comparison(T lhs, T rhs){
		return lhs.compareTo(rhs);
	}
	
	/**
	 * Method used to write a file in the format of .DOT so that you can see
	 * a visual representation of your BST.
	 * 
	 * @param filename - the name of the file after it is saved.
	 */
	public void writeDot(String filename)
	 {
	 try {
		 // PrintWriter(FileWriter) will write output to a file
		 PrintWriter output = new PrintWriter(new FileWriter(filename));
		 // Set up the dot graph and properties
		 output.println("digraph BST {");
		 output.println("node [shape=record]");
		 if(node != null)
			 writeDotRecursive(node, output);
		 // Close the graph
		 output.println("}");
		 output.close();
	 }
	 catch(Exception e){e.printStackTrace();}
	 }
	// Recursive method for writing the tree to a dot file
	 private void writeDotRecursive(BinarySearchNode n, PrintWriter output) throws Exception
	 {
		 output.println(n.getData() + "[label=\"<L> |<D> " + n.getData() + "|<R> \"]");
		 if(n.getLeft() != null)
		 {
			 // write the left subtree
			 writeDotRecursive(n.getLeft(), output);
			 // then make a link between n and the left subtree
			 output.println(n.getData() + ":L -> " + n.getLeft().getData() + ":D" );
		 }
		 if(n.getRight() != null)
		 {
			 // write the left subtree
			 writeDotRecursive(n.getRight(), output);
			 // then make a link between n and the right subtree
			 output.println(n.getData() + ":R -> " + n.getRight().getData() + ":D" ); 
		 }
	 }


	 /**
	  * A Binary Search Tree is a recursive data structure, so you really 
	  * only need to implement it's base structure, the Binary Search Node! 
	  * 
	  *  @author Stephen Hogan (u0813633) and Chris Grayston (u0906710)
	  *   
	  */
	 private class BinarySearchNode {

	 	private T data;
	 	private BinarySearchNode left;
	 	private BinarySearchNode right;

	 	/**
	 	 * Construct a new node with known children
	 	 */
	 	public BinarySearchNode(T data, BinarySearchNode left,
	 			BinarySearchNode right) {
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
	 	 * Checks to see if the node has a left child.
	 	 * 
	 	 * @return true if so, false otherwise
	 	 */
	 	public boolean hasLeft(){
	 		return left != null;
	 	}

	 	/**
	 	 * Getter method.
	 	 * 
	 	 * @return the left child node.
	 	 */
	 	public BinarySearchNode getLeft() {
	 		return left;
	 	}

	 	/**
	 	 * Setter method.
	 	 * @param _left
	 	 *            - the left child node to be set.
	 	 */
	 	public void setLeft(BinarySearchNode left) {
	 		this.left = left;
	 	}

	 	/**
	 	 * Checks to see if the node has a right child.
	 	 * 
	 	 * @return true if so, false otherwise
	 	 */
	 	public boolean hasRight(){
	 		return right != null;
	 	}
	 	
	 	/**
	 	 * Getter method.
	 	 * @return the right child node.
	 	 */
	 	public BinarySearchNode getRight() {
	 		return right;
	 	}

	 	/**
	 	 * Setter method.
	 	 * @param right
	 	 *            - the right child node to be set.
	 	 */
	 	public void setRight(BinarySearchNode right) {
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
	 		if (hasLeft()) {
	 			numChildren++;
	 		}
	 		if (hasRight()) {
	 			numChildren++;
	 		}
	 		
	 		return numChildren;
	 	}

	 	/**
	 	 * @return The leftmost node in the binary tree rooted at this node.
	 	 */
	 	public BinarySearchNode getLeftmostNode() {
	 		if(!hasLeft()){
	 			return this;
	 		}
	 		else{
	 			return getLeft().getLeftmostNode();
	 		}
	 	}
	 	
	 	/**
	 	 * @return The rightmost node in the binary tree rooted at this node.
	 	 */
	 	public BinarySearchNode getRightmostNode() {
	 		if(!hasRight()){
	 			return this;
	 		}
	 		else {
	 			return getRight().getRightmostNode();
	 		}
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
	 		if(numChildren() == 0){
	 			return 0;
	 		}
	 		if(hasLeft()){
	 			return getLeft().height() + 1;
	 		}
	 		else if(hasRight()){
	 			return getRight().height() + 1;
	 		}
	 		return -1;
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
	 	public BinarySearchNode getSuccessor() {
	 		if(hasRight())
	 			return getRight().getLeftmostNode();
	 		return getLeft().getRightmostNode();
	 	}
	 	
	 	/**
	 	 * Method used to add nodes. This method is called by the BST's add method.
	 	 * 
	 	 * @param item - to be added
	 	 * @return true if the item is successfully added, false otherwise
	 	 */
	 	public boolean add(T item) {
			if (item.compareTo(getData()) == 0){
				return false;
			}
			else if (item.compareTo(getData()) > 0) {
				if (getRight() == null) {
					setRight(new BinarySearchNode(item));
					size++;
					return true;
				} else
					return getRight().add(item);
			}
			else if (item.compareTo(getData()) < 0) {
				if (getLeft() == null) {
					setLeft(new BinarySearchNode(item));
					size++;
					return true;
				} else
					return getLeft().add(item);
			} 
			else
				return false;
		}
	 	
	 	/**
	 	 * Method that is useful in removing individual nodes and also managing the 
	 	 * references from the parent.
	 	 * 
	 	 * @param item - item to be removed
	 	 * @param parent - the parent of the node that contains the specified value (item)
	 	 * @return true if the item was removed, false otherwise
	 	 */
	 	public boolean remove(T item, BinarySearchNode parent) {
	 		if(item == null){
	 			return false;
	 		}
	 		if (item.compareTo(getData()) > 0) {
	             if (getRight() != null)
	             	return getRight().remove(item, this);
	             else
	             	return false;
	         } else if (item.compareTo(getData()) < 0) {
	             if (getLeft() != null)
	                 return getLeft().remove(item, this);
	             else
	                 return false;
	         } else {
	               if (getLeft() != null && getRight() != null) {
	             	  setData(getRight().getLeftmostNode().getData());
	             	  getRight().remove(getData(), this);
	               } else if (parent.getLeft() == this) {
	             	  if(hasLeft()){
	                 	  parent.setLeft(getLeft());
	             	  }
	             	  else{
	             		  parent.setLeft(getRight());
	             	  }
	               } else if (parent.getRight() == this) {
	             	  if(hasLeft()){
	                 	  parent.setRight(getLeft());
	             	  }
	             	  else{
	             		  parent.setRight(getRight());
	             	  }
	               }
	               return true;
	         }
	   }
	 }


}
