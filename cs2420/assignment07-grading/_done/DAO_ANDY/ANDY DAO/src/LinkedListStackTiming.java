package assignment07;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Timing test class for LinkedList Stack, for push, pop, peek.
 * 
 * @author Andy Dao, uID: u0692334
 */
public class LinkedListStackTiming {

    private static final int ITER_COUNT = 10000;

    public static void main(String[] args) {
	pushStack();
	popStack();
	peekStack();
    }

    /**
     * Timing method for the push method with a DoublyLinkedList data structure
     * of a stack.
     */
    private static void pushStack() {
	long startTime = System.nanoTime();
	while (System.nanoTime() - startTime < 1000000000) {
	    ;
	}

	try (FileWriter fw = new FileWriter(new File("pushStack_Experiment.tsv"))) { //open up a file writer so we can write to file.
	    System.out.println("Starting timing pushStack");

	    // Calculate size of set
	    for (int exponent = 10; exponent <= 20; exponent++) { // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
		int size = (int) Math.pow(2, exponent);

		// Now do the experiment multiple times then average out the results
		long totalTime = 0; // Start total time at 0.

		/* SETUP STACK */
		LinkedListStack<Integer> stack = new LinkedListStack<>();
		for (int addIndex = 0; addIndex < size; addIndex++) {
		    stack.push(addIndex);
		}

		for (int iterate = 0; iterate < ITER_COUNT; iterate++) {

		    /* TIME THE CODE */
		    long start = System.nanoTime();
		    stack.push(10);
		    long stop = System.nanoTime();
		    totalTime += stop - start;

		    // Pop what we pushed
		    stack.pop();

		}
		double averageTime = totalTime / (double) ITER_COUNT;
		fw.write(size + "\t" + averageTime + "\n"); // Write data to file
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Done timing pushStack");
    }

    /**
     * Timing method for the pop method with a DoublyLinkedList data structure
     * of a stack.
     */
    private static void popStack() {
	long startTime = System.nanoTime();
	while (System.nanoTime() - startTime < 1000000000) {
	    ;
	}

	try (FileWriter fw = new FileWriter(new File("popStack_Experiment.tsv"))) { //open up a file writer so we can write to file.
	    System.out.println("Starting timing popStack");

	    // Calculate size of set
	    for (int exponent = 10; exponent <= 20; exponent++) { // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
		int size = (int) Math.pow(2, exponent);

		// Now do the experiment multiple times then average out the results
		long totalTime = 0; // Start total time at 0.

		/* SETUP STACK */
		LinkedListStack<Integer> stack = new LinkedListStack<>();
		for (int addIndex = 0; addIndex < size; addIndex++) {
		    stack.push(addIndex);
		}

		/* TIME THE CODE */
		for (int iterate = 0; iterate < ITER_COUNT; iterate++) {
		    stack.push(10);

		    /* TIME THE METHOD */
		    long start = System.nanoTime();
		    stack.pop();
		    long stop = System.nanoTime();
		    totalTime += stop - start;

		    // Re-push what we popped
		    stack.push(10);

		}
		double averageTime = totalTime / (double) ITER_COUNT;
		fw.write(size + "\t" + averageTime + "\n"); // Write data to file
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Done timing popStack");
    }

    /**
     * Timing method for the peek method with a DoublyLinkedList data structure
     * of a stack.
     */
    private static void peekStack() {
	long startTime = System.nanoTime();
	while (System.nanoTime() - startTime < 1000000000) {
	    ;
	}

	try (FileWriter fw = new FileWriter(new File("peekStack_Experiment.tsv"))) { //open up a file writer so we can write to file.
	    System.out.println("Starting timing peekStack");

	    // Calculate size of set
	    for (int exponent = 10; exponent <= 20; exponent++) { // Used as exponent. We're going to be starting from 2^10, working up to 2^20.
		int size = (int) Math.pow(2, exponent);

		// Now do the experiment multiple times then average out the results
		long totalTime = 0; // Start total time at 0.

		/* SETUP STACK */
		LinkedListStack<Integer> stack = new LinkedListStack<>();
		for (int addIndex = 0; addIndex < size; addIndex++) {
		    stack.push(addIndex);
		}
		/* TIME THE CODE */
		for (int iterate = 0; iterate < ITER_COUNT; iterate++) {

		    /* TIME THE METHOD */
		    long start = System.nanoTime();
		    stack.peek();
		    long stop = System.nanoTime();
		    totalTime += stop - start;
		}
		double averageTime = totalTime / (double) ITER_COUNT;
		fw.write(size + "\t" + averageTime + "\n"); // Write data to file
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	System.out.println("Done timing peekStack");
    }

}
