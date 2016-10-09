/**
@Author Adrian
*/
package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContainsTimingExperiment {

	private static final int ITER_COUNT = 100;
	
	public static void main(String[] args) {
		 // you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		try(FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) { //open up a file writer so we can write to file.
			Random random = new Random();
			

			for(int size = 1000; size <= 20000; size+=1000) { // This is used as the exponent to calculate the size of the set.
				//int size = (int) Math.pow(2, exp); // or ..  
				int[] temp = new int[size];
				DoublyLinkedList<String> dll = new DoublyLinkedList<String>();
				//ArrayList<Integer> dll = new ArrayList<Integer>();
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				for(int i = 0; i< size;i++){
					dll.addFirst((int) Math.ceil(Math.random() * 100)+"");
				}
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// SET UP!
					int z = 0;
					while (z < size){
					// TIME IT!
					long start = System.nanoTime();
					dll.remove(z);
					long stop = System.nanoTime();
					
					totalTime += stop - start;
					z++;
					}
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
