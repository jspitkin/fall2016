package assignment09;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class PathFinderTester {
	
	private static int countDots(String inputFile) throws IOException {
		BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
		String[] dimensions = inputReader.readLine().split(" ");
		int height = Integer.parseInt(dimensions[0]);
		int width = Integer.parseInt(dimensions[1]);

		int numDots = 0;
		
		for (int row = 0; row < height; row++) {
			char[] charArray = inputReader.readLine().toCharArray();
			for (int column = 0; column < width; column++) {

				if (charArray[column] == '.') {
					numDots++;
				}
			}

		}
		inputReader.close();
		
		return numDots;
	}

	@Before
	public void setUp() throws Exception {
		PathFinder.solveMaze("tinyMaze.txt", "tinyMazeOut.txt");
		PathFinder.solveMaze("unsolvable.txt", "UnsolvableOut.txt");
		PathFinder.solveMaze("bigMaze.txt", "bigMazeOut.txt");
		PathFinder.solveMaze("classic.txt", "classicOut.txt");
		PathFinder.solveMaze("demoMaze.txt", "demoMazeOut.txt");
		PathFinder.solveMaze("randomMaze.txt", "randomMazeOut.txt");
		PathFinder.solveMaze("straight.txt", "straightOut.txt");
		PathFinder.solveMaze("tinyOpen.txt", "tinyOpenOut.txt");
		PathFinder.solveMaze("turn.txt", "turnOut.txt");
		PathFinder.solveMaze("adjacent.txt", "adjacentSolved.txt");
		PathFinder.solveMaze("mediumMaze.txt", "mediumMazeOut.txt");

	}

	@Test
	public void adjacentStartAndGoalTest() throws IOException {
		PathFinder.solveMaze("adjacent.txt", "adjacentSolved.txt");
		assertEquals(0, countDots("adjacentSolved.txt"));
	}

	@Test
	public void bigMazeTest() throws IOException {
		assertEquals(countDots("bigMazeOut.txt"), countDots("bigMazeSol.txt"));
	}
	@Test
	public void demoMazeTest() throws IOException {
		assertEquals(countDots("demoMazeOut.txt"), countDots("demoMazeSol.txt"));
	}
	@Test
	public void mediumMazeTest() throws IOException {
		assertEquals(countDots("mediumMazeOut.txt"), countDots("mediumMazeSol.txt"));
	}
	@Test
	public void randomMazeTest() throws IOException {
		assertEquals(countDots("randomMazeOut.txt"), countDots("randomMazeSol.txt"));
	}
	@Test
	public void straightTest() throws IOException {
		assertEquals(countDots("straightOut.txt"), countDots("straightSol.txt"));
	}
	@Test
	public void tinyMazeTest() throws IOException {
		assertEquals(countDots("tinyMazeOut.txt"), countDots("tinyMazeSol.txt"));
	}
	@Test
	public void TinyOpenTest() throws IOException {
		assertEquals(countDots("tinyOpenOut.txt"), countDots("tinyOpenSol.txt"));
	}

	@Test
	public void turnTest() throws IOException {
		assertEquals(countDots("turnOut.txt"), countDots("turnSol.txt"));
	}

	@Test
	public void UnsolvableTest() throws IOException {
		assertEquals(countDots("unsolvableOut.txt"), countDots("unsolvableSol.txt"));
	}

}
