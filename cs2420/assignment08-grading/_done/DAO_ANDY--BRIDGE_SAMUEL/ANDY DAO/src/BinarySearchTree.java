package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * BinarySearchTree that implements a SortedSet interface. This class represents
 * a Binary Search Tree.
 * 
 * @author Andy Dao, uID: u0692334
 * @author Sam Bridge, uID: u0984334
 */
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {

    private Node root; // Root node
    private int size; // Size of BST

    /**
     * The no-parameter constructor for BST
     */
    public BinarySearchTree () {
	root = null;
	size = 0;
    }

    @Override
    public boolean add(T item) {
	// If the item is null, throw NullPointerException
	if (item == null) {
	    throw new NullPointerException();
	}
	// If the root is null (no elements have been added yet)
	if (root == null) {
	    root = new Node(item); // Add the new item to the root
	    size++;
	    return true;
	}
	else { // Perform recursion method for add
	    return addRecursive(item, root);
	}
    }

    /**
     * Helper method for add done by recursive calls
     * 
     * @param item
     *            - item to be inserted
     * @param node
     *            - node to be looked examined, starts from root
     * @return true if the set has been changed by this method call (the item
     *         was actually inserted). Returns false otherwise.
     */
    private boolean addRecursive(T item, Node node) {
	// Comparison result. Less than 1 if item is less than the node we are currently at, 0 if same, greater than 1 if it is bigger
	int comparisonResult = item.compareTo(node.data);

	// If the comparison == 0, we know that it's a duplicate, and we don't want duplicates in a set
	if (comparisonResult == 0) {
	    return false;
	}
	else if (comparisonResult < 0) {
	    // If the current node we are at's left child is null, we can insert there
	    if (node.leftChild == null) {
		node.leftChild = new Node(item); // Insertion takes place
		size++;
		return true;
	    }
	    else {
		return addRecursive(item, node.leftChild); // Go down to the left child node
	    }
	}
	else { // the comparison result is > 0
		   // If the current node we are at's right child is null, we can insert there
	    if (node.rightChild == null) {
		node.rightChild = new Node(item);
		size++;
		return true;
	    }
	    else {
		return addRecursive(item, node.rightChild);
	    }
	}
    }

    @Override
    public boolean addAll(Collection<? extends T> items) {
	boolean setHasBeenChanged = false;
	// Go through each item in the collection and add them to the BST
	for (T item : items) {
	    if (item == null) {
		throw new NullPointerException();
	    }
	    // If we were able to at least add 1 item, we can return true as by change of set
	    if (add(item)) {
		setHasBeenChanged = true;
	    }
	}
	return setHasBeenChanged;
    }

    @Override
    public void clear() {
	root = null; // Set the root of the BST to null, and garbage collection will handle the rest
	size = 0;
    }

    @Override
    public boolean contains(T item) {
	if (item == null) {
	    throw new NullPointerException();
	}
	return containsRecursive(item, root);
    }

    /**
     * Recursive call for the contains method. Returns true if the item we are
     * searching for is in the BST.
     * 
     * @param item
     *            - the item to search for
     * @param root
     *            - the current node we are at to compare with. Starts at the
     *            root node.
     * @return - true if the item is in the BST. False otherwise.
     */
    private boolean containsRecursive(T item, Node root) {
	// Comparison result. Less than 1 if item is less than the node we are currently at, 0 if same, greater than 1 if it is bigger
	int comparisonResult = item.compareTo(root.data);

	// If the comparison == 0, we know that it's a match so we return true for that it is in the BST.
	if (comparisonResult == 0) {
	    return true;
	}
	else if (comparisonResult < 0) {
	    // If the current node we are at's left child is null, we know we reached as far left for this subtree as we can go
	    if (root.leftChild == null) {
		return false;
	    }
	    else {
		return containsRecursive(item, root.leftChild); // Go down to the left child node
	    }
	}
	else { // the comparison result is > 0
		   // If the current node we are at's right child is null, we know we reached as far right for this subtree as we can go
	    if (root.rightChild == null) {
		return false;
	    }
	    else {
		return containsRecursive(item, root.rightChild);
	    }
	}
    }

    @Override
    public boolean containsAll(Collection<? extends T> items) {
	// Go through each item in the collection 
	for (T item : items) {
	    // Throw NullPointerException if any of the items are null
	    if (item == null) {
		throw new NullPointerException();
	    }
	    // If at any time the contains method returns false, just return false immediately
	    if (!contains(item)) {
		return false;
	    }
	}
	return true;
    }

    @Override
    public T first() throws NoSuchElementException {
	if (root == null) {
	    throw new NoSuchElementException();
	}
	return root.getLeftmostNode().data;
    }

    @Override
    public boolean isEmpty() {
	return root == null;
    }

    @Override
    public T last() throws NoSuchElementException {
	if (root == null) {
	    throw new NoSuchElementException();
	}
	return root.getRightmostNode().data;
    }

    @Override
    public boolean remove(T item) {
	// If the item we are removing is null, throw NullPointerException
	if (item == null) {
	    throw new NullPointerException();
	}
	// Check right away if the BST is empty (root == null)
	if (root == null) {
	    return false;
	}
	else {
	    // Check if the item is even in the BST, if not, return false
	    if (!contains(item)) {
		return false;
	    }
	    // Perform remove recursion method
	    return removeRecursive(item, root);
	}
    }

    /**
     * Recursive call to the remove method for BST.
     * 
     * @param item
     *            - the item whose absence is ensured in this set
     * @param current
     *            - the current node
     * @return true if this set changed as a result of this method call (that
     *         is, if the input item was actually removed); otherwise, returns
     *         false
     */
    private boolean removeRecursive(T item, Node current) {
	// If what we are removing is at the root
	if (current.data == item) {
	    // At root, case 3 (has 2 children)
	    if (current.leftChild != null && current.rightChild != null) {
		Node successor = current.getSuccessor();
		successor.leftChild = current.leftChild;
		successor.rightChild = current.rightChild;
		root = successor;
	    }
	    // At root, case 2 (has only 1 children)
	    // if the root has only the left child
	    else if (current.leftChild != null) {
		root = current.leftChild;
	    }
	    // if the root has only the right child
	    else if (current.rightChild != null) {
		root = current.rightChild;
	    }
	    // If the root has no child
	    else {
		root = null;
	    }
	    size--;
	    return true;
	}
	// If the node has a left child
	if (current.leftChild != null) {
	    // .. and it equals the item we are searching for
	    if (current.leftChild.data == item) {
		// Case 3 (has 2 children)
		// The current node's left child has a left child and a right child
		if (current.leftChild.leftChild != null && current.leftChild.rightChild != null) {
		    // Find the successor of the current left child
		    Node successor = current.leftChild.getSuccessor();
		    // Assign the successor's left and right child with the current nodes left left child, and it's left right child
		    successor.leftChild = current.leftChild.leftChild;
		    successor.rightChild = current.leftChild.rightChild;
		    // Replace the current node's left child with it's the successor 
		    current.leftChild = successor;
		}
		// Case 2 (only has 1 child)
		// else if the current nodes left left child is not null 
		else if (current.leftChild.leftChild != null) {
		    current.leftChild = current.leftChild.leftChild; // Take currents left childs, left child, and replace currents left child 
		}
		// Case 2 (only has 1 child)
		// else if the current nodes left right child is not null
		else if (current.leftChild.rightChild != null) {
		    current.leftChild = current.leftChild.rightChild;
		}
		// Case 1 (has no children)
		// else the current nodes left child has no children, we can just set it to null and be done
		else {
		    current.leftChild = null;
		}
		size--;
		return true;
	    }
	}
	// if the node has a right child
	else if (current.rightChild != null) {
	    // current node right child is the item
	    if (current.rightChild.data == item) {
		// Case 3 (has 2 children)
		// The current node's right child has a left child and a right child
		if (current.rightChild.leftChild != null && current.rightChild.rightChild != null) {
		    // Find the successor of the current right child
		    Node successor = current.rightChild.getSuccessor();

		    // Assign the successor correct let and right child before replacing it
		    successor.leftChild = current.rightChild.leftChild;
		    successor.rightChild = current.rightChild.rightChild;
		    // Replace the current node's left child with it's the successor 
		    current.rightChild = successor;
		}
		// Case 2 (only has 1 child)
		// else if the current nodes right left child is not null
		else if (current.rightChild.leftChild != null) {
		    current.rightChild = current.rightChild.leftChild;
		}
		// Case 2 (only has 1 child)
		// else if the current nodes right right child is not null instead
		else if (current.rightChild.rightChild != null) {
		    current.rightChild = current.rightChild.rightChild;
		}
		// Case 1 (has no children)
		// else we can just set the current nodes right child to null
		else {
		    current.rightChild = null;
		}
		size--;
		return true;
	    }
	}

	// Keep searching for the node/item to remove
	// if the item to remove is less than the current node we are at, traverse left
	if (item.compareTo(current.data) < 0) {
	    return removeRecursive(item, current.leftChild);
	}
	else if (item.compareTo(current.data) > 0) {
	    return removeRecursive(item, current.rightChild);
	}
	else {
	    return false;
	}

    }

    @Override
    public boolean removeAll(Collection<? extends T> items) {
	// If we were able to remove 1 item, change removeStatus to true
	boolean removeStatus = false;

	// If any of the items in the collection is null, throw NullPointerException 
	for (T item : items) {
	    if (item == null) {
		throw new NullPointerException();
	    }
	    else {
		// Able to remove an item, turn removeStatus to true
		if (remove(item)) {
		    removeStatus = true;
		}
	    }
	}
	return removeStatus;
    }

    @Override
    public int size() {
	return size;
    }

    @Override
    public ArrayList<T> toArrayList() {
	ArrayList<T> toArrayList = new ArrayList<T>();
	toArrayListRecursive(root, toArrayList);
	return toArrayList;
    }

    /**
     * Recursive helper method to convert the BST into an ArrayList.
     * 
     * @param root
     *            = the root node
     * @param toArrayList
     *            - the ArrayList to insert into
     */
    private void toArrayListRecursive(Node root, ArrayList<T> toArrayList) {
	if (root == null) {
	    return;
	}
	else {
	    // Traverse down left 
	    toArrayListRecursive(root.leftChild, toArrayList);
	    // Add the element to array
	    toArrayList.add(root.data);
	    // Traverse down right
	    toArrayListRecursive(root.rightChild, toArrayList);
	}
    }

    /**
     * Generate a dot file from our BST. This outputs a .dot file which can be
     * opened with Graphviz to display your tree.
     * 
     * @param filename
     *            - filename we will write out to
     */
    public void writeDot(String filename) {
	try {
	    // PrintWriter(FileWriter) will write output to a file
	    PrintWriter output = new PrintWriter(new FileWriter(filename));

	    // Set up the dot graph and properties
	    output.println("digraph BST {");
	    output.println("node [shape=record]");

	    if (root != null) {
		writeDotRecursive(root, output);
	    }
	    // Close the graph
	    output.println("}");
	    output.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Recursive call to write the DOT file starting from the root Node passed
     * in.
     */
    private void writeDotRecursive(Node root, PrintWriter output) throws Exception {
	output.println(root.data + "[label=\"<L> |<D> " + root.data + "|<R> \"]");
	if (root.leftChild != null) {
	    // write the left subtree
	    writeDotRecursive(root.leftChild, output);
	    // then make a link between n and the left subtree
	    output.println(root.data + ":L -> " + root.leftChild.data + ":D");
	}
	if (root.rightChild != null) {
	    // write the left subtree
	    writeDotRecursive(root.rightChild, output);
	    // then make a link between n and the right subtree
	    output.println(root.data + ":R -> " + root.rightChild.data + ":D");
	}
    }

    /**
     * Inner class for a binary node
     */
    private class Node {
	T data; // The node data
	Node leftChild;
	Node rightChild;

	/**
	 * Constructor that creates a binary node object.
	 * 
	 * @param data
	 *            - The element to be inserted into the node
	 */
	public Node (T data) {
	    this.data = data;
	    leftChild = null;
	    rightChild = null;
	}

	/**
	 * @return The leftmost node in the binary tree rooted at this node.
	 */
	public Node getLeftmostNode() {
	    if (leftChild == null) {
		return this;
	    }
	    // Check the roots' left child. If null, return the root (which is leftChild)
	    if (leftChild.leftChild == null) {
		return leftChild;
	    }
	    return leftChild.getLeftmostNode();
	}

	/**
	 * @return The rightmost node in the binary tree rooted at this node.
	 */
	public Node getRightmostNode() {
	    if (rightChild == null) {
		return this;
	    }
	    // Check the roots' right child. If null, return the root (which is rightChild)
	    if (rightChild.rightChild == null) {
		return rightChild;
	    }
	    return rightChild.getRightmostNode();
	}

	/**
	 * 
	 * This method applies to binary search trees only (not general binary
	 * trees).
	 * 
	 * @return The successor of this node.
	 * 
	 *         The successor is a node which can replace this node in a
	 *         case-3 BST deletion. It is either the smallest node in the
	 *         right subtree, or the largest node in the left subtree.
	 */
	public Node getSuccessor() {
	    if (rightChild != null) {
		return rightChild.getLeftmostNode(); // Smallest node in the right subtree
	    }
	    return leftChild.getRightmostNode(); // Largest node on the left sub tree
	}
    }

}
