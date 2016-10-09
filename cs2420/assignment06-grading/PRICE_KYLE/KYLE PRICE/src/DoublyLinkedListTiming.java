package assignment06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

import assignment05.SortUtil;
 
/**
 * DoublyLinkedListTiming - runs timing tests for the class DoublyLinkedList. 
 * @author Kyle Price 
 * 10/05/16
 */
class LinkedListComparator implements Comparator<Integer> { 
        @Override
        public int compare(Integer arg0, Integer arg1) { 
            return arg0.compareTo(arg1);
        }
    }

public class DoublyLinkedListTiming{
    static final int ITER_COUNT = 1000;

    public static void main(String[] args) {

        // you spin me round baby, right round
        long startTime = System.nanoTime();
        while (System.nanoTime() - startTime < 1_000_000_000);

        try (FileWriter fw = new FileWriter(new File("DoublyLinkedList_Experiments.tsv"))) { // open

            //Random random = new Random();
            for (int exp = 10; exp <= 23; exp++) {
                                                                                                    
                int size = (int) Math.pow(2, exp); 

                // Do the experiment multiple times, and average out the results
                long totalTime = 0;
                ArrayList<Integer> arrList = new ArrayList<Integer>();
				DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<Integer>();
				for (int i = 0; i < size; i++) {
					 arrList.add(i);
				}

				Random random = new Random(98);
				for (int iter = 0; iter < ITER_COUNT; iter++) {
					// TIME IT!
					long start = System.nanoTime();
					arrList.remove(random.nextInt(size));
					long stop = System.nanoTime();
					arrList.add(0);
					totalTime += stop - start;

				}
                double averageTime = totalTime / (double) ITER_COUNT;
                System.out.println(size + "\t" + averageTime); // print to
                                                                // console
                fw.write(size + "\t" + averageTime + "\n"); // write to file.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Charter charter = new Charter();
        charter.createChart("DoublyLinkedList_Experiments.tsv", "chart.png");
    }
}