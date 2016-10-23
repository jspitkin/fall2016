/**
 * @author Scott Krstyen
 * UNID = U0760822
 */
package assignment06;

import java.util.ArrayList;
import java.util.Random;

public class DoublyLinkedListTimingTests {
	
	static Double[] averageRunTimes = new Double[12];
	public static final int BILLION = 1_000_000_000;
	public static final int MILLION = 1_000_000;
	public static int raisedTo = 10;
	public static DoublyLinkedList<Integer> myList = new DoublyLinkedList<Integer>();
	public static Random r = new Random();
	
	public static void main(String[] args){
		
		timingTestForAddFirst();
		timingTestForArrayListAddAtZeroIndex();
		timingTestForArrayListGet();
		timingTestForGet();
		timingTestForArrayRemove();
		timingTestForDoublyLinkedListRemove();
		
	}
	/**
	 * Timing tests for DoublyLinkedList Remove
	 */
	private static void timingTestForDoublyLinkedListRemove() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;

		for (int k = 0; k < 11; k++) {
			sum = 0;
			total = 0;
			myList.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				myList.addLast(i);
			}

			for (int j = 0; j < 100000; j++) {
				int value = r.nextInt((int)(Math.pow(2, raisedTo)));
				long start = System.nanoTime();
				myList.remove(value);
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
				myList.addLast(5);
				
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
			System.out.println("Finished " + k);
		}
		
		System.out.println("Get Method:");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
		
	}
	/**
	 * Timing Tests for ArrayList Remove
	 */
	private static void timingTestForArrayRemove() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;
		
		ArrayList myArrayList = new ArrayList();

		for (int k = 0; k < 11; k++) {
			sum = 0;
			total = 0;
			myArrayList.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				myArrayList.add(i);
			}

			for (int j = 0; j < (100000); j++) {
				int value = r.nextInt((int)(Math.pow(2, raisedTo)));
				long start = System.nanoTime();
				myArrayList.remove(value);
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
				myArrayList.add(5);
				
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
			System.out.println("Finished " + k);
		}
		
		System.out.println("ArrayList Remove Method");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
	}
	/**
	 * Timing tests for DoublyLinkedList AddFirst
	 */
	private static void timingTestForAddFirst() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;

		for (int k = 0; k < 11; k++) {
			sum = 0;
			total = 0;
			myList.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				myList.addLast(i);
			}

			for (int j = 0; j < (MILLION); j++) {
				long start = System.nanoTime();
				myList.addFirst(5);
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
				myList.removeFirst();
				
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
		}
		
		System.out.println("AddFirst Method:");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
		
	}
	/**
	 * Timing Tests for ArrayList Add
	 */
	private static void timingTestForArrayListAddAtZeroIndex() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;
		
		ArrayList myArrayList = new ArrayList();

		for (int k = 0; k < 11; k++) {
			sum = 0;
			total = 0;
			myArrayList.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				myArrayList.add(i);
			}

			for (int j = 0; j < (MILLION); j++) {
				long start = System.nanoTime();
				myArrayList.add(0, 5);
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
				myArrayList.remove(0);
				
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
			System.out.println("Finished " + k);
		}
		
		System.out.println("ArrayList Add At First Index Method");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
		
	}
	/**
	 * Timing tests for DoublyLinkedList Get
	 */
	private static void timingTestForGet() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;

		for (int k = 0; k < 8; k++) {
			sum = 0;
			total = 0;
			myList.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				myList.addLast(i);
			}

			for (int j = 0; j < (MILLION); j++) {
				int value = r.nextInt((int)(Math.pow(2, raisedTo)));
				long start = System.nanoTime();
				myList.get(value);
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
				
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
			System.out.println("Finished " + k);
		}
		
		System.out.println("Get Method:");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
		
	}
	/**
	 * Timing Tests for ArrayList Get
	 */
	private static void timingTestForArrayListGet() {
		raisedTo = 10;
		double sum = 0;
		double total = 0;
		int iterations = MILLION;
		
		ArrayList myArrayList = new ArrayList();

		for (int k = 0; k < 8; k++) {
			sum = 0;
			total = 0;
			myArrayList.clear();

			for (int i = 0; i < (Math.pow(2, raisedTo)); i++) {
				myArrayList.add(i);
			}

			for (int j = 0; j < (MILLION); j++) {
				int value = r.nextInt((int)(Math.pow(2, raisedTo)));
				long start = System.nanoTime();
				myArrayList.get(value);
				long end = System.nanoTime() - start;
				double time = ((double) end) / ((double) BILLION);
				sum += time;
				total++;
				
			}

			averageRunTimes[k] = (sum / total);
			raisedTo++;
			System.out.println("Finished " + k);
		}
		
		System.out.println("ArrayList Get Method");
		System.out.println();
		for(int i = 0; i < 11; i++){
			System.out.println(averageRunTimes[i]);
		}
		System.out.println();
		
	}
	
}
