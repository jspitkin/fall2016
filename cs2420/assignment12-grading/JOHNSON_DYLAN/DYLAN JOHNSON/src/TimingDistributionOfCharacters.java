package assignment12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Timing experiment to assess how Huffman Compression varies with the frequency of characters.
 * @author Dylan Johnson - u1024233
 * 11/21/16
 */
public class TimingDistributionOfCharacters {

	public static final int NUM_OF_TESTS = 100;
	public static final double[] ENGLISH_PROBABILITIES = { .08167, .01492, .02782, .04283, .12702, .0228, .02015,
			.06094, .06966, .00153, .00772, .04025, .02406, .06749, .07507, .01929, .00095, .05987, .06327, .09056,
			.02758, .00978, .02360, .00150, .01974, .00074 };
	public static double[] counts;

	private static double generateFile(String fileName, int number) {

		File file = new File(fileName);

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		double[] probabilities = ENGLISH_PROBABILITIES.clone();
		counts = new double[probabilities.length];
		for (int index = 0; index < probabilities.length; index++) {
			/** Modify probabilities here in order
			 * to create various standard deviations
			 */ 
		}

		for (int i = 0; i < 100_000; i++) {
			for (int j = 0; j < 26; j++) {
				double probability = Math.random();
				if (probability > .9) {
					j++;
					writer.append(' ');
				} else if (probabilities[j] > probability) {
					char character = (char) (97 + j);
					counts[j]++;
					writer.append(character);
				}
			}

		}

		writer.close();

		return getDeviation(counts);

	}

	public static void main(String[] args) {

		System.out.println("\tCompressionRatio");

		HuffmanTree tree = new HuffmanTree();
		double compressionRatio = 0;
		double totalDeviation = 0;

		for (int number = 1; number < 25; number++) {

			compressionRatio = 0;
			totalDeviation = 0;

			for (int testNumber = 0; testNumber < NUM_OF_TESTS; testNumber++) {

				File originalFile = new File("compressed_files/original" + number + ".txt");
				totalDeviation += generateFile("compressed_files/original" + number + ".txt", number);
				File compressedFile = new File("compressed_files/compressed" + number + ".txt");

				tree.compressFile(originalFile, compressedFile);

				compressionRatio += ((double) compressedFile.length()) / originalFile.length();
			}

			compressionRatio /= NUM_OF_TESTS;

			System.out.println(totalDeviation / NUM_OF_TESTS + "\t" + compressionRatio);

		}

	}

	public static double getDeviation(double[] data) {
		double result = 0;
		double mean;
		double sum = 0;
		for (double n : data) {
			sum += n;
		}
		mean = sum / data.length;
		for (double dataPoint : data) {
			result += Math.pow(dataPoint - mean, 2);
		}
		result /= data.length;
		result = Math.sqrt(result);
		return result;
	}

}
