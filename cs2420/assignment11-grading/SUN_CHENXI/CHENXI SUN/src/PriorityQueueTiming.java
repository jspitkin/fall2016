package assignment11;

import java.util.ArrayList;
import java.util.Random;

import assignment05.ComparatorInteger;
import assignment05.SortUtil;
/**
 * 
 * @author Chenxi Sun
 * @uid u0455173
 *
 */
public class PriorityQueueTiming {

	
	public static void main(String[] args){
		
		PriorityQueueTiming();
	}
	
	
	public static void PriorityQueueTiming() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
			for(int increase = 0; increase <= 3000; increase+=100) { 
				
				
				long totalTime = 0;
									
				// TIME IT!
		
				long start = System.nanoTime();
				ComparatorInteger cmp=new ComparatorInteger();
				PriorityQueue<Integer> abc =new PriorityQueue<Integer>();
				ArrayList<Integer> abc2= generateAverageCase(increase);
				for(int i = 0; i < increase; i++){ 
					
					
					abc.add(abc2.get(i));
					
					
					//will increase the increase by 10 and change the random string length by 10 each time through the loop
				}
	
					long stop = System.nanoTime();
				totalTime += stop - start;
				
				long DeletetotalTime = 0;
				
				long Deletestart = System.nanoTime();
				for(int i = 0; i < increase; i++){ 
			
					
					//will increase the increase by 10 and change the random string length by 10 each time through the loop
				}
					long Deletestop = System.nanoTime();
				DeletetotalTime += Deletestop - Deletestart;

				double averageTime = totalTime -DeletetotalTime;
				
				
			long DeleteTime=System.nanoTime();
				
				
				
				
				System.out.println(increase + "\t" + averageTime);
			}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static ArrayList<Integer> generateAverageCase(int size) {

		ArrayList<Integer> averageArray = new ArrayList<Integer>();

		Random randomIndex = new Random();

		for (int i = 0; i < size; i++) {

			averageArray.add(i);
		}

		for (int i = 0; i < size; i++) {
			int randomNumber = randomIndex.nextInt(size);

			int temp = averageArray.get(randomNumber);
			averageArray.set(randomNumber, averageArray.get(i));
			averageArray.set(i, temp);

		}

		return averageArray;

	}
	
	
	
	
	
	
	
	
	
}
