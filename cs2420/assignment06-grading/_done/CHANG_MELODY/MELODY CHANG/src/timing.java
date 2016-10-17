package assignment06;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Ching-Yuan Chang u0914005
 */

public class timing {

	public static void main(String[] args) {
		long startTime = 0;
		long stopTime = 0;
		long totalTime = 0;
		// int wordSize = 10;
		int size = 100000;
		Random rand = new Random();

		for (int j = 0; j < 10; j++) {
			DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
			ArrayList<Integer> array = new ArrayList<Integer>();

			for (int k = 0; k < size; k++) {
				list.addFirst(k);
				 array.add(k);

			}
			int get = size/2;
			for (int i = 0; i < 10; i++) {
				startTime = System.nanoTime();
//				list.get(size/2);
//				array.get(get);
				list.remove(get);
//				array.remove(get);
				stopTime = System.nanoTime();
				totalTime += stopTime - startTime;
			}

			double averageTime = totalTime / 10;

			System.out.println(size + "\t" + averageTime);
			size *= 2;
			list.clear();
			array.clear();

			// double averageTime = totalTime / 3;
			//
			// System.out.println(" " + "\t" + size + "\t" + averageTime);

		}
	}
}
