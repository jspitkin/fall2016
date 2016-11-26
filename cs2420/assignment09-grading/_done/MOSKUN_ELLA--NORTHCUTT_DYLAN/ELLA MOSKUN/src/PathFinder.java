package assignment09;

import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Class to Find the shortest path from a maze in an input file and to output
 * the solved maze as a file
 * 
 * @author Ella Moskun     u0897252
 * @author Dylan Northcutt u1055102
 */
public class PathFinder {

	/**
	 * Solves a maze by reading in its file and outputting the same file with
	 * spaces changed to periods to mark a path of the shortest possible
	 * distance, if one exists. The input is assumed to follow the conventions
	 * specified in the assignment description. Otherwise, the behavior of this
	 * method is undefined and may or may not result in the destruction of the
	 * universe.
	 * 
	 * @param inputPath
	 * @param outputPath
	 */
	public static void solveMaze(String inputPath, String outputPath) {
		Node[][] nodes = null;
		Node     start = null;
		Node     end   = null;

		// Read in the maze
		try (Scanner scanner = new Scanner(new File(inputPath))) {
			String[] dimensions = scanner.nextLine().split(" ");
			nodes = new Node[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];

			for (int row = 0; row < nodes.length; row++) {
				String line = scanner.nextLine();
				for (int column = 0; column < nodes[0].length; column++) {
					Node node = new Node(row, column, line.charAt(column));

					switch (node.data) {
					case 'X':
						// Do not add walls
						break;
					case 'S':
						start = node;
						nodes[row][column] = node;
						break;
					case 'G':
						end = node;
						nodes[row][column] = node;
						break;
					default:
						nodes[row][column] = node;
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		performBreadthFirstSearch(nodes, start, end);
		markPath(nodes, start, end);
		writeOutput(new File(outputPath), nodes);
	}

	/**
	 * Finds the shortest path in the maze using a breadth-first search
	 * 
	 * @param nodes
	 * @param start
	 * @param end
	 */
	private static void performBreadthFirstSearch(Node[][] nodes, Node start, Node end) {
		Queue<Node> queue = new LinkedList<Node>();
		start.visited = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			Node current = queue.remove();
			Node[] neighbors = neighbors(nodes, current);

			for (Node neighbor : neighbors) {
				if (neighbor == end) {
					neighbor.cameFrom = current;
					return; // We reached the end!

				} else if (neighbor != null && !neighbor.visited) {
					neighbor.visited = true;
					neighbor.cameFrom = current;
					queue.add(neighbor);
				}
			}
		}
	}

	/**
	 * Keeps track of all the neighborers (above, below, left and right of a
	 * node
	 * 
	 * @param nodes
	 * @param current
	 * @return
	 */
	private static Node[] neighbors(Node[][] nodes, Node current) {
		return new Node[] { nodes[current.row - 1][current.column], // North
				nodes[current.row][current.column + 1], // East
				nodes[current.row + 1][current.column], // South
				nodes[current.row][current.column - 1] // West
		};
	}

	/**
	 * Marks '.' for the path once it has been found
	 * 
	 * @param nodes
	 * @param start
	 * @param end
	 */
	private static void markPath(Node[][] nodes, Node start, Node end) {
		// There needs to be a path for us to mark one!
		if (end.cameFrom != null) {
			Node current = end.cameFrom;
			while (current != start) {
				current.data = '.';
				current = current.cameFrom;
			}
		}
	}

	/**
	 * writes the solved maze to a file with '.' marking the path or returns
	 * original maze if no path is found
	 * 
	 * @param outputFile
	 * @param nodes
	 */
	private static void writeOutput(File outputFile, Node[][] nodes) {
		try (PrintWriter output = new PrintWriter(outputFile)) {
			// Dimensions
			output.println(nodes.length + " " + nodes[0].length);

			// Maze
			for (String line : toStrings(nodes)) {
				output.println(line);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * turns the maze into an array of string
	 * 
	 * @param nodes
	 * @return
	 */
	private static String[] toStrings(Node[][] nodes) {
		String[] result = new String[nodes.length];
		for (int row = 0; row < nodes.length; row++) {
			String line = "";
			for (int column = 0; column < nodes[0].length; column++) {
				// Use X to represent walls
				line += (nodes[row][column] == null) ? 'X' : nodes[row][column].data;
			}
			result[row] = line;
		}
		return result;
	}

	/**
	 * Creates nodes containing the information, position , visited and where it
	 * was visited from
	 */
	private static class Node {
		int row;
		int column;
		char data;
		boolean visited;
		Node cameFrom;

		/**
		 * Assigns nodes containing the information, position , visited and
		 * where it was visited from
		 * 
		 * @param row
		 * @param column
		 * @param data
		 */
		Node(int row, int column, char data) {
			this.row = row;
			this.column = column;
			this.data = data;
			visited = false;
			cameFrom = null;
		}
	}
}
