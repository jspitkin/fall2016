package assignment07;

public class LinkedListStackTiming {

	public static void main(String[] args) {
		LinkedListStack<Integer> test = new LinkedListStack<Integer>();

		long startTime = 0;
		long endTime = 0;
		long totalTime = 0;

		for (int i = 1024; i < 1000000; i *= 2) {
			test = new LinkedListStack<>();
			for (int x = 0; x < i; x++) {
				test.push(x);
			}
			for (int j = 0; j < 100000000; j++) {
				startTime = System.nanoTime();
				test.push(i);
				endTime = System.nanoTime();
				totalTime += endTime - startTime;
				test.pop();

			}
			System.out.println("TotalTime at  " + i + "\t" + totalTime / 100000000);

			totalTime = 0;
		}

		System.out.println("End");
	}
}
