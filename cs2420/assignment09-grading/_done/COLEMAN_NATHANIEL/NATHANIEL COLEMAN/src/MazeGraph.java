package assignment09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class representation of a pacman maze, where each point in a coordinate system is a MazeNode.
 * 
 * @author Nathaniel Coleman u0913541, Torin McDonald u0940235
 *
 */
public class MazeGraph {

	private MazeNode[][] maze;
	private MazeNode start;
	private MazeNode goal;

	/**
	 * Constructor for a MazeGraph object representing a 2D array whose elements create a maze. 
	 * @param inputFile
	 * @throws IOException
	 */
	public MazeGraph(String inputFile) throws IOException {
		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		String[] dimensions = inputReader.readLine().split(" ");
		int height = Integer.parseInt(dimensions[0]);
		int width = Integer.parseInt(dimensions[1]);

		maze = new MazeNode[height][width];

		for (int row = 0; row < height; row++) {
			char[] charArray = inputReader.readLine().toCharArray();
			for (int column = 0; column < width; column++) {

				MazeNode node = new MazeNode(charArray[column], row, column);
				maze[row][column] = node;

				if(node.isStart()){
					start = node;
				}
				else if(node.isGoal()){
					goal = node;
				}

			}

		}
		inputReader.close();
	}

	/**
	 * Accessor for any node in maze.
	 * @param row of node
	 * @param column of node
	 * @return MazeNode at location
	 */
	public MazeNode getNode(int row, int column) {
		return maze[row][column];
	}

	/**
	 * @return this maze's start node.
	 */
	public MazeNode getStart(){
		return start;
	}

	/**
	 * @return this maze's goal node.
	 */
	public MazeNode getGoal(){
		return goal;
	}
}
