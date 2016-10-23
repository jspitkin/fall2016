package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class LinkedListStackTimingCode {

	private static final int ITER_COUNT = 1000;

	public static void main(String[] args) {

		// you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);

		String fileName = "StackPush_experiment.tsv";
		try(FileWriter fw = new FileWriter(new File(fileName))) { //open up a file writer so we can write to file.
			System.out.println(fileName);

			for(int exp = 0; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the input.
				int size = (int) Math.pow(2, exp);

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					LinkedListStack<Integer> nums = new LinkedListStack<Integer>();
					for(int s = 0; s < size; s++){
						nums.push(10);
					}

					// TIME IT!
					long start = System.nanoTime();
					nums.push(20);
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
			System.out.println("Total time: " + (System.nanoTime() - startTime) / 1000000000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}