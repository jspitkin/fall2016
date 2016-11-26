package assignment09;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Builds a graph of nodes, according to input file, and solves the shortest path
 * from start node to goal node.
 * @author Jana Klopsch (u0854469) && Savannah Simmons (u1086770)
 * Last Modified 11-2-16
 */
public class PathFinder {
	
	static ArrayList<Node> shortestPath = new ArrayList<>(); //Holds nodes that are on shortest path
	
	/**
	 * Given a maze, will find shortest path from start to goal.  Path does not go over 'X'
	 * and does not travel in diagonal path.  
	 * @param inputFile -	maze to be solved, with the dimensions of the maze on first line,
	 * 						rest of txt file is maze, with X characters being "walls", S as 
	 * 						the start, and G as the goal.
	 * @param outputFile -	input maze, with shortest path marked with '.' along the path, and 
	 * 						the same maze without any '.' if the maze is unsolvable
	 */
	public static void solveMaze(String inputFile, String outputFile){
		Graph maze;
		try { //File must exist to create the graph
			maze = new Graph(inputFile);
		} catch (FileNotFoundException e1) {
			System.out.println("File not found");
			return;
		}
		
		Node[][] nodes = maze.getNodes();
		
		LinkedList<Node> queue = new LinkedList<>(); //Keep track of nodes we visit using BFS technique in creating path
		
		int rows = maze.rows;
		int cols = maze.cols;
		
		queue.add(maze.getStart());
		maze.getStart().setVisited(true); //Begin at Start node
		
		Node currentNode, leftNode, rightNode, upNode, downNode; //Initialize neighbor nodes
		
		while (!(queue.isEmpty())){
			currentNode = queue.remove();  //Pull node out of queue to look at its neighbors
			
			//If at Goal node, shortest path has been found
			if (currentNode == maze.getGoal()){
				shortestPath(currentNode);
				break;
			}
			//Declare where each neighbor node is in relation to current node
			leftNode = nodes[currentNode.rowNumber] [currentNode.colNumber - 1];
			rightNode = nodes[currentNode.rowNumber] [currentNode.colNumber + 1];
			upNode = nodes[currentNode.rowNumber - 1] [currentNode.colNumber];
			downNode = nodes[currentNode.rowNumber + 1] [currentNode.colNumber];
			
			//Check neighboring Nodes to see if path continues, or if Goal node
			//If so, then add to queue
			if ((leftNode.character != 'X') && (leftNode.hasBeenVisited() ==  false)){
				queue.add(leftNode);
				leftNode.previous = currentNode;
				leftNode.setVisited(true);
			}
			
			if ((rightNode.character != 'X') && (rightNode.hasBeenVisited() ==  false)){
				queue.add(rightNode);
				rightNode.previous = currentNode;
				rightNode.setVisited(true);
			}
			
			if ((upNode.character != 'X') && (upNode.hasBeenVisited() ==  false)){
				queue.add(upNode);
				upNode.previous = currentNode;
				upNode.setVisited(true);
			}
			
			if ((downNode.character != 'X') && (downNode.hasBeenVisited() ==  false)){
				queue.add(downNode);
				downNode.previous = currentNode;
				downNode.setVisited(true);
			}
		}
				
		//Writes solution to maze (shortest path) using '.' to represent nodes on this path
		try(FileWriter fw = new FileWriter(outputFile)){
			fw.write(rows + " " + cols + "\n");
			for(int row = 0; row < rows; row++){
				for(int col = 0; col < cols; col++){
					fw.write(nodes[row][col].character);
				}
				
				fw.write("\n");
			}
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts at goal node, and traverses backwards to start, using each node's previous 
	 * node information.  While traversing back, each node on the path is placed in the 
	 * shortestPath array, and pathLength is incremented.
	 * @param goal - The Goal node we have found the path to
	 */
	public static void shortestPath(Node goal){
		Node currentNode = goal.previous;
		
		while (currentNode.previous != null){
			shortestPath.add(currentNode);
			currentNode.character = '.';
			currentNode = currentNode.previous;
		}
	}
	
	/**
	 * Counts the number of '.' in an input file.  Assumes that first line in file
	 * is the number of rows, a space, and then the number of columns.
	 * @param filename - file to be scanned through
	 * @return - the number of dots on a path (excludes Start and Goal nodes)
	 * @throws FileNotFoundException - if file does not exist
	 */
	public static int pathCount(String filename) throws FileNotFoundException{
		int rowNumber = 0;
		int colNumber = 0;
		int pathCount = 0;
		
		try{
			FileReader file = new FileReader(filename);
			Scanner scan = new Scanner(file);
			//Extract number of rows, and number of columns from first line of file
			int rows = scan.nextInt();
			int cols = scan.nextInt();
			scan.nextLine();
			
			//Scan through each row in the file
			while (rowNumber < rows){
				
				String line = scan.nextLine();
				
				@SuppressWarnings("resource")
				Scanner lineScan = new Scanner(line).useDelimiter("(?<=.)");
				colNumber = 0;
				//Scan through each character in each row
				while (colNumber < cols){
					Character nextChar = lineScan.next().charAt(0);
					if(nextChar == '.'){
						pathCount++;
					}
					++colNumber;
				}
				++rowNumber;
				lineScan.close();
			}
			scan.close();
		} 
		catch(FileNotFoundException e){
			throw new FileNotFoundException();
		}
		
		return pathCount;
	}

}
