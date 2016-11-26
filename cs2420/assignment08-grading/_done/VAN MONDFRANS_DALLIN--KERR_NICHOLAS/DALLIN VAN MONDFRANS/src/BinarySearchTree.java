/**
 * Assignment 08 - BinarySearchTree
 * Names: Nicholas Kerr & Dallin Van Mondfrans
 * Nicholas's uID: u0125990
 * Dallin's uID: u0717113
 * Date: October 26, 2016
 */

package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	private BinarySearchNode<T> root = new BinarySearchNode<T>(null);
	private int size;
	
	/**
	 * This is a no parameter constructor that initializes 
	 * this binary search tree
	 */
	public BinarySearchTree() {
		root.setData(null);
		size = 0;
	}
	
	/**
	 * @return the root of this binary search tree
	 */
	public BinarySearchNode<T> getRoot() { 
		return this.root;
	}
	
	/**
	 * {@inheritDoc};
	 */
	@Override
	public boolean add(T item) {
		boolean done = false;
		if( root.getData() == null ) {
			root.setData(item);
			size++;
			return true;
		}
		BinarySearchNode<T> currentNode = root;
		while( !done ) {
			if( item.compareTo(currentNode.getData()) == 0 ) {
				return false;
			} else if( item.compareTo(currentNode.getData()) < 0 ) {
				if(currentNode.getLeft() == null) {
					currentNode.setLeft(new BinarySearchNode<T>(item));
					size++;
					return true;
				}
				currentNode = currentNode.getLeft();
			} else {
				if(currentNode.getRight() == null) {
					currentNode.setRight(new BinarySearchNode<T>(item));
					size++;
					return true;
				}
				currentNode = currentNode.getRight();
			}
		}
		return false;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public boolean addAll(Collection<? extends T> items) {
		boolean result = false;
		for( T el : items ) {
			if( add(el) ) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public void clear() {
		root.setData(null);
		size = 0;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public boolean contains(T item) {
		return root.DFS(item) != null;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public boolean containsAll(Collection<? extends T> items) {
		boolean result = true;
		for( T el : items ) {
			if( !contains(el) ) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public T first() throws NoSuchElementException {
		if( root.getData() == null ) {
			throw new NoSuchElementException();
		}
		return root.getLeftmostNode().getData();
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public boolean isEmpty() {
		return size < 1;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public T last() throws NoSuchElementException {
		if( root.getData() == null ) {
			throw new NoSuchElementException();
		}
		return root.getRightmostNode().getData();
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public boolean remove(T item) {
		// Check for special cases
		if( root.getData() == null ) { // if the tree is empty, there is nothing to delete
			return false;
		} else if( !contains(item) ) { // if the set does not contain the item we want to remove, return
			return false;
		} else if( root.getData().equals(item) && size == 1 ) { // delete the only element in the tree
			root = new BinarySearchNode<T>(null);
			size--;
			return true;
		}
		
		// Initialize variables
		BinarySearchNode<T> parent = root.getParentOf(item);
		BinarySearchNode<T> child = root;
		boolean leftOfParent = true;
		boolean isRoot = false;
		
		if( parent == null  && item.equals(root.getData()) ) { // Determine if we are deleting the root node
			parent = root;
			child = root;
			isRoot = true;
		}
		
		// Deleting root node with only one child
		if( isRoot && parent.getLeft() == null ) {
			root = parent.getRight();
			size--;
			return true;
		} else if( isRoot && parent.getRight() == null ) {
			root = parent.getLeft();
			size--;
			return true;
		}

		// Determine which child of the parent we want to delete and keep track if it is left or right of parent
		if( parent.getLeft() == null ) {
			if( !isRoot ) {
				child = parent.getRight();
			}
			leftOfParent = false;
		} else if( parent.getRight() == null ) {
			if( !isRoot ) {
				child = parent.getLeft();
			}
			leftOfParent = true;
		} else if (!isRoot) {
			if( item.compareTo(parent.getLeft().getData()) == 0 ) {
				child = parent.getLeft();
				leftOfParent = true;
			} else {
				child = parent.getRight();
				leftOfParent = false;
			}
		}
		
		// Determine which deletion type this case is, and perform the deletion
		if( child.isLeaf() ) { // Case 1
			if( leftOfParent ) {
				parent.setLeft(null);
			} else {
				parent.setRight(null);
			}
		} else if( child.getLeft() == null ) { // Case 2 with child on right
			if( leftOfParent ) {
				parent.setLeft(parent.getLeft().getRight());
			} else {
				parent.setRight(parent.getRight().getRight());
			}
		} else if( child.getRight() == null ) { // Case 2 with child on left
			if( leftOfParent ) {
				parent.setLeft(parent.getLeft().getLeft());
			} else {
				parent.setRight(parent.getRight().getLeft());
			}
		} else { // Case 3
			BinarySearchNode<T> successor = child.getSuccessor();
			T temp = successor.getData();
			remove(successor.getData()); // Recursive call to remove the successor
			child.setData(temp);
			size++; // must increment size because the recursive call will decrement it, as will this method
		}
		
		size--;
		return true;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public boolean removeAll(Collection<? extends T> items) {
		boolean result = false;
		for( T el : items ) {
			if( remove(el) ) {
				result = true;
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * {@inheritDoc};
	 */
	@Override
	public ArrayList<T> toArrayList() {
		ArrayList<T> possibleArrayList = new ArrayList<T>();
		if (this.isEmpty()) {
			return possibleArrayList;
		}
		depthFirst(root, possibleArrayList);
		return possibleArrayList;
	}
	
	/**
	 * Perfoms a depth-first search to copy node data to the passed in array list
	 * @param node - where to begin the search
	 * @param arrayList - the trees data will be copied into
	 */
	private void depthFirst(BinarySearchNode<T> node, ArrayList<T> arrayList) {
		if (node == null) {
			return;
		}
		depthFirst(node.getLeft(), arrayList);
		arrayList.add(node.getData());
		depthFirst(node.getRight(), arrayList);
	}
	
	/**
	 * This method will write this BinarySearchTree to a dot file
	 * @param filename - the name of the file you want it to be written to
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
	    } catch(Exception e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
	 * Recursive method for writing the tree to  a dot file
	 * @param n - a BinarySearchNode<T>
	 * @param output - The dot file representing this specific tree
	 * @throws Exception - any exception
	 */
	private void writeDotRecursive(BinarySearchNode<T> n, PrintWriter output) throws Exception
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

}
