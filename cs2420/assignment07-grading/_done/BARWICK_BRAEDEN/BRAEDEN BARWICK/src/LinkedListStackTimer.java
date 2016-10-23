package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Timing for the addFirst(), get(), and remove() methods for DoublyLinkedList
 * @author Braeden Barwick u0959391
 *
 */
public class LinkedListStackTimer {
	private static long start;
	private static long end;
	private static long total;
	private static int exp = 10;
	private static final int TESTS = 11;
	private static final int  ITERATIONS = 10000;
	private static Random rand = new Random();
	
	public static void main(String[]args) {
		while (System.nanoTime() - start < 1_000_000_000);
		
		try(FileWriter fw = new FileWriter(new File("linkedListStackTiming.tsv"))) {
			int[] collectionSize = new int[TESTS];
			ArrayList<LinkedListStack<String>> stringLists = new ArrayList<LinkedListStack<String>>();
			for (int i = 0; i < TESTS; i++) {
				LinkedListStack<String> newList = new LinkedListStack<String>();
				int n = (int) Math.pow(2, exp);
				fillList(newList, n);
				stringLists.add(newList);
				collectionSize[i] = n;
				exp++;
			}
			
			System.out.println("Tests initiated push():");
			
			for(int i = 0; i < 1000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				total = 0;
				for (int j = 0; j < ITERATIONS; j++) {
					LinkedListStack<String> stack = stringLists.get(i);
					String random = randomString(5);
					start = System.nanoTime();
					stack.push(random);
					end = System.nanoTime()-start;
					total += end;
				}
				System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t"+ collectionN);
				fw.write((total/ITERATIONS) +"\t"+ collectionN + "\n");
			}
			fw.write("newData" + "\n");
			
			System.out.println("Tests initiated peek():");
			
			for(int i = 0; i < 1000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				total = 0;
				for (int j = 0; j < ITERATIONS; j++) {
					LinkedListStack<String> stack = stringLists.get(i);
					start = System.nanoTime();
					stack.peek();
					end = System.nanoTime()-start;
					total += end;
				}
				System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t"+ collectionN);
				fw.write((total/ITERATIONS) +"\t"+ collectionN + "\n");
			}
			fw.write("newData" + "\n");

			System.out.println("Tests initiated pop():");
			
			for(int i = 0; i < 1000000; i++){}
			
			for (int i = 0; i < TESTS; i++) {
				int collectionN = collectionSize[i];
				total = 0;
				for (int j = 0; j < ITERATIONS; j++) {
					LinkedListStack<String> stack = stringLists.get(i);
					start = System.nanoTime();
					stack.pop();
					end = System.nanoTime()-start;
					total += end;
				}
				System.out.println((i+1)+":\t"+ (total/ITERATIONS) +"\t"+ collectionN);
				fw.write((total/ITERATIONS) +"\t"+ collectionN + "\n");
				exp++;
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LinkedListStackCharter charter = new LinkedListStackCharter();
		charter.createChart("linkedListStackTiming.tsv", "timingChart.png");
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
	 * @param list the list to be filled
	 * @param size the number of items to fill the list with
	 */
	public static void fillList(LinkedListStack<String> list, int size) {
		for(int i = 0; i < size; i++){
			list.push(randomString(5));
		}
	}
}
