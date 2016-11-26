//William Frank
//Elliot Lee
package assignment09;

import java.util.ArrayList;

public class GraphNode {
	
	public char data;
	public ArrayList<GraphNode> adjacentNodes;
	public GraphNode cameFrom;
	public int row;
	public int col;
	public boolean visited;
	
	/**
	 * Creates a node
	 * @param value the data held by the node
	 * @param aRow the "row" in the graph
	 * @param aCol the "column" in the graph
	 */
	public GraphNode(char value, int aRow, int aCol)
	{
		data = value;
		row = aRow;
		col = aCol;
		adjacentNodes = new ArrayList<GraphNode>();
		visited = false;
		cameFrom = null;
	}
	
	/**
	 * Adds a node to this node's adjacent nodes list
	 * @param node node to be linked to this node
	 */
	public void linkNode(GraphNode node)
	{
		adjacentNodes.add(node);
	}
}
