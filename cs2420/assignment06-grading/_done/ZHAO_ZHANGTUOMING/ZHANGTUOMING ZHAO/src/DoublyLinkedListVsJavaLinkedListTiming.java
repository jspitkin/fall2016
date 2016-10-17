package assignment06;

import java.util.LinkedList;

/**
 * @author Zhangtuoming Zhao
 * @UID u0847610
 * 
 * 
 * 
 * 
 *      this class is going to compare the DoublyLinkedList and Java's
 *      LinkedList
 * 
 *      which also includes the addFirst(),get(),and remove().
 */
public class DoublyLinkedListVsJavaLinkedListTiming extends DoublyLinkedList {

	public static void main(String[] args) {
		// addFirstTester();
		// getMethodTester();
		removeMethodTester();

	}

	private static void addFirstTester() {
		System.out.println(" value of list size(addFirst Method)" + "\t " + "DoublyLinkedList timing" + "\t "
				+ "Java's LinkedList timing");
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

			LinkedList<Integer> JavaLinkedList = new LinkedList<Integer>();

			for (int i = 0; i < n; i++) {
				JavaLinkedList.add(i);
			}

			long newStartTime, newMidpointTime, newStopTime;

			newStartTime = System.nanoTime();
			while (System.nanoTime() - newStartTime < 1000000000) {

			}

			newStartTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				JavaLinkedList.add(0, 11);
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
				" value of list size" + "\t  " + "DoublyLinkedList timing" + "\t " + "Java's LinkedList timing");
		int timesToLoop = 1000;
		int size = 10000;

		for (int n = 1000; n <= size; n += 1000) {
			DoublyLinkedList DoublyLinkedListLinkedList = new DoublyLinkedList();

			for (int i = 0; i < n; i++) {
				DoublyLinkedListLinkedList.addFirst(i);
			}

			long startTime, midpointTime, stopTime;

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) {
			}

			startTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				DoublyLinkedListLinkedList.get(n / 2);
			}

			midpointTime = System.nanoTime();
			for (long i = 0; i < timesToLoop; i++) {
			}

			stopTime = System.nanoTime();

			double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / timesToLoop;

			LinkedList<Integer> JavaLinkedList = new LinkedList<Integer>();

			for (int i = 0; i < n; i++) {
				JavaLinkedList.add(i);
			}

			long newStartTime, newMidpointTime, newStopTime;

			newStartTime = System.nanoTime();
			while (System.nanoTime() - newStartTime < 1000000000) {
			}

			newStartTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				JavaLinkedList.get(n / 2);
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
		System.out
				.println(" value of list size" + "\t" + "DoublyLinkedList timing" + "\t" + "Java's LinkedList timing");
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

			LinkedList<Integer> JavaLinkedList = new LinkedList<Integer>();

			for (int i = 0; i < n; i++) {
				JavaLinkedList.add(i);
			}

			long newStartTime, newMidpointTime, newStopTime;

			newStartTime = System.nanoTime();
			while (System.nanoTime() - newStartTime < 1000000000) {
																	
			}

			newStartTime = System.nanoTime();

			for (int i = 0; i < timesToLoop; i++) {
				JavaLinkedList.remove(JavaLinkedList.size() - 1);
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
