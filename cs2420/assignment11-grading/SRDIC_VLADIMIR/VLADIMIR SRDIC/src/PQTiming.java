package assignment11;

import java.util.Random;

public class PQTiming {
	
	private static final int ITER = 500;

	public static void main(String[] args) {
		PriorityQueue<Integer> pq;
		Random rand;
		int nSize;
		long start, end, totalAdd, totalFind, totalDelete;

		System.out.println("Size\tAdd\tFind\tDelete");
		for (int pow = 10; pow <= 18; pow++) {
			nSize = (int) Math.pow(2, pow);
			totalAdd = 0;
			totalFind = 0;
			totalDelete = 0;
			pq = new PriorityQueue<Integer>();
			rand = new Random();

			for (int count = 0; count < ITER; count++) {
				for (int curr = 0; curr < nSize; curr++) {
					pq.add(rand.nextInt(nSize) + 1);
				}
				
				start = System.nanoTime();
				pq.add(rand.nextInt(nSize) + 1);
				end = System.nanoTime();
				pq.deleteMin();

				totalAdd += (end - start);

				start = System.nanoTime();
				pq.findMin();
				end = System.nanoTime();

				totalFind += (end - start);

				start = System.nanoTime();
				pq.deleteMin();
				end = System.nanoTime();

				totalDelete += (end - start);
			}
			
			totalAdd /= ITER;
			totalFind /= ITER;
			totalDelete /= ITER;

			System.out.println(nSize + "\t" + totalAdd + "\t" + totalFind + "\t" + totalDelete);
		}
	}

}
