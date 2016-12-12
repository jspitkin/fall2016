package assignment11;

import java.util.Random;

/**
 * Times the operations add, deleteMin, and findMin on a PriorityQueue
 * @author Savannah Simmons, u1086770
 *
 */
public class PriorityQueueTimer {

	public static void main(String[] args) {
		PriorityQueue<Integer> queue;
		
		int startingPow = 10;
		int steps = 15;
		
		//Print out so I know which columns are which
		System.out.println("N \t \t Get \t Delete \t Add");
		
		//Test on queues of size 2^10 to 2^24
		for(int N = (int) Math.pow(2, startingPow); N <= Math.pow(2, startingPow + steps - 1); N *= 2){
			queue = new PriorityQueue<>();
			
			Random rand = new Random();
			
			//Fill the queue with N random integers
			for (int idx = 0; idx < N; idx++){
				queue.add(rand.nextInt());
			}
			
			long sumGetNanos = 0, sumDeleteNanos = 0 , sumAddNanos = 0, startTime;
			
			int numIterations = 1_000_000;
			
			//Run operations 1,000,000 times
			for(int iter = 0; iter < numIterations; iter++){
				
				//Do all three tests on each iteration to keep queue same size
				startTime = System.nanoTime();
				queue.findMin();
				sumGetNanos += System.nanoTime() - startTime;
				
				startTime = System.nanoTime();
				queue.deleteMin();
				sumDeleteNanos += System.nanoTime() - startTime;
				
				startTime = System.nanoTime();
				queue.add(rand.nextInt());
				sumAddNanos += System.nanoTime() - startTime;
			}
			
			long avgGetNanos = sumGetNanos / numIterations;
			long avgDeleteNanos = sumDeleteNanos / numIterations;
			long avgAddNanos = sumAddNanos / numIterations;
			
			System.out.println(N + "\t \t" + avgGetNanos + "\t" + avgDeleteNanos + "\t" + avgAddNanos);
			
			
		}
	}

}
