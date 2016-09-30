package assignment04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * TimingTests provide tests for timing our implementation of AnagramUtil methods
 * @author Andrew Keaton Bruce, Longsheng Du
 * u1006889, u1093993
 * CS 2420
 * 9/20/2016
 */
public class TimingTests {
	private final static int WORD_ITER_COUNT = 10000;
	private final static int LIST_ITER_COUNT = 1;
	private final static int BILLION = 1_000_000_000;
	
	public static void main(String args[]) {
		long start;
		long end;
		long totalTime = 0;
		int listLength;
		double average;
		String randStr1;
		String randStr2;
		//This String array uses a HUGE list of words here
		String [] wordList = AnagramTester.readFile("wordsEn.txt");
	
		
		try{
			FileWriter fwarea = new FileWriter(new File("areAnagrams_experiment.tsv"));
			FileWriter fwgetgroup = new FileWriter(new File("getLargestAnagramGroup_experiment.tsv"));
			FileWriter fwgetgroupJava = new FileWriter(new File("getLargestAnagramGroupJavaSort_experiment.tsv"));
	
			System.out.println("areAnagrams test:\n");
	
			//Testing variable String lengths to determine variations in run time
			//with strings of realistic length
			for (int stringLength = 2; stringLength < 50; stringLength++)
			{

				totalTime = 0;
				//Begin iterations
				for (int iteration = 0; iteration < WORD_ITER_COUNT; iteration++) {
					//Create two different random strings of the same size
					randStr1 = AnagramTester.randomString(stringLength);
					randStr2 = AnagramTester.randomString(stringLength);
					
					//Begins time testing areAnagrams with the two random strings
					start = System.nanoTime();
					AnagramUtil.areAnagrams(randStr1, randStr2);
					end = System.nanoTime();
					
					//Add the time taken to the total time
					totalTime += (end - start);
				}
				//Compute the average based on the total and the iterations
				average = (totalTime / (double) WORD_ITER_COUNT);
				System.out.println(stringLength + "\t" + average);
				fwarea.write(stringLength + "\t" + average + "\n"); // write to file.

			}
			
			System.out.println("getLargestAnagramGroup test:\n");
			
			//Here we are testing arrays of various sizes
			//We are with 2^10 and end with around 2^17, but it is actually the end of the array
			for (int exp = 10; exp <= 17; exp++)
			{
				if (exp==17) {
					listLength = wordList.length;
				}
				else {
					listLength = (int)Math.pow(2, exp);
				}
				
				//Copy a section of the original String array into a new one
				//Based on the size of listLength which is defined in the for loop.
				String [] subWordList = Arrays.copyOfRange(wordList, 0, listLength);
				
				totalTime = 0;
				//Begin iterating once more
				for (int iteration = 0; iteration < LIST_ITER_COUNT; iteration++) {
					
					//Start the timer and test getLargestAnagramGroup on our new array
					//that is periodically growing bigger as the exponent grows
					start = System.nanoTime();
					AnagramUtil.getLargestAnagramGroup(subWordList);
					end = System.nanoTime();
					
					totalTime += (end - start);
				}
				//Calculate average time
				average = (totalTime / (double) LIST_ITER_COUNT);
				//Convert to seconds because it takes a long time
				average = average / BILLION;
				System.out.println(listLength + "\t" + average);
				fwgetgroup.write(listLength + "\t" + average + "\n"); // write to file.
			}
	
			System.out.println("getLargestAnagramGroup with Java's sort test:\n");
			
			//Here we are testing arrays of various sizes
			//We are with 2^10 and end with around 2^17, but it is actually the end of the array
			for (int exp = 10; exp <= 17; exp++)
			{
				if (exp==17) {
					listLength = wordList.length;
				}
				else {
					listLength = (int)Math.pow(2, exp);
				}
	
				//Copy a section of the original String array into a new one
				//Based on the size of listLength which is defined in the for loop.
				String [] subWordList = Arrays.copyOfRange(wordList, 0, listLength);
	
				totalTime = 0;
				//Begin iterating once more
				for (int iteration = 0; iteration < LIST_ITER_COUNT; iteration++) {
	
					//Start the timer and test getLargestAnagramGroup on our new array
					//that is periodically growing bigger as the exponent grows
					start = System.nanoTime();
					AnagramUtil.getLargestAnagramGroupJavaSort(subWordList);
					end = System.nanoTime();
	
					totalTime += (end - start);
				}
				//Calculate average time
				average = (totalTime / (double) LIST_ITER_COUNT);
				//Convert to seconds because it takes a long time
				average = average / BILLION;
				System.out.println(listLength + "\t" + average);
				fwgetgroupJava.write(listLength + "\t" + average + "\n"); // write to file.

			}
			
			fwarea.close();
			fwgetgroup.close();
			fwgetgroupJava.close();

		
	}
	catch (IOException e) {
		e.printStackTrace();
	}
		
	}
}
