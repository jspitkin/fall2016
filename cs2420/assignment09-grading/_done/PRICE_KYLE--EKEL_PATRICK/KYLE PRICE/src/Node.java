package assignment09;

/**
 * This class creates a node object which is used in the Graph class
 * @author Kyle Price and Patrick Ekel
 * 11/2/2016
 */
public class Node {
	public char value;
	public Node parent;
	public boolean wasVisited;
	public int row;
	public int col;

	/**
	 * Node constructor
	 * @param c the char data of the Node
	 */
	public Node(char c) {
		this.value = c;
		this.parent = null;
		wasVisited = false;
		this.col = 0;
		this.row = 0;
	}
}