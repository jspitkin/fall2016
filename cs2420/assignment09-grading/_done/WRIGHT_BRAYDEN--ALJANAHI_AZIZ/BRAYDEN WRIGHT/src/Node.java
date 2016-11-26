package assignment09;

import java.util.Collections;
import java.util.LinkedList;

/**
 * A node in the graph, or a point in the maze.
 *
 * @author Brayden Wright (u0895942), Abdulaziz Aljanahi (u0901606)
 */
@SuppressWarnings("WeakerAccess")
public class Node {
    private char data;
    private boolean visited;
    private Node visitedBy;
    private Point point;
    private LinkedList<Node> neighbors;

    /**
     * Create the node with some basic values, if none were passed.
     */
    public Node() {
        this.visited = false;
        this.neighbors = new LinkedList<>();
    }

    /**
     * Create the node with the given data and point to associate it with.
     *
     * @param data  - the data that lives in this node
     * @param point - this node's associated point in the maze
     */
    public Node(char data, Point point) {
        this.data = data;
        this.point = point;
        this.visited = false;
        this.neighbors = new LinkedList<>();
    }

    /**
     * Set this node's data.
     *
     * @param data - the data to store in this node
     */
    public void setData(char data) {
        this.data = data;
    }

    /**
     * @return this node's data
     */
    public char getData() {
        return this.data;
    }

    /**
     * @return whether or not this node has been visited
     */
    public boolean isVisited() {
        return this.visited;
    }

    /**
     * Mark this node as visited.
     */
    public void visited() {
        this.visited = true;
    }

    /**
     * Mark this node as visited by a specific node.
     *
     * @param node - the node that visited this node
     */
    public void visitedBy(Node node) {
        this.visited = true;
        this.visitedBy = node;
    }

    /**
     * @return which node visited this node
     */
    public Node getVisitedBy() {
        return this.visitedBy;
    }

    /**
     * @return a list of this node's neighbors
     */
    public LinkedList<Node> getNeighbors() {
        Collections.shuffle(this.neighbors);
        return this.neighbors;
    }

    /**
     * Add to this node's list of neighbors.
     *
     * @param node - the node to add to this node's neighbors
     */
    public void addNeighbor(Node node) {
        if (node.data == 'X') return;
        this.neighbors.add(node);
    }

    /**
     * Get this node's point in the maze.
     * Useful for writing out the path taken in (x, y) points instead of visually.
     *
     * @return this node's point in the maze
     */
    public Point getPoint() {
        return this.point;
    }

}
