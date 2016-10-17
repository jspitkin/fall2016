package assignment06;

import java.util.ArrayList;

/**
 * Timing test class for the DoublyLinkedList class.
 *
 * @author Calvin Kling
 *
 */
public class TimingTests {

	public static void main(String[] args) 
	{
//		addFirstTest();
//		addFirstArrayListTest();
//		GetTest();
//		getArrayListTest();
//		removeTest();
		removeArrayListTest();
	}

	
	public static void addFirstTest() 
	{		
			// Do the experiment multiple times, and average out the results
			int IterCount = 1000;
			DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
				
			for(int j = 1; j < 1_000_000; j*=10)
			{
				long totalTime = 0;
				
				for(int k = 1; k <= j; k++)
					list.addFirst(1);
				
				for(int i = 0; i < IterCount; i++)
				{
					// TIME IT!
					long startTime = System.nanoTime();
					while (System.nanoTime() - startTime < 1_000_000);
					long start = System.nanoTime();
				
					list.addFirst(1);
					
					long stop = System.nanoTime();
					totalTime += stop - start;
					list.removeFirst();
				}
				double averageTime = totalTime/IterCount;
				System.out.println(j + "\t" + averageTime);
			}
	}
	
	public static void addFirstArrayListTest() 
	{		

			int IterCount = 1000;
			
				
			for(int j = 1; j < 1_000_000; j*=10)
			{
				long totalTime = 0;
				ArrayList<Integer> list = new ArrayList<Integer>();
				
				for(int k = j/10; k <= j; k++)
					list.add(0, 1);
				
				for(int i = 0; i < IterCount; i++)
				{

					long startTime = System.nanoTime();
					while (System.nanoTime() - startTime < 1_000_000);
					long start = System.nanoTime();
				
					list.add(0, 1);
					
					long stop = System.nanoTime();
					totalTime += stop - start;
					list.remove(0);
				}
				
				double averageTime = totalTime/IterCount;
				System.out.println(j + "\t" + averageTime);
			}
	}
	
	public static void GetTest() 
	{		

			int IterCount = 1000;
			DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
				
			for(int j = 1; j <= 1_000_000; j*=2)
			{
				long totalTime = 0;
				
				for(int k = j/10; k <= j; k++)
					list.addFirst(1);
				
				for(int i = 0; i < IterCount; i++)
				{

					long startTime = System.nanoTime();
					while (System.nanoTime() - startTime < 1_000_000);
					long start = System.nanoTime();
				
					list.get((int) (Math.random() * list.size()));
					
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				
				double averageTime = totalTime/IterCount;
				System.out.println(j + "\t" + averageTime);
			}
	}
	
	public static void getArrayListTest() 
	{		

			int IterCount = 1000;
			
				
			for(int j = 1; j < 1_000_000; j*=2)
			{
				long totalTime = 0;
				ArrayList<Integer> list = new ArrayList<Integer>();
				
				for(int k = j/10; k <= j; k++)
					list.add(0, 1);
				
				for(int i = 0; i < IterCount; i++)
				{

					long startTime = System.nanoTime();
					while (System.nanoTime() - startTime < 1_000_000);
					long start = System.nanoTime();
				
					list.get((int)(Math.random() * list.size()));
					
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				
				double averageTime = totalTime/IterCount;
				System.out.println(j + "\t" + averageTime);
			}
	}
	
	public static void removeTest() 
	{		

			int IterCount = 1000;
			DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
				
			for(int j = 1; j <= 1_000_000; j*=10)
			{
				long totalTime = 0;
				
				for(int k = 1; k <= j; k++)
					list.addFirst(1);
				
				for(int i = 0; i < IterCount; i++)
				{

					long startTime = System.nanoTime();
					while (System.nanoTime() - startTime < 1_000_000);
					long start = System.nanoTime();
				
					list.remove((int)(Math.random() * list.size()));
					
					long stop = System.nanoTime();
					totalTime += stop - start;
					list.addFirst(1);
				}
				double averageTime = totalTime/IterCount;
				System.out.println(j + "\t" + averageTime);
			}
	}
	
	public static void removeArrayListTest() 
	{		

			int IterCount = 1000;
			
				
			for(int j = 1; j <= 1_000_000; j*=10)
			{
				long totalTime = 0;
				ArrayList<Integer> list = new ArrayList<Integer>();
				
				for(int k = j/10; k <= j; k++)
					list.add(0, 1);
				
				for(int i = 0; i < IterCount; i++)
				{

					long startTime = System.nanoTime();
					while (System.nanoTime() - startTime < 1_000_000);
					long start = System.nanoTime();
				
					list.remove((int)(Math.random() * list.size()));
					
					long stop = System.nanoTime();
					totalTime += stop - start;
					list.add(0, 1);
				}
				
				double averageTime = totalTime/IterCount;
				System.out.println(j + "\t" + averageTime);
			}
	}
}
