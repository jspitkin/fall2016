package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * JavaDoc comments were mostly taken directly from the SortedSet interface.
 *
 * @author Brayden Wright (u0895942), Abdulaziz Aljanahi (u0901606)
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
    private BinarySearchNode root;
    private ArrayList<T> list;
    private int size;

    /**
     * Start by creating an empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = new BinarySearchNode(null);
        this.list = new ArrayList<T>();
        this.size = 0;
    }

    /**
     * Ensures that this set contains the specified item. Calls the recursive
     * add method.
     *
     * @param item - the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean add(T item) {

        if (item == null)
            throw new NullPointerException();

        // Sets root's data if null
        if (root.getData() == null) {
            root.setData(item);
            size++;
            return true;
        }

        // Start recursion fun
        return add(root, item);
    }

    /**
     * Recursively traverse and (potentially) add to this tree
     *
     * @param node - the current node
     * @param item - the item to add
     * @return true if added to the tree, false otherwise
     */
    private boolean add(BinarySearchNode node, T item) {

        // 'item' is less than current node, go left.
        if (node.getData().compareTo(item) > 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BinarySearchNode(item));
                size++;
                return true;
            }
            return add(node.getLeft(), item);
        }

        // 'item' is greater than current node, go right.
        else if (node.getData().compareTo(item) < 0) {
            if (node.getRight() == null) {
                node.setRight(new BinarySearchNode(item));
                size++;
                return true;
            }
            return add(node.getRight(), item);
        }

        // Item already in set, not adding.
        return false;
    }

    /**
     * Ensures that this set contains all items in the specified collection.
     *
     * @param items - the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean addAll(Collection<? extends T> items) {

        // Passed a null collection, throwing exception.
        if (items == null)
            throw new NullPointerException();

        // Set will not be changed, return false
        if (items.size() == 0)
            return false;

        boolean changed = false;
        for (T item : items) {
            changed |= add(item); // ORs 'changed' with the result of adding the item.
        }
        return changed;
    }

    /**
     * Removes all items from this set. The set will be empty after this method
     * call.
     */
    @Override
    public void clear() {
        this.root = new BinarySearchNode(null);
        this.list = new ArrayList<T>();
        this.size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified
     * item.
     *
     * @param item - the item sought in this set
     * @return true if there is an item in this set equal to the input item
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean contains(T item) {
        // Begin Haiku

        // The tree is passed null.
        // The tree throws an exception.
        if (item == null)
            throw new NullPointerException();

        // The tree is empty.
        if (size == 0)
            return false;

        // End Haiku. Recurse.
        return contains(root, item);
    }

    /**
     * The helpful recursive contains method.
     *
     * @param node - the current node
     * @param item - the item being searched for
     * @return true if 'item' is found
     */
    private boolean contains(BinarySearchNode node, T item) {

        // Simply return true if the current node corresponds to 'item'
        if (node.getData().compareTo(item) == 0)
            return true;

        // Ask the left child if they know anything about 'item'
        if (node.getData().compareTo(item) > 0 && node.getLeft() != null)
            return contains(node.getLeft(), item);

        // Otherwise, ask the other child
        else if (node.getData().compareTo(item) < 0 && node.getRight() != null)
            return contains(node.getRight(), item);

        // The children know nothing
        return false;
    }

    /**
     * Determines if for each item in the specified collection, there is an item
     * in this set that is equal to it.
     *
     * @param items - the collection of items sought in this set
     * @return true if for each item in the specified collection, there is an
     * item in this set that is equal to it
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean containsAll(Collection<? extends T> items) {
        if (items == null) throw new NullPointerException();

        if (items.size() == 0)
            return true;

        boolean allContained = true;

        for (T item : items) {
            allContained &= contains(item); // ANDs 'allContained' against result of contains check
        }
        return allContained;
    }

    /**
     * Returns the first (i.e., smallest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T first() throws NoSuchElementException {
        return (T) root.getLeftmostNode().getData();
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty() {
        return (root == null || size == 0);
    }

    /**
     * Returns the last (i.e., largest) item in this set.
     *
     * @throws NoSuchElementException if the set is empty
     */
    @Override
    public T last() throws NoSuchElementException {
        return (T) root.getRightmostNode().getData();
    }

    /**
     * Ensures that this set does not contain the specified item.
     *
     * @param item - the item whose absence is ensured in this set
     * @return true if this set changed as a result of this method call (that
     * is, if the input item was actually removed); otherwise, returns
     * false
     * @throws NullPointerException if the item is null
     */
    @Override
    public boolean remove(T item) {
        if (item == null)
            throw new NullPointerException();

        if (size == 0)
            return false;

        // Remove if root matches
        if (root.getData().compareTo(item) == 0) {
            BinarySearchNode successor = root.getSuccessor();
            root.setData(successor.getData());
            return remove(root, root.getRight(), (T) successor.getData());
        }

        // Begin recursive fun
        if (root.getData().compareTo(item) > 0)
            return remove(root, root.getLeft(), item);
        return remove(root, root.getRight(), item);
    }

    /**
     * @param parent - the parent node, makes some operations a little easier
     * @param node   - the node currently being checked
     * @param item   - the item to remove
     * @return whether or not 'item' was removed
     */
    private boolean remove(BinarySearchNode parent, BinarySearchNode node, T item) {

        if (node.getData().compareTo(item) == 0) {

            // Case 1: Removing a leaf
            if (node.numChildren() == 0) {

                // Node is the left child, remove left child
                if (parent.getLeft() == node) {
                    parent.setLeft(null);
                    size--;
                    return true;
                }

                // Otherwise, remove right child
                parent.setRight(null);
                size--;
                return true;
            }

            // Case 2: Removing a node with one child
            if (node.numChildren() == 1) {
                if (node.getRight() == null) {
                    if (parent.getLeft() == node) {
                        parent.setLeft(node.getRight());
                        size--;
                        return true;
                    }

                    parent.setRight(node.getLeft());
                    size--;
                    return true;
                }

                if (parent.getRight() == node) {
                    if (node.getLeft() == null)
                        parent.setRight(node.getRight());
                    else parent.setRight(node.getLeft());
                    size--;
                    return true;
                }

                if (node.getRight() == null)
                    parent.setLeft(node.getLeft());
                else parent.setLeft(node.getRight());
                size--;
                return true;
            }

            // Case 3: Removing a node with two children
            if (node.numChildren() == 2) {
                BinarySearchNode successor = node.getSuccessor();
                node.setData(successor.getData());

                // Can just call remove on the successor at this point.
                return remove(node, node.getRight(), (T) successor.getData());
            }

            return false;
        } else if (node.getData().compareTo(item) > 0)
            return remove(node, node.getLeft(), item);
        else
            return remove(node, node.getRight(), item);
    }

    /**
     * Ensures that this set does not contain any of the items in the specified
     * collection.
     *
     * @param items - the collection of items whose absence is ensured in this set
     * @return true if this set changed as a result of this method call
     * @throws NullPointerException if any of the items is null
     */
    @Override
    public boolean removeAll(Collection<? extends T> items) {
        boolean removed = false;

        if (items == null)
            throw new NullPointerException();

        // This set was not changed, return false
        if (items.size() == 0)
            return false;

        for (T item : items) {
            removed |= remove(item);
        }
        return removed;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns an ArrayList containing all of the items in this set, in sorted
     * order.
     */
    @Override
    public ArrayList<T> toArrayList() {

        // Reinitialize the list so that we don't just add to an existing one
        list = new ArrayList<T>();

        // Perform an in-order traversal over this tree, starting at root
        inOrderTraversal(root);
        return list;
    }

    /**
     * Perform an in-order traversal, adding each item to 'list'
     *
     * @param node - te node to begin traversing
     */
    private void inOrderTraversal(BinarySearchNode node) {
        if (node == null)
            return;

        inOrderTraversal(node.getLeft());
        list.add((T) node.getData());
        inOrderTraversal(node.getRight());
    }

    /**
     * Write the tree to a dot file utilizing recursion.
     * Pulled directly from the slides presented in class.
     *
     * @param filename - the name of the dot file
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

    /**
     * The recursive part of 'writeDot'
     */
    private void writeDotRecursive(BinarySearchNode node, PrintWriter output) throws Exception {
        output.println(node.getData() + "[label=\"<L> |<D> " + node.getData() + "|<R> \"]");
        if (node.getLeft() != null) {
            // write the left subtree
            writeDotRecursive(node.getLeft(), output);
            // then make a link between node and the left subtree
            output.println(node.getData() + ":L -> " + node.getLeft().getData() + ":D");
        }
        if (node.getRight() != null) {
            // write the left subtree
            writeDotRecursive(node.getRight(), output);
            // then make a link between node and the right subtree
            output.println(node.getData() + ":R -> " + node.getRight().getData() + ":D");
        }
    }
}
