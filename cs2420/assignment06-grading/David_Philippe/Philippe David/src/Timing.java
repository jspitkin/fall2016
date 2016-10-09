package assignment06;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Random;


public class Timing {


	/**
	 * Timing Tests for DoublyLinkedList
	 * 
	 * @author Philippe David
	 */
	public static void main(String[] args) {
		String OUTPUT_DIRECTORY = "output/";
		String OUTPUT_FILE_PATH = OUTPUT_DIRECTORY
				+ "assignment06_experiment.tsv";

		// Create the output directory if it doesn't already exist
		File directory = new File(OUTPUT_DIRECTORY);
		if (!directory.exists()) {
			try {
				directory.mkdir();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
			System.out.println("An \"output/\" directory has been created to "
					+ "store the results from the timing tests.");
		}

		// Set up the timing test
		//DoublyLinkedList<Integer> testSet = new DoublyLinkedList<Integer>();
		Random random = new Random();

		final int START_EXPONENT = 14;
		final int END_EXPONENT = 22;

		final int ITER_COUNT = 30;

		int size;

		// Set up the log writer
		try (FileWriter fw = new FileWriter(new File(OUTPUT_FILE_PATH))) {
			String header = "size\taddFirst\tget\tremove\n";
			System.out.print(header);
			fw.write(header);

			// For each problem size
			for (int exp = START_EXPONENT; exp <= END_EXPONENT; exp++) {
				size = (int) Math.pow(2, exp);

				long getTotalTime = 0;
				long addFTotalTime = 0;
				long removeTotalTime = 0;

				double getAverageTime = 0;
				double addFAverageTime = 0;
				double removeAverageTime = 0;

				

				// Average over a number of tests
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					DoublyLinkedList<Integer> testSet = new DoublyLinkedList<Integer>();

					// Generate a clean new set
					testSet.clear();
					for (int i = 0; i < size; i++) {
						testSet.addFirst(new Integer(i));
					}

					// Pick a random element to test contains and add
					Integer randomElement = new Integer(random.nextInt(size));
					//randomElement = new Integer(size/2);
					// Time contains method
					long start = System.nanoTime();
					testSet.get(randomElement);
					long stop = System.nanoTime();

					getTotalTime += stop - start;

					
					// Time add method
					start = System.nanoTime();
					testSet.addFirst(randomElement);
					stop = System.nanoTime();

					addFTotalTime += stop - start;
					
					// Remove an element to be added later
					 start = System.nanoTime();
					testSet.remove(randomElement);
					 stop = System.nanoTime();
					 testSet.clear();
					removeTotalTime += stop - start;
					if(exp == 20) {
						
						System.out.println(iter);
					}
					//System.out.println(iter);
					
				}
				getAverageTime = getTotalTime / (double) ITER_COUNT;
				addFAverageTime = addFTotalTime / (double) ITER_COUNT;
				removeAverageTime = removeTotalTime / (double) ITER_COUNT;

				// Generate and store the log output
				String output = size + "\t" + addFAverageTime + "\t"
						+ getAverageTime +  "\t" + removeAverageTime + "\n";

				System.out.print(output);
				fw.write(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
