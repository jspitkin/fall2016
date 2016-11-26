package assignment09;

import java.io.IOException;

/**
 * Simple tester class for testing input mazes, and solution output files
 * @author Jana Klopsch (u0854469) && Savannah Simmons (u1086770)
 * Last Modified 11-2-16
 *
 */
public class PacmanTester {
	
	public static void main(String[] args) throws IOException{
		PathFinder.solveMaze("randomMaze.txt", "testFile.txt");
	}

}
