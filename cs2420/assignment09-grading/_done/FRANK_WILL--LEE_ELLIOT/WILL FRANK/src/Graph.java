//William Frank
//Elliot Lee
package assignment09;

import java.util.ArrayList;

/**
 * Class representing a 2D graph
 * @author Elliot Lee & Will Frank
 */
public class Graph {
	
	ArrayList<GraphNode> nodes;
	public static GraphNode start;
	public static GraphNode goal;
	int rowLength;
	int colLength;
	
	/**
	 * Creates a new graph from a 2d input char array
	 * for an index (x, y) in the input array, true means there is a node present at that index and false means there is no node present
	 * If input is empty or the first row of input is empty, throws a null pointer exception
	 * @param input
	 */
	public Graph(char[][] input)
	{
		if (input == null || input[0].length == 0)
		{
			throw new NullPointerException();
		}
		nodes = new ArrayList<GraphNode>();
		rowLength = input.length;
		colLength = input[0].length;
		
		for (int i = 0; i < input.length; i++)
		{
			for (int j = 0; j < input[0].length; j++)
			{
				GraphNode newNode = new GraphNode(input[i][j], i, j);
				GraphNode left = getNodeAtLocation(i, j - 1);
				GraphNode above = getNodeAtLocation(i - 1, j);
				if (left != null && left.data != 'X')
				{
					newNode.linkNode(left);
					left.linkNode(newNode);
				}
				if (above != null && above.data != 'X')
				{
					newNode.linkNode(above);
					above.linkNode(newNode);
				}
				nodes.add(newNode);
				if (newNode.data == 'S')
				{
					start = newNode;
				}
				else if (newNode.data == 'G')
				{
					goal = newNode;
				}
			}
		}

	}
	
	/**
	 * Takes a location in the maze and returns the node there
	 * @param row the row in the maze
	 * @param col the column in the maze
	 * @return the node at the specified location
	 */
	private GraphNode getNodeAtLocation(int row, int col)
	{
		if (row < 0 || col < 0 || row > rowLength - 1 || col > colLength - 1)
		{
			return null;
		}
		return nodes.get((row * colLength) + col);
	}

}
