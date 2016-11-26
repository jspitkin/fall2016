package assignment08;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;


/**
 * Timing class for the BST and also for Java's TreeSet.
 * 
 * @author Stephen Hogan (u0813633) and Chris Grayston (u0906710)
 *
 */
public class BSTTimer {

	private static final int ITER_COUNT = 100;
	
	public static void main(String[] args) {
//		timeOurBSTAddToJava();
//		timeOurBSTContainsToJava();
//		timeBSTInsertion();
//		timingContainsSortedOrder();
//		timingContainsUnsortedOrder();
	}

	
	/**
	 * 
	 * Timer part 1 for the following instructions. Times our BST versus Java's
	 * TreeSet in the add method and then the contains method.
	 * 
	 * a.	Add N items to a TreeSet in a random order and record the time 
	 * 	required to do this.
	 * b.	Record the time required to invoke the contains method for 
	 * 	each item in the TreeSet.
	 * c.	Add the same N items (in the same random order) as in #1 
	 * 	to a BinarySearchTree and record the time required to do this.	
	 * d.	Record the time required to invoke the contains method for 
	 * 	each item in the BinarySearchTree.
	 * e.	Let one line of the plot be the running times found in #1 
	 * 	for each N in the range [1000, 10000] stepping by 100. 
	 * 	(Feel free to change the range, as needed, to complement your machine.) 
	 * 	Let the other line of the plot be the running times found in the #3 
	 * 	for each N in the same range as above.
	 * f.	Let one line of a new plot be the running times found in #2 
	 * 	for each N in the same range as above. Let the other line of plot 
	 * 	be the running times found in #4 for each N in the same range. 
	 * 	(You can combine the plots in the last two steps, if the y axes are similar.) 
	 */
	public static void timeOurBSTAddToJava(){
		Random random = new Random();
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		for(int exp = 1000; exp <= 10000; exp+= 1000) { 
			int size = exp;  
			
			// Do the experiment multiple times, and average out the results
			long totalTimeTS = 0;
			long totalTimeBST = 0;

			for (int iter = 0; iter < ITER_COUNT; iter++) {
				// SET UP!
				TreeSet<Integer> treeSet = new TreeSet<Integer>();
				BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
				ArrayList<Integer> randomNumbers = new ArrayList<Integer>();
				while(randomNumbers.size() != size){
					randomNumbers.add(random.nextInt(size));
				}
				// TIME IT!
				long start1 = System.nanoTime();
				treeSet.addAll(randomNumbers);
				long stop1 = System.nanoTime();
				totalTimeTS += stop1 - start1;

				// TIME IT!
				long startBST = System.nanoTime();
				bst.addAll(randomNumbers);
				long stopBST = System.nanoTime();
				totalTimeBST += stopBST - startBST;
				
			}
			double averageTimeTS = totalTimeTS / (double)ITER_COUNT;
			System.out.println("TS\t" + size + "\t" + averageTimeTS); // print to console
			
			double averageTimeBST = totalTimeBST / (double)ITER_COUNT;
			System.out.println("BST\t" + size + "\t" + averageTimeBST); // print to console
		}
	}
		
	/**
	 * 
	 * Timer part 2 for the following instructions. Times our BST versus Java's
	 * TreeSet in the add method and then the contains method.
	 * 
	 * a.	Add N items to a TreeSet in a random order and record the time 
	 * 	required to do this.
	 * b.	Record the time required to invoke the contains method for 
	 * 	each item in the TreeSet.
	 * c.	Add the same N items (in the same random order) as in #1 
	 * 	to a BinarySearchTree and record the time required to do this.	
	 * d.	Record the time required to invoke the contains method for 
	 * 	each item in the BinarySearchTree.
	 * e.	Let one line of the plot be the running times found in #1 
	 * 	for each N in the range [1000, 10000] stepping by 100. 
	 * 	(Feel free to change the range, as needed, to complement your machine.) 
	 * 	Let the other line of the plot be the running times found in the #3 
	 * 	for each N in the same range as above.
	 * f.	Let one line of a new plot be the running times found in #2 
	 * 	for each N in the same range as above. Let the other line of plot 
	 * 	be the running times found in #4 for each N in the same range. 
	 * 	(You can combine the plots in the last two steps, if the y axes are similar.) 
	 */
	public static void timeOurBSTContainsToJava(){
		Random random = new Random();
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		for(int exp = 1000; exp <= 10000; exp+= 1000) { 
			int size = exp; 
			
			// Do the experiment multiple times, and average out the results
			long totalTimeTS = 0;
			long totalTimeBST = 0;

			for (int iter = 0; iter < ITER_COUNT; iter++) {
				// SET UP!
				TreeSet<Integer> treeSet = new TreeSet<Integer>();
				BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
				ArrayList<Integer> randomNumbers = new ArrayList<Integer>();
				while(randomNumbers.size() != size){
					randomNumbers.add(random.nextInt(size));
				}
				treeSet.addAll(randomNumbers);
				bst.addAll(randomNumbers);
				randomNumbers.sort(null);
				// TIME IT!
				long start1 = System.nanoTime();
				treeSet.containsAll(randomNumbers);
				long stop1 = System.nanoTime();
				totalTimeTS += stop1 - start1;
				
				long startBST = System.nanoTime();
				bst.containsAll(randomNumbers);
				long stopBST = System.nanoTime();
				totalTimeBST += stopBST - startBST;
				
			}
			double averageTimeTS = totalTimeTS / (double)ITER_COUNT;
			System.out.println("TS\t" + size + "\t" + averageTimeTS); // print to console
			
			double averageTimeBST = totalTimeBST / (double)ITER_COUNT;
			System.out.println("BST\t" + size + "\t" + averageTimeBST); // print to console
		}
	}
		
	/**
	 * 
	 * Timer for insertion for BST in sorted vs random order and then timing the 
	 * contains method. Prints to the console the results.
	 * 
	 * a.	Add N items to a BST in sorted order, then record 
	 * 		the time required to invoke the contains method for each item in the BST.
	 * b.	Add the same N items to a new BST in a random order, 
	 * 		then record the time required to invoke the contains method 
	 * 		for each item in the new BST. (Due to the randomness of this step, 
	 * 		you may want to perform it several times and record the average 
	 * 		running time required.)
	 * c.	Let one line of the plot be the running times found in #1 
	 * 		for each N in the range [1000, 10000] stepping by 100. 
	 * 		(Feel free to change the range, as needed, to complement your machine.)  
	 * 		Let the other line of the plot be the running times found in #2 for 
	 * 		each N in the same range.
	 */
	public static void timeBSTInsertion() {
		Random random = new Random();
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1_000_000_000);
		
		for(int exp = 1000; exp <= 10000; exp+= 1000) { 
			int size = exp; 
			
			// Do the experiment multiple times, and average out the results
			long totalTimeSorted = 0;
			long totalTimeRandom = 0;

			for (int iter = 0; iter < ITER_COUNT; iter++) {
				// SET UP!
				BinarySearchTree<Integer> bst1 = new BinarySearchTree<Integer>();
				BinarySearchTree<Integer> bst2 = new BinarySearchTree<Integer>();
				ArrayList<Integer> randomNumbers = new ArrayList<Integer>();
				while(randomNumbers.size() != size){
					randomNumbers.add(random.nextInt(size));
				}
				bst1.addAll(randomNumbers);
				// TIME IT!
				long startSorted = System.nanoTime();
				bst1.contains(size + 1);
				long stopSorted = System.nanoTime();
				totalTimeSorted += stopSorted - startSorted;
				
				randomNumbers.sort(null);
				bst2.addAll(randomNumbers);

				// TIME IT!
				long startRandom = System.nanoTime();
				bst2.contains(size + 1);
				long stopRandom = System.nanoTime();
				totalTimeRandom += stopRandom - startRandom;
				
			}
			double averageTimeSorted = totalTimeSorted / (double)ITER_COUNT;
			System.out.println("Sorted\t" + size + "\t" + averageTimeSorted); // print to console
			
			double averageTimeRandom = totalTimeRandom / (double)ITER_COUNT;
			System.out.println("Random\t" + size + "\t" + averageTimeRandom); // print to console
		}
	}
		
	/**
     * Testing code for contains in sorted order
     *  - add N items in sorted order then record time required to invoke contains
     *  - add N items in random order then record time required
     *  - x-axis range is 1000 - 10000 step size is 100
     *  - y-axis range is running times found per size of N 
     */
    public static void timingContainsSortedOrder() {
        // Step through
        for (int stepSize = 1000; stepSize <= 10000; stepSize = stepSize + 1000) { 
            
            //  set current size
            int size = stepSize;

            // Do the experiment multiple times, and average out the results
            BinarySearchTree<Integer> containsTest = new BinarySearchTree<Integer>();
            
            // populate tree
            for(int i = 0; i < stepSize; i++) 
                containsTest.add(i);
                
            long totalTime = 0;
            for (int iter = ITER_COUNT; iter >= 0; --iter) {
                // Start time
                long start = System.nanoTime();

                containsTest.contains(stepSize);

                // Stop time
                long stop = System.nanoTime();
                totalTime += stop - start;
            }
            double averageTime = totalTime / (double) ITER_COUNT;
            System.out.println(size + "\t" + averageTime);
        }

        System.out.println("Timing done: Contains Sorted");
    }
    
    /**
     * Testing code for contains in sorted order
     *  - add N items in sorted order then record time required to invoke contains
     *  - add N items in random order then record tiem required
     *  - x-axis range is 1000 - 10000 step size is 100
     *  - y-axis range is running times found per size of N 
     */
    public static void timingContainsUnsortedOrder() {
    	Random random = new Random();
        // Step through
        for (int stepSize = 1000; stepSize <= 10000; stepSize = stepSize + 1000) { 
            
            //  set current size
            int size = stepSize;

            // Do the experiment multiple times, and average out the results
            BinarySearchTree<Integer> containsTest = new BinarySearchTree<Integer>();
            
            // populate tree with random numbers between 0 and stepSize - 1
            while(containsTest.size() < stepSize) {
                containsTest.add(random.nextInt(stepSize) - 1);

            }
            
            //System.out.println(containsTest.size());
            
            
                
            long totalTime = 0;
            for (int iter = 0; iter < ITER_COUNT; ++iter) {
                // Start time
                long start = System.nanoTime();

                containsTest.contains(stepSize);

                // Stop time
                long stop = System.nanoTime();
                totalTime += stop - start;
            }
            double averageTime = totalTime / (double) ITER_COUNT;
            System.out.println(size + "\t" + averageTime);
        }

        System.out.println("Timing done: Contains Unsorted");
    
    }
}
