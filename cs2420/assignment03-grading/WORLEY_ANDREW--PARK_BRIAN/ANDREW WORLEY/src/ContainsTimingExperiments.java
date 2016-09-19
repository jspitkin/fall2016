package assignment03;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author Brian Park, Andrew Worley
 * Class for timing the contains method of the BinarySearchSet class of type int
 *
 */
public class ContainsTimingExperiments {

	private static final int ITERATIONS = 100000;
	
	public static void main(String[] args){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		try(FileWriter writer = new FileWriter(new File("contains_data.tsv"))) {
		
			Random rand = new Random();
			for(int runs = 10; runs <= 20; runs ++){
				int size = (int) Math.pow(2, runs);
				
				double timeTally = 0;
				for (int i = 0; i < ITERATIONS; i ++){
					BinarySearchSet<Integer> set = new BinarySearchSet<Integer>();
					for(int numAdds = 0 ; numAdds < size; numAdds ++){
						set.add(numAdds);
					}
					int searchItem = rand.nextInt(size);
					
					long start = System.nanoTime();
					set.contains(searchItem);
					long stop = System.nanoTime();
					timeTally += stop - start;
				}
				double averageTime = timeTally / (double)ITERATIONS;
				
				System.out.println(size + "\t" + averageTime);
				writer.write(size + "\t" + averageTime + "\n");
			
			}
		}
		catch (IOException ex){
			System.out.print("IOException!");
		}
		
		
	}
	
}
