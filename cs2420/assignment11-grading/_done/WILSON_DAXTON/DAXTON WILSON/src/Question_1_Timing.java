package assignment11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Timing Class for Question 1 of Assignment11 Analysis Document
 * 
 * @author Daxton Wilson u0264580
 */

public class Question_1_Timing {
	private static int ITER_COUNT;

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			;

		for (int exp = 5; exp <= 20; exp++) { // This is used as the
															// exponent
			// to calculate the size of the set.
			ITER_COUNT = (int) Math.pow(2, exp);

			// int ITER_COUNT = size;// or ..

			// Do the experiment multiple times, and average out the results
			long totalTime = 0;

			for (int iter = 0; iter < ITER_COUNT; iter++) {

				Random rand = new Random();

				// SET UP!
				PriorityQueue<Integer> intPQ = new PriorityQueue<Integer>();
				
//				for (int i = 0; i < size; i++) {
//					intPQ.add(rand.nextInt(size));
//				}
				
				// TIME IT!
				long start = System.nanoTime();
				intPQ.add(rand.nextInt(ITER_COUNT));
//				intPQ.findMin();
//				intPQ.deleteMin();
				long stop = System.nanoTime();
				totalTime += stop - start;

			}
			double averageTime = totalTime / ((double) ITER_COUNT);
			System.out.println(ITER_COUNT + "\t" + averageTime); // print to console
		}
	}
	
	
}
