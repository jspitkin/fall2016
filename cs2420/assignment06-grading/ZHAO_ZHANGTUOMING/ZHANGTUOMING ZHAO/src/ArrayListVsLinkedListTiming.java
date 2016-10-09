package assignment06;
/**
 * @author Zhangtuoming Zhao
 * @UID u0847610
 */

import java.util.ArrayList;

/**
 * 
 * it will timing the methods in the DoublyLinkedList and comparing with java's
 * array List.
 * 
 * it includes that addFirst(), get(), remove().
 * 
 * I use the kind of the same timing thoughts in the assignment 05 to write
 * these timing code.
 * 
 * the running time - loop time
 */

public class ArrayListVsLinkedListTiming extends DoublyLinkedList {
	/**
	 * 
	 * run the addfirst first to time the add method
	 */
	public static void main(String[] args) {
		// addFirstTester();
		// getMethodTester();
		removeMethodTester();

	}

	private static void addFirstTester() {
		System.out.println(" value of list size(addFirst Method)" + "\t " + "DoublyLinkedList timing" + "\t "
				+ "Java's ArrayList timing");
		int timesToLoop = 1000;
		int size = 10000;

		for (int n = 1000; n <= size; n += 1000) {
			DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();

			for (int i = 0; i < n; i++) {
				DoublyLinkedList.addFirst(i);
			}

			long startTime, midpointTime, stopTime;

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				DoublyLinkedList.addFirst(11);
			}

			midpointTime = System.nanoTime();
			for (long i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			ArrayList<Integer> arrList = new ArrayList<Integer>();

			for (int i = 0; i < n; i++) {
				arrList.add(i);
			}

			long newStartTime, newMidpointTime, newStopTime;

			newStartTime = System.nanoTime();
			while (System.nanoTime() - newStartTime < 1000000000) {

			}

			newStartTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				arrList.add(0, 11);
			}

			newMidpointTime = System.nanoTime();
			for (long i = 0; i < timesToLoop; i++) {
			}

			newStopTime = System.nanoTime();

			double newAverageTime = ((newMidpointTime - newStartTime) - (newStopTime - newMidpointTime)) / timesToLoop;

			System.out.println(
					" " + n + "\t" + "                        " + averageTime + "\t" + "           " + newAverageTime);

		}

	}

	private static void getMethodTester() {
		System.out.println(
				" value of list size" + "\t  " + "DoublyLinkedList timing" + "\t " + "Java's ArrayList timing");
		int timesToLoop = 1000;
		int size = 10000;

		for (int n = 1000; n <= size; n += 1000) {
			DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();

			for (int i = 0; i < n; i++) {
				DoublyLinkedList.addFirst(i);
			}

			long startTime, midpointTime, stopTime;

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				DoublyLinkedList.get(n / 2);
			}

			midpointTime = System.nanoTime();
			for (long i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			ArrayList<Integer> arrList = new ArrayList<Integer>(n);

			for (int i = 0; i < n; i++) {
				arrList.add(i);
			}

			long newStartTime, newMidpointTime, newStopTime;

			newStartTime = System.nanoTime();
			while (System.nanoTime() - newStartTime < 1000000000) {
			}

			newStartTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				arrList.get(n / 2);
			}

			newMidpointTime = System.nanoTime();
			for (long i = 0; i < timesToLoop; i++) {
			}

			newStopTime = System.nanoTime();

			double newAverageTime = ((newMidpointTime - newStartTime) - (newStopTime - newMidpointTime)) / timesToLoop;

			System.out.println(
					" " + n + "\t" + "                        " + averageTime + "\t" + "           " + newAverageTime);

		}
	}

	private static void removeMethodTester() {
		System.out.println(" value of list size" + "\t" + "DoublyLinkedList timing" + "\t" + "Java's ArrayList timing");
		int timesToLoop = 1000;
		int size = 10000;

		for (int n = 1000; n <= size; n += 1000) {
			DoublyLinkedList DoublyLinkedList = new DoublyLinkedList();

			for (int i = 0; i < n; i++) {
				DoublyLinkedList.addFirst(i);
			}

			long startTime, midpointTime, stopTime;

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				DoublyLinkedList.remove(DoublyLinkedList.size() - 1);
			}

			midpointTime = System.nanoTime();
			for (long i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			ArrayList<Integer> arrList = new ArrayList<Integer>(n);

			for (int i = 0; i < n; i++) {
				arrList.add(i);
			}

			long newStartTime, newMidpointTime, newStopTime;

			newStartTime = System.nanoTime();
			while (System.nanoTime() - newStartTime < 1000000000) {

			}

			newStartTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				arrList.remove(arrList.size() - 1);
			}

			newMidpointTime = System.nanoTime();
			for (long i = 0; i < timesToLoop; i++) {
			}

			newStopTime = System.nanoTime();

			double newAverageTime = ((newMidpointTime - newStartTime) - (newStopTime - newMidpointTime)) / timesToLoop;

			System.out.println(
					" " + n + "\t" + "                        " + averageTime + "\t" + "           " + newAverageTime);

		}

	}

}
