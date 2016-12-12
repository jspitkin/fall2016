package assignment11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class PriorityQueueTiming {
	private final static int TRIAL_COUNT = 100;
	
	public static void main(String[] args) {
		Object[][] data = new Object[10 + 2][3*2];	// Data points per experiment + header; Number of experiments * 2
		int i = 0;
		
		warmUp();
		
		System.out.println("addPrioQueue");
		addPriorityQueueExperiment(data, i++);
		
		System.out.println("removePrioQueue");
		removePriorityQueueExperiment(data, i++);


		
		writeToFile(data, "assignment08.csv");
		System.err.println("FINISHED");
	}

	private static void warmUp() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000L) {
		}
	}
	
	private static void addPriorityQueueExperiment(Object[][] output, int offset) {
		output[0][offset*2] = "Complexity of Adding N Integers to Priority Queue";
		output[0][offset*2+1] = "";
		output[1][offset*2] = "Time(mS)";
		output[1][offset*2+1] = "Problem Size - N (Integers)";
		int row = 2;
		
		for(int problemSize = 100_000; problemSize <= 1_000_000; problemSize += 100_000) {
			long startTime, endTime; 
			long runningTime = 0;
			
			for(int trial = 0; trial < TRIAL_COUNT; trial++) {
				PriorityQueue<Integer> intPrioQueue = new PriorityQueue<>();
				
				startTime = System.nanoTime();
				for(int i = 0; i < problemSize; i++) {
					intPrioQueue.add(i);
				}	
				endTime = System.nanoTime();
				runningTime += (endTime - startTime);
			}
		
			output[row][offset*2] = problemSize;
			output[row++][1 + offset*2] = ((double) runningTime) / TRIAL_COUNT / 1_000_000;
		
			System.out.println(problemSize + ",\t" + ((double) runningTime) / TRIAL_COUNT / 1_000_000);
		}
	}
	
	private static void removePriorityQueueExperiment(Object[][] output, int offset) {
		output[0][offset*2] = "Complexity of Deleting N Integers from a Priority Queue";
		output[0][offset*2+1] = "";
		output[1][offset*2] = "Time(mS)";
		output[1][offset*2+1] = "Problem Size - N (Integers)";
		int row = 2;
		
		for(int problemSize = 100_000; problemSize <= 1_000_000; problemSize += 100_000) {
			long startTime, endTime; 
			long runningTime = 0;
			
			for(int trial = 0; trial < TRIAL_COUNT; trial++) {
				PriorityQueue<Integer> intPrioQueue = new PriorityQueue<>();
				
				for(int i = 0; i < problemSize; i++) {
					intPrioQueue.add(i);
				}	
				
				startTime = System.nanoTime();
				for(int i = 0; i < problemSize; i++) {
					intPrioQueue.deleteMin();
				}	
				endTime = System.nanoTime();
				runningTime += (endTime - startTime);
			}
		
			output[row][offset*2] = problemSize;
			output[row++][1 + offset*2] = ((double) runningTime) / TRIAL_COUNT / 1_000_000;
		
			System.out.println(problemSize + ",\t" + ((double) runningTime) / TRIAL_COUNT / 1_000_000);
		}
	}	
	
	private static void writeToFile(Object[][] data, String string) {
		try(FileWriter output = new FileWriter(new File(string))) {
			StringBuilder result = new StringBuilder();
			
			for(Object[] row : data) {
				for(int column = 0; column < row.length; column++) {
					if(column % 2 == 0) {
						result.append(row[column] + ",");
					} else {
						result.append(row[column] + ",\t,\t,");
					}
				}
				result.append("\n");
			}

			output.write(result.toString());
			
		} catch(IOException e) {
			System.err.println("Unable to write to assignment06.csv");
		}
		
	}
}
