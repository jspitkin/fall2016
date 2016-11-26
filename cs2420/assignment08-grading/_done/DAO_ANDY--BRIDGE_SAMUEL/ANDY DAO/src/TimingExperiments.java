package assignment08;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * Timing experiments for the analysis document. 
 * This class can run the methods:
 * 	Contains on a sorted order BST
 * 	Contains on a random order BST
 * 	Add with a random order list, comparison between Java's Tree Set and our BST
 	Contains with a random order list, comparison between Java's Tree Set and our BST
 * 
 * @author Andy Dao, uID: u0692334
 * @author Sam Bridge, uID: u0984334
 */
public class TimingExperiments {

    private static final int ITER_COUNT = 100;
    private static final int STARTING_SIZE = 1000;
    private static final int MAX_SIZE = 10000;
    private static final int INCREMENT = 100;

    public static void main(String[] args) {
	//	containsSortedOrder();
	//	containsRandomOrder();
	addWithBalancedTreeAndUnBalancedTree();
	containsWithBalancedTreeAndUnBalancedTree();
    }

    /* Timing contains in sorted order method */
    public static void containsSortedOrder() {
	System.out.println("=== Testing contains() Sorted Order ===");

	long startTime = System.nanoTime();
	while (System.nanoTime() - startTime < 1000000000) {
	    ;
	}

	try (FileWriter fw = new FileWriter(new File("Contains_Timing_Sorted.tsv"))) { //open up a file writer so we can write to file.

	    // Calculate size of set
	    for (int N = STARTING_SIZE; N <= MAX_SIZE; N += 1000) { // Used as exponent. We're going to be starting from 1000 and work up to 10000
		long averageTime = 0;

		for (int i = 0; i < ITER_COUNT; i++) {
		    BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();

		    for (int j = 0; j < N; j++) {
			BST.add(j);
		    }

		    startTime = System.nanoTime();
		    for (int count = 0; count < N; count++) {
			BST.contains(count);
		    }
		    long stopTime = System.nanoTime();
		    averageTime += stopTime - startTime;
		}

		averageTime = averageTime / ITER_COUNT;
		fw.write(N + "\t" + averageTime + "\n"); // Write data to file
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Done running contains() Sorted Order Timing");
    }

    /* Timing contains in sorted order method */
    public static void containsRandomOrder() {
	System.out.println("=== Testing contains() Random Order ===");

	long startTime = System.nanoTime();
	while (System.nanoTime() - startTime < 1000000000) {
	    ;
	}

	try (FileWriter fw = new FileWriter(new File("Contains_Timing_Random.tsv"))) { //open up a file writer so we can write to file.

	    // Calculate size of set
	    for (int N = STARTING_SIZE; N <= MAX_SIZE; N += 1000) { // Used as exponent. We're going to be starting from 1000 and work up to 10000
		long averageTime = 0;

		for (int i = 0; i < ITER_COUNT; i++) {
		    ArrayList<Integer> randomOrderList = new ArrayList<Integer>();
		    BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();

		    for (int j = 0; j < N; j++) {
			randomOrderList.add(j);
		    }

		    Collections.shuffle(randomOrderList);
		    BST.addAll(randomOrderList);

		    startTime = System.nanoTime();
		    for (int count = 0; count < N; count++) {
			BST.contains(count);
		    }
		    long stopTime = System.nanoTime();
		    averageTime += stopTime - startTime;
		}

		averageTime = averageTime / ITER_COUNT;
		fw.write(N + "\t" + averageTime + "\n"); // Write data to file
	    }

	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Done running contains() Random Order Timing");
    }

    /* Timing Add Method for a Balanced vs Unbalanced BST */
    public static void addWithBalancedTreeAndUnBalancedTree() {
	System.out.println("=== Running Balanced vs Unbalanced Add Method Timing Test ===");

	long startTime, midTime, endTime;

	try (FileWriter fwBalanced = new FileWriter(new File("Add_Random_JavaTreeSet.tsv"))) { //open up a file writer so we can write to file.
	    try (FileWriter fwUnbalanced = new FileWriter(new File("Add_Random_UnbalancedBST.tsv"))) { //open up a file writer so we can write to file.

		// Calculate size of set
		for (int N = STARTING_SIZE; N <= MAX_SIZE; N += INCREMENT) {
		    TreeSet<Integer> balancedBST = new TreeSet<Integer>();
		    BinarySearchTree<Integer> unbalancedBST = new BinarySearchTree<Integer>();
		    ArrayList<Integer> randomOrderList = new ArrayList<Integer>();

		    // Add integers from 0 to N set size
		    for (int i = 0; i < N; i++) {
			randomOrderList.add(i);
		    }
		    // Shuffle the array list in random order
		    Collections.shuffle(randomOrderList);

		    // Warm up
		    startTime = System.nanoTime();
		    while (System.nanoTime() - startTime < 1000000000) {
			;
		    }

		    // Time the add method 
		    startTime = System.nanoTime();
		    for (int j = 0; j < ITER_COUNT; j++) {
			// Add the integers from the random order list 
			for (int k = 0; k < N; k++) {
			    balancedBST.add(randomOrderList.get(k));
			}
		    }

		    // Run empty loops of the same amount to subtract later the cost of running the loops
		    midTime = System.nanoTime();
		    for (int j = 0; j < ITER_COUNT; j++) {
			for (int k = 0; k < N; k++) {
			}
		    }

		    endTime = System.nanoTime();

		    // Compute average time
		    double averageTime = ((midTime - startTime) - (endTime - midTime)) / ITER_COUNT;
		    fwBalanced.write(N + "\t" + averageTime + "\n");

		    // Start to time the add method for a unbalanced BST implementation
		    startTime = System.nanoTime();
		    for (int j = 0; j < ITER_COUNT; j++) {
			// Add the integers from the random order list 
			for (int k = 0; k < N; k++) {
			    unbalancedBST.add(randomOrderList.get(k));
			}
		    }
		    // Run the empty loop to subtract later
		    midTime = System.nanoTime();
		    for (int j = 0; j < ITER_COUNT; j++) {
			for (int k = 0; k < N; k++) {
			}
		    }

		    endTime = System.nanoTime();
		    // Compute average time
		    averageTime = ((midTime - startTime) - (endTime - midTime)) / ITER_COUNT;
		    fwUnbalanced.write(N + "\t" + averageTime + "\n");
		}
		System.out.println("Done Running Balanced vs Unbalanced Add Method Timing Test");

	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    /* Timing Add Method for a Balanced vs Unbalanced BST */
    public static void containsWithBalancedTreeAndUnBalancedTree() {
	System.out.println("=== Running Balanced vs Unbalanced Contains Method Timing Test ===");

	long startTime, midTime, endTime;

	try (FileWriter fwBalanced = new FileWriter(new File("Contains_Random_JavaTreeSet.tsv"))) { //open up a file writer so we can write to file.
	    try (FileWriter fwUnbalanced = new FileWriter(new File("Contains_Random_UnbalancedBST.tsv"))) { //open up a file writer so we can write to file.

		// Calculate size of set
		for (int N = STARTING_SIZE; N <= MAX_SIZE; N += INCREMENT) {
		    TreeSet<Integer> balancedBST = new TreeSet<Integer>();
		    BinarySearchTree<Integer> unbalancedBST = new BinarySearchTree<Integer>();
		    ArrayList<Integer> randomOrderList = new ArrayList<Integer>();

		    // Add integers from 0 to N set size
		    for (int i = 0; i < N; i++) {
			randomOrderList.add(i);
		    }
		    // Shuffle the array list in random order
		    Collections.shuffle(randomOrderList);

		    // Warm up
		    startTime = System.nanoTime();
		    while (System.nanoTime() - startTime < 1000000000) {
			;
		    }

		    // Setup the Balanced and Unbalanced Trees
		    balancedBST.addAll(randomOrderList);
		    unbalancedBST.addAll(randomOrderList);

		    // Start to time the contains method for a balanced BST implementation
		    startTime = System.nanoTime();
		    for (int j = 0; j < ITER_COUNT; j++) {
			for (int k = 0; k < N; k++) {
			    balancedBST.contains(k);
			}
		    }

		    // Run empty loops of the same amount to subtract later the cost of running the loops
		    midTime = System.nanoTime();
		    for (int j = 0; j < ITER_COUNT; j++) {
			for (int k = 0; k < N; k++) {
			}
		    }

		    endTime = System.nanoTime();

		    // Compute average time
		    double averageTime = ((midTime - startTime) - (endTime - midTime)) / ITER_COUNT;
		    fwBalanced.write(N + "\t" + averageTime + "\n");

		    // Start to time the add method for a unbalanced BST implementation
		    startTime = System.nanoTime();
		    for (int j = 0; j < ITER_COUNT; j++) {
			// Add the integers from the random order list 
			for (int k = 0; k < N; k++) {
			    unbalancedBST.contains(k);
			}
		    }
		    // Run the empty loop to subtract later
		    midTime = System.nanoTime();
		    for (int j = 0; j < ITER_COUNT; j++) {
			for (int k = 0; k < N; k++) {
			}
		    }

		    endTime = System.nanoTime();
		    // Compute average time
		    averageTime = ((midTime - startTime) - (endTime - midTime)) / ITER_COUNT;
		    fwUnbalanced.write("Contains Method with an Unbalanced BST:\t" + N + "\t" + averageTime + "\n");
		}
		System.out.println("Done Running Balanced vs Unbalanced Contains Method Timing Test");
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
