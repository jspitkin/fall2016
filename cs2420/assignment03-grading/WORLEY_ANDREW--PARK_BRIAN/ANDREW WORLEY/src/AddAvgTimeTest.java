package assignment03;

import java.util.Random;

/**
 * Class finds the average time to add objects
 * to the BinarySearchSet collection
 * 
 * @author Andrew Worley / u0651238
 * @author Brian Park / u0735732
 *last update, 9/14/2016 22:58
 */
public class AddAvgTimeTest {
	
	private static final int ITERATIONS = 10;
	
	public static void main(String[]args) {
		BinarySearchSet<Integer> intergerCollection = new BinarySearchSet<Integer>();
		long start = 0;
		long end = 0;
		long total = 0;
		long numOfAdds = 1000;
		for (int n = 0; n < ITERATIONS; n++) {
			int addConstant = new Random().nextInt(1000);
			for (int i = 0; i < 10_000_000; i += 2) {
				intergerCollection.add(i);
			}
			for (int i = 0; i < numOfAdds; i++) {
				for (int j = 0; j < 1000; j++) {
					intergerCollection.add(i);
				}
				start = System.nanoTime();
				intergerCollection.add(addConstant);
				end = System.nanoTime();
				total += end - start;
				intergerCollection.remove(addConstant);
			}
			long avgNano = total / numOfAdds;
			System.out.println(avgNano+ "\t" +intergerCollection.size());
		}
		System.out.println("Test Complete");
	}
}
