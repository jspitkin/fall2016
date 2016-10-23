package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


/**
 * TIming tests for LinkedList Stack push, pop, and peek methods
 * @author ShahidBilal Razzaq
 * u0996062
 *
 */
public class LinkedListStackTimingTests {

	private static long ITERATION_COUNT = 1000;
	
	/**
	 * Main MEthod
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter the Following\n1.Time push method\n2.Time pop method\n3.Time peek method");
		int selection = userInput.nextInt();
		if(selection==1){
			timePushMethod();
		}else if(selection==2){
			timePopMethod();
		}else if(selection==3){
			timePeekMethod();
		}else{
			System.exit(0);
		}

	}
	
	/**
	 * 
	 */
	public static void timePushMethod(){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			; // Cleans out the thread
		try (FileWriter containsExperimentFile = new FileWriter(new File("pushMethodTimingData.txt"))) {
			Random randomNumber = new Random();

			for (int exp = 10; exp <= 20; exp++) {
				int size = (int) Math.pow(2, exp);
				//Generate an empty stack
				LinkedListStack<Integer> intStack = new LinkedListStack<Integer>();

				// Generate a random Integer
				int randomInt = size;
				// Set timeers
				long totalTime = 0;

				// Time stack pushMethod
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {

					long start = System.nanoTime(); // start timer
					intStack.push(randomInt);
					long stop = System.nanoTime(); // end timer
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double) 100;

				System.out.println(size + "\t" + averageTime );
				containsExperimentFile.write(size + "\t" + averageTime );

			}
		} catch (IOException e) {
			System.err.println("Cannot Write To File!");
		}
		
	}
	
	/**
	 * 
	 */
	public static void timePopMethod(){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			; // Cleans out the thread
		try (FileWriter containsExperimentFile = new FileWriter(new File("popMethodTimingData.txt"))) {
			Random randomNumber = new Random();

			for (int exp = 10; exp <= 20; exp++) {
				int size = (int) Math.pow(2, exp);
				//Generate an empty stack
				LinkedListStack<Integer> intStack = new LinkedListStack<Integer>();

				// Generate a random Integer
				int randomInt = size;
				// Set timeers
				long totalTime = 0;
				//create a stack with lots of integers
				for (int iteration = 0; iteration < size;  iteration++) {
					intStack.push(randomInt);
				}

				// Time stack popMethod
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {

					long start = System.nanoTime(); // start timer
					intStack.pop();
					long stop = System.nanoTime(); // end timer
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double) 100;

				System.out.println(size + "\t" + averageTime );
				containsExperimentFile.write(size + "\t" + averageTime );

			}
		} catch (IOException e) {
			System.err.println("Cannot Write To File!");
		}
		
	}
	
	/**
	 * 
	 */
	public static void timePeekMethod(){
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000)
			; // Cleans out the thread
		try (FileWriter containsExperimentFile = new FileWriter(new File("peekMethodTimingData.txt"))) {
			Random randomNumber = new Random();

			for (int exp = 10; exp <= 20; exp++) {
				int size = (int) Math.pow(2, exp);
				//Generate an empty stack
				LinkedListStack<Integer> intStack = new LinkedListStack<Integer>();

				// Generate a random Integer
				int randomInt = size;
				// Set timeers
				long totalTime = 0;
				//create a stack with lots of integers
				for (int iteration = 0; iteration < size;  iteration++) {
					intStack.push(randomInt);
				}

				// Time stack popMethod
				for (int iteration = 0; iteration < ITERATION_COUNT; iteration++) {

					long start = System.nanoTime(); // start timer
					intStack.peek();
					long stop = System.nanoTime(); // end timer
					totalTime += stop - start;
				}
				double averageTime = totalTime / (double) 100;

				System.out.println(size + "\t" + averageTime );
				containsExperimentFile.write(size + "\t" + averageTime );

			}
		} catch (IOException e) {
			System.err.println("Cannot Write To File!");
		}
	}
	
	
	
	
	
	
	
	
	
	

}
