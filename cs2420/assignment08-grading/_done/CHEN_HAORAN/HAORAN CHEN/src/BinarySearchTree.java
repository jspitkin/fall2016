package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 *binarySerachTree Class which generates a binary search tree, along with a node class
 *to be used in tree.  
 *
 *@author Eduardo Ortiz/Haoran Chen
 *@uid u09226248/u1060286
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	private Node root;
	int size;
	
	private class Node {
		private T data;
		private Node left;
		private Node right;
		
		/**
		 * The node class needed for the tree
		 * @param data data stored in the node
		 * @param left left child of the node
		 * @param right right child of the node
		 */
		public Node(T data, Node left,
				Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		/**
		 * Construct a new node with no children
		 */
		public Node(T data) {
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
		public Node getLeft() {
			return left;
		}

		/**
		 * Setter method.
		 * @param _left
		 *            - the left child node to be set.
		 */
		public void setLeft(Node left) {
			this.left = left;
		}

		/**
		 * Getter method.
		 * @return the right child node.
		 */
		public Node getRight() {
			return right;
		}

		/**
		 * Setter method.
		 * @param right
		 *            - the right child node to be set.
		 */
		public void setRight(Node right) {
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
		
		public Node getLeftmostNode() {
			return getLeftmostNode(this);
		}
		
		private Node getLeftmostNode(Node node){
			while (node.getLeft()!=null){
				return getLeftmostNode(node.getLeft());
			}
			return node;
		}
		
		/**
		 * @return The rightmost node in the binary tree rooted at this node.
		 */
		public Node getRightmostNode() {
			return getRightmostNode(this);
		}
		
		private Node getRightmostNode(Node node){
			while (node.getRight()!=null){
				return getRightmostNode(node.getRight());
			}
			return node;
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
			if(numChildren()==0){
				return 0;
			}
			return Math.max(left.height(), right.height())+1;
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
		public Node getSuccessor() {
			Node tmp = this.getRight();;
			return tmp.getLeftmostNode();
		}
	}
	
	public BinarySearchTree(){
		root = new Node(null, null, null);
		
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
		if(item == null){
			throw new NullPointerException();
		}
		if(isEmpty()){
			root.setData(item);
			size++;
		}
		else{
			Node current = root;
			Node parent;
			while(current.getData().compareTo(item)!=0){
				parent = current;
				if(current.getData().compareTo(item)>0){
					current = current.left;
					if(current==null){
						parent.left = new Node(item,null,null);
						size++;
						return true;
					}
				}
				else if(current.getData().compareTo(item)<0){
					current = current.right;
					if(current==null){
						parent.right = new Node(item,null,null);
						size++;
						return true;
					}
				}
			}
		}		
		return false;
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
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			this.add((T) itr.next());
		}
		return true;
	}

	/**
	   * Removes all items from this set. The set will be empty after this method
	   * call.
	   */
	@Override
	public void clear() {
		root = new Node(null, null, null);	
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
		if(item == null){
			throw new NullPointerException();
		}
		if(size == 0)
			return false;
		Node current = root;	
		while(current.getData().compareTo(item)!=0){
			
			if(current.getData().compareTo(item)>0){
				current = current.left;			
			}
			else if(current.getData().compareTo(item)<0){
				current = current.right;
			}
			if((current == null)||(current.getData()== null)){//this check needed or error is found removeTest1
				return false;
			}
		
		}
		return true;
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
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			if(!this.contains((T) itr.next())){
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
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		return root.getLeftmostNode().data;
	}

	/**
	   * Returns true if this set contains no items.
	   */
	@Override
	public boolean isEmpty() {
		if(size!=0){
			return false;
		}
		return true;
	}

	  /**
	   * Returns the last (i.e., largest) item in this set.
	   * 
	   * @throws NoSuchElementException
	   *           if the set is empty
	   */
	@Override
	public T last() throws NoSuchElementException {
		if (this.isEmpty()){
			throw new NoSuchElementException();
		}
		return root.getRightmostNode().data;
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
	public boolean remove(T item) {
		if(item == null){
			throw new NullPointerException();
		}
		if(root.data == null)
			return false;
		
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		while(current.getData().compareTo(item)!=0){
			parent = current;
			if(current.getData().compareTo(item)>0){
				current = current.left;
				isLeftChild = true;
			}
			else if(current.getData().compareTo(item)<0){
				current = current.right;
				isLeftChild = false;
			}
			if(current== null){
				return false;
			}
		}
		

		// leaf
		if(current.numChildren()==0){
			if(current == root){
				root = new Node(null,null,null);
			}
			else if(isLeftChild){
				parent.left = null;			
			}
			else{
				parent.right = null;				
			}
		}
		
		//one child
		if(current.numChildren()==1){
			if(current == root){
				root = new Node(null,null,null);
			}
			if(current.right == null){
				if(isLeftChild){
					parent.left = current.left;				
				}
				else{
					parent.right = current.left;
				}
			}
			if(current.left == null){
				if(isLeftChild){
					parent.left = current.right;				
				}
				else{
					parent.right = current.right;
				}
					}
			}
		
		//two children
		if(current.numChildren()==2){
			Node successor = current.getSuccessor();
			remove(current.getSuccessor().data);
			if(current == root){
				root = successor;	
				successor.left = current.left;
				successor.right = current.right;
			}
			else if(isLeftChild){
				parent.left = successor;
				successor.right= current.right;
			}
			else{
				parent.right = successor;
				successor.left= current.left;
			}
		}
		size--;
		return true;
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
		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			remove((T) itr.next());
		}
		return true;
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
		ArrayList<T> bstArray = new ArrayList<T>();
	    toArrayRecursion(bstArray, root);
	    return bstArray;
	}

	/**Recursive method to generate an array of the BinarySearchTree.
	 * @param bstArray Array to add values to.
	 * @param currentNode node which is being added to arrayList. 
	 */
	private void toArrayRecursion(ArrayList<T> bstArray,Node currentNode) {
	    if (currentNode == null) {
	        return;
	    }
	    else{
	    	toArrayRecursion(bstArray,currentNode.left); 
	    	bstArray.add(currentNode.data); 
	    	toArrayRecursion( bstArray,currentNode.right); 
	    }
	}
	
	/**Method to generate file image of the BinarySearch Tree. 
	 * @param filename The file which you want to generate image of. 
	 */
	public void writeDot(String filename){
		try {
		 // PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));
			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");
			if(root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		}
		catch(Exception e){e.printStackTrace();}
		}
	
	
		// Recursive method for writing the tree to a dot file
	private void writeDotRecursive(Node n, PrintWriter output) throws Exception{
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if(n.left != null){
			// write the left subtree
			writeDotRecursive(n.left, output);
			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D" );
		}
		if(n.right != null)
		{
			// write the left subtree
			writeDotRecursive(n.right, output);
			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D" );
		}
	} 

}

