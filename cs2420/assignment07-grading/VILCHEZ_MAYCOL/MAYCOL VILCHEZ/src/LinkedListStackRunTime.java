package assignment07;

/**
 * @author maycol vilchez u0832923
 */

public class LinkedListStackRunTime {

	private static long startTime;
	private static long stopTime;

	public static void main(String[] args) {

		for (int index = 2000; index < 256_000; index *= 2)
			System.out.println(push(index));

		System.out.println();

		for (int index = 2000; index < 256_000; index *= 2)
			System.out.println(peek(index));

		System.out.println();

		for (int index = 2000; index < 256_000; index *= 2)
			System.out.println(pop(index));

	}

	public static String push(int listLength) {

		LinkedListStack<Integer> list = new LinkedListStack<>();

		long totalTime = 0;

		for (int index = 0; index < listLength; index++) {
			startTime = System.nanoTime();
			list.push(index);
			stopTime = System.nanoTime();

			totalTime += stopTime - startTime;
		}

		double averageTime = totalTime / (double) listLength;

		return "" + averageTime;

	}

	public static String peek(int listLength) {

		LinkedListStack<Integer> list = new LinkedListStack<>();

		long totalTime = 0;

		for (int index = 0; index < listLength; index++) {

			list.push(index);

		}

		for (int index = 0; index < list.size(); index++) {
			startTime = System.nanoTime();
			list.peek();
			stopTime = System.nanoTime();

			totalTime += stopTime - startTime;
			list.pop();
		}

		double averageTime = totalTime / (double) listLength;

		return "" + averageTime;

	}

	public static String pop(int listLength) {

		LinkedListStack<Integer> list = new LinkedListStack<>();

		long totalTime = 0;

		for (int index = 0; index < listLength; index++) {

			list.push(index);

		}
		startTime = System.nanoTime();
		for (int index = 0; index < list.size(); index++) {

			list.pop();

		}
		stopTime = System.nanoTime();

		totalTime += stopTime - startTime;

		double averageTime = totalTime / (double) listLength;

		return "" + averageTime;

	}

}
