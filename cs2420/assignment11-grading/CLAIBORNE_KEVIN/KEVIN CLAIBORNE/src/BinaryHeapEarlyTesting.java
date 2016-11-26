/*************************************
 * @author 	Kevin Claiborne | u1080787
 *
 * @title 	Assignment 11 - Binary Heap
 * @date	November 16, 2016
 **************************************/

package assignment11;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class BinaryHeapEarlyTesting {

	public static void main(String[] args) {

		Comparator<Integer> intComparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer arg0, Integer arg1) {
				return arg0.compareTo(arg1);
			}
		};

		PriorityQueue<Integer> pqInt = new PriorityQueue<Integer>(intComparator);

		ArrayList<Integer> intList = new ArrayList<Integer>();
		int size = 100000;
		intList.addAll(generateAverageCase(size));

		for (int i = 0; i < intList.size(); i++) {
			pqInt.add(intList.get(i));
		}

		System.out.println("Testing Started........\n\n");
		int j = 0;
		int errorCount = 0;
		for (int i = pqInt.size(); i > 0; i--) {
			int deleted = pqInt.deleteMin();
			if (deleted != j) {
				System.err.println("Danger Kevin Claiborne, Danger!!!");
				System.err.println(deleted + " " + pqInt.toString());
				errorCount++;
			}
			j++;
			// pqInt.generateDotFile("Twenty" + i + ".dot");
		}
		// pqInt.generateDotFile("Twenty.dot");
		System.out.print("\nTesting complete. There were ");
		System.out.print(errorCount);
		System.out.print(" problems found for a Priority Queue of " + size + " size. \n");
		if (errorCount == 0) {
			System.out.println("\nGreat Work Kevin!");
		}
	}

	public static ArrayList<Integer> generateAverageCase(int size) {
		Random r1 = new Random(1);
		Random r2 = new Random(2);

		ArrayList<Integer> newList = generateBestCase(size);

		for (int i = 0; i < size * 2; i++) {
			int pos1 = r1.nextInt(size);
			int pos2 = r2.nextInt(size);
			int temp = newList.get(pos1);

			newList.set(pos1, newList.get(pos2));
			newList.set(pos2, temp);
		}
		return newList;
	}

	public static ArrayList<Integer> generateBestCase(int size) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			newList.add(i);
		}
		return newList;
	}

}
