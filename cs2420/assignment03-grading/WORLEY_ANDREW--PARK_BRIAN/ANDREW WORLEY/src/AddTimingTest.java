package assignment03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
/**
 * 
 * 
 * This is a performance measuring class for the add method of the BinarySearchSet<E> class that measures
 * the speed of the add method many times and averages them.
 * These numbers are then written to a text file.
 * 
 * @author Andrew Worley / u0651238
 * @author Brian Park / u0735732
 *
 */
public class AddTimingTest {
	private static final int ITERATIONS = 100;

	public static void main(String[] args) {

		try (FileWriter writer = new FileWriter(new File("add_data.tsv"))) {

			Random rand = new Random();
			for (int runs = 10; runs < 20; runs++) {
				int size = (int) Math.pow(2, runs);

				double timeTally = 0;
				for (int i = 0; i < ITERATIONS; i++) {
					BinarySearchSet<Integer> set = new BinarySearchSet<Integer>();
					for (int numAdds = 0; numAdds < size; numAdds++) {
						long start = System.nanoTime();
						set.add(numAdds);
						long stop = System.nanoTime();
						timeTally += stop - start;
					}
					int searchIndex = rand.nextInt(size);

				}
				double averageTime = timeTally / (double) ITERATIONS;

				System.out.println(size + "\t" + averageTime);
				writer.write(size + "\t" + averageTime + "\n");

			}
		} catch (IOException ex) {
			System.out.print("IOException!");
		}

	}
}
