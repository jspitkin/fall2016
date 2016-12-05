package assignment12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Timing experiment to assess how Huffman Compression varies with the number of unique characters.
 * @author Dylan Johnson - u1024233
 * 11/21/16
 */
public class TimingUniqueCharacters {
	
	public static final int NUM_OF_TESTS = 10;
	
	private static File generateFile(String fileName, int number) {

		Random rand = new Random();
		
		File file = new File(fileName);

		PrintWriter writer = null;

		try {
			writer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 1_000_000; i++) {
				char character = (char) (rand.nextInt(number/2) + 32);
				writer.append(character);
		}
		
		writer.close();
		
		return file;
	}

	public static void main(String[] args) {

		System.out.println("\tCompressionRatio");
		
		HuffmanTree tree = new HuffmanTree();
		double compressionRatio = 0;
		
		for (int number = 2; number < 226; number += 10) {

			compressionRatio = 0;
			
			for (int testNumber = 0; testNumber < NUM_OF_TESTS; testNumber++) {
				
				File originalFile = generateFile("compressed_files/original" + number + ".txt", number);
				File compressedFile = new File("compressed_files/compressed" + number + ".txt");
				
				tree.compressFile(originalFile, compressedFile);

				compressionRatio += ((double)compressedFile.length())/originalFile.length();
			}
			
			compressionRatio /= NUM_OF_TESTS;
			
			System.out.println(number + "\t" + compressionRatio);
			
		}

	}

}
