package assignment09;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.LinkedList;
import java.util.Queue;

/**
 * A set of tools used to find a path in the maze of a file containing text that has specific parameters.
 * The maze must have on the first line, a col size followed by a row size, I.E "5 5"
 * The rest of the file must have a rectangular shaped maze which is surrounded by 'X' characters
 * Goal = 'G' and Start = 'S' and Walls = 'X'
 *
 * @author - Lindsey Loveland (u0970740)
 * @author - Jacob Brown (u0583647)
 */
public class PathFinder {
	
	
	/**
	 * 
	 * @param inputFile
	 * @param outputFile
	 * 
	 * Reads in a maze from the input file, solves it, and outputs the solved maze in outputFile
	 */
	public static void solveMaze(String inputFile, String outputFile){
		
		//read in maze from input file, store to 2d char array
		char[][] mazeArray = readFile(inputFile);
		
		//The graph of the maze.
		Graph<Matrix<Character>> mazeGraph = new Graph<Matrix<Character>>();
		
		//convert 2d char array to graph
		mazeGraphGenerator(mazeArray,mazeGraph);
		
		//perform breadth-first search, store results
		Node<Matrix<Character>> results = BFS(mazeGraph);
		
		//if there is no pathway, there is no solution
		if (results == null) {
			
			//Write unsolved maze.
			writeMazeToFile(mazeArray, outputFile);
			
		}
		else {
			
			//draw pathway with '.' characters on maze
			drawGoalPath(results,mazeArray,mazeGraph);
			
			//Write maze to file.
			writeMazeToFile(mazeArray, outputFile);
			
		}
			
	}

	/**
	 * 
	 * @param node
	 * @return String representation of the Node path solution
	 * 
	 * Prints the node pathway in coordinate (x,y) form for testing purposes. Unused.
	 * 
	 */
	@SuppressWarnings("unused")
	private static String printPath(Node<Matrix<Character>> node) {
		
		Node<Matrix<Character>> printNode = node; 
		String nodePath = "";
		boolean end = false;
		
		// While path has yet to have reached the start, concat the current node's data in the path to a string.
		while (!end)
		{
			
			if (printNode.getData().getData().equals('S')) {
				end = true;
			}
			
			nodePath+= "Goal - Data:'" + printNode.getData().getData() + "' (x,y) (" + printNode.getData().getXVal() + "," + printNode.getData().getYVal() + ")" + "-> \n";
			
			printNode = printNode.getCameFrom();
			
		}
		return nodePath;
	}
	
	/**
	 * 
	 * @param node
	 * @param maze
	 * 
	 * Inserts a solution path into a 2d char maze from the goal node.
	 * 
	 */
	private static void drawGoalPath(Node<Matrix<Character>> node, char[][] maze, Graph<Matrix<Character>> mazeGraph) {
		
		// If the incoming node came from somewhere.
		if (node.getCameFrom() != null) {
			
			// Start with the node which preceded the goal node.
			Node<Matrix<Character>> currNode = node.getCameFrom(); 
			
			// While the currNode != the start node.
			while (!currNode.getData().getData().equals('S'))
			{
				// Replace the node's data with a "." in the graph.
				currNode.getData().setData('.');
				// Replace the (x,y) data with a "." in the 2d char array.
				maze[currNode.getData().getXVal()][currNode.getData().getYVal()] = '.';
	
				// Go to the next node in the solution path.
				currNode = currNode.getCameFrom();
				
			}
		}

	}

	/**
	 * 
	 * @param maze
	 * @param mazeGraph
	 * 
	 * Converts a 2D char array maze into a graph with matrix nodes. 
	 */
	private static void mazeGraphGenerator(char[][] maze, Graph<Matrix<Character>> mazeGraph) {
		
		// Getting the size of the char[][] maze.
		int colSize = maze.length;
	    int rowSize = maze[0].length;
		
	    // we want to go through each element in the char array.
	    for (int currRow = 0; currRow < rowSize; currRow++) {
	    
		    for (int currCol = 0; currCol < colSize; currCol++) {
		    
		    	// We add every element from the char array into a graph and we store the x,y,data in a matrix.
		    	Node<Matrix<Character>> node = new Node<Matrix<Character>>(new Matrix<Character> (currCol,currRow,maze[currCol][currRow]));
		    	
		    	// Add that node into the graph.
		    	mazeGraph.addNode(node);
		    	
		    }
	    }
	    
	    // Map the neighboring nodes together.
	    mapMatrixNeighbors(mazeGraph);
	    	
	}

	
	/**
	 * 
	 * @param mazeGraph
	 * 
	 * Finds all neighbors of a node in a maze graph (up, down, right, and left)
	 * 
	 */
	private static void mapMatrixNeighbors(Graph<Matrix<Character>> mazeGraph) {
		
		// For ever node in the maze.
		for (Node<Matrix<Character>> node : mazeGraph.getNodes()) {
			
			// Get the node's x,y values.
			int nodeX = node.getData().getXVal();
			int nodeY = node.getData().getYVal();
			
			// For every node again in the maze.
			for (Node<Matrix<Character>> nodeNeighbor : mazeGraph.getNodes()) {
				
				// If node is self, then continue to next node.
				if (nodeNeighbor == node) 
				continue;
				
				// If node is already a neighbor, then continue to next node.
				if (node.getNeighbors().contains(nodeNeighbor))
				continue;
				
				// Get the X,Y of the potential neighbor.
				int nodeNeighborX = nodeNeighbor.getData().getXVal();
				int nodeNeighborY = nodeNeighbor.getData().getYVal();
				
				// If the X,Y distance of the two neighbors is <= 1, AND the distance of X + Y of the two neighbors is <= 1 then they are neighbors.
				if (((Math.abs((nodeX - nodeNeighborX)) <= 1) && (Math.abs((nodeY - nodeNeighborY)) <= 1)) && ((Math.abs((nodeX - nodeNeighborX)) + Math.abs((nodeY - nodeNeighborY)) <= 1))) {
					node.addNeighbor(nodeNeighbor);
				}
				
			}
			
		}
		
	}
	
	/**
	 * 
	 * @param mazeGraph
	 * @return Goal graph node
	 * 
	 * Breadth-first Search for maze graph, maps out pathway from start to goal.
	 */
	private static Node<Matrix<Character>> BFS(Graph<Matrix<Character>> mazeGraph){
		
		// Create a queue that stores Node's which stores matrices which stores characters in those matrices.
		Queue<Node<Matrix<Character>>> queue = new LinkedList<Node<Matrix<Character>>>();
		
		// Initialize start.
		Node<Matrix<Character>> start = null;
		
		// Find the node with the character 'S', this is the starting node.
		for(Node<Matrix<Character>> n : mazeGraph.getNodes()){
			if(n.getData().getData().equals('S')){
				start = n;
			}
		}
		
		// If no 'S' was found, return null.
		if (start == null)
		return start;
		
		// Add the starting node as visited, and add it to the queue.
		start.setVisited(true);
		queue.add(start);
		
		while (!queue.isEmpty()){
			// Peek at the queue and get the current node.
			Node<Matrix<Character>> current = queue.peek();
			
			//if we are at our goal, return - this was the shortest path.
			if(current.getData().getData().equals('G')){
				return current;
			}
			
			// Remove current node from queue.
			queue.remove(current);
			
			// For every neighbor of current node.
			for(Node<Matrix<Character>> currNeighbor : current.getNeighbors()){
				
				// If it isn't a wall or has been visited.
				if((!currNeighbor.getData().getData().equals('X')) && (!currNeighbor.isVisited())) {
					
					// Add to queue.
					queue.add(currNeighbor);
					
					//Mark as cameFrom and as visited.
					currNeighbor.setCameFrom(current);
					currNeighbor.setVisited(true);
					
				}
			}
			
		}
			
		return null;
		
	}


	/**
	 * 
	 * @param inputFile
	 * @return the Maze represented as a 2D string array
	 * @throws IOException
	 * 
	 * Reads in the maze in the inputFile and stores in a 2D string array
	 * 
	 */
	public static char[][] readFile(String inputFile) {
		
		try (RandomAccessFile ramAccessReader = new RandomAccessFile(inputFile , "r")) {	
		    
		    // Get dimensions
		    String[] dimensions = ramAccessReader.readLine().split(" ");
			
			int rowSize = Integer.parseInt(dimensions[0]);
		    int colSize = Integer.parseInt(dimensions[1]);

		    // Create maze 2D array with dimensions
			char[][] maze = new char[colSize][rowSize];
	
			// Current row.
			int rowNum = 0;
			
			// Current line in file.
			String line = ramAccessReader.readLine();
			
			//While the rowNum is less than the size of the maze.
			while ((rowNum < rowSize)) {
				
				for (int j = 0; j < line.length(); j++) {
					
					// Add the character of the line at [x][y] into the maze at maze[x][y]
					maze[j][rowNum] = line.toCharArray()[j];
					
				}
				
				// Read a new line.
				line = ramAccessReader.readLine();
				
				// Increment row.
				rowNum++;
				
			}
			
			// Close reader and return maze.
			ramAccessReader.close();
			return maze;
			
		} 
		
		catch (IOException e) {
			e.printStackTrace();
		}

		return null;

	}
	
	/**
	 * 
	 * @param inputMaze
	 * @return String representation of maze 
	 * 
	 * Prints a visual representation of the maze from a 2D char array
	 */
	public static String printMaze(char[][] inputMaze) {
		
		// Get row/col size from array.
		int rowSize = inputMaze[0].length;
		int colSize = inputMaze.length;
		
		// Create string.
		String stringMaze = "";
		
		// Concatenate the size of the array to the string.
		stringMaze+= colSize + " " + rowSize + "\n";
		
		for (int currRow = 0; currRow < rowSize; currRow++ ) {
		
			for(int currCol = 0; currCol < colSize; currCol++){

				// Add the data from the array position to the string.
				stringMaze+= inputMaze[currCol][currRow];
				
			}
			
			if (currRow + 1 < rowSize)
			stringMaze+= '\n';
			
		}
		
		return stringMaze;
		
	}
	
	/**
	 * 
	 * @param inputMaze
	 * @param mazeGraph
	 * @return String representation of maze
	 * 
	 * Prints a visual representation of maze graph
	 */
	public static String printMaze(char[][] inputMaze, Graph<Matrix<Character>> mazeGraph) {
		
		// Get row/col size from array.
		int rowSize = inputMaze[0].length;
		int colSize = inputMaze.length;
		
		// Create string.
		String stringMaze = "";
		
		// Concatenate the size of the array to the string.
		stringMaze+= colSize + " " + rowSize + "\n";
		
		int colNum = 0;
		
		for (Node<Matrix<Character>> currentNode : mazeGraph.getNodes()) {
		
			// Add the data from the node to the string.
			stringMaze+= currentNode.getData().getData();
			colNum++;
			
			// Since there is no defined shape of a graph, make a new line for every time colNum is as big as the array.
			if (colNum >= colSize) {
				stringMaze+= "\n";
				colNum = 0;
			}
			
		}
			
		return stringMaze;
		
	}
	
	/**
	 * 
	 * @param maze
	 * 
	 * Writes pathway solution to a text file
	 */
	public static void writeMazeToFile(char[][] maze, String outputFile){
		
		try {
			// Create an output writer.
			PrintWriter output = new PrintWriter(new FileWriter(outputFile));

			// Declare size of array.
			int rowSize = maze[0].length;
			int colSize = maze.length;
			
			// Output size of maze.
			output.println(rowSize + " " + colSize);
			
			for (int currRow = 0; currRow < rowSize; currRow++ ) {
				
				for(int currCol = 0; currCol < colSize; currCol++){
					
					//Print maze data to file.
					output.print(maze[currCol][currRow]);
				}
				//Print new line to file after col is at colSize.
				output.print('\n');
			}
			// Close output writer.
			output.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}

	

