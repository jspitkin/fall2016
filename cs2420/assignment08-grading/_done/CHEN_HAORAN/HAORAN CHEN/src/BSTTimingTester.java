package assignment08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 * This is the timer tester for the assignment
 * @author Haoran Chen/Eduardo Ortiz
 * @uid 1060286/u09226248
 */
public class BSTTimingTester {

	private static final int ITER_COUNT = 100;

	static ArrayList<Double> bst = new ArrayList();
	static ArrayList<Double> jt = new ArrayList();
	
	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
//		while (System.nanoTime() - startTime < 1_000_000_000);
		
			Random random = new Random();
			for(int multiplier = 0; multiplier <= 15; multiplier++) { 
				int size = (int) 1000 + 1000 * multiplier; 

				long totalTime = 0;
				long totalTime2 = 0;
				
				
				for (int iter = 0; iter < ITER_COUNT; iter++) {

					BinarySearchTree<Integer> BST = new BinarySearchTree();
				
					//BST.addAll(generateSortedCase(size));
					TreeSet<Integer> TS = new TreeSet();

					ArrayList<Integer> a = generateRandomCase(size);
					BST.addAll(a);
					TS.addAll(a);
					//BST.writeDot("a.dot");
					

					
					// TIME IT!
					long start = System.nanoTime();
					BST.contains(size-1);
					long stop = System.nanoTime();
					totalTime += stop - start;
					
					// TIME IT!
					long start2 = System.nanoTime();
					TS.contains(size-1);
					long stop2 = System.nanoTime();
					totalTime2 += stop2 - start2;

				}
				
				double averageTime = totalTime / (double)ITER_COUNT;
				double averageTime2 = totalTime2 / (double)ITER_COUNT;
				bst.add(averageTime);
				jt.add(averageTime2);
				

			}
			for(int i = 0; i< bst.size();i++){
			System.out.println(bst.get(i)); 
			}
			System.out.println("-----------------------------------------------------");
			for(int i = 0; i< bst.size();i++){
				System.out.println(jt.get(i)); 
				}// print to console
	}
	
	/**
	 * The method provides an arraylist with integers from 1 to the size in ascending order
	 * @param size  the size of the built arraylist
	 * @return an arraylist
	 */
	public static ArrayList<Integer> generateSortedCase(int size){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 1; i <= size; i++){
			result.add(i);
		}
		return result;
		
	}
	
	/**
	 * The method provides an arraylist with integers from 1 to the size in random order
	 * @param size  the size of the built arraylist
	 * @return an arraylist
	 */
	public static ArrayList<Integer> generateRandomCase(int size){
		ArrayList<Integer> result = new ArrayList<Integer>();
		for(int i = 1; i <= size; i++){
			result.add(i);
		}
		Collections.shuffle(result);
		return result;
		
	}
}
