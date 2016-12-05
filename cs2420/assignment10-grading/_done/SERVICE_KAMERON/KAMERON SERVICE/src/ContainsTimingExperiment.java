package assignment10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContainsTimingExperiment {

	private static final int ITER_COUNT = 1000;

	public static void main(String[] args) {
		 // you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		try(FileWriter fw = new FileWriter(new File("DLLExpirament.tsv"))) { //open up a file writer so we can write to file.
			Random random = new Random();
			int arrSize = 1;
			
			ArrayList<String> list = new ArrayList<String>();
//			String alph = "abcdefghijklmnopqrstuvwxyz";
//			for(int i = 0; i < 100_000; i++){
//				int num = random.nextInt(10)+1;
//				StringBuffer sb = new StringBuffer(num);
//				for(int j = 0; j < num; j++){
//					int ndx = random.nextInt(26);
//					sb.append(alph.charAt(ndx));
//				}
//				list.add(sb.toString());
//			}
			for(int exp = 100_000; exp <= 2_000_000; exp += 100_000) { // This is used as the exponent to calculate the size of the set.
				int size = exp; // or ..  
				
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				
				
				ChainingHashTable hash = new ChainingHashTable(10, new GoodHashFunctor());
				//QuadProbeHashTable hash = new QuadProbeHashTable(31, new GoodHashFunctor());
				
				String alph = "abcdefghijklmnopqrstuvwxyz";
				for(int i = 0; i < size; i++){
					int num = random.nextInt(10)+1;
					StringBuffer sb = new StringBuffer(num);
					for(int j = 0; j < num; j++){
						int ndx = random.nextInt(26);
						sb.append(alph.charAt(ndx));
					}
					hash.add(sb.toString());
				}
				//hash.addAll(list);
				
				
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
//					int findElement = random.nextInt(size); // This gets me a random int between 0 and size;
					
					// TIME IT!
					long start = System.nanoTime();
					hash.contains("Kameron");
					long stop = System.nanoTime();
					totalTime += stop - start;
					hash.clear();
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime + "\t"); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
				arrSize += 100;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		Charter charter = new Charter();
//		charter.createChart("contains_experiment.tsv", "chart.png");
	}
}
