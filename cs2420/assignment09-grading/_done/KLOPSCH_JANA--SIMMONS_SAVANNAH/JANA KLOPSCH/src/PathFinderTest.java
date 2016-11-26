package assignment09;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for PathFinder class.  Tests pathCount method, to ensure length 
 * of solution path is equal expected length, and to length of the solution 
 * maze path. (Ensure that solveMaze method is finding shortest path)
 * @author Jana Klopsch (u0854469) && Savannah Simmons (u1086770)
 * Last Modified 11-2-16
 */
public class PathFinderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	//Tests for pathCount
	@Test
	public void testPathCountBasic() {
		try {
			assertTrue(PathFinder.pathCount("tinyOpenSol.txt") == 3);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPathCountNoPath() {
		try {
			assertTrue(PathFinder.pathCount("tinyWall.txt") == 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPathCountNoSolutionPath() {
		try {
			assertTrue(PathFinder.pathCount("mediumNoSolMaze.txt") == 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testPathCountNoGoalNode() {
		try {
			assertTrue(PathFinder.pathCount("tinyBox.txt") == 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//Tests for solveMaze solutions
	@Test
	public void testSolveTinyOpen() {
		PathFinder.solveMaze("tinyOpen.txt", "testFile.txt");
		try {
			assertTrue(PathFinder.pathCount("tinyOpenSol.txt") == PathFinder.pathCount("testFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSolveBigMaze() {
		PathFinder.solveMaze("bigMaze.txt", "testFile.txt");
		try {
			assertTrue(PathFinder.pathCount("bigMazeSol.txt") == PathFinder.pathCount("testFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUnsolvableMaze() {
		PathFinder.solveMaze("unsolvable.txt", "testFile.txt");
		try {
			assertTrue(PathFinder.pathCount("unsolvableSol.txt") == PathFinder.pathCount("testFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSolveTinyBoxMaze() {
		PathFinder.solveMaze("tinyBox.txt", "testFile.txt");
		try {
			assertTrue(PathFinder.pathCount("tinyBoxSol.txt") == PathFinder.pathCount("testFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUnreachableGoalMaze() {
		PathFinder.solveMaze("mediumNoSolMaze.txt", "testFile.txt");
		try {
			assertTrue(PathFinder.pathCount("mediumNoSolSolution.txt") == PathFinder.pathCount("testFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNoWallsMaze() {
		PathFinder.solveMaze("noWalls.txt", "testFile.txt");
		try {
			assertTrue(PathFinder.pathCount("noWallsSol.txt") == PathFinder.pathCount("testFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testZigZagMaze() {
		PathFinder.solveMaze("zigZag.txt", "testFile.txt");
		try {
			assertTrue(PathFinder.pathCount("zigZagSol.txt") == PathFinder.pathCount("testFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
