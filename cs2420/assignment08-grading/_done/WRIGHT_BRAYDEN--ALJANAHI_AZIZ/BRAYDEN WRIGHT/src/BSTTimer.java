package assignment08;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Times 'addAll and 'containsAll' in our BinarySearchTree
 *
 * @author Brayden Wright (u0895942), Abdulaziz Aljanahi (u0901606)
 */
public class BSTTimer {
	public static void main(String[] args) {
		long startTime, duration, endTime;
		BinarySearchTree<Integer> temp = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> tempBalanced = new BinarySearchTree<Integer>();
		ArrayList<Integer> nums = new ArrayList<Integer>();

		// Warm up JavaVM
		long pause = System.nanoTime();
		while (System.nanoTime() - pause < 5_000_000_000L);

//		System.out.println("Sorted");
		System.out.println("Random");
		int inc = 5_000;
		int arraySize = 100_000;
		for (int x = 1; x < 32; x++) {

			nums.clear();
			temp.clear();
			for (int i = 1; i <= arraySize; i++) // creates an ArrayList of numbers
				nums.add(i);

			Collections.shuffle(nums);  // comment out for sorted order
			startTime = System.nanoTime();
			temp.addAll(nums);
			endTime = System.nanoTime();
			duration = (endTime - startTime) / arraySize;

//			Collections.shuffle(nums);
//			startTime = System.nanoTime();
//			temp.containsAll(nums);
//			endTime = System.nanoTime();
//			duration = (endTime - startTime) / arraySize;
//
//			System.out.println("ContainsAll " + (x) + ": it took " + duration
//					+ " nanoseconds, to contains all " + arraySize + "(2084+"
//					+ inc + ") items");
//			inc += 500;

			System.out.println(arraySize + "\t" + duration);
			arraySize += inc;
		}

//		 temp.writeDot("test1.dot");

	}

}