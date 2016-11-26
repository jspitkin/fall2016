package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * @author Qi Luo & Anthony Iovino 
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	public class BinarySearchNode {

		private T data;

		private BinarySearchNode left;

		private BinarySearchNode right;

		private BinarySearchNode parent;

		/**
		 * Construct a new BinarySearchNode with known children and parent.
		 */

		public BinarySearchNode( BinarySearchNode _parent,T _data, BinarySearchNode _left,BinarySearchNode _right) {

			this.parent = _parent;

			this.data = _data;

			this.left = _left;

			this.right = _right;

		}
	}

	private BinarySearchNode root;

	private int size;

	/**
	 * Construct a new BinarySearchTree.
	 */
	public BinarySearchTree(){
		root = new BinarySearchNode(null,null,null,null);
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
		if(item == null){
			throw new NullPointerException();
		}
		if(isEmpty()){
			root.data = item;
			size++;
			return true;
		}
		if(!contains(item)){
			BinarySearchNode newNode = new BinarySearchNode(null,item,null,null);
			add(root,newNode);   
			size++;
			return true;
		}
		return false;
	}

	/**
	 * A helper method to add item to the binary search tree.
	 * @param currentNode
	 * 					 - a BinarySearchNode.
	 * @param item
	 * 				- the item whose presence is ensured in this set
	 */
	private void add(BinarySearchNode currentNode, BinarySearchNode newNode){	
		//left
		if(currentNode.data.compareTo(newNode.data)>0){
			if(currentNode.left == null){  
				newNode.parent = currentNode;
				currentNode.left = newNode;
				return;
			}
			else{ 
				add(currentNode.left,newNode);
			}
		}
		//right
		else if(currentNode.data.compareTo(newNode.data)<0){
			if(currentNode.right == null){  
				newNode.parent = currentNode;
				currentNode.right = newNode;
				return;
			}
			else{ 
				add(currentNode.right,newNode);
			}
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
		boolean addAll = false;
		for(T item:items){
			if(!contains(item)){
				add(item);
				addAll = true;
			}
		}
		return addAll;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear() {
		root = new BinarySearchNode(null,null,null,null);
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
		if(item == null){
			throw new NullPointerException();
		}
		if(isEmpty()){
			return false;
		}
		return contains(root,item);
	}

	/**
	 * A helper method to determines if there is an item in this set that is 
	 * equal to the specified item.
	 * 
	 * @param currentNode
	 * 					- a BinarySearchNode.
	 * @param item
	 * 				- the item sought in this set. 
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 */
	private boolean contains(BinarySearchNode currentNode, T item){
		if(currentNode == null){
			return false;
		}
		
		int compareResult = currentNode.data.compareTo(item);
		
		if(compareResult>0){
			return contains(currentNode.left,item);
		}
		else if(compareResult<0){
			return contains(currentNode.right, item);
		}
		else{
			return true;
		}
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
		for(T item:items){
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
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return first(root).data;
	}

	/**
	 * A helper method to returns the first (i.e., smallest) 
	 * item in this set.
	 * @param currentNode
	 * 					- a BinarySearchNode.
	 * @return the first (i.e., smallest) item in this set.
	 */
	private BinarySearchNode first(BinarySearchNode currentNode){
		if(currentNode.left == null){
			return currentNode;
		}
		return first(currentNode.left);
	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty() {
		return size()==0;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *           if the set is empty
	 */
	@Override
	public T last() throws NoSuchElementException {
		if(isEmpty()){
			throw new NoSuchElementException();
		}
		return last(root).data;
	}

	/**
	 * A helper method to returns the last (i.e., largest) 
	 * item in this set.
	 * @param currentNode
	 * 					- a BinarySearchNode.
	 * @return the last (i.e., largest) item in this set.
	 */
	private BinarySearchNode last(BinarySearchNode currentNode){
		if(currentNode.right == null){
			return currentNode;
		}
		return last(currentNode.right);
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
		if (item == null) {
			throw new NullPointerException();
		}
		if (isEmpty()) {
			return false;
		}
		if(contains(item)){

			if(size()==1){
				clear();
				return true;
			}

			BinarySearchNode currentNodeParent = findParent(root,item);

			//remove the root
			if(currentNodeParent == null){
				if(numChildren(root)==1){
					if(root.left!=null){
						root = root.left;
						root.parent = null;
						size--;
						return true;
					}
					else if(root.right!=null){
						root = root.right;
						root.parent = null;
						size--;
						return true;
					}
				}
				else if(numChildren(root)==2){
					BinarySearchNode successor = getSuccessor(root);
					root.data = successor.data;
					if(successor.right!=null){
						successor.parent.left= successor.right;
					}
					else{
						successor.parent.left = null;
					}
					size--;
					return true;
				}
			}

			//the node is on the left side of the parent
			if(currentNodeParent.data.compareTo(item) > 0){
				//case 1:  no child
				if(numChildren(currentNodeParent.left) == 0){
					currentNodeParent.left = null;
				}
				//case 2 : one child
				else if(numChildren(currentNodeParent.left)==1){
					if(currentNodeParent.left.right == null){
						currentNodeParent.left = currentNodeParent.left.left;
						currentNodeParent.left.left = null;
					}
					else if(currentNodeParent.left.left == null){
						currentNodeParent.left = currentNodeParent.left.right;
						currentNodeParent.left.right = null;
					}
				}
				//case 3: two children
				else if(numChildren(currentNodeParent.left) == 2){
					BinarySearchNode successor = getSuccessor(currentNodeParent.left);
					currentNodeParent.left.data =successor.data;
					//if successor have right child.
					if(successor.right!=null){
						successor.parent.left= successor.right;
					}
					else{
						successor.parent.left = null;
					}
				}
			}
			//right side
			else{
				//case 1:  no child
				if(numChildren(currentNodeParent.right)==0){
					currentNodeParent.right=null;
				}
				//case 2 : one child
				else if(numChildren(currentNodeParent.right)==1){
					if(currentNodeParent.right.right==null){
						currentNodeParent.right = currentNodeParent.right.left;
						currentNodeParent.right.left = null;
					}
					else if(currentNodeParent.right.left==null){
						currentNodeParent.right = currentNodeParent.right.right;
						currentNodeParent.right.right = null;
					}
				}
				//case 3: two children
				else if(numChildren(currentNodeParent.right)==2){
					BinarySearchNode successor = getSuccessor(currentNodeParent.right);
					currentNodeParent.right.data =successor.data;
					//if successor have right child.
					if(successor.right!=null){
						successor.parent.left= successor.right;
					}
					else{
						successor.parent.left = null;
					}
				}
			}
			size--;
			return true; 
		}
		return false;
	}

	/**
	 * A helper method to get the successor.
	 * @param currentNode
	 * 					- the BinarySearchNode.
	 * @return the BinarySearchNode store the successor.
	 */
	private BinarySearchNode getSuccessor(BinarySearchNode currentNode){
		return first(currentNode.right);
	}

	/**
	 * A helper method to find the node's parent.
	 * @param currentNode
	 * 					- the BinarySearchNode.
	 * @param item
	 *          - the item whose absence is ensured in this set
	 * @return the BinarySearchNode when the node's value is equals to the item.
	 */
	private BinarySearchNode findParent(BinarySearchNode currentNode, T item ){
		if(currentNode.data.compareTo(item)<0){
			return findParent(currentNode.right, item);
		}
		else if(currentNode.data.compareTo(item)>0){
			return findParent(currentNode.left, item);
		}
		return currentNode.parent;
	}
	/**
	 * Number of children Use this to help figure out which BST deletion case to
	 * perform
	 * 
	 * @return The number of children of this node
	 */
	private int numChildren(BinarySearchNode currentNode) {
		int numChildren = 0;
		if (currentNode.left != null) {
			numChildren++;
		}
		if (currentNode.right != null) {
			numChildren++;
		}
		return numChildren;
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
		boolean removed=false;
		for(T item:items){
			if(contains(item)){
				remove(item);
				removed=true;
			}
		}
		return removed;
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
		ArrayList<T> result = new ArrayList<T>(size());
	
		if(size()==0){
			return result;
		}
		toArrayList(result,root);
		
		return result;
	}

	/**
	 * A helper method to add all of the items in this set to a ArrayList.
	 * @param currentNode
	 * 					- the BinarySearchNode.
	 */
	private void toArrayList(ArrayList<T> sortedArray, BinarySearchNode currentNode){  //idea from stackoverflow.com
		if(currentNode.left != null){
			toArrayList(sortedArray,currentNode.left);
		}
		sortedArray.add(currentNode.data);
		if(currentNode.right != null){
			toArrayList(sortedArray,currentNode.right);
		}
	}

	/**
	 * To create a dot file of BinarySearchTree.
	 * @param filename
	 * 					- the name of the file.
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

	/**
	 * Recursive method for writing the tree to a dot file
	 * @param n
	 * 			- the BinarySearchNode.
	 * @param output
	 * 				- the dot file.
	 * @throws Exception
	 */
	private void writeDotRecursive(BinarySearchNode n, PrintWriter output) throws Exception{
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

	/**
	 * A method to return the value store in the root. (Use it in Junit test)
	 * @return the value store in the root.
	 */
	public T getRoot(){
		return root.data;
	}
}

