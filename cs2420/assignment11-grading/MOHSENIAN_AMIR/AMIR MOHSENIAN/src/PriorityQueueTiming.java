package assignment11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;


/**
 * Timer for the PriorityQueue Methods

 */
public class PriorityQueueTiming {
	@SuppressWarnings("null")
	public static void main (String[]args)
	{
		
		long startTime;
		@SuppressWarnings("unused")
		long midTime;
		long endTime;
		long finalTime;
		
		PriorityQueue<Integer> queueTest = null;
		
		ArrayList<Integer> list1 = null; 
		
		Comparator<String> comparison; 
		
		String[] words = StringMaker.readFile("large_sample.txt");

		Collection<String> list = new ArrayList<String>();


		int index = 0;
		while( index <= 100000)
		{
			list1.add(index);
			index++;
		}
		
		Collections.shuffle(list1);
		
		
		int index2 = 0;
		while( index2 <= 100000)
		{
			queueTest.add(list1.get(index2));
			index2++;
		}
		
		
			//Used loop for timing add Integer index =0;
			while(index < words.length)
			{
				startTime = System.nanoTime();
				list.add(words[index]);

				endTime = System.nanoTime();
				index = index+1;
				
				finalTime = endTime-startTime;
				if(index%1000 ==0)
				System.out.println("PriorityQueue size to time is:  " +  index+ " " + finalTime);
			}
	}
}