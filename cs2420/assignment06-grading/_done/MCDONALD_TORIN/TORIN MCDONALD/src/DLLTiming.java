package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 * 
 * @author Torin McDonald
 * @uid u0940253
 *
 */
public class DLLTiming {

	private static final int ITER_COUNT = 1000;

	public static void main(String[] args) {

		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);

		try(FileWriter fw = new FileWriter(new File("addFirstDLL_exp.tsv"))) { //open up a file writer so we can write to file.
			//Random random = new Random();

			for(int exp = 10; exp <= 14; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
					for(int i = 0; i < size; i++) {
						long start = System.nanoTime();
						list.addFirst(i);;
						long stop = System.nanoTime();
						totalTime+=stop-start;
					}

				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter = new Charter();
		charter.setTitles("addFirstDLL");
		charter.createChart("addFirstDLL_exp.tsv", "addFirstDLL.png");


		try(FileWriter fw = new FileWriter(new File("addFirstAL_exp.tsv"))) { //open up a file writer so we can write to file.
			//Random random = new Random();

			for(int exp = 10; exp <= 25000; exp*=2) { // This is used as the exponent to calculate the size of the set.
				int size = exp; // or ..  

				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					ArrayList<Integer> list = new ArrayList<Integer>();
					for(int i = 0; i < size; i++) {
						long start = System.nanoTime();
						list.add(0,i);;
						long stop = System.nanoTime();
						totalTime+=stop-start;
					}

				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter2 = new Charter();
		charter2.setTitles("addFirstArrayList");
		charter2.createChart("addFirstAL_exp.tsv", "addFirstAL.png");
		
		
		try(FileWriter fw = new FileWriter(new File("getAL_exp.tsv"))) { //open up a file writer so we can write to file.
			Random random = new Random();

			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					ArrayList<Integer> list = new ArrayList<Integer>();
					for(int i = 0; i < size; i++) {
						list.add(i);
					}
					int findElement = random.nextInt(size); // This gets me a random int between 0 and size;


					long start = System.nanoTime();
					list.get(findElement);
					long stop = System.nanoTime();
					totalTime+=stop-start;


				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter3 = new Charter();
		charter3.setTitles("get()AL");
		charter3.createChart("getAL_exp.tsv", "getAL.png");
		
		
		try(FileWriter fw = new FileWriter(new File("getDLL_exp.tsv"))) { //open up a file writer so we can write to file.
			Random random = new Random();

			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
					for(int i = 0; i < size; i++) {
						list.addFirst(i);
					}
					int findElement = random.nextInt(size); // This gets me a random int between 0 and size;


					long start = System.nanoTime();
					list.get(findElement);
					long stop = System.nanoTime();
					totalTime+=stop-start;


				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter4 = new Charter();
		charter4.setTitles("get()DLL");
		charter4.createChart("getDLL_exp.tsv", "getDLL.png");
		try(FileWriter fw = new FileWriter(new File("removeAL_exp.tsv"))) { //open up a file writer so we can write to file.
			Random random = new Random();

			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					ArrayList<Integer> list = new ArrayList<Integer>();
					for(int i = 0; i < size; i++) {
						list.add(i);
					}
					int findElement = random.nextInt(size); // This gets me a random int between 0 and size;


					long start = System.nanoTime();
					list.remove(findElement);
					long stop = System.nanoTime();
					totalTime+=stop-start;


				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter5 = new Charter();
		charter5.setTitles("remove()AL");
		charter5.createChart("removeAL_exp.tsv", "removeAL.png");
		
		
		try(FileWriter fw = new FileWriter(new File("removeDLL_exp.tsv"))) { //open up a file writer so we can write to file.
			Random random = new Random();

			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..  
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
					for(int i = 0; i < size; i++) {
						list.addFirst(i);
					}
					int findElement = random.nextInt(size); // This gets me a random int between 0 and size;


					long start = System.nanoTime();
					list.remove(findElement);
					long stop = System.nanoTime();
					totalTime+=stop-start;


				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter6 = new Charter();
		charter6.setTitles("removeDLL)DLL");
		charter6.createChart("removeDLL_exp.tsv", "removeDLL.png");
		
	}
}




