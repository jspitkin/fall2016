package assignment11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * timing class for PriorityQueue.java
 * @author Braeden Barwick u0959391
 */
public class PQTimer {
	private static long start;
	private static long end;
	private static long total;
	private static int exp = 10;
	private static final int TESTS = 15;
	private static final int  ITERATIONS = 10000;
	private static Random rand = new Random();
	
	public static void main(String[]args) {
		while (System.nanoTime() - start < 1_000_000_000);
		
		try(FileWriter fw = new FileWriter(new File("minHeapTiming.tsv"))) {
			int[] collectionSize = new int[TESTS];
			ArrayList<ArrayList<String>> stringLists = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < TESTS; i++) {
				ArrayList<String> newList = new ArrayList<String>();
				int n = (int) Math.pow(2, exp);
				fillList(newList, n);
				stringLists.add(newList);
				collectionSize[i] = n;
				exp++;
			}
			
			System.out.println("Tests initiated findMin():");
			
			for(int i = 0; i < 10000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				total = 0;
				ArrayList<String> list = stringLists.get(i);
				PriorityQueue<String> queue = new PriorityQueue<String>();
				for(String string : list)
					queue.add(string);
				
				for (int j = 0; j < ITERATIONS; j++) {
					start = System.nanoTime();
					queue.findMin();
					end = System.nanoTime()-start;
					total += end;
				}
				System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t"+ collectionN);
				fw.write((total/ITERATIONS) +"\t"+ collectionN + "\n");
			}
			fw.write("newData" + "\n");
			
			System.out.println("Tests initiated deleteMin():");
			
			for(int i = 0; i < 1000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				total = 0;
				ArrayList<String> list = stringLists.get(i);
				Collections.shuffle(list);
				PriorityQueue<String> queue = new PriorityQueue<String>();
				for(String string : list)
					queue.add(string);
				
				for (int j = 0; j < ITERATIONS; j++) {
					start = System.nanoTime();
					queue.deleteMin();
					end = System.nanoTime()-start;
					total += end;
					String random = randomString(5);
					queue.add(random);//keep the queue the same size
				}
				System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t"+ collectionN);
				fw.write((total/ITERATIONS) +"\t"+ collectionN + "\n");
			}
			fw.write("newData" + "\n");

			System.out.println("Tests initiated add():");
			
			for(int i = 0; i < 1000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				total = 0;
				ArrayList<String> list = stringLists.get(i);
				PriorityQueue<String> queue = new PriorityQueue<String>();
				for(String string : list)
					queue.add(string);
				
				for (int j = 0; j < ITERATIONS; j++) {
					String random = randomString(5);
					start = System.nanoTime();
					queue.add(random);
					end = System.nanoTime()-start;
					total += end;
					queue.deleteMin();//keep size of queue the same
				}
				System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t"+ collectionN);
				fw.write((total/ITERATIONS) +"\t"+ collectionN + "\n");
				exp++;
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		PQCharter charter = new PQCharter();
		charter.createChart("minHeapTiming.tsv", "timingChart.png");
		System.out.println("Tests complete.");
	}
	
	// Create a random string [a-z] of specified length
	public static String randomString(int length)
	{
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			char randomChar = (char)('a' + (rand.nextInt(26)));
			stringBuilder.append(randomChar); 
		}
		return stringBuilder.toString();
	}
	
	/**
	 * helper method to fill lists with random strings
	 * @param newList the list to be filled
	 * @param size the number of items to fill the list with
	 */
	public static void fillList(ArrayList<String> newList, int size) {
		for(int i = 0; i < size; i++){
			newList.add(randomString(8));
		}
	}
}
