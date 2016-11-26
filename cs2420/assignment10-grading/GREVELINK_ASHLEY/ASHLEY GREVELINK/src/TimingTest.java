package assignment10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Timing for QuadProbeHashTable and ChainingHashTable for 
 * comparing those hash tables and the three hash functors.
 * 
 * @author Ashley Grevelink (u0749357)
 */
public class TimingTest {
	private static final int ITER_COUNT = 5;

	public static void main(String[] args) {
		collisionsBHF();
		collisionsMHF();
		collisionsGHF();

		timeBHF();
		timeMHF();
		timeGHF();

		collisionsSC();
		timeSC();
	}

	private static void collisionsBHF() {
		try (FileWriter fw = new FileWriter(new File("collisionsBHF_experiment.tsv"))) { // open
																							// file.
			for (int exp = 10; exp <= 14; exp++) {
				int size = (int) Math.pow(2, exp);
				double averageCollisions = 0;
				int collisions = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					QuadProbeHashTable myQPHT = new QuadProbeHashTable(size, new BadHashFunctor());
					
					ArrayList<String> myAL = new ArrayList<String>(size);
					for (int index = 1; index < size; index++) {
						Integer item = (Integer) index;
						String itemString = item.toString();
						myAL.add(itemString);
					}
					
					myQPHT.addAll(myAL);
					collisions += myQPHT.collisions();
				}
				
				averageCollisions = (double) collisions / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageCollisions); // print to
																// console
				fw.write(size + "\t" + averageCollisions + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void timeBHF() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);

		try (FileWriter fw = new FileWriter(new File("timeBHF_experiment.tsv"))) { // open
																					// file.
			for (int exp = 10; exp <= 14; exp++) {
				int size = (int) Math.pow(2, exp);
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {

					QuadProbeHashTable myQPHT = new QuadProbeHashTable(size, new BadHashFunctor());
					
					ArrayList<String> myAL = new ArrayList<String>(size);
					for (int index = 1; index < size; index++) {
						Integer item = (Integer) index;
						String itemString = item.toString();
						myAL.add(itemString);
					}
					
					long start = System.nanoTime();
					myQPHT.addAll(myAL);
					long stop = System.nanoTime();
					
					totalTime += stop - start;
				}
				// subtracting the for-loop time from total time first
				double averageTime = totalTime / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void collisionsMHF() {
		try (FileWriter fw = new FileWriter(new File("collisionsMHF_experiment.tsv"))) { // open
																							// file.
			for (int exp = 10; exp <= 14; exp++) {
				int size = (int) Math.pow(2, exp);
				double averageCollisions = 0;
				int collisions = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					QuadProbeHashTable myQPHT = new QuadProbeHashTable(size, new MediocreHashFunctor());
					
					ArrayList<String> myAL = new ArrayList<String>(size);
					for (int index = 1; index < size; index++) {
						Integer item = (Integer) index;
						String itemString = item.toString();
						myAL.add(itemString);
					}
					
					myQPHT.addAll(myAL);
					collisions += myQPHT.collisions();
				}
				
				averageCollisions = (double) collisions / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageCollisions); // print to
																// console
				fw.write(size + "\t" + averageCollisions + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void timeMHF() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);

		try (FileWriter fw = new FileWriter(new File("timeMHF_experiment.tsv"))) { // open
																					// file.
			for (int exp = 10; exp <= 14; exp++) {
				int size = (int) Math.pow(2, exp);
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {

					QuadProbeHashTable myQPHT = new QuadProbeHashTable(size, new MediocreHashFunctor());
					
					ArrayList<String> myAL = new ArrayList<String>(size);
					for (int index = 1; index < size; index++) {
						Integer item = (Integer) index;
						String itemString = item.toString();
						myAL.add(itemString);
					}
					
					long start = System.nanoTime();
					myQPHT.addAll(myAL);
					long stop = System.nanoTime();
					
					totalTime += stop - start;
				}
				// subtracting the for-loop time from total time first
				double averageTime = totalTime / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void collisionsGHF() {
		try (FileWriter fw = new FileWriter(new File("collisionsGHF_experiment.tsv"))) { // open
																							// file.
			for (int exp = 10; exp <= 14; exp++) {
				int size = (int) Math.pow(2, exp);
				double averageCollisions = 0;
				int collisions = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					QuadProbeHashTable myQPHT = new QuadProbeHashTable(size, new GoodHashFunctor());
					
					ArrayList<String> myAL = new ArrayList<String>(size);
					for (int index = 1; index < size; index++) {
						Integer item = (Integer) index;
						String itemString = item.toString();
						myAL.add(itemString);
					}
					
					myQPHT.addAll(myAL);
					collisions += myQPHT.collisions();
				}
				
				averageCollisions = (double) collisions / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageCollisions); // print to
																// console
				fw.write(size + "\t" + averageCollisions + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void timeGHF() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);

		try (FileWriter fw = new FileWriter(new File("timeGHF_experiment.tsv"))) { // open
																					// file.
			for (int exp = 10; exp <= 14; exp++) {
				int size = (int) Math.pow(2, exp);
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {

					QuadProbeHashTable myQPHT = new QuadProbeHashTable(size, new GoodHashFunctor());
					
					ArrayList<String> myAL = new ArrayList<String>(size);
					for (int index = 1; index < size; index++) {
						Integer item = (Integer) index;
						String itemString = item.toString();
						myAL.add(itemString);
					}
					
					long start = System.nanoTime();
					myQPHT.addAll(myAL);
					long stop = System.nanoTime();
					
					totalTime += stop - start;
				}
				// subtracting the for-loop time from total time first
				double averageTime = totalTime / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void collisionsSC() {
		try (FileWriter fw = new FileWriter(new File("collisionsSC_experiment.tsv"))) { // open
																							// file.
			for (int exp = 10; exp <= 14; exp++) {
				int size = (int) Math.pow(2, exp);
				double averageCollisions = 0;
				int collisions = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {
					ChainingHashTable mySCHT = new ChainingHashTable(size, new GoodHashFunctor());
					
					ArrayList<String> myAL = new ArrayList<String>(size);
					for (int index = 1; index < size; index++) {
						Integer item = (Integer) index;
						String itemString = item.toString();
						myAL.add(itemString);
					}
					
					mySCHT.addAll(myAL);
					collisions += mySCHT.collisions();
				}
				
				averageCollisions = (double) collisions / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageCollisions); // print to
																// console
				fw.write(size + "\t" + averageCollisions + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void timeSC() {
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);

		try (FileWriter fw = new FileWriter(new File("timeSC_experiment.tsv"))) { // open
																					// file.
			for (int exp = 10; exp <= 14; exp++) {
				int size = (int) Math.pow(2, exp);
				// Do the experiment multiple times, and average out the results
				long totalTime = 0;

				for (int iter = 0; iter < ITER_COUNT; iter++) {

					ChainingHashTable mySCHT = new ChainingHashTable(size, new GoodHashFunctor());
					
					ArrayList<String> myAL = new ArrayList<String>(size);
					for (int index = 1; index < size; index++) {
						Integer item = (Integer) index;
						String itemString = item.toString();
						myAL.add(itemString);
					}
					
					long start = System.nanoTime();
					mySCHT.addAll(myAL);
					long stop = System.nanoTime();
					
					totalTime += stop - start;
				}
				// subtracting the for-loop time from total time first
				double averageTime = totalTime / (double) ITER_COUNT;
				System.out.println(size + "\t" + averageTime); // print to
																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
