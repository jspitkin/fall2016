//William Frank
//Elliot Lee
package assignment09;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class PathFinder {
	
	private static LinkedList<GraphNode> queue = new LinkedList<GraphNode>();
	
	/**
	 * Solves the maze stored in inputFile
	 * @param inputFile the location of the maze
	 * @param outputFile the desired location of the solution
	 * @throws IOException if file note found, or other errors
	 */
	public static void solveMaze(String inputFile, String outputFile) throws IOException
	{
		File file = new File(inputFile);
		Scanner scan = new Scanner(file);
		int rows = Integer.parseInt(scan.next());
		int cols = Integer.parseInt(scan.next());
		scan.useDelimiter("");
		scan.next();
		
		char[][] maze = new char[rows][cols];
		char[] column = new char[cols];
		int rowIterator = 0;
		int colIterator = 0;
		//Scans in the maze from file
		while (scan.hasNext())
		{
			String next = scan.next();
			if (next.equals("\n"))
			{
				maze[rowIterator] = column;
				column = new char[cols];
				colIterator = 0;
				rowIterator++;
				continue;
			}
			//Builds a 2D array of characters to later print the solution
			else if (next.equals("X"))
			{
				column[colIterator] = 'X';
			}
			else if (next.equals("S"))
			{
				column[colIterator] = 'S';
			}
			else if (next.equals("G"))
			{
				column[colIterator] = 'G';
			}
			else if (next.equals(" "))
			{
				column[colIterator] = ' ';
			}
			colIterator++;
		}
		scan.close();
		
		Graph graph = new Graph(maze);
		
		
		GraphNode start = Graph.start;
		GraphNode goal = Graph.goal;
		ArrayList<GraphNode> visits = new ArrayList<GraphNode>();
		
		queue.add(start);

		//runs a breadth first search
		while (queue.size() > 0)
		{
			
			GraphNode current = queue.removeFirst();

			for (Iterator<GraphNode> nodes = current.adjacentNodes.iterator(); nodes.hasNext();) {
				GraphNode node = nodes.next();
				
				if (!visits.contains(node))
				{
					node.cameFrom = current;
					if (node.data == 'G')
					{
						goal = node;
						queue.clear();
						break;
					}
					else if (node.data == ' ')
					{
						queue.addLast(node);
					}
					
					visits.add(node);
				}

			}

		}	
		
		//retraces the path from goal and prints it to the 2D array
		if (goal.cameFrom != null)
		{
			goal = goal.cameFrom;
			while (goal.data != 'S')
			{
				maze[goal.row][goal.col] = '.';
				goal = goal.cameFrom;
			}
		}
		
		//prints the solution to file
		try(PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
			output.println(graph.colLength + " " + graph.rowLength);

			for (int row = 0; row < maze.length; row++) {
				for (int col = 0; col < maze[0].length; col++) {
					output.print(maze[row][col]);
				}
				output.println("");
			}
		}
		
	}

}
