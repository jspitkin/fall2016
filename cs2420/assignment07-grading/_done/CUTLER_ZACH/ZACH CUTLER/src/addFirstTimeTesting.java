package assignment06;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 * timing class for the methods in DoublyLinkedList
 *@author Zachary Cutler (u1025642)
 */
public class addFirstTimeTesting {

	public static void main(String[] args) {

		long startTime = 0, stopTime = 0;

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) {

		}

		DoublyLinkedList<Integer> list;

		for (int arraySize = 1024; arraySize < 2000000; arraySize *= 2) {
			long timeToLocate = 0;
			list = new DoublyLinkedList<Integer>();
			for(int i = 0; i < arraySize; i++){
				list.addFirst(i);
			}
			for (int numOfLoops = 0; numOfLoops < 100; numOfLoops++) {

				Random rand = new Random();
				int random = rand.nextInt(arraySize - 10);
				startTime = System.nanoTime();
				list.remove(arraySize/2);
				stopTime = System.nanoTime();

				
				timeToLocate += (stopTime - startTime);

				if (numOfLoops + 1 == 100) {
					Long averageTimeToLocate = (timeToLocate / 100);
					int size = arraySize;

					System.out.println(averageTimeToLocate + " " + size);
				}
			}
		}
	}
}
