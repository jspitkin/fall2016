package assignment_06;

/**
 * Anthony Iovino 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Timing {

	
	DoublyLinkedList<Integer> list1 = new DoublyLinkedList<Integer>();
	
	
	

	
		
	public static void main(String args[]){
		
		int startPower = 8;
		int startSize = (int) Math.pow(2, startPower);
		int endSize = (int) Math.pow(2, startPower + 20);

		System.out.println("addFirst");
		// For each size of collection
		for (int size = startSize; size < endSize; size *= 2) {
			long timeSum = 0;
			int numSamples = 1000;// Do 1000 samples

			for (int x = 0; x < numSamples; x++) {
				//DoublyLinkedList<Integer> list = createList(size);
                DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
				int element = 5; 
				long startTime = System.nanoTime();
				list.addFirst(element);  //first timed method 
				long endTime = System.nanoTime();

				timeSum += endTime - startTime;
			}

			long average = timeSum / numSamples;

			System.out.println(size + "\t" + average);
		}
		

		
		
		System.out.println("get");

		
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		for (int i = 0; i<7000; i++) { // add 1000 elements to array 
		    list.addFirst(i);
		}
		
		long timeStamp = 0;
		for (int i = 0; i<7000; i++) {//start zero and increment to 1000 
		
			long startTime = System.nanoTime();
			list.get(i);
			long endTime = System.nanoTime();		
			timeStamp = endTime-startTime;
			
			
			System.out.println(i + "\t" + timeStamp);
		}
		
		System.out.println("remove");
		
		DoublyLinkedList<Integer> list2 = new DoublyLinkedList<Integer>();
		for (int i = 0; i<1000; i++) { // add 1000 elements to array 
		    list.addFirst(i*20);
		}
		
		long timeStamp2 = 0;
		for (int i = 0; i<1000; i++) {//start zero and increment to 1000 
		
			long startTime = System.nanoTime();
			list.remove(i);
			long endTime = System.nanoTime();		
			timeStamp = endTime-startTime;
			
			
			System.out.println(i + "\t" + timeStamp);
		}
		
		
		
	}
	
	
	
	public static DoublyLinkedList<Integer> createList(int size){
		DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
		Random random = new Random();
		for (int x = 0; x < size; x++) {
			list.addFirst(random.nextInt());
		}
		
		return list;

	}
}
		