package assignment09;

/**
 * This class creates a graph
 * @author Kyle Price and Patrick Ekel
 * 11/2/2016
 */
public class Graph {
	Node[][] graph;
	int width;
	int height;
	static Node start;

	/**
	 * Constructor for the Graph
	 * @param height the height of the graph
	 * @param width the width of the graph
	 */
	public Graph(int height, int width) {
		this.height = height;
		this.width = width;
		graph = new Node[height][width];
	}

	/**
	 * Adds a new node to the graph
	 * @param c the data in the node
	 * @param row the row where the node will be
	 * @param column the column where the node will be
	 */
	public void add(char c, int row, int column) {
		if (c == 'S') {
			start = new Node(c);
			graph[row][column] = start;
			start.row = row;
			start.col = column;
		}
		Node temp = new Node(c);
		temp.row = row;
		temp.col = column;
		graph[row][column] = temp;
	}

	/**
	 * Helper for getting node
	 * @param x row
	 * @param y column
	 * @return the Node at row, column
	 */
	public Node getNode(int x, int y) {
		return graph[x][y];
	}

	/**
	 * Helper for getting value in a node
	 * @param row the row of the node
	 * @param col the column of the node
	 * @return the value in the node
	 */
	public char getValue(int row, int col) {
		return graph[row][col].value;
	}

	/**
	 * Helper for setting the value of a node
	 * @param row the row of the node
	 * @param col the column of the node
	 * @param dot the value of the new node
	 */
	public void setValue(int row, int col, char dot) {
		graph[row][col].value = dot;
	}

	/**
	 * A classic toString method for the graph
	 */
	public String toString() {
		String newString = height + " " + width + "\n";
		boolean twoOrMore = false;
		for (int i = 0; i < height; i++) {
			if (twoOrMore) {
				newString += "\n";
			}
			twoOrMore = true;
			for (int j = 0; j < width; j++) {
				newString += graph[i][j].value;
			}
		}
		return newString;
	}
}
