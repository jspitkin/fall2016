package assignment09;

import static org.junit.Assert.*;

import org.junit.*;
import java.io.*;
  

/**
 * 
 * @author Adrian Bollerslev
 * u1115021
 * Joshua Shipley
 * u919708
 *
 *This class tests the PathFinder class with numerous "mazes"
 *the guidelines for these mazes are specified in the
 *PathFinder class
 */
public class TestPathFinder {

	/**
	 * This method counts the number of dots in an input file
	 * this method guarantees that the solution found is one of the shortest
	 * possible paths
	 * @param filen the input file
	 * @return The number of dots found within the input file
	 */
	public static int countDots(String filen){
		int count = 0;
		try{
			BufferedReader input = new BufferedReader(new FileReader(filen));
			String[] dimensions = input.readLine().split(" ");
	        for(int r = 0; r < Integer.parseInt(dimensions[0]); r++){
	              String line = input.readLine();
	              count += line.length() - line.replace(".", "").length();
	           }
	           //close the file
	           input.close();
		}
		catch(FileNotFoundException e){
			System.out.println("Input file not found.");
		}
		catch (IOException e)
        {
            System.out.println("Error reading from file.");
        }
		return count;
	}
	@Test
	public void testbig(){
		String filen = "src/assignment09/bigMaze";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	@Test
	public void testclassic(){
		String filen = "src/assignment09/classic";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	@Test
	public void testdemo(){
		String filen = "src/assignment09/demoMaze";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	@Test
	public void testmedium(){
		String filen = "src/assignment09/mediumMaze";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	@Test
	public void testrandom(){
		String filen = "src/assignment09/randomMaze";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	@Test
	public void teststraight(){
		String filen = "src/assignment09/straight";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	
	@Test
	public void testtinyMaze(){
		String filen = "src/assignment09/tinyMaze";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	@Test
	public void testtinyOpen(){
		String filen = "src/assignment09/tinyOpen";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	@Test
	public void testturn(){
		String filen = "src/assignment09/turn";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
	@Test
	public void testunsol(){
		String filen = "src/assignment09/unsolvable";
		PathFinder.solveMaze(filen+".txt", filen+"Output.txt");
		assertEquals(countDots(filen+"Output.txt"),countDots(filen+"Sol.txt"));
	}
}
