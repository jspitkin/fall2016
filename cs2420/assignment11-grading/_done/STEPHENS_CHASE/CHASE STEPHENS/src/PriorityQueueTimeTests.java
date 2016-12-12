package assignment11;

/**
 * 
 * @author Chase Stephens
 * 
 *         Time tests for Priority Queue.
 *
 */

public class PriorityQueueTimeTests {

	private static int ITER_COUNT = 10000;

	public static void main(String[] args) {

		for (int size = 1000000; size < 20000000; size += 500000) {
			// testBestCase(size);
			// testWorstCase(size);
		//	testAverageCase(size);
	//		testFindMin(size);
			testDeleteMin(size);
		}
	}

	/**
	 * Time test for delete min. 
	 */
	private static void testDeleteMin(int size) {

		PriorityQueue test = new PriorityQueue<Integer>();

		for (int i = 0; i < size; i++) {
			test.add(i);
		}
		int x = size - 1;
		int count = 0;
		long totalTime = 0;
		for (int iter = 0; iter < ITER_COUNT; iter++) {

			long start = System.nanoTime();
			test.deleteMin();
			long stop = System.nanoTime();
			test.add(++x);

			// Averaging time
			totalTime += stop - start;
			count++;
		}

		double averageTime = totalTime / count;
		System.out.println("DeletMin" + "\t" + size + "\t" + averageTime / 1000000);
	}

	/**
	 * Time test for find min. 
	 */
	private static void testFindMin(int size) {
		PriorityQueue test = new PriorityQueue<Integer>();

		for (int i = 0; i < size; i++) {
			test.add(i);
		}
		int count = 0;
		long totalTime = 0;
		for (int iter = 0; iter < ITER_COUNT; iter++) {

			long start = System.nanoTime();
			test.findMin();
			long stop = System.nanoTime();

			// Averaging time
			totalTime += stop - start;
			count++;
		}

		double averageTime = totalTime / count;
		System.out.println("findMin" + "\t" + size + "\t" + averageTime / 1000000);

		
	}

	/**
	 * Best case time test. 
	 */
	private static void testBestCase(int size) {

		PriorityQueue test = new PriorityQueue<Integer>();

		for (int i = 0; i < size; i++) {
			test.add(i);
		}
		int x = size - 1;
		int count = 0;
		long totalTime = 0;
		for (int iter = 0; iter < ITER_COUNT; iter++) {

			long start = System.nanoTime();
			test.add(++x);
			long stop = System.nanoTime();
			test.deleteMin();

			// Averaging time
			totalTime += stop - start;
			count++;
		}

		double averageTime = totalTime / count;
		System.out.println("bestCase" + "\t" + size + "\t" + averageTime / 1000000);

	}

	/**
	 * Worst case time test. 
	 */
	private static void testWorstCase(int size) {
		PriorityQueue test = new PriorityQueue<Integer>();

		for (int i = 0; i < size; i++) {
			test.add(i);
		}
		int x = size - 1;
		int count = 0;
		long totalTime = 0;
		for (int iter = 0; iter < ITER_COUNT; iter++) {

			long start = System.nanoTime();
			test.add(-1);
			long stop = System.nanoTime();
			test.deleteMin();

			// Averaging time
			totalTime += stop - start;
			count++;
		}

		double averageTime = totalTime / count;
		System.out.println("worstCase" + "\t" + size + "\t" + averageTime / 1000000);

	}

}
