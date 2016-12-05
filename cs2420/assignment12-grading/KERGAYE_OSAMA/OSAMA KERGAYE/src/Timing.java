/**
 * @author Osama Kergaye
 * @UID u0767339
 */
package assignment12;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Timing test class, template pulled from lab
 * 
 * @author Osama Kergaye
 * @UID u0767339
 */
public class Timing {


	public static void main(String[] args) {
		long totalTimeOfExperiment = System.nanoTime();
		// "spin up" in order to help remove start time jiter
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		// open up a file writer so we can write to file.

		// Change the file name to match experiment used
		try (FileWriter fw = new FileWriter(new File("uniqeTest.txt"))) {

			// This is used as the exponent to calculate the size of the set.
			for (int exponent = 15; exponent <= 15; exponent++) {

				int sizeN = (int) Math.pow(2, exponent);

				// Do the experiment multiple times, and average out the results
				long  start = 0, stop = 0;

				HuffmanTree huffer = new HuffmanTree();
				Random rand = new Random(1);
				
				for (int iter = 1; iter < 27; iter++) {
					File testFile = new File("zHuffmanTest.txt");
					File testFileCompressed = new File("zHuffmanTestCompressed.txt");

					// SET UP!
					
					StringBuilder percentStrings = new StringBuilder();
					
//					double sameAppend =  (double) sizeN / (10/(11- (double)iter));
//					double numberOfA = 0, numOfRand = 0;
//					for (int i = 0; i < sameAppend; i++) {
//
//						
//					percentStrings.append(generateString(rand, "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, 1), 10));
//					
//
//					numberOfA = i;
//					}
					for (int i = 0; i < sizeN; i++) {
						
						
						percentStrings.append(generateString(rand, "ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, iter), 10));
						
						
					}

						
//					for (int i = 0; i < sizeN - sameAppend; i++) {
//
//						percentStrings.append(generateString(rand, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 10));
//						numOfRand = i;
//					}
					
					
					try(FileWriter toBeSqeezed = new FileWriter(testFile))
					{
						toBeSqeezed.write(percentStrings.toString());
					}
				
					
					
			
						// SIZE IT!
						start = testFile.length();

						huffer.compressFile(testFile ,testFileCompressed );
						
						stop = testFileCompressed.length();
						// End SIZEING

						
						double ratio = (double)stop/(double)start;
					
					
//						long percent = (long) ((numberOfA/sizeN)*100);
//						percent += 1;

						fw.write(start + "\t" + stop + "\t"+ ratio + "\t"+ iter  +"\n");
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		//Done stuff
		java.awt.Toolkit.getDefaultToolkit().beep();
		long totalTimeOfExperimentAfter = System.nanoTime();
		System.out.println(
				"Done " + ((totalTimeOfExperimentAfter - totalTimeOfExperiment) / 1000000000) + " seconds to complete");

	}
	
	
	
	
	public static String generateString(Random rng, String characters, int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}

}
