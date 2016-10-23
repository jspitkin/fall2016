package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Timing experiments for LinkedListStack;
 * 
 * @author Ella Moskun u0897252
 */
public class LinkedListStackTiming {

	private final static int TRIAL_COUNT = 10_000;
	private static Random random = new Random();

	/**
	 * Runs the actual timing tests for LinkedListStack. Output is saved to
	 * LinkedListStack.csv. The columns are as follows:
	 * 
	 * Column 1: The size of the stack (N), in millions
	 * 
	 * Column 2: The average running time of push, in nanoseconds
	 * 
	 * Column 3: The average running time of pop, in nanoseconds
	 * 
	 * Column 4: The average running time of peek, in nanoseconds
	 * 
	 * A stack trace is printed if a problem occurs writing the .csv file.
	 * 
	 * @param args
	 *            Ignored
	 */
	public static void main(String[] args) {
		try (FileWriter output = new FileWriter(new File("LinkedListStack.csv"))) {
			for (int N = 1_000_000; N <= 20_000_000; N += 1_000_000) {
				long pushTotalTime = 0;
				long popTotalTime = 0;
				long peekTotalTime = 0;
				
				// Construct the stack to be used for this trial
				LinkedListStack<Integer> problem = new LinkedListStack<Integer>();
				for (int i = 0; i < N; i++) {
					problem.push(random.nextInt());
				}

				for (int trial = 0; trial < TRIAL_COUNT; trial++) {
					long start;
					long stop;

					// push
					Integer addMe = random.nextInt();
					start = System.nanoTime();
					problem.push(addMe);
					stop = System.nanoTime();
					pushTotalTime += stop - start;

					// cleanup
					problem.pop();

					// pop
					start = System.nanoTime();
					problem.pop();
					stop = System.nanoTime();
					popTotalTime += stop - start;

					// cleanup
					problem.push(random.nextInt());

					// peek
					start = System.nanoTime();
					problem.peek();
					stop = System.nanoTime();
					peekTotalTime += stop - start;
				}

				// The first several trials was just to warm up
				if (N < 5_000_000) {
					System.out.println("Warming up...");
				} else {
					StringBuilder row = new StringBuilder();
					row.append(N / 1_000_000);
					row.append(',');
					row.append((double) pushTotalTime / TRIAL_COUNT);
					row.append(',');
					row.append((double) popTotalTime / TRIAL_COUNT);
					row.append(',');
					row.append((double) peekTotalTime / TRIAL_COUNT);
					row.append('\n');
					output.write(row.toString());
					System.out.print(row.toString());
				}
			}
			System.out.println("Finished!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
