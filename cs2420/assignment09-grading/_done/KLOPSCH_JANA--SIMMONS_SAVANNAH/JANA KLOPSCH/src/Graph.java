package assignment09;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Builds a graph of Nodes from an input file, backed by a 2D array of Nodes.
 * @author Jana Klopsch (u0854469) && Savannah Simmons (u1086770)
 * Last Modified 11-2-16
 */
public class Graph {

	private Node nodes[][];
	private Node start;
	private Node goal;
	public int rows;
	public int cols;
	
	/**
	 * Constructor for Graph
	 * Creates a 2D array to hold values of the input file's maze.  Assumes that
	 * first line in input file contains dimensions of maze (rows, then columns)
	 * separated by a space, then the maze is underneath.  Also assumes maze is 
	 * surrounded by 'X' nodes, or walls, and that there is a 'S' and 'G' 
	 * @param filename - input file with maze
	 * @throws FileNotFoundException - if input file does not exist.
	 */
	public Graph(String filename) throws FileNotFoundException{
		
		int rowNumber = 0;
		int colNumber;

		try{
			FileReader file = new FileReader(filename);
			Scanner scan = new Scanner(file);
			//Extract number of rows, and number of columns from first line of file
			rows = scan.nextInt();
			cols = scan.nextInt();
			scan.nextLine();
			//Create 2D array of the appropriate size
			nodes = new Node[rows][cols];
			
			//Scan through each row in the file
			while (rowNumber < rows){
				
				String line = scan.nextLine();
				
				@SuppressWarnings("resource")
				Scanner lineScan = new Scanner(line).useDelimiter("(?<=.)");
				
				colNumber = 0;
			
				while (colNumber < cols){
					Character nextChar = lineScan.next().charAt(0);
					
					nodes[rowNumber][colNumber] = new Node(rowNumber, colNumber); //Create a node at each spot from file
					nodes[rowNumber][colNumber].character = nextChar; //Node holds value of char in file
					
					switch (nextChar){
					case 'S' : 	start = nodes[rowNumber][colNumber]; //Keep track of start node
								break;
					case 'G' : 	goal = nodes[rowNumber][colNumber]; //Keep track of goal node
								break;
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
	}
	
	/**
	 * Getter method for 2D array of nodes in graph
	 * @return - 2D array of nodes
	 */
	public Node[][] getNodes(){
		return nodes;
	}
	
	/**
	 * Getter method for start node - if not initialized in Graph constructor, 
	 * start node is null
	 * @return - the start node
	 */
	public Node getStart(){
		return start;
	}
	
	/**
	 * Getter method for goal node - if not initialized in Graph constructor,
	 * goal node is null
	 * @return - the goal node
	 */
	public Node getGoal(){
		return goal;
	}
	
}
