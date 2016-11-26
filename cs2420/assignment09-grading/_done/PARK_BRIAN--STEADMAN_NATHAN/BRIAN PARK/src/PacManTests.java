package assignment09;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

/**
 * This class tests our solutions against the solutions given to us by scanning 
 * each of the solutions and counting the number of dots(traversed nodes)in each 
 * of the files and asserting that they are the same number of dots in each file 
 * and that a solution exists. If a solution does not exist we assert that no 
 * dots (path) were printed to the outputfile.
 * 
 * @authors Brian Park u0735732, Nathan Steadman u0738818
 * 
 */
 
public class PacManTests {
	Scanner scan1;
	Scanner scan2;
	String actual= "";
	String expected = "";
	File f1;
	File f2;
	PathFinder finder;
	int count1;
	int count2;
	@Before
	public void setup(){
		count1=0;
		count2=0;
	}
	@Test
	public void bigMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("bigMaze.txt", "bigMazeOutput.txt");
			File f1 = new File("bigMazeSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("bigMazeOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
	}
		
	
	@Test
	public void classicMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("classic.txt", "classicOutput.txt");
			File f1 = new File("classicSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("classicOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
	}
	@Test
	public void demoMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("demoMaze.txt", "demoMazeOutput.txt");
			File f1 = new File("demoMazeSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("demoMazeOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
				fail();
				
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
	}
	@Test
	public void mediumMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("mediumMaze.txt", "mediumMazeOutput.txt");
			File f1 = new File("mediumMazeSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("mediumMazeOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
				fail();
				
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
	}
	@Test
	public void randomMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("randomMaze.txt", "randomMazeOutput.txt");
			File f1 = new File("randomMazeSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("randomMazeOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
				fail();
				
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
	}
	@Test
	public void straightTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("straight.txt", "straightOutput.txt");
			File f1 = new File("straightSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("straightOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
				fail();
				
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
		
	}
	@Test
	public void tinyMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("tinyMaze.txt", "tinyMazeOutput.txt");
			File f1 = new File("tinyMazeSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("tinyMazeOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
				fail();
				
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
	}
	@Test
	public void tinyOpenMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("tinyOpen.txt", "tinyOpenOutput.txt");
			File f1 = new File("tinyOpenSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("tinyOpenOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
				fail();
				
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
	}
	@Test
	public void turnMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("turn.txt", "turnOutput.txt");
			File f1 = new File("turnSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("turnOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
				fail();
				
		}
		assertFalse(count1 == 0);
		assertEquals(count1, count2);
	}
	@Test
	public void unsolvableMazeTest() {
		try{
			finder = new PathFinder();
			finder.solveMaze("unsolvable.txt", "unsolvableOutput.txt");
			File f1 = new File("unsolvableSol.txt");
			scan1 = new Scanner(f1);
			while(scan1.hasNext()){
				if(scan1.next().contains(".")){
					count1 ++;
				}
			}
			File f2 = new File("unsolvableOutput.txt");
			scan2 = new Scanner(f2);
			while(scan2.hasNext()){
				if(scan2.next().contains(".")){
					count2 ++;
				}
			}
		}
		catch(IOException e){
				System.err.println("error reading file");
				fail();
				
		}
		assertTrue(count1 == 0);
		assertEquals(count1, count2);
	}
}
