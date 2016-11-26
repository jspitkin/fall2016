package assignment09;

import java.util.LinkedList;

/**
 * A representation of a node which holds a value, and has a non-specified (not sorted) sorting of neighbor nodes.
 * 
 * @author - Lindsey Loveland (u0970740)
 * @author - Jacob Brown (u0583647)
 *
 * @param <T> Data type stored in Node.
 */
public class Node<T> {
	
	// LinkedList of the node's neighbors.
	private LinkedList<Node<T>> neighbors;
	
	// Data stored in node.
	private T data;
	
	// If the node has been visited.
	private boolean visited = false;
	
	// If the node is a chain in a path.
	private Node<T> cameFrom = null;
	
	// Constructor.
	Node (T data) {
		
		this.updateData(data);
		neighbors = new LinkedList<Node<T>>();
		
	}
	
	/**
	 * Add a neighbor to the node.
	 * @param n
	 */
	public void addNeighbor(Node<T> n) {
		
		neighbors.add(n);

	}
	
	/**
	 * Remove a neighbor from the node.
	 * @param n
	 * @return returns true if it was removed.
	 */
	public boolean removeNeighbor(Node<T> n) {
		
		return neighbors.remove(n);
		
	}
	
	/**
	 * Replace node's data with parameterized data.
	 * @param data
	 */
	public void updateData(T data) {
		this.data = data;
	}
	
	/**
	 * Get data stored in node.
	 * @return stored data.
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * Get neighbors of node.
	 * @return LinkedList of neighbors.
	 */
	public LinkedList<Node<T>> getNeighbors() {
		
		return neighbors;
		
	}

	/**
	 * If has been visited.
	 * @return true if visited.
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Set if node has been visited.
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Returns the preceding node if it has one.
	 * @return
	 */
	public Node<T> getCameFrom() {
		return cameFrom;
	}

	/**
	 * Sets the preceding node in a path of nodes.
	 * @param cameFrom
	 */
	public void setCameFrom(Node<T> cameFrom) {
		this.cameFrom = cameFrom;
	}
	
}