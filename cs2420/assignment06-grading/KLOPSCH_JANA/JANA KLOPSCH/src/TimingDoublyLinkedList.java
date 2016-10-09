package assignment06;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class TimingDoublyLinkedList {

	public static final int BILLION = 1000_000_000;
	static DoublyLinkedList<Integer> intList = new DoublyLinkedList<Integer>();
	static ArrayList<Integer> integerList = new ArrayList<Integer>();
	static LinkedList<Integer> intlinked = new LinkedList<Integer>();

	public static void timeAddFirst(int size, String listType) {

		long startTime = 0;
		long endTime = 0;
		long averageTime = 0;
		int iterations = 5000;

		if (listType.equals("linked")){
			for(int index = 0; index < size; index++){ //Create a doubly linked list of size "size"
				intList.add(index, index);
			}
		}
		else{
			for(int index = 0; index < size; index++){
				//integerList.add(index);
				intlinked.add(index, (Integer) index);
			}
		}
		
		if (listType.equals("linked")){
			for(int data = 0; data < iterations; data++){
				startTime = System.nanoTime();
				intList.addFirst(2);
				endTime = System.nanoTime() - startTime;
				averageTime += endTime;
				intList.removeLast();//Remove an item to keep the list size the same
			}
		}
		else{
			for(int data = 0; data < iterations; data++){
				startTime = System.nanoTime();
				//integerList.add(0, 4);
				intlinked.add(0, (Integer) 11);
				endTime = System.nanoTime() - startTime;
				averageTime += endTime;
				//integerList.remove(size-1);//Remove an item to keep the array size the same
				intlinked.removeLast();
			}
		}
		averageTime /= iterations;
		double time = averageTime / (double) BILLION;

		if (listType.equals("linked")){
			System.out.println("addFirst time for linked list size " + size + " is " + time + " seconds");
		}
		else{
			System.out.println("add(0,item) time for LinkedList size " + size + " is " + time + " seconds");
			//System.out.println("add(0,item) time for ArrayList size " + size + " is " + time + " seconds");
		}

	}
	
	public static void timeGet(int size, String listType){
		long startTime = 0;
		long endTime = 0;
		long averageTime = 0;
		int iterations = 5000;

		if (listType.equals("linked")){
			for(int index = 0; index < size; index++){ //Create a doubly linked list of size "size"
				intList.add(index, index);
			}
		}
		else{
			for(int index = 0; index < size; index++){ //Create an ArrayList of size "size"
				//integerList.add(index);
				intlinked.add(index, (Integer) index);
			}
		}
		Random rand = new Random();
		if (listType.equals("linked")){
			for(int data = 0; data < iterations; data++){
				int itemIndex = rand.nextInt(size);
				startTime = System.nanoTime();
				intList.get(itemIndex);
				endTime = System.nanoTime() - startTime;
				averageTime += endTime;
			}
		}
		else{
			for(int data = 0; data < iterations; data++){
				int itemIndex = rand.nextInt(size);
				startTime = System.nanoTime();
				//integerList.get(itemIndex);
				intlinked.get(itemIndex);
				endTime = System.nanoTime() - startTime;
				averageTime += endTime;
			}
		}
		averageTime /= iterations;
		double time = averageTime / (double) BILLION;

		if (listType.equals("linked")){
			System.out.println("get(i) time for linked list size " + size + " is " + time + " seconds");
		}
		else{
			//System.out.println("get(i) time for ArrayList size " + size + " is " + time + " seconds");
			System.out.println("get(i) time for LinkedList size " + size + " is " + time + " seconds");
		}
	}
	
	public static void timeRemove(int size, String listType){
		long startTime = 0;
		long endTime = 0;
		long averageTime = 0;
		int iterations = 5000;

		if (listType.equals("linked")){
			for(int index = 0; index < size; index++){ //Create a doubly linked list of size "size"
				intList.add(index, index);
			}
		}
		else{
			for(int index = 0; index < size; index++){ //Create an ArrayList of size "size"
				//integerList.add(index);
				intlinked.add(index, (Integer) index);
			}
		}
		Random rand = new Random();
		if (listType.equals("linked")){
			for(int data = 0; data < iterations; data++){
				int itemIndex = rand.nextInt(size);
				startTime = System.nanoTime();
				intList.remove(itemIndex);
				endTime = System.nanoTime() - startTime;
				averageTime += endTime;
				intList.addFirst(3); //Add another item to keep the list size the same
			}
		}
		else{
			for(int data = 0; data < iterations; data++){
				int itemIndex = rand.nextInt(size);
				startTime = System.nanoTime();
				//integerList.remove(itemIndex);
				intlinked.remove(itemIndex);
				endTime = System.nanoTime() - startTime;
				averageTime += endTime;
				//integerList.add(size-1, 5); //Add another item to keep the array size the same
				intlinked.add(size-1, 5);
			}
		}
		averageTime /= iterations;
		double time = averageTime / (double) BILLION;

		if (listType.equals("linked")){
			System.out.println("get(i) time for linked list size " + size + " is " + time + " seconds");
		}
		else{
			//System.out.println("get(i) time for ArrayList size " + size + " is " + time + " seconds");
			System.out.println("get(i) time for LinkedList size " + size + " is " + time + " seconds");
		}
	}

	
	
	public static void main(String [] args){
		//Timing for addFirst
//		for(int listSize = 5000; listSize <= 80_000; listSize*=2){
//			timeAddFirst(listSize, "linked");
//		}
//		System.out.println();
//		for(int listSize = 5000; listSize <= 80_000; listSize*=2){
//			//timeAddFirst(listSize, "ArrayList");
//			timeAddFirst(listSize, "LinkedList");
//		}
		
		//Timing for get(i), using random values of i
//		for(int listSize = 5000; listSize <= 80_000; listSize*=2){
//			timeGet(listSize, "linked");
//		}
//		System.out.println();
//		for(int listSize = 5000; listSize <= 80_000; listSize*=2){
//			//timeGet(listSize, "ArrayList");
//			timeGet(listSize, "LinkedList");
//		}
		
		//Timing for remove(i), using random values of i
		for(int listSize = 5000; listSize <= 80_000; listSize*=2){
			timeRemove(listSize, "linked");
		}
		System.out.println();
		for(int listSize = 5000; listSize <= 80_000; listSize*=2){
			timeRemove(listSize, "ArrayList");
			//timeRemove(listSize, "LinkedList");
		}
		
	}
}
