package assignment12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

/**
 * Class to test the compression ratio of different files with various
 * number of unique characters and character frequencies.
 * @author Jared Nielson u0495206
 *
 */
public class CompressionTester {
	private static int MIN_CHAR = 32;
	
	private static int MIN_NUMCHARS = 5;
	private static int MAX_NUMCHARS = 85;
	
	private static int MIN_FREQUENCY = 1;
	private static int MAX_FREQUENCY = 150;
	private static int STDEV = 15;
	
	public static void main(String[] args) {
		testUniques();
		testFrequency();
		testNormalDistribution();
		testUniformDistribution();

	}
	/**
	 * Tests compression ratio by varying the number of unique characters
	 * and holding the frequency constant for each character.
	 */
	private static void testUniques(){
		for(int index = MIN_NUMCHARS; index <= MAX_NUMCHARS; index += 5){
			HuffmanTree t = new HuffmanTree();
			
			int[] chars = new int[index];
			int[] frequency = new int[index];
			
			for(int charIdx = 0; charIdx < index; charIdx++){
				chars[charIdx] = MIN_CHAR + charIdx;
				frequency[charIdx] = MAX_FREQUENCY / 2;
			}
			
			FileDifUtil.generateRandomTxt("generated.txt", chars, frequency);
			
			t.compressFile(new File("generated.txt"), new File("generatedComp.txt"));
			
			try {
				double ratio = FileDifUtil.compressionRatio("generated.txt", "generatedComp.txt");
				
				System.out.println(index + "\t" + ratio);
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Tests compression ratios by holding the number of unique characters constant but varying the 
	 * frequency of the characters. Each character has the same frequency.
	 */
	private static void testFrequency(){
		for(int index = MIN_FREQUENCY; index <= MAX_FREQUENCY; index += 5){
			HuffmanTree t = new HuffmanTree();
			
			int[] chars = new int[MAX_NUMCHARS];
			int[] frequency = new int[MAX_NUMCHARS];
			
			for(int charIdx = 0; charIdx < MAX_NUMCHARS; charIdx++){
				chars[charIdx] = MIN_CHAR + charIdx;
				frequency[charIdx] = index;
			}
			
			FileDifUtil.generateRandomTxt("generated.txt", chars, frequency);
			
			t.compressFile(new File("generated.txt"), new File("generatedComp.txt"));
			
			try {
				double ratio = FileDifUtil.compressionRatio("generated.txt", "generatedComp.txt");
				
				System.out.println(index + "\t" + ratio);
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Tests compression ratio by varying the number of unique characters. Each
	 * character has a random frequency that is normally distributed.
	 */
	private static void testNormalDistribution(){
		for(int index = MIN_NUMCHARS; index <= MAX_NUMCHARS; index += 5){
			HuffmanTree t = new HuffmanTree();
			
			int[] chars = new int[index];
			int[] frequency = new int[index];
			
			Random rng = new Random(334787239876234L);
			for(int charIdx = 0; charIdx < index; charIdx++){
				chars[charIdx] = MIN_CHAR + charIdx;
				frequency[charIdx] = (int) ((rng.nextGaussian() * STDEV) + (MAX_FREQUENCY/2));
			}
			
			FileDifUtil.generateRandomTxt("generated.txt", chars, frequency);
			
			t.compressFile(new File("generated.txt"), new File("generatedComp.txt"));
			
			try {
				double ratio = FileDifUtil.compressionRatio("generated.txt", "generatedComp.txt");
				
				System.out.println(index + "\t" + ratio);
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Tests compression ratio by varying the number of unique characters. Each
	 * character has a randomly assigned frequency that is distributed uniformly.
	 */
	private static void testUniformDistribution(){
		for(int index = MIN_NUMCHARS; index <= MAX_NUMCHARS; index += 5){
			HuffmanTree t = new HuffmanTree();
			
			int[] chars = new int[index];
			int[] frequency = new int[index];
			
			Random rng = new Random(334787239876234L);
			for(int charIdx = 0; charIdx < index; charIdx++){
				chars[charIdx] = MIN_CHAR + charIdx;
				frequency[charIdx] = rng.nextInt(MAX_FREQUENCY) + 1;
			}
			
			FileDifUtil.generateRandomTxt("generated.txt", chars, frequency);
			
			t.compressFile(new File("generated.txt"), new File("generatedComp.txt"));
			
			try {
				double ratio = FileDifUtil.compressionRatio("generated.txt", "generatedComp.txt");
				
				System.out.println(index + "\t" + ratio);
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
				e.printStackTrace();
			}
			
		}	
	}

}











