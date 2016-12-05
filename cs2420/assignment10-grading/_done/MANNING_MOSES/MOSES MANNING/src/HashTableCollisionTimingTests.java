package assignment10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class HashTableCollisionTimingTests {
	private static final int ITER_COUNT = 100;

	public static HashFunctor ReallyBadHashFunctor = new ReallyBadHashFunctor();
	public static HashFunctor MediocreHashFunctor = new MediocreHashFunctor();
	public static HashFunctor GoodHashFunctor = new GoodHashFunctor();

	private static int capacity = 1;
	public static ChainingHashTable chainingHashTableBad = new ChainingHashTable(capacity, ReallyBadHashFunctor);
	public static QuadProbeHashTable quadProbeHashTableBad = new QuadProbeHashTable(capacity, ReallyBadHashFunctor);
	public static ChainingHashTable chainingHashTableMed = new ChainingHashTable(capacity, MediocreHashFunctor);
	public static QuadProbeHashTable quadProbeHashTableMed = new QuadProbeHashTable(capacity, MediocreHashFunctor);
	public static ChainingHashTable chainingHashTableGood = new ChainingHashTable(capacity, GoodHashFunctor);
	public static QuadProbeHashTable quadProbeHashTableGood = new QuadProbeHashTable(capacity, GoodHashFunctor);
	public static int collCount;

	private static ArrayList<String> list;

	public static void main(String[] args) {
		timeHashFunctorCollisions();
	}

	/**
	 * Timing shindig for areAnagrams()
	 */
	private static void timeHashFunctorCollisions() {
		try (FileWriter fw = new FileWriter(new File("hashCollisions.tsv"))) { // open
			// up
			// a
			// file
			// writer
			// so
			// we
			// can
			// write
			// to
			// file.
			// Set sizes of N
			collCount = 0;
			for (int n = 2*2; n <= 131072; n *= 2) {
				long startTime, midTime, endTime;

				// Create random strings of the length of N
				for (int i = 0; i < n; i++) {
					list = new ArrayList<>();
					list.add(randomStringGenerator(n));
				}

				// Stabilize thread
				startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1_000_000_000)
					;// Stabilizes for 1 second

				// Time the areAnagram method
				startTime = System.nanoTime();
				for (int i = 0; i < ITER_COUNT; i++) {
					chainingHashTableGood.addAll(list);
				}
				// Time empty loops for midTime and endTime that will computed
				// in the averageTime

				midTime = System.nanoTime();
				for (int i = 0; i < ITER_COUNT; i++) {
				}

				endTime = System.nanoTime();
				// Compute averageTime first with midTime - startTime, then the
				// endTime - midTime, divide by the iteration count
				double averageTime = ((midTime - startTime) - (endTime - midTime)) / ITER_COUNT;
				//System.out.println(n + "\t" + averageTime + "\t" + chainingHashTableGood.collisions);
				fw.write(n + "\t" + averageTime + "\t" + chainingHashTableGood.collisions);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Charter charter = new Charter();
		charter.createChart("hashCollisions.tsv", "chart.png");
	}

	/**
	 * Creates a random string from 0 to the length specified
	 * 
	 * @param length
	 *            - length of string
	 * @return - random created string with a specified length.
	 */
	private static String randomStringGenerator(int length) {
		String stringGen = "";
		Random randomNumber = new Random();
		int randNum;
		char character;

		// Create the new string for the length of the given length
		for (int i = 0; i < length; i++) {
			randNum = (randomNumber.nextInt(5));
			character = (char) randNum;
			stringGen += character;
		}
		return stringGen;
	}
}
