package assignment11;

import java.util.ArrayList;
import java.util.Collections;

public class PriorityQueueTimingTests 
{
	private static final int ITER_COUNT = 1000;

	public static void main(String[] args) 
	{
		PriorityQueue<Integer> myIntegerQueue = new PriorityQueue<Integer>();
		ArrayList<Integer> integerList;
		integerList = new ArrayList<Integer>();

		for (int numberOfItems = 1000; numberOfItems <= 100000; numberOfItems += 4000) 
		{ 
			long totalTime = 0;
			
			integerList.clear();
			for (int i = 0; i < numberOfItems; i++)
			{
				integerList.add(i);
			}

			Collections.shuffle(integerList);
			Collections.shuffle(integerList);
			
			// Time it multiple times to get an average of the time
			for (int iter = 0; iter < ITER_COUNT; iter++)
			{
				myIntegerQueue.clear();
				for (int i = 0; i < numberOfItems; i++) 
				{
					myIntegerQueue.add(integerList.get(i));
				}
				
				long start = System.nanoTime();
				myIntegerQueue.add(numberOfItems / 2);
				long stop = System.nanoTime();
				totalTime += stop - start;
				
			}
			
			double averageTime = totalTime / (double) ITER_COUNT;
			
			// print to console
			System.out.println(numberOfItems + "\t" + averageTime); 

		} 
	}
}
