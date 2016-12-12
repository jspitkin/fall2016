package assignment11;

import java.util.Random;

public class TimePriorityQueue {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Random rand = new Random();
		
		double avgSingleAdd; 
		double avgTotalAdd;
		double findMin; 
		double avgSingleDelMin; 
		double avgTotalDelMin; 
		double timeToWait = 1000000000;
		int nMax = 1000000;
		int timesToLoop  = 10;
	
		
		for (int i = 10000; i < nMax; i += 10000) {
			double totalAdd, totalDel;
			avgSingleAdd = avgTotalAdd = findMin = avgSingleDelMin = avgTotalDelMin = totalAdd = totalDel = 0;
			PriorityQueue<Integer> test = new PriorityQueue<Integer>();
			
			double stopTime;
			double startTime = stopTime = 0;
			
		
			test.clear();
			for (int k = 0; k < i; k++) {
				test.add(rand.nextInt(i * 2));
			}
			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < timeToWait)
				;// even out thread
			for (int k = 0; k < timesToLoop * 100000; k++) {
				test.findMin();
			}
			stopTime = System.nanoTime();

			findMin = (double) (stopTime - startTime - timeToWait) / (double) (timesToLoop * 100000);

			avgSingleAdd = (double) totalAdd / (double) i / (double) timesToLoop;
			avgTotalAdd = (double) totalAdd / (double) timesToLoop;
			avgSingleDelMin = (double) totalDel / (double) i / (double) timesToLoop;
			avgTotalDelMin = (double) totalDel / (double) timesToLoop;

			System.out.println(i + "," + +avgSingleAdd + "," + avgTotalAdd + "," + findMin + "," + avgSingleDelMin + ","
					+ avgTotalDelMin);
		}
	}

}
