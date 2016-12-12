package assignment11;

import java.util.Random;

public class PriorityQueueTiming {

	private static PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
	private static Random rand = new Random(919708);
	
	private static final int START_SIZE = 1024;
	private static final double MAX_SIZE = Math.pow(2, 25);
	private static final int REP_COUNT = 1000000;
	
	public static void main(String[] args){
		allTimings();
	}
	
	private static void allTimings(){
		System.out.println("PriorityQueue Timings:");
		System.out.println("Size\tAdd\tFindMin\tDeleteMin");
		
		for(int size = START_SIZE; size <= MAX_SIZE; size *= 2){
			queue.clear();
			fillQueue(size);
			
			long start, end;
			Long[] addTimings = new Long[REP_COUNT], findTimings = new Long[REP_COUNT], deleteTimings = new Long[REP_COUNT];
			for(int rep = 0; rep < REP_COUNT; rep++){
				int nextInt = rand.nextInt();
				
				start = System.nanoTime();
				queue.add(nextInt);
				end = System.nanoTime();
				addTimings[rep] = end - start;
				
				start = System.nanoTime();
				queue.findMin();
				end = System.nanoTime();
				findTimings[rep] = end - start;
				
				start = System.nanoTime();
				queue.deleteMin();
				end = System.nanoTime();
				deleteTimings[rep] = end - start;
			}
			
			long addSum = 0, findSum = 0, deleteSum = 0;
			for(int rep = 0; rep < REP_COUNT; rep++){
				addSum += addTimings[rep];
				findSum += findTimings[rep];
				deleteSum += deleteTimings[rep];
			}
			
			System.out.println(size + "\t" + (addSum/REP_COUNT) + "\t" + (findSum/REP_COUNT) + "\t" + (deleteSum/REP_COUNT));
		}
	}
	
	private static void fillQueue(int size){
		for(int i = 0; i <= size; i++){
			queue.add(rand.nextInt());
		}
	}
}
