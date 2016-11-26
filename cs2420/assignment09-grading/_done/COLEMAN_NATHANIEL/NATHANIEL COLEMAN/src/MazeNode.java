package assignment09;

import java.util.LinkedList;

/**
 * Class representation of a point in a maze organized by rows and columns. MazeNodes can be walls, open spaces, a start point,
 * or a goal point.
 * 
 * @author Nathaniel Coleman u0913541, Torin McDonald u0940235
 *
 */
public class MazeNode {
	
	private char data;			//Defines what type this node is. 'X' if wall, 'S' if start, 'G' if goal, and ' ' if open pathway.
	private MazeNode prevNode;	//Node that was prior to this node in a pathway. Will only be used if this node is in the shortest path.
	private boolean visited;	//Will be false unless added to a viable path.
	private boolean isStart, isGoal; 	//Another way of checking if a node is start or goal.
	private int row, column;	//Location of node in 2D array.
	
	public MazeNode(char property, int row, int column) {
		this.row = row;
		this.column = column;
		data = property;
		
		prevNode = null;
		visited = false;
		
		isGoal = false;
		isStart = false;
		if (property == 'G') {
			isGoal = true;
		}
		if (property == 'S') {
			isStart = true;
		}
	}
	/**
	 * @return data of this node. Could be 'X', 'S', 'G', or ' '.
	 */
	public char getData() {
		return data;
	}
	/**
	 * Sets this nodes data to given character.
	 * @param property - char to be new data.
	 */
	public void setData(char property) {
		data = property;
	}
	/**
	 * @return this node's previous node in a path.
	 */
	public MazeNode getPrev() {
		return prevNode;
	}
	/**
	 * Sets this node's previous node to given node.
	 * @param parent - node to be this node's parent in path.
	 */
	public void setPrev(MazeNode parent) {
		prevNode = parent;
	}
	
	/**
	 * Method to check if a node is valid (i.e. node is not a wall and is unvisited) and if so, add it to a given queue, change its
	 * visited to true, and set it prevNode to a given MazeNode.
	 * @param queue - if valid, this node will be added to the end of this queue.
	 * @param parent - if added to queue, this node's prevNode will be set to MazeNode Parent.
	 * @return true if node was valid, false otherwise.
	 */
	public boolean ifValid(LinkedList<MazeNode> queue, MazeNode parent) {
		if (data == 'X' || visited) {
			return false;
		}
		queue.offer(this);
		toggleVisited();
		setPrev(parent);
		
		return true;
	}
	/**
	 * @return true if node has been visited, false otherwise.
	 */
	public boolean isVisited() {
		return visited;
	}
	/**
	 * Changes this visited value. If it was false, now true, and vice versa.
	 */
	public void toggleVisited() {
		visited = !visited;
	}
	/**
	 * @return true if this node is the goal, false otherwise.
	 */
	public boolean isGoal() {
		return isGoal;
	}
	/**
	 * @return true if this node is the start, false otherwise.
	 */
	public boolean isStart() {
		return isStart;
	}
	/**
	 * @return this node's row in 2D array
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @return this node's column in 2D array
	 */
	public int getColumn() {
		return column;
	}

}
