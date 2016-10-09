package assignment06;

import java.util.ArrayList;
import java.util.Random;

/**
 * This class tests the running time of a DoublyLinkedList and an ArrayList.
 * 
 * @author Makenzie Peacock (u0873188)
 *
 */
public class DoublyLinkedListTimingTester {

	public static final int BILLION = 1000_000_000;

	public static int Iterations = 1000;

	public static void main(String[] args){

		addFirstTiming();
		arrayListAddTiming();
		getMethodTiming();
		arrayListGetTiming();
		removeMethodTiming();
		arrayListRemoveTiming();
	}

	/**
	 * Tests the running time of the addFirst method for a DoublyLinkedList.
	 */
	public static void addFirstTiming(){

		Random randomInt = new Random();

		DoublyLinkedList<Integer> intList = new DoublyLinkedList<Integer>();

		System.out.println("addFirst Method: ");

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			double totalTime = 0;

			for(int iterCount = 1; iterCount < size; iterCount++){
				int element = randomInt.nextInt(size);
				long startTime;
				startTime = System.nanoTime();
				intList.addFirst(element);
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / size;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
	}

	/**
	 * Tests the running time of the add method for an ArrayList.
	 */
	public static void arrayListAddTiming(){

		Random randomInt = new Random();

		ArrayList<Integer> intArrayList = new ArrayList<Integer>();

		System.out.println("ArrayList add Method: ");

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			double totalTime = 0;

			for(int iterCount = 1; iterCount < size; iterCount++){
				int element = randomInt.nextInt(size);
				long startTime;
				startTime = System.nanoTime();
				intArrayList.add(0, element);
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / size;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
		System.out.println("---------------------------------------------------");
	}

	/**
	 * Tests the running time of the get method for a DoublyLinkedList.
	 */
	public static void getMethodTiming(){

		Random randomInt = new Random();

		DoublyLinkedList<Integer> intList = new DoublyLinkedList<Integer>();

		System.out.println("LinkedList get Method: ");

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			for(int index = 0; index < size; index++){
				int element = randomInt.nextInt(size);
				intList.addFirst(element);
			}

			double totalTime = 0;

			for(int iterCount = 1; iterCount < Iterations; iterCount++){
				int index = size / 2;
				long startTime;
				startTime = System.nanoTime();
				intList.get(index);
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / Iterations;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
	}

	/**
	 * Tests the running time of the get method for an ArrayList.
	 */
	public static void arrayListGetTiming(){

		Random randomInt = new Random();

		ArrayList<Integer> intArrayList = new ArrayList<Integer>();

		System.out.println("ArrayList get Method: ");

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			for(int index = 0; index < size; index++){
				int element = randomInt.nextInt(size);
				intArrayList.add(0, element);
			}

			double totalTime = 0;

			for(int iterCount = 1; iterCount < Iterations; iterCount++){
				int index = size / 2;
				long startTime;
				startTime = System.nanoTime();
				intArrayList.get(index);
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / Iterations;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
		System.out.println("---------------------------------------------------");
	}

	/**
	 * Tests the running time of the remove method for a DoublyLinkedList.
	 */
	public static void removeMethodTiming(){

		Random randomInt = new Random();

		DoublyLinkedList<Integer> intList = new DoublyLinkedList<Integer>();

		System.out.println("LinkedList remove Method: ");

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			for(int index = 0; index < size; index++){
				int element = randomInt.nextInt(size);
				intList.addFirst(element);
			}

			double totalTime = 0;

			for(int iterCount = 1; iterCount < Iterations; iterCount++){
				int index = intList.size() / 2;
				long startTime;
				startTime = System.nanoTime();
				intList.remove(index);
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / Iterations;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
	}
	
	/**
	 * Tests the running time of the remove method for an ArrayList.
	 */
	public static void arrayListRemoveTiming(){

		Random randomInt = new Random();

		ArrayList<Integer> intArrayList = new ArrayList<Integer>();

		System.out.println("ArrayList remove Method: ");

		for(int exp=10; exp<=20; exp++ ){
			int size = (int) Math.pow(2,exp);

			for(int index = 0; index < size; index++){
				int element = randomInt.nextInt(size);
				intArrayList.add(0, element);
			}

			double totalTime = 0;

			for(int iterCount = 1; iterCount < Iterations; iterCount++){
				int index = intArrayList.size() / 2;
				long startTime;
				startTime = System.nanoTime();
				intArrayList.remove(index);
				long endTime = System.nanoTime() - startTime;
				double time = endTime / (double)BILLION;
				totalTime += time;
			}

			double averageTime = totalTime / Iterations;
			System.out.println("Size:\t" + size + "\tAverage Time:\t " + averageTime);

		}
	}
}
