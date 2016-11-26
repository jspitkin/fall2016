package assignment11;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Timing class for the analysis of the PriorityQueue class in assignment 11.
 * 
 * @author Nathan Page
 * @uid u0741592
 */
public class PriorityQueueTiming {
	
	private static final double BILLION = 1_000_000_000;
	
	private static int runs = 100;
	private static int listSize = 100000;
	private static int runAvg = 10;
	
	private static PriorityQueue<Integer> intQueue;
	
	private static Random random = new Random();

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("Assignment11_Run2.txt", false));
		
		intQueue = new PriorityQueue<Integer>();
		
//		random.setSeed(0);
		
		bw.write("Add Timing");
		bw.newLine();
		bw.write("Queue Size" + "\t" + "Time");
		bw.newLine();
		
		for(int i = 1; i <= runs; i++) {
			bw.write("" + (i * listSize) + "\t" + addTiming((i * listSize), runAvg));
			bw.newLine();
		}
		
		System.out.println("Add timing complete...");
		
		bw.write("Delete Timing");
		bw.newLine();
		bw.write("Time");
		bw.newLine();
		
		for(int i = 1; i <= runs; i++) {
			bw.write("" + deleteTiming((i * listSize), runAvg));
			bw.newLine();
		}
		
		System.out.println("Delete timing complete...");
		
		bw.write("Find Min Timing");
		bw.newLine();
		bw.write("Time");
		bw.newLine();
		
		for(int i = 1; i <= runs; i++) {
			bw.write("" + findMinTiming((i * listSize), runAvg));
			bw.newLine();
		}
		
		System.out.println("Find min timing complete...");
		
		System.out.println("Timing complete");
		
		bw.close();
	}

	/**
	 * Private timing method for the findMin method.
	 * 
	 * @param size - 
	 * 		Size to make the queue.
	 * @param runAvg - 
	 * 		Number of times to run the test to find an average.
	 * @return the average time taken to find the minimum value.
	 */
	private static double findMinTiming(int size, int runAvg) {
		long start;
		long end;
		
		double time = 0;
		
		for(int i = 0; i < runAvg; i++) {
			for(int index = 0; index < size; index++) {
				intQueue.add(random.nextInt(size));
			}
			
			start = System.nanoTime();
			intQueue.findMin();
			end = System.nanoTime() - start;
			
			intQueue.clear();
			
			time += end / BILLION;
		}
		
		time = time / runAvg;
		
		return time;
	}

	/**
	 * Timing method for the delete method.
	 * 
	 * @param size - 
	 * 		Size to make the array prior to deletion.
	 * @param runAvg - 
	 * 		Number of times to run the test to find an average.
	 * @return the average time taken by the delete method to delete one item.
	 */
	private static double deleteTiming(int size, int runAvg) {
		long start; 
		long end;
		
		double time = 0;
		
		for(int i = 0; i < runAvg; i++) {
			for(int index = 0; index < size; index++) {
				intQueue.add(random.nextInt(size));
			}
			
			start = System.nanoTime();
			intQueue.deleteMin();
			end = System.nanoTime() - start;
			
			intQueue.clear();
			
			time += end / BILLION;
		}
		
		time = time / runAvg;
		
		return time;
	}

	/**
	 * Timing method for the add method.  This method is different in that the
	 * time represents multiple calls to add, the time returned will be the time 
	 * taken to fill the whole queue.
	 * 
	 * @param size - 
	 * 		Size the queue should end up being immediately following the test.
	 * @param runAvg - 
	 * 		Number of times to run the test to find an average.
	 * @return the average time taken by the add method to add every item.
	 */
	private static double addTiming(int size, int runAvg) {
		long start;
		long end;
		
		double time = 0;
		
		for(int i = 0; i < runAvg; i++) {
			start = System.nanoTime();
			for(int index = 0; index < size; index++) {
				intQueue.add(random.nextInt(size));
			}
			end = System.nanoTime() - start;
			
			intQueue.clear();
			
			time += end / BILLION;
		}
		
		time = time / runAvg;
		
		return time;
	}
}
