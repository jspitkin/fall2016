package assignment07;

import java.util.Random;

/**
 * This class gives the timing data of methods from the LinkedListStack class.
 * 
 * @author Makenzie Peacock (u0873188)
 */
public class LinkedListStackTimingTester {
	public static final int BILLION = 1000_000_000;

	public static double numOfIterations = 1000;

	public static void main(String[] args){
		//pushTiming();
		//popTiming();
		peekTiming();
	}

	/**
	 * Tests the timing of the push method.
	 */
	public static void pushTiming(){
		LinkedListStack<Character> testStack = new LinkedListStack<Character>();
		
		Random random = new Random();

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			double totalTime = 0;
			
			for(int index = 0; index < size; index++){
				testStack.push((char) random.nextInt(size));
			}

			for(int iterCount = 1; iterCount < numOfIterations; iterCount++){
				long startTime;
				startTime = System.nanoTime();
				testStack.push('1');
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / numOfIterations;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
	}
	
	/**
	 * Tests the timing of the pop method.
	 */
	public static void popTiming(){
		LinkedListStack<Character> testStack = new LinkedListStack<Character>();
		
		Random random = new Random();

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			double totalTime = 0;
			
			for(int index = 0; index < size; index++){
				testStack.push((char) random.nextInt(size));
			}

			for(int iterCount = 1; iterCount < numOfIterations; iterCount++){
				long startTime;
				startTime = System.nanoTime();
				testStack.pop();
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / numOfIterations;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
	}
	
	/**
	 * Tests the timing of the peek method.
	 */
	public static void peekTiming(){
		LinkedListStack<Character> testStack = new LinkedListStack<Character>();
		
		Random random = new Random();

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			double totalTime = 0;
			
			for(int index = 0; index < size; index++){
				testStack.push((char) random.nextInt(size));
			}

			for(int iterCount = 1; iterCount < numOfIterations; iterCount++){
				long startTime;
				startTime = System.nanoTime();
				testStack.peek();
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / numOfIterations;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
	}
}
