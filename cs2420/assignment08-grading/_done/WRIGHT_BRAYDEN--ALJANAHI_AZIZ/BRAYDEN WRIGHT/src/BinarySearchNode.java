package assignment08;

/**
 * A node for use with BinarySearchTree.
 * Borrowed and modified from Lab 08.
 *
 * @author Brayden wright (u0895942), Abdulaziz Aljanahi (u0901606)
 */
public class BinarySearchNode {

    /*
        Since the assignment says this can't be generic,
        and we must use 'compareTo()', let's just store
        'data' as a Comparable.
     */
    private Comparable data;
    private BinarySearchNode left;
    private BinarySearchNode right;

    /**
     * Construct a new node with the specified children.
     */
    public BinarySearchNode(Comparable data, BinarySearchNode left, BinarySearchNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    /**
     * Construct a new node with no children
     */
    public BinarySearchNode(Comparable data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    /**
     * Get data from the node.
     *
     * @return the data contained within this node
     */
    public Comparable getData() {
        return data;
    }

    /**
     * Set the node's data.
     *
     * @param data - the node data to be set.
     */
    public void setData(Comparable data) {
        this.data = data;
    }

    /**
     * Get the node's left child.
     *
     * @return the left child node.
     */
    public BinarySearchNode getLeft() {
        return left;
    }

    /**
     * Set the node's left child.
     *
     * @param left - the left child node to be set.
     */
    public void setLeft(BinarySearchNode left) {
        this.left = left;
    }

    /**
     * Get the node's right child.
     *
     * @return the right child node.
     */
    public BinarySearchNode getRight() {
        return right;
    }

    /**
     * Set the node's right child.
     *
     * @param right - the right child node to be set.
     */
    public void setRight(BinarySearchNode right) {
        this.right = right;
    }


    /**
     * Number of children.
     * Helpful for determining which deletion case to use.
     *
     * @return this node's number of children
     */
    public int numChildren() {
        int numChildren = 0;

        if (left != null)
            numChildren++;
        if (right != null)
            numChildren++;

        return numChildren;
    }

    /**
     * Get the leftmost node.
     *
     * @return the leftmost node in the binary tree rooted at this node.
     */
    public BinarySearchNode getLeftmostNode() {
        if (left == null) return this;
        return getLeftmostNode(left);
    }

    /**
     * Recursive implementation of getLeftmostNode.
     *
     * @param node - the left node
     * @return the leftmost node
     */
    private BinarySearchNode getLeftmostNode(BinarySearchNode node) {
        if (node.left == null) return node;
        return getLeftmostNode(node.left);
    }

    /**
     * Get the rightmost node.
     *
     * @return the rightmost node in the binary tree rooted at this node.
     */
    public BinarySearchNode getRightmostNode() {
        if (right == null) return this;
        return getRightmostNode(right);
    }

    /**
     * Get the rightmost node.
     *
     * @param node - the right node
     * @return the rightmost node
     */
    private BinarySearchNode getRightmostNode(BinarySearchNode node) {
        if (node.right == null) return node;
        return getRightmostNode(node.right);
    }

    /**
     * Return the height of the binary tree at the current node.
     *
     * @return the largest height of this node's children
     */
    public int height() {
        int leftHeight = 0;
        int rightHeight = 0;
        leftHeight += height(left, -1);
        rightHeight += height(right, 1);

        if (leftHeight > rightHeight) return leftHeight;
        return rightHeight;
    }

    /**
     * Recursive height method
     *
     * @param node - the current node
     * @param side - left child is -1, right child is 1
     * @return an integer representing the height
     */
    private int height(BinarySearchNode node, int side) {
        if (side < 0 && node.left != null) return 1 + height(node.left, -1);
        if (side > 0 && node.right != null) return 1 + height(node.right, 1);
        return 0;
    }

    /**
     * Returns the node that can replace this one in a Case 3 deletion.
     * Specifically, the smallest node in the right subtree.
     *
     * @return the successor of this node.
     */
    public BinarySearchNode getSuccessor() {
        if (left == null && right == null) return null;
        return getLeftmostNode(right);
    }
}
