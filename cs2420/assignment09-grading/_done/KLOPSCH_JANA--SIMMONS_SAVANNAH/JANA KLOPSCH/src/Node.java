package assignment09;

/**
 * Creates a Node that holds a vertex in a graph. Holds the location of the node,
 * a boolean value that determines whether or not the node has been visited, and
 * the character value of the node in the Graph.
 *@author Jana Klopsch (u0854469) && Savannah Simmons (u1086770)
 * Last Modified 11-2-16
 */
public class Node {
	
	private boolean visited;
	public int rowNumber, colNumber;
	public Node previous; //When creating path, each Node will keep track of the node before it
	public char character;
	
	/**
	 * Constructor for Nodes
	 * @param rowNumber - row location in graph
	 * @param colNumber - column location in graph
	 */
	public Node(int rowNumber, int colNumber){
		visited = false;
		this.rowNumber = rowNumber;
		this.colNumber = colNumber;
	
	}
	
	/**
	 * Getter method for visited variable
	 * @return - boolean value of visited variable
	 */
	public boolean hasBeenVisited(){
		return visited;
	}
	
	/**
	 * Setter method for visited variable
	 * @param visited - (boolean) value to set visited to
	 */
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	 

}
