package assignment09;

import java.util.LinkedList;

import assignment09.Node;

/**
 * A generic representation of a graph with nodes.
 * 
 * @author - Lindsey Loveland (u0970740)
 * @author - Jacob Brown (u0583647)
 *
 * @param <T> data type stored in the node's of the graph.
 */
public class Graph<T> {
	
	// LinkedList of nodes within the graph
	private LinkedList<Node<T>> nodes;
	
	// Graph constructor.
	Graph() {
		nodes = new LinkedList<Node<T>>();
	}
	
	/**
	 * Adds a node to the graph.
	 * @param node
	 */
	public void addNode(Node<T> node) {
		nodes.add(node);
	}

	/**
	 * Gets a LinkedList of the nodes in the graph.
	 * @return all nodes in graph.
	 */
	public LinkedList<Node<T>> getNodes() {
		return nodes;
	}
	
}