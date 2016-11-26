//William Frank
//Elliot Lee
package assignment09;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class PathFinderTest {

	@Test
	public void bigMaze() throws IOException {
		PathFinder.solveMaze("bigMaze.txt", "ourBigMazeSol.txt");
		
		assertEquals(dotCounter("bigMazeSol.txt"), dotCounter("ourBigMazeSol.txt"));
	}
	
	@Test
	public void classic() throws IOException {
		PathFinder.solveMaze("classic.txt", "ourclassicSol.txt");
		
		assertEquals(dotCounter("classicSol.txt"), dotCounter("ourclassicSol.txt"));
	}
	
	@Test
	public void demoMaze() throws IOException {
		PathFinder.solveMaze("demoMaze.txt", "ourdemoMazeSol.txt");
		
		assertEquals(dotCounter("demoMazeSol.txt"), dotCounter("ourdemoMazeSol.txt"));
	}
	
	@Test
	public void mediumMaze() throws IOException {
		PathFinder.solveMaze("mediumMaze.txt", "ourmediumMazeSol.txt");
		
		assertEquals(dotCounter("mediumMazeSol.txt"), dotCounter("ourmediumMazeSol.txt"));
	}
	
	@Test
	public void randomMaze() throws IOException {
		PathFinder.solveMaze("randomMaze.txt", "ourrandomMazeSol.txt");
		
		assertEquals(dotCounter("randomMazeSol.txt"), dotCounter("ourrandomMazeSol.txt"));
	}
	
	@Test
	public void stright() throws IOException {
		PathFinder.solveMaze("straight.txt", "ourstraightSol.txt");
		
		assertEquals(dotCounter("straightSol.txt"), dotCounter("ourstraightSol.txt"));
	}
	
	@Test
	public void tinyMaze() throws IOException {
		PathFinder.solveMaze("tinyMaze.txt", "ourtinyMazeSol.txt");
		
		assertEquals(dotCounter("tinyMazeSol.txt"), dotCounter("ourtinyMazeSol.txt"));
	}
	
	@Test
	public void tinyOpen() throws IOException {
		PathFinder.solveMaze("tinyOpen.txt", "ourtinyOpenSol.txt");
		
		assertEquals(dotCounter("tinyOpenSol.txt"), dotCounter("ourtinyOpenSol.txt"));
	}
	
	@Test
	public void turn() throws IOException {
		PathFinder.solveMaze("turn.txt", "ourturnSol.txt");
		
		assertEquals(dotCounter("turnSol.txt"), dotCounter("ourturnSol.txt"));
	}
	
	@Test
	public void unsolvable() throws IOException {
		PathFinder.solveMaze("unsolvable.txt", "ourunsolvableSol.txt");
		
		assertEquals(dotCounter("unsolvableSol.txt"), dotCounter("ourunsolvableSol.txt"));
	}
	
	@Test
	public void biggestMaze() throws IOException {
		double start = System.nanoTime();
		
		PathFinder.solveMaze("100Maze.txt", "100MazeSol.txt");
				
		double time = System.nanoTime() - start;
		double tenSeconds = 1000*1000*1000;
		
		System.out.println(time);
		
		assertTrue(time < tenSeconds);
		
	}
	
	public int dotCounter(String maze) throws FileNotFoundException
	{
		int dots = 0;
		String temp;
		Scanner sc = new Scanner(new File(maze));
		sc.useDelimiter("");
		
		while (sc.hasNext())
		{
			temp = sc.next();
			if (temp.equals("."))
			{
				dots++;
			}
		}
		
		sc.close();
		return dots;
	}

}
