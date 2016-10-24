package assignment07;

/**
 * @author Alex Motro
 */

import java.util.ArrayList;
import java.util.Random;

public class LinkedListStackTiming {
	public static void main(String[] args) {		
		// Spin up the CPU
		for (int i = 0; i < 1000000; i++) {
			
		}
		
		System.out.println("Push:");
		// Test Push
		for (int i = 5; i < 15; i++) {
			LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
			long time = 0;
			for (int k = 0; k <= Math.pow(2, i); k++) {
				long start = System.nanoTime();
				stack.push(k);
				long stop = System.nanoTime();
				time += stop - start;
			}
			double average = (double)time /(double) (Math.pow(2, i));
			System.out.println(average);
		}

		// Test Peek
		System.out.println("Peek:");
		for (int i = 5; i < 15; i++) {
			LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
			long time = 0;
			for (int k = 0; k <= Math.pow(2, i); k++) {
				stack.push(k);
				long start = System.nanoTime();
				stack.peek();
				long stop = System.nanoTime();
				time += stop - start;
			}
			double average = (double)time /(double)(Math.pow(2,i));
			System.out.println(average);
		}
		
		// Test Pop
				System.out.println("Pop:");
				for (int i = 5; i < 15; i++) {
					LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
					long time = 0;
					for (int j = 0; j<=Math.pow(2, i); j++){
						stack.push(j);
					}
					for (int k = 0; k <= Math.pow(2, i); k++) {
						long start = System.nanoTime();
						stack.pop();
						long stop = System.nanoTime();
						time += stop - start;
					}
					double average = (double)time/(double)(Math.pow(2, i)) ;
					System.out.println(average);
			}
	}
}
