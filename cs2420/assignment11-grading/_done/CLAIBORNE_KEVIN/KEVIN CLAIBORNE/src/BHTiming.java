package assignment11;

import java.util.Comparator;
import java.util.Random;


public class BHTiming {

	public static void main(String[] args) {
		
		int size = 1000;
		int average = 1000;
		Long startTime;
		Long totalTimeAdd;
		Long totalTimeDelete;
		Long totalTimeFindMin;
		Random rand = new Random();
		Comparator<String> compString = new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};
		
		System.out.println("size\tadd\tdelete\tclear");

		while (size <= 1000000) {
						
			totalTimeAdd = totalTimeDelete= totalTimeFindMin = 0L;
			
			
			
			for (int runTimes = 0; runTimes < average; runTimes++) {
				
				PriorityQueue<String> stringList = new PriorityQueue<String>(compString);
				
				for (int i = 0; stringList.size() <= size; i++) {
					String tempString = randomString(rand.nextInt(10) + 1);
					stringList.add(tempString);
				}
				
				startTime = System.nanoTime();
				stringList.add("Kevin");
				totalTimeAdd += (System.nanoTime() - startTime);
				
				startTime = System.nanoTime();
				stringList.deleteMin();
				totalTimeDelete += (System.nanoTime() - startTime);
				
				startTime = System.nanoTime();
				stringList.findMin();
				totalTimeFindMin += (System.nanoTime() - startTime);

			}
			totalTimeAdd /= average;
			totalTimeDelete /= average;
			totalTimeFindMin /= average;
			
			
			System.out.println(size + "\t" + totalTimeAdd + "\t" + totalTimeDelete + "\t" + totalTimeFindMin);
			size += 1000;
		}

	}
	
	//Helper Methods
	
	public static String randomString(int length) {
		Random rand = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {

			char randomChar = (char) ('a' + (rand.nextInt(25)));
			stringBuilder.append(randomChar);
		}
		return stringBuilder.toString();
	}
}
