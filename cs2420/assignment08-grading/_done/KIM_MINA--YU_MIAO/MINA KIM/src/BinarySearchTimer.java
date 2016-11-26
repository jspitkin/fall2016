package assignment08;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import assignment07.LinkedListStack;

/**
 * A small demonstration of the SpellChecker class.
 * 
 * @author u1054673 Mina Kim
 */

public class BinarySearchTimer {

	/**
	 * Question four
	 */
	
	public static void main(String[] args) 
	{
		BinarySearchTree<Integer> timing = new BinarySearchTree<Integer>();
		TreeSet<Integer> timingUtil = new TreeSet<Integer>();
		Random randInt = new Random();

		int rand = 0;
		long startTime = 0;
		long stopTime = 0;
		long timeDiff = 0;
		long timeTotal = 0;
		int size = 10000;
		
		
		// Add N items to a TreeSet in a random order
		for (int idx = 1; idx < size; idx *= 2)
		{
			rand = randInt.nextInt(size);
			while (timingUtil.contains(rand))
				rand = randInt.nextInt(size);
			
			// Record the time required to do this
			startTime = System.nanoTime();
			timingUtil.add(rand);
			stopTime = System.nanoTime();
			timeDiff = stopTime - startTime;
			System.out.println(idx + "\t" + timeDiff);
		}
			
		System.out.print("\n");
		
		// Record the time required to invoke the contains method for each item in the TreeSe
		for (int idx = 1; idx < size; idx *= 2)
		{
			startTime = System.nanoTime();
			timingUtil.contains(idx);
			stopTime = System.nanoTime();
			timeDiff = stopTime - startTime;
			System.out.println(idx + "\t" + timeDiff);
		}
		
		System.out.print("\n");
		
		// Add the same N items (in the same random order) as in #1 to a Binary Search Tree
		for (int idx = 1; idx < size; idx *= 2)
		{
			rand = randInt.nextInt(size);
			while (timing.contains(rand))
				rand = randInt.nextInt(size);
			
			// Record the time required to do this
			startTime = System.nanoTime();
			timing.add(rand);
			stopTime = System.nanoTime();
			timeDiff = stopTime - startTime;
			System.out.println(idx + "\t" + timeDiff);
		}

		System.out.print("\n");
					
		// Record the time required to invoke the contains method for each item in the BinarySearchTree
		for (int idx = 1; idx < size; idx *= 2)
		{
			startTime = System.nanoTime();
			timing.contains(idx);
			stopTime = System.nanoTime();
			timeDiff = stopTime - startTime;
			System.out.println(idx + "\t" + timeDiff);
		}



		/**
		 * Question Three
		 */

		//
		//		public static void main(String[] args) 
		//		{
		//			BinarySearchTree<Integer> timing = new BinarySearchTree<Integer>();
		//			Random randInt = new Random();
		//			
		//			int rand = 0;
		//			long startTime = 0;
		//			long stopTime = 0;
		//			long timeDiff = 0;
		//			long timeTotal = 0;
		//			int size = 15000;
		//
		//			// Add N items to a BST in sorted order
		//			for (int idx = 1; idx < size; idx++)
		//				timing.add(idx);
		//			
		//			// Record the time required to invoke the contains method for each item in the BST.
		//			for (int idx = 1; idx < size; idx *= 2)
		//			{
		//				startTime = System.nanoTime();
		//				timing.contains(idx);
		//				stopTime = System.nanoTime();
		//				timeDiff = stopTime - startTime;
		//				System.out.println(idx + "\t" + timeDiff);
		//			}
		//			
		//			// Clear timing binary search tree
		//			timing.clear();
		//			
		//			// Add the same N items to a new BST in a random order
		//			for (int idx = 1; idx < size; idx++)
		//			{
		//				rand = randInt.nextInt(size);
		//				while (timing.contains(rand))
		//					rand = randInt.nextInt(size);
		//				timing.add(rand);
		//			}
		//			
		//			// Record the time required to invoke the contains method for each item in the new BST. 
		//			// (Due to the randomness of this step, perform it several times and record the average running time required.)
		//			for (int idx = 1; idx < size; idx *= 2)
		//			{
		//				for (int avgIdx = 0; avgIdx < 10; avgIdx++)
		//				{
		//					startTime = System.nanoTime();
		//					timing.contains(idx);
		//					stopTime = System.nanoTime();
		//					timeDiff = stopTime - startTime;
		//					timeTotal += timeDiff;
		//				}
		//				long average = timeTotal / 10;
		//				System.out.println(average);
		//			}




	}















}


























