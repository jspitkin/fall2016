package assignment07;

import java.util.Random;

/**
 * Timing tests for LinkedListStack and BalancedSymbolChecker.
 * @author Scott Krstyen U0760822
 */

import assignment06.DoublyLinkedList;

public class LinkedListStackTimingTests {
	
	static Double[] averageRunTimes = new Double[12];
	public static final int BILLION = 1_000_000_000;
	public static final int MILLION = 1_000_000;
	public static int raisedTo = 10;
	public static Random r = new Random();
	public static LinkedListStack<Integer> linkedListStack = new LinkedListStack<Integer>();

	public static void main(String[] args){
		
		
		timingTestForPop();
		timingTestForPeek();
		timingTestForPush();
	}
	
	private static void timingTestForPush() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;

		for (int k = 0; k < 11; k++) {
			sum = 0;
			total = 0;
			linkedListStack.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				linkedListStack.push(i);
			}

			for (int j = 0; j < 1000000; j++) {
				long start = System.nanoTime();
				linkedListStack.push(500);
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
				linkedListStack.pop();
				
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
			System.out.println("Finished " + k);
		}
		
		System.out.println();
		System.out.println("Push Method:");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
		
	}
	
	private static void timingTestForPop() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;

		for (int k = 0; k < 11; k++) {
			sum = 0;
			total = 0;
			linkedListStack.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				linkedListStack.push(i);
			}

			for (int j = 0; j < 1000000; j++) {
				long start = System.nanoTime();
				linkedListStack.pop();
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
				linkedListStack.push(500);
				
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
			System.out.println("Finished " + k);
		}
		
		System.out.println();
		System.out.println("Pop Method:");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
		
	}
	
	private static void timingTestForPeek() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;

		for (int k = 0; k < 11; k++) {
			sum = 0;
			total = 0;
			linkedListStack.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				linkedListStack.push(i);
			}

			for (int j = 0; j < 1000000; j++) {
				long start = System.nanoTime();
				linkedListStack.peek();
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
			System.out.println("Finished " + k);
		}
		
		System.out.println();
		System.out.println("Peek Method:");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
		
	}
}
