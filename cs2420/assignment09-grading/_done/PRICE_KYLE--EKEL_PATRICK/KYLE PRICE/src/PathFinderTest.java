package assignment09;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * pathFinderTest - runs several rigorous JUnit tests for the class "PathFinder". 
 * @author Kyle Price and Patrick Ekel
 * 11/2/2016
 */
public class PathFinderTest {
	BufferedReader result;
	BufferedReader trueResult;
	String inputFile;
	String outputFile;
	String solFile;
	int count;
	int trueCount;
	int height;
	int width;
	int trueHeight;
	int trueWidth;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTinyMaze() throws IOException {

		PathFinder.solveMaze("tinyMaze.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("tinyMazeSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testBigMaze() throws IOException {
		PathFinder.solveMaze("bigMaze.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("bigMazeSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testClassic() throws IOException {
		PathFinder.solveMaze("classic.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("classicSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testDemoMaze() throws IOException {
		PathFinder.solveMaze("demoMaze.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("demoMazeSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testMediumMaze() throws IOException {
		PathFinder.solveMaze("mediumMaze.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("mediumMazeSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testRandomMaze() throws IOException {
		PathFinder.solveMaze("randomMaze.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("randomMazeSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testStraight() throws IOException {
		PathFinder.solveMaze("straight.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("straightSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testTurn() throws IOException {
		PathFinder.solveMaze("turn.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("turnSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testUnsolvable() throws IOException {
		PathFinder.solveMaze("unsolvable.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("unsolvableSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}

	@Test
	public void testTinyOpen() throws IOException {
		PathFinder.solveMaze("tinyOpen.txt", "tinyMazeOutput.txt");

		result = new BufferedReader(new FileReader("tinyMazeOutput.txt"));
		trueResult = new BufferedReader(new FileReader("tinyOpenSol.txt"));

		String[] dimensions = result.readLine().split(" ");
		height = Integer.parseInt(dimensions[0]);
		width = Integer.parseInt(dimensions[1]);
		count = 0;

		String[] trueDimensions = trueResult.readLine().split(" ");
		trueHeight = Integer.parseInt(trueDimensions[0]);
		trueWidth = Integer.parseInt(trueDimensions[1]);
		trueCount = 0;

		char[] line;
		char[] solLine;
		for (int row = 0; row < height; row++) {
			line = result.readLine().toCharArray();
			solLine = trueResult.readLine().toCharArray();
			for (int column = 0; column < width; column++) {
				if (line[column] == '.') {
					count++;
				}
				if (solLine[column] == '.') {
					trueCount++;
				}
			}
		}
		assertEquals(count, trueCount);
	}
}