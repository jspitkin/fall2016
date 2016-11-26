package assignment08;

/**
 *  @author Mina Kim u 1054673 & Miao Yu u0827659
 */
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T>{

	/**
	 * A Binary Search Tree is a recursive data structure, so you really only need to implement it's base
	 * structure, the Binary Search Node! That's what we'll be doing in todays lab.
	 * 
	 * @param <T> - IMPORTANT: The BinarSearchNode is generic, but when you write Assignment08 DO NOT have your 
	 * inner BinarySearchNode be generic. The BST class should be the only class with type arguments.
	 * This class needs to be generic for the purposes of the lab, but if you use this class in Assignment 8, make 
	 * sure to remove the type parameters.   
	 */
	public class BinarySearchNode {

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
		public BinarySearchNode getLeftmostNode() {
			if (left == null)
				return this;
			return left.getLeftmostNode();
		}

		/**
		 * @return The rightmost node in the binary tree rooted at this node.
		 */
		public BinarySearchNode getRightmostNode() {
			if (right == null)
				return this;
			return right.getRightmostNode();
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
			if (numChildren() == 0)
				return 0;
			return Math.max(left.height(), right.height()) + 1;
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
		public BinarySearchNode getSuccessor() 
		{
			if (this.right != null)
				return right.getLeftmostNode();
			else if (this.left != null)
				return left.getRightmostNode();
			else
				return this;
		}
	}


	private BinarySearchNode root;
	private int numOfElements; 
	/**
	 * Default constructor for binary search tree class
	 */
	public BinarySearchTree()
	{
		root = null;
		numOfElements = 0;
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
	public boolean add(T item) throws NullPointerException
	{
		if (item == null){
			throw new NullPointerException();
		}

		BinarySearchNode node = new BinarySearchNode(item, null, null);
		if (root == null)
		{
			this.root = node;
			this.numOfElements++;
			return true;
		}
		else
			return addRecursive(item, root);
	}

	private boolean addRecursive(T item, BinarySearchNode node)
	{
		BinarySearchNode itemContainer = new BinarySearchNode(item);

		if (item.compareTo(node.data) < 0)
		{
			if (node.getLeft() == null)
			{
				node.setLeft(itemContainer);
				this.numOfElements++;
				return true;
			}
			else
				return addRecursive(item, node.left);
		}
		else if (item.compareTo(node.data) > 0)
		{
			if (node.getRight() == null)
			{
				node.setRight(itemContainer);
				this.numOfElements++;
				return true;
			}
			else
				return addRecursive(item, node.right);
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
	public boolean addAll(Collection<? extends T> items) throws NullPointerException
	{
		if (items.isEmpty() == true)
			return true;

		boolean added = false;
		for(T item: items)
		{
			if (item == null)
				throw new NullPointerException();
			else
			{
				boolean addedElem = add(item);
				if (addedElem == true)
					added = true;
			}
		}

		return added;
	}
	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		this.root = null;
		this.numOfElements = 0;
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
	public boolean contains(T item) throws NullPointerException {
		if(item==null)
			throw new NullPointerException();

		if (containsRecursive(item, root) == true)
			return true;
		return false;
	}

	/**
	 * Recursive helper method for the contains method
	 * @param item
	 * @param node
	 * @return
	 */
	public boolean containsRecursive(T item, BinarySearchNode node)
	{
		if (node == null)
			return false;
		else if (item.compareTo(node.data) < 0)
		{
			if (node.getLeft() == null)
				return false;
			else if (node.getLeft().data == null)
				return false;
			else if (node.getLeft().data.compareTo(item) == 0)
				return true;
			else
				return containsRecursive(item, node.left);
		}
		else if (item.compareTo(node.data) > 0)
		{
			if (node.getRight() == null)
				return false;
			else if (node.getRight().data == null)
				return false;
			else if (node.getRight().data.compareTo(item) == 0)
				return true;
			else
				return containsRecursive(item, node.right);
		}
		else
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
	public boolean containsAll(Collection<? extends T> items) throws NullPointerException {
		for(T item : items){
			if(item==null)
				throw new NullPointerException();
			// TODO do we return true as long as only one item is included then? or if all items are included
			// for now let's say true as long as all items are contained
			boolean itemContained = contains(item);
			if (itemContained == false)
				return false;
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
		if(isEmpty())
			throw new NoSuchElementException();	
		return root.getLeftmostNode().data;
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() 
	{
		if (numOfElements == 0)
			return true;
		return false;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *           if the set is empty
	 */
	@Override
	public T last() throws NoSuchElementException {
		if(isEmpty())
			throw new NoSuchElementException();	
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
	public boolean remove(T item) throws NullPointerException{
		if(item == null)
			throw new NullPointerException();
		if(this.contains(item))
			return removeRecursive(item, root, false);
		return false;
	}

	public boolean removeRecursive(T item, BinarySearchNode node, boolean subRecursion)
	{
		if (node == null)
			return false;
		else if (node.data == item)
		{
			if (node.getLeft() == null && node.getRight() == null)
				node.setData(null);
			else
			{
				T replacement = node.getSuccessor().data;
				removeRecursive(replacement, node, true);
				node.data = replacement;
			}
			if (subRecursion == false)
				this.numOfElements--;
			return true;
		}
		else if (item.compareTo(node.data) < 0)
		{
			if (node.getLeft() == null)
				return false; // TODO ???
			else if (node.getLeft().data == null)
				return false;
			else if (node.getLeft().data == item)
			{
				if (node.getLeft().getLeft() == null && node.getLeft().getRight() == null)
					node.setLeft(null);
				else if (node.getLeft().getLeft() == null)
					node.setLeft(node.getLeft().getRight());
				else if (node.getLeft().getRight() == null)
					node.setLeft(node.getLeft().getLeft());
				else
				{
					T replacement = node.getLeft().getSuccessor().data;
					removeRecursive(replacement, node, true);
					node.getLeft().data = replacement;
				}
				if (subRecursion == false)
					this.numOfElements--;
				return true;
			}
			else
				return removeRecursive(item, node.left, subRecursion);
		}
		else if (item.compareTo(node.data) > 0)
		{
			if (node.getRight() == null)
				return false; // TODO ???
			else if (node.getRight().data == null)
				return false;
			else if (node.getRight().data == item)
			{
				if (node.getRight().getLeft() == null && node.getRight().getRight() == null)
					node.setRight(null);
				else if (node.getRight().getLeft() == null)
					node.setRight(node.getRight().getRight());
				else if (node.getRight().getRight() == null)
					node.setRight(node.getRight().getLeft());
				else
				{
					T replacement = node.getRight().getSuccessor().data;
					removeRecursive(replacement, node, true);
					node.getRight().data = replacement;
				}
				if (subRecursion == false)
					this.numOfElements--;
				return true;
			}
			else
				return removeRecursive(item, node.right, subRecursion);
		}
		else
		{
			if (node.getLeft().data == null)
				return false;
			else if (node.getLeft().getLeft().data == null && node.getLeft().getRight().data == null)
				node.setLeft(null);
			else if (node.getLeft().getLeft().data == null)
				node.setLeft(node.getLeft().getRight());
			else if (node.getLeft().getRight().data == null)
				node.setLeft(node.getLeft().getLeft());
			else
			{
				T replacement = node.getSuccessor().data;
				removeRecursive(replacement, node, true);
				node.getLeft().data = replacement;
			}
			if (subRecursion == false)
				this.numOfElements--;
			return true;
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
	public boolean removeAll(Collection<? extends T> items) throws NullPointerException{
		if (items.isEmpty() == true)
			return true;

		boolean removed = false;
		for(T item: items)
		{
			if (item == null)
				throw new NullPointerException();
			else
			{
				boolean removedElem = remove(item);
				if (removedElem == true)
					removed = true;
			}
		}
		return removed;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size() {
		return numOfElements;
	}


	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<T> toArrayList() {
		ArrayList<T> result = new ArrayList();
		recursiveToArrayList(result,root);
		return result;
	}
	
	public void recursiveToArrayList(ArrayList<T> result, BinarySearchNode node){
		
		if(node==null){
			return ;
		}
		recursiveToArrayList(result,node.left);
		result.add(node.data);
		recursiveToArrayList(result,node.right);		
	}
	
	

	/**
	 * Writes the data into a dot file that can be graphed
	 * @param filename
	 */
	public void writeDot(String filename)
	{
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

	/**
	 * Recursive method for writing the tree to a dot file
	 * @param n
	 * @param output
	 * @throws Exception
	 */
	private void writeDotRecursive(BinarySearchNode n, PrintWriter output) throws Exception
	{
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if(n.left != null)
		{
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	



