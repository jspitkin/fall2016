package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * 
 * @author Prathusha Boppana (u0778008)
 *
 */

public class Timing {
	private static final int ITER_COUNT = 1000;
	
	
	public static void timeAddFirst() {
		System.out.println("ADD FIRST");
		try(FileWriter fw = new FileWriter(new File("add_first_data.csv"))) { //open up a file writer so we can write to file.
			Random random = new Random();
			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				long totalTime = 0;
				
				
				DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
				
				for (int iter = 0; iter < ITER_COUNT; iter++) {
		
					long start = System.nanoTime();
					list.addFirst(new Integer(random.nextInt()));
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + ", " + averageTime); // print to console
				fw.write(size + ", " + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void timeAdd() {
		System.out.println("ADD");
		try(FileWriter fw = new FileWriter(new File("add_data.csv"))) { //open up a file writer so we can write to file.
			Random random = new Random();
			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				
				DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
				
				long totalTime = 0;
	
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					
					long start = System.nanoTime();
					list.add(0, new Integer(random.nextInt()));
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + ", " + averageTime); // print to console
				fw.write(size + ", " + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void timeGet() {
		System.out.println("GET");
		try(FileWriter fw = new FileWriter(new File("get_data.csv"))) { //open up a file writer so we can write to file.
			Random random = new Random();
			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				
				DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
				
				for (int index = 0; index < size; index++) {
					list.add(index, new Integer(index));
				}
				
				long totalTime = 0;
	
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					
					long start = System.nanoTime();
					list.get(random.nextInt(size));
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + ", " + averageTime); // print to console
				fw.write(size + ", " + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void timeRemove() {
		System.out.println("REMOVE");
		try(FileWriter fw = new FileWriter(new File("remove_data.csv"))) { //open up a file writer so we can write to file.
			Random random = new Random();
			for(int exp = 10; exp <= 20; exp++) { // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp);
				
				DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
				
				for (int index = 0; index < size; index++) {
					list.add(index, new Integer(index));
				}
				
				long totalTime = 0;
	
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					
					long start = System.nanoTime();
					list.remove(random.nextInt(size));
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + ", " + averageTime); // print to console
				fw.write(size + ", " + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		 // you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		timeAddFirst();
		timeAdd();
		timeGet();
		timeRemove();
		
		
	}
}
