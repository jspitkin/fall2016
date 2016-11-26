package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * An interface to represent a binary search tree of generically-typed items.
 * The tree contains no duplicate items. The items are ordered using their
 * natural ordering.
 * 
 * @author Colton Haacke (u0935270) and Ashley Grevelink (u0749357)
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements
		SortedSet<T> {

	private BSTNode root;

	/**
	 * Creates an empty binary search tree
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Creates a binary search tree with a given node as the root
	 * 
	 * @param node
	 *                - the node that will be the root of the tree
	 */
	public BinarySearchTree(BSTNode node) {
		root = node;
	}

	public static void main(String[] args) {
		BinarySearchTree<String> test = new BinarySearchTree<String>();
		test.add("dog");
		test.add("cat");
		test.add("elephant");
		test.writeDot("dogcatelephant.dot");

	}

	/**
	 * If the item is not already in the tree, adds it.
	 * 
	 * @param item
	 *                - The item to be added
	 * 
	 * @return - true if the tree was changed, false if it was not
	 * 
	 * @throws NullPointerException
	 *                 - if the item is null
	 */
	@Override
	public boolean add(T item) throws NullPointerException {
		if (item == null) {
			throw new NullPointerException();
		}
		if (root == null) {
			root = new BSTNode(item);
			return true;
		}
		return add(item, root);
	}

	/**
	 * Recursive case of the add method.
	 * 
	 * If the item is not already in the tree, adds it.
	 * 
	 * @param item
	 *                - The item to be added
	 * 
	 * @param currentRoot
	 *                - The current root of the subtree being looked at
	 * 
	 * @return - true if he tree was changed, false if it was not
	 */
	private boolean add(T item, BSTNode currentRoot) {
		if (currentRoot == null) {
			currentRoot = new BSTNode(item);
			return true;
		}
		int comparisonValue = item.compareTo(currentRoot.data);

		if (comparisonValue < 0 && currentRoot.left != null) {
			return add(item, currentRoot.left);
		}
		else if (comparisonValue > 0 && currentRoot.right != null) {
			return add(item, currentRoot.right);
		}
		
		if (comparisonValue < 0) {
			currentRoot.left = new BSTNode(item);
			return true;
		}
		else if (comparisonValue > 0) {
			currentRoot.right = new BSTNode(item);
			return true;
		}
		// duplicate because comparisonValue == 0
		return false;
	}

	/**
	 * If any items in the collection are not already in the tree, adds
	 * them.
	 * 
	 * @param items
	 *                - The items to be added
	 * 
	 * @return - true if the tree was changed, false if it was not
	 * 
	 * @throws NullPointerException
	 *                 - if any of the items are null
	 */
	@Override
	public boolean addAll(Collection<? extends T> items)
			throws NullPointerException {
		boolean changed = false;
		for (T item : items) {
			changed = add(item) || changed;
		}
		return changed;
	}

	/**
	 * Removes all items from the tree.
	 */
	@Override
	public void clear() {
		root = null;
	}

	/**
	 * Determines if the item is equal to one already contained in the set.
	 * 
	 * @param item
	 *                - the item we are looking for
	 * 
	 * @return - true if an equal item is found, if not returns false
	 * 
	 * @throws NullPointerException
	 *                 - if the item is null
	 */
	@Override
	public boolean contains(T item) throws NullPointerException {
		if (item == null) {
			throw new NullPointerException();
		}
		return contains(item, root);
	}

	/**
	 * Recursive case of the contains method Determines if the item is equal
	 * to one already contained in the set.
	 * 
	 * @param item
	 *                - the item we are looking for
	 * 
	 * @param currentRoot
	 *                - The current root of the subtree being looked at
	 * 
	 * @return - true if an equal item is found, if not returns false
	 */
	private boolean contains(T item, BSTNode currentRoot) {
		if (currentRoot == null) {
			return false;
		}
		int comparisonValue = item.compareTo(currentRoot.data);

		if (comparisonValue < 0) {
			return contains(item, currentRoot.left);
		} else if (comparisonValue > 0) {
			return contains(item, currentRoot.right);
		}
		return true;
	}

	/**
	 * Determines if for every item in the list, if it is equal to one
	 * already contained in the set.
	 * 
	 * @param item
	 *                - the collection of items we are looking for
	 * 
	 * @return - true if an equal item is found for every item in the
	 *         collection, if not returns false
	 * 
	 * @throws NullPointerException
	 *                 - if the item is null
	 */
	@Override
	public boolean containsAll(Collection<? extends T> items)
			throws NullPointerException {
		boolean contains = true;
		for (T item : items) {
			contains = contains(item) && contains;
		}
		return contains;
	}

	/**
	 * Returns the first item in this set.
	 * 
	 * @throws NoSuchElementException
	 *                 - if the set is empty
	 */
	@Override
	public T first() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		}
		return root.getLeftmostNode().data;
	}

	/**
	 * Checks if the tree is empty
	 * 
	 * @return true if there are no items in the tree
	 */
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Returns the last item in this set.
	 * 
	 * @throws NoSuchElementException
	 *                 - if the set is empty
	 */
	@Override
	public T last() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		}
		return root.getRightmostNode().data;
	}

	/**
	 * If the item is in the tree, removes it.
	 * 
	 * @param item
	 *                - The item to be removed
	 * 
	 * @return - true if the tree was changed, false if it was not
	 * 
	 * @throws NullPointerException
	 *                 - if the item is null
	 */
	@Override
	public boolean remove(T item) throws NullPointerException {
		if (item == null) {
			throw new NullPointerException();
		}
		if (root == null) {
			return false;
		}
		if (item.equals(root.data)) {
			// If the item being removed is the root, there is no
			// parent, so it passes the method null.
			remove((BSTNode) null, root);
			return true;
		}
		return remove(item, root);
	}

	/**
	 * Recursive case of the remove method
	 * 
	 * If the item is in the tree, removes it.
	 * 
	 * @param - The item to be removed
	 * 
	 * @return - true if the tree was changed, false if it was not
	 * 
	 * @throws NullPointer
	 *                 Exception - if the item is null
	 */
	private boolean remove(T item, BSTNode currentNode) {
		int comparisonValue = item.compareTo(currentNode.data);

		if (comparisonValue < 0) {
			if (currentNode.left == null) {
				return false;
			}
			if (currentNode.left.data == item) {
				remove(currentNode, currentNode.left);
				return true;
			}
			return remove(item, currentNode.left);
		} else {
			if (currentNode.right == null) {
				return false;
			}
			if (currentNode.right.data == item) {
				remove(currentNode, currentNode.right);
				return true;
			}
			return contains(item, currentNode.right);
		}
	}

	/**
	 * Last step of the remove method
	 * 
	 * Removes the child node from the tree, and sets the parent node's
	 * references to the correct nodes
	 * 
	 * @param parent
	 *                - the parent of the node being removed
	 * @param child
	 *                - the node being removed
	 */
	private void remove(BSTNode parent, BSTNode child) {
		BSTNode replacement = findReplacement(child);
		if (parent == null) {
			root = replacement;
		} else {
			parent.replace(child, replacement);
		}
	}

	/**
	 * Finds the replacement node for a given node
	 * 
	 * @param node
	 *                - The node we are finding a replacement for
	 *                
	 * @return - The node that is a replacement for our given node
	 */
	private BSTNode findReplacement(BSTNode node) {
		if (node.numChildren() == 0) {
			return null;
		} else if (node.numChildren() == 1) {
			if (node.left == null) {
				return node.right;
			} else {
				return node.left;
			}
		} else {
			BSTNode replacement = node.getSuccessor();
			replacement.left = node.left;
			replacement.right = node.right;
			return replacement;
		}
	}

	/**
	 * If any of the items are in the tree, removes them.
	 * 
	 * @param item
	 *                - The items to be removed
	 * 
	 * @return - true if the tree was changed, false if it was not
	 * 
	 * @throws NullPointerException
	 *                 - if the item is null
	 */
	@Override
	public boolean removeAll(Collection<? extends T> items)
			throws NullPointerException {
		boolean changed = false;
		for (T item : items) {
			changed = remove(item) || changed;
		}
		return changed;
	}

	/**
	 * Checks the size of the tree
	 */
	@Override
	public int size() {
		if (root == null) {
			return 0;
		}
		int size = 1;
		if (root.left != null) {
			size += new BinarySearchTree<T>(root.left).size();
		}
		if (root.right != null) {
			size += new BinarySearchTree<T>(root.right).size();
		}
		return size;
	}

	/**
	 * Creates an arrayList from the items in the tree
	 */
	@Override
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>();
		if (root == null) {
			return list;
		}
		if (root.left != null) {
			list.addAll(new BinarySearchTree<T>(root.left)
					.toArrayList());
		}
		list.add(root.data);
		if (root.right != null) {
			list.addAll(new BinarySearchTree<T>(root.right)
					.toArrayList());
		}
		return list;
	}

	/**
	 * A representation of a node for a binary search tree, that contains
	 * its value and a reference to two children.
	 */
	public class BSTNode {

		private T data;
		private BSTNode left;
		private BSTNode right;

		/**
		 * Construct a new node with known children
		 */
		public BSTNode(T data, BSTNode left, BSTNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		/**
		 * Construct a new node with no children
		 */
		public BSTNode(T data) {
			this(data, null, null);
		}

		/**
		 * Number of children Use this to help figure out which BST
		 * deletion case to perform
		 * 
		 * @return The number of children of this node
		 */
		public int numChildren() {
			int numChildren = 0;
			if (left != null) {
				numChildren++;
			}
			if (right != null) {
				numChildren++;
			}

			return numChildren;
		}

		/**
		 * @return The leftmost node in the binary tree rooted at this
		 *         node.
		 */
		public BSTNode getLeftmostNode() {
			if (left != null) {
				return left.getLeftmostNode();
			}
			return this;
		}

		/**
		 * @return The rightmost node in the binary tree rooted at this
		 *         node.
		 */
		public BSTNode getRightmostNode() {
			if (right != null) {
				return right.getRightmostNode();
			}
			return this;
		}

		/**
		 * 
		 * This method applies to binary search trees only (not general
		 * binary trees).
		 * 
		 * @return The successor of this node.
		 * 
		 *         The successor is a node which can replace this node
		 *         in a case-3 BST deletion. It is either the smallest
		 *         node in the right subtree, or the largest node in the
		 *         left subtree.
		 */
		public BSTNode getSuccessor() {
			if (right != null) {
				return right.getLeftmostNode();
			}
			return left.getRightmostNode();
		}

		/**
		 * Replaces one of the parents child nodes with another node
		 * 
		 * @param original
		 *                - The node we are replacing
		 * @param replacement
		 *                - The node that is serving as the replacement
		 */
		public void replace(BSTNode original, BSTNode replacement) {
			if (left == null) {
				right = replacement;
			} else if (right == null) {
				left = replacement;
			} else if (left.equals(original)) {
				left = replacement;
			} else if (right.equals(original)) {
				right = replacement;
			}
		}
	}

	/**
	 * Method that creates a dot file with the given filename
	 * @param filename - the filename of the dot file we want to write
	 */
	public void writeDot(String filename) {
		try {
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(
					filename));
			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");
			if (root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recursive method for writing a tree to a dot file
	 * @param currentNode - The current node on the tree that we are writing to the file
	 * @param output - The FileWriter we are writing to
	 * @throws Exception
	 */
	private void writeDotRecursive(BSTNode currentNode, PrintWriter output)
			throws Exception {
		output.println(currentNode.data + "[label=\"<L> |<D> " + currentNode.data
				+ "|<R> \"]");
		if (currentNode.left != null) {
			// write the left subtree
			writeDotRecursive(currentNode.left, output);
			// then make a link between n and the left subtree
			output.println(currentNode.data + ":L -> " + currentNode.left.data + ":D");
		}
		if (currentNode.right != null) {
			// write the left subtree
			writeDotRecursive(currentNode.right, output);
			// then make a link between n and the right subtree
			output.println(currentNode.data + ":R -> " + currentNode.right.data + ":D");
		}
	}
}
