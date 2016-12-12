package assignment11;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class PriorityQueueTests 
{
	PriorityQueue<Integer> myIntegerQueue;
	PriorityQueue<Integer> myIntegerQueue2;
	PriorityQueue<String> myStringQueue;

	@Before
	public void setUp() throws Exception 
	{
		myIntegerQueue = new PriorityQueue<Integer>();
		myIntegerQueue2 = new PriorityQueue<Integer>();
		myStringQueue = new PriorityQueue<String>();
		
		myIntegerQueue2.add(3);
		myIntegerQueue2.add(14);
		myIntegerQueue2.add(16);
		myIntegerQueue2.add(31);
		myIntegerQueue2.add(22);
		myIntegerQueue2.add(17);
		myIntegerQueue2.add(92);
		myIntegerQueue2.add(100);
		myIntegerQueue2.add(56);
		myIntegerQueue2.add(45);
		myIntegerQueue2.add(43);
	}

	@Test
	public void addRoot() 
	{
		myIntegerQueue.add(3);
		assertTrue(myIntegerQueue.findMin() == 3);
		assertEquals(myIntegerQueue.size(), 1);
	}
	
	@Test
	public void testingAddFunction()
	{
		myIntegerQueue.add(23);
		myIntegerQueue.add(45);
		myIntegerQueue.add(19);
		myIntegerQueue.add(11);
		myIntegerQueue.add(3);
		myIntegerQueue.add(10);
		myIntegerQueue.add(5);
		myIntegerQueue.add(1);
		assertTrue(myIntegerQueue.findMin() == 1);
		assertEquals(myIntegerQueue.size(), 8);
		myIntegerQueue.generateDotFile("myDotFile.dot");
	}
	
	@Test
	public void testingStringAdd()
	{
		myStringQueue.add("Will");
		myStringQueue.add("You");
		myStringQueue.add("Marry");
		myStringQueue.add("Me");
		assertEquals(myStringQueue.size(), 4);
		myStringQueue.generateDotFile("String.dot");
	}
	
	@Test
	public void addPastCapacityTest()
	{
		myIntegerQueue2.add(87);
		myIntegerQueue2.add(77);
		myIntegerQueue2.add(2);
		myIntegerQueue2.add(1);
		myIntegerQueue2.add(0);
		myIntegerQueue2.add(4);
		myIntegerQueue2.add(5);
		myIntegerQueue2.add(6);
		myIntegerQueue2.add(7);
		myIntegerQueue2.add(8);
		myIntegerQueue2.add(9);
		assertEquals(myIntegerQueue2.size(), 22);
		assertTrue(myIntegerQueue2.findMin() == 0);
		myIntegerQueue2.generateDotFile("LargeGraph.dot");
	}
	
	@Test
	public void deleteMinTest()
	{
		myIntegerQueue2.deleteMin();
		assertEquals(myIntegerQueue2.size(), 10);
		assertTrue(myIntegerQueue2.findMin() == 14);
		myIntegerQueue2.generateDotFile("removeDot.dot");
	}
	
	@Test (expected = NoSuchElementException.class)
	public void deleteMinEmptyTest()
	{
		myIntegerQueue.deleteMin();
	}
	
	@Test
	public void deleteMinOneElementTest()
	{
		myIntegerQueue.add(7);
		assertTrue(myIntegerQueue.size() == 1);
		myIntegerQueue.deleteMin();
		assertTrue(myIntegerQueue.size() == 0);
	}
	
	@Test
	public void deleteMinTwoElementTest()
	{
		myIntegerQueue.add(7);
		myIntegerQueue.add(3);
		myIntegerQueue.deleteMin();
		assertEquals(myIntegerQueue.size(), 1);
		assertTrue(myIntegerQueue.findMin() == 7);
	}
	
	@Test (expected = NoSuchElementException.class)
	public void findMinEmptyTest()
	{
		myIntegerQueue.findMin();
	}

}
