/**
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Timing test class, template pulled from lab
 * @author Osama Kergaye & Samuel Bridge
 * @UID u0767339 & u0984334
 */
public class Timing {

	private static final int ITER_COUNT = 100;
	static String lhs;
	static String rhs;
	
	
	public static void main(String[] args) {

		// "spin up" in order to help remove start time jiter
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		// open up a file writer so we can write to file.

		//Change the file name to match experiment used
		try (FileWriter fw = new FileWriter(new File("areAnagramsTiming_experiment.txt"))) {
		
		
			for (int exponent = 10; exponent <= 17; exponent++) { // This is used as the exponent to calculate the ssize of tahe set.

				int size = (int) Math.pow(2, exponent);

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {

					
			
					
					//SET UP!
					//below is for getLargestAnagramGroup
					String[] lotsOfWords = new String[size];
							
					for (int i = 0; i < size; i++) {
						
						lotsOfWords[i] = AnagramTester.randomString(5);
						
					}
					
					
					
					
					
					/*		
						//are anagrams timing
						lhs = AnagramTester.randomString(size);
						rhs = AnagramTester.randomString(size);
				    */
					
					
					// SET END!
			

					// TIME IT!
					long start = System.nanoTime();
					//getLargest timing
					AnagramUtil.getLargestAnagramGroup(lotsOfWords);
					
					//Are anagrams timing 
					//AnagramUtil.areAnagrams(lhs, rhs);
					
					
					long stop = System.nanoTime();
					// End Timing

					

					totalTime += stop - start;

				}

				double averageTime = totalTime / (double) ITER_COUNT;
				fw.write(size + "\t" + averageTime + "\n"); // write to file.

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		java.awt.Toolkit.getDefaultToolkit().beep();
		System.out.println("Done");

	}
}
