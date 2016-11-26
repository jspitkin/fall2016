package assignment09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Grouping class for static methods involved in solving a pacman maze.
 * 
 * @author Nathaniel Coleman u0913541, Torin McDonald u0940235
 *
 */
public class PathFinder {

	public PathFinder() {

	}

	/**
	 * Method to find the shortest path between a specified start and goal if one exists. Input file must provide dimensions of maze.
	 * Maze must be rectangular and have walls on all edges. inputFile is not modified.
	 * @param inputFile - text file with structure to be used as maze.
	 * @param outputFile - copy of inputFile with shortest path shown by periods. If no path exists, outputFile will be an unmodified
	 * 		version of inputFile.
	 * @throws IOException if an I/O error occurs.
	 */
	public static void solveMaze(String inputFile, String outputFile) throws IOException {
		MazeGraph maze = new MazeGraph(inputFile);		//Create maze through constructor.
		breadthFirstSearch(maze);						//Nodes in shortest path are modified to hold a '.'

		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		String[] dimensions = inputReader.readLine().split(" ");
		int height = Integer.parseInt(dimensions[0]);
		int width = Integer.parseInt(dimensions[1]);

		try(PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
			output.println(height + " " + width);			//Write dimensions to outputFile.
			System.out.println(height + " " + width);

			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					output.print(maze.getNode(row, col).getData());		//Copy data from nodes to recreate maze. The nodes within the path
					System.out.print(maze.getNode(row, col).getData());	//	will now have data equal to '.'
				}
				output.println();
				System.out.println();
			}

		}
		inputReader.close();
	}

	/**
	 * Uses a breadth-first search algorithm to find a shortest path through given maze, if one exists. Nodes that are 
	 * part of the path will have their data modified to a '.'
	 * @param maze
	 */
	private static void breadthFirstSearch(MazeGraph maze) {
		LinkedList<MazeNode> queue = new LinkedList<MazeNode>();
		queue.offer(maze.getStart());
		maze.getStart().toggleVisited();
		MazeNode currNode;

		while (!queue.isEmpty()) {
			currNode = queue.poll();
			if (currNode.isGoal()) {								//Handles if goal is found.
				while (currNode.getPrev() != maze.getStart()) {
					currNode = currNode.getPrev();
					currNode.setData('.');							//Will set data in all nodes contained in path to '.'
				}
			}
			else {
				int row = currNode.getRow();
				int col = currNode.getColumn();

				ArrayList<MazeNode> neighborList = new ArrayList<MazeNode>();
				neighborList.add(maze.getNode(row - 1, col));	//Add the 4 neighbors of currNode to a list.
				neighborList.add(maze.getNode(row, col + 1));
				neighborList.add(maze.getNode(row + 1, col));
				neighborList.add(maze.getNode(row, col - 1));

				for (MazeNode neighbor : neighborList) {
					neighbor.ifValid(queue, currNode);			//Add whichever neighbors are valid to the queue.
				}

			}

		}

	}

}
