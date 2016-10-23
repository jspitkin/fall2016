package assignment07;

import java.util.Random;

import assignment06.DoublyLinkedList;

public class PushPopPeelTimingTests {

	public static void main(String[] args) {

		long startTime = 0, stopTime = 0;

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) {

		}

		LinkedListStack<Integer> list;

		for (int arraySize = 1024; arraySize < 2100000; arraySize *= 2) {
			long timeToLocate = 0;
			list = new LinkedListStack<Integer>();
			for(int i = 0; i < arraySize; i++){
				list.push(i);
			}
			for (int numOfLoops = 0; numOfLoops < 100; numOfLoops++) {

				startTime = System.nanoTime();
				list.pop();
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