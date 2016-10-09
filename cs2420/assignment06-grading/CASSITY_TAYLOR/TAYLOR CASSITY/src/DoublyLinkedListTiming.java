package assignment06;

import java.util.ArrayList;
import java.util.Random;

import utilities.OutputPrint;

public class DoublyLinkedListTiming {

	private static final int ITER_COUNT = 1000;
	static int expMin = 10;
	static int expCap = 20;
	static DoublyLinkedList dll = new DoublyLinkedList();
	static DoublyLinkedList ell = new DoublyLinkedList(); //empty

	public static void main(String[] args) {
		
		OutputPrint printer = new OutputPrint();
		double[] listSize = new double[expCap - expMin+1];
		int b = -1;
		
		printer.clearFile("output.txt");
		
		for(int exp = expMin; exp <= expCap; exp++)
			listSize[++b] = (int) Math.pow(2, exp);
		
		printer.printToFile("output.txt", "list sizes", listSize);
		printer.printToFile("output.txt", "addfirst", addfirst());
		printer.printToFile("output.txt", "get", get());
		printer.printToFile("output.txt", "remove", remove());
		
		System.out.println("=====Done=====");
		
	}

	
	private static double[] remove() {
		System.out.println("=====remove Timing=====");
		long startTime = System.nanoTime();
		double[] timeArray = new double[expCap-expMin+1];
		int x = -1;
		
		//Empty warmup
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		Random random = new Random();
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int exp = expMin; exp <= expCap; exp++) { //Exponential list size
			int size = (int) Math.pow(2, exp); //Setting list size
			

			long totalTime = 0;
			
			// Do the experiment multiple times, and average out the results
			for (int iter = 0; iter < ITER_COUNT; iter++) {
				

				//Set up Array
				for(int i = 0; i < ITER_COUNT; i++) {
					//dll.add(i,random.nextInt(size));
					al.add(i);
				}
				int findElement = random.nextInt(size); // This gets me a random int between 0 and size;
				
				// TIME IT!
				long start = System.nanoTime();
				//Actual timed function
				//dll.remove(0);
				al.remove(0);
				long stop = System.nanoTime();
				
				totalTime += stop - start;
			}
			//Average time
			double averageTime = totalTime / (double)ITER_COUNT;
			timeArray[++x] = averageTime;
			System.out.println(size + "\t" + averageTime); // print to console
		}
		
		return timeArray;
	}
		

	private static double[] get() {
		System.out.println("=====get Timing=====");
		long startTime = System.nanoTime();
		double[] timeArray = new double[expCap-expMin+1];
		int x = -1;
		
		//Empty warmup
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		Random random = new Random();
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int exp = expMin; exp <= expCap; exp++) { //Exponential list size
			int size = (int) Math.pow(2, exp); //Setting list size
			int y = -1;

			long totalTime = 0;
			
			// Do the experiment multiple times, and average out the results
			for (int iter = 0; iter < ITER_COUNT; iter++) {

				//Set up Array
				for(int i = 0; i < ITER_COUNT+1; i++) {
					//dll.add(i,random.nextInt(size));
					al.add(i);
				}
				int findElement = random.nextInt(size); // This gets me a random int between 0 and size;
				
				// TIME IT!
				long start = System.nanoTime();
				//Actual timed function
				//dll.get(++y);
				al.get(++y);
				long stop = System.nanoTime();
				
				totalTime += stop - start;
			}
			//Average time
			double averageTime = totalTime / (double)ITER_COUNT;
			timeArray[++x] = averageTime;
			System.out.println(size + "\t" + averageTime); // print to console
		}
		
		return timeArray;
	}
		
	


	public static double[] addfirst(){
		
		System.out.println("=====addFirst Timing=====");
		long startTime = System.nanoTime();
		double[] timeArray = new double[expCap-expMin+1];
		int x = -1;
		int y = -1;
		//Empty warmup
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		Random random = new Random();
		ArrayList<Integer> al = new ArrayList<Integer>();
		
		for(int exp = expMin; exp <= expCap; exp++) { //Exponential list size
			int size = (int) Math.pow(2, exp); //Setting list size
			

			long totalTime = 0;
			
			// Do the experiment multiple times, and average out the results
			for (int iter = 0; iter < ITER_COUNT; iter++) {
				
				//Set up Array
				
				//Set up Array
				for(int i = 0; i < ITER_COUNT; i++) {
					//dll.add(i,random.nextInt(size));
					al.add(i);
				}
				int findElement = random.nextInt(size); // This gets me a random int between 0 and size;
				
				// TIME IT!
				long start = System.nanoTime();
				//Actual timed function
				//dll.addFirst(findElement);
				al.add(0, 0);
				long stop = System.nanoTime();
				
				totalTime += stop - start;
			}
			//Average time
			double averageTime = totalTime / (double)ITER_COUNT;
			timeArray[++x] = averageTime;
			System.out.println(size + "\t" + averageTime); // print to console
		}
		
		return timeArray;
	}
	
}
