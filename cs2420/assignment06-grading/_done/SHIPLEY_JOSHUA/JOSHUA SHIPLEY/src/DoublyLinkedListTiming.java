package assignment06;

import java.util.ArrayList;
import java.util.Random;

public class DoublyLinkedListTiming {

	private static Random rand = new Random(919708);
	
	private static final int REP_COUNT = 100000;
	private static final int LIST_SIZE = 1024;
	private static final double MAX_SIZE = Math.pow(2, 20);
	
	public static void main(String[] args) {
		//testAddFirst();
		//testGet();
		testRemove();
	}
	
	/**
	 * Fills the given list with randomly selected Integers until it reaches the given size
	 * @param list - The list to be filled
	 * @param size - The desired size of the list
	 */
	private static void buildRandomList(DoublyLinkedList<Integer> list, int size){
		for(int i = 0; i < size; i++){
			list.addLast(rand.nextInt());
		}
	}
	
	/**
	 * Times the average time of DoublyLinkedList's addFirst function and compares it to the average time of adding to the front of Java's ArrayList
	 */
	public static void testAddFirst(){
		System.out.println("AddFirst() Timings");
		System.out.println("Size" + "\t" + "Linked List" + "\t" + "Array List");
		
		for (int size = LIST_SIZE; size < MAX_SIZE; size *= 2) {
			DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
			ArrayList<Integer> checkList = new ArrayList<Integer>();
			buildRandomList(testList, size);
			
			for(Integer i : testList){
				checkList.add(i);
			}
			
			int nextInt;
			long startTime, endTime;
			ArrayList<Long> linkedListTimings = new ArrayList<Long>();
			ArrayList<Long> arrayListTimings = new ArrayList<Long>();
			for (int i = 0; i < REP_COUNT; i++) {
				nextInt = rand.nextInt();
				startTime = System.nanoTime();
				testList.addFirst(nextInt);
				endTime = System.nanoTime();

				linkedListTimings.add(endTime - startTime);
				
				startTime = System.nanoTime();
				checkList.add(0, nextInt);
				endTime = System.nanoTime();
				
				arrayListTimings.add(endTime - startTime);
			}
			long sum = 0;
			for (int i = 0; i < REP_COUNT; i++) {
				sum += linkedListTimings.get(i);
			}
			long linkedListAvg = sum / REP_COUNT;
			
			sum = 0;
			for(int i = 0; i < REP_COUNT; i++){
				sum += arrayListTimings.get(i);
			}
			long arrayListAvg = sum / REP_COUNT;
			
			System.out.println(size + "\t" + linkedListAvg + "\t" + arrayListAvg);
		}
		
	}
	
	/**
	 * Times the average time it takes to look up a randomly selected index within a DoublyLinkedList compared to Java's ArrayList
	 */
	public static void testGet(){
		System.out.println("Get() Timings");
		System.out.println("Size" + "\t" + "Linked List" + "\t" + "Array List");
		
		for (int size = LIST_SIZE; size < MAX_SIZE; size *= 2) {
			DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
			ArrayList<Integer> checkList = new ArrayList<Integer>();
			buildRandomList(testList, size);
			
			for(int i = 0; i < size; i++){
				checkList.add(testList.get(i));
			}
			
			int nextInt;
			long startTime, endTime;
			ArrayList<Long> linkedListTimings = new ArrayList<Long>();
			ArrayList<Long> arrayListTimings = new ArrayList<Long>();
			for (int i = 0; i < REP_COUNT; i++) {
				nextInt = rand.nextInt(testList.size());
				startTime = System.nanoTime();
				testList.get(nextInt);
				endTime = System.nanoTime();

				linkedListTimings.add(endTime - startTime);
				
				startTime = System.nanoTime();
				checkList.get(nextInt);
				endTime = System.nanoTime();
				
				arrayListTimings.add(endTime - startTime);
			}
			long sum = 0;
			for (int i = 0; i < REP_COUNT; i++) {
				sum += linkedListTimings.get(i);
			}
			long linkedListAvg = sum / REP_COUNT;
			
			sum = 0;
			for(int i = 0; i < REP_COUNT; i++){
				sum += arrayListTimings.get(i);
			}
			long arrayListAvg = sum / REP_COUNT;
			
			System.out.println(size + "\t" + linkedListAvg + "\t" + arrayListAvg);
		}
		
	}
	
	/**
	 * Times the average amount of time it takes to remove a random element from DoublyLinkedList and compares it to that of removing from an
	 * identical ArrayList
	 */
	public static void testRemove(){
		System.out.println("Remove() Timings");
		System.out.println("Size" + "\t" + "Linked List" + "\t" + "Array List");
		
		for (int size = LIST_SIZE; size < MAX_SIZE; size *= 2) {
			DoublyLinkedList<Integer> testList = new DoublyLinkedList<Integer>();
			ArrayList<Integer> checkList = new ArrayList<Integer>();
			buildRandomList(testList, size);
			
			for(int i = 0; i < size; i++){
				checkList.add(testList.get(i));
			}
			
			int nextInt, removedInt;
			long startTime, endTime;
			ArrayList<Long> linkedListTimings = new ArrayList<Long>();
			ArrayList<Long> arrayListTimings = new ArrayList<Long>();
			for (int i = 0; i < REP_COUNT; i++) {
				nextInt = rand.nextInt(testList.size());
				removedInt = checkList.get(nextInt);
				startTime = System.nanoTime();
				testList.remove(nextInt);
				endTime = System.nanoTime();

				linkedListTimings.add(endTime - startTime);
				testList.addLast(removedInt);
				
				startTime = System.nanoTime();
				checkList.remove(nextInt);
				endTime = System.nanoTime();
				
				arrayListTimings.add(endTime - startTime);
				checkList.add(removedInt);
			}
			long sum = 0;
			for (int i = 0; i < REP_COUNT; i++) {
				sum += linkedListTimings.get(i);
			}
			long linkedListAvg = sum / REP_COUNT;
			
			sum = 0;
			for(int i = 0; i < REP_COUNT; i++){
				sum += arrayListTimings.get(i);
			}
			long arrayListAvg = sum / REP_COUNT;
			
			System.out.println(size + "\t" + linkedListAvg + "\t" + arrayListAvg);
		}
		
	}

}
