package assignment10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import assignment03.BinarySearchSet;

/**
 * @author Eduardo Ortiz u0922628
 *
 */
public class TimingTest{
	
public static void main(String[] args) {
	timer(new BadHashFunctor());
	//timer(new MediocreHashFunctor());
	//timer(new GoodHashFunctor());
}

private static void timer(HashFunctor hash) {
	System.out.println("\n" + "QB Table " + hash.getClass() + "\n");

	int timesToLoop = 300;
	for (int n = 1000; n <= 10000; n += 1000) {

		long startTime, midpointTime, stopTime;

		startTime = System.nanoTime();

		while (System.nanoTime() - startTime < 1000000000) { 
		}
		startTime = System.nanoTime();

		for (int i=0;i<(timesToLoop);i++) {
			QuadProbeHashTable set = new QuadProbeHashTable(89, hash);
			set.addAll(generateRandomCollection(n, timesToLoop));
		}

		midpointTime = System.nanoTime();

		for (long i = 0; i < timesToLoop; i++) { 
			QuadProbeHashTable set = new QuadProbeHashTable(89, hash);
			generateRandomCollection(n, timesToLoop);
		}

		stopTime = System.nanoTime();

		double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
				/ timesToLoop;
		System.out.println(n + "\t" + averageTime);	
	}}

	public static Collection<String> generateRandomCollection(int size, int seed) {

		String[] arr = new String[size];
		Random rng = new Random(seed);
		
		for (int i=0; i<arr.length; i++) {
			int strLength = rng.nextInt(50);
			char[] c = new char[strLength]; 
			for(int j = 0; j < strLength; j++){
				c[j] = (char) ('a'+rng.nextInt(26) );  	
			}
			arr[i] = c.toString();
		}
		return Arrays.asList(arr);
	}}