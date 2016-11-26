package Assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Yuhong Lin && Yixiong Qin
 *
 * 
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	private BinaryNode root;
	private int size;
	private ArrayList<T> toArrayList;

	public BinarySearchTree() {
		root = null;
		size = 0;
		toArrayList = new ArrayList<T>();
	}

	@Override
	public boolean add(T item) {
		if (item == null) {
			throw new NullPointerException();
		}
		if (this.contains(item)) {
			return false;
		}
		if (this.root == null) {
			this.root = new BinaryNode(item, null, null);
			size++;
			return true;
		} else {
			addSide(item, this.root);
			size++;
			return true;
		}
	}

	/**
	 * find out the location that item should be inserted
	 * 
	 * @param item
	 *            the element to be inserted
	 * @param root
	 *            the root of the BST
	 */
	public void addSide(T item, BinaryNode root) {
		if (item.compareTo(root.data) < 0) {
			if (root.left == null) {
				root.left = new BinaryNode(item, null, null);
				return;
			} else {
				addSide(item, root.left);
			}
		} else {
			if (root.right == null) {
				root.right = new BinaryNode(item, null, null);
				return;
			} else {
				addSide(item, root.right);
			}
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> items) {
		Iterator<? extends T> iterator = items.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			T temp = iterator.next();
			if (this.add(temp)) {
				count++;
			}
		}
		if (count > 0) {
			size += count;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
		toArrayList = new ArrayList<T>();
	}

	@Override
	public boolean contains(T item) {
		if (item == null) {
			throw new NullPointerException();
		} else {
			return containDriver(item, this.root);
		}
	}

	@Override
	public boolean containsAll(Collection<? extends T> items) {
		Iterator<? extends T> iterator = items.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			T temp = iterator.next();
			if (this.contains(temp)) {
				count++;
			}
		}
		if (count == items.size()) {
			size += count;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Use recursion to determine if the tree contain the item or not.
	 * 
	 * @param item
	 * @param root
	 * @return
	 */
	public boolean containDriver(T item, BinaryNode root) {
		if (root == null) {
			return false;
		}
		if (item.equals(root.data)) {
			return true;
		}
		if (item.compareTo(root.data) < 0) {
			return containDriver(item, root.left);
		} else {
			return containDriver(item, root.right);
		}
	}

	@Override
	public T first() throws NoSuchElementException {
		if (root == null) {
			throw new NoSuchElementException();
		} else {
			return firstDriver(this.root);
		}
	}

	/**
	 * Using recursion to get the smallest item.
	 * 
	 * @param root
	 *            the earliest node
	 * @return return the smallest node if the left child is null, otherwise
	 *         using recursion
	 */
	public T firstDriver(BinaryNode root) {
		if (root.left == null) {
			return root.data;
		} else {
			return firstDriver(root.left);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public T last() throws NoSuchElementException {
		if (this.root == null) {
			throw new NoSuchElementException();
		} else {
			return lastDriver(this.root);
		}
	}

	/**
	 * Opposite the firstDriver, using recursion to get the largest item.
	 * 
	 * @param root
	 *            the earliest node
	 * @return return the largest node if the right child is null, otherwise
	 *         using recursion
	 */
	public T lastDriver(BinaryNode root) {
		if (root.right == null) {
			return root.data;
		} else {
			return lastDriver(root.right);
		}

	}

	@Override
	public boolean remove(T item) {
		if (item == null)
			throw new NullPointerException();

		if (this.contains(item) == false)
			return false;
		BinaryNode current = root;
		BinaryNode parent = root;
		boolean isLeftNode = true;
		while (current.data != item) {
			parent = current;
			if (current.data.compareTo(item) > 0) {
				isLeftNode = true;
				current = current.left;
			} else {
				isLeftNode = false;
				current = current.right;
			}
		}
		// Three situations
		if (current.left == null && current.right == null) { // the node has
																// no child
			if (current == root) {
				// if the node is the root, clear the tree
				clear();
			} else if (isLeftNode) {
				parent.left = null;
				size--;
			} else {
				parent.right = null;
				size--;
			}
		} else if (current.left == null) {
			// while current node only has right child
			if (current == root) {
				root = current.right;
			} else if (isLeftNode) {
				parent.left = current.right;
				size--;
			} else {
				parent.right = current.right;
				size--;
			}
		} else if (current.right == null) {
			// while current node only has left child
			if (current == root) {
				root = current.left;
				size--;
			} else if (isLeftNode) {
				parent.left = current.left;
				size--;
			} else {
				parent.right = current.left;
				size--;
			}
		} else { // while current node have two child
			BinaryNode successor = findSuccessor(current);
			if (current == root) {
				root = successor;
			} else if (isLeftNode) {
				parent.left = successor;
			} else {
				parent.right = successor;
			}
			successor.left = current.left;
		}

		return true;

	}

	/**
	 * find the smallest node in the right side of deleted node
	 * 
	 * @param delNode
	 * @return
	 */
	private BinaryNode findSuccessor(BinaryNode delNode) {
		BinaryNode parent = delNode;
		BinaryNode successor = delNode;
		BinaryNode current = delNode.right;
		while (current != null) {
			parent = successor;
			successor = current;
			current = current.left;
		}
		if (successor != delNode.right) {
			parent.left = successor.right;
			successor.right = delNode.right;
		}
		return successor;
	}

	@Override
	public boolean removeAll(Collection<? extends T> items) {
		if (items == null)
			throw new NullPointerException();
		Iterator<? extends T> iterator = items.iterator();
		int count = 0;
		while (iterator.hasNext()) {
			T temp = iterator.next();
			if (this.remove(temp)) {
				count++;
			}
		}
		if (count > 0) {
			size -= count;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public ArrayList<T> toArrayList() {
		toArrayList(this.root);
		return toArrayList;
	}

	public void toArrayList(BinaryNode node) {
		if (node == null) {
			return;
		} else {
			toArrayList(node.left);
			toArrayList.add(node.data);
			toArrayList(node.right);
		}
	}

	// Driver for writing this tree to a dot file
	public void writeDot(String filename) {
		try {
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));

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

	// Recursive method for writing the tree to a dot file
	private void writeDotRecursive(BinaryNode n, PrintWriter output) throws Exception {
		output.println(n.data + "[label=\"<L> |<D> " + n.data + "|<R> \"]");
		if (n.left != null) {
			// write the left subtree
			writeDotRecursive(n.left, output);

			// then make a link between n and the left subtree
			output.println(n.data + ":L -> " + n.left.data + ":D");
		}
		if (n.right != null) {
			// write the left subtree
			writeDotRecursive(n.right, output);

			// then make a link between n and the right subtree
			output.println(n.data + ":R -> " + n.right.data + ":D");
		}

	}

	/**
	 * the constructor of node
	 *
	 */
	private class BinaryNode {
		T data;
		BinaryNode left;
		BinaryNode right;

		public BinaryNode(T data, BinaryNode left, BinaryNode right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

	}
}
