package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class AnagramUtilTiming {
	public static void main(String[] args) {
		String OUTPUT_DIRECTORY = "output/";

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
		final int START_EXPONENT = 2;
		final int END_EXPONENT = 12;

		final int ITER_COUNT = 100;

		final int MAX_WORD_LENGTH = 8; // Most words are < 16 chars long

		int size;

		// Set up log writer for areAnagrams timing test
		try (FileWriter fw = new FileWriter(
				new File(OUTPUT_DIRECTORY + "assignmet04_areAnagrams.tsv"))) {
			String header = "size\tareAnagrams\n";
			System.out.print(header);
			fw.write(header);

			// For difference sized words
			for (int exp = START_EXPONENT; exp <= END_EXPONENT; exp++) {
				size = (int) Math.pow(2, exp);

				long totalTime = 0;
				double averageTime = 0;

				// Average over a number of tests
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// Generate two random words
					String randWord1 = randomString(size);
					String randWord2 = randomString(size);

					// Calculate the time
					long start = System.nanoTime();
					AnagramUtil.areAnagrams(randWord1, randWord2);
					long stop = System.nanoTime();

					totalTime += stop - start;
				}
				averageTime = totalTime / (double) ITER_COUNT;

				// Generate log output
				String output = size + "\t" + averageTime + "\n";

				System.out.print(output);
				fw.write(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Set up log writer for getLargestAnagram timing tests
		try (FileWriter fw = new FileWriter(new File(
				OUTPUT_DIRECTORY + "assignmet04_getLargestAnagramGroup.tsv"))) {
			String header = "size\tinsertionSort\tjavaArraySort\n";
			System.out.print(header);
			fw.write(header);
			
			// For different number of words
			for (int exp = START_EXPONENT; exp <= END_EXPONENT; exp++) {
				size = (int) Math.pow(2, exp);

				long insertionTotalTime = 0;
				long javaSortTotalTime = 0;

				double insertionAverageTime = 0;
				double javaSortAverageTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// Generate random strings
					String[] words = new String[size];
					for (int i = 0; i < words.length; i++) {
						words[i] = randomString(MAX_WORD_LENGTH);
					}

					// Test insertion sort
					long start = System.nanoTime();
					AnagramUtil.getLargestAnagramGroup(words);
					long stop = System.nanoTime();

					insertionTotalTime += stop - start;

					// Test java's sort method
					start = System.nanoTime();
					AnagramUtilAlt.getLargestAnagramGroup(words);
					stop = System.nanoTime();

					javaSortTotalTime += stop - start;
				}
				insertionAverageTime = insertionTotalTime / (double) ITER_COUNT;
				javaSortAverageTime = javaSortTotalTime / (double) ITER_COUNT;

				// Generate and store the log output
				String output = size + "\t" + insertionAverageTime + "\t"
						+ javaSortAverageTime + "\n";
				
				System.out.print(output);
				fw.write(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Create a random string [a-z] of specified length
	public static String randomString(int length) {
		Random rand = new Random();

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < length; i++) {
			// ASCII values a-z,A-Z are contiguous (52 characters)
			char randomChar = (char) ('a' + (rand.nextInt(26)));
			stringBuilder.append(randomChar);
		}
		return stringBuilder.toString();
	}
}
