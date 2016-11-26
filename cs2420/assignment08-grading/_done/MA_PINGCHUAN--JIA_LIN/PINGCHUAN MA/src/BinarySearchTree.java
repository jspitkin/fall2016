package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/*
 * Build BinarySearchTree.
 * @author Lin Jia uid:u1091732
 * @author Pingchuan Ma uid:u0805309
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
	private Node root;
	private int size;

	private class Node {
		// Data part of the node
		private T data;
		// Subtrees
		private Node left;
		private Node right;

		public Node(T item) {
			this.data = item;
			left = right = null;
		}

		public boolean add(T item) {
			//already have the item
			if (item.compareTo(this.data) == 0)
				return false;
			else if (item.compareTo(this.data) < 0) {//add to left
				if (left == null) {
					left = new Node(item);
					return true;
				} else
					return left.add(item);
			} else if (item.compareTo(this.data) > 0) {//add to right
				if (right == null) {
					right = new Node(item);
					return true;
				} else
					return right.add(item);
			}
			return false;
		}

		public Node getLeft() {
			return left;
		}

		public Node getRight() {
			return right;
		}

		public Node getLeftMost() {
			if (getLeft() == null)
				return this;
			return getLeft().getLeftMost();
		}

		public Node getRightMost() {
			if (getRight() == null)
				return this;
			return getRight().getRightMost();
		}

		public boolean remove(T item) {
			if (currentNode(item) == null)//does not contain the item
				return false;
			//currentNode is leaf
			if (currentNode(item).left == null && currentNode(item).right == null) {
				if (parent(item) == null) {
					root = null;
				} else {
					if (item.compareTo(parent(item).data) < 0)
						parent(item).left = null;
					else if (item.compareTo(parent(item).data) > 0)
						parent(item).right = null;
				}
				size--;
				return true;
				//currentNode has a left child
			} else if (currentNode(item).left != null && currentNode(item).right == null) {
				if (parent(item) == null) {//currentNode has a left child,no parent
					root = currentNode(item).left;
				} else {//currentNode has a left child and a parent
					if (item.compareTo(parent(item).data) < 0)
						parent(item).left = currentNode(item).left;
					else
						parent(item).right = currentNode(item).left;
				}
				size--;
				return true;
				//currentNode has a right child
			} else if (currentNode(item).left == null && currentNode(item).right != null) {
				if (parent(item) == null) {//currentNode has a right child,no parent
					root = currentNode(item).right;
				} else {//currentNode has a right child and a parent
					if (item.compareTo(parent(item).data) < 0)
						parent(item).left = currentNode(item).right;
					else
						parent(item).right = currentNode(item).right;
				}
				size--;
				return true;
				//currentNode has two children
			} else if (currentNode(item).left != null && currentNode(item).right != null) {
				//copy value of currenNode's right's leftmost node
				T a = currentNode(item).right.getLeftMost().data;
				//remove currenNode's right's leftmost node
				remove(currentNode(item).right.getLeftMost().data);
				currentNode(item).data = a;
				return true;
			}
			return false;
		}

		//the node with value item
		public Node currentNode(T item) {
			int comp = item.compareTo(this.data);
			if (comp == 0)
				return this;
			else if (comp < 0 && left != null)
				return left.currentNode(item);
			else if (comp > 0 && right != null)
				return right.currentNode(item);
			// no matching node was found
			return null;
		}
		//find the parent of the node with value item
		public Node parent(T item) {
			if (left != null && left.data == item || right != null && right.data == item)
				return this;
			else if (item.compareTo(this.data) < 0 && left != null)
				return this.left.parent(item);
			else if (item.compareTo(this.data) > 0 && right != null)
				return this.right.parent(item);
			return null;
		}

	}

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	@Override
	public boolean add(T item) {
		if (item == null)
			throw new NullPointerException();
		if (root == null) {
			root = new Node(item);
			size++;
			return true;
		} else {
			if (root.add(item))
				size++;
			return root.add(item);
		}
	}

	@Override
	public boolean addAll(Collection<? extends T> items) {
		for (T el : items)
			add(el);
		return false;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public boolean contains(T item) {
		if (root != null && root.currentNode(item) != null)
			return true;
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends T> items) {
		for (T el : items) {
			if (!contains(el))
				return false;
		}
		return true;
	}

	@Override
	public T first() throws NoSuchElementException {
		if (!this.isEmpty())
			return root.getLeftMost().data;
		else
			throw new NoSuchElementException();
	}

	@Override
	public boolean isEmpty() {
		if (size == 0)
			return true;
		return false;
	}

	@Override
	public T last() throws NoSuchElementException {
		if (!this.isEmpty())
			return root.getRightMost().data;
		else
			throw new NoSuchElementException();

	}

	@Override
	public boolean remove(T item) {

		if (root != null) {
			return root.remove(item);
		}
		return false;
	}

	@Override
	public boolean removeAll(Collection<? extends T> items) {
		for (T el : items) {
			if (!remove(el))
				return false;
		}
		return true;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<>();
		this.toArray(root, list);
		return list;
	}

	public void toArray(Node n, ArrayList<T> list) {
		if (n == null)
			return;
		toArray(n.left, list);
		list.add(n.data);
		toArray(n.right, list);
	}

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
	private void writeDotRecursive(Node n, PrintWriter output) throws Exception {
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
