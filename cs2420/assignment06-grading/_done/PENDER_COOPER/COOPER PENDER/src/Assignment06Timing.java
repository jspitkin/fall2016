package assignment06;

import java.util.ArrayList;
import java.util.Random;


/**
 * This class is used to time several of
 * the methods of a doubly linked list and
 * also compare those methods to similear
 * ones in an array list.
 * 
 * @author Cooper Pender (u0843147)
 * 
 * Last Edited On: 10/03/16
 */
public class Assignment06Timing {

	private static final int iteration_amount = 100; //Number of tests to be completed in a set size.

	public static void main(String[] args) {
		
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000); //Used to get the machine going.
		
			for(int power = 10; power <= 20; power++) { //Test sizes from 2^10 to 2^20 where "power" is the exponent.
				
				int size = (int)Math.pow(2, power); //Size of data set being created. 
				
				//Averaging out over several tests.
				long totalTime = 0;
				
				Random rand = new Random(); //used to add a random element to a list or an arraylist.
				
				for (int tests = 0; tests < iteration_amount; tests++) {
					
//					ArrayList<Integer> testArr = new ArrayList<Integer>(); //Arraylist used to test against a doubly linked list.
//					
//					for(int i = 0; i < size; i++) {
//						testArr.add(i);
//					}
					
					DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>(); //doubly linked list used for testing.
					
					for(int i = 0; i < size; i++) {
						testList.add(i, i);
					}
					
					int randItem = rand.nextInt(size);
					//timing portion.
					long start = System.nanoTime();
					testList.remove(randItem);
					//testArr.remove(randItem);
					long stop = System.nanoTime();
					totalTime += stop - start;
				}
				double averageTime = (totalTime / (double)iteration_amount); //taking the average time for the set.
				System.out.println(size + "\t" + averageTime); //print this data to the console.
		}
	}
}
	
