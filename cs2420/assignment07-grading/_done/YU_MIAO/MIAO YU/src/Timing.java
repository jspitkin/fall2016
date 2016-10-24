package assignment07;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * This class provides timer testing the time taken for areAnagrams and getLargestAnagramGroup methods.
 * 
 * @author Miao Yu
 * 
 * assignment07
 *
 */
public class Timing {
	private static final int ITER_COUNT = 100;
	

	public static void main(String[] args) {

		timer();
	}
	
	public static void timer(){
		
		long startTime = System.nanoTime();

		LinkedListStack<Integer> test = new LinkedListStack();
		
		while (System.nanoTime() - startTime < 1_000_000_000);		
		for(int exp = 5; exp <= 30; exp++) { // This is used as the exponent to calculate the size of the set.
			int size = (int) Math.pow(2, exp); // or ..  

			
			// Do the experiment multiple times, and average out the results
			long totalTime = 0;		
			long totalTimeA = 0;
			long totalTimeb = 0;
			for (int iter = 0; iter < ITER_COUNT; iter++) {
				Random r = new Random();
				test = stackSize(size);
				long start = System.nanoTime();
				test.push(r.nextInt()*10);
				long stop = System.nanoTime();
				totalTime += stop - start;
				
				long startA = System.nanoTime();
				test.peek();
				long stopA = System.nanoTime();
				totalTimeA += stopA - startA;
				
				long startb= System.nanoTime();
				test.pop();
				long stopb = System.nanoTime();
				totalTimeb += stopb - startb;
			}
			double averageTime = totalTime / (double)ITER_COUNT ;
			double averageTimeA = totalTimeA / (double)ITER_COUNT ;
			double averageTimeB = totalTimeb / (double)ITER_COUNT ;
			System.out.println(size + "\t" + averageTime + "\t"+ averageTimeA+ "\t"+ averageTimeB); // print to console
		}
	}

	
//	public static void timer() {
//		mCompare compare = new mCompare();
//		long startTime = System.nanoTime();
//		ArrayList<Integer> test = new ArrayList<>();
//
//		while (System.nanoTime() - startTime < 1_000_000_000);
//
//		for (int exp = 5; exp <= 25; exp++) { // This is used as the
//												// exponent to calculate the
//												// size of the set.
//			int size = (int) Math.pow(2, exp); // or ..
//
//			// Do the experiment multiple times, and average out the results
//			long totalTime = 0;
//			test = SortUtil.generateWorstCase(size);
//			for (int iter = 0; iter < ITER_COUNT; iter++) {
//				long start = System.nanoTime();
//				SortUtil.quicksort(test, compare);
//				long stop = System.nanoTime();
//				totalTime += stop - start;
//			}
//			double averageTime = totalTime / (double) ITER_COUNT / 1000000;
//			System.out.println(size + "\t" + averageTime); // print to
//		}
//	}
	
	
    public static ArrayList<Integer> randomArray (int size){
    	ArrayList<Integer> result = new ArrayList<>();
		Random rdm = new Random(60289);
		for(int i=0; i<size;i++){
			result.add(rdm.nextInt(1000));
		}
    	return result;
    }
    public static LinkedListStack<Integer> stackSize(int size){
    	LinkedListStack<Integer> result = new LinkedListStack();
    	for(int i=0;i<size;i++){
    		result.push(i);
    	}
		return result;   	
    }

}
