package assignment09;

import java.io.File;

/**
 * This class allows us to find the path to the files indicated in our solveMaze
 * method which takes in string names of an inputFile and outputFile and solves
 * the inputFile for the most efficient path and writes that path to the 
 * outputFile name indicated and places that in the same folder the inputFile 
 * came from.
 * 
 * @authors Brian Park u0735732, Nathan Steadman u0738818
 * 
 */
public class PathFinder {
	
	/**
	 * This method takes in string names of an inputFile and outputFile and solves
     * the inputFile for the most efficient path and writes that path to the 
     * outputFile name indicated and places that in the same folder the inputFile 
     * came from.
	 */ 
	public static void solveMaze(String inputFile, String outputFile){
		File before = new File(inputFile);
		PacmanGraph solver = new PacmanGraph(before, outputFile);
	}


}
