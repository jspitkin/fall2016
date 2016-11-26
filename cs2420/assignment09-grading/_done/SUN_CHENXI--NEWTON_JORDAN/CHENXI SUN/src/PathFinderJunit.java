package assignment09;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PathFinderJunit {
	
	MyQueue<String> abc=new MyQueue<String>();
	@Test
	public void enqueueTest()  {
		abc.enqueue("taylor");
		assertEquals("taylor", abc.dequeue());
		abc.enqueue("taylor");
		abc.enqueue("dakota");
		abc.enqueue("kylie");
		assertEquals("taylor", abc.dequeue());
		assertEquals("dakota", abc.dequeue());
		assertEquals("kylie", abc.dequeue());
	}
	
	
	@Test
	public void dequeueTest()  {
		abc.enqueue("taylor");
		assertEquals("taylor", abc.dequeue());
		abc.enqueue("taylor");
		abc.enqueue("dakota");
		abc.enqueue("kylie");
		assertEquals("taylor", abc.dequeue());
		assertEquals("dakota", abc.dequeue());
		assertEquals("kylie", abc.dequeue());
		
		}
	
	PathFinder abc2=new PathFinder();
	
	
	@Test
	public void graphTest()  {
	abc2.solveMaze("mediumMaze.txt", "mysolution.txt");
	assertEquals(67, abc2.shortestpath.size());
	abc2.solveMaze("tinyOpen.txt", "mysolution.txt");
	assertEquals(3, abc2.shortestpath.size());	
	abc2.solveMaze("turn.txt", "mysolution.txt");	
	assertEquals(55, abc2.shortestpath.size());		
	}
	
	
	
	
}
