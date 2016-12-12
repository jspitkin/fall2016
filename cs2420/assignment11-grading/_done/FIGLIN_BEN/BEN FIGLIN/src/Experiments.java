package assignment11;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is used to perform the experiments required by the analysis document.
 * 
 * @author Ben Figlin (u1115949)
 * 
 */
public class Experiments {
	private static DecimalFormat df = new DecimalFormat();
	
	public static void main(String[] args) {
		df.setMaximumFractionDigits(4);
		
		addPerformance();
		deletePerformance();
		findMinPerformance();
	}
	
	public static void addPerformance() {
		int iter_count = 50000000;
		
		System.out.println("Begin testing..."); // print to console
		try(FileWriter fw = new FileWriter(new File("PQ_add_experiment.csv"))) { //open up a file writer so we can write to file.
			
			System.out.print("\t"); // print to console
			fw.write(","); // write to file.
			System.out.print("add time\t\t"); // print to console
			fw.write("add time,"); // write to file.
			System.out.print("\n"); // print to console
			fw.write("\n"); // write to file.
			
			for(int exp = 1; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				
				iter_count /= 2;
				
				System.out.print(size + "\t"); // print to console
				fw.write(size + ","); // write to file.
				
				ArrayList<Integer> list = new ArrayList<Integer>(size);
				Random rand = new Random();
				for (int i=0; i<size; i++) {
					list.add(rand.nextInt(size));
				}
				
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				
				for (int iter = 0; iter < iter_count; iter++) {						
					
					long start;
					long stop;
					
					// SET UP!
					PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
					
					for (Integer item : list) {
						queue.add(item);
					}
					
					// create a random integer to be inserted
					Integer randomInt = rand.nextInt(size);
					
					// TIME IT!
					start = System.nanoTime();
					queue.add(randomInt);
					stop = System.nanoTime();				
					
					totalTime += stop - start;
				}
				
				double averageTime = totalTime / (double)iter_count;
				System.out.print(df.format(averageTime/1000) + "\t\t\t"); // print to console
				fw.write(averageTime/1000 + ","); // write to file.
				
				
				System.out.print("\n"); // print to console
				fw.write("\n"); // write to file.

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Charter charter = new Charter();
		//charter.createChart("areAnagrams_experiment.csv", "chart_areAnagrams.png","areAnagrams() Timing Experiment", "Size (N)", "Time (ms)", "areAnagrams()");
	}
	
	public static void deletePerformance() {
		int iter_count = 12000000;
		
		System.out.println("Begin testing..."); // print to console
		try(FileWriter fw = new FileWriter(new File("PQ_delete_experiment.csv"))) { //open up a file writer so we can write to file.
			
			System.out.print("\t"); // print to console
			fw.write(","); // write to file.
			System.out.print("delete time\t\t"); // print to console
			fw.write("delete time,"); // write to file.
			System.out.print("\n"); // print to console
			fw.write("\n"); // write to file.
			
			for(int exp = 1; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				
				iter_count /= 2;
				
				System.out.print(size + "\t"); // print to console
				fw.write(size + ","); // write to file.
				

				
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				
				for (int iter = 0; iter < iter_count; iter++) {						
					
					long start;
					long stop;
					
					// SET UP!
					
					// create a new random list for each iteration
					ArrayList<Integer> list = new ArrayList<Integer>(size);
					Random rand = new Random();
					for (int i=0; i<size; i++) {
						list.add(rand.nextInt(size));
					}
					
					PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
					
					for (Integer item : list) {
						queue.add(item);
					}
					
					// TIME IT!
					start = System.nanoTime();
					queue.deleteMin();
					stop = System.nanoTime();				
					
					totalTime += stop - start;
				}
				
				double averageTime = totalTime / (double)iter_count;
				System.out.print(df.format(averageTime/1000) + "\t\t\t"); // print to console
				fw.write(averageTime/1000 + ","); // write to file.
				
				
				System.out.print("\n"); // print to console
				fw.write("\n"); // write to file.

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Charter charter = new Charter();
		//charter.createChart("areAnagrams_experiment.csv", "chart_areAnagrams.png","areAnagrams() Timing Experiment", "Size (N)", "Time (ms)", "areAnagrams()");
	}
	
	public static void findMinPerformance() {
		int iter_count = 300000;
		
		System.out.println("Begin testing..."); // print to console
		try(FileWriter fw = new FileWriter(new File("PQ_findMin_experiment.csv"))) { //open up a file writer so we can write to file.
			
			System.out.print("\t"); // print to console
			fw.write(","); // write to file.
			System.out.print("findMin time\t\t"); // print to console
			fw.write("findMin time,"); // write to file.
			System.out.print("\n"); // print to console
			fw.write("\n"); // write to file.
			
			for(int exp = 1; exp <= 16; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				
				iter_count /= 2;
				
				System.out.print(size + "\t"); // print to console
				fw.write(size + ","); // write to file.
				

				
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;
				
				for (int iter = 0; iter < iter_count; iter++) {						
					
					long start;
					long stop;
					
					// SET UP!
					
					// create a new random list for each iteration
					ArrayList<Integer> list = new ArrayList<Integer>(size);
					Random rand = new Random();
					for (int i=0; i<size; i++) {
						list.add(rand.nextInt(size));
					}
					
					PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
					
					for (Integer item : list) {
						queue.add(item);
					}
					
					
					// TIME IT!
					start = System.nanoTime();
					queue.findMin();
					stop = System.nanoTime();				
					
					totalTime += stop - start;
				}
				
				double averageTime = totalTime / (double)iter_count;
				System.out.print(df.format(averageTime/1000) + "\t\t\t"); // print to console
				fw.write(averageTime/1000 + ","); // write to file.
				
				
				System.out.print("\n"); // print to console
				fw.write("\n"); // write to file.

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Charter charter = new Charter();
		//charter.createChart("areAnagrams_experiment.csv", "chart_areAnagrams.png","areAnagrams() Timing Experiment", "Size (N)", "Time (ms)", "areAnagrams()");
	}
}
