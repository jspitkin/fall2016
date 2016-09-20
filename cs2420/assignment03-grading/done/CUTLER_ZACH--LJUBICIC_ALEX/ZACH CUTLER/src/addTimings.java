package assignment03;

import java.lang.reflect.Array;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/**
 * timing class for the add method
 * @author Zachary Cutler (u1025642) and Alessandro Ljubicic (U0753409)
 *
 */
public class addTimings {

	public static void main(String[] args) {

		long startTime, stopTime;

		startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000) {

		}

		BinarySearchSet newSet = new BinarySearchSet();

		for (int arraySize = 1000; arraySize < 1500000; arraySize *= 2) {

			long timeToLocate = 0;

			for (int numOfLoops = 0; numOfLoops < 1001; numOfLoops++) {

				Object[] newArray = randomArray(arraySize);
				
				newSet.setData(newArray);

				newSet.setCount(arraySize - 1);

				int randomNumber = ThreadLocalRandom.current().nextInt(0, arraySize + 1);

				newSet.remove(randomNumber);

				startTime = System.nanoTime();
				newSet.add(randomNumber);
				stopTime = System.nanoTime();

				timeToLocate += (stopTime - startTime);

				if (numOfLoops + 1 == 1001) {
					Long averageTimeToAdd = timeToLocate / numOfLoops;
					int size = arraySize;

					System.out.println(averageTimeToAdd + " " + size);
				}

			}

		}
	}

	public static Object[] randomArray(Object x) {
		// array to store N random integers (0 - N-1)
		Object[] newArray = new Object[(int) x];

		// initialize each value at index i to the value i
		for (int i = 0; i < newArray.length; ++i) {
			newArray[i] = i;
		}

		Random randomGenerator = new Random();

		int randomIndex; // the randomly selected index each time through the
							// loop
		int randomValue; // the value at newArray[randomIndex] each time through
							// the loop

		// randomize order of values
		for (int i = 0; i < newArray.length; ++i) {
			// select a random index
			randomIndex = randomGenerator.nextInt(newArray.length);

			// swap values
			randomValue = (int) newArray[randomIndex];
			newArray[randomIndex] = newArray[i];
			newArray[i] = i;
		}

		return newArray;
	}

}