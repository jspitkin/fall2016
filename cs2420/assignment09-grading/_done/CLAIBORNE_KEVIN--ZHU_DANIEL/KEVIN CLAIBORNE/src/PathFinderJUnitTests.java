package assignment09;

import static org.junit.Assert.*;

import org.junit.Test;

public class PathFinderJUnitTests {

	@Test
	public void turn() {
		String startingMaze = "turn";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void bigMaze() {
		String startingMaze = "bigMaze";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void classic() {
		String startingMaze = "classic";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void demoMaze() {
		String startingMaze = "demoMaze";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void mediumMaze() {
		String startingMaze = "mediumMaze";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void randomMaze() {
		String startingMaze = "randomMaze";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void straight() {
		String startingMaze = "straight";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void tinyMaze() {
		String startingMaze = "tinyMaze";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void tinyOpen() {
		String startingMaze = "tinyOpen";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
	}
	
	@Test
	public void unsolvable() {
		String startingMaze = "unsolvable";
		String startFile = "mazes/unsolved/" + startingMaze + ".txt";
		String endFile = "mazes/ourresults/" + startingMaze + "SolOurs.txt";
		String expectedFile = "mazes/providedresults/" + startingMaze + "Sol.txt";
		
		PathFinder.solveMaze(startFile, endFile);
		assertEquals(PathFinder.countDots(endFile)
				, PathFinder.countDots(expectedFile));
		
		assertEquals(PathFinder.displayGraph(startFile), PathFinder.displayGraph(endFile));
	}
	

}
