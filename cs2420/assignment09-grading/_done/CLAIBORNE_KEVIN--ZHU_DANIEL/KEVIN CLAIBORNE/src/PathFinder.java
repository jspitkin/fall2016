/*************************************
 * @author 	Daniel Zhu 		| u0922894
 * @author 	Kevin Claiborne | u1080787
 *
 * @title 	Assignment 09 - PacMan!
 * @date	November 2, 2016 
 **************************************/

package assignment09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

public class PathFinder {

	private static int width;
	private static int height;
	private static LinkedList<GraphNode> queue;
	private static GraphNode[][] allNodes;
	private static GraphNode start;
	private static GraphNode goal;

	/**
	 * If solution exists then output with display solution. If no solution out
	 * will be the same as input.
	 *
	 * @param inputFile
	 *            Filename of maze that needs to be solved.
	 * @param outputFile
	 *            Desired filename for the output.
	 *
	 */
	public static void solveMaze(String inputFile, String outputFile) {
		buildMaze(inputFile);

		GraphNode path = BFS(start);

		while (path.cameFrom != null && path.cameFrom.data != 'S') {
			path = path.cameFrom;
			path.data = '.';
		}
		writeToFile(outputFile);
	}

	/**
	 * Writes the Maze to .txt file.
	 * 
	 * @param outputFile
	 *            Desired File Name
	 */
	private static void writeToFile(String outputFile) {
		char[] lineHolder = new char[width];
		String[] LineHolderS = new String[height];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				lineHolder[col] = allNodes[row][col].data;
			}
			LineHolderS[row] = new String(lineHolder);
		}

		try {
			PrintWriter writer = new PrintWriter(outputFile);
			writer.println(height + " " + width);
			for (int i = 0; i < LineHolderS.length; i++) {
				writer.println(LineHolderS[i]);
			}
			writer.close();
		} catch (Exception e) {
		}
	}

	/**
	 * Performs breadth first search in order to find the shortest path from
	 * Start to Goal.
	 * 
	 * @param starter
	 *            Starting GraphNode to begin search.
	 * @return If path is possible then return Goal. If no path is possible then
	 *         return Start.
	 */
	private static GraphNode BFS(GraphNode starter) {
		queue = new LinkedList<GraphNode>();
		queue.addLast(starter);
		GraphNode current;

		while (!queue.isEmpty()) {
			current = queue.removeFirst();
			current.visited = true;

			if (current == goal) {
				return current;
			}

			for (GraphNode g : current.neighbors) {
				if (!g.visited) {
					queue.addLast(g);
					g.visited = true;
					g.cameFrom = current;
				}
			}
		}
		return start;
	}

	/**
	 * Inner class GraphNode
	 */
	private static class GraphNode {
		private char data;
		private GraphNode cameFrom;
		boolean visited;
		ArrayList<GraphNode> neighbors;

		/**
		 * GraphNode constructor
		 * 
		 * @param c
		 *            - character to add to data.
		 */
		private GraphNode(char c) {
			data = c;
			visited = false;
			cameFrom = null;
			neighbors = new ArrayList<GraphNode>();
		}
	}

	/**
	 * Reads file in line by line. Adds all char to char[][] and any ' ' to
	 * GraphNode[][].
	 * 
	 * @param filename
	 *            - Filename of maze.
	 * 
	 */
	public static void buildMaze(String filename) {
		int lineNumber = 0;

		// BufferReader to assist with reading in the file.
		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while (input.ready()) {
				if (lineNumber == 0) {
					// get dimensions.
					String[] dimensions = input.readLine().split(" ");
					height = Integer.parseInt(dimensions[0]);
					width = Integer.parseInt(dimensions[1]);
					lineNumber++;
					// create our GraphNode[][]
					allNodes = new GraphNode[height][width];
				}
				if (lineNumber > 0) {
					char[] tempChar = input.readLine().toCharArray();

					for (int i = 0; i < tempChar.length; i++) {
						allNodes[lineNumber - 1][i] = new GraphNode(tempChar[i]);
					}
				}
				lineNumber++;
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		linkGraphNodes(allNodes);
	}

	/**
	 * Takes in GraphNode[][] and links each GraphNode to any neighbors that do
	 * not contain an X.
	 * 
	 * @param mazeNodes
	 *
	 */
	private static void linkGraphNodes(GraphNode[][] mazeNodes) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {

				GraphNode myNode = mazeNodes[row][col];

				/*
				 * Assign each graphNode not containing an X it's neighbors.
				 * Only if it's neighbors are not X.
				 */
				if (myNode.data != 'X') {
					if (mazeNodes[row - 1][col].data != 'X') {
						myNode.neighbors.add(mazeNodes[row - 1][col]);
					}
					if (mazeNodes[row + 1][col].data != 'X') {
						myNode.neighbors.add(mazeNodes[row + 1][col]);
					}
					if (mazeNodes[row][col - 1].data != 'X') {
						myNode.neighbors.add(mazeNodes[row][col - 1]);
					}
					if (mazeNodes[row][col + 1].data != 'X') {
						myNode.neighbors.add(mazeNodes[row][col + 1]);
					}

					// Identify starting point and goal.
					if (myNode.data == 'S') {
						start = myNode;
					}
					if (myNode.data == 'G') {
						goal = myNode;
					}
				}
			}
		}
	}

	/**
	 * @param fileName
	 *            - Name of file original maze can be found in.
	 * @return String representation of the maze solution.
	 */
	public static String displayGraphSolution(String fileName) {
		buildMaze(fileName);
		String result;

		GraphNode path = BFS(start);

		if (path == start) {
			result = "Looks like we didn't find a solution \n\n";
		} else {
			result = "Here is the shortest path we found \n\n";
		}

		while (path.cameFrom != null && path.cameFrom.data != 'S') {
			path = path.cameFrom;
			path.data = '.';
		}

		return result + convertToString();
	}

	/**
	 * @param fileName
	 *            - Name of file maze can be found in.
	 * @return String representation of the maze.
	 */
	public static String displayGraph(String fileName) {
		buildMaze(fileName);
		return "Your Maze Looks Like This...\n\n" + convertToString();
	}

	/**
	 * Converts GraphNode[][] to String
	 * 
	 * @return String representation of the maze.
	 */
	private static String convertToString() {
		String result = height + " " + width + "\n";

		// adds [row][col] to result.
		for (int rows = 0; rows < height; rows++) {
			for (int col = 0; col < width; col++) {
				result += allNodes[rows][col].data;
			}
			result += "\n";
		}
		return result;
	}
	
	/**
	 * Counts the steps taken from start to goal.
	 * 
	 * @param filename
	 * Filename of maze that needs path dots counted.
	 * @return
	 * Int number of dots that makes up path.
	 */
	public static int countDots(String filename) {
		int dotCount = 0;
		
		// BufferReader to assist with reading in the file.
		try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
			while (input.ready()) {
				char[] tempChar = input.readLine().toCharArray();
				for (char c: tempChar) {
					if (c == '.') {
						dotCount++;
					}
				}
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dotCount;

	}
}
