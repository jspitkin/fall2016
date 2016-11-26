package assignment09;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Graph class
 * @author Jana Klopsch (u0854469) && Savannah Simmons (u1086770)
 * Last Modified 11-2-16
 */
public class GraphTest {

	Graph classicMaze;
	Graph wallMaze;
	Graph testMaze;

	
	@Before
	public void setUp() throws Exception {
		classicMaze = new Graph("classic.txt");
		wallMaze = new Graph("tinyWall.txt");
				
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test (expected = FileNotFoundException.class)
	public void testGraphFileNotFound() throws FileNotFoundException {
		 testMaze = new Graph("DoesNotExist.txt");
	}
	
	@Test
	public void testRowsAndColsBasic() {
		assertTrue(classicMaze.rows == 11);
		assertTrue(classicMaze.cols == 20);
	}
	
	@Test
	public void testRowsAndColsEmpty() {
		assertTrue(wallMaze.rows == 2);
		assertTrue(wallMaze.cols == 2);
	}
	
	@Test
	public void testStartandGoalNodesBasic() {
		assertTrue(classicMaze.getStart().character == 'S');
		assertTrue(classicMaze.getGoal().character == 'G');
	}
	
	@Test
	public void testStartNodesWallMaze() {
		assertNull(wallMaze.getStart());
	}
	
	@Test
	public void testGoalNodeWallMaze() {
		assertNull(wallMaze.getGoal());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
