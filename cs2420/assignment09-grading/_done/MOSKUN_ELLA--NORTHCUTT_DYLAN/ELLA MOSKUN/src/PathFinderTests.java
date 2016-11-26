package assignment09;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Scanner;

import org.junit.AfterClass;
import org.junit.Test;

/**
 * Class to test the PathFinder class
 *  
 * @author Ella Moskun     u0897252
 * @author Dylan Northcutt u1055102
 */
public class PathFinderTests {

	@AfterClass
	public static void cleanup() throws Exception {
		// Added
		new File("bigMaze2Output.txt")  .delete();
		new File("biggerMazeOutput.txt").delete();
		
		// Included
		new File("bigMazeOutput.txt")   .delete();
		new File("classicOutput.txt")   .delete();
		new File("demoMazeOutput.txt")  .delete();
		new File("mediumMazeOutput.txt").delete();
		new File("randomMazeOutput.txt").delete();
		new File("straightOutput.txt")  .delete();
		new File("tinyMazeOutput.txt")  .delete();
		new File("tinyOpenOutput.txt")  .delete();
		new File("turnOutput.txt")      .delete();
		new File("unsolvableOutput.txt").delete();
	}
	
	/*
	 * Added
	 */
	
	@Test
	public void testBigMaze2() {
		String input    = "bigMaze2.txt";
		String output   = "bigMaze2Output.txt";
		String expected = "bigMaze2Sol.txt";
		
		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}
	
	@Test
	public void testBiggerMaze() {
		String input    = "biggerMaze.txt";
		String output   = "biggerMazeOutput.txt";
		String expected = "biggerMazeSol.txt";
				
		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	/*
	 * Included
	 */
	
	@Test
	public void testBigMaze() {
		String input    = "bigMaze.txt";
		String output   = "bigMazeOutput.txt";
		String expected = "bigMazeSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testClassic() {
		String input    = "classic.txt";
		String output   = "classicOutput.txt";
		String expected = "classicSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testDemoMaze() {
		String input    = "demoMaze.txt";
		String output   = "demoMazeOutput.txt";
		String expected = "demoMazeSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testMediumMaze() {
		String input    = "mediumMaze.txt";
		String output   = "mediumMazeOutput.txt";
		String expected = "mediumMazeSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testRandomMaze() {
		String input    = "randomMaze.txt";
		String output   = "randomMazeOutput.txt";
		String expected = "randomMazeSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testStraight() {
		String input    = "straight.txt";
		String output   = "straightOutput.txt";
		String expected = "straightSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testTinyMaze() {
		String input    = "tinyMaze.txt";
		String output   = "tinyMazeOutput.txt";
		String expected = "tinyMazeSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testTinyOpen() {
		String input    = "tinyOpen.txt";
		String output   = "tinyOpenOutput.txt";
		String expected = "tinyOpenSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testTurn() {
		String input    = "turn.txt";
		String output   = "turnOutput.txt";
		String expected = "turnSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	@Test
	public void testUnsolvable() {
		String input    = "unsolvable.txt";
		String output   = "unsolvableOutput.txt";
		String expected = "unsolvableSol.txt";

		PathFinder.solveMaze(input, output);
		assertMazeSolved(expected, output);
	}

	/**
	 * Helper method to test that a maze is solved. Assertions are made to
	 * verify that the maze remains intact and that a path of the expected
	 * length is marked.
	 * 
	 * @param expectedPath
	 *            the path to the known solution
	 * @param rightPath
	 *            the path to the output solution
	 * @throws Exception
	 *             when something goes wrong
	 */
	private static void assertMazeSolved(String expectedPath, String rightPath) {
		try (	Scanner left  = new Scanner(new File(expectedPath));
				Scanner right = new Scanner(new File(rightPath))) {
			int leftCount = 0;
			int rightCount = 0;

			while (left.hasNextLine() && right.hasNextLine()) {
				String leftLine  = left .nextLine();
				String rightLine = right.nextLine();

				// Count periods
				leftCount  += leftLine.length()  - leftLine .replaceAll("\\.", "").length();
				rightCount += rightLine.length() - rightLine.replaceAll("\\.", "").length();

				// The lines should be identical except for periods in place of
				// spaces, suggesting that the integrity of the maze was not
				// compromised.
				assertEquals(leftLine.replace('.', ' '), rightLine.replace('.', ' '));
			}

			// The files should have the same length, indicating that this
			// aspect of integrity was not compromised.
			assertFalse(left .hasNextLine());
			assertFalse(right.hasNextLine());

			// The files should contain an equal number of periods, indicating
			// that a shortest path was found.
			assertEquals(leftCount, rightCount);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
