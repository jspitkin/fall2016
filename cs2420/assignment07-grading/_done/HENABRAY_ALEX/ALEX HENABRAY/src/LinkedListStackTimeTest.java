package assignment07;

import java.util.Random;

/**
 * 
 * @author Alex Henabray (uID: u0795787), last updated 10/19/16
 * 
 * This class tests the Big-O behavior of LinkedListStack's push, pop, 
 * and peek method.
 *
 */
public class LinkedListStackTimeTest {

	private final static int BILLION = 1_000_000_000;
	
	
	public static void test() {
		
		long average = 0;
		
		Random rand = new Random();
		
		for(int n = 100; n < 5_000_000; n *= 2) {
			
			LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
			
			for(int iterations = 1; iterations <= 180; iterations++) {

				for(int item = 1; item <= n; item++) {
					stack.push(rand.nextInt(n));
				}
				
				long start = System.nanoTime();
				stack.peek();
				long end = System.nanoTime() - start;
				stack.clear();
				
				average += end;
				
			}
			
			average /= 180;
			
			double time = average / (double) BILLION;
			
			System.out.println(n + "\t" + time);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		test();
	}

}
