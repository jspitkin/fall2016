package assignment07;

import assignment06.DoublyLinkedList;

public class StackTimer {
	private static final long iter_count = 100000;

	public static void main(String[] args) {
		int size = 0;

//		System.out.println("time_peek_Data");
//		System.out.println("Size(N)\t" + "get()Time");
//		for (int exponent = 1; size < 5000; exponent+=500) {
//			size = exponent;
//			time_peek(size);
//		}
//		size=0;
//		System.out.println("time_pop_Data");
//		System.out.println("Size(N)\t" + "get()Time");
//		for (int exponent = 1; size < 5000; exponent+=500) {
//			size = exponent;
//			time_pop(size);
//		}
		size=0;
		System.out.println("time_push_Data");
		System.out.println("Size(N)\t" + "get()Time");
		for (int exponent = 1; size < 5000; exponent+=500) {
			size = exponent;
			time_push(size);
		}

	}

	private static void time_push(int size) {
		long startTimeInsertion = System.nanoTime();
		double averageTime = 0;
		long startTime = System.nanoTime();
		long stopTime;
		long totalTime = 0;

		while (System.nanoTime() - startTimeInsertion < 1000_000_000)
			;

		for (int iteration = 0; iteration < iter_count; iteration++) {
			LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
			for (int n = 1; n <= size; n++) {
				stack.push(n);
			}
			startTime = System.nanoTime();
			stack.push(size + 1);
			stopTime = System.nanoTime();
			totalTime += stopTime - startTime;
		}
		averageTime = (double) totalTime / iter_count;
		System.out.println(size + "\t" + averageTime);
	}

	private static void time_peek(int size) {
		long startTimeInsertion = System.nanoTime();
		double averageTime = 0;
		long startTime = System.nanoTime();
		long stopTime;
		long totalTime = 0;

		while (System.nanoTime() - startTimeInsertion < 1000_000_000)
			;
		for (int iteration = 0; iteration < iter_count; iteration++) {
			LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
			for (int n = 1; n <= size; n++) {
				stack.push(n);
			}
			startTime = System.nanoTime();
			stack.peek();
			stopTime = System.nanoTime();
			totalTime += stopTime - startTime;
		}
		averageTime = (double) totalTime / iter_count;
		System.out.println(size + "\t" + averageTime);
	}

	private static void time_pop(int size) {
		long startTimeInsertion = System.nanoTime();
		double averageTime = 0;
		long startTime = System.nanoTime();
		long stopTime;
		long totalTime = 0;

		while (System.nanoTime() - startTimeInsertion < 1000_000_000)
			;
		for (int iteration = 0; iteration < iter_count; iteration++) {
			LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
			for (int n = 1; n <= size; n++) {
				stack.push(n);
			}
			startTime = System.nanoTime();
			stack.pop();
			stopTime = System.nanoTime();
			totalTime += stopTime - startTime;
		}
		averageTime = (double) totalTime / iter_count;
		System.out.println(size + "\t" + averageTime);
	}

}
