

package assignment10;

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
		
		hashFuncCompare();
		hashTableCompare();
	}
	
	public static void hashFuncCompare() {
		int iter_count = 120000;
		
		System.out.println("Begin testing..."); // print to console
		try(FileWriter fw = new FileWriter(new File("hash_func_experiment.csv"))) { //open up a file writer so we can write to file.
			
			System.out.print("\t"); // print to console
			fw.write(","); // write to file.
			System.out.print("GHF collisions\t\t"); // print to console
			fw.write("GHF collisions,"); // write to file.
			System.out.print("GHF add time\t\t"); // print to console
			fw.write("GHF add time,"); // write to file.
			System.out.print("MHF collisions\t\t"); // print to console
			fw.write("MHF collisions,"); // write to file.
			System.out.print("MHF add time\t\t"); // print to console
			fw.write("MHF add time,"); // write to file.
			System.out.print("BHF collisions\t\t"); // print to console
			fw.write("BHF collisions,"); // write to file.
			System.out.print("BHF add time\t\t"); // print to console
			fw.write("BHF add time,"); // write to file.
			System.out.print("\n"); // print to console
			fw.write("\n"); // write to file.
			
			for(int exp = 3; exp <= 14; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				
				iter_count /= 2;
				
				System.out.print(size + "\t"); // print to console
				fw.write(size + ","); // write to file.
				
				ArrayList<String> stringList = new ArrayList<String>(size);
				Random rand = new Random();
				for (int i=0; i<size; i++) {
					stringList.add(randomString(rand.nextInt(30)));
				}
				
				HashFunctor functor;
				for (int func = 0; func < 3; func++) {
					
					// select the hash functor
					switch (func) {
					case 0:
						functor = new GoodHashFunctor();
						break;
					case 1:
						functor = new MediocreHashFunctor();
						break;
					default:
						functor = new BadHashFunctor();
						break;
					}
					
					// Do the experiment multiple times, and average out the results
					long totalTime = 0;
					double collisions = 0;
					
					for (int iter = 0; iter < iter_count; iter++) {						
						
						long start;
						long stop;
						
						// SET UP!
						QuadProbeHashTable table = new QuadProbeHashTable(size*4, functor);
						
						// TIME IT!
						start = System.nanoTime();
						table.addAll(stringList);
						stop = System.nanoTime();

						collisions += (double)table.nCollisions();						
						
						totalTime += stop - start;
					}
					

					// Print the collisions
					System.out.print((int)(collisions / (double)iter_count) + "\t\t\t"); // print to console
					fw.write((int)(collisions / (double)iter_count) + ","); // write to file.
					
					double averageTime = totalTime / (double)iter_count;
					System.out.print(df.format(averageTime/1000000) + "\t\t\t"); // print to console
					fw.write(averageTime/1000000 + ","); // write to file.
				}
				
				System.out.print("\n"); // print to console
				fw.write("\n"); // write to file.

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Charter charter = new Charter();
		//charter.createChart("areAnagrams_experiment.csv", "chart_areAnagrams.png","areAnagrams() Timing Experiment", "Size (N)", "Time (ms)", "areAnagrams()");
	}
	
	public static void hashTableCompare() {
		int iter_count = 120000;
		
		System.out.println("Begin testing..."); // print to console
		try(FileWriter fw = new FileWriter(new File("hash_table_experiment.csv"))) { //open up a file writer so we can write to file.
			
			System.out.print("\t"); // print to console
			fw.write(","); // write to file.
			System.out.print("QPHT collisions\t\t"); // print to console
			fw.write("QPHT collisions,"); // write to file.
			System.out.print("QPHT add time\t\t"); // print to console
			fw.write("QPHT add time,"); // write to file.
			System.out.print("CHT collisions\t\t"); // print to console
			fw.write("CHT collisions,"); // write to file.
			System.out.print("CHT add time\t\t"); // print to console
			fw.write("CHT add time,"); // write to file.
			System.out.print("\n"); // print to console
			fw.write("\n"); // write to file.
			
			for(int exp = 5; exp <= 15; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				
				iter_count /= 2;
				
				System.out.print(size + "\t"); // print to console
				fw.write(size + ","); // write to file.
				
				ArrayList<String> stringList = new ArrayList<String>(size);
				Random rand = new Random();
				for (int i=0; i<size; i++) {
					stringList.add(randomString(rand.nextInt(30)));
				}
				
				
				for (int func = 0; func < 2; func++) {
					// Do the experiment multiple times, and average out the results
					long totalTime = 0;
					double collisions = 0;					
					
					for (int iter = 0; iter < iter_count; iter++) {						
						
						
						long start;
						long stop;
						
						// select the hash table
						switch (func) {
						case 0:
							QuadProbeHashTable table = new QuadProbeHashTable(size*2, new GoodHashFunctor());
							
							// TIME IT!
							start = System.nanoTime();
							table.addAll(stringList);
							stop = System.nanoTime();

							collisions += (double)table.nCollisions();	
							break;
						default:
							ChainingHashTable table2 = new ChainingHashTable(size*2, new GoodHashFunctor());
							// TIME IT!
							start = System.nanoTime();
							table2.addAll(stringList);
							stop = System.nanoTime();

							collisions += (double)table2.nCollisions();	
							break;
						}					
						
						totalTime += stop - start;
					}
					

					// Print the collisions
					System.out.print((int)(collisions / (double)iter_count) + "\t\t\t"); // print to console
					fw.write((int)(collisions / (double)iter_count) + ","); // write to file.
					
					double averageTime = totalTime / (double)iter_count;
					System.out.print(df.format(averageTime/1000000) + "\t\t\t"); // print to console
					fw.write(averageTime/1000000 + ","); // write to file.
				}
				
				System.out.print("\n"); // print to console
				fw.write("\n"); // write to file.

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Charter charter = new Charter();
		//charter.createChart("areAnagrams_experiment.csv", "chart_areAnagrams.png","areAnagrams() Timing Experiment", "Size (N)", "Time (ms)", "areAnagrams()");
	}
	
	// Create a random string [a-z] of specified length
	private static String randomString(int length)
	{
		// Set up the random number generator for the randomString function
		Random rand = new Random();
		
		StringBuilder stringBuilder = new StringBuilder();

		for(int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			char randomChar = (char)((int)'a' + (rand.nextInt(26)));// This will throw a null pointer! Find the bug and squash it!
			stringBuilder.append(randomChar); 
		}
		return stringBuilder.toString();
	}
}
