package assignment11;

/**
 * A class for testing the run-time of the add, findMin, and deleteMin methods of the PriorityQueue
 * 
 * @author Samuel Teare | UID: u0663592
 *
 */
public class PriorityQueueTimingTest {

	public static void main(String[] args) {
		
		PriorityQueue<Integer> testPQ = new PriorityQueue<Integer>();
		
		long start = 0;
		long stop = 0;
		long total = 0;
		
		System.out.println("add()");
		for(int i = 0; i <= 100000; i++) {
			if(i % 1000 == 0) {
				start = System.nanoTime();
				testPQ.add(i);
				stop = System.nanoTime();
				total = stop - start;
				System.out.println(total);
			}
			else {
				testPQ.add(i);
			}
		}
		
		testPQ.clear();
		
		System.out.println();
		System.out.println("findMin()");
		for(int i = 0; i <= 100000; i++) {
			testPQ.add(i);
			if(i % 1000 == 0) {
				start = System.nanoTime();
				testPQ.findMin();
				stop = System.nanoTime();
				total = stop - start;
				System.out.println(total);
			}
		}
		
		testPQ.clear();
		
		System.out.println();
		System.out.println("deleteMin()");
		for(int i = 0; i <= 100000; i++) {
			testPQ.add(i);
			if(i % 1000 == 0) {
				start = System.nanoTime();
				testPQ.deleteMin();
				stop = System.nanoTime();
				total = stop - start;
				System.out.println(total / 1000.00);
				testPQ.add(i);
			}
		}
		
	}

}
