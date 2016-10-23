package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 * This is the timer tester for the assignment
 * @author Haoran Chen
 * @uid 1060286
 */
public class TimingTester {

	private static final int ITER_COUNT = 100;

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
//		while (System.nanoTime() - startTime < 1_000_000_000);
		
			Random random = new Random();
			for(int exp = 10; exp <= 20; exp++) { 
				int size = (int) Math.pow(2, exp); 

				long totalTime = 0;
				
				for (int iter = 0; iter < ITER_COUNT; iter++) {

					LinkedListStack<Integer> dLL = new LinkedListStack();
					for(int i = 0; i < size; i++) {
						dLL.push(i);
					}
					
					
					// TIME IT!
					long start = System.nanoTime();
//					dLL.push(325);
					dLL.peek();
//					dLL.pop();
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double)ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to console
			}
	}
}
