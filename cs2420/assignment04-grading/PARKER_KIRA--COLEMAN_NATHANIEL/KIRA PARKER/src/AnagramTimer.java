package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


/**
 * Class for doing timing analysis on AnagramUtil
 * @author Kira Parker u1073760
 *
 */
public class AnagramTimer {
	public static final int ITER_COUNT = 1_000;

	public static void main(String[] args) {
		try(FileWriter fw = new FileWriter(new File("assignment04-timing.csv"))) { //open up a file writer so we can write to file.
			for(int exp = 3; exp <= 10; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); 
				
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					String[] words = new String[size];
					for(int i=0; i<words.length; i++){
						words[i] = AnagramTester.randomString(10);
					}
					//String s1 = AnagramTester.randomString(size);
					//String s2 = AnagramTester.randomString(size);
					//time the experiment
					long start = System.nanoTime();
					AnagramUtil.getLargestAnagramGroup(words);
					//AnagramUtil.areAnagrams(s1,  s2);
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "," + averageTime + "," + (averageTime/1000000) + "\n"); // write to file. (size, nanoseconds, milliseconds)
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
}
