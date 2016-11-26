
package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * 
 * @author Hyrum Johnson
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

	int size = 0;
	Node<T> root;

	/**
	 * This recursively adds an item to the right or left
	 * 
	 * @param item
	 *            - item to be added
	 * @param node
	 *            - Starting Node
	 * @return
	 */
	private Node<T> recursiveAdd(T item, Node<T> node) {
		if (node.data.compareTo(item) > 0) {
			if (node.left == null) {
				node.left = new Node<T>(item);
				return node;
			} else {
				return recursiveAdd(item, node.left);
			}
		} else {
			if (node.right != null) {
				return recursiveAdd(item, node.right);

			} else {
				node.right = new Node<T>(item);
				return node;
			}
		}
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	public boolean add(T item) {

		if (item != null) {

			if (root == null) {
				root = new Node<T>(item);
				size++;
				return true;
			} else {
				recursiveAdd(item, root);
				size++;
				return true;
			}
		}
		if (contains(item) == true) {
			return false;

		}
		throw new NullPointerException();

	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	public boolean addAll(Collection<? extends T> items) {
		int x = 0;
		for (T temp : items) {
			if (add(temp)) {
				x = 1;
			}
		}

		if (x == 0) {
			return false;
		} else if (x == 1) {
			return true;
		}
		return true;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	public void clear() {

		// Just set root to null.
		this.root = null;

		// Reset the size.
		size = 0;
	}

	/**
	 * This method recursively checks whether the item is in the BST
	 * 
	 * @param item
	 *            - item to be checked for
	 * @param root
	 *            - starting node to begin checking for item
	 * @return
	 */
	private boolean recursiveCheckMethod(T item, Node<T> root) {
		if (root != null) {
			if (root.data.equals(item) == true) {
				return true;
			}
			if (root.data.compareTo(item) > 0 == true) {

				return recursiveCheckMethod(item, root.left);
			} else {
				return recursiveCheckMethod(item, root.right);
			}
		}
		return false;

	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	public boolean contains(T item) {
		if (item != null) {
			return recursiveCheckMethod(item, root);

		} else {
			throw new NullPointerException();
		}

	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	public boolean containsAll(Collection<? extends T> items) {
		for (T tempVar : items) {
			if (this.contains(tempVar) == true) {
				return true;
			} else if (this.contains(tempVar) == false) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	public T first() throws NoSuchElementException {
		if (this.root != null) {
			T first = this.root.getLeftMost().data;
			return first;
		}
		throw new NoSuchElementException();

	}

	/**
	 * Returns true if this set contains no items.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	public T last() throws NoSuchElementException {
		if (this.root != null) {
			T last = this.root.getRightMost().data;
			return last;
		}
		throw new NoSuchElementException();
	}

	/**
	 * This method searches for the item you wish to remove's parent node.
	 * 
	 * @param item
	 *            - item that's going to be deleted.
	 * @param parent
	 *            - starting node for the binary search.
	 * @return
	 */
	public Node<T> lookForItem(T item, Node<T> parent) {
		if (item != null) {

			if (parent.data.equals(item) == true) {
				return parent;
			}
			if (parent.left != null) {
				if (parent.left.data.equals(item) == true) {
					return parent;
				}
			} else if (parent.right != null) {
				if (parent.right.data.equals(item) == true) {
					return parent;
				}
			}

			if (parent.data.compareTo(item) > 0 == true) {
				return lookForItem(item, parent.left);
			} else {
				return lookForItem(item, parent.right);
			}
		}
		throw new NullPointerException();

	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item
	 *            - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually removed); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	public boolean remove(T item) {

		if (contains(item) == true) {

			if (item == root.data && root.left == null && root.right == null) {
				root = null;
				size = size - 1;
				return true;
			} else if (item == root.data && root.left != null && root.right == null) {
				root = root.left;
				size = size - 1;
				return true;
			} else if (item == root.data && root.left == null && root.right != null) {
				root = root.right;
				size = size - 1;
				return true;
			} else if (item == root.data && root.left != null && root.right != null) {
				root.data = root.right.getLeftMost().data;
				size = size - 1;
				T newParam = root.data;
				return remove(newParam, root);
			}
			// Remove method call.
			return remove(item, lookForItem(item, root));
		}
		return false;
	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item
	 *            - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually removed); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	public boolean remove(T item, Node<T> selectedNode) {

		// SelectedNode equals found item with selected node.
		selectedNode = lookForItem(item, selectedNode);

		do {

			// This is if there is no children
			if (selectedNode.right != null && selectedNode.left != null) {
				if (item.compareTo(selectedNode.right.data) == 0 && selectedNode.right == null
						&& selectedNode.right.right == null) {
					selectedNode.right = null;
					size = size - 1;
					return true;
				}
				if (item.compareTo(selectedNode.left.data) == 0 && selectedNode.left.left == null
						&& selectedNode.left == null) {
					selectedNode.left = null;
					size = size - 1;
					return true;
				}
			} else if ((selectedNode.left != null)) {
				if (item.compareTo(selectedNode.left.data) == 0 && selectedNode.left != null
						&& selectedNode.left.left == null) {
					selectedNode.left = selectedNode.left.right;
					size--;
					return true;
				} else if (item.compareTo(selectedNode.left.data) == 0 && selectedNode.left.right == null
						&& selectedNode.left.left != null) {
					selectedNode.left = selectedNode.left.left;
					size--;
					return true;
				}
			}

			// When there is one child on the right of parent
			if ((selectedNode.right != null)) {
				if (item.compareTo(selectedNode.right.data) == 0 && selectedNode.right.right != null
						&& selectedNode.right.left == null) {
					selectedNode.right = selectedNode.right.right;
					size--;
					return true;
				} else if (item.compareTo(selectedNode.right.data) == 0 && selectedNode.right.right == null
						&& selectedNode.right.left != null) {
					selectedNode.right = selectedNode.right.left;
					size--;
					return true;
				}
			}

			// Using recursion if two children on the right of parent.
			if (selectedNode.right != null) {
				if (item.compareTo(selectedNode.right.data) == 0 && selectedNode.right.right != null
						&& selectedNode.right.left != null) {
					size = size - 1;
					selectedNode.right.data = selectedNode.right.right.getLeftMost().data;
					remove(selectedNode.right.data, selectedNode.right.right);
				}
			}

			// This is if two children is on the left of the parent also uses
			// recusion.
			if (item.compareTo(selectedNode.left.data) == 0 && item.compareTo(selectedNode.left.data) == 0
					&& selectedNode.left.left != null && selectedNode.left.right != null) {
				size = size - 1;
				;
				selectedNode.left.data = selectedNode.left.left.getRightMost().data;
				remove(selectedNode.left.data, selectedNode.left.left);
			}
		} while (true);
	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items
	 *            - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually removed);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	public boolean removeAll(Collection<? extends T> items) {
		boolean removeAllItems = false;
		for (T temp : items) {
			if (remove(temp)) {
				removeAllItems = true;
			}
		}
		return removeAllItems;
	}

	/**
	 * Returns the number of items in this set.
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> toArrayList = new ArrayList<T>();

		return toArrayList;
	}

	// Driver for writing this tree to a dot file
	/**
	 * This writes the nodes to a file and creates an image to display the path
	 * of all the nodes.
	 * 
	 * @param filename
	 *            - filename to be saved to
	 */
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
	/**
	 * 
	 * @param n
	 *            - starting Node
	 * @param output
	 *            - PrintWriter
	 * @throws Exception
	 */
	private void writeDotRecursive(Node<T> n, PrintWriter output) throws Exception {
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

}