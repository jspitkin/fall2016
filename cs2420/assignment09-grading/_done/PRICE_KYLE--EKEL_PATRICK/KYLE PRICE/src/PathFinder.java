package assignment09;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * PathFinder - finds the shortest path in a maze that gets passed in as a string. It starts at 
 * 'S', it ends at 'G', and walls are denoted as 'X'.
 * @author Kyle Price and Patrick Ekel
 * 11/02/2016
 *
 */
public class PathFinder {
	private static LinkedList<Node> ll;
	private static Graph g;
	static int height;
	static int width;

	/**
	 * solveMaze - is the main method that finds the shortest path. 
	 * @param inputFile - a string containing the maze.
	 * @param outputFile - is where the maze (with the shortest path indicated) is saved. 
	 * @throws IOException
	 */
	public static void solveMaze(String inputFile, String outputFile) throws IOException {
		BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader(inputFile));
			String[] dimensions = input.readLine().split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			g = new Graph(height, width);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		char[] line;
		for (int row = 0; row < height; row++) {
			line = input.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				g.add(line[column], row, column);
			}
		}

		ArrayList<Node> itemsVisited = bfs(); // This contains the nodes that need to be dotted.
		modifyGraph(itemsVisited);
		try (PrintWriter outputNew = new PrintWriter(new FileWriter(outputFile))) {
			outputNew.println(g.toString()); // This custom toString() method prints the graph to a file.

		} catch (FileNotFoundException o) {
			System.out.println("There was an error with the file writer.");
		}
	}

	/**
	 * modifyGraph - takes a list of the nodes that need to be dotted and modifies the graph accordingly.
	 * @param nodesToDot - a list of the path nodes.
	 */
	public static void modifyGraph(ArrayList<Node> nodesToDot) {
		for (Node node : nodesToDot) {
			if (g.getValue(node.row, node.col) != 'S' && g.getValue(node.row, node.col) != 'G') {
				g.setValue(node.row, node.col, '.');
			}
		}
	}

	/**
	 * bfs - performs breadth first search on the graph "g" and finds the shortest path
	 * from "S" to "G". 
	 * @return - an ArrayList of the nodes that contain the shortest path.
	 */
	public static ArrayList<Node> bfs() {
		ll = new LinkedList<Node>(); // ll keeps track of all the items touched that contain ' '. 
		ll.addFirst(Graph.start);
		Node currentNode;
		ArrayList<Node> itemsVisited = new ArrayList<Node>();
		itemsVisited.add(Graph.start);
		while (!ll.isEmpty()) {
			currentNode = ll.removeLast();
			if (currentNode.value == 'G') {
				break;
			}

			boolean skipRight = false;
			Node rightNode = null;
			// Check right node...
			rightNode = g.getNode(currentNode.row, currentNode.col + 1);
			for (Node node : itemsVisited) { // This checks to see if this node was already visited.
				if (rightNode.col == node.col && rightNode.row == node.row) {
					skipRight = true;
				}
			}

			if (!skipRight && !rightNode.wasVisited) {
				char rightItem = rightNode.value;
				if (rightItem == ' ') {
					ll.addFirst(rightNode);
					rightNode.wasVisited = true;
					itemsVisited.add(rightNode);
					rightNode.parent = currentNode;
				} else if (rightItem == 'G') {
					ll.addFirst(rightNode);
					rightNode.wasVisited = true;
					itemsVisited.add(rightNode);
					rightNode.parent = currentNode;
					break;
				}
			}

			boolean skipTop = false;
			Node topNode = null;
			// Check top node...
			topNode = g.getNode(currentNode.row - 1, currentNode.col);
			for (Node node : itemsVisited) { // This checks to see if this node was already visited.
				if (topNode.col == node.col && topNode.row == node.row) {
					skipTop = true;
				}
			}

			if (!skipTop && !topNode.wasVisited) {
				char upItem = topNode.value;
				if (upItem == ' ') {
					ll.addFirst(topNode);
					itemsVisited.add(topNode);
					topNode.parent = currentNode;
					topNode.wasVisited = true;
				} else if (upItem == 'G') {
					ll.add(topNode);
					topNode.wasVisited = true;
					itemsVisited.add(topNode);
					topNode.parent = currentNode;
					break;

				}
			}

			boolean skipLeft = false;
			Node leftNode = null;
			// Check left node...
			leftNode = g.getNode(currentNode.row, currentNode.col - 1);
			for (Node node : itemsVisited) { // This checks to see if this node was already visited.
				if (leftNode.col == node.col && leftNode.row == node.row) {
					skipLeft = true;
				}
			}

			if (!skipLeft && !leftNode.wasVisited) {
				char leftItem = leftNode.value;
				if (leftItem == ' ') {
					ll.addFirst(leftNode);
					itemsVisited.add(leftNode);
					leftNode.parent = currentNode;// should this happen here?
					leftNode.wasVisited = true;
				} else if (leftItem == 'G') {
					ll.add(leftNode);
					leftNode.wasVisited = true;
					itemsVisited.add(leftNode);
					leftNode.parent = currentNode;
					break;
				}
			}

			boolean skipBottom = false;
			Node bottomNode = null;
			// check bottom node...
			bottomNode = g.getNode(currentNode.row + 1, currentNode.col);
			for (Node node : itemsVisited) { // This checks to see if this node was already visited.
				if (bottomNode.col == node.col && bottomNode.row == node.row) {
					skipBottom = true;
				}
			}

			if (!skipBottom && !bottomNode.wasVisited) {
				char bottomItem = bottomNode.value;
				if (bottomItem == ' ') {
					ll.addFirst(bottomNode);
					itemsVisited.add(bottomNode);
					bottomNode.wasVisited = true;
					bottomNode.parent = currentNode;
				} else if (bottomItem == 'G') {
					ll.add(bottomNode);
					bottomNode.wasVisited = true;
					itemsVisited.add(bottomNode);
					bottomNode.parent = currentNode;
					break;
				}
			}
		}

		ArrayList<Node> pathArr = new ArrayList<Node>();
		// The final ArrayList with the correct path nodes.

		boolean pathComplete = false;
		if (itemsVisited.isEmpty()) {
			return itemsVisited;
		}
		Node currentItem = itemsVisited.get(itemsVisited.size() - 1);
		if (currentItem.value != 'G') { // Checks to see if 'G' was ever reached, if not, don't modify graph.
			return pathArr;
		}
		while (!pathComplete) {
			pathArr.add(currentItem);
			if (currentItem.parent == null) {
				pathComplete = true;
				break;
			}
			currentItem = currentItem.parent;
		}
		return pathArr;
	}
}
